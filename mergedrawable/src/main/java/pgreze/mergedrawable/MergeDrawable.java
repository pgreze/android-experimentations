package pgreze.mergedrawable;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * See http://stackoverflow.com/a/11740676
 */
public class MergeDrawable extends ActionBarActivity {
    private static final String TAG = MergeDrawable.class.getName();

    private static final int WIDTH = 640;
    private static final int HEIGHT = 640;
    private int watermarkHeight;
    private int watermarkWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merge_drawable);
        ImageView img = (ImageView) findViewById(R.id.imgview);

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
}
