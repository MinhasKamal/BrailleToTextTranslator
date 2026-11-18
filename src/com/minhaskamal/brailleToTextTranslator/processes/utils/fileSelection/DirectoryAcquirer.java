/***********************************************************
* Developer: Minhas Kamal (minhaskamal024@gmail.com)       *
* Date: 20-Oct-2016                                        *
* License: MIT License                                     *
***********************************************************/

package com.minhaskamal.brailleToTextTranslator.processes.utils.fileSelection;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.*;
import com.minhaskamal.util.fileChoose.FileChooser;

public class DirectoryAcquirer extends FileAcquirer{
	
	// GUI Declaration
	
	//**
	// Variable Declaration 																	#*******D*******#
	//**
	// End of Variable Declaration 																#_______D_______#

	/***##Constructor##***/
	public DirectoryAcquirer() {
		super();
	}
	
	public DirectoryAcquirer(String[] fileExtensions) {
		super(fileExtensions);
	}

	//**
	// Action Events 																			#*******AE*******#
	//**
	protected void jButtonBrowseActionPerformed(ActionEvent evt){
		String filePath = new FileChooser(fileExtensions).chooseFilePathFromComputer();
		if(!filePath.contains(".")){
			return ;
		}
		
		String directoryPath = filePath.substring(0, filePath.lastIndexOf('\\'));
		jTextFieldDirectoryPath.setText(directoryPath);
	}
	// End of Action Events 																	#_______AE_______#

	//**
	// Auxiliary Methods 																		#*******AM*******#
	//**
	
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
		DirectoryAcquirer opt = new DirectoryAcquirer();
		
		JFrame jFrame = new JFrame();
		jFrame.setBounds(10, 10, 500, 400);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setVisible(true);
		jFrame.setLayout(new GridLayout());
		jFrame.add(opt.gui);
	}

	
}
