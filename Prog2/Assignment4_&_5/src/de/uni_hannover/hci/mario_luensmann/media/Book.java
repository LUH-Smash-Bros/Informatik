package de.uni_hannover.hci.mario_luensmann.media;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import de.uni_hannover.hci.mario_luensmann.main;
import de.uni_hannover.hci.mario_luensmann.isortable.ISortable;
import de.uni_hannover.hci.mario_luensmann.modes.SortMode;

public class Book implements ISortable {
	
	private String title;
	
	private String author;
	
	private String publisher;
	
	private String isbn_13;
	
	private int priceInt;
	
	private double priceDouble;
	
	public Book(String pTitle, String pAuthor, String pPublisher, String pISBN_13, int pPriceI, double pPriceD){
		this.title = pTitle;
		this.author = pAuthor;
		this.publisher = pPublisher;
		this.isbn_13 = pISBN_13;
		if(main.realism){
			this.priceDouble = pPriceD;
		}
		else{
			this.priceInt = pPriceI;
		}
	}

	@Override
	public String getSortString(SortMode mode) {
			
		switch(mode){
			case Name:
				String bookName = String.format("%1$s" + " - " + "%2$s" + " - " + "%3$s", getBookTitle(), getBookAuthor(), getBookPublisher());
				return bookName;
			case Price:
				if(main.realism){
					DecimalFormat df = new DecimalFormat("#.##");
					df.setRoundingMode(RoundingMode.CEILING);
					String bookPrice = String.format(df.format(getBookPriceDouble()) + " \u20AC");
					return bookPrice;
				}
				else{
					String bookPrice = String.format("%06d" + " \u20AC", getBookPriceInt());
					return bookPrice;
				}
			case ID:
				String bookID = String.format("%1$s", getBookISBN13());
				return bookID;
		}
		
		return null;
	}
	
	public String getBookTitle(){
		return this.title;
	}
	
	public String getBookAuthor(){
		return this.author;
	}
	
	public String getBookPublisher(){
		return this.publisher;
	}
	
	public String getBookISBN13(){
		return this.isbn_13;
	}
	
	public int getBookPriceInt(){
		return this.priceInt;
	}
	
	public double getBookPriceDouble(){
		return this.priceDouble;
	}

}
