package com.famebuster.ui.placeholder;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.famebuster.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PlaceholderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlaceholderFragment extends Fragment {

    private String mParam1;

    public PlaceholderFragment(String mParam1) {
        this.mParam1 = mParam1;
        // Required empty public constructor
    }

    public static PlaceholderFragment newInstance(String param1) {
        PlaceholderFragment fragment = new PlaceholderFragment(param1);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_placeholder, container, false);
        TextView t = v.findViewById(R.id.placeholder_text);
        t.setText( mParam1 );
        return v;
    }

    @NonNull
    @Override
    public String toString() {
        return mParam1;
    }
}
