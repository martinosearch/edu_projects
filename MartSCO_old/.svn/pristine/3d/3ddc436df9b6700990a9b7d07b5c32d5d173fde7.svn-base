package rapportCompta;

import java.math.BigDecimal;

import ecolage.EcolageComputer;
import ecolage.EleveEcolage;
import ecolage.Operation;
import eleve.Eleve;
import function.Constance;
import graphicsModel.MartList;
import interfacePerso.MartRangeable;

import org.joda.time.DateTime;

import agent.Agent;

public class ListeCompteEntreeModelGeneral extends ListeCompteEntreeModel {

	public boolean write() {

		for (EleveEcolage eleve : listeVersEcolage) {
			MartList<Operation> listeOperation = eleve.getlisteOperation();

			for (Operation op : listeOperation) {
				DateTime date = op.getDate();
				for (Agent agent : listeOperateur) {
					if (agent.getCodeInfo().equals(op.getCharge())) {
						op.setMatricule(eleve.getCodeInfo());
						agent.addOperation(op);
					}
				}
			}

			// barre de progression
			progress.increment();
		}

		for (Agent agent : listeOperateur) {
			MartList<Operation> listeOperation = agent.getListeOperation();
			computer = new EcolageComputer();
			computer.setAgent(agent);

			if (listeOperation.size() > 0) {
				htmlBody += "<div id='result'><table><tr><td colspan=2><b>"
						+ getPeriode()
						+ "</b></td></tr>"
						+ "<tr><td>Opérateur:</td><td>"
						+ agent.decrisToi()
						+ "</td></tr>"
						+ "<tr><td>Total Inscription:</td><td>"
						+ new BigDecimal(computer.getInscriptionEncaisseAgent())
						+ " " + Constance.getDeviseMonaie() + "</td></tr>"
						+ "<tr><td>Total Ecolage:</td><td>"
						+ new BigDecimal(computer.getEcolageEncaisseAgent())
						+ " " + Constance.getDeviseMonaie() + "</td></tr>"
						+ "<tr>Total Encaissé:</td><td>"
						+ new BigDecimal(computer.getTotalEncaisseAgent())
						+ " " + Constance.getDeviseMonaie()
						+ "</td></tr></table></div>";

				htmlBody += "<table width=100% class='tabSB'>"
						+ "<tr  id='result'>"
						+ "<td class='tdBC' width=5%>N° d'ordre</td>"
						+ "<td class='tdSBLC' width=15%>Code</td>"
						+ "<td class='tdSBLC' width=15%>Nom et Prénoms de l'élève</td>"
						+ "<td class='tdSBLC' width=5%>Sexe</td>"
						+ "<td class='tdSBLC' width=5%>Classe</td>"
						+ "<td class='tdSBLC' width=15%>Somme("
						+ Constance.getDeviseMonaie() + ")</td>"
						+ "<td class='tdSBLC' width=10%>Date</td>"
						+ "<td class='tdSBLC' width=25%>Justification</td>";

				htmlBody += "</tr>";

				int i = 0;
				for (Operation op : listeOperation) {
					try {
						i++;
						superEleve = (Eleve) elvdao.findObj(op.getMatricule());

						htmlBody += "<tr  id='result'>" + "<td class='tdSBT'>"
								+ i + "</td>" + "<td class='tdBInf'>"
								+ op.getNumOperation() + "</td>"
								+ "<td class='tdBInf'>"
								+ superEleve.decrisToi() + "</td>"
								+ "<td class='tdBInf'>" + superEleve.getSexe()
								+ "</td>" + "<td class='tdBInf'>"
								+ superEleve.getClasseAnnee(annee) + "</td>"
								+ "<td class='tdBInf'><b>" + op.getMontant()
								+ "</b></td>" + "<td class='tdBInf'>"
								+ formatter.print(op.getDate()) + "</td>"
								+ "<td class='tdBInf'>" + op.getJustification()
								+ "</td></tr>";

					} catch (Exception e) {
						e.printStackTrace();
					}

				}
				htmlBody += "</table><br/><div align='center'>*********************************************"
						+ "***************************************************************</div>";
				// htmlBody += bs.getTabSignature(DocFormat.DIR,
				// "tabSB");

			}
			// *****POUR LA BARRE DE PORGRESSION***********
			progress.increment();
			// *********************FIN********************
		}// fin for agent

		return true;
	}

	@Override
	public void valider(int type) {
		// TODO Auto-generated method stub

	}

	@Override
	public void supprimer(int mode) {
		// TODO Auto-generated method stub

	}

	@Override
	public void executeTab(int type, int mode) {
		// TODO Auto-generated method stub

	}

	@Override
	public MartList<MartRangeable> getRessources() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPeriode() {
		// TODO Auto-generated method stub
		return "Année: " + annee;
	}

}
