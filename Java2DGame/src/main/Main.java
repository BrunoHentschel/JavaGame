package main;

import javax.swing.*;
import java.awt.*;

public class Main {


    public static void main(String[] args) {

        JFrame window = new JFrame();

        // Stoppe das Programm, wenn das Fenster Geschloßen wird
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        window.setResizable(false);
        window.setTitle("Java2DGame");

        // Spieloberfläche Hinzufügen
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        // passt die Größe des Fensters an dessen Inhalt an und validiert das Fenster
        window.pack();

        // Zentriere das Fenster
        window.setLocationRelativeTo(null);

        window.setVisible(true);

        gamePanel.startGameThread();

    }

}
