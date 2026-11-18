/***********************************************************
* Developer: Minhas Kamal (minhaskamal024@gmail.com)       *
* Date: 22-Oct-2016                                        *
* License: MIT License                                     *
***********************************************************/

package com.minhaskamal.brailleToTextTranslator.processes.utils.commonUserInterfaces;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.minhaskamal.brailleToTextTranslator.processes.preProcessing.*;

import javax.swing.*;

public class PreProcessingMethod {

	// GUI Declaration
	private PreProcessingMethodGui gui;
	
	private JComponent parent;
	
	//**
	// Variable Declaration 																	#*******D*******#
	//**
	@SuppressWarnings("rawtypes")
	private JComboBox jComboBoxPreProcessingProcess, jComboBoxPreProcessingAlgorithm;
	private JButton jButtonPreProcessingRemove;
	// End of Variable Declaration 																#_______D_______#

	/***##Constructor##***/
	public PreProcessingMethod() {

		initialComponent();
	}

	
	/**
	 * Method for Initializing all the GUI variables and placing them all to specific space on 
	 * the frame. It also specifies criteria of the main frame.
	 */
	private void initialComponent() {
		// GUI Initialization
		gui = new PreProcessingMethodGui();
		
		parent = null;
		
		//**
		// Assignation 																			#*******A*******#
		//**
		jComboBoxPreProcessingProcess = gui.jComboBoxPreProcessingProcess;
		jComboBoxPreProcessingAlgorithm = gui.jComboBoxPreProcessingAlgorithm;
		jButtonPreProcessingRemove = gui.jButtonPreProcessingRemove;
		// End of Assignation																	#_______A_______#

		//**
		// Adding Action Events & Other Attributes												#*******AA*******#
		//**
		jButtonPreProcessingRemove.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent evt) {
	        	jButtonPreProcessingRemoveActionPerformed(evt);
	        }
	    });
		jComboBoxPreProcessingProcess.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	jComboBoxPreProcessingProcessActionPerformed(evt);
            }
		});
		// End of Adding Action Events & Other Attributes										#_______AA_______#
	}

	//**
	// Action Events 																			#*******AE*******#
	//**
	private void jButtonPreProcessingRemoveActionPerformed(ActionEvent evt){
		remove();
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void jComboBoxPreProcessingProcessActionPerformed(ActionEvent evt){
		String selectedProcess=jComboBoxPreProcessingProcess.getSelectedItem().toString();
		jComboBoxPreProcessingAlgorithm.setEnabled(true);

		if(selectedProcess==PreProcessingManager.PROCESS_ENHANCEMENT){
			jComboBoxPreProcessingAlgorithm.setModel(new DefaultComboBoxModel(new String[] {
					PreProcessingManager.ALGORITHM_EMPTY, PreProcessingManager.ENHA_HISTOGRAM_EQUALIZATION,
					PreProcessingManager.ENHA_BI_HISTOGRAM_EQUALIZATION
			}));
		}else if(selectedProcess==PreProcessingManager.PROCESS_NOISE_REDUCTION){
			jComboBoxPreProcessingAlgorithm.setModel(new DefaultComboBoxModel(new String[] {
					PreProcessingManager.ALGORITHM_EMPTY, PreProcessingManager.NOIS_MEAN_FILTER,
					PreProcessingManager.NOIS_GAUSSIAN_FILTER, PreProcessingManager.NOIS_MEDIAN_FILTER
			}));
		}else if(selectedProcess==PreProcessingManager.PROCESS_CONNECTIVITY_IMPROVEMENT){
			jComboBoxPreProcessingAlgorithm.setModel(new DefaultComboBoxModel(new String[] {
					PreProcessingManager.ALGORITHM_EMPTY, PreProcessingManager.CONN_DILATION,
					PreProcessingManager.CONN_EROSION
			}));
		}else if(selectedProcess==PreProcessingManager.PROCESS_QUANTIZATION){
			jComboBoxPreProcessingAlgorithm.setModel(new DefaultComboBoxModel(new String[] {
					PreProcessingManager.ALGORITHM_EMPTY, PreProcessingManager.QUAN_OTSU_THRESHOLDING,
					PreProcessingManager.QUAN_K_MEANS_CLUSTERING
			}));
		}else{
			jComboBoxPreProcessingAlgorithm.setEnabled(false);			
		}
	}
	// End of Action Events 																	#_______AE_______#

	//**
	// Auxiliary Methods 																		#*******AM*******#
	//**
	public void attachTo(JComponent jComponent){
		parent = jComponent;
		jComponent.add(gui, "h 35!, w 100:500:1000");
		jComponent.revalidate();
	}
	public void remove(){
		if(parent!=null){
			parent.remove(gui);
			parent.revalidate();
			parent.repaint();
		}
		gui.setVisible(false);
	}
	public boolean isActive(){
		boolean isAlgorithmSelected = PreProcessingManager.ALGORITHM_EMPTY!=getArgument();
		
		return isAlgorithmSelected && gui.isVisible();
	}
	public String getProcess(){
		return jComboBoxPreProcessingProcess.getSelectedItem().toString();
	}
	public String getArgument(){
		return jComboBoxPreProcessingAlgorithm.getSelectedItem().toString();
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
		PreProcessingMethod opt1 = new PreProcessingMethod();
		PreProcessingMethod opt2 = new PreProcessingMethod();
		PreProcessingMethod opt3 = new PreProcessingMethod();
		
		JFrame jFrame = new JFrame();
		jFrame.setBounds(200, 150, 600, 450);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setVisible(true);
		jFrame.setLayout(new GridLayout());
		jFrame.add(opt1.gui);
		jFrame.add(opt2.gui);
		jFrame.add(opt3.gui);
	}

}
