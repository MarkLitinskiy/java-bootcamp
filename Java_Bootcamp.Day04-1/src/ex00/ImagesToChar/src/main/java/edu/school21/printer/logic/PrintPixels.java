package edu.school21.printer.logic;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class PrintPixels {
    String path;
    BufferedImage image;
    int width;
    int height;

    public PrintPixels(String path){
        this.path = path;
        try {
            File input = new File(path);
            image = ImageIO.read(input);
            width = image.getWidth();
            height = image.getHeight();

            for(int i = 0; i < height; ++i) {
                for(int j = 0; j < width; ++j) {
                    Color c = new Color(image.getRGB(j, i));
                    if (c.getBlue() == 255 && c.getRed() == 255 && c.getGreen() == 255){
                        System.out.print('.');
                    } else {
                        System.out.print('0');
                    }
                }
                System.out.println();
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
