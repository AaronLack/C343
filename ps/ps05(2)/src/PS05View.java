import java.awt.Color;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Random;
import javax.swing.JComponent;
import javax.swing.JFrame;


public class PS05View extends JComponent implements ActionListener {

    JFrame aJFrame;

    public PS05View() {
        aJFrame = new JFrame();
        aJFrame.add(this);
        aJFrame.setSize(1280, 1280);
        aJFrame.setVisible(true);
    }

    public void actionPerformed(ActionEvent event) {
        this.repaint();
    }

    public void paintComponent(Graphics colors) {
        super.paintComponent(colors);
        colors.setColor(Color.WHITE);
        Shape shape = colors.getClip();
        int width = shape.getBounds().width;
        int height = shape.getBounds().height;
        colors.fillRect(0, 0, width, height);
        Color startColor = new Color(255, 0, 0);
        colors.setColor(startColor);

        int x = 0;
        for (int i = -255; i < 1280; i++) {
            for (int j = 0; j < 1280; j++) {
                this.drawPoint(x, j, i);
            }
            x++;
        }

        for (int i = 0; i < 1280; i++) {
            for (int j = 0; j < 1280; j++) {
                this.clear();
            }
        }
    }

    //Method to draw the array, one point at a time.
    //loops through model class array and set colors of the points here. value of array
    public void drawPoint(int x, int y, int value) {
        //Coordinate, make new color, plug in (red, green, blue)
        //Create graphics objects here
        //Attach graphics object to jframe using jComponent.getGraphics
        //Then set color and fill rectangle
        if (value <= 255) {
            Color red = new Color(255, 0, 0);
            this.getGraphics().setColor(red);
            this.getGraphics().fillRect(x, y, 1, 1);
        } else if (value <= -1 && value >= -254) {
            Color darkRed = new Color(Math.abs(value), 0, 0);
            this.getGraphics().setColor(darkRed);
            this.getGraphics().fillRect(x, y, 1, 1);
        } else if (value == 0) {
            Color black = new Color(0, 0, 0);
            this.getGraphics().setColor(black);
            this.getGraphics().fillRect(x, y, 1, 1);
        } else if (value >= 1 && value <= 254) {
            Color darkGreen = new Color(0, Math.abs(value), 0);
            this.getGraphics().setColor(darkGreen);
            this.getGraphics().fillRect(x, y, 1, 1);
        } else if (value >= 255) {
            Color green = new Color(0, 255, 0);
            this.getGraphics().setColor(green);
            this.getGraphics().fillRect(x, y, 1, 1);
        }
    }

    //Clears entire content of array, sets the elements to black, set giant rectangle size 1280x1280 to black
    public void clear() {
        Color black = new Color(0, 0, 0);
        this.getGraphics().setColor(black);
        this.getGraphics().fillRect(0, 0, 1280, 1280);
        repaint();
    }
}