package com.handpay.it.hornetq.cli.table;


class AnsiSGR extends AnsiEscape {

    static final AnsiSGR RESET = new AnsiSGR( 0 );

    protected static final char CODE = 'm';

    protected AnsiSGR(int mode) {
        super( CODE, mode );
    }

    protected static String escape(String str, char code, int... modes) {
        return escape( str , code , true , modes );
    }

    protected static String escape(String str, char code, boolean isEndReset, int... modes) {
        StringBuilder builder = new StringBuilder( ESCAPE );
        for ( int i = 0 ; i < modes.length ; i++ ) {
            if ( i > 0 ) {
                builder.append( MULTI_MODES_SEPERATOR );
            }
            builder.append( modes[i] );
        }
        builder.append( code );
        builder.append( str );
        if ( isEndReset ) {
            builder.append( RESET.getEscape() );
        }
        return builder.toString();
    }
}
