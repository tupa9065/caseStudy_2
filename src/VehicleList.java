import java.io.*;
import java.util.ArrayList;

public class VehicleList implements Serializable{
    public static ArrayList<Vehicle> list =new ArrayList<>();

    public VehicleList() {

    }

    public static synchronized void addData(Vehicle vehicle){
        list.add(vehicle);
        saveDataToFile();
    }
    public static void showList(){
        System.out.println("******************<<   danh sách xe đã rửa xong   >>*********************");
        for (Vehicle vehicle:list
             ) {
            System.out.println(vehicle);
        }

    }
    public static synchronized void getDataFromFile(){
        try{
            File file = new File("listVehicle.txt");
            if(!file.exists() || file.length()==0){
                return;
            }
            ObjectInputStream oi = new ObjectInputStream(new FileInputStream(file));
            list = (ArrayList<Vehicle>) oi.readObject();
            oi.close();
        }catch (Exception e){
            e.printStackTrace();
            System.err.println("đọc file bị lỗi");
        }
    }
    public static synchronized void saveDataToFile(){
        try{
            if(list.isEmpty()){
                return;
            }
            ObjectOutputStream oo = new ObjectOutputStream(new FileOutputStream("listVehicle.txt"));
            oo.writeObject(list);
            oo.flush();
        }catch (Exception e){
            e.printStackTrace();
            //System.err.println("save file bị lỗi");
        }
    }
}
