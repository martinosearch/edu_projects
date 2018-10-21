package statistique;

import configurationEcole.ConfigEcole;
import rapportBulletin.DocFormat;

public class StaNoteCompo extends AbstractStaWritter {
	private ConfigEcole conf;

	public StaNoteCompo(String tr) {
		super(tr);
	}

	public void valider() {
		conf = new ConfigEcole(chooser.getTrimestre(), chooser.getAnnee());

		if (conf.bullConfig.getTypeStaNoteCompo() == DocFormat.STA_MIXTE) {
			model = new StaNoteCompoModelMixte();
		}

		if (conf.bullConfig.getTypeStaNoteCompo() == DocFormat.STA_MASC_FEM) {
			model = new StaNoteCompoModelMascFem();
		}

		model.addObserver(this);
		controler = new StaNoteCompoControler(model);

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
