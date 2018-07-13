package ir.baset.iwallpapers;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;

import java.util.Random;

public class FileDownloader{
    private Context context;
    private String url;
    private DownloadManager downloadManager;
    public FileDownloader(Context context,String url){
        this.context=context;
        this.url=url;
    }
    public void startDownload(){
        downloadManager=(DownloadManager)context.getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri=Uri.parse(url);
        DownloadManager.Request request=new DownloadManager.Request(uri);
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
        request.setAllowedOverRoaming(false);
        request.setTitle("Iwallpapers");
        request.setDescription("در حال دانلود لطفا منتظر بمانید...");
        request.setVisibleInDownloadsUi(true);
        Random random=new Random();
        int random_name=random.nextInt(999);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "/Iwallpapers/"  + "/" +"Iwallpapers"+ random_name);
        long refid=downloadManager.enqueue(request);
    }
}
