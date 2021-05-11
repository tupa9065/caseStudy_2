import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PriceMap priceMap = new PriceMap();
        priceMap.getPriceOfType().put("sedan", 50);
        priceMap.getPriceOfType().put("suv", 70);
        priceMap.getPriceOfType().put("moto", 15);

        CarWashThread carWashThread_1 = new CarWashThread();
        CarWashThread carWashThread_2 = new CarWashThread();
        carWashThread_1.start();
        carWashThread_2.start();
        Scanner scanner = new Scanner(System.in);
        String choose;
        do {
            choose = getChoose(carWashThread_1, carWashThread_2, scanner, priceMap);
        } while (choose == "0");
    }

    private static String getChoose(CarWashThread carWashThread_1, CarWashThread carWashThread_2, Scanner scanner, PriceMap priceMap) {
        String choose;
        System.out.println("******************<<   Menu   >>*********************");
        System.out.println("1. Thêm xe vào rửa");
        System.out.println("2. Xóa xe khỏi hàng chờ");
        System.out.println("3. Xem danh sách xe đang chờ");
        System.out.println("4. Thống kê số lượng xe đã rửa ");
        System.out.println("5. Kiểm tra số tiền đã thu được ");
        System.out.println("6. Báo giá rửa xe");
        System.out.println("7. Thay đổi giá rửa xe");
        System.out.println("8. Thêm mới chủng lợi vào bảng báo giá");
        System.out.println("0. Thoát chương trình");
        System.out.println("Nhập lựa chọn của bạn");

        choose = scanner.nextLine();

        switch (choose) {
            case "1":
                Vehicle vehicle = new Vehicle(priceMap);
                vehicle.inputInfo();
                synchronized (VehicleQueue.queue) {
                    boolean check = false;
                    for (Vehicle v : VehicleQueue.queue
                    ) {
                        if (v.getNumOfVehicle().equals(vehicle.getNumOfVehicle())) {
                            System.out.println("trùng biển số xe");
                            check = true;
                            break;
                        }

                    }
                    if (!check) {
                        VehicleQueue.queue.add(vehicle);
                    }
                }
                carWashThread_1.resume();
                carWashThread_2.resume();
                getChoose(carWashThread_1, carWashThread_2, scanner, priceMap);
                break;

            case "2":
                System.out.println("Nhập biển số xe ");
                String numOfVehicle = scanner.nextLine();
                Vehicle check = VehicleQueue.findVehicle(numOfVehicle);
                if(check!=null){
                    try{
                        VehicleQueue.deleteVehicle(check);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    VehicleQueue.showQueue();

                }else {
                    System.out.println("không tìm thấy biển số xe");
                }
                getChoose(carWashThread_1, carWashThread_2, scanner, priceMap);
                break;

            case "3":

                VehicleQueue.showQueue();
                getChoose(carWashThread_1, carWashThread_2, scanner, priceMap);
                break;

            case "4":
                VehicleList.showList();
                getChoose(carWashThread_1, carWashThread_2, scanner, priceMap);
                break;
            case "5":
                getChoose(carWashThread_1, carWashThread_2, scanner, priceMap);

                break;
            case "6":
                System.out.println("******************<<   Bảng báo giá rửa xe   >>*********************");
                for (Map.Entry<String,Integer> entry :priceMap.getPriceOfType().entrySet()
                     ) {
                    System.out.println(entry.getKey()+" - "+entry.getValue()+".000 VNĐ");
                }
                getChoose(carWashThread_1, carWashThread_2, scanner, priceMap);

                break;
            case "7":
                System.out.println("Nhập loại xe cần thay đổi giá thành");
                String type = scanner.nextLine();
                if(priceMap.getPriceOfType().containsKey(type)){
                    System.out.println(priceMap.getPriceOfType().get(type));
                    System.out.println("Nhập giá mới");
                    int value = scanner.nextInt();
                    priceMap.getPriceOfType().replace(type,value);
                }else {
                    System.out.println("không tìm thấy ");
                }
                getChoose(carWashThread_1, carWashThread_2, scanner, priceMap);

                break;
            case "8":
                System.out.println("Nhập loại xe  thành");
                String newType = scanner.nextLine();
                if(!priceMap.getPriceOfType().containsKey(newType)){
                    System.out.println("Nhập giá thành");
                    int value = scanner.nextInt();
                    priceMap.getPriceOfType().put(newType,value);
                }
                getChoose(carWashThread_1, carWashThread_2, scanner, priceMap);

                break;
            case "0":
                System.out.println("Goodbye Sếp!!!");
                System.exit(0);
                break;

            default:
                System.out.println("Lựa chọn của bạn không phù hợp, mời chọn lại");
                getChoose(carWashThread_1, carWashThread_2, scanner, priceMap);
                break;
        }
        return choose;
    }

}
