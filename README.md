# Avaj Launcher

Aircraft simulation program built as part of the **42 Network** curriculum. Models weather-driven behavior for three aircraft types using classic OOP design patterns.

---

## Description

Avaj Launcher is the first project of the Java branch at 42. The goal is to turn a given UML class diagram into clean object-oriented code, applying the **Observer**, **Singleton** and **Factory** design patterns.

The program simulates aircraft flying under changing weather conditions:

1. It reads a scenario file: the first line is the number of simulation rounds, and each following line describes an aircraft (`TYPE NAME LONGITUDE LATITUDE HEIGHT`).
2. The whole file is validated first. On invalid input, an error is printed to standard output and the program stops without writing any output file.
3. Each aircraft is created through the factory and registers to the weather tower.
4. On every round the weather changes. Each point in 3D space has its own weather — **SUN, RAIN, FOG** or **SNOW** — so each aircraft moves according to its type and the weather at its position, and logs a message.
5. Height is capped at 100. When an aircraft reaches height 0, it lands and unregisters from the tower.
6. Every event is written to `simulation.txt`.

### How the code is organized

- **`Simulator`** is the entry point: it validates the scenario, then runs the simulation.
- **Aircraft**: `Flyable` (abstract) → `Aircraft` → `Balloon`, `JetPlane`, `Helicopter`. Each type defines its reaction to the weather in `updateConditions()`.
- **`weather/`**: `Tower` keeps the list of registered aircraft and notifies them (Observer), `WeatherTower` triggers the weather changes, and `WeatherProvider` computes the weather from the coordinates (Singleton).
- **`AircraftFactory`** creates the right aircraft type and assigns each one a unique id (Singleton + Factory).
- **`Coordinates`** is an immutable 3D point, **`Logger`** writes `simulation.txt`, and **`exceptions/`** holds the custom exception used for invalid scenarios (bonus).

---

## Design Patterns

| Pattern | Class | Description |
|---|---|---|
| Observer | `Tower` / `Flyable` | Aircraft register to the `WeatherTower` and are notified on every weather change |
| Singleton | `WeatherProvider` | Single instance that generates the weather for a given point |
| Singleton + Factory | `AircraftFactory` | Single instance that creates the correct aircraft type and assigns unique ids |

---

## Aircraft Behavior

| Type | SUN | RAIN | FOG | SNOW |
|---|---|---|---|---|
| Balloon | longitude +2, height +4 | height -5 | height -3 | height -15 |
| JetPlane | latitude +10, height +2 | latitude +5 | latitude +1 | height -7 |
| Helicopter | longitude +10, height +2 | longitude +5 | longitude +1 | height -12 |

---

## Usage

### Compile

```bash
find * -name "*.java" > sources.txt
javac @sources.txt
```

### Run

```bash
java com.jgravalo.avaj.simulator.Simulator scenario.txt
cat simulation.txt
```

### With make

```bash
make test                 # compile, run scenario.txt and print simulation.txt
make test scenario1.txt   # same with another scenario file
make fclean               # remove .class files and simulation.txt
```

---

## Scenario File Format

```
5                          ← number of simulation rounds
Balloon B1 5 5 50          ← TYPE NAME LONGITUDE LATITUDE HEIGHT
JetPlane J1 10 20 60
Helicopter H1 15 10 30
Helicopter H4 20 5 70
```

**Rules:**
- Coordinates must be positive integers
- Height above 100 is capped at 100
- Valid types: `Balloon`, `JetPlane`, `Helicopter`
- The whole file is validated before the simulation starts: on invalid input an error is printed to standard output and no `simulation.txt` is written

---

## Project Structure

```
avaj-launcher/
├── makefile
├── sources.txt
├── scenario.txt
└── com/jgravalo/avaj/simulator/
    ├── Simulator.java                ← entry point: validates the scenario, then runs it
    ├── Flyable.java                  ← abstract observer
    ├── Aircraft.java                 ← base aircraft: movement, landing, log format
    ├── Balloon.java
    ├── JetPlane.java
    ├── Helicopter.java
    ├── Coordinates.java              ← immutable 3D point
    ├── AircraftFactory.java          ← Singleton factory
    ├── Logger.java                   ← writes simulation.txt
    ├── weather/
    │   ├── Tower.java                ← observable: register / unregister / conditionChanged
    │   ├── WeatherTower.java         ← Tower that triggers weather changes
    │   └── WeatherProvider.java      ← Singleton weather generator
    └── exceptions/
        └── InvalidScenarioException.java ← custom exception (bonus)
```

Aircraft classes share the package with `Coordinates` and `Simulator` because the UML declares the `Coordinates` constructor package-private.

The class structure follows the UML class diagram provided with the subject.

---

## Stack

Java 21 · OOP · Design Patterns · No external dependencies
