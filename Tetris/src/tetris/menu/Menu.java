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
public class Menu {
	protected Game game;
	protected InputHandler input;
        protected PxFont font;

        public Menu (Game game, InputHandler input, PxFont font) {
		this.game = game;
		this.input = input;
                this.font = font;
	}

	public void tick() {
	}

	public void render(Graphics g) {
	}
}
