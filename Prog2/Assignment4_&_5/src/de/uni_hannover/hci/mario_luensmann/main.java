package de.uni_hannover.hci.mario_luensmann;

import java.util.Scanner;

import de.uni_hannover.hci.mario_luensmann.IntRandomTree.IntRandTree;
import de.uni_hannover.hci.mario_luensmann.IntSearchableTree.IntSearchTree;
import de.uni_hannover.hci.mario_luensmann.Sort.Sort;
import de.uni_hannover.hci.mario_luensmann.isortable.ISortable;
import de.uni_hannover.hci.mario_luensmann.media.BluRay;
import de.uni_hannover.hci.mario_luensmann.media.Book;
import de.uni_hannover.hci.mario_luensmann.media.CD;
import de.uni_hannover.hci.mario_luensmann.modes.SortMode;

public class main {
	
	public static boolean realism;

	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		IntRandTree MyRandomizedTree = new IntRandTree(10, null, null);
		
		int[] MyArray = new int[]{3, 6, 16, 32, 7, 2, 54, 15};
		
		for(int AValue: MyArray){
			MyRandomizedTree.insert(AValue);
		}
	
		IntSearchTree MySearchTree = new IntSearchTree(10, null, null);
		
		for(int AValue: MyArray){
			MySearchTree.insert(AValue);
		}
		
		//System.out.println(MyRandomizedTree.toString());
		//System.out.println(MySearchTree.toString());
		
		Scanner s = new Scanner(System.in);
		System.out.println("Bitte booleschen Flag für Realismus setzen: ");
		realism = s.nextBoolean();
		s.close();
		
		if(realism)
		{
			BluRay myBluRay = new BluRay("Illuminati", "Sony Pictures Home Entertainment", "B002ACP13W", 0, 10.99);
			CD myCD = new CD("Fantastica 2000", "Various", "Polystar (Polystar)", "B00003W809", 0, 64.95);
			Book myBook = new Book("Das verlorene Symbol", "Dan Brown", "Bastei Lübbe; Auflage: 2013", "978-3404160006", 0, 9.99);
			
			myBluRay.getSortString(SortMode.Name);
			myBluRay.getSortString(SortMode.Price);
			myBluRay.getSortString(SortMode.ID);
						
			myCD.getSortString(SortMode.Name);
			myCD.getSortString(SortMode.Price);
			myCD.getSortString(SortMode.ID);
						
			myBook.getSortString(SortMode.Name);
			myBook.getSortString(SortMode.Price);
			myBook.getSortString(SortMode.ID);
		}
		else
		{
			BluRay myBluRayN = new BluRay("My_Blu-Ray", "Virgin Records", "100-555XYZ", 20, 0);
			BluRay myBluRay1 = new BluRay("foo", "bar", "fBar", 21, 0);
			BluRay myBluRay2 = new BluRay("bar", "foo", "fooB", 22, 0);
			BluRay myBluRay3 = new BluRay("Foo", "Bar", "FBar", 23, 0);
			BluRay myBluRay4 = new BluRay("Bar", "Foo", "Foob", 24, 0);
			BluRay myBluRay5 = new BluRay("mybar", "myfoo", "myfooB", 25, 0);
			BluRay myBluRay6 = new BluRay("myfoo", "mybar", "myfBar", 26, 0);
			BluRay myBluRay7 = new BluRay("myBar", "myFoo", "myFoob", 27, 0);
			BluRay myBluRay8 = new BluRay("Myfar", "Mybar", "MyfooB", 28, 0);
			BluRay myBluRay9 = new BluRay("Mybar", "Myfoo", "MyfBar", 29, 0);
			CD myCD = new CD("Good Vibes", "Rob Zombie", "The Gangster Club", "555-1857-367-HFPUSA", 55, 0);
			Book myBook = new Book("Langsam fällt mir echt nichts mehr ein", "meeeeee!", "Mein Herumspringer", "meine 13-stellige ISBN", 14, 0);
			
			ISortable[] mySortAble = new ISortable[]{myBluRayN, myBluRay1, myBluRay2, myBluRay3, myBluRay4, myBluRay5, myBluRay6, myBluRay7, myBluRay8, myBluRay9};
			
			Sort mySort = new Sort();
			
			mySort.Sort(SortMode.Name, mySortAble);
			
			myBluRayN.getSortString(SortMode.Name);
			myBluRayN.getSortString(SortMode.Price);
			myBluRayN.getSortString(SortMode.ID);
			
			myCD.getSortString(SortMode.Name);
			myCD.getSortString(SortMode.Price);
			myCD.getSortString(SortMode.ID);
			
			myBook.getSortString(SortMode.Name);
			myBook.getSortString(SortMode.Price);
			myBook.getSortString(SortMode.ID);
		}
		
	}

}
