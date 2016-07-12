package org.jcapps.bakery;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CursorAdapter;

import java.util.Locale;


public class MainActivity extends AppCompatActivity {
    private CursorAdapter mCursorAdapter;
    private BakerySQLiteOpenHelper mHelper;

    Intent mSearchIntent;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BakerySQLiteOpenHelper db = BakerySQLiteOpenHelper.getInstance(this);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // To display the logo in the toolbar.
        getSupportActionBar().setLogo(R.drawable.logo3);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.logo3);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Home"));
        tabLayout.addTab(tabLayout.newTab().setText("Menu"));
        tabLayout.addTab(tabLayout.newTab().setText("Locations"));
        tabLayout.addTab(tabLayout.newTab().setText("About Us"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        // Takes care of the initial intent in the OnCreate and executes a cursor search if the intent is a search.
        handleIntent(getIntent());

        // Create database and table, pastry.  Should only run once, then commented out.
        SQLiteDatabase db2;

        db2 = openOrCreateDatabase("Bakery.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);

        db2.setVersion(1);
        db2.setLocale(Locale.getDefault());

        Integer[] id = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17};
        String[] name = new String[]{"Fruit Cake", "Green Tea Mousse Cake", "Honey Dew Roll Cake", "Sponge Cake", "Curry Beef Pastry Puff", "Hot Dog Bun", "Hot Dog Scallion Bun", "Roast Pork Bun", "Roast Pork Pastry Puff", "Coconut Cream Bun", "Custard Bun", "Egg Tart", "Portuguese Egg Custard Tart", "Pineapple Bun", "Red Lotus Pastry Puff", "Sesame Ball", "Wife Cake / Melon Cake"};
        String[] description = new String[]{"The sponge cake layers are light and fluffy and not too sweet. Two cake layers are sandwiched on top of vanilla custard and fresh fruits. The entire cake is covered in a freshly made whipped cream frosting and garnished with additional fresh fruits.",
                "A unique must for any fan of Japanese green tea, our Green Tea Mousse Cake alternates a delicate green tea sponge cake with a matching, ethereal light green tea mousse. A lush cover of fresh whipped cream and fine dusting of green tea cake crumbs to complete.",
                "A honey dew flavored sponge cake rolled with a whipped cream filling",
                "Simple paper-wrapped sponge cake. They are light as air, soft and fluffy, and taste mildly sweet and eggy.",
                "Hong Kong style Curry Beef Pastry Puff has curry beef filling in the center and a light flaky crust on the outside.",
                "Hot dog neatly wrapped inside a soft, sweet bread. They''re really handy as snacks or a quick meal on the go.",
                "The hot dog is sliced and arranged in clover-shaped bun topped with scallions for more sweetness.",
                "Barbecue pork (Char Siu in Chinese) gets chopped up fine and mixed with a blend of primarily oyster sauce, hoisin, soy sauce and sugar. The bun is then topped with a sugar glaze before baking.",
                "In Chinese, it''s called Char Siu So – essentially, the same filling as the Roast Pork Bun, but instead of being enclosed in a bread dough, it is wrapped in a buttery, flakey, pastry dough that is almost like biscuit, but flakier, which is wrapped into a triangle shape.",
                "Coconut buns are usually split down the middle, filled with a salty-sweet cream made of shortening and sugar, and topped with some dried coconut.",
                "A yellow custard filled bun, topped with a sweet baked crust on top.",
                "Hong Kong style egg custard baked into single-serving tart shells.  The filling is a creamy paste by upping the egg yolks and lowering the dairy and sugar. The filling is silky smooth with a slightly wiggly, creamy, rich texture and a strong eggy flavor, balanced out by moderate sweetness. And the tart shell is flaky and light.",
                "Hong Kong''s take on the Portuguese original, fittingly described as Portuguese egg custard tarts. They share the flaky crust as the Egg Tart, but have a smoother, lighter filling and a broiled crust like crème brûlée. The custard here should be sweet and mildly eggy with a vanilla kick.",
                "No actual pineapple here. Instead, bun dough is topped with a sweet paste of sugar, flour, eggs, and fat that turns burnished yellow and crackly in the oven for a pattern that looks a pineapple rind.",
                "A flakey outside filled with a sweet, nutty and rich, lotus bean paste almost brings to mind peanut butter but without the gooey stickiness.",
                "It has a crispy exterior that tastes of sesame seeds and a chewy layer of glutinous rice dough, and a well-filled core of red bean paste.",
                "An archetypical Chinese sweet: light on sugar but big on chewy texture. This cookie-shaped cake is filled with a subtle, sticky-chewy winter melon filling that has a taste and texture reminiscent of mashed sticky rice with some notes of sweet summer squash."};
        String[] picture_resource_id = new String[]{"fruit_cake","green_tea_mousse_cake","honey_dew_roll","sponge_cake","curry_beef_pastry_puff","hot_dog_bun","hot_dog_scallion_bun","roast_pork_bun","roast_pork_pastry_puff","coconut_cream_bun","custard_bun","egg_tart","portuguese_egg_custard_tart","pineapple_bun","red_lotus_pastry_puff","sesame_ball","wife_cake"};
        Integer[] calories = new Integer[]{4960, 1680, 1032, 103, 150, 218, 250, 207, 150, 356, 210, 178, 250, 340, 220, 132, 397};
        String[] price = new String[]{"18.99", "15.99", "6.00", "1.00", "1.75", "1.50", "1.50", "1.25", "1.30", "1.50", "1.20", "1.25", "1.25", "1.10", "0.95", "1.00", "1.50"};
        String[] category = new String[]{"Cake", "Cake", "Cake", "Cake", "Savory", "Savory", "Savory", "Savory", "Savory", "Sweet", "Sweet", "Sweet", "Sweet", "Sweet", "Sweet", "Sweet", "Sweet"};
        Integer[] in_stock = new Integer[]{0, 1, 1, 1, 0, 0, 1, 1, 0, 1, 0, 1, 1, 0, 0, 1, 1};
        db2.execSQL("CREATE TABLE IF NOT EXISTS pastry (_id integer, name text, description text, picture_resource_id text, calories integer, price text, category text, in_stock integer);");
        for (int i = 0; i < name.length; i++) {
            db2.execSQL("INSERT INTO pastry Values (" + id[i] + ",'" + name[i] + "', '" + description[i] + "','" + picture_resource_id[i] + "'," + calories[i] + ",'" + price[i] + "','" + category[i] + "'," + in_stock[i] + ");");
        }

    }

    // This is called since launchMode is set to "singleTop" in the AndroidManifest.xml file.
    // The activity is re-launched if on top of the activity stack and reuses the existing instance of the intent
    // that was called to relaunch it.
    // Note: An activity will always be paused before receiving a new intent, so you can count on onResume()
    // being called after this method.

    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        // Call menu xml file.
        menuInflater.inflate(R.menu.main_menu, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        // Call search from main_menu.xml file.
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        return true;
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            // Reassign cursor by rerunning the query using the search string.
            mSearchIntent = new Intent(MainActivity.this, SearchDetailActivity.class);
            mSearchIntent.putExtra("SEARCH_QUERY", query);
            startActivity(mSearchIntent);

            //cursor = mHelper.searchMenuList(query);
            // send query to fragment
            // have fragment search

            // Closes the current cursor from getShoppingList and uses the new cursor called
            // from searchShoppingList.
            //mCursorAdapter.changeCursor(cursor);
            // The view will refresh since the data from the new cursor has changed.
            //mCursorAdapter.notifyDataSetChanged();
        }
    }

}