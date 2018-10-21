package statistique;

import configurationEcole.ConfigEcole;
import rapportBulletin.DocFormat;

public class StaMoyAn extends AbstractStaWritter {
	private ConfigEcole conf;

	public StaMoyAn(String tr) {
		super(tr);
	}

	public void valider() {
		conf = new ConfigEcole(chooser.getTrimestre(), chooser.getAnnee());

		if (conf.bullConfig.getTypeStaMoyAn() == DocFormat.STA_MIXTE) {
			model = new StaMoyAnModelMixte();
		}

		if (conf.bullConfig.getTypeStaMoyAn() == DocFormat.STA_MASC_FEM) {
			model = new StaMoyAnModelMascFem();
		}

		model.addObserver(this);
		controler = new StaMoyAnControler(model);

		controler.setAnnee(chooser.getAnnee());
		controler.setTrimestre(chooser.getTrimestre());

		model.setAnnee(chooser.getAnnee());
		model.setTrimestre(chooser.getTrimestre());
		model.setTableChoix(chooser.getTableOfChoise());

		controler.valider();
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

}
