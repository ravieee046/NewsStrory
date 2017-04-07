package com.ravi.phoebus.www.newsstrory;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by gravi on 4/7/2017.
 */

public class NewsStoryAdapter extends ArrayAdapter<NewsStory> {
    public NewsStoryAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<NewsStory> newsStories) {
        super(context, 0, newsStories);
    }
}
