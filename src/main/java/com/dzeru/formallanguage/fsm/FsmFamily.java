package com.dzeru.formallanguage.fsm;

import java.util.List;

public class FsmFamily {

    private final List<FiniteStateMachine> finiteStateMachines;
    private final int priority;
    private final String name;

    public FsmFamily(List<FiniteStateMachine> finiteStateMachines,
                     String name) {
        this.finiteStateMachines = finiteStateMachines;
        this.priority = 1;
        this.name = name;
    }

    public FsmFamily(List<FiniteStateMachine> finiteStateMachines,
                     String name,
                     int priority) {
        this.finiteStateMachines = finiteStateMachines;
        this.name = name;
        this.priority = priority;
    }

    public List<FiniteStateMachine> getFiniteStateMachines() {
        return finiteStateMachines;
    }

    public void addFiniteStateMachine(FiniteStateMachine finiteStateMachine) {
        finiteStateMachines.add(finiteStateMachine);
    }

    public int getPriority() {
        return priority;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "FsmFamily{" +
                "finiteStateMachines=" + finiteStateMachines +
                ", priority=" + priority +
                ", name='" + name + '\'' +
                '}';
    }
}
