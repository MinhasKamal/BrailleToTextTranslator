/***********************************************************
* Developer: Minhas Kamal (minhaskamal024@gmail.com)       *
* Date: 30-Oct-2016                                        *
* License: MIT License                                     *
***********************************************************/

package com.minhaskamal.brailleToTextTranslator.processes;

import com.minhaskamal.brailleToTextTranslator.processes.utils.openingPanel.OpeningPanel;
import com.minhaskamal.util.message.Message;

public abstract class ProcessManager {

	public static final String TITLE_BRAILLE_TO_TEXT_GENERATION = "BRAILLE TO TEXT GENERATION";
	public static final String TITLE_PRE_PROCESSING = "PRE PROCESSING";
	public static final String TITLE_TRANSLATION = "TRANSLATION";
	public static final String TITLE_POST_PROCESSING = "POST PROCESSING";
	public static final String TITLE_TEMPLATE_GENERATION = "TEMPLATE GENERATION";
	

	public static final String PRE_PROCESSING_CONFIGURATION_TYPE = "pre-processing-configuration";
	public static final String TRANSLATION_CONFIGURATION_TYPE = "translation-configuration";
	public static final String POST_PROCESSING_CONFIGURATION_TYPE = "translation-configuration";
	
	public static final String INITIALIZER_TYPE = "initializer";
	public static final String FINALIZER_TYPE = "finalizer";

	public static final String PROCESS_READ = "read";
	public static final String PROCESS_WRITE = "write";
	
	
	public static final String[] ACCEPTABLE_IMAGE_EXTENSIONS = new String[]{"jpg", "png", "jpeg"};
	public static final String[] ACCEPTABLE_TEMPLATE_FILE_EXTENSIONS = new String[]{"bpt", "xml"};
	public static final String[] ACCEPTABLE_CODE_TO_CHARACTER_MAP_FILE_EXTENSIONS = new String[]{"ccm", "txt"};
	public static final String[] ACCEPTABLE_WORD_LIST_FILE_EXTENSIONS = new String[]{"dwl", "txt"};
	public static final String[] ACCEPTABLE_TEXT_FILE_EXTENSIONS = new String[]{"txt"};
	
	////////
	
	protected String rootInputDirectory;
	protected String rootOutputDirectory;
	
	public ProcessManager(UserSetting userSetting){
		rootInputDirectory = userSetting.getUserInput(0).getArgument(0);
		rootOutputDirectory = userSetting.getUserInput(2).getArgument(0);
	}
	
	public ProcessManager(){
	}
	
	public void run(){
		try{
			manage();
			
			new Message("Operation is successful.", Message.MESSAGE_TYPE_CONFIRMATION_MESSAGE);
		}
		catch(Exception e){
			new Message("Error! Exception happened during \nexecution.", Message.MESSAGE_TYPE_ERROR_MESSAGE);
		}finally{
			OpeningPanel.getOpeningPanel().attachToBoard();
		}
	}
	
	protected abstract void manage() throws Exception;
}
