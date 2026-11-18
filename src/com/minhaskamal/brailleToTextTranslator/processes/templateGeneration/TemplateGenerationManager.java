/***********************************************************
* Developer: Minhas Kamal (minhaskamal024@gmail.com)       *
* Date: 26-Oct-2016                                        *
* License: MIT License                                     *
***********************************************************/

package com.minhaskamal.brailleToTextTranslator.processes.templateGeneration;

import com.minhaskamal.brailleToTextTranslator.processes.*;
import com.minhaskamal.util.fileReadWrite.FileIO;

public class TemplateGenerationManager extends ProcessManager{

	//////////////////////
	
	private UserSetting userSetting;
	
	public TemplateGenerationManager(UserSetting userSetting) {
		this.userSetting = new UserSetting();
		this.userSetting.add(userSetting.getUserInput(0));
		this.userSetting.add(userSetting.getUserInput(1));
		this.userSetting.add(userSetting.getUserInput(2));
		
		rootOutputDirectory = userSetting.getUserInput(3).getArgument(0);
	}
	
	@Override
	protected void manage() throws Exception{
		FileIO.writeWholeFile(rootOutputDirectory, userSetting.dump());
	}
}
