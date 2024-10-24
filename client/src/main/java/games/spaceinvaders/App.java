package games.spaceinvaders;

import javax.swing.JFrame;

import games.spaceinvaders.client.WebSocketClient;
import games.spaceinvaders.constants.Board;
import games.spaceinvaders.game.AnimationManager;
import games.spaceinvaders.game.GameManager;
import games.spaceinvaders.game.SpaceInvaders;
import games.spaceinvaders.input.InputHandler;

public class App {

    public static void main( String[] args ) {

        final var animationManager = new AnimationManager();
        final var gameManager = new GameManager( animationManager );
        final var webSocketClient = new WebSocketClient( gameManager );
        webSocketClient.connect( "ws://localhost:8080/spaceinvaders-server" );

        final var inputHandler = new InputHandler( webSocketClient );

        // Create the window frame
        final var frame = new JFrame();
        frame.setSize( Board.width, Board.height );
        frame.setLocationRelativeTo( null );
        frame.setResizable( false );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

        // Add SpaceInvaders to the frame
        final var spaceInvaders = new SpaceInvaders( gameManager, inputHandler );
        spaceInvaders.requestFocus();
        frame.add( spaceInvaders );
        frame.pack();
        frame.setVisible( true );
    }
}
