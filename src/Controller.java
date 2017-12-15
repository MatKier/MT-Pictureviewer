import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Controller Klasse
 * 
 * @author M.K.
 *
 */
public class Controller implements ActionListener {

	private View view;
	private Model model;

	public Controller() {
		model = new Model();
		view = new View(this);

		model.addObserver(view);
		view.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if (cmd.equals(StringConstants.LOAD_FILE)) {
			File pictureFile = view.showFilechooser();
			if (pictureFile != null) {
				model.setPicture(view.scalePicture(pictureFile),
						pictureFile.getName());
			}
		} else if (cmd.equals(StringConstants.EXIT)) {
			System.exit(0);
		} else if (cmd.equals(StringConstants.BLUR)) {
			model.blurFilter();
		} else if (cmd.equals(StringConstants.DISTORT)) {
			model.distortFilter();
		} else if (cmd.equals(StringConstants.GREEN)) {
			model.greenFilter();
		} else if (cmd.equals(StringConstants.SHARPEN)) {
			model.sharpenFilter();
		} else if (cmd.equals(StringConstants.EDGE)) {
			model.edgeFilter();
		} else if (cmd.equals(StringConstants.CONTRAST)) {
			model.contrastFilter();
		} else if (cmd.equals(StringConstants.INVERT)) {
			model.invertFilter();
		} else if (cmd.equals(StringConstants.BW)) {
			model.bwFilter();
		} else if (cmd.equals(StringConstants.ZOOM)) {
			model.zoomFilter();
		} else if (cmd.equals(StringConstants.ABOUT_MED_TECH)) {
			view.showAboutDialog();
		} else if (cmd.equals(StringConstants.ALL_EFFECTS)) {
			model.allFilter();
		} else if (cmd.equals(StringConstants.RANDOM)) {
			model.rndFilter();
		}
	}
}
