package me.oso.yattay.editor.mask;

import me.oso.lib.graphics.*;
import me.oso.lib.math.*;

/**
 * MaskShader.java
 * @author Ibanez Thomas
 * @date 27 sept. 2016
 */
public class MaskShader extends Shader {

	public static final String VERT_FILE = "src/res/shaders/mask.vert";
	public static final String FRAG_FILE = "src/res/shaders/mask.frag";
	private ProjectionMatrix pr;
	private int size, blockSize;
	private Vector2f position;
	
	private int prLoc, sizeLoc, blockSizeLoc, posLoc;
	
	public MaskShader(ProjectionMatrix pr) {
		super(VERT_FILE, FRAG_FILE);
		this.pr = pr;
		this.position = new Vector2f();
		this.prLoc = this.getUniformLocation("prMat");
		this.sizeLoc = this.getUniformLocation("size");
		this.blockSizeLoc = this.getUniformLocation("blockSize");
		this.posLoc = this.getUniformLocation("xyPosition");
	}


	@Override
	public void loadUniforms() {
		this.setUniform(sizeLoc, size);
		this.setUniform(blockSizeLoc, blockSize);
		this.setUniform(posLoc, position);
	}

	@Override
	public void loadFrameUniforms() {
		this.setUniform(prLoc, pr);
	}


	public int getSize() {
		return size;
	}


	public void setSize(int size) {
		this.size = size;
	}


	public int getBlockSize() {
		return blockSize;
	}


	public void setBlockSize(int blockSize) {
		this.blockSize = blockSize;
	}


	public Vector2f getPosition() {
		return position;
	}


	public void setPosition(Vector2f position) {
		this.position = position;
	}

}
