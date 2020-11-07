package com.dzeru.formallanguage;

import com.dzeru.formallanguage.fsm.FiniteStateMachine;
import com.dzeru.formallanguage.fsm.FsmConverter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class FormalLanguageApplication {

    public static void main(String[] args) {
        try {
            boolean hasNext = true;
            while(hasNext) {
                System.out.println("Введите абсолютный путь к файлу с описанием автомата: ");
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String dir = br.readLine();
                System.out.println("Введите входную последовательность: ");
                String input = br.readLine();
                System.out.println("Введите количество пропускаемых сначала символов: ");
                int skip = Integer.parseInt(br.readLine());
                // C:\bagpack\aprojects\formal-language\src\main\resources\test1.json
                // C:\bagpack\aprojects\formal-language\src\main\resources\test2.json
                // 12345
                String json = new String(Files.readAllBytes(Paths.get(dir)));
                FiniteStateMachine finiteStateMachine = FsmConverter.fromJson(json);
                Map.Entry<Boolean, Integer> mx = finiteStateMachine.max(input, skip);
                System.out.println(String.format("Результат работы автомата: success = %s, res = %d", mx.getKey(), mx.getValue()));
                if(mx.getKey()) {
                    System.out.println("Вывод: " + input.substring(skip, skip + mx.getValue()));
                }
                System.out.println("Хотите ввести еще? [y/n, да/нет]");
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
}
