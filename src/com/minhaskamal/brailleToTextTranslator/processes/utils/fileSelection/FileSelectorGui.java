/***********************************************************
* Developer: Minhas Kamal (minhaskamal024@gmail.com)       *
* Date: 20-Oct-2016                                        *
* License: MIT License                                     *
***********************************************************/

package com.minhaskamal.brailleToTextTranslator.processes.utils.fileSelection;

import java.awt.*;
import javax.swing.*;

import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class FileSelectorGui extends JLabel {

	//**
	// Variable Declaration 																	#*******D*******#
	//**
	JTextField jTextFieldDirectoryPath;
	JButton jButtonBrowse;
	// End of Variable Declaration 																#_______D_______#

	/***##Constructor##***/
	public FileSelectorGui() {

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
		jTextFieldDirectoryPath = new JTextField();
		jButtonBrowse = new JButton("...");
		// End of Initialization																#_______I_______#

		//**
		// Setting Bounds and Attributes of the Elements 										#*******S*******#
		//**
		jButtonBrowse.setToolTipText("Browse");
		// End of Setting Bounds and Attributes 												#_______S_______#
		
		//**Setting Criterion of the Label**//
		//setBorder(BorderFactory.createDashedBorder(null));
		setLayout(new MigLayout("ins 0"));

		//**
		// Adding Components 																	#*******A*******#
		//**
		add(jTextFieldDirectoryPath, "h 25:35:50, w 80:2000:2000");
		add(jButtonBrowse, "h 25:35:50, w 40!");
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
		FileSelectorGui gui = new FileSelectorGui();
		
		JFrame jFrame = new JFrame();
		jFrame.setBounds(10, 10, 500, 400);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setVisible(true);
		jFrame.setLayout(new GridLayout());
		jFrame.add(gui);
	}

}
