package graphicsModel;

import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.WindowListener;

public abstract class AbstractAfficher implements ActionListener,KeyListener,
MouseListener,WindowListener,FocusListener {
	public AbstractAfficher(){};
	public abstract void load();
	public abstract void update();
}
