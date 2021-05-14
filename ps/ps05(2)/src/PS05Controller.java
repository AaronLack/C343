import java.util.Observable;
import java.util.Observer;

public class PS05Controller implements Observer {

    //Instantiate PS05Model and PS05View objects here
    PS05View view = new PS05View();
    PS05Model model = new PS05Model(1280, 1280);

    //call the PS05Model object's getArray(), to obtain the current values in the 2D array as stored by the PS05Model object.
    //redraw the entire content of the PS05Model's 2D array, by call the necessary PS05View methods.
    @Override
    public void update(Observable o, Object arg) {
        //TODO
        model.getArray();
        view.drawPoint(0,0,200);
    }

    public static void main(String[] args) {
        //I HIGHLY RECOOMENT Commenting out and testing methods seperately.
        //Make an instance of controller here
        PS05Controller controller = new PS05Controller();
        controller.model.getArray();
        controller.view.drawPoint(0,0,200);
        controller.view.clear();
        Observable observe = new Observable();
        Object obj = new Object();
        controller.update(observe, obj);
    }


}
