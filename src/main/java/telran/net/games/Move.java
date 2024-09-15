package telran.net.games;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

	@Entity
	@Table(name="move")
public class Move
	{
		@Id
		private long id;
		private String sequence;
		private int bulls;
		private int cows;
		private long game_gamer_id;
		
		public Move()
		{
			
		}

		public Move(long id, String sequence, int bulls, int cows, long game_gamer_id)
		{
			super();
			this.id = id;
			this.sequence = sequence;
			this.bulls = bulls;
			this.cows = cows;
			this.game_gamer_id = game_gamer_id;
		}

		public long getId() {
			return id;
		}

		public String getSequence() {
			return sequence;
		}

		public int getBulls() {
			return bulls;
		}

		public int getCows() {
			return cows;
		}

		public long getGame_gamer_id() {
			return game_gamer_id;
		}
		
		

}