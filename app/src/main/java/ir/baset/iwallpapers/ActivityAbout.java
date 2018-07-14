package ir.baset.iwallpapers;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class ActivityAbout extends AppCompatActivity implements View.OnClickListener {
private Toolbar toolbar;
private TextView app_version;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        setupViews();
        setupToolbar();
        setupTextViews();
    }

    private void setupTextViews() {
        app_version.setText(getResources().getString(R.string.app_version)+" "+BuildConfig.VERSION_NAME);
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
     if (getSupportActionBar() != null){
         getSupportActionBar().setDisplayHomeAsUpEnabled(true);
         toolbar.setNavigationOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 finish();
             }
         });
     }
    }

    private void setupViews() {
        toolbar=findViewById(R.id.toolbar);
        app_version=findViewById(R.id.about_app_version);
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id){
            case R.id.about_github_source:
                Intent github =new Intent(Intent.ACTION_VIEW, Uri.parse("https://gitlab.com/Baset_esmaeili/Iwallpapers"));
                startActivity(github);
                break;
            case R.id.about_developer_website:
                Intent weblog=new Intent(Intent.ACTION_VIEW,Uri.parse("https://basetesmaeili.blogspot.com/"));
                startActivity(weblog);
                break;
            case R.id.about_send_feedback:
                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{"baset.esmaili0@gmail.com"});
                email.setType("message/rfc822");
                startActivity(Intent.createChooser(email, "Choose an Email client :"));
                break;
        }
    }
}
