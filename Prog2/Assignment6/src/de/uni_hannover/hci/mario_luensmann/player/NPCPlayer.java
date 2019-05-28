package de.uni_hannover.hci.mario_luensmann.player;

public abstract class NPCPlayer extends ReversiPlayer {
	
	public void createTimeDelay(int milliseconds) {
		try {
			
			Thread.sleep(milliseconds);
			
		} catch(InterruptedException ex) {
			
			Thread.currentThread().interrupt();
			
		}
	}
}
