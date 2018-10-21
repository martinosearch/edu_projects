package connection;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JToolBar;


public class Fenetre extends JFrame {
 
  /**
  * ToolBar pour le lancement des requ�tes
  */
  private JToolBar tool = new JToolBar();
 
  /**
  * Le bouton
  */
  private JButton load = new JButton("load");
 
  /**
  * Le d�limiteur
  */
  private JSplitPane split;
 
  /**
  * Le conteneur de r�sultat
  */
  private JPanel result = new JPanel();
 
  /**
  * Requ�te par d�faut pour le d�marrage
  */
  private String requete = "SELECT  * FROM classe";
 
  /**
  * Le composant dans lequel taper la requ�te
  */
  private JTextArea text = new JTextArea(requete);
         
  /**
  * Constructeur
  */
  public Fenetre(){
    setSize(900, 600);
    setTitle("TP JDBC");
    setLocationRelativeTo(null);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
         
    initToolbar();
    initContent();
    initTable(requete);
  }
     
  /**
  * Initialise la toolbar
  */
  private void initToolbar(){
    load.setPreferredSize(new Dimension(100, 35));
    load.setBorder(null);
    load.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent event){
        initTable(text.getText());
      }
    });
 
    tool.add(load);
    
    getContentPane().add(tool, BorderLayout.NORTH);
  }
     
  /**
  * Initialise le contenu de la fen�tre
  */
  public void initContent(){
    //Vous connaissez �a...
    result.setLayout(new BorderLayout());
    split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, new JScrollPane(text), result);
    split.setDividerLocation(100);
    getContentPane().add(split, BorderLayout.CENTER);      
  }
     
  /**
  * Initialise le visuel avec la requ�te saisie dans l'�diteur
  * @param query
  */
  public void initTable(String query){
    try {
    //On cr�e un statement
      long start = System.currentTimeMillis();
      Statement state = MartConnection.getInstance().
    		  createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
 
      //On ex�cute la requ�te
      ResultSet res = state.executeQuery(query);
      //Temps d'ex�cution
 
      //On r�cup�re les meta afin de r�cup�rer le nom des colonnes
      ResultSetMetaData meta = res.getMetaData();
      //On initialise un tableau d'Object pour les en-t�tes du tableau
      Object[] column = new Object[meta.getColumnCount()];
 
      for(int i = 1 ; i <= meta.getColumnCount(); i++)
        column[i-1] = meta.getColumnName(i);
 
      //Petite manipulation pour obtenir le nombre de lignes
      res.last();
      int rowCount = res.getRow();
      Object[][] data = new Object[res.getRow()][meta.getColumnCount()];
 
      //On revient au d�part
      res.beforeFirst();
      int j = 1;
 
      //On remplit le tableau d'Object[][]
      while(res.next()){
        for(int i = 1 ; i <= meta.getColumnCount(); i++)
          data[j-1][i-1] = res.getObject(i);
                 
        j++;
      }
 
      //On ferme le tout                                    
      res.close();
      state.close();
 
      long totalTime = System.currentTimeMillis() - start;
 
      //On enl�ve le contenu de notre conteneur
      result.removeAll();
      //On y ajoute un JTable
      result.add(new JScrollPane(new JTable(data, column)), BorderLayout.CENTER);
      result.add(new JLabel("La requ�te � �t� ex�cuter en " + totalTime +
    		  " ms et a retourn� " + rowCount + " ligne(s)"), BorderLayout.SOUTH);
      //On force la mise � jour de l'affichage
      result.revalidate();
             
    } catch (SQLException e) {
      //Dans le cas d'une exception, on affiche une pop-up et on efface le contenu     
      result.removeAll();
      result.add(new JScrollPane(new JTable()), BorderLayout.CENTER);
      result.revalidate();
      JOptionPane.showMessageDialog(null, e.getMessage(), "ERREUR ! ", JOptionPane.ERROR_MESSAGE);
    }  
  }
 
  /**
  * Point de d�part du programme
  * @param args
  */
  public static void main(String[] args){
    Fenetre fen = new Fenetre();
  fen.setVisible(true);
  }

}
