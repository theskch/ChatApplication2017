package com.sakac.vladimir.chatapplication2017.view;

import android.content.Context;
import android.text.format.DateFormat;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.sakac.vladimir.chatapplication2017.model.Message;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * Created by sakac on 5/20/2017.
 */

@EViewGroup
public class BaseMessageItemView extends RelativeLayout {

    @ViewById
    TextView messageTime;

    @ViewById
    TextView messageUser;

    @ViewById
    TextView messageText;

    @ViewById
    SimpleDraweeView userPhoto;

    public BaseMessageItemView(Context context) {
        super(context);
    }

    public BaseMessageItemView bind(Message message){
        messageText.setText(message.getText());
        messageTime.setText(DateFormat.format("HH:hh dd MMM", message.getTimestamp()));
        messageUser.setText(message.getUser().getUsername());
        userPhoto.setImageURI(message.getUser().getPhotoUrl());
        return this;
    }
}
