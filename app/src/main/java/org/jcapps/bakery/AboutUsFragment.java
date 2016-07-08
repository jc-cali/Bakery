package org.jcapps.bakery;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class AboutUsFragment extends Fragment {
    TextView mText1;
    ImageView mImage;
    String text1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View aboutusFragment = inflater.inflate(R.layout.fragment_about_us, container, false);
//        ImageView image = (ImageView) aboutusFragment.findViewById(R.id.imageaboutus);
//        Picasso.with(getContext()).load(R.drawable.about_us).resize(1000,800).centerInside().into(image);
        TextView mText1 = (TextView) aboutusFragment.findViewById(R.id.textaboutus1);

        text1 = "A Traditional Chinese Bakery Since 1986\n" +
                "TO: Our amazing friends, family and familiar faces\n" +
                "FROM: The Chiu Family\n" +
                "\n" +
                "Without your unwavering support, we would not be here today. It’s been 25 years since we opened our doors. That makes us the oldest Chinese bakery in Chinatown!\n" +
                "\n" +
                "Thank you for spending your Sunday mornings here. Thank you for introducing your children, and then your grandchildren to our fresh BBQ pork buns and soft, pillowy cream cakes. Thank you for sharing your special moments with us. We’ve enjoyed watching your families grow just as you have watched us change for so many years. Thank you for giving us a chance and insisting that our buns are indeed, better than theirs.\n" +
                "\n" +
                "Because of you, we purchase the finest ingredients so the end products aren’t only made with care, but made with perfectly riped fruits, fresh whipped cream, and the most tender cuts of meat. Our menu has come to include walnut cookies, coconut cream turnovers, lotus bean mooncakes, mango mousse cakes, cream cones, custard buns, and so much more. We have many different items in the bakery at any given time and we couldn’t have done this without your suggestions and helpful criticism. Please continue to give us feedback as we can only serve you best when we are truly at our best.\n" +
                "\n" +
                "Many, many thanks to all the old faces and new faces. To many more friendships, conversations, and buns. Happy eating!\n" +
                "\n" +
                "YOURS TRULY,\n" +
                "CHIU QUON BAKERY";
        mText1.setText(text1);

        return aboutusFragment;


    }

}
