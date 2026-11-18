/***********************************************************
* Developer: Minhas Kamal (minhaskamal024@gmail.com)       *
* Date: 21-Nov-2016                                        *
* License: MIT License                                     *
***********************************************************/

package com.minhaskamal.brailleToTextTranslator.processes.brailleToTextTranslation.process;

import com.minhaskamal.brailleToTextTranslator.processes.UserInput;
import com.minhaskamal.brailleToTextTranslator.processes.postProcessing.process.PostProcessingRunner;
import com.minhaskamal.brailleToTextTranslator.processes.preProcessing.process.PreProcessingRunner;
import com.minhaskamal.brailleToTextTranslator.processes.translation.process.TranslationRunner;
import com.minhaskamal.egamiLight.Matrix;

public class BrailleToTextTranslationRunner {
	
	private PreProcessingRunner preProcessingRunner;
	private TranslationRunner translationRunner;
	private PostProcessingRunner postProcessingRunner;
	
	public BrailleToTextTranslationRunner(UserInput userInputPreProcessing,
			UserInput userInputTranslation, UserInput userInputPostProcessing) throws Exception {
		
		preProcessingRunner = new PreProcessingRunner(userInputPreProcessing);
		translationRunner = new TranslationRunner(userInputTranslation);
		postProcessingRunner = new PostProcessingRunner(userInputPostProcessing);
	}
	
	public String run(Matrix matrix){
		matrix = preProcessingRunner.run(matrix);
		matrix.write("C:\\Users\\admin\\Desktop\\pop.png");
		String text = translationRunner.run(matrix);
		text = postProcessingRunner.run(text);
		
		return text;
	}
}
