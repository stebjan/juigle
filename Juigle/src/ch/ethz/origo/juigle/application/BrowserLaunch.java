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

import java.lang.reflect.Method;
import java.util.Arrays;

import javax.swing.JOptionPane;

/**
 * Class for invoking browser
 * 
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 0.1.0 10/28/09
 * @since 0.1.0
 *
 */
public class BrowserLaunch {
	
	 static final String[] browsers = { "firefox", "opera", "konqueror", "epiphany",
     "seamonkey", "galeon", "kazehakase", "mozilla", "netscape" };


	 /**
   * Opens the specified web page in a web browser
   * @param url A web address (URL) of a web page (ex: "http://www.google.com/")
   */
  public static void openURL(String url) {
     String osName = System.getProperty("os.name");
     try {
        if (osName.startsWith("Mac OS")) {
           Class<?> fileMgr = Class.forName("com.apple.eio.FileManager");
           Method openURL = fileMgr.getDeclaredMethod("openURL",
              new Class[] {String.class});
           openURL.invoke(null, new Object[] {url});
           }
        else if (osName.startsWith("Windows"))
           Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);
        else { //assume Unix or Linux
           boolean found = false;
           for (String browser : browsers)
              if (!found) {
                 found = Runtime.getRuntime().exec(
                    new String[] {"which", browser}).waitFor() == 0;
                 if (found)
                    Runtime.getRuntime().exec(new String[] {browser, url});
                 }
           if (!found)
              throw new Exception(Arrays.toString(browsers));
           }
        }
     catch (Exception e) {
        JOptionPane.showMessageDialog(null,
           "Error attempting to launch web browser\n" + e.toString());
        }
     }

}