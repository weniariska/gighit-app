package mypackage;

import javax.swing.*;

public class Starters extends Food {
    private double startersPrice;

    Starters(String startersName, ImageIcon startersImage, double startersPrice, String startersDesc){
        super(startersName,startersImage,startersDesc);
        this.startersPrice = startersPrice;
    }

    @Override
    public Packing packing() {
        return new PaperPackaging();
    }

    @Override
    public double getPrice() {
        return this.startersPrice;
    }

    public void setStartersPrice(double startersPrice){
        this.startersPrice = startersPrice;
    }
}

