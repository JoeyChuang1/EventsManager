import java.time.LocalDate;
import java.util.*;

public class Concerts extends EventTemp{
    private List<String> VIP = new ArrayList<>();
    private String Artists;
    /*
    A flight weight factory that will take in all the paramter needed for this class and out a new
    object if it is the first time that this object was created. Or use the object that was stored in the hashmap
     */
    public static Concerts ConcertsFactory(String aName, Location aLocation, LocalDate aDate, double aPrice, int aNumTickets, String aArtists, List<String> aVIP){
        //meaning the event is coming soon.
        if (aLocation == null){
            return new Concerts(aName, aLocation, aDate, aPrice, aNumTickets, aArtists, aVIP);
        }else {
            if (map.get(aDate) == null) {
                Concerts newScreen = new Concerts(aName, aLocation, aDate, aPrice, aNumTickets, aArtists, aVIP);
                HashMap<Location, Event> newMap = new HashMap<Location, Event>();
                newMap.put(aLocation, newScreen);
                map.put(aDate, newMap);
                return newScreen;
            }
            else if(map.get(aDate).get(aLocation) == null){
                Concerts newScreen = new Concerts(aName, aLocation, aDate, aPrice, aNumTickets, aArtists, aVIP);
                HashMap<Location, Event> OldMap = map.get(aDate);
                OldMap.put(aLocation, newScreen);
                return newScreen;
            } else {
                if (map.get(aDate).get(aLocation) instanceof Concerts){
                    return (Concerts) map.get(aDate).get(aLocation);}
                else{
                    System.out.println("This Location and date has been occupied by another event Sorry");
                    return null;
                }
            }
        }
    }

    private Concerts(String aName, Location aLocation, LocalDate aDate, double aPrice, int aNumTickets, String aArtists, List<String> aVIP){
        super(aName, aLocation, aDate, aPrice, aNumTickets);
        Artists = aArtists;
        List<String> dummy = new ArrayList<>();
        for(String name: aVIP){
            dummy.add(name);
        }
        VIP = dummy;
    }


    public List<String> getVIP() {
        return new ArrayList<>(VIP);
    }

    public String getArtists() {
        return Artists;
    }


    /*
    Get the number of the VIP in this event
     */
    public double getVIPNum(){
        return VIP.size();
    }
}
