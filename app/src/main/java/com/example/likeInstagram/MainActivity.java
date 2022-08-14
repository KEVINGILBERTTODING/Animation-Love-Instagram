package com.example.likeInstagram;

import androidx.appcompat.app.AppCompatActivity;

import android.gesture.GestureStore;
import android.os.Bundle;
import android.os.Handler;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnDoubleTapListener, View.OnClickListener{


    ImageView post, post2;
    ImageButton btnLikes, btnLikes2;
    LottieAnimationView animationView, disslike_animation, animationView2, disslike_animation2, lottieHold, lottieHold2;
    TextView totalLike, totalLike2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLikes = findViewById(R.id.btn_love);
        btnLikes2 = findViewById(R.id.btn_love2);
        post = findViewById(R.id.img_post);
        post2 = findViewById(R.id.img_post2);
        animationView= findViewById(R.id.lottie_like);
        totalLike  = findViewById(R.id.total_like);
        disslike_animation = findViewById(R.id.lottie_disslike);
        totalLike2 = findViewById(R.id.total_like2);
        animationView2 = findViewById(R.id.lottie_like2);
        disslike_animation2 = findViewById(R.id.lottie_disslike2);
        lottieHold2 = findViewById(R.id.lottie_hold2);
        lottieHold = findViewById(R.id.lottie_hold);

        // hide navbar

        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);


        // implements double tap to like and disslike post 1

        post.setOnTouchListener(new View.OnTouchListener() {

            GestureDetector gestureDetector = new GestureDetector(MainActivity.this, new GestureDetector.SimpleOnGestureListener(){

                @Override
                public boolean onDoubleTap(MotionEvent e) {


                    //if buton like is clicked and background drwable is ic_love
                    if (btnLikes.getBackground().getConstantState() == getResources().getDrawable(R.drawable.ic_love).getConstantState()){
                        btnLikes.setBackgroundResource(R.drawable.icon_liked);
                        totalLike.setText(String.valueOf(Integer.parseInt(totalLike.getText().toString()) + 1));

                        // show like animation
                        likeAnimation();

                        Snackbar.make(findViewById(android.R.id.content), "You liked this post", Snackbar.LENGTH_SHORT).show();

                    }
                    else {
                        btnLikes.setBackgroundResource(R.drawable.ic_love);
                        totalLike.setText(String.valueOf(Integer.parseInt(totalLike.getText().toString()) - 1));

                        // sow disslike animation
                        disslikeAnimation();

                        Snackbar.make(findViewById(android.R.id.content), "You disliked this post", Snackbar.LENGTH_SHORT).show();

                    }

                    return super.onDoubleTap(e);
                }

                @Override
                public boolean onDown(MotionEvent e) {
                    return true;
                }
            });




            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                 gestureDetector.onTouchEvent(motionEvent);
                return  true;
            }
        });



        // implements double tap to like and disslike post 1
        btnLikes.setOnClickListener(view -> {
            if (btnLikes.getBackground().getConstantState() == getResources().getDrawable(R.drawable.icon_liked).getConstantState()) {
                // show disslike animation
                disslikeAnimation();
                totalLike.setText(String.valueOf(Integer.parseInt(totalLike.getText().toString()) - 1));
                btnLikes.setBackground(getResources().getDrawable(R.drawable.ic_love));
                Snackbar.make(view, "You unliked this post", Snackbar.LENGTH_SHORT).show();


            } else {
                // shoow like animation
                likeAnimation();
                // change background button
                btnLikes.setBackground(getResources().getDrawable(R.drawable.icon_liked));
                totalLike.setText(String.valueOf(Integer.parseInt(totalLike.getText().toString()) + 1));
                Snackbar.make(view, "liked", Snackbar.LENGTH_SHORT).show();
            }
        });

        // implements double tap to like and disslike post 2

        post2.setOnTouchListener(new View.OnTouchListener() {

            GestureDetector gestureDetector = new GestureDetector(MainActivity.this, new GestureDetector.SimpleOnGestureListener(){

                @Override
                public boolean onDoubleTap(MotionEvent e) {


                    //if buton like is clicked and background drwable is ic_love
                    if (btnLikes2.getBackground().getConstantState() == getResources().getDrawable(R.drawable.ic_love).getConstantState()){
                        btnLikes2.setBackgroundResource(R.drawable.icon_liked);
                        totalLike2.setText(String.valueOf(Integer.parseInt(totalLike2.getText().toString()) + 1));

                        // show like animation
                        likeAnimation2();

                        Snackbar.make(findViewById(android.R.id.content), "You liked this post", Snackbar.LENGTH_SHORT).show();

                    }
                    else {
                        btnLikes2.setBackgroundResource(R.drawable.ic_love);
                        totalLike2.setText(String.valueOf(Integer.parseInt(totalLike2.getText().toString()) - 1));

                        // sow disslike animation
                        disslikeAnimation2();

                        Snackbar.make(findViewById(android.R.id.content), "You disliked this post", Snackbar.LENGTH_SHORT).show();

                    }

                    return super.onDoubleTap(e);
                }

                @Override
                public boolean onDown(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    lottieHold2.setVisibility(View.VISIBLE);
                    Snackbar.make(findViewById(android.R.id.content), "Hold to save", Snackbar.LENGTH_SHORT).show();
                    super.onLongPress(e);
                }
            });




            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                gestureDetector.onTouchEvent(motionEvent);
                return  true;
            }
        });

        // implement hold tap to show sprinkle love effect


        btnLikes2.setOnTouchListener(new View.OnTouchListener() {

            GestureDetector gestureDetector = new GestureDetector(MainActivity.this, new GestureDetector.SimpleOnGestureListener() {

                @Override
                public void onLongPress(MotionEvent e) {

                    if (btnLikes2.getBackground().getConstantState() == getResources().getDrawable(R.drawable.icon_liked).getConstantState()) {
                        // show disslike animation
                        lottieHold2.setVisibility(View.VISIBLE);
                        totalLike2.setText(String.valueOf(Integer.parseInt(totalLike2.getText().toString()) - 1));
                        btnLikes2.setBackground(getResources().getDrawable(R.drawable.ic_love));
                        Snackbar.make(findViewById(android.R.id.content), "You unliked this post", Snackbar.LENGTH_SHORT).show();
                    } else {
                        // shoow like animation
                        lottieHold2.setVisibility(View.VISIBLE);
                        // change background button
                        btnLikes2.setBackground(getResources().getDrawable(R.drawable.icon_liked));
                        totalLike2.setText(String.valueOf(Integer.parseInt(totalLike2.getText().toString()) + 1));
                        Snackbar.make(findViewById(android.R.id.content), "liked", Snackbar.LENGTH_SHORT).show();
                    }


                    Snackbar.make(findViewById(android.R.id.content), "Hold to save", Snackbar.LENGTH_SHORT).show();
                    super.onLongPress(e);
                }
            });

                @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                gestureDetector.onTouchEvent(motionEvent);
                return true;
            }
        });

        // implement click to like and disslike post 2
        btnLikes2.setOnClickListener(view -> {
            if (btnLikes2.getBackground().getConstantState() == getResources().getDrawable(R.drawable.icon_liked).getConstantState()) {
                // show disslike animation
                disslikeAnimation2();
                totalLike.setText(String.valueOf(Integer.parseInt(totalLike2.getText().toString()) - 1));
                btnLikes2.setBackground(getResources().getDrawable(R.drawable.ic_love));
                Snackbar.make(view, "You unliked this post", Snackbar.LENGTH_SHORT).show();


            } else {
                // show like animation
                likeAnimation2();

                // change background button
                btnLikes2.setBackground(getResources().getDrawable(R.drawable.icon_liked));
                totalLike2.setText(String.valueOf(Integer.parseInt(totalLike2.getText().toString()) + 1));
                Snackbar.make(view, "liked", Snackbar.LENGTH_SHORT).show();
            }
        });




        


    }


    @Override
    public void onClick(View view) {



    }

    // show like animation
    private  void likeAnimation(){
        animationView.setVisibility(View.VISIBLE);
        animationView.playAnimation();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                animationView.setVisibility(View.GONE);
                animationView.cancelAnimation();
            }
        }, 1000);
    }

    // show disslike animation
    private void disslikeAnimation() {
        disslike_animation.setVisibility(View.VISIBLE);
        disslike_animation.playAnimation();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                disslike_animation.cancelAnimation();
                disslike_animation.setVisibility(View.GONE);
            }
        }, 1500);
    }  // show like animation
    private  void likeAnimation2(){
        animationView2.setVisibility(View.VISIBLE);
        animationView2.playAnimation();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                animationView2.setVisibility(View.GONE);
                animationView2.cancelAnimation();
            }
        }, 1000);
    }

    // show disslike animation
    private void disslikeAnimation2() {
        disslike_animation2.setVisibility(View.VISIBLE);
        disslike_animation2.playAnimation();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                disslike_animation2.cancelAnimation();
                disslike_animation2.setVisibility(View.GONE);
            }
        }, 1500);
    }


    @Override
    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        return false;
    }
}