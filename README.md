# Avaj Launcher

Aircraft simulation program built as part of the **42 Network** curriculum. Models weather-driven behavior for three aircraft types using classic OOP design patterns.

---

## Description

Aircraft react to four weather conditions — **SUN, RAIN, FOG, SNOW** — by adjusting their coordinates each simulation round. When an aircraft reaches ground level (height 0), it lands and unregisters automatically. All events are logged to `simulation.txt`.

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
    ├── Tower.java                    ← observable: register / unregister / conditionChanged
    ├── WeatherTower.java             ← Tower that triggers weather changes
    ├── WeatherProvider.java          ← Singleton weather generator
    ├── AircraftFactory.java          ← Singleton factory
    ├── Logger.java                   ← writes simulation.txt
    └── InvalidScenarioException.java ← custom exception (bonus)
```

The class structure follows the UML class diagram provided with the subject.

---

## Stack

Java 21 · OOP · Design Patterns · No external dependencies
