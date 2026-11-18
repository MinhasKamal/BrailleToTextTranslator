/***********************************************************
* Developer: Minhas Kamal (minhaskamal024@gmail.com)       *
* Date: 24-Oct-2016                                        *
* License: MIT License                                     *
***********************************************************/

package com.minhaskamal.brailleToTextTranslator.processes.preProcessing.gui;

import java.awt.GridLayout;
import javax.swing.*;
import com.minhaskamal.brailleToTextTranslator.processes.*;
import com.minhaskamal.brailleToTextTranslator.processes.preProcessing.PreProcessingManager;
import com.minhaskamal.brailleToTextTranslator.processes.utils.commonUserInterfaces.Initializer;

public class PreProcessingInitializer extends Initializer{

	
	/***##Constructor##***/
	public PreProcessingInitializer(UserInterface previousUserInterface) {
		super(previousUserInterface);
		
		initialComponent();
	}
	
	/**
	 * Method for Initializing all the GUI variables and placing them all to specific space on 
	 * the frame. It also specifies criteria of the main frame.
	 */
	private void initialComponent() {
		directoryChooser.setFileExtensions(PreProcessingManager.ACCEPTABLE_IMAGE_EXTENSIONS);
		
		setPrompt("Select Input Braille Image Directory");
		setTitle(title);
	}

	@Override
	protected void callFollowingUI(){
		new PreProcessing_PreProcessingConfiguration(this).attachToBoard();
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
		
		new PreProcessingInitializer(new OpeningUserInterface(jPanel)).attachToBoard();
	}

}
