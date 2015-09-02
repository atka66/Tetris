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
public class PieceT extends Pieces {
    
    public PieceT (Field field) {
        super(field);
        type = 'T';
        piece = new int[][]{
            {0,6,0},
            {6,6,6},
            {0,0,0}
        };
        isGameOver();
    }
}
