package com.example.goran.prvaaplikacijabrainster;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Main3Activity extends AppCompatActivity {

    @BindView(R.id.spiner)
    Spinner userspiner;
    @BindView(R.id.slika)
    ImageView image;
    @BindView(R.id.namelastname)
    TextView imeprezime;
    @BindView(R.id.tvnet)
    TextView networkstate;
    @BindView(R.id.male2)
    RadioButton masko;
    @BindView(R.id.Female2)
    RadioButton zensko;
    @BindView(R.id.rg1)
    RadioGroup gruparb;

    ArrayList<User> users;
    ArrayAdapter<User> mojadapter;
    User guest;
    User user;
    User selektiranUser;
    User editiraj;
    int kluc = 1000;
    int kluc1 = 1000;
    int kluc2 = 1000;
    char pol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);


        ButterKnife.bind(this);

        users = new ArrayList<>();


        Intent intent1 = getIntent();
        if (intent1.hasExtra("User")) {
            user = (User) intent1.getSerializableExtra("User");
            users.add(user);
        }

        Intent intent = getIntent();
        if (intent.hasExtra("Guest")) {
            guest = (User) intent.getExtras().getSerializable("Guest");
            users.add(guest);
        }


        mojadapter = new ArrayAdapter<User>(this, android.R.layout.simple_list_item_1, users);
        userspiner.setAdapter(mojadapter);
        mojadapter.notifyDataSetChanged();
        userspiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selektiranUser = mojadapter.getItem(position);
                pol = selektiranUser.getGender();
                String imePrezime = selektiranUser.getName() + " " + selektiranUser.getLastname();
                imeprezime.setText(imePrezime);

                if (pol == 'F') {
                    zensko.setChecked(true);
                    image.setImageDrawable(getResources().getDrawable(R.drawable.mujer));
                } else if (pol == 'M'){
                    masko.setChecked(true);
                    image.setImageDrawable(getResources().getDrawable(R.drawable.man));
                }else{}

                gruparb.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        int checked = group.getCheckedRadioButtonId();

                        switch (checked) {
                            case R.id.male2:
                                pol = 'M';
                                selektiranUser.setGender('M');
                                image.setImageDrawable(getResources().getDrawable(R.drawable.man));
                                break;

                            case R.id.Female2:
                                pol = 'F';
                                selektiranUser.setGender('F');
                                image.setImageDrawable(getResources().getDrawable(R.drawable.mujer));
                                break;
                        }
                    }
                });

                editiraj = mojadapter.getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


    }

    @OnClick(R.id.edituser)
    public void EditUSer(View view) {
        int editing = 1;
        Intent edit = new Intent(this, Main2Activity.class);
        edit.putExtra("EditNext", editing);
        edit.putExtra("EditUser", (Serializable) editiraj);
        startActivityForResult(edit, kluc1);

    }

    @OnClick(R.id.adduser)
    public void AddUSer(View view) {

        int add = 1;
        Intent intent = new Intent(this, Main2Activity.class);
        intent.putExtra("Add", add);
        startActivityForResult(intent, kluc);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == kluc || resultCode == RESULT_OK && requestCode == kluc1 || resultCode == RESULT_OK && requestCode == kluc2) {
            if (data.hasExtra("AddUser")) {
                User user = (User) data.getExtras().getSerializable("AddUser");
                users.add(user);


            } else if (data.hasExtra("EditUser")) {
                User user = (User) data.getExtras().getSerializable("EditUser");
                selektiranUser.setGender(user.gender);
                selektiranUser.setName(user.getName());
                selektiranUser.setLastname(user.getLastname());
                selektiranUser.setUsername(user.getUsername());
                mojadapter.notifyDataSetChanged();
                String imePrezime = selektiranUser.getName() + " " + selektiranUser.getLastname();
                imeprezime.setText(imePrezime);
                pol = selektiranUser.getGender();

                  if (pol == 'F') {
                    zensko.setChecked(true);
                    image.setImageDrawable(getResources().getDrawable(R.drawable.mujer));
                } else if (pol == 'M'){
                    masko.setChecked(true);
                    image.setImageDrawable(getResources().getDrawable(R.drawable.man));
                  }else{}

            } else if (data.hasExtra("Net")) {
                networkstate.setText(data.getExtras().getString("Net"));


            } else {

                Toast.makeText(this, "No new users", Toast.LENGTH_SHORT).show();

            }

        }
    }

    @OnClick(R.id.checkconnection)
    public void Konekcija(View view) {

        Intent intentnet = new Intent(Main3Activity.this, Main4Activity.class);
        startActivityForResult(intentnet, kluc2);


    }

}
