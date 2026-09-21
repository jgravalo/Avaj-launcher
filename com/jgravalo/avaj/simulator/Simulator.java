package com.jgravalo.avaj.simulator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Simulator {
    private static final String OUTPUT_FILE = "simulation.txt";
    private static final int MAX_HEIGHT = 100;

    private record AircraftSpec(String type, String name, int longitude, int latitude, int height) {}

    private record Scenario(int rounds, List<AircraftSpec> aircrafts) {}

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Error: exactly one argument required (scenario file path).");
            System.exit(1);
        }

        try {
            // Validate the whole scenario before creating anything or writing any output
            Scenario scenario = parseScenario(args[0]);
            runSimulation(scenario);
        } catch (InvalidScenarioException e) {
            System.out.println("Invalid scenario: " + e.getMessage());
            System.exit(1);
        } catch (IOException e) {
            System.out.println("File error: " + e.getMessage());
            System.exit(1);
        }
    }

    private static Scenario parseScenario(String p_path) throws InvalidScenarioException, IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(p_path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }

        if (lines.isEmpty() || lines.get(0).trim().isEmpty()) {
            throw new InvalidScenarioException("File is empty or missing round count.");
        }

        int rounds = parseInt(lines.get(0).trim(), 1, "number of rounds");
        if (rounds <= 0) {
            throw new InvalidScenarioException("Line 1: number of rounds must be a positive integer, got: " + rounds);
        }

        List<AircraftSpec> aircrafts = new ArrayList<>();
        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i).trim();
            if (!line.isEmpty()) {
                aircrafts.add(parseAircraft(line, i + 1));
            }
        }

        return new Scenario(rounds, aircrafts);
    }

    private static AircraftSpec parseAircraft(String p_line, int p_lineNumber) throws InvalidScenarioException {
        String[] parts = p_line.split("\\s+");
        if (parts.length != 5) {
            throw new InvalidScenarioException("Line " + p_lineNumber
                + ": expected TYPE NAME LONGITUDE LATITUDE HEIGHT, got: " + p_line);
        }

        String type = parts[0];
        if (!AircraftFactory.isValidType(type)) {
            throw new InvalidScenarioException("Line " + p_lineNumber + ": unknown aircraft type: " + type);
        }

        int longitude = parseInt(parts[2], p_lineNumber, "longitude");
        int latitude  = parseInt(parts[3], p_lineNumber, "latitude");
        int height    = parseInt(parts[4], p_lineNumber, "height");

        if (longitude < 0 || latitude < 0 || height < 0) {
            throw new InvalidScenarioException("Line " + p_lineNumber + ": coordinates must be positive, got: " + p_line);
        }

        // Height above the limit stays at the limit
        return new AircraftSpec(type, parts[1], longitude, latitude, Math.min(height, MAX_HEIGHT));
    }

    private static int parseInt(String p_value, int p_lineNumber, String p_field) throws InvalidScenarioException {
        try {
            return Integer.parseInt(p_value);
        } catch (NumberFormatException e) {
            throw new InvalidScenarioException("Line " + p_lineNumber + ": " + p_field
                + " must be an integer, got: " + p_value);
        }
    }

    private static void runSimulation(Scenario p_scenario) throws IOException {
        Logger.open(OUTPUT_FILE);
        try {
            WeatherTower weatherTower = new WeatherTower();
            AircraftFactory factory = AircraftFactory.getInstance();

            for (AircraftSpec spec : p_scenario.aircrafts()) {
                Coordinates coordinates = new Coordinates(spec.longitude(), spec.latitude(), spec.height());
                factory.newAircraft(spec.type(), spec.name(), coordinates).registerTower(weatherTower);
            }

            for (int i = 0; i < p_scenario.rounds(); i++) {
                weatherTower.changeWeather();
            }
        } finally {
            Logger.close();
        }
    }
}
