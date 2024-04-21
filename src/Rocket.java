import javax.swing.*;
import java.awt.*;

public class Rocket implements MoveableShape {

    private ImageIcon rocket;
    private ImageIcon laser;
    private JPanel canvas;
    private int x, y;

    public Rocket (JPanel canvas, int x, int y) {
        this.canvas = canvas;
        rocket = new ImageIcon("rocket.png");
        laser = new ImageIcon("laser.png");
        this.x = x; //canvas.getWidth()/2;
        this.y = y; //(int)(Math.random() * canvas.getHeight());
    }
    @Override
    public void draw(Graphics2D g2) {
        rocket.paintIcon(canvas, g2, x, y);
    }

    @Override
    public void translate(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    public int getHeight() {
        return y;
    }
}
