package com.example.goran.prvaaplikacijabrainster;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Main2Activity extends AppCompatActivity {

    @BindView(R.id.edttxtname)
    EditText name;
    @BindView(R.id.edttxtLastname)
    EditText Lastname;
    @BindView(R.id.edttxtUsername)
    EditText Username;
    @BindView(R.id.createUser)
    Button create;
    @BindView(R.id.male)
    RadioButton masko;
    @BindView(R.id.Female)
    RadioButton zensko;


    String ime;
    String prezime;
    String korisnickoIme;
    char gender = 'N';
    User newUser;
    User useredit;
    int add = 0;
    int edit = 0;
    String pol = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ButterKnife.bind(this);



        Intent intenadd = getIntent();
        if (intenadd.hasExtra("Add")) {
            add = intenadd.getExtras().getInt("Add");


        }

        Intent intentedit = getIntent();
        if (intentedit.hasExtra("EditUser")) {
            edit = intentedit.getIntExtra("EditNext", 1);
            useredit = (User) intentedit.getExtras().getSerializable("EditUser");
            name.setText(useredit.getName());
            Lastname.setText(useredit.getLastname());
            Username.setText(useredit.getUsername());
            gender = useredit.getGender();

            if (gender == 'M') {
                masko.setChecked(true);
                pol = "M";

            } else if (gender == 'F'){
                zensko.setChecked(true);
                pol = "F";

            }else {}

        }


    }

    @OnClick({R.id.male, R.id.Female})
    public void onRadioButtonClicked(RadioButton radioB) {

        boolean checked = radioB.isChecked();

        switch (radioB.getId()) {
            case R.id.male:
                if (checked) {
                    gender = 'M';
                    pol = "M";

                }
                break;
            case R.id.Female:
                if (checked) {
                    gender = 'F';
                    pol = "F";
                }
                break;
        }
    }


    @OnClick(R.id.createUser)
    public void NewUSer(View view) {

        if (add == 1) {
            Intent useradd = new Intent();
            ime = name.getText().toString();
            prezime = Lastname.getText().toString();
            korisnickoIme = Username.getText().toString();
            newUser = new User(ime, prezime, korisnickoIme, gender);
            if (ime.isEmpty() || prezime.isEmpty() || korisnickoIme.isEmpty() || pol.isEmpty()) {
                create.setError("Registration fields are not complete");
                Toast.makeText(this, "Registration fields are not complete", Toast.LENGTH_LONG).show();
            } else {
                useradd.putExtra("AddUser", (Serializable) newUser);
                setResult(RESULT_OK, useradd);
                finish();
            }


        } else if (edit == 1) {
            Intent useredt = new Intent();
            ime = name.getText().toString();
            prezime = Lastname.getText().toString();
            korisnickoIme = Username.getText().toString();
            newUser = new User(ime, prezime, korisnickoIme, gender);
            if (ime.isEmpty() || prezime.isEmpty() || korisnickoIme.isEmpty() || pol.isEmpty()) {
                create.setError("Registration fields are not complete");
                Toast.makeText(this, "Registration fields are not complete", Toast.LENGTH_LONG).show();
            } else {
                useredt.putExtra("EditUser", (Serializable) newUser);
                setResult(RESULT_OK, useredt);
                finish();
            }


        } else {
            ime = name.getText().toString();
            prezime = Lastname.getText().toString();
            korisnickoIme = Username.getText().toString();
            newUser = new User(ime, prezime, korisnickoIme, gender);
            if (ime.isEmpty() || prezime.isEmpty() || korisnickoIme.isEmpty() || pol.isEmpty()) {
                create.setError("Registration fields are not complete");
                Toast.makeText(this, "Registration fields are not complete", Toast.LENGTH_LONG).show();
            } else {
                Intent intent = new Intent(Main2Activity.this, Main3Activity.class);
                intent.putExtra("User", newUser);
                startActivity(intent);
            }


        }


    }


    @Override
    public void onBackPressed() {
        if (add == 1) {
            Intent useradd = new Intent();
            ime = name.getText().toString();
            prezime = Lastname.getText().toString();
            korisnickoIme = Username.getText().toString();
            newUser = new User(ime, prezime, korisnickoIme, gender);
            if (ime.isEmpty() || prezime.isEmpty() || korisnickoIme.isEmpty()) {
                create.setError("Registration fields are not complete");
                Toast.makeText(this, "Registration fields are not complete", Toast.LENGTH_LONG).show();
            } else {
                useradd.putExtra("AddUser", (Serializable) newUser);
                setResult(RESULT_OK, useradd);
                finish();
            }


        } else if (edit == 1) {
            Intent useredt = new Intent();
            ime = name.getText().toString();
            prezime = Lastname.getText().toString();
            korisnickoIme = Username.getText().toString();
            newUser = new User(ime, prezime, korisnickoIme, gender);
            if (ime.isEmpty() || prezime.isEmpty() || korisnickoIme.isEmpty()) {
                create.setError("Registration fields are not complete");
                Toast.makeText(this, "Registration fields are not complete", Toast.LENGTH_LONG).show();
            } else {
                useredt.putExtra("EditUser", (Serializable) newUser);
                setResult(RESULT_OK, useredt);
                finish();
            }

        } else {

            finish();
        }
    }

}