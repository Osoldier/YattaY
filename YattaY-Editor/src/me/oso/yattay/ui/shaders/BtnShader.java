package me.oso.yattay.ui.shaders;

import me.oso.lib.graphics.Shader;
import me.oso.lib.math.*;

/**
 * Created by Thomas on 24 sept. 2016
 */
public class BtnShader extends Shader {

	private static final String VERT_FILE = "src/me/oso/yatty/ui/shaders/btn.vert";
	private static final String FRAG_FILE = "src/me/oso/yatty/ui/shaders/btn.frag";
	
	private ProjectionMatrix prMat;
	private ModelMatrix mlMat;
	
	private int prMatLoc, mlMatLoc;
	
	public BtnShader(ProjectionMatrix prMat) {
		super(VERT_FILE, FRAG_FILE);
		this.prMat = prMat;
		this.mlMat = new ModelMatrix();
		this.prMatLoc = this.getUniformLocation("prMat");
		this.mlMatLoc = this.getUniformLocation("mlMat");
	}

	@Override
	public void loadUniforms() {
		this.setUniform(mlMatLoc, mlMat);
	}

	@Override
	public void loadFrameUniforms() {
		this.setUniform(prMatLoc, prMat);
	}

	public ModelMatrix getMlMat() {
		return mlMat;
	}
	
}
