/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris.pieces;

import tetris.Field;

/**
 *
 * @author atka
 */
public class PieceO extends Pieces {
    
    public PieceO (Field field) {
        super(field);
        type = 'O';
        piece = new int[][]{
            {4,4},
            {4,4}
        };
        isGameOver();
    }
}
