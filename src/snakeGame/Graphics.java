package snakeGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Graphics extends JPanel implements ActionListener {

    private Timer t = new Timer(100,this);
    String state;
    private Snake s;
    private Food f;
    private Game game;

    public Graphics(Game g) {
        t.start();
        state = "START";

        game = g;
        s = g.getPlayer();
        f = g.getFood();

        this.addKeyListener(g);
        this.setFocusable(true);
        this.setFocusTraversalKeysEnabled(false);

    }

    public void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, Game.width * Game.dimension + 5, Game.height * Game.dimension + 5);


        if (state=="START") {
            g2d.setColor(Color.white);
            g2d.drawString("Ойунду башташ учун каалаган тамганы басыныз ",Game.width/2*Game.dimension-40,Game.height/2*Game.dimension-20);
        }
        else if(state=="RUNNING") {
            g2d.setColor(Color.orange);
            g2d.fillRect(f.getX() * Game.dimension, f.getY() * Game.dimension, Game.dimension, Game.dimension);
            g2d.setColor(Color.blue);
            for (Rectangle r : s.getBody()) {
                g2d.fill(r);
            }
        }
        else {
            g2d.setColor(Color.white);
            g2d.drawString("УТУЛДУНУЗ! Сиздин упай:  "+(s.getBody().size()-3),Game.width/2*Game.dimension-40,Game.height/2*Game.dimension-20);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
        game.update();
    }
}
