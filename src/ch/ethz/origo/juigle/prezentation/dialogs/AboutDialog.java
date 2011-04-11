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
package ch.ethz.origo.juigle.prezentation.dialogs;

import java.awt.Dimension;
import java.util.ResourceBundle;

import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import org.jdesktop.swingx.JXButton;
import org.jdesktop.swingx.JXLabel;
import org.jdesktop.swingx.JXPanel;

import ch.ethz.origo.juigle.application.ILanguage;
import ch.ethz.origo.juigle.application.exception.JUIGLELangException;
import ch.ethz.origo.juigle.application.observers.LanguageObservable;
import ch.ethz.origo.juigle.prezentation.JUIGLEGraphicsUtils;

/**
 * Construct instance of dialog called About dialog.
 * 
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 0.1.0 09/06/09
 * @since 0.1.0 (05/18/09)
 * @see JUIGLEDialog
 */
public class AboutDialog extends JDialog implements ILanguage {

	/** Only for serialization */
	private static final long serialVersionUID = -6954165040360494938L;

	public static String DIALOG_RB_KEY_TITLE = "about.dialog.title";
	public static String DIALOG_RB_KEY_APP_NAME = "about.dialog.app.name";
	public static String DIALOG_RB_KEY_APP_VERSION = "about.dialog.version";
	public static String DIALOG_RB_KEY_COPYRIGHT= "about.dialog.copyright";
	public static String DIALOG_RB_KEY_HOMEPAGE= "about.dialog.homepage";
	public static String DIALOG_RB_KEY_CONTRIBUTION = "about.dialog.contribution";

	private JXPanel jAboutPanel;
	private JXPanel jBtnOKPanel;
	private JXButton jButton1;
	private JXPanel jCreditPanel;
	private JXLabel jLabel1;
	private JXLabel jLabel2;
	private JXLabel jLabel3;
	private JXLabel jLabel4;
	private JXLabel jLogoLabel;
	private JXPanel jLogoPanel;
	private JXPanel jPanel3;
	private JTabbedPane jTabbedPane1;
	private JTextArea jTextArea1;

	private Icon icon;

	private ResourceBundle resource;
	private String path;
	
	private AboutRecord aboutRecord;

	/**
	 * Initialize dialog
	 * 
	 * @throws JUIGLELangException
	 */
	public AboutDialog(String resourceBundlePath, Icon icon, boolean modal)
			throws JUIGLELangException {
		super();
		setLocalizedResourceBundle(resourceBundlePath);
		this.icon = icon;
		initialize();
		this.setModal(modal);
		updateText();
		LanguageObservable.getInstance().attach(this);
	}

	/**
	 * Construct main GUI of the about dialog
	 */
	private void initialize() {
		initComponents();
		initContentPanel();
		this.setAlwaysOnTop(true);
		this.setResizable(true);
		this.pack();
		this.setVisible(false);
		this.setLocation(JUIGLEGraphicsUtils.getCenterPosition(this.getSize()));
	}

	/**
	 * Initialize content panel
	 */
	private void initContentPanel() {
		jTabbedPane1.addTab("",
				jAboutPanel);
		jTabbedPane1.addTab("", jCreditPanel);
		/*
		 * contentPanel = (JXPanel)content; contentPanel.setBackgroundPainter(new
		 * PinstripePainter());
		 */
	}

	private void initComponents() {
		java.awt.GridBagConstraints gridBagConstraints;

		jLogoPanel = new JXPanel();
		jLogoLabel = new JXLabel();
		jBtnOKPanel = new JXPanel();
		jButton1 = new JXButton();
		jPanel3 = new JXPanel();
		jTabbedPane1 = new JTabbedPane();
		jAboutPanel = new JXPanel();
		jLabel1 = new JXLabel();
		jLabel2 = new JXLabel();
		jLabel3 = new JXLabel();
		jLabel4 = new JXLabel();
		jCreditPanel = new JXPanel();
		jTextArea1 = new JTextArea();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		setFont(new java.awt.Font("A.D. MONO", 0, 10));
		setForeground(java.awt.Color.white);
		setModal(true);
		setName("AboutDialog");
		setResizable(false);
		jLogoPanel.setLayout(new java.awt.BorderLayout());

		jLogoPanel.setBorder(new javax.swing.border.EtchedBorder(
				javax.swing.border.EtchedBorder.RAISED));
		jLogoPanel.setPreferredSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));
		jLogoLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLogoLabel.setIcon(icon);
		jLogoPanel.add(jLogoLabel, java.awt.BorderLayout.CENTER);

		getContentPane().add(jLogoPanel, java.awt.BorderLayout.WEST);

		jBtnOKPanel.setBorder(new javax.swing.border.EtchedBorder(
				javax.swing.border.EtchedBorder.RAISED));
		jBtnOKPanel.setPreferredSize(new java.awt.Dimension(350, 35));
		jButton1.setText("Ok");
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				AboutDialog.this.dispose();
			}
		});

		jBtnOKPanel.add(jButton1);

		getContentPane().add(jBtnOKPanel, java.awt.BorderLayout.SOUTH);

		jPanel3.setLayout(new java.awt.BorderLayout());

		jPanel3.setPreferredSize(new java.awt.Dimension(350, 250));
		jAboutPanel.setLayout(new java.awt.GridBagLayout());

		jLabel1.setFont(new java.awt.Font("Dialog", 1, 14));
		jLabel1.setForeground(new java.awt.Color(0, 0, 255));
		jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new java.awt.Insets(30, 5, 30, 5);
		jAboutPanel.add(jLabel1, gridBagConstraints);

		jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 0);
		jAboutPanel.add(jLabel2, gridBagConstraints);

		jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 0);
		jAboutPanel.add(jLabel3, gridBagConstraints);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 0);
		jAboutPanel.add(jLabel4, gridBagConstraints);

		jCreditPanel.setLayout(new java.awt.BorderLayout());

		jTextArea1.setEditable(false);

		jCreditPanel.add(jTextArea1, java.awt.BorderLayout.CENTER);

		jPanel3.add(jTabbedPane1, java.awt.BorderLayout.CENTER);

		getContentPane().add(jPanel3, java.awt.BorderLayout.CENTER);
	}
	
	/**
	 * Set list of authors and contributions
	 * 
	 * @param record list of authors and contributions
	 */
	public void setAboutRecord(AboutRecord record) {
		this.aboutRecord = record;
		if (record != null) {
			StringBuffer sbAuthor = new StringBuffer();
			for (String auhor : aboutRecord.getAuthors()) {
				sbAuthor.append(auhor);
				sbAuthor.append("\n");
			}
			jLabel3.setText(sbAuthor.toString());
		}
		
		StringBuffer sbContr = new StringBuffer();
		for (String contri : aboutRecord.getContribution()) {
			sbContr.append(contri);
	    sbContr.append("\n");
		}
		jTextArea1.setText(sbContr.toString());
	}

	@Override
	public String getResourceBundlePath() {
		return path;
	}

	@Override
	public void setLocalizedResourceBundle(String path) {
		this.path = path;
		this.resource = ResourceBundle.getBundle(path);
	}

	@Override
	public void setResourceBundleKey(String key) {
		throw new RuntimeException("NOT IMPLEMENTED");
	}

	@Override
	public void updateText() throws JUIGLELangException {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				setLocalizedResourceBundle(getResourceBundlePath());
				setTitle("v " + resource.getString(AboutDialog.DIALOG_RB_KEY_APP_NAME) + " "
						+ resource.getString(AboutDialog.DIALOG_RB_KEY_APP_VERSION));
				jLabel1.setText(resource.getString(AboutDialog.DIALOG_RB_KEY_APP_VERSION));
				jLabel4.setText(resource.getString(AboutDialog.DIALOG_RB_KEY_COPYRIGHT));
				jLabel2.setText(resource.getString(AboutDialog.DIALOG_RB_KEY_HOMEPAGE));
				
			jTabbedPane1.setTitleAt(0, resource.getString(AboutDialog.DIALOG_RB_KEY_TITLE));
			jTabbedPane1.setTitleAt(1, resource.getString(AboutDialog.DIALOG_RB_KEY_CONTRIBUTION));

			}

		});
	}
}