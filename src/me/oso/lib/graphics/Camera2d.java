package me.oso.lib.graphics;

import me.oso.lib.math.*;

/**
 * Created by Thomas on 19 sept. 2016
 */
public class Camera2d {

	private ViewMatrix matrix;
	private Vector3f position;
	private final Vector3f angles;
	
	public Camera2d() {
		this(0,0);
	}
	
	public Camera2d(float x, float y) {
		this.position = new Vector3f(x, y, 0);
		this.matrix = new ViewMatrix();
		this.angles = new Vector3f();
	}
	
	public void lookThrough() {
		this.matrix.Transform(position, angles);
	}


	public ViewMatrix getMatrix() {
		return matrix;
	}

	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}
}
