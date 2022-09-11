import java.util.*;

public class LocationFilter implements Filters{
    private final Location filter;
    /*
    LocationFilter is a visitor patter that implements "filter" and allow us to filter the event within
    a list by the Location specified in the input
     */
    public LocationFilter(Location aFiler){
        assert aFiler != null;
        filter = aFiler;
    }

    /*
    Same procedure as price range filter, we are going to loop through every event within the list
    and filter out any event that dosent satisfie the condition
     */
    @Override
    public List<Event> execute(List<Event> input) {
        assert input != null;
        List<Event> Dummy = new ArrayList<>();
        for(Event event: input){
            if(event.getLocation().isEmpty()){
                continue;
            }
            if(event.getLocation().get().equals(filter)){
                Dummy.add(event);
            }
        }
        return Dummy;
    }

}
