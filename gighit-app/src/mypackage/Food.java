package mypackage;

import javax.swing.*;

public abstract class Food implements Produk {
    private String name;
    private ImageIcon pict;
    private String desc;

    Food(String name, ImageIcon pict, String desc){
        this.name = name;
        this.pict = pict;
        this.desc = desc;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public ImageIcon getImage(){
        return this.pict;
    }

    public void setImage(ImageIcon image){
        this.pict = image;
    }

    public String getDesc(){
        return this.desc;
    }

    public void setDesc(String desc){
        this.desc = desc;
    }

    public abstract Packing packing();

    public abstract double getPrice();
}
