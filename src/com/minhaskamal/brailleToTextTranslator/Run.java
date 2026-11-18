/***********************************************************
* Developer: Minhas Kamal (minhaskamal024@gmail.com)       *
* License: MIT License                                     *
***********************************************************/

package com.minhaskamal.brailleToTextTranslator;

import javax.swing.UIManager;

public class Run {
	public static void main(String args[]) {
		/*// Set the NIMBUS look and feel //*/
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception ex) {
			// do nothing if operation is unsuccessful
		}

		/* Create */
		new MainFrame();
	}
}
