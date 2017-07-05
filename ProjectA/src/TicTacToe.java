/*
 * Author: Hao Yu Yeh
 * Date: 28/03/2016
 * Comment: project A(TicTacToe game) in COMP90041
 */

import java.util.Scanner;
import java.lang.Math;

public class TicTacToe {
	private final static int GRIDNUM = 9, OWIN = 0, XWIN = 1, DRAW = 2, CONTINUE = 3;
	private final static char O = 'O', X = 'X';
	private char[][] chessBoard;
	private String player1 = "", player2 = "", gridLine = "";
	private int chessBoardCol, row = 0, col = 0; 
	
	public TicTacToe(){
		chessBoardCol = (int)Math.sqrt( GRIDNUM );
		chessBoard = new char[ chessBoardCol ][ chessBoardCol ];		
		for( int i = 0; i < chessBoardCol; i++ ){
			for( int j = 0; j < chessBoardCol; j++ ){
				chessBoard[ i ][ j ] = ' ';
			}
		}
		// according the size of chessboard to generate the horizontal grid line
		for( int i = 0; i < ( 2 * chessBoardCol - 1 ); i++ ){
			gridLine += "-";
		}
	}
	
	public void setPlayer1( String name ){
		player1 = name;
	}
	
	public String getPlayer1(){
		return player1;
	}
	
	public void setPlayer2( String name ){
		player2 = name;
	}
	
	public String getPlayer2(){
		return player2;
	}
	
	// run a game
	public void run(){		
		Scanner keyboard = new Scanner( System.in );
		System.out.println( "Welcome to Tic Tac Toe!" );
		System.out.println();
		System.out.println( "Enter Player O's name:" );
//		player1 = keyboard.next();
		setPlayer1( keyboard.next() );
		System.out.println( "Enter Player X's name:" );
//		player2 = keyboard.next();
		setPlayer2( keyboard.next() );
		printGrid();		
		// making movements 
		int type = CONTINUE, count = 1;
		while(true){
			switch( type ){
				case OWIN: 
					System.out.println( "Game over. " + player1 + " won!" );
					keyboard.close();
					return;
				case XWIN: 
					System.out.println( "Game over. " + player2 + " won!" );
					keyboard.close();
					return;
				case DRAW: 
					System.out.println( "Game over. It was a draw!" );
					keyboard.close();
					return;
				default:
				case CONTINUE: 
					// player1's turn
					if( ( count % 2 ) == 1 ){ 
						System.out.println( player1 + "'s move:" );
						row = keyboard.nextInt();
						col = keyboard.nextInt();
						chessBoard[row][col] = O;
						type = getGameState( count );
					}else{ 
						System.out.println( player2 + "'s move:" );
						row = keyboard.nextInt();
						col = keyboard.nextInt();
						chessBoard[row][col] = X;
						type = getGameState( count );
					}
					break;			
			}					
			printGrid();
			count++;
		}		
	}
	
	// print the chessboard
	private void printGrid(){ 
		for( int i = 0; i < chessBoardCol; i++ ){
			String temp = "";
			for( int j = 0; j < chessBoardCol; j++ ){
				temp += chessBoard[i][j];
				// adding vertical grid line
				if( j < ( chessBoardCol - 1) ){
					temp += "|";
				}
			}
			System.out.println( temp );
			// adding horizontal grid line
			if( i < ( chessBoardCol - 1) ){
				System.out.println( gridLine );
			}
		}		
	}
	
	// checking if there is a player satisfying the winning criteria 
	private boolean checkWinner( char target ){
		char tempVertival, tempHorizontal, tempDiagonal1 = chessBoard[0][0], tempDiagonal2 = chessBoard[0][chessBoardCol-1];		
		for( int i = 0; i < chessBoardCol; i++ ){
			tempHorizontal = chessBoard[i][0];
			tempVertival = chessBoard[0][i];
			// check if there is a consecutive character 'target' filled in all grids of one horizontal line 
			if( tempHorizontal == target ){
				for( int j = 1; j < chessBoardCol; j++ ){
					tempHorizontal &= chessBoard[ i ][ j ];
					// stop checking once there is a grid which is not filled with the character 'target'
					if( tempHorizontal != target ){
						break;
					}else if( ( j == ( chessBoardCol-1 ) ) && ( tempHorizontal == target ) ){
						return true;
					}
				}
			}			
			// check if there is a consecutive character 'target' filled in all grids of one vertical line
			if( tempVertival == target ){
				for( int j = 1; j < chessBoardCol; j++ ){
					tempVertival &= chessBoard[ j ][ i ];
					if( tempVertival != target ){
						break;
					}else if( ( j == ( chessBoardCol-1 ) ) && ( tempVertival == target ) ){
						return true;
					}					
				}
			}	
			// check if there is a consecutive character 'target' filled in all grids of one diagonal line
			if( i > 0 ){
				tempDiagonal1 &= chessBoard[ i ][ i ];
				tempDiagonal2 &= chessBoard[ i ][ chessBoardCol - 1 - i ];
			}			
		}
		if ( tempDiagonal1 == target ){
			return true;
		}else if( tempDiagonal2 == target ){
			return true;
		}else{
			return false;
		}
	}
	
	// determine the current status of the game, such as who wins, draw or continue
	private int getGameState( int count ){ 
		if( checkWinner( O ) ){
			return OWIN;
		}else if( checkWinner( X ) ){
			return XWIN;
		}else if( count == GRIDNUM ){			
			return DRAW;
		}else{
			return CONTINUE;
			
		}
	}
	
	public static void main(String[] args){
		TicTacToe game = new TicTacToe();
		game.run();
	}
}
