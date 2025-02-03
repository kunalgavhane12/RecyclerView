package com.example.recyclerview;

import android.app.Dialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerContactAdapter adapter;
    RecyclerView recyclerView;
    FloatingActionButton btnOpenDialog;
    ArrayList<ContactModel> arrContact = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.recyclerContact);
        btnOpenDialog = findViewById(R.id.btnOpenDialog);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        ContactModel model = new ContactModel(R.drawable.ic_launcher_background, "A", "9888888888");
//        arrContact.add(model);

        arrContact.add(new ContactModel(R.drawable.ic_baseline_account_circle_24, "A", "9888888880"));
        arrContact.add(new ContactModel(R.drawable.ic_baseline_account_circle_24, "B", "9888888881"));
        arrContact.add(new ContactModel(R.drawable.ic_baseline_account_circle_24, "C", "9888888882"));
        arrContact.add(new ContactModel(R.drawable.ic_baseline_account_circle_24, "D", "9888888883"));
        arrContact.add(new ContactModel(R.drawable.ic_baseline_account_circle_24, "E", "9888888884"));
        arrContact.add(new ContactModel(R.drawable.ic_baseline_account_circle_24, "F", "9888888885"));
        arrContact.add(new ContactModel(R.drawable.ic_baseline_account_circle_24, "G", "9888888886"));
        arrContact.add(new ContactModel(R.drawable.ic_baseline_account_circle_24, "H", "9888888887"));
        arrContact.add(new ContactModel(R.drawable.ic_baseline_account_circle_24, "I", "9888888899"));
        arrContact.add(new ContactModel(R.drawable.ic_baseline_account_circle_24, "J", "9888888891"));
        arrContact.add(new ContactModel(R.drawable.ic_baseline_account_circle_24, "K", "9888888892"));
        arrContact.add(new ContactModel(R.drawable.ic_baseline_account_circle_24, "L", "9888888893"));
        arrContact.add(new ContactModel(R.drawable.ic_baseline_account_circle_24, "M", "9888888894"));
        arrContact.add(new ContactModel(R.drawable.ic_baseline_account_circle_24, "N", "9888888895"));
        arrContact.add(new ContactModel(R.drawable.ic_baseline_account_circle_24, "O", "9888888896"));

        adapter = new RecyclerContactAdapter(this, arrContact);
        recyclerView.setAdapter(adapter);

        btnOpenDialog.setOnClickListener(v -> {
            Dialog dialog = new Dialog(MainActivity.this);
            dialog.setContentView(R.layout.add_update_lay);
            EditText edtName = dialog.findViewById(R.id.edtName);
            EditText edtNumber = dialog.findViewById(R.id.edtNumber);
            Button btnAction = dialog.findViewById(R.id.btnAction);

            btnAction.setOnClickListener(v1 -> {
                String name = "" , number = "";
                if(!edtName.getText().toString().isEmpty()) {
                    name = edtName.getText().toString();
                } else {
                    Toast.makeText(MainActivity.this, "Please Enter Contact Name!", Toast.LENGTH_SHORT).show();
                }
                if(!edtNumber.getText().toString().isEmpty()){
                    number = edtNumber.getText().toString();
                } else {
                    Toast.makeText(MainActivity.this, "Please Enter Number!", Toast.LENGTH_SHORT).show();
                }
                arrContact.add(new ContactModel(R.drawable.ic_baseline_account_circle_24, name, number));
                adapter.notifyItemInserted(arrContact.size()-1);
                recyclerView.scrollToPosition(arrContact.size()-1);
                dialog.dismiss();
            });
            dialog.show();

        });
    }
}