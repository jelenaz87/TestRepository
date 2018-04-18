package com.example.android.sunshine.messages;

import com.example.android.sunshine.sync.SunshineSyncTask;
import com.example.android.sunshine.ui_component.DetailActivity;
import com.example.android.sunshine.ui_component.MainActivity;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by jelena.zivanovic on 12/13/2017.
 */

public class MessageQueue {

    public static ArrayBlockingQueue<Object> listMessages = new ArrayBlockingQueue<>(16);


    public void postMessage(Object object) {
        listMessages.add(object);
        getMessage();
    }

    private void getMessage() {
        Object o = listMessages.remove();
        Class<?> aClass = o.getClass();
        if (RegisterSubscriber.listSubscribers.containsKey(aClass)) {
            for (int i = 0; i < RegisterSubscriber.listSubscribers.get(aClass).size(); i++) {
                RegisterSubscriber.listSubscribers.get(aClass).get(i).message(o);
            }

        }


    }
}


