import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.Observable;
import java.util.Observer;

public class PS07Controller extends JFrame implements ActionListener, Observer {

    //TODO
    // draw a horizontal line for aString(), upper part of view
    // draw a vertical line for bString(), left side of view
    // draw a white pixel for a space character,
    // draw a cyan vowel for string,
    // draw a purple vowel for a consonant
    // Call models objects eLine() and dLine() to obtain values in current line of the e and d 2D arrays.
    // use e array to draw green pixel if two compared characters are same
    // draw a yellow pixel for substituted,
    // draw a red pixel for deleted,
    // draw a blue pixel for inserted.
    // after reading each line, highlight by drawing a black pixel at the position where the local
    // minimum distance is found on each line, for each line obtained by dLine() method, find the
    // minimum integer value in the current d line: where the current Edit distance is located, for
    // each row in the algorithm.

    int width;
    int height;
    PS07Model model;
    PS07View view;

    public PS07Controller(int width, int height){
        this.width = width;
        this.height = height;
        view = new PS07View();
        model = new PS07Model(1280, 1280);
        model.addObserver(this);
    }

    public void actionPerformed(ActionEvent e) {
        view.repaint();
    }

    public void paintComponents(Graphics g) {
        super.paintComponents(g);
        this.setBackground(Color.WHITE);
    }

    @Override
    public void update(Observable o, Object arg) {
        //Horizontal Line, aString()
        for (int i = 0; i < model.aString().length(); i++) {
            int x = 50;
            int y = 20;
            if (model.aString().charAt(i) == ' ') {
                view.drawPoint(x+1,y,255,255,255);
            }
            //For these, it might be view and change the
            // other ones to not be static.
            else if (PS07View.isVowel(model.aString())) {
                view.drawPoint(x+i,y,0,255,255);
            }
            else if (PS07View.isConsonant(model.aString())) {
                view.drawPoint(x+i,y,255,0,255 );
            }
            //For special characters like periods or commas.
            else {
                view.drawPoint(x+i,y,255,255,255);
            }
            this.model.aString();
        }

        //Vertical Line bString()
        for (int i = 0; i < model.bString().length(); i++) {
            int x = 50;
            int y = 20;
            if (model.bString().charAt(i) == ' ') {
                view.drawPoint(x,y+1,255,255,255);
            }
            else if (PS07View.isVowel(model.bString())) {
                view.drawPoint(x,y+i,0,255,255);
            }
            else if (PS07View.isConsonant(model.bString())) {
                view.drawPoint(x,y+i,255,0,255 );
            }
            //For special characters like periods or commas.
            else {
                view.drawPoint(x,y+1,255,255,255); //This one might be y+i
            }
            this.model.bString();
        }

        //eLine
        for (int i = 0; i < model.eLine(i); i++) {
            int x = 100;
            int y = 100;
            //Green, the same characters.
            if (model.eLine(i) == ' ') {
                view.drawPoint(x+1,y,0,255,0);
            }
            //Substitute, yellow
            else if (model.eLine(i) == 'S') {
                view.drawPoint(x+1,y,255,255,0);
            }
            //Delete, red
            else if (model.eLine(i) == 'D') {
                view.drawPoint(x+1,y,255,0,0);
            }
            //Insertion, blue
            else if (model.eLine(i) == 'I') {
                view.drawPoint(x+1,y,0,0,255);
            }
            this.model.eLine(i);
        }

        //dLine
        for (int i = 0; i < 1280; i++) {
            int y = 100;
            for (int j = 0; j < model.dLine(i); j++) {
                if (model.dLine(j) == Math.min(model.dLine(j),model.dLine(j+1))) {
                    view.drawPoint(i,y,0,0,0);
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        PS07Controller controller = new PS07Controller(1280, 1280);
        controller.model.dist(controller.model.aString(), controller.model.bString());
    }
}
