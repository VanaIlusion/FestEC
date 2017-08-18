package com.wind.festec.example.generator;

import com.wind.latte.annotations.EntryGenerator;
import com.wind.latte.wechat.templates.WXEntryTemplate;

/**
 * Created by theWind on 2017/8/12.
 */

@EntryGenerator(
        packageName =  "com.wind.festec.example",
        entryTemplete = WXEntryTemplate.class
)
public interface WeChatEntry {
}
