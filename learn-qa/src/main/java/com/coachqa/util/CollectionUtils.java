package com.coachqa.util;

import java.util.Collection;
import java.util.List;

/**
 * Created by a.nigam on 25/12/16.
 */
public class CollectionUtils {
    public static boolean isEmpty(Collection<?> tags) {
        return org.springframework.util.CollectionUtils.isEmpty(tags);
    }
}
