package com.arj.hicarehygiene;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by yogi on 17/06/17.
 */

public abstract class ListBaseFragment extends BaseFragment {

  public boolean isLastItemDisplaying(RecyclerView recyclerView) {
    if (recyclerView.getAdapter().getItemCount() != 0) {
      int lastVisibleItemPosition =
          ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition();
      if (lastVisibleItemPosition != RecyclerView.NO_POSITION
          && lastVisibleItemPosition == recyclerView.getAdapter().getItemCount() - 1) {
        return true;
      }
    }
    return false;
  }
}
