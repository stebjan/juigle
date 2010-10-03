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

/*		PropertiesPerspectiveReader.java
 *  
 *    Copyright (C) 2009 - 2010 
 *    							University of West Bohemia, 
 *                  Department of Computer Science and Engineering, 
 *                  Pilsen, Czech Republic
 */
package ch.ethz.origo.juigle.data;

import java.util.ArrayList;
import java.util.List;

import ch.ethz.origo.juigle.application.IPerspectiveReader;
import ch.ethz.origo.juigle.application.exception.PerspectiveException;
import ch.ethz.origo.juigle.application.exception.PropertiesException;
import ch.ethz.origo.juigle.context.BasicPropertiesReader;

/**
 * This class loading perspectives (which are implemented inside the application
 * - not a plug-in) from the <code>.properties</code> file.
 * 
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 0.1.0.00 (9/30/2010)
 * @since 2.0.0 (9/30/2010)
 * @see {@link IPerspectiveReader}
 */
public class PropertiesPerspectiveReader implements IPerspectiveReader {

	private static final String KEY_PERSPECTIVES = "perspective.list";
	private static final String KEY_PERSPECTIVES_PACKAGE = "perspective.path.package";
	private static final String KEY_DEFAULT_PERSPECTIVE = "perspective.default";

	private String filePath;
	private String perspectivePackage;
	private String defaultPerspective;

	private List<String> listOfPerspectiveNames;

	public PropertiesPerspectiveReader(String filePath) {
		setPerspectiveFilePath(filePath);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getDefaultPerspectiveName() {
		return defaultPerspective;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<String> getListOfInnerPerspectivesNames()
			throws PerspectiveException {
		return listOfPerspectiveNames;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setPerspectiveFilePath(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getPerspectivePackagePath(String perspectiveName) {
		return perspectivePackage;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void readFile() throws PerspectiveException {
		if (filePath == null) {
			throw new PerspectiveException("JG025");
		}
		listOfPerspectiveNames = new ArrayList<String>();

		try {
			BasicPropertiesReader.loadProperties(filePath);
			String[] perspectives = BasicPropertiesReader.getPropertyValues(
					KEY_PERSPECTIVES, ",");
			for (String perspective : perspectives) {
				listOfPerspectiveNames.add(perspective);
			}
			defaultPerspective = BasicPropertiesReader
					.getPropertyValue(KEY_DEFAULT_PERSPECTIVE);
			perspectivePackage = BasicPropertiesReader
					.getPropertyValue(KEY_PERSPECTIVES_PACKAGE);
		} catch (PropertiesException e) {
			throw new PerspectiveException(e);
		}
	}

}