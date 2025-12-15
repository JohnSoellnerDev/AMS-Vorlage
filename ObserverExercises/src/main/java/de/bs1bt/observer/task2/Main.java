package de.bs1bt.observer.task2;

public class Main {
    public static void main(String[] args) {
        ClassInfoBoard board = new ClassInfoBoard();

        Observer channel1 = new ConsoleInfoChannel("Klassenchat");
        Observer channel2 = new ConsoleInfoChannel("Schwarzes Brett");
        Observer warnings = new WarningChannel("Warnkanal");

        board.addObserver(channel1);
        board.addObserver(channel2);
        board.addObserver(warnings);

        board.setMessage("Nächste Woche: Wandertag");
        board.setMessage("WICHTIG: Morgen Mathe-Schulaufgabe!");
    }
}
