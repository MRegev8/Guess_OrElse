package com.example.guessorelse;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class win extends AppCompatActivity {

    TextView number;
    TextView numoftries;
    Button rest;
    Button exit;
    int winTimes=0;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);
        Intent intent=getIntent();
        String count=intent.getExtras().getString("count");
        String num=intent.getExtras().getString("num");
        numoftries=findViewById(R.id.score);
        rest=findViewById(R.id.restart);
        exit=findViewById(R.id.exit);
        number=findViewById(R.id.numw);
        numoftries.setText(count+" tries");
        number.setText(num);

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert=new AlertDialog.Builder(win.this);
                alert.setTitle("EXIT");
                alert.setMessage("ARE YOU SURE YOU WANT TO EXIT?");
                alert.setPositiveButton("YES",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finishAffinity();
                        System.exit(0);
                    }
                });
                alert.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(win.this,"I want to stay", Toast.LENGTH_LONG).show();
                    }
                });
                alert.create().show();
            }
        });
        rest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert=new AlertDialog.Builder(win.this);
                alert.setTitle("EXIT");
                alert.setMessage("ARE YOU SURE YOU WANT TO RESTART?");
                alert.setPositiveButton("YES",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {

                        finish();
                    }
                });
                alert.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(win.this,"I want to stay", Toast.LENGTH_LONG).show();
                    }
                });
                alert.create().show();
            }
        });
    }
}