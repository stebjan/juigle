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
package ch.ethz.origo.juigle.application;

import java.util.ArrayList;
import java.util.List;

import ch.ethz.origo.juigle.application.exception.PerspectiveException;
import ch.ethz.origo.juigle.data.ErrorCodes;
import ch.ethz.origo.juigle.data.PropertiesPerspectiveReader;
import ch.ethz.origo.juigle.data.xml.XMLPerspectiveReader;
import ch.ethz.origo.juigle.plugin.IPluggable;
import ch.ethz.origo.juigle.plugin.PluginEngine;
import ch.ethz.origo.juigle.prezentation.perspective.Perspective;

/**
 * This class loading all perspectives to the application. As first this class
 * loading integrated perspectives. (This must be added to the configure file).
 * Next loader loading classes (perspectives) as Plug-in Engine.
 * 
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 0.1.1.04 (10/29/2010)
 * @since 2.0.0 (07/17/2010) - from the software JERPA
 * @see IPerspectiveLoader
 */
public class PerspectiveLoader implements IPerspectiveLoader {

	/** PLUGIN key for the perspective */
	public static final String PLUGIN_PERSPECTIVES_KEY = "Perspective";

	private static PerspectiveLoader loader;

	private IPerspectiveReader perspectiveReader;

	private List<Perspective> perspectives;

	/**
	 * Default constructor
	 * 
	 * @throws PerspectiveException
	 */
	private PerspectiveLoader() {
	}

	public static PerspectiveLoader getInstance() throws PerspectiveException {
		if (loader == null) {
			loader = new PerspectiveLoader();
		}
		return loader;
	}

	public void loadPerspectives(String perspFilePath)
			throws PerspectiveException {
		if (perspFilePath == null || perspFilePath.length() == 0) {
			throw new PerspectiveException(
					ErrorCodes.CFG_PERSPECTIVE_FILE_NOT_FOUND_P + perspFilePath);
		}

		if (perspFilePath.endsWith(JUIGLEFileUtils.XML_FILE_EXTENSION)) {
			perspectiveReader = new XMLPerspectiveReader(perspFilePath);
		} else if (perspFilePath
				.endsWith(JUIGLEFileUtils.PROPERTIES_FILE_EXTENSION)) {
			perspectiveReader = new PropertiesPerspectiveReader(perspFilePath);
		} else {
			throw new PerspectiveException(ErrorCodes.UNSUPPORTED_PERSPECTIVE_FILE_P
					+ perspFilePath);
		}
		perspectiveReader.readFile();
		ClassLoader loader = PerspectiveLoader.class.getClassLoader();

		perspectives = new ArrayList<Perspective>();
		List<String> perspectivesName = perspectiveReader
				.getListOfInnerPerspectivesNames();

		for (String name : perspectivesName) {
			try {
				Perspective prsvClass = (Perspective) loader.loadClass(
						perspectiveReader.getPerspectivePackagePath(name) + "." + name)
						.newInstance();
				checkIfPerspectiveIsDefault(prsvClass, name);
				perspectives.add(prsvClass);
			} catch (InstantiationException e) {
				throw new PerspectiveException(ErrorCodes.PERSPECTIVE_NOT_LOADED_P
						+ perspectivesName, e);
			} catch (IllegalAccessException e) {
				throw new PerspectiveException(ErrorCodes.PERSPECTIVE_NOT_LOADED_P
						+ perspectivesName, e);
			} catch (ClassNotFoundException e) {
				throw new PerspectiveException(ErrorCodes.PERSPECTIVE_NOT_LOADED_P
						+ perspectivesName, e);
			}
		}
		// now load perspectives from plug-ins
		PluginEngine plugEngine = PluginEngine.getInstance();
		for (IPluggable plugin : plugEngine
				.getAllCorrectPluggables(PerspectiveLoader.PLUGIN_PERSPECTIVES_KEY)) {
			perspectives.add((Perspective) plugin);
			plugEngine.startPluggable(plugin);
		}

	}

	private void checkIfPerspectiveIsDefault(Perspective perspective,
			String className) {
		if (className.equals(perspectiveReader.getDefaultPerspectiveName())) {
			perspective.setPerspectiveAsDefault(true);
		}
	}

	@Override
	public Perspective getDefaultPerspective() {
		for (Perspective per : perspectives) {
			if (per.isDefaultPerspective()) {
				return per;
			}
		}
		return null;
	}

	@Override
	public List<Perspective> getListOfPerspectives() {
		return perspectives;
	}
}