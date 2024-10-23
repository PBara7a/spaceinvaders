package games.spaceinvaders.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BulletDto {

	private int x;
	private int y;
	private boolean used;

	@Override
	public String toString() {
		return String.format( "BulletDto(x=%d, y=%d, used=%s)", getX(), getY(), isUsed() );
	}
}
