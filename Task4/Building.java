public abstract class Building {
    protected Intersection buildLocation;
    protected Player buildOwner; 

    public Building(Intersection buildingIntersection, Player owner) {
        buildLocation = buildingIntersection;
        buildOwner = owner;
    }

    public Intersection getBuildlocation() {
        return buildLocation;
    }

    public abstract int getVictoryPoints();

    public Player getOwner() {
        return buildOwner;
    }
}

