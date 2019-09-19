package com.arj.hicarehygiene.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.arj.hicarehygiene.BaseActivity;
import com.arj.hicarehygiene.R;
import com.arj.hicarehygiene.databinding.ActivityHomeBinding;
import com.arj.hicarehygiene.fragments.AccountFragment;
import com.arj.hicarehygiene.fragments.AddReferralFragment;
import com.arj.hicarehygiene.fragments.ComplaintHistoryFragment;
import com.arj.hicarehygiene.fragments.HomeFragment;
import com.arj.hicarehygiene.fragments.OfferFragment;
import com.arj.hicarehygiene.fragments.OrderFragment;
import com.arj.hicarehygiene.handler.UserHomeClickHandler;

public class HomeActivity extends BaseActivity  {
    ActivityHomeBinding mactivityHomeBinding;
    private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mactivityHomeBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_home);

        setSupportActionBar(mactivityHomeBinding.toolbar);

        addFragment(HomeFragment.newInstance(), "HomeActivity-HomeFragment");

        setupNavigationView();
//        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) mactivityHomeBinding.bottomNavigation.getLayoutParams();
//        layoutParams.setBehavior(new BottomNavigationBehavior());

//        CoordinatorLayout.LayoutParams layoutParams1 = (CoordinatorLayout.LayoutParams) mactivityHomeBinding.bottomNavigation1.getLayoutParams();
//        layoutParams1.setBehavior(new BottomNavigationBehavior());

//        initNavigationDrawer();
//        setAppBar();
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        this.menu = menu;
//        getMenuInflater().inflate(R.menu.menu_home, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        MenuItem search = menu.findItem(R.id.search);
//
//        switch (item.getItemId()) {
//            case R.id.cart:
//                Log.i("item id ", item.getItemId() + "");
//                Intent intent = new Intent(HomeActivity.this, CartActivity.class);
//                startActivity(intent);
//                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
//                return false;
//
//            case R.id.search:
//                intent = new Intent(HomeActivity.this, SearchActivity.class);
//                startActivity(intent);
//
//                return false;
//
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }


//    private void setAppBar() {
//
//        mactivityHomeBinding.appBar.addOnOffsetChangedListener(new AppBarLayout.BaseOnOffsetChangedListener() {
//            boolean isShow = false;
//            int scrollRange = -1;
//
//            @Override
//            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
//                if (scrollRange == -1) {
//                    scrollRange = appBarLayout.getTotalScrollRange();
//                }
//                if (scrollRange + verticalOffset == 0) {
//                    isShow = true;
//                    showOption(R.id.search);
//                } else if (isShow) {
//                    isShow = false;
//                    hideOption(R.id.search);
//                }
//            }
//        });
//    }


    private void showOption(int id) {
        MenuItem item = menu.findItem(id);
        item.setVisible(true);
    }

    private void hideOption(int id) {
        MenuItem item = menu.findItem(id);
        item.setVisible(false);
    }

    private void setupNavigationView() {
        if (mactivityHomeBinding.bottomNavigation != null) {

            // Select first menu item by default and show Fragment accordingly.
            Menu menu = mactivityHomeBinding.bottomNavigation.getMenu();

            selectFragment(menu.getItem(0));

            // Set action to perform when any menu-item is selected.
            mactivityHomeBinding.bottomNavigation.setOnNavigationItemSelectedListener(
                    new BottomNavigationView.OnNavigationItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                            selectFragment(item);
                            return false;
                        }
                    });
        }

//        if (mactivityHomeBinding.bottomNavigation1 != null) {
//
//            // Select first menu item by default and show Fragment accordingly.
//            Menu menu = mactivityHomeBinding.bottomNavigation1.getMenu();
//
//            selectFragment(menu.getItem(0));
//
//            // Set action to perform when any menu-item is selected.
//            mactivityHomeBinding.bottomNavigation1.setOnNavigationItemSelectedListener(
//                    new BottomNavigationView.OnNavigationItemSelectedListener() {
//                        @Override
//                        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                            selectFragment(item);
//                            return false;
//                        }
//                    });
//        }


    }


    private void selectFragment(MenuItem item) {
//        item.isChecked();
        item.setChecked(true);
//        mactivityHomeBinding.bottomNavigation.getMenu().getItem(item.getItemId()).setChecked(true);
        switch (item.getItemId()) {
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(mactivityHomeBinding.container.getId(), HomeFragment.newInstance()).addToBackStack(null).commit();
                break;
            case R.id.nav_orders:
                // Action to perform when Bag Menu item is selected.
                getSupportFragmentManager().beginTransaction().replace(mactivityHomeBinding.container.getId(), OrderFragment.newInstance()).addToBackStack(null).commit();
                break;
            case R.id.nav_offer:
                // Action to perform when Account Menu item is selected.
//                Intent intent = new Intent(HomeActivity.this, OtherActivity.class);
//                intent.putExtra("navigation","Offer");
                getSupportFragmentManager().beginTransaction().replace(mactivityHomeBinding.container.getId(), OfferFragment.newInstance()).addToBackStack(null).commit();

//                startActivity(intent);
                break;
            case R.id.nav_account:
//                intent = new Intent(HomeActivity.this, OtherActivity.class);
//                intent.putExtra("navigation","Account");
//                startActivity(intent);
                getSupportFragmentManager().beginTransaction().replace(mactivityHomeBinding.container.getId(), AccountFragment.newInstance()).addToBackStack(null).commit();
                break;
        }
    }


//    public void initNavigationDrawer() {
//        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
//        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(MenuItem menuItem) {
//
//                int id = menuItem.getItemId();
//
//                switch (id) {
//
//                    case R.id.nav_home:
////                        addFragment(ProfileFragment.newInstance(), "dashboard-profileFragment");
//                        getSupportFragmentManager().beginTransaction().replace(mactivityHomeBinding.container.getId(), HomeFragment.newInstance()).addToBackStack(null).commit();
//                        mactivityHomeBinding.drawer.closeDrawers();
//                        break;
//
//                    case R.id.nav_account:
////                        addFragment(ProfileFragment.newInstance(), "dashboard-profileFragment");
//                        getSupportFragmentManager().beginTransaction().replace(mactivityHomeBinding.container.getId(), AccountFragment.newInstance()).addToBackStack(null).commit();
//                        mactivityHomeBinding.drawer.closeDrawers();
//                        break;
//                    case R.id.nav_referral:
//                        mactivityHomeBinding.drawer.closeDrawers();
//                        break;
//
//                    case R.id.nav_money:
//                        mactivityHomeBinding.drawer.closeDrawers();
//                        break;
//
//                    case R.id.nav_complaint:
//                        getSupportFragmentManager().beginTransaction().replace(mactivityHomeBinding.container.getId(), ComplaintHistoryFragment.newInstance()).addToBackStack(null).commit();
//
//                        mactivityHomeBinding.drawer.closeDrawers();
//                        break;
//
//
//                    case R.id.nav_settings:
//                        mactivityHomeBinding.drawer.closeDrawers();
//                        break;
//
//                    case R.id.nav_logout:
//                        mactivityHomeBinding.drawer.closeDrawers();
//                        break;
//
//                    case R.id.nav_offer:
//                        mactivityHomeBinding.drawer.closeDrawers();
//                        break;
//                }
//                return true;
//            }
//        });
//
//        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, mactivityHomeBinding.drawer, mactivityHomeBinding.toolbar, R.string.openDrawer, R.string.closeDrawer) {
//
//            @Override
//            public void onDrawerClosed(View v) {
//                super.onDrawerClosed(v);
//            }
//
//            @Override
//            public void onDrawerOpened(View v) {
//                super.onDrawerOpened(v);
//            }
//        };
//        mactivityHomeBinding.drawer.addDrawerListener(actionBarDrawerToggle);
//        actionBarDrawerToggle.syncState();
//
//    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        int fragment = getSupportFragmentManager().getBackStackEntryCount();
//        Log.e("fragments", String.valueOf(fragment));
//        if (fragment < 1) {
//            showExitAlert();
//        } else {
//            getFragmentManager().popBackStack();
//        }
        MenuItem homeItem = mactivityHomeBinding.bottomNavigation.getMenu().getItem(0);

        if (mactivityHomeBinding.bottomNavigation.getSelectedItemId() == R.id.nav_home) {
            showExitAlert();
        } else {
            getSupportFragmentManager().popBackStack();
            mactivityHomeBinding.bottomNavigation.setSelectedItemId(R.id.nav_home);
//            selectFragment();
        }

//        if (mactivityHomeBinding.bottomNavigation.getSelectedItemId() != R.id.nav_home) {
//            mactivityHomeBinding.bottomNavigation.setSelectedItemId(R.id.nav_home);
//        } else {
//            super.onBackPressed();
//        }
    }


    private void showExitAlert() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Exit");
        dialog.setMessage("Do you want to exit?");
        dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        dialog.setNegativeButton("No", null);
        dialog.show();
    }




    public class BottomNavigationBehavior extends CoordinatorLayout.Behavior<BottomNavigationView> {

        private int height;

        @Override
        public boolean onLayoutChild(CoordinatorLayout parent, BottomNavigationView child, int layoutDirection) {
            height = child.getHeight();
            return super.onLayoutChild(parent, child, layoutDirection);
        }

        @Override
        public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout,
                                           BottomNavigationView child, @NonNull
                                                   View directTargetChild, @NonNull View target,
                                           int axes, int type) {
            return axes == ViewCompat.SCROLL_AXIS_VERTICAL;
        }

        @Override
        public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull BottomNavigationView child,
                                   @NonNull View target, int dxConsumed, int dyConsumed,
                                   int dxUnconsumed, int dyUnconsumed,
                                   @ViewCompat.NestedScrollType int type) {
            if (dyConsumed > 0) {
                slideDown(child);
            } else if (dyConsumed < 0) {
                slideUp(child);
            }
        }

        private void slideUp(BottomNavigationView child) {
            child.clearAnimation();
            child.animate().translationY(0).setDuration(120);
        }

        private void slideDown(BottomNavigationView child) {
            child.clearAnimation();
            child.animate().translationY(height).setDuration(120);
        }
    }
}
