package com.example.staticmusicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
private Button play;
private Button pause;
private SeekBar seekBar;
private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        play=findViewById(R.id.play);
        pause=findViewById(R.id.pause);
        seekBar=findViewById(R.id.seekBar);
        //Using Local Source
//     mediaPlayer =MediaPlayer.create(this,R.raw.new_song);
      //  mediaPlayer.start();
        //Using Remote source or Internet
        mediaPlayer =new MediaPlayer();
        try {
            mediaPlayer.setDataSource("https://themamaship.com/music/Catalog/Africa%20-%20Toto.mp3");
        } catch (IOException e) {
            e.printStackTrace();
        }


        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                Toast.makeText(MainActivity.this, "Ready to Play", Toast.LENGTH_SHORT).show();
                mediaPlayer.start();
                seekBar.setMax(mediaPlayer.getDuration());
                seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                       if(fromUser){
                           mediaPlayer.seekTo(progress);
                       }
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });
            }
        });
        mediaPlayer.prepareAsync();

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.start();

            }
        });
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.pause();
            }
        });
        // mediaPlayer.seekTo(R.raw.ed_sheeran_perfect);
        // mediaPlayer.stop();


    }
}