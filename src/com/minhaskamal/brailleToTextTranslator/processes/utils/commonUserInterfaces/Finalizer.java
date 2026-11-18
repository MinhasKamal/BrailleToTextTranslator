/***********************************************************
* Developer: Minhas Kamal (minhaskamal024@gmail.com)       *
* Date: 26-Oct-2016                                        *
* License: MIT License                                     *
***********************************************************/

package com.minhaskamal.brailleToTextTranslator.processes.utils.commonUserInterfaces;

import java.awt.GridLayout;
import javax.swing.*;
import com.minhaskamal.brailleToTextTranslator.processes.*;
import com.minhaskamal.brailleToTextTranslator.processes.utils.fileSelection.DirectoryCreator;
import com.minhaskamal.util.message.Message;

public class Finalizer extends UserInterface{

	//**
	// Variable Declaration 																	#*******D*******#
	//**
	protected DirectoryCreator directoryCreator;
	protected JLabel jLabelBrowse;
	// End of Variable Declaration 																#_______D_______#

	/***##Constructor##***/
	public Finalizer(UserInterface previousUserInterface) {
		super(previousUserInterface);
		
		initialComponent();
	}

	
	/**
	 * Method for Initializing all the GUI variables and placing them all to specific space on 
	 * the frame. It also specifies criteria of the main frame.
	 */
	private void initialComponent() {
		// GUI Initialization
		gui = new FinalizerGui();
		
		//**
		// Assignation 																			#*******A*******#
		//**
		directoryCreator = new DirectoryCreator();
		jLabelBrowse = ((FinalizerGui)gui).jLabelBrowse;
		directoryCreator.attachTo(jLabelBrowse);
		
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
		((FinalizerGui)gui).jLabelPrompt.setText(string);
	}
	// End of Auxiliary Methods 																#_______AM_______#
	
	//**
	// Overridden Methods 																		#*******OM*******#
	//**
	@Override
	protected void okAction(){ 
		String argument = directoryCreator.getPath();
		
		if(argument.length()<1){
			new Message("Choose the output text directory!", Message.MESSAGE_TYPE_WARNING_MESSAGE);
		}else{
			UserInput userInput = new UserInput(ProcessManager.FINALIZER_TYPE);
			userInput.addNewCommand(ProcessManager.PROCESS_WRITE, argument);
			userSetting.add(userInput);
			
			remove();
			runProcessManager();
		}
	}
	
	protected void runProcessManager(){
		
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
		
		new Finalizer(new OpeningUserInterface(jPanel, "ok")).attachToBoard();
	}

}
