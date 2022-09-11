import java.time.LocalDate;
import java.util.*;

public class Galas extends EventTemp{
    private final List<String> VIP;

    /*
    Galas object
     */
    private Galas(String aName, Location aLocation, LocalDate aDate, double aPrice, int aNumTickets,List<String> aVIP){
        super(aName, aLocation, aDate, aPrice, aNumTickets);
        List<String> dummy = new ArrayList<>();
        for(String name: aVIP){
            dummy.add(name);
        }
        VIP = dummy;
    }

    /*
    A flight weight factory that will take in all the paramter needed for this class and out a new
    object if it is the first time that this object was created. Or use the object that was stored in the hashmap
     */
    public static Galas GalasFactory(String aName, Location aLocation, LocalDate aDate, Double aPrice, Integer aNumTickets, List<String> aVIP){
        //meaning the event is coming soon.
        if (aLocation == null){
            return new Galas(aName, aLocation, aDate, aPrice, aNumTickets, aVIP);
        }else {
            if (map.get(aDate) == null) {
                Galas newScreen = new Galas(aName, aLocation, aDate, aPrice, aNumTickets, aVIP);
                HashMap<Location, Event> newMap = new HashMap<Location, Event>();
                newMap.put(aLocation, newScreen);
                map.put(aDate, newMap);
                return newScreen;
            }
            else if(map.get(aDate).get(aLocation) == null){
                Galas newScreen = new Galas(aName, aLocation, aDate, aPrice, aNumTickets, aVIP);
                HashMap<Location, Event> OldMap = map.get(aDate);
                OldMap.put(aLocation, newScreen);
                return newScreen;
            } else {
                if(map.get(aDate).get(aLocation) instanceof Galas){
                    return (Galas) map.get(aDate).get(aLocation);} else {
                    System.out.println("Sorry the location and date have both been occupied" +
                            "by another events");
                    return null;
                }
            }
        }
    }

    private Galas(String aName,  LocalDate aDate, List<String> aVIP){
        super(aName,  aDate);
        List<String> dummy = new ArrayList<>();
        for(String name: aVIP){
            dummy.add(name);
        }
        VIP = dummy;
    }

    public double getVIPNum(){
        return VIP.size();
    }
}
