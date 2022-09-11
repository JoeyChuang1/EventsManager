import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
Controller to manage events hosted on EventBrite.
 */
public class EventManagement {
    private List<Event> aHostedEvents = new ArrayList<Event>();

    /*
    Method to host a new concert event
     */
    public void addConcertEvent(String aName, Location aLocation, LocalDate aDate, double aPrice, int aNumTickets, String aArtists, List<String> aVIP){
            aHostedEvents.add(Concerts.ConcertsFactory(aName,aLocation,aDate,aPrice,aNumTickets,aArtists,aVIP));
    }

    /*
    Method to host a new Gala event
     */
    public void addGalaEvent(String aName, Location aLocation, LocalDate aDate, Double aPrice, Integer aNumTickets, List<String> aVIP){
            aHostedEvents.add(Galas.GalasFactory(aName, aLocation, aDate, aPrice, aNumTickets, aVIP));
    }

    /*
    Method to host a new Screening event
     */
    public void addScreeningEvent(String aName, Location aLocation, LocalDate aDate, Double aPrice, Integer aNumTickets, Rating aRating){
          aHostedEvents.add(Screenings.ScreenFactory(aName, aLocation, aDate, aPrice, aNumTickets, aRating));
    }

    /*
    Method to host a new Workshop event
     */
    public void addWorkshopEvent(String aName, Location aLocation, LocalDate aDate, double aPrice, int aNumTickets, List<Workshops> aWorkshop){
            aHostedEvents.add(Workshops.WorkshopsFactory(aName, aLocation, aDate, aPrice, aNumTickets, aWorkshop));
    }
    /*
    Method to host a new Festival event
     */
    public void addFestival(String name, List<Event> EVENT){
        aHostedEvents.add(Festival.FesFactory(name, EVENT));
    }

    /*
    Return the list of hosted events on EventBrite.
    This method assumes that Events are immutable.
     */
    public ArrayList<Event> getHostedEvents(){
        return new ArrayList<Event>(aHostedEvents);
    }
}