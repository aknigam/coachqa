package com.publish;

/**
 * Created by a.nigam on 29/12/16.
 */
@Deprecated
public interface ApplicationEventListener<E> {

    void onEvent(ApplicationEvent<E> event);
}
