package ch.ethz.origo.juigle.data.tables;

/**
 * Abstract class for classes (objects) which represents any type of record and
 * need use ID. For example it should be used for database records.
 * 
 * @author Vaclav Souhrada
 * @version 0.1.1 (03/10/2010)
 * @since 0.1.0 (11/25/09)
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
