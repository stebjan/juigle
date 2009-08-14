package ch.ethz.origo.juigle.application.listener;

import java.util.EventObject;

public class AppButtonsEvent extends EventObject {

  /**
   * Only for serialization
   */
  private static final long serialVersionUID = -7299616202161865296L;
  
  private int id; // id of event

  private String message; // information text
  
  private Exception exception; // exception
  
  /**
   * Initialize event
   * 
   * @param source object - owner
   * @param id type of event
   * @param message text to display
   * @param exception caused Exception
   */
  public AppButtonsEvent(Object source, int id, String message, Exception exception) {
    super(source);
    setId(id);
    this.setMessage(message);
    this.setException(exception);
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
