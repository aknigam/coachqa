package com.coachqa.enums;

/**
 * Created by a.nigam on 25/12/16.
 */
public enum QuestionLevelEnum {

    /**
     * '1','Medium','1'
     '2','Hard','3'
     '3','Easy','1'
     */

    MEDIUM(1),
    HARD(2),
    EASY(3);

    public int getId() {
        return id;
    }

    private int id;

    QuestionLevelEnum(int id) {
        this.id = id;
    }
}
