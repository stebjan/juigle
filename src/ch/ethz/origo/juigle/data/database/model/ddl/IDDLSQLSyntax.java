package ch.ethz.origo.juigle.data.database.model.ddl;

import ch.ethz.origo.juigle.application.exception.database.SQLDDLException;

/**
 * Syntax of SQL Data Definition Language
 * 
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 0.1.0 (2/28/2010)
 * @since 0.1.0 (2/28/2010)
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
