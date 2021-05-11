import java.util.LinkedList;
import java.util.Queue;

public class VehicleQueue {
    public  static final Queue<Vehicle> queue = new LinkedList<>();
    public VehicleQueue() {

    }

    public static synchronized Vehicle deQueue(){
        synchronized (queue){
            return queue.poll();
        }
    }
    public static void showQueue(){
        System.out.println("******************<<   danh sách xe đang đợi   >>*********************");
        for (Vehicle vehicle:queue) {
            System.out.println(vehicle);
        }
    }
}
