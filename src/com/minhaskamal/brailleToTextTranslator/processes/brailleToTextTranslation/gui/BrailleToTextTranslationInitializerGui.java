/***********************************************************
* Developer: Minhas Kamal (minhaskamal024@gmail.com)       *
* Date: 20-Oct-2016                                        *
* License: MIT License                                     *
***********************************************************/

package com.minhaskamal.brailleToTextTranslator.processes.brailleToTextTranslation.gui;

import java.awt.*;
import javax.swing.*;
import com.minhaskamal.brailleToTextTranslator.processes.UserInterfaceGui;

@SuppressWarnings("serial")
public class BrailleToTextTranslationInitializerGui extends UserInterfaceGui{

	//**
	// Variable Declaration 																	#*******D*******#
	//**
	JLabel jLabelBraillImagePrompt;
	JLabel jLabelBrowseBraillImage;
	ButtonGroup buttonGroup1;
	JRadioButton jRadioButtonUseTemplate, jRadioButtonCustomize;
	JPanel jPanelTemplateSelection;
	
	JLabel jLabelTemplateSelectionPrompt;
	ButtonGroup buttonGroup2;
	JRadioButton jRadioButtonDefaultTemplate, jRadioButtonCustomTemplate;
	JPanel jPanelBrowseTemplate;
	JLabel jLabelBrowseTemplate;
	// End of Variable Declaration 																#_______D_______#

	/***##Constructor##***/
	public BrailleToTextTranslationInitializerGui() {

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
		jLabelBraillImagePrompt = new JLabel("Select Input Braille Image Directory");
		jLabelBrowseBraillImage = new JLabel();
		buttonGroup1 = new ButtonGroup();
		jRadioButtonUseTemplate = new JRadioButton("Use Template");
		jRadioButtonCustomize = new JRadioButton("Customize");
		jPanelTemplateSelection = new JPanel();
		
		jLabelTemplateSelectionPrompt = new JLabel("Select Braille Processing Template");
		buttonGroup2 = new ButtonGroup();
		jRadioButtonDefaultTemplate = new JRadioButton("Use Default Template");
		jRadioButtonCustomTemplate = new JRadioButton("Use Custom Template");
		jPanelBrowseTemplate = new JPanel();
		jLabelBrowseTemplate = new JLabel();
		// End of Initialization																#_______I_______#

		//**
		// Setting Bounds and Attributes of the Elements 										#*******S*******#
		//**
		int panelHeight = 165;
		int ypos = 50;
		

		jLabelBraillImagePrompt.setBounds(xMargin, ypos, componantWidth, componantHeight);
		ypos += componantHeight+gapSmall;
		jLabelBrowseBraillImage.setBounds(xMargin, ypos, componantWidth, componantHeight);
		jLabelBrowseBraillImage.setLayout(new GridLayout());
		
		ypos += componantHeight+gapLarge;
		int templatePanelWidth = componantWidth/2;
		jRadioButtonUseTemplate.setBounds(xMargin, ypos, templatePanelWidth, componantHeight);
		jRadioButtonCustomize.setBounds(xMargin+templatePanelWidth, ypos, templatePanelWidth, componantHeight);
		ypos += componantHeight+gapSmall;
		jPanelTemplateSelection.setBounds(xMargin, ypos, templatePanelWidth, panelHeight);
		jPanelTemplateSelection.setBackground(new Color(230, 230, 230));
		jPanelTemplateSelection.setLayout(null);
		
		int templatePanelYpos = gapSmall;
		int templatePanelCompWidth = templatePanelWidth-2*xMargin;
		jLabelTemplateSelectionPrompt.setBounds(xMargin, templatePanelYpos, templatePanelCompWidth, componantHeight);
		templatePanelYpos += componantHeight+gapSmall;
		jRadioButtonDefaultTemplate.setBounds(xMargin, templatePanelYpos, templatePanelCompWidth, componantHeight);
		templatePanelYpos += componantHeight+gapSmall;
		jRadioButtonCustomTemplate.setBounds(xMargin, templatePanelYpos, templatePanelCompWidth, componantHeight);
		templatePanelYpos += componantHeight+gapSmall;
		jPanelBrowseTemplate.setBounds(xMargin, templatePanelYpos, templatePanelCompWidth, componantHeight+2*gapSmall);
		jPanelBrowseTemplate.setBackground(new Color(200, 200, 200));
		jPanelBrowseTemplate.setLayout(null);
		jLabelBrowseTemplate.setBounds(gapSmall, gapSmall, templatePanelCompWidth-2*gapSmall, componantHeight);
		jLabelBrowseTemplate.setLayout(new GridLayout());
		
		jButtonCancel.setText("Cancel");
		jButtonOk.setText("Next >");
		// End of Setting Bounds and Attributes 												#_______S_______#
		
		//**Setting Criterion of the Label**//
		//setBorder(BorderFactory.createDashedBorder(null));
		setLayout(null);

		//**
		// Adding Components 																	#*******A*******#
		//**
		add(jLabelBraillImagePrompt);
		add(jLabelBrowseBraillImage);
		add(jRadioButtonUseTemplate);
		add(jRadioButtonCustomize);
		add(jPanelTemplateSelection);
		
		buttonGroup1.add(jRadioButtonUseTemplate);
		buttonGroup1.add(jRadioButtonCustomize);
		
		jPanelTemplateSelection.add(jLabelTemplateSelectionPrompt);
		jPanelTemplateSelection.add(jRadioButtonDefaultTemplate);
		jPanelTemplateSelection.add(jRadioButtonCustomTemplate);
		jPanelTemplateSelection.add(jPanelBrowseTemplate);
		jPanelBrowseTemplate.add(jLabelBrowseTemplate);
		
		buttonGroup2.add(jRadioButtonDefaultTemplate);
		buttonGroup2.add(jRadioButtonCustomTemplate);
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
		BrailleToTextTranslationInitializerGui gui = new BrailleToTextTranslationInitializerGui();
		
		JFrame jFrame = new JFrame();
		jFrame.setBounds(200, 150, 600, 450);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setVisible(true);
		jFrame.setLayout(new GridLayout());
		jFrame.add(gui);
	}


}
