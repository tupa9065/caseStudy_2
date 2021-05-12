import java.util.LinkedList;

public class VehicleWash {
    public static LinkedList<Vehicle> vehiclesWash = new LinkedList<>();

    public static synchronized void addData(Vehicle vehicle){
        vehiclesWash.add(vehicle);

    }
    public static synchronized void deleteVehicle(Vehicle vehicle) {
        vehiclesWash.remove(vehicle);
    }
    public static void showList(){
        System.out.println("******************<<   danh sách xe đang rửa   >>*********************");
        for (Vehicle vehicle:vehiclesWash
        ) {
            System.out.println(vehicle);
        }

    }
}
