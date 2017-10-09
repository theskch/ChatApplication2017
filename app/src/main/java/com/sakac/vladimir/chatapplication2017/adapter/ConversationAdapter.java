package com.sakac.vladimir.chatapplication2017.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.sakac.vladimir.chatapplication2017.dao.ConversationDao;
import com.sakac.vladimir.chatapplication2017.eventbus.OttoBus;
import com.sakac.vladimir.chatapplication2017.eventbus.event.ConversationsUpdatedEvent;
import com.sakac.vladimir.chatapplication2017.model.Conversation;
import com.sakac.vladimir.chatapplication2017.view.ConversationItemView;
import com.sakac.vladimir.chatapplication2017.view.ConversationItemView_;
import com.squareup.otto.Subscribe;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sakac on 5/7/2017.
 */

@EBean
public class ConversationAdapter extends BaseAdapter {

    private List<Conversation> conversations = new ArrayList<>();

    @RootContext
    Context context;

    @Bean
    ConversationDao conversationDao;

    @Bean
    OttoBus bus;

    @AfterInject
    void init(){
        bus.register(this);
    }

    public void resetConversatonFlow(){
        conversationDao.init();
    }

    @Override
    public int getCount() {
        return conversations.size();
    }

    @Override
    public Conversation getItem(int position) {
        return conversations.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ConversationItemView personItemView;
        if (convertView == null) {
            personItemView = ConversationItemView_.build(context);
        } else {
            personItemView = (ConversationItemView) convertView;
        }

        personItemView.bind(conversations.get(position));

        return personItemView;
    }

    public List<Conversation> getConversations() {
        return conversations;
    }

    private void setConversations(List<Conversation> conversations) {
        this.conversations = conversations;
        notifyDataSetChanged();
    }

    @Subscribe
    public void ConversationsUpdated(ConversationsUpdatedEvent event){
        setConversations(conversationDao.getConversations());
    }
}
