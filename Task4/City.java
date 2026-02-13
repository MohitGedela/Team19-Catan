public class City extends Building {
    
    public City(Intersection cityIntersection) {
        super(cityIntersection);
    }

    @Override
    public int getVictoryPoints() {
        return 2;
    }

}