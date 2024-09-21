package telran.net.games.exception;

@SuppressWarnings("serial")
public class GameNotFoundException extends IllegalArgumentException 
{			 
	public GameNotFoundException(long gameId) 
	{
		super("Not found game " + gameId);
	}
}
