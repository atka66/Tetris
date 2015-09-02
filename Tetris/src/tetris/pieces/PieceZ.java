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
public class PieceZ extends Pieces {
    
    public PieceZ (Field field) {
        super(field);
        type = 'Z';
        piece = new int[][]{
            {7,7,0},
            {0,7,7},
            {0,0,0}
        };
        isGameOver();
    }
}
