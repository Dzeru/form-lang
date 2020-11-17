package com.dzeru.formallanguage.fsm;

import com.dzeru.formallanguage.lexeme.Lexeme;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FsmFamilyHelper {
    // k - family name, v - family
    private static Map<String, FsmFamily> fsmFamilies = new HashMap<>();

    public static void loadFsmFamilies(List<String> paths) throws Exception {
        for(String path: paths) {
            String familyName = getFamilyName(path);
            FiniteStateMachine finiteStateMachine = loadFsm(path);
            if(fsmFamilies.containsKey(familyName)) {
                fsmFamilies.get(familyName).addFiniteStateMachine(finiteStateMachine);
            }
            else {
                List<FiniteStateMachine> finiteStateMachines = new ArrayList<>();
                finiteStateMachines.add(finiteStateMachine);
                fsmFamilies.put(familyName, new FsmFamily(finiteStateMachines, familyName, getPriorityByFamilyName(familyName)));
            }
        }
    }

    public static FsmFamily findByName(String name) {
        return fsmFamilies.get(name);
    }

    public static List<FsmFamily> findByPriority(int priority) {
        List<FsmFamily> families = new ArrayList<>();
        for(Map.Entry<String, FsmFamily> familyEntry : fsmFamilies.entrySet()) {
            if(familyEntry.getValue().getPriority() == priority) {
                families.add(familyEntry.getValue());
            }
        }
        return families;
    }

    public static List<Lexeme> max(String input, int skip) {
        List<Lexeme> lexemes = new ArrayList<>();
        for(Map.Entry<String, FsmFamily> fsmFamilyEntry: fsmFamilies.entrySet()) {
            for(FiniteStateMachine fsm : fsmFamilyEntry.getValue().getFiniteStateMachines()) {
                Map.Entry<Boolean, Integer> mx = fsm.max(input, skip);
                if(mx.getKey()) {
                    lexemes.add(new Lexeme(skip, skip + mx.getValue(), fsmFamilyEntry.getKey()));
                }
            }
        }
        return lexemes;
    }

    private static int getPriorityByFamilyName(String familyName) {
        switch(familyName) {
            case "assignment": return 10;
            case "boolean": return 9;
            case "keyword": return 10;
            case "number": return 8;
            case "operator": return 9;
            case "whitespace": return 1;
            case "id": return 1;
            default: return 1;
        }
    }

    private static FiniteStateMachine loadFsm(String dir) throws Exception {
        String json = new String(Files.readAllBytes(Paths.get(dir)));
        return FsmConverter.fromJson(json);
    }

    private static String getFamilyName(String path) {
        String tmp = path.substring(0, path.lastIndexOf("\\"));
        return tmp.substring(tmp.lastIndexOf("\\") + 1);
    }
}
