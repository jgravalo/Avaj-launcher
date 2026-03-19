# Avaj Launcher

Aircraft simulation program built as part of the **42 Network** curriculum. Models weather-driven behavior for three aircraft types using classic OOP design patterns.

---

## Description

Aircraft react to four weather conditions — **SUN, RAIN, FOG, SNOW** — by adjusting their coordinates each simulation round. When an aircraft reaches ground level (height 0), it lands and unregisters automatically. All events are logged to `simulation.txt`.

---

## Design Patterns

| Pattern | Class | Description |
|---|---|---|
| Singleton | `WeatherTower` | Single global instance managing all weather events |
| Observer | `Weatherable` | Aircraft register/unregister to receive weather updates |
| Factory | `AircraftFactory` | Creates the correct aircraft type from the scenario file |

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
make
```

### Run

```bash
java avaj.Simulator scenario.txt
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
- Height must be between 0 and 100
- Valid types: `Balloon`, `JetPlane`, `Helicopter`

---

## Project Structure

```
avaj-launcher/
├── Makefile
├── sources.txt
├── scenario.txt
└── avaj/
    ├── Simulator.java                ← main entry point
    ├── Tower.java                    ← logging base class
    ├── WeatherTower.java             ← Singleton + Observable
    ├── Weatherable.java              ← Observer interface
    ├── Aircraft.java                 ← abstract base class
    ├── AircraftFactory.java          ← Factory pattern
    ├── Balloon.java
    ├── JetPlane.java
    ├── Helicopter.java
    ├── Coordinates.java
    └── InvalidScenarioException.java ← custom exception (bonus)
```

---

## Stack

Java 21 · OOP · Design Patterns · No external dependencies
