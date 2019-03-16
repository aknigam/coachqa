package com.coachqa.util;

import com.coachqa.entity.Answer;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by a.nigam on 25/12/16.
 */
public class CollectionUtils {
    public static boolean isEmpty(Collection<?> tags) {
        return org.springframework.util.CollectionUtils.isEmpty(tags);
    }

    public static List<Answer> normaliseNullList(List<Answer> answers) {
        if(org.springframework.util.CollectionUtils.isEmpty(answers)) {
            return Collections.emptyList();
        }
        return answers;
    }
}
