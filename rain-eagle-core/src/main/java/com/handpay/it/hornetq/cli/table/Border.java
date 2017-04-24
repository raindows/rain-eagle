package com.handpay.it.hornetq.cli.table;

public enum Border {
    EMPTY ( '\u0000' , '\u0000' ) {
        @Override
        public boolean isEmpty() {
            return true;
        }
        @Override
        public boolean isVisable() {
            return false;
        }
    },
    SPACE ( ' ' , ' ' ) {
        @Override
        public boolean isVisable() {
            return false;
        }
    },
    SINGLE ( '|' , '-' ),
    DOUBLE ( '|' , '=' )
    ;
    private final char vchar;
    private final char hchar;

    private Border(char vchar, char hchar) {
        this.vchar = vchar;
        this.hchar = hchar;
    }

    public char getVchar() {
        return this.vchar;
    }

    public char getHchar() {
        return this.hchar;
    }

    public void appendHorizonChar(char[] line , int offset , int count) {
        for ( int i = 0 ; i < count ; i++ ) {
            line[ offset++ ] = getHchar();
        }
    }
    
    public boolean isEmpty() {
        return false;
    }
    
    public boolean isVisable() {
        return true;
    }
}
