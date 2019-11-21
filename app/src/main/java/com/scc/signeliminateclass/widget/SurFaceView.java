package com.scc.signeliminateclass.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.os.Build;
import android.util.AttributeSet;
import android.view.SurfaceView;

import androidx.annotation.RequiresApi;

/**
 * @author
 * @data 2019/11/8
 */
public class SurFaceView extends SurfaceView {
    /**
     * SurFaceView
     * @param context context
     */
    public SurFaceView(Context context) {
        super(context);
    }

    /**
     * SurFaceView
     * @param context context
     * @param attrs attrs
     */
    public SurFaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * SurFaceView
     * @param context  context
     * @param attrs attrs
     * @param defStyleAttr defStyleAttr
     */
    public SurFaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * SurFaceView
     * @param context context
     * @param attrs attrs
     * @param defStyleAttr defStyleAttr
     * @param defStyleRes defStyleRes
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public SurFaceView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void draw(Canvas canvas) {
        Path path = new Path();
        //用矩形表示SurfaceView宽高
        RectF rect = new RectF(0, 0, this.getWidth(), this.getHeight());
        //15.0f即是圆角半径
        path.addRoundRect(rect, 300.0f, 300.0f, Path.Direction.CCW);
        //裁剪画布，并设置其填充方式
        canvas.clipPath(path, Region.Op.REPLACE);
        super.draw(canvas);
    }

}
