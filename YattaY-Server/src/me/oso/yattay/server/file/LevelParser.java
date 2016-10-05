package me.oso.yattay.server.file;

import java.io.*;
import java.nio.*;

import me.oso.yattay.world.*;

/**
 * Created by Thomas on 29 sept. 2016
 */
public class LevelParser {

	private static final byte VERSION = 0;

	public static void save(Level l, String filename) {
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

	public static Level load(String filename) {
		File file = new File(filename);
		FileInputStream inFile = null;
		Level level = null;
		try {
			inFile = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace(System.err);
		}
		try {
			byte[] version = new byte[1];
			byte[] int32 = new byte[4];
			inFile.read(version);
			if(version[0] <= VERSION) {
				System.out.println("Version: "+version[0]);
				inFile.read(int32);
				int width = ByteBuffer.wrap(int32).getInt();
				inFile.read(int32);
				int height = ByteBuffer.wrap(int32).getInt();
				level = new Level(width, height);
				inFile.read(int32);
				level.setRedX(ByteBuffer.wrap(int32).getInt());
				inFile.read(int32);
				level.setRedY(ByteBuffer.wrap(int32).getInt());
				inFile.read(int32);
				level.setBlueX(ByteBuffer.wrap(int32).getInt());
				inFile.read(int32);
				level.setBlueY(ByteBuffer.wrap(int32).getInt());
				for (int i = 0; i < width; i++) {
					for (int j = 0; j < height; j++) {
						inFile.read(int32);
						int id = ByteBuffer.wrap(int32).getInt();
						level.getLevel()[i][j] = new Block(typeFromID(id));
					}
				}
			}
			inFile.close();
		} catch (IOException e) {
			e.printStackTrace(System.err);
		}
		return level;
	}
	
	private static BlockType typeFromID(int id) {
		for (BlockType bt : BlockType.values()) {
			if(bt.getTexture() == id) {
				return bt;
			}
		}
		return BlockType.AIR;
	}

}
