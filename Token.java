import java.util.Stack;

public class Token
{
    // Instance Variables
    private char tokenColor;
    private int tokenNum;
    private int tokenYCoord;
    private int tokenXCoord;

    //Constructor
    public Token(Stack<Token> tokenStack)
    {
        this.tokenColor = (tokenStack.size() % 2 == 0) ? 'y' : 'r';
        this.tokenNum = tokenStack.size() + 1;
    }

    // Getters
    public char getColor()
    {
        return this.tokenColor;
    }

    public int getTokenNum()
    {
        return this.tokenNum;
    }

    public int getYCoord()
    {
        return this.tokenYCoord;
    }

    public int getXCoord()
    {
        return this.tokenXCoord;
    }

    // Public methods
    public boolean dropToken(int droppedColumn, char[][] boardGrid)
    {
        for(int y = boardGrid.length - 1; y > 0; y--)
        {
            if(boardGrid[y][droppedColumn] == '*')
            {
                this.tokenYCoord = y;
                this.tokenXCoord = droppedColumn;
                return true;
            }
        }
        return false;
    }
}