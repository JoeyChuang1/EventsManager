import java.time.LocalDate;
import java.util.*;

public class Workshops extends EventTemp{
    /*
    Workshops object
     */
    private final List<Workshops> Prerequsite = new ArrayList<>();
    private Workshops(String aName, Location aLocation, LocalDate aDate, double aPrice, int aNumTickets, List<Workshops> aWorkshop){
        super(aName, aLocation, aDate, aPrice, aNumTickets);
        for(Workshops workshop: aWorkshop){
            Prerequsite.add(workshop);
        }
    }

    /*
    A flight weight factory that will take in all the paramter needed for this class and out a new
    object if it is the first time that this object was created. Or use the object that was stored in the hashmap
     */
    public static Workshops WorkshopsFactory(String aName, Location aLocation, LocalDate aDate, Double aPrice, Integer aNumTickets, List<Workshops> aWorkshop){
        //meaning the event is coming soon.
        if (aLocation == null){
            return new Workshops(aName, aLocation, aDate, aPrice, aNumTickets, aWorkshop);
        }else {
            if (map.get(aDate) == null) {
                Workshops newScreen = new Workshops(aName, aLocation, aDate, aPrice, aNumTickets, aWorkshop);
                HashMap<Location, Event> newMap = new HashMap<Location, Event>();
                newMap.put(aLocation, newScreen);
                map.put(aDate, newMap);
                return newScreen;
            }
            else if(map.get(aDate).get(aLocation) == null){
                Workshops newScreen = new Workshops(aName, aLocation, aDate, aPrice, aNumTickets, aWorkshop);
                HashMap<Location, Event> OldMap = map.get(aDate);
                OldMap.put(aLocation, newScreen);
                return newScreen;
            } else {
                if(map.get(aDate).get(aLocation) instanceof Workshops){
                    return (Workshops) map.get(aDate).get(aLocation);} else {
                    System.out.println("Sorry the location and date have both been occupied");
                    return null;
                }
            }
        }
    }

    private Workshops(String aName, LocalDate aDate, List<Workshops> aWorkshop){
        super(aName, aDate);
        for(Workshops workshop: aWorkshop){
            Prerequsite.add(workshop);
        }
    }
}
