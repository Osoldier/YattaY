package me.oso.yattay.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

import me.oso.yattay.world.Level;

/**
 * Created by Thomas on 29 sept. 2016
 */
public class Saver {

	private static final byte VERSION = 0;

	public static void Save(Level l, String filename) {
		File file = new File(filename + ".ymf");
		byte[] header = ByteBuffer.allocate(1).put(VERSION).array();
		byte[] width = ByteBuffer.allocate(4).putInt(l.getLevel().length).array();
		byte[] height = ByteBuffer.allocate(4).putInt(l.getLevel()[0].length).array();
		byte[] sr = ByteBuffer.allocate(8).putInt(l.getRedX()).putInt(l.getRedY()).array();
		byte[] sb = ByteBuffer.allocate(8).putInt(l.getBlueX()).putInt(l.getBlueY()).array();

		FileOutputStream fos;
		try {
			fos = new FileOutputStream(file);
			fos.write(header, 0, header.length);
			fos.write(width, 0, width.length);
			fos.write(height, 0, height.length);
			fos.write(sr, 0, sr.length);
			fos.write(sb, 0, sb.length);
			for (int i = 0; i < l.getLevel().length; i++) {
				for (int j = 0; j < l.getLevel()[0].length; j++) {
					byte[] block = ByteBuffer.allocate(4).putInt(l.getLevel()[i][j].getType().getTexture()).array();
					fos.write(block, 0, block.length);
				}
			}
			fos.flush();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
