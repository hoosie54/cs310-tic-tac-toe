package edu.jsu.mcis;

public class TicTacToeModel {
    
    private Mark[][] board; /* Game board */
    private boolean xTurn;  /* True if X is current player */
    private int width;      /* Size of game board */
    
    /* ENUM TYPE DEFINITIONS */
    
    /* Mark (represents X, O, or an empty square */
    
    public enum Mark {
        
        X("X"), 
        O("O"), 
        EMPTY("-");

        private String message;
        
        private Mark(String msg) {
            message = msg;
        }
        
        @Override
        public String toString() {
            return message;
        }
        
    };
    
    /* Result (represents the final state of the game: X wins, O wins, a TIE,
       or NONE if the game is not yet over) */
    
    public enum Result {
        
        X("X"), 
        O("O"), 
        TIE("TIE"), 
        NONE("NONE");

        private String message;
        
        private Result(String msg) {
            message = msg;
        }
        
        @Override
        public String toString() {
            return message;
        }
        
    };
    
    /* CONSTRUCTOR */
    
    public TicTacToeModel() {
        
        this(TicTacToe.DEFAULT_WIDTH);
        
    }
    
    /* CONSTRUCTOR */
    
    public TicTacToeModel(int width) {
        
        /* Initialize width; X goes first */
        
        this.width = width;
        xTurn = true;
        
        /* Create board (width x width) as a 2D Mark array */
        
        board = new Mark[width][width];

        /* Initialize board by filling every square with empty marks */

        for ( int i = 0; i < width; i++ ) {
     
            for ( int j = 0; j < width; j++ ) {
                board[i][j] = Mark.EMPTY;
            }
            
        }
        
    }
	
    public boolean makeMark(int row, int col) {
        
        /* Use "isValidSquare()" to check if the specified location is in range,
           and use "isSquareMarked()" to see if the square is empty!  If the
           specified location is valid, make a mark for the current player, then
           toggle "xTurn" from true to false (or vice-versa) to switch to the
           other player before returning TRUE.  Otherwise, return FALSE. */
        
        boolean made = false;
        
        if ( isValidSquare(row, col) && !isSquareMarked(row, col) ) {
            
            if ( xTurn ){
                board[row][col] = Mark.X;
            }
            else if ( !xTurn ) {
                board[row][col] = Mark.O;
            }
            
            xTurn = !xTurn;
            made = true;
            return made;
            
        }
        
        return made; 
        
    }
	
    private boolean isValidSquare(int row, int col) {
        
        /* Return TRUE if the specified location is within the bounds of the board */
        
        if ( row < getWidth() && col < getWidth() && row >= 0 && col >= 0 ) {
            return true;
        }
        
        return false;
        
    }
	
    private boolean isSquareMarked(int row, int col) {
        
        /* Return TRUE if the square at specified location is marked */
        
        if ( isValidSquare(row, col) ) {
            
            if ( getMark(row, col).equals(Mark.EMPTY) ) {
                return false;
            }
            
        }
        
        return true;
        
    }
	
    public Mark getMark(int row, int col) {
        
        /* Return the mark from the square at the specified location */
        
        return board[row][col];
            
    }
	
    public Result getResult() {
        
        /* Call "isMarkWin()" to see if X or O is the winner, if the game is a
           TIE, or if the game is not over.  Return the corresponding Result
           value */
        
        if ( isMarkWin(Mark.X) == true ){
            return Result.X;
        }
        if ( isMarkWin(Mark.O) == true ){
            return Result.O;
        }
        if ( isTie() == true ){
            return Result.TIE;
        }
        else
            return Result.NONE;
        
    }
	
    private boolean isMarkWin(Mark mark) {
        
        /* Check the squares of the board to see if the specified mark is the
           winner */
        
        int counter = 0;
        boolean win = false;
        
        // check row for win
        for ( int i = 0; i < width; ++i ) {
            
            counter = 0;
            
            for ( int j = 0; j < width; ++j ) {
                
                if ( board[i][j].equals(mark) ){
                    counter++;
                }
                if ( counter == width ) {
                    win = true;
                }
                
            }
            
        }
        
        // check column for win
        for ( int i = 0; i < width; ++i ) {
            
            counter = 0;
            
            for ( int j = 0; j < width; ++j ) {
                
                if ( board[j][i].equals(mark) ){
                    counter++;
                }
                if ( counter == width ) {
                    win = true;
                }
                
            }
            
        }
        
        // check diagonal left to right
        counter = 0;
        for ( int i = 0; i < width; ++i ) {
            
            if ( board[i][i].equals(mark) ){
                counter++;
            }
            if ( counter == width ) {
                win = true;
            }
              
        }
        
        // check diagonal right to left
        counter = 0;
        for ( int i = 0; i < width; ++i ) {
                
            if ( board[i][width - i - 1].equals(mark) ){
                counter++;
            }
            if ( counter == width ) {
                win = true;
            }
                
            
            
        }
        
        return win;
        
    }
	
    private boolean isTie() {
        
        /* Check the squares of the board to see if the game is a tie */
        
        int counter = 0;
        boolean tie = false;
        
        for ( int i = 0; i < width; i++ ) {
            
            for ( int j = 0; j < width; j++ ) {
                
                if( !board[i][j].equals(Mark.EMPTY )) {
                    counter++;
                }
                
            }
            
        }
        
        if( counter == (width*width) ) {
            tie = true;
        }
        
        return tie;
        
    }

    public boolean isGameover() {
        
        /* Return TRUE if the game is over */
        
        return (Result.NONE != getResult());
        
    }

    public boolean isXTurn() {
        
        /* Getter for xTurn */
        
        return xTurn;
        
    }

    public int getWidth() {
        
        /* Getter for width */
        
        return width;
        
    }
    
    @Override
    public String toString() {
        
        StringBuilder output = new StringBuilder("  ");
        
        /* Output the board contents as a string (see examples) */

        for ( int i = 0; i < getWidth(); i++ ){
            output.append(i);
        }
        output.append("\n\n");
        for ( int i = 0; i < getWidth(); i++ ) {
            
            output.append( i + " ");
            for ( int j = 0; j < getWidth(); j++ ) {
                output.append(getMark(i, j));
            }
            output.append("\n");
            
        }
        
        return output.toString();
        
    }
    
}
