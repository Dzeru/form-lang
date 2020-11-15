package com.dzeru.formallanguage.fsm;

public class FsmPair {
    private final String input;
    private final String state;

    public FsmPair(String input, String state) {
        this.input = input;
        this.state = state;
    }

    public String getInput() {
        return input;
    }

    public String getState() {
        return state;
    }

    @Override
    public String toString() {
        return "FsmPair{" +
                "input='" + input + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
