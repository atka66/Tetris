/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;



/**
 *
 * @author atka
 */
public class Res {
    //Fonts
    public PxFont fontBlack;
    
    public Res () {
        System.out.println("Loading Resources...");
        
        System.out.println("  Fonts");
        fontBlack = new PxFont("font8px.png", 8, new int[] {0,0,0,255});
        
        System.out.println("Loading completed!");
    }
    
}
