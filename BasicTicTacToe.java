import java.util.*;

public class BasicTicTacToe {

	boolean player;
	boolean computer;
	boolean gameOver = false;
	String compSymbol = "";

	public String[][] states = {{" ", " ", " "}, {" ", " ", " "}, {" ", " ", " "}};


	public BasicTicTacToe() {	
	}

	public void printBoard(){
		for(int i = 0; i < states.length; i++){
			System.err.println(Arrays.toString(states[i]));
		}
	}

	//returns true if the play can be made and makes the change
	public boolean playX(int xIndex, int yIndex) {
		if (states[xIndex][yIndex].equals(" ")) {
			states[xIndex][yIndex] = "X";
			return true;
		} else {
			return false;
		}
	}

	//returns true if the play can be made and makes the change
	public boolean playO(int xIndex, int yIndex) {
		if (states[xIndex][yIndex].equals(" ")) {
			states[xIndex][yIndex] = "O";
			return true;
		} else {
			return false;
		}
	}

    public boolean boardIsEmpty() {
        boolean empty = true;
        for (String[] a: states) {
            for (String b: a) {
                if(!(b.equalsIgnoreCase(" "))) {
                    empty = false;
                    break;
                }
            }
        }
        return empty;
    }
    
	public boolean checkIfWin(String symbol){

		//first row across
		if(states[0][0].equalsIgnoreCase(symbol) && states[0][1].equalsIgnoreCase(symbol) && states[0][2].equalsIgnoreCase(symbol)){
			return true;

			//second row across
		}else if(states[1][0].equalsIgnoreCase(symbol) && states[1][1].equalsIgnoreCase(symbol) && states[1][2].equalsIgnoreCase(symbol)){
			return true;

			//third row across
		}else if(states[2][0].equalsIgnoreCase(symbol) && states[2][1].equalsIgnoreCase(symbol) && states[2][2].equalsIgnoreCase(symbol)){
			return true;

			//first row down
		}else if(states[0][0].equalsIgnoreCase(symbol) && states[1][0].equalsIgnoreCase(symbol) && states[2][0].equalsIgnoreCase(symbol)){
			return true;

			//second row down
		}else if(states[0][1].equalsIgnoreCase(symbol) && states[1][1].equalsIgnoreCase(symbol) && states[2][1].equalsIgnoreCase(symbol)){
			return true;

			//third row down
		}else if(states[0][2].equalsIgnoreCase(symbol) && states[1][2].equalsIgnoreCase(symbol) && states[2][2].equalsIgnoreCase(symbol)){
			return true;

			//diagonal to the right
		}else if(states[0][0].equalsIgnoreCase(symbol) && states[1][1].equalsIgnoreCase(symbol) && states[2][2].equalsIgnoreCase(symbol)){
			return true;

			//diagonal to the left
		}else if(states[0][2].equalsIgnoreCase(symbol) && states[1][1].equalsIgnoreCase(symbol) && states[2][0].equalsIgnoreCase(symbol)){
			return true;

		}else{
			return false;
		}
	}

	public void playerMakeMove(String playerSymbol, int index) {
		int indexOne, indexTwo = -1;
		boolean possibleMove = true;
		if (index == 1) {
			indexOne = 0;
			indexTwo = 0;
		} else if (index == 2) {
			indexOne = 0;
			indexTwo = 1;
		} else if (index == 3) {
			indexOne = 0;
			indexTwo = 2;
		} else if (index == 4) {
			indexOne = 1;
			indexTwo = 0;
		} else if (index == 5) {
			indexOne = 1;
			indexTwo = 1;
		} else if (index == 6) {
			indexOne = 1;
			indexTwo = 2;
		} else if (index == 7) {
			indexOne = 2;
			indexTwo = 0;
		} else if (index == 8) {
			indexOne = 2;
			indexTwo = 1;
		} else if (index == 9) {
			indexOne = 2;
			indexTwo = 2;
		} else {
			System.err.println("Error - not a possible move.");
			possibleMove = false;
			indexOne = -1;
			indexTwo = -1;
		}

		if (playerSymbol.equalsIgnoreCase("X") && possibleMove) {
			if (!(playX(indexOne, indexTwo))) {
				System.err.println("Not a possible move. Pick another index.");
				Scanner input = new Scanner(System.in);
				int playIndex = input.nextInt();

				this.playerMakeMove(playerSymbol, playIndex); //doing game.makeMove instead of just makeMove bc makeMove isn't a static method
			}
		} else if (playerSymbol.equalsIgnoreCase("O") && possibleMove){
			if (!(playO(indexOne, indexTwo))) {
				System.err.println("Not a possible move. Pick another index.");
				Scanner input = new Scanner(System.in);
				int playIndex = input.nextInt();

				this.playerMakeMove(playerSymbol, playIndex); //doing game.makeMove instead of just makeMove bc makeMove isn't a static method
			}
		}
	}

	public void compMakeMove2(int[] bestMove) {

		if (player == true) {
			//computer is O
			states[bestMove[0]][bestMove[1]] = "O";

		} else {
			//computer is x
			states[bestMove[0]][bestMove[1]] = "X";
		}

		//print out index
	}

	public void compMakeMove(){
		Random random = new Random();

		int index1 = random.nextInt(3);
		int index2 = random.nextInt(3);
		boolean possibleMove = true;

		if(compSymbol.equalsIgnoreCase("X")){

			if(!playX(index1, index2)) {
				compMakeMove();
			} else {
				System.err.println();
				if (index1 == 0 && index2 == 0) {
					System.err.println("Index that the computer played:");
					System.out.println(1);
				} else if (index1 == 0 && index2 == 1) {
					System.err.println("Index that the computer played:");
					System.out.println(2);
				} else if (index1 == 0 && index2 == 2) {
					System.err.println("Index that the computer played:");
					System.out.println(3);
				} else if (index1 == 1 && index2 == 0) {
					System.err.println("Index that the computer played:");
					System.out.println(4);
				} else if (index1 == 1 && index2 == 1) {
					System.err.println("Index that the computer played:");
					System.out.println(5);
				} else if (index1 == 1 && index2 == 2) {
					System.err.println("Index that the computer played:");
					System.out.println(6);
				} else if (index1 == 2 && index2 == 0) {
					System.err.println("Index that the computer played:");
					System.out.println(7);
				} else if (index1 == 2 && index2 == 1) {
					System.err.println("Index that the computer played:");
					System.out.println(8);
				} else if (index1 == 2 && index2 == 2) {
					System.err.println("Index that the computer played:");
					System.out.println(9);
				}
			}
			//			while(!playX(index1, index2)){
			//				int tryIndex1 = random.nextInt(2);
			//				int tryIndex2 = random.nextInt(2);
			//				//playX(tryIndex1, tryIndex2);
			//			}

		}else if(compSymbol.equalsIgnoreCase("O")){
			if(!playO(index1, index2)) {
				compMakeMove();
			} else {
				System.err.println();
				if (index1 == 0 && index2 == 0) {
					System.err.println("Index that the computer played:");
					System.out.println(1);
				} else if (index1 == 0 && index2 == 1) {
					System.err.println("Index that the computer played:");
					System.out.println(2);
				} else if (index1 == 0 && index2 == 2) {
					System.err.println("Index that the computer played:");
					System.out.println(3);
				} else if (index1 == 1 && index2 == 0) {
					System.err.println("Index that the computer played:");
					System.out.println(4);
				} else if (index1 == 1 && index2 == 1) {
					System.err.println("Index that the computer played:");
					System.out.println(5);
				} else if (index1 == 1 && index2 == 2) {
					System.err.println("Index that the computer played:");
					System.out.println(6);
				} else if (index1 == 2 && index2 == 0) {
					System.err.println("Index that the computer played:");
					System.out.println(7);
				} else if (index1 == 2 && index2 == 1) {
					System.err.println("Index that the computer played:");
					System.out.println(8);
				} else if (index1 == 2 && index2 == 2) {
					System.err.println("Index that the computer played:");
					System.out.println(9);
				}
			}

			//			while(!playO(index1, index2)){
			//				int tryIndex1 = random.nextInt(2);
			//				int tryIndex2 = random.nextInt(2);
			//				//playO(tryIndex1, tryIndex2);
			//			}
		}
		//        if (!possibleMove) {
		//            
		//        }
	}

	public boolean gameTied() {
		boolean tied = false;
		if (!checkIfWin("X") && !checkIfWin("O")) {
			tied = true;
			for (String[] a: states) {
				for (String b: a) {
					if(b.equalsIgnoreCase(" ")) {
						tied = false;
					}
				}
			}
		}
		return tied;
	}

	public static void main(String[] args){
		boolean keepPlaying = true;
		while (keepPlaying) {

			BasicTicTacToe game = new BasicTicTacToe();
			MiniMaxValue minimax = new MiniMaxValue(game);

			System.err.println("WELCOME TO TIC TAC TOE");
			Scanner input = new Scanner(System.in);

			System.err.println("Would you like to be 'X' or 'O' ?");
			String playerSymbol = input.next();
			boolean beginningOfGame = true;

			if(playerSymbol.equalsIgnoreCase("X")){
				game.player = true;
				game.computer = false;
				game.compSymbol = "O";

			}else if(playerSymbol.equalsIgnoreCase("O")){
				game.player = false;
				game.computer = true;
				game.compSymbol = "X";
			}

			while(game.gameOver != true){
				
				if (!beginningOfGame || playerSymbol.equals("X")) {

					System.err.println("Pick a number one through nine to place your symbol on the grid");
					int playIndex = input.nextInt();

					game.playerMakeMove(playerSymbol, playIndex);
					System.err.println();
					System.err.println("Your move:");
					game.printBoard();
				}

				minimax.setGame(game);

				if(game.checkIfWin(playerSymbol)){
					game.gameOver = true;
					System.err.println("wow dude nice! you just beat the comp");
					break;
				}
				
				if(game.checkIfWin(game.compSymbol)){
					game.gameOver = true;
					System.err.println("LOL humans suk, the computer beat u!!!");
					break;
				}

				if(game.gameTied()) {
					game.gameOver =true;
					System.err.println("Tie. Game over.");
					break;
				}

				//game.compMakeMove();

				game.compMakeMove2(minimax.bestMove(game.states));
				System.err.println();
				System.err.println("Computer move:");
				game.printBoard();
				beginningOfGame = false;

				if(game.checkIfWin(playerSymbol)){
					game.gameOver = true;
					System.err.println("wow dude nice! you just beat the comp");
					break;
				}
				
				if(game.checkIfWin(game.compSymbol)){
					game.gameOver = true;
					System.err.println("LOL humans suk, the computer beat u!!!");
					break;
				}
				
				if(game.gameTied()) {
					game.gameOver =true;
					System.err.println("Tie. Game over.");
					break;
				}
			}
			
			System.err.println("starting a new game...");
		}
	}
}
