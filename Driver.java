import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Driver {
    public static void main(String[] args){
        //creating all the object needed for our result
        LocalDate date = LocalDate.of(2025, 2, 8);
        LocalDate date2 = LocalDate.of(2026, 2, 8);
        String name1 = "joey";
        String name2 = "angus";
        List<String> vip = new ArrayList<>();
        vip.add(name1);
        vip.add(name2);
        EventManagement manager = new EventManagement();
        //create the object through EventManager
        manager.addConcertEvent("concert 1",Location.BellCentre, date, 30.0, 3, "Artist1", vip);
        manager.addGalaEvent("Gala 1",Location.ParcJeanDrapeau, date, 20.0, 3, vip);
        manager.addScreeningEvent("Screening 1", Location.BellCentre, date2, 25.0, 3,   Rating.G);
        List<Event> Array = manager.getHostedEvents();
        manager.addFestival("Festival 1", Array);
        List<Event> ListOfEvent = manager.getHostedEvents();
        //Get the filter that we want
        FilterResult filter = new FilterResult(ListOfEvent);
        Filters LocationFilter = new LocationFilter(Location.BellCentre);
        Filters RangeFilter = new PriceRangeFilter(10, 26);
        List<Filters> ComplexFilter = new ArrayList<>();
        //below is how to use filter
        ComplexFilter.add(LocationFilter);
        ComplexFilter.add(RangeFilter);
        Filters ComplexFiilter = new ComplexFilter(ComplexFilter);
        //we are be able to retrieve what we want by applying different filter
        //eg we can change the code below to:
        // FilterResult result = filter.filtering(LocationFilter)
        //FilterResult result = filter.filtering(RangeFilter)
        FilterResult result = filter.filtering(ComplexFiilter);
        //how to use Visitor
        Visitor vistor1 = new ExpectedProfit(100, 100, 50, 100);
        Double Profit = filter.acceptVisitor(vistor1);
        System.out.println(Profit);//should be 390
        Visitor vistor = new VIPCount();
        filter.acceptVisitor(vistor);
        Double VIPCountShouldBe8 = filter.acceptVisitor(vistor);
        System.out.println(VIPCountShouldBe8);
    }
}
