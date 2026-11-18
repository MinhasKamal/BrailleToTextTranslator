/***********************************************************
* Developer: Minhas Kamal (minhaskamal024@gmail.com)       *
* Date: 18-Nov-2016                                        *
* License: MIT License                                     *
***********************************************************/

package com.minhaskamal.brailleToTextTranslator.processes.translation.process;

import java.util.ArrayList;

public class BrailleDotOrientationDetectorAdapter {
	public int rowStart = 75;//98;
	public int colStart = 72;//71;
	
	public int rowLength = 45;//58;
	public int colLength = 26;//36;
	
	public double rowGap = 45;//5.7;
	public double colGap = 27;//2.5;
	
	public int numberOfRows = 15;//23;
	public int numberOfCols = 20;//29;
	
	public double rowShift = -0.001;
	public double colShift = 0.001;
	
	public BrailleDotOrientationDetectorAdapter(ArrayList<int[]> connectedPixelGroupPositions){
		
	}
	
}
