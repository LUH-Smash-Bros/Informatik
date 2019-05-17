package de.uni_hannover.hci.mario_luensmann.Sort;

import java.util.ArrayList;
import java.util.Collections;

import de.uni_hannover.hci.mario_luensmann.isortable.ISortable;
import de.uni_hannover.hci.mario_luensmann.modes.SortMode;

public class Sort {
	
	static ArrayList<String> myArr = new ArrayList<String>();
	
	@SuppressWarnings("static-access")
	public static void Sort(SortMode pMode, ISortable[] pToSort){
		for(int i = 0; i < pToSort.length; i++){
			myArr.add(pToSort[i].getSortString(pMode));
		}
		Collections.sort(myArr);
		myArr.toArray(new String[]{});
		/*for(String strV: myArr){
			System.out.println(strV); /*Nur für Testzwecke und ja es 
			ist immer noch ein normales Array nur zwischenzeitlich nicht ;).
			Effizient nur vier Zeilen lang aber braucht noch die fünfte für die
			Konvertierung in ein stink normales String Array womit Ihr ja nur arbeitet.
		}*/
	}
	
}
