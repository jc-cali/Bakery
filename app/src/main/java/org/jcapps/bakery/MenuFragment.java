package org.jcapps.bakery;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.support.v4.app.Fragment;

//import org.jcapps.bakery.setup.DBAssetHelper;


/**
 * A simple {@link Fragment} subclass.
 */
public class MenuFragment extends Fragment {
    private ListView mMenuListView;
    private CursorAdapter mCursorAdapter;
    private BakerySQLiteOpenHelper mHelper;

    Intent mDetailIntent;
    Cursor cursor;
    AdapterView.OnItemClickListener mClickListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View menuFragment =  inflater.inflate(R.layout.fragment_menu, container, false);

        Context ctx = getContext(); // singleton
        BakerySQLiteOpenHelper db = BakerySQLiteOpenHelper.getInstance(ctx);
        //Ignore the two lines below, they are for setup
//        final DBAssetHelper dbSetup = new DBAssetHelper(getActivity());
//        dbSetup.getReadableDatabase();

        cursor = db.getMenuList();
        mMenuListView = (ListView) menuFragment.findViewById(R.id.menu_list_view);
//        mHelper = new BakerySQLiteOpenHelper(getActivity());

        //  Use the bindView to combine the NAME and TYPE columns in my list view.
        mCursorAdapter = new CursorAdapter(ctx, cursor, 0) {
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
                        " - " + cursor.getString(cursor.getColumnIndex("category")) +
                        " - " + cursor.getString(cursor.getColumnIndex("price"));
                txt.setText(rowData);
            }
        };

        mClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mDetailIntent = new Intent(getActivity(), MenuDetailActivity.class);

                // I had to use lowercase for the column names from the bakery_list table.
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String description = cursor.getString(cursor.getColumnIndex("description"));
                String price = cursor.getString(cursor.getColumnIndex("price"));
                String category = cursor.getString(cursor.getColumnIndex("category"));
                mDetailIntent.putExtra("NAME", name);
                mDetailIntent.putExtra("DESCRIPTION", description);
                mDetailIntent.putExtra("PRICE", price);
                mDetailIntent.putExtra("CATEGORY", category);
                startActivity(mDetailIntent);
            }
        };

        mMenuListView.setOnItemClickListener(mClickListener);
        mMenuListView.setAdapter(mCursorAdapter);

        return menuFragment;
    }

}
