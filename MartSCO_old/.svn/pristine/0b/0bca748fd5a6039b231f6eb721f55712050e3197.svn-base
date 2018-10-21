package graphicsModel;

import java.awt.Component;
import java.awt.Container;
import java.awt.FocusTraversalPolicy;
import java.util.HashMap;

public class MartFocusManager extends FocusTraversalPolicy {
	private HashMap<Integer,Component> components=new HashMap<Integer,Component>();
	private HashMap<Component,Integer> positions=new HashMap<Component,Integer>();
	
	public void addComponent(Component component,Integer position){
		components.put(position,component);
		positions.put(component, position);
	}

	@Override
	public Component getComponentAfter(Container parent, Component component) {
		int position=positions.get(component);
		
		return components.get(position+1);
	}

	@Override
	public Component getComponentBefore(Container parent, Component component) {
		int position=positions.get(component);
		
		return components.get(position-1);
	}

	@Override
	public Component getDefaultComponent(Container parent) {
		return components.get(0);
	}

	@Override
	public Component getFirstComponent(Container parent) {
		
		return components.get(0);
	}

	@Override
	public Component getLastComponent(Container arg0) {
		int last=components.size()-1;
		return components.get(last);
	}

}
