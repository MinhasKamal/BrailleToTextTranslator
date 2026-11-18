/***********************************************************
* Developer: Minhas Kamal (minhaskamal024@gmail.com)       *
* Date: 22-Oct-2016                                        *
* License: MIT License                                     *
***********************************************************/

package com.minhaskamal.brailleToTextTranslator.processes.utils.commonUserInterfaces;

import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;
import com.minhaskamal.brailleToTextTranslator.processes.*;
import com.minhaskamal.brailleToTextTranslator.processes.translation.TranslationManager;
import com.minhaskamal.brailleToTextTranslator.processes.utils.fileSelection.FileAcquirer;
import com.minhaskamal.util.message.Message;

public class TranslationConfiguration extends UserInterface{

	//**
	// Variable Declaration 																	#*******D*******#
	//**
	protected FileAcquirer mapFileChooser;
	private JSpinner jSpinnerSegmentationMinDotSize, jSpinnerSegmentationMaxDotSize;
	@SuppressWarnings("rawtypes")
	private JComboBox jComboBoxPatternRecognitionAlgorithm;
	private JRadioButton jRadioButtonDefaultCharacterMap, jRadioButtonCustomCharacterMap;
	// End of Variable Declaration 																#_______D_______#

	/***##Constructor##***/
	public TranslationConfiguration(UserInterface previousUserInterface) {
		super(previousUserInterface);
		
		initialComponent();
	}

	
	/**
	 * Method for Initializing all the GUI variables and placing them all to specific space on 
	 * the frame. It also specifies criteria of the main frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initialComponent() {
		// GUI Initialization
		gui = new TranslationConfigurationGui();
		
		//**
		// Assignation 																			#*******A*******#
		//**
		mapFileChooser = new FileAcquirer(TranslationManager.ACCEPTABLE_CODE_TO_CHARACTER_MAP_FILE_EXTENSIONS);
		mapFileChooser.attachTo(((TranslationConfigurationGui)gui).jLabelBrowseCharacterMap);
		
		jSpinnerSegmentationMinDotSize = ((TranslationConfigurationGui)gui).jSpinnerSegmentationMinDotSize;
		jSpinnerSegmentationMaxDotSize = ((TranslationConfigurationGui)gui).jSpinnerSegmentationMaxDotSize;
		jComboBoxPatternRecognitionAlgorithm = ((TranslationConfigurationGui)gui).jComboBoxPatternRecognitionAlgorithm;
		jRadioButtonDefaultCharacterMap = ((TranslationConfigurationGui)gui).jRadioButtonDefaultCharacterMap;
		jRadioButtonCustomCharacterMap = ((TranslationConfigurationGui)gui).jRadioButtonCustomCharacterMap;
		
		initialTwoButtons();
		// End of Assignation																	#_______A_______#

		//**
		// Adding Action Events & Other Attributes												#*******AA*******#
		//**
		jSpinnerSegmentationMinDotSize.setModel(new SpinnerNumberModel(5, 1, 50, 1));
		jSpinnerSegmentationMaxDotSize.setModel(new SpinnerNumberModel(8, 1, 50, 1));
		jComboBoxPatternRecognitionAlgorithm.setModel(new DefaultComboBoxModel(new String[]{
				TranslationManager.PATTERN_RECOGNITION_ALGORITHM_EMPTY,
				TranslationManager.PATTERN_RECOGNITION_ALGORITHM_ROTATION_INVARIENT}));
		
		jRadioButtonDefaultCharacterMap.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	mapFileChooser.enable(false);
            }
        });
		jRadioButtonCustomCharacterMap.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	mapFileChooser.enable(true);
            }
        });
		// End of Adding Action Events & Other Attributes										#_______AA_______#
		
		jRadioButtonDefaultCharacterMap.setSelected(true);
		mapFileChooser.enable(false);
	}

	//**
	// Action Events 																			#*******AE*******#
	//**
	
	// End of Action Events 																	#_______AE_______#

	//**
	// Auxiliary Methods 																		#*******AM*******#
	//**
	public boolean isValidDotSize(){
		boolean flag = true;
		
		int minDotSize = getSegmentationMinDotSize();
		int maxDotSize = getSegmentationMaxDotSize();
		if(minDotSize>maxDotSize){
			flag = false;
		}if(maxDotSize<1){
			flag = false;
		}
		
		return flag;
	}
	public boolean isPatternRecognitionAlgorithmSelected(){
		return !getPatternRecognitionAlgorithm().equals(TranslationManager.PATTERN_RECOGNITION_ALGORITHM_EMPTY);
	}
	public boolean isCodeToCharacterMapFileSelected(){
		return !getCodeToCharacterMapFile().isEmpty();
	}
	
	public int getSegmentationMinDotSize(){
		return (Integer) jSpinnerSegmentationMinDotSize.getValue();
	}
	public int getSegmentationMaxDotSize(){
		return (Integer) jSpinnerSegmentationMaxDotSize.getValue();
	}
	public String getSegmentationDotSize(){
		return getSegmentationMinDotSize()+","+getSegmentationMaxDotSize();
	}
	public String getPatternRecognitionAlgorithm(){
		return jComboBoxPatternRecognitionAlgorithm.getSelectedItem().toString();
	}
	public String getCodeToCharacterMapFile(){
		String codeToCharacterMapFile = "";
		
		if(jRadioButtonDefaultCharacterMap.isSelected()){
			codeToCharacterMapFile = TranslationManager.DEFAULT_MAP_FILE_PATH;
		}else if(jRadioButtonCustomCharacterMap.isSelected()){
			codeToCharacterMapFile = mapFileChooser.getPath();
		}
		
		return codeToCharacterMapFile;
	}
	// End of Auxiliary Methods 																#_______AM_______#
	
	//**
	// Overridden Methods 																		#*******OM*******#
	//**
	@Override
	protected void okAction(){
		if(!isValidDotSize()){
			new Message("Invalid dot size.", Message.MESSAGE_TYPE_WARNING_MESSAGE);
		}else if(!isPatternRecognitionAlgorithmSelected()){
			new Message("Select a pattern recognition algorithm.", Message.MESSAGE_TYPE_WARNING_MESSAGE);
		}else if(!isCodeToCharacterMapFileSelected()){
			new Message("Select a code to character map file.", Message.MESSAGE_TYPE_WARNING_MESSAGE);
		}else{
			UserInput userInput = new UserInput(TranslationManager.TRANSLATION_CONFIGURATION_TYPE);
			
			userInput.addNewCommand(TranslationManager.PROCESS_DOT_SEGMENTATION, getSegmentationDotSize());
			userInput.addNewCommand(TranslationManager.PROCESS_PATTERN_RECOGNITION, getPatternRecognitionAlgorithm());
			userInput.addNewCommand(TranslationManager.PROCESS_CODE_TO_CHARACTER_MAP, getCodeToCharacterMapFile());
			
			userSetting.add(userInput);
			
			remove();
			callFollowingUI();
		}
	}
	
	protected void callFollowingUI(){
		
	}
	// End of Overridden Methods 																#_______OM_______#
	
	
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
		
		new TranslationConfiguration(new OpeningUserInterface(jPanel)).attachToBoard();
	}

}
