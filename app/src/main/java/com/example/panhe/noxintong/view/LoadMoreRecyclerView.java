package com.example.panhe.noxintong.view;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.view.MotionEvent;


/**
 * Created by HeTianpeng on 2015/11/26.
 */
public class LoadMoreRecyclerView extends RecyclerView implements Runnable {
    private boolean isLoadingMore;
    private OnLoadMoreListener mOnLoadMoreListener;
    private boolean isNoMore;

    public LoadMoreRecyclerView(Context context) {
        super(context);
        init();
    }

    public LoadMoreRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LoadMoreRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public void loadingMoreComplete() {
        this.isLoadingMore = false;
    }

    private void init() {
        addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                RecyclerView.LayoutManager layoutManager = getLayoutManager();
                int itemCount = 0;
                if (layoutManager instanceof LinearLayoutManager) {
                    LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
                    int lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                    itemCount = linearLayoutManager.getItemCount();

                    //dy>0 表示向下滑动
                    load(dy, itemCount, lastVisibleItem);
                } else if (layoutManager instanceof GridLayoutManager) {
                    GridLayoutManager gridLayoutMananager = (GridLayoutManager) layoutManager;
                    int lastVisibleItem = gridLayoutMananager.findLastVisibleItemPosition();
                    itemCount = gridLayoutMananager.getItemCount();

                    //dy>0 表示向下滑动
                    load(dy, itemCount, lastVisibleItem);

                } else {
                    StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) layoutManager;
                    int[] visibleItems = staggeredGridLayoutManager
                            .findLastVisibleItemPositions(null);
                    itemCount = staggeredGridLayoutManager.getItemCount();
                    int lastVisibleItem = Math.max(visibleItems[0], visibleItems[1]);
                    load(dy, itemCount, lastVisibleItem);


                }
            }
        });
    }

    private void load(int dy, int itemCount, int lastVisibleItem) {
//        if (lastVisibleItem <= itemCount - 1) {
//            RecyclerView.Adapter adapter =  getAdapter();
//            if (adapter instanceof BaseRecyclerViewAdapter) {
//                BaseRecyclerViewAdapter baseRecyclerViewAdapter = (BaseRecyclerViewAdapter) adapter;
//                baseRecyclerViewAdapter.setIsUseFooter(false);
//                baseRecyclerViewAdapter.notifyDataSetChanged();
//            }
//            return;
//        }

        //dy>0 表示向下滑动
        if (lastVisibleItem >= itemCount - 2 && dy > 0) {
            if (!isLoadingMore && !isNoMore) {
                isLoadingMore = true;
                if (mOnLoadMoreListener != null) {
                    postDelayed(this, 500);
                }
            }
        }
    }

    public boolean isNoMore() {
        return isNoMore;
    }

    public void setNoMore(boolean noMore) {
        isNoMore = noMore;
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        mOnLoadMoreListener = onLoadMoreListener;
    }

    @Override
    public void run() {
        mOnLoadMoreListener.onLoadMore();
    }

    public interface OnLoadMoreListener {
        void onLoadMore();
    }

    private float xDistance, yDistance, xLast, yLast;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                xDistance = yDistance = 0f;
                xLast = ev.getX();
                yLast = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                if (yLast == 0 || xLast == 0) {
                    xLast = ev.getX();
                    yLast = ev.getY();
                }

                final float curX = ev.getX();
                final float curY = ev.getY();

                xDistance += Math.abs(curX - xLast);
                yDistance += Math.abs(curY - yLast);
                xLast = curX;
                yLast = curY;

                if (xDistance > yDistance) {
                    return false;
                }
        }
        return super.onInterceptTouchEvent(ev);
    }
}
