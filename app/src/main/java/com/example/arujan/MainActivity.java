package com.example.arujan;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListAdapter listAdapter;
    ArrayList<ListData> dataArrayList = new ArrayList<>();
    ListData listData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int[] imageList = {
                R.drawable.ccharm,
                R.drawable.java,
                R.drawable.python,
                R.drawable.sql,
                R.drawable.html,
                R.drawable.kotlin,
                R.drawable.jquery,
                R.drawable.rust,
                R.drawable.php,
                R.drawable.cplus,
                R.drawable.andorid
        };

        int[] ingredientList = {
                R.string.csharpIngredients,
                R.string.javaIngredients,
                R.string.pythonIngredients,
                R.string.sqlIngredients,
                R.string.htmlcssIngredients,
                R.string.kotlinIngredients,
                R.string.jqueryIngredients,
                R.string.rustIngredients,
                R.string.phpIngredients,
                R.string.cppIngredients,
                R.string.androidIngredients
        };

        int[] descList = {
                R.string.csharpDesc,
                R.string.javaDesc,
                R.string.pythonDesc,
                R.string.sqlDesc,
                R.string.htmlcssDesc,
                R.string.kotlinDesc,
                R.string.jqueryDesc,
                R.string.rustDesc,
                R.string.phpDesc,
                R.string.cppDesc,
                R.string.androidDesc
        };

        String[] nameList = {
                "C#",
                "Java",
                "Python",
                "SQL",
                "HTML/CSS",
                "Kotlin",
                "jQuery",
                "Rust",
                "PHP",
                "C++",
                "Android"
        };

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        for (int i = 0; i < imageList.length; i++) {
            listData = new ListData(nameList[i], "", ingredientList[i], descList[i], imageList[i]);
            dataArrayList.add(listData);
        }

        listAdapter = new ListAdapter(MainActivity.this, dataArrayList);
        recyclerView.setAdapter(listAdapter);

        listAdapter.setOnItemClickListener(new ListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(MainActivity.this, DetailedActivity.class);
                intent.putExtra("name", nameList[position]);
                intent.putExtra("ingredients", ingredientList[position]);
                intent.putExtra("desc", descList[position]);
                intent.putExtra("image", imageList[position]);
                startActivity(intent);
            }
        });

        EditText searchEditText = findViewById(R.id.searchEditText);
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                listAdapter.filter(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }
}