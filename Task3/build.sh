#!/bin/bash

# Board Game Simulator Build Script

echo "========================================="
echo "  Board Game Simulator - Build Script"
echo "========================================="
echo ""

# Create output directories
mkdir -p bin
mkdir -p bin/src
mkdir -p bin/demo
mkdir -p bin/test

# Compile source files
echo "Compiling source files..."
javac -d bin/src src/*.java

if [ $? -eq 0 ]; then
    echo "✓ Source files compiled successfully"
else
    echo "✗ Error compiling source files"
    exit 1
fi

# Compile demo
echo ""
echo "Compiling demo..."
javac -cp bin/src -d bin/demo demo/*.java

if [ $? -eq 0 ]; then
    echo "✓ Demo compiled successfully"
else
    echo "✗ Error compiling demo"
    exit 1
fi

echo ""
echo "========================================="
echo "  Build Complete!"
echo "========================================="
echo ""
echo "To run the demo:"
echo "  cd bin/demo && java -cp .:../src GameDemo"
echo ""
