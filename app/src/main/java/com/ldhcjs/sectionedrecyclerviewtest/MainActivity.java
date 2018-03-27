package com.ldhcjs.sectionedrecyclerviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Items> arrayList;
    RecyclerView list;
    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arrayList = new ArrayList<Items>();
        initData();

        list = (RecyclerView) findViewById(R.id.list);
        adapter = new CustomAdapter(MainActivity.this, arrayList);
        // 2 는 1열당 위치할 수 있는 아이템의 갯수
        GridLayoutManager manager = new GridLayoutManager(this, 3);
        list.setLayoutManager(manager);
        adapter.setLayoutManager(manager);
        list.setAdapter(adapter);


        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int rel, int abs) {
                Toast.makeText(MainActivity.this, "select : " + rel + ", " + abs, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onClick(View v) {
        arrayList.get(0).getContent().remove(2);
        adapter.notifyDataSetChanged();
    }

    public void initData() {
        Items items = new Items();
        items.setTitle("People1");
        ArrayList<String> tmp = new ArrayList<>();
        tmp.add("Thomas");
        tmp.add("Jenny");
        tmp.add("John");
        tmp.add("Keith");
        items.setContent(tmp);
        arrayList.add(items);

        items = new Items();
        items.setTitle("People2");
        tmp = new ArrayList<>();
        tmp.add("Tom");
        tmp.add("Catherine");
        tmp.add("Minsu");
        tmp.add("Tony");
        tmp.add("Gini");
        tmp.add("Zepri");
        tmp.add("Jocobichi");
        items.setContent(tmp);
        arrayList.add(items);

        items = new Items();
        items.setTitle("People3");
        tmp = new ArrayList<>();
        tmp.add("Jung");
        tmp.add("Wong");
        tmp.add("Yeti");
        tmp.add("Pocopoco");
        tmp.add("Edi");
        items.setContent(tmp);
        arrayList.add(items);
    }
}
