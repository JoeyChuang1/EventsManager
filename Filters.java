import java.util.*;

public interface Filters {
    /*
    Filter is visitor design pattern that
    will allow us to easily extend the function that can interact with "List<event> object"
     */
    public List<Event> execute(List<Event> input);
}
