package mypackage;

import javax.swing.*;

public class Main {
    public static void main(String[] args){

        // Frame
        JFrame frameUtama = new JFrame();
        frameUtama.setSize(1280,650);
        OrderMenu orderMenu = new OrderMenu();
        orderMenu.login(frameUtama);
        frameUtama.setVisible(true);
        frameUtama.setResizable(false);
        frameUtama.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameUtama.setLocationRelativeTo(null);
        frameUtama.setLayout(null);

    }
}
