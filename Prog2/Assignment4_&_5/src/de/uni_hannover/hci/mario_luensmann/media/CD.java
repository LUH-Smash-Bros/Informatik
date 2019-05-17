package de.uni_hannover.hci.mario_luensmann.media;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import de.uni_hannover.hci.mario_luensmann.main;
import de.uni_hannover.hci.mario_luensmann.isortable.ISortable;
import de.uni_hannover.hci.mario_luensmann.modes.SortMode;

public class CD implements ISortable {
	
	private String albumTitle;
	
	private String musician;
	
	private String publisher;
	
	private String asinc;
	
	private int priceInt;
	
	private double priceDouble;
	
	public CD(String pAlbumTitle, String pMusician, String pPublisher, String pASINC, int pPriceI, double pPriceD){
		this.albumTitle = pAlbumTitle;
		this.musician = pMusician;
		this.publisher = pPublisher;
		this.asinc = pASINC;
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
				String CDName = String.format("%1$s" + " - " + "%2$s" + " - " + "%3$s", getCDTitle(), getCDMusician(), getCDPublisher());
				return CDName;
			case Price:
				if(main.realism){
					DecimalFormat df = new DecimalFormat("#.##");
					df.setRoundingMode(RoundingMode.CEILING);
					String CDPrice = String.format(df.format(getCDPriceDouble()) + " \u20AC");
					return CDPrice;
				}
				else{
					String CDPrice = String.format("%06d" + " \u20AC", getCDPriceInt());
					return CDPrice;
				}
			case ID:
				String CDID = String.format("%1$s", getCDASINC());
				return CDID;
		}
		
		return null;
	}
	
	public String getCDTitle(){
		return this.albumTitle;
	}
	
	public String getCDMusician(){
		return this.musician;
	}
	
	public String getCDPublisher(){
		return this.publisher;
	}
	
	public String getCDASINC(){
		return this.asinc;
	}
	
	public int getCDPriceInt(){
		return this.priceInt;
	}
	
	public double getCDPriceDouble(){
		return this.priceDouble;
	}

}
