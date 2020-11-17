package com.dzeru.formallanguage.lexeme;

import com.dzeru.formallanguage.fsm.FsmFamilyHelper;

import java.util.*;
import java.util.stream.Collectors;

public class LexemeFinder {

    private final String text;

    public LexemeFinder(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public Set<Lexeme> findAllLexemes(int skip) {
        int i = skip;
        List<Lexeme> allLexemes = new ArrayList<>();
        while(i < text.length()) {
            List<Lexeme> lexemes = FsmFamilyHelper.max(text, i);
            if(!lexemes.isEmpty()) {
                allLexemes.addAll(lexemes);
            }
            i++;
        }

        allLexemes = allLexemes
                .stream()
                .sorted(Comparator.comparingInt(Lexeme::getStartPos))
                .collect(Collectors.toList());

        return filterByPriority(allLexemes);
    }

    public void printLexemes(Set<Lexeme> lexemes) {
        System.out.println("ЛЕКСЕМЫ");
        System.out.println(String.format("Найдено лексем: %d", lexemes.size()));
        for(Lexeme lexeme : lexemes) {
            System.out.println(text.substring(lexeme.getStartPos(), lexeme.getEndPos()));
            System.out.println(lexeme);
            System.out.println("---------");
        }
    }

    private Set<Lexeme> filterByPriority(List<Lexeme> lexemes) {
        Set<Lexeme> filteredLexemes = new TreeSet<>(lexemes);

        for(int i = 0; i < lexemes.size(); i++) {
            for(int k = i + 1; k < lexemes.size(); k++) {
                if(i != k) {
                    Lexeme one = lexemes.get(i);
                    Lexeme sec = lexemes.get(k);
                    if(one.getStartPos() >= sec.getStartPos()
                            || one.getEndPos() == sec.getEndPos()) {
                        int firstLength = one.getEndPos() - one.getStartPos();
                        int secondLength = sec.getEndPos() - sec.getStartPos();
                        if(firstLength > secondLength) {
                            filteredLexemes.remove(sec);
                        }
                        else if(firstLength < secondLength) {
                            filteredLexemes.remove(one);
                        }
                        else {
                            int firstPriority = FsmFamilyHelper.findByName(lexemes.get(i).getFamily()).getPriority();
                            int secondPriority = FsmFamilyHelper.findByName(lexemes.get(k).getFamily()).getPriority();
                            if(firstPriority > secondPriority) {
                                filteredLexemes.remove(sec);
                            }
                            else {
                                filteredLexemes.remove(one);
                            }
                        }
                    }
                }
            }
        }
        return filteredLexemes;
    }
}
