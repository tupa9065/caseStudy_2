import java.util.ArrayList;

public class VehicleList {
    public static ArrayList<Vehicle> list =new ArrayList<>();

    public VehicleList() {

    }

    public static synchronized void addData(Vehicle vehicle){
        list.add(vehicle);
    }
    public static void showList(){
        System.out.println("******************<<   danh sách xe đã rửa xong   >>*********************");
        for (Vehicle vehicle:list
             ) {
            System.out.println(vehicle);
        }

    }
}
