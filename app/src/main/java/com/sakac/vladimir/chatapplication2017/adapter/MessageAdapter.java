package com.sakac.vladimir.chatapplication2017.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.sakac.vladimir.chatapplication2017.dao.MessageDao;
import com.sakac.vladimir.chatapplication2017.dao.UserDao;
import com.sakac.vladimir.chatapplication2017.eventbus.OttoBus;
import com.sakac.vladimir.chatapplication2017.eventbus.event.MessagesUpdatedEvent;
import com.sakac.vladimir.chatapplication2017.generic.RecyclerViewAdapterBase;
import com.sakac.vladimir.chatapplication2017.generic.ViewWrapper;
import com.sakac.vladimir.chatapplication2017.model.Conversation;
import com.sakac.vladimir.chatapplication2017.model.Message;
import com.sakac.vladimir.chatapplication2017.view.BaseMessageItemView;
import com.sakac.vladimir.chatapplication2017.view.IncomingMessageItemView_;
import com.sakac.vladimir.chatapplication2017.view.OutgoingMessageItemView;
import com.sakac.vladimir.chatapplication2017.view.OutgoingMessageItemView_;
import com.squareup.otto.Subscribe;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

/**
 * Created by sakac on 5/20/2017.
 */

@EBean
public class MessageAdapter extends RecyclerViewAdapterBase<Message, BaseMessageItemView> {

    private static final int INCOMING_MESSAGE = 0;
    private static final int OUTGOING_MESSAGE = 1;

    @Bean
    UserDao userDao;

    @Bean
    MessageDao messageDao;

    @RootContext
    Context context;

    @Bean
    OttoBus bus;

    public void initFor(Conversation conversation){
        bus.register(this);
        messageDao.initFor(conversation);
    }

    @Override
    protected BaseMessageItemView onCreateItemView(ViewGroup parent, int viewType) {
        switch (viewType){
            case OUTGOING_MESSAGE:
                return OutgoingMessageItemView_.build(context);
            case INCOMING_MESSAGE:
                return IncomingMessageItemView_.build(context);
            default:
                throw new IllegalArgumentException("View type not supported");
        }
    }

    @Override
    public void onBindViewHolder(ViewWrapper<BaseMessageItemView> holder, int position) {
        final Message message = items.get(position);
        holder.getView().bind(message);
    }

    @Override
    public int getItemViewType(int position) {
        final Message message = items.get(position);
        return  message.getUser().equals(userDao.getCurrentUser()) ? OUTGOING_MESSAGE : INCOMING_MESSAGE;
    }

    @Subscribe
    public void updateMessages(MessagesUpdatedEvent event){
        items = messageDao.getMessages();
        notifyDataSetChanged();
    }
}
