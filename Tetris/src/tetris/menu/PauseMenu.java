/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.menu;

import java.awt.Graphics;
import tetris.Game;
import tetris.InputHandler;
import tetris.PxFont;

/**
 *
 * @author atka
 */
public class PauseMenu extends Menu {
    String oofMsg = "---;PAUSED;---";
    String oofMsgEmpty = "   ;      ;   ";
    
    public PauseMenu (Game game, InputHandler input, PxFont font) {
        super(game, input, font);
    }
    
    public void tick () {
        if (input.accept.clicked || input.pause.clicked || input.escape.clicked) {
            game.setMenu(null);
        }
    }
    
    public void render (Graphics g) {
        if ((game.tickCount / 30) % 2 == 0) {
            game.res.fontBlack.drawText(g, oofMsg, game.windowWidth/2, (game.windowHeight/2)-32, 1, true);
        } else {
            game.res.fontBlack.drawText(g, oofMsgEmpty, game.windowWidth/2, (game.windowHeight/2)-32, 1, true);
        }
    }
}