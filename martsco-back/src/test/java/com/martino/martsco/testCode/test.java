package com.martino.martsco.testCode;

import java.io.File;

public class test {

	public static void main(String[] args) {
		try {
			java.awt.Desktop.getDesktop().open(new File("C:\\Users\\Martin\\pdfTemp1530493632463815354.pdf"));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
