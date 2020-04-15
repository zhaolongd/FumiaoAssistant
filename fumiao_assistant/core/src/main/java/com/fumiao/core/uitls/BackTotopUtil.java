package com.fumiao.core.uitls;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;




/**
 * Created by zhaolong on 2019/08/02.
 */

public class BackTotopUtil {

    private static boolean isOneTop;

    public interface OnHomeToTopListener {
        void onBackToTop(boolean isTop);

        void onCanTop();
    }

    public static class OnHomeToTopListenerAdpater implements OnHomeToTopListener {
        @Override
        public void onBackToTop(boolean isTop) {

        }

        @Override
        public void onCanTop() {

        }
    }
    private OnHomeToTopListener onHomeToTopListener;

    public void setOnHomeToTopListener(OnHomeToTopListener onHomeToTopListener) {
        this.onHomeToTopListener = onHomeToTopListener;
    }


    public static void buildBackToTopListener(final RecyclerView recyclerView, final OnHomeToTopListener listener) {
        if (recyclerView == null || listener == null) {
            return;
        }
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                boolean is = canShowBackToTop(recyclerView);
                if (is) {
                    isOneTop = true;
                }
                listener.onBackToTop(is);
                if (isOneTop && canTop(recyclerView)) {
                    isOneTop = false;
                    listener.onCanTop();
                }
            }
        });
    }

    public static boolean canShowBackToTop(RecyclerView recyclerView) {
        if (recyclerView == null) {
            return false;
        }
        final RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            return ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition() > 5;
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            int[] positions = ((StaggeredGridLayoutManager) layoutManager).findLastVisibleItemPositions(null);
            if (positions != null && positions.length > 0) {
                return positions[positions.length - 1] > 20;
            }
        }
        return false;
    }

    public static boolean canTop(RecyclerView recyclerView) {
        if (recyclerView == null) {
            return false;
        }
        final RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            return ((LinearLayoutManager) layoutManager).findFirstCompletelyVisibleItemPosition() == 0;
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            int[] positions = ((StaggeredGridLayoutManager) layoutManager).findFirstCompletelyVisibleItemPositions(null);
            if (positions != null && positions.length > 0) {
                return positions[positions.length - 1] == 0;
            }
        }
        return false;
    }

    public static void backToTop(RecyclerView recyclerView) {
        final RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            if (((LinearLayoutManager) layoutManager).findLastVisibleItemPosition() > 40) {
                recyclerView.scrollToPosition(0);
            } else {
                recyclerView.smoothScrollToPosition(0);
            }
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            int[] positions = ((StaggeredGridLayoutManager) layoutManager).findLastVisibleItemPositions(null);
            if (positions != null && positions.length > 0) {
                if (positions[positions.length - 1] > 80) {
                    recyclerView.scrollToPosition(0);
                } else {
                    recyclerView.smoothScrollToPosition(0);
                }
            }
        }
    }
}
