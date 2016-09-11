package productions.funky.com.smokenomore.fragments;


import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import productions.funky.com.smokenomore.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainPage extends Fragment {

    public static MainPage newInstance() {
        MainPage fragment = new MainPage();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public MainPage() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_page, container, false);
        TextView counterText = (TextView)view.findViewById(R.id.count_text);
        ((GradientDrawable)counterText.getBackground()).setColor(Color.BLUE);
        return view;
    }


}
