package graphicsModel;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.util.ArrayList;

/**
 * This layout manager allows you to place components to form a circle within a
 * Container
 * 
 * @author Oscar De Leon oedeleon@netscape.net
 * 
 */

public class CircleLayout implements LayoutManager {

	ArrayList components;

	ArrayList names;

	private boolean isCircle;
	private int x, y, w, h, s, c;

	private int n;

	private int parentWidth;

	private int parentHeight;

	private Insets insets;

	private int centerX;

	private int centerY;

	private Component comp;

	private Dimension compPS;

	private Container parent;

	private int count = 0;

	/**
	 * Creates a new CircleLayout that lays out components in a perfect circle
	 */

	public CircleLayout() {
		this(true);
	}

	/**
	 * Creates a new CircleLayout that lays out components in either an Ellipse
	 * or a Circle. Ellipse Layout is not yet implemented.
	 * 
	 * @param circle
	 *            Indicated the shape to use. It's true for circle or false for
	 *            ellipse.
	 */
	public CircleLayout(boolean circle) {
		isCircle = circle;
	}

	/**
	 * For compatibility with LayoutManager interface
	 */
	public void addLayoutComponent(String name, Component comp) {
	}

	/**
	 * Arranges the parent's Component objects in either an Ellipse or a Circle.
	 * Ellipse is not yet implemented.
	 */

	public void layoutContainer(Container parent) {
		count++;
		// System.out.print("Je suis appelé =====================>>" + count);

		int coef = ((count % 2 == 0) ? 6 : (count % 3 == 0) ? 12 : 3);

		this.parent = parent;
		n = parent.getComponentCount();
		parentWidth = parent.getSize().width;
		parentHeight = parent.getSize().height;
		insets = parent.getInsets();
		centerX = (int) (parentWidth - (insets.left + insets.right)) / 2;
		centerY = (int) (parentHeight - (insets.top + insets.bottom)) / 2;

		comp = null;
		compPS = null;
		if (n == 1) {
			comp = parent.getComponent(0);
			x = centerX;
			y = centerY;
			compPS = comp.getPreferredSize();
			w = compPS.width;
			h = compPS.height;
			comp.setBounds(x, y, w, h);
		} else {
			double r = (Math.min(parentWidth - (insets.left + insets.right),
					parentHeight - (insets.top + insets.bottom))) / 2;

			r *= 0.75; // Multiply by .75 to account for extreme right and
						// bottom
						// Components
			for (int i = 0; i < n; i++) {
				comp = parent.getComponent(i);
				compPS = comp.getPreferredSize();
				if (isCircle) {
					c = (int) (r * Math.cos(2 * i * Math.PI / n));
					s = (int) (r * Math.sin(2 * i * Math.PI / n));
				} else {
					c = (int) ((centerX * 0.75) * Math
							.cos((2 * i * Math.PI / n) - Math.PI / coef));
					s = (int) ((centerY * 0.75) * Math
							.sin((2 * i * Math.PI / n) - Math.PI / coef));
				}
				x = c + centerX;
				y = s + centerY;

				w = compPS.width;
				h = compPS.height;

				comp.setBounds(x, y, w, h);
			}
		}

	}

	/**
	 * Returns this CircleLayout's preferred size based on its Container
	 * 
	 * @param target
	 *            This CircleLayout's target container
	 * @return The preferred size
	 */

	public Dimension preferredLayoutSize(Container target) {
		return target.getSize();
	}

	/**
	 * Returns this CircleLayout's minimum size based on its Container
	 * 
	 * @param target
	 *            This CircleLayout's target container
	 * @return The minimum size
	 */
	public Dimension minimumLayoutSize(Container target) {
		return target.getSize();
	}

	/**
	 * For compatibility with LayoutManager interface
	 */
	public void removeLayoutComponent(Component comp) {
	}

	/**
	 * Returns a String representation of this CircleLayout.
	 * 
	 * @return A String that represents this CircleLayout
	 */
	public String toString() {
		return this.getClass().getName();
	}

}