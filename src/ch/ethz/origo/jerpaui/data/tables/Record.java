package ch.ethz.origo.juigle.data.tables;

/**
 * 
 * @author Vaclav Souhrada
 * @version 0.1.0 (11/25/09)
 * @since 0.1.0
 *
 */
public abstract class Record {
	
	private Object id;

	public void setId(Object id) {
		this.id = id;
	}
	
	public Object getId() {
		return id;
	}
	
}
