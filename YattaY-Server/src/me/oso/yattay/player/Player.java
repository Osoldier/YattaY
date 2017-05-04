package me.oso.yattay.player;

import me.oso.lib.math.Vector2f;

/**
 * Player.java
 * @author Ibanez Thomas
 * @date 20 sept. 2016
 */
public class Player {

	private Vector2f position;
	private Weapon weapon;
	private String nickname;
	
	public Player(String name) {
		this.position = new Vector2f();
		this.weapon = new Weapon();
		this.nickname = name;
	}

	public Vector2f getPosition() {
		return position;
	}

	public void setPosition(Vector2f position) {
		this.position = position;
	}

	public Weapon getWeapon() {
		return weapon;
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
}
