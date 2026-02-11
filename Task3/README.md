# Board Game Simulator

A Java-based board game simulator implementing a hexagonal tile-based strategy game similar to Settlers of Catan. This project includes a complete game engine with players, resources, buildings, and turn-based gameplay.

## Project Structure

```
boardgame/
├── src/           # Source code
│   ├── Board.java
│   ├── Building.java
│   ├── City.java
│   ├── Edge.java
│   ├── HexTerrain.java
│   ├── Intersection.java
│   ├── Player.java
│   ├── ResourceType.java
│   ├── Road.java
│   ├── Settlement.java
│   └── Simulator.java
├── test/          # Unit tests
│   ├── BoardTest.java
│   ├── BuildingTest.java
│   ├── HexTerrainTest.java
│   ├── IntersectionAndEdgeTest.java
│   ├── PlayerTest.java
│   └── SimulatorTest.java
└── demo/          # Demo application
    └── GameDemo.java
```

## Features

### Core Components

1. **Board System**
   - Hexagonal tile-based game board
   - 19 hex tiles with different terrain types
   - 54 intersections for building settlements/cities
   - 72 edges for building roads

2. **Player Management**
   - Multiple player support
   - Resource inventory system
   - Victory point tracking
   - Building ownership

3. **Game Resources**
   - Wood (from forests)
   - Brick (from hills)
   - Ore (from mountains)
   - Grain (from fields)
   - Wool (from pastures)

4. **Buildings**
   - Settlements (1 victory point)
   - Cities (2 victory points, upgraded from settlements)
   - Roads (connect settlements)

5. **Game Mechanics**
   - Dice rolling (2-12)
   - Resource production based on dice rolls
   - Building construction with resource costs
   - Victory point system
   - Turn-based gameplay

## Class Overview

### Board
Manages the game board, hex tiles, intersections, and edges.

**Key Methods:**
- `generateBoard()` - Creates the game board structure
- `getHexTile(int hexNum)` - Retrieves a specific hex tile
- `getIntersection(int location)` - Gets an intersection by ID
- `findEdge(int loc1, int loc2)` - Finds edge between intersections

### Player
Represents a player with resources, buildings, and actions.

**Key Methods:**
- `rollDice()` - Rolls two dice (2-12)
- `addResource(ResourceType, int)` - Adds resources to inventory
- `buildSettlement(Intersection)` - Builds a settlement
- `buildCity(Intersection)` - Upgrades settlement to city
- `buildRoad(Edge)` - Constructs a road

### Simulator
Main game controller managing gameplay flow.

**Key Methods:**
- `setupRound()` - Initializes the game
- `runRound()` - Executes one game round
- `runGame(int maxRounds)` - Runs complete game simulation
- `checkWinner()` - Checks for victory condition
- `resourceProduction(int)` - Distributes resources based on dice roll

### HexTerrain
Represents a hexagonal tile on the board.

**Terrain Types:**
- FOREST → Wood
- HILLS → Brick
- MOUNTAINS → Ore
- FIELDS → Grain
- PASTURE → Wool
- DESERT → No resource

### Building Classes
- `Building` - Abstract base class
- `Settlement` - Basic building (1 VP)
- `City` - Advanced building (2 VP)
- `Road` - Connection between intersections

## Building Costs

| Structure  | Resources Required                |
|-----------|-----------------------------------|
| Settlement | 1 Wood, 1 Brick, 1 Wool, 1 Grain |
| City      | 3 Ore, 2 Grain                    |
| Road      | 1 Wood, 1 Brick                   |

## Compilation and Running

### Compile the project:
```bash
# Compile all source files
cd src
javac *.java

# Compile demo
cd ../demo
javac -cp ../src GameDemo.java

# Compile tests (requires JUnit 5)
cd ../test
javac -cp ../src:junit-platform-console-standalone.jar *.java
```

### Run the demo:
```bash
cd demo
java -cp .:../src GameDemo
```

### Run the tests:
```bash
cd test
java -jar junit-platform-console-standalone.jar --class-path ../src:. --scan-class-path
```

## Usage Example

```java
// Create game simulator
Simulator game = new Simulator();

// Add players
Player player1 = new Player("Alice");
Player player2 = new Player("Bob");
game.addPlayer(player1);
game.addPlayer(player2);

// Run the game for 20 rounds
game.runGame(20);

// Check winner
if (game.getWinner() != null) {
    System.out.println("Winner: " + game.getWinner().getId());
}
```

## Game Rules

1. **Setup Phase**
   - Board is generated with random hex placement
   - Players receive starting resources

2. **Turn Sequence**
   - Roll dice
   - Distribute resources based on roll
   - Build structures (optional)
   - Check for winner

3. **Victory Condition**
   - First player to reach 10 victory points wins

4. **Victory Points**
   - 1 point per settlement
   - 2 points per city

## Testing

The project includes comprehensive unit tests for all major components:

- **PlayerTest** - Tests player actions and resource management
- **BoardTest** - Tests board generation and structure
- **SimulatorTest** - Tests game flow and logic
- **BuildingTest** - Tests settlement and city functionality
- **HexTerrainTest** - Tests terrain and resource mapping
- **IntersectionAndEdgeTest** - Tests board connectivity

## Future Enhancements

Potential additions to the game:

1. Development cards
2. Trading between players
3. Longest road / largest army bonuses
4. Robber mechanics
5. Port trading
6. Network multiplayer support
7. AI players
8. GUI interface

## UML Design

The implementation follows the UML class diagram with:
- Proper inheritance (Building → Settlement/City)
- Composition relationships (Board contains HexTerrain, Intersections, Edges)
- Association relationships (Player owns Buildings and Roads)
- Encapsulation of game state

## License

This is an educational project based on board game mechanics.

## Credits

Developed based on UML specifications for a hexagonal tile-based strategy game.
