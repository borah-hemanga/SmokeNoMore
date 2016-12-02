package productions.funky.com.smokenomore.fragments;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;


import java.util.concurrent.Executors;

import productions.funky.com.smokenomore.R;
import productions.funky.com.smokenomore.database.SmokeRecord;
import productions.funky.com.smokenomore.util.CircleProgressBar;
import productions.funky.com.smokenomore.util.ResourceMapper;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainPage extends Fragment implements NoSmokeFragmentInterface{

    int count;
    CircleProgressBar progressBar;
    LinearLayout mainpage_layout;
    NoSmokeActivityInterface mListener;

    public static MainPage newInstance() {
        MainPage fragment = new MainPage();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public MainPage() {
        count = 0;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity parentActivity;
        if (context instanceof Activity){
            parentActivity = (Activity) context;
            try {
                mListener = (NoSmokeActivityInterface) parentActivity;
            } catch (ClassCastException e) {
                throw new ClassCastException(parentActivity.toString() + " must implement NoSmokeActivityInterface");
            }
        }
    }

    public static float dipToPixels(Context context, float dipValue) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, metrics);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_main_page, container, false);
        //TextView counterText = (TextView)view.findViewById(R.id.count_text);
        //((GradientDrawable)counterText.getBackground()).setStroke((int)dipToPixels(getActivity(), 10), Color.YELLOW);
        progressBar = (CircleProgressBar) view.findViewById(R.id.custom_progressBar);
        progressBar.setText(Integer.toString(count));
        mainpage_layout = (LinearLayout) view.findViewById(R.id.mainpage_layout);
        FloatingActionButton add_button = (FloatingActionButton) view.findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Executors.newSingleThreadExecutor().execute(new Runnable() {
                    @Override
                    public void run() {
                        SmokeRecord.storeRecord();
                    }
                });
                mListener.onCountChanged(count + 1);
            }
        });


        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                setCountValue(count);
            }
        });
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        count = SmokeRecord.getCurrentState();
    }

    private void modifyUIforCountValue(int count) {
        progressBar.setText(Integer.toString(count));
        progressBar.setColor(getResources().getColor(ResourceMapper.getColorForCount(count)));
        mainpage_layout.setBackgroundResource(ResourceMapper.getDrawableForCount(count));
        mainpage_layout.invalidate();
    }

    @Override
    public void setCountValue(final int count) {
        this.count = count;
        modifyUIforCountValue(count);
    }
}
