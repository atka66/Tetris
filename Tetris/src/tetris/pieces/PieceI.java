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
public class PieceI extends Pieces {
    
    public PieceI (Field field) {
        super(field);
        type = 'I';
        piece = new int[][]{
            {0,0,0,0},
            {1,1,1,1},
            {0,0,0,0},
            {0,0,0,0}
        };
        isGameOver();
    }
    
}
