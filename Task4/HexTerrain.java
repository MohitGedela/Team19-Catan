public abstract class HexTerrain {
    protected int hexID;
    protected HexBoardNum hexNumber;

    public HexTerrain(int hexagonID, HexBoardNum tileNum) {
        hexID = hexagonID;
        hexNumber = tileNum;
    }

    public abstract ResourceType produceResource();
}