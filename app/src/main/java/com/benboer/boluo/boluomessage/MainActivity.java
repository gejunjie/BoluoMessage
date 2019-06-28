package com.benboer.boluo.boluomessage;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.benboer.boluo.core.activity.ProxyActivity;
import com.benboer.boluo.core.app.BoLuo;
import com.benboer.boluo.core.fragment.SupportFragment;
import com.benboer.boluo.factory.launcher.LauncherFragment;
import com.benboer.boluo.ui.launcher.ILauncherListener;

/**
 * Created by BenBoerBoluojiushiwo on 2019/6/26.
 */
public class MainActivity extends ProxyActivity implements ILauncherListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BoLuo.getConfigurator().withActivity(this);
    }

    @Override
    public SupportFragment setRootFragment() {
        return new LauncherFragment();
    }

    @Override
    public void onLauncherFinish(int launcherTag) {
        switch (launcherTag){
            case SINGED:
//                getSupportDelegate().popTo();
                break;
            case NOT_SINGED:
                break;
            default:
                break;
        }
    }
}
