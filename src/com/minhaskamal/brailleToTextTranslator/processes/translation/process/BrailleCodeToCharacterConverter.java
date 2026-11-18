/***********************************************************
* Developer: Minhas Kamal (minhaskamal024@gmail.com)       *
* Date: Apr-2016                                           *
* License: MIT License                                     *
***********************************************************/

package com.minhaskamal.brailleToTextTranslator.processes.translation.process;

import java.util.ArrayList;
import com.minhaskamal.util.fileReadWrite.FileIO;

public class BrailleCodeToCharacterConverter {
	
	private ArrayList<CharacterCode> characterCodes;
	public static final char UNDEFINED_CHARACTER = '#';
	
	public BrailleCodeToCharacterConverter(String codeToCharacterMap) {
		try {
			characterCodes = buidCharacterCodes(codeToCharacterMap);
		} catch (Exception e) {}
	}
	
	/////////////////////////////////////////////////////////
	
	public String decodeStirng(String codeString){
		String string = "";
		
		String[] codes = codeString.split(" ");
		
		for(String code: codes){
			string += decodeChar(code);
		}
		
		return string;
	}
	
	public char decodeChar(String code){
		char character = UNDEFINED_CHARACTER;
		
		for(CharacterCode characterCode: characterCodes){
			if(characterCode.code.equals(code)){
				character = characterCode.character;
				break;
			}
		}
		
		return character;
	}
	
	///////////////////////////////////////////
	
	private ArrayList<CharacterCode> buidCharacterCodes(String data){
		String[] lines = data.split("\n");
		
		ArrayList<CharacterCode> characterCodes = new ArrayList<>();
		for(String line: lines){
			if(line.length()>6){
				String[] parts = line.split("\t");
				
				characterCodes.add(
						new CharacterCode(parts[0].replaceAll(" ", ""), parts[1].charAt(0)));
			}
		}
		
		return characterCodes;
	}
	
	////////////////////////////////////////////
	
	private class CharacterCode{
		String code;
		char character;
		
		public CharacterCode(String code, char character) {
			this.code = code;
			this.character = character;
		}
	}
	
	///////////////////////////////////////////
	
	//test
	public static void main(String[] args) throws Exception {
		BrailleCodeToCharacterConverter brailleCodeToCharacter = new BrailleCodeToCharacterConverter
				(FileIO.readWholeFile("src/res/english_braille_code.txt", "UTF-8"));
		
		String out = "";
		//out += brailleCodeToCharacter.decodeChar("010110");
		out += brailleCodeToCharacter.decodeStirng("010110 110010");
		
		FileIO.writeWholeFile("C:\\Users\\admin\\Desktop\\out.txt", out);
		
		System.out.println("### OPERATION SUCCESSFUL!!!");
	}
}
