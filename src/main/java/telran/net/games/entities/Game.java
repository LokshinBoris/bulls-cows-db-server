package telran.net.games.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

	@Entity
	@Table(name="game")
public class Game
	{
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private long id;
		@Column(name="date_time", nullable=true)
		private LocalDateTime dateTime;
		@Column(name="is_finished" ,nullable=false)
		private Boolean isFinished;
		@Column(nullable=false)
		private String sequence;
		
		public Game()
		{
			
		}

		public Game( LocalDateTime dateTime, Boolean isFinished, String sequence) {
			this.dateTime = dateTime;
			this.isFinished = isFinished;
			this.sequence = sequence;
		}

		
		public long getId() {
			return id;
		}

		public LocalDateTime getDate() {
			return dateTime;
		}

		public Boolean isfinished() {
			return isFinished;
		}

		public String getSequence() {
			return sequence;
		}

		public void setDate(LocalDateTime dateTime)
		{
			this.dateTime = dateTime;
		}

		public void setIsFinished(Boolean isFinished) 
		{
			this.isFinished = isFinished;
		}

		public void setSequence(String sequence) 
		{
			this.sequence = sequence;
		}

		
}
