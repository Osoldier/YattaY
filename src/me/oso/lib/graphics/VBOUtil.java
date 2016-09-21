package me.oso.lib.graphics;

import java.nio.*;
import java.util.*;

public class VBOUtil {

	public static ByteBuffer createByteBuffer(byte[] array) {
		ByteBuffer result = ByteBuffer.allocateDirect(array.length).order(ByteOrder.nativeOrder());
		result.put(array).flip();
		return result;
	}

	public static FloatBuffer createFloatBuffer(float[] array) {
		FloatBuffer result = ByteBuffer.allocateDirect(array.length << 2).order(ByteOrder.nativeOrder()).asFloatBuffer();
		result.put(array).flip();
		return result;
	}

	public static IntBuffer createIntBuffer(int[] array) {
		IntBuffer result = ByteBuffer.allocateDirect(array.length << 2).order(ByteOrder.nativeOrder()).asIntBuffer();
		result.put(array).flip();
		return result;
	}

	public static float[] toFloatArray(ArrayList<Float> e) {
		float[] floatArray = new float[e.size()];
		int i = 0;

		for (Float f : e) {
			floatArray[i++] = (f != null ? f : Float.NaN);
		}

		return floatArray;
	}

	public static float[] FloatListToArray(List<Float> e) {
		float[] f = new float[e.size()];
		int i = 0;
		for (Float fl : e) {
			f[i] = fl;
			i++;
		}
		return f;
	}
	
	public static int[] IntListToArray(List<Integer> e) {
		int[] f = new int[e.size()];
		int i = 0;
		for (Integer fl : e) {
			f[i] = fl;
			i++;
		}
		return f;
	}
}
