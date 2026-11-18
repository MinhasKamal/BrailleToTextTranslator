/***********************************************************
* Developer: Minhas Kamal (minhaskamal024@gmail.com)       *
* Date: 22-Oct-2016                                        *
* License: MIT License                                     *
***********************************************************/

package com.minhaskamal.brailleToTextTranslator.processes.utils.openingPanel;

import java.awt.GridLayout;

import javax.swing.*;

@SuppressWarnings("serial")
public class OpeningPanelGui  extends JLabel{

	//**
	// Variable Declaration 																	#*******D*******#
	//**
	JButton jButtonBrailleToTextTranslation, jButtonTemplateGeneration, jButtonInstruction;
	// End of Variable Declaration 																#_______D_______#

	/***##Constructor##***/
	public OpeningPanelGui() {

		initialComponent();
	}

	
	/**
	 * Method for Initializing all the GUI variables and placing them all to specific space on 
	 * the component. It also specifies criteria of the main component.
	 */
	private void initialComponent() {
		//**
		// Initialization 																		#*******I*******#
		//**
		jButtonBrailleToTextTranslation = new JButton("Run Braille to Text Translation");
		jButtonTemplateGeneration = new JButton("Create Braille Processing Template");
		jButtonInstruction = new JButton("Read Instruction");
		// End of Initialization																#_______I_______#

		//**
		// Setting Bounds and Attributes of the Elements 										#*******S*******#
		//**
		int buttonWidth = 250;
		int buttonHeight = 30;
		int x = (584-buttonWidth)/2;
		int yStart = 150;
		int yGap = 10;
		int yPosition = buttonHeight+yGap;
		
		jButtonBrailleToTextTranslation.setBounds(x, yStart, buttonWidth, buttonHeight);
		jButtonTemplateGeneration.setBounds(x, yStart+yPosition, buttonWidth, buttonHeight);
		jButtonInstruction.setBounds(x, yStart+2*yPosition, buttonWidth, buttonHeight);
		// End of Setting Bounds and Attributes 												#_______S_______#
		
		//**Setting Criterion of the Label**//
		//setBorder(BorderFactory.createDashedBorder(null));
		setLayout(null);

		//**
		// Adding Components 																	#*******A*******#
		//**
		add(jButtonBrailleToTextTranslation);
		add(jButtonTemplateGeneration);
		add(jButtonInstruction);
		// End of Adding Components 															#_______A_______#
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
		OpeningPanelGui gui = new OpeningPanelGui();
		
		JFrame jFrame = new JFrame();
		jFrame.setBounds(200, 150, 600, 450);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setVisible(true);
		jFrame.setLayout(new GridLayout());
		jFrame.add(gui);
	}


}
