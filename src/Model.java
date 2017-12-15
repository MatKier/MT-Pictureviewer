import java.util.Observable;
import java.util.Random;

/**
 * Model Klasse
 * 
 * @author M.K.
 *
 */
public class Model extends Observable {
	// Filter Booleans
	private Boolean blur;
	private Boolean distort;
	private Boolean green;
	private Boolean sharpen;
	private Boolean edge;
	private Boolean contrast;
	private Boolean invert;
	private Boolean bw;
	private Boolean zoom;

	// Bild Daten
	private String pictureName;
	private ImageWithDimension img;

	// Dienen zur Speicherung der "alten" werte für die allFilter Methode
	private Boolean allChecked;

	private Boolean blurOld;
	private Boolean distortOld;
	private Boolean greenOld;
	private Boolean sharpenOld;
	private Boolean edgeOld;
	private Boolean contrastOld;
	private Boolean invertOld;
	private Boolean bwOld;
	private Boolean zoomOld;

	private int lastRandFilter;

	public Model() {
		this.blur = false;
		this.distort = false;
		this.green = false;
		this.sharpen = false;
		this.edge = false;
		this.contrast = false;
		this.invert = false;
		this.bw = false;
		this.zoom = false;

		this.pictureName = "";
		this.img = null;

		this.allChecked = false;

		this.blurOld = false;
		this.distortOld = false;
		this.greenOld = false;
		this.sharpenOld = false;
		this.edgeOld = false;
		this.contrastOld = false;
		this.invertOld = false;
		this.bwOld = false;
		this.zoomOld = false;

		this.lastRandFilter = 0;
	}

	public ImageWithDimension getImg() {
		return img;
	}

	/**
	 * Setzt alle Filter auf true, falls die Methoide das erste mal aufgerufen
	 * wurde Bei jedem zweiten mal werden die Filter auf den vorherigen Zustand
	 * zurück gesetzt.
	 * 
	 */
	public void allFilter() {
		if (!allChecked) {
			// Speichert aktuellen Zustand der Filter
			blurOld = blur;
			distortOld = distort;
			greenOld = green;
			sharpenOld = sharpen;
			edgeOld = edge;
			contrastOld = contrast;
			invertOld = invert;
			bwOld = bw;
			zoomOld = zoom;
			allChecked = true;
			setAllFIlters(true);
		} else {
			allChecked = false;
			// Setzt Filter auf die gespeicherten Werte
			blur = blurOld;
			distort = distortOld;
			green = greenOld;
			sharpen = sharpenOld;
			edge = edgeOld;
			contrast = contrastOld;
			invert = invertOld;
			bw = bwOld;
			zoom = zoomOld;
		}

		setChanged();
		notifyObservers();
	}

	/**
	 * Setzt alle Filter auf den übergebenen Boolean
	 * 
	 * @param setValue
	 */
	private void setAllFIlters(Boolean setValue) {
		blur = setValue;
		distort = setValue;
		green = setValue;
		sharpen = setValue;
		edge = setValue;
		contrast = setValue;
		invert = setValue;
		bw = setValue;
		zoom = setValue;
	}

	/**
	 * Setzt einen zufälligen Filter auf true, alle anderen auf false
	 */
	public void rndFilter() {
		setAllFIlters(false);
		Random rand = new Random();
		int randFilter = lastRandFilter;
		while (randFilter == lastRandFilter) {
			randFilter = rand.nextInt(8);
		}
		lastRandFilter = randFilter;
		switch (randFilter) {
		case 0:
			blur = true;
			break;
		case 1:
			distort = true;
			break;
		case 2:
			sharpen = true;
			break;
		case 3:
			contrast = true;
			break;
		case 4:
			invert = true;
			break;
		case 5:
			bw = true;
			break;
		case 6:
			green = true;
			break;
		case 7:
			edge = true;
			break;
		default:
			break;
		}

		setChanged();
		notifyObservers();
	}

	/**
	 * Setzte das Bild, dessen Maße und Namen im Controller und benachrichtigt
	 * die View
	 * 
	 * @param image
	 * @param pictureName
	 */
	public void setPicture(ImageWithDimension image, String pictureName) {
		this.img = image;

		this.pictureName = pictureName;

		setChanged();
		notifyObservers();
	}

	public void blurFilter() {
		blur = !blur;

		setChanged();
		notifyObservers();
	}

	public void distortFilter() {
		distort = !distort;

		setChanged();
		notifyObservers();
	}

	public void greenFilter() {
		green = !green;

		setChanged();
		notifyObservers();
	}

	public void sharpenFilter() {
		sharpen = !sharpen;

		setChanged();
		notifyObservers();
	}

	public void edgeFilter() {
		edge = !edge;

		setChanged();
		notifyObservers();
	}

	public void contrastFilter() {
		contrast = !contrast;

		setChanged();
		notifyObservers();
	}

	public void invertFilter() {
		invert = !invert;

		setChanged();
		notifyObservers();
	}

	public void bwFilter() {
		bw = !bw;

		setChanged();
		notifyObservers();
	}

	public void zoomFilter() {
		zoom = !zoom;

		setChanged();
		notifyObservers();
	}

	public Boolean getBlur() {
		return blur;
	}

	public Boolean getDistort() {
		return distort;
	}

	public Boolean getGreen() {
		return green;
	}

	public Boolean getSharpen() {
		return sharpen;
	}

	public Boolean getEdge() {
		return edge;
	}

	public Boolean getContrast() {
		return contrast;
	}

	public Boolean getInvert() {
		return invert;
	}

	public Boolean getBw() {
		return bw;
	}

	public Boolean getZoom() {
		return zoom;
	}

	public String getPictureName() {
		return pictureName;
	}
}
