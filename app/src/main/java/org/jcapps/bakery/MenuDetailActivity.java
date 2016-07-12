package org.jcapps.bakery;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * A simple {@link Fragment} subclass.
 */
public class MenuDetailActivity extends AppCompatActivity {
    TextView mTxtName;
    TextView mTxtCategory;
    TextView mTxtDescription;
    TextView mTxtInStock;
    TextView mTxtPrice;
    ImageView mImgItem;
    Integer availabilityVal;
    String imagename;
    String moneyString;

//    public MenuDetailActivity() {
        // Required empty public constructor
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_detail);

        // Inflate the layout for this fragment
        mTxtName = (TextView) findViewById(R.id.txt_name);
        mTxtCategory = (TextView) findViewById(R.id.txt_category);
        mImgItem = (ImageView) findViewById(R.id.img_detail);
        mTxtDescription = (TextView) findViewById(R.id.txt_description);
        mTxtInStock = (TextView) findViewById(R.id.txt_instock);
        mTxtPrice = (TextView) findViewById(R.id.txt_price);

        Intent listIntent = getIntent();
        String name = listIntent.getStringExtra("NAME");
        String category = listIntent.getStringExtra("CATEGORY");
        String picture_resource_id = listIntent.getStringExtra("PICTURE_RESOURCE_ID");
        String description = listIntent.getStringExtra("DESCRIPTION");

        int in_stock = listIntent.getIntExtra("IN_STOCK",0);
        //int available = Integer.parseInt(in_stock);
        boolean instock = (in_stock != 0);

        String price = listIntent.getStringExtra("PRICE");
        double pricedouble = Double.parseDouble(price);
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        String moneyString = formatter.format(pricedouble);


        mTxtName.setText(name);
        mTxtCategory.setText(category);

        mTxtDescription.setText(description);
        mTxtPrice.setText(moneyString);
        if (instock) {
            mTxtInStock.setText("In Stock");
            mTxtInStock.setTextColor(Color.GREEN);
//            mTxtInStock.setTextColor(Color.parseColor("#4CAF50"));
        } else {
            mTxtInStock.setText("Out of Stock");
            mTxtInStock.setTextColor(Color.RED);
        }

//        ImageView mImgItem = new ImageView(this);


            switch (name) {
                case "Fruit Cake":
                    Picasso.with(this).load(R.drawable.fruit_cake).resize(1000,1000).centerCrop().into(mImgItem);
                    break;
                case
                    "Green Tea Mousse Cake":
                    Picasso.with(this).load(R.drawable.green_tea_mousse_cake).resize(1000,1000).centerCrop().into(mImgItem);
                    break;
                case
                    "Honey Dew Roll Cake":
                    Picasso.with(this).load(R.drawable.honey_dew_roll).resize(1000,1000).centerCrop().into(mImgItem);
                    break;
                case
                    "Sponge Cake":
                    Picasso.with(this).load(R.drawable.sponge_cake).resize(1000,1000).centerCrop().into(mImgItem);
                    break;
                case
                    "Curry Beef Pastry Puff":
                    Picasso.with(this).load(R.drawable.curry_beef_pastry_puff).resize(1000,1000).centerCrop().into(mImgItem);
                    break;
                case
                    "Hot Dog Bun":
                    Picasso.with(this).load(R.drawable.hot_dog_bun).resize(1000,1000).centerCrop().into(mImgItem);
                    break;
                case
                    "Hot Dog Scallion Bun":
                    Picasso.with(this).load(R.drawable.hot_dog_scallion_bun).resize(1000,1000).centerCrop().into(mImgItem);
                    break;
                case
                    "Roast Pork Bun":
                    Picasso.with(this).load(R.drawable.roast_pork_bun).resize(1000,1000).centerCrop().into(mImgItem);
                    break;
                case
                    "Roast Pork Pastry Puff":
                    Picasso.with(this).load(R.drawable.roast_pork_pastry_puff).resize(1000,1000).centerCrop().into(mImgItem);
                    break;
                case
                    "Coconut Cream Bun":
                    Picasso.with(this).load(R.drawable.coconut_cream_bun).resize(1000,1000).centerCrop().into(mImgItem);
                    break;
                case
                    "Custard Bun":
                    Picasso.with(this).load(R.drawable.custard_bun).resize(1000,1000).centerCrop().into(mImgItem);
                    break;
                case
                    "Egg Tart":
                    Picasso.with(this).load(R.drawable.egg_tart).resize(1000,1000).centerCrop().into(mImgItem);
                    break;
                case
                    "Portuguese Egg Custard Tart":
                    Picasso.with(this).load(R.drawable.portuguese_egg_custard_tart).resize(1000,1000).centerCrop().into(mImgItem);
                    break;
                case
                    "Pineapple Bun":
                    Picasso.with(this).load(R.drawable.pineapple_bun).resize(1000,1000).centerCrop().into(mImgItem);
                    break;
                case
                    "Red Lotus Pastry Puff":
                    Picasso.with(this).load(R.drawable.red_lotus_pastry_puff).resize(1000,1000).centerCrop().into(mImgItem);
                    break;
                case
                    "Sesame Ball":
                    Picasso.with(this).load(R.drawable.sesame_ball).resize(1000,1000).centerCrop().into(mImgItem);
                    break;
                case
                    "Wife Cake / Melon Cake":
                    Picasso.with(this).load(R.drawable.wife_cake).resize(1000,1000).centerCrop().into(mImgItem);
                    break;
                default:
                    break;
            }
    }

}