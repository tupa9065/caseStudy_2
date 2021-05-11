import java.util.LinkedList;
import java.util.Queue;

public class VehicleQueue {
    public static Queue<Vehicle> queue = new LinkedList<>();

    public VehicleQueue() {

    }

    public static synchronized Vehicle pollQueue() {
        synchronized (queue) {
            return queue.poll();
        }
    }
    public static synchronized void deleteVehicle(Vehicle vehicle) {
        synchronized (queue) {
            LinkedList<Vehicle> vehicles = new LinkedList<>(queue);
            vehicles.remove(vehicle);
            queue = new LinkedList<>(vehicles);
        }
    }
    public static void showQueue() {
        System.out.println("******************<<   danh sách xe đang đợi   >>*********************");
        for (Vehicle vehicle : queue) {
            System.out.println(vehicle);
        }
    }

    public static Vehicle findVehicle(String str) {
        synchronized (queue) {
            for (Vehicle vehicle : queue
            ) {
                if (vehicle.getNumOfVehicle().equals(str)) {
                    return vehicle;
                }
            }
        }
        return null;
    }
}
