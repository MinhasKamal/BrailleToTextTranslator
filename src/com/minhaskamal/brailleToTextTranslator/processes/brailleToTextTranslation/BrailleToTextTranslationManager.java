/***********************************************************
* Developer: Minhas Kamal (minhaskamal024@gmail.com)       *
* Date: 26-Oct-2016                                        *
* License: MIT License                                     *
***********************************************************/

package com.minhaskamal.brailleToTextTranslator.processes.brailleToTextTranslation;

import java.io.File;

import com.minhaskamal.brailleToTextTranslator.processes.ProcessManager;
import com.minhaskamal.brailleToTextTranslator.processes.UserSetting;
import com.minhaskamal.brailleToTextTranslator.processes.brailleToTextTranslation.process.BrailleToTextTranslationRunner;
import com.minhaskamal.egamiLight.Matrix;
import com.minhaskamal.util.fileReadWrite.FileIO;
import com.minhaskamal.util.fileReadWrite.FilePreparer;

public class BrailleToTextTranslationManager extends ProcessManager{

	public static final String DEFAULT_TEMPLATE_FILE_PATH = "src\\res\\txt\\BrailleProcessingTemplate.bpt";
	
	private BrailleToTextTranslationRunner brailleToTextTranslationRunner;
	
	public BrailleToTextTranslationManager(UserSetting userSetting) {
		rootInputDirectory = userSetting.getUserInput(0).getArgument(0);
		rootOutputDirectory = userSetting.getUserInput(4).getArgument(0);
		
		try {
			brailleToTextTranslationRunner = new BrailleToTextTranslationRunner(
					userSetting.getUserInput(1),
					userSetting.getUserInput(2),
					userSetting.getUserInput(3));
		} catch (Exception e) {}
	}
	
	@Override
	protected void manage() throws Exception{
		File file = new File(rootOutputDirectory);
		file.mkdirs();
		
		String[] inputImageNames = FilePreparer.getAllFileNames(rootInputDirectory, ACCEPTABLE_IMAGE_EXTENSIONS);
		
		for(int i=0; i<inputImageNames.length; i++){
			Matrix matrix = new Matrix(rootInputDirectory+"\\"+inputImageNames[i], Matrix.BLACK_WHITE);
			String text = brailleToTextTranslationRunner.run(matrix);
			FileIO.writeWholeFile(rootOutputDirectory+"\\"+inputImageNames[i]+".txt", text, "UTF-8");
		}
	}

}
