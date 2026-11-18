/***********************************************************
* Developer: Minhas Kamal (minhaskamal024@gmail.com)       *
* Date: 22-Oct-2016                                        *
* License: MIT License                                     *
***********************************************************/

package com.minhaskamal.brailleToTextTranslator.processes.utils.commonUserInterfaces;

import java.awt.*;
import javax.swing.*;

import com.minhaskamal.brailleToTextTranslator.processes.preProcessing.*;

import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class PreProcessingMethodGui  extends JLabel{

	//**
	// Variable Declaration 																	#*******D*******#
	//**
	@SuppressWarnings("rawtypes")
	JComboBox jComboBoxPreProcessingProcess, jComboBoxPreProcessingAlgorithm;
	JButton jButtonPreProcessingRemove;
	// End of Variable Declaration 																#_______D_______#

	/***##Constructor##***/
	public PreProcessingMethodGui() {

		initialComponent();
	}

	
	/**
	 * Method for Initializing all the GUI variables and placing them all to specific space on 
	 * the component. It also specifies criteria of the main component.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initialComponent() {
		//**
		// Initialization 																		#*******I*******#
		//**
		jComboBoxPreProcessingProcess = new JComboBox(new String[]{PreProcessingManager.PROCESS_EMPTY,
				PreProcessingManager.PROCESS_ENHANCEMENT, PreProcessingManager.PROCESS_NOISE_REDUCTION,
				PreProcessingManager.PROCESS_CONNECTIVITY_IMPROVEMENT, PreProcessingManager.PROCESS_QUANTIZATION});
		jComboBoxPreProcessingAlgorithm = new JComboBox(new String[]{PreProcessingManager.ALGORITHM_EMPTY});
		jButtonPreProcessingRemove = new JButton("X");
		// End of Initialization																#_______I_______#

		//**
		// Setting Bounds and Attributes of the Elements 										#*******S*******#
		//**
		//jComboBoxPreProcessingAlgorithm.setEditable(true);
		jComboBoxPreProcessingAlgorithm.setEnabled(false);
		jButtonPreProcessingRemove.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
		// End of Setting Bounds and Attributes 												#_______S_______#
		
		//**Setting Criterion of the Label**//
		//setBorder(BorderFactory.createDashedBorder(null));
		setLayout(new MigLayout());

		//**
		// Adding Components 																	#*******A*******#
		//**
		add(jComboBoxPreProcessingProcess, "h 30:35:50, w 80:2000:2000");
		add(jComboBoxPreProcessingAlgorithm, "h 30:35:50, w 80:2000:2000");
		add(jButtonPreProcessingRemove, "h 30:35:50, w 35!");
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
		PreProcessingMethodGui gui = new PreProcessingMethodGui();
		
		JFrame jFrame = new JFrame();
		jFrame.setBounds(200, 150, 600, 450);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setVisible(true);
		jFrame.setLayout(new GridLayout());
		jFrame.add(gui);
	}


}
