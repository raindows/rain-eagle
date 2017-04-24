package com.handpay.it.hornetq.cli.table;


public class TableColumn {

    private static final int AUTO_WIDTH = 0;

    private int index;

    private final boolean isAutoWidth;

    private int width;

    private int leftPadding;

    private int rightPadding;

    private Align align;

    private TableColumn(int width) {
        this.width = width;
        this.leftPadding = 1;
        this.rightPadding = 1;
        this.align = Align.LEFT;
        this.isAutoWidth = ( width <= AUTO_WIDTH );
    }

    public static Builder create() {
        return new Builder( AUTO_WIDTH );
    }

    public static Builder create(int width) {
        return new Builder( width );
    }

    public boolean isAutoWidth() {
        return this.isAutoWidth;
    }

    public int getTotalWidth() {
        return getWidth() + getLeftPadding() + getRightPadding();
    }

    public int getWidth() {
        return this.width;
    }

    public int getLeftPadding() {
        return this.leftPadding;
    }

    public int getRightPadding() {
        return this.rightPadding;
    }

    public Align getAlign() {
        return this.align;
    }

    public int getIndex() {
        return this.index;
    }

    void setIndex(int index) {
        this.index = index;
    }

    void collectCellWidth(int cellWidth) {
        if ( isAutoWidth() && this.width < cellWidth ) {
            this.width = cellWidth;
        }
    }

    private void setLeftPadding(int leftPadding) {
        this.leftPadding = leftPadding;
    }

    private void setRightPadding(int rightPadding) {
        this.rightPadding = rightPadding;
    }

    private void setAlign(Align align) {
        this.align = align;
    }

    @Override
    public String toString() {
        StringBuilder builder2 = new StringBuilder();
        builder2.append( "TableColumn [index=" );
        builder2.append( this.index );
        builder2.append( ", width=" );
        builder2.append( this.width );
        builder2.append( ", leftPadding=" );
        builder2.append( this.leftPadding );
        builder2.append( ", rightPadding=" );
        builder2.append( this.rightPadding );
        builder2.append( ", align=" );
        builder2.append( this.align );
        builder2.append( "]" );
        return builder2.toString();
    }

    public static final class Builder {

        private final TableColumn object;

        private Builder(int width) {
            this.object = new TableColumn( width );
        }

        public Builder leftPadding(int leftPadding) {
            this.object.setLeftPadding( leftPadding );
            return this;
        }

        public Builder rightPadding(int rightPadding) {
            this.object.setRightPadding( rightPadding );
            return this;
        }

        public Builder align(Align align) {
            this.object.setAlign( align );
            return this;
        }

        public Builder alignLeft() {
            return align( Align.LEFT );
        }

        public Builder alignCenter() {
            return align( Align.CENTER );
        }

        public Builder alignRight() {
            return align( Align.RIGHT );
        }

        public TableColumn build() {
            return this.object;
        }
    }
}
