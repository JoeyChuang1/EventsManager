import java.util.List;

public class VIPCount implements Visitor{

    /*
    This is a call back function that is going to find total VIP count inside a festival object.
    We are going to call AcceptVisitor on every event within this visitor.
     */
    @Override
    public double FestivalVisitor(Festival festival){
        List<Event> a = festival.getFesEvent();
        double b = 0;
        for(Event event: a){
            b = b + event.AcceptVisitor(this);
        }
        return b;
    }
    /*
    This is a call back function that is going to get aFilterEvent inside the filter result, and
    we are going to call AcceptVistor on every event in the list.
     */
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
    /*
    This is a call back function that is
    going to get the number of VIP from Screenings, Galas, Concerts and Wroshops
    Of course, we only have VIP in Concerts and Galas, hence we are going to return 0.00 when the event
    is screening and workshop
     */
    @Override
    public double EventVisitor(EventTemp eventTemp){
        if(eventTemp instanceof Concerts) {
            return ((Concerts) eventTemp).getVIPNum();
        }else if (eventTemp instanceof Galas){
            return ((Galas) eventTemp).getVIPNum();
        }  else if (eventTemp instanceof Screenings){
            return 0.00; }
        else if (eventTemp instanceof Workshops){
            return 0.00;} else {
            return 0.00; }
    }

}
