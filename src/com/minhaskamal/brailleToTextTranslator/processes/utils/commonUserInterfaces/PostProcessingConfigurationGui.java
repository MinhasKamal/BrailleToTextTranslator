/***********************************************************
* Developer: Minhas Kamal (minhaskamal024@gmail.com)       *
* Date: 22-Oct-2016                                        *
* License: MIT License                                     *
***********************************************************/

package com.minhaskamal.brailleToTextTranslator.processes.utils.commonUserInterfaces;

import java.awt.*;
import javax.swing.*;
import com.minhaskamal.brailleToTextTranslator.processes.UserInterfaceGui;

@SuppressWarnings("serial")
public class PostProcessingConfigurationGui extends UserInterfaceGui{

	//**
	// Variable Declaration 																	#*******D*******#
	//**
	JLabel jLabelPrompt;
	
	JCheckBox jCheckBoxSpellChecking;
	JPanel jPanelSpellChecking;
	
	JLabel jLabelPromptWordListFile;
	ButtonGroup buttonGroup;
	JRadioButton jRadioButtonDefaultWordList, jRadioButtonCustomWordList;
	JPanel jPanelBrowseWordList;
	JLabel jLabelBrowseWordList;
	// End of Variable Declaration 																#_______D_______#

	/***##Constructor##***/
	public PostProcessingConfigurationGui() {

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
		jLabelPrompt = new JLabel("Configure Post-Pressing Procedure");
		
		jCheckBoxSpellChecking = new JCheckBox("Spell Checking");
		jPanelSpellChecking = new JPanel();
		
		jLabelPromptWordListFile = new JLabel("Select Domain Word List File");
		buttonGroup = new ButtonGroup();
		jRadioButtonDefaultWordList = new JRadioButton("Use Default  Bengali Word List");
		jRadioButtonCustomWordList = new JRadioButton("Use Custom Word List");
		jPanelBrowseWordList = new JPanel();
		jLabelBrowseWordList = new JLabel();
		// End of Initialization																#_______I_______#

		//**
		// Setting Bounds and Attributes of the Elements 										#*******S*******#
		//**
		int panelHeight = 200;
		int ypos = 50;
		
		
		jLabelPrompt.setBounds(xMargin, ypos, componantWidth, componantHeight);
		
		ypos += componantHeight+gapLarge;
		jCheckBoxSpellChecking.setBounds(xMargin, ypos, componantWidth, componantHeight);
		ypos += componantHeight+gapSmall;
		jPanelSpellChecking.setBounds(xMargin, ypos, componantWidth, panelHeight);
		jPanelSpellChecking.setBackground(new Color(230, 230, 230));
		jPanelSpellChecking.setLayout(null);

		int SplChckCompWidth = componantWidth-2*xMargin;
		jLabelPromptWordListFile.setBounds(xMargin, gapLarge, SplChckCompWidth, componantHeight);
		int SplChckYpos = gapLarge+componantHeight;
		jRadioButtonDefaultWordList.setBounds(xMargin, SplChckYpos, SplChckCompWidth/2, componantHeight);
		jRadioButtonCustomWordList.setBounds(xMargin+SplChckCompWidth/2, SplChckYpos, SplChckCompWidth/2, componantHeight);
		SplChckYpos += componantHeight+gapSmall;
		jPanelBrowseWordList.setBounds(xMargin+SplChckCompWidth/2, SplChckYpos, SplChckCompWidth/2, componantHeight+2*gapSmall);
		jPanelBrowseWordList.setBackground(new Color(200, 200, 200));
		jPanelBrowseWordList.setLayout(null);
		jLabelBrowseWordList.setBounds(gapSmall, gapSmall, SplChckCompWidth/2-2*gapSmall, componantHeight);
		jLabelBrowseWordList.setLayout(new GridLayout());
		
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
		
		add(jCheckBoxSpellChecking);
		add(jPanelSpellChecking);
		
		jPanelSpellChecking.add(jLabelPromptWordListFile);
		jPanelSpellChecking.add(jRadioButtonDefaultWordList);
		jPanelSpellChecking.add(jRadioButtonCustomWordList);
		jPanelSpellChecking.add(jPanelBrowseWordList);
		jPanelBrowseWordList.add(jLabelBrowseWordList);
		
		buttonGroup.add(jRadioButtonDefaultWordList);
		buttonGroup.add(jRadioButtonCustomWordList);
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
		PostProcessingConfigurationGui gui = new PostProcessingConfigurationGui();
		
		JFrame jFrame = new JFrame();
		jFrame.setBounds(200, 150, 600, 450);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setVisible(true);
		jFrame.setLayout(new GridLayout());
		jFrame.add(gui);
	}


}
