package org.jcapps.bakery;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MenuFragment extends Fragment {
    TextView mText1;
    TextView mText2;
    TextView mText3;
    String text1;
    String text2;
    String text3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View menuFragment =  inflater.inflate(R.layout.fragment_menu, container, false);
        text1 = "Menu Item1: Cake";
        text2 = "Menu Item2: Buns";
        text3 = "Menu Item3: Pastries";
        TextView mText1 = (TextView) menuFragment.findViewById(R.id.txtmenu1);
        mText1.setText(text1);
        TextView mText2 = (TextView) menuFragment.findViewById(R.id.txtmenu2);
        mText2.setText(text2);
        TextView mText3 = (TextView) menuFragment.findViewById(R.id.txtmenu3);
        mText3.setText(text3);

        return menuFragment;
    }

}
