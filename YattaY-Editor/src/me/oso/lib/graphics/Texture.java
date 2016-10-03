package me.oso.lib.graphics;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.glGenerateMipmap;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL14;
import org.lwjgl.stb.STBImage;

public class Texture {

	private int id;

	private static final int BYTES_PER_PIXEL = 4;// 3 for RGB, 4 for RGBA

	public Texture(String pLocation, float bias) {
		this.id = loadTexture(pLocation);
	}

	public Texture(String pLocation) {
		this.id = loadTexture(pLocation);
	}

	public Texture(int id) {
		this.id = id;
	}

	public void bind() {
		glBindTexture(GL_TEXTURE_2D, id);
	}

	public void release() {
		glDeleteTextures(id);
	}

	@Override
	protected void finalize() throws Throwable {
		glDeleteTextures(id);
	}

	public static void bindNone() {
		glBindTexture(GL_TEXTURE_2D, GL13.GL_TEXTURE0);
	}

	/*
	private int loadTexture(BufferedImage image, boolean mipmap, float bias) {

		int[] pixels = new int[image.getWidth() * image.getHeight()];
		image.getRGB(0, 0, image.getWidth(), image.getHeight(), pixels, 0, image.getWidth());

		ByteBuffer buffer = BufferUtils.createByteBuffer(image.getWidth() * image.getHeight() * BYTES_PER_PIXEL);

		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				int pixel = pixels[y * image.getWidth() + x];
				buffer.put((byte) ((pixel >> 16) & 0xFF)); // Red component
				buffer.put((byte) ((pixel >> 8) & 0xFF)); // Green component
				buffer.put((byte) (pixel & 0xFF)); // Blue component
				buffer.put((byte) ((pixel >> 24) & 0xFF)); // Alpha component.
				// Only for RGBA
			}
		}

		buffer.flip();

		int textureID = glGenTextures(); // Generate texture ID
		glBindTexture(GL_TEXTURE_2D, textureID); // Bind texture ID

		// Setup wrap mode
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);

		// Setup texture scaling filtering
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

		// Send texel data to OpenGL
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, image.getWidth(), image.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);
		
		if (mipmap) {
			glGenerateMipmap(GL_TEXTURE_2D);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR_MIPMAP_LINEAR);
			glTexParameterf(GL_TEXTURE_2D, GL14.GL_TEXTURE_LOD_BIAS, bias);
		}
		// Return the texture ID so we can bind it later again
		return textureID;
	}
	*/
	
	public int loadTexture(String filename){
	    ByteBuffer imageBuffer;
	    int width, height, comp;
	    try{
	        imageBuffer = readFile(filename);
	    }
	    catch (IOException e) {
	        throw new RuntimeException(e);
	    }

	    IntBuffer w = BufferUtils.createIntBuffer(1);
	    IntBuffer h = BufferUtils.createIntBuffer(1);
	    IntBuffer compa = BufferUtils.createIntBuffer(1);

	    ByteBuffer image = STBImage.stbi_load_from_memory(imageBuffer, w, h, compa, 0);
	    if(image == null){
	        throw new RuntimeException("Failed to load image: " + STBImage.stbi_failure_reason());
	    }

	    width = w.get(0);
	    height = h.get(0);
	    comp = compa.get(0);

	    int id = glGenTextures();
	    glBindTexture(GL_TEXTURE_2D, id);
	    
	    if(comp == 3){
	        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGB, width, height, 0, GL_RGB, GL_UNSIGNED_BYTE, image);
	    }
	    else{
	        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, image);

	        glEnable(GL_BLEND);
	        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
	    }

	    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
	    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);

	    return id;
	}

	private ByteBuffer readFile(String resource) throws IOException{
	    File file = new File(resource);

	    FileInputStream fis = new FileInputStream(file);
	    FileChannel fc = fis.getChannel();

	    ByteBuffer buffer = BufferUtils.createByteBuffer((int) fc.size() + 1);

	    while(fc.read(buffer) != -1);

	    fis.close();
	    fc.close();
	    buffer.flip();

	    return buffer;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}