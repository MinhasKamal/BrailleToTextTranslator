/***********************************************************
* Developer: Minhas Kamal (minhaskamal024@gmail.com)       *
* Date: 22-Oct-2016                                        *
* License: MIT License                                     *
***********************************************************/

package com.minhaskamal.brailleToTextTranslator.processes.utils.commonUserInterfaces;

import java.awt.*;
import javax.swing.*;
import javax.swing.JSpinner.DefaultEditor;
import com.minhaskamal.brailleToTextTranslator.processes.UserInterfaceGui;

@SuppressWarnings("serial")
public class TranslationConfigurationGui extends UserInterfaceGui{

	//**
	// Variable Declaration 																	#*******D*******#
	//**
	JLabel jLabelPrompt;
	JPanel jPanelTransLation;
	
	JLabel jLabelSegmentationPrompt, jLabelPatternRecognitionPrompt, jLabelCharacterMapPrompt;
	
	JLabel jLabelSegmentationMinDotSize, jLabelSegmentationMaxDotSize;
	JSpinner jSpinnerSegmentationMinDotSize, jSpinnerSegmentationMaxDotSize;
	
	@SuppressWarnings("rawtypes")
	JComboBox jComboBoxPatternRecognitionAlgorithm;
	
	ButtonGroup buttonGroup;
	JRadioButton jRadioButtonDefaultCharacterMap, jRadioButtonCustomCharacterMap;
	JPanel jPanelBrowseCharacterMap;
	JLabel jLabelBrowseCharacterMap;
	// End of Variable Declaration 																#_______D_______#

	/***##Constructor##***/
	public TranslationConfigurationGui() {

		initialComponent();
	}

	
	/**
	 * Method for Initializing all the GUI variables and placing them all to specific space on 
	 * the component. It also specifies criteria of the main component.
	 */
	@SuppressWarnings({ "rawtypes" })
	private void initialComponent() {
		//**
		// Initialization 																		#*******I*******#
		//**
		jLabelPrompt = new JLabel("Configure Translation Procedure");
		jPanelTransLation = new JPanel();
		
		jLabelSegmentationPrompt = new JLabel("Customize Segmentation Process");
		jLabelPatternRecognitionPrompt = new JLabel("Select Pattern Recognition Algorithm");
		jLabelCharacterMapPrompt = new JLabel("Select Code to Character Map File");
		
		jLabelSegmentationMinDotSize = new JLabel("Min Dot Size:");
		jLabelSegmentationMaxDotSize = new JLabel("Max Dot Size:");
		jSpinnerSegmentationMinDotSize = new JSpinner();
		jSpinnerSegmentationMaxDotSize = new JSpinner();
		
		jComboBoxPatternRecognitionAlgorithm = new JComboBox();
		
		buttonGroup = new ButtonGroup();
		jRadioButtonDefaultCharacterMap = new JRadioButton("Use Default  Bengali Map File");
		jRadioButtonCustomCharacterMap = new JRadioButton("Use Custom Map File");
		jPanelBrowseCharacterMap = new JPanel();
		jLabelBrowseCharacterMap = new JLabel();
		// End of Initialization																#_______I_______#

		//**
		// Setting Bounds and Attributes of the Elements 										#*******S*******#
		//**
		this.gapLarge = 12;
		int panelHeight = 260;
		int ypos = 50;
		
		
		jLabelPrompt.setBounds(xMargin, ypos, componantWidth, componantHeight);
		
		ypos += componantHeight+gapSmall;
		jPanelTransLation.setBounds(xMargin, ypos, componantWidth, panelHeight);
		jPanelTransLation.setBackground(new Color(230, 230, 230));
		jPanelTransLation.setLayout(null);
		
		
		int translationYpos = gapSmall;
		int translationCompWidth = componantWidth-2*xMargin;
		
		jLabelSegmentationPrompt.setBounds(xMargin, translationYpos, translationCompWidth, componantHeight);
		
		translationYpos += componantHeight;
		int segmentationWidth = translationCompWidth/4-xMargin;
		jLabelSegmentationMinDotSize.
			setBounds(xMargin, translationYpos, segmentationWidth, componantHeight);
		jSpinnerSegmentationMinDotSize.
			setBounds(2*xMargin+segmentationWidth, translationYpos, segmentationWidth, componantHeight);
		((DefaultEditor) jSpinnerSegmentationMaxDotSize.getEditor()).getTextField().setEditable(false);
		jLabelSegmentationMaxDotSize.
			setBounds(3*xMargin+segmentationWidth*2, translationYpos, segmentationWidth, componantHeight);
		jSpinnerSegmentationMaxDotSize.
			setBounds(4*xMargin+segmentationWidth*3, translationYpos, segmentationWidth, componantHeight);
		((DefaultEditor) jSpinnerSegmentationMinDotSize.getEditor()).getTextField().setEditable(false);
		
		
		translationYpos += componantHeight+gapLarge;
		jLabelPatternRecognitionPrompt.setBounds(xMargin, translationYpos, translationCompWidth, componantHeight);
		
		translationYpos += componantHeight;
		jComboBoxPatternRecognitionAlgorithm.setBounds(xMargin, translationYpos, translationCompWidth, componantHeight);
		
		
		translationYpos += componantHeight+gapLarge;
		jLabelCharacterMapPrompt.setBounds(xMargin, translationYpos, translationCompWidth, componantHeight);
		
		translationYpos += componantHeight;
		jRadioButtonDefaultCharacterMap.
			setBounds(xMargin, translationYpos, translationCompWidth/2, componantHeight);
		jRadioButtonCustomCharacterMap.
			setBounds(xMargin+translationCompWidth/2, translationYpos, translationCompWidth/2, componantHeight);
		translationYpos += componantHeight;
		jPanelBrowseCharacterMap.
			setBounds(xMargin+translationCompWidth/2, translationYpos, translationCompWidth/2, componantHeight+2*gapSmall);
		jPanelBrowseCharacterMap.setBackground(new Color(200, 200, 200));
		jPanelBrowseCharacterMap.setLayout(null);
		jLabelBrowseCharacterMap.setBounds(gapSmall, gapSmall, translationCompWidth/2-gapSmall*2, componantHeight);
		jLabelBrowseCharacterMap.setLayout(new GridLayout());
		
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
		
		add(jPanelTransLation);
		jPanelTransLation.add(jLabelSegmentationPrompt);
		jPanelTransLation.add(jLabelPatternRecognitionPrompt);
		jPanelTransLation.add(jLabelCharacterMapPrompt);
		
		
		jPanelTransLation.add(jLabelSegmentationMinDotSize);
		jPanelTransLation.add(jLabelSegmentationMaxDotSize);
		jPanelTransLation.add(jSpinnerSegmentationMinDotSize);
		jPanelTransLation.add(jSpinnerSegmentationMaxDotSize);
		
		jPanelTransLation.add(jComboBoxPatternRecognitionAlgorithm);
		
		jPanelTransLation.add(jRadioButtonDefaultCharacterMap);
		jPanelTransLation.add(jRadioButtonCustomCharacterMap);
		jPanelTransLation.add(jPanelBrowseCharacterMap);
		jPanelBrowseCharacterMap.add(jLabelBrowseCharacterMap);
		
		buttonGroup.add(jRadioButtonDefaultCharacterMap);
		buttonGroup.add(jRadioButtonCustomCharacterMap);
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
		TranslationConfigurationGui gui = new TranslationConfigurationGui();
		
		JFrame jFrame = new JFrame();
		jFrame.setBounds(200, 150, 600, 450);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setVisible(true);
		jFrame.setLayout(new GridLayout());
		jFrame.add(gui);
	}

}
