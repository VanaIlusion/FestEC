package com.wind.festec.example.generator;

import com.wind.latte.annotations.PayEntryGenerator;
import com.wind.latte.wechat.templates.WXPayEntryTemplate;

/**
 * Created by theWind on 2017/8/12.
 */

@PayEntryGenerator(
        packageName = "com.wind.festec.example",
        payEntryTemplete = WXPayEntryTemplate.class
)
public interface WeChatPayEntry {
}
