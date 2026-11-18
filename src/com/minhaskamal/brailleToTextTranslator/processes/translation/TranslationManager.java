/***********************************************************
* Developer: Minhas Kamal (minhaskamal024@gmail.com)       *
* Date: 26-Oct-2016                                        *
* License: MIT License                                     *
***********************************************************/

package com.minhaskamal.brailleToTextTranslator.processes.translation;

import java.io.File;
import com.minhaskamal.brailleToTextTranslator.processes.*;
import com.minhaskamal.brailleToTextTranslator.processes.translation.process.TranslationRunner;
import com.minhaskamal.egamiLight.Matrix;
import com.minhaskamal.util.fileReadWrite.*;

public class TranslationManager extends ProcessManager{
	public static final String PROCESS_DOT_SEGMENTATION = "dot-segmentation"; 
	public static final String PROCESS_PATTERN_RECOGNITION = "pattern-recognition"; 
	public static final String PROCESS_CODE_TO_CHARACTER_MAP = "code-to-character-map"; 
	
	public static final String PATTERN_RECOGNITION_ALGORITHM_EMPTY = "select pattern recognition algorithm";
	public static final String PATTERN_RECOGNITION_ALGORITHM_ROTATION_INVARIENT = "rotation-invarient-pattern-recognition";
	
	
	public static final String DEFAULT_MAP_FILE_PATH = "src\\res\\txt\\BengaliBrailleCodeToCharacterMap.ccm";

	
	////////////////////////////////////////////////////////
	
	private TranslationRunner translationRunner;
	
	public TranslationManager(UserSetting userSetting){
		super(userSetting);
		
		try {
			translationRunner = new TranslationRunner(userSetting.getUserInput(1));
		} catch (Exception e) {}
	}
	
	@Override
	protected void manage() throws Exception{
		File file = new File(rootOutputDirectory);
		file.mkdirs();
		
		String[] inputImageNames = FilePreparer.getAllFileNames(rootInputDirectory, ACCEPTABLE_IMAGE_EXTENSIONS);
		
		for(int i=0; i<inputImageNames.length; i++){
			Matrix matrix = new Matrix(rootInputDirectory+"\\"+inputImageNames[i], Matrix.BLACK_WHITE);
			String text = translationRunner.run(matrix);
			FileIO.writeWholeFile(rootOutputDirectory+"\\"+inputImageNames[i]+".txt", text, "UTF-8");
		}
	}
}
