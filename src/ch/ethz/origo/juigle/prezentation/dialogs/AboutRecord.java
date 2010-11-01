package ch.ethz.origo.juigle.prezentation.dialogs;

import java.util.ArrayList;
import java.util.List;

/**
 * Record contains basic info for About Dialog
 * 
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 0.1.0 (5/19/2010)
 * @since 1.0.0 (5/19/2010)
 */
public class AboutRecord {
	
	private List<String> authors = new ArrayList<String>();
	private List<String> contributions = new ArrayList<String>();
	
	public List<String> getAuthors() {
		return authors;
	}
	public void addAuthor(String author) {
		this.authors.add(author);
	}
	public List<String> getContribution() {
		return contributions;
	}
	public void addContribution(String contribution) {
		this.contributions.add(contribution);
	}

}
