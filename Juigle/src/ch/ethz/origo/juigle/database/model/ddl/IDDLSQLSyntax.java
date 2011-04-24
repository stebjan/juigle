package ch.ethz.origo.juigle.database.model.ddl;

import ch.ethz.origo.juigle.context.exceptions.SQLDDLException;

/**
 *
 * 
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 0.1.0 (1/15/2011)
 * @since 1.0.0 (1/15/2011)
 */
public interface IDDLSQLSyntax {

  /**
	 * Syntax create component
	 *
	 * @return syntaxes of creation String
	 * @throws SQLDDLException
	 */
	public String create() throws SQLDDLException;

	/**
	 * Syntax add component to database
	 *
	 * @return syntaxes of addition String
	 * @throws SQLDDLException
	 */
	public String add() throws SQLDDLException;

	/**
	 * Syntax modify component in database
	 *
	 * @return syntaxes of modification String
	 * @throws SQLDDLException
	 */
	public String modify() throws SQLDDLException;

	/**
	 * Syntax Drop component in database
	 *
	 * @return syntaxes of dropping String
	 * @throws SQLDDLException
	 */
	public String drop() throws SQLDDLException;

}
