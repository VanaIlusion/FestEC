package com.wind.latte.app;

import android.app.Activity;
import android.os.Handler;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Interceptor;

/**
 * 配置文件类
 * Created by theWind on 2017/8/9.
 */

public class Configurator {

    /**
     * 据说WeakHashMap里面的键值对在不使用的时候就会回收，且非常及时，可以最大限度的避免内存爆满
     * 由于LATTE_CONFIGS几乎伴随着整个应用的生命周期，这里显然不适用
     */
//    private static final WeakHashMap<String,Object> LATTE_CONFIGS = new WeakHashMap<>();
    private static final HashMap<Object, Object> LATTE_CONFIGS = new HashMap<>();
    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();//存储字体图标
    private static final ArrayList<Interceptor> INTERCEPTORS = new ArrayList<>();//拦截
    private static final Handler HANDLER = new Handler();

    /**
     * 配置开始了但是没有配置完成
     */
    private Configurator() {
        LATTE_CONFIGS.put(ConfigKeys.CONFIG_READY, false);
        LATTE_CONFIGS.put(ConfigKeys.HANDLER, HANDLER);
    }

    public static HashMap<Object, Object> getLatteConfigs() {
        return LATTE_CONFIGS;
    }

    private void initIcons() {
        if (ICONS.size() > 0) {//有字体了
            final Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            for (int i = 1; i < ICONS.size(); i++) {
                initializer.with(ICONS.get(i));
            }
        }
    }

    //是否调试阶段,用来做一些控制如log的打印
    public final Configurator withDebug(boolean isDebug) {
        LATTE_CONFIGS.put(ConfigKeys.IS_DEBUG, isDebug);
        return this;
    }

    public final Configurator withIcon(IconFontDescriptor descriptor) {
        ICONS.add(descriptor);
        return this;
    }

    public final Configurator withInterceptor(Interceptor interceptor) {
        INTERCEPTORS.add(interceptor);
        LATTE_CONFIGS.put(ConfigKeys.INTERCEPTOR, INTERCEPTORS);
        return this;
    }

    public final Configurator withInterceptors(ArrayList<Interceptor> interceptors) {
        INTERCEPTORS.addAll(interceptors);
        LATTE_CONFIGS.put(ConfigKeys.INTERCEPTOR, INTERCEPTORS);
        return this;
    }

    /**
     * 静态内部类单例模式的初始化
     * 非常完美的线程安全的懒汉模式
     */
    private static class Holder {
        private static final Configurator INSTANCE = new Configurator();
    }

    public static Configurator getInstance() {
        return Holder.INSTANCE;
    }

    public final void configure() {
        LATTE_CONFIGS.put(ConfigKeys.CONFIG_READY, true);
        initIcons();
    }

    public final Configurator withLoaderDelayed(long delayed) {
        LATTE_CONFIGS.put(ConfigKeys.LOADER_DELAYED, delayed);
        return this;
    }

    /**
     * @param host
     * @return :这里返回值类型的这种设计值得借鉴,一种标准初始化的思路
     */
    public final Configurator withApiHost(String host) {
        LATTE_CONFIGS.put(ConfigKeys.API_HOST, host);
        return this;
    }

    /**
     * 微信配置
     *
     * @param appSecret
     * @return
     */
    public final Configurator withWeChatAppSecret(String appSecret) {
        LATTE_CONFIGS.put(ConfigKeys.WE_CHAT_APP_SECRET, appSecret);
        return this;
    }

    /**
     * 配置微信的appId
     *
     * @param appId
     * @return
     */
    public final Configurator withWeChatAppId(String appId) {
        LATTE_CONFIGS.put(ConfigKeys.WE_CHAT_APP_ID, appId);
        return this;
    }

    /**
     * 微信拉取回调Activity时，会需要一个Activity的上下文，这里显然不能用全局的Context
     *
     * @param activity
     * @return
     */
    public final Configurator withWeChatActivity(Activity activity) {
        LATTE_CONFIGS.put(ConfigKeys.ACTIVITY, activity);
        return this;
    }

    /**
     * 检查配置项是否完成
     */
    private static void checkConfiguration() {
        //在写类变量或方法变量时，尽量让它的不可变性达到最大化
        final boolean isReady = (boolean) LATTE_CONFIGS.get(ConfigKeys.CONFIG_READY);
        if (!isReady) {
            throw new RuntimeException("Configuration is not ready,call configure");
        }
    }

    //这里该注解：表示这个类型是没有检测过的，但并不抛出警告
    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Object key) {
        checkConfiguration();
        return (T) LATTE_CONFIGS.get(key);
    }

}
