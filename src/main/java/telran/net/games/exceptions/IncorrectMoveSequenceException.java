package telran.net.games.exceptions;

public class IncorrectMoveSequenceException extends IllegalArgumentException 
{
	public IncorrectMoveSequenceException(String sequence)
	{
		super("Incorrect sequence " + sequence);
	}
}
