package telran.net.games.exception;

@SuppressWarnings("serial")
public class GamerAlreadyExistsException extends IllegalArgumentException {
	public GamerAlreadyExistsException(String username) {
		super("Already exists gamer " + username);
	}
}