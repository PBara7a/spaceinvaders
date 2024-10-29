package games.spaceinvaders.server.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.swing.Timer;

import org.springframework.stereotype.Service;

import games.spaceinvaders.server.model.Alien;
import games.spaceinvaders.server.model.Game;
import games.spaceinvaders.server.model.Ship;
import games.spaceinvaders.constants.Board;
import games.spaceinvaders.server.model.GameState;
import games.spaceinvaders.server.service.GameStateService;
import games.spaceinvaders.server.utils.CollisionDetector;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Service
public class StateManager implements ActionListener {

	private final Map<Integer, Game> games;

	private final Timer gameLoop;
	private final GameStateService gameStateService;
	private final WaveManager waveManager;

	public StateManager( final GameStateService gameStateService, final WaveManager waveManager ) {
		this.games = new ConcurrentHashMap<>();
		this.gameStateService = gameStateService;
		this.waveManager = waveManager;
		this.gameLoop = new Timer( 1000/30, this );
		gameLoop.start();
	}

	public Game getGame( final int gameId ) {
		return games.get( gameId );
	}

	public void createGame( final int gameId ) {
		if ( !games.containsKey( gameId ) ) {
			final var game = new Game( gameId );
			game.setState( createStartingState() );
			games.put( gameId, game );
		}
	}

	public void resetGame( final int gameId ) {
		if ( games.get( gameId ) != null ) {
			final var newGame = new Game( gameId );
			newGame.setState( createStartingState() );
			games.put( newGame.getId(), newGame );
		}
	}

	@Override
	public void actionPerformed( final ActionEvent e ) {
		updateGames();
		broadcastGames();
	}

	private GameState createStartingState() {
		final var alienWave = waveManager.createFirstWave();
		return new GameState( alienWave, new LinkedList<>(), new Ship() );
	}

	private void updateGames() {
		for ( int gameId : games.keySet() ) {
			updateGame( gameId );
		}
	}

	private void updateGame( final int gameId ) {
		final var currentGameState = games.get( gameId ).getState();
		final var ship = currentGameState.getShip();
		final var aliens = currentGameState.getAliens();
		final var bullets = currentGameState.getBullets();

		// Move Aliens
		boolean hasBouncedOnEdge = false;

		for ( Alien alien : aliens ) {
			if ( alien.isAlive() ) {
				alien.moveXAxis();

				if ( alien.getX() + alien.getWidth() >= Board.width - Board.alienGap || alien.getX() <= 0 ) {
					hasBouncedOnEdge = true;
					break;
				}

				if ( CollisionDetector.hasCollided( alien, ship ) || alien.getY() > ship.getY() ) {
					currentGameState.setGameOver( true );
				}
			}
		}

		if ( hasBouncedOnEdge ) {
			for ( Alien alien : aliens ) {
				alien.reverseDirection();
				alien.moveYAxis();
			}
		}

		// Move Bullets
		final var bulletIterator = bullets.iterator();
		while ( bulletIterator.hasNext() ) {
			final var bullet = bulletIterator.next();
			bullet.flyYAxis();

			for ( Alien alien : aliens ) {
				if ( !bullet.isUsed() && alien.isAlive() && CollisionDetector.hasCollided( bullet, alien ) ) {
					bullet.setUsed( true );
					alien.setAlive( false );
					currentGameState.setAlienCount( currentGameState.getAlienCount() - 1 );
					currentGameState.setScore( currentGameState.getScore() + 10 );
				}
			}

			if ( bullet.isUsed() || bullet.getY() < 0 ) {
				bulletIterator.remove();
			}
		}

		// Next level
		if ( currentGameState.getAlienCount() <= 0 ) {
			currentGameState.setScore( currentGameState.getScore() + calculateBonusScore( waveManager.getAlienRows(), waveManager.getAlienColumns() ) );
			bullets.clear();
			final var nextAlienWave = waveManager.createNextWave();
			currentGameState.setAliens( nextAlienWave );
			currentGameState.setAlienCount( nextAlienWave.size() );
		}
	}

	private int calculateBonusScore( final int alienRows, final int alienCols ) {
		return alienCols * alienRows * 100;
	}

	private void broadcastGames() {
		for ( final Game game : games.values() ) {
			gameStateService.broadcastGameState( game );
		}
	}

}
