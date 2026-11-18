/***********************************************************
* Developer: Minhas Kamal (minhaskamal024@gmail.com)       *
* Date: 20-Oct-2016                                        *
* License: MIT License                                     *
***********************************************************/

package com.minhaskamal.brailleToTextTranslator;

import java.awt.event.*;

import javax.swing.*;
import com.minhaskamal.brailleToTextTranslator.processes.OpeningUserInterface;
import com.minhaskamal.brailleToTextTranslator.processes.ProcessManager;
import com.minhaskamal.brailleToTextTranslator.processes.brailleToTextTranslation.gui.BrailleToTextTranslationInitializer;
import com.minhaskamal.brailleToTextTranslator.processes.postProcessing.gui.PostProcessingInitializer;
import com.minhaskamal.brailleToTextTranslator.processes.preProcessing.gui.PreProcessingInitializer;
import com.minhaskamal.brailleToTextTranslator.processes.templateGeneration.gui.TemplateGenerationPreProcessingConfiguration;
import com.minhaskamal.brailleToTextTranslator.processes.translation.gui.TranslationInitializer;
import com.minhaskamal.brailleToTextTranslator.processes.utils.openingPanel.OpeningPanel;
import com.minhaskamal.help.about.About;
import com.minhaskamal.help.instruction.Instruction;
import com.minhaskamal.util.fileReadWrite.FileIO;

public class MainFrame {

	// GUI Declaration
	private MainFrameGui gui;
	
	//**
	// Variable Declaration 																	#*******D*******#
	//**
	private JMenuItem jMenuItemBraillToTextTranslation,
		jMenuItemPreProcessing, jMenuItemTranslation, jMenuItemPostProcessing,
		jMenuItemTemplateGeneration;
	private JMenuItem jMenuItemInstruction, jMenuItemAbout;
	
	private JPanel jPanelBoard;
	
	//other variables
	// End of Variable Declaration 																#_______D_______#

	/***##Constructor##***/
	public MainFrame() {
		
		initialComponent();
		OpeningPanel.getOpeningPanel().attachTo(jPanelBoard);
	}

	
	/**
	 * Method for Initializing all the GUI variables and placing them all to specific space on 
	 * the frame. It also specifies criteria of the main frame.
	 */
	private void initialComponent() {
		// GUI Initialization
		gui = new MainFrameGui();
		gui.setVisible(true);
		
		//**
		// Assignation 																			#*******A*******#
		//**
		jMenuItemBraillToTextTranslation = gui.jMenuItemBraillToTextTranslation;
		jMenuItemPreProcessing = gui.jMenuItemPreProcessing;
		jMenuItemTranslation = gui.jMenuItemTranslation;
		jMenuItemPostProcessing = gui.jMenuItemPostProcessing;
		jMenuItemTemplateGeneration = gui.jMenuItemTemplateGeneration;
		
		jMenuItemInstruction = gui.jMenuItemInstruction;
		jMenuItemAbout = gui.jMenuItemAbout;
		
		jPanelBoard = gui.jPanelBoard;
		
		// End of Assignation																	#_______A_______#

		//**
		// Adding Action Events & Other Attributes												#*******AA*******#
		//**
		jMenuItemBraillToTextTranslation.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	clearBoard();
            	new BrailleToTextTranslationInitializer(
            			new OpeningUserInterface(jPanelBoard, ProcessManager.TITLE_BRAILLE_TO_TEXT_GENERATION))
            			.attachToBoard();
            }
        });
		jMenuItemPreProcessing.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	clearBoard();
            	new PreProcessingInitializer(
            			new OpeningUserInterface(jPanelBoard, ProcessManager.TITLE_PRE_PROCESSING))
            			.attachToBoard();
            }
        });
		
		jMenuItemTranslation.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	clearBoard();
            	new TranslationInitializer(
            			new OpeningUserInterface(jPanelBoard, ProcessManager.TITLE_TRANSLATION))
            			.attachToBoard();
            }
        });
		jMenuItemPostProcessing.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	clearBoard();
            	new PostProcessingInitializer(
            			new OpeningUserInterface(jPanelBoard, ProcessManager.TITLE_POST_PROCESSING))
            			.attachToBoard();
            }
        });
		jMenuItemTemplateGeneration.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	clearBoard();
            	new TemplateGenerationPreProcessingConfiguration(
            			new OpeningUserInterface(jPanelBoard, ProcessManager.TITLE_TEMPLATE_GENERATION))
            			.attachToBoard();
            }
        });
		
		jMenuItemInstruction.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	String instruction = "Empty";
            	try{
            		instruction = FileIO.readWholeFile(
            				getClass().getResourceAsStream("/res/txt/Instruction.txt"));
            	}catch(Exception e){
            		e.printStackTrace();
            	}
            	
            	new Instruction(instruction);
            }
        });
		jMenuItemAbout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	String about = "Empty";
            	try{
            		about = FileIO.readWholeFile(
            				getClass().getResourceAsStream("/res/txt/About.txt"));
            	}catch(Exception e){
            		e.printStackTrace();
            	}
            	
            	new About(about);
            }
        });
		
		// End of Adding Action Events & Other Attributes										#_______AA_______#
	}

	//**
	// Action Events 																			#*******AE*******#
	//**
	
	
	// End of Action Events 																	#_______AE_______#

	//**
	// Auxiliary Methods 																		#*******AM*******#
	//**
	private void clearBoard(){
		jPanelBoard.removeAll();
		jPanelBoard.repaint();
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

		/* Create */
		new MainFrame();
	}

}
