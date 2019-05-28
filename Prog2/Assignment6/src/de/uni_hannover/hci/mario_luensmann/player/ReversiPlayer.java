package de.uni_hannover.hci.mario_luensmann.player;

import de.uni_hannover.hci.mario_luensmann.rboard.ReversiBoard;

public abstract class ReversiPlayer {
	
	protected boolean[][] validMoves;
	
	private boolean didForfeitTurn = false;
	
	private String className = "";
	
	private String stoneIdentifier = "";
		
	public abstract void nextMove(ReversiBoard pCurrentBoard);
	
	public abstract boolean isStillAValidMove();
	
	public boolean[][] getValidMoves(){
		return this.validMoves;
	}
	
	public void setValidMoves(boolean[][] pMovement){
		this.validMoves = pMovement;
	}
	
	public boolean getPlayerSurrenders(){
		return this.didForfeitTurn;
	}
	
	public void setPlayerSurrenders(boolean pValue){
		this.didForfeitTurn = pValue;
	}
	
	public String getStoneIdentifier(){
		return this.stoneIdentifier;
	}
	
	public void setStoneIdentifier(String pID){
		if(pID.equals("|X|") || pID.equals("|O|")){
			this.stoneIdentifier = pID;
		}
	}
	
	public String getClassName(){
		return this.className;
	}
	
	public void setClassName(String pClassName){
		this.className = pClassName;
	}
	
}
