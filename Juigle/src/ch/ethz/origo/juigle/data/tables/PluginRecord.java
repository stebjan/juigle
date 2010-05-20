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
package ch.ethz.origo.juigle.data.tables;

import ch.ethz.origo.juigle.plugin.IPluggable;
import ch.ethz.origo.juigle.prezentation.tables.PluginsTreeTable;

/**
 * Class which represents plug-in for PluginsTreeTable.
 * 
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 0.1.1 (5/19/2010)
 * @since 0.1.0 (3/28/2010)
 * @see Record
 * @see Comparable
 * @see PluginsTreeTable
 */
public class PluginRecord extends Record implements Comparable<PluginRecord> {

	private String category;
	private IPluggable plugin;

	public PluginRecord() {

	}

	public PluginRecord(IPluggable plugin) {
		setPlugin(plugin);
		setCategory(plugin.getCategory());
	}

	/**
	 * Set category of algorithm. For example <code>Filter</code>
	 * 
	 * @param category
	 *          name of category
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * Return category name of algorithm
	 * 
	 * @return category name
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * Set Plugin class <code>Pluggable</code> of algorithm
	 * 
	 * @param algorithmClass
	 *          class
	 */
	public void setPlugin(IPluggable algorithmClass) {
		this.plugin = algorithmClass;
	}

	/**
	 * Get algorithm class
	 * 
	 * @return class of algorithm
	 */
	public IPluggable getPlugin() {
		return plugin;
	}

	@Override
	public String toString() {
		if (getPlugin() != null) {
			StringBuffer sb = new StringBuffer();
			sb.append(category);
			sb.append(" -> " + plugin.getPluginName() + " -> "
					+ plugin.getPluginVersion() + " -> " + plugin.getAuthorName());
		}
		return category;
	}

	@Override
	public int compareTo(PluginRecord fr) {
		int res = 1;
		IPluggable plug = fr.getPlugin();
		if (plug != null && getPlugin() != null) {
			int author = plug.getAuthorName().compareTo(getPlugin().getAuthorName());
			int version = plug.getPluginVersion().compareTo(
					getPlugin().getPluginVersion());
			int category = plug.getCategory().compareTo(getPlugin().getCategory());
			if (author == 0 && version == 0 && category == 0) {
				return 0;
			}
		}
		return res;
	}

}
