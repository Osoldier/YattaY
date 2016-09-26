package me.oso.yattay.ui.shaders;

import me.oso.lib.graphics.Shader;
import me.oso.lib.math.*;

/**
 * Created by Thomas on 24 sept. 2016
 */
public class BtnShader extends Shader {

	private static final String VERT_FILE = "src/me/oso/yattay/ui/shaders/btn.vert";
	private static final String FRAG_FILE = "src/me/oso/yattay/ui/shaders/btn.frag";
	
	private ProjectionMatrix prMat;
	
	private int prMatLoc;
	
	public BtnShader(ProjectionMatrix prMat) {
		super(VERT_FILE, FRAG_FILE);
		this.prMat = prMat;
		this.prMatLoc = this.getUniformLocation("prMat");
	}

	@Override
	public void loadUniforms() {
	}

	@Override
	public void loadFrameUniforms() {
		this.setUniform(prMatLoc, prMat);
	}
	
}
