import java.util.*;

public class FilterResult {
    private final List<Event> aFilteredEvents;

    /*
    FilterResult is a requirement in this assignment.
    This function will be able take in a list of event (will not be able to change it after).
    It can accept different kind of visitor pattern. We can accept filter visitor pattern that
    will filter the event base on the filter object. We can accept Visitor vistor pattern that will
    allow us to calculate the expected profit among the event or VIP count among the event
     */
    public FilterResult(List<Event> ListOfEvents){
        List<Event> dummy = new ArrayList<>();
        for(Event event: ListOfEvents){
            dummy.add(event);
        }
        aFilteredEvents = dummy;
    }
    /*
    Getter
     */
    public List<Event> getaFilteredEvents() {
        return new ArrayList<>(aFilteredEvents);
    }
    /*
    Accept a visitor
     */
    public double acceptVisitor(Visitor pVisitor){
        double a = pVisitor.FiltersResultVisitor(this);
        return a;
    }
    /*
    Accept a filter that will filter the event within this object.
    return a new FilterResult object with the result of the filter
     */
    public FilterResult filtering(Filters filters){
        List<Event> result;
        result = filters.execute(aFilteredEvents);
        return new FilterResult(result);
    }
}
