package travellld.service;

import java.io.PrintStream;

public class ConsolePrintService {

    private static final PrintStream printer = System.out;

    private ConsolePrintService() {}
    
    /**
     * Prints given input String to the console
     * @param s String object
     */
    public static void print(String s) {
        printer.print(s);
    }

}
