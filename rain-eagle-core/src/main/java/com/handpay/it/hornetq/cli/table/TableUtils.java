package com.handpay.it.hornetq.cli.table;

public final class TableUtils {

    private TableUtils() {
    }

    public static String createLine(char cross, char horizone, int... columnWidth) {
        char[] chs = new char[ sum(columnWidth) + columnWidth.length + 1 ];
        int offset = 0;
        for ( int i = 0 ; i < columnWidth.length ; i++ ) {
            chs[offset++] = cross;
            for ( int j = 0 ; j < columnWidth[i] ; j++ ) {
                chs[offset++] = horizone;
            }
        }
        chs[offset++] = cross;
        return new String( chs );
    }

    public static int sum(int... nums) {
        int sum = 0;
        for ( int i = 0 ; i < nums.length ; i++ ) {
            sum += nums[i];
        }
        return sum;
    }

    public static StringBuilder alignRight( String text , int width , StringBuilder builder ) {
        return alignRight( text , width , builder , null );
    }

    public static StringBuilder alignLeft( String text , int width , StringBuilder builder ) {
        return alignLeft( text , width , builder , null );
    }

    public static StringBuilder alignCenter( String text , int width , StringBuilder builder ) {
        return alignCenter( text , width , builder , null );
    }

    public static StringBuilder alignRight( String text , int width , StringBuilder builder, AnsiColor color, AnsiStyle... styles ) {
        int textWidth = calcTextWidth( text );
        appendSpace( builder , width - textWidth );
        appendText( text , builder , color , styles );
        return builder;
    }

    public static StringBuilder alignLeft( String text , int width , StringBuilder builder, AnsiColor color, AnsiStyle... styles ) {
        int textWidth = calcTextWidth( text );
        appendText( text , builder , color , styles );
        return appendSpace( builder , width - textWidth );
    }

    public static StringBuilder alignCenter( String text , int width , StringBuilder builder, AnsiColor color, AnsiStyle... styles ) {
        int textWidth = calcTextWidth( text );
        int remain = width - textWidth;
        appendSpace( builder , remain - remain / 2);
        appendText( text , builder , color , styles );
        appendSpace( builder , remain / 2 );
        return builder;
    }

    public static StringBuilder appendText( String text , StringBuilder builder, AnsiColor color, AnsiStyle... styles ) {
        if ( color == null && (styles == null || styles.length == 0) ) {
            return builder.append( text );
        }
        return builder.append( Ansi.color(text, color, styles) );
    }

    public static int calcTextWidth(String text) {
        if ( text == null || text.length() == 0 ) {
            return 0;
        }
        int width = 0;
        char[] chs = text.toCharArray();
        for ( int i = 0 ; i < chs.length ; i++ ) {
            width += (chs[i] > 0xff ? 2 : 1);
        }
        return width;
    }

    public static StringBuilder appendSpace(StringBuilder builder, int spaceCount) {
        for ( int i = 0 ; i < spaceCount ; i++ ) {
            builder.append( ' ' );
        }
        return builder;
    }
}
