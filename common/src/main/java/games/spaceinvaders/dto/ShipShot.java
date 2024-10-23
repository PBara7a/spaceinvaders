package games.spaceinvaders.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ShipShot extends PlayerAction {

	public ShipShot( final int player ) {
		super( player );
	}

	@Override
	public String toString() {
		return String.format( "ShipShoot(player=%d)", getPlayer() );
	}
}
