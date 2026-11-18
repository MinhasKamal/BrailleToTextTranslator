/***********************************************************
* Developer: Minhas Kamal (minhaskamal024@gmail.com)       *
* Date: 31-Oct-2016                                        *
* License: MIT License                                     *
***********************************************************/

package com.minhaskamal.brailleToTextTranslator.processes.templateGeneration.gui;

import java.awt.GridLayout;
import javax.swing.*;
import com.minhaskamal.brailleToTextTranslator.processes.*;
import com.minhaskamal.brailleToTextTranslator.processes.templateGeneration.TemplateGenerationManager;
import com.minhaskamal.brailleToTextTranslator.processes.utils.commonUserInterfaces.Finalizer;
import com.minhaskamal.brailleToTextTranslator.processes.utils.fileSelection.FileCreator;
import com.minhaskamal.util.message.Message;

public class TemplateGenerationFinalizer extends Finalizer{

	private FileCreator templateFileCreator;
	
	/***##Constructor##***/
	public TemplateGenerationFinalizer(UserInterface previousUserInterface) {
		super(previousUserInterface);

		initialComponent();
	}

	/**
	 * Method for Initializing all the GUI variables and placing them all to specific space on 
	 * the frame. It also specifies criteria of the main frame.
	 */
	private void initialComponent() {
		setPrompt("Select Output Braille Processing Template File");
		setTitle(title);
		
		templateFileCreator = new FileCreator(ProcessManager.ACCEPTABLE_TEMPLATE_FILE_EXTENSIONS);
		directoryCreator.unAttach();
		templateFileCreator.attachTo(jLabelBrowse);
	}
	
	@Override
	protected void okAction(){ 
		String argument = templateFileCreator.getPath();
		
		if(argument.length()<=1){
			new Message("Choose the output template file!", Message.MESSAGE_TYPE_WARNING_MESSAGE);
		}else{
			UserInput userInput = new UserInput(ProcessManager.FINALIZER_TYPE);
			userInput.addNewCommand(ProcessManager.PROCESS_WRITE, argument);
			userSetting.add(userInput);
			
			remove();
			runProcessManager();
		}
	}

	@Override
	protected void runProcessManager(){
		new TemplateGenerationManager(userSetting).run();
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
		JPanel jPanel = new JPanel(new GridLayout());
		
		JFrame jFrame = new JFrame();
		jFrame.setBounds(200, 150, 600, 450);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setVisible(true);
		jFrame.setLayout(new GridLayout());
		jFrame.add(jPanel);
		
		new TemplateGenerationFinalizer(new OpeningUserInterface(jPanel)).attachToBoard();
	}
}
