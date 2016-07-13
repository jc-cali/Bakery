package org.jcapps.bakery;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class SearchDetailActivity extends AppCompatActivity {
    private ListView mMenuListView;
    private CursorAdapter mCursorAdapter;
    private BakerySQLiteOpenHelper mHelper;

    Intent mDetailIntent;
    Cursor cursor;
    AdapterView.OnItemClickListener mClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // To display the logo in the toolbar.
        getSupportActionBar().setLogo(R.drawable.logo3);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.logo3);

        BakerySQLiteOpenHelper db = BakerySQLiteOpenHelper.getInstance(this);
        mMenuListView = (ListView) findViewById(R.id.lst_search);

        String query = getIntent().getExtras().getString("SEARCH_QUERY");
        cursor = db.searchMenuList(query);
//        cursor = db.getMenuList();
//        mHelper = new BakerySQLiteOpenHelper(getActivity());

        //  Use the bindView to combine the NAME and TYPE columns in my list view.
        mCursorAdapter = new CursorAdapter(this, cursor, 0) {
            @Override
            public View newView(Context context, Cursor cursor, ViewGroup parent) {
                return LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1,
                        parent, false);
            }
            @Override
            public void bindView(View view, Context context, Cursor cursor) {
                TextView txt = (TextView) view.findViewById(android.R.id.text1);
                // String rowData = cursor.getString(cursor.getColumnIndex("name"));
                String rowData = cursor.getString(cursor.getColumnIndex("name")) +
//                        " - " + cursor.getString(cursor.getColumnIndex("category")) +
                        " - " + cursor.getString(cursor.getColumnIndex("price"));
                txt.setText(rowData);
            }
        };

        handleIntent(getIntent());
        mClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mDetailIntent = new Intent(SearchDetailActivity.this, MenuDetailActivity.class);

                // I had to use lowercase for the column names from the bakery_list table.
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String description = cursor.getString(cursor.getColumnIndex("description"));
                String picture_resource_id = cursor.getString(cursor.getColumnIndex("picture_resource_id"));
                String price = cursor.getString(cursor.getColumnIndex("price"));
                String category = cursor.getString(cursor.getColumnIndex("category"));
                int in_stock = cursor.getInt(cursor.getColumnIndex("in_stock"));
                mDetailIntent.putExtra("NAME", name);
                mDetailIntent.putExtra("DESCRIPTION", description);
                mDetailIntent.putExtra("PICTURE_RESOURCE_ID", picture_resource_id);
                mDetailIntent.putExtra("PRICE", price);
                mDetailIntent.putExtra("CATEGORY", category);
                mDetailIntent.putExtra("IN_STOCK", in_stock);
                startActivity(mDetailIntent);
            }
        };

        mMenuListView.setOnItemClickListener(mClickListener);
        mMenuListView.setAdapter(mCursorAdapter);

    }

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
            BakerySQLiteOpenHelper db = BakerySQLiteOpenHelper.getInstance(this);
            cursor = db.getMenuList();
            if(cursor!=null) {
                cursor = db.searchMenuList(query);
            }
            // send query to fragment
            // have fragment search

            // Closes the current cursor from getShoppingList and uses the new cursor called
            // from searchShoppingList.
            mCursorAdapter.changeCursor(cursor);
            // The view will refresh since the data from the new cursor has changed.
            mCursorAdapter.notifyDataSetChanged();
        }
    }

}
