package telran.net.games.exception;

@SuppressWarnings("serial")
public class GamerNotFoundException extends IllegalArgumentException {
	public GamerNotFoundException(String username) {
		super("Not found gamer " + username);
	}
}