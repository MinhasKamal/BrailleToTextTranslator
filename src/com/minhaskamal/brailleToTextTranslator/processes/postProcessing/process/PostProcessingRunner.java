/***********************************************************
* Developer: Minhas Kamal (minhaskamal024@gmail.com)       *
* Date: 26-Oct-2016                                        *
* License: MIT License                                     *
***********************************************************/

package com.minhaskamal.brailleToTextTranslator.processes.postProcessing.process;

import com.minhaskamal.brailleToTextTranslator.processes.UserInput;

public class PostProcessingRunner {
	SpellChecker spellChecker;
	boolean checkSpell;
	
	public PostProcessingRunner(UserInput userInput) throws Exception{
		String spellCheckingWordFilePath = userInput.getArgument(0);
		if(!spellCheckingWordFilePath.isEmpty()){
			checkSpell = true;
			spellChecker = new SpellChecker(spellCheckingWordFilePath);
		}else{
			checkSpell = false;
		}
	}
	
	public String run(String rawText){
		rawText = rawText.replace('_', ' ');
		
		if(checkSpell){
			rawText = spellChecker.checkSpell(rawText);
		}
		
		return rawText;
	}
}
