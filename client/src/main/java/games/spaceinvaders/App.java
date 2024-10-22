package games.spaceinvaders;

import javax.swing.JFrame;

import games.spaceinvaders.contants.Board;
import games.spaceinvaders.game.SpaceInvaders;

public class App {

    public static void main( String[] args ) {

        // Create the window frame
        final var frame = new JFrame();
        frame.setSize( Board.width, Board.height );
        frame.setLocationRelativeTo( null );
        frame.setResizable( false );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

        // Add SpaceInvaders to the frame
        final var spaceInvaders = new SpaceInvaders();
        spaceInvaders.requestFocus();
        frame.add( spaceInvaders );
        frame.pack();
        frame.setVisible( true );
    }
}
