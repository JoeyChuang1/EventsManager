import java.util.*;

public class ComplexFilter implements Filters {
    private final List<Filters> filters = new ArrayList<>();

    /*
    Complex Filter will be taking in a bunch of filter
     */
    public ComplexFilter(List<Filters> afilters) {
        for (Filters filter : afilters) {
            filters.add(filter);
        }
    }

    /*
    execute will be able to execute all the filter storing in "filters" one by one
    and in the end we will return the final result (that has applied all the filters)
     */
    @Override
    public List<Event> execute(List<Event> input) {
        List<Event> dummy = new ArrayList<>(input);
        for (Filters filter: filters){
            dummy = filter.execute(dummy);
        }
        return dummy;
    }
}
