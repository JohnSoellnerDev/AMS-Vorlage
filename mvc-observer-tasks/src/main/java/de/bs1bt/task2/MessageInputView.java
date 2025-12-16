package de.bs1bt.task2;

import de.bs1bt.common.Console;

/** InputView: liest neue Nachrichten von der Konsole. */
public class MessageInputView {
    public String readMessage() {
        return Console.readLine("Neue Nachricht (oder 'exit'): ").trim();
    }
}
