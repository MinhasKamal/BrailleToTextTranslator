/***********************************************************
* Developer: Minhas Kamal (minhaskamal024@gmail.com)       *
* Date: 22-Oct-2016                                        *
* License: MIT License                                     *
***********************************************************/

package com.minhaskamal.brailleToTextTranslator.processes.utils.commonUserInterfaces;

import java.awt.*;
import javax.swing.*;
import com.minhaskamal.brailleToTextTranslator.processes.UserInterfaceGui;
import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class PreProcessingConfigurationGui extends UserInterfaceGui{

	//**
	// Variable Declaration 																	#*******D*******#
	//**
	JLabel jLabelPrompt;
	JButton jButtonAddPreProcessingMethod;
	
	JScrollPane jScrollPanePreProcessing;
	JPanel jPanelPreProcessing;
	// End of Variable Declaration 																#_______D_______#

	/***##Constructor##***/
	public PreProcessingConfigurationGui() {

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
		jLabelPrompt = new JLabel("Add Pre-Processing  Methods");
		jButtonAddPreProcessingMethod = new JButton("Add +");
		
		jScrollPanePreProcessing = new JScrollPane();
		jPanelPreProcessing = new JPanel();
		// End of Initialization																#_______I_______#

		//**
		// Setting Bounds and Attributes of the Elements 										#*******S*******#
		//**
		int panelHeight = 260;
		int ypos = 50;
		
		jLabelPrompt.setBounds(xMargin, ypos, componantWidth/2, componantHeight);
		jButtonAddPreProcessingMethod.setBounds(xMargin+componantWidth-100, ypos, 100, componantHeight);
		
		ypos += componantHeight+gapSmall;
		jScrollPanePreProcessing.setViewportView(jPanelPreProcessing);
		jScrollPanePreProcessing.setBounds(xMargin, ypos, componantWidth, panelHeight);
		jPanelPreProcessing.setBackground(new Color(230, 230, 230));
		jPanelPreProcessing.setLayout(new MigLayout("flowy"));
		
		jButtonCancel.setText("Cancel");
		jButtonOk.setText("Next >");
		// End of Setting Bounds and Attributes 												#_______S_______#
		
		//**Setting Criterion of the Label**//
		//setBorder(BorderFactory.createDashedBorder(null));
		setLayout(null);

		//**
		// Adding Components 																	#*******A*******#
		//**
		add(jLabelPrompt);
		add(jButtonAddPreProcessingMethod);
		
		add(jScrollPanePreProcessing);
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
		PreProcessingConfigurationGui gui = new PreProcessingConfigurationGui();
		
		JFrame jFrame = new JFrame();
		jFrame.setBounds(200, 150, 600, 450);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setVisible(true);
		jFrame.setLayout(new GridLayout());
		jFrame.add(gui);
	}


}
