//package com.yogee.youge.common.push;
//
//import com.turo.pushy.apns.ApnsClientBuilder;
//import com.yogee.youge.common.config.Global;
//
//import java.util.List;
//import java.util.Map;
//
//public class PushNotificationTools
//{
//    public static final String IOS_BUNDLE_ID = Global.getConfig("ios.bundle.id");
//    public static final String IOS_P12_PATH = Global.getConfig("ios.p12.path");
//    public static final String IOS_P12_PASSWORD = Global.getConfig("ios.p12.password");
//    public static final String IOS_PUSH_TYPE = Global.getConfig("ios.push.type");
//    public static final String GETUI_APP_ID = Global.getConfig("getui.app.id");
//    public static final String GETUI_APP_KEY = Global.getConfig("getui.app.key");
//    public static final String GETUI_MASTER_SECRET = Global.getConfig("getui.master.secret");
//    public static final String GETUI_URL = Global.getConfig("getui.url");
//    public PushNotificationManager _notificationManager;
//
//    /**
//     * App推送
//     * @param deviceTokenList   IOs 设备token List
//     * @param CIDList           安卓 设备ID List
//     * @param alertTitle        标题
//     * @param alertBody         主体
//     * @param customMap         自定义Map
//     */
//    public void pushTo(List<String> deviceTokenList, List<String> CIDList, String alertTitle, String alertBody, Map customMap)
//    {
//        if (_notificationManager == null)
//        {
//            _notificationManager = new PushNotificationManager();
//        }
//        _notificationManager.pushToAPNs(deviceTokenList, alertTitle, "", alertBody, 1, customMap);
//        _notificationManager.pushToGeTui(CIDList, alertTitle, alertBody, customMap);
//    }
//
//
//}
