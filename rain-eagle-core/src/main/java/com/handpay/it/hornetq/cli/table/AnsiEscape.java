package com.handpay.it.hornetq.cli.table;

abstract class AnsiEscape {

    protected static final String ESCAPE = "\u001b[";
    protected static final char MULTI_MODES_SEPERATOR = ';';

    private final char code;
    private final int mode;
    private final String escape;

    protected AnsiEscape(char code, int mode) {
        this.code = code;
        this.mode = mode;
        this.escape = createEscape( code , mode );
    }

    char getCode() {
        return code;
    }

    int getMode() {
        return mode;
    }

    String getEscape() {
        return escape;
    }

    static String createEscape(char code, int mode) {
        return ESCAPE + mode + code;
    }
}
