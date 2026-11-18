/***********************************************************
* Developer: Minhas Kamal (minhaskamal024@gmail.com)       *
* Date: 26-Oct-2016                                        *
* License: MIT License                                     *
***********************************************************/

package com.minhaskamal.brailleToTextTranslator.processes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import com.minhaskamal.brailleToTextTranslator.processes.utils.openingPanel.OpeningPanel;
import com.minhaskamal.util.message.Confirm;

public abstract class UserInterface {
	
	protected UserInterfaceGui gui;
	
	//protected UserInterface previousUserInterface;
	protected UserInterface nextUserInterface;
	
	protected UserSetting userSetting;
	
	protected JComponent board;
	protected String title;
	protected JButton jButtonCancel, jButtonOk;
	
	///////////////////////////////////////////////////////

	public UserInterface(UserInterface previousUserInterface) {
		this(previousUserInterface.board, previousUserInterface.title);
		
		//this.previousUserInterface = previousUserInterface;
		this.userSetting = previousUserInterface.userSetting;
	}
	
	protected UserInterface(JComponent board){
		this(board, "");
	}
	
	protected UserInterface(JComponent board, String title){
		this.userSetting = new UserSetting();
		this.board = board;
		this.title = title;
		
	}
	
	
	///////////////////////////////////////////////////////
	
	protected void initialTwoButtons() {
		jButtonCancel = gui.jButtonCancel;
		jButtonOk = gui.jButtonOk;
		
		jButtonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	cancelAction();
            }
        });
		jButtonOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	okAction();
            }
        });
	}
	
	///////////////////////////////////////////////////////
	
	protected void cancelAction(){
		if(new Confirm("Do you really want to cancel?").getDecision()){;
			remove();
			OpeningPanel.getOpeningPanel().attachTo(board);
		}
	}
	
	protected abstract void okAction();
	
	public void attachToBoard(){
		board.add(this.gui);
		board.revalidate();
	}
	
	protected void remove(){
		board.remove(this.gui);
		board.repaint();
	}
	
	protected void setTitle(String string){
		gui.jLabelTitle.setText(string);
	}
}
