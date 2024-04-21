import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JPanel;
import java.io.File;
import java.io.IOException;

public class WavPlayer extends JPanel {

    public WavPlayer() {
        super();
    }

    @Override
    public void addNotify() {
        super.addNotify();
        playSound("Rasputin-8Bit.wav");
    }

    void playSound(String soundFilePath) {
        new Thread(() -> {
            try {
                File soundFile = new File(soundFilePath);
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
                Clip clip = AudioSystem.getClip();
                clip.open(audioIn);
                clip.start();
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
            }
        }).start();
    }
}