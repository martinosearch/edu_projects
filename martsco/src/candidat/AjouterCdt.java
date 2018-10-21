package candidat;

import eleve.Eleve;
import eleve.EleveClasse;
import examen.ChooserExamEts;
import function.Constance;
import graphicsModel.MartFrame;
import graphicsModel.MartList;
import interfacePerso.Observer;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import org.joda.time.DateTime;

import classe.ChooserClasse;
import progress.Avancer;
import progress.Progress;
import progress.ProgressFrame;
import rapportBulletin.BullModelEditor1;
import rapportBulletin.BullWriter;
import rapportBulletin.BullWriterControler;
import Import.ImportListeEleve;
import abstractObject.AbstractControler;
import abstractObject.AbstractModel;
import annee.ChooserDate;
import componentFactory.MartButton;
import configurationExamen.ConfigExamen;
import connection.DAO;
import connection.DAOFactory;

public class AjouterCdt extends MartFrame implements Observer {
	private static AjouterCdt instance;
	private Font police1 = new Font("Times new Romam", Font.TYPE1_FONT, 16);
	private Font police2 = new Font("courrier new", Font.BOLD, 14);
	protected Dimension dim1 = new Dimension(SMALL_FRAME.width - 40, 32);

	private JTabbedPane tabPan;
	private JPanel panInscription;

	private JLabel lbNom = new JLabel("Nom *");
	private JLabel lbPrenom = new JLabel("Prénom *");
	private JLabel lbSexe = new JLabel("Sexe *");
	private JLabel lbEts = new JLabel("Etablissement *");
	private JLabel lbDateNais = new JLabel("Date de naissance");

	private JTextField tfNom = new ThisTextField();
	private JTextField tfPrenom = new ThisTextField();

	private JComboBox cbSexe = new ThisComboBox();
	private JComboBox cbEts = new ThisComboBox();

	private ThisChooserDate tfDateNais = new ThisChooserDate();

	private MartButton bSave = new MartButton().getSauvegarder();
	private Eleve eleve;
	private DAO elvdao;
	private DAO etsdao;

	private String examen;
	private ConfigExamen conf;
	private JPanel panImport;
	private MartButton bExcel = new MartButton().getExcel();
	private MartButton bInterne = new MartButton().getDownload();
	private Progress progress;
	private ProgressFrame pFrame;
	private String etablissement;
	protected Thread tExcel, tInterne;

	public AjouterCdt() {
		setSize(SMALL_FRAME);
		setTitle("Eleve");
		setLocation(MEDIUM_FRAME_CHOOSER_LOCATION);
		setToolBarVertical();

		initComponent();

		getContentPane().add(container, BorderLayout.CENTER);
	}

	private void initComponent() {
		barreOutilsV.add(bSave);

		addListeners();

		// pour le sexe
		cbSexe.addItem("M");
		cbSexe.addItem("F");

		container = new JPanel();
		container.setLayout(new BorderLayout());
	}

	public static void main(String[] args) {
		new AjouterCdt().setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Component source = (Component) e.getSource();

		if (source == bSave) {
			doValider();
		}

		if (source == bExcel) {
			final ChooserExamEts chooser = ChooserExamEts.getInstance();

			chooser.setAction(new ActionListener() {

				public void actionPerformed(ActionEvent arg0) {
					etablissement = chooser.getEts();
					chooser.close();
					ImportListeEleve imp = new ImportListeEleve();
					final MartList<Eleve> liste = imp
							.getListeExcelExamen(etablissement);

					progress = new Progress();
					pFrame = new ProgressFrame();
					progress.getProgress(pFrame, 0, liste.size());

					tExcel = new Thread(new Runnable() {
						public void run() {
							for (Eleve eleve : liste) {
								eleve.initPrimaryKey();

								saveEleve(eleve);

								try {
									Thread.sleep(50);
								} catch (InterruptedException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}

								progress.increment();
							}
							pFrame.close();
						}
					});

					tExcel.start();
				}
			});

			chooser.setVisible(true);

		}

		if (source == bInterne) {
			final ChooserExamEts chooser = ChooserExamEts.getInstance();

			chooser.setAction(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					etablissement = chooser.getEts();
					chooser.close();

					// on demande le
					// remplissage**********************************
					final ChooserClasse monChoix = ChooserClasse.getInstance();
					monChoix.setTrimestreChoosing(false);

					// On définit l'action qui serra assignée au chooser
					monChoix.setAction(new ActionListener() {
						private DAO elvclsdao;
						private MartList<EleveClasse> liste;

						public void actionPerformed(ActionEvent arg0) {
							elvdao = DAOFactory.getDAO(DAO.ELEVE);
							elvdao.load();
							elvclsdao = DAOFactory.getDAO(DAO.ELEVE_CLASSE);
							elvclsdao.load(null, monChoix.getClasse()
									.getIntitule(), 0, monChoix.getAnnee());

							liste = elvclsdao.getListObt();

							progress = new Progress();
							pFrame = new ProgressFrame();
							progress.getProgress(pFrame, 0, liste.size());
							tInterne = new Thread(new Runnable() {

								private Eleve superEleve;

								public void run() {

									for (EleveClasse el : liste) {
										superEleve = (Eleve) elvdao.findObj(el
												.getCodeInfo());
										superEleve.setEts(etablissement);

										saveEleve(superEleve);

										progress.increment();

										try {
											Thread.sleep(10);
										} catch (InterruptedException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}
									pFrame.close();
								}
							});

							tInterne.start();

							monChoix.close();
						}
					});
					monChoix.setVisible(true);
					// ***********************************************************
				}

			});

			chooser.setVisible(true);
		}
	}

	@Override
	public void refresh() {
		elvdao = DAOFactory.getDAO(DAO.ELEVE);
		elvdao.load();

		// ajout des Item à nos combo
		conf = new ConfigExamen(examen);
		MartList<String> listEts = conf.relConfig.getEtsPerso();

		String[] tabEts = new String[listEts.size() + 1];
		tabEts[0] = "- -";

		for (int i = 0; i < listEts.size(); i++) {
			String ets = listEts.get(i);
			tabEts[i + 1] = ets;
			System.out.println("Ets=================================>>" + ets);
		}

		String[] sexe = { "", "M", "F" };

		cbSexe = new ThisComboBox(sexe);
		cbEts = new ThisComboBox(tabEts);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == 10) {
			doValider();
		}
	}

	private void doValider() {
		if (eleve == null) {
			eleve = new Eleve();
		}

		// System.out.println("Eleve charge" + eleve.getNom());
		eleve.setNom(getNom());
		eleve.setPrenom(getPrenom());
		eleve.setSexe(getSexe());
		eleve.setDateNais(getDateNais());
		eleve.setEts(getEts());
		eleve.initPrimaryKey();

		saveEleve(eleve);
	}

	public void saveEleve(Eleve eleve) {
		CdtModel model = new CdtModel();
		CdtControler controler = new CdtControler(model);

		model.addObserver(this);
		model.setData(eleve);
		model.setExamen(examen);

		controler.setData(eleve);
		controler.setExamen(examen);

		controler.valider();

		// System.out.println("===============================>> Averti");
	}

	private String getEts() {
		return (String) cbEts.getSelectedItem();
	}

	public String getNom() {
		return tfNom.getText();
	}

	public String getPrenom() {
		return tfPrenom.getText();
	}

	public String getSexe() {
		return (String) cbSexe.getSelectedItem();
	}

	public DateTime getDateNais() {
		return tfDateNais.getDate();
	}

	public void loadEleve(Eleve elv) {
		set();
		eleve = elv;
		tfNom.setText(eleve.getNom());
		tfPrenom.setText(eleve.getPrenom());
		tfDateNais.setDate(eleve.getDateNais());
		cbEts.setSelectedItem(eleve.getEts());
		cbSexe.setSelectedItem(eleve.getSexe());
	}

	// classe interne
	class ThisTextField extends JTextField {
		public ThisTextField() {
			super();
			setPreferredSize(dim1);
			setFont(police1);
		}
	}

	class ThisComboBox extends JComboBox {
		public ThisComboBox() {
			super();
			setPreferredSize(dim1);
			setFont(police1);
		}

		public ThisComboBox(Object[] obj) {
			super(obj);
			setPreferredSize(dim1);
			setFont(police1);
		}
	}

	class ThisChooserDate extends ChooserDate {
		public ThisChooserDate() {
			super();
			setPreferredSize(dim1);
			setFont(police1);
			setEmpty();
		}
	}

	public static AjouterCdt getInstance() {
		if (instance == null) {
			instance = new AjouterCdt();
		}
		return instance;
	}

	public void setExamen(String exam) {
		examen = exam;
	}

	public String getEtablissement() {
		return etablissement;
	}

	public void setEtablissement(String etablissement) {
		this.etablissement = etablissement;
	}

	public void set() {
		load();
		refresh();

		// ajout aux conteneur
		panInscription = new JPanel();
		panInscription.setLayout(new FlowLayout(FlowLayout.LEFT));
		panInscription.add(lbNom);
		panInscription.add(tfNom);
		panInscription.add(lbPrenom);
		panInscription.add(tfPrenom);
		panInscription.add(lbSexe);
		panInscription.add(cbSexe);
		panInscription.add(lbEts);
		panInscription.add(cbEts);
		panInscription.add(lbDateNais);
		panInscription.add(tfDateNais);

		bExcel.addActionListener(this);
		bInterne.addActionListener(this);

		panImport = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panImport.add(bExcel);
		panImport.add(bInterne);

		tabPan = new JTabbedPane();
		tabPan.add("Nouvelle inscription", panInscription);
		tabPan.add("Importer une liste", panImport);

		container.removeAll();
		container.add(tabPan, BorderLayout.CENTER);
		container.revalidate();
	}

	@Override
	public void update() {
		((Observer) parent).update();
		close();
	}

	public void stopProcess() {
		try {
			tExcel.stop();
		} catch (Exception e) {

		}
		try {
			tInterne.stop();
		} catch (Exception e) {

		}
	}

	@Override
	public Avancer getAvancer() {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub

	}

	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void focusLost(FocusEvent arg0) {
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
}
