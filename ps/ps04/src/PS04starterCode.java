// explicit import of every Java class we use from the AWT package:
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Random;


// explicit import of every Java class we use from the Swing package:
import javax.swing.JComponent;
import javax.swing.JFrame;


// note: here we use
//     https://docs.oracle.com/en/java/javase/14/docs/api/java.desktop/javax/swing/Timer.html
// and we DON'T use any other Java classes named Timer; e.g. we don't use:
//     https://docs.oracle.com/en/java/javase/14/docs/api/java.base/java/util/Timer.html
import javax.swing.Timer;
// It is sometimes confusing to consider all the many Java "packages", ey?)



// this PS04starterCode Java class will:
//   extend JComponent to draw into a Graphics object, and
//   implement ActionListener to periodically repaint the Graphics object.
public class PS04starterCode extends JComponent implements ActionListener {

    static final int H_SIZE = 256;
    static final int V_SIZE = 512;
    int[][] pixels = new int[200][200];

    // TODO: draw the content of this 2D array of integers,
    //       where each integer value in the array should be represented thus:
    //       from MIN_VALUE to 0 (included) - black color
    //       from 1 to 254 - gray color at given intensity
    //       from 255 to MAX_VALUE - white color

    javax.swing.Timer timer;

    // we have to implement actionPerformed() since we implement the ActionListener interface:
    public void actionPerformed(ActionEvent e) {
        System.out.println("here be actionPerformed() for PS04starterCode");

        // ...do whatever you need to do at repeated intervals...
        // tell the JComponent that it ought to repaint itself:
        this.repaint();
    } // end of actionPerformed()


    // we override the JComponent's paintComponent() method, to do some drawing:
    //  and we receive the currently active graphics for our JComponent:
    public void paintComponent(Graphics pGraphics) {
        System.out.println("here be paintComponent() for PS04starterCode");
        //
        // "The Graphics class is the abstract base class for all graphics contexts
        //   that allow an application to draw onto components that are realized on\
        //   various devices, as well as onto off-screen images."
        // https://docs.oracle.com/javase/8/docs/api/java/awt/Graphics.html
        super.paintComponent(pGraphics);

        // clear background at every re-painting:
        pGraphics.setColor(Color.WHITE);
        Shape lClipArea = pGraphics.getClip();
        int lWidth = lClipArea.getBounds().width;
        int lHeight = lClipArea.getBounds().height;
        pGraphics.fillRect(0,0,lWidth,lHeight);

        // set paint color for drawing, using the constructor with RGB int values:
        // https://docs.oracle.com/javase/8/docs/api/java/awt/Color.html#Color(int,%20int,%20int)
        Color lForegroundColor = new Color(0,0,0);
        pGraphics.setColor(lForegroundColor);

        // just for testing purposes,
        // let's draw a diagonal line across the Graphics context:
        int x, y;
        for (x=0; x<pixels.length; x++) {
            for(y=0; y<pixels[x].length;y++) {
                lForegroundColor = new Color((pixels[x][y] % 256),(pixels[x][y]% 256),(pixels[x][y] % 256));
                pGraphics.setColor(lForegroundColor);
                pGraphics.drawOval(x,y,1,1);
            }
        }
    } // end of paintComponent()

    public void paint(Graphics g) {
        g.fillRect(0,0,200,200);
        for(int i = 0; i < pixels.length; i++) {
            for(int j = 0; j < pixels[i].length; j++) {
                repaint();
                if(pixels[i][j] <= 0) {
                    g.setColor(Color.BLACK);
                    g.fillRect(i,j,1,1);
                }
                else if (pixels[i][j] < 255 && pixels[i][j] > 0) {
                    g.setColor(Color.GRAY);
                    g.fillRect(i, j, 1, 1);
                }
                else if (pixels[i][j] > 255) {
                    g.setColor(Color.WHITE);
                    g.fillRect(i,j,1,1);
                }
            }
        }
    }

    public void displayMatrix() {
        for(int i = 0; i < pixels.length; i++) {
            for(int j = 0; j < pixels[i].length; j++) {
                System.out.println(pixels[i][j] + " ");
            }
            System.out.println();
        }
    }

    public int getRandomNumber(int min, int max) {
        Random rand = new Random();
        return rand.ints(min,max).findFirst().getAsInt();
    }

    public void randomArray() {
        for(int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels.length; j++) {
                int fill = getRandomNumber(Integer.MIN_VALUE, Integer.MAX_VALUE);
                pixels[i][j] = fill;
            }
        }
    }

    // client code - main() method:
    public static void main(String[] args) {

        // instantiate the main JComponent, i.e. "this" Java class:
        PS04starterCode theMainJComponent = new PS04starterCode();

//        int[][] matrix = new int[200][200];
//        Int2DArray test = new Int2DArray(matrix);

        theMainJComponent.randomArray();
        theMainJComponent.displayMatrix();

        // create JFrame where we (the main object in its JComponent identity) can paint:
        JFrame aJFrame = new JFrame();
        aJFrame.add(theMainJComponent);
        aJFrame.setSize(H_SIZE, V_SIZE);
        aJFrame.setVisible( true );

        // start a Timer object, to periodically re-paint the JComponent:
        theMainJComponent.timer = new Timer(100, theMainJComponent);
        theMainJComponent.timer.start();

    } // end of main()
} // end of class PS04starterCode()