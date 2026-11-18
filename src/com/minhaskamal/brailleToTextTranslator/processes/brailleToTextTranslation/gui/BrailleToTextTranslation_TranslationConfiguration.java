/***********************************************************
* Developer: Minhas Kamal (minhaskamal024@gmail.com)       *
* Date: 21-Nov-2016                                        *
* License: MIT License                                     *
***********************************************************/

package com.minhaskamal.brailleToTextTranslator.processes.brailleToTextTranslation.gui;

import java.awt.GridLayout;
import javax.swing.*;
import com.minhaskamal.brailleToTextTranslator.processes.*;
import com.minhaskamal.brailleToTextTranslator.processes.utils.commonUserInterfaces.TranslationConfiguration;

public class BrailleToTextTranslation_TranslationConfiguration extends TranslationConfiguration{

	/***##Constructor##***/
	public BrailleToTextTranslation_TranslationConfiguration(UserInterface previousUserInterface) {
		super(previousUserInterface);

		initialComponent();
	}

	/**
	 * Method for Initializing all the GUI variables and placing them all to specific space on 
	 * the frame. It also specifies criteria of the main frame.
	 */
	private void initialComponent() {

		setTitle(title);
	}

	@Override
	protected void callFollowingUI(){
		new BrailleToTextTranslation_PostProcessingConfiguration(this).attachToBoard();
	}
	
	/********* Main Method *********/
	public static void main(String args[]) {
		/*// Set the NIMBUS look and feel //*/
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception ex) {
			// do nothing if operation is unsuccessful
		}

		/* Create and display the form */
		JPanel jPanel = new JPanel(new GridLayout());
		
		JFrame jFrame = new JFrame();
		jFrame.setBounds(200, 150, 600, 450);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setVisible(true);
		jFrame.setLayout(new GridLayout());
		jFrame.add(jPanel);
		
		new BrailleToTextTranslation_TranslationConfiguration(new OpeningUserInterface(jPanel)).attachToBoard();
	}
}
