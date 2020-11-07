package com.dzeru.formallanguage.fsm;

import com.google.gson.*;

import java.util.*;

public class FsmConverter {

    private static Gson gson = new GsonBuilder().create();

    public static FiniteStateMachine fromJson(String json) {
        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
        JsonArray start = jsonObject.get("start").getAsJsonArray();
        Set<String> startStates = new HashSet<>();
        for(JsonElement s : start) {
            startStates.add(s.getAsString());
        }
        JsonArray finish = jsonObject.get("finish").getAsJsonArray();
        Set<String> finishStates = new HashSet<>();
        for(JsonElement f : finish) {
            finishStates.add(f.getAsString());
        }
        Map<FsmPair, String> states = new HashMap<>();
        JsonObject matrix = jsonObject.getAsJsonObject("matrix");
        for(Map.Entry<String, JsonElement> entry : matrix.entrySet()) {
            JsonObject step = entry.getValue().getAsJsonObject();
            for(Map.Entry<String, JsonElement> stepEntry : step.entrySet()) {
                JsonArray st = stepEntry.getValue().getAsJsonArray();
                for(JsonElement el : st) {
                    states.put(new FsmPair(stepEntry.getKey(), entry.getKey()), el.getAsString());
                }
            }
        }

        return new FiniteStateMachine(states, startStates, finishStates);
    }
}
