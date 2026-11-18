/***********************************************************
* Developer: Minhas Kamal (minhaskamal024@gmail.com)       *
* Date: 29-Oct-2016                                        *
* License: MIT License                                     *
***********************************************************/

package com.minhaskamal.brailleToTextTranslator.processes.translation.process;

import java.util.ArrayList;

import com.minhaskamal.brailleToTextTranslator.processes.UserInput;
import com.minhaskamal.brailleToTextTranslator.processes.translation.TranslationManager;
import com.minhaskamal.egami.connectedComponentAnalysis.ConnectedComponentLabeler;
import com.minhaskamal.egamiLight.Matrix;
import com.minhaskamal.util.fileReadWrite.FileIO;

public class TranslationRunner {

	private int minDotSize, maxDotSize;
	private DotToCode dotToCode;
	private BrailleCodeToCharacterConverter brailleCodeToCharacter;
	
	public TranslationRunner(UserInput userInput) throws Exception{
		String[] minMaxDotSize = userInput.getArgument(0).split(",");
		this.minDotSize = Integer.parseInt(minMaxDotSize[0]);
		this.maxDotSize = Integer.parseInt(minMaxDotSize[1]);
		
		this.dotToCode = getDotToCode(userInput.getArgument(1));
		
		String codeToCharacterFilePath = userInput.getArgument(2);
		brailleCodeToCharacter = new BrailleCodeToCharacterConverter(FileIO.readWholeFile(
					codeToCharacterFilePath, "UTF-8"));
	}
	
	public String run(Matrix matrix){
		///////////
		ConnectedComponentLabeler componentLabeler = new ConnectedComponentLabeler(matrix);
		componentLabeler.extractPixelGroup(Matrix.MAX_PIXEL/2,
				this.minDotSize, this.minDotSize, this.maxDotSize, this.maxDotSize);
		ArrayList<int[]> dotPositions = componentLabeler.getConnectedPixelGroupPositions();
		
		//////////
		String code = dotToCode.run(dotPositions);
		
		//////////
		String text = brailleCodeToCharacter.decodeStirng(code);

		return text;
	}
	
	private DotToCode getDotToCode(String argument){
		
		if(argument.equals(TranslationManager.PATTERN_RECOGNITION_ALGORITHM_ROTATION_INVARIENT)){
			return new AlgDotToCode();
		}
		
		return new EmptyDotToCode();
	}
	
	
	
	////////////////////////////////////////////////
	
	private interface DotToCode {
		public abstract String run(ArrayList<int[]> dotPositions);
	}
	private class EmptyDotToCode implements DotToCode{
		public String run(ArrayList<int[]> dotPositions){
			return "";
		}
	}
	private class AlgDotToCode implements DotToCode{
		BrailleDotToCodeRecognizer brailleDotToCode;
		
		public AlgDotToCode() {
			brailleDotToCode = new BrailleDotToCodeRecognizer();
		}
		
		public String run(ArrayList<int[]> dotPositions){
			return brailleDotToCode.generateCodeString(dotPositions);
		}
	}
}
