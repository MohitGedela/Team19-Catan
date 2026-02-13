public class Intersection {
    int intersectionID;
    Building intersectionBuilding = new Building();

    Intersection(int node) {
        intersectionID = node;
    }

///
    public int getIntersectionLocation() {
        return intersectionID;
    }    
}