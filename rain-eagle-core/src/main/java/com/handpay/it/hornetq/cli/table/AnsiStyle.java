package com.handpay.it.hornetq.cli.table;

public final class AnsiStyle extends AnsiSGR {

    public static final AnsiStyle ON_BOLD = new AnsiStyle( 1 );

    public static final AnsiStyle ON_UNDERLINE = new AnsiStyle( 4 );

    public static final AnsiStyle ON_BLINK = new AnsiStyle( 5 );

    public static final AnsiStyle ON_REVERSE = new AnsiStyle( 7 );

    public static final AnsiStyle ON_INVISIBLE = new AnsiStyle( 8 );

    public static final AnsiStyle OFF_BOLD = new AnsiStyle( 22 );

    public static final AnsiStyle OFF_UNDERLINE = new AnsiStyle( 24 );

    public static final AnsiStyle OFF_BLINK = new AnsiStyle( 25 );

    public static final AnsiStyle OFF_REVERSE = new AnsiStyle( 27 );

    private AnsiStyle(int mode) {
        super( mode );
    }
}
