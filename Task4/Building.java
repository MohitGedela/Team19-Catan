public abstract class Building {
    protected Intersection buildLocation;

    public Building(Intersection buildingIntersection) {
        buildLocation = buildingIntersection;
    }

    public Intersection getBuildlocation() {
        return buildLocation;
    }

    public abstract int getVictoryPoints();
}