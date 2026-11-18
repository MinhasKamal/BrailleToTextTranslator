/***********************************************************
* Developer: Minhas Kamal (minhaskamal024@gmail.com)       *
* Date: 24-Oct-2016                                        *
* License: MIT License                                     *
***********************************************************/

package com.minhaskamal.brailleToTextTranslator.processes.utils.commonUserInterfaces;

import java.awt.GridLayout;
import java.io.File;

import javax.swing.*;
import com.minhaskamal.brailleToTextTranslator.processes.*;
import com.minhaskamal.brailleToTextTranslator.processes.utils.fileSelection.DirectoryAcquirer;
import com.minhaskamal.util.message.Message;

public class Initializer extends UserInterface{

	//**
	// Variable Declaration 																	#*******D*******#
	//**
	protected DirectoryAcquirer directoryChooser;
	JLabel jLabelBrowse;
	// End of Variable Declaration 																#_______D_______#

	/***##Constructor##***/
	public Initializer(UserInterface previousUserInterface) {
		super(previousUserInterface);
		
		initialComponent();
	}

	
	/**
	 * Method for Initializing all the GUI variables and placing them all to specific space on 
	 * the frame. It also specifies criteria of the main frame.
	 */
	private void initialComponent() {
		// GUI Initialization
		gui = new InitializerGui();
		
		//**
		// Assignation 																			#*******A*******#
		//**
		directoryChooser = new DirectoryAcquirer();
		jLabelBrowse = ((InitializerGui)gui).jLabelBrowse;
		directoryChooser.attachTo(jLabelBrowse);
		
		initialTwoButtons();
		// End of Assignation																	#_______A_______#

		//**
		// Adding Action Events & Other Attributes												#*******AA*******#
		//**
	
		// End of Adding Action Events & Other Attributes										#_______AA_______#
	}

	//**
	// Action Events 																			#*******AE*******#
	//**
	
	// End of Action Events 																	#_______AE_______#

	//**
	// Auxiliary Methods 																		#*******AM*******#
	//**
	
	protected void setPrompt(String string){
		((InitializerGui)gui).jLabelPrompt.setText(string);
	}
	
	// End of Auxiliary Methods 																#_______AM_______#
	
	//**
	// Overridden Methods 																		#*******OM*******#
	//**
	@Override
	protected void okAction(){
		String argument = directoryChooser.getPath();

		if(argument.length()<1){
			new Message("Choose the input image directory!", Message.MESSAGE_TYPE_WARNING_MESSAGE);
		}else if(!new File(argument).isDirectory()){
			new Message("Choose valid image directory!", Message.MESSAGE_TYPE_WARNING_MESSAGE);
		}else{
			UserInput userInput = new UserInput(ProcessManager.INITIALIZER_TYPE);
			userInput.addNewCommand(ProcessManager.PROCESS_READ, argument);
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
		
		new Initializer(new OpeningUserInterface(jPanel)).attachToBoard();
	}

}
