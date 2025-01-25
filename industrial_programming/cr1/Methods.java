import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.stream.Collectors;

public class Methods {
    public static ArrayList<Bulb> increasePrice(ArrayList<Bulb> lampList){
        return lampList.stream().sorted(Comparator.comparing(Bulb::countCost)).collect(Collectors.
                toCollection(ArrayList::new));
    }

    public static ArrayList<Bulb> pricePower(ArrayList<Bulb> lampList){
        return lampList.stream().sorted(Comparator.comparing(o -> (double) o.countCost() / o.power))
                .collect(Collectors.
                toCollection(ArrayList::new));
    }

    public static ArrayList<String> manufactureStartedWithC(ArrayList<Bulb> lampList){
        HashSet<String> result = new HashSet<String>();
        lampList = lampList.stream().filter(o -> o.manufacture.startsWith("C")).collect(Collectors.
                        toCollection(ArrayList::new));
        for (Bulb lamp : lampList){
            result.add(lamp.manufacture);
        }
        return new ArrayList<String>(result);
    }

    public static double averagePriceManufacture(ArrayList<Bulb> lampList, String producer){
        HashSet<String> result = new HashSet<String>();
        lampList = lampList.stream().filter(o -> o.manufacture.equals(producer)).collect(Collectors.
                toCollection(ArrayList::new));
        Integer prices = lampList.stream().map(Bulb::countCost).reduce(Integer::sum).orElse(0);
        return (double) (prices / lampList.size());
    }
}
