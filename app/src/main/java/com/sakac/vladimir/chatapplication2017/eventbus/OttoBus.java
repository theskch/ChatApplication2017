package com.sakac.vladimir.chatapplication2017.eventbus;

import com.squareup.otto.Bus;

import org.androidannotations.annotations.EBean;

/**
 * Created by sakac on 5/13/2017.
 */

@EBean(scope = EBean.Scope.Singleton)
public class OttoBus extends Bus {

}