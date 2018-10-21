package note;

import eleve.EleveClasse;
import function.MartComputer;
import graphicsModel.FrameIcon;
import graphicsModel.MartCheckBox;
import graphicsModel.MartFrame;
import graphicsModel.MartLabel;
import graphicsModel.MartList;
import graphicsModel.MyFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import matiere.MatiereProg;

import org.joda.time.DateTime;

import progress.Avancer;
import progress.Progress;
import progress.ProgressFrame;
import tableComponent.MartFixedTable;
import tableComponent.MartTabModel;
import annee.Annee;
import classe.Classe;
import componentFactory.MartButton;
import connection.DAO;
import connection.DAOFactory;

public class SerieNoteFrame extends MartFrame {
	private static SerieNoteFrame instance;
	private JPanel panControl;
	private JPanel panListe;
	private JComboBox cbAnnee;
	private JComboBox cbClasse;
	private JComboBox cbMatiere;
	private JComboBox cbTrimestre;
	private Dimension dimCb = new Dimension(200, 25);
	private DAO andao, matpdao, clsdao, elvclsdao, snotedao;
	private MartList<Annee> annees;
	private MartList<MatiereProg> matieres;
	private MartList<Classe> classes;

	private MartList<EleveClasse> eleves;
	private MartButton bAjouter = new MartButton().getAjouter();
	private MartButton bSupprimer = new MartButton().getSupprimer();
	private MartButton bSave = new MartButton().getSauvegarder();
	private MartButton bFusion = new MartButton().getFusion();
	private MartButton bExport = new MartButton().getExport();
	private MartFixedTable table;
	private int numSerie;
	private MartList<SerieNote> series;
	private boolean adding = false;
	private int numCol = 0;
	private MartTabModel model;
	private double bareme = 0;
	private MartLabel lbInfoSelected, lbInfoAll;
	private boolean addEnable = true;
	private MartButton bFinalize;
	private Progress progress;
	private ProgressFrame pFrame;

	public SerieNoteFrame() {
		setSize(MEDIUM_PLUS_FRAME);
		setLocation(INTERNAL_FRAME_LOCATION);

		setToolBar();
		setIcone(new FrameIcon().getNotes("Série de Notes"));

		setToolBarVertical();

		initComponent();

		getContentPane().add(container, BorderLayout.CENTER);

		setVisible(true);
	}

	private void initComponent() {
		barreOutilsV.add(bAjouter);
		barreOutilsV.add(bSave);
		barreOutilsV.add(bSupprimer);
		barreOutilsV.add(bFusion);
		barreOutilsV.add(bExport);
		addListeners();

		container = new JPanel();
		container.setLayout(new BorderLayout());

		panControl = new JPanel();
		panControl.setLayout(new FlowLayout());
		panControl.setPreferredSize(new Dimension(dimCb.width,
				MEDIUM_FRAME.height));

		panListe = new JPanel();
		panListe.setLayout(new BorderLayout());
		panListe.setBackground(Color.YELLOW);

		load();
		setControls();
		setListe();

		container.add(panControl, BorderLayout.WEST);
		container.add(panListe, BorderLayout.CENTER);
	}

	private void setControls() {
		andao.load();
		clsdao.load();

		annees = andao.getListObt();
		classes = clsdao.getListObt();

		// combo des années
		String[] tabAnnee = new String[annees.size()];
		int i = 0;
		for (Annee an : annees) {
			tabAnnee[i] = an.getIntitule();
			i++;
		}
		cbAnnee = new JComboBox(tabAnnee);

		// combo des classes
		String[] tabClasse = new String[classes.size()];
		i = 0;
		for (Classe cl : classes) {
			tabClasse[i] = cl.getIntitule();
			i++;
		}
		cbClasse = new JComboBox(tabClasse);

		cbTrimestre = new JComboBox();
		// initialisation de cbTrimestre
		cbTrimestre.addItem(1);
		cbTrimestre.addItem(2);
		cbTrimestre.addItem(3);

		// combo de matieres
		matpdao.load(null, getClasse(), getTrimestre(), getAnnee());
		matieres = matpdao.getListObt();

		cbMatiere = new JComboBox();
		for (MatiereProg mat : matieres) {
			cbMatiere.addItem(mat.getIntitule());
		}

		panControl.add(cbAnnee);
		panControl.add(cbTrimestre);
		panControl.add(cbClasse);
		panControl.add(cbMatiere);

		JPanel panInfo = new JPanel();
		panInfo.setLayout(new FlowLayout());
		panInfo.setPreferredSize(new Dimension(dimCb.width, 260));
		lbInfoAll = new MartLabel();
		panInfo.add(lbInfoAll);

		JPanel panInfo2 = new JPanel();
		panInfo2.setLayout(new FlowLayout());
		lbInfoSelected = new MartLabel();
		panInfo2.add(lbInfoSelected);

		JPanel panButtonControl = new JPanel();
		panButtonControl.setLayout(new FlowLayout());
		bFinalize = new MartButton();
		bFinalize
				.setText("<html><div align='center'>Finaliser le  traitement</div>"
						+ "<div align='center'> des notes</div></html>");
		bFinalize.addActionListener(this);
		panButtonControl.add(bFinalize);

		panControl.add(panInfo);
		panControl.add(panInfo2);
		panControl.add(panButtonControl);

		Font lbFont = new Font("arial", Font.BOLD, 16);
		lbInfoAll.setFont(lbFont);
		lbInfoSelected.setFont(lbFont);
		lbInfoSelected.setForeground(Color.RED);

		cbAnnee.setPreferredSize(dimCb);
		cbClasse.setPreferredSize(dimCb);
		cbMatiere.setPreferredSize(dimCb);
		cbTrimestre.setPreferredSize(dimCb);

		// ajout d'écouteur
		cbAnnee.addActionListener(this);
		cbClasse.addActionListener(this);
		cbTrimestre.addActionListener(this);
		cbMatiere.addActionListener(this);
	}

	private String getAnnee() {
		return (String) cbAnnee.getSelectedItem();
	}

	private int getTrimestre() {
		return (int) cbTrimestre.getSelectedItem();
	}

	private String getClasse() {
		return (String) cbClasse.getSelectedItem();
	}

	private String getMatiere() {
		return (String) cbMatiere.getSelectedItem();
	}

	public static void main(String[] args) {
		new SerieNoteFrame().setVisible(true);
	}

	@Override
	public Avancer getAvancer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Component source = (Component) e.getSource();

		if (source == cbAnnee || source == cbTrimestre || source == cbClasse
				|| source == cbMatiere) {
			setListe();
		}

		if (source == bAjouter) {
			if (addEnable) {
				addEnable = false;
				String val = JOptionPane
						.showInputDialog("Sur combien est notée la série?");
				try {
					bareme = Double.parseDouble(val);
					adding = true;
					setListe();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(null,
						"Veuillez sauvegarder la série en cours avant tout!");
			}
		}

		if (source == bSave) {
			// ajout possible maintenant
			addEnable = true;

			// progression de la sauvegarde
			pFrame = new ProgressFrame();
			progress = new Progress();

			progress.getLoading(pFrame,
					"Traitement en cours, veuillez patienter");

			new Thread(new Runnable() {
				public void run() {
					int colonne = table.getColumnCount();
					int k = 2;
					while (k < colonne) {
						SerieNote serie = null;
						// En cas d'existence ancienne
						if (k - 2 < series.size()) {
							SerieNote old = series.get(k - 2);
							serie = new SerieNote(old.getPrimaryKey());
							serie.setDate(old.getDate());
							serie.setBareme(old.getBareme());
						} else {
							numSerie++;
							serie = new SerieNote(getClasse() + getMatiere()
									+ ";" + numSerie);
							serie.setBareme(bareme);
							serie.setDate(new DateTime());
						}

						serie.setTrimestre(getTrimestre());
						serie.setMatiere(getMatiere());
						serie.setClasse(getClasse());

						int i = 0;
						for (EleveClasse eleve : eleves) {
							System.out.println(table.getValueAt(i, k)
									+ "==================>" + k);

							String not = "ABS";
							try {
								not = (String) table.getValueAt(i, k);
							} catch (Exception e1) {
								e1.printStackTrace();
							}

							// System.out.println("--------------------->>" +
							// not);
							Note note = new Note();
							note.setCodeInfo(eleve.getCodeInfo());
							note.setNote1str(not);

							serie.add(note);

							try {
								Thread.sleep(10);
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

							i++;
						}

						snotedao.update_create(serie);
						k++;
					}

					pFrame.close();
					setListe();
				}
			}).start();
		}

		if (source == bSupprimer) {
			int col = model.getSelectedColumn();
			int rep = JOptionPane.showConfirmDialog(null,
					"Voulez- vous vraiment supprimer la Série: " + (col - 1)
							+ " ?", "CONFIRMATION", JOptionPane.YES_NO_OPTION);
			if (rep == JOptionPane.YES_OPTION) {
				SerieNote supSerie = series.get(col - 2);
				snotedao.deleteObj(supSerie);

				// mise à jour
				setListe();
			}
		}

		if (source == bExport) {
			ExportPan pan = new ExportPan();
		}

		if (source == bFusion) {
			FusionPan pan = new FusionPan();
		}

		if (source == bFinalize) {
			int rep = JOptionPane
					.showConfirmDialog(
							null,
							"<html><div>Voulez- vous vraiment excuter cette action?</div>"
									+ "<u>NB:</u> Si vous le faites, toutes les séries de notes \n"
									+ " qui ne sont pas sur (20) seront coefficiées pour l'atteindre.",
							"CONFIRMATION", JOptionPane.YES_NO_OPTION);

			if (rep == JOptionPane.YES_OPTION) {
				for (SerieNote serie : series) {
					if (serie.getBareme() != 20) {
						double coef = 20 / serie.getBareme();

						MartList<Note> liste = serie.getListe();

						for (Note note : liste) {
							if (note.getNote1() != 0.001) {
								note.setNote1str(String.valueOf(note.getNote1()
										* coef));
							}
						}

						serie.setBareme(serie.getBareme() * coef);
						serie.setListe(liste);

						snotedao.update_create(serie);
					}
				}

				setListe();
			}
		}
	}

	private void setListe() {
		pFrame = new ProgressFrame();
		progress = new Progress();
		progress.getLoading(pFrame, "Traitement en cours, veuillez patienter");

		new Thread(new Runnable() {
			public void run() {
				// System.out.println("--------------------->> appelle");
				// recupération de la liste des élèves
				elvclsdao.load(getMatiere(), getClasse(), getTrimestre(),
						getAnnee());
				eleves = elvclsdao.getListObt();

				// recupération de la liste des notes
				snotedao.load(getMatiere(), getClasse(), getTrimestre(),
						getAnnee());
				series = snotedao.getListObt();

				// gestion de l'identification
				numSerie = 0;
				String info = "<html>";
				int count = 0;

				for (SerieNote serie : series) {
					if (serie.getNumber() > numSerie) {
						numSerie = serie.getNumber();
					}

					// affichage des barème
					count++;
					info += "<div> Série " + count + ": notée sur "
							+ serie.getBareme() + "</div>";
				}

				info += "</html>";
				lbInfoAll.setText(info);

				String[] title = null;
				Object[][] data = null;

				// cas d'ajout
				if (adding == true) {
					title = new String[2 + series.size() + 1];
					data = new Object[eleves.size()][2 + series.size() + 1];
					// System.out.println("Series: " + series.size());
				} else {
					title = new String[2 + series.size()];
					data = new Object[eleves.size()][2 + series.size()];
					// System.out.println("Series: " + series.size());
				}

				numCol = title.length;
				title[0] = "N°";
				title[1] = "Nom et Prénoms";

				int i = 2;
				if (adding == true) {
					for (int k = 0; k < numCol - 2; k++) {
						title[i + k] = "Série " + (i + k - 1);
					}
				} else {
					for (int k = 0; k < numCol - 2; k++) {
						title[i + k] = "Série " + (i + k - 1);
					}
				}

				// System.out.println("===================>>Peaceful" +
				// series.size());

				int j = 0;
				for (EleveClasse elv : eleves) {
					data[j][0] = j + 1;
					data[j][1] = elv.decrisToi();
					j++;
				}

				// parcourir les series s'il en existe
				if (series.size() > 0) {
					// progression
					int max = series.size() * eleves.size();
					progress.getLoading(pFrame,
							"Veuillez patienter pendant le chargement");
					progress.getProgress(pFrame, 0, max);

					for (int k = 0; k < series.size(); k++) {
						SerieNote serieTemp = series.get(k);
						MartList<Note> listTemp = serieTemp.getListe();

						int a = 0;
						Object not = null;
						Note note = new Note();
						for (EleveClasse elv : eleves) {
							not = new String();

							try {
								note = listTemp.find(elv.getCodeInfo());
								if (!note.getNote1str().equals("")) {
									not = note.getNote1str();
								}
							} catch (Exception e) {
								// e.printStackTrace();

							}

							System.out.println(elv.getCodeInfo()
									+ "===================>>Peaceful" + not);

							data[a][2 + k] = not;

							/*
							 * try { Thread.sleep(100); } catch
							 * (InterruptedException e) { // TODO Auto-generated
							 * catch block e.printStackTrace(); }
							 */

							// pour la progression
							progress.increment();
							a++;
						}
					}

				}

				// on ferme la progression
				try {
					pFrame.close();
				} catch (Exception e) {
					e.printStackTrace();
				}

				int b = 0;
				if (adding == true) {
					for (EleveClasse elv : eleves) {
						data[b][numCol - 1] = "";
						b++;
					}
				}

				model = new MartTabModel(data, title, 2);

				for (int m = 2; m < title.length; m++) {
					model.setColEditable(m);
				}

				table = new MartFixedTable(model);
				table.addMouseListener(SerieNoteFrame.this);
				table.addFocusListener(SerieNoteFrame.this);

				table.setColumnSize(0, 2);
				table.setColumnSize(1, 10);
				table.setRowHeight(1);

				panListe.removeAll();
				JLabel lb = new JLabel("Série de notes");
				panListe.add(lb, BorderLayout.NORTH);
				panListe.add(table, BorderLayout.CENTER);
				panListe.revalidate();
				panListe.repaint();

				// réinitialisation du mode ajout
				adding = false;
			}
		}).start();
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		int col = model.getSelectedColumn();

		SerieNote serie = series.get(col - 2);
		lbInfoSelected.setText("La selection est sur: " + serie.getBareme());
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void load() {
		andao = DAOFactory.getDAO(DAO.ANNEE);
		matpdao = DAOFactory.getDAO(DAO.MATIERE_PROG);
		clsdao = DAOFactory.getDAO(DAO.CLASSE);
		elvclsdao = DAOFactory.getDAO(DAO.ELEVE_CLASSE);
		snotedao = DAOFactory.getDAO(DAO.SERIE_NOTE);
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub

	}

	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void focusLost(FocusEvent e) {
		System.out.println("Tu peux wep..............");
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	// classe interne affichant les choix d'exportation
	class ExportPan extends MyFrame {
		private Dimension dimComp = new Dimension(450, 20);
		private Dimension dimPan = new Dimension(450, 50);
		private Dimension dimPan2 = new Dimension(450, 30);
		private Dimension dimPan3 = new Dimension(450, 120);
		private Dimension dimCb = new Dimension(150, 20);

		private MartCheckBox ckMeilleure1;
		private MartCheckBox ckMeilleure2;
		private MartCheckBox ckMoyenne;
		private MartCheckBox ckAutomatic;
		private MartCheckBox ckManuel;
		private JComboBox cbInterro1;
		private JComboBox cbInterro2;
		private JLabel lbInterro1;
		private JLabel lbInterro2;
		private MartButton bValider = new MartButton().getValider();
		private MartButton bAnnuler = new MartButton().getAnnuler();
		private DAO notedao = DAOFactory.getDAO(DAO.NOTE);

		public ExportPan() {
			setSize(SMALL_FRAME);
			setLocation(MEDIUM_FRAME_CHOOSER_LOCATION);

			ckMeilleure1 = new MartCheckBox("La meilleure note");
			ckMeilleure2 = new MartCheckBox("Deux (2) meilleures note");
			ckMoyenne = new MartCheckBox("La moyenne de toutes les notes");
			ckAutomatic = new MartCheckBox("Selection automatique");
			ckManuel = new MartCheckBox("Selection manuelle");
			ckManuel.setSelected(true);

			ButtonGroup bg = new ButtonGroup();
			bg.add(ckManuel);
			bg.add(ckAutomatic);

			ckAutomatic.setPreferredSize(dimComp);
			ckManuel.setPreferredSize(dimComp);
			ckMeilleure1.setPreferredSize(dimComp);
			ckMeilleure2.setPreferredSize(dimComp);
			ckMoyenne.setPreferredSize(dimComp);

			ckMeilleure1.setEnabled(false);
			ckMeilleure2.setEnabled(false);
			ckMoyenne.setEnabled(false);

			ckMeilleure1.addActionListener(this);
			ckMeilleure2.addActionListener(this);
			ckMoyenne.addActionListener(this);
			ckAutomatic.addActionListener(this);
			ckManuel.addActionListener(this);
			bValider.addActionListener(this);
			bAnnuler.addActionListener(this);

			// combobox
			cbInterro1 = new JComboBox();
			cbInterro2 = new JComboBox();
			lbInterro1 = new JLabel("Interro 1");
			lbInterro2 = new JLabel("Interro 2");

			cbInterro1.setPreferredSize(dimCb);
			cbInterro2.setPreferredSize(dimCb);

			// ajout des item au combo de selection de serie
			cbInterro1.addItem("");
			cbInterro2.addItem("");

			int i = 0;
			for (SerieNote serie : series) {
				i++;
				cbInterro1.addItem("série " + (i));
				cbInterro2.addItem("série " + (i));
			}

			JPanel pan = new JPanel();
			JPanel pan1 = new JPanel();
			JPanel pan2 = new JPanel();
			JPanel pan3 = new JPanel();
			JPanel pan4 = new JPanel();
			JPanel pan5 = new JPanel();

			pan.setLayout(new FlowLayout());
			pan1.setLayout(new FlowLayout());
			pan2.setLayout(new FlowLayout());
			pan3.setLayout(new FlowLayout());
			pan4.setLayout(new FlowLayout());
			pan4.setLayout(new FlowLayout());

			pan1.setPreferredSize(dimPan3);
			pan2.setPreferredSize(dimPan2);
			pan4.setPreferredSize(dimPan2);
			pan3.setPreferredSize(dimPan3);

			pan1.add(lbInterro1);
			pan1.add(cbInterro1);
			pan1.add(lbInterro2);
			pan1.add(cbInterro2);

			pan2.add(ckAutomatic);
			pan3.add(ckMeilleure1);
			pan3.add(ckMeilleure2);
			pan3.add(ckMoyenne);

			pan4.add(ckManuel);

			pan5.add(bValider);
			pan5.add(bAnnuler);
			pan.add(pan4);
			pan.add(pan1);
			pan.add(pan2);
			pan.add(pan3);
			pan.add(pan5);

			getContentPane().add(pan, BorderLayout.CENTER);

			setVisible(true);
		}

		public void actionPerformed(ActionEvent e) {
			Component source = (Component) e.getSource();
			// System.out.println("J'écoute");
			if (source == ckAutomatic) {
				ckMeilleure1.setEnabled(true);
				ckMeilleure2.setEnabled(true);
				ckMoyenne.setEnabled(true);

				lbInterro1.setEnabled(false);
				cbInterro1.setEnabled(false);
				lbInterro2.setEnabled(false);
				cbInterro2.setEnabled(false);
			}
			if (source == ckManuel) {
				lbInterro1.setEnabled(true);
				cbInterro1.setEnabled(true);
				lbInterro2.setEnabled(true);
				cbInterro2.setEnabled(true);

				ckMeilleure1.setSelected(false);
				ckMeilleure2.setSelected(false);
				ckMoyenne.setSelected(false);

				ckMeilleure1.setEnabled(false);
				ckMeilleure2.setEnabled(false);
				ckMoyenne.setEnabled(false);
			}

			if (source == bValider) {
				notedao.load(getMatiere(), getClasse(), getTrimestre(),
						getAnnee());
				MartList<Note> listeNoteOriginale = notedao.getListObt();

				if (ckManuel.isSelected()) {
					SerieNote serie1 = null;
					try {
						serie1 = series.get(cbInterro1.getSelectedIndex() - 1);
					} catch (Exception e1) {
						e1.printStackTrace();
					}

					SerieNote serie2 = null;
					try {
						serie2 = series.get(cbInterro2.getSelectedIndex() - 1);
					} catch (Exception e2) {
						e2.printStackTrace();
					}

					MartList<Note> listeNote1 = null;
					try {
						listeNote1 = serie1.getListe();
					} catch (Exception e3) {
						e3.printStackTrace();
					}

					MartList<Note> listeNote2 = null;
					try {
						listeNote2 = serie2.getListe();
					} catch (Exception e2) {
						e2.printStackTrace();
					}

					for (Note note : listeNoteOriginale) {
						if (cbInterro1.getSelectedIndex() != 0) {
							note.setNote1str(listeNote1
									.find(note.getCodeInfo()).getNote1str());
						}

						if (cbInterro2.getSelectedIndex() != 0) {
							note.setNote2str(listeNote2
									.find(note.getCodeInfo()).getNote1str());
						}

						notedao.update_create(note);
					}
				}

				if (ckAutomatic.isSelected()) {
					if (ckMeilleure1.isSelected()) {
						for (Note note : listeNoteOriginale) {
							double note1 = 0;
							for (SerieNote serie : series) {
								Note noteTemp = serie.getListe().find(
										note.getCodeInfo());
								try {
									if (noteTemp.getNote1() > note1) {
										note1 = noteTemp.getNote1();
										note.setNote1str(noteTemp.getNote1str());
									}
								} catch (Exception e1) {
									e1.printStackTrace();
								}
							}

							notedao.update_create(note);
						}
					}

					if (ckMeilleure2.isSelected()) {
						for (Note note : listeNoteOriginale) {
							double note1 = 0;
							for (SerieNote serie : series) {
								Note noteTemp = serie.getListe().find(
										note.getCodeInfo());

								if (noteTemp.getNote1() > note1) {
									note.setNote2str(note.getNote1str());
									note.setNote1str(noteTemp.getNote1str());
									note1 = noteTemp.getNote1();
								}
							}

							notedao.update_create(note);
						}
					}

					if (ckMoyenne.isSelected()) {
						for (Note note : listeNoteOriginale) {
							double note1 = 0;
							double total = 0;
							int div = 0;
							for (SerieNote serie : series) {
								Note noteTemp = serie.getListe().find(
										note.getCodeInfo());
								total = total + noteTemp.getNote1();

								if (noteTemp.getNote1() > 0) {
									div++;
								}

								int noteUnique = (int) MartComputer.round(total
										/ div, 0);
								note.setNote1str(String.valueOf(noteUnique));
							}

							notedao.update_create(note);
						}
					}
				}
			}

		}
	}

	class FusionPan extends MyFrame {
		private Dimension dimCb = new Dimension(150, 20);

		private JComboBox cbSerie1 = new JComboBox();
		private JComboBox cbSerie2 = new JComboBox();
		private JLabel lbSerie1 = new JLabel("1ère série");
		private JLabel lbSerie2 = new JLabel("2è série");

		private MartButton bValider = new MartButton().getValider();
		private MartButton bAnnuler = new MartButton().getAnnuler();
		private JPanel pan, pan1, pan2;

		public FusionPan() {
			setSize(SMALL_FRAME);
			setLocation(MEDIUM_FRAME_CHOOSER_LOCATION);

			cbSerie1.setPreferredSize(dimCb);
			cbSerie2.setPreferredSize(dimCb);

			int i = 0;
			for (SerieNote serie : series) {
				i++;
				cbSerie1.addItem("série " + (i));
				cbSerie2.addItem("série " + (i));
			}

			pan = new JPanel();
			pan.setLayout(new BorderLayout());
			pan1 = new JPanel();
			pan1.setLayout(new FlowLayout());
			pan2 = new JPanel();
			pan2.setLayout(new FlowLayout());

			pan1.add(lbSerie1);
			pan1.add(cbSerie1);
			pan1.add(lbSerie2);
			pan1.add(cbSerie2);

			bValider.addActionListener(this);
			bAnnuler.addActionListener(this);
			pan2.add(bValider);
			pan2.add(bAnnuler);

			pan.add(pan1, BorderLayout.CENTER);
			pan.add(pan2, BorderLayout.SOUTH);

			getContentPane().add(pan, BorderLayout.CENTER);
			setVisible(true);
		}

		public void actionPerformed(ActionEvent e) {
			Component source = (Component) e.getSource();
			// System.out.println("J'écoute");
			if (source == bValider) {
				SerieNote serie1 = series.get(cbSerie1.getSelectedIndex());
				SerieNote serie2 = series.get(cbSerie2.getSelectedIndex());

				MartList<Note> liste1 = serie1.getListe();
				MartList<Note> liste2 = serie2.getListe();

				SerieNote serie = new SerieNote(serie1.getId());
				serie.setBareme(serie1.getBareme() + serie2.getBareme());
				serie.setDate(serie2.getDate());
				serie.setMatiere(getMatiere());
				serie.setTrimestre(getTrimestre());
				serie.setClasse(getClasse());

				for (Note note : liste1) {
					Note note2 = liste2.find(note.getCodeInfo());
					double not = 0.001;

					if (note.getNote1() == 0.001) {
						not = note2.getNote1();
					} else if (note2.getNote1() == 0.001) {
						not = note.getNote1();
					} else {
						not = note.getNote1() + note2.getNote1();
					}

					note.setNote1str(String.valueOf(not));

					serie.add(note);
				}

				snotedao.update_create(serie);
				snotedao.deleteObj(serie2);

				setListe();
				close();
			}
		}
	}

	public static SerieNoteFrame getInstance() {
		if (instance == null) {
			instance = new SerieNoteFrame();
		}
		return instance;
	}
}
