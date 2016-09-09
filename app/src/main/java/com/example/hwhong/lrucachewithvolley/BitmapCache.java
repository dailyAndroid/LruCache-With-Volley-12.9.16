package com.example.hwhong.lrucachewithvolley;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 * Created by hwhong on 9/9/16.
 */
public class BitmapCache implements ImageLoader.ImageCache {

    private LruCache<String, Bitmap> lruCache;

    public BitmapCache() {
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int cacheSize = maxMemory/8;

        lruCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount();
            }
        };
    }

    @Override
    public Bitmap getBitmap(String url) {
        return lruCache.get(url);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        synchronized (lruCache) {
            if (lruCache.get(url) == null) {
                lruCache.put(url, bitmap);
            }
        }
    }
}
