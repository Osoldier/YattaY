package me.oso.yattay.editor.ui;

import me.oso.yattay.ui.*;
import me.oso.yattay.world.*;

/**
 * Created by Thomas on 26 sept. 2016
 */
public class BtnBlock extends Button {

	private static BlockType lastSelectedType = BlockType.AIR;
	private BlockType bt;
	
	public BtnBlock(int x, int y, int width, int height, BlockType t) {
		super(x, y, width, height, t.getUiTexture());
		this.bt = t;
	}
	
	@Override
	public void onClick() {
		super.onClick();
		lastSelectedType = bt;
	}
	
	@Override
	public void onMouseHover() {
		super.onMouseHover();
	}

	public static BlockType getLastSelectedType() {
		return lastSelectedType;
	}	
}
