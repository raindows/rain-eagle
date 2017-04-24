package com.handpay.it.hornetq.cli.table;

/**
 * <p>ANSI color</p>
 *
 * @author gaobaowen
 * @since 1.0.0
 */
public final class AnsiColor extends AnsiSGR {

    /**
     * <p>ANSI Color: black <span style="background-color:black;width:2em;"></span></p>
     */
    public static final AnsiColor BLACK = new AnsiColor( 0 );

    /**
     * <p>ANSI Color: red <span style="background-color:#800000;width:2em;"></span></p>
     */
    public static final AnsiColor RED = new AnsiColor( 1 );

    /**
     * <p>ANSI Color: green <span style="background-color:#008000;width:2em;"></span></p>
     */
    public static final AnsiColor GREEN = new AnsiColor( 2 );

    /**
     * <p>ANSI Color: green <span style="background-color:#808000;width:2em;"></span></p>
     */
    public static final AnsiColor YELLOW = new AnsiColor( 3 );

    /**
     * <p>ANSI Color: blue <span style="background-color:#000080;width:2em;"></span></p>
     */
    public static final AnsiColor BLUE = new AnsiColor( 4 );

    /**
     * <p>ANSI Color: magenta <span style="background-color:#800080;width:2em;"></span></p>
     */
    public static final AnsiColor MAGENTA = new AnsiColor( 5 );

    /**
     * <p>ANSI Color: cyan <span style="background-color:#008080;width:2em;"></span></p>
     */
    public static final AnsiColor CYAN = new AnsiColor( 6 );

    /**
     * <p>ANSI Color: white <span style="background-color:#C0C0C0;width:2em;"></span></p>
     */
    public static final AnsiColor WHITE = new AnsiColor( 7 );

    private static final int FOREGROUND_START = 30;

    private static final int BACKGROUND_START = 40;

    private final String foregroundEscape;
    private final String backgroundEscape;

    private AnsiColor(int mode) {
        super( mode );
        this.foregroundEscape = createEscape( CODE , getForegroundMode() );
        this.backgroundEscape = createEscape( CODE , getBackgroundMode() );
    }

    @Override
    @Deprecated
    String getEscape() {
        throw new UnsupportedOperationException( "using 'getForegroundEscape' or 'getBackgroundEscape' method" );
    }

    int getForegroundMode() {
        return FOREGROUND_START + getMode();
    }

    int getBackgroundMode() {
        return BACKGROUND_START + getMode();
    }

    String getForegroundEscape() {
        return this.foregroundEscape;
    }

    String getBackgroundEscape() {
        return this.backgroundEscape;
    }

    public String escape(String str, AnsiStyle... styles) {
        return escape( str , this , null , styles );
    }

    public String escape(String str, AnsiColor background, AnsiStyle... styles) {
        return escape( str , this , background , styles );
    }

    static String escape(String str, AnsiColor foreground, AnsiColor background, AnsiStyle... styles) {
        if ( Ansi.isAnsiOff() ) {
            return str;
        }
        int[] modes = new int[ styles.length + (foreground == null ? 0 : 1) + (background == null ? 0 : 1) ];
        int offset = 0;
        while ( offset < styles.length ) {
            modes[offset] = styles[offset].getMode();
            offset++;
        }
        if ( foreground != null ) {
            modes[offset++] = foreground.getForegroundMode();
        }
        if ( background != null ) {
            modes[offset++] = background.getBackgroundMode();
        }
        return escape( str , CODE , modes );
    }
}
