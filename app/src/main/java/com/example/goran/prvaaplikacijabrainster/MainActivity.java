package com.example.goran.prvaaplikacijabrainster;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.guest)TextView guest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);



       String underline = "<u>Guest</u>";

                guest.setText(Html.fromHtml("Continue as a "+underline));




    }

    @OnClick(R.id.btnlogin)
    public void Login(View view) {

        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
        startActivity(intent);

    }

    @OnClick(R.id.guest)
    public void Guest(View view) {


        Intent intent1 = new Intent(MainActivity.this, Main3Activity.class);
        User guestuser = new User("", "", "Guest", 'M');
        intent1.putExtra("Guest", guestuser);
        startActivity(intent1);


    }
}
