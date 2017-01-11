package demo.memory.exceptions;

public class DatabaseRuntimeException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6852073968193053449L;

	public DatabaseRuntimeException(Exception e) {
		super("Error Occurred: "+e.getMessage());
	}
	
	public DatabaseRuntimeException(String message) {
		super(message);
	}
	
}
