import java.io.Serializable;
import java.time.LocalDate;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Vehicle implements Serializable {
    private String numOfVehicle;
    private String type;
    private String dateSwash;
    private final PriceMap PRICE_MAP;
    private int price;

    public Vehicle(PriceMap PRICE_MAP) {
        this.PRICE_MAP = PRICE_MAP;
        dateSwash=LocalDate.now().toString();
    }

    public Vehicle(String numOfVehicle, String type, PriceMap PRICE_MAP) {
        this.numOfVehicle = numOfVehicle;
        this.type = type;
        this.PRICE_MAP = PRICE_MAP;
        dateSwash=LocalDate.now().toString();
    }

    public int getPrice() {
        return price;
    }

    public String getNumOfVehicle() {
        return numOfVehicle;
    }

    public boolean setNumOfVehicle(String numOfVehicle) {
        if(numOfVehicle.matches("^\\d{2}[a-z|A-Z]\\d{4,5}$")){
            this.numOfVehicle = numOfVehicle;
            return true;
        }
        System.err.println("Biển số nhập vào không đúng");
        return false;
    }

    public String getType() {
        return type;
    }

    public String getDateSwash() {
        return dateSwash;
    }

    public void setDateSwash(String dateSwash) {
        this.dateSwash = dateSwash;
    }

    public boolean setType(String type) {
        if (isType(type)) {
            this.type = type;
            return true;
        } else {
            return false;
        }

    }

    private boolean isType(String type) {
        for (Map.Entry<String, Integer> entry :
                PRICE_MAP.getPriceOfType().entrySet()) {
            if (entry.getKey().equals(type)) {
                return true;
            }
        }
        System.err.println("kiểu xe vừa nhập không có trong danh sách");
        return false;
    }


    @Override
    public String toString() {
        return "Vehicle{" +
                "numOfVehicle='" + numOfVehicle + '\'' +
                ", type='" + type + '\'' +
                ", dateSwash='" + dateSwash + '\'' +
                ",price='" + price + '\'' +
                '}';
    }

    public void inputInfo() {
        Scanner scanner = new Scanner(System.in);
        String num;
        do{
            System.out.println("Nhập biển số xe (ví dụ: 35c1234 hoặc 35c12345)");
            num = scanner.nextLine();
        }while (!setNumOfVehicle(num));

        String typeVehicle;
        Set set = PRICE_MAP.getPriceOfType().keySet();
        do {
            System.out.println("nhập kiểu xe (" + set + ")");
            typeVehicle = scanner.nextLine();

        } while (!setType(typeVehicle));

        price = PRICE_MAP.getPriceOfType().get(typeVehicle);

    }
}
