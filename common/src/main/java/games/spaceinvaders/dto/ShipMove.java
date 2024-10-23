package games.spaceinvaders.dto;

import games.spaceinvaders.constants.Direction;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ShipMove extends PlayerAction {

	private Direction direction;

	public ShipMove( final int player, final Direction direction ) {
		super( player );
		this.direction = direction;
	}

	@Override
	public String toString() {
		return String.format( "ShipMove(player=%d, direction=%s)", getPlayer(), getDirection() );
	}
}
