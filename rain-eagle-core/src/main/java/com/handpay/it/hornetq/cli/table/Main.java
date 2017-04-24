package com.handpay.it.hornetq.cli.table;

public class Main {

    public static void main(String[] args) {

        Table table = Table.create()
                .addColumn( TableColumn.create( 3 ).alignRight().build() )
                .addColumn( TableColumn.create().build() )
                .addColumn( TableColumn.create().build() )
                .addColumn( TableColumn.create( 8 ).alignRight().build() )
                .addColumn( TableColumn.create( 8 ).alignRight().build() )
                .addColumn( TableColumn.create( 8 ).alignRight().build() )
                .addColumn( TableColumn.create( 8 ).alignRight().build() )
                .addColumn( TableColumn.create( 8 ).alignRight().build() ).build();

        table.newTableRow()
                .appendCell( TableCell.create( "No." ).isHeader().color(AnsiColor.GREEN).build() )
                .appendCell( TableCell.create( "Name" ).isHeader().build() )
                .appendCell( TableCell.create( "JNDI Bindings" ).isHeader().build() )
                .appendCell( TableCell.create( "Dlvy.Cnt" ).isHeader().build() )
                .appendCell( TableCell.create( "Msgs" ).isHeader().build() )
                .appendCell( TableCell.create( "Msg.Cnt" ).isHeader().build() )
                .appendCell( TableCell.create( "Cons.Cnt" ).isHeader().build() )
                .appendCell( TableCell.create( "Sche.Cnt" ).isHeader().build() );

        table.newTableRow()
                .appendCell( TableCell.create( 1 ).build() )
                .appendCell( TableCell.create( "boc85Queue" ).build() )
                .appendCell( TableCell.EMPTY_CELL )
                .appendCell( TableCell.create( 0 ).build() )
                .appendCell( TableCell.create( 0 ).build() )
                .appendCell( TableCell.create( 0 ).build() )
                .appendCell( TableCell.create( 0 ).build() )
                .appendCell( TableCell.create( 0 ).build() );

        table.newTableRow()
                .appendCell( TableCell.create( 2 ).build() )
                .appendCell( TableCell.create( "DLQ" ).build() )
                .appendCell( TableCell.create( "/queue/DLQ" ).build() )
                .appendCell( TableCell.create( 0 ).build() )
                .appendCell( TableCell.create( 0 ).build() )
                .appendCell( TableCell.create( 0 ).build() )
                .appendCell( TableCell.create( 0 ).build() )
                .appendCell( TableCell.create( 0 ).build() );

        table.newTableRow()
                .appendCell( TableCell.create( 3 ).build() )
                .appendCell( TableCell.create( "GBW" ).build() )
                .appendCell( TableCell.create( "/queue/GBW", "/queue/GBW_0", "/queue/GBW_1" ).build() )
                .appendCell( TableCell.create( 0 ).build() )
                .appendCell( TableCell.create( 0 ).build() )
                .appendCell( TableCell.create( 0 ).build() )
                .appendCell( TableCell.create( 0 ).build() )
                .appendCell( TableCell.create( 0 ).build() );

        table.newTableRow()
                .appendCell( TableCell.create( 4 ).build() )
                .appendCell( TableCell.create( "exceptionQueue" ).build() )
                .appendCell( TableCell.EMPTY_CELL )
                .appendCell( TableCell.create( 0 ).build() )
                .appendCell( TableCell.create( 0 ).build() )
                .appendCell( TableCell.create( 0 ).build() )
                .appendCell( TableCell.create( 0 ).build() )
                .appendCell( TableCell.create( 0 ).build() );

        table.newTableRow()
                .appendCell( TableCell.create( 5 ).build() )
                .appendCell( TableCell.create( "ExpiryQueue" ).build() )
                .appendCell( TableCell.create( "/queue/ExpiryQueue" ).build() )
                .appendCell( TableCell.create( 0 ).build() )
                .appendCell( TableCell.create( 0 ).build() )
                .appendCell( TableCell.create( 0 ).build() )
                .appendCell( TableCell.create( 0 ).build() )
                .appendCell( TableCell.create( 0 ).build() );
        
        SystemOut.putTable( table );
    }
}
