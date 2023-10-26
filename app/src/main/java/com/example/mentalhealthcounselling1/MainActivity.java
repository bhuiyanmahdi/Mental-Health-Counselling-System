package com.example.mentalhealthcounselling1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<MainRV_item> mainRV_items;
    FloatingActionButton button;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.mainRV);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        mainRV_items = new ArrayList<MainRV_item>();
        mainRV_items.add(new MainRV_item(R.drawable.depression2, "Anxiety"));
        mainRV_items.add(new MainRV_item(R.drawable.depression, "Depression"));
        mainRV_items.add(new MainRV_item(R.drawable.anxiety, "Panic Attack"));
        mainRV_items.add(new MainRV_item(R.drawable.meditation, "Meditation"));
        mainRV_items.add(new MainRV_item(R.drawable.selfcare, "Selfcare"));
        MainRV_adapter mainRV_adapter = new MainRV_adapter(mainRV_items, this);
        recyclerView.setAdapter(mainRV_adapter);



        button = findViewById(R.id.idChatbot);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivity();
            }
        });

    }
    public void openNewActivity(){
        Intent intent = new Intent(this, ChatbotActivity.class);
        startActivity(intent);

    }

}