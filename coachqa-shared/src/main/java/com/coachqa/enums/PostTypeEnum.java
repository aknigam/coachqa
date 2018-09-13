package com.coachqa.enums;




public enum PostTypeEnum {

    QUESTION(1),
    ANSWER(0);


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    private int type;

    PostTypeEnum(int type){
       this.type = type;
    }

    public static PostTypeEnum getPostType(int type){
        if(type == 1)
            return QUESTION;

        if(type == 2)
            return ANSWER;

        throw new RuntimeException("Invalid type: "+ type);

    }


}
