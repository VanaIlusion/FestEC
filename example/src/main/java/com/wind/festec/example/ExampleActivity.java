package com.wind.festec.example;

import com.wind.latte.activitys.ProxyActivity;
import com.wind.latte.delegates.LatteDelegate;

public class ExampleActivity extends ProxyActivity {


    @Override
    public LatteDelegate setRootDelegate() {
        return new ExampleDelegate();
    }
}
