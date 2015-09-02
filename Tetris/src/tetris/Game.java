/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

import tetris.menu.TitleMenu;
import tetris.menu.Menu;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author atka
 */
public class Game extends Canvas implements Runnable {

    public final int windowWidth = 640;
    public final int windowHeight = 480;
    public final String windowTitle = "Tetris";

    private final JFrame frame;
    public Boolean running = false;
    public int tickCount = 0;
    public int fps;
    
    public InputHandler input;
    public Menu menu;
    public Res res;
    public Field field;
    
    public boolean shouldRender = true;
    
    String oofMsg = "PAUSED;;Click in to regain focus!";
    String oofMsgEmpty = "      ;;                         ";
    
    public Game() {
        setMinimumSize(new Dimension(windowWidth, windowHeight));
        setMaximumSize(new Dimension(windowWidth, windowHeight));
        setPreferredSize(new Dimension(windowWidth, windowHeight));

        frame = new JFrame(windowTitle);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        frame.add(this, BorderLayout.CENTER);
        frame.pack();

        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    public void setMenu(Menu menu) {
        this.menu = menu;
    }
    
    private synchronized void start() {
        running = true;
        new Thread(this).start();
    }

    private synchronized void stop() {
        running = false;
    }

    public void init() {
        input = new InputHandler(this); 
        res = new Res();
        setMenu(new TitleMenu(this, input, res.fontBlack));
    }
    
    public void startGame () {
        menu = null;
        field = new Field(this);
        tickCount = 0;
    }
    
    public void endGame () {
        menu = null;
        field = null;
        setMenu(new TitleMenu(this, input, res.fontBlack));
    }

    public void exitGame () {
        frame.dispose();
        running = false;
    }
    
    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double nsPerTick = 1000000000D / 60D;

        int ticks = 0;
        int frames = 0;

        long lastTimer = System.currentTimeMillis();
        double delta = 0;

        init();

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / nsPerTick;
            lastTime = now;

            while (delta >= 1) {
                ticks++;
                tick();
                delta -= 1;

            }

            try {
                Thread.sleep(2);
            } catch (InterruptedException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null,
                        ex);
            }

            if (shouldRender) {
                render();
                frames++;

            }
            if (System.currentTimeMillis() - lastTimer >= 1000) {
                lastTimer += 1000;
                System.out.println("Frames: " + frames + ", Ticks: " + ticks);
                fps = frames;
                frames = 0;
                ticks = 0;

            }
        }
    }

    public void tick() {
        tickCount++;
        if (!hasFocus()) {
            input.releaseAll();
        } else {
            input.tick();
            if (menu != null) {
                menu.tick();
            } else {
                field.tick();
            }
        }
    }

    public static void main(String[] args) {
        new Game().start();
    }

    public void render() {
        if (!hasFocus()) {
            renderOutOfFocus();
        } else {
            BufferStrategy bs = getBufferStrategy();
            if (bs == null) {
                createBufferStrategy(3);
                return;
            }
            Graphics g = bs.getDrawGraphics();
            
            if (menu != null) {
                menu.render(g);
            } else {
                //field.shouldrender=true;
                field.render(g);
            }
            g.dispose();
            bs.show();
        }
    }

    private void renderOutOfFocus() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        //Out of Focus Message
        if ((tickCount / 30) % 2 == 0) {
            res.fontBlack.drawText(g, oofMsg, windowWidth/2, (windowHeight/2)-32, 1, true);
        } else {
            res.fontBlack.drawText(g, oofMsgEmpty, windowWidth/2, (windowHeight/2)-32, 1, true);
        }

        g.dispose();
        bs.show();
    }
}

