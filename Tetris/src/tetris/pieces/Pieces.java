/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.pieces;

import tetris.Field;
import tetris.menu.GameOverMenu;

/**
 *
 * @author atka
 */
public class Pieces {
    public Field field;
    public int x;
    public int y;
    public int[][] piece;
    public char type;
    
    public Pieces (Field field) {
        this.field = field;
        x = 4;
        y = 0;
    }
    
    public void move (int xx, int yy) {
        x += xx;
        y += yy;
    }
    
    public void rotate (int r) {
        int temp[][] = new int[piece.length][piece[0].length];
        int temp2[][] = new int[piece.length][piece[0].length];
        if (r == 1) {
            for (int i = 0; i < piece.length; i++) {
                for (int j = 0; j < piece[i].length; j++) {
                    temp[i][j] = temp2[i][j] = piece[i][j];
                }
            }
            for (int i = 0; i < piece.length; i++) {
                for (int j = 0; j < piece[i].length; j++) {
                    temp2[i][j] = temp[j][i];
                }
            }
            for (int i = 0; i < piece.length; i++) {
                for (int j = 0; j < piece[i].length; j++) {
                    temp[i][j] = temp2[i][j];
                }
            }
            for (int i = 0; i < piece.length; i++) {
                for (int j = 0; j < piece[i].length; j++) {
                    temp2[i][j] = temp[i][piece[i].length-1-j];
                }
            }
        } else {
            for (int i = 0; i < piece.length; i++) {
                for (int j = 0; j < piece[i].length; j++) {
                    temp[i][j] = temp2[i][j] = piece[i][j];
                }
            }
            for (int i = 0; i < piece.length; i++) {
                for (int j = 0; j < piece[i].length; j++) {
                    temp2[i][j] = temp[j][i];
                }
            }
            for (int i = 0; i < piece.length; i++) {
                for (int j = 0; j < piece[i].length; j++) {
                    temp[i][j] = temp2[i][j];
                }
            }
            for (int i = 0; i < piece.length; i++) {
                for (int j = 0; j < piece[i].length; j++) {
                    temp2[i][j] = temp[piece.length-1-i][j];
                }
            }
        }
        if (intersects(temp2)) {
            for (int i = 0; i < piece.length; i++) {
                for (int j = 0; j < piece[i].length; j++) {
                    piece[i][j] = temp2[i][j];
                }
            }
        }
    }
    
    public boolean intersects (int[][] temp2) {
        for (int i = 0; i < piece.length; i++) {
            for (int j = 0; j < piece[i].length; j++) {
                if (temp2[i][j] != 0) {
                    if (x+j < 0 || x+j >= 10 || y+i < 0 || y+i >= 20) {
                        return false;
                    }
                }
                if (temp2[i][j] != 0 && field.map[i+y][j+x] != 0) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public void isGameOver () {
        for (int i = 0; i < piece.length; i++) {
            for (int j = 0; j < piece[i].length; j++) {
                if (piece[i][j] != 0 && field.map[i+y][j+x] != 0) {
                    field.game.setMenu(new GameOverMenu(field.game, field.game.input, field.game.res.fontBlack));
                }
            }
        }
    }
}
