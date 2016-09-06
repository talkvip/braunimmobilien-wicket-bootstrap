package braunimmobilien.service;

public class XtypExistsException extends Exception {
	
	    private static final long serialVersionUID = 4050482305178810162L;

	    /**
	     * Constructor for UserExistsException.
	     *
	     * @param message exception message
	     */
	    public XtypExistsException(final String message) {
	        super(message);
	    }
	}