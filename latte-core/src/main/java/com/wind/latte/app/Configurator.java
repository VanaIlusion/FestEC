package com.wind.latte.app;

import java.util.WeakHashMap;

/**
 * Created by theWind on 2017/8/9.
 */

public class Configurator {

    //据说WeakHashMap里面的键值对在不使用的时候就会回收，且非常及时，可以最大限度的避免内存爆满
    private static final WeakHashMap<String,Object> LATTE_CONFIGS = new WeakHashMap<>();
    public static WeakHashMap<String,Object> getLatteConfigs(){
        return LATTE_CONFIGS;
    }

    private Configurator(){
        LATTE_CONFIGS.put(ConfigType.CONGIG_READY.name(),false);
    }

    /**
     * 静态内部类单例模式的初始化
     * 非常完美的线程安全的懒汉模式
     */
    private static class Holder{
        private static final Configurator INSTANCE = new Configurator();
    }
    public static Configurator getInstance(){
        return Holder.INSTANCE;
    }

    public final void configure(){
        LATTE_CONFIGS.put(ConfigType.CONGIG_READY.name(),true);
    }

    /**
     *
     * @param host
     * @return :这里返回值类型的这种设计值得借鉴
     */
    public final Configurator withApiHost(String host){
        LATTE_CONFIGS.put(ConfigType.API_HOST.name(),host);
        return this;
    }

    /**
     * 检查配置项是否完成
     */
    private static void checkConfiguration(){
        final boolean isReady = (boolean) LATTE_CONFIGS.get(ConfigType.CONGIG_READY.name());
        if (isReady){
            throw  new RuntimeException("Configuration is not ready,call configure");
        }
    }

    //这里该注解：表示这个类型是没有检测过的，但并不抛出警告
    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Enum<ConfigType> key){
        checkConfiguration();
        return (T) LATTE_CONFIGS.get(key.name());
    }

}
