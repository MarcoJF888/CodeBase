package com.zjf.myself.codebase.util;


import android.content.Context;
import android.os.IBinder;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class ImeiUtil {
    public static List<Object> getIphonesubinfoList() throws Exception {
        List<Object> ret = new ArrayList<Object>();

        Class<?> cls_sm = Class.forName("android.os.ServiceManager");
        Method m_listservice = cls_sm.getMethod("listServices");
        Method m_getservice = cls_sm.getMethod("getService", String.class);

        Class<?> cls_stub = Class.forName("com.android.internal.telephony.IPhoneSubInfo$Stub");
        Method m_asintf = cls_stub.getMethod("asInterface", IBinder.class);

        Log.i("", "find iphonesubinfo using listServices method");
        String[] services = (String[]) m_listservice.invoke(null);
        for (String service : services) {
            if (service != null && service.contains("iphonesubinfo")) {
                Log.i("", "found service " + service);
                IBinder b = (IBinder) m_getservice.invoke(null, service);
                Object obj = m_asintf.invoke(null, b);
                ret.add(obj);
            }
        }

        // try old method for inferior phones
        if (ret.size() < 1) {
            Log.i("", "find iphonesubinfo using blackbox method");
            String[] list = { "iphonesubinfo", "iphonesubinfo1", "iphonesubinfo2", "iphonesubinfo3", "iphonesubinfo4",
                    "iphonesubinfo5" };
            for (String service : list) {
                IBinder b = (IBinder) m_getservice.invoke(null, service);
                if (b != null) {
                    Log.i("", "found service " + service);
                    Object obj = m_asintf.invoke(null, b);
                    ret.add(obj);
                }
            }
        }

        return ret;
    }

    public static Map<String, String> getImeiMap(Context context) {
        Map<String, String> map = new HashMap<String, String>();

        // let's get from common methods first
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String imei = tm.getDeviceId();
        String imsi = tm.getSubscriberId();
        if (imei != null && imei.length() > 0) {
            map.put(imei, imsi);
            Log.i("common sdk method", "IMEI " + imei + " -> IMSI " + imsi);
            return map;
        }

        do {
            Class<?> cls_iphonesubinfo ;
            Method m_getimei = null;
            Method m_getimsi = null;
            Method m_getdualimei = null;
            Method m_getdualimsi = null;

            try {
                cls_iphonesubinfo = Class.forName("com.android.internal.telephony.IPhoneSubInfo");
                m_getimei = cls_iphonesubinfo.getMethod("getDeviceId");
                m_getimsi = cls_iphonesubinfo.getMethod("getSubscriberId");
                m_getdualimei = cls_iphonesubinfo.getMethod("getDualDeviceId", int.class);
                m_getdualimsi = cls_iphonesubinfo.getMethod("getDualSubscriberId", int.class);
            } catch (Exception e) {
            }

            try {
                List<Object> objList = getIphonesubinfoList();
                for (Object obj : objList) {
                    imei = (String) m_getimei.invoke(obj);
                    imsi = (String) m_getimsi.invoke(obj);
                    if (imei != null && imei.length() > 0) {
                        map.put(imei, imsi);
//                        Log.i("IPhoneSubInfo.getxx method", "IMEI " + imei + " -> IMSI " + imsi);
                    }

                    if (m_getdualimei != null && m_getdualimsi != null) {
                        for (int i = 0; i < 5; i++) {
                            imei = (String) m_getdualimei.invoke(obj, i);
                            imsi = (String) m_getdualimsi.invoke(obj, i);
                            if (imei != null && imei.length() > 0) {
                                map.put(imei, imsi);
//                                Log.i("IPhoneSubInfo.getDualxx method " + i, "IMEI " + imei + " -> IMSI " + imsi);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } while (false);
        return map;
    }

    public static List<String> getImeiList(Context context) {
        List<String> ret = new ArrayList<String>();
        Map<String, String> map = getImeiMap(context);
        Set<Entry<String, String>> sets = map.entrySet();
        for (Entry<String, String> entry : sets) {
            ret.add(entry.getKey());
        }
        return ret;
    }

    public static List<String> getImsiList(Context context) {
        List<String> ret = new ArrayList<String>();
        Map<String, String> map = getImeiMap(context);
        Set<Entry<String, String>> sets = map.entrySet();
        for (Entry<String, String> entry : sets) {
            ret.add(entry.getValue());
        }
        return ret;
    }

    public static String getImei(Context context) {
        String ret = null;
        Map<String, String> map = getImeiMap(context);
        Set<Entry<String, String>> sets = map.entrySet();
        for (Entry<String, String> entry : sets) {
            if (ret == null || entry.getKey().compareToIgnoreCase(ret) < 0) {
                ret = entry.getKey();
            }
        }

        if (ret == null) {
            ret = "null";
        }
        return ret;
    }

    public static class StringComparator implements Comparator<String> {

        public int compare(String arg0, String arg1) {
            return arg0.compareToIgnoreCase(arg1);
        }

    }
}
