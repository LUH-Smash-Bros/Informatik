/**
 * Die package Logik ist hier für das funzen des Programms nicht bedingend.
 */
package de.uni_hannover.hci.mario_luensmann.Matrix_Logic;

/**
 *  Nur dazu da um Randomisierte Werte für die Matrizen zu berechnen!
 */
import java.util.Random;

/**
 * Die Java Klasse mit dem Bezeichner eben dieser also Matrix!
 * @author Fox W. Mulder
 *
 */
public class Matrix {
	
	/**
	 *  Eine Ergebnismatrix initialisiert mit dem feststehenden Wert null der für alles stehen 
	 *  kann in java.
	 */
	static double resultMatrix[][]  = null;
	
	/**
	 * Die erste Methode zum berechnen von zwei Matrizen egal wie rum man Sie baut inklusive Abfangen von
	 * möglichen Fehlern. Wobei es gilt: m x n * n x m oder n x m * m x n, alles andere ist per Mathematik
	 * genauer LA zur Matrizenmultiplikation nicht erlaubt! Siehe Formeln in eurer Aufgabe. Ausgabe in
	 * resultMatrix.
	 * @param a
	 * @param b
	 * @return resultMatrix
	 */
	public static double[][] matMult(double[][] a, double[][] b){
		
		/**
		 * Eingabe von Zeilen und Spaltenwerten der jeweiligen Matrizen
		 * zum Abgleich über die jeweiligen If-Anweisungen und auch zur Nutzung
		 * innerhalb der fors.
		 */
		int zeilenLaengeM1 = a.length;
		int spaltenLaengeM1 = a[0].length;
		int zeilenLaengeM2 = b.length;
		int spaltenLaengeM2 = b[0].length;

		/**
		 * Abfangen von der möglichen Situation m x n * n x m
		 */
		if(spaltenLaengeM1 == zeilenLaengeM2){
			
			/**
			 * Erzeugen einer neuen Instanz von resultMatrix mit den jeweiligen Ausgabeparametern,
			 * also der Länge und Breite der Matrix
			 */
			resultMatrix = new double[zeilenLaengeM1][spaltenLaengeM2];
			
			/**
			 * Iteration über die jeweiligen Zeilen- und Spalten inklusive der Berechnung von
			 * m x n * n x m wobei m != m sein muss siehe die Formel in eurer Aufgabe!
			 * Also a_ik * b_kj bei der i != j ist.
			 */
			for(int i = 0; i < zeilenLaengeM1; i++){
				for(int j = 0; j < spaltenLaengeM2; j++){
					resultMatrix[i][j] = 0;
					for(int k = 0; k < spaltenLaengeM1; k++){
						resultMatrix[i][j] += a[i][k] * b[k][j];
					}
				}
			}
		}
		/**
		 * Dies hier checkt einfach den entgegengesetzten Fall ab falls, n x m * m x n vorkommt.
		 */
		else if(zeilenLaengeM1 == spaltenLaengeM2){
			
			/**
			 * siehe oben!
			 */
			resultMatrix = new double[spaltenLaengeM1][zeilenLaengeM2];
			
			/**
			 * siehe oben!
			 */
			for(int i = 0; i < spaltenLaengeM1; i++){
				for(int j = 0; j < zeilenLaengeM2; j++){
					resultMatrix[i][j] = 0;
					for(int k = 0; k < spaltenLaengeM1; k++){
						resultMatrix[i][j] += a[k][i] * b[j][k];
					}
				}
			}
		}
		/**
		 * Bei Fehlerausgabe da Dimensionalität nicht stimmt für Matrixmultiplikation
		 * kommt dieser Fehler vor!
		 */
		else
		{
			System.out.println("Matrizen haben nicht dieselbe Dimensionalität!");
		}
		/**
		 * Wenn alles klappt kommt dies hier raus!
		 */
		return resultMatrix;
	}
	
	/**
	 * Addition zweier Matrizen wobei jeweils Komponentenweise addiert wird, das heißt
	 * a_ij + b_ij = c_ij für jede einzelne Komponente innerhalb der Ergebnismatrix.
	 * Rest siehe dem Aufgabenzettel.
	 * @param a
	 * @param b
	 * @return resultMatrix
	 */
	public static double[][] matAdd(double[][] a, double[][] b){
		
		/**
		 * Analog siehe oben!
		 */
		int zeilenLaengeM1 = a.length;
		int spaltenLaengeM1 = a[0].length;
		int zeilenLaengeM2 = b.length;
		int spaltenLaengeM2 = b[0].length;
		
		/**
		 * Abfrage das beide Zeilen von beiden Matrizen gleich lang sind und das beide
		 * Spalten von beiden Matrizen ebenfalls gleich lang sind.
		 */
		if(zeilenLaengeM1 == zeilenLaengeM2 && spaltenLaengeM1 == spaltenLaengeM2){
			
			/**
			 * Ausgabe neu instantieren im Speicher
			 */
			resultMatrix = new double[zeilenLaengeM1][spaltenLaengeM2];
			
			/**
			 * Analog siehe oben nur diesmal braucht man die dritte for nicht,
			 * weil hier schlicht und ergreifend Komponentenweise addiert wird.
			 */
			for(int i = 0; i < zeilenLaengeM1; i++){
				for(int j = 0; j < spaltenLaengeM2; j++){
					resultMatrix[i][j] = 0;
					resultMatrix[i][j] += a[i][j] + b[i][j];
				}
			}
		}
		/**
		 * Ausgabe bei Ungleichheit eines der Bedingungen siehe oben
		 */
		else
		{
			System.out.println("Matrizen haben nicht dieselbe Dimensionalität!");
		}
		/**
		 * Ausgabe des Matrixergebnisses
		 */
		return resultMatrix;
	}
	
	/**
	 * Ausgabeformat für die Matrizen als Ausgabe über die Zeilen und Spalten der Matrizen.
	 * Funktioniert für ungleiche Matrizen leider noch nicht so gut wie ich es wollte aber naja.
	 * Heute Abend mach ich das nicht mehr!
	 * @param a
	 */
	public static double[][] matOut(double[][] a){
		
		/**
		 * Testausgabe zur Ausgabe der Zeilen und Spalten einer jeden Matrix die ausgegeben wird.
		 */
		System.out.println("Matrixlänge: " + a.length + " " + "Matrixhöhe: " + a[0].length);
		
		/**
		 * For Iterationen Komponentenweise jeweils zur Ausgabe der Matrizen in einer geordneten
		 * Struktur, also anhand von Zeilenzahl und Spaltenzahl.
		 */
		for(int i = 0; i < a.length; i++){
			for(int j = 0; j < a[0].length; j++){
				if(j == (a[0].length - 1)){
					System.out.println(a[i][j]);
				}
				else
				{
					System.out.print(a[i][j] + "\t");	
				}
			}
		}
		System.out.println();
		
		return null;
	}

	
	public static void main(String[] args) {
		/**
		 * Hiermit wird die zM1 Zeilenanzahl bei Matrix 1 zM2 analog nur für Matrix 2 und sM1
		 * Spaltenanzahl für Matrix 1 und analog sM2 für Matrix 2 mit festgelegt.
		 * @param zM1
		 * @param sM1
		 * @param zM2
		 * @param sM2
		 */
		int zM1 = 3;
		int sM1 = 2;
		int zM2 = 4;
		int sM2 = 3;
		
		/**
		 * Erzeugen von 2 Matrixinstanzen mit den jeweiligen Parametern siehe oben!
		 */
		double[][] myMatrix1 = new double[zM1][sM1];
		double[][] myMatrix2 = new double[zM2][sM2];
		
		/**
		 * Dient zur Befüllung von dem linken und dem rechten Grenzwert an Werten 
		 * die beide Matrizen aufnehmen sollen, wobei das d dahinter nur den double Typ kennzeichnet,
		 * also kein Hexadezimal seitens Javas technisch gesehen!
		 */
		double leftBoundary = 0d;
		double rightBoundary = 9d;
		
		/**
		 * Erstellung einer Random Instanz, das heißt hier haben wir ein Random Objekt,
		 * dass Methoden der Klasse java.util.random verwendet um Zufallszahlen als doubles
		 * in die Matrix zu packen und damit zu errechnen um damit nachher weiterzurechnen.
		 */
		Random arrayNumbers = new Random();
		
		/**
		 * If else zur Befüllung beider Matrizen jeweils Komponentenweise inklusive Ausgabe der zwei Matrizen
		 * und der Ausgabe der Ergebnisse von beiden statischen Methoden matMult() und matAdd().
		 */
		if(zM1 == zM2 && sM1 == sM2){ 
			for(int i = 0; i < myMatrix1.length; i++){
				for(int j = 0; j < myMatrix1[0].length; j++){
					myMatrix1[i][j] += leftBoundary + arrayNumbers.nextDouble() * (rightBoundary - leftBoundary);
				}
			}
			for(int i = 0; i < myMatrix2.length; i++){
				for(int j = 0; j < myMatrix2[0].length; j++){
					myMatrix2[i][j] += leftBoundary + arrayNumbers.nextDouble() * (rightBoundary - leftBoundary);
				}
			}
			matOut(myMatrix1);
			matOut(myMatrix2);
			matOut(matMult(myMatrix1, myMatrix2));
			matOut(matAdd(myMatrix1, myMatrix2));
		}
		else
		{
			for(int i = 0; i < myMatrix1.length; i++){
				for(int j = 0; j < myMatrix1[0].length; j++){
					myMatrix1[i][j] += leftBoundary + arrayNumbers.nextDouble() * (rightBoundary - leftBoundary);
				}
			}
			for(int i = 0; i < myMatrix2.length; i++){
				for(int j = 0; j < myMatrix2[0].length; j++){
					myMatrix2[i][j] += leftBoundary + arrayNumbers.nextDouble() * (rightBoundary - leftBoundary);
				}
			}
			matOut(myMatrix1);
			matOut(myMatrix2);
			matOut(matMult(myMatrix1, myMatrix2));
		}
	}
}