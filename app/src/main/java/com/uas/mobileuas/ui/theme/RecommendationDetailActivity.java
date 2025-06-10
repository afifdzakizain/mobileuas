package com.uas.mobileuas;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.uas.mobileuas.R;

public class RecommendationDetailActivity extends AppCompatActivity {

    ImageView img;
    TextView tvTitle, tvDesc, tvPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommendation_detail);

        img = findViewById(R.id.imgDetail);
        tvTitle = findViewById(R.id.tvDetailTitle);
        tvDesc = findViewById(R.id.tvDetailDesc);
        tvPrice = findViewById(R.id.tvDetailPrice);

        img.setImageResource(getIntent().getIntExtra("img", 0));
        tvTitle.setText(getIntent().getStringExtra("title"));
        tvDesc.setText(getIntent().getStringExtra("desc"));
        tvPrice.setText(getIntent().getStringExtra("price"));
    }
}
