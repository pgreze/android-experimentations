package pgreze.mergedrawable;

import android.animation.Animator;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * See http://stackoverflow.com/a/11740676
 */
public class MergeDrawable extends AppCompatActivity {
    private static final String TAG = MergeDrawable.class.getName();

    private static final int WIDTH = 640;
    private static final int HEIGHT = 640;

    private ImageView img;
    private int watermarkHeight;
    private int watermarkWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merge_drawable);
        img = (ImageView) findViewById(R.id.imgview);

        setImage();
        animate();
    }

    private void setImage() {
        Log.i(TAG, "Hello");
        Bitmap b = Bitmap.createBitmap(WIDTH, HEIGHT, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        try {
            Drawable cover = readDrawableAsset("daftpunk.jpg");
            Drawable watermark = getResources().getDrawable(R.drawable.watermark_instasound);

            int w = watermark.getIntrinsicWidth(), h = watermark.getIntrinsicHeight();
            watermarkWidth = (int) Math.round(WIDTH * 0.5);
            watermarkHeight = Math.round(h * ((float) watermarkWidth / w));
            Log.i(TAG, String.format("Resize watermark %sx%s -> %sx%s", w, h, watermarkWidth, watermarkHeight));

            useDrawable(c, cover, watermark);

            // Draw canvas into image view
            img.setImageDrawable(new BitmapDrawable(getResources(), b));
            // And in sdcard
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            b.compress(Bitmap.CompressFormat.PNG, 0, bos);
            FileOutputStream out = new FileOutputStream(new File("/sdcard/test.png"));
            out.write(bos.toByteArray());
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void useDrawable(Canvas c, Drawable cover, Drawable watermark) {
        cover.setBounds(0, 0, WIDTH, HEIGHT);
        cover.draw(c);

        watermark.setBounds(WIDTH - watermarkWidth, HEIGHT - watermarkHeight,
                WIDTH, HEIGHT);
        watermark.draw(c);
    }

    private void useBitmap(Canvas c, Drawable cover, Drawable watermark) {
        // Scale cover
        Bitmap coverB = Bitmap.createScaledBitmap(drawableToBitmap(cover), WIDTH, HEIGHT, false);

        // Scale watermark
        Bitmap watermarkB = Bitmap.createScaledBitmap(
                drawableToBitmap(watermark),
                watermarkWidth, watermarkHeight, false);

        // Apply these bitmap on the canvas
        c.drawBitmap(coverB, 0, 0, null);
        c.drawBitmap(watermarkB,
                WIDTH - watermarkWidth,
                HEIGHT - watermarkHeight, null); // bottom right
    }

    private Drawable readDrawableAsset(String filename) throws IOException {
        return Drawable.createFromStream(getAssets().open(filename), null);
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable)drawable).getBitmap();
        }

        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }

    // Animation

    private ObjectAnimator anim;

    /**
     * See http://developer.android.com/guide/topics/graphics/prop-animation.html#keyframes
     * Or XML alternative: http://developer.android.com/reference/android/animation/ObjectAnimator.html
     * But keyframe xml tag is enabled for at least API level 23.
     */
    private void animate() {
        int seconds = 3;
        float fraction = 1f / (12 * seconds);
        Keyframe[] keyframes = new Keyframe[] {
                Keyframe.ofFloat(0f, 0f),
                Keyframe.ofFloat(fraction, 15f),
                Keyframe.ofFloat(fraction * 3f, -15f),
                Keyframe.ofFloat(fraction * 5f, 10f),
                Keyframe.ofFloat(fraction * 7f, -10f),
                Keyframe.ofFloat(fraction * 9f, 5f),
                Keyframe.ofFloat(fraction * 11f, -5f),
                Keyframe.ofFloat(fraction * 12f, 0f),
                Keyframe.ofFloat(1f, 0f),
        };
        PropertyValuesHolder pvhRotation = PropertyValuesHolder.ofKeyframe("rotationY", keyframes);
        anim = ObjectAnimator.ofPropertyValuesHolder(img, pvhRotation);
        anim.setDuration(seconds * 1000);
        anim.setRepeatCount(ValueAnimator.INFINITE);
        anim.setRepeatMode(ValueAnimator.RESTART);
        anim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                Log.d(TAG, "AnimationStart(" + animation + ")");
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Log.d(TAG, "AnimationEnd(" + animation + ")");
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                Log.d(TAG, "AnimationCancel(" + animation + ")");
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                Log.d(TAG, "AnimationRepeat(" + animation + ")");
            }
        });
        anim.start();

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (anim.isRunning()) {
                    anim.cancel();
                    img.startAnimation(AnimationUtils.loadAnimation(
                            getApplicationContext(), R.anim.shake));
                } else {
                    img.clearAnimation();
                    anim.start();
                }
            }
        });
    }
}
