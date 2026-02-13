public class Edge {
    private int start;
    private int end;

    public Edge(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public int[] getLocation() {
        return new int[] {start, end};
    }
}
