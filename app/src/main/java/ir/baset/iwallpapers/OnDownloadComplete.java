package ir.baset.iwallpapers;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.muddzdev.styleabletoastlibrary.StyleableToast;

public class OnDownloadComplete extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        long resId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID,-1);
        if (resId==intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID,-1)){
            StyleableToast.makeText(context,context.getResources().getString(R.string.download_complete),
                    Toast.LENGTH_SHORT,R.style.SuccesToast).show();
        }
    }
}
