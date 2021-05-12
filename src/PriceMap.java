import java.io.*;
import java.util.*;

public class PriceMap implements Serializable {
    private  Map<String,Integer> priceOfType = new TreeMap<>();

    public Map<String, Integer> getPriceOfType() {
        return priceOfType;
    }

    public void setPriceOfType(Map<String, Integer> priceOfType) {
        this.priceOfType = priceOfType;
    }

    public PriceMap() {
    }
    public boolean searchType(){

        return false;
    }

    public boolean isType(String type) {
        Set keys = priceOfType.keySet();
        for (Object key: keys
             ) {
            if(key.equals(type)){
                return true;
            }
        }
        return false;
    }
    public void setPriceOfType(String type){

    }


}
