package com.example.hwhong.lrucachewithvolley;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

/**
 * Created by hwhong on 9/9/16.
 */
public class Adapter extends BaseAdapter {

    private Context context;
    private RequestQueue queue;
    private ImageLoader imageLoader;

    public Adapter(Context c) {
        context = c;
        queue = Volley.newRequestQueue(context);
        imageLoader = new ImageLoader(queue, new BitmapCache());
    }

    static class ViewHolder{
        NetworkImageView imageView;
    }

    @Override
    public int getCount() {
        return Images.imageUrls.length;
    }

    @Override
    public Object getItem(int i) {
        return Images.imageUrls[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {

        final String url = (String) getItem(i);
        final ViewHolder holder;
        View view = convertView;

        if(view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.photo_layout, null);
            holder = new ViewHolder();
            holder.imageView = (NetworkImageView) view.findViewById(R.id.photo);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.imageView.setImageUrl(url, imageLoader);
        return view;

    }
}
