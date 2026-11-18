/***********************************************************
* Developer: Minhas Kamal (minhaskamal024@gmail.com)       *
* Date: 20-Oct-2016                                        *
* License: MIT License                                     *
***********************************************************/

package com.minhaskamal.brailleToTextTranslator;

import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class MainFrameGui extends JFrame {
	//**
	// Variable Declaration 																	#*******D*******#
	//**
	JMenuBar jMenuBarMain;
	JMenu jMenuOperation, jMenuHelp;
    
	JMenuItem jMenuItemBraillToTextTranslation,
		jMenuItemPreProcessing, jMenuItemTranslation, jMenuItemPostProcessing,
		jMenuItemTemplateGeneration;
	JSeparator separator1, separator2;
	JMenuItem jMenuItemInstruction, jMenuItemAbout;
	
	JPanel jPanelBoard;
	// End of Variable Declaration 																#_______D_______#

	/***##Constructor##***/
	public MainFrameGui() {

		initialComponent();
	}

	/**
	 * Method for Initializing all the GUI variables and placing them all to specific space on 
	 * the frame. It also specifies criteria of the main frame.
	 */
	private void initialComponent() {
		//**
		// Initialization 																		#*******I*******#
		//**
		jMenuBarMain = new JMenuBar();
		
		jMenuOperation = new JMenu();
		jMenuHelp = new JMenu();
	    
		jMenuItemBraillToTextTranslation = new JMenuItem();
		jMenuItemPreProcessing = new JMenuItem();
		jMenuItemTranslation = new JMenuItem();
		jMenuItemPostProcessing = new JMenuItem();
		jMenuItemTemplateGeneration = new JMenuItem();
		
		separator1 = new JSeparator();
		separator2 = new JSeparator();
		
		jMenuItemInstruction = new JMenuItem();
		jMenuItemAbout = new JMenuItem();
		
		jPanelBoard = new JPanel();
		// End of Initialization																#_______I_______#

		//**
		// Setting Bounds and Attributes of the Elements 										#*******S*******#
		//**
		jMenuOperation.setText("   Operations   ");
		jMenuHelp.setText("   Help   ");
		
		jMenuItemBraillToTextTranslation.setText("Braill to Text Translation");
		jMenuItemPreProcessing.setText("Pre Processing");
		jMenuItemTranslation.setText("Translation");
		jMenuItemPostProcessing.setText("Post Processing");
		jMenuItemTemplateGeneration.setText("Template Generation");
		
		jMenuItemInstruction.setText("Instruction");
		jMenuItemAbout.setText("About");
		
		jPanelBoard.setBackground(Color.WHITE);
		jPanelBoard.setLayout(new GridLayout());
		
		// End of Setting Bounds and Attributes 												#_______S_______#
		
		//**Setting Criterion of the Frame**//
		setIconImage(new ImageIcon(getClass().getResource("/res/img/BrailleToTextTranslatorIcon.png")).getImage());
		setBounds(200, 150, 600, 460);
		setResizable(false);
		setTitle("Braille To Text Translator");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		//**
		// Adding Components 																	#*******A*******#
		//**
		jMenuOperation.add(jMenuItemBraillToTextTranslation);
		jMenuOperation.add(separator1);
		jMenuOperation.add(jMenuItemPreProcessing);
		jMenuOperation.add(jMenuItemTranslation);
		jMenuOperation.add(jMenuItemPostProcessing);
		jMenuOperation.add(separator2);
		jMenuOperation.add(jMenuItemTemplateGeneration);

		jMenuHelp.add(jMenuItemInstruction);
		jMenuHelp.add(jMenuItemAbout);
		
		jMenuBarMain.add(jMenuOperation);
		jMenuBarMain.add(jMenuHelp);
		
		setJMenuBar(jMenuBarMain);
		add(jPanelBoard);
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
		MainFrameGui gui = new MainFrameGui();
		gui.setVisible(true);
	}
}
