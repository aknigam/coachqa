package com.coachqa.entity;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public enum UserTypeEnum {
    application_admin("application_admin",1),
    account_admin("account_admin",2),
    classroom_admin("classroom_admin",3),
    application_user("application_user",4);

    private final int id;
    private final String name;

    UserTypeEnum(String name, int id) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    private static final Map<Integer, UserTypeEnum> m = new HashMap<>();
    static {

        for (UserTypeEnum u : UserTypeEnum.values())
            m.put(u.getId(), u);
    }
    public static UserTypeEnum from(Integer id) {
        return m.get(id);
    }
}
