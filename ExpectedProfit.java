import java.util.*;

public class ExpectedProfit implements Visitor{

    double CPro;
    double WPro;
    double GPro;
    double SPro;
    /*
    ExpectedProfit will follow the formula and calculate the expected profit
     */
    public ExpectedProfit(double ConcertProfit, double WorkshopProfit, double GalaProfit, double ScreeningProfit){
        CPro =ConcertProfit/100.00;
        WPro = WorkshopProfit/100.00;
        GPro = GalaProfit/100.00;
        SPro = ScreeningProfit/100.00;
    }

    public double getCPro() {
        return CPro;
    }

    public double getWPro() {
        return WPro;
    }

    public double getGPro() {
        return GPro;
    }

    public double getSPro() {
        return SPro;
    }

    //a call back function that will get the festival and visit the festival
    //we will sum up all the event price within the festival
    @Override
    public double FestivalVisitor(Festival festival){
            List<Event> a = festival.getFesEvent();
            double b = 0;
            for(Event event: a){
                b = b + event.AcceptVisitor(this);
            }
            return b;
    }

    //this is a call back function that will be calling acceptVisitor on all event
    //insider this filterResult
    //we will sum up the value of ALL the event inside this filterREesult
    @Override
    public double FiltersResultVisitor(FilterResult filterResult){
        List<Event> dummy;
        dummy = filterResult.getaFilteredEvents();
        double stack = 0;
        for(Event dum: dummy){
                stack = stack + dum.AcceptVisitor(this);
        }
        return stack;
    }

    //This is the call back function for each event (other than festival)
    //we will be counting the ExpectedProfit for each event and return it
    @Override
    public double EventVisitor(EventTemp eventTemp){
        if(eventTemp instanceof Concerts) {
            return eventTemp.getNumTickets().orElse(0) * eventTemp.getPrice().orElse(0.00)*CPro;
        }else if (eventTemp instanceof Galas){
            return eventTemp.getNumTickets().orElse(0) * eventTemp.getPrice().orElse(0.00)*GPro;
        }  else if (eventTemp instanceof Screenings){
            return eventTemp.getNumTickets().orElse(0) * eventTemp.getPrice().orElse(0.00)*SPro;  }
        else if (eventTemp instanceof Workshops){
            return eventTemp.getNumTickets().orElse(0) * eventTemp.getPrice().orElse(0.00)*WPro;}
        else {
        return 0.00; }
    }
}
