import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GamePanel extends JPanel {

    final int PANEL_WIDTH = 1000,
    PANEL_HEIGHT = 500,
    RIGHT = PANEL_WIDTH,
    LEFT = 32,
    TOP = 0,
    BOTTOM = PANEL_HEIGHT,
    MARGIN = 10;
    final int DELAY = 10;

    ArrayList<Asteroid> roids;
    Rocket rocket;

    GamePanel here;

    public boolean mousePressed = false;

    public GamePanel() {
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setBackground(Color.BLACK);
        here = this;

        rocket = new Rocket(this, 32, 250);
        roids = new ArrayList();



        //Infinitly spawning asteroids
        Timer spawnT = new Timer(DELAY + 2000, event -> {
            int tempY = (int)(Math.random() * (PANEL_HEIGHT / 5)) * 5;
            roids.add(new Asteroid(this, PANEL_WIDTH, tempY));
        });
        spawnT.start();

        // Moves the asteroids
        Timer t = new Timer(DELAY, event -> {
            for (Asteroid roid : roids) {
               roid.translate(-1, 0);
               here.repaint();
        }});
        t.start();



        // W & D Keyboard listener
        this.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {
            }

            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_W:
                        rocket.translate(0, -5); //Moves up by 5 pixels
                        break;
                    case KeyEvent.VK_S:
                        rocket.translate(0, 5); //Moves down by 5 pixels
                        break;
                }
                repaint();

            }

            public void keyReleased(KeyEvent e) {
            }
        });
        this.setFocusable(true);

        this.addMouseListener(new LaserListener());
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
    }

    //Enabled mousePressed when it detects we pressed the mouse
    private class LaserListener implements MouseListener {
        @Override
        public void mousePressed(MouseEvent e) {
            mousePressed = true;
            repaint();
        }
        @Override
        public void mouseReleased(MouseEvent e) {
            mousePressed = false;
            repaint();
        }
        @Override
        public void mouseClicked(MouseEvent e) { }
        @Override
        public void mouseEntered(MouseEvent e) { }
        @Override
        public void mouseExited(MouseEvent e) { }
    }

    //Mouse Inputs we don't care about. No need to expand  method
    private class NotListening implements MouseMotionListener {

        @Override
        public void mouseDragged(MouseEvent e) {

        }

        @Override
        public void mouseMoved(MouseEvent e) {

        }
    }

    // Draws the rocket once and automatically draws the asteroids as they spawn
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D)g;

        Image background = null;
        try {
            background = ImageIO.read(new File("background.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), this);

        for (Asteroid roid : roids) {
            roid.draw(g2);
        }

        rocket.draw(g2);

        //Paints the laser we see on the screen when we press mouse1
        if (mousePressed) {
            //Gets the rocket height to follow the rocket.
            int startLaserY = rocket.getHeight() + 15,
                    startLaserX = LEFT + MARGIN,
                    endLaserY = rocket.getHeight() + 15,
                    endLaserX = RIGHT - MARGIN;
            g.setColor(Color.BLACK);
            g.drawLine(startLaserX, startLaserY - 1, endLaserX, endLaserY - 1);
            g.drawLine(startLaserX, startLaserY + 1, endLaserX, endLaserY + 1);
            g.setColor(Color.ORANGE);
            g.drawLine(startLaserX, startLaserY, endLaserX, endLaserY);

            // COLLISION for the laser hitting the asteroid
            for (int i = 0; i < roids.size(); i++) {
                if (roids.isEmpty())
                    break;
                switch(roids.get(i).getSize()) {
                    case "small":
                        if (startLaserY == roids.get(i).getHeight() + 10) { // Hitbox for asteroid on size small
                            roids.remove(i);
                            repaint();
                        }

                        break;
                    case "medium":
                        if (startLaserY == roids.get(i).getHeight() + 30) { // Hitbox for asteroid on size medium
                            roids.remove(i);
                            repaint();
                        }

                        break;
                    case "large":
                        if (startLaserY == roids.get(i).getHeight() + 50) { // Hitbox for asteroid on size large
                            roids.remove(i);
                            repaint();
                        }

                        break;
                }
            }
        }
    }
}
