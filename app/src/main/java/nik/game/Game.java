package nik.game;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.app.Notification;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;



public class Game extends AppCompatActivity {
    private float x=100,y=200,vx=3,vy=3,r=30,sc=0,sec=0;
    private float tp=970,bp=tp+50,lp=400,rp=lp+150;
    private int width=900, height=1020;
    Button rt,lt;
    Paint paint=new Paint();
    Paint paintline=new Paint();
    ImageView img;
    TextView score;
    TextView timetext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        TextView tv = (TextView) findViewById(R.id.tv);
        Bitmap b= Bitmap.createBitmap(width,height,Bitmap.Config.ARGB_4444);
        final Canvas c=new Canvas(b);
        paint.setColor(Color.YELLOW);
        paintline.setColor(Color.GREEN);
        score=(TextView)findViewById(R.id.score);
        rt=(Button)findViewById(R.id.rt);
        lt=(Button)findViewById(R.id.lt);
        timetext=(TextView)findViewById(R.id.timetext);
        timetext.setText("Time 0s");
        Intent in = getIntent();
        final String name = in.getStringExtra("name");
        tv.setText("Hi " + name);
        Timer myTimer=new Timer();

        lt.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if(rp<150&&lp<0){
                            rp=150;
                            lp=0;
                        }
                        lp = lp - 15;
                        rp = rp - 15;
                        return true;
                }

                return false;}
        });

        final Intent inend = new Intent(Game.this,EndGame.class);
        inend.putExtra("name",""+name);

        img=(ImageView)findViewById(R.id.img);
        img.setImageBitmap(b);
        final Timer timer=new Timer();
        final Timer time =new Timer();


        timer.schedule(
                new TimerTask(){
                    @Override
                    public void run() {
                        runOnUiThread(
                                new Runnable() {
                                    @Override
                                    public void run() {
                                        c.drawCircle(x, y, r, paint);
                                        x=x+vx;
                                        y=y+vy;
                                        if(x+r>=width)vx=-vx;
                                        if(x-r<=0)vx=-vx;
                                        if(y+r+50>=height)
                                        { if(x-r>lp&&x+r<rp) {
                                            vy = -vy;

                                        }
                                        else{
                                            x=200;
                                            y=300;
                                            vx=-3;
                                            vy=3;
                                            sc += 1;
                                            String scs = Float.toString(sc);
                                            score.setText("Score: " + scs);
                                            if(sc>5){
                                                timer.cancel();


                                            }

                                        }
                                        }
                                        if(y-r<=0)vy=-vy;
                                        rt.setOnTouchListener(new View.OnTouchListener() {
                                            @Override
                                            public boolean onTouch(View view, MotionEvent motionEvent) {
                                                switch (motionEvent.getAction()) {

                                                    case MotionEvent.ACTION_DOWN:
                                                        if(rp>900&&lp>750){
                                                            rp=900;
                                                            lp=750;
                                                        }
                                                        lp = lp + 15;
                                                        rp = rp + 15;
                                                        return true;
                                                }

                                                return false;}
                                        });
                                        c.drawColor(Color.BLACK);
                                        c.drawCircle(x, y, r, paint);
                                        c.drawRect(lp,tp,rp,bp,paint);
                                        c.drawRect(0,965,900,970,paintline);
                                        img.invalidate();

                                    }
                                }
                        );
                    }
                },0,10 );
        time.schedule(
                new TimerTask(){
                    @Override
                    public void run() {
                        runOnUiThread(
                                new Runnable() {
                                    @Override
                                    public void run() {
                                        sec=sec+1;
                                        String secs=Float.toString(sec);
                                        timetext.setText("Time "+secs+"s");
                                        if(sc>5){
                                            time.cancel();
                                            inend.putExtra("seconds",secs);
                                            startActivity(inend);
                                        }


                                    }
                                }
                        );
                    }
                },0,1000 );

    }



    }







