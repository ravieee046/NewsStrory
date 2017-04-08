package com.ravi.phoebus.www.newsstrory;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class NewsStoryAsyncTaskLoader extends AsyncTaskLoader<List<NewsStory>> {

    private String[] mUrl;

    public NewsStoryAsyncTaskLoader(Context context, String... mUrl) {
        super(context);
        this.mUrl = mUrl;
    }

    @Override
    public List<NewsStory> loadInBackground() {
        return QueryUtils.fetchNewsStoryData(mUrl[0]);
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }
}
