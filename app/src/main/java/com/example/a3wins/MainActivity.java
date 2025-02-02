package com.example.a3wins;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {

    List<ImageView> imageViewList = new ArrayList<>();

    public String[] spielfeld;

    boolean player1;
    int zahlen;

    int punkteP1;
    int punkteP2;
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


                    //Vertikale Operation
                    for (String punkt:spielfeld) {
                        zahlen++;
                        if(zahlen==4){punkteP2=0;punkteP1=0;zahlen=1;}
                        if(Objects.equals(punkt, "Player1")){
                            punkteP1++;
                            Log.i("PUNKTEEE", "Punkt für Player 1");
                        }
                        else {
                            punkteP1=0;
                        }
                        if(Objects.equals(punkt, "Player2")){
                            punkteP2++;
                            Log.i("PUNKTEEE", "Punkt für Player 2");
                        }
                        else {
                            punkteP2=0;
                        }

                        if(punkteP1==3) {
                            Toast.makeText(this, "RED Wins", Toast.LENGTH_SHORT).show();
                            winningAnimation();

                            break;
                        }
                        if(punkteP2==3){
                            Toast.makeText(this, "YELLOW Wins", Toast.LENGTH_SHORT).show();
                            winningAnimation();

                            break;
                        }
                    }

                    punkteP2=0;
                    punkteP1=0;

                    //Horizontale Operation
                    for(int g = 0; ; g=g+3){
                        if(g==9){punkteP1=0;punkteP2=0;g=1;}
                        if(g==10){punkteP1=0;punkteP2=0;g=2;}
                        if(g==11){break;}
                        Log.i("G=",String.valueOf(g));
                        if(Objects.equals(spielfeld[g], "Player1")){
                            punkteP1++;
                            Log.i("PUNKTEEE", "Punkt für Player 1");
                        }
                        else {
                            punkteP1=0;
                        }
                        if(Objects.equals(spielfeld[g], "Player2")){
                            punkteP2++;
                            Log.i("PUNKTEEE", "Punkt für Player 2");
                        }
                        else {
                            punkteP2=0;
                        }

                        if(punkteP1==3) {
                            Toast.makeText(this, "RED Wins", Toast.LENGTH_SHORT).show();
                            winningAnimation();

                            break;
                        }
                        if(punkteP2==3){
                            Toast.makeText(this, "YELLOW Wins", Toast.LENGTH_SHORT).show();
                            winningAnimation();

                            break;
                        }
                    }
                    punkteP2=0;
                    punkteP1=0;
                    int[] sequenz = {0, 4, 8, 2, 4, 6};
                    //Diagonale Operation
                    for(int see : sequenz ){
                        //if(sequenz[see]==8){punkteP1=0;punkteP2=0;}





                        Log.i("GH=",String.valueOf(see));
                        if(Objects.equals(spielfeld[see], "Player1")){
                            punkteP1++;
                            Log.i("PUNKTEEE", "Punkt für Player 1");
                        }
                        else {
                            punkteP1=0;
                        }
                        if(Objects.equals(spielfeld[see], "Player2")){
                            punkteP2++;
                            Log.i("PUNKTEEE", "Punkt für Player 2");
                        }
                        else {
                            punkteP2=0;
                        }

                        if(punkteP1==3) {
                            Toast.makeText(this, "RED Wins", Toast.LENGTH_SHORT).show();
                            winningAnimation();

                            break;
                        }
                        if(punkteP2==3){
                            Toast.makeText(this, "YELLOW Wins", Toast.LENGTH_SHORT).show();
                            winningAnimation();

                            break;
                        }
                    }
                    punkteP2=0;
                    punkteP1=0;



                });
            }

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

            i.animate().rotation(360.0F).setDuration(1000).withEndAction(() -> {
                i.animate().alpha(0).setDuration(1000).withEndAction(()->{i.setRotation(0);i.setImageResource(0);});

            }
            );

        }
    }

    public void winningAnimation(){
        for (ImageView z : imageViewList) {

            z.animate().alpha(0.1F).setDuration(500).withEndAction(()->{z.animate().alpha(1).setDuration(500);});

        }
    }
}