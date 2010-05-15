/*
 *    This program is free software; you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation; either version 2 of the License, or
 *    (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with this program; if not, write to the Free Software
 *    Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 */

/*
 *  
 *    Copyright (C) 2009 - 2010 
 *    							University of West Bohemia, 
 *                  Department of Computer Science and Engineering, 
 *                  Pilsen, Czech Republic
 */
package ch.ethz.origo.juigle.data.database.model.types;

/**
 * 
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 0.1.0 (3/03/2010)
 * @since 0.1.0 (3/03/2010)
 * @see AbstractSQLDataType
 * 
 */
public class SQLChar extends AbstractSQLDataType {
	
	public static final String CHAR_TYPE = "CHAR";
	
	public SQLChar(int size) {
		this.size = size;
	}

	@Override
	public String getSyntax() {
		return syntaxType + "(" + size + ")";
	}

	@Override
	protected String getSyntaxFIREBIRD() {
		return SQLChar.CHAR_TYPE;
	}

	@Override
	protected String getSyntaxMSSQL() {
		return SQLChar.CHAR_TYPE;
	}

	@Override
	protected String getSyntaxMYSQL() {
		return null;
	}

	@Override
	protected String getSyntaxMaxDB() {
		return SQLChar.CHAR_TYPE;
	}

	@Override
	protected String getSyntaxORACLE() {
		return SQLChar.CHAR_TYPE;
	}

	@Override
	protected String getSyntaxPOSTGRESQL() {
		return SQLChar.CHAR_TYPE;
	}

	@Override
	public String setBasicType() {
		return SQLChar.CHAR_TYPE;
	}

	@Override
	public Class<?> setJavaType() {
		return String.class;
	}

}
