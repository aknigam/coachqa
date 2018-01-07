package com.coachqa.enums;

/**
 * Created by a.nigam on 27/12/16.
 */
public enum QuestionStatusEnum {
    NEW(1);

    private final int id;

    public int getId(){
        return id;
    }

    QuestionStatusEnum(int id) {
        this.id = id;
    }

    public static QuestionStatusEnum from(int id) {
        if(id == 1){
            return NEW;
        }
        throw new RuntimeException("Unknown question status");
    }
}
