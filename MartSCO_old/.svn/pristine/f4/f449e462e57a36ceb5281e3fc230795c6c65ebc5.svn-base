package browser;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.html.HTMLEditorKit;

import editeur.MartEditorPane;


public class MartLatexhtml extends JFrame implements ActionListener { 

private JTextArea latexSource;
private JButton btnRender; 
private MartEditorPane drawingArea; 
Container content;


public MartLatexhtml() { 
	this.setTitle("JLatexMath Tourné à ma façon");
	this.setSize(500, 500);
	content = this.getContentPane();
	content.setLayout(new GridLayout(2, 1));
	
	this.latexSource = new JTextArea();
	
	JPanel editorArea = new JPanel();
	editorArea.setLayout(new BorderLayout());
	editorArea.add(new JScrollPane(this.latexSource),BorderLayout.CENTER);
	editorArea.add(btnRender = new JButton("Render"),BorderLayout.SOUTH);
	content.add(editorArea); 
	this.drawingArea = new MartEditorPane();
	drawingArea.setEditorKit(new HTMLEditorKit());
	content.add(drawingArea); 
	
	this.btnRender.addActionListener(this); 
	this.latexSource.setText("<html><head></head>"
			+ "<body>\n"
			+ "<div valign='center'>\n"
			+ "<latex></latex>\n"
			+ "</div>\n"
			+ "</body></html>"); 
}
	


  public void render() {
	  try {
		  // get the text
		  String latex = this.latexSource.getText(); 
		  System.out.println("Le texte actuel est: "+latex);
		  drawingArea.setHtml(latex); 
		  drawingArea.revalidate();
		  content.revalidate();
          
       } catch (Exception ex) {
    	   ex.printStackTrace(); 
    	   JOptionPane.showMessageDialog(null,ex.getMessage(),
    			   "Error",JOptionPane.INFORMATION_MESSAGE);
    	   } 
  }
	   

    public static void main(String[] args) { 
           MartLatexhtml frame = new MartLatexhtml();       
              frame.setVisible(true); 
    } 



  public void actionPerformed(ActionEvent e) {
	  if( e.getSource()==this.btnRender ) {
		  render();
		  } 
  }
}
  
