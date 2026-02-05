package de.bs1bt.task1;

import de.bs1bt.common.Console;

/** InputView: liest neue Status-Werte von der Konsole. */
public class StreamerInputView {
    public String readStatus() {
        return Console.readLine("Neuen Status eingeben (oder 'exit'): ").trim();
    }
}
