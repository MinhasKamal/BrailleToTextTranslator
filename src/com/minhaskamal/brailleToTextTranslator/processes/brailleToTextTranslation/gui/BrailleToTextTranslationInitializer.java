/***********************************************************
* Developer: Minhas Kamal (minhaskamal024@gmail.com)       *
* Date: 20-Oct-2016                                        *
* License: MIT License                                     *
***********************************************************/

package com.minhaskamal.brailleToTextTranslator.processes.brailleToTextTranslation.gui;

import java.awt.GridLayout;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.minhaskamal.brailleToTextTranslator.processes.*;
import com.minhaskamal.brailleToTextTranslator.processes.brailleToTextTranslation.BrailleToTextTranslationManager;
import com.minhaskamal.brailleToTextTranslator.processes.utils.fileSelection.*;
import com.minhaskamal.util.message.Message;

public class BrailleToTextTranslationInitializer extends UserInterface{

	//**
	// Variable Declaration 																	#*******D*******#
	//**
	private JRadioButton jRadioButtonUseTemplate, jRadioButtonCustomize;
	private DirectoryAcquirer directoryChooser;
	
	private JRadioButton jRadioButtonDefaultTemplate, jRadioButtonCustomTemplate;
	private FileAcquirer templateFileChooser;
	// End of Variable Declaration 																#_______D_______#

	/***##Constructor##***/
	public BrailleToTextTranslationInitializer(UserInterface previousUserInterface) {
		super(previousUserInterface);
		
		initialComponent();
	}

	
	/**
	 * Method for Initializing all the GUI variables and placing them all to specific space on 
	 * the frame. It also specifies criteria of the main frame.
	 */
	private void initialComponent() {
		// GUI Initialization
		gui = new BrailleToTextTranslationInitializerGui();
		
		//**
		// Assignation 																			#*******A*******#
		//**
		jRadioButtonUseTemplate = ((BrailleToTextTranslationInitializerGui)gui).jRadioButtonUseTemplate;
		jRadioButtonCustomize = ((BrailleToTextTranslationInitializerGui)gui).jRadioButtonCustomize;
		directoryChooser = new DirectoryAcquirer(BrailleToTextTranslationManager.ACCEPTABLE_IMAGE_EXTENSIONS);
		directoryChooser.attachTo(((BrailleToTextTranslationInitializerGui)gui).jLabelBrowseBraillImage);
		
		jRadioButtonDefaultTemplate = ((BrailleToTextTranslationInitializerGui)gui).jRadioButtonDefaultTemplate;
		jRadioButtonCustomTemplate = ((BrailleToTextTranslationInitializerGui)gui).jRadioButtonCustomTemplate;
		templateFileChooser = new FileAcquirer(BrailleToTextTranslationManager.ACCEPTABLE_TEMPLATE_FILE_EXTENSIONS);
		templateFileChooser.attachTo(((BrailleToTextTranslationInitializerGui)gui).jLabelBrowseTemplate);
		
		initialTwoButtons();
		// End of Assignation																	#_______A_______#

		//**
		// Adding Action Events & Other Attributes												#*******AA*******#
		//**
		jRadioButtonUseTemplate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	enableTemplateChoicePanel(true);
            }
        });
		jRadioButtonCustomize.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	enableTemplateChoicePanel(false);
            }
        });
		jRadioButtonDefaultTemplate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	templateFileChooser.enable(false);
            }
        });
		jRadioButtonCustomTemplate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	templateFileChooser.enable(true);
            }
        });
		// End of Adding Action Events & Other Attributes										#_______AA_______#
	
		
		jRadioButtonUseTemplate.setSelected(true);
		jRadioButtonDefaultTemplate.setSelected(true);
		templateFileChooser.enable(false);
		setTitle(title);
	}

	//**
	// Action Events 																			#*******AE*******#
	//**
	
	// End of Action Events 																	#_______AE_______#

	//**
	// Auxiliary Methods 																		#*******AM*******#
	//**
	private void enableTemplateChoicePanel(boolean bool){
		((BrailleToTextTranslationInitializerGui)gui).jLabelTemplateSelectionPrompt.setEnabled(bool);
		jRadioButtonDefaultTemplate.setEnabled(bool);
    	jRadioButtonCustomTemplate.setEnabled(bool);
    	
    	if(jRadioButtonCustomTemplate.isSelected()){
    		templateFileChooser.enable(bool);
    	}
	}
	// End of Auxiliary Methods 																#_______AM_______#
	
	//**
	// Overridden Methods 																		#*******OM*******#
	//**
	@Override
	protected void okAction(){
		String argument = directoryChooser.getPath();
		
		if(argument.length()<1){
			new Message("Choose the input image directory.", Message.MESSAGE_TYPE_WARNING_MESSAGE);
		}else if(!new File(argument).isDirectory()){
			new Message("Choose valid image directory.", Message.MESSAGE_TYPE_WARNING_MESSAGE);
		}else{
			if(jRadioButtonCustomize.isSelected()){
				UserInput userInput = new UserInput(ProcessManager.INITIALIZER_TYPE);
				userInput.addNewCommand(ProcessManager.PROCESS_READ, argument);
				userSetting.add(userInput);
				
				remove();
				new BrailleToTextTranslation_PreProcessingConfiguration(this).attachToBoard();
			}else{
				String templateFilePath = BrailleToTextTranslationManager.DEFAULT_TEMPLATE_FILE_PATH;
				if(jRadioButtonCustomTemplate.isSelected()){
					templateFilePath = templateFileChooser.getPath();
				}
				
				if(jRadioButtonCustomTemplate.isSelected() && templateFilePath.length()<1){
					new Message("Choose the braille processing \ntemplate file.", Message.MESSAGE_TYPE_WARNING_MESSAGE);
				}else if(jRadioButtonCustomTemplate.isSelected() && !new File(templateFilePath).isFile()){
					new Message("Choose valid template file.", Message.MESSAGE_TYPE_WARNING_MESSAGE);
				}else{
					UserInput userInput = new UserInput(ProcessManager.INITIALIZER_TYPE);
					userInput.addNewCommand(ProcessManager.PROCESS_READ, argument);
					userSetting.add(userInput);
					
					try{
						Document document = DocumentBuilderFactory.newInstance().
								newDocumentBuilder().parse(new File(templateFilePath));
						NodeList nodeList = document.getElementsByTagName(UserSetting.USER_SETTING_TAG);
						Node userSettingNode = nodeList.item(0);
						UserSetting templateSetting = new UserSetting(userSettingNode);
						
						userSetting.add(templateSetting.getUserInput(0));
						userSetting.add(templateSetting.getUserInput(1));
						userSetting.add(templateSetting.getUserInput(2));
					}catch(Exception e){
						new Message("Error!", Message.MESSAGE_TYPE_ERROR_MESSAGE);
					}
					
					remove();
					new BrailleToTextTranslationFinalizer(this).attachToBoard();
				}
			}
		}
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
		
		new BrailleToTextTranslationInitializer(new OpeningUserInterface(jPanel)).attachToBoard();
	}

}
