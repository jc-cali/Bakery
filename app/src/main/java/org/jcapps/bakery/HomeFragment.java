package org.jcapps.bakery;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;




/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PictureFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PictureFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    ImageView mImage;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View pictureFragment = inflater.inflate(R.layout.fragment_home, container, false);
        ImageView image = (ImageView) pictureFragment.findViewById(R.id.imagehome);
//            Picasso.with(getContext()).load(R.drawable.me).resize(1000,1000).centerInside().into(image);
        Picasso.with(getContext()).load(R.drawable.main).fit().centerCrop().into(image);

//            image.setImageResource(R.drawable.me2);

        return pictureFragment;
    }

}