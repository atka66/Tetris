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
public class PieceL extends Pieces {
    
    public PieceL (Field field) {
        super(field);
        type = 'L';
        piece = new int[][]{
            {0,0,3},
            {3,3,3},
            {0,0,0}
        };
        isGameOver();
    }
}
