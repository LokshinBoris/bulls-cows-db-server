package telran.net.games;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

	@Entity
	@Table(name="game")
public class Game
	{
		@Id
		private long id;
		private LocalDateTime date;
		private Boolean is_finished;
		private String sequence;
		
		public Game()
		{
			
		}

		public Game(long id, LocalDateTime date, Boolean is_finished, String sequence) {
			super();
			this.id = id;
			this.date = date;
			this.is_finished = is_finished;
			this.sequence = sequence;
		}

		
		public long getId() {
			return id;
		}

		public LocalDateTime getDate() {
			return date;
		}

		public Boolean getIs_finished() {
			return is_finished;
		}

		public String getSequence() {
			return sequence;
		}

		@Override
		public String toString()
		{
			return "Game [id=" + id + ", date=" + date + ""
					+ ", is_finished=" + is_finished + ", sequence=" + sequence +"]";
		}
		
}
