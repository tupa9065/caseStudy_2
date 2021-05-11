import java.util.HashMap;
import java.util.Map;

public class PriceMap {
    private  Map<String,Integer> priceOfType = new HashMap<>();

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

}
