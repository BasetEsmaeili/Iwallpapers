package ir.baset.iwallpapers;

import android.content.Context;
import android.widget.Toast;

import com.muddzdev.styleabletoastlibrary.StyleableToast;

import io.realm.Realm;
import io.realm.RealmResults;


public class GifFavoritesDao {
    private Context context;
    private Realm realm;
    public GifFavoritesDao(Context context){
    this.context=context;
    realm=Realm.getDefaultInstance();
    }
    public void add(final ModelGifFavorites modelGifFavorites){
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
realm.copyToRealmOrUpdate(modelGifFavorites);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                StyleableToast.makeText(context,context.getResources()
                        .getString(R.string.added_to_favorites), Toast.LENGTH_SHORT,R.style.SuccesToast).show();
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                StyleableToast.makeText(context,context.getResources()
                        .getString(R.string.error_in_save), Toast.LENGTH_SHORT,R.style.ErrorToast).show();
            }
        });
    }
    public void remove(final String id){
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.where(ModelGifFavorites.class).equalTo("id",id).findFirst().deleteFromRealm();
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
StyleableToast.makeText(context,context.getResources().getString(R.string.removed_from_favorites),Toast.LENGTH_SHORT,R.style.ErrorToast).show();
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                StyleableToast.makeText(context,context.getResources().getString(R.string.error_in_delet),Toast.LENGTH_SHORT,R.style.ErrorToast).show();
            }
        });
    }
    public boolean find(String id){
        ModelGifFavorites favorites = realm.where(ModelGifFavorites.class).equalTo("id", id).findFirst();
        if (favorites!=null){
            return true;
        }else{
            return false;
        }
    }
    public RealmResults<ModelGifFavorites> getAll(){
        RealmResults<ModelGifFavorites> realmResults=realm.where(ModelGifFavorites.class).findAllAsync();
        realmResults.load();
        return realmResults;
    }
}
