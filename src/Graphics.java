import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Graphics extends JPanel implements ActionListener {

    private Timer t = new Timer(100, this);
    public String state;

    private Snake s;
    private Food f;
    private Game game;

    private int w = Game.width;
    private int h = Game.height;
    private int d = Game.dimension;


    public Graphics(Game g){
        t.start();
        state = "START";

        game = g;
        s = g.getPlayer();
        f = g.getFood();


        // Adding Key Listener.
        this.addKeyListener(g);
        this.setFocusable(true);
        this.setFocusTraversalKeysEnabled(false);
    }

    public void paintComponent(java.awt.Graphics g){
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, w*d, h*d);

        if(state == "START"){
            g2d.setColor(Color.white);
            g2d.drawString("Press any key to continue...", w / 2 * d, h / 2 * d);
        }else if(state == "RUNNING"){
            g2d.setColor(Color.red);
            g2d.fillRect(f.getX() * d, f.getY() * d, d, d);

            g2d.setColor(Color.green);
            for(Rectangle r: s.getBody()) {
                g2d.fill(r);
            }
        } else {
            g2d.setColor(Color.white);
            g2d.drawString("Your Score: " + (s.getBody().size() - 3), w/2*d - 40, h/2*d - 20);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();

        game.update();
    }
}
