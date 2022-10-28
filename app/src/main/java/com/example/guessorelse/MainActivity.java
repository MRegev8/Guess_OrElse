package com.example.guessorelse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView number;
    TextView counter;
    TextView Weasy,Wnormal, Whard;
    Button guess;
    Button p1;
    Button m1;
    Button p10;
    Button m10;
    int count=0;
    int num=0;
    int usernum;
    int max_easy=10;
    int max_normal=50;
    int max_difficult=100;
    int max=100;
    SharedPreferences sp;
    int count1;
    int count2;
    int count3;
    boolean isEsay=false;
    boolean isNormal=false;
    boolean isHard=false;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Weasy=findViewById(R.id.counter1);
        Wnormal=findViewById(R.id.counter2);
        Whard=findViewById(R.id.counter3);
        sp = getSharedPreferences("id_details", 0);
        count1 = sp.getInt("counter1", 0);
        count2 = sp.getInt("counter2", 0);
        count3 = sp.getInt("counter3", 0);
        Weasy.setText("EASY wins: "+String.valueOf(count1));
        Wnormal.setText("NORMAL wins: "+String.valueOf(count2));
        Whard.setText("HARD wins: "+ String.valueOf(count3));
        number=findViewById(R.id.guessView);
        counter=findViewById(R.id.scoreView);
        guess=findViewById(R.id.power);
        p1=findViewById(R.id.plus1);
        m1=findViewById(R.id.minus1);
        p10=findViewById(R.id.plus10);
        m10=findViewById(R.id.minus10);
        p1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(usernum<max)usernum++;
                number.setText(String.valueOf(usernum));
            }
        });
        m1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(usernum>0) usernum--;
                number.setText(String.valueOf(usernum));
            }
        });
        p10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(usernum<=max-10)usernum+=10;
                number.setText(String.valueOf(usernum));
            }
        });
        m10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(usernum>=10) usernum-=10;
                number.setText(String.valueOf(usernum));
            }
        });
        guess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(num==0)Toast.makeText(MainActivity.this, "first, you need to pick a difficulty", Toast.LENGTH_SHORT).show();
                if(usernum>num&&num!=0)
                {
                    Toast.makeText(MainActivity.this, "guess lower", Toast.LENGTH_SHORT).show();
                    count++;
                    counter.setText(String.valueOf(count));
                }
                if (usernum<num)
                {
                    Toast.makeText(MainActivity.this, "guess higher", Toast.LENGTH_SHORT).show();
                    count++;
                    counter.setText(String.valueOf(count));
                }
                if (usernum==num&&num!=0)
                {
                    if(isEsay)
                    {
                        count1++;
                    }
                    if(isNormal)
                    {
                        count2++;
                    }
                    if(isHard)
                    {
                        count3++;
                    }
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putInt("counter1", count1);
                    editor.putInt("counter2", count2);
                    editor.putInt("counter3", count3);
                    editor.commit();
                    count++;
                    counter.setText(String.valueOf(count));
                    Intent intent=new Intent(MainActivity.this,win.class);
                    String nownum= String.valueOf(num);
                    String nowcount= String.valueOf(count);
                    intent.putExtra("count",nowcount);
                    intent.putExtra("num",nownum);
                    startActivity(intent);
                    Weasy.setText("EASY wins: "+String.valueOf(count1));
                    Wnormal.setText("NORMAL wins: "+String.valueOf(count2));
                    Whard.setText("HARD wins: "+ String.valueOf(count3));
                    counter.setText("0");
                    count=0;
                    num=0;
                    isEsay=false;
                    isNormal=false;
                    isHard=false;
                    max=max_normal;
                }
                }
            });
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        super.onOptionsItemSelected(item);
        int id = item.getItemId();
        if (id == R.id.to10) {
            num = (int) Math.floor(Math.random() * (max_easy) + 1);
            max=max_easy;
            isEsay=true;
            isNormal=false;
            isHard=false;
            if(usernum>max_easy)usernum=max_easy;
            number.setText(String.valueOf(usernum));
            count=0;
            counter.setText(String.valueOf(count));
        }
        if (id == R.id.to50) {
            num = (int) Math.floor(Math.random() * (max_normal) + 1);
            max=max_normal;
            isEsay=false;
            isNormal=true;
            isHard=false;
            if(usernum>max_normal)usernum=max_normal;
            number.setText(String.valueOf(usernum));
            count=0;
            counter.setText(String.valueOf(count));
        }
        if (id == R.id.to100) {
            num = (int) Math.floor(Math.random() * (max_difficult) + 1);
            max=max_difficult;
            isEsay=false;
            isNormal=false;
            isHard=true;
            count=0;
            counter.setText(String.valueOf(count));
        }
        return true;
    }
}