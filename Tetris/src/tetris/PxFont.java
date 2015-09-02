/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

import java.awt.Graphics;

/**
 *
 * @author atka
 */
public class PxFont {
    private Sprite fontset[] = new Sprite[256];
    public int px;
    
    public PxFont (String file, int px, int color[]) {
        this.px = px;
        int it = -1;
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                fontset[++it] = new Sprite(j*px, i*px, px, px, file);
                int r = color[0];
                int g = color[1];
                int b = color[2];
                int a = color[3];
                int col = (a << 24) | (r << 16) | (g << 8) | b;
                setColor(fontset[it], px*2, col);
            }
        }
    }
    
    private void setColor (Sprite spr, int size, int col) {
        int r = 255;
        int g = 0;
        int b = 0;
        int a = 255;
        int from = (a << 24) | (r << 16) | (g << 8) | b;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (spr.image.getRGB(i, j) == from) {
                    spr.image.setRGB(i, j, col);
                }
            }
        }
    }
    
    private Sprite getChar (char ch) {
        switch (ch) {
            case '0' : return fontset[0];
            case '1' : return fontset[1];
            case '2' : return fontset[2];
            case '3' : return fontset[3];
            case '4' : return fontset[4];
            case '5' : return fontset[5];
            case '6' : return fontset[6];
            case '7' : return fontset[7];
            case '8' : return fontset[8];
            case '9' : return fontset[9];
                
            case '.' : return fontset[10];
            case '!' : return fontset[11];
            case '?' : return fontset[12];
            case '-' : return fontset[13];
            case ',' : return fontset[14];
            case ' ' : return fontset[15];
                
            case 'A' : return fontset[16];
            case 'B' : return fontset[17];
            case 'C' : return fontset[18];
            case 'D' : return fontset[19];
            case 'E' : return fontset[20];
            case 'F' : return fontset[21];
            case 'G' : return fontset[22];
            case 'H' : return fontset[23];
            case 'I' : return fontset[24];
            case 'J' : return fontset[25];
            case 'K' : return fontset[26];
            case 'L' : return fontset[27];
            case 'M' : return fontset[28];
            case 'N' : return fontset[29];
            case 'O' : return fontset[30];
            case 'P' : return fontset[31];
            case 'Q' : return fontset[32];
            case 'R' : return fontset[33];
            case 'S' : return fontset[34];
            case 'T' : return fontset[35];
            case 'U' : return fontset[36];
            case 'V' : return fontset[37];
            case 'W' : return fontset[38];
            case 'X' : return fontset[39];
            case 'Y' : return fontset[40];
            case 'Z' : return fontset[41];
                
            case ':' : return fontset[43];
            case '/' : return fontset[44];
            case '(' : return fontset[45];
            case ')' : return fontset[46];
                
            case 'a' : return fontset[48];
            case 'b' : return fontset[49];
            case 'c' : return fontset[50];
            case 'd' : return fontset[51];
            case 'e' : return fontset[52];
            case 'f' : return fontset[53];
            case 'g' : return fontset[54];
            case 'h' : return fontset[55];
            case 'i' : return fontset[56];
            case 'j' : return fontset[57];
            case 'k' : return fontset[58];
            case 'l' : return fontset[59];
            case 'm' : return fontset[60];
            case 'n' : return fontset[61];
            case 'o' : return fontset[62];
            case 'p' : return fontset[63];
            case 'q' : return fontset[64];
            case 'r' : return fontset[65];
            case 's' : return fontset[66];
            case 't' : return fontset[67];
            case 'u' : return fontset[68];
            case 'v' : return fontset[69];
            case 'w' : return fontset[70];
            case 'x' : return fontset[71];
            case 'y' : return fontset[72];
            case 'z' : return fontset[73];
        }
        return fontset[42];
    }
    
    public void drawText (Graphics g, String text, int x, int y, int xOrientation, boolean panel) {
        String[] textRows = text.split(";");
        int rows = textRows.length;
        int longestRow = 0;
        for(int i = 0; i < textRows.length; i++){
            if(textRows[i].length() > longestRow){
                longestRow = textRows[i].length();
            }      
        }
        
        if (panel) {
            g.drawImage(fontset[80].image, 
                x + (-1*(px*2)) - (xOrientation * ((longestRow*(px*2))/2)), y + (-1*(px*2)), null);
            for (int i = 0; i < longestRow; i++) {
                g.drawImage(fontset[81].image, 
                    x + (i*px*2) - (xOrientation * ((longestRow*(px*2))/2)), y + (-1*(px*2)), null);
            }
            g.drawImage(fontset[82].image, 
                x + (longestRow*px*2) - (xOrientation * ((longestRow*(px*2))/2)), y + (-1*(px*2)), null);
            
            for (int i = 0; i < rows; i++) {
                g.drawImage(fontset[96].image, 
                    x + (-1*px*2) - (xOrientation * ((longestRow*(px*2))/2)), y + (i*(px*2)), null);
                for (int j = 0; j < longestRow; j++) {
                    g.drawImage(fontset[97].image, 
                        x + (j*px*2) - (xOrientation * ((longestRow*(px*2))/2)), y + (i*(px*2)), null);
                }
                g.drawImage(fontset[98].image, 
                    x + (longestRow*px*2) - (xOrientation * ((longestRow*(px*2))/2)), y + (i*(px*2)), null);
            }
            
            g.drawImage(fontset[112].image, 
                x + (-1*px*2) - (xOrientation * ((longestRow*(px*2))/2)), y + (rows*(px*2)), null);
            for (int i = 0; i < longestRow; i++) {
                g.drawImage(fontset[113].image, 
                    x + (i*px*2) - (xOrientation * ((longestRow*(px*2))/2)), y + (rows*(px*2)), null);
            }
            g.drawImage(fontset[114].image, 
                x + (longestRow*px*2) - (xOrientation * ((longestRow*(px*2))/2)), y + (rows*(px*2)), null);
            
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < textRows[i].length(); j++) {
                g.drawImage(getChar(textRows[i].charAt(j)).image, 
                    x + (j*px*2) - (xOrientation * ((textRows[i].length()*(px*2))/2)), y + (i*(px*2)), null);
            }
        }        
    }
}
