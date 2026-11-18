/***********************************************************
* Developer: Minhas Kamal (minhaskamal024@gmail.com)       *
* Date: 26-Oct-2016                                        *
* License: MIT License                                     *
***********************************************************/

package com.minhaskamal.brailleToTextTranslator.processes.preProcessing;

import java.io.File;
import com.minhaskamal.brailleToTextTranslator.processes.*;
import com.minhaskamal.brailleToTextTranslator.processes.preProcessing.process.PreProcessingRunner;
import com.minhaskamal.egamiLight.Matrix;
import com.minhaskamal.util.fileReadWrite.FilePreparer;

public class PreProcessingManager extends ProcessManager{
	public static final String PROCESS_EMPTY = "selct process"; 
	public static final String PROCESS_ENHANCEMENT = "enhancement"; 
	public static final String PROCESS_NOISE_REDUCTION = "noise-reduction"; 
	public static final String PROCESS_CONNECTIVITY_IMPROVEMENT = "connectivity-improvement"; 
	public static final String PROCESS_QUANTIZATION = "quantization"; 
	
	public static final String ALGORITHM_EMPTY = "selct algorithm"; 
	
	public static final String ENHA_HISTOGRAM_EQUALIZATION = "histogram-equalization"; 
	public static final String ENHA_BI_HISTOGRAM_EQUALIZATION = "bi-histogram-equalization"; 
	
	public static final String NOIS_GAUSSIAN_FILTER = "gaussian-filter"; 
	public static final String NOIS_MEAN_FILTER = "mean-filter"; 
	public static final String NOIS_MEDIAN_FILTER = "median-filter"; 
	
	public static final String CONN_EROSION = "erosion"; 
	public static final String CONN_DILATION = "dilation"; 
	
	public static final String QUAN_OTSU_THRESHOLDING = "otsu-thresholding"; 
	public static final String QUAN_K_MEANS_CLUSTERING = "k-means-clustering";
	
	////////////////////////////////////////////////////////

	private PreProcessingRunner preProcessRunner;
	
	public PreProcessingManager(UserSetting userSetting){
		super(userSetting);
		
		preProcessRunner = new PreProcessingRunner(userSetting.getUserInput(1));
	}
	
	@Override
	protected void manage() throws Exception{
		File file = new File(rootOutputDirectory);
		file.mkdirs();
		
		String[] inputImageNames = FilePreparer.getAllFileNames(rootInputDirectory, ACCEPTABLE_IMAGE_EXTENSIONS);
		
		for(int i=0; i<inputImageNames.length; i++){
			Matrix matrix = new Matrix(rootInputDirectory+"\\"+inputImageNames[i], Matrix.BLACK_WHITE);
			matrix = preProcessRunner.run(matrix);
			matrix.write(rootOutputDirectory+"\\"+inputImageNames[i]);
		}
	}
}
