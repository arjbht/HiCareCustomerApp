package com.arj.hicarehygiene.utils;

import android.support.annotation.IntDef;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.view.View;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import com.arj.hicarehygiene.BaseFragment;

/**
 * Created by arjun on 25/03/19.
 */

public class Notifier {

  public static final int LENGTH_SHORT = -1;
  public static final int LENGTH_LONG = 0;
  public static final int LENGTH_INDEFINITE = -2;
  private int mLength;
  private String mMessage;
  private BaseFragment mFragment;
  private String action;
  private View.OnClickListener mListner;
  private Snackbar snackbar;

  private Notifier(BaseFragment fragment, String message, int length) {
    this.mFragment = fragment;
    this.mMessage = message;
    this.mLength = length;
  }

  private Notifier(@NonNull BaseFragment fragment, @StringRes int message, int length) {
    this.mFragment = fragment;
    this.mMessage = fragment.getString(message);
    this.mLength = length;
  }

  public static Notifier make(BaseFragment fragment, String message, @Duration int length) {
    return new Notifier(fragment, message, length);
  }

  public static Notifier make(BaseFragment fragment, @StringRes int message, @Duration int length) {
    return new Notifier(fragment, message, length);
  }

  public void show() {
    if (mFragment.isVisible()) {

      switch (mLength) {
        case LENGTH_SHORT:
          snackbar = Snackbar.make(mFragment.getView(), mMessage, Snackbar.LENGTH_SHORT);
          break;

        case LENGTH_LONG:
          snackbar = Snackbar.make(mFragment.getView(), mMessage, Snackbar.LENGTH_LONG);
          break;

        case LENGTH_INDEFINITE:
          snackbar = Snackbar.make(mFragment.getView(), mMessage, Snackbar.LENGTH_INDEFINITE);
          break;
      }
      if (action != null) {
        snackbar.setAction(action, mListner);
      }
      snackbar.show();
    }
  }

  public Notifier setAction(String action, View.OnClickListener listner) {
    this.action = action;
    this.mListner = listner;
    return this;
  }

  public void dismiss() {
    snackbar.dismiss();
  }

  @IntDef({ LENGTH_INDEFINITE, LENGTH_SHORT, LENGTH_LONG }) @IntRange(from = 1)
  @Retention(RetentionPolicy.SOURCE) public @interface Duration {
  }
}
