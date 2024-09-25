package telran.net.games.exceptions;

public class GameNotStartedException extends IllegalStateException
{
	public GameNotStartedException(Long gameId)
	{
		super("Not started game " + gameId);
	}
}
