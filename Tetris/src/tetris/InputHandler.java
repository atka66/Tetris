/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author karfiol
 */
public class InputHandler implements KeyListener{

    public InputHandler(Game game) {
        game.requestFocus();
        game.addKeyListener(this);
    }
    
    
    public class Key {
		public int presses, absorbs;
		public boolean down, clicked;

		public Key() {
			keys.add(this);
		}

		public void toggle(boolean pressed) {
			if (pressed != down) {
				down = pressed;
			}
			if (pressed) {
				presses++;
			}
		}

		public void tick() {
			if (absorbs < presses) {
				absorbs++;
				clicked = true;
			} else {
				clicked = false;
			}
		}
	}

	public List<Key> keys = new ArrayList<>();

    public Key down = new Key();
    public Key left = new Key();
    public Key right = new Key();
    public Key accept = new Key();
    public Key escape = new Key();
    public Key rotateR = new Key();
    public Key rotateL = new Key();
    public Key pause = new Key();
    
    public void releaseAll() {
		for (int i = 0; i < keys.size(); i++) {
			keys.get(i).down = false;
		}
	}

	public void tick() {
		for (int i = 0; i < keys.size(); i++) {
			keys.get(i).tick();
		}
	}
    
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        toggleKey(e.getKeyCode(),true);
    }

    @Override
    public void keyReleased(KeyEvent e) {        
        toggleKey(e.getKeyCode(),false);
    }
    
    
    public void toggleKey(int KeyCode, boolean isPressed){
        if (KeyCode == KeyEvent.VK_S) down.toggle(isPressed);
        if (KeyCode == KeyEvent.VK_DOWN) down.toggle(isPressed);
        if (KeyCode == KeyEvent.VK_NUMPAD2) down.toggle(isPressed);
        if (KeyCode == KeyEvent.VK_A) left.toggle(isPressed);
        if (KeyCode == KeyEvent.VK_LEFT) left.toggle(isPressed);
        if (KeyCode == KeyEvent.VK_NUMPAD4) left.toggle(isPressed);
        if (KeyCode == KeyEvent.VK_D) right.toggle(isPressed);
        if (KeyCode == KeyEvent.VK_RIGHT) right.toggle(isPressed);
        if (KeyCode == KeyEvent.VK_NUMPAD6) right.toggle(isPressed);
        if (KeyCode == KeyEvent.VK_E) rotateR.toggle(isPressed);
        if (KeyCode == KeyEvent.VK_Q) rotateL.toggle(isPressed);
        if (KeyCode == KeyEvent.VK_ENTER) accept.toggle(isPressed);
        if (KeyCode == KeyEvent.VK_SPACE) accept.toggle(isPressed);
        if (KeyCode == KeyEvent.VK_ESCAPE) escape.toggle(isPressed);
        if (KeyCode == KeyEvent.VK_P) pause.toggle(isPressed);
    }
}
