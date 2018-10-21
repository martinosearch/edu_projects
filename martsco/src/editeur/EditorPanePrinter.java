package editeur;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.print.PageFormat;
import java.awt.print.Pageable;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.lang.reflect.Method;
import java.util.ArrayList;

import javax.print.PrintService;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.DialogTypeSelection;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.text.BoxView;
import javax.swing.text.CompositeView;
import javax.swing.text.Position;
import javax.swing.text.View;

public class EditorPanePrinter extends JPanel implements Pageable, Printable {
	private MartEditorPane sourcePane;
	private Paper paper;
	private Insets margins;
	private ArrayList<PagePanel> pages;
	private int pageWidth;
	private int pageHeight;
	private View rootView;
	public static int PAGE_DROP = 20;
	private PageFormat pageFormat;
	private PrintRequestAttributeSet attributes;
	private int orientation;

	// le contructeur
	public EditorPanePrinter(MartEditorPane pane, Paper paper, Insets margins) {
		orientation = pane.getOrientation();
		initData(pane, paper, margins);
	}

	// initialisation des données
	public void initData(MartEditorPane pane, Paper paper1, Insets margins) {

		if (pane.getMathOption() == MartEditorPane.MATHS_DOCUMENT) {
			sourcePane = new MartEditorPane(MartEditorPane.MATHS_DOCUMENT);
		} else {
			sourcePane = new MartEditorPane(MartEditorPane.SIMPLE_DOCUMENT);
		}

		sourcePane.setOrientation(orientation);
		sourcePane.setContentType(pane.getContentType());
		sourcePane.setEditorKit(pane.getMartEditorKit());
		sourcePane.setHtml(pane.getHtml());
		sourcePane.revalidate();

		this.paper = paper1;
		this.margins = margins;

		pageFormat = new PageFormat();

		if (orientation == PageFormat.PORTRAIT) {
			this.pageWidth = (int) paper1.getWidth();
			this.pageHeight = (int) paper1.getHeight();

			paper.setImageableArea(0, 0, paper1.getWidth(), paper1.getHeight());
			pageFormat.setOrientation(PageFormat.PORTRAIT);
		} else {
			this.pageWidth = (int) paper1.getHeight();
			this.pageHeight = (int) paper1.getWidth();

			paper.setImageableArea(0, 0, paper1.getWidth(), paper1.getHeight());
			pageFormat.setOrientation(PageFormat.LANDSCAPE);
		}

		pageFormat.setPaper(paper);

		doPagesLayout();

		// pour l'impression
		attributes = new HashPrintRequestAttributeSet();
		sourcePane.revalidate();
	}

	public void doPagesLayout() {
		setLayout(null);
		removeAll();
		this.rootView = sourcePane.getUI().getRootView(sourcePane);

		sourcePane.setSize(pageWidth - margins.left - margins.right,
				Integer.MAX_VALUE);// **************
		// réfléchir sur cette partie

		Dimension d = sourcePane.getPreferredSize();
		sourcePane.setSize(pageWidth - margins.left - margins.right, d.height);

		calculatePageInfo();

		int count = pages.size();
		if (orientation == PageFormat.PORTRAIT)
			this.setPreferredSize(new Dimension(pageWidth * 2, PAGE_DROP
					+ count * (pageHeight + PAGE_DROP)));

		if (orientation == PageFormat.LANDSCAPE)
			this.setPreferredSize(new Dimension(pageWidth * 2, PAGE_DROP
					+ count * (pageHeight + PAGE_DROP)));
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.YELLOW);

		// Pagination (astruce pour g�rer la paginatiion)
		int max = getNumberOfPages();
		long maxLong = this.getHeight();
		int pos = pageHeight + PAGE_DROP - 10;
		int num = 1;
		while (pos < maxLong) {
			g.drawString("Page: " + num + " / " + max, 10, pos);
			pos = pos + pageHeight + PAGE_DROP;
			num++;
		}
		// fin pagination*********************************

		AffineTransform old = ((Graphics2D) g).getTransform();
		((Graphics2D) g).setTransform(old);
	}

	protected void calculatePageInfo() {
		pages = new ArrayList<PagePanel>();
		int startY = 0;
		int endPageY = getEndPageY(startY);
		while (startY + pageHeight - margins.top - margins.bottom < sourcePane
				.getHeight()) {
			Shape pageShape = getPageShape(startY, pageWidth - margins.left
					- margins.right, pageHeight - margins.top - margins.bottom,
					sourcePane);
			pages.add(new PagePanel(startY, endPageY, pageShape));
			startY = endPageY;
			endPageY = getEndPageY(startY);
		}
		Shape pageShape = getPageShape(startY, pageWidth - margins.left
				- margins.right, pageHeight - margins.top - margins.bottom,
				sourcePane);
		pages.add(new PagePanel(startY, endPageY, pageShape));

		int count = 0;
		for (PagePanel pi : pages) {
			add(pi);
			// pour centrer
			int position = 10;
			if (orientation == PageFormat.PORTRAIT)
				position = pageWidth / 2 - pageWidth / 6;

			if (orientation == PageFormat.LANDSCAPE)
				position = pageWidth / 4 - pageWidth / 6;

			pi.setLocation(position, PAGE_DROP + count
					* (pageHeight + PAGE_DROP));
			count++;
		}
	}

	protected int getEndPageY(int startY) {
		int desiredY = startY + pageHeight - margins.top - margins.bottom;
		int realY = desiredY;

		for (int x = 1; x < pageWidth; x++) {
			View v = getLeafViewAtPoint(new Point(x, realY), rootView);
			if (v != null) {
				Rectangle alloc = getAllocation(v, sourcePane).getBounds();
				if (alloc.height > pageHeight - margins.top - margins.bottom) {
					continue;
				}
				if (alloc.y + alloc.height > desiredY) {
					realY = Math.min(realY, alloc.y);
				}
			}
		}

		return realY;
	}

	protected View getLeafViewAtPoint(Point p, View root) {
		return getLeafViewAtPoint(p, root, sourcePane);
	}

	public static View getLeafViewAtPoint(Point p, View root,
			MartEditorPane sourcePane) {
		int pos = sourcePane.viewToModel(p);
		View v = sourcePane.getUI().getRootView(sourcePane);
		while (v.getViewCount() > 0) {
			int i = v.getViewIndex(pos, Position.Bias.Forward);
			v = v.getView(i);
		}
		Shape alloc = getAllocation(root, sourcePane);
		if (alloc.contains(p)) {
			return v;
		}

		return null;
	}

	public static Shape getPageShape(int pageStartY, int pageWidth,
			int pageHeight, MartEditorPane sourcePane) {
		Area result = new Area(new Rectangle(0, 0, pageWidth, pageHeight));
		View rootView = sourcePane.getUI().getRootView(sourcePane);
		Rectangle last = new Rectangle();
		for (int x = 1; x < pageWidth; x++) {
			View v = getLeafViewAtPoint(new Point(x, pageStartY), rootView,
					sourcePane);
			if (v != null) {
				Rectangle alloc = getAllocation(v, sourcePane).getBounds();
				if (alloc.y < pageStartY && alloc.y + alloc.height > pageStartY) {
					if (!alloc.equals(last)) {
						Rectangle r = new Rectangle(alloc);
						r.y -= pageStartY;
						result.subtract(new Area(r));
					}
				}
				last = alloc;
			}
		}

		last = new Rectangle();
		for (int x = 1; x < pageWidth; x++) {
			View v = getLeafViewAtPoint(new Point(x, pageStartY + pageHeight),
					rootView, sourcePane);
			if (v != null) {
				Rectangle alloc = getAllocation(v, sourcePane).getBounds();
				if (alloc.y < pageStartY + pageHeight
						&& alloc.y + alloc.height > pageStartY + pageHeight) {
					if (!alloc.equals(last)) {
						Rectangle r = new Rectangle(alloc);
						r.y -= pageStartY;
						result.subtract(new Area(r));
					}
				}
				last = alloc;
			}
		}

		return result;
	}

	// pageable methods
	public int getNumberOfPages() {
		return pages.size();
	}

	public PageFormat getPageFormat(int pageIndex)
			throws IndexOutOfBoundsException {
		return pageFormat;
	}

	public Printable getPrintable(int pageIndex)
			throws IndexOutOfBoundsException {
		return this;
	}

	public int print(Graphics g, PageFormat pageFormat, int pageIndex)
			throws PrinterException {
		if (pageIndex < pages.size()) {
			pageFormat.getPaper().setImageableArea(0, 0, paper.getWidth(),
					paper.getHeight());

			pages.get(pageIndex).isPrinting = true;
			pages.get(pageIndex).paint(g);
			pages.get(pageIndex).isPrinting = false;

			return PAGE_EXISTS;
		}

		return NO_SUCH_PAGE;
	}

	class PagePanel extends JPanel {
		int pageStartY;
		int pageEndY;
		Shape pageShape;
		boolean isPrinting = false;

		JPanel innerPage = new JPanel() {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);

				AffineTransform old = ((Graphics2D) g).getTransform();

				Shape oldClip = g.getClip();

				Area newClip = new Area(oldClip);
				if (isPrinting) {
					newClip = new Area(pageShape);
				} else {
					newClip.intersect(new Area(pageShape));
				}
				g.setClip(newClip);

				g.translate(0, -pageStartY);

				sourcePane.paint(g);
				for (Component c : sourcePane.getComponents()) {
					AffineTransform tmp = ((Graphics2D) g).getTransform();
					g.translate(c.getX(), c.getY());
					((Container) c).getComponent(0).paint(g);
					((Graphics2D) g).setTransform(tmp);
				}

				((Graphics2D) g).setTransform(old);
				g.setClip(oldClip);
			}

		};

		public PagePanel() {
			this(0, 0, null);
		}

		public PagePanel(int pageStartY, int pageEndY, Shape pageShape) {
			this.pageStartY = pageStartY;
			this.pageEndY = pageEndY;
			this.pageShape = pageShape;

			setSize(pageWidth, pageHeight);
			setBackground(Color.white);
			setLayout(null);
			innerPage.setBorder(null);
			innerPage.setBackground(Color.white);
			innerPage.setBounds(margins.left, margins.top, pageWidth
					- margins.left - margins.right, pageHeight - margins.top
					- margins.bottom);
			add(innerPage);

		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(Color.black);

		}
	}

	// methode qui fait l'impression
	public void print() {
		try {
			PrinterJob job = PrinterJob.getPrinterJob();
			job.setPageable(this);
			attributes.add(DialogTypeSelection.COMMON);

			if (job.printDialog(attributes)) {
				job.print(attributes);
			}

		} catch (PrinterException e) {
			e.printStackTrace();
		}
	}

	public void print(PrintService ps) {
		try {
			PrinterJob pj = PrinterJob.getPrinterJob();
			JFrame tmp = null;
			if (this.getParent() == null) {
				tmp = new JFrame();
				tmp.getContentPane().add(new JScrollPane(this));
				tmp.pack();
				tmp.setVisible(false);
			}

			pj.setPageable(this);
			if (ps != null) {
				pj.setPrintService(ps);
			}
			pj.print();

			if (tmp != null) {
				tmp.dispose();
			}
		} catch (PrinterException e1) {
			e1.printStackTrace();
		}
	}

	public void exportPDF() {
		try {
			PrinterJob job = PrinterJob.getPrinterJob();
			job.setPageable(this);
			attributes.add(DialogTypeSelection.COMMON);

			PrintService[] services = job.lookupPrintServices();
			System.out.println("Services contient: " + services.length);
			for (PrintService s : services) {
				System.out.println(s.getName() + "; ");

				if (s.getName().equals("PDF24 PDF")) {
					job.setPrintService(s);
					// job.printDialog(attributes);
					job.print(attributes);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// **************METHODE DEPLACEE DE PRINTER*********************
	protected static Shape getAllocation(View v, JEditorPane edit) {
		Insets ins = edit.getInsets();
		View root = edit.getUI().getRootView(edit);
		View vParent = v.getParent();
		int x = ins.left;
		int y = ins.top;
		while (vParent != null) {
			int i = vParent.getViewIndex(v.getStartOffset(),
					Position.Bias.Forward);
			Shape alloc = vParent.getChildAllocation(i, new Rectangle(0, 0,
					Short.MAX_VALUE, Short.MAX_VALUE));
			x += alloc.getBounds().x;
			y += alloc.getBounds().y;

			vParent = vParent.getParent();
		}

		return new Rectangle(x, y, (int) v.getPreferredSpan(View.X_AXIS),
				(int) v.getPreferredSpan(View.Y_AXIS));
	}

	public int getOffset(BoxView source, int axis, int childIndex) {
		try {
			Method m = BoxView.class.getDeclaredMethod("getOffset",
					new Class[] { int.class, int.class });
			m.setAccessible(true);
			return ((Number) m
					.invoke(source, new Object[] { axis, childIndex }))
					.intValue();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	public short getLeftInset(CompositeView source) {
		try {
			Method m = CompositeView.class.getDeclaredMethod("getLeftInset");
			m.setAccessible(true);
			return ((Number) m.invoke(source)).shortValue();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	public short getTopInset(CompositeView source) {
		try {
			Method m = CompositeView.class.getDeclaredMethod("getTopInset");
			m.setAccessible(true);
			return ((Number) m.invoke(source)).shortValue();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	public static void main(String[] args) {
		MartEditorPane panbull = new MartEditorPane();
		Paper p = new Paper();
		p.setSize(595, 842);
		EditorPanePrinter pan = new EditorPanePrinter(panbull, p, new Insets(
				10, 15, 10, 15));

		pan.exportPDF();
	}
}
