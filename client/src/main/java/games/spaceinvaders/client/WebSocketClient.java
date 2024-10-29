package games.spaceinvaders.client;

import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.lang.reflect.Type;
import java.util.Objects;

import games.spaceinvaders.dto.GameStateDto;
import games.spaceinvaders.dto.PlayerAction;
import games.spaceinvaders.game.GameManager;

@Component
public class WebSocketClient {

	private StompSession session;
	private final GameManager gameManager;

	public WebSocketClient( final GameManager gameManager ) {
		this.gameManager = gameManager;
	}

	public void connect( final String serverUri ) {
		final var webSocketClient = new StandardWebSocketClient();
		final var stompClient = new WebSocketStompClient( webSocketClient );
		stompClient.setMessageConverter( new MappingJackson2MessageConverter() );

		final var sessionHandler = new SessionHandler();
		stompClient.connect( serverUri, sessionHandler );
	}

	public void sendMessage( final String destination, final PlayerAction message ) {
		if ( session != null && session.isConnected() ) {
			session.send( destination, message );
		} else {
			System.err.println( "Cannot send message, no active session." );
		}
	}

	public void joinGame( final int gameId ) {
		if ( session != null && session.isConnected() ) {
			session.send( "/server/join-game", gameId );
		} else {
			System.err.println( "Cannot send message, no active session." );
		}
	}

	private class SessionHandler extends StompSessionHandlerAdapter {

		@Override
		public void afterConnected( final StompSession session, final StompHeaders connectedHeaders ) {
			System.out.println( "Connected to server. Session id: " + session.getSessionId() );
			WebSocketClient.this.session = session;

			// Subscribe to topic
			// TODO: make dynamic
			final int gameId = 1;
			session.subscribe( "/topic/game/" + gameId + "/gamestate", this );
			System.out.println( "Subscribed to /topic/game/" + gameId + "/gamestate" );
			joinGame( gameId );
		}

		@Override
		public void handleFrame( final StompHeaders headers, final Object payload ) {
			System.out.println( "Received message: " + payload );
			if ( payload instanceof GameStateDto ) {
				WebSocketClient.this.gameManager.updateLocalGameState( (GameStateDto) payload );
			}
		}

		@Override
		public Type getPayloadType( final StompHeaders headers ) {
			// TODO: make dynamic
			if ( Objects.equals( headers.getFirst( "destination" ), "/topic/game/1/gamestate" ) ) {
				return GameStateDto.class;
			}

			try {
				return Class.forName( headers.getFirst( "type" ) );
			} catch ( ClassNotFoundException e ) {
				throw new RuntimeException( e );
			}
		}

		@Override
		public void handleTransportError( final StompSession session, Throwable exception ) {
			System.err.println( "Transport error: " + exception.getMessage() );
		}
	}
}
