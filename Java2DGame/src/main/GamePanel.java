package main;

import entity.Player;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    // Bildschirmeinstellungen

    // FPS pro Sekunde
    int FPS = 60;

    // Ursprüngliche Größe 16x16
    final int originalTileSize = 16;

    // Skaliere hoch für Bildschirme mit höherer Pixel dichte
    final int scale = 3;

    // Vergrößere auf 48x48
    public final int tileSize = originalTileSize * scale;

    // Spalten Anzahl für Raster Definieren
    final int maxScreenCol = 16;

    // Zeilen Anzahl für Raster Definieren
    final int maxScreenRow = 12;

    // Vorhandene Bildschirmgröße mit Fenstergröße verrechnen
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;


    KeyHandler keyH = new KeyHandler();

    Thread gameThread;

    Player player = new Player(this,keyH);

    // Anfangsposition von Spieler Bestimmen
    int playerX = 100;
    int playerY = 100;

    int playerSpeed = 4;

    public GamePanel() {

        // hängt von der Kachelgröße, der Spielfeldgröße und dem Skalierungsfaktor ab
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

    }

    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start();

    }

    @Override
    public void run() {
        double drawInterval = (double) 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;

            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;

            }

        }
    }

    @SuppressWarnings("PointlessBooleanExpression")
    public void update() {

        player.update();

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        player.draw(g2);

        g2.dispose();
    }
}
