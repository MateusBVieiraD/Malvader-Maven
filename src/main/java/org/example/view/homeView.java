package org.example.view;

import javax.swing.*;
import java.awt.*;

public class homeView extends JFrame {
    public homeView(){
        viewHome();
    }

    private void viewHome(){
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        JButton botaoCliente = new JButton();
        JButton botaoFuncionario = new JButton();
        JButton botaoSair = new JButton();

        frame.setName("Malavder Home");
        frame.setSize(800, 700);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);


        frame.setLayout(new FlowLayout());



        frame.setVisible(true);
    }


}
