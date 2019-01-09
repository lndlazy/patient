package com.ylean.cf_hospitalapp.photoView;

import android.content.Context;
import android.graphics.RectF;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.ylean.cf_hospitalapp.inquiry.activity.PicDetailAc;


/**
 * Provided default implementation of MGestureDetector.OnDoubleTapListener, to be overriden with custom behavior, if needed
 * <p>&nbsp;</p>
 */
// * To be used via {@link uk.co.senab.photoview.PhotoViewAttacher#setOnDoubleTapListener(MGestureDetector.OnDoubleTapListener)}
public class DefaultOnDoubleTapListener implements GestureDetector.OnDoubleTapListener {

    private PhotoViewAttacher photoViewAttacher;
    private Context ctx;

    /**
     * Default constructor
     *
     * @param photoViewAttacher PhotoViewAttacher to bind to
     */
    public DefaultOnDoubleTapListener(PhotoViewAttacher photoViewAttacher, Context ctx) {
        setPhotoViewAttacher(photoViewAttacher);
        this.ctx = ctx;
    }

    /**
     * Allows to change PhotoViewAttacher within range of single instance
     *
     * @param newPhotoViewAttacher PhotoViewAttacher to bind to
     */
    public void setPhotoViewAttacher(PhotoViewAttacher newPhotoViewAttacher) {
        this.photoViewAttacher = newPhotoViewAttacher;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        if (this.photoViewAttacher == null)
            return false;

        ImageView imageView = photoViewAttacher.getImageView();


        //TODO

        //记录手指按下时的x和y轴坐标
        if (ctx instanceof PicDetailAc) {

            PicDetailAc chatActivity = (PicDetailAc) ctx;
            chatActivity.hintPicDetail();

        }

//        LogUtil.e("TAG", getClass().getName() + ":=====单击点击事件=====");

        if (null != photoViewAttacher.getOnPhotoTapListener()) {
            final RectF displayRect = photoViewAttacher.getDisplayRect();

            if (null != displayRect) {
                final float x = e.getX(), y = e.getY();

                // Check to see if the user tapped on the photo
                if (displayRect.contains(x, y)) {

                    float xResult = (x - displayRect.left)
                            / displayRect.width();
                    float yResult = (y - displayRect.top)
                            / displayRect.height();

                    photoViewAttacher.getOnPhotoTapListener().onPhotoTap(imageView, xResult, yResult);
                    return true;
                }
            }
        }
        if (null != photoViewAttacher.getOnViewTapListener()) {
            photoViewAttacher.getOnViewTapListener().onViewTap(imageView, e.getX(), e.getY());
        }

        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent ev) {
        if (photoViewAttacher == null)
            return false;

        try {
            float scale = photoViewAttacher.getScale();
            float x = ev.getX();
            float y = ev.getY();

            if (scale < photoViewAttacher.getMediumScale()) {
                photoViewAttacher.setScale(photoViewAttacher.getMediumScale(), x, y, true);
            } else if (scale >= photoViewAttacher.getMediumScale() && scale < photoViewAttacher.getMaximumScale()) {
                photoViewAttacher.setScale(photoViewAttacher.getMaximumScale(), x, y, true);
            } else {
                photoViewAttacher.setScale(photoViewAttacher.getMinimumScale(), x, y, true);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            // Can sometimes happen when getX() and getY() is called
        }

        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        // Wait for the confirmed onDoubleTap() instead
        return false;
    }

}
