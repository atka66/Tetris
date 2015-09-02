/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

/**
 *
 * @author karfiol
 */
public class Sprite {
    Game game;
    public BufferedImage image = null;

    public Sprite(int imagex, int imagey, int width, int height, String file) {
        this.game = game;
        BufferedImage kep=null;
        
        try {   
             kep= ImageIO.read(Game.class.getResourceAsStream(file));
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (kep==null){
            return;
        }
        
        Image img = kep.getSubimage(imagex, imagey, width, height);
        
        image = toBufferedImageRescale(img);
    }
    
    public static BufferedImage toBufferedImageRescale(Image img)
    {
        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(img.getWidth(null)*2, img.getHeight(null)*2, BufferedImage.TYPE_INT_ARGB);

        
        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.scale(2, 2);
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();
        

        // Return the buffered image
        return bimage;
    }
    
    
}
