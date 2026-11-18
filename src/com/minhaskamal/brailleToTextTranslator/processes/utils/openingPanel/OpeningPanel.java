/***********************************************************
* Developer: Minhas Kamal (minhaskamal024@gmail.com)       *
* Date: 22-Oct-2016                                        *
* License: MIT License                                     *
***********************************************************/

package com.minhaskamal.brailleToTextTranslator.processes.utils.openingPanel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import com.minhaskamal.brailleToTextTranslator.processes.OpeningUserInterface;
import com.minhaskamal.brailleToTextTranslator.processes.ProcessManager;
import com.minhaskamal.brailleToTextTranslator.processes.brailleToTextTranslation.gui.BrailleToTextTranslationInitializer;
import com.minhaskamal.brailleToTextTranslator.processes.templateGeneration.gui.TemplateGenerationPreProcessingConfiguration;
import com.minhaskamal.help.instruction.Instruction;

public class OpeningPanel {

	private static OpeningPanel openingPanel = null;
	private JComponent board;
	
	// GUI Declaration
	private OpeningPanelGui gui;
	
	//**
	// Variable Declaration 																	#*******D*******#
	//**
	private JButton jButtonBrailleToTextTranslation, jButtonTemplateGeneration, jButtonInstruction;
	// End of Variable Declaration 																#_______D_______#

	/***##Constructor##***/
	private OpeningPanel() {

		initialComponent();
		board = null;
	}

	
	/**
	 * Method for Initializing all the GUI variables and placing them all to specific space on 
	 * the frame. It also specifies criteria of the main frame.
	 */
	private void initialComponent() {
		// GUI Initialization
		gui = new OpeningPanelGui();
		
		//**
		// Assignation 																			#*******A*******#
		//**
		jButtonBrailleToTextTranslation = gui.jButtonBrailleToTextTranslation;
		jButtonTemplateGeneration = gui.jButtonTemplateGeneration;
		jButtonInstruction = gui.jButtonInstruction;
		// End of Assignation																	#_______A_______#

		//**
		// Adding Action Events & Other Attributes												#*******AA*******#
		//**
		jButtonBrailleToTextTranslation.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	brailleToTextTranslationActionPerformed(evt);
            }
        });
		jButtonTemplateGeneration.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	templateGenerationActionPerformed(evt);
            }
        });
		jButtonInstruction.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	new Instruction();
            }
        });
		// End of Adding Action Events & Other Attributes										#_______AA_______#
	}

	//**
	// Action Events 																			#*******AE*******#
	//**
	private void brailleToTextTranslationActionPerformed(ActionEvent evt) {
		clearBoard();
    	new BrailleToTextTranslationInitializer(
    			new OpeningUserInterface(board, ProcessManager.TITLE_BRAILLE_TO_TEXT_GENERATION))
    			.attachToBoard();
	}
	private void templateGenerationActionPerformed(ActionEvent evt) {
		clearBoard();
    	new TemplateGenerationPreProcessingConfiguration(
    			new OpeningUserInterface(board,  ProcessManager.TITLE_TEMPLATE_GENERATION))
    			.attachToBoard();
	}
	// End of Action Events 																	#_______AE_______#

	//**
	// Auxiliary Methods 																		#*******AM*******#
	//**
	public void attachTo(JComponent jComponent){
		board = jComponent;
		attachToBoard();
	}
	
	public void attachToBoard(){
		if(board!=null){
			board.add(gui);
			board.revalidate();
		}
	}
	
	private void clearBoard(){
		board.removeAll();
		board.repaint();
	}
	
	public static synchronized OpeningPanel getOpeningPanel(){
		if(openingPanel==null){
			openingPanel = new OpeningPanel();
		}
		
		return openingPanel;
	}
	// End of Auxiliary Methods 																#_______AM_______#
	
	//**
	// Overridden Methods 																		#*******OM*******#
	//**
	
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
		OpeningPanel opt = new OpeningPanel();
		
		JFrame jFrame = new JFrame();
		jFrame.setBounds(200, 150, 600, 450);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setVisible(true);
		jFrame.setLayout(new GridLayout());
		jFrame.add(opt.gui);
	}

}
