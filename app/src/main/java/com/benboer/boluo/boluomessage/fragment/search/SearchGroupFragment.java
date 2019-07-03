package com.benboer.boluo.boluomessage.fragment.search;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.benboer.boluo.boluomessage.R;
import com.benboer.boluo.boluomessage.activity.PersonalActivity;
import com.benboer.boluo.boluomessage.activity.SearchActivity;
import com.benboer.boluo.core.fragment.PresenterFragment;
import com.benboer.boluo.widget.EmptyView;
import com.benboer.boluo.widget.PortraitView;
import com.benboer.boluo.widget.recycler.RecyclerAdapter;
import com.benboer.boluo.factory.model.card.GroupCard;
import com.benboer.boluo.factory.presenter.search.SearchContract;
import com.benboer.boluo.factory.presenter.search.SearchGroupPresenter;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by BenBoerBoluojiushiwo on 2019/5/21.
 */
public class SearchGroupFragment extends PresenterFragment<SearchContract.Presenter>
        implements SearchContract.GroupView {
    @BindView(R.id.empty)
    EmptyView mEmptyView;

    @BindView(R.id.recycler)
    RecyclerView mRecycler;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    private RecyclerAdapter<GroupCard> mAdapter;

    @Override
    protected SearchContract.Presenter initPresenter() {
        return new SearchGroupPresenter(this);
    }

    @Override
    public void onSearchDone(List<GroupCard> groupCards) {

        mAdapter.replace(groupCards);

        mPlaceHolderView.triggerOkOrEmpty(mAdapter.getItemCount() > 0);
    }

    @Override
    public Object setLayout() {
        return R.layout.fragment_group;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View root) {
        //让onCreateOptionsMenu方法被调用
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
        setHasOptionsMenu(true);

        mRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecycler.setAdapter(mAdapter = new RecyclerAdapter<GroupCard>() {
            @Override
            protected int getItemViewType(int position, GroupCard userCard) {
                return R.layout.cell_search_group_list;
            }

            @Override
            protected ViewHolder<GroupCard> onCreateViewHolder(View root, int viewType) {
                return new SearchGroupFragment.ViewHolder(root);
            }
        });

        mEmptyView.bind(mRecycler);
        setPlaceHolderView(mEmptyView);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.search, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String content) {
                mPresenter.onSearch(content);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String content) {
                if (TextUtils.isEmpty(content)) {
                    mPresenter.onSearch("");
                    return true;
                }
                return false;
            }
        });
    }

    class ViewHolder extends RecyclerAdapter.ViewHolder<GroupCard>{
        @BindView(R.id.im_portrait)
        PortraitView mPortraitView;

        @BindView(R.id.txt_name)
        TextView mName;

        @BindView(R.id.im_join)
        ImageView mJoin;

        public ViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void onBind(GroupCard groupCard) {
            mPortraitView.setup(Glide.with(SearchGroupFragment.this), groupCard.getPicture());
            mName.setText(groupCard.getName());
            // 加入时间判断是否加入群
            mJoin.setEnabled(groupCard.getJoinAt() == null);
        }

        @OnClick(R.id.im_join)
        void onJoinClick() {
            // 进入创建者的个人界面
            PersonalActivity.show(getContext(), data.getOwnerId());
        }
    }
}
