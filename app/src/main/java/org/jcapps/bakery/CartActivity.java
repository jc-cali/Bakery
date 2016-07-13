package org.jcapps.bakery;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    private ShoppingCart mCart;
    private ListView lv;
    double total = 0;
    TextView mtxtTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // To display the logo in the toolbar.
        getSupportActionBar().setLogo(R.drawable.logo3);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.logo3);

       // mCart = ShoppingCart.getInstance();
//        mCart.name.add("");
        lv = (ListView) findViewById(R.id.lst_cart);
        ArrayList<Pastry> cart = (ArrayList<Pastry>) getIntent().getSerializableExtra("cart");
        for (int i=0; i < cart.size(); i++) {
            double price = cart.get(i).getPrice();
            int quantity = cart.get(i).getQuantity();
            price = price * quantity;
            total = total + price;
        }
        mtxtTotal = (TextView) findViewById(R.id.txt_total);
        mtxtTotal.setText(String.format("$%.2f", total));
//        mtxtTotal.setText(String.valueOf(total));

       // ArrayList<String> cart_items = new ArrayList<>();
      //  cart_items.add(mCart.getName().toString()+mCart.getPrice()+mCart.getQuantity());

        ArrayAdapter<Pastry> arrayAdapter = new ArrayAdapter<Pastry>(
                this,
                android.R.layout.simple_list_item_1, cart);

        lv.setAdapter(arrayAdapter);



    }
}
