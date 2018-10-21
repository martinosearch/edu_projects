package statistique;

import rapportBulletin.DocFormat;
import abstractObject.AbstractControler;
import configurationEcole.ConfigEcole;

public class StaMoyTrim extends AbstractStaWritter {
	private ConfigEcole conf;

	public StaMoyTrim(String tr) {
		super(tr);
	}

	public void valider() {
		conf = new ConfigEcole(chooser.getTrimestre(), chooser.getAnnee());

		if (conf.bullConfig.getTypeStaMoyTrim() == DocFormat.STA_MIXTE) {
			model = new StaMoyTrimModelMixte();
		}

		if (conf.bullConfig.getTypeStaMoyTrim() == DocFormat.STA_MASC_FEM) {
			model = new StaMoyTrimModelMascFem();
		}

		model.addObserver(this);
		controler = new StaMoyTrimControler(model);

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
