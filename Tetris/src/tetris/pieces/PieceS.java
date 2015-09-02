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
public class PieceS extends Pieces {
    
    public PieceS (Field field) {
        super(field);
        type = 'S';
        piece = new int[][]{
            {0,5,5},
            {5,5,0},
            {0,0,0}
        };
        isGameOver();
    }
}
