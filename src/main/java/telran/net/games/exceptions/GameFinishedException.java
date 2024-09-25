package telran.net.games.exceptions;

public class GameFinishedException extends IllegalStateException 
{
	public GameFinishedException(long gameId)
	{
		super("game is finished" + gameId);
	}
}
