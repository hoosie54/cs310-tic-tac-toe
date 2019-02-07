package edu.jsu.mcis;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToeController implements ActionListener {

    private final TicTacToeModel model;
    private final TicTacToeView view;
    
    /* CONSTRUCTOR */

    public TicTacToeController(int width) {
        
        /* Initialize model, view, and width */

        model = new TicTacToeModel(width);
        view = new TicTacToeView(TicTacToeController.this, width);
        
    }
    
    public String getMarkAsString(int row, int col) {        
        return (model.getMark(row, col).toString());        
    }
    
    public TicTacToeView getView() {        
        return view;        
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        
        String name = ((JButton) event.getSource()).getName(); // Get Buttons Name
        
        // INSERT YOUR CODE HERE
        int row, col;
        char x, y;
        char[] ch = name.toCharArray();
        int width = model.getWidth();
        
        x = ch[6];
        y = ch[7];
        
        row = Character.getNumericValue(x);
        col = Character.getNumericValue(y);
        
        model.makeMark(row, col);
         
        String mark = model.getMark(row, col).toString();
        view.board[row][col].setText(mark);
        
        if (model.getResult() != TicTacToeModel.Result.NONE) {
            
            view.resultLabel.setText(model.getResult().toString().toUpperCase());
            
            for(int i = 0; i < width; i++){
                
                for (int j = 0; j < width; j++) {
                    
                    view.board[i][j].setEnabled(false);
                    
                }
                
            }
            
        }
        
    }

    public void start() {
    
        /* MAIN LOOP (repeats until game is over) */

        /* Display the board using the View's "showBoard()", then use
           "getNextMove()" to get the next move from the player.  Enter
           the move (using the Model's "makeMark()", or display an error
           using the View's "showInputError()" if the move is invalid. */
        
        while( !model.isGameover() ) {
            
            
        
        }
        
        /* After the game is over, show the final board and the winner */

        //view.showBoard(model.toString());

        view.showResult(model.getResult().toString());
        
    }

}
