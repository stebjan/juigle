package ch.ethz.origo.juigle.application.listener;

import java.util.EventObject;

/**
 * 
 * 
 * @author Vaclav Souhrada (v.souhrada@gmail.com)
 * @version 0.1.0 08/12/09
 * @since 0.1.0 (05/18/09)
 * @see EventObject
 */
public class EventJUIGLEObject extends EventObject {

	/** Only for serialization */
	private static final long serialVersionUID = 4310379668745503846L;
	
	private int id; // id of event

	private String message; // information text

	private Exception exception; // exception

	
	public EventJUIGLEObject(Object source, int id, String message,
			Exception exceptnion) {
		super(source);
		
	}
	
	public int getId() {
		return id;
	}

	public String getMessage() {
		return message;
	}

	public Exception getException() {
		return exception;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}
	
}
