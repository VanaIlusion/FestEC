package com.wind.festec.example.generator;

import com.wind.latte.annotations.AppRegisterGenerator;
import com.wind.latte.wechat.templates.AppRegisterTemplate;

/**
 * Created by theWind on 2017/8/12.
 */

@AppRegisterGenerator(
        packageName = "com.wind.festec.example",
        registerTemplete = AppRegisterTemplate.class
)
public interface AppRegister {
}
