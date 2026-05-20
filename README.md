
[![Made with Java](https://img.shields.io/badge/Made%20with-Java-red.svg)](https://www.java.com/)
[![Framework: Swing](https://img.shields.io/badge/Framework-Swing-orange.svg)]()
[![Platform: Desktop](https://img.shields.io/badge/Platform-Desktop-lightgrey.svg)]()
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)
[![Game Project](https://img.shields.io/badge/Type-Game-blue.svg)]()

# Java Card Game

<p align="center">
  <img src="https://raw.githubusercontent.com/bobblet11/Java-card-game/main/Images/readme/playthrough.gif" alt="Gameplay" width="800">
</p>

A small, single-player Java card game with a simple GUI. This repository contains the core classes used to represent a deck of cards, hands, and a minimal graphical interface to run and test the game locally.

## Features

- Simple Swing-based GUI for gameplay and testing
- Core card model: `Card`, `Deck`, `Hand`
- Easy to compile and run with plain `javac` / `java`

## Requirements

- Java JDK 11 or newer

## Build & Run

Open a terminal in the project root and run:

```bash
javac *.java
java MyGui
```

Or compile and run only the GUI class (the other classes will be compiled as needed):

```bash
javac MyGui.java
java MyGui
```

If you're using an IDE (IntelliJ IDEA, Eclipse, VS Code), import the folder as a Java project and run the `MyGui` class.

## Project Structure

- `Card.java` — represents a playing card (rank, suit)
- `Deck.java` — deck operations: shuffle, draw, reset
- `Hand.java` — holds a player's hand and helper methods
- `MyGui.java` — Swing GUI entry point for the game
- `Images/` — supporting images and screenshots

## Gameplay

This project is a simple demonstration rather than a full game with rules. Use the GUI to deal cards, view a hand, and explore the basic mechanics provided by the model classes.

<p align="center">
  <img src="https://raw.githubusercontent.com/bobblet11/Java-card-game/main/Images/readme/starting_page.png" alt="Gameplay" width="800">
</p>

<p align="center">
  <img src="https://raw.githubusercontent.com/bobblet11/Java-card-game/main/Images/readme/hand_dealt_ss.png" alt="Gameplay" width="800">
</p>

<p align="center">
  <img src="https://raw.githubusercontent.com/bobblet11/Java-card-game/main/Images/readme/replacing_card_1_ss.png" alt="Gameplay" width="800">
</p>

<p align="center">
  <img src="https://raw.githubusercontent.com/bobblet11/Java-card-game/main/Images/readme/reveal_dealer_lost_ss.png" alt="Gameplay" width="800">
</p>

<p align="center">
  <img src="https://raw.githubusercontent.com/bobblet11/Java-card-game/main/Images/readme/reveal_dealer_win_ss.png" alt="Gameplay" width="800">
</p>

<p align="center">
  <img src="https://raw.githubusercontent.com/bobblet11/Java-card-game/main/Images/readme/gameover_ss.png" alt="Gameplay" width="800">
</p>


## License

This project is provided as-is. Add a license file if you intend to share or publish it (for example, `LICENSE` with the MIT license).
