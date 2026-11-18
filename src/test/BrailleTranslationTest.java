package test;

import java.util.ArrayList;

import com.minhaskamal.brailleToTextTranslator.processes.translation.process.*;
import com.minhaskamal.egami.connectedComponentAnalysis.ConnectedComponentLabeler;
import com.minhaskamal.egami.drawing.Drawer;
import com.minhaskamal.egamiLight.Matrix;
import com.minhaskamal.egamiLight.MatrixUtilities;

public class BrailleTranslationTest  extends BrailleToTextTranslatorTest{

	public static void main(String[] args) {
		new BrailleTranslationTest().runTest();
	}
	
	@Override
	public void testMethod() throws Exception {
		Matrix matrix = new Matrix(DESKTOP+"pop.png", Matrix.BLACK_WHITE);
		int minDotDiameter = 3, maxDotDiameter = 9;
		System.out.println("1");
		
		ConnectedComponentLabeler componentLabeler = new ConnectedComponentLabeler(matrix.clone());
		componentLabeler.extractPixelGroup(200, minDotDiameter, minDotDiameter, maxDotDiameter, maxDotDiameter);
		System.out.println("2");

		/*BrailleDotToCode brailleDotToCode = new BrailleDotToCode();
		String string = brailleDotToCode.generateCodeString(componentLabeler.getConnectedPixelGroupPositions());
		System.out.println("3");
		System.out.println(string);*/
		
		ArrayList<int[]> dotPositions = componentLabeler.getConnectedPixelGroupPositions();
		for(int i=0, temp; i<dotPositions.size(); i++){	// converts row|col to X|Y system
			temp = dotPositions.get(i)[0];
			dotPositions.get(i)[0] = dotPositions.get(i)[1];
			dotPositions.get(i)[1] = temp;
		}
		//TODO //probable dot diameter will be counted automatically
		BrailleDotOrientationDetector dotOrientationDetector = 
				new BrailleDotOrientationDetector(dotPositions, 
						(maxDotDiameter+minDotDiameter)/2);
		matrix = Drawer.drawGrid(matrix,
				dotOrientationDetector.startY, dotOrientationDetector.startX,
				dotOrientationDetector.verticalDotDistance*2, dotOrientationDetector.horizontalDotDistance, 
				dotOrientationDetector.verticalCharGap, dotOrientationDetector.horizontalCharGap, 
				dotOrientationDetector.numberOfRows, dotOrientationDetector.numberOfCols,
				-dotOrientationDetector.shift, dotOrientationDetector.shift,
				MatrixUtilities.getBlackPixel(matrix));
		matrix.write(DESKTOP+"opo.png");
	}

}
