package com.example.android.sunshine.messages;

import android.support.annotation.NonNull;

import com.example.android.sunshine.retrofit.GetClient;

import java.util.concurrent.ConcurrentHashMap;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jelena.zivanovic on 12/13/2017.
 */

public class MessageBus {

    private static MessageBus messageBus;
    public RegisterSubscriber subscriber;
    public MessageQueue queue;

    private MessageBus() {
        subscriber = new RegisterSubscriber();
        queue = new MessageQueue();
    }


    public static MessageBus getInstance() {

        if (messageBus == null) {
            messageBus = new MessageBus();
        }
        return messageBus;
    }

}
