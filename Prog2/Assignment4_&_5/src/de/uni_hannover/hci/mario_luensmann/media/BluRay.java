package de.uni_hannover.hci.mario_luensmann.media;

import de.uni_hannover.hci.mario_luensmann.isortable.ISortable;
import de.uni_hannover.hci.mario_luensmann.modes.SortMode;
import de.uni_hannover.hci.mario_luensmann.main;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class BluRay implements ISortable {
	
	private String title;
	
	private String publisher;
	
	private String asinc;
	
	private int priceInt;
	
	private double priceDouble;
	
	public BluRay(String pTitle, String pPublisher, String pASIN_C, int pPriceI, double pPriceD){
		this.title = pTitle;
		this.publisher = pPublisher;
		this.asinc = pASIN_C;
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
				String bluRayName = String.format("%1$s" + " - " + "%2$s", getBluRayTitle(), getBluRayPublisher());
				return bluRayName;
			case Price:
				if(main.realism){
					DecimalFormat df = new DecimalFormat("#.##");
					df.setRoundingMode(RoundingMode.CEILING);
					String bluRayPrice = String.format(df.format(getBluRayPriceDouble()) + " \u20AC");
					return bluRayPrice;
				}
				else{
					String bluRayPrice = String.format("%06d" + " \u20AC", getBluRayPriceInt());
					return bluRayPrice;
				}
			case ID:
				String bluRayID = String.format("%1$s", getBluRayASINC());
				return bluRayID;
		}
		
		return null;
	}
	
	public String getBluRayTitle(){
		return this.title;
	}
	
	public String getBluRayPublisher(){
		return this.publisher;
	}
	
	public String getBluRayASINC(){
		return this.asinc;
	}
	
	public int getBluRayPriceInt(){
		return this.priceInt;
	}
	
	public double getBluRayPriceDouble(){
		return this.priceDouble;
	}
	
}
