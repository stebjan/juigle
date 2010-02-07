package ch.ethz.origo.juigle.data.database;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;

import ch.ethz.origo.juigle.application.database.DbProvider;

/**
 * 
 * @author Vaclav Souhrada
 * @version 0.1.0 (1/29/2010)
 * @since 0.1.0 (1/29/2010)
 *
 */
public class ProvidersLoader {

	private File file;
	private List<DbProvider> providers;
	
	public ProvidersLoader(File file) {
		this.file = file;
		providers = new ArrayList<DbProvider>();
	}
	
	public List<DbProvider> getListOfProviders() {
		if (providers.size() == 0) {
			//xmlLoad();			
		}
		return providers;
	}
	
	private void xmlLoad(String filename) {
		 DOMParser parser = new DOMParser();
     Document document = parser.getDocument();
     Element root = document.getDocumentElement(); // root element
	}
}
