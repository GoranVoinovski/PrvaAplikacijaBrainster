package com.example.goran.prvaaplikacijabrainster;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Main4Activity extends AppCompatActivity {

    @BindView(R.id.nettext)
    TextView network;

    MyReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        ButterKnife.bind(this);


    }

    @OnClick(R.id.checknet)
    public void ResiverRegister(View view){
        receiver = new MyReceiver();
        registerReceiver(receiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
}

    @OnClick(R.id.back)
    public void BackNet(View view) {

        Intent intentnetwork = new Intent();
        String networkState = network.getText().toString();
        intentnetwork.putExtra("Net", networkState);
        setResult(RESULT_OK, intentnetwork);
        finish();


    }


    @Override
    public void onBackPressed() {
        Intent intentnetwork = new Intent();
        String networkState = network.getText().toString();
        intentnetwork.putExtra("Net", networkState);
        setResult(RESULT_OK, intentnetwork);
        finish();
    }


    @Override
    protected void onPause() {

        if (receiver != null) {

            unregisterReceiver(receiver);
        }

        super.onPause();
    }
}
