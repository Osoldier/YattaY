package me.oso.yattay.player;

/**
 * PlayerStatus.java
 * @author Ibanez Thomas
 * @date 17 avr. 2017
 */
public enum PlayerStatus {
	ALLOWED(0), BANNED(1);
	
	int id;
	
	PlayerStatus(int i) {
		this.id = i;
	}
	
	public static PlayerStatus fromID(int id) {
		for (PlayerStatus p : PlayerStatus.values()) {
			if(p.id == id) {
				return p;
			}
		}
		throw new RuntimeException("This status does not exists");
	}
}
