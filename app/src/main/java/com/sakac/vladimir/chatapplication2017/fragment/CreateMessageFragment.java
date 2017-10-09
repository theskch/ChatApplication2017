package com.sakac.vladimir.chatapplication2017.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.sakac.vladimir.chatapplication2017.R;
import com.sakac.vladimir.chatapplication2017.dao.MessageDao;
import com.sakac.vladimir.chatapplication2017.dao.UserDao;
import com.sakac.vladimir.chatapplication2017.model.Conversation;
import com.sakac.vladimir.chatapplication2017.model.Message;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

/**
 * A simple {@link Fragment} subclass.
 */
@EFragment(R.layout.fragment_create_message)
public class CreateMessageFragment extends Fragment {

    @Bean
    MessageDao messageDao;

    @ViewById
    EditText messageText;

    @Bean
    UserDao userDao;

    public CreateMessageFragment() {
        // Required empty public constructor
    }

    public void initFor(Conversation conversation){
        messageDao.initFor(conversation);
    }

    @Click
    void sendMessage(){
        final String text = messageText.getText().toString();
        if(text.isEmpty()){
            return;
        }

        final Message message = new Message(userDao.getCurrentUser(), text);
        messageDao.write(message);
        messageText.getText().clear();
    }
}
