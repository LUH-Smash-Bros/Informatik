package de.uni_hannover.hci.mario_luensmann.rboard;

import de.uni_hannover.hci.mario_luensmann.player.ReversiPlayer;

public interface IBoard {
	public void printBoard();
	
	public void updateBoard(int row, int col, ReversiPlayer p);
	
	public boolean isFull();
}
