/***********************************************************
* Developer: Minhas Kamal (minhaskamal024@gmail.com)       *
* Date: 22-Oct-2016                                        *
* License: MIT License                                     *
***********************************************************/

package com.minhaskamal.brailleToTextTranslator.processes.utils.commonUserInterfaces;

import java.awt.GridLayout;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;
import com.minhaskamal.brailleToTextTranslator.processes.*;
import com.minhaskamal.brailleToTextTranslator.processes.postProcessing.PostProcessingManager;
import com.minhaskamal.brailleToTextTranslator.processes.utils.fileSelection.FileAcquirer;
import com.minhaskamal.util.message.Message;

public class PostProcessingConfiguration extends UserInterface{

	//**
	// Variable Declaration 																	#*******D*******#
	//**
	private JCheckBox jCheckBoxSpellChecking;
	
	private JRadioButton jRadioButtonDefaultWordList, jRadioButtonCustomWordList;
	private FileAcquirer wordListFileChooser;
	// End of Variable Declaration 																#_______D_______#

	/***##Constructor##***/
	public PostProcessingConfiguration(UserInterface previousUserInterface) {
		super(previousUserInterface);

		initialComponent();
	}

	
	/**
	 * Method for Initializing all the GUI variables and placing them all to specific space on 
	 * the frame. It also specifies criteria of the main frame.
	 */
	private void initialComponent() {
		// GUI Initialization
		gui = new PostProcessingConfigurationGui();
		
		//**
		// Assignation 																			#*******A*******#
		//**
		jCheckBoxSpellChecking = ((PostProcessingConfigurationGui)gui).jCheckBoxSpellChecking;
		
		jRadioButtonDefaultWordList = ((PostProcessingConfigurationGui)gui).jRadioButtonDefaultWordList;
		jRadioButtonCustomWordList = ((PostProcessingConfigurationGui)gui).jRadioButtonCustomWordList;
		wordListFileChooser = new FileAcquirer(PostProcessingManager.ACCEPTABLE_WORD_LIST_FILE_EXTENSIONS);
		wordListFileChooser.attachTo(((PostProcessingConfigurationGui)gui).jLabelBrowseWordList);
		
		initialTwoButtons();
		// End of Assignation																	#_______A_______#

		//**
		// Adding Action Events & Other Attributes												#*******AA*******#
		//**
		jCheckBoxSpellChecking.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	enableSpellCheckingPanel(jCheckBoxSpellChecking.isSelected());
            }
        });
		jRadioButtonDefaultWordList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	wordListFileChooser.enable(false);
            }
        });
		jRadioButtonCustomWordList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	wordListFileChooser.enable(true);
            }
        });
		// End of Adding Action Events & Other Attributes										#_______AA_______#
		
		jRadioButtonDefaultWordList.setSelected(true);
		enableSpellCheckingPanel(false);
		wordListFileChooser.enable(false);
	}

	//**
	// Action Events 																			#*******AE*******#
	//**
	
	// End of Action Events 																	#_______AE_______#

	//**
	// Auxiliary Methods 																		#*******AM*******#
	//**
	private void enableSpellCheckingPanel(boolean bool){
		((PostProcessingConfigurationGui)gui).jLabelPromptWordListFile.setEnabled(bool);
		jRadioButtonDefaultWordList.setEnabled(bool);
		jRadioButtonCustomWordList.setEnabled(bool);
		
		if(jRadioButtonCustomWordList.isSelected()){
			wordListFileChooser.enable(bool);
    	}
	}
	protected String getWordListFilePath(){
		String wordListFilePath = "";
		
		if(jCheckBoxSpellChecking.isSelected()){
			if(jRadioButtonDefaultWordList.isSelected()){
				wordListFilePath = PostProcessingManager.DEFAULT_WORD_LIST_FILE_PATH;
			}else if(jRadioButtonCustomWordList.isSelected()){
				wordListFilePath = wordListFileChooser.getPath();
			}
		}
		return wordListFilePath;
	}
	// End of Auxiliary Methods 																#_______AM_______#
	
	//**
	// Overridden Methods 																		#*******OM*******#
	//**
	@Override
	protected void okAction(){
		if(jCheckBoxSpellChecking.isSelected() && getWordListFilePath().isEmpty()){
			new Message("Select custom word list.", Message.MESSAGE_TYPE_WARNING_MESSAGE);
		}else if(jCheckBoxSpellChecking.isSelected() && !new File(getWordListFilePath()).isFile()){
			new Message("Select valid word list file.", Message.MESSAGE_TYPE_WARNING_MESSAGE);
		}else{
			UserInput userInput = new UserInput(PostProcessingManager.POST_PROCESSING_CONFIGURATION_TYPE);
			userInput.addNewCommand(PostProcessingManager.PROCESS_USE_SPELLCHECKER, getWordListFilePath());
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
		
		new PostProcessingConfiguration(new OpeningUserInterface(jPanel)).attachToBoard();
	}

}
