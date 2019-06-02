package com.example.owner.languageconverter;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    private MediaPlayer.OnCompletionListener mCompleteListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_phrases);

            final ArrayList<Word> words = new ArrayList<>();
            words.add(new Word("Where are you going?", "Minto Wuksus", R.raw.phrase_where_are_you_going));
            words.add(new Word("What is your name?", "tinnә oyaase'nә", R.raw.phrase_what_is_your_name));
            words.add(new Word("My name is...", "oyaaset...", R.raw.phrase_my_name_is));
            words.add(new Word("How are you feeling?", "michәksәs?", R.raw.phrase_how_are_you_feeling));
            words.add(new Word("I’m feeling good.", "kuchi achit", R.raw.phrase_im_feeling_good));
            words.add(new Word("Are you coming?", "әәnәs'aa?", R.raw.phrase_are_you_coming));
            words.add(new Word("Yes, I’m coming.", "hәә’ әәnәm", R.raw.phrase_yes_im_coming));
            words.add(new Word("I’m coming.", "әәnәm", R.raw.phrase_im_coming));
            words.add(new Word("Let’s go.", "yoowutis", R.raw.phrase_lets_go));
            words.add(new Word("Come here.", "әnni'nem", R.raw.phrase_come_here));

            // Create an {@link WordAdapter}, whose data source is a list of {@link Word}s. The
            // adapter knows how to create list items for each item in the list.
            WordAdapter adapter = new WordAdapter(this, words, R.color.category_phrases);

            // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
            // There should be a {@link ListView} with the view ID called list, which is declared in the
            // activity_numbers.xml layout file.
            ListView listView = (ListView) findViewById(R.id.list);

            // Make the {@link ListView} use the {@link WordAdapter} we created above, so that the
            // {@link ListView} will display list items for each {@link Word} in the list.
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Word word = words.get(position);

                    releaseMediaPlayer();

                    mediaPlayer = MediaPlayer.create(PhrasesActivity.this, word.getmAudioResourceId());

                    mediaPlayer.start();

                    mediaPlayer.setOnCompletionListener(mCompleteListener);
                }
            });
        }

    @Override
    protected void onStop(){
        super.onStop();
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer() {
        if(mediaPlayer != null){
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
