package com.e.android3hw.ui.onboard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import com.e.android3hw.R;
import com.e.android3hw.data.entity.OnBoardEntity;
import java.util.ArrayList;

public class OnBoardAdapter extends PagerAdapter {

    private ArrayList<OnBoardEntity> resource;

    OnBoardAdapter(ArrayList<OnBoardEntity> resource) {
        this.resource = resource;
    }

    @Override
    public int getCount() {
        return resource.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull final ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.layout_info,null);
        ImageView imageView = view.findViewById(R.id.imageViewInfo);
        TextView textView = view.findViewById(R.id.textViewInfo);

        imageView.setImageDrawable(container.getContext().getResources().getDrawable(resource.get(position).getImg()));
        textView.setText(resource.get(position).getTitle());

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout) object);
    }
}