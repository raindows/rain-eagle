package com.handpay.it.hornetq.cli.table;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Table {

    private final TableBorder border;

    private TableColumn[] columns;

    private int[] columnsWidths;

    private List<TableRow> rows;

    private Map<Integer, BitSet> mergedCells;

    private Table() {
        this.border = new TableBorder();
    }

    public static Builder create() {
        return new Builder();
    }

    public TableBorder getBorder() {
        return this.border;
    }

    public int getColumnsCount() {
        return this.columns.length;
    }

    public TableColumn getColumn(int index) {
        return this.columns[index];
    }

    public Table newTableRow() {
        if ( this.rows == null ) {
            this.rows = new ArrayList<TableRow>();
        }
        this.rows.add( new TableRow( this.rows.size(), this.columns.length ) );
        return this;
    }

    public Table appendCell(TableCell cell) {
        int rowIndex = this.rows.size() - 1;
        TableRow row = this.rows.get( rowIndex );
        row.appendCell( cell , this );
        return this;
    }

    public Iterable<TableRow> rows() {
        if ( rows == null ) {
            return Collections.emptyList();
        }
        return rows;
    }

    public void printTo(PrintStream out) {
        if ( this.rows == null || this.rows.size() == 0 ) {
            return;
        }
        TableBuilder builder = new TableBuilder( this );
        builder.pintTo( out );
    }

    void addMergedCellCoords(int rowIndex, int colIndex, int colspan, int rowspan) {
        if ( colspan == 1 && rowspan == 1 ) {
            return;
        }
        if ( this.mergedCells == null ) {
            this.mergedCells = new HashMap<Integer, BitSet>();
        }
        for ( int i = rowIndex + rowspan; i >= rowIndex; i-- ) {
            mergedCell( i , colIndex , colIndex + colspan );
        }
    }

    int[] getColumnsWidths() {
        if ( this.columnsWidths == null ) {
            this.columnsWidths = new int[this.columns.length];
            for ( int i = 0; i < this.columns.length; i++ ) {
                this.columnsWidths[i] = this.columns[i].getTotalWidth();
            }
        }
        return this.columnsWidths;
    }

    private void mergedCell(int rowIndex, int fromColIndex, int toColIndex) {
        BitSet bitset = this.mergedCells.get( rowIndex );
        if ( bitset == null ) {
            bitset = new BitSet( this.columns.length );
            this.mergedCells.put( rowIndex , bitset );
        }
        bitset.set( fromColIndex , toColIndex );
    }

    private void setColumns(TableColumn[] columns) {
        this.columns = columns;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append( "Table" );
        builder.append( "\n  " ).append( this.border );
        for ( TableColumn column : this.columns ) {
            builder.append( "\n  " ).append( column );
        }
        for ( TableRow row : this.rows ) {
            builder.append( "\n  " ).append( row );
        }
        return builder.toString();
    }

    public static final class Builder {

        private final Table table;

        private final List<TableColumn> columns;

        private Builder() {
            this.table = new Table();
            this.columns = new ArrayList<TableColumn>();
        }

        public Builder addColumn(TableColumn column) {
            column.setIndex( this.columns.size() );
            this.columns.add( column );
            return this;
        }

        public Builder topBorder(Border border) {
            this.table.getBorder().setTop( border );
            return this;
        }

        public Builder bottomBorder(Border border) {
            this.table.getBorder().setBottom( border );
            return this;
        }

        public Builder leftBorder(Border border) {
            this.table.getBorder().setLeft( border );
            return this;
        }

        public Builder rightBorder(Border border) {
            this.table.getBorder().setRight( border );
            return this;
        }

        public Builder horizontalBorder(Border border) {
            this.table.getBorder().setHorizontal( border );
            return this;
        }

        public Builder verticalBorder(Border border) {
            this.table.getBorder().setVertical( border );
            return this;
        }

        public Builder outerBorder(Border top, Border bottom, Border left, Border right) {
            this.table.getBorder().setTop( top );
            this.table.getBorder().setBottom( bottom );
            this.table.getBorder().setLeft( left );
            this.table.getBorder().setRight( right );
            return this;
        }

        public Builder innerBorder(Border horizon, Border vertical) {
            this.table.getBorder().setHorizontal( horizon );
            this.table.getBorder().setVertical( vertical );
            return this;
        }

        public Table build() {
            this.table.setColumns( this.columns.toArray( new TableColumn[this.columns.size()] ) );
            return this.table;
        }
    }
}
