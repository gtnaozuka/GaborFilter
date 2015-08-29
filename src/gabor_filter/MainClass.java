package gabor_filter;

import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MainClass {

    private static final double STANDARD_DEVIATION = 1;
    private static final double ORIENTATION = Math.PI * 0.25;
    private static final double WAVE_LENGTH = 1;
    private static final double PHASE_OFFSET = 0;
    private static final double ASPECT_RATIO = 0.5;

    private static final String EXTENSION = "jpg";
    private static final String INPUT_PATH = "./src/images/fingerprint" + "." + EXTENSION;
    private static final String OUTPUT_PATH = "./src/images/Fingerprint/Aspect Ratio/";
    private static final String OUTPUT_FILENAME = String.valueOf(STANDARD_DEVIATION)
            + "|" + String.valueOf(ORIENTATION) + "|" + String.valueOf(WAVE_LENGTH)
            + "|" + String.valueOf(PHASE_OFFSET) + "|" + String.valueOf(ASPECT_RATIO)
            + "." + EXTENSION;

    public static void main(String[] args) {
        try {
            File file = new File(INPUT_PATH);
            BufferedImage originalImage = ImageIO.read(file);
            BufferedImage filteredImage = new BufferedImage(originalImage.getWidth(),
                    originalImage.getHeight(), originalImage.getType());

            GaborFilter gf = new GaborFilter(STANDARD_DEVIATION, ORIENTATION,
                    WAVE_LENGTH, PHASE_OFFSET, ASPECT_RATIO);
            gf.filter(originalImage, filteredImage);

            //displayImage(originalImage, "Imagem Original");
            //displayImage(filteredImage, "Imagem Filtrada");
            File output = new File(OUTPUT_PATH);
            if (!output.exists()) {
                output.mkdirs();
            }

            output = new File(OUTPUT_PATH + OUTPUT_FILENAME);
            ImageIO.write(filteredImage, EXTENSION, output);
        } catch (IOException ex) {
            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void displayImage(BufferedImage img, String title) {
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new FlowLayout());
        frame.getContentPane().add(new JLabel(new ImageIcon(img)));
        frame.pack();
        frame.setVisible(true);
    }
}
