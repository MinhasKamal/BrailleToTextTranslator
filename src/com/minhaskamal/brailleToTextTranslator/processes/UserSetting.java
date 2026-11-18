/***********************************************************
* Developer: Minhas Kamal (minhaskamal024@gmail.com)       *
* Date: 28-Oct-2016                                        *
* License: MIT License                                     *
***********************************************************/

package com.minhaskamal.brailleToTextTranslator.processes;

import java.util.ArrayList;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class UserSetting {
	public static final String USER_SETTING_TAG = "user-setting";
	
	private ArrayList<UserInput> userInputs;
	
	public UserSetting(){
		this.userInputs = new ArrayList<UserInput>();
	}
	
	public UserSetting(Node userSettingNode){
		this();
		
		NodeList userInputNodeList = userSettingNode.getChildNodes();
		for(int i=0; i<userInputNodeList.getLength(); i++){
			Node userInputNode = userInputNodeList.item(i);
			if(userInputNode.getNodeName()==UserInput.USER_INPUT_TAG){
				this.userInputs.add(new UserInput(userInputNode));
			}
		}
	}
	
	public void add(UserInput userInput){
		this.userInputs.add(userInput);
	}
	
	public String dump(){
		String string = "<"+USER_SETTING_TAG+">\n";
		for(UserInput userInput: userInputs){
			string += userInput.dump()+"\n";
		}
		string += "</"+USER_SETTING_TAG+">";
		
		return string;
	}
	
	public UserInput getUserInput(int index){
		return this.userInputs.get(index);
	}
	public int getNumberOfUserInputs(){
		return this.userInputs.size();
	}
}
