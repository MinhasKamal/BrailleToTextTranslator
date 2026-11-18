/***********************************************************
* Developer: Minhas Kamal (minhaskamal024@gmail.com)       *
* Date: Apr-2016                                           *
* License: MIT License                                     *
***********************************************************/

package com.minhaskamal.brailleToTextTranslator.processes.translation.process;

import java.util.ArrayList;

public class BrailleDotToCodeRecognizer {

	private double rowSections;
	private double colSections;
	
	public BrailleDotToCodeRecognizer() {
		this.rowSections = 3;
		this.colSections = 2;
	}
	
	public String generateCodeString(int rowStart, int colStart, int rowLength, int colLength,
			double rowGap, double colGap, int numberOfRows, int numberOfCols,
			double rowShift, double colShift, ArrayList<int[]> connectedPixelGroupPositions){
		
		String codeString = "";
		
		for(int i=0; i<numberOfRows; i++){
			String codeRow = "";
			double rawRow = rowStart + (rowLength+rowGap)*i;
			
			for(int j=0; j<numberOfCols; j++){
				double rawCol = colStart + (colLength+colGap)*j;

				int row = (int) (rawRow + rawCol*colShift);
				int col = (int) (rawCol + rawRow*rowShift);
				
				codeRow += getCode(row, col, rowLength, colLength, connectedPixelGroupPositions)+" ";
			}
			
			codeString += codeRow+"\n";
		}
		
		return codeString;
	}
	
	public String generateCodeString(ArrayList<int[]> connectedPixelGroupPositions){
		BrailleDotOrientationDetectorAdapter bDOD = 
				new BrailleDotOrientationDetectorAdapter(connectedPixelGroupPositions);

		return generateCodeString(bDOD.rowStart, bDOD.colStart, bDOD.rowLength, bDOD.colLength,
				bDOD.rowGap, bDOD.colGap, bDOD.numberOfRows, bDOD.numberOfCols,
				bDOD.rowShift, bDOD.colShift, connectedPixelGroupPositions);
	}
	
	//improvement: used `connectedPixelGroupPositions` can be removed and much more!
	private String getCode(int row, int col, int rowLength, int colLength, ArrayList<int[]> connectedPixelGroupPositions){
		String code = "";
		
		double rowSectionLength = (rowLength/rowSections);
		double colSectionLength = (colLength/colSections);
		
		for(int i=0; i<rowSections; i++){
			for(int j=0; j<colSections; j++){
				
				int rectX = (int) (col + (colSectionLength * j));
				int rectY = (int) (row + (rowSectionLength * i));
				
				code += codeInRect(rectX, rectY, (int)colSectionLength, (int)rowSectionLength,
						connectedPixelGroupPositions);
			}
		}
		
		return code;
	}
	
	private int codeInRect(int rectX, int rectY, int rectWidth, int rectHeight,
			ArrayList<int[]> connectedPixelGroupPositions){
		
		for(int[] connectedPixelGroupPosition: connectedPixelGroupPositions){
			if(pointInRect(rectX, rectY, rectWidth, rectHeight,
					connectedPixelGroupPosition[1], connectedPixelGroupPosition[0])){
				return 1;
			}
		}
		
		return 0;
	}
	
	private boolean pointInRect(int rectX, int rectY, int rectWidth, int rectHeight, int pointX, int pointY){
		if(rectX<pointX && rectY<pointY &&
				(rectX+rectWidth)>pointX && (rectY+rectHeight)>pointY){
			
			return true;
		}else{
			return false;
		}
	}
	
	//test only
	public static void main(String[] args) {
		ArrayList<int[]> connectedPixelGroupPositions = new ArrayList<int[]>();//dummy data
		String data = "57 37 65 900 65 958 65 1028 65 1082 65 1105 65 1158 65 1209 65 1239 65 1262" +
				" 65 1313 67 1185 83 1057 84 853 87 907 89 876 89 925 89 977 89 1083 89 1183 93 1340" +
				" 102 61 103 38 105 853 105 926 105 1025 107 1179 107 1236 110 1083 113 1346 121 33" +
				" 145 137 145 161 145 185 145 214 145 235 145 291 145 395 145 413 145 471 145 491 145" +
				" 514 145 572 145 1258 147 368 148 772 148 805 150 879 151 750 153 376 153 849 153 979" +
				" 153 1026 153 1106 153 1190 153 1234 153 1387 153 1412 155 198 160 289 161 137 164 193" +
				" 164 497 166 549 167 161 167 292 169 260 169 313 169 805 169 953 171 855 174 978 174" +
				" 1008 175 1081 175 1132 175 1234";
		String[] dataArray = data.split(" ");
		for(int i=0; i+1<dataArray.length; i++){
			connectedPixelGroupPositions.add(new int[]{
					Integer.parseInt(dataArray[i]), 
					Integer.parseInt(dataArray[i+1])
				} );
		}
		
		BrailleDotToCodeRecognizer brailleDotToCode = new BrailleDotToCodeRecognizer();
		String codeString = brailleDotToCode.generateCodeString(connectedPixelGroupPositions);
		System.out.println(codeString);
	}
}
