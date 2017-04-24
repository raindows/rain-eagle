package com.handpay.it.hornetq.cli.table;

public enum Align {

    LEFT {
        @Override
        public StringBuilder format(String text, int width, StringBuilder builder) {
            return TableUtils.alignLeft( text , width , builder );
        }
        @Override
        public StringBuilder format(String text, int width, StringBuilder builder, AnsiColor color, AnsiStyle... styles) {
            return TableUtils.alignLeft( text , width , builder , color , styles );
        }
    },

    CENTER {
        @Override
        public StringBuilder format(String text, int width, StringBuilder builder) {
            return TableUtils.alignCenter( text , width , builder );
        }
        @Override
        public StringBuilder format(String text, int width, StringBuilder builder, AnsiColor color, AnsiStyle... styles) {
            return TableUtils.alignCenter( text , width , builder , color , styles );
        }
    },

    RIGHT {
        @Override
        public StringBuilder format(String text, int width, StringBuilder builder) {
            return TableUtils.alignRight( text , width , builder );
        }
        @Override
        public StringBuilder format(String text, int width, StringBuilder builder, AnsiColor color, AnsiStyle... styles) {
            return TableUtils.alignRight( text , width , builder , color , styles );
        }
    }

    ;

    public abstract StringBuilder format(String text, int width, StringBuilder builder);

    public abstract StringBuilder format(String text, int width, StringBuilder builder, AnsiColor color, AnsiStyle... styles);
}
