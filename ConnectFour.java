import java.util.Stack;
import java.util.Scanner;

public class ConnectFour
{
    public static void main(String args[])
    {
        //Initialize new Board and Scanner objects, and create a new Token stack.
        Board myNewBoard = new Board();
        Scanner userInput = new Scanner(System.in);
        Stack<Token> myTokenStack = new Stack<Token>();
        boolean hasWon = false;
        String currentPlayer = "Yellow";
        int myInput;

        //Output starting prompts
        System.out.println("Welcome to connect four!\n");
        System.out.println("[Press 0 anytime to exit]");
        myNewBoard.printBoardGrid();
        System.out.print("Yellow player, press the number of the column you would like to put a token into (between 1 and 8): ");
        myInput = userInput.nextInt();
        while(myInput != 0)
        {
            if(myInput >= 1 || myInput <= 8)
            {
                
                myTokenStack.push(new Token(myTokenStack));
                currentPlayer = (myTokenStack.peek().getColor()== 'y') ? "Yellow" : "Red";
                if(!myTokenStack.peek().dropToken(myInput - 1, myNewBoard.getBoardGrid()))
                    {
                        System.out.println("Column is full! Please try another column.");
                        myTokenStack.pop();
                    }
                myNewBoard.updateBoardGrid(myTokenStack);
                System.out.println("[Press 0 anytime to exit]");
                myNewBoard.printBoardGrid();
                if(myTokenStack.peek().getTokenNum() >= 7)
                {
                    hasWon = myNewBoard.checkForVictory(myTokenStack);
                }
            }
            else
            {
                System.out.println("Not a valid input. Please enter a number between 1-8, or press 0 to exit:");
            }
            if(hasWon)
            {
                System.out.printf("Congratulations, %s player! You've won!%nExiting...", currentPlayer);
                break;
            }
            // Outputs the next players turn.
            System.out.printf("%s's turn:", (currentPlayer == "Yellow" ? "Red" : "Yellow"));
            myInput = userInput.nextInt();
        }
        userInput.close();
        System.exit(0);
    }
}