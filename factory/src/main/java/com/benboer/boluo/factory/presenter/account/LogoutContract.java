package com.benboer.boluo.factory.presenter.account;

import com.benboer.boluo.factory.presenter.BaseContract;

/**
 * Created by BenBoerBoluojiushiwo on 2019/6/21.
 */
public interface LogoutContract {

    interface View extends BaseContract.View<LogoutContract.Presenter>{

        void logoutSuccess();
    }

    interface Presenter extends BaseContract.Presenter{

        void logout(String phone);
    }
}
