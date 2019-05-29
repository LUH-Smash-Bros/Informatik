package de.uni_hannover.hci.mario_luensmann.rmain;

import java.util.Scanner;

import de.uni_hannover.hci.mario_luensmann.player.HumanPlayer;
import de.uni_hannover.hci.mario_luensmann.player.RandomisedNPCPlayer;
import de.uni_hannover.hci.mario_luensmann.player.ReversiPlayer;
import de.uni_hannover.hci.mario_luensmann.rboard.ReversiBoard;
//import de.uni_hannover.hci.mario_luensmann.stones.ReversiStones;
import de.uni_hannover.hci.mario_luensmann.gametype.GameMode;

public class ReversiMain {
	
	private ReversiBoard myBoard;
	
	private HumanPlayer player1;
	
	private HumanPlayer player2;
	
	private RandomisedNPCPlayer npcplayer1; 
	
	private RandomisedNPCPlayer npcplayer2; 
	
	private GameMode type;
	
	public ReversiMain(HumanPlayer pPlayer1, HumanPlayer pPlayer2){
		myBoard = new ReversiBoard();
		player1 = pPlayer1;
		player2 = pPlayer2;
		type = GameMode.Human_vs_Human;
	}
	
	public ReversiMain(HumanPlayer pPlayer1, RandomisedNPCPlayer pPlayer2){
		myBoard = new ReversiBoard();
		player1 = pPlayer1;
		npcplayer2 = pPlayer2;
		type = GameMode.Human_vs_AI;
	}
	
	public ReversiMain(RandomisedNPCPlayer pPlayer1, HumanPlayer pPlayer2){
		myBoard = new ReversiBoard();
		npcplayer1 = pPlayer1;
		player2 = pPlayer2;
		type = GameMode.AI_vs_Human;
	}
	
	public ReversiMain(RandomisedNPCPlayer pPlayer1, RandomisedNPCPlayer pPlayer2){
		myBoard = new ReversiBoard();
		npcplayer1 = pPlayer1;
		npcplayer2 = pPlayer2;
		type = GameMode.AI_vs_AI;
	}

	public static void main(String[] args) {
		
		ReversiMain game = null;
		
		String[] myArray = new String[2];
		
		Scanner myInputScanner1 = new Scanner(System.in);
		
		Scanner myInputScanner2 = new Scanner(System.in);
		
		System.out.println("Start via: [Player 1 Type] and \"Enter\" and [Player 2 Type] and \"Enter\" ");
		
		myArray[0] = myInputScanner1.next();
		myArray[1] = myInputScanner1.next();
		
		switch(myArray.length){
			case 2:
				if(myArray[0].equals("Human1") && myArray[1].equals("Human2")){
					game = new ReversiMain(new HumanPlayer("|X|"), new HumanPlayer("|O|"));
					break;
				}
				else if(myArray[0].equals("Human1") && myArray[1].equals("NPCPlayer2")){
					game = new ReversiMain(new HumanPlayer("|X|"), new RandomisedNPCPlayer("|O|"));
					break;
				}
				else if(myArray[0].equals("NPCPlayer1") && myArray[1].equals("Human2")){
					game = new ReversiMain(new RandomisedNPCPlayer("|X|"), new HumanPlayer("|O|"));
					break;
				}
				else if(myArray[0].equals("NPCPlayer1") && myArray[1].equals("NPCPlayer2")){
					game = new ReversiMain(new RandomisedNPCPlayer("|X|"), new RandomisedNPCPlayer("|O|"));
					break;
				}
			default:
				//System.out.println("Start via: [Player 1 Type] and \"Enter\" and [Player 2 Type] and \"Enter\" ");
				System.exit(0);
		}
		/*System.out.println("Start via: [Player 1 Type] and \"Enter\" and [Player 2 Type] and \"Enter\" ");
		String firstPlayer = myInputScanner1.next();
		String secondPlayer = myInputScanner2.next();
		switch(firstPlayer.length()){
		
		}
		if(firstPlayer.equals("Human") && secondPlayer.equals("Human")){
			game = new ReversiMain(new HumanPlayer("X"), new HumanPlayer("O"));
			break;
		}*/
		
		//ReversiBoard myReversiBoard = new ReversiBoard();
		//myReversiBoard.printBoard();
		
		game.run();
		myInputScanner1.close();
		myInputScanner2.close();
	}
	
	private void initMessage(){
		System.out.println();
		System.out.println("Welcome to Reversi! Your Moves should be entered in a \"[row]_[column]\" format.");
		System.out.println();
	}
	
	private void printSurrender(ReversiPlayer pPlayer){
		System.out.println();
		System.out.println(pPlayer.getStoneIdentifier() + " has no possible moves anymore." + " Game Over.");
		System.out.println();
	}
	/*
	 * Magic Method ^^. A generic Method with generic Type Parameters and
	 * a generic Type that it needs and which needs to be of Type ReversiPlayer
	 * in order to work properly as it does not return anything but uses the
	 * objects for backend operations in the run method. It is besides
	 * the Main my heart of my implementation, which basically controls
	 * the entire gameflow!
	 */
	private <T extends ReversiPlayer> void playTurn(T pPlayer){
		
		pPlayer.setValidMoves(myBoard.getValidMovesPlayers(pPlayer));
		if(!pPlayer.isStillAValidMove()){
			pPlayer.setPlayerSurrenders(true);
			printSurrender(pPlayer);
		}
		else{
			pPlayer.setPlayerSurrenders(false);
		}
		
		if(!pPlayer.getPlayerSurrenders()){
			myBoard.printBoard();
			currentScore();
			pPlayer.nextMove(myBoard);
			pPlayer.setValidMoves(null);
		}
	}
	
	private void currentScore(){
		//System.out.println();
		System.out.println(" Score: O " + myBoard.countStonesP2() + ":" + myBoard.countStonesP1() + " X");
		//System.out.println();
	}
	
	private void player1Wins(){
		System.out.println();
		System.out.println("X player is the winner of Reversi!");
		System.out.println();
		System.out.println("End Score: X=" + myBoard.countStonesP1() + " , O=" + myBoard.countStonesP2());
		System.out.println();
		myBoard.printBoard();
		System.exit(0);
	}
	
	private void player2Wins(){
		System.out.println();
		System.out.println("O player is the winner of Reversi!");
		System.out.println();
		System.out.println("End Score: O=" + myBoard.countStonesP2() + " , X=" + myBoard.countStonesP1());
		System.out.println();
		myBoard.printBoard();
		System.exit(0);
	}
	
	private void drawGame(){
		System.out.println();
		System.out.println("No one is the winner of Reversi!");
		System.out.println();
		System.out.println("End Score: X=" + myBoard.countStonesP1() + " , O=" + myBoard.countStonesP2());
		System.out.println();
		myBoard.printBoard();
		System.exit(0);
	}
	
	private void showTheWinner(ReversiPlayer pPlayer1, ReversiPlayer pPlayer2){
		if((pPlayer1.getPlayerSurrenders() && pPlayer2.getPlayerSurrenders()) || myBoard.isFull()){
			if(myBoard.countStonesP1() > myBoard.countStonesP2()){
				player1Wins();
			}
			else if(myBoard.countStonesP2() > myBoard.countStonesP1()){
				player2Wins();
			}
			else{
				drawGame();
			}
		}
	}
	
	/*
	 * Here happens magic as well cause i have created thanks to java generics
	 * one player1 value that always changes thanks to the while(true) statement
	 * the type of player1 which is basically just a String representation of either
	 * |X| or |O| just that player1 gets either the first object of |X| or the second
	 * object of |O| if the second time <T extends ReversiPlayer>playTurn(T Player) is 
	 * used. In between it always checks for whether there is already a winner or not in
	 * each one of the cases seen below!
	 */
	private void run(){
		initMessage();
		
		switch(this.type){
			case Human_vs_Human:
				while(true){
					this.<HumanPlayer>playTurn(player1);
					showTheWinner(player1, player2);
					this.<HumanPlayer>playTurn(player2);
					showTheWinner(player1, player2);
				}
			case Human_vs_AI:
				while(true){
					this.<HumanPlayer>playTurn(player1);
					showTheWinner(player1, npcplayer2);
					this.<RandomisedNPCPlayer>playTurn(npcplayer2);
					showTheWinner(player1, npcplayer2);
				}
			case AI_vs_Human:
				while(true){
					this.<RandomisedNPCPlayer>playTurn(npcplayer1);
					showTheWinner(npcplayer1, player2);
					this.<HumanPlayer>playTurn(player2);
					showTheWinner(npcplayer1, player2);
				}
			case AI_vs_AI:
				while(true){
					this.<RandomisedNPCPlayer>playTurn(npcplayer1);
					showTheWinner(npcplayer1, npcplayer2);
					this.<RandomisedNPCPlayer>playTurn(npcplayer2);
					showTheWinner(npcplayer1, npcplayer2);
				}
		}
	}

}
