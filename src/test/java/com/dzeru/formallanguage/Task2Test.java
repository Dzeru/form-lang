package com.dzeru.formallanguage;

import com.dzeru.formallanguage.fsm.FsmFamilyHelper;
import com.dzeru.formallanguage.lexeme.Lexeme;
import com.dzeru.formallanguage.lexeme.LexemeFinder;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class Task2Test {

    @BeforeAll
    public static void loadFsm() throws Exception {
        FsmFamilyHelper.loadFsmFamilies(lexerPaths);
    }

    private static final List<String> lexerPaths = Arrays.asList(
            "lexer\\assignment\\assignment.json",
            "lexer\\boolean\\false.json",
            "lexer\\keyword\\break.json",
            "lexer\\keyword\\let.json",
            "lexer\\keyword\\var.json",
            "lexer\\keyword\\while.json",
            "lexer\\number\\number.json",
            "lexer\\operator\\and.json",
            "lexer\\operator\\closecurlybracket.json",
            "lexer\\operator\\closeroundbracket.json",
            "lexer\\operator\\div.json",
            "lexer\\operator\\g.json",
            "lexer\\operator\\le.json",
            "lexer\\operator\\minus.json",
            "lexer\\operator\\not.json",
            "lexer\\operator\\opencurlybracket.json",
            "lexer\\operator\\openroundbracket.json",
            "lexer\\operator\\semicolon.json",
            "lexer\\id\\id.json",
            "lexer\\whitespace\\n.json",
            "lexer\\whitespace\\r.json",
            "lexer\\whitespace\\t.json",
            "lexer\\whitespace\\whitespace.json"
    );

    @Test
    public void testLexemeTypeSize() {
        final String analyzeText = "let a = 12";
        LexemeFinder lexemeFinder = new LexemeFinder(analyzeText);
        List<Lexeme> lexemes = lexemeFinder.findAllLexemes(0);
        assertEquals(7, lexemes.size());
        assertEquals(1, lexemes.stream().filter(l -> l.getFamily().equals("keyword")).count());
        assertEquals(3, lexemes.stream().filter(l -> l.getFamily().equals("whitespace")).count());
        assertEquals(1, lexemes.stream().filter(l -> l.getFamily().equals("id")).count());
        assertEquals(1, lexemes.stream().filter(l -> l.getFamily().equals("assignment")).count());
        assertEquals(1, lexemes.stream().filter(l -> l.getFamily().equals("number")).count());
    }

    @Test
    public void testLexemeKeyword() {
        final String analyzeText = "let a = 1";
        LexemeFinder lexemeFinder = new LexemeFinder(analyzeText);
        List<Lexeme> lexemes = lexemeFinder.findAllLexemes(0);
        Optional<Lexeme> keywordLexeme = lexemes
                .stream()
                .filter(l -> l.getFamily().equals("keyword"))
                .findFirst();
        if(keywordLexeme.isPresent()) {
            Lexeme l = keywordLexeme.get();
            final String testKeyword = analyzeText.substring(l.getStartPos(), l.getEndPos());
            assertEquals("let", testKeyword);
        }
        else {
            Assertions.fail();
        }
    }

    @Test
    public void testLexemes() {
        final String analyzeText = "let a = 12";
        LexemeFinder lexemeFinder = new LexemeFinder(analyzeText);
        List<Lexeme> lexemes = lexemeFinder.findAllLexemes(0);
        final List<Lexeme> expectedLexemes = Arrays.asList(
                new Lexeme(0, 3, "keyword"),
                new Lexeme(3, 4, "whitespace"),
                new Lexeme(4, 5, "id"),
                new Lexeme(5, 6, "whitespace"),
                new Lexeme(6, 7, "assignment"),
                new Lexeme(7, 8, "whitespace"),
                new Lexeme(8, 10, "number")
        );
        assertEquals(expectedLexemes.size(), lexemes.size());
        for(int i = 0; i < lexemes.size(); i++) {
            assertEquals(expectedLexemes.get(i), lexemes.get(i));
        }
    }
}
