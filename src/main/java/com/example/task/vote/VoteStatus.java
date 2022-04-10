package com.example.task.vote;

public enum VoteStatus {
    UP(+1), DOWN(-1);

    private int value;
    public int getValue() {
        return this.value;
    }
    VoteStatus(int value) {
        this.value = value;
    }
}