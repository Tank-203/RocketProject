import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Asteroid implements MoveableShape {

    private ImageIcon asteroid;
    private JPanel canvas;
    private int x, y;
    private AsteroidSize[] sizes = AsteroidSize.values();
    private static final Random random = new Random();
    private String size;

    public Asteroid (JPanel canvas, int x, int y) {
        this.canvas = canvas;

        int randomIndex = random.nextInt(sizes.length);

        switch (sizes[randomIndex]) {
            case SMALL:
                asteroid = new ImageIcon("smallAsteroid.png"); // Small size, no change
                size = "small";
                break;
            case MEDIUM:
                asteroid = new ImageIcon("mediumAsteroid.png"); // Medium size, double the base size
                size = "medium";
                break;
            case LARGE:
                asteroid = new ImageIcon("largeAsteroid.png"); // Large size, triple the base size
                size = "large";
                break;
            default:
                asteroid = new ImageIcon("smallAsteroid.png"); // Default to small if undefined
                size = "small";
                break;
        }

        this.x = x; //canvas.getWidth()/2;
        this.y = y; //(int)(Math.random() * canvas.getHeight());
    }
    @Override
    public void draw(Graphics2D g2) {
        asteroid.paintIcon(canvas, g2, x, y);
    }

    @Override
    public void translate(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    public int getHeight() {
        return y;
    }

    public ImageIcon getAsteroid() {
        return asteroid;
    }

    public String getSize() {
        return size;
    }
}
