package games.spaceinvaders.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import games.spaceinvaders.server.game.StateManager;
import games.spaceinvaders.server.game.WaveManager;
import games.spaceinvaders.server.service.GameStateService;

@Configuration
public class GameConfig {

	@Bean
	public StateManager stateManager( final GameStateService gameStateService, final WaveManager waveManager ) {
		return new StateManager( gameStateService, waveManager );
	}

	@Bean
	public WaveManager waveManager() {
		return new WaveManager();
	}
}
