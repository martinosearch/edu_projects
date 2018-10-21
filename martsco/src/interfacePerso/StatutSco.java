package interfacePerso;

import eleve.Scolarite;
import eleve.Scolarite.Statut;

public interface StatutSco {
	public void setClasse(String cls);

	public String getClasse();

	public Statut getStatut();

	void setSco(Scolarite scolarite);
}
