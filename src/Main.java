import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        PriceMap priceMap = new PriceMap();

        getDataFromFile(priceMap);

        VehicleList.getDataFromFile();

        CarWashThread carWashThread_1 = new CarWashThread();
        CarWashThread carWashThread_2 = new CarWashThread();

        carWashThread_1.start();
        carWashThread_1.setName("Automatic Wash System 1");

        carWashThread_2.start();
        carWashThread_2.setName("Automatic Wash System 2");

        Scanner scanner = new Scanner(System.in);

        String choose ;

        do {
            showMenu();
            choose = scanner.nextLine();
            switch (choose) {
                case "1":
                    addVehicleToWash(carWashThread_1, carWashThread_2, scanner, priceMap);
                    break;

                case "2":
                    deleteVehicleFromQueue(scanner);
                    break;

                case "3":
                    VehicleQueue.showQueue();
                    break;

                case "4":
                    VehicleWash.showList();
                    break;

                case "5":
                    salesReportMenu();
                    //System.out.println("chức năng chưa hoàn thiện");
                    break;

                case "6":
                    serviceQuotes(priceMap);
                    break;

                case "7":
                    quoteChange(scanner, priceMap);
                    break;

                case "8":
                    addNewService(scanner, priceMap);
                    break;

                case "0":
                    System.out.println("Goodbye Sếp!!!");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Lựa chọn của bạn không phù hợp, mời chọn lại");
                    break;
            }

        } while (true);
    }

    private static void salesReportMenu() {
        Scanner scanner = new Scanner(System.in);
        String choice;
        do {
            System.out.println("1. Doanh thu ngày hôm nay ");
            System.out.println("2. Doanh thu tháng này ");
            System.out.println("3. Doanh thu theo khoảng thời gian ");
            System.out.println("4. Tổng doanh thu ");
            System.out.println("5. quay lại menu chính ");
            choice=scanner.nextLine();
            switch (choice){
                case "1":{
                    ArrayList<Vehicle> newDayList = getDayVehicleList();
                    int totalOfDay = revenue(newDayList);
                    System.out.println("Doanh thu ngày hôm nay là: "+totalOfDay +".000 VNĐ");
                    showList(newDayList);
                }
                    break;
                case "2":{
                    ArrayList<Vehicle> newList = getMonthVehicleList();
                    int total = revenue(newList);
                    System.out.println("Doanh thu tháng này là: "+total +".000 VNĐ");
                    showList(newList);
                }
                    break;
                case "3":{
                    System.out.println("Nhập ngày bất đầu (YYYY-MM-DD)");
                    String fromTimeStr = scanner.nextLine();
                    LocalDate fromTimeDate = LocalDate.parse(fromTimeStr);
                    System.out.println("Nhập ngày kết thúc (YYYY-MM-DD)");
                    String toTimeStr = scanner.nextLine();
                    LocalDate toTimeDate = LocalDate.parse(toTimeStr);
                    ArrayList<Vehicle> newList = getSetTimeVehicleList(fromTimeDate,toTimeDate);
                    int total = revenue(newList);
                    System.out.println("Doanh thu từ " +fromTimeStr+" đến "+toTimeStr+" là: "+total +".000 VNĐ");
                    showList(newList);

                }



                    break;
                case "4": {
                    int total = revenue(VehicleList.list);
                    System.out.println("Tổng doanh thu : "+total +".000 VNĐ");
                    VehicleList.showList();
                }
                    break;
                case "5":
                    break;
                default:
                    System.err.println("lựa chọn không hợp lệ mời chọn lại");
                    break;
            }
        }while (!(choice.equals("5")));
    }

    private static ArrayList<Vehicle> getSetTimeVehicleList(LocalDate fromTime,LocalDate toTime) {
        ArrayList<Vehicle> setTimeList = new ArrayList<>();
        fromTime=fromTime.minusDays(1);
        toTime = toTime.plusDays(1);
        for (Vehicle vehicle :VehicleList.list) {
            LocalDate vehicleDate = LocalDate.parse(vehicle.getDateSwash());
            if(vehicleDate.isAfter(fromTime)&&vehicleDate.isBefore(toTime)){
                setTimeList.add(vehicle);
            }
        }
        return setTimeList;
    }


    private static ArrayList<Vehicle> getDayVehicleList() {
        ArrayList<Vehicle> dayList = new ArrayList<>();
        LocalDate thisDay = LocalDate.now();
        for (Vehicle vehicle :VehicleList.list) {
            if(vehicle.getDateSwash().equals(thisDay.toString())){
                dayList.add(vehicle);
            }
        }
        return dayList;
    }

    private static ArrayList<Vehicle> getMonthVehicleList() {
        ArrayList<Vehicle> monthList = new ArrayList<>();
        Month thisMonth = LocalDate.now().getMonth();
        for (Vehicle vehicle :VehicleList.list) {
            LocalDate date = LocalDate.parse(vehicle.getDateSwash());
            if (date.getMonth() != thisMonth) {
            } else {
                monthList.add(vehicle);
            }
        }
        return monthList;
    }
    private static int revenue(ArrayList<Vehicle> list) {
        int total = 0;
        for (Vehicle vehicle :list) {
            total+= vehicle.getPrice();
        }
        return total;
    }

    private static void showList(ArrayList<Vehicle> list) {
        for (Vehicle vehicle:list
             ) {
            System.out.println(vehicle);
        }
    }
    private static void showMenu() {
        System.out.println("******************<<   Menu   >>*********************");
        System.out.println("1. Thêm xe vào rửa");
        System.out.println("2. Xóa xe khỏi hàng chờ");
        System.out.println("3. Xem danh sách xe đang chờ");
        System.out.println("4. Xem danh sách xe đang rửa");
        System.out.println("5. Thống kê  ");
        System.out.println("6. Báo giá rửa xe");
        System.out.println("7. Thay đổi giá rửa xe");
        System.out.println("8. Thêm mới chủng loại vào bảng báo giá");
        System.out.println("0. Thoát chương trình");
        System.out.println("Nhập lựa chọn của bạn");
    }

    private static void addNewService(Scanner scanner, PriceMap priceMap) {
        System.out.println("Nhập loại xe ");
        String newType = scanner.nextLine();
        if (!priceMap.getPriceOfType().containsKey(newType)) {
            System.out.println("Nhập giá thành");
            int value = scanner.nextInt();
            priceMap.getPriceOfType().put(newType, value);
            saveDataToFile(priceMap);
        }
    }

    private static void quoteChange(Scanner scanner, PriceMap priceMap) {
        System.out.println("Nhập loại xe cần thay đổi giá thành");
        String type = scanner.nextLine();
        if (priceMap.getPriceOfType().containsKey(type)) {
            System.out.println(priceMap.getPriceOfType().get(type));
            System.out.println("Nhập giá mới");
            int value = scanner.nextInt();
            priceMap.getPriceOfType().replace(type, value);
            saveDataToFile(priceMap);

        } else {
            System.out.println("không tìm thấy ");
        }
    }

    private static void serviceQuotes(PriceMap priceMap) {
        System.out.println("******************<<   Bảng báo giá rửa xe   >>*********************");
        for (Map.Entry<String, Integer> entry : priceMap.getPriceOfType().entrySet()
        ) {
            System.out.println(entry.getKey() + " - " + entry.getValue() + ".000 VNĐ");
        }
    }

    private static void deleteVehicleFromQueue(Scanner scanner) {
        System.out.println("Nhập biển số xe ");
        String numOfVehicle = scanner.nextLine();
        Vehicle check = VehicleQueue.findVehicle(numOfVehicle);
        if (check != null) {
            try {
                VehicleQueue.deleteVehicle(check);
            } catch (Exception e) {
                e.printStackTrace();
            }
            VehicleQueue.showQueue();

        } else {
            System.out.println("không tìm thấy biển số xe");
        }
    }

    private static void addVehicleToWash(CarWashThread carWashThread_1, CarWashThread carWashThread_2, Scanner scanner, PriceMap priceMap) {
        Vehicle vehicle = new Vehicle(priceMap);
        vehicle.inputInfo();
        synchronized (VehicleQueue.queue) {
            boolean check = false;
            for (Vehicle element : VehicleQueue.queue
            ) {
                if (element.getNumOfVehicle().equals(vehicle.getNumOfVehicle())) {
                    System.err.println("trùng biển số xe trong hàng chờ");
                    check = true;
                    break;
                }

            }
            for (Vehicle element : VehicleWash.vehiclesWash
            ) {
                if (element.getNumOfVehicle().equals(vehicle.getNumOfVehicle())) {
                    System.err.println("trùng biển số xe đang rửa");
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
    }

    public static void getDataFromFile(PriceMap priceMap) {
        try {
            File file = new File("priceMap.txt");
            if (!file.exists() || file.length() == 0) {
                return;
            }
            ObjectInputStream oi = new ObjectInputStream(new FileInputStream(file));
            priceMap.setPriceOfType((Map<String, Integer>) oi.readObject());
            oi.close();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public static void saveDataToFile(PriceMap priceMap) {
        try {
            if (priceMap.getPriceOfType().isEmpty()) {
                return;
            }
            ObjectOutputStream oo = new ObjectOutputStream(new FileOutputStream("priceMap.txt"));
            oo.writeObject(priceMap.getPriceOfType());
            oo.flush();
        } catch (Exception e) {
            e.printStackTrace();
            //System.err.println("save file bị lỗi");
        }
    }

}
