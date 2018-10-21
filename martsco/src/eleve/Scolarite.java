package eleve;

import abstractObject.AbstractPojo;
import function.Constance;
import graphicsModel.MartList;
import interfacePerso.MartRangeable;

public class Scolarite extends AbstractPojo {
	private String matricule = "Default";
	private MartList<NiveauSco> Niveaux = new MartList<NiveauSco>();
	String niveau;
	private String niveauActuel = "default";

	public Scolarite(String matricule, String niveau) {
		this.matricule = matricule;
		this.niveau = niveau;
		this.addNiveau(niveau);
	}

	public Scolarite() {

	}

	public String getNiveau() {
		return this.niveau;
	}

	public String getMatricule() {
		return this.matricule;
	}

	public synchronized void addNiveau(String niv2) {
		niveau = niv2;
		if (niveau != null) {
			boolean exist = false;

			if (Niveaux.size() != 0) {
				for (NiveauSco niv : Niveaux) {
					if (niv.getIntitule().equals(niveau)) {
						niv.incrementNiveau();
						exist = true;
					}
				}
			}

			if (exist == false) {
				NiveauSco niv = new NiveauSco(niveau);
				niv.incrementNiveau();
				Niveaux.add(niv);
			}
		}
	}

	public Statut getStatut(String niveau) {
		Statut value = new Statut();
		try {
			value = (Niveaux.find(niveau)).createStatut();
		} catch (Exception e) {

		}
		return value;
	}

	public class NiveauSco implements MartRangeable {
		private String intitule = "";
		private int grade = -1;

		public NiveauSco(String intitule) {
			this.intitule = intitule;
		}

		public Statut createStatut() {
			return new Statut(grade);
		}

		public String getIntitule() {
			return intitule;
		}

		public void incrementNiveau() {
			this.grade++;
		}

		@Override
		public String getId() {
			return intitule;
		}

		@Override
		public void setId(String id) {
			intitule = id;
		}

		@Override
		public void setIntitule(String inti) {
			// TODO Auto-generated method stub

		}

		@Override
		public int getRang() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public void setRang(int rg) {
			// TODO Auto-generated method stub

		}

		@Override
		public double getValue() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public void setValue(double val) {
			// TODO Auto-generated method stub

		}
	}

	public class Statut {
		int value = -1;

		public Statut(int value) {
			this.value = value;
		}

		public Statut() {
			// TODO Auto-generated constructor stub
		}

		public String toString() {
			String str;

			if (value == -1)
				str = "";
			else if (value == 0)
				str = "N";
			else
				str = "R";
			return str;
		}

		public String toStringComplete() {
			String str;
			if (value == -1)
				str = "";
			else if (value == 0)
				str = "Nouveau";
			else
				str = "Redoublant";
			return str;
		}

		public String toStringCompleteF() {
			String str;
			if (value == -1)
				str = "";
			else if (value == 0)
				str = "Nouvelle";
			else
				str = "Redoublante";
			return str;
		}

		public int intValue() {
			return value;
		}

		public int getGrade() {
			return value;
		}
	}

	@Override
	public String getId() {
		return matricule;
	}

	@Override
	public void setId(String id) {
		matricule = id;
	}

	@Override
	public String getIntitule() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setIntitule(String inti) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getRang() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setRang(int rg) {
		// TODO Auto-generated method stub

	}

	@Override
	public double getValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setValue(double val) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getPrimaryKey() {
		return matricule;
	}

	@Override
	public String getCor() {
		return Constance.getCor(matricule);
	}

	public void setMatricule(String code) {
		matricule = code;
	}

	public void setNiveau(String niveau2) {
		niveau = niveau2;
	}

	public void setNiveauActuel(String niv) {
		niveauActuel = niv;
	}

	public String getNiveauActuel() {
		return niveauActuel;
	}

	@Override
	public String getInfo(int i) {
		// TODO Auto-generated method stub
		return null;
	}

}
