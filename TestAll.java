import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.*;

public class TestAll {
    @Test
    //This test if we can actually change the name of the PlayList throough command_interface
    public void test1() {
        //checking that all flyweight object work
        EventManagement events = new EventManagement();
        LocalDate date = LocalDate.of(2020, 1, 8);
        String name1 = "joey";
        String name2 = "angus";
        List<String> vip = new ArrayList<>();
        vip.add(name1);
        vip.add(name2);
        Event a = Concerts.ConcertsFactory("concert 1",Location.BellCentre, date, 2.0, 3, "Artist1", vip);
        Event b = Concerts.ConcertsFactory("(Flyweight) concert 1",Location.BellCentre, date, 2.0, 3, "Artist1", vip);
        assertEquals(a, b);
    }

    @Test
    public void test2() {
        EventManagement events = new EventManagement();
        LocalDate date = LocalDate.of(2020, 1, 8);
        String name1 = "joey";
        String name2 = "angus";
        List<String> vip = new ArrayList<>();
        vip.add(name1);
        vip.add(name2);
        events.addConcertEvent("concert 1",Location.BellCentre, date, 2.0, 3, "Artist1", vip);
        events.addScreeningEvent("Screening 1", Location.OlympicStadium,date, 2.0, 3,   Rating.G);
        assertEquals(events.getHostedEvents().size(), 2);
    }

    @Test
    public void test3() {
        //testing for the flyweight again
        LocalDate date = LocalDate.of(2020, 1, 8);
        String name1 = "joey";
        String name2 = "angus";
        List<String> vip = new ArrayList<>();
        vip.add(name1);
        vip.add(name2);
        Event a = Concerts.ConcertsFactory("concert 1",Location.BellCentre, date, 2.0, 3, "Artist1", vip);
        Event b = Screenings.ScreenFactory("(Flyweight) SCREENING 1",Location.BellCentre, date, 2.0, 3, Rating.G);
        assertEquals(b, null);
    }

    @Test
    public void test4() {
        //this test will check if addPlayable can be done through the interface
        //use the reflection to see if the variable have been correctly set
        LocalDate date = LocalDate.of(2020, 1, 8);
        String name1 = "joey";
        String name2 = "angus";
        List<String> vip = new ArrayList<>();
        vip.add(name1);
        vip.add(name2);
        Event a = Concerts.ConcertsFactory("concert 1",Location.BellCentre, date, 2.0, 3, "Artist1", vip);
        Class class1 = a.getClass();
        Class class2 = Concerts.class;
        try{
            Field locate = class1.getDeclaredField("Artists");
            locate.setAccessible(true);
            locate.set(a, "Artist1");
        }catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void test5() {
        //This thing will be testing for the function expected profit
        LocalDate date = LocalDate.of(2020, 1, 8);
        String name1 = "joey";
        String name2 = "angus";
        List<String> vip = new ArrayList<>();
        vip.add(name1);
        vip.add(name2);
        Event a = Concerts.ConcertsFactory("concert 1",Location.BellCentre, date, 2.0, 3, "Artist1", vip);
        Event b = Screenings.ScreenFactory("Screening 1", Location.OlympicStadium,date, 2.0, 3,   Rating.G);
        Event c = Galas.GalasFactory("Gala 1",Location.ParcJeanDrapeau, date, 2.0, 3, vip);
        List<Event> array = new ArrayList<>();
        array.add(a);
        array.add(b);
        array.add(c);
        Festival fes = Festival.FesFactory("fes1", array);
        array.add(fes);
        FilterResult filter = new FilterResult(array);
        Visitor vistor = new VIPCount();
        filter.acceptVisitor(vistor);
        Double VIPCountShouldBe8 = filter.acceptVisitor(vistor);
        assertEquals(VIPCountShouldBe8, 8.0);
    }
    @Test
    public void test6() {
        //testing fo expectedValue
        LocalDate date = LocalDate.of(2020, 1, 8);
        String name1 = "joey";
        String name2 = "angus";
        List<String> vip = new ArrayList<>();
        vip.add(name1);
        vip.add(name2);
        Event a = Concerts.ConcertsFactory("concert 1",Location.BellCentre, date, 2.0, 3, "Artist1", vip);
        Event b = Screenings.ScreenFactory("Screening 1", Location.OlympicStadium,date, 2.0, 3,   Rating.G);
        Event c = Galas.GalasFactory("Gala 1",Location.ParcJeanDrapeau, date, 2.0, 3, vip);
        List<Event> array = new ArrayList<>();
        array.add(a);
        array.add(b);
        array.add(c);
        Festival fes = Festival.FesFactory("fes1", array);
        array.add(fes);
        FilterResult filter = new FilterResult(array);
        Visitor vistor1 = new ExpectedProfit(100, 100, 50, 100);
        filter.acceptVisitor(vistor1);
        Double Profit = filter.acceptVisitor(vistor1);
        assertEquals(Profit, 30.0);
    }
    @Test
    public void test7() {
        //testing for filters
        //testing fo expectedValue
        LocalDate date = LocalDate.of(2022, 2, 8);
        String name1 = "joey";
        String name2 = "angus";
        List<String> vip = new ArrayList<>();
        vip.add(name1);
        vip.add(name2);
        Event a = Concerts.ConcertsFactory("concert 1",Location.BellCentre, date, 30.0, 3, "Artist1", vip);
        Event b = Screenings.ScreenFactory("Screening 1", Location.OlympicStadium,date, 25.0, 3,   Rating.G);
        Event c = Galas.GalasFactory("Gala 1",Location.ParcJeanDrapeau, date, 20.0, 3, vip);
        List<Event> array = new ArrayList<>();
        array.add(a);
        array.add(b);
        array.add(c);
        FilterResult filter = new FilterResult(array);
        Filters RangeFilter = new PriceRangeFilter(10, 26);
        FilterResult result = filter.filtering(RangeFilter);
        //a with the aPrice = 30 has been filtered out
        assertEquals(result.getaFilteredEvents().size(), 2);
    }
    @Test
    public void test8() {
        //testing for filters
        //testing for LocationFilter
        LocalDate date = LocalDate.of(2023, 2, 8);
        LocalDate date2 = LocalDate.of(2024, 2, 8);
        String name1 = "joey";
        String name2 = "angus";
        List<String> vip = new ArrayList<>();
        vip.add(name1);
        vip.add(name2);
        Event a = Concerts.ConcertsFactory("concert 1",Location.BellCentre, date, 30.0, 3, "Artist1", vip);
        Event b = Screenings.ScreenFactory("Screening 1", Location.BellCentre, date2, 25.0, 3,   Rating.G);
        Event c = Galas.GalasFactory("Gala 1",Location.ParcJeanDrapeau, date, 20.0, 3, vip);
        List<Event> array = new ArrayList<>();
        array.add(a);
        array.add(b);
        array.add(c);
        FilterResult filter = new FilterResult(array);
        Filters LocationFilter = new LocationFilter(Location.BellCentre);
        FilterResult result = filter.filtering(LocationFilter);
        //a with the aPrice = 30 has been filtered out
        assertEquals(result.getaFilteredEvents().size(), 2);
    }
    @Test
    public void test9() {
        LocalDate date = LocalDate.of(2025, 2, 8);
        LocalDate date2 = LocalDate.of(2026, 2, 8);
        String name1 = "joey";
        String name2 = "angus";
        List<String> vip = new ArrayList<>();
        vip.add(name1);
        vip.add(name2);
        EventManagement manager = new EventManagement();
        manager.addConcertEvent("concert 1",Location.BellCentre, date, 30.0, 3, "Artist1", vip);
        manager.addGalaEvent("Gala 1",Location.ParcJeanDrapeau, date, 20.0, 3, vip);
        manager.addScreeningEvent("Screening 1", Location.BellCentre, date2, 25.0, 3,   Rating.G);
        List<Event> ListOfEvent = manager.getHostedEvents();
        FilterResult filter = new FilterResult(ListOfEvent);
        Filters LocationFilter = new LocationFilter(Location.BellCentre);
        Filters RangeFilter = new PriceRangeFilter(10, 26);
        List<Filters> ComplexFilter = new ArrayList<>();
        ComplexFilter.add(LocationFilter);
        ComplexFilter.add(RangeFilter);
        Filters ComplexFiilter = new ComplexFilter(ComplexFilter);
        FilterResult result = filter.filtering(ComplexFiilter);
        //ONLY ONE ELEMENT WILL SATISFIE BOTH
        assertEquals(result.getaFilteredEvents().size(), 1);
    }
    }