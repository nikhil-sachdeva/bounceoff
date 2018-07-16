package nik.game;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class EndGame extends AppCompatActivity {
    TextView Endmsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);
        Intent inend = getIntent();
        String name=inend.getStringExtra("name");
        String seconds = inend.getStringExtra("seconds");
        Endmsg=(TextView)findViewById(R.id.Endmsg);
        Endmsg.setText("Thanks for playing "+name+"!! \n You played for "+seconds+ "seconds");
    }

}
