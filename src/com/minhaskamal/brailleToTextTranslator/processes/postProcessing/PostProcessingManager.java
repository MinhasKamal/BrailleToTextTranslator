/***********************************************************
* Developer: Minhas Kamal (minhaskamal024@gmail.com)       *
* Date: 26-Oct-2016                                        *
* License: MIT License                                     *
***********************************************************/

package com.minhaskamal.brailleToTextTranslator.processes.postProcessing;

import java.io.File;

import com.minhaskamal.brailleToTextTranslator.processes.*;
import com.minhaskamal.brailleToTextTranslator.processes.postProcessing.process.PostProcessingRunner;
import com.minhaskamal.util.fileReadWrite.FileIO;
import com.minhaskamal.util.fileReadWrite.FilePreparer;

public class PostProcessingManager extends ProcessManager{
	public static final String PROCESS_USE_SPELLCHECKER = "spell-check";
	
	public static final String DEFAULT_WORD_LIST_FILE_PATH = "src\\res\\txt\\BengaliWordList.dwl";
	
	///////////////////////////////////////////////////////
	
	private PostProcessingRunner postProcessingRunner;
	
	public PostProcessingManager(UserSetting userSetting){
		super(userSetting);
		
		try {
			postProcessingRunner = new PostProcessingRunner(userSetting.getUserInput(1));
		} catch (Exception e) {}
	}
	
	@Override
	protected void manage() throws Exception{
		File file = new File(rootOutputDirectory);
		file.mkdirs();
		
		String[] inputTextFileNames = FilePreparer.getAllFileNames(rootInputDirectory, ACCEPTABLE_TEXT_FILE_EXTENSIONS);
		
		for(int i=0; i<inputTextFileNames.length; i++){
			String string = FileIO.readWholeFile(rootInputDirectory+"\\"+inputTextFileNames[i], "UTF-8");
			String text = postProcessingRunner.run(string);
			FileIO.writeWholeFile(rootOutputDirectory+"\\"+inputTextFileNames[i]+".txt", text, "UTF-8");
		}
	}
}
