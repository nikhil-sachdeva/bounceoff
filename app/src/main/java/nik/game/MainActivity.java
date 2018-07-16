package nik.game;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView rules;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button but=(Button)findViewById(R.id.but);
        rules = (TextView)findViewById(R.id.rules);
        rules.setText("Rules: \n1. Don't let the ball touch down for more than 5 times. \n2. Play for as long as you can!");


    }
    public void Goto(View view){

        EditText name = (EditText)findViewById(R.id.name);
        String nm=name.getText().toString();
        if(nm.isEmpty()){
            Toast.makeText(this,"Please enter name",Toast.LENGTH_LONG).show();
        }
        else {
            Intent in = new Intent(MainActivity.this, Game.class);
            in.putExtra("name", nm);
            startActivity(in);
        }

    }

}
