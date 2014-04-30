package exceptii;

public class ClicException extends Exception {

	private static final long serialVersionUID = -1878071495267055811L;

	public ClicException() {
		super();
	}

	public ClicException(String message) {
		super(message);
	}

	public ClicException(String message, Throwable cause) {
		super(message, cause);
	}
}