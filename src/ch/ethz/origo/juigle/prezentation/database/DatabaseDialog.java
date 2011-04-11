package ch.ethz.origo.juigle.prezentation.database;

import org.jdesktop.swingx.JXLoginPane;
import org.jdesktop.swingx.auth.JDBCLoginService;
import org.jdesktop.swingx.auth.LoginListener;

import ch.ethz.origo.juigle.application.db.Database;

public abstract class DatabaseDialog extends JXLoginPane implements LoginListener {

	/**
	 * Only for serialization
	 */
	private static final long serialVersionUID = 2879424870308743334L;
	
	private Database db;
	
	private JDBCLoginService svc;
	
	public DatabaseDialog(Database database) {
		this.db = database;
		initialize();
	}
	
	public void initialize() {
		svc = db.getLoginServiceInstance();
		svc.addLoginListener(this);
		setLoginService(svc);
		
		setBannerText("Connect to database");
		
		JXLoginPane.JXLoginFrame frame = JXLoginPane.showLoginFrame(this);
	  frame.setTitle("Database Login Dialog");
	  frame.setAlwaysOnTop(true);
	  frame.setVisible(true);
	}

}
