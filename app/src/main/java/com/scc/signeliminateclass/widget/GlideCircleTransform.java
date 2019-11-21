package com.scc.signeliminateclass.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

/**
 * GlideCircleTransform
 */
public class GlideCircleTransform extends BitmapTransformation {
  /**
   * GlideCircleTransform
   * @param context context
   */
  public GlideCircleTransform(Context context) {
    super(context);
  }

  /**
   * transform
   * @param pool pool
   * @param toTransform toTransform
   * @param outWidth outWidth
   * @param outHeight outHeight
   * @return Bitmap
   */
  protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
    return circleCrop(pool, toTransform);
  }

  /**
   * circleCrop
   * @param pool pool
   * @param source source
   * @return Bitmap
   */
  private static Bitmap circleCrop(BitmapPool pool, Bitmap source) {
    if (source == null) {
      return null;
    }
    int size = Math.min(source.getWidth(), source.getHeight());
    int x = (source.getWidth() - size) / 2;
    int y = (source.getHeight() - size) / 2;
    // TODO this could be acquired from the pool too
    Bitmap squared = Bitmap.createBitmap(source, x, y, size, size);
    Bitmap result = pool.get(size, size, Bitmap.Config.ARGB_8888);
    if (result == null) {
      result = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
    }
    Canvas canvas = new Canvas(result);
    Paint paint = new Paint();
    paint.setShader(new BitmapShader(squared, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
    paint.setAntiAlias(true);
    float r = size / 2f;
    canvas.drawCircle(r, r, r, paint);
    return result;
  }
  @Override public String getId() {
    return getClass().getName();
  }
}