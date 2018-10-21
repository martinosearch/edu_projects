package graphicsModel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.joda.time.DateTime;

import function.Constance;

public class OptionItem extends JPanel implements MouseListener {
	private JPanel container;
	private Color back;
	private Color selectionColor = new Color(255, 229, 157);
	private boolean isSelected = false;
	private Object info;
	private String text;
	private String chemin;
	private JLabel lbTitle;
	private JLabel lbImage;
	private Color currentSelection = new Color(255, 22, 180);
	private DateTime time1;
	private boolean dClick;

	public OptionItem() {
		initComponent();
	}

	public OptionItem(String ch, String str) {
		initComponent();
		setText(str);
		setChemin(ch);
	}

	public void initComponent() {
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createLineBorder(new Color(172, 233, 255)));

		lbTitle = new JLabel();
		lbTitle.setFont(new Font("Arial", Font.BOLD, 14));

		lbImage = new JLabel();
		lbImage.setPreferredSize(MartFrame.OPTION_BUTTON_SIZE);

		// pan.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		add(lbTitle, BorderLayout.CENTER);
		add(lbImage, BorderLayout.WEST);
		addMouseListener(this);
	}

	public Color getBack() {
		return back;
	}

	public void setBack(Color back) {
		this.back = back;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
		lbTitle.setText("<html><head><meta><style>" + getStyle()
				+ "</style></meta></head><body>" + text + "</body>" + "</html>");
	}

	public String getChemin() {
		return chemin;
	}

	public void setChemin(String chemin) {
		this.chemin = chemin;
		URL url = getURL(chemin);
		ImageIcon image = new ImageIcon(url);
		lbImage.setIcon(image);
	}

	private String getStyle() {
		String style = "#explication{font-size:12pt;"
				+ "font-family:'bookman old style';font-style:'italic';font-weight:normal;"
				+ "}";

		return style;
	}

	private URL getURL(String str) {
		ClassLoader loader = getClass().getClassLoader();
		URL url = loader.getResource(Constance.getImageFolder() + str);

		return url;
	}

	public void addListener(Object listener) {
		addMouseListener((MouseListener) listener);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (isSelected == true && e.getClickCount() < 2) {
			setSelected(false);
		} else {
			setSelected(true);
		}
		update();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		setBackground(selectionColor);
		revalidate();
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		if (isSelected == true) {
			setBackground(currentSelection);
		} else {
			setBackground(back);
		}
		revalidate();
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public boolean isSelected() {
		// System.out.println("is selected: " + isSelected);
		return isSelected;
	}

	public void setSelected(boolean bool) {
		isSelected = bool;
	}

	/*
	 * public boolean isDoubleClick() { boolean bool = false; if (dClick ==
	 * false) { long duree = 3; if (time1 != null) { DateTime time2 = new
	 * DateTime();
	 * 
	 * duree = time2.getMillis() - time1.getMillis(); if (duree < 500) { dClick
	 * = true; } time1 = null; } else { time1 = new DateTime(); dClick = false;
	 * } bool = dClick; dClick = false; System.out.println("temps: " + duree +
	 * bool); }
	 * 
	 * return bool; }
	 */

	public void update() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				if (isSelected() == true) {
					setBackground(selectionColor);
					// System.out.println("I am here");
				} else {
					setBackground(back);
				}
			}
		});

		revalidate();
		repaint();
	}

	public void setInfo(Object obj) {
		info = obj;
	}

	public Object getInfo() {
		// TODO Auto-generated method stub
		return info;
	}
}
