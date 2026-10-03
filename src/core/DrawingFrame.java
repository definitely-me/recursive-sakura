package core;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DrawingFrame extends JFrame {
    ArrayList<Drawing> drawings;
    private final int BACKGROUND_WIDTH = 1980;
    private final int BACKGROUND_HEIGHT = 1200;

    Color COLOR = new Color(221, 145, 145);

    public DrawingFrame(ArrayList<Drawing> drawings) {
        this.drawings = drawings;
        setTitle("fractal forest");
        setSize(BACKGROUND_WIDTH, BACKGROUND_HEIGHT);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        DrawingPanel panel = new DrawingPanel();
        add(panel);
        panel.setVisible(true);
        //pack();
    }

    public class DrawingPanel extends JPanel {
        public DrawingPanel() {
            setSize(new Dimension(BACKGROUND_WIDTH, BACKGROUND_HEIGHT));
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(COLOR);
            g2d.fillRect(0, 0, BACKGROUND_WIDTH, BACKGROUND_HEIGHT);
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            for (Drawing d : drawings) {
                d.draw(g2d);
            }
        }
    }
}
