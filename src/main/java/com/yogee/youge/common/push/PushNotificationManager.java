package com.yogee.youge.common.push;

//import com.alibaba.fastjson.JSON;
import com.gexin.fastjson.JSON;
import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.ListMessage;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.LinkTemplate;
import com.gexin.rp.sdk.template.style.Style0;
import com.turo.pushy.apns.ApnsClient;
import com.turo.pushy.apns.ApnsClientBuilder;
import com.turo.pushy.apns.PushNotificationResponse;
import com.turo.pushy.apns.util.ApnsPayloadBuilder;
import com.turo.pushy.apns.util.SimpleApnsPushNotification;
import com.turo.pushy.apns.util.TokenUtil;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class PushNotificationManager
{
    public static void main(String args[]) throws IOException
    {
        List<String> tokenList = new ArrayList<String>();
        tokenList.add("1a1178df89303a1896f26c353eb349f4d21cc7310fbbddfb179f6bfd4b16efc5");
        PushNotificationManager pushTest = new PushNotificationManager();
        Map map = new HashMap();
        map.put("id", "asduasaspdjpasjd");
        map.put("img", "http://");
        map.put("id", "asduasaspdjpasjd");
        pushTest.pushToAPNs(tokenList, "这是推送", "这是subTitle", "这是body", 99, map);

    }


    /**
     * getui4Android
     * @param CIDList       设备id List
     * @param alertTitle    推送标题
     * @param alertBody     推送简介
     * @param customMap     自定义Map
     */
    public void pushToGeTui(List<String> CIDList, String alertTitle, String alertBody, Map customMap)
    {
        IGtPush push = new IGtPush(PushNotificationTools.GETUI_URL, PushNotificationTools.GETUI_APP_KEY, PushNotificationTools.GETUI_MASTER_SECRET);

        customMap.put("title", alertTitle);
        customMap.put("detail", alertBody);
        // 定义"点击链接打开通知模板"，并设置标题、内容、链接
        LinkTemplate template = new LinkTemplate();
        template.setAppId(PushNotificationTools.GETUI_APP_ID);
        template.setAppkey(PushNotificationTools.GETUI_APP_KEY);
        Style0 style0 = new Style0();
        //如果有问题请用alibaba.fastjson
        style0.setText(JSON.toJSONString(customMap));
        style0.setRing(true);
        style0.setVibrate(true);
        style0.setClearable(true);
        template.setStyle(style0);

        //单推
        if(CIDList.size() == 1)
        {
            SingleMessage message = new SingleMessage();
            message.setData(template);
            message.setOffline(true);
            message.setOfflineExpireTime(1000 * 600);
            message.setPushNetWorkType(0);
            Target target = new Target();
            target.setAppId(PushNotificationTools.GETUI_APP_ID);
            target.setClientId(CIDList.get(0));
            IPushResult ret = push.pushMessageToSingle(message, target);
            System.out.println(ret.getResponse().toString());
        }
        else
        {
            List<Target> targets = new ArrayList<Target>();
            for (String cid:CIDList){
                Target target = new Target();
                target.setAppId(PushNotificationTools.GETUI_APP_ID);
                target.setClientId(cid);
                targets.add(target);
            }
            ListMessage message = new ListMessage();
            message.setData(template);
            message.setOffline(true);
            message.setOfflineExpireTime(1000 * 600);
            message.setPushNetWorkType(0);
            IPushResult ret = push.pushMessageToList(push.getContentId(message), targets);
            System.out.println(ret.getResponse().toString());
        }
    }

//    private static final Logger logger = LoggerFactory.getLogger(PushTest.class);

    private static ApnsClient apnsClient = null;

    private static final Semaphore semaphore = new Semaphore(10000);

    /**
     * push4IOs
     * @param deviceTokenList   设备token List
     * @param alertTitle        标题
     * @param alertSubTitle     小标题
     * @param alertBody         主体
     * @param badge             未读消息数
     * @param customMap         自定义Map
     */
    public void pushToAPNs(List<String> deviceTokenList, String alertTitle, String alertSubTitle,
                           String alertBody, Integer badge, Map customMap)
    {
        long startTime = System.currentTimeMillis();

        if (apnsClient == null)
        {
            try
            {
                EventLoopGroup eventLoopGroup = new NioEventLoopGroup(4);
                apnsClient = new ApnsClientBuilder().setApnsServer(PushNotificationTools.IOS_PUSH_TYPE).setClientCredentials(
                        new File(PushNotificationTools.IOS_P12_PATH),
                        PushNotificationTools.IOS_P12_PASSWORD).setConcurrentConnections(4).setEventLoopGroup(eventLoopGroup).build();
            }
            catch (Exception e)
            {
                System.out.println("ios get pushy apns client failed!");
                e.printStackTrace();
            }
        }

        long total = deviceTokenList.size();

        final CountDownLatch latch = new CountDownLatch(deviceTokenList.size());

        final AtomicLong successCnt = new AtomicLong(0);

        long startPushTime = System.currentTimeMillis();

        for (String deviceToken : deviceTokenList)
        {
            ApnsPayloadBuilder payloadBuilder = new ApnsPayloadBuilder();
            payloadBuilder.setAlertBody(alertBody);
            payloadBuilder.setAlertTitle(alertTitle);
            payloadBuilder.setBadgeNumber(badge);
            payloadBuilder.setAlertSubtitle(alertSubTitle);
            payloadBuilder.addCustomProperty("customDic", customMap);

            String payload = payloadBuilder.buildWithDefaultMaximumLength();
            final String token = TokenUtil.sanitizeTokenString(deviceToken);
            SimpleApnsPushNotification pushNotification = new SimpleApnsPushNotification(token,
                                                                                         PushNotificationTools
                                                                                                 .IOS_BUNDLE_ID,
                                                                                         payload);

            try
            {
                semaphore.acquire();
            }
            catch (InterruptedException e)
            {
                System.out.println("ios push get semaphore failed, deviceToken:" + deviceToken);
                e.printStackTrace();
            }
            final Future<PushNotificationResponse<SimpleApnsPushNotification>> future = apnsClient.sendNotification(
                    pushNotification);

            future.addListener(new GenericFutureListener<Future<PushNotificationResponse>>()
            {
                @Override
                public void operationComplete(Future<PushNotificationResponse> pushNotificationResponseFuture) throws Exception
                {
                    if (future.isSuccess())
                    {
                        final PushNotificationResponse<SimpleApnsPushNotification> response = future.getNow();
                        if (response.isAccepted())
                        {
                            successCnt.incrementAndGet();
                        }
                        else
                        {
                            Date invalidTime = response.getTokenInvalidationTimestamp();
                            System.out.println("Notification rejected by the APNs gateway: " + response.getRejectionReason());
                            if (invalidTime != null)
                            {
                                System.out.println("\t…and the token is invalid as of " + response.getTokenInvalidationTimestamp());
                            }
                        }
                    }
                    else
                    {
                        System.out.println("send notification device token="+token+" is failed  "+
                                     future.cause().getMessage());
                    }
                    latch.countDown();
                    semaphore.release();
                }
            });
        }

        try
        {
            latch.await(20, TimeUnit.SECONDS);
        }
        catch (InterruptedException e)
        {
            System.out.println("ios push latch await failed!");
            e.printStackTrace();
        }

        long endPushTime = System.currentTimeMillis();

        System.out.println("test pushMessage success. [共推送" + total + "个][成功" + (successCnt.get()) + "个], totalcost = " + (endPushTime - startTime) + ", pushCost = " + (endPushTime - startPushTime));
    }
}
