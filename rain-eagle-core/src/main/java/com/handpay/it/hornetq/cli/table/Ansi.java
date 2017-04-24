package com.handpay.it.hornetq.cli.table;

import java.util.regex.Pattern;

public final class Ansi {

    public static final String ANSI_SWITCH_PROP_NAME = "com.handpay.it.hornetq.cli.ansi";

    public static final String ANSI_OFF = "off";

    private Ansi() {
    }

    public static String resetColor(String str) {
        return AnsiSGR.RESET.getEscape() + str;
    }

    public static String style(String str, AnsiStyle... styles) {
        return color( str , null , styles );
    }

    public static String color(String str, AnsiColor foreground, AnsiStyle... styles) {
        return color( str , foreground , null , styles );
    }

    public static String color(String str, AnsiColor foreground, AnsiColor background, AnsiStyle... styles) {
        return AnsiColor.escape( str , foreground , background , styles );
    }

    public static String getResetEscape() {
        if ( isAnsiOff() ) {
            return "";
        }
        return AnsiSGR.RESET.getEscape();
    }

    public static String clearAnsi(String str) {
        if ( str == null || str.trim().length() == 0 ) {
            return str;
        }
        return str.replaceAll( Pattern.quote(AnsiEscape.ESCAPE) + ".*?" + Pattern.quote(String.valueOf(AnsiSGR.CODE)) , "" );
    }

    public static boolean isAnsiOff() {
        return ANSI_OFF.equalsIgnoreCase( System.getProperty( ANSI_SWITCH_PROP_NAME ) );
    }

    public static void closeAnsi() {
        System.setProperty( ANSI_SWITCH_PROP_NAME , ANSI_OFF );
    }
}
