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
public class PieceJ extends Pieces {
    
    public PieceJ (Field field) {
        super(field);
        type = 'J';
        piece = new int[][]{
            {2,0,0},
            {2,2,2},
            {0,0,0}
        };
        isGameOver();
    }
}
