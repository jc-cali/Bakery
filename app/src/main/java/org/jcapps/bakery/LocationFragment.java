package org.jcapps.bakery;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class LocationFragment extends Fragment implements View.OnClickListener{
    TextView mText1;
    TextView mText2;
    TextView mText3;
    String text1;
    String text2;
    String text3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View locationFragment =  inflater.inflate(R.layout.fragment_location, container, false);
        TextView mText1 = (TextView) locationFragment.findViewById(R.id.txtChinatown);
        text1 = "Chinatown\n" +
                "2242 S Wentworth Ave\n" +
                "Chicago, IL 60616\n" +
                "Phone: (312)225-6608\n" +
                "Hours: 7am - 9pm (Daily)";
        mText1.setText(text1);
        mText1.setOnClickListener(this);

        TextView mText2 = (TextView) locationFragment.findViewById(R.id.txtUptown);
        text2 = "Uptown\n" +
                "1127 W Argyle St\n" +
                "Chicago, IL 60640\n" +
                "Phone: (773)907-8888\n" +
                "Hours: 7am – 7:30pm (Daily)";
        mText2.setText(text2);
        mText2.setOnClickListener(this);

        TextView mText3 = (TextView) locationFragment.findViewById(R.id.txtMcKinleyPark);
        text3 = "McKinley Park\n" +
                "3324 S Archer Ave\n" +
                "Chicago, IL 60608\n" +
                "Phone: (773)376-3839\n" +
                "Hours: 10am – 9pm (Daily)";
        mText3.setText(text3);
        mText3.setOnClickListener(this);

        return locationFragment;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txtChinatown:
                Intent intentchinatown = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.maps.google.com/maps?&daddr=2242 S Wentworth Ave"));
                startActivity(intentchinatown);
                break;
            case R.id.txtUptown:
                Intent intentuptown = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.maps.google.com/maps?&daddr=1127 W Argyle St"));
                startActivity(intentuptown);
                break;
            case R.id.txtMcKinleyPark:
                Intent intentmckinleypark = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.maps.google.com/maps?&daddr=3324 S Archer Ave"));
                startActivity(intentmckinleypark);
                break;
            default:
                break;
        }

    }
}
