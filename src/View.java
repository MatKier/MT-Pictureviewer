import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * View Klasse
 * 
 * @author M.K.
 *
 */
public class View extends JFrame implements Observer {

	private JPanel contentPictures;
	private JLabel picLabel1;
	private JLabel picLabel2;

	private JTextField textFieldFilters;

	private JCheckBoxMenuItem itemBlur;
	private JCheckBoxMenuItem itemDistort;
	private JCheckBoxMenuItem itemGreen;
	private JCheckBoxMenuItem itemSharpen;
	private JCheckBoxMenuItem itemEdgeDetection;
	private JCheckBoxMenuItem itemContrast;
	private JCheckBoxMenuItem itemInvert;
	private JCheckBoxMenuItem itemBW;

	public View(ActionListener controller) {
		this.setTitle("Medientechnik");
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		// Panels
		JPanel contentAll = new JPanel();
		contentAll.setLayout(new BorderLayout());

		contentPictures = new JPanel();
		contentPictures.setLayout(new BorderLayout());
		contentAll.add(contentPictures, BorderLayout.PAGE_START);
		contentAll.add(new JSeparator(SwingConstants.HORIZONTAL),
				BorderLayout.CENTER);

		JPanel contentBottom = new JPanel();
		contentBottom.setLayout(new FlowLayout());
		contentBottom.setBorder(BorderFactory.createEmptyBorder());
		contentAll.add(contentBottom, BorderLayout.PAGE_END);

		// Menu
		JMenuBar menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		JMenu menuFile = new JMenu("File");
		JMenuItem itemLoadFile = new JMenuItem("Load File");
		itemLoadFile.setActionCommand(StringConstants.LOAD_FILE);
		itemLoadFile.addActionListener(controller);
		JMenuItem itemExit = new JMenuItem("Exit");
		itemExit.setActionCommand(StringConstants.EXIT);
		itemExit.addActionListener(controller);
		menuFile.add(itemLoadFile);
		menuFile.addSeparator();
		menuFile.add(itemExit);
		menuBar.add(menuFile);

		JMenu menuFilters = new JMenu("Filters");
		itemBlur = new JCheckBoxMenuItem("Blur");
		itemBlur.setActionCommand(StringConstants.BLUR);
		itemBlur.addActionListener(controller);
		itemDistort = new JCheckBoxMenuItem("Distort");
		itemDistort.setActionCommand(StringConstants.DISTORT);
		itemDistort.addActionListener(controller);
		itemGreen = new JCheckBoxMenuItem("Green");
		itemGreen.setActionCommand(StringConstants.GREEN);
		itemGreen.addActionListener(controller);
		itemSharpen = new JCheckBoxMenuItem("Sharpen");
		itemSharpen.setActionCommand(StringConstants.SHARPEN);
		itemSharpen.addActionListener(controller);
		itemEdgeDetection = new JCheckBoxMenuItem("Edge Detection");
		itemEdgeDetection.setActionCommand(StringConstants.EDGE);
		itemEdgeDetection.addActionListener(controller);
		itemContrast = new JCheckBoxMenuItem("Contrast");
		itemContrast.setActionCommand(StringConstants.CONTRAST);
		itemContrast.addActionListener(controller);
		itemInvert = new JCheckBoxMenuItem("Invert");
		itemInvert.setActionCommand(StringConstants.INVERT);
		itemInvert.addActionListener(controller);
		itemBW = new JCheckBoxMenuItem("B/W");
		itemBW.setActionCommand(StringConstants.BW);
		itemBW.addActionListener(controller);

		menuFilters.add(itemBlur);
		menuFilters.add(itemDistort);
		menuFilters.add(itemGreen);
		menuFilters.add(itemSharpen);
		menuFilters.add(itemEdgeDetection);
		menuFilters.add(itemContrast);
		menuFilters.add(itemInvert);
		menuFilters.add(itemBW);
		menuBar.add(menuFilters);

		JMenu menuAbout = new JMenu("About");
		JMenuItem itemAbout = new JMenuItem("About Medientechnik");
		itemAbout.setActionCommand(StringConstants.ABOUT_MED_TECH);
		itemAbout.addActionListener(controller);
		menuAbout.add(itemAbout);
		menuBar.add(menuAbout);

		// Pictures
		picLabel1 = new JLabel();
		picLabel1.setText("Original");
		picLabel1.setHorizontalTextPosition(SwingConstants.CENTER);
		picLabel1.setVerticalTextPosition(SwingConstants.BOTTOM);
		picLabel2 = new JLabel();
		picLabel2.setText("Preview");
		picLabel2.setHorizontalTextPosition(SwingConstants.CENTER);
		picLabel2.setVerticalTextPosition(SwingConstants.BOTTOM);
		contentPictures.add(picLabel1, BorderLayout.LINE_START);
		contentPictures.add(picLabel2, BorderLayout.LINE_END);

		// Bottom Bar
		JRadioButton radioAllEffects = new JRadioButton("Alle Effekte");
		radioAllEffects.setActionCommand(StringConstants.ALL_EFFECTS);
		radioAllEffects.addActionListener(controller);
		JButton buttonRandom = new JButton("Random");
		buttonRandom.setActionCommand(StringConstants.RANDOM);
		buttonRandom.addActionListener(controller);
		JLabel labelFilters = new JLabel("Filters");
		textFieldFilters = new JTextField(35);
		textFieldFilters.setEditable(false);
		contentBottom.add(radioAllEffects);
		contentBottom.add(buttonRandom);
		contentBottom.add(labelFilters);
		contentBottom.add(textFieldFilters);

		this.setPreferredSize(new Dimension(900, 300));

		this.setContentPane(contentAll);
		this.pack();
	}

	/**
	 * Erzeigt den About-Dialog
	 */
	public void showAboutDialog() {
		JOptionPane.showMessageDialog(this,
				"Author: Wir sollen doch unseren Namen nicht verraten?",
				"About Medientechnik", JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * Erzeugt den Filechooser Dialog und gibt das ausgewählte File zurück
	 * 
	 * @return Ausgewähltes Bild als File Objekt
	 */
	public File showFilechooser() {
		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Bilder",
				"jpg", "gif", "png");
		fileChooser.setFileFilter(filter);
		int chooserResult = fileChooser.showOpenDialog(this);

		if (chooserResult == JFileChooser.APPROVE_OPTION) {
			return fileChooser.getSelectedFile();
		} else {
			return null;
		}
	}

	/**
	 * Skaliert das Bild und gibt ein ImageWithDimensionObjekt zurück, welches
	 * das Image und dessen Maße enthält.
	 * 
	 * Scheitert die Skalierung wird null zurückgegeben
	 * 
	 * @param pictureFile
	 * @return ImageWithDimension Objekt, welches das Image und dessen Maße
	 *         enthält
	 */
	public ImageWithDimension scalePicture(File pictureFile) {
		double screenWidth = Toolkit.getDefaultToolkit().getScreenSize()
				.getWidth();
		double screenHeight = Toolkit.getDefaultToolkit().getScreenSize()
				.getHeight();
		Image img = null;
		int scaledWidth = 0;
		int scaledHeight = 0;
		try {
			BufferedImage myPicture = ImageIO.read(pictureFile);

			img = myPicture;
			int pictureHeight = myPicture.getHeight();
			int pictureWidth = myPicture.getWidth();

			double factorHeight = (screenHeight - 160) / pictureHeight;
			double factorWidth = (screenWidth / 2) / pictureWidth;

			scaledWidth = pictureWidth;
			scaledHeight = pictureHeight;

			// Skaliert Höhe und Breite des Bildes mit dem selben Faktor,
			// falls
			// die Höhe zu groß ist
			if (pictureHeight >= screenHeight - 160) {
				scaledHeight = (int) (pictureHeight * factorHeight);
				scaledWidth = (int) (pictureWidth * factorHeight);
				img = myPicture.getScaledInstance(scaledWidth, scaledHeight,
						Image.SCALE_DEFAULT);
			}
			// Skaliert Höhe und Breite des Bildes mit dem Faktor für
			// Breite,
			// falls die erste Skalierung nicht ausreichend war, um auch die
			// Breite richtig anzupassen (Auch wieder Höhe und Breite mit
			// dem selben Faktor um das Seitenverhältnis zu wahren)
			if (pictureWidth * 2 >= screenWidth && factorWidth < factorHeight) {
				scaledWidth = (int) (pictureWidth * factorWidth);
				scaledHeight = (int) (pictureHeight * factorWidth);
				img = myPicture.getScaledInstance(scaledWidth, scaledHeight,
						Image.SCALE_DEFAULT);
			}
		} catch (IllegalArgumentException iAE) {
			// iAE.printStackTrace();
		} catch (IOException ioE) {
			// ioE.printStackTrace();
		} catch (NullPointerException nPE) {
			// nPE.printStackTrace();
		}
		if (img != null) {
			return new ImageWithDimension(img, scaledHeight, scaledWidth);
		} else {
			return null;
		}
	}

	/**
	 * Immer wenn sich irgenwas im Modell ändert werden alle Objekte im View
	 * aktualisiert. Geht bestimmt schlauer. Leider weiß ich nicht, wie ich hier
	 * unterscheiden kann, was gemacht wurde.
	 * 
	 * So wie es jetzt ist, ist es auf jeden Fall ziemlich schei**. Bei jedem
	 * anticken eines Filters wird das Bild neuskaliert.
	 */
	@Override
	public void update(Observable observable, Object object) {
		Model m = (Model) observable;
		this.setTitle("Medientechnik - " + m.getPictureName());

		if (m.getImg() != null) {
			setFiltersAndPicture(m);
		}
	}

	/**
	 * Setzt die Filter in der JBar und dem Textfeld,
	 * wendet diese auf das Vorschaubild an und setzt die Bilder
	 * 
	 * @param m
	 */
	private void setFiltersAndPicture(Model m) {
		StringBuffer buffer = new StringBuffer();
		checkAllFilters(false);

		BufferedImage filteredImage = m.getImg().getBufferedImage();

		if (m.getBlur()) {
			buffer.append("Blur; ");
			filteredImage = ImageFilters.blurFilter(filteredImage);
			itemBlur.setSelected(true);
		}
		if (m.getDistort()) {
			buffer.append("Distort; ");
			filteredImage = ImageFilters.distortFilter(filteredImage);
			itemDistort.setSelected(true);
		}
		if (m.getGreen()) {
			buffer.append("Green; ");
			filteredImage = ImageFilters.greenFilter(filteredImage);
			itemGreen.setSelected(true);
		}
		if (m.getSharpen()) {
			buffer.append("Sharpen; ");
			filteredImage = ImageFilters.sharpenFilter(filteredImage);
			itemSharpen.setSelected(true);
		}
		if (m.getEdge()) {
			buffer.append("Edge; ");
			filteredImage = ImageFilters.edgeFilter(filteredImage);
			itemEdgeDetection.setSelected(true);
		}
		if (m.getContrast()) {
			buffer.append("Contrast; ");
			filteredImage = ImageFilters.contrastFilter(filteredImage);
			itemContrast.setSelected(true);
		}
		if (m.getInvert()) {
			buffer.append("Invert; ");
			filteredImage = ImageFilters.invertFilter(filteredImage);
			itemInvert.setSelected(true);
		}
		if (m.getBw()) {
			buffer.append("B/W; ");
			filteredImage = ImageFilters.bwFilter(filteredImage);
			itemBW.setSelected(true);
		}
		textFieldFilters.setText(buffer.toString());

		picLabel1.setIcon(new ImageIcon(m.getImg().getImg()));
		picLabel2.setIcon(new ImageIcon(filteredImage));

		this.setPreferredSize(new Dimension(m.getImg().getWidth() * 2, m
				.getImg().getHeight() + 140));
		this.pack();
	}

	/**
	 * Setzt den Selected Status aller Filter auf den übergebenen Boolean
	 * 
	 * @param setValue
	 */
	private void checkAllFilters(Boolean setValue) {
		itemBlur.setSelected(setValue);
		itemDistort.setSelected(setValue);
		itemGreen.setSelected(setValue);
		itemSharpen.setSelected(setValue);
		itemEdgeDetection.setSelected(setValue);
		itemContrast.setSelected(setValue);
		itemInvert.setSelected(setValue);
	}
}
