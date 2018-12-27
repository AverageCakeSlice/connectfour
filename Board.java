import java.util.Stack;

public class Board
{
    // Initialize new empty board variable
    private char[][] boardGrid;

    // Default empty-board constructor. 8x8 slots.
    public Board()
    {
        this.boardGrid = new char[8][8];
    
        for(int y = 0; y < this.boardGrid.length; y++)
        {
            for(int x = 0; x < this.boardGrid[y].length; x++)
            {
                this.boardGrid[y][x] = '*';
            }
        }
    }

    // Two-argument constructor, allows for custom board sizes.
    // Sets minimum size to 4x4, since it's... uh... Connect FOUR?
    public Board(int sizeY, int sizeX)
    {
        if(sizeY < 4 || sizeX < 4)
        {
            System.out.println("The minimum size for a board must be at least 4x4.");
            System.out.println("Setting grid size to 4x4...");
            this.boardGrid = new char[4][4];
        }
        else
        {
            this.boardGrid = new char[sizeY][sizeX];
        }

        for(int y = 0; y < this.boardGrid.length; y++)
        {
            for(int x = 0; x < this.boardGrid[y].length; x++)
            {
                this.boardGrid[y][x] = '*';
            }
        }
    }

    // Getters
    public char[][] getBoardGrid()
    {
        return this.boardGrid;
    }

    // Methods
    public void updateBoardGrid(Stack<Token> tokenStack)
    {
        if(!tokenStack.empty())
        {
            int y = tokenStack.peek().getYCoord();
            int x = tokenStack.peek().getXCoord();
            if (tokenStack.peek().getColor() == 'y')
            {
                this.boardGrid[y][x] = 'Y';
            }
            else
            {
                this.boardGrid[y][x] = 'R';
            }
        }
        else;
    }

    //checkForVictory() searches the provided token stack for contiguous tokens in a vector, and
    //calls the seekVector() helper function to find other tokens in a line. It only checks the last
    //token pushed onto the stack, so it is executed every time a new token is played.
    public boolean checkForVictory(Stack<Token> tokenStack)
    {
        int vectorDirectionY;
        int vectorDirectionX;
        Token lastTokenPlaced = tokenStack.peek();

            for(Token t : tokenStack)
            {

                if((Math.abs(lastTokenPlaced.getYCoord() - t.getYCoord()) <= 1) 
                    && (Math.abs(lastTokenPlaced.getXCoord() - t.getXCoord()) <= 1)
                    && lastTokenPlaced != t)
                {
                    if(lastTokenPlaced.getColor() == t.getColor())
                    {
                        vectorDirectionY = lastTokenPlaced.getYCoord() - t.getYCoord();
                        vectorDirectionX = lastTokenPlaced.getXCoord() - t.getXCoord();
                        if(findWinningVector(tokenStack, t, vectorDirectionY, vectorDirectionX))
                        {
                            return true;
                        }
                        
                    }
                }
            }
        return false;
    }

    private boolean findWinningVector(Stack<Token> tokenStack, Token nextToken, int vectorDirectionY, int vectorDirectionX)
    {
        Token reverseToken = findReverseToken(tokenStack, nextToken, vectorDirectionY, vectorDirectionX);
        int vectorMagnitude = findVectorMagnitude(tokenStack, nextToken, vectorDirectionY, vectorDirectionX, 2);

        if(vectorMagnitude >= 4)
        {
            return true;
        }
        else
        {
            if(findVectorMagnitude(tokenStack, reverseToken, (vectorDirectionY * -1), (vectorDirectionX * -1), 1) >= 4)
            {
                return true;
            }
        }
        return false;
    }

    

    private int findVectorMagnitude(Stack<Token> tokenStack, Token nextToken, int vectorDirectionY, int vectorDirectionX, int initialMagnitude)
    {
        boolean foundNextToken = false;
        int vectorMagnitude = initialMagnitude;

        do
        {
            for(Token t : tokenStack)
            {
                if((nextToken.getYCoord() == t.getYCoord() + vectorDirectionY) 
                    && (nextToken.getXCoord() == t.getXCoord()  + vectorDirectionX)
                    && t.getColor() == nextToken.getColor())
                {
                    vectorMagnitude++;
                    nextToken = t;
                    foundNextToken = true;
                    break;
                }
                else
                {
                    foundNextToken = false;
                }
            }
        }while(foundNextToken);

        return vectorMagnitude;
    }

    private Token findReverseToken(Stack<Token> tokenStack, Token nextToken, int vectorDirectionY, int vectorDirectionX)
    {
        boolean foundNextToken = false;
        Token reverseToken = nextToken;

        do
        {
            for(Token t : tokenStack)
            {
                if((nextToken.getYCoord() == t.getYCoord() + vectorDirectionY) && (nextToken.getXCoord() == t.getXCoord()  + vectorDirectionX))
                {
                    if(t.getColor() == nextToken.getColor())
                    {
                        reverseToken = t;
                        nextToken = t;
                        foundNextToken = true;
                        break;
                    }
                }
                else
                {
                    foundNextToken = false;    
                }
            }
        }while(foundNextToken);

        return reverseToken;
    }

    public void printBoardGrid()
    {
        System.out.println((char)218 + "   --1--2--3--4--5--6--7--8--" + (char)191);
        for(int y = 0; y < this.boardGrid.length; y++)
        {
            System.out.printf(" %d | ", y);
            for(int x = 0; x < this.boardGrid[y].length; x++)
            {
                System.out.print(" " + this.boardGrid[y][x] + " ");
            }
            System.out.printf(" |%n");
        }
        System.out.println((char)192 + "   --------------------------" + (char)217);
    }
}