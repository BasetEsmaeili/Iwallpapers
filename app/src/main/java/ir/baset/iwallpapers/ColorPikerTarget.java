package ir.baset.iwallpapers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.graphics.Palette;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import de.hdodenhof.circleimageview.CircleImageView;

public class ColorPikerTarget implements Target {
    private Context context;
    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;
    private TextView textView5;
    private TextView textView6;
    private TextView textView7;
    private CircleImageView imageView1;
    private CircleImageView imageView2;
    private CircleImageView imageView3;
    private CircleImageView imageView4;
    private CircleImageView imageView5;
    private CircleImageView imageView6;
    private CircleImageView imageView7;
    public ColorPikerTarget(
            Context context,
            TextView textView1
            ,TextView textView2
            ,TextView textView3
            ,TextView textView4
            ,TextView textView5
            ,TextView textView6
            ,TextView textView7
            ,CircleImageView imageView1
            ,CircleImageView imageView2
            ,CircleImageView imageView3
            ,CircleImageView imageView4
            ,CircleImageView imageView5
            ,CircleImageView imageView6
            ,CircleImageView imageView7){
        this.context=context;
        this.textView1=textView1;
        this.textView2=textView2;
        this.textView3=textView3;
        this.textView4=textView4;
        this.textView5=textView5;
        this.textView6=textView6;
        this.textView7=textView7;
        this.imageView1=imageView1;
        this.imageView2=imageView2;
        this.imageView3=imageView3;
        this.imageView4=imageView4;
        this.imageView5=imageView5;
        this.imageView6=imageView6;
        this.imageView7=imageView7;
    }
    @Override
    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(@NonNull Palette palette) {
                setViewSwatch(imageView1,textView1,palette.getDominantSwatch());
                setViewSwatch(imageView2,textView2,palette.getMutedSwatch());
                setViewSwatch(imageView3,textView3,palette.getVibrantSwatch());
                setViewSwatch(imageView4,textView4,palette.getDarkMutedSwatch());
                setViewSwatch(imageView5,textView5,palette.getDarkVibrantSwatch());
                setViewSwatch(imageView6,textView6,palette.getLightMutedSwatch());
                setViewSwatch(imageView7,textView7,palette.getLightVibrantSwatch());
            }
        });
    }

    @Override
    public void onBitmapFailed(Drawable errorDrawable) {

    }

    @Override
    public void onPrepareLoad(Drawable placeHolderDrawable) {

    }
    public void setViewSwatch(CircleImageView imageView,TextView textView,Palette.Swatch swatch){
        if (swatch!=null) {
            imageView.setBackgroundColor(swatch.getRgb());
            imageView.setCircleBackgroundColor(swatch.getRgb());
            textView.setText(" " + swatch.getRgb());
        }else {
            imageView.setVisibility(View.GONE);
            textView.setVisibility(View.GONE);
        }
    }
}
