package com.example.a3wins;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {

    List<ImageView> imageViewList = new ArrayList<>();

    public String[] spielfeld;

    boolean player1;
    int[] imageViewIds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        spielfeld = new String[]{"", "", ""
                                ,"", "", ""
                                ,"", "", ""};
        imageViewIds = new int[]{
                R.id.imageView6,
                R.id.imageView7,
                R.id.imageView8,
                R.id.imageView9,
                R.id.imageView10,
                R.id.imageView11,
                R.id.imageView12,
                R.id.imageView13,
                R.id.imageView14
        };


        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;


        });



        Log.i("info",String.valueOf(imageViewIds[1]));

        for (int id : imageViewIds) {
            ImageView imageView = findViewById(id);
            imageViewList.add(imageView);
        }


    }

    public void play(View view) {

        int clickedID = view.getId();


        for (int i = 0; i < imageViewList.size(); i++) {

            if (imageViewList.get(i).getId() == clickedID) {

                Log.i("CLICKED ID",String.valueOf(imageViewList.get(i).getId()));
                Log.i("PLAYER1?",String.valueOf(player1));


                int finalI = i;
                imageViewList.get(i).animate().alpha(0).setDuration(50).withEndAction(() -> {
                    imageViewList.get(finalI).setImageResource(player1 ? R.drawable.red : R.drawable.yellow);

                            spielfeld[finalI] = player1 ? "Player1" : "Player2";
                            Log.i("spielfeld2", spielfeld[finalI]);


                    player1 = !player1;
                    Log.i("spielfeld", Arrays.deepToString(spielfeld));
                    imageViewList.get(finalI).animate().alpha(1).setDuration(10);
                });
            }

        }

        for (String punkt:spielfeld) {
            Log.i("Punkt", punkt);
        }

    }

    /*

    [Player2, ,
    , , Player1,
    , , , Player2]


     */

    public void newGame(View view) {

        spielfeld = new String[]{"", "", ""
                ,"", "", ""
                ,"", "", ""};


        for (ImageView i : imageViewList) {
            i.setImageResource(0);
            i.animate().alpha(1.0f);

        }
    }
}