package com.arj.hicarehygiene.handler;

import android.view.View;

public interface UserAccountClickHandler {
    void onEditProfileClicked(View view);

    void onNotificationClicked(View view);

    void onMyOrdersClicked(View view);

    void onMyWalletClicked(View view);

    void onManageAddressClicked(View view);

    void onSignoutClicked(View view);

    void onHelpClicked(View view);

}
