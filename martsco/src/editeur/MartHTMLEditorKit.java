package editeur;

import java.util.ArrayList;

import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;

public class MartHTMLEditorKit extends HTMLEditorKit{
	StyleSheet styleSheet;
	
	public MartHTMLEditorKit(){
		super();
		this.styleSheet=(new HTMLEditorKit()).getStyleSheet();
	}
	
	public void addStyle(String rule){
		styleSheet.addRule(rule);
		this.setStyleSheet(styleSheet);
	}
	
	public void addStyle(ArrayList<String> rules){
		for(String rule:rules){
		styleSheet.addRule(rule);
		this.setStyleSheet(styleSheet);
		}
	}	
}
