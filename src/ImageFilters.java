import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

import com.jhlabs.image.ContrastFilter;
import com.jhlabs.image.PolarFilter;

/**
 * Klasse für alle Filtermethoden
 * @author M.K.
 *
 */
public class ImageFilters {
	/**
	 * Wendet den Black and White Filter auf das Bufferedimage an
	 * 
	 * @param img
	 * @return
	 */
	public static BufferedImage bwFilter(BufferedImage img) {
		for (int x = 0; x < img.getWidth(); x++) {
			for (int y = 0; y < img.getHeight(); y++) {
				int pixelValue = img.getRGB(x, y);
				if (pixelValue > -8388607) {
					img.setRGB(x, y, -1);
				} else {
					img.setRGB(x, y, -16777215);
				}
			}
		}
		return img;
	}

	/**
	 * Wendet den Blur Filter an
	 * 
	 * @param img
	 * @return
	 */
	public static BufferedImage blurFilter(BufferedImage img) {
		float[] values = { 1 / 9f, 1 / 9f, 1 / 9f, 1 / 9f, 1 / 9f, 1 / 9f,
				1 / 9f, 1 / 9f, 1 / 9f };

		Kernel kernel = new Kernel(3, 3, values);
		ConvolveOp convolutionFilter = new ConvolveOp(kernel);
		BufferedImage bimg = new BufferedImage(img.getWidth(), img.getHeight(),
				img.getType());
		convolutionFilter.filter(img, bimg);
		return bimg;
	}

	/**
	 * Wendet den Sharpen Filter an
	 * 
	 * @param img
	 * @return
	 */
	public static BufferedImage sharpenFilter(BufferedImage img) {
		float[] values = { 0, -1, 0, -1, 5, -1, 0, -1, 0 };
		BufferedImage targetImage = new BufferedImage(img.getWidth(),
				img.getHeight(), img.getType());

		Kernel kernel = new Kernel(3, 3, values);
		ConvolveOp convolutionFilter = new ConvolveOp(kernel);
		convolutionFilter.filter(img, targetImage);
		return targetImage;
	}

	/**
	 * Wendet den Edge Filter an
	 * 
	 * @param img
	 * @return
	 */
	public static BufferedImage edgeFilter(BufferedImage img) {
		float[] values = { -1, -1, -1, -1, 8, -1, -1, -1, -1 };
		BufferedImage targetImage = new BufferedImage(img.getWidth(),
				img.getHeight(), BufferedImage.TYPE_INT_RGB);

		Kernel kernel = new Kernel(3, 3, values);
		ConvolveOp convolutionFilter = new ConvolveOp(kernel);
		convolutionFilter.filter(img, targetImage);
		return targetImage;
	}

	/**
	 * Wendet den Green Filter an
	 * 
	 * @param img
	 * @return
	 */
	public static BufferedImage greenFilter(BufferedImage img) {
		BufferedImage targetImage = new BufferedImage(img.getWidth(),
				img.getHeight(), BufferedImage.TYPE_INT_RGB);

		for (int x = 0; x < img.getWidth(); x++) {
			for (int y = 0; y < img.getHeight(); y++) {
				int pixel = img.getRGB(x, y);
				int green = (pixel >> 8) & 0xff;
				int targetPixel = green << 8;
				targetImage.setRGB(x, y, targetPixel);
			}
		}
		return targetImage;
	}

	/**
	 * Wendet den Green Filter an
	 * 
	 * @param img
	 * @return
	 */
	public static BufferedImage invertFilter(BufferedImage img) {
		BufferedImage targetImage = new BufferedImage(img.getWidth(),
				img.getHeight(), BufferedImage.TYPE_INT_RGB);

		for (int x = 0; x < img.getWidth(); x++) {
			for (int y = 0; y < img.getHeight(); y++) {
				int pixel = img.getRGB(x, y);
				targetImage.setRGB(x, y, 0xFFFFFF - pixel);
			}
		}
		return targetImage;
	}

	/**
	 * 
	 * @param img
	 * @return
	 */
	public static BufferedImage contrastFilter(BufferedImage img) {
		ContrastFilter contra = new ContrastFilter();
		contra.setBrightness(1.1f);
		contra.setContrast(2);
		BufferedImage targetImage = new BufferedImage(img.getWidth(),
				img.getHeight(), img.getType());

		targetImage = contra.filter(img, targetImage);

		return targetImage;
	}

	/**
	 * 
	 * @param img
	 * @return
	 */
	public static BufferedImage distortFilter(BufferedImage img) {
		PolarFilter pF = new PolarFilter();
		pF.setType(PolarFilter.POLAR_TO_RECT);

		BufferedImage targetImage = new BufferedImage(img.getWidth(),
				img.getHeight(), img.getType());
		pF.filter(img, targetImage);

		return targetImage;
	}

	/**
	 * 
	 * @param img
	 * @return
	 */
	public static BufferedImage zoomFilter(BufferedImage img) {
		int newImageWidth = img.getWidth() * 2;
		int newImageHeight = img.getHeight() * 2;
		BufferedImage resizedImage = new BufferedImage(newImageWidth,
				newImageHeight, img.getType());
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(img, 0, 0, newImageWidth, newImageHeight, null);
		g.dispose();

		return resizedImage;
	}
}
