/***********************************************************
* Developer: Minhas Kamal (minhaskamal024@gmail.com)       *
* Date: 22-Oct-2016                                        *
* License: MIT License                                     *
***********************************************************/

package com.minhaskamal.brailleToTextTranslator.processes.utils.commonUserInterfaces;

import java.awt.GridLayout;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

import com.minhaskamal.brailleToTextTranslator.processes.OpeningUserInterface;
import com.minhaskamal.brailleToTextTranslator.processes.UserInput;
import com.minhaskamal.brailleToTextTranslator.processes.UserInterface;
import com.minhaskamal.brailleToTextTranslator.processes.preProcessing.PreProcessingManager;

public class PreProcessingConfiguration extends UserInterface{

	protected ArrayList<PreProcessingMethod> preProcessingMethods;
	
	//**
	// Variable Declaration 																	#*******D*******#
	//**
	private JButton jButtonAddPreProcessingMethod;	
	// End of Variable Declaration 																#_______D_______#

	/***##Constructor##***/
	public PreProcessingConfiguration(UserInterface previousUserInterface) {
		super(previousUserInterface);
		
		initialComponent();
		preProcessingMethods= new ArrayList<>();
	}

	
	/**
	 * Method for Initializing all the GUI variables and placing them all to specific space on 
	 * the frame. It also specifies criteria of the main frame.
	 */
	private void initialComponent() {
		// GUI Initialization
		gui = new PreProcessingConfigurationGui();
		
		//**
		// Assignation 																			#*******A*******#
		//**
		jButtonAddPreProcessingMethod = ((PreProcessingConfigurationGui)gui).jButtonAddPreProcessingMethod;
		
		initialTwoButtons();
		// End of Assignation																	#_______A_______#

		//**
		// Adding Action Events & Other Attributes												#*******AA*******#
		//**
		jButtonAddPreProcessingMethod.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent evt) {
	        	jButtonAddPreProcessingMethodActionPerformed(evt);
	        }
	    });
		// End of Adding Action Events & Other Attributes										#_______AA_______#
	}

	//**
	// Action Events 																			#*******AE*******#
	//**
	private void jButtonAddPreProcessingMethodActionPerformed(ActionEvent evt){
		PreProcessingMethod preProcessingMethod = new PreProcessingMethod();
		preProcessingMethods.add(preProcessingMethod);
		preProcessingMethod.attachTo(((PreProcessingConfigurationGui)gui).jPanelPreProcessing);
	}
	
	// End of Action Events 																	#_______AE_______#

	//**
	// Auxiliary Methods 																		#*******AM*******#
	//**
	
	// End of Auxiliary Methods 																#_______AM_______#
	
	//**
	// Overridden Methods 																		#*******OM*******#
	//**
	@Override
	protected void okAction(){
		UserInput userInput = new UserInput(PreProcessingManager.PRE_PROCESSING_CONFIGURATION_TYPE);
		for(PreProcessingMethod preProcessingMethod: preProcessingMethods){
			if(preProcessingMethod.isActive()){				
				userInput.addNewCommand(preProcessingMethod.getProcess(), preProcessingMethod.getArgument());
			}
		}
		userSetting.add(userInput);
		
		remove();
		callFollowingUI();
	}
	
	protected void callFollowingUI(){
		
	}
	// End of Overridden Methods 																#_______OM_______#
	
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
		
		new PreProcessingConfiguration(new OpeningUserInterface(jPanel)).attachToBoard();
	}

}
