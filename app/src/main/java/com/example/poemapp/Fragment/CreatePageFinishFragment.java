package com.example.poemapp.Fragment;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.poemapp.R;

public class CreatePageFinishFragment extends Fragment {
    Bitmap bitmap;
    ImageView finishImage;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_create_page_finish,container,false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();
    }

    public CreatePageFinishFragment(Bitmap bmp){
        bitmap = bmp;
    }

    private void initView() {
        finishImage = getActivity().findViewById(R.id.finish_bmp);
        finishImage.setImageBitmap(bitmap);
    }

}
