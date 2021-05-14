import java.util.Observable;

public class Lab08Model extends Observable {

    int width;
    int height;

    public Lab08Model(int width, int height){
        this.width = width;
        this.height = height;
    }

    public int[] getArray() {
        int[] arr = new int[width];
        return arr;
    }

    public void randomize() {
        int[] arr = new int[width];
        for (int i = 0; i < width; i++) {
            arr[i] = (int) (Math.random() * height);
        }
    }


    public void heapsort(int[] arr) {
        int size = arr.length;
        //build the heap
        for (int i = width / 2 - 1; i >= 0; i--) {
            heapify(arr,size,i);
        }
        for (int i = size-1; i > 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            heapify(arr,size,0);
        }
        setChanged();
        notifyObservers();
    }

    //Helper function for heapsort
    void heapify(int[] arr, int size, int root) {
        int largest = root;
        int left = 2*root + 1;
        int right = 2*root + 2;
        if (left < size && arr[left] > arr[largest])
            largest = left;
        if (right < size && arr[right]> arr[largest])
            largest = right;
        if (largest != root) {
            int swap = arr[root];
            arr[root] = arr[largest];
            arr[largest] = swap;
            heapify(arr,size,largest);
        }
        setChanged();
        notifyObservers();
    }
}
