package ch.ethz.origo.juigle.data.database.model.constraint;

import java.util.ArrayList;
import java.util.List;

import ch.ethz.origo.juigle.data.database.model.DBColumn;
import ch.ethz.origo.juigle.data.database.model.DBTable;
import ch.ethz.origo.juigle.data.database.model.ddl.ForeignKeyDDLSQLSyntax;

/**
 * 
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 0.1.0 (3/02/2010)
 * @since 0.1.0 (3/02/2010)
 * @see DBConstraint
 * 
 */
public class ForeignKeyConstraint extends DBConstraint {

	protected List<DBColumn> fkColumnList;
	protected List<DBColumn> pkColumnList;
	protected DBTable targetTable;

	public ForeignKeyConstraint(String name, DBTable ownerTable,
			DBColumn fkOwnerColumn, DBTable targetTable, DBColumn pkTargetColumn) {
		super(name, ownerTable);
		this.targetTable = targetTable;
		fkColumnList = new ArrayList<DBColumn>();
		pkColumnList = new ArrayList<DBColumn>();
		fkColumnList.add(fkOwnerColumn);
		pkColumnList.add(pkTargetColumn);
	}

	public ForeignKeyConstraint(DBTable ownerTable, DBColumn fkOwnerColumn,
			DBTable targetTable, DBColumn pkTargetColumn) {
		this(autoNameGenerator("FK", ownerTable.getComponentName()), ownerTable,
				fkOwnerColumn, targetTable, pkTargetColumn);
	}

	public ForeignKeyConstraint(String name, DBTable ownerTable,
			List<DBColumn> fkOwnerColumn, DBTable targetTable,
			List<DBColumn> pkTargetColumn) {
		super(name, ownerTable);
		this.fkColumnList = fkOwnerColumn;
		this.pkColumnList = pkTargetColumn;
	}

	public ForeignKeyConstraint(DBTable ownerTable, List<DBColumn> fkOwnerColumn,
			DBTable targetTable, List<DBColumn> pkTargetColumn) {
		this(autoNameGenerator("FK", ownerTable.getComponentName()), ownerTable,
				fkOwnerColumn, targetTable, pkTargetColumn);
	}
	
	public Class<?> getDDLSQLSyntaxClass() {
    return ForeignKeyDDLSQLSyntax.class;
  }

  public List<DBColumn> gtFKownerColumns() {
    return fkColumnList;
  }

  /**
   * Gets count of columns, which are linked between FK column(s) and PK column(s).
   *
   * @return Count of column.
   */
  public int getFkColumnCount() {
    return fkColumnList.size();
  }

  public int getPkColumnCount() {
    return pkColumnList.size();
  }
  
  private boolean isIndexBound(List<DBColumn> ar, int index) {
    return index >= 0 && index < ar.size();
  }

  private DBColumn getColumn(List<DBColumn> cols, int index) {
    if (isIndexBound(cols, index)) return cols.get(index);
    return null;
  }

  public DBColumn getFKownerColumn(int index) {
    return getColumn(fkColumnList, index);
  }

  public List<DBColumn> getPKtargetColumns() {
    return pkColumnList;
  }

  public DBColumn getPKtargetColumn(int index) {
    return getColumn(pkColumnList, index);
  }

  public DBTable getTargetTable() {
    return targetTable;
  }

}
