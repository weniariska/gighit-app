package mypackage;

import javax.swing.*;

public interface Produk {

    public String getName();

    public void setName(String name);

    public ImageIcon getImage();

    public void setImage(ImageIcon image);

    public String getDesc();

    public void setDesc(String desc);

    public Packing packing();

    public double getPrice();

}
