public interface Visitor {
    /*
    Visitor will allow us to easily extend the function that can interact with "events object"
     */
    public double FestivalVisitor(Festival festival);
    public double FiltersResultVisitor(FilterResult filterResult);
    public double EventVisitor(EventTemp eventTemp);
}
