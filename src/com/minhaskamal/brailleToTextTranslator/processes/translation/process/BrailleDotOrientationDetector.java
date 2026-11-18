/***********************************************************
* Developer: Minhas Kamal (minhaskamal024@gmail.com)       *
* Date: 08-Nov-2016                                        *
* License: MIT License                                     *
***********************************************************/

package com.minhaskamal.brailleToTextTranslator.processes.translation.process;

import java.util.*;

import com.minhaskamal.egami.drawing.Drawer;
import com.minhaskamal.egamiLight.Matrix;

public class BrailleDotOrientationDetector {
	private static final int MAX_VAL = 1000;
	private static final int SUPPORT_DOT_COUNT = 5;
	
	private static final int AXIS = 2; //unchangable
	
	
	// denotes the starting point; the top left position of braille writing
	public int startX;
	public int startY;
	
	// distance between two dots in a char
	public double horizontalDotDistance;
	public double verticalDotDistance;
	
	// distance between two characters
	public double horizontalCharGap;
	public double verticalCharGap;
	
	public int numberOfRows; // number of lines in a document
	public int numberOfCols; // number of characters in a line
	
	// rotation in braille writing; positive value indicates clock
	// wise rotation & negative value indicates anti clock wise
	// rotation
	public double shift;
	
	
	/////////////////////////////////////////////////////////
	
	/**
	 * 
	 * @param dotPositions ArrayList<int[x, y]>
	 * @param dotDiameter
	 */
	public BrailleDotOrientationDetector(ArrayList<int[]> dotPositions, int dotDiameter) {
		initialize(dotPositions, dotDiameter);
		
	}
	
	private void initialize(ArrayList<int[]> dotPositions, int dotDiameter){
		//nearest dots to the Y & X axis
		ArrayList<ArrayList<int[]>> supportPointsYX = identifySupportPoints(dotPositions, dotDiameter);
		
		int[][] centerPoint = identifyCenterPoint(supportPointsYX, dotDiameter/2);
		//System.out.println(centerPoint[0][0]+" "+centerPoint[0][1]+","+centerPoint[1][0]+" "+centerPoint[1][1]);
		
		supportPointsYX = refineSupportPoints(supportPointsYX, centerPoint, dotDiameter*dotDiameter);
		
		int[][] dotDistanceVH = calculateActualDotDistance(supportPointsYX, dotDiameter);
		
		double s = calculateShift(supportPointsYX, centerPoint, dotDiameter/2);
		
		startX = centerPoint[0][0];//75
		startY = centerPoint[1][1];//72
		
		horizontalDotDistance = dotDistanceVH[1][0];//20
		verticalDotDistance = dotDistanceVH[0][0];//20
		
		horizontalCharGap = dotDistanceVH[1][1];//30
		verticalCharGap = dotDistanceVH[0][1];//45
		
		numberOfRows = 18;//26
		numberOfCols = 18;//30
		
		shift = s;//0.01;
		
		///////TEST///////
		Matrix matrix = new Matrix(System.getenv("SystemDrive")+System.getenv("HOMEPATH")+"\\Desktop\\pop.png",
				Matrix.RED_GREEN_BLUE);//
		for(int a=0; a<supportPointsYX.size(); a++){
			for(int i=0, temp; i<supportPointsYX.get(a).size(); i++){	// converts X|Y to row|col system
				temp = supportPointsYX.get(a).get(i)[0];
				supportPointsYX.get(a).get(i)[0] = supportPointsYX.get(a).get(i)[1];
				supportPointsYX.get(a).get(i)[1] = temp;
			}
		}
		Drawer.drawPoint(matrix, supportPointsYX.get(0), 4, 4, new int[]{200, 20, 20});//
		Drawer.drawPoint(matrix, supportPointsYX.get(1), 4, 4, new int[]{200, 20, 20});//
		
		ArrayList<int[]> center = new ArrayList<int[]>();
		center.add(new int[]{centerPoint[0][1], centerPoint[0][0]});
		center.add(new int[]{centerPoint[1][1], centerPoint[1][0]});
		Drawer.drawPoint(matrix, center, 4, 4, new int[]{20, 200, 20});//
		
		int c = (int) (centerPoint[1][1] - shift*centerPoint[1][0]);
		Drawer.drawLine(matrix, shift, c, new int[]{20, 20, 200});
		
		matrix.write(System.getenv("SystemDrive")+System.getenv("HOMEPATH")+"\\Desktop\\pop-supportpoints.png");//
	}
	

	/////////////////////////////////////////////////////////

	/**
	 * Filters raw support points.
	 * @param dotPositions
	 * @param probableDotDiameter
	 * @return
	 */
	private ArrayList<ArrayList<int[]>> identifySupportPoints(
			ArrayList<int[]> dotPositions, int dotDiameter) {
		
		int[][][] rawSupportPointsYX = identifyRawSupportPointsFromYXAxis(dotPositions, dotDiameter);
		
		ArrayList<ArrayList<int[]>> supportPointsYX = new ArrayList<ArrayList<int[]>>();
		
		for(int a=0; a<rawSupportPointsYX.length; a++){
			supportPointsYX.add(new ArrayList<int[]>());
			
			for(int i=0; i<rawSupportPointsYX[a].length; i++){
				
				if(rawSupportPointsYX[a][i][a]<MAX_VAL){
					supportPointsYX.get(a).add(rawSupportPointsYX[a][i]);
					//System.out.println(rawSupportPointsYX[a][i][0]+" "+rawSupportPointsYX[a][i][1]);
				}
			}
			//System.out.println("-----");
		}
		
		return supportPointsYX;
	}
	
	/**
	 * First horizontally divides the whole image into <code>MAX_VAL</code> sections.
	 * Each section breadth is <code>probableDotDiameter</code>. Now detects all points
	 * which are closest to the Y axis for each section. These points are called 
	 * <code>supportPoints</code> and the distances are called <code>supportDistances
	 * </code> <br/>
	 * The same thing is done vertically too.
	 * @param dotPositions 
	 * @param probableDotDiameter diameter of an expected dot
	 * @return 
	 */
	private int[][][] identifyRawSupportPointsFromYXAxis(
			ArrayList<int[]> dotPositions, int dotDiameter) {
		
		int[][][] rawSupportPointsYX = new int[AXIS][MAX_VAL][AXIS]; // [Y-X][sections][position]
		for(int a=0; a<rawSupportPointsYX.length; a++){
			for(int i=0; i<rawSupportPointsYX[a].length; i++){
				rawSupportPointsYX[a][i][a] = MAX_VAL;
			}
		}
		
		for(int[] dot: dotPositions){
			for(int a=0; a<rawSupportPointsYX.length; a++){
				int groupNumberAlongAxis = dot[1-a]/dotDiameter;
				
				if(rawSupportPointsYX[a][groupNumberAlongAxis][a] > dot[a]){
					rawSupportPointsYX[a][groupNumberAlongAxis] = dot.clone();
				}
			}
		}
		
		return rawSupportPointsYX;
	}
	
	
	/////////////////////////////////////////////////////////
	
	/**
	 * Among the support points, finds the point of smallest distance which also have a
	 * frequency of at least <code>SUPPORT_DOT_COUNT</code>.
	 * @param supportPointsYX
	 * @param probableDotRadius
	 * @return
	 */
	private int[][] identifyCenterPoint(ArrayList<ArrayList<int[]>> supportPointsYX, int dotDiameter) {
		
		int[][] freqOfSupportDistancesFromYX = calculatefreqOfSupportDistances(supportPointsYX, dotDiameter);
		/*for(int i=0; i<freqOfSupportDistancesFromYX.length; i++){
			for(int j=0; j<freqOfSupportDistancesFromYX[i].length; j++){
				System.out.println(freqOfSupportDistancesFromYX[i][j]);
			}
			System.out.println("---");
		}*/
		
		int[][] centerPoint = new int[AXIS][AXIS];
		for(int a=0; a<centerPoint.length; a++){
			centerPoint[a][a] = MAX_VAL;
		}
		
		for(int a=0; a<centerPoint.length; a++){
			for(int i=0; i<freqOfSupportDistancesFromYX[a].length; i++){
				
				if(freqOfSupportDistancesFromYX[a][i]>=SUPPORT_DOT_COUNT && 
						centerPoint[a][a]>supportPointsYX.get(a).get(i)[a]){
					
					centerPoint[a] = supportPointsYX.get(a).get(i).clone();
				}
			}
		}
		
		return centerPoint;
	}
	
	/**
	 * Finds distances of support points from the respective axis. Then calculates number  
	 * of other supporting points having the same distance. Distance is considered same if
	 * the difference is less than <code>probableDotRadious</code>.
	 * @param supportPointsYX
	 * @param probableDotRadious
	 * @return
	 */ // improvement: probableDotRadious/2 may be used for probableDotRadious  
	private int[][] calculatefreqOfSupportDistances(
			ArrayList<ArrayList<int[]>> supportPointsYX, int dotDiameter) {
		
		int[][] freqOfDistanceFromYX = new int[supportPointsYX.size()][];
		for(int a=0; a<freqOfDistanceFromYX.length; a++){
			freqOfDistanceFromYX[a] = new int[supportPointsYX.get(a).size()];
			
			for(int i=0; i<freqOfDistanceFromYX[a].length; i++){
				for(int j=0; j<freqOfDistanceFromYX[a].length; j++){
					int difference = Math.abs(supportPointsYX.get(a).get(i)[a]-supportPointsYX.get(a).get(j)[a]);
					
					if(difference<dotDiameter){
						freqOfDistanceFromYX[a][i]++;
					}
				}
			}
		}
		
		return freqOfDistanceFromYX;
	}
	
	
	/////////////////////////////////////////////////////////
	
	/**
	 * If a support point's distance from it's axis is significantly higher than
	 * the respective center point then remove that support point.
	 * @param supportPointsYX
	 * @param centerPoint
	 * @param threshold
	 * @return
	 */
	private ArrayList<ArrayList<int[]>> refineSupportPoints(ArrayList<ArrayList<int[]>> supportPointsYX,
			int[][] centerPoint, int threshold){
		
		for(int a=0; a<supportPointsYX.size(); a++){
			for(int i=0; i<supportPointsYX.get(a).size(); i++){
				
				int differenceFromCenterPoint = supportPointsYX.get(a).get(i)[a]-centerPoint[a][a];
				if(differenceFromCenterPoint>threshold || differenceFromCenterPoint<-threshold){
					//System.out.println(supportPointsYX.get(a).get(i)[0]+" "+supportPointsYX.get(a).get(i)[1]);
					supportPointsYX.get(a).remove(i);
					i--;
				}
			}
			//System.out.println("---"+supportPointsYX.get(a).size()+"---");
		}
		
		return supportPointsYX;
	}
	
	
	/////////////////////////////////////////////////////////
	
	private int[][] calculateActualDotDistance(
			ArrayList<ArrayList<int[]>> supportPointsYX, int dotDiameter){
		
		ArrayList<ArrayList<Integer>> distancesVH = calculateDotDistances(
				supportPointsYX, dotDiameter, 1);
		
		/*for(int a=0; a<distancesVH.size(); a++){
			for(int i=0; i<distancesVH.get(a).size(); i++){
				System.out.println(distancesVH.get(a).get(i));
			}
			System.out.println("***");
		}*/
		
		ArrayList<ArrayList<int[]>> distanceValuePairVH = calculateDistanceFrequancy(
				distancesVH, dotDiameter/8);

		/*for(int a=0; a<distanceValuePairVH.size(); a++){
			for(int i=0; i<distanceValuePairVH.get(a).size(); i++){
				System.out.println(distanceValuePairVH.get(a).get(i)[0]+" "+
						distanceValuePairVH.get(a).get(i)[1]);
			}System.out.println("---");
		}*/
		
		distanceValuePairVH = integrateNeighbouringFrequancies(distanceValuePairVH, 1);
		
		/*for(int a=0; a<distanceValuePairVH.size(); a++){
			for(int i=0; i<distanceValuePairVH.get(a).size(); i++){
				System.out.println(distanceValuePairVH.get(a).get(i)[0]+" "+
						distanceValuePairVH.get(a).get(i)[1]);
			}System.out.println("|||");
		}*/
		
		int[][] dotDistanceVH = findDistanceOfPeakFrequancy(distanceValuePairVH);
		
		for(int i=0; i<dotDistanceVH.length; i++){
			for(int j=0; j<dotDistanceVH[i].length; j++){
				System.out.println(dotDistanceVH[i][j]);
			}System.out.println();
		}
		
		return dotDistanceVH;
	}
	
	/**
	 * Calculates distances between all support points inside the <code>depth</code>.
	 * @param supportPointsYX
	 * @param probableDotDiameter
	 * @param depth
	 * @return
	 */
	private ArrayList<ArrayList<Integer>> calculateDotDistances(
			ArrayList<ArrayList<int[]>> supportPointsYX, int dotDiameter, int depth){
		
		ArrayList<ArrayList<Integer>> distancesVH = new ArrayList<ArrayList<Integer>>();
		
		for(int a=0; a<supportPointsYX.size(); a++){
			
			distancesVH.add(new ArrayList<Integer>());
			for(int i=depth; i<supportPointsYX.get(a).size(); i++){
				
				for(int d=1; d<=depth; d++){
					int distance = supportPointsYX.get(a).get(i)[1-a]-supportPointsYX.get(a).get(i-d)[1-a];
					if(distance>dotDiameter){
						distancesVH.get(a).add(distance);
					}
				}
			}
		}
		
		return distancesVH;
	}
	
	/**
	 * Counts frequency of dot distances; & sort them according to position, not
	 * on frequency.
	 * @param distancesVH
	 * @param dotFocus
	 * @return
	 */
	private ArrayList<ArrayList<int[]>> calculateDistanceFrequancy(
			ArrayList<ArrayList<Integer>> distancesVH, int dotFocus){
		
		ArrayList<ArrayList<int[]>> distanceFrequancyPairVH = new ArrayList<ArrayList<int[]>>();

		for(int a=0; a<distancesVH.size(); a++){
			
			distanceFrequancyPairVH.add(new ArrayList<int[]>());
			for(int i=0; i<distancesVH.get(a).size(); i++){
				
				boolean flag = false;
				int dist = distancesVH.get(a).get(i);
				for(int j=0; j<distanceFrequancyPairVH.get(a).size(); j++){
					int diff = dist-distanceFrequancyPairVH.get(a).get(j)[0];
					
					if(diff<dotFocus && diff>-dotFocus){
						distanceFrequancyPairVH.get(a).get(j)[1]++;
						if(diff==0){
							flag=true;
						}
					}
				}
				if(!flag){
					distanceFrequancyPairVH.get(a).add(new int[]{dist, 1});
				}
			}
		}
		
		Comparator<int[]> comparator = new Comparator<int[]>() {
		    @Override
		    public int compare(int[] c1, int[] c2) {
		        return c1[0]-c2[0];
		    }
		};
		for(int a=0; a<distanceFrequancyPairVH.size(); a++){
			Collections.sort(distanceFrequancyPairVH.get(a), comparator);
		}
		
		return distanceFrequancyPairVH;
	}
	
	/**
	 * Adds the previous and later frequency with the frequency of a distance.
	 * @param distanceFrequancyPairVH
	 * @return
	 */
	private ArrayList<ArrayList<int[]>> integrateNeighbouringFrequancies(
			ArrayList<ArrayList<int[]>> distanceFrequancyPairVH, int radious){
		
		int init = radious*2;
		for(int a=0; a<distanceFrequancyPairVH.size(); a++){
			
			int[] freq = new int[2];
			int dist;
			int temp = 0;
			for(int i=init; i<distanceFrequancyPairVH.get(a).size(); i++){
				
				dist = distanceFrequancyPairVH.get(a).get(i-radious)[0];
				freq[temp] = 0;
				for(int j=0; j<=init; j++){
					
					if(Math.abs(dist-distanceFrequancyPairVH.get(a).get(i-j)[0])<=radious){
						freq[temp] += distanceFrequancyPairVH.get(a).get(i-j)[1];
					}
				}
				
				temp = 1-temp;
				distanceFrequancyPairVH.get(a).get(i-2)[1] = freq[temp];
			}
		}
		
		return distanceFrequancyPairVH;
	}
	
	/**
	 * Peak point is defined by- the point which has more frequency than both the 
	 * point before and after it.
	 * @param distanceFrequancyPairVH
	 * @return
	 *///there should not be two peaks right beside; check that
	private int[][] findDistanceOfPeakFrequancy(
			ArrayList<ArrayList<int[]>> distanceFrequancyPairVH){
		
		int[][] dotDistanceVH = new int[AXIS][2];
		for(int a=0; a<distanceFrequancyPairVH.size(); a++){
			
			int b=0;
			for(int i=2; i<distanceFrequancyPairVH.get(a).size(); i++){
				if((distanceFrequancyPairVH.get(a).get(i-1)[1]-
						distanceFrequancyPairVH.get(a).get(i-2)[1]>0)
						&& 
						(distanceFrequancyPairVH.get(a).get(i-1)[1]-
						distanceFrequancyPairVH.get(a).get(i)[1]>0)){
					
					dotDistanceVH[a][b] = distanceFrequancyPairVH.get(a).get(i-1)[0];
					b++;
				}
				if(b>=dotDistanceVH[a].length){
					break;
				}
			}
		}
		
		return dotDistanceVH;
	}
	
	/////////////////////////////////////////////////////////
	
	/*private double calculateShift(
			ArrayList<ArrayList<int[]>> supportPointsYX, int[][] centerPoint, int dotRadious){
		
		double shift = 0;
		int maxPointsOnLine = 0;
		int minSumDistance = Integer.MAX_VALUE;
		
		double shiftLimit = 0.1;
		double increase = shiftLimit/100;
		for(double m=-shiftLimit; m<shiftLimit; m+=increase){
			double c = centerPoint[1][1]-centerPoint[1][0]*m;
			int pointsOnLine = 0;
			int sumDistance = 0;
			
			for(int i=0; i<supportPointsYX.get(1).size(); i++){
				int y = (int) (m*supportPointsYX.get(1).get(i)[0]+c);
				int distance = Math.abs(y-supportPointsYX.get(1).get(i)[1]);
				
				if(distance < dotRadious){
					sumDistance+=distance;
					pointsOnLine++;
				}
			}
			System.out.println(m+" "+pointsOnLine+" "+sumDistance);
			if(maxPointsOnLine<pointsOnLine || (maxPointsOnLine==pointsOnLine && minSumDistance>sumDistance)){
				shift = m;
				maxPointsOnLine = pointsOnLine;
				minSumDistance = sumDistance;
			}
		}
		
		System.out.println(maxPointsOnLine+" "+shift);
		return shift;
	}*/
	
	private double calculateShift(
			ArrayList<ArrayList<int[]>> supportPointsYX, int[][] centerPoint, int dotRadious){
		
		double shift = 0;
		int maxPointsOnLine = 0;
		int minSumDistance = Integer.MAX_VALUE;
		
		double shiftLimit = 0.01;
		double increase = shiftLimit/100;
		for(double m=-shiftLimit; m<shiftLimit; m+=increase){
			double c = centerPoint[0][0]-centerPoint[1][1]*m;
			int pointsOnLine = 0;
			int sumDistance = 0;
			
			for(int i=0; i<supportPointsYX.get(0).size(); i++){
				int x = (int) (m*supportPointsYX.get(0).get(i)[1]+c);
				int distance = Math.abs(x-supportPointsYX.get(0).get(i)[0]);
				
				if(distance < dotRadious){
					sumDistance+=distance;
					pointsOnLine++;
				}
			}
			System.out.println(m+" "+pointsOnLine+" "+sumDistance);
			if(maxPointsOnLine<pointsOnLine || (maxPointsOnLine==pointsOnLine && minSumDistance>sumDistance)){
				shift = m;
				maxPointsOnLine = pointsOnLine;
				minSumDistance = sumDistance;
			}
		}
		
		System.out.println(maxPointsOnLine+" "+shift);
		return shift;
	}
}
