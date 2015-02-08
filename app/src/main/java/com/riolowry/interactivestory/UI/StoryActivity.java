package com.riolowry.interactivestory.UI;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.riolowry.interactivestory.Model.Page;
import com.riolowry.interactivestory.Model.Story;
import com.riolowry.interactivestory.R;

public class StoryActivity extends Activity {

    public static final String TAG = StoryActivity.class.getSimpleName();

    private Story mStory = new Story();
    private ImageView mImageView;
    private TextView mTextView;
    private Button mChoice1;
    private Button mChoice2;
    private String mName;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        Intent intent = getIntent();
        mName = intent.getStringExtra(getString(R.string.key_name));

        if(mName == null) {
            mName = "Friend";
        }
        Log.d(TAG, mName);

        mImageView = (ImageView)findViewById(R.id.storyImageView);
        mTextView = (TextView)findViewById(R.id.storyTextView);
        mChoice1 = (Button)findViewById(R.id.choiceButton1);
        mChoice2 = (Button)findViewById(R.id.choiceButton2);

        loadPage();
    }

    private void loadPage(){
        Page page = mStory.getPage(0);

        Drawable drawable = getResources().getDrawable(page.getImageId());
        mImageView.setImageDrawable(drawable);

        String pageText = page.getText();
        // Add name if placeholder included. Won't add if there is no placeholder
        pageText = String.format(pageText,mName);
        mTextView.setText(page.getText());

        mChoice1.setText(page.getChoice1().getText());
        mChoice2.setText(page.getChoice2().getText());
    }
}
