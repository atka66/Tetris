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
public class GameOverMenu extends Menu {
        String titleMsg = ";GAME OVER; ";
        
        public GameOverMenu (Game game, InputHandler input, PxFont font) {
            super(game, input, font);
        }
        
	public void tick() {
            if (input.accept.clicked || input.escape.clicked) {
                game.endGame();
            }
	}

	public void render(Graphics g) {
        font.drawText(g, titleMsg, 96, 128, 1, true);
        }
}
