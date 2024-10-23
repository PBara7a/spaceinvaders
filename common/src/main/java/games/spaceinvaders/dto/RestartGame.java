package games.spaceinvaders.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RestartGame extends PlayerAction {
	public RestartGame( final int player ) {
		super( player );
	}
}
