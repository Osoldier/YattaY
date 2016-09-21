package me.oso.yattay.world;

import me.oso.lib.graphics.*;
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
	
	private int mlMatLoc, vwMatLoc, prMatLoc;
	private ModelMatrix mlMat;
	private ViewMatrix vwMat;
	private ProjectionMatrix prMat;
	
	public BlockShader(int blockSize, float atlasSize) {
		super(VERT_FILE, FRAG_FILE);
		mlMatLoc = this.getUniformLocation("mlMat");
		vwMatLoc = this.getUniformLocation("vwMat");
		prMatLoc = this.getUniformLocation("prMat");
		this.setUniform(this.getUniformLocation("atlasSize"), atlasSize);
		this.setUniform(this.getUniformLocation("blockSize"), blockSize);
		this.mlMat = new ModelMatrix();
	}

	@Override
	public void loadUniforms() {
		this.setUniform(mlMatLoc, mlMat);
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
}
