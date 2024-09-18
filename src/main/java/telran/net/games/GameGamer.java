package telran.net.games;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="game_gamer")
public class GameGamer
{
	@Id
	private long id;
	private Boolean is_winner;
	@ManyToOne
	@JoinColumn(name="game_id")
	private Game game;
	@ManyToOne	
	@JoinColumn(name="gamer_id")
	private Gamer gamer;
	
	public GameGamer()
	{
		
	}

	public long getId() {
		return id;
	}

	public Boolean getIs_winner() {
		return is_winner;
	}

	@Override
	public String toString()
	{
		return "Game [id=" + id + ", game_id=" + game + ""
				+ ", gamer_id=" + gamer + ", is_winner=" + is_winner +"]";
	}
	
}
