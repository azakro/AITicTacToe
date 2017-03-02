import java.util.*;

public class ComplexTicTacToe {
    
    boolean player;
    boolean computer;
    boolean gameOver = false;
    String compSymbol = "";
    public int currentIndex = -1;
    public int currentIndexX = -1;
    public int currentIndexY = -1;
    
    BasicTicTacToe one = new BasicTicTacToe();
    BasicTicTacToe two = new BasicTicTacToe();
    BasicTicTacToe three = new BasicTicTacToe();
    BasicTicTacToe four = new BasicTicTacToe();
    BasicTicTacToe five = new BasicTicTacToe();
    BasicTicTacToe six = new BasicTicTacToe();
    BasicTicTacToe seven = new BasicTicTacToe();
    BasicTicTacToe eight = new BasicTicTacToe();
    BasicTicTacToe nine = new BasicTicTacToe();
    
    static BasicTicTacToe game1 = new BasicTicTacToe();
    static BasicTicTacToe game2 = new BasicTicTacToe();
    static BasicTicTacToe game3 = new BasicTicTacToe();
    static BasicTicTacToe game4 = new BasicTicTacToe();
    static BasicTicTacToe game5 = new BasicTicTacToe();
    static BasicTicTacToe game6 = new BasicTicTacToe();
    static BasicTicTacToe game7 = new BasicTicTacToe();
    static BasicTicTacToe game8 = new BasicTicTacToe();
    static BasicTicTacToe game9 = new BasicTicTacToe();
    
    static MiniMaxValue minimax1 = new MiniMaxValue(game1);
    static MiniMaxValue minimax2 = new MiniMaxValue(game2);
    static MiniMaxValue minimax3 = new MiniMaxValue(game3);
    static MiniMaxValue minimax4 = new MiniMaxValue(game4);
    static MiniMaxValue minimax5 = new MiniMaxValue(game5);
    static MiniMaxValue minimax6 = new MiniMaxValue(game6);
    static MiniMaxValue minimax7 = new MiniMaxValue(game7);
    static MiniMaxValue minimax8 = new MiniMaxValue(game8);
    static MiniMaxValue minimax9 = new MiniMaxValue(game9);
    
    
    //starts with 9 empty basic tic tac toe boards
    public BasicTicTacToe[][] complexStates = {{one, two, three}, {four, five, six}, {seven, eight, nine}};
    public String[][] overallBoard = {{" ", " ", " "}, {" ", " ", " "}, {" ", " ", " "}};
    
    public ComplexTicTacToe() {
    }
    
    public void printBoard(){
        System.err.println("printing board...");
        //		for(int i = 0; i < complexStates.length; i++){
        //			System.err.println(Arrays.toString(complexStates[i]));
        //		}
        
        for(int i = 0; i < complexStates.length; i++){
            for(int j = 0; j < complexStates[i].length; j++) {
                complexStates[i][j].printBoard();
            }
            System.err.println();
        }
        
    }
    
    public int indexConversion(int[] array) {
        
        int index1 = array[0];
        int index2 = array[1];
        
        if (index1 == 0 && index2 == 0) {
            
            return 1;
            
        } else if (index1 == 0 && index2 == 1) {
            
            return 2;
            
        } else if (index1 == 0 && index2 == 2) {
            
            return 3;
            
        } else if (index1 == 1 && index2 == 0) {
            
            return 4;
            
        } else if (index1 == 1 && index2 == 1) {
            
            return 5;
            
        } else if (index1 == 1 && index2 == 2) {
            
            return 6;
            
        } else if (index1 == 2 && index2 == 0) {
            
            return 7;
            
        } else if (index1 == 2 && index2 == 1) {
            
            return 8;
            
        } else {
            
            return 9;
        }
    }
    //returns true if the play can be made and makes the change
    //currentState is which index of the board the player must make a move in. xIndex and yIndex are where the player is placing their move within that basic tic tac toe board
    public boolean playX(int xIndex, int yIndex, int currentStateX, int currentStateY) {
        if (complexStates[currentStateX][currentStateY].states[xIndex][yIndex].equals(" ")) {
            complexStates[currentStateX][currentStateY].states[xIndex][yIndex] = "X";
            return true;
        } else {
            return false;
        }
    }
    
    //returns true if the play can be made and makes the change
    //currentState is which index of the board the player must make a move in. xIndex and yIndex are where the player is placing their move within that basic tic tac toe board
    public boolean playO(int xIndex, int yIndex, int currentStateX, int currentStateY) {
        if (complexStates[currentStateX][currentStateY].states[xIndex][yIndex].equals(" ")) {
            complexStates[currentStateX][currentStateY].states[xIndex][yIndex] = "O";
            return true;
        } else {
            return false;
        }
    }
    
    public boolean checkIfWin(String symbol)
    {
        if(game1.checkIfWin(symbol) || game2.checkIfWin(symbol) || game3.checkIfWin(symbol) || game4.checkIfWin(symbol) || game5.checkIfWin(symbol) || game6.checkIfWin(symbol) || game7.checkIfWin(symbol) || game8.checkIfWin(symbol) || game9.checkIfWin(symbol)) {
            return true;
        } else {
            return false;
        }
        //		//first row
        //		if(complexStates[0][0].checkIfWin(symbol) && complexStates[0][1].checkIfWin(symbol) && complexStates[0][2].checkIfWin(symbol)) {
        //			return true;
        //		}
        //		//second row across
        //		else if(complexStates[1][0].checkIfWin(symbol) && complexStates[1][1].checkIfWin(symbol) && complexStates[1][2].checkIfWin(symbol)){
        //			return true;
        //			//third row across
        //		}else if(complexStates[2][0].checkIfWin(symbol) && complexStates[2][1].checkIfWin(symbol) && complexStates[2][2].checkIfWin(symbol)){
        //			return true;
        //			//first row down
        //		}else if(complexStates[0][0].checkIfWin(symbol) && complexStates[1][0].checkIfWin(symbol) && complexStates[2][0].checkIfWin(symbol)){
        //			return true;
        //			//second row down
        //		}else if(complexStates[0][1].checkIfWin(symbol) && complexStates[1][1].checkIfWin(symbol) && complexStates[2][1].checkIfWin(symbol)){
        //			return true;
        //			//third row down
        //		}else if(complexStates[0][2].checkIfWin(symbol) && complexStates[1][2].checkIfWin(symbol) && complexStates[2][2].checkIfWin(symbol)){
        //			return true;
        //			//diagonal to the right
        //		}else if(complexStates[0][0].checkIfWin(symbol) && complexStates[1][1].checkIfWin(symbol) && complexStates[2][2].checkIfWin(symbol)){
        //			return true;
        //			//diagonal to the left
        //		}else if(complexStates[0][2].checkIfWin(symbol) && complexStates[1][1].checkIfWin(symbol) && complexStates[2][0].checkIfWin(symbol)){
        //			return true;
        //		}else{
        //			return false;
        //		}
    }
    
    public void playerMakeMove(String playerSymbol, int index, int complexStateX, int complexStateY) {
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
            if (!(playX(indexOne, indexTwo, complexStateX, complexStateY))) {
                System.err.println("Not a possible move. Pick another index.");
                Scanner input = new Scanner(System.in);
                int playIndex = input.nextInt();
                
                this.playerMakeMove(playerSymbol, playIndex, complexStateX, complexStateY); //doing game.makeMove instead of just makeMove bc makeMove isn't a static method
            } else {
                System.err.println("Index of complex board that the next move must be made in: " + index);
                currentIndex = index;
                setIndexCoordinates(index);
                
                while (boardIsFull(currentIndexX, currentIndexY) && !complexBoardIsFull()) {
                    System.err.println("Board at index " + currentIndex + " is full. The computer can pick which board they'd like to play instead");
                    Random random = new Random();
                    int newIndex = random.nextInt(9) + 1; //chosen at random for now...
                    
                    currentIndex = newIndex;
                    setIndexCoordinates(newIndex);
                }
            }
        } else if (playerSymbol.equalsIgnoreCase("O") && possibleMove){
            if (!(playO(indexOne, indexTwo, complexStateX, complexStateY))) {
                System.err.println("Not a possible move. Pick another index.");
                Scanner input = new Scanner(System.in);
                int playIndex = input.nextInt();
                
                this.playerMakeMove(playerSymbol, playIndex, complexStateX, complexStateY); //doing game.makeMove instead of just makeMove bc makeMove isn't a static method
            } else {
                System.err.println("Index of complex board that the next move must be made in: " + index);
                currentIndex = index;
                setIndexCoordinates(index);
                
                while (boardIsFull(currentIndexX, currentIndexY) && !complexBoardIsFull()) {
                    System.err.println("Board at index " + currentIndex + " is full. The computer can pick which board they'd like to play instead");
                    Random random = new Random();
                    int newIndex = random.nextInt(9) + 1; //chosen at random for now...
                    
                    currentIndex = newIndex;
                    setIndexCoordinates(newIndex);
                }
                
            }
        }
    }
    
    public void setIndexCoordinates(int index) {
        if (index == 1) {
            currentIndexX = 0;
            currentIndexY = 0;
        } else if (index == 2) {
            currentIndexX = 0;
            currentIndexY = 1;
        } else if (index == 3) {
            currentIndexX = 0;
            currentIndexY = 2;
        } else if (index == 4) {
            currentIndexX = 1;
            currentIndexY = 0;
        } else if (index == 5) {
            currentIndexX = 1;
            currentIndexY = 1;
        } else if (index == 6) {
            currentIndexX = 1;
            currentIndexY = 2;
        } else if (index == 7) {
            currentIndexX = 2;
            currentIndexY = 0;
        } else if (index == 8) {
            currentIndexX = 2;
            currentIndexY = 1;
        } else if (index == 9) {
            currentIndexX = 2;
            currentIndexY = 2;
        }
    }
    
    public void compMakeMove2(int[] bestMove) {
        
        if (player == true) {
            //computer is O
            complexStates[currentIndexX][currentIndexY].states[bestMove[0]][bestMove[1]] = "O";
        } else {
            //computer is x
            complexStates[currentIndexX][currentIndexY].states[bestMove[0]][bestMove[1]] = "X";
        }
        
        int index1 = bestMove[0];
        int index2 = bestMove[1];
        if (index1 == 0 && index2 == 0) {
            System.err.println("Index that the computer played within the current board:");
            System.out.println(1);
            currentIndex = 1;
            setIndexCoordinates(currentIndex);
        } else if (index1 == 0 && index2 == 1) {
            System.err.println("Index that the computer played within the current board:");
            System.out.println(2);
            currentIndex = 2;
            setIndexCoordinates(currentIndex);
        } else if (index1 == 0 && index2 == 2) {
            System.err.println("Index that the computer played within the current board:");
            System.out.println(3);
            currentIndex = 3;
            setIndexCoordinates(currentIndex);
        } else if (index1 == 1 && index2 == 0) {
            System.err.println("Index that the computer played within the current board:");
            System.out.println(4);
            currentIndex = 4;
            setIndexCoordinates(currentIndex);
        } else if (index1 == 1 && index2 == 1) {
            System.err.println("Index that the computer played within the current board:");
            System.out.println(5);
            currentIndex = 5;
            setIndexCoordinates(currentIndex);
        } else if (index1 == 1 && index2 == 2) {
            System.err.println("Index that the computer played within the current board:");
            System.out.println(6);
            currentIndex = 6;
            setIndexCoordinates(currentIndex);
        } else if (index1 == 2 && index2 == 0) {
            System.err.println("Index that the computer played within the current board:");
            System.out.println(7);
            currentIndex = 7;
            setIndexCoordinates(currentIndex);
        } else if (index1 == 2 && index2 == 1) {
            System.err.println("Index that the computer played within the current board:");
            System.out.println(8);
            currentIndex = 8;
            setIndexCoordinates(currentIndex);
        } else if (index1 == 2 && index2 == 2) {
            System.err.println("Index that the computer played within the current board:");
            System.out.println(9);
            currentIndex = 9;
            setIndexCoordinates(currentIndex);
        }
        
        while (boardIsFull(currentIndexX, currentIndexY) && !complexBoardIsFull()) {
            System.err.println("Board at index " + currentIndex + " is full. Enter which board you'd like to play instead");
            Scanner input = new Scanner(System.in);
            int newIndex = input.nextInt();
            
            currentIndex = newIndex;
            setIndexCoordinates(newIndex);
        }
        
        //print out index
    }
    
    public void compChooseBoard(int[] bestMove) {
        int index1 = bestMove[0];
        int index2 = bestMove[1];
        if (index1 == 0 && index2 == 0) {
            System.err.println("Index of the board that the computer chose:");
            System.out.println(1);
            currentIndex = 1;
            setIndexCoordinates(currentIndex);
        } else if (index1 == 0 && index2 == 1) {
            System.err.println("Index of the board that the computer chose:");
            System.out.println(2);
            currentIndex = 2;
            setIndexCoordinates(currentIndex);
        } else if (index1 == 0 && index2 == 2) {
            System.err.println("Index of the board that the computer chose:");
            System.out.println(3);
            currentIndex = 3;
            setIndexCoordinates(currentIndex);
        } else if (index1 == 1 && index2 == 0) {
            System.err.println("Index of the board that the computer chose:");
            System.out.println(4);
            currentIndex = 4;
            setIndexCoordinates(currentIndex);
        } else if (index1 == 1 && index2 == 1) {
            System.err.println("Index of the board that the computer chose:");
            System.out.println(5);
            currentIndex = 5;
            setIndexCoordinates(currentIndex);
        } else if (index1 == 1 && index2 == 2) {
            System.err.println("Index of the board that the computer chose:");
            System.out.println(6);
            currentIndex = 6;
            setIndexCoordinates(currentIndex);
        } else if (index1 == 2 && index2 == 0) {
            System.err.println("Index of the board that the computer chose:");
            System.out.println(7);
            currentIndex = 7;
            setIndexCoordinates(currentIndex);
        } else if (index1 == 2 && index2 == 1) {
            System.err.println("Index of the board that the computer chose:");
            System.out.println(8);
            currentIndex = 8;
            setIndexCoordinates(currentIndex);
        } else if (index1 == 2 && index2 == 2) {
            System.err.println("Index of the board that the computer chose:");
            System.out.println(9);
            currentIndex = 9;
            setIndexCoordinates(currentIndex);
        }
        
    }
    
    public void compMakeMove(){
        Random random = new Random();
        
        int index1 = random.nextInt(3);
        int index2 = random.nextInt(3);
        boolean possibleMove = true;
        
        if(compSymbol.equalsIgnoreCase("X")){
            
            if(!playX(index1, index2, currentIndexX, currentIndexY)) {
                compMakeMove();
            } else {
                System.err.println();
                if (index1 == 0 && index2 == 0) {
                    System.err.println("Index that the computer played within the current board:");
                    System.out.println(1);
                    currentIndex = 1;
                    setIndexCoordinates(currentIndex);
                } else if (index1 == 0 && index2 == 1) {
                    System.err.println("Index that the computer played within the current board:");
                    System.out.println(2);
                    currentIndex = 2;
                    setIndexCoordinates(currentIndex);
                } else if (index1 == 0 && index2 == 2) {
                    System.err.println("Index that the computer played within the current board:");
                    System.out.println(3);
                    currentIndex = 3;
                    setIndexCoordinates(currentIndex);
                } else if (index1 == 1 && index2 == 0) {
                    System.err.println("Index that the computer played within the current board:");
                    System.out.println(4);
                    currentIndex = 4;
                    setIndexCoordinates(currentIndex);
                } else if (index1 == 1 && index2 == 1) {
                    System.err.println("Index that the computer played within the current board:");
                    System.out.println(5);
                    currentIndex = 5;
                    setIndexCoordinates(currentIndex);
                } else if (index1 == 1 && index2 == 2) {
                    System.err.println("Index that the computer played within the current board:");
                    System.out.println(6);
                    currentIndex = 6;
                    setIndexCoordinates(currentIndex);
                } else if (index1 == 2 && index2 == 0) {
                    System.err.println("Index that the computer played within the current board:");
                    System.out.println(7);
                    currentIndex = 7;
                    setIndexCoordinates(currentIndex);
                } else if (index1 == 2 && index2 == 1) {
                    System.err.println("Index that the computer played within the current board:");
                    System.out.println(8);
                    currentIndex = 8;
                    setIndexCoordinates(currentIndex);
                } else if (index1 == 2 && index2 == 2) {
                    System.err.println("Index that the computer played within the current board:");
                    System.out.println(9);
                    currentIndex = 9;
                    setIndexCoordinates(currentIndex);
                }
            }
            //			while(!playX(index1, index2)){
            //				int tryIndex1 = random.nextInt(2);
            //				int tryIndex2 = random.nextInt(2);
            //				//playX(tryIndex1, tryIndex2);
            //			}
            
        }else if(compSymbol.equalsIgnoreCase("O")){
            if(!playO(index1, index2, currentIndexX, currentIndexY)) {
                compMakeMove();
            } else {
                System.err.println();
                if (index1 == 0 && index2 == 0) {
                    System.err.println("Index that the computer played within the current board:");
                    System.out.println(1);
                    currentIndex = 1;
                    setIndexCoordinates(currentIndex);
                } else if (index1 == 0 && index2 == 1) {
                    System.err.println("Index that the computer played within the current board:");
                    System.out.println(2);
                    currentIndex = 2;
                    setIndexCoordinates(currentIndex);
                } else if (index1 == 0 && index2 == 2) {
                    System.err.println("Index that the computer played within the current board:");
                    System.out.println(3);
                    currentIndex = 3;
                    setIndexCoordinates(currentIndex);
                } else if (index1 == 1 && index2 == 0) {
                    System.err.println("Index that the computer played within the current board:");
                    System.out.println(4);
                    currentIndex = 4;
                    setIndexCoordinates(currentIndex);
                } else if (index1 == 1 && index2 == 1) {
                    System.err.println("Index that the computer played within the current board:");
                    System.out.println(5);
                    currentIndex = 5;
                    setIndexCoordinates(currentIndex);
                } else if (index1 == 1 && index2 == 2) {
                    System.err.println("Index that the computer played within the current board:");
                    System.out.println(6);
                    currentIndex = 6;
                    setIndexCoordinates(currentIndex);
                } else if (index1 == 2 && index2 == 0) {
                    System.err.println("Index that the computer played within the current board:");
                    System.out.println(7);
                    currentIndex = 7;
                    setIndexCoordinates(currentIndex);
                } else if (index1 == 2 && index2 == 1) {
                    System.err.println("Index that the computer played within the current board:");
                    System.out.println(8);
                    currentIndex = 8;
                    setIndexCoordinates(currentIndex);
                } else if (index1 == 2 && index2 == 2) {
                    System.err.println("Index that the computer played within the current board:");
                    System.out.println(9);
                    currentIndex = 9;
                    setIndexCoordinates(currentIndex);
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
        //		boolean tied = true;
        //
        //		for (BasicTicTacToe[] a: complexStates) {
        //			for (BasicTicTacToe b: a) {
        //				for (String[] c: b.states) {
        //					for (String d: c) {
        //						if(d.equalsIgnoreCase(" ")) {
        //							tied = false;
        //							break;
        //						}
        //					}
        //				}
        //			}
        //		}
        //		return tied;
        if (game1.gameTied() == true) {
            if (game2.gameTied() == true) {
                if (game3.gameTied() == true) {
                    if (game4.gameTied() == true) {
                        if (game5.gameTied() == true) {
                            if (game6.gameTied() == true) {
                                if (game7.gameTied() == true) {
                                    if (game8.gameTied() == true) {
                                        if (game9.gameTied() == true) {
                                            
                                            return true;
                                        }
                                        
                                    }
                                    
                                }
                                
                            }
                            
                        }
                        
                    }
                    
                }
                
            }
            
        }
        return false;
    }
    
    public boolean boardIsFull(int indexX, int indexY) {
        boolean full = true;
        BasicTicTacToe board = complexStates[indexX][indexY];
        for (String[] a: board.states) {
            for (String b: a) {
                if(b.equalsIgnoreCase(" ")) {
                    full = false;
                }
            }
        }
        return full;
    }
    
    public boolean complexBoardIsFull() {
        boolean full = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(!boardIsFull(i,j)) {
                    full = false;
                    return full;
                }
            }
        }
        return full;
    }
    
    public static void main(String[] args){
        boolean keepPlaying = true;
        while(keepPlaying) {
            
            ComplexTicTacToe game = new ComplexTicTacToe();
            game1 = new BasicTicTacToe();
            game2 = new BasicTicTacToe();
            game3 = new BasicTicTacToe();
            game4 = new BasicTicTacToe();
            game5 = new BasicTicTacToe();
            game6 = new BasicTicTacToe();
            game7 = new BasicTicTacToe();
            game8 = new BasicTicTacToe();
            game9 = new BasicTicTacToe();
            
            System.err.println("WELCOME TO COMPLEX TIC TAC TOE");
            Scanner input = new Scanner(System.in);
            
            System.err.println("Would you like to be 'X' or 'O' ?");
            String playerSymbol = input.next();
            boolean beginningOfGame = true;
            
            if(playerSymbol.equals("X")){
                game.player = true;
                game.computer = false;
                game.compSymbol = "O";
                
                game1.player = true;
                game1.computer = false;
                game1.compSymbol = "O";
                
                game2.player = true;
                game2.computer = false;
                game2.compSymbol = "O";
                
                game3.player = true;
                game3.computer = false;
                game3.compSymbol = "O";
                
                game4.player = true;
                game4.computer = false;
                game4.compSymbol = "O";
                
                game5.player = true;
                game5.computer = false;
                game5.compSymbol = "O";
                
                game6.player = true;
                game6.computer = false;
                game6.compSymbol = "O";
                
                game7.player = true;
                game7.computer = false;
                game7.compSymbol = "O";
                
                game8.player = true;
                game8.computer = false;
                game8.compSymbol = "O";
                
                game9.player = true;
                game9.computer = false;
                game9.compSymbol = "O";
                
            }else if(playerSymbol.equals("O")){
                game.player = false;
                game.computer = true;
                game.compSymbol = "X";
                
                game1.player = false;
                game1.computer = true;
                game1.compSymbol = "X";
                
                game2.player = false;
                game2.computer = true;
                game2.compSymbol = "X";
                
                game3.player = false;
                game3.computer = true;
                game3.compSymbol = "X";
                
                game4.player = false;
                game4.computer = true;
                game4.compSymbol = "X";
                
                game5.player = false;
                game5.computer = true;
                game5.compSymbol = "X";
                
                game6.player = false;
                game6.computer = true;
                game6.compSymbol = "X";
                
                game7.player = false;
                game7.computer = true;
                game7.compSymbol = "X";
                
                game8.player = false;
                game8.computer = true;
                game8.compSymbol = "X";
                
                game9.player = false;
                game9.computer = true;
                game9.compSymbol = "X";
            }
            
            MiniMaxValue minimax;
            
            if (playerSymbol.equals("X")) {
                System.err.println("Enter the index of the board you would like to begin in.");
                game.currentIndex = input.nextInt();
                game.setIndexCoordinates(game.currentIndex);
                minimax = new MiniMaxValue(game.complexStates[game.currentIndexX][game.currentIndexY]);
            } else {
                minimax = new MiniMaxValue(game.complexStates[1][1]); //arbitrarily chosen board since they're all empty anyway
                
                game.compChooseBoard(minimax.bestMove(game.complexStates[1][1].states));
            }
            
            minimax.setGame(game.complexStates[game.currentIndexX][game.currentIndexY]);
            
            int playIndex = 0;
            while(game.gameOver != true){
                
                if (!beginningOfGame || playerSymbol.equals("X")) {
                    
                    System.err.println("Pick a number one through nine to place your symbol on the grid");
                    playIndex = input.nextInt();
                    
                    //					game.playerMakeMove(playerSymbol, playIndex, game.currentIndexX, game.currentIndexY);
                    if (game.currentIndex == 1) {
                        
                        game1.playerMakeMove(playerSymbol, playIndex);
                        
                    } else if (game.currentIndex == 2) {
                        
                        
                        game2.playerMakeMove(playerSymbol, playIndex);
                        
                    } else if (game.currentIndex == 3) {
                        
                        
                        game3.playerMakeMove(playerSymbol, playIndex);
                        
                    } else if (game.currentIndex == 4) {
                        
                        
                        game4.playerMakeMove(playerSymbol, playIndex);
                        
                    } else if (game.currentIndex == 5) {
                        
                        
                        game5.playerMakeMove(playerSymbol, playIndex);
                        
                    } else if (game.currentIndex == 6) {
                        
                        
                        game6.playerMakeMove(playerSymbol, playIndex);
                        
                    } else if (game.currentIndex == 7) {
                        
                        
                        game7.playerMakeMove(playerSymbol, playIndex);
                        
                    } else if (game.currentIndex == 8) {
                        
                        
                        game8.playerMakeMove(playerSymbol, playIndex);
                        
                    } else if (game.currentIndex == 9) {
                        
                        
                        game9.playerMakeMove(playerSymbol, playIndex);
                        
                    }
                    
                    System.err.println();
                    System.err.println("Your move:");
                    game1.printBoard();
                    game2.printBoard();
                    game3.printBoard();
                    System.err.println();
                    game4.printBoard();
                    game5.printBoard();
                    game6.printBoard();
                    System.err.println();
                    game7.printBoard();
                    game8.printBoard();
                    game9.printBoard();
                }
                
                minimax.setGame(game.complexStates[game.currentIndexX][game.currentIndexY]);
                minimax1.setGame(game1);
                minimax2.setGame(game2);
                minimax3.setGame(game3);
                minimax4.setGame(game4);
                minimax5.setGame(game5);
                minimax6.setGame(game6);
                minimax7.setGame(game7);
                minimax8.setGame(game8);
                
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
                
                while (game.boardIsFull(game.currentIndexX, game.currentIndexY) && !game.complexBoardIsFull()) {
                    System.err.println("Board at index " + game.currentIndex + " is full. The computer can pick which board they'd like to play instead");
                    Random random = new Random();
                    int indexNew = random.nextInt(9) + 1; //chosen at random for now...
                    
                    game.currentIndex = indexNew;
                    game.setIndexCoordinates(indexNew);
                }
                
                int[] bestMoveIndices = minimax.bestMove(game.complexStates[game.currentIndexX][game.currentIndexY].states);
                
                Random random = new Random();
                
                if (playIndex == 1) {
                    if(game1.boardIsEmpty()) {
                        bestMoveIndices[0] = random.nextInt(2);
                        bestMoveIndices[1] = random.nextInt(2);
                    } else {
                        bestMoveIndices = minimax1.bestMove(game1.states);
                    }
                    game1.compMakeMove2(bestMoveIndices);
                    //					game.currentIndex = game.indexConversion(minimax1.bestMove(game1.states));
                    //game.setIndexCoordinates(game.currentIndex);
                    
                } else if (playIndex == 2) {
                    if(game2.boardIsEmpty()) {
                        bestMoveIndices[0] = random.nextInt(2);
                        bestMoveIndices[1] = random.nextInt(2);
                    } else {
                        bestMoveIndices = minimax2.bestMove(game2.states);
                    }
                    game2.compMakeMove2(bestMoveIndices);
                    
                } else if (playIndex == 3) {
                    if(game3.boardIsEmpty()) {
                        bestMoveIndices[0] = random.nextInt(2);
                        bestMoveIndices[1] = random.nextInt(2);
                    } else {
                        
                        bestMoveIndices = minimax3.bestMove(game3.states);
                    }
                    game3.compMakeMove2(bestMoveIndices);
                    
                } else if (playIndex == 4) {
                    if(game4.boardIsEmpty()) {
                        bestMoveIndices[0] = random.nextInt(2);
                        bestMoveIndices[1] = random.nextInt(2);
                    } else {
                        
                        bestMoveIndices = minimax4.bestMove(game4.states);
                    }
                    game4.compMakeMove2(bestMoveIndices);
                } else if (playIndex == 5) {
                    if(game5.boardIsEmpty()) {
                        bestMoveIndices[0] = random.nextInt(2);
                        bestMoveIndices[1] = random.nextInt(2);
                    } else {
                        
                        bestMoveIndices = minimax5.bestMove(game5.states);
                    }
                    game5.compMakeMove2(bestMoveIndices);
                    //					game.currentIndex = game.indexConversion(minimax5.bestMove(game5.states));
                    //					game.setIndexCoordinates(game.currentIndex);
                    
                } else if (playIndex == 6) {
                    if(game6.boardIsEmpty()) {
                        bestMoveIndices[0] = random.nextInt(2);
                        bestMoveIndices[1] = random.nextInt(2);
                    } else {
                        
                        bestMoveIndices = minimax6.bestMove(game6.states);
                    }
                    game6.compMakeMove2(bestMoveIndices);
                    //					game.currentIndex = game.indexConversion(minimax6.bestMove(game6.states));
                    //					game.setIndexCoordinates(game.currentIndex);
                    
                } else if (playIndex == 7) {
                    if(game7.boardIsEmpty()) {
                        bestMoveIndices[0] = random.nextInt(2);
                        bestMoveIndices[1] = random.nextInt(2);
                    } else {
                        
                        bestMoveIndices = minimax7.bestMove(game7.states);
                    }
                    game7.compMakeMove2(bestMoveIndices);
                    //					game.currentIndex = game.indexConversion(minimax7.bestMove(game7.states));
                    //					game.setIndexCoordinates(game.currentIndex);
                    
                } else if (playIndex == 8) {
                    if(game8.boardIsEmpty()) {
                        bestMoveIndices[0] = random.nextInt(2);
                        bestMoveIndices[1] = random.nextInt(2);
                    } else {
                        
                        bestMoveIndices = minimax8.bestMove(game8.states);
                    }
                    game8.compMakeMove2(bestMoveIndices);
                    //					game.currentIndex = game.indexConversion(minimax8.bestMove(game8.states));
                    //					game.setIndexCoordinates(game.currentIndex);
                    
                } else if (playIndex == 9) {
                    if(game9.boardIsEmpty()) {
                        bestMoveIndices[0] = random.nextInt(2);
                        bestMoveIndices[1] = random.nextInt(2);
                    } else {
                        
                        bestMoveIndices = minimax9.bestMove(game9.states);
                    }
                    game9.compMakeMove2(bestMoveIndices);
                    //					game.currentIndex = game.indexConversion(minimax9.bestMove(game9.states));
                    //					game.setIndexCoordinates(game.currentIndex);
                    //
                }
                game.currentIndex = game.indexConversion(bestMoveIndices);
                game.setIndexCoordinates(game.currentIndex);
                
                System.err.println();
                System.err.println("Computer move:");
                game1.printBoard();
                game2.printBoard();
                game3.printBoard();
                System.err.println();
                game4.printBoard();
                game5.printBoard();
                game6.printBoard();
                System.err.println();
                game7.printBoard();
                game8.printBoard();
                game9.printBoard();
                beginningOfGame = false;
                System.err.println("Computer played in index: " + game.currentIndex);
                
                minimax1.setGame(game1);
                minimax2.setGame(game2);
                minimax3.setGame(game3);
                minimax4.setGame(game4);
                minimax5.setGame(game5);
                minimax6.setGame(game6);
                minimax7.setGame(game7);
                minimax8.setGame(game8);
                minimax9.setGame(game9);
                
                
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
            }
        }
        System.err.println("starting a new game...");
    }
}
