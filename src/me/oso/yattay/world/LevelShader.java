package me.oso.yattay.world;

import me.it.lib.graphics.*;

/**
 * LevelShader.java
 * @author Ibanez Thomas
 * @date 18 sept. 2016
 */
public class LevelShader extends Shader {

	private static final String VERT_FILE = "res/";
	private static final String FRAG_FILE = "";
	
	public LevelShader() {
		super(VERT_FILE, FRAG_FILE);
	}

	@Override
	public void loadUniforms() {
		
	}
	
}
