package ir.baset.iwallpapers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

public class FragmentCategory extends Fragment implements InterfaceCategoryFragment.ResponseFromServer, InterfaceCategoryFragment.RecyclerItemClick ,Constants{
    private Context context;
    private SwipeRefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_category,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupViews(view);
        customizeRefrshlayout();
        setupRequest();
    }

    private void setupRequest() {
        refreshLayout.setRefreshing(true);
RequestAllCategory requestAllCategory=new RequestAllCategory(context);
requestAllCategory.startRequest(this);
    }

    private void setupViews(View view) {
        refreshLayout =view.findViewById(R.id.fragment_category_swipe_to_refresh_layout);
        recyclerView=view.findViewById(R.id.fragment_category_recyvlerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
    }
    private void customizeRefrshlayout() {
        refreshLayout.setColorSchemeColors(ContextCompat.getColor(context, R.color.colorAccent));
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                setupRequest();
            }
        });
    }

    @Override
    public void OnCategoryRecived(List<ModelCategoryFragment> categoryFragments) {
        refreshLayout.setRefreshing(false);
        AdapterCategoryFragment adapterCategoryFragment=new AdapterCategoryFragment(context,categoryFragments,this);
        recyclerView.setAdapter(adapterCategoryFragment);

    }

    @Override
    public void OnCategoryError(String message) {
        refreshLayout.setRefreshing(false);
        showError(message);

    }
    private void showError(String mesage) {
        Snackbar.make(getView(), context.getResources().getString(R.string.error_in_get_data), Snackbar.LENGTH_INDEFINITE)
                .setActionTextColor(ContextCompat.getColor(context, R.color.colorAccent)).setAction(context.getResources().getString(R.string.try_again), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setupRequest();
            }
        }).show();
    }

    @Override
    public void onItemViewClick(String cat_id) {
        Intent intent=new Intent(context,ActivityCategory.class);
        intent.putExtra(KEY_INTENT_DETAIL_TO_CATEGORY,cat_id);
        context.startActivity(intent);
    }
}
