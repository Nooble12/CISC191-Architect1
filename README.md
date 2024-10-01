# CISC191
Intermediate Java Programming Architect Assignment 1
## Prerequisites
1. Maven
2. Git
3. JDK 1.8
## Building
mvn clean install
## Running
java -jar Server/target/Server-1.0.0.jar  
java -jar Client/target/Client-1.0.0.jar

## Course Objectives
Module 1: In the game, a one-dimensional array stores a loot table of items that the player can roll to obtain items.
Module 2: The shop uses a two-dimensional array to display various items for the player to purchase.
Module 3: The game is displayed using JavaFX.
Module 4: The Item class has numerous child classes such as foodItem class and ComputerItem class. Each item has its own implementation with different rewards, chances to fail, and names.
Module 5: On exiting the game, player money and inventory will be saved via Java serialization class into a .ser file.
Module 6: When opening the "Patch Notes" button, the client will send a request to the server for the update notes and the server will send back a string to be displayed on the JavaFX Window.

## Common Module
Shared classes between client and server modules.
## Server Module
The server application handles multiple clients.
## Client Module
The client application is used to connect to the server.
