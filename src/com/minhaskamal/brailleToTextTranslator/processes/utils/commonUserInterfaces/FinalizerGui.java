/***********************************************************
* Developer: Minhas Kamal (minhaskamal024@gmail.com)       *
* Date: 26-Oct-2016                                        *
* License: MIT License                                     *
***********************************************************/

package com.minhaskamal.brailleToTextTranslator.processes.utils.commonUserInterfaces;

import java.awt.GridLayout;
import javax.swing.*;
import com.minhaskamal.brailleToTextTranslator.processes.UserInterfaceGui;

@SuppressWarnings("serial")
public class FinalizerGui extends UserInterfaceGui{

	//**
	// Variable Declaration 																	#*******D*******#
	//**
	JLabel jLabelPrompt;
	JLabel jLabelBrowse;
	// End of Variable Declaration 																#_______D_______#

	/***##Constructor##***/
	public FinalizerGui() {

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
		jLabelPrompt = new JLabel();
		jLabelBrowse = new JLabel();
		// End of Initialization																#_______I_______#

		//**
		// Setting Bounds and Attributes of the Elements 										#*******S*******#
		//**
		int ypos = 50;
		
		jLabelPrompt.setBounds(xMargin, ypos, componantWidth, componantHeight);
		ypos += componantHeight+gapSmall;
		jLabelBrowse.setBounds(xMargin, ypos, componantWidth, componantHeight);
		jLabelBrowse.setLayout(new GridLayout());
		
		jButtonCancel.setText("Cancel");
		jButtonOk.setText("Run");
		// End of Setting Bounds and Attributes 												#_______S_______#
		
		//**Setting Criterion of the Label**//
		//setBorder(BorderFactory.createDashedBorder(null));
		setLayout(null);

		//**
		// Adding Components 																	#*******A*******#
		//**
		add(jLabelPrompt);
		add(jLabelBrowse);
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
		FinalizerGui gui = new FinalizerGui();
		
		JFrame jFrame = new JFrame();
		jFrame.setBounds(200, 150, 600, 450);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setVisible(true);
		jFrame.setLayout(new GridLayout());
		jFrame.add(gui);
	}


}
