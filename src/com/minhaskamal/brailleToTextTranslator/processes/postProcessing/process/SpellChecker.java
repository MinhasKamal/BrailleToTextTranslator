/***********************************************************
* Developer: Minhas Kamal (minhaskamal024@gmail.com)       *
* Date: 21-Nov-2016                                        *
* License: MIT License                                     *
***********************************************************/

package com.minhaskamal.brailleToTextTranslator.processes.postProcessing.process;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import com.minhaskamal.brailleToTextTranslator.processes.translation.process.BrailleCodeToCharacterConverter;

//TODO only supports Bengali because of alphabets & punctuationMarks
public class SpellChecker {
	private HashMap<String, Integer> dictionary;
	private char targetCharacter;
	
	private char[] alphabets; 
	//private char[] punctuationMarks; 
	
	public SpellChecker(String dictionaryPath) throws Exception{
		this.dictionary = constructDictionary(dictionaryPath);
		
		this.targetCharacter = BrailleCodeToCharacterConverter.UNDEFINED_CHARACTER;
		
		char start = '\u0985', end = '\u09DF';
		int length = end-start+1;
		this.alphabets = new char[length];
		int i=0;
		for(char c=start; c<=end; c++){
			alphabets[i]=c;
			i++;
		}
		//this.punctuationMarks = new char[]{'\u09BE', ',', ';', ':', '?', '!', '-'};
	}
	
	private HashMap<String, Integer> constructDictionary(String dictionaryPath) throws Exception{
		HashMap<String, Integer> dictionary = new HashMap<String, Integer>();
		
		BufferedReader mainBR = new BufferedReader(new InputStreamReader(
				new FileInputStream(dictionaryPath), "UTF-8"));
		
		int i=0;
		String string = mainBR.readLine();
		while(string!=null){	//reading step by step
			
			dictionary.put(string, i);
			
			i++;
			string = mainBR.readLine();
		}
		
		mainBR.close();
		
		return dictionary;
	}
	
	public String checkSpell(String string){
		String[] words = string.split(" ");
		
		string = "";
		for(int i=0; i<words.length; i++){
			string += checkSpellOfAWord(words[i])+" ";
		}
		
		return string;
	}
	
	public String checkSpellOfAWord(String word){
		if(dictionary.containsKey(word)){
			return word;
		}

		ArrayList<String> edits = getAllEdits(word);
		
		HashMap<Integer, String> candidates = new HashMap<>();
		for(String edit: edits){
			Integer value = dictionary.get(edit);
			if(value != null){
				candidates.put(value, edit);
			}
		}
		
		if (!candidates.isEmpty()) {
			word = candidates.get(Collections.min(candidates.keySet()));
		}
		
		return word;
	} 
	
	private ArrayList<String> getAllEdits(String word){
		ArrayList<String> edits = new ArrayList<String>();
		
		String editedWord;
		for (int i=0; i<word.length(); i++) {
			if(word.charAt(i)==targetCharacter){
				for (int j=0; j<alphabets.length; j++) {
					editedWord = word.substring(0,i)+alphabets[j]+word.substring(i+1);
					edits.add(editedWord);
				}
			}
		}
				
		return edits;
	}
}
