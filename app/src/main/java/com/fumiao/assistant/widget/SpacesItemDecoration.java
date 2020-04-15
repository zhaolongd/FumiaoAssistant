package com.fumiao.assistant.widget;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class SpacesItemDecoration extends RecyclerView.ItemDecoration{
    private int leftSpace;
    private int rightSpace;
    private int bottomSpace;
    private int topSpace;

    public SpacesItemDecoration(int leftSpace, int rightSpace, int topSpace, int bottomSpace) {
        this.leftSpace = leftSpace;
        this.rightSpace = rightSpace;
        this.topSpace = topSpace;
        this.bottomSpace = bottomSpace;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view,
                               RecyclerView parent, RecyclerView.State state) {
        outRect.left = leftSpace;
        outRect.right = rightSpace;
        outRect.bottom = bottomSpace;
        outRect.top = topSpace;

        // Add top margin only for the first item to avoid double space between items
//        if (parent.getChildPosition(view) == 0)
//            outRect.top = space;
    }

}
