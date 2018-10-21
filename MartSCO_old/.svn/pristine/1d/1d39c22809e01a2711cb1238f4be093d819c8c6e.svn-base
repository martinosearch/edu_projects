/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package complements;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;

 
import javax.swing.JButton;
/**
 *
 * @author webdev
 */
//public class Bouton extends JButton implements MouseListener
public class JCoolBouton extends JButton{
/**
* 
*/
	private static final long serialVersionUID = 1671314658637614873L;
	private int inset = 2;
	private Color buttonColor = Color.black.brighter().brighter().brighter().brighter();
        private String separ = System.getProperty("file.separator");
        private Image img;
        private Chemin myChemin = new Chemin();
 
	public JCoolBouton(String aNameString){
		super(aNameString);
		setContentAreaFilled(false);
		setForeground(Color.white);
       	}
 
 
 
	protected void paintComponent(Graphics g)
	{
 
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
 
		int vWidth = getWidth();
		int vHeight = getHeight();
 
		// Calcule la taile du bouton
		int vButtonHeight = vHeight - (inset * 2);
		int vButtonWidth = vWidth - (inset * 2);
		int vArcSize = (inset * 4);
 
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
 
		//Créer la peinture gradié pour la première couche du bouton
		Color vGradientStartColor =  buttonColor.darker().darker().darker().red;
		Color vGradientEndColor = buttonColor.brighter().brighter().brighter();
		Paint vPaint = new GradientPaint(0, inset, vGradientStartColor, 0, vButtonHeight, vGradientEndColor, false);
		g2d.setPaint(vPaint);
 
		// Dessine la première couche du bouton
		g2d.fillRoundRect(inset, inset, vButtonWidth, vButtonHeight, vArcSize, vArcSize);
 
		// Calule la seconde couche du bouton
		int vHighlightInset = 1;
		int vButtonHighlightHeight = vButtonHeight - (vHighlightInset * 2);
 
                // creé la seconde couche du bouton
		vGradientStartColor = Color.WHITE;
		vGradientEndColor = buttonColor.darker();
		vPaint = new GradientPaint(0,inset+vHighlightInset,vGradientStartColor,0,inset+vHighlightInset+(vButtonHighlightHeight/2), buttonColor.brighter(), false);
 
		// dessine la seconde couche du bouton
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,.8f));
		g2d.setPaint(vPaint);
  		g2d.fillRoundRect(inset, inset, vButtonWidth, vButtonHeight, vArcSize, vArcSize);

		RoundRectangle2D.Float r2d =new RoundRectangle2D.Float(inset, inset, vButtonWidth, vButtonHeight, vArcSize, vArcSize);
		g2d.clip(r2d);		
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1.0f));
		super.paintComponent(g);
 
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
	}

}
