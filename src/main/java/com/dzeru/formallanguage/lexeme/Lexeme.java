package com.dzeru.formallanguage.lexeme;

import java.util.Comparator;

public class Lexeme implements Comparable<Lexeme> {
    private final int startPos;
    private final int endPos;
    private final String family;

    public Lexeme(int startPos, int endPos, String family) {
        this.startPos = startPos;
        this.endPos = endPos;
        this.family = family;
    }

    public int getStartPos() {
        return startPos;
    }

    public int getEndPos() {
        return endPos;
    }

    public String getFamily() {
        return family;
    }

    @Override
    public String toString() {
        return "Lexeme{" +
                "startPos='" + startPos + '\'' +
                ", endPos='" + endPos + '\'' +
                ", family='" + family + '\'' +
                '}';
    }

    @Override
    public int compareTo(Lexeme o) {
        return Comparator.comparingInt(Lexeme::getStartPos).compare(this, o);
    }
}
