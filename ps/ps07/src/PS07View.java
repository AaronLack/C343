import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PS07View extends JComponent implements ActionListener {
    static char[] vowels = {'a', 'e', 'i', 'o', 'u'};
    static char[] consonants = {'b','c','d','f','g','h','j','k','l','m','n','p','q','r','s','t','v','w','x',
            'z'};
    JFrame aJFrame;

    public PS07View() {
        aJFrame = new JFrame();
        aJFrame.add(this);
        aJFrame.setSize(1280, 1280);
        aJFrame.setVisible(true);
    }

    //Not 100% sure if I need this or not.
    public void actionPerformed(ActionEvent event) {
        this.repaint();
    }

    //Draws each point on jFrame one at a time
    //The implementation of your drawPoint() method should draw a single pixel at coordinates x,y,
    //colored according to the three parameters r, g, and b. Your drawPoint()
    //method needs to truncate the r, g, and b values, so that any values smaller than 0 are set to 0,
    //and any values larger than 255 are truncated to 255.
    public void drawPoint(int x, int y, int r, int g, int b) {
        //A list of if conditions to truncate r,g,b values to make sure they aren't over 255 and under 0.
        if(r > 255) {
            r = 255;
        }
        if (r < 0) {
            r = 0;
        }
        if(g > 255) {
            g = 255;
        }
        if (g < 0) {
            g = 0;
        }
        if(b > 255) {
            b = 255;
        }
        if (b < 0) {
            b = 0;
        }

        Graphics graphics = getGraphics();
        Color color = new Color(r, g, b);
        graphics.setColor(color);
        graphics.fillRect(0, 0, 1, 1);
        graphics.dispose();
    }

    //method to set entire jFrame to r = 127, g = 127, and b = 127
    public void clear() {
        Color clear = new Color(127,127,127);
        this.getGraphics().setColor(clear);
        this.getGraphics().fillRect(0,0,1280,1280);
        this.repaint();
    }

    //Call these methods to check if there are vowels or consonants.
    public static boolean isVowel(String str) {
        boolean result = false;
        for (int i = 0; i < vowels.length-1; i++) {
            if (str.charAt(i) == vowels[i]) {
                result = true;
                break;
            }
        }
        return result;
    }

    public static boolean isConsonant(String str) {
        boolean result = false;
        for (int i = 0; i < consonants.length-1; i++) {
            if (str.charAt(i) == consonants[i]) {
                result = true;
                break;
            }
        }
        return result;
    }
}
