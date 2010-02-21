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
 *    JUIGLEFrame.java
 *    Copyright (C) 2009 University of West Bohemia, 
 *                       Department of Computer Science and Engineering, 
 *                       Pilsen, Czech Republic
 */
package ch.ethz.origo.juigle.prezentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Paint;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import nezarazeno.IPerspectiveLoader;

import org.apache.log4j.Logger;
import org.jdesktop.swingx.JXButton;
import org.jdesktop.swingx.JXCollapsiblePane;
import org.jdesktop.swingx.JXFrame;
import org.jdesktop.swingx.JXLabel;
import org.jdesktop.swingx.JXPanel;
import org.jdesktop.swingx.painter.Painter;

import ch.ethz.origo.juigle.application.exception.PerspectiveException;
import ch.ethz.origo.juigle.data.EmailErrorReporter;
import ch.ethz.origo.juigle.data.JUIGLEErrorParser;
import ch.ethz.origo.juigle.prezentation.perspective.Perspective;
import ch.ethz.origo.juigle.prezentation.perspective.PerspectivePanel;

import com.nilo.plaf.nimrod.NimRODLookAndFeel;
import com.nilo.plaf.nimrod.NimRODTheme;

/**
 * Main <code>JUIGLE<code> software java frame.
 * 
 * @author Vaclav Souhrada (v.souhrada at gmail.com)
 * @version 0.2.3 (2/21/2010)
 * @since 0.1.0 (05/18/09)
 * @see JXFrame
 */
public class JUIGLEFrame extends JXFrame {

	/** Only for serialization */
	private static final long serialVersionUID = -6992843525391631876L;

	private String title = "";
	private String copyright = "";

	private static Logger logger = Logger.getLogger(JUIGLEFrame.class);

	private GridBagConstraints gbcMenuToolBar;

	private JXPanel jContentPane;
	private JXPanel headerPanel;
	private JXPanel footerPanel;

	public static JXCollapsiblePane headerCoollapse;
	public static JXCollapsiblePane footerCollapse;

	private PerspectivePanel perspectivePanel;

	private JUIGLEMainMenu mainToolBar;

	private JXButton maximalizeApp;

	private BufferedImage logoImg, testImg;
	private BufferedImage minimizeImg, closeImg, minimizeOverImg;
	private BufferedImage closeOverImg, resizerImg;
	private BufferedImage maximizeImg, maximizeOverImg, maximize2Img,
			maximize2OverImg;

	private InnerListener innerListener;

	private IPerspectiveLoader perspectiveLoader;

	private GridBagConstraints gbcCopyright1;

	private static int frameExtendState;

	public JUIGLEFrame() {
		try {
			initialize();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PerspectiveException e) {
			// parsing error message
			String errorMSG = JUIGLEErrorParser.getJUIGLEErrorMessage(e.getMessage());
			// display error GUI
			JUIGLErrorInfoUtils.showErrorDialog("Error dialog", errorMSG, e,
					Level.WARNING, new EmailErrorReporter());
			logger.warn(errorMSG, e);
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param title
	 *          Frame title
	 * @param logoImg
	 * 
	 * @since 0.1.0
	 */
	public JUIGLEFrame(String title, InputStream logoImg) {
		try {
			setAppTitle(title);
			setLogo(logoImg);
			initialize();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (PerspectiveException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * 
	 * @param title
	 * @param logoImg
	 * @param perspectiveLoader
	 * @since 0.1.0
	 */
	public JUIGLEFrame(String title, InputStream logoImg,
			IPerspectiveLoader perspectiveLoader) {
		this(title, logoImg);
		try {
			setPerspectives(perspectiveLoader);
		} catch (PerspectiveException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @throws IOException
	 * @throws PerspectiveException
	 * @since 0.1.0
	 */
	private void initialize() throws IOException, PerspectiveException {
		initImages();
		initGUI();
	}

	/**
	 * Initialize images
	 * 
	 * @since 0.1.0
	 */
	private void initImages() throws PerspectiveException {
		try {

			minimizeImg = ImageIO
					.read(ClassLoader
							.getSystemResourceAsStream("ch/ethz/origo/juigle/data/images/minimize.png"));
			maximizeImg = ImageIO
					.read(ClassLoader
							.getSystemResourceAsStream("ch/ethz/origo/juigle/data/images/maximize.png"));
			closeImg = ImageIO
					.read(ClassLoader
							.getSystemResourceAsStream("ch/ethz/origo/juigle/data/images/close.png"));
			minimizeOverImg = ImageIO
					.read(ClassLoader
							.getSystemResourceAsStream("ch/ethz/origo/juigle/data/images/minimize_over.png"));
			maximizeOverImg = ImageIO
					.read(ClassLoader
							.getSystemResourceAsStream("ch/ethz/origo/juigle/data/images/maximize_over.png"));
			closeOverImg = ImageIO
					.read(ClassLoader
							.getSystemResourceAsStream("ch/ethz/origo/juigle/data/images/close_over.png"));
			maximize2OverImg = ImageIO
					.read(ClassLoader
							.getSystemResourceAsStream("ch/ethz/origo/juigle/data/images/maximize2_over.png"));
			maximize2Img = ImageIO
					.read(ClassLoader
							.getSystemResourceAsStream("ch/ethz/origo/juigle/data/images/maximize2.png"));
			resizerImg = ImageIO
					.read(ClassLoader
							.getSystemResourceAsStream("ch/ethz/origo/juigle/data/images/resizer.png"));

			testImg = ImageIO
					.read(ClassLoader
							.getSystemResourceAsStream("ch/ethz/origo/juigle/data/images/aaa.png"));
		} catch (IOException e) {
			JUIGLEFrame.logger.error("Could not read default images...", e); // TODO
			// nefunguje
			// TODO vylepsit chybu vypisem do GUI
			throw new PerspectiveException(e);
		}
	}

	/**
	 * Initialize GUI of <code>JERPA</code>> Frame
	 * 
	 * @throws PerspectiveException
	 * 
	 * @since 0.1.0
	 */
	private void initGUI() throws PerspectiveException {
		JUIGLEFrame.logger.info("...Initialize The Main Frame...");
		System.setProperty("sun.awt.noerasebackground", "true");
		System.setProperty("sun.java2d.noddraw", "true");
		System.setProperty("sun.java2d.opengl", "true");

		UIManager.put("Button.textShiftOffset", 0);

		try {
			NimRODTheme nt = new NimRODTheme();
			nt.setPrimary1(new Color(0, 98, 137));
			nt.setPrimary2(new Color(104, 188, 222));
			nt.setPrimary3(new Color(104, 188, 222));
			NimRODLookAndFeel look = new NimRODLookAndFeel();
			NimRODLookAndFeel.setCurrentTheme(nt);
			UIManager.setLookAndFeel(look);
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		innerListener = new InnerListener();

		this.setDefaultCloseOperation(JXFrame.EXIT_ON_CLOSE);
		this.setSize(new Dimension(800, 600));
		this.setUndecorated(true);
		this.setTitle(title);
		this.setContentPane(getContetPane());
		this.setStartPosition(JXFrame.StartPosition.CenterInScreen);
	}

	/**
	 * 
	 * @return
	 * @throws PerspectiveException
	 * @since 0.1.0
	 */
	private JXPanel getContetPane() throws PerspectiveException {
		if (jContentPane == null) {
			jContentPane = new JXPanel();
			jContentPane.setBackground(Color.lightGray);
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getHeaderPanel(), BorderLayout.NORTH);
			jContentPane.add(getPerspectivesPanel(), BorderLayout.CENTER);
			jContentPane.add(getFooterPanel(), BorderLayout.SOUTH);
		}
		return jContentPane;
	}

	/**
	 * This method create Application header panel
	 * 
	 * @return
	 * @throws PerspectiveException
	 * @since 0.1.0
	 */
	private JXCollapsiblePane getHeaderPanel() throws PerspectiveException {
		final Paint backgroundMenu = JUIGLEGraphicsUtils.createBackgroundTexture(
				new Color(0, 98, 137), new Color(104, 188, 222),
				logoImg.getHeight() + 5);
		headerPanel = new JXPanel(true);
		Painter<Component> p = new Painter<Component>() {
			@Override
			public void paint(Graphics2D g, Component c, int width, int height) {
				Graphics2D g2d = (Graphics2D) g;
				g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
						RenderingHints.VALUE_ANTIALIAS_ON);

				g2d.setPaint(backgroundMenu);
				if (getExtendedState() != JXFrame.MAXIMIZED_BOTH) {
					g2d.fillRoundRect(0, 0, c.getWidth(), c.getHeight() + 5, 20, 20);
				} else
					g2d.fillRect(0, 0, c.getWidth(), c.getHeight() + 5);
			}
		};
		headerPanel.setBackgroundPainter(p);

		JXButton minimalizeApp = new JXButton(new ImageIcon(minimizeImg));
		minimalizeApp.setRolloverIcon(new ImageIcon(minimizeOverImg));
		minimalizeApp.setBorder(null);
		minimalizeApp.setFocusPainted(false);
		minimalizeApp.setContentAreaFilled(false);
		minimalizeApp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setExtendedState(JXFrame.ICONIFIED);
			}
		});
		maximalizeApp = new JXButton(new ImageIcon(maximizeImg));
		maximalizeApp.setRolloverIcon(new ImageIcon(maximizeOverImg));
		maximalizeApp.setBorder(null);
		maximalizeApp.setFocusPainted(false);
		maximalizeApp.setContentAreaFilled(false);
		maximalizeApp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (getExtendedState() != JXFrame.MAXIMIZED_BOTH) {
					setExtendedState(JXFrame.MAXIMIZED_BOTH);
				} else {
					setExtendedState(JXFrame.NORMAL);
				}
			}
		});
		JXButton closeApp = new JXButton(new ImageIcon(closeImg));
		closeApp.setRolloverIcon(new ImageIcon(closeOverImg));
		closeApp.setBorder(null);
		closeApp.setFocusPainted(false);
		closeApp.setContentAreaFilled(false);
		closeApp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		headerPanel.setLayout(new GridBagLayout());

		GridBagConstraints gbcLogoLabel = new GridBagConstraints(0, 0, 1, 1, 10.0,
				1.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(
						10, 0, 0, 0), 0, 0);
		GridBagConstraints gbcMinimalizeButt = new GridBagConstraints(2, 0, 1, 1,
				0.0, 0, GridBagConstraints.NORTHEAST, GridBagConstraints.NONE,
				new Insets(0, 0, 0, 0), 0, 0);
		GridBagConstraints gbcMaximalizeButt = new GridBagConstraints(3, 0, 1, 1,
				0.0, 0, GridBagConstraints.NORTHEAST, GridBagConstraints.NONE,
				new Insets(0, 0, 0, 0), 0, 0);
		GridBagConstraints gbcCloseButt = new GridBagConstraints(4, 0, 1, 1, 0.0,
				0, GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, new Insets(0,
						0, 0, 0), 0, 0);

		gbcMenuToolBar = new GridBagConstraints(1, 1, 1, 1, 0.0, 0,
				GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, new Insets(0, 0,
						0, 0), 0, 0);

		headerPanel.add(new JXLabel(new ImageIcon(testImg)), gbcLogoLabel);
		headerPanel.add(minimalizeApp, gbcMinimalizeButt);
		headerPanel.add(maximalizeApp, gbcMaximalizeButt);
		headerPanel.add(closeApp, gbcCloseButt);

		headerPanel.addMouseListener(innerListener);
		headerPanel.addMouseMotionListener(innerListener);

		headerPanel.setOpaque(false);

		headerCoollapse = new JXCollapsiblePane();
		headerCoollapse.add(headerPanel);

		return headerCoollapse;
	}

	/**
	 * 
	 * @throws PerspectiveException
	 * @version 1.0.0
	 * @since 0.1.0
	 */
	public void addMainMenu(JUIGLEMainMenu mainMenu) throws PerspectiveException {
		mainToolBar = mainMenu;
		mainToolBar.setFloatable(false);
		mainToolBar.setRollover(true);
		mainToolBar.setOpaque(false);
		headerPanel.add(mainToolBar, gbcMenuToolBar);

	}

	/**
	 * 
	 * @return
	 * @throws PerspectiveException
	 * @since 0.1.0
	 */
	public JXPanel getPerspectivesPanel() throws PerspectiveException {
		if (perspectivePanel == null) {
			perspectivePanel = new PerspectivePanel();
			if (perspectiveLoader != null) {
				Perspective perspective = perspectiveLoader.getDefaultPerspective();
				perspectivePanel.add(perspective);
			}
		}
		return perspectivePanel;
	}

	/**
	 * 
	 * @return
	 * @since 0.1.0
	 */
	private JXCollapsiblePane getFooterPanel() {
		if (footerPanel == null) {
			footerPanel = new JXPanel(new GridBagLayout(), true);
			final Paint footerBackground = JUIGLEGraphicsUtils
					.createBackgroundTexture(Color.WHITE, Color.LIGHT_GRAY, 45);
			Painter<Component> painter = new Painter<Component>() {

				@Override
				public void paint(Graphics2D g, Component c, int width, int height) {
					Graphics2D g2d = (Graphics2D) g;
					g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
							RenderingHints.VALUE_ANTIALIAS_ON);
					g2d.setPaint(footerBackground);

					if (getExtendedState() != JXFrame.MAXIMIZED_BOTH) {
						g2d.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 20, 20);
					} else {
						g2d.fillRect(0, 0, c.getWidth(), c.getHeight());
					}
				}
			};
			footerPanel.setPreferredSize(new Dimension(100, 45));
			footerPanel.setOpaque(false);
			footerPanel.setBackgroundPainter(painter);

			GridBagConstraints gbcResizer = new GridBagConstraints(2, 0, 1, 1, 1, 1,
					GridBagConstraints.SOUTHEAST, GridBagConstraints.NONE, new Insets(0,
							0, 3, 3), 0, 0);
			GridBagConstraints gbcAppName = new GridBagConstraints(1, 0, 1, 1, 1, 1,
					GridBagConstraints.SOUTHEAST, GridBagConstraints.NONE, new Insets(0,
							0, 3, 0), 0, 0);
			gbcCopyright1 = new GridBagConstraints(1, 1, 1, 1, 1, 1,
					GridBagConstraints.SOUTHEAST, GridBagConstraints.NONE, new Insets(0,
							0, 3, 0), 0, 0);

			JLabel resizer = new JLabel(new ImageIcon(resizerImg));
			resizer.setCursor(Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR));
			resizer.addMouseMotionListener(innerListener);
			resizer.addMouseListener(innerListener);

			footerPanel.add(resizer, gbcResizer);
			footerPanel.add(new JXLabel(title), gbcAppName);
			footerPanel.add(new JXLabel(copyright), gbcCopyright1);

			footerCollapse = new JXCollapsiblePane();
			footerCollapse.add(footerPanel);
		}
		return footerCollapse;
	}

	/**
	 * 
	 * 
	 * @param minimizeImg
	 * @param minimizeOverImg
	 * @version 0.1.0
	 * @since 0.1.0
	 */
	public void setIconifiedButton(InputStream minimizeImg,
			InputStream minimizeOverImg) {
		try {
			this.minimizeImg = ImageIO.read(minimizeImg);
			this.minimizeOverImg = ImageIO.read(minimizeOverImg);
		} catch (IOException e) {
			// TODO osetrit vyjimku
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * 
	 * @param maximizeImg
	 * @param maximizeOverImg
	 * @param maximize2Img
	 * @param maximize2OverImg
	 * @since 0.1.0
	 */
	public void setMaximalizeButton(InputStream maximizeImg,
			InputStream maximizeOverImg, InputStream maximize2Img,
			InputStream maximize2OverImg) {
		try {
			this.maximizeImg = ImageIO.read(maximizeImg);
			this.maximizeOverImg = ImageIO.read(maximizeOverImg);
			this.maximize2Img = ImageIO.read(maximize2Img);
			this.maximize2OverImg = ImageIO.read(maximize2OverImg);
		} catch (IOException e) {
			// TODO osetrit vyjimku
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * 
	 * @param closeImg
	 * @param closeOverImg
	 * @since 0.1.0
	 */
	public void setCloseButton(InputStream closeImg, InputStream closeOverImg) {
		try {
			this.closeImg = ImageIO.read(closeImg);
			this.closeOverImg = ImageIO.read(closeOverImg);
		} catch (IOException e) {
			// TODO osetrit vyjimku
			e.printStackTrace();
		}
	}

	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
	}

	/**
	 * 
	 * 
	 * @param logo
	 * @since 0.1.0
	 */
	public void setLogo(InputStream logo) {
		try {
			this.logoImg = ImageIO.read(logo);
		} catch (IOException e) {
			// TODO osetrit vyjimku
			e.printStackTrace();
		}
	}

	public void setAppTitle(String title) {
		this.title = title;
	}

	public void setCopyrightTitle(String copyright) {
		this.copyright = copyright;
		footerPanel.add(new JXLabel(copyright), gbcCopyright1);
	}

	public void setAndInitPerspective(Perspective perspective)
			throws PerspectiveException {
		perspectivePanel.add(perspective);
	}

	@Override
	public synchronized void setExtendedState(int state) {
		super.setExtendedState(state);
		JUIGLEFrame.frameExtendState = state;
		if (state == JXFrame.MAXIMIZED_BOTH) {
			maximalizeApp.setIcon(new ImageIcon(maximize2Img));
			maximalizeApp.setRolloverIcon(new ImageIcon(maximize2OverImg));
		} else {
			maximalizeApp.setIcon(new ImageIcon(maximizeImg));
			maximalizeApp.setRolloverIcon(new ImageIcon(maximizeOverImg));
		}
	}

	public void setPerspectives(IPerspectiveLoader perspectiveLoader)
			throws PerspectiveException {
		this.perspectiveLoader = perspectiveLoader;
		mainToolBar.addPerspectiveItems(JUIGLEGraphicsUtils.createImageIcon(
				"ch/ethz/origo/juigle/data/images/tabs_48.png", 32, 32),
				perspectivePanel, perspectiveLoader.getListOfPerspectives());
		perspectivePanel.add(perspectiveLoader.getDefaultPerspective());
	}

	/**
	 * This method return current extended state of <code>JERPAFrame</code>.
	 * 
	 * @return current extended state of <code>JERPAFrame</code>> as integer type.
	 */
	public static int getFrameState() {
		return JUIGLEFrame.frameExtendState;
	}

	/**
	 * 
	 * 
	 * @param fullScreen
	 * @version 0.1.0
	 * @since 0.2.2
	 */
	public void setFullScreen(boolean fullScreen) {
		if (fullScreen && getExtendedState() != JXFrame.MAXIMIZED_BOTH) {
			setExtendedState(JXFrame.MAXIMIZED_BOTH);
		}

	}

	/**
	 * 
	 * 
	 * @author Vaclav Souhrada
	 * @version 0.1.0 07/12/2009
	 * @since 0.1.0
	 * 
	 */
	protected class InnerListener extends MouseAdapter implements
			MouseMotionListener {

		private Point startDrag;

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
				if (getExtendedState() == JXFrame.NORMAL) {
					setExtendedState(JXFrame.MAXIMIZED_BOTH);
				} else {
					setExtendedState(JXFrame.NORMAL);
				}
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			if (e.getButton() == MouseEvent.BUTTON1)
				startDrag = e.getPoint();
			else
				startDrag = null;
		}

		public void mouseDragged(MouseEvent e) {
			if (startDrag != null) {
				if (e.getComponent() == headerPanel) {
					if (getExtendedState() != JXFrame.MAXIMIZED_BOTH)
						setLocation(e.getX() + getLocation().x - startDrag.x, e.getY()
								+ getLocation().y - startDrag.y);

				} else { // TODO zvazit ucinost tohoto kodu
					int width = getWidth() + e.getX() - startDrag.x;
					int height = getHeight() + e.getY() - startDrag.y;
					if (width < getMinimumSize().width) {
						width = getMinimumSize().width;
					}
					if (height < getMinimumSize().height) {
						height = getMinimumSize().height;
					}
					setSize(width, height);
					setVisible(true);
					repaint();
				}
			}
		}

		public void mouseMoved(MouseEvent e) {
		}

	}
	
}
