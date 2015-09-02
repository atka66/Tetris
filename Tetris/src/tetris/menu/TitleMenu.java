package tetris.menu;

import java.awt.Color;
import java.awt.Graphics;
import tetris.Game;
import tetris.InputHandler;
import tetris.PxFont;

/**
 *
 * @author atka
 */
public class TitleMenu extends Menu {
        String titleMsg = "---;  Tetris  ;---";
        String continueMsg = "Press SPACEBAR/ENTER to Start";
        String helpMsg = "  Controls :;;"
            + "W, A, S, D / Arrow keys : Move;"
            + "Q, E : Rotate;"
            + "P : Pause;;Author: Atka;License: Free for any use";
        
        public TitleMenu (Game game, InputHandler input, PxFont font) {
            super(game, input, font);
        }
        
	public void tick() {
            if (input.accept.clicked) {
                game.startGame();
            }
            if (input.escape.clicked) {
                game.exitGame();
            }
	}

	public void render(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, game.windowWidth, game.windowHeight);
       
        font.drawText(g, titleMsg, (game.windowWidth / 2), 128, 1, true);
        font.drawText(g, continueMsg, game.windowWidth/2, game.windowHeight-64, 1, true);
        font.drawText(g, helpMsg, game.windowWidth/2, game.windowHeight/2, 1, true);
        }
}
