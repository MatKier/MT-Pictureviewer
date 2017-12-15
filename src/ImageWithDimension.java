import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 * Datenklasse f�r das Image. Enth�lt h�he und Breite, da sich diese aus dem
 * eigentlichen Image Objekt nur umst�ndlich auslesen l�sst
 * 
 * @author M.K
 *
 */
public class ImageWithDimension {
	private Image img;
	private int height;
	private int width;

	public Image getImg() {
		return img;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}
	
	/**
	 * Gibt eine Kopie des Bildes in Form eines BufferedImages aus.
	 * @return
	 */
	public BufferedImage getBufferedImage() {
	    // Create a buffered image with transparency
	    BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

	    // Draw the image on to the buffered image
	    Graphics2D bGr = bimage.createGraphics();
	    bGr.drawImage(img, 0, 0, null);
	    bGr.dispose();

	    // Return the buffered image
	    return bimage;
	}

	public ImageWithDimension(Image img, int height, int width) {
		this.img = img;
		this.height = height;
		this.width = width;
	}
}
