package ir.baset.iwallpapers;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class Application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration configuration=new RealmConfiguration.Builder()
                .name("Iwallpapers.realm")
                .schemaVersion(3)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(configuration);
    }
}
