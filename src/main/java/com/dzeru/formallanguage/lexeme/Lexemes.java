package com.dzeru.formallanguage.lexeme;

public class Lexemes {
    public static final String[] AS = {"="};
    public static final String[] OP = {"+", "-", "/", "%", "<=", "=>", ">", "<", ";", "!", "&&", "||", "{", "}"};
    public static final String[] KW = {"let", "var", "if", "break", "while"};
    public static final String[] ID = {"^[a-zA-Z_]+[a-zA-Z0-9_]*$"};
    public static final String[] RE = {"^(-|)(\\d*|(\\d*\\.\\d*)|(\\d*\\.\\d*e\\d*))$", "false"};
    String whitespaces = " \\n\\t\\r";
}
