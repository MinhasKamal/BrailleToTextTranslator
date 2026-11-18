/***********************************************************
* Developer: Minhas Kamal (minhaskamal024@gmail.com)       *
* Date: 26-Oct-2016                                        *
* License: MIT License                                     *
***********************************************************/

package com.minhaskamal.brailleToTextTranslator.processes;

import java.util.ArrayList;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class UserInput {
	
	public static final String USER_INPUT_TAG = "user-input";
	public static final String USER_INPUT_ATTRIBUTE_TYPE = "type";

	
	private ArrayList<Command> commands;
	private String type;
	
	
	public UserInput(String type){
		commands = new ArrayList<UserInput.Command>();
		this.type = type;
	}
	
	public UserInput(Node UserInputNode){
		this(UserInputNode.getAttributes().getNamedItem(USER_INPUT_ATTRIBUTE_TYPE).getNodeValue());
		
		NodeList commandNodeList = UserInputNode.getChildNodes();
		for(int i=0; i<commandNodeList.getLength(); i++){
			Node commandNode = commandNodeList.item(i);
			if(commandNode.getNodeName()==Command.COMMAND_TAG){
				this.commands.add(new Command(commandNode));
			}
		}
	}
	
	
	public void addNewCommand(String process, String argument){
		commands.add(new Command(process, argument));
	}
	
	public int getNumberOfCommands(){
		return this.commands.size();
	}
	public String getProcess(int index){
		return this.commands.get(index).process;
	}
	public String getArgument(int index){
		return this.commands.get(index).argument;
	}
	
	public String dump(){
		String string = "<"+USER_INPUT_TAG+" "+USER_INPUT_ATTRIBUTE_TYPE+"=\""+type+"\">\n";
		
		for(Command command: commands){
			string += command.dump()+"\n";
		}
		string += "</"+USER_INPUT_TAG+">";
		
		return string;
	}
	
	
	/////////////////////////////
	
	public class Command{
		public static final String COMMAND_TAG = "command";
		public static final String PROCESS_TAG = "process";
		public static final String ARGUMENT_TAG = "argument";
		
		String process;
		String argument;
		
		Command(String process, String argument){
			this.process = process;
			this.argument = argument;
		}
		
		Command(Node commandNode){
			NodeList processArgumentNodeList = commandNode.getChildNodes();
			this.process = processArgumentNodeList.item(1).getTextContent();
			this.argument = processArgumentNodeList.item(3).getTextContent();
		}
		
		public String dump(){
			String openingString = "<"+COMMAND_TAG+">\n";
			String processString= "<"+PROCESS_TAG+">"+this.process+"</"+PROCESS_TAG+">\n";
			String argumentString= "<"+ARGUMENT_TAG+">"+this.argument+"</"+ARGUMENT_TAG+">\n";
			String closingString = "</"+COMMAND_TAG+">";
			
			return openingString+processString+argumentString+closingString;
		}
	}
}
