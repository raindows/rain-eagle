package com.handpay.it.hornetq.cli.table;

import static com.handpay.it.hornetq.cli.table.TableBorder.CROSS;

import java.io.PrintStream;

public class TableBuilder {

    private final Table table;

    public TableBuilder(Table table) {
        this.table = table;
    }

    public TableBorder getBorder() {
        return table.getBorder();
    }

    public TableColumn getColumn(int index) {
        return table.getColumn( index );
    }

    public void pintTo(PrintStream out) {

        if ( !table.getBorder().getTop().isEmpty() ) {
            out.println( createLineSeperator( table.getBorder().getTop(), table.getColumnsWidths() ));
        }

        String lineSeperator = createLineSeperator( table.getBorder().getHorizontal() , table.getColumnsWidths() );

        int rowIndex = 0;

        for ( TableRow row : table.rows() ) {
            if ( rowIndex++ > 0 && lineSeperator != null ) {
                out.println( lineSeperator );
            }
            printRow( row , out );
        }

        if ( !table.getBorder().getBottom().isEmpty() ) {
            out.println( createLineSeperator( table.getBorder().getBottom(), table.getColumnsWidths() ));
        }
    }

    private void printRow(TableRow row, PrintStream out) {
        for ( int line = 0 ; line < row.getMaxCellLinesCount() ; line++ ) {
            StringBuilder appender = new StringBuilder();
            appendLeftBorder( appender );
            for ( int i = 0 ; i < row.getCellLength() ; i++ ) {
                if ( i > 0 ) {
                    appendInnerVerticalBorder( appender );
                }
                TableColumn column = table.getColumn( row.getCell(i).getColIndex() );
                appendCell( line , column, row.getCell(i) , appender );
            }
            appendRightBorder( appender );
            out.println( appender.toString() );
        }
    }

    private StringBuilder appendCell(int line, TableColumn column, TableCell cell, StringBuilder builder) {
        TableUtils.appendSpace( builder , column.getLeftPadding() );
        if ( cell.isHeader() ) {
            Align.CENTER.format( cell.getLineContent(line) , column.getWidth() , builder , cell.getColor() , cell.getStyles() );
        }
        else {
            column.getAlign().format( cell.getLineContent( line ) , column.getWidth() , builder , cell.getColor() , cell.getStyles()  );
        }
        TableUtils.appendSpace( builder , column.getRightPadding() );
        return builder;
    }

    private String createLineSeperator(Border border, int... columnWidth) {
        if ( border.isEmpty() ) {
            return null;
        }
        StringBuilder builder = new StringBuilder();
        appendLeftCross( builder );
        for ( int i = 0 ; i < columnWidth.length ; i++ ) {
            if ( i > 0 ) {
                appendInterCross( builder );
            }
            for ( int j = 0 ; j < columnWidth[i] ; j++ ) {
                builder.append( border.getHchar() );
            }
        }
        appendRightCross( builder );
        return builder.toString();
    }

    public StringBuilder appendLeftCross(StringBuilder builder) {
        if ( getBorder().getLeft().isEmpty() ) {
            return builder;
        }
        return builder.append( CROSS );
    }

    public StringBuilder appendInterCross(StringBuilder builder) {
        if ( getBorder().getVertical().isVisable() && getBorder().getHorizontal().isVisable() ) {
            return builder.append( CROSS );
        }
        if ( !getBorder().getVertical().isVisable() ) {
            return appendInnerHorizontalBorder( builder );
        }
        return appendInnerVerticalBorder( builder );
    }

    public StringBuilder appendRightCross(StringBuilder builder) {
        if ( getBorder().getRight().isEmpty() ) {
            return builder;
        }
        return builder.append( CROSS );
    }

    public StringBuilder appendLeftBorder(StringBuilder builder) {
        if ( getBorder().getLeft().isEmpty() ) {
            return builder;
        }
        return builder.append( getBorder().getLeft().getVchar() );
    }

    public StringBuilder appendRightBorder(StringBuilder builder) {
        if ( getBorder().getRight().isEmpty() ) {
            return builder;
        }
        return builder.append( getBorder().getRight().getVchar() );
    }

    public StringBuilder appendInnerHorizontalBorder(StringBuilder builder) {
        return builder.append( getBorder().getHorizontal().getHchar() );
    }

    public StringBuilder appendInnerVerticalBorder(StringBuilder builder) {
        return builder.append( getBorder().getVertical().getVchar() );
    }
}
