package com.dzeru.formallanguage.fsm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FiniteStateMachine {
    private int res = 0;
    private boolean success = false;
    private Map<FsmPair, String> states;
    private Set<String> start;
    private Set<String> finish;
    private Set<String> currentStates;
    private boolean isRegExp = false;

    public FiniteStateMachine(int res,
                              boolean success,
                              Map<FsmPair, String> states,
                              Set<String> start,
                              Set<String> finish) {
        this.res = res;
        this.success = success;
        this.states = states;
        this.start = start;
        this.finish = finish;
        this.currentStates = start;
    }

    public FiniteStateMachine(int res,
                              boolean success,
                              Map<FsmPair, String> states,
                              Set<String> start,
                              Set<String> finish,
                              boolean isRegExp) {
        this.res = res;
        this.success = success;
        this.states = states;
        this.start = start;
        this.finish = finish;
        this.currentStates = start;
        this.isRegExp = isRegExp;
    }

    public FiniteStateMachine(Map<FsmPair, String> states,
                              Set<String> start,
                              Set<String> finish) {
        this.states = states;
        this.start = start;
        this.finish = finish;
        this.currentStates = start;
    }

    public FiniteStateMachine(Map<FsmPair, String> states,
                              Set<String> start,
                              Set<String> finish,
                              boolean isRegExp) {
        this.states = states;
        this.start = start;
        this.finish = finish;
        this.currentStates = start;
        this.isRegExp = isRegExp;
    }

    public FiniteStateMachine() {
    }

    //TODO: read about regexp patterns and ability to find indexes
    public Map.Entry<Boolean, Integer> max(String input, int skip) {
        Map<Boolean, Integer> resSuccess = new HashMap<>();
        int counter = 0;
        for(int i = Math.max(0, skip); i < input.length(); i++) {
            if(hasFinalState()) {
                this.res = counter;
                this.success = true;
            }
            String curChar = String.valueOf(input.charAt(i));
            this.currentStates = findNextStates(curChar, this.currentStates);
            if(this.currentStates.isEmpty()) {
                break;
            }
            else {
                counter++;
            }
        }
        if(hasFinalState()) {
            this.res = counter;
            this.success = true;
        }

        resSuccess.put(this.success, this.res);
        return resSuccess.entrySet().iterator().next();
    }

    private boolean hasFinalState() {
        for(String cur : this.currentStates) {
            if(this.finish.contains(cur)) {
                return true;
            }
        }
        return false;
    }

    private Set<String> findNextStates(String input, Set<String> currentStates) {
        Set<String> nextStates = new HashSet<>();
        for(Map.Entry<FsmPair, String> st : this.states.entrySet()) {
            if(st.getKey().getInput().equals(input) && currentStates.contains(st.getKey().getState())) {
                nextStates.add(st.getValue());
            }
        }
        return nextStates;
    }

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Map<FsmPair, String> getStates() {
        return states;
    }

    public void setStates(Map<FsmPair, String> states) {
        this.states = states;
    }

    public Set<String> getStart() {
        return start;
    }

    public void setStart(Set<String> start) {
        this.start = start;
    }

    public Set<String> getFinish() {
        return finish;
    }

    public void setFinish(Set<String> finish) {
        this.finish = finish;
    }

    public Set<String> getCurrentStates() {
        return currentStates;
    }

    public void setCurrentStates(Set<String> currentStates) {
        this.currentStates = currentStates;
    }

    public boolean isRegExp() {
        return isRegExp;
    }

    public void setRegExp(boolean regExp) {
        isRegExp = regExp;
    }

    @Override
    public String toString() {
        return "FiniteStateMachine{" +
                "res=" + res +
                ", success=" + success +
                ", states=" + states +
                ", start=" + start +
                ", finish=" + finish +
                ", currentStates=" + currentStates +
                ", isRegExp=" + isRegExp +
                '}';
    }
}
