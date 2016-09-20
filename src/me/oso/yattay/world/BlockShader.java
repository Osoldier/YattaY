package me.oso.yattay.world;

import me.it.lib.graphics.Shader;
import me.it.lib.graphics.Texture;
import me.oso.lib.math.ModelMatrix;
import me.oso.lib.math.ProjectionMatrix;
import me.oso.lib.math.ViewMatrix;

/**
 * LevelShader.java
 * @author Ibanez Thomas
 * @date 18 sept. 2016
 */
public class BlockShader extends Shader {

	private static final String VERT_FILE = "src/res/shaders/block.vert";
	private static final String FRAG_FILE = "src/res/shaders/block.frag";
	
	private int texLoc, mlMatLoc, vwMatLoc, prMatLoc;
	private ModelMatrix mlMat;
	private ViewMatrix vwMat;
	private ProjectionMatrix prMat;
	private Texture tex;
	
	public BlockShader() {
		super(VERT_FILE, FRAG_FILE);
		texLoc = this.getUniformLocation("texture");
		mlMatLoc = this.getUniformLocation("mlMat");
		vwMatLoc = this.getUniformLocation("vwMat");
		prMatLoc = this.getUniformLocation("prMat");
		this.mlMat = new ModelMatrix();
	}

	@Override
	public void loadUniforms() {
		this.setUniform(mlMatLoc, mlMat);
		//this.setUniform(texLoc, tex.getId());
	}

	@Override
	public void loadFrameUniforms() {
		this.setUniform(vwMatLoc, vwMat);
		this.setUniform(prMatLoc, prMat);
	}

	public ModelMatrix getMlMat() {
		return mlMat;
	}

	public void setMlMat(ModelMatrix mlMat) {
		this.mlMat = mlMat;
	}

	public ViewMatrix getVwMat() {
		return vwMat;
	}

	public void setVwMat(ViewMatrix vwMat) {
		this.vwMat = vwMat;
	}

	public ProjectionMatrix getPrMat() {
		return prMat;
	}

	public void setPrMat(ProjectionMatrix prMat) {
		this.prMat = prMat;
	}

	public Texture getTex() {
		return tex;
	}

	public void setTex(Texture tex) {
		this.tex = tex;
	}
	
}
