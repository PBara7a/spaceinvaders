package games.spaceinvaders.server.service;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import games.spaceinvaders.dto.GameStateDto;
import games.spaceinvaders.server.model.GameState;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GameStateService {

	private final SimpMessagingTemplate messagingTemplate;
	private final ObjectMapper objectMapper;

	public void broadcastGameState( final GameState gameState ) {
//		// TODO: investigate - Custom headers added were not getting to the client
//		final var accessor = StompHeaderAccessor.create();
//		accessor.setLeaveMutable( true );
//		accessor.setHeader( "type", gameState.getClass().getName() );
//		messagingTemplate.convertAndSend( "/topic/gamestate", gameStateDto, accessor.getMessageHeaders() );

		final var gameStateDto = objectMapper.convertValue( gameState, GameStateDto.class );
		messagingTemplate.convertAndSend( "/topic/gamestate", gameStateDto );
	}
}
