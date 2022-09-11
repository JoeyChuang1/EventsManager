import java.util.*;

public class PriceRangeFilter implements Filters{
    private final int LowerBound;
    private final int UpperBound;
    /*
    Price range is a visitor patter that implements filter and allow us to filter the event within
    a list by the LowerBound and UpperBound.
     */
    public PriceRangeFilter(int aLowerBound, int aUpperBound){
        LowerBound = aLowerBound;
        UpperBound = aUpperBound;
    }

    /*
    We are going to get the price of every event in the list and see if its within the range.
    Return the list that is within the range
     */
    @Override
    public List<Event> execute(List<Event> input){
        assert input != null;
        List<Event> dummy = new ArrayList<>();
        for(Event event: input){
            if(event.getPrice().isEmpty()){
                continue;
            }
            if(event.getPrice().get() <= UpperBound && event.getPrice().get() >= LowerBound){
                dummy.add(event);
            }
        }
        return dummy;
    }
}
