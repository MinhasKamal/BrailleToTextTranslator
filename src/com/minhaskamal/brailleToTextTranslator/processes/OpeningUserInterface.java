/***********************************************************
* Developer: Minhas Kamal (minhaskamal024@gmail.com)       *
* Date: 26-Oct-2016                                        *
* License: MIT License                                     *
***********************************************************/

package com.minhaskamal.brailleToTextTranslator.processes;

import javax.swing.JComponent;

/**
 * A dummy user interface
 */
public class OpeningUserInterface extends UserInterface{

	public OpeningUserInterface(JComponent board) {
		super(board);
	}
	
	public OpeningUserInterface(JComponent board, String title) {
		super(board, title);
	}

	@Override
	protected void okAction() {
		
	}

	@Override
	public void attachToBoard() {
		
	}

	@Override
	public void remove() {
		
	}

}
