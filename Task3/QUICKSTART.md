# Quick Start Guide

## Prerequisites
- Java Development Kit (JDK) 8 or higher
- (Optional) JUnit 5 for running tests

## Getting Started

### 1. Compile the Project

```bash
# Navigate to the src directory
cd src

# Compile all Java files
javac *.java

# Or use the build script from the root directory
cd ..
chmod +x build.sh
./build.sh
```

### 2. Run the Demo

```bash
# From the demo directory
cd demo
javac -cp ../src GameDemo.java
java -cp .:../src GameDemo
```

Expected output:
```
========================================
   BOARD GAME SIMULATOR DEMO
========================================

Player Alice joined the game.
Player Bob joined the game.
Player Charlie joined the game.

========================================
    STARTING GAME SIMULATION
========================================
Setting up game...
...
```

### 3. Understanding the Code

#### Creating a Simple Game

```java
// 1. Create a simulator
Simulator game = new Simulator();

// 2. Create and add players
Player alice = new Player("Alice");
Player bob = new Player("Bob");
game.addPlayer(alice);
game.addPlayer(bob);

// 3. Setup the game
game.setupRound();

// 4. Run rounds manually
for (int i = 0; i < 10; i++) {
    game.runRound();
    if (game.checkWinner()) {
        System.out.println("Winner: " + game.getWinner().getId());
        break;
    }
}

// Or run automatically
game.runGame(20); // Runs up to 20 rounds or until winner
```

#### Working with the Board

```java
Board board = new Board();
board.generateBoard();

// Get a hex tile
HexTerrain hex = board.getHexTile(5);
System.out.println("Terrain: " + hex.getTerrain());
System.out.println("Resource: " + hex.getResource());

// Get an intersection
Intersection intersection = board.getIntersection(10);
System.out.println("Is occupied: " + intersection.isOccupied());
```

#### Player Actions

```java
Player player = new Player("Demo");

// Add resources
player.addResource(ResourceType.WOOD, 5);
player.addResource(ResourceType.BRICK, 3);

// Check resources
if (player.checkResource(ResourceType.WOOD, 1)) {
    System.out.println("Player has enough wood!");
}

// Build structures (requires board setup)
Board board = new Board();
board.generateBoard();
Intersection location = board.getIntersection(0);

// Give resources for settlement
player.addResource(ResourceType.WOOL, 1);
player.addResource(ResourceType.GRAIN, 1);

player.buildSettlement(location);
System.out.println("Victory Points: " + player.getVictoryPoints());
```

### 4. Run Tests

If you have JUnit 5:

```bash
cd test

# Download JUnit standalone JAR (if not available)
wget https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/1.9.3/junit-platform-console-standalone-1.9.3.jar

# Compile tests
javac -cp ../src:junit-platform-console-standalone-1.9.3.jar *.java

# Run tests
java -jar junit-platform-console-standalone-1.9.3.jar --class-path ../src:. --scan-class-path
```

## Project Structure

```
boardgame/
├── src/              # All source code
├── test/             # JUnit tests
├── demo/             # Demo application
├── README.md         # Full documentation
├── QUICKSTART.md     # This file
└── build.sh          # Build script
```

## Key Classes to Know

1. **Simulator** - Main game controller
2. **Board** - Game board management
3. **Player** - Player state and actions
4. **HexTerrain** - Individual hex tiles
5. **Building** - Base class for settlements/cities
6. **ResourceType** - Enum for game resources

## Common Tasks

### Change Number of Players
```java
Simulator game = new Simulator();
for (int i = 1; i <= 4; i++) {
    game.addPlayer(new Player("Player" + i));
}
```

### Modify Victory Points Required
Edit `Simulator.java`:
```java
private static final int WINNING_POINTS = 10; // Change this value
```

### Add More Resources to a Player
```java
player.addResource(ResourceType.WOOD, 10);
player.addResource(ResourceType.BRICK, 10);
player.addResource(ResourceType.WOOL, 10);
player.addResource(ResourceType.GRAIN, 10);
player.addResource(ResourceType.ORE, 10);
```

## Troubleshooting

### Compilation Errors
- Ensure JDK is installed: `javac -version`
- Check all files are in correct directories
- Compile in order: src files first, then demo/tests

### Runtime Errors
- Make sure classpath includes src directory
- Check that Board is generated before accessing intersections/edges

## Next Steps

1. Read the full README.md for detailed documentation
2. Explore the test files to see usage examples
3. Modify GameDemo.java to experiment with different scenarios
4. Extend the classes to add new features

## Support

This is an educational project. For questions about the implementation:
1. Check the inline code comments
2. Review the unit tests for usage examples
3. Refer to the UML diagram for class relationships

Happy coding!
