package eleve;

import function.Constance;
import graphicsModel.MartList;
import interfacePerso.MartRangeable;
import interfacePerso.Observer;

import java.awt.Color;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import classe.Classe;
import classe.Niveau;
import abstractObject.AbstractModel;
import progress.Progress;
import progress.ProgressFrame;
import tableComponent.MartTabModel;
import tableComponent.MartTable;
import connection.DAO;
import connection.DAOFactory;

public class PromoEleveModel extends AbstractModel {
	MartTable tableRetrait;
	MartTabModel mod, mod2, mod3;
	private PromoEleve obsPrincipal;
	private int progmax;
	private Progress progress;
	private MartList<Scolarite> scolarites;
	private MartTable tableRedoublant;
	private MartTable tableNouveau;
	private MartTable tableDef;
	private ProgressFrame pFrame;

	public PromoEleveModel() {
		super();
		scolarites = new MartList<Scolarite>();
	}

	public void valider(int tpe) {
		this.type = tpe;
		clsdao = DAOFactory.getDAO(DAO.CLASSE);
		promoelvdao = DAOFactory.getDAO(DAO.PROMO_ELEVE);
		clsdao.load();

		for (Observer obs : listObserver) {
			if (obs instanceof PromoEleve) {
				obsPrincipal = (PromoEleve) obs;
			}
		}

		tableRedoublant = obsPrincipal.getSelector().getTabGauche();
		tableNouveau = obsPrincipal.getSelector().getTabDroite();
		tableDef = obsPrincipal.getSelector().getMatDef();

		int length2 = tableRedoublant.getRowCount();
		int length3 = tableNouveau.getRowCount();
		int length = tableDef.getRowCount();

		// ***************LA BARRE DE PROGRESSION********************
		pFrame = new ProgressFrame();
		progmax = (length + length2 + length3) * 2 + progress.FIN;
		progress = new Progress();
		progress.getProgress(pFrame, 0, progmax);
		progress.setColor(Color.green);
		// ********************FIN*****************************/*****

		if (type == 1) {
			// On met à jour la table promo /pour tout le monde
			// pour l'année en cours
			promoelvdao.load(new String(), classe, trimestre, annee);

			for (int j = 0; j < length2; j++) {
				String code = (String) tableRedoublant.getValueAt(j, 1);

				Scolarite temp = new Scolarite();
				temp.setMatricule(code);

				Classe cls = (Classe) clsdao.findObj(classe);
				temp.setNiveau(cls.getNiveau());

				promoelvdao.update_create(temp);

				// *****POUR LA BARRE DE PORGRESSION***********
				progress.increment();
				// *********************FIN********************
			}

			for (int j = 0; j < length3; j++) {
				String code = (String) tableNouveau.getValueAt(j, 1);

				Scolarite temp = new Scolarite();
				temp.setMatricule(code);
				Classe cls = (Classe) clsdao.findObj(classe);
				temp.setNiveau(cls.getNiveau());

				promoelvdao.update_create(temp);

				// *****POUR LA BARRE DE PORGRESSION***********
				progress.increment();
				// *********************FIN********************
			}

			for (int j = 0; j < length; j++) {
				String code = (String) tableDef.getValueAt(j, 0);

				Scolarite temp = new Scolarite();
				temp.setMatricule(code);

				temp.setNiveau("");// on ne met rien pour le niveau

				promoelvdao.update_create(temp);

				// *****POUR LA BARRE DE PORGRESSION***********
				progress.increment();
				// *********************FIN********************
			}

			// pour l'année dernière
			// on les maintient le même niveau
			promoelvdao.load(new String(), classe, trimestre,
					Constance.getAnneePrec(annee));

			for (int j = 0; j < length2; j++) {
				String code = (String) tableRedoublant.getValueAt(j, 1);

				Scolarite temp = new Scolarite();
				temp.setMatricule(code);
				Classe cls = (Classe) clsdao.findObj(classe);
				temp.setNiveau(cls.getNiveau());

				promoelvdao.update_create(temp);

				// *****POUR LA BARRE DE PORGRESSION***********
				progress.increment();
				// *********************FIN********************
			}

			// on choisi le niveau inférieur pour les nouveau
			for (int j = 0; j < length3; j++) {
				String code = (String) tableNouveau.getValueAt(j, 1);

				Scolarite temp = new Scolarite();
				temp.setMatricule(code);
				Classe cls = (Classe) clsdao.findObj(classe);
				temp.setNiveau(new Niveau(cls.getNiveau()).getNiveauInf());// le
																			// changement
																			// est
																			// ici

				promoelvdao.update_create(temp);

				// *****POUR LA BARRE DE PORGRESSION***********
				progress.increment();
				// *********************FIN********************
			}

			for (int j = 0; j < length; j++) {
				String code = (String) tableDef.getValueAt(j, 0);

				Scolarite temp = new Scolarite();
				temp.setMatricule(code);
				temp.setNiveau("");

				promoelvdao.update_create(temp);

				// *****POUR LA BARRE DE PORGRESSION***********
				progress.increment();
				// *********************FIN********************
			}
		}

		// *****POUR LA BARRE DE PORGRESSION***********
		progress.increment();
		// *********************FIN********************
		pFrame.close();
		// on met a jour la vue
		notifyObserver();
	}

	// gestion de la promotion
	public void addValue(Scolarite sco) {
		Scolarite scolarite = sco;
		boolean exist = false;

		Scolarite oldSco = null;
		try {
			oldSco = scolarites.find(scolarite.getId());
		} catch (Exception e) {

		}

		if (oldSco != null) {
			oldSco.addNiveau(scolarite.getNiveau());

			String nivAct = scolarite.getNiveauActuel();

			try {
				if (!(nivAct.equals("default"))) {
					oldSco.setNiveauActuel(scolarite.getNiveauActuel());
				}
			} catch (Exception e) {
			}
		}

		else {
			scolarites.add(sco);
		}

	}

	public MartList<Scolarite> getListeScolarites() {
		return scolarites;
	}

	@Override
	public void executeTab(int type, int mode) {
		// TODO Auto-generated method stub

	}

	@Override
	public void supprimer(int mode) {
		// TODO Auto-generated method stub

	}

	@Override
	public MartList<MartRangeable> getRessources() {
		// TODO Auto-generated method stub
		return null;
	}

}
