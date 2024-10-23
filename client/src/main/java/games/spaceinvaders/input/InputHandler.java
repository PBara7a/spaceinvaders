package games.spaceinvaders.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import games.spaceinvaders.client.WebSocketClient;
import games.spaceinvaders.constants.Direction;
import games.spaceinvaders.dto.RestartGame;
import games.spaceinvaders.dto.ShipMove;
import games.spaceinvaders.dto.ShipShot;
import games.spaceinvaders.game.GameState;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class InputHandler implements KeyListener {

	private WebSocketClient webSocketClient;

	@Override
	public void keyTyped( final KeyEvent e ) {}

	@Override
	public void keyPressed( final KeyEvent e ) {

		// Ship controls
		if ( e.getKeyCode() == KeyEvent.VK_LEFT ) {
			webSocketClient.sendMessage( "/server/move", new ShipMove( 1, Direction.LEFT ) );
		} else if ( e.getKeyCode() == KeyEvent.VK_RIGHT ) {
			webSocketClient.sendMessage( "/server/move", new ShipMove( 1, Direction.RIGHT ) );
		} else if ( e.getKeyCode() == KeyEvent.VK_SPACE ) {
			webSocketClient.sendMessage( "/server/shoot", new ShipShot( 1 ) );
		}

		// Restart game
		if ( GameState.gameOver && e.getKeyCode() == KeyEvent.VK_ENTER ) {
			webSocketClient.sendMessage( "/server/restart", new RestartGame( 1 ) );
		}
	}

	@Override
	public void keyReleased( final KeyEvent e ) {}

}
