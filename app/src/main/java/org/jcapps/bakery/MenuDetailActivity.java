package org.jcapps.bakery;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class MenuDetailActivity extends AppCompatActivity {
    TextView mTxtName;
    TextView mTxtDescription;
    TextView mTxtPrice;
    TextView mTxtCategory;


//    public MenuDetailActivity() {
        // Required empty public constructor
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_detail);

        // Inflate the layout for this fragment
        mTxtName = (TextView) findViewById(R.id.txt_name);
        mTxtDescription = (TextView) findViewById(R.id.txt_description);
        mTxtPrice = (TextView) findViewById(R.id.txt_price);
        mTxtCategory = (TextView) findViewById(R.id.txt_category);

        Intent listIntent = getIntent();
        String name = listIntent.getStringExtra("NAME");
        String description = listIntent.getStringExtra("DESCRIPTION");
        String price = listIntent.getStringExtra("PRICE");
        String type = listIntent.getStringExtra("CATEGORY");

        mTxtName.setText(name);
        mTxtDescription.setText(description);
        mTxtPrice.setText(price);
        mTxtCategory.setText(category);

    }

}