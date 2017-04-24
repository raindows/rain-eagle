package com.handpay.it.hornetq.cli.table;

public class TableBorder {

    public static final char CROSS = '+';

    private Border top;
    private Border bottom;
    private Border left;
    private Border right;
    private Border horizontal;
    private Border vertical;

    TableBorder() {
        this.top = Border.SINGLE;
        this.bottom = Border.SINGLE;
        this.left = Border.SINGLE;
        this.right = Border.SINGLE;
        this.horizontal = Border.SINGLE;
        this.vertical = Border.SINGLE;
    }

    public Border getTop() {
        return this.top;
    }

    public Border getBottom() {
        return this.bottom;
    }

    public Border getLeft() {
        return this.left;
    }

    public Border getRight() {
        return this.right;
    }

    public Border getHorizontal() {
        return this.horizontal;
    }

    public Border getVertical() {
        return this.vertical;
    }

    void setTop(Border top) {
        this.top = top;
    }

    void setBottom(Border bottom) {
        this.bottom = bottom;
    }

    void setLeft(Border left) {
        this.left = left;
    }

    void setRight(Border right) {
        this.right = right;
    }

    void setHorizontal(Border horizontal) {
        this.horizontal = horizontal;
    }

    void setVertical(Border vertical) {
        this.vertical = vertical;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append( "TableBorder [top=" );
        builder.append( this.top );
        builder.append( ", bottom=" );
        builder.append( this.bottom );
        builder.append( ", left=" );
        builder.append( this.left );
        builder.append( ", right=" );
        builder.append( this.right );
        builder.append( ", horizontal=" );
        builder.append( this.horizontal );
        builder.append( ", vertical=" );
        builder.append( this.vertical );
        builder.append( "]" );
        return builder.toString();
    }
}
