package telran.net.games.entities;

import jakarta.persistence.*;

	@Entity
	@Table(name="move")
public class Move
	{
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private long id;
		private String sequence;
		private int bulls;
		private int cows;
		@ManyToOne
		@JoinColumn(name="game_gamer_id")
		private GameGamer gameGamer;
		
		public Move()
		{
			
		}
		
		public Move(String sequence, int bulls, int cows, GameGamer gameGamer) 
		{
			super();
			this.sequence = sequence;
			this.bulls = bulls;
			this.cows = cows;
			this.gameGamer = gameGamer;
		}
		
		public long getId() {
			return id;
		}

		public void setSequence(String sequence) {
			this.sequence = sequence;
		}

		public void setBulls(int bulls) {
			this.bulls = bulls;
		}

		public void setCows(int cows) {
			this.cows = cows;
		}

		public void setGameGamer(GameGamer gameGamer) {
			this.gameGamer = gameGamer;
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

		public GameGamer getGame_gamer_id() {
			return gameGamer;
		}
		
		

}