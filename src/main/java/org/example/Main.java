package org.example;

public class Main {
    public static void main(String[] args) {
        Printer consolePrinter = new ConsolePrinter();

        TrafficLogParser trafficLogParser = new TrafficLogParser();
        TrafficLogAnalyzer trafficLogAnalyzer = new TrafficLogAnalyzer();
        Outputer outputer = new Outputer(consolePrinter);
        ArgsParser argsParser = new ArgsParser();

        TrafficCounter trafficCounter = new TrafficCounter(trafficLogParser, trafficLogAnalyzer, outputer, argsParser);
        trafficCounter.run(args);
    }
}
