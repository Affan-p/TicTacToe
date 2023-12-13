package my_game;

import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

abstract class myAbstract{
	public abstract void enterName();
	public abstract void displayBoard();
	public abstract boolean playerOneTurn();
	public abstract boolean playerTwoTurn();
	public abstract boolean checkWins(char x);
	public abstract void congrats();
}

class Tic_Tac_Toe extends myAbstract{
	private HashMap<String,Character>myMap;
	private String p1Name;
	private String p2Name;
	
	public Tic_Tac_Toe(){
		HashMap<String,Character>myMap=new HashMap<>();
		try {
		File fe=new File("options.txt");
		Scanner sc=new Scanner(fe);
		while(sc.hasNextLine()) {
			String str=sc.nextLine();
			String []strarr=str.split("=");
			myMap.put(strarr[0], strarr[1].charAt(0));
		}
		}
		catch(Exception e) {
			System.out.println("Here is the exception "+e.getMessage());
		}
		this.myMap=myMap;
		
	}

	
	@Override
	public void enterName() {
		Scanner p1=new Scanner(System.in);
		System.out.println("Enter player 1 name: ");
		p1Name=p1.nextLine();
		System.out.println("Enter player 2 name: ");
		p2Name=p1.nextLine();
		
		
	}

	@Override
	public void displayBoard() {
		System.out.println("============================================================\n"
						+ "|       |       A      |       B       |         C         |\n"
						+ "|===========================================================\n"
						+ "|   1   |      "+myMap.get("A1")+"       |       "+myMap.get("B1")+"       |         "+myMap.get("C1")+"         |\n"
						+ "|===========================================================\n"
						+ "|   2   |      "+myMap.get("A2")+"       |       "+myMap.get("B2")+"       |         "+myMap.get("C2")+"         |\n"
						+ "|===========================================================\n"
						+ "|   3   |      "+myMap.get("A3")+"       |       "+myMap.get("B3")+"       |         "+myMap.get("C3")+"         |\n"
						+ "|===========================================================\n");

		
		
	}


	@Override
	public boolean playerOneTurn() {
	    Scanner in = new Scanner(System.in);

	    while (true) {
	        System.out.println("Select the box that " + p1Name + " wants to cross: ");
	        String choice = in.nextLine().toUpperCase();

	        if (myMap.containsKey(choice) && myMap.get(choice) == ' ') {
	            myMap.put(choice, 'X');
	            displayBoard();

	            if (checkWins('X')) {
	                displayBoard();
	                System.out.println(p1Name + " wins!");
	                congrats();
	                return true;
	            } else {
	                return false; 
	            }
	        } else {
	            System.out.println("**********Error*********");
	            System.out.println("Cannot put here, already marked by " + p2Name + " or yourself");
	        }
	    }
	}

	@Override
	public boolean playerTwoTurn() {
	    Scanner in = new Scanner(System.in);

	    while (true) {
	        System.out.println("Select the box that " + p2Name + " wants to cross: ");
	        String choice = in.nextLine().toUpperCase();

	        if (myMap.containsKey(choice) && myMap.get(choice) == ' ') {
	            myMap.put(choice, 'O');
	            displayBoard();

	            if (checkWins('O')) {
	                displayBoard();
	                System.out.println(p2Name + " wins!");
	                congrats();
	                return true; 
	            } else {
	                return false; 
	            }
	        } else {
	            System.out.println("**********Error*********");
	            System.out.println("Cannot put here, already marked by " + p1Name + " or yourself");
	        }
	    }
	}
	


	@Override
	public void congrats() {
		// TODO Auto-generated method stub
		System.out.println("******Congratulations******");
	}


	@Override
	public boolean checkWins(char x) {
		// TODO Auto-generated method stub
		char[] rows = {'A', 'B', 'C'};
        char[] cols = {'1', '2', '3'};
        
        
        for (char row : rows) {
            if (myMap.get(row + "1") == x && myMap.get(row + "2") == x&& myMap.get(row + "3") == x) {
                return true;
            }
        }

       
        for (char column : cols) {
            if (myMap.get("A" + column) == x && myMap.get("B" + column) == x && myMap.get("C" + column) == x) {
                return true;
            }
        }

        // Check diagonals for winning condition
        if ((myMap.get("A1") == x && myMap.get("B2") == x && myMap.get("C3") == x) || (myMap.get("C1") == x && myMap.get("B2") == x && myMap.get("A3") == x)) {
            return true;
        }
		return false;
	}


	

	
	
}



public class Main {

	public static void main(String[] args) {
		Tic_Tac_Toe my=new Tic_Tac_Toe();
		System.out.println("Hello");
		System.out.println("**************************Welcome to my Tic-Tac-Toe game**************************");
		System.out.println("Do you want to start playing (type yes or no)?");
		Scanner sc=new Scanner(System.in);
		String userinput=sc.nextLine().trim().toUpperCase();
		System.out.println(userinput);
		while(true) {
		if(userinput.equals("YES")) {		  
		  my.enterName();
		  my.displayBoard();
		  boolean gameWin = false;

	        while (!gameWin) {
	            gameWin = my.playerOneTurn();

	            if (gameWin) {
	                break;
	            } else {
	                gameWin = my.playerTwoTurn();
	                if (gameWin) {
	                    break; // 
	                }
	            }
	        }
		  System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!The END!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		  System.out.println("Rerun program to play again");
		  break;
		  
		}
		else if(userinput.equals("NO")) {
			System.out.println("****Exiting game****");
			System.out.println("Goodbye");
			break;
		}
		}	

	}

}
