package de.uni_hannover.hci.mario_luensmann.main;

import de.uni_hannover.hci.mario_luensmann.datareader.Data;
import de.uni_hannover.hci.mario_luensmann.datareader.DataReader;
import de.uni_hannover.hci.mario_luensmann.perceptron.Perzeptron;
import de.uni_hannover.hci.mario_luensmann.Matrix_Logic.Matrix;

public class MainClass {
	
	public static int epochsPerRun = 10;
	
	public static double learnRate = 0.5;
	
	private static int arrayLength = 2;
	
	public static double[][] theTrainData = new double[][]{{10.7f,6.1f},{4.9f,3.4f},{3.8f,5.9f},{1.7f,4.7f},
													{8.9f,2.6f},{8.9f,1.8f},{8.8f,1.9f},{13.4f,1.7f},
													{19.8f,0.0f},{18.9f,0.1f},{15.8f,0.0f},{12.1f,1.4f},
													{8.6f,3.8f},{5.8f,1.6f},{2.3f,2.9f},{3.3f,5.3f},
													{14.9f,0.5f},{18.8f,0.3f},{22.8f,0.0f},{27.3f,0.3f},
													{30.7f,0.0f},{30.6f,0.0f},{27.6f,0.0f},{23.2f,0.2f}};
	
	public static int[] theTrainOutput = new int[]{1,1,1,1,
												   1,1,1,1,
												   0,0,0,1,
												   1,1,1,1,
												   1,0,0,0,
												   0,0,0,0};
	
	
	public static double[] myWeightArray = new double[arrayLength];

	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		Data myData = new Data(theTrainData, theTrainOutput);
		
		Matrix.matOut(myData.myTrainData); 
		DataReader.arrayOut(myData.myTrainOutput);
		
		Perzeptron myPerzeptron = new Perzeptron(epochsPerRun, learnRate, myWeightArray);
		DataReader myDataReader = new DataReader();
		myPerzeptron.train(myDataReader.read(myData.myTrainData, myData.myTrainOutput));
		myPerzeptron.accuracyOfPerceptron(myPerzeptron.myFinishedResultsArray, myDataReader.read(myData.myTrainData, myData.myTrainOutput));
	}
}
