package me.oso.yattay.ui.shaders;

import me.oso.lib.graphics.Shader;

/**
 * Created by Thomas on 24 sept. 2016
 */
public class BtnShader extends Shader {

	private static final String VERT_FILE = "src/me/oso/yatty/ui/shaders/btn.vert";
	private static final String FRAG_FILE = "src/me/oso/yatty/ui/shaders/btn.frag";
	
	public BtnShader() {
		super(VERT_FILE, FRAG_FILE);
	}

	@Override
	public void loadUniforms() {
		
	}

	@Override
	public void loadFrameUniforms() {
		
	}
	
}
