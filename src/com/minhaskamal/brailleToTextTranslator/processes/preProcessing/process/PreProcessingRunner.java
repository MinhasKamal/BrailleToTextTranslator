/***********************************************************
* Developer: Minhas Kamal (minhaskamal024@gmail.com)       *
* Date: 28-Oct-2016                                        *
* License: MIT License                                     *
***********************************************************/

package com.minhaskamal.brailleToTextTranslator.processes.preProcessing.process;

import java.util.ArrayList;
import com.minhaskamal.brailleToTextTranslator.processes.UserInput;
import com.minhaskamal.brailleToTextTranslator.processes.preProcessing.PreProcessingManager;
import com.minhaskamal.egami.filtering.ConvolutionRunner;
import com.minhaskamal.egami.filtering.MedianFilterRunner;
import com.minhaskamal.egami.morphologicalOperation.MorphologicalOperator;
import com.minhaskamal.egami.quantization.ColorQuantizer;
import com.minhaskamal.egami.utilityAlgorithms.ClusterMaker;
import com.minhaskamal.egami.utilityAlgorithms.GlobalThresholdCalculator;
import com.minhaskamal.egamiLight.Matrix;
import com.minhaskamal.egamiLight.MatrixUtilities;

public class PreProcessingRunner {
	private ArrayList<PreProcessor> preProcessors;
	
	
	//////////////////////////////////////////////
	
	public PreProcessingRunner(UserInput userInput){
		preProcessors = new ArrayList<PreProcessingRunner.PreProcessor>();
		constructPreProcesses(userInput);
	}
	
	
	//////////////////////////////////////////////
	
	public Matrix run(Matrix matrix){
		for(PreProcessor preProcessor: preProcessors){
			matrix = preProcessor.run(matrix);
		}
		return matrix;
	}
	
	
	//////////////////////////////////////////////
	
	private void constructPreProcesses(UserInput userInput){
		int numberOfCommands = userInput.getNumberOfCommands();
		for(int i=0; i<numberOfCommands; i++){
			PreProcessor preProcessor = createPreProcess(userInput.getProcess(i), userInput.getArgument(i));
			preProcessors.add(preProcessor);
		}
	}
	
	private PreProcessor createPreProcess(String process, String argument){
		if(process.equals(PreProcessingManager.PROCESS_ENHANCEMENT)){
			if(argument.equals(PreProcessingManager.ENHA_HISTOGRAM_EQUALIZATION)){
				return new HistogramEqualizer();
			}else if(argument.equals(PreProcessingManager.ENHA_BI_HISTOGRAM_EQUALIZATION)){
				return new BiHistogramEqualizer();
			}
		}else if(process.equals(PreProcessingManager.PROCESS_NOISE_REDUCTION)){
			if(argument.equals(PreProcessingManager.NOIS_GAUSSIAN_FILTER)){
				return new GaussianFilter();
			}else if(argument.equals(PreProcessingManager.NOIS_MEAN_FILTER)){
				return new MeanFilter();
			}else if(argument.equals(PreProcessingManager.NOIS_MEDIAN_FILTER)){
				return new MedianFilter();
			}
		}else if(process.equals(PreProcessingManager.PROCESS_CONNECTIVITY_IMPROVEMENT)){
			if(argument.equals(PreProcessingManager.CONN_DILATION)){
				return new Dialation();
			}else if(argument.equals(PreProcessingManager.CONN_DILATION)){
				return new Erosion();
			}
		}else if(process.equals(PreProcessingManager.PROCESS_QUANTIZATION)){
			if(argument.equals(PreProcessingManager.QUAN_OTSU_THRESHOLDING)){
				return new OtsuThresholding();
			}else if(argument.equals(PreProcessingManager.QUAN_K_MEANS_CLUSTERING)){
				return new kMeansClustering();
			}
		}
		
		return new EmptyPreProcessor();
	}
	
	
	//////////////////////////////////////////////
	
	private interface PreProcessor {
		public abstract Matrix run(Matrix matrix);
	}
	private class EmptyPreProcessor implements PreProcessor{
		public Matrix run(Matrix matrix){
			return matrix;
		}
	}
	private class HistogramEqualizer implements PreProcessor{
		public Matrix run(Matrix matrix){
			return com.minhaskamal.egami.contrastEnhancement.HistogramEqualizer.histogramEqualizer(matrix);
		}
	}
	private class BiHistogramEqualizer implements PreProcessor{
		public Matrix run(Matrix matrix){
			return com.minhaskamal.egami.contrastEnhancement.HistogramEqualizer.biHistogramEqualizer(matrix);
		}
	}
	private class GaussianFilter implements PreProcessor{
		public Matrix run(Matrix matrix){
			return ConvolutionRunner.applyMask(matrix, ConvolutionRunner.getGaussianFilter(), true);
		}
	}
	private class MeanFilter implements PreProcessor{
		public Matrix run(Matrix matrix){
			return ConvolutionRunner.applyMask(matrix, ConvolutionRunner.getMeanFilter(3), true);
		}
	}
	private class MedianFilter implements PreProcessor{
		public Matrix run(Matrix matrix){
			return MedianFilterRunner.applyMedianFilter(matrix, 3);
		}
	}
	private class Erosion implements PreProcessor{
		public Matrix run(Matrix matrix){
			return MorphologicalOperator.erode(matrix, 1, 150);
		}
	}
	private class Dialation implements PreProcessor{
		public Matrix run(Matrix matrix){
			return MorphologicalOperator.dilate(matrix, 1, 150);
		}
	}
	private class OtsuThresholding implements PreProcessor{
		public Matrix run(Matrix matrix){
			int[] vector1d = vectorize(matrix);
			int threshold = GlobalThresholdCalculator.getOtsuThreshold(vector1d);
			return ColorQuantizer.convertToBinary(matrix, threshold);
		}
		
		protected int[] vectorize(Matrix matrix){
			int[][] vector2d = MatrixUtilities.vectorize(matrix);
			int[] vector1d = new int[vector2d.length];
			for(int i=0; i<vector1d.length; i++){
				vector1d[i] = vector2d[i][0];
			}
			return vector1d;
		}
	}
	private class kMeansClustering implements PreProcessor{
		public Matrix run(Matrix matrix){
			int[][] vectorMatrix = applyClustering(matrix);
			return MatrixUtilities.createMatrix(vectorMatrix, matrix.getRows(), matrix.getCols());
		}
		
		private int[][] applyClustering(Matrix matrix){
			int[][] dataPoints = MatrixUtilities.vectorize(matrix);
			int[][] clusterMeans = ClusterMaker.kMeansClustering(dataPoints, 2);
			int[] pointInClusters = ClusterMaker.assignPointToCluster(dataPoints, clusterMeans);
			
			for(int i=0; i<dataPoints.length; i++){
				for(int j=0; j<dataPoints[0].length; j++){
					dataPoints[i] = clusterMeans[pointInClusters[i]].clone();
				}
			}
			
			return dataPoints;
		}
	}
}
