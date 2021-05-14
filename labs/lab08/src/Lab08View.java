import javax.swing.*;

public class Lab08View extends JComponent {

    public Lab08View(){
        JFrame frame = new JFrame();
        frame.add(this);
        frame.setSize(1280,1280);
        frame.setVisible(true);
        frame.setTitle("Lab 08 Visualization");
    }

    public void drawPoint() {
        int[] arr = new int[800];
        int x = 0;
        int j = 0;
        for (int i = 0; i <= arr.length-1; i++) {
            this.getGraphics().drawLine(i,j,x,arr[i]);
            x++;
            j++;
        }
    }
}
