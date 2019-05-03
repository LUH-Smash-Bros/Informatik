package datareader;

import java.util.Arrays;
import datareader.Data;

public class DataReader {

	public static int[] arrayOut(int[] pArrOut) {
		System.out.println("Matrixlänge: " + pArrOut.length);
		int counter = 1;

		for (int i = 0; i < pArrOut.length; i++) {
			if (counter / 4 == 1) {
				counter = 1;
				System.out.println(pArrOut[i] + " ");
			} else {
				counter++;
				System.out.print(pArrOut[i] + " ");
			}
		}

		return null;
	}
	
	public static void printData(Data[] d){
		//for(Data D : d){
		for(int i = 0; i < 1; i++){
			System.out.print(Arrays.deepToString(d[i].myTrainData));
			System.out.println(Arrays.toString(d[i].myTrainOutput));
		}
	}

	public static Data[] read(double[][] pMyTrainData, int[] pMyTrainOut) {
		Data[] myMergedArray = new Data[24];
		for (int i = 0; i < myMergedArray.length; i++) {
			myMergedArray[i] = new Data(pMyTrainData, pMyTrainOut);
		}
		//printData(myMergedArray);
		return myMergedArray;
	}	
}
