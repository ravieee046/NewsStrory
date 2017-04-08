package com.ravi.phoebus.www.newsstrory;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

public class NewsStoryMainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<NewsStory>> {

    public static final String LOG_TAG = NewsStoryMainActivity.class.getSimpleName();
    public static final String GUARDIAN_REQUEST_URL = "https://content.guardianapis.com/search?api-key=460ff024-fb65-411e-81eb-b16c23a61eca";

    private NewsStoryAdapter newsStoryAdapter;
    private ProgressBar progressBar;
    private TextView emptyTextView;
    private ListView newsListView;

    private static final int NEWSSTORY_LOADER_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_story_main);
        progressBar = (ProgressBar)findViewById(R.id.progress_bar);
        emptyTextView = (TextView)findViewById(R.id.empty_view);

        if( (getActiveNetworkInfo() == null) || !getActiveNetworkInfo().isConnected() ) {
            progressBar.setVisibility(View.GONE);
            emptyTextView.setText(R.string.no_internet_connection);
        } else {
            progressBar.setVisibility(View.VISIBLE);
            emptyTextView.setText("");

            // Get a reference to the LoaderManager, in order to interact with loaders.
            LoaderManager loaderManager = getLoaderManager();

            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            loaderManager.initLoader(NEWSSTORY_LOADER_ID, null, this);
        }
    }

    public NetworkInfo getActiveNetworkInfo() {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo();
    }

    @Override
    public Loader<List<NewsStory>> onCreateLoader(int i, Bundle bundle) {
        return new NewsStoryAsyncTaskLoader(this,GUARDIAN_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<NewsStory>> loader, List<NewsStory> newsStories) {
        progressBar.setVisibility(View.GONE);
        emptyTextView.setText(R.string.no_news_found);
        // Clear the adapter of previous earthquake data
        if (newsStoryAdapter != null) {
            newsStoryAdapter.clear();
        }

        if(newsStories == null){
            return;
        }
        // Update the information displayed to the user.
        updateUi(newsStories);
    }

    @Override
    public void onLoaderReset(Loader<List<NewsStory>> loader) {
        newsStoryAdapter.clear();
    }

    private void updateUi(List<NewsStory> newsStories){
        newsStoryAdapter = new NewsStoryAdapter(this,newsStories);

        newsListView = (ListView)findViewById(R.id.list);
        newsListView.setEmptyView(emptyTextView);
        newsListView.setAdapter(newsStoryAdapter);

        setNewsStoryItemClickListener();
    }
    private void setNewsStoryItemClickListener(){
        newsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                NewsStory newsStory = newsStoryAdapter.getItem(position);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(newsStory.getWebUrl()));
                startActivity(intent);
            }
        });
    }
}