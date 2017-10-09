package com.sakac.vladimir.chatapplication2017.view;

import android.content.Context;

import com.sakac.vladimir.chatapplication2017.R;

import org.androidannotations.annotations.EViewGroup;

/**
 * Created by sakac on 5/20/2017.
 */

@EViewGroup(R.layout.item_view_message_incoming)
public class IncomingMessageItemView extends BaseMessageItemView {

    public IncomingMessageItemView(Context context) {
        super(context);
    }
}
