import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PriceMap priceMap = new PriceMap();
        priceMap.getPriceOfType().put("sedan", 50);
        priceMap.getPriceOfType().put("truck", 70);
        priceMap.getPriceOfType().put("moto", 15);

        CarWashThread carWashThread_1 = new CarWashThread();
        CarWashThread carWashThread_2 = new CarWashThread();
        carWashThread_1.start();
        carWashThread_2.start();
        Scanner scanner = new Scanner(System.in);
        int choose;
        do {
            choose = getChoose(carWashThread_1, carWashThread_2, scanner, priceMap);
        } while (choose == 0);
    }

    private static int getChoose(CarWashThread carWashThread_1, CarWashThread carWashThread_2, Scanner scanner, PriceMap priceMap) {
        int choose;
        System.out.println("******************<<   Menu   >>*********************");
        System.out.println("1. Thêm xe vào rửa");
        System.out.println("2. Xóa xe khỏi hàng chờ");
        System.out.println("3. Xem danh sách xe đang chờ");
        System.out.println("4. Thống kê số lượng xe đã rửa ");
        System.out.println("5. Kiểm tra số tiền đã thu được ");
        System.out.println("6. Kiểm tra giá thành rửa xe");
        System.out.println("7. Thay đổi giá thành rửa xe");
        System.out.println("8. Thêm mới chủng loại ");
        System.out.println("0. Thoát chương trình");
        System.out.println("Nhập lựa chọn của bạn");

        choose = Integer.parseInt(scanner.nextLine());

        switch (choose) {
            case 1:
                Vehicle vehicle = new Vehicle(priceMap);
                vehicle.inputInfo();
                synchronized (VehicleQueue.queue) {
                    boolean check =false ;
                    for (Vehicle v:VehicleQueue.queue
                         ) {
                        if(v.getNumOfVehicle().equals(vehicle.getNumOfVehicle())){
                            System.out.println("trùng biển số xe");
                            check=true;
                            break;
                        }

                    }
                    if(!check){
                        VehicleQueue.queue.add(vehicle);
                    }
                }
                carWashThread_1.resume();
                carWashThread_2.resume();
                getChoose(carWashThread_1, carWashThread_2, scanner, priceMap);
                break;

            case 2:
                System.out.println("Nhập biển số xe ");

                getChoose(carWashThread_1, carWashThread_2, scanner, priceMap);
                break;

            case 3:

                VehicleQueue.showQueue();
                getChoose(carWashThread_1, carWashThread_2, scanner, priceMap);
                break;

            case 4:
                VehicleList.showList();
                getChoose(carWashThread_1, carWashThread_2, scanner, priceMap);

                break;

            case 0:
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
