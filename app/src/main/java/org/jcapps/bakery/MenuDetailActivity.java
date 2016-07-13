package org.jcapps.bakery;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MenuDetailActivity extends AppCompatActivity {
    private ListView mCartListView;
    private CursorAdapter mCursorAdapter;

    TextView mTxtName;
    TextView mTxtCategory;
    TextView mTxtDescription;
    TextView mTxtInStock;
    TextView mTxtPrice;
    ImageView mImgItem;
    Spinner mQuantity;
    Button mBtnCart;
    Integer availabilityVal;
    String imagename;
    String moneyString;

    Intent mCartIntent;
    Cursor cursor;
    AdapterView.OnItemClickListener mClickListener;

    static ArrayList<Pastry> cart = new ArrayList<>();

//    public MenuDetailActivity() {
        // Required empty public constructor
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_detail);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        // To display the logo in the toolbar.
//        getSupportActionBar().setLogo(R.drawable.logo3);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setIcon(R.drawable.logo3);

        //cart = ShoppingCart.getInstance();

        // Inflate the layout for this fragment
        mTxtName = (TextView) findViewById(R.id.txt_name);
        mTxtCategory = (TextView) findViewById(R.id.txt_category);
        mImgItem = (ImageView) findViewById(R.id.img_detail);
        mTxtDescription = (TextView) findViewById(R.id.txt_description);
        mTxtInStock = (TextView) findViewById(R.id.txt_instock);
        mTxtPrice = (TextView) findViewById(R.id.txt_price);
        mQuantity = (Spinner) findViewById(R.id.spin_quantity);


        Intent listIntent = getIntent();
        final String name = listIntent.getStringExtra("NAME");
        String category = listIntent.getStringExtra("CATEGORY");
        String picture_resource_id = listIntent.getStringExtra("PICTURE_RESOURCE_ID");
        String description = listIntent.getStringExtra("DESCRIPTION");

        int in_stock = listIntent.getIntExtra("IN_STOCK",0);
        //int available = Integer.parseInt(in_stock);
        final boolean instock = (in_stock != 0);

        final String price = listIntent.getStringExtra("PRICE");
        double pricedouble = Double.parseDouble(price);
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        String moneyString = formatter.format(pricedouble);


        mTxtName.setText(name);
        mTxtCategory.setText(category);

        mTxtDescription.setText(description);
        mTxtPrice.setText(moneyString);
        if (instock) {
            mTxtInStock.setText("In Stock");
//            mTxtInStock.setTextColor(Color.GREEN);
            mTxtInStock.setTextColor(Color.parseColor("#8BC34A"));
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

        //Here we declare our add to cart button
        Button mBtnCart = (Button) findViewById(R.id.btn_cart);
        assert mBtnCart != null;
        mBtnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (instock) {
                    // the name of the receiving activity is declared in the Intent Constructor
                    Intent mCartIntent = new Intent(MenuDetailActivity.this, CartActivity.class);
                    //mCartIntent.putStringArrayListExtra("quantity",cart.quantity);
                    int quantity = Integer.parseInt(String.valueOf(mQuantity.getSelectedItem()));
                    Pastry item = new Pastry(name, Double.valueOf(price), quantity);
                    if (item != null) {
                        cart.add(item);
                    }
                    Toast.makeText(getApplicationContext(),
                            "Added to Cart", Toast.LENGTH_SHORT).show();

                    mCartIntent.putExtra("cart", cart);

                    // cart.add("Quantity: " + cart.quantity.add(String.valueOf(mQuantity.getSelectedItem())) + " Name: " + cart.name.add(name) + " Price: $"  + cart.price.add(price));

                    startActivity(mCartIntent);

                } else {
                    Toast.makeText(getApplicationContext(),
                            "Out of Stock", Toast.LENGTH_SHORT).show();

                }
            }

        });

    }

}