/***********************************************************
* Developer: Minhas Kamal (minhaskamal024@gmail.com)       *
* Date: 20-Oct-2016                                        *
* License: MIT License                                     *
***********************************************************/

package com.minhaskamal.brailleToTextTranslator.processes.utils.fileSelection;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import com.minhaskamal.util.fileChoose.FileChooser;

public class FileAcquirer {

	// GUI Declaration
	protected FileSelectorGui gui;
	
	//**
	// Variable Declaration 																	#*******D*******#
	//**
	protected JTextField jTextFieldDirectoryPath;
	private JButton jButtonBrowse;
	
	protected String[] fileExtensions;
	// End of Variable Declaration 																#_______D_______#

	/***##Constructor##***/
	public FileAcquirer() {
		this(new String[]{"*"});
	}
	
	public FileAcquirer(String[] fileExtensions) {
		this.fileExtensions = fileExtensions;
		
		initialComponent();
	}

	
	/**
	 * Method for Initializing all the GUI variables and placing them all to specific space on 
	 * the frame. It also specifies criteria of the main frame.
	 */
	private void initialComponent() {
		// GUI Initialization
		gui = new FileSelectorGui();
		
		//**
		// Assignation 																			#*******A*******#
		//**
		jTextFieldDirectoryPath = gui.jTextFieldDirectoryPath;
		jButtonBrowse = gui.jButtonBrowse;
		// End of Assignation																	#_______A_______#

		//**
		// Adding Action Events & Other Attributes												#*******AA*******#
		//**
		jButtonBrowse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	jButtonBrowseActionPerformed(evt);
            }
        });
		// End of Adding Action Events & Other Attributes										#_______AA_______#
	}

	//**
	// Action Events 																			#*******AE*******#
	//**
	protected void jButtonBrowseActionPerformed(ActionEvent evt){
		String filePath = new FileChooser(fileExtensions).chooseFilePathFromComputer();
		if(!filePath.contains(".")){
			return ;
		}
		
		jTextFieldDirectoryPath.setText(filePath);
	}
	// End of Action Events 																	#_______AE_______#

	//**
	// Auxiliary Methods 																		#*******AM*******#
	//**
	public void attachTo(JComponent jComponent){
		jComponent.add(gui);
		jComponent.revalidate();
	}
	public void unAttach(){
		gui.getParent().remove(gui);
	}
	
	public String getPath(){
		return jTextFieldDirectoryPath.getText();
	}
	
	public void enable(boolean bool){
		jTextFieldDirectoryPath.setEnabled(bool);
		jButtonBrowse.setEnabled(bool);
	}
	public void setFileExtensions(String[] fileExtensions){
		this.fileExtensions = fileExtensions;
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
		FileAcquirer opt = new FileAcquirer(new String[]{"png"});
		
		JFrame jFrame = new JFrame();
		jFrame.setBounds(10, 10, 500, 400);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setVisible(true);
		jFrame.setLayout(new GridLayout());
		jFrame.add(opt.gui);
	}

	
}
