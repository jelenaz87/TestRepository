package com.example.android.sunshine.messages;

import com.example.android.sunshine.ui_component.MainActivity;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by jelena.zivanovic on 12/13/2017.
 */

public class RegisterSubscriber {

    public static ConcurrentHashMap<Class<?>,ArrayList<MessageEvent> > listSubscribers = new ConcurrentHashMap<>();

    public RegisterSubscriber () {

    }

    public void register (Class<?> mClass, MessageEvent event) {
     if(listSubscribers.containsKey(mClass) ) {
         listSubscribers.get(mClass).add(event);
     } else {
         ArrayList<MessageEvent> messageEventArrayList = new ArrayList<>();
         messageEventArrayList.add(event);
         listSubscribers.put(mClass, messageEventArrayList);
     }


    }

    public void unRegister (Class<?> mClass, MessageEvent event) {
       if (listSubscribers.get(mClass).contains(event) ) {
           listSubscribers.get(mClass).remove(event);
       }

       }
     {


    }
}
