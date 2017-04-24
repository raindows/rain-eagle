package com.handpay.it.hornetq.cli.table;

import java.io.PrintStream;

import org.fusesource.jansi.AnsiConsole;

public final class SystemOut {

    private static final PrintStream OUT = initPrintStream();

    private SystemOut() {
    }

    public static void putTable(Table table) {
        table.printTo( OUT );
    }

    private static PrintStream initPrintStream() {
        if ( System.getProperty("os.name").indexOf( "Windows" ) > -1 ) {
            return AnsiConsole.out;
        }
        return System.out;
    }
}
