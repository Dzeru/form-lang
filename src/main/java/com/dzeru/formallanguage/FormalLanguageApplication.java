package com.dzeru.formallanguage;

import com.dzeru.formallanguage.fsm.FiniteStateMachine;
import com.dzeru.formallanguage.fsm.FsmConverter;
import com.dzeru.formallanguage.fsm.FsmFamilyHelper;
import com.dzeru.formallanguage.lexeme.Lexeme;
import com.dzeru.formallanguage.lexeme.LexemeFinder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class FormalLanguageApplication {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

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

    public static void main(String[] args) throws IOException {
        System.out.print("Какое задание хотите протестировать? [1/2] ");
        String task = br.readLine();
        if(task.equalsIgnoreCase("1")) {
            task1();
        }
        else if(task.equalsIgnoreCase("2")) {
            task2();
        }
    }

    private static void task1() {
        try {
            boolean hasNext = true;
            while(hasNext) {
                System.out.println("Введите абсолютный путь к файлу с описанием автомата: ");
                String dir = br.readLine();
                System.out.println("Введите входную последовательность: ");
                String input = br.readLine();
                System.out.println("Введите количество пропускаемых сначала символов: ");
                int skip = Integer.parseInt(br.readLine());
                // C:\bagpack\aprojects\formal-language\src\main\resources\test1.json
                // C:\bagpack\aprojects\formal-language\src\main\resources\test2.json
                // C:\bagpack\aprojects\formal-language\src\main\resources\lexer\real\number.json
                // C:\bagpack\aprojects\formal-language\src\main\resources\lexer\int\int.json
                // C:\bagpack\aprojects\formal-language\src\main\resources\lexer\id\id.json
                // 12345
                String json = new String(Files.readAllBytes(Paths.get(dir)));
                FiniteStateMachine finiteStateMachine = FsmConverter.fromJson(json);
                Map.Entry<Boolean, Integer> mx = finiteStateMachine.max(input, skip);
                System.out.println(String.format("Результат работы автомата: success = %s, res = %d", mx.getKey(), mx.getValue()));
                if(mx.getKey()) {
                    System.out.println("Вывод:" + input.substring(skip, skip + mx.getValue()) + ":");
                }
                System.out.print("Хотите ввести еще? [y/n, да/нет] ");
                String continueInput = br.readLine();
                if(continueInput.equalsIgnoreCase("n")
                        || continueInput.equalsIgnoreCase("нет")) {
                    hasNext = false;
                }
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    private static void task2() {
        try {
            FsmFamilyHelper.loadFsmFamilies(lexerPaths);
            // C:\bagpack\aprojects\formal-language\src\main\resources\lexer.js
            // C:\bagpack\aprojects\formal-language\src\main\resources\lexer2.js
            System.out.println("Введите путь к анализируемому файлу:");
            String analyzePath = br.readLine();
            System.out.println("Содержимое файла:");
            String analyzeText = new String(Files.readAllBytes(Paths.get(analyzePath)));
            System.out.println(analyzeText);
            LexemeFinder lexemeFinder = new LexemeFinder(analyzeText);
            List<Lexeme> lexemes = lexemeFinder.findAllLexemes(0);
            lexemeFinder.printLexemes(lexemes);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
