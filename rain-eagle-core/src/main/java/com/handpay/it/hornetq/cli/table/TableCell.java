package com.handpay.it.hornetq.cli.table;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class TableCell {

    private static final String EMPTY_CELL_CONTENT = "";
    public static final TableCell EMPTY_CELL = TableCell.create( EMPTY_CELL_CONTENT ).build();
    private static final int DEFAULT_INDEX = -1;

    private int rowIndex;
    private int colIndex;
    private String[] lines;
    private int colspan;
    private int rowspan;
    private boolean isHeader;
    private int maxWidth;
    private AnsiColor color;
    private AnsiStyle[] styles;

    private TableCell() {
        this.colspan = 1;
        this.rowspan = 1;
        this.rowIndex = DEFAULT_INDEX;
        this.colIndex = DEFAULT_INDEX;
    }

    public static Builder createObject(Object object) {
        return new Builder().object( object );
    }

    public static Builder create(String... content) {
        return new Builder().line( content );
    }

    public static Builder create(int... number) {
        return new Builder().line( number );
    }

    public String getLineContent(int line) {
        if ( line < this.lines.length ) {
            return this.lines[line];
        }
        return EMPTY_CELL_CONTENT;
    }

    public boolean isHeader() {
        return this.isHeader;
    }

    public int getTotalLines() {
        if ( this.lines == null ) {
            return 0;
        }
        return this.lines.length;
    }

    public String[] getLines() {
        return this.lines;
    }

    public int getMaxLineWidth() {
        return this.maxWidth;
    }

    public int getColspan() {
        return this.colspan;
    }

    public int getRowspan() {
        return this.rowspan;
    }

    public int getColIndex() {
        return this.colIndex;
    }

    public int getRowIndex() {
        return this.rowIndex;
    }

    public AnsiColor getColor() {
        return this.color;
    }

    public AnsiStyle[] getStyles() {
        if ( this.styles == null ) {
            return new AnsiStyle[0];
        }
        return this.styles;
    }

    void setRowIndex(int rowIndex) {
        if ( this.rowIndex == DEFAULT_INDEX ) {
            this.rowIndex = rowIndex;
        }
    }

    void setColIndex(int colIndex) {
        if ( this.colIndex == DEFAULT_INDEX ) {
            this.colIndex = colIndex;
        }
    }

    private void setLines(String[] lines) {
        this.lines = lines;
        this.maxWidth = -1;
        for ( String line : lines ) {
            int len = TableUtils.calcTextWidth( line );
            if ( this.maxWidth < len ) {
                this.maxWidth = len;
            }
        }
    }

    private void setHeader(boolean isHeader) {
        this.isHeader = isHeader;
    }

    private void setColspan(int colspan) {
        this.colspan = colspan;
    }

    private void setRowspan(int rowspan) {
        this.rowspan = rowspan;
    }

    private void setColor(AnsiColor color) {
        this.color = color;
    }

    private void setStyles(AnsiStyle[] styles) {
        this.styles = styles;
    }

    @Override
    public String toString() {
        StringBuilder builder2 = new StringBuilder();
        builder2.append( "TableCell [rowIndex=" );
        builder2.append( this.rowIndex );
        builder2.append( ", colIndex=" );
        builder2.append( this.colIndex );
        builder2.append( ", lines=" );
        builder2.append( Arrays.toString(this.lines) );
        builder2.append( ", colspan=" );
        builder2.append( this.colspan );
        builder2.append( ", rowspan=" );
        builder2.append( this.rowspan );
        builder2.append( ", isHeader=" );
        builder2.append( this.isHeader );
        builder2.append( ", maxWidth=" );
        builder2.append( this.maxWidth );
        builder2.append( ", color=" );
        builder2.append( this.color );
        builder2.append( ", styles=" );
        builder2.append( this.styles );
        builder2.append( "]" );
        return builder2.toString();
    }

    public static final class Builder {

        private final TableCell object;
        private final List<String> lines;

        private Builder() {
            this.object = new TableCell();
            this.lines = new ArrayList<String>();
        }

        private Builder object(Object object) {
            if ( object != null ) {
                if ( object instanceof String[] ) {
                    return line( (String[]) object );
                }
                return line( String.valueOf(object) );
            }
            return this;
        }

        private Builder line(String... line) {
            if ( line != null ) {
                for ( String str : line ) {
                    this.lines.add( str );
                }
            }
            return this;
        }

        private Builder line(int... number) {
            if ( number != null ) {
                for ( int num : number ) {
                    this.lines.add( String.valueOf(num) );
                }
            }
            return this;
        }

        public Builder isHeader() {
            this.object.setHeader( true );
            return this;
        }

        public Builder colspan(int colspan) {
            this.object.setColspan( colspan );
            return this;
        }

        public Builder rowspan(int rowspan) {
            this.object.setRowspan( rowspan );
            return this;
        }

        public Builder color(AnsiColor color) {
            this.object.setColor( color );
            return this;
        }

        public Builder style(AnsiStyle... styles) {
            this.object.setStyles( styles );
            return this;
        }

        public TableCell build() {
            this.object.setLines( this.lines.toArray( new String[this.lines.size()] ) );
            return this.object;
        }
    }
}
