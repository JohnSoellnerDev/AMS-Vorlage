package de.bs1bt.task2;

public class MainTask2 {
    public static void main(String[] args) {
        ClassInfoboard board = new ClassInfoboard();

        board.addObserver(new InfoChannelView());
        board.addObserver(new WarningChannelView());

        // Controller mit Model und InputView verbinden
        MessageInputView inputView = new MessageInputView();
        InfoboardController controller = new InfoboardController(board, inputView);

        // Starte die Eingabeschleife
        controller.runLoop();
    }
}
