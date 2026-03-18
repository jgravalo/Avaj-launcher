package avaj;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Simulator {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Error: exactly one argument required (scenario file path).");
            System.exit(1);
        }

        try {
            runSimulation(args[0]);
        } catch (InvalidScenarioException e) {
            System.out.println("Invalid scenario: " + e.getMessage());
            System.exit(1);
        } catch (IOException e) {
            System.out.println("File error: " + e.getMessage());
            System.exit(1);
        }
    }

    private static void runSimulation(String scenarioPath) throws InvalidScenarioException, IOException {
        BufferedReader reader = new BufferedReader(new FileReader(scenarioPath));
        PrintWriter writer = new PrintWriter(new FileWriter("simulation.txt"));

        Tower.setWriter(writer);
        WeatherTower tower = WeatherTower.getInstance();
        List<Aircraft> aircrafts = new ArrayList<>();

        try {
            // --- Parse number of rounds ---
            String firstLine = reader.readLine();
            if (firstLine == null || firstLine.trim().isEmpty()) {
                throw new InvalidScenarioException("File is empty or missing round count.");
            }

            int rounds;
            try {
                rounds = Integer.parseInt(firstLine.trim());
            } catch (NumberFormatException e) {
                throw new InvalidScenarioException("First line must be a positive integer, got: " + firstLine.trim());
            }
            if (rounds <= 0) {
                throw new InvalidScenarioException("Number of rounds must be a positive integer, got: " + rounds);
            }

            // --- Parse aircraft lines ---
            String line;
            int lineNumber = 1;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                if (line.trim().isEmpty()) continue;

                String[] parts = line.trim().split("\\s+");
                if (parts.length != 5) {
                    throw new InvalidScenarioException(
                        "Line " + lineNumber + " must have exactly 5 fields (TYPE NAME LONGITUDE LATITUDE HEIGHT), got: " + line
                    );
                }

                String type      = parts[0];
                String name      = parts[1];
                int longitude, latitude, height;

                try {
                    longitude = Integer.parseInt(parts[2]);
                    latitude  = Integer.parseInt(parts[3]);
                    height    = Integer.parseInt(parts[4]);
                } catch (NumberFormatException e) {
                    throw new InvalidScenarioException(
                        "Line " + lineNumber + ": coordinates must be integers. Got: " + line
                    );
                }

                if (longitude < 0 || latitude < 0) {
                    throw new InvalidScenarioException(
                        "Line " + lineNumber + ": longitude and latitude must be positive. Got: " + line
                    );
                }
                if (height < 0 || height > 100) {
                    throw new InvalidScenarioException(
                        "Line " + lineNumber + ": height must be between 0 and 100. Got: " + line
                    );
                }

                Aircraft aircraft;
                try {
                    aircraft = AircraftFactory.newAircraft(type, name, longitude, latitude, height);
                } catch (IllegalArgumentException e) {
                    throw new InvalidScenarioException("Line " + lineNumber + ": " + e.getMessage());
                }

                aircrafts.add(aircraft);

                // Register to tower and log it
                tower.register((Weatherable) aircraft);
                Tower.logMessage("Tower says: " + type + "#" + name + "(" + aircraft.getId() + ") registered to weather tower.");
            }

            // --- Run simulation ---
            for (int i = 0; i < rounds; i++) {
                tower.changeWeather();
            }

        } finally {
            reader.close();
            writer.flush();
            writer.close();
        }
    }
}
