/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import tetris.menu.PauseMenu;
import tetris.pieces.PieceI;
import tetris.pieces.PieceJ;
import tetris.pieces.PieceL;
import tetris.pieces.PieceO;
import tetris.pieces.PieceS;
import tetris.pieces.PieceT;
import tetris.pieces.PieceZ;
import tetris.pieces.Pieces;

/**
 *
 * @author atka
 */
public class Field {
    
    public Game game;
    public int offsetX;
    public int offsetY;
    public int[][] map = new int[20][10];
    public Pieces currentPiece;
    public Pieces nextPiece;
    public Random rand = new Random();
    public int point;
    
    public Field (Game game) {
        point = 0;
        this.game = game;
        offsetX = game.windowWidth/2 - (5*16);
        offsetY = game.windowHeight/2 - (10*16);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 20; j++) {
                map[j][i] = 0;
            }
        }
        
            switch (rand.nextInt(7)) {
                case 0 : {nextPiece = new PieceI(this); break;}
                case 1 : {nextPiece = new PieceJ(this); break;}
                case 2 : {nextPiece = new PieceL(this); break;}
                case 3 : {nextPiece = new PieceO(this); break;}
                case 4 : {nextPiece = new PieceS(this); break;}
                case 5 : {nextPiece = new PieceT(this); break;}
                case 6 : {nextPiece = new PieceZ(this); break;}
            }
    }

    public void tick () {
        if (game.input.escape.clicked) {
            game.endGame();
        }
        if (game.input.pause.clicked) {
            game.setMenu(new PauseMenu(game, game.input, game.res.fontBlack));
        }
        if (currentPiece == null) {
            trySpawn();
        } else {
            if (game.input.left.clicked) {
                if (!collision(-1, 0)) {
                    currentPiece.move(-1, 0);
                }
            }
            if (game.input.right.clicked) {
                if (!collision(1, 0)) {
                    currentPiece.move(1, 0);
                }
            }
            if (game.input.down.down) {
                if (!collision(0, 1)) {
                    currentPiece.move(0, 1);
                } else {
                    settlePiece();
                }
            }
            if (game.input.rotateR.clicked) {
                currentPiece.rotate(1);
            }
            if (game.input.rotateL.clicked) {
                currentPiece.rotate(0);
            }
            if (game.tickCount % 30 == 0) {
                if (!game.input.down.down) {
                    if (!collision(0, 1)) {
                        currentPiece.move(0, 1);
                    } else {
                        settlePiece();
                    }
                }
            }
        }
    }
    
    public void render (Graphics g) {
        g.setColor(Color.gray);
        g.fillRect(0, 0, game.windowWidth, game.windowHeight);
        renderCurrentMap(g);
        if (currentPiece != null) {
            renderCurrentPiece(g);
        }
        renderHud(g);
        if (nextPiece != null) {
            renderNextPiece(g);
        }
    }
    
    public Color getColor (int color) {
        switch (color) {
            case 1 : return Color.cyan;
            case 2 : return Color.blue;
            case 3 : return Color.orange;
            case 4 : return Color.yellow;
            case 5 : return Color.green;
            case 6 : return Color.magenta;
            case 7 : return Color.red;
            default : return Color.black;
        }
    }
    
    public void renderCurrentMap (Graphics g) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 20; j++) {
                g.setColor(getColor(map[j][i]));
                g.fillRect(i*16 + offsetX, j*16 + offsetY, 16, 16);
                g.setColor(Color.darkGray);
                g.drawRect(i*16 + offsetX, j*16 + offsetY, 16, 16);
            }
        }
    }
    
    public void renderCurrentPiece (Graphics g) {
        for (int i = 0; i < currentPiece.piece.length; i++) {
            for (int j = 0; j < currentPiece.piece[i].length; j++) {
                if (currentPiece.piece[i][j] != 0) {
                    g.setColor(getColor(currentPiece.piece[i][j]));
                    g.fillRect((j+currentPiece.x)*16 + offsetX, 
                            (i+currentPiece.y)*16 + offsetY, 16, 16);
                    g.setColor(Color.lightGray);
                    g.drawRect((j+currentPiece.x)*16 + offsetX, 
                            (i+currentPiece.y)*16 + offsetY, 16, 16);
                }
            }
        }
    }
    
    public void renderNextPiece (Graphics g) {
        for (int i = 0; i < nextPiece.piece.length; i++) {
            for (int j = 0; j < nextPiece.piece[i].length; j++) {
                if (nextPiece.piece[i][j] != 0) {
                    g.setColor(getColor(nextPiece.piece[i][j]));
                    g.fillRect(j*16 + 480, 
                            i*16 + 80, 16, 16);
                    g.setColor(Color.darkGray);
                    g.drawRect(j*16 + 480, 
                            i*16 + 80, 16, 16);
                }
            }
        }
    }
    
    public void renderHud (Graphics g) {
        game.res.fontBlack.drawText(g, " Score: ;;" + point, 96, 32, 1, true);
        game.res.fontBlack.drawText(g, " Next:;;;;;      ", 512, 32, 1, true);
    }
    
    public boolean collision (int xx, int yy) {
        for (int i = 0; i < currentPiece.piece.length; i++) {
            for (int j = 0; j < currentPiece.piece[i].length; j++) {
                if (currentPiece.piece[i][j] != 0) {
                    if (currentPiece.x+j+xx < 0 || currentPiece.x+j+xx >= 10 ||
                            currentPiece.y+i+yy < 0 || currentPiece.y+i+yy >= 20) {
                        return true;
                    }
                    if (map[currentPiece.y+i+yy][currentPiece.x+j+xx] != 0) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public void settlePiece () {
        for (int i = 0; i < currentPiece.piece.length; i++) {
            for (int j = 0; j < currentPiece.piece[i].length; j++) {
                if (currentPiece.piece[i][j] != 0) {
                    map[i+currentPiece.y][j+currentPiece.x] = currentPiece.piece[i][j];
                }
            }   
        }
        currentPiece = null;
        int rowsFull = 0;
        for (int i = 0; i < map.length; i++) {
            if (rowFull(i)) {
                rowsFull++;
                for (int ii = i; ii >= 1; ii--) {
                    for (int j = 0; j < map[ii].length; j++) {
                        map[ii][j] = map[ii-1][j];
                    }
                }
            }
        }
        switch (rowsFull) {
            case 1 : {point += 40; break;}
            case 2 : {point += 100; break;}
            case 3 : {point += 300; break;}
            case 4 : {point += 1200; break;}
        }
    }
    
    public boolean rowFull (int i) {
        for (int j = 0; j < map[i].length; j++) {
            if (map[i][j] == 0) {
                return false;
            }
        }
        return true;
    }
    
    public void trySpawn () {
            switch (nextPiece.type) {
                case 'I' : {currentPiece = new PieceI(this); break;}
                case 'J' : {currentPiece = new PieceJ(this); break;}
                case 'L' : {currentPiece = new PieceL(this); break;}
                case 'O' : {currentPiece = new PieceO(this); break;}
                case 'S' : {currentPiece = new PieceS(this); break;}
                case 'T' : {currentPiece = new PieceT(this); break;}
                case 'Z' : {currentPiece = new PieceZ(this); break;}
            }
            switch (rand.nextInt(7)) {
                case 0 : {nextPiece = new PieceI(this); break;}
                case 1 : {nextPiece = new PieceJ(this); break;}
                case 2 : {nextPiece = new PieceL(this); break;}
                case 3 : {nextPiece = new PieceO(this); break;}
                case 4 : {nextPiece = new PieceS(this); break;}
                case 5 : {nextPiece = new PieceT(this); break;}
                case 6 : {nextPiece = new PieceZ(this); break;}
            }
    }
}
