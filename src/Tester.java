import javax.swing.*;

public class Tester {

    static WavPlayer wavPlayer = new WavPlayer();

    public static void main(String[] args) {

        JFrame frame = new JFrame("Cheesy Asteroid Destroyer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GamePanel displayPanel = new GamePanel();

        frame.getContentPane().add(displayPanel);
        frame.pack();
        frame.setVisible(true);

        //This is just for fun. You can comment this out if you don't want to listen to Rasputin while testing
        wavPlayer.addNotify();
    }
}


/*Classes in separate files.  Do class divisions make sense? DONE-------------------------------------
Interface for MoveableObject, implemented by Rocket and Asteroid at a minimum DONE-=-------------------
Anonymous class for Keyboard Listeners DONE-------------------------------------------------------------
A Panel that extends JPanel DONE-----------------------------------------------------------------------
MouseListener as an Inner class, to shoot the laser. DONE --------------------------------------------
Timer that takes in a Lambda Function. DONE -----------------------------------------------------------
An Enum of AsteroidSize: Small, Medium, Large. DONE ---------------------------------------------------
Do asteroids move automatically? DONE---------------------------------------------------------
Does spaceship move with keyboard commands? DONE------------------------------------------------------
Does Laser "fire"? DONE -----------------------------------------------------------------------------
Do asteroids disappear after getting shot with the laser? DONE -----------------------------------------
Do more asteroids appear as the time goes on? DONE ----------------------------------------------------*/