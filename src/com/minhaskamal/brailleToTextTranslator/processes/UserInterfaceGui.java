/***********************************************************
* Developer: Minhas Kamal (minhaskamal024@gmail.com)       *
* Date: 01-Nov-2016                                        *
* License: MIT License                                     *
***********************************************************/

package com.minhaskamal.brailleToTextTranslator.processes;

import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class UserInterfaceGui extends JLabel{

	//**
	// Variable Declaration 																	#*******D*******#
	//**
	public JLabel jLabelTitle;
	
	public JButton jButtonCancel, jButtonOk;
	
	////////
	
	protected int width, hight, xMargin, gapSmall, gapLarge, componantWidth,
		componantHeight, buttonWidth;
	// End of Variable Declaration 																#_______D_______#

	/***##Constructor##***/
	public UserInterfaceGui() {
		
		initComponentPositions();
		initialComponent();
	}

	
	private void initComponentPositions(){
		this.width = 584;
		this.hight = 400;
		this.xMargin = 10;
		this.gapSmall = 5;
		this.gapLarge = 20;
		this.componantWidth = width-2*xMargin;
		this.componantHeight = 30;
		this.buttonWidth = 80;
	}
	
	/**
	 * Method for Initializing all the GUI variables and placing them all to specific space on 
	 * the component. It also specifies criteria of the main component.
	 */
	private void initialComponent() {
		//**
		// Initialization 																		#*******I*******#
		//**
		jLabelTitle = new JLabel();
		
		jButtonCancel = new JButton("Cancel");
		jButtonOk = new JButton("Ok");
		// End of Initialization																#_______I_______#

		//**
		// Setting Bounds and Attributes of the Elements 										#*******S*******#
		//**
		jLabelTitle.setBounds(xMargin, xMargin, componantWidth, componantHeight);
		jLabelTitle.setHorizontalAlignment(SwingConstants.CENTER);
		jLabelTitle.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, componantHeight-2*gapSmall));
		 
		jButtonCancel.setBounds(width-2*buttonWidth-xMargin-gapSmall, hight-gapSmall-componantHeight, buttonWidth, componantHeight);
		jButtonOk.setBounds(width-buttonWidth-xMargin, hight-gapSmall-componantHeight, buttonWidth, componantHeight);
		// End of Setting Bounds and Attributes 												#_______S_______#
		
		//**Setting Criterion of the Label**//
		//setBorder(BorderFactory.createDashedBorder(null));
		setLayout(null);

		//**
		// Adding Components 																	#*******A*******#
		//**
		add(jLabelTitle);

		add(jButtonCancel);
		add(jButtonOk);
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
		UserInterfaceGui gui = new UserInterfaceGui();
		
		JFrame jFrame = new JFrame();
		jFrame.setBounds(200, 150, 600, 450);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setVisible(true);
		jFrame.setLayout(new GridLayout());
		jFrame.add(gui);
	}


}
