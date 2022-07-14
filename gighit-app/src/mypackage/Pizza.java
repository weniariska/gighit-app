package mypackage;

import javax.swing.*;

public class Pizza extends Food {
    private double pricePizzaM, pricePizzaL;
    private Size size;

    Pizza(String namaPizza, ImageIcon imagePizza, double priceM, double priceL, String descPizza){
        super(namaPizza,imagePizza,descPizza);
        this.pricePizzaM = priceM;
        this.pricePizzaL = priceL;
    }

    @Override
    public Packing packing() {
        return new PizzaBox();
    }

    @Override
    public double getPrice() {
        if (size.getSize().equals("Large"))
            return this.pricePizzaL;
        else {
            return this.pricePizzaM;
        }
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Size getSize(){
        return this.size;
    }

    public void setPricePizzaM(double pricePizzaM){
        this.pricePizzaM = pricePizzaM;
    }

    public void setPricePizzaL(double pricePizzaL){
        this.pricePizzaL = pricePizzaL;
    }

}
