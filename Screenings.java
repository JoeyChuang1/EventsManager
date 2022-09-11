import java.time.LocalDate;
import java.util.*;

public class Screenings extends EventTemp{
    private final Rating rating;
    //private static HashMap<LocalDate, HashMap<Location, Screenings>> map = new HashMap<LocalDate, HashMap<Location, Screenings>>();

    /*
    A flight weight factory that will take in all the paramter needed for this class and out a new
    object if it is the first time that this object was created. Or use the object that was stored in the hashmap
     */
    public static Screenings ScreenFactory(String aName, Location aLocation, LocalDate aDate, Double aPrice, Integer aNumTickets, Rating aRating){
        //meaning the event is coming soon.
        if (aLocation == null){
            return new Screenings(aName, aLocation, aDate, aPrice, aNumTickets, aRating);
        }else {
            if (map.get(aDate) == null) {
                Screenings newScreen = new Screenings(aName, aLocation, aDate, aPrice, aNumTickets, aRating);
                HashMap<Location, Event> newMap = new HashMap<Location, Event>();
                newMap.put(aLocation, newScreen);
                map.put(aDate, newMap);
                return newScreen;
            }
            else if(map.get(aDate).get(aLocation) == null){
                Screenings newScreen = new Screenings(aName, aLocation, aDate, aPrice, aNumTickets, aRating);
                HashMap<Location, Event> OldMap = map.get(aDate);
                OldMap.put(aLocation, newScreen);
                return newScreen;
            } else {
                if(map.get(aDate).get(aLocation) instanceof Screenings){
                return (Screenings) map.get(aDate).get(aLocation);} else {
                    System.out.println("Sorry the location and date have both been occupied");
                    return null;
                }
            }
        }
    }
    /*
    Screening object that extends events
     */
    private Screenings(String aName, Location aLocation, LocalDate aDate, double aPrice, int aNumTickets, Rating aRating){
        super(aName, aLocation, aDate, aPrice, aNumTickets);
        rating = aRating;
    }

    private Screenings(String aName,LocalDate aDate, Rating aRating){
        super(aName,  aDate);
        rating = aRating;
    }

    //static final Screenings[][] cache = new Screenings[][];


    public Rating getRating() {
        return rating;
    }
}
