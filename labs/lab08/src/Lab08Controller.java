import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class Lab08Controller extends JFrame implements Observer {

    int height;
    int width;
    Lab08Model model;
    Lab08View view;

    //Constructor
    public Lab08Controller(int width, int height){
        this.width = width;
        this.height = height;
        model = new Lab08Model(1280, 1280);
        view = new Lab08View();
        model.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        model.getArray();
        view.drawPoint();
    }

    public void paintComponents(Graphics g) {
        super.paintComponents(g);
        this.setBackground(Color.WHITE);
    }

    public static void main(String[] args) {
        Lab08Controller controller = new Lab08Controller(1280,1280);
        controller.model.randomize();
        controller.model.getArray();
        controller.model.heapsort(controller.model.getArray());
        controller.view.drawPoint();
    }

}
