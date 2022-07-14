package mypackage;

import javax.swing.*;

public class Drink implements Produk {
    private String namaDrink;
    private ImageIcon imageDrink;
    private String descDrink;
    private double priceDrinkM, priceDrinkL;
    private Size size;

    Drink(String namaDrink, ImageIcon imageDrink, double priceM, double priceL, String deskripsiDrink){
        this.namaDrink = namaDrink;
        this.imageDrink = imageDrink;
        this.priceDrinkM = priceM;
        this.priceDrinkL = priceL;
        this.descDrink = deskripsiDrink;
    }

    @Override
    public String getName() {
        return this.namaDrink;
    }

    @Override
    public void setName(String name) {
        this.namaDrink = name;
    }

    @Override
    public ImageIcon getImage() {
        return this.imageDrink;
    }

    @Override
    public void setImage(ImageIcon image) {
        this.imageDrink = image;
    }

    @Override
    public String getDesc() {
        return this.descDrink;
    }

    @Override
    public void setDesc(String desc) {
        this.descDrink = desc;
    }

    @Override
    public Packing packing() {
        return new GelasPlastik();
    }

    @Override
    public double getPrice() {
        if (size.getSize().equals("Large"))
            return this.priceDrinkL;
        else
            return this.priceDrinkM;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Size getSize(){
        return this.size;
    }

    public void setPriceDrinkM(double priceDrinkM){
        this.priceDrinkM = priceDrinkM;
    }

    public void setPriceDrinkL(double priceDrinkL){
        this.priceDrinkL = priceDrinkL;
    }

}

