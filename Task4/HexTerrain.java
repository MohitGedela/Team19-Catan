public abstract class HexTerrain {
    protected int hexID;
    protected HexBoardNum hexNumber;

    public HexTerrain(int hexagonID, HexBoardNum tileNum) {
        hexID = hexagonID;
        hexNumber = tileNum;
    }

    public int getHexID() {
        return hexID;
    }

    public HexBoardNum getHexNumber() {
        return hexNumber;
    }

    public abstract ResourceType produceResource();
    public abstract boolean productionStatus();
}