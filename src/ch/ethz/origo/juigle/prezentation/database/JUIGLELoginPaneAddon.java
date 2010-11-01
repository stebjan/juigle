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
package ch.ethz.origo.juigle.prezentation.database;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.plaf.BorderUIResource;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;
import javax.swing.plaf.metal.MetalLookAndFeel;

import org.jdesktop.swingx.plaf.AbstractComponentAddon;
import org.jdesktop.swingx.plaf.DefaultsList;
import org.jdesktop.swingx.plaf.LookAndFeelAddons;
import org.jdesktop.swingx.plaf.UIManagerExt;

/**
* Pane addon for login dialog
* @author rbair
*/
public class JUIGLELoginPaneAddon extends AbstractComponentAddon {

   /** Creates a new instance of LoginPaneAddon */
   public JUIGLELoginPaneAddon() {
       super("JUIGLELoginPane");
   }

 @Override
 protected void addBasicDefaults(LookAndFeelAddons addon, DefaultsList defaults) {
   super.addBasicDefaults(addon, defaults);
   Color errorBG = new Color(255, 215, 215);

   defaults.add(JUIGLELoginPane.uiClassID, "org.jdesktop.swingx.plaf.basic.BasicLoginPaneUI");
   defaults.add("JUIGLELoginPane.errorIcon",
           LookAndFeel.makeIcon(JUIGLELoginPaneAddon.class, "basic/resources/error16.png"));
   defaults.add("JUIGLELoginPane.bannerFont", new FontUIResource("Arial Bold", Font.PLAIN, 36));
   //#911 Not every LAF has Label.font defined ...
   Font labelFont = UIManager.getFont("Label.font");
   Font boldLabel = labelFont != null ? labelFont.deriveFont(Font.BOLD) : new Font("SansSerif", Font.BOLD, 12);
   defaults.add("JUIGLELoginPane.pleaseWaitFont",
           new FontUIResource(boldLabel));
   defaults.add("JUIGLELoginPane.bannerForeground", new ColorUIResource(Color.WHITE));
   defaults.add("JUIGLELoginPane.bannerDarkBackground", new ColorUIResource(Color.GRAY));
   defaults.add("JUIGLELoginPane.bannerLightBackground", new ColorUIResource(Color.LIGHT_GRAY));
   defaults.add("JUIGLELoginPane.errorBackground", new ColorUIResource(errorBG));
   defaults.add("JUIGLELoginPane.errorBorder",
           new BorderUIResource(BorderFactory.createCompoundBorder(
                   BorderFactory.createEmptyBorder(0, 36, 0, 11),
                   BorderFactory.createCompoundBorder(
                           BorderFactory.createLineBorder(Color.GRAY.darker()),
                           BorderFactory.createMatteBorder(5, 7, 5, 5, errorBG)))));

   UIManagerExt.addResourceBundle(
       "org.jdesktop.swingx.plaf.basic.resources.LoginPane");
 }

 @Override
 protected void addMetalDefaults(LookAndFeelAddons addon, DefaultsList defaults) {
   super.addMetalDefaults(addon, defaults);

   if (isPlastic()) {
     defaults.add("JUIGLELoginPane.bannerForeground", new ColorUIResource(Color.WHITE));
     defaults.add("JUIGLELoginPane.bannerDarkBackground", new ColorUIResource(Color.GRAY));
     defaults.add("JUIGLELoginPane.bannerLightBackground", new ColorUIResource(Color.LIGHT_GRAY));
   } else {
       defaults.add("JUIGLELoginPane.bannerForeground", new ColorUIResource(Color.WHITE));
       defaults.add("JUIGLELoginPane.bannerDarkBackground",
               MetalLookAndFeel.getCurrentTheme().getPrimaryControlDarkShadow());
       defaults.add("JUIGLELoginPane.bannerLightBackground",
               MetalLookAndFeel.getCurrentTheme().getPrimaryControl());
   }
 }

 @Override
 protected void addWindowsDefaults(LookAndFeelAddons addon, DefaultsList defaults) {
   super.addWindowsDefaults(addon, defaults);

   defaults.add("JUIGLELoginPane.bannerForeground", new ColorUIResource(Color.WHITE));
   defaults.add("JUIGLELoginPane.bannerDarkBackground", new ColorUIResource(49, 121, 242));
   defaults.add("JUIGLELoginPane.bannerLightBackground", new ColorUIResource(198, 211, 247));
 }
}

