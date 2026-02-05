package de.bs1bt.common;

import java.util.Scanner;

public final class Console {
    private static final Scanner SCANNER = new Scanner(System.in);

    private Console() {}

    public static String readLine(String prompt) {
        System.out.print(prompt);
        return SCANNER.nextLine();
    }
}
