public class Settlement extends Building {
    
    public Settlement(Intersection settlementIntersection) {
        super(settlementIntersection);
    }

    @Override
    public int getVictoryPoints() {
        return 1;
    }

}