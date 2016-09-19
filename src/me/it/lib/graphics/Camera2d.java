package me.it.lib.graphics;

import me.oso.lib.math.ViewMatrix;

/**
 * Created by Thomas on 19 sept. 2016
 */
public class Camera2d {

	private ViewMatrix matrix;
	
	public Camera2d() {
		this.matrix = new ViewMatrix();
	}

	public ViewMatrix getMatrix() {
		return matrix;
	}
}
