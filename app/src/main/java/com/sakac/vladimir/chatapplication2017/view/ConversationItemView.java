package com.sakac.vladimir.chatapplication2017.view;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sakac.vladimir.chatapplication2017.R;
import com.sakac.vladimir.chatapplication2017.model.Conversation;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * Created by sakac on 5/7/2017.
 */

@EViewGroup(R.layout.item_view_conversation)
public class ConversationItemView extends LinearLayout {

    @ViewById
    TextView title;

    public ConversationItemView(Context context) {
        super(context);
    }

    public void bind(Conversation conversation){
        title.setText(conversation.getTitle());
    }
}
