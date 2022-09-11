import java.time.LocalDate;
import java.util.*;


public class Festival extends EventTemp {

    /*
    This is a callback function for visitor
     */
    public double AcceptVisitor(Visitor pVisitor) {
        double a = pVisitor.FestivalVisitor(this);
        return a;
    }


    /*
    A flight weight factory that will take in all the paramter needed for this class and out a new
    object if it is the first time that this object was created. Or use the object that was stored in the hashmap
     */
    public static Festival FesFactory(String aName, List<Event> FesEvents){
        //meaning the event is coming soon.
        Festival fes = new Festival(aName, FesEvents);
        if (fes.getLocation().isEmpty()){
            return fes;
        }else {
            if (map.get(fes.getDate()) == null) {
                HashMap<Location, Event> newMap = new HashMap<Location, Event>();
                newMap.put(fes.getLocation().get(), fes);
                map.put(fes.getDate(), newMap);
                return fes;
            }
            else if(map.get(fes.getDate()).get(fes.getLocation().get()) == null){
                HashMap<Location, Event> OldMap = map.get(fes.getDate());
                OldMap.put(fes.getLocation().get(), fes);
                return fes;
            } else {
                if(map.get(fes.getDate()).get(fes.getLocation().get()) instanceof Festival){
                    return (Festival) map.get(fes.getDate()).get(fes.getLocation().get());} else {
                    System.out.println("Sorry the location and date have both been occupied");
                    return null;
                }
            }
        }
    }

    public List<Event> getFesEvent() {
        List<Event> dummy = new ArrayList<>();
        for (Event event : FesEvents) {
            dummy.add(event);
        }
        return dummy;
    }

    private Festival(String aName, List<Event> FesEvents) {
        super(aName, FesEvents);
    }

    /*
    This is to calculate the best location inside list of the event
    @para List<Event> FesEvent
     */
    static Location BestLocation(List<Event> FesEvents) {
        Location aLocation = null;
        for (Event event : FesEvents) {
            if (event.getLocation().isEmpty()) {
                continue;
            }
            if (aLocation == null) {
                aLocation = event.getLocation().get();
            } else if (aLocation != event.getLocation().get()) {
                aLocation = Location.Multiple;
            }
        }
        return aLocation;
    }
    /*
    To calculate the earliest date inside list of event
    @para List<Event> FesEvents
     */
    static LocalDate BestDate(List<Event> FesEvents) {
        LocalDate ADate = null;
        for (Event event : FesEvents) {
            if (ADate == null) {
                ADate = event.getDate();
            } else if (event.getDate().isBefore(ADate)) {
                ADate = event.getDate();
            }
        }
        return ADate;
    }
    /*
    To calculate the lowest ticket price inside list of event
     @para List<Event> FesEvents
     */
    static int BestTicket(List<Event> FesEvents) {
        int ANumTickets = Integer.MAX_VALUE;
        for (Event event : FesEvents) {
            if (event.getNumTickets().isEmpty()) {
                continue;
            }
            if (event.getNumTickets().get() < ANumTickets) {
                ANumTickets = event.getNumTickets().get();
            }
        }
        return ANumTickets;
    }
    /*
    To calculate the price by multiplying every event price by 0.2 (20%) and add it together
    @para List<Event> FesEvents
     */
    static double CalculatingPrice(List<Event> FesEvents) {
        double price = 0.00;
        for (Event event : FesEvents) {
            if (event.getPrice().isEmpty()) {
                continue;
            }
            price += event.getPrice().get() * 0.2;
        }
        return price;
    }

}


