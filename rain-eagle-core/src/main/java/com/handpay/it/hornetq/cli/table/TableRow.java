package com.handpay.it.hornetq.cli.table;

public class TableRow {

    private final int rowIndex;
    private final TableCell[] cells;
    private int nextColumnIndex;
    private int maxTotalLines;

    TableRow(int rowIndex , int columnsCount) {
        this.cells = new TableCell[columnsCount];
        this.nextColumnIndex = 0;
        this.rowIndex = rowIndex;
    }

    int getMaxCellLinesCount() {
        return this.maxTotalLines;
    }

    int getNextColumnIndex() {
        return this.nextColumnIndex;
    }

    int getCellLength() {
        return cells.length;
    }

    TableCell getCell(int index) {
        if ( cells == null ) {
            return null;
        }
        return cells[ index ];
    }

    void appendCell(TableCell cell , Table table) {

        this.cells[this.nextColumnIndex] = cell;
        cell.setRowIndex( this.rowIndex );
        cell.setColIndex( this.nextColumnIndex );
        table.getColumn( this.nextColumnIndex ).collectCellWidth( cell.getMaxLineWidth() );
        table.addMergedCellCoords( this.rowIndex , this.nextColumnIndex , cell.getColspan() , cell.getRowspan() );
        this.nextColumnIndex += cell.getColspan();
        if ( this.maxTotalLines < cell.getTotalLines() ) {
            this.maxTotalLines = cell.getTotalLines();
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append( "TableRow: rowIndex=" ).append( this.rowIndex );
        builder.append( ", nextColumnIndex=" ).append( this.nextColumnIndex );
        for ( TableCell cell : this.cells ) {
            builder.append( "\n    " ).append( cell );
        }
        return builder.toString();
    }
}
