package perceptron;

import main.MainClass;

import java.util.Random;

import datareader.Data;

public class Perzeptron {
	
	private MainClass myMain1;
	
	private boolean firstEpoch = false;
	
	private double error = 0.0;
	
	private Random myWeightRandom = new Random();
		
	public int[] myFinishedResultsArray = new int[24];
	
	@SuppressWarnings("static-access")
	public Perzeptron(int pEpochs, double pLearningRate, double[] pWeightArray){
		pEpochs = myMain1.epochsPerRun;
		pLearningRate = myMain1.learnRate;
		pWeightArray = myMain1.myWeightArray;
	}
	
	@SuppressWarnings("static-access")
	public void train(Data[] pMergedArray){
		double myValue1 = 0.0, myValue2 = 0.0;
		
		Perzeptron.this.myMain1.myWeightArray[0] = myWeightRandom.nextDouble();
		Perzeptron.this.myMain1.myWeightArray[1] = myWeightRandom.nextDouble();
		
		//System.out.println(Perzeptron.this.myMain1.myWeightArray[0]);
		//System.out.println(Perzeptron.this.myMain1.myWeightArray[1]);
		
		while(Perzeptron.this.myMain1.epochsPerRun != 0){
			for(int i = 0; i < pMergedArray.length; i++){
				for(int j = 0; j < 2; j++){
					if(j == 0){
						myValue1 = pMergedArray[i].myTrainData[i][j];
					}
					else if(j == 1)
					{
						myValue2 = pMergedArray[i].myTrainData[i][j];
						myFinishedResultsArray[i] = output(myValue1, myValue2, Perzeptron.this.myMain1.myWeightArray);
						if(myFinishedResultsArray[i] == 0){
							System.out.println("Temperatur: " + myValue1 + "\t Niederschlag: " + myValue2 + " -> \t Gutes Wetter");
						}
						else
						{
							System.out.println("Temperatur: " + myValue1 + "\t Niederschlag: " + myValue2 + " -> \t Schlechtes Wetter");
						}
					}
				}
			}
			for(int i = 0; i < myFinishedResultsArray.length; i++){
				error += pMergedArray[i].myTrainOutput[i] - myFinishedResultsArray[i];
				//System.out.println(error);
			}
			for(int i = 0; i < pMergedArray.length; i++){
				for(int j = 0; j < Perzeptron.this.myMain1.myWeightArray.length; j++){
					Perzeptron.this.myMain1.myWeightArray[j] += Perzeptron.this.myMain1.learnRate * error * pMergedArray[i].myTrainData[i][j];
					System.out.println(Perzeptron.this.myMain1.myWeightArray[j]);
				}
			}
			Perzeptron.this.myMain1.epochsPerRun -= 1;
			if(!firstEpoch){
				System.out.println("Die erste Epoche ist vergangen! Übrig sind noch: " + Perzeptron.this.myMain1.epochsPerRun);
			}
			else
			{
				System.out.println("Eine weitere Epoche ist vergangen! Übrig sind noch: " + Perzeptron.this.myMain1.epochsPerRun);
			}
			firstEpoch = true;
			Perzeptron.this.accuracyOfPerceptron(myFinishedResultsArray, pMergedArray);
		}
	}
	
	private int output(double firstTrainDataValue, double secondTrainDataValue, double[] pWeightArray){
		int myValue1 = 0, myValue2 = 1;
		double myValue3 = firstTrainDataValue * pWeightArray[0] + secondTrainDataValue * pWeightArray[1];
		
		if(myValue3 >= myValue1){
			return myValue2;
		}
		else
		{
			return myValue1;
		}
	}
	
	public double accuracyOfPerceptron(int[] pResultArray, Data[] pMergedArray){
		int myValue1 = 1;
		double myValue2 = 0.0;
		for(int i = 0; i < pResultArray.length; i++){
			if(pResultArray[i] == pMergedArray[i].myTrainOutput[i]){
				myValue2 += myValue1 / pResultArray.length;
				System.out.println(myValue2);
			}
		}
		System.out.println("Derzeitige Erkennungsrate liegt bei: " + myValue2);
		return myValue2;
	}

}
