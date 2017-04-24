package com.handpay.it.hornetq.cli.table;

public class TableCellCoords {

    private final int rowIndex;
    private final int columnIndex;

    public TableCellCoords(int rowIndex, int columnIndex) {
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + columnIndex;
        result = prime * result + rowIndex;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if ( this == obj ) {
            return true;
        }
        if ( obj == null ) {
            return false;
        }
        if ( !( obj instanceof TableCellCoords ) ) {
            return false;
        }
        TableCellCoords other = (TableCellCoords) obj;
        if ( columnIndex != other.columnIndex ) {
            return false;
        }
        if ( rowIndex != other.rowIndex ) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Row: " + rowIndex + ", Col: " + columnIndex;
    }
}
