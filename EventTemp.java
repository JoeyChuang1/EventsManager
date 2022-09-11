import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public abstract class EventTemp implements Event {
    private final String name;
    private final Optional<Location> Locations;
    private final LocalDate Date;
    private final Optional<Double> Price;
    private final Optional<Integer> NumTickets;
    private boolean ComingSoon;
    protected final List<Event> FesEvents = new ArrayList<>();

    protected final static HashMap<LocalDate, HashMap<Location, Event>> map = new HashMap<LocalDate, HashMap<Location, Event>>();

    /*
    Event temp is an abstract class that aloow us to store something that are in common among the event
    For example, the hashmap is shared among the event
    getters are shared among the events and other vairable are shared among the events.
    * */
    public EventTemp(String aName, Location aLocation, LocalDate aDate, Double aPrice, Integer aNumTickets) {
        name = aName;
        Locations = Optional.ofNullable(aLocation);
        Date = aDate;
        Price = Optional.ofNullable(aPrice);
        NumTickets = Optional.ofNullable(aNumTickets);
        ComingSoon = false;
    }

    public EventTemp(String aName, List<Event> FesEvent){
        for (Event event : FesEvent) {
            FesEvents.add(event);
        }

            Locations = Optional.of(Festival.BestLocation(FesEvents));
            Price = Optional.of(Festival.CalculatingPrice(FesEvents));
            NumTickets = Optional.of(Festival.BestTicket(FesEvents));
            Date = Festival.BestDate(FesEvents);
            name = aName;

    }

    public EventTemp(String aName, LocalDate aDate) {
        name = aName;
        Locations = Optional.empty();
        Date = aDate;
        Price = Optional.empty();
        NumTickets = Optional.empty();
        ComingSoon = true;
    }

    @Override
    public double AcceptVisitor(Visitor pVisitor) {
        double a = pVisitor.EventVisitor(this);
        return a;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Optional<Location> getLocation() {
        return Locations;
    }

    @Override
    public LocalDate getDate() {
        return Date;
    }

    @Override
    public Optional<Double> getPrice() {
        return Price;
    }

    @Override
    public Optional<Integer> getNumTickets() {
        return NumTickets;
    }

}




