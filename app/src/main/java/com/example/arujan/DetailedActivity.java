package com.example.arujan;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.arujan.databinding.ActivityDetailedBinding;

public class DetailedActivity extends AppCompatActivity {
    ActivityDetailedBinding binding;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = this.getIntent();
        if (intent != null) {
            String name = intent.getStringExtra("name");
            int ingredients = intent.getIntExtra("ingredients", 0);
            int desc = intent.getIntExtra("desc", 0);
            int image = intent.getIntExtra("image", R.drawable.ic_launcher_foreground);

            binding.detailName.setText(name);
            binding.detailName.setText(getResources().getString(ingredients));
            binding.detailDesc.setText(getResources().getString(desc));
            binding.detailImage.setImageResource(image);

            // Извлекаем массив ключевых особенностей для соответствующего языка
            String[] keyFeatures;
            if (name.equals(getString(R.string.csharpIngredients))) {
                keyFeatures = getResources().getStringArray(R.array.key_features_csharp);
            } else if (name.equals(getString(R.string.javaIngredients))) {
                keyFeatures = getResources().getStringArray(R.array.key_features_java);
            } else if (name.equals(getString(R.string.pythonIngredients))) {
                keyFeatures = getResources().getStringArray(R.array.key_features_python);
            } else if (name.equals(getString(R.string.sqlIngredients))) {
                keyFeatures = getResources().getStringArray(R.array.key_features_sql);
            } else if (name.equals(getString(R.string.htmlcssIngredients))) {
                keyFeatures = getResources().getStringArray(R.array.key_features_htmlcss);
            } else if (name.equals(getString(R.string.kotlinIngredients))) {
                keyFeatures = getResources().getStringArray(R.array.key_features_kotlin);
            } else if (name.equals(getString(R.string.jqueryIngredients))) {
                keyFeatures = getResources().getStringArray(R.array.key_features_jquery);
            } else if (name.equals(getString(R.string.rustIngredients))) {
                keyFeatures = getResources().getStringArray(R.array.key_features_rust);
            } else if (name.equals(getString(R.string.phpIngredients))) {
                keyFeatures = getResources().getStringArray(R.array.key_features_php);
            } else if (name.equals(getString(R.string.cppIngredients))) {
                keyFeatures = getResources().getStringArray(R.array.key_features_cpp);
            } else if (name.equals(getString(R.string.androidIngredients))) {
                keyFeatures = getResources().getStringArray(R.array.key_features_android);
            } else {
                keyFeatures = new String[]{};
            }

            // Отображаем ключевые особенности
            StringBuilder keyFeaturesText = new StringBuilder("\n");
            for (String feature : keyFeatures) {
                keyFeaturesText.append("- ").append(feature).append("\n");
            }
            binding.detailTime.setText(keyFeaturesText.toString());

            ImageView backButton = findViewById(R.id.backButton);
            backButton.setOnClickListener(v -> onBackPressed());
        }
    }
}