package ir.baset.iwallpapers;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class AdapterTabsPager extends FragmentPagerAdapter {
    private Context context;
    private ArrayList<Fragment> fragments=new ArrayList<>();
    public AdapterTabsPager(FragmentManager fm, Context context) {
        super(fm);
        this.context=context;

    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
    public void addFragments(Fragment fragment){
        fragments.add(fragment);
    }

}
