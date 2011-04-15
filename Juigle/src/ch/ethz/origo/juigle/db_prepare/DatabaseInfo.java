package ch.ethz.origo.juigle.db_prepare;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 
 * 
 * @author Vaclav Souhrada
 * @version 0.1.0.00 (10/12/2010)
 * @since 2.0.0 (9/12/2010)
 * 
 */
public class DatabaseInfo {

	private String name;
	private String dialect;
	private String driverClassName;
	private String url;
	private String userName;
	private String password;
	private String defaultSchemaName;
	private Set<String> schemaNames;

	public DatabaseInfo() {
		// default constructor
	}

	public DatabaseInfo(String name, String dialect, String driverClassName,
			String url, String userName, String password, List<String> schemaNames) {
		super();
		this.name = name;
		this.dialect = dialect;
		this.driverClassName = driverClassName;
		this.url = url;
		this.userName = userName;
		this.password = password;
		this.defaultSchemaName = schemaNames.get(0);
		this.schemaNames = new HashSet<String>(schemaNames);
	}

	public String getName() {
		return name;
	}

	public String getDialect() {
		return dialect;
	}

	public String getDriverClassName() {
		return driverClassName;
	}

	public String getUrl() {
		return url;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public String getDefaultSchemaName() {
		return defaultSchemaName;
	}

	public Set<String> getSchemaNames() {
		return schemaNames;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDialect(String dialect) {
		this.dialect = dialect;
	}

	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}

	public void setURL(String url) {
		this.url = url;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setDefaultSchemaName(String defaultSchemaName) {
		this.defaultSchemaName = defaultSchemaName;
	}

	public void setSchemaNames(List<String> schemaNames) {
		this.schemaNames = new HashSet<String>(schemaNames);
	}

}