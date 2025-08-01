package io.github.rushiranpise.gameunlocker;

import android.os.Build;
import android.util.Log;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class GAMEUNLOCKER implements IXposedHookLoadPackage {

    private static final String TAG = "GAMEUNLOCKER";
    private static final Map<String, Map<String, String>> SPOOF_PROPS = new HashMap<>();
    private volatile boolean keepSpoofing = true; // Flag to control the spoofing thread

    static {
        // REDMAGIC 9
        SPOOF_PROPS.put("com.mobilelegends.mi", new HashMap<String, String>() {{
            put("BRAND", "ZTE"); put("DEVICE", "NX769J"); put("MANUFACTURER", "ZTE"); put("MODEL", "NX769J");
        }});
        SPOOF_PROPS.put("com.supercell.brawlstars", new HashMap<String, String>() {{
            put("BRAND", "ZTE"); put("DEVICE", "NX769J"); put("MANUFACTURER", "ZTE"); put("MODEL", "NX769J");
        }});
        SPOOF_PROPS.put("com.blizzard.diablo.immortal", new HashMap<String, String>() {{
            put("BRAND", "ZTE"); put("DEVICE", "NX769J"); put("MANUFACTURER", "ZTE"); put("MODEL", "NX769J");
        }});
        SPOOF_PROPS.put("com.netease.newspike", new HashMap<String, String>() {{
            put("BRAND", "ZTE"); put("DEVICE", "NX769J"); put("MANUFACTURER", "ZTE"); put("MODEL", "NX769J");
        }});
        SPOOF_PROPS.put("com.activision.callofduty.warzone", new HashMap<String, String>() {{
            put("BRAND", "ZTE"); put("DEVICE", "NX769J"); put("MANUFACTURER", "ZTE"); put("MODEL", "NX769J");
        }});
        SPOOF_PROPS.put("com.pubg.newstate", new HashMap<String, String>() {{
            put("BRAND", "ZTE"); put("DEVICE", "NX769J"); put("MANUFACTURER", "ZTE"); put("MODEL", "NX769J");
        }});
        SPOOF_PROPS.put("com.gamedevltd.destinywarfare", new HashMap<String, String>() {{
            put("BRAND", "ZTE"); put("DEVICE", "NX769J"); put("MANUFACTURER", "ZTE"); put("MODEL", "NX769J");
        }});
        SPOOF_PROPS.put("com.pikpok.dr2.play", new HashMap<String, String>() {{
            put("BRAND", "ZTE"); put("DEVICE", "NX769J"); put("MANUFACTURER", "ZTE"); put("MODEL", "NX769J");
        }});
        SPOOF_PROPS.put("com.CarXTech.highWay", new HashMap<String, String>() {{
            put("BRAND", "ZTE"); put("DEVICE", "NX769J"); put("MANUFACTURER", "ZTE"); put("MODEL", "NX769J");
        }});
        SPOOF_PROPS.put("com.nekki.shadowfight3", new HashMap<String, String>() {{
            put("BRAND", "ZTE"); put("DEVICE", "NX769J"); put("MANUFACTURER", "ZTE"); put("MODEL", "NX769J");
        }});
        SPOOF_PROPS.put("com.nekki.shadowfightarena", new HashMap<String, String>() {{
            put("BRAND", "ZTE"); put("DEVICE", "NX769J"); put("MANUFACTURER", "ZTE"); put("MODEL", "NX769J");
        }});
        SPOOF_PROPS.put("com.gameloft.android.ANMP.GloftA8HM", new HashMap<String, String>() {{
            put("BRAND", "ZTE"); put("DEVICE", "NX769J"); put("MANUFACTURER", "ZTE"); put("MODEL", "NX769J");
        }});
        SPOOF_PROPS.put("com.nekki.shadowfight", new HashMap<String, String>() {{
            put("BRAND", "ZTE"); put("DEVICE", "NX769J"); put("MANUFACTURER", "ZTE"); put("MODEL", "NX769J");
        }});
        SPOOF_PROPS.put("com.ea.game.nfs14_row", new HashMap<String, String>() {{
            put("BRAND", "ZTE"); put("DEVICE", "NX769J"); put("MANUFACTURER", "ZTE"); put("MODEL", "NX769J");
        }});
        SPOOF_PROPS.put("com.ea.games.r3_row", new HashMap<String, String>() {{
            put("BRAND", "ZTE"); put("DEVICE", "NX769J"); put("MANUFACTURER", "ZTE"); put("MODEL", "NX769J");
        }});
        SPOOF_PROPS.put("com.supercell.squad", new HashMap<String, String>() {{
            put("BRAND", "ZTE"); put("DEVICE", "NX769J"); put("MANUFACTURER", "ZTE"); put("MODEL", "NX769J");
        }});
        SPOOF_PROPS.put("com.blitzteam.battleprime", new HashMap<String, String>() {{
            put("BRAND", "ZTE"); put("DEVICE", "NX769J"); put("MANUFACTURER", "ZTE"); put("MODEL", "NX769J");
        }});
        SPOOF_PROPS.put("com.tencent.tmgp.gnyx", new HashMap<String, String>() {{
            put("BRAND", "ZTE"); put("DEVICE", "NX769J"); put("MANUFACTURER", "ZTE"); put("MODEL", "NX769J");
        }});

        // Black Shark 4
        SPOOF_PROPS.put("com.proximabeta.mf.uamo", new HashMap<String, String>() {{
            put("BRAND", "Black Shark"); put("DEVICE", "Black Shark 4"); put("MANUFACTURER", "Xiaomi"); put("MODEL", "2SM-X706B");
        }});

        // Mi 11T Pro
        SPOOF_PROPS.put("com.ea.gp.apexlegendsmobilefps", new HashMap<String, String>() {{
            put("BRAND", "Xiaomi"); put("DEVICE", "Mi 11T PRO"); put("MANUFACTURER", "Xiaomi"); put("MODEL", "2107113SI");
        }});
        SPOOF_PROPS.put("com.levelinfinite.hotta.gp", new HashMap<String, String>() {{
            put("BRAND", "Xiaomi"); put("DEVICE", "Mi 11T PRO"); put("MANUFACTURER", "Xiaomi"); put("MODEL", "2107113SI");
        }});
        SPOOF_PROPS.put("com.supercell.clashofclans", new HashMap<String, String>() {{
            put("BRAND", "Xiaomi"); put("DEVICE", "Mi 11T PRO"); put("MANUFACTURER", "Xiaomi"); put("MODEL", "2107113SI");
        }});
        SPOOF_PROPS.put("com.vng.mlbbvn", new HashMap<String, String>() {{
            put("BRAND", "Xiaomi"); put("DEVICE", "Mi 11T PRO"); put("MANUFACTURER", "Xiaomi"); put("MODEL", "2107113SI");
        }});

        // Mi 13 Pro
        SPOOF_PROPS.put("com.levelinfinite.sgameGlobal", new HashMap<String, String>() {{
            put("BRAND", "Xiaomi"); put("DEVICE", "Mi 13 Pro"); put("MANUFACTURER", "Xiaomi"); put("MODEL", "2210132G");
        }});
        SPOOF_PROPS.put("com.tencent.tmgp.sgame", new HashMap<String, String>() {{
            put("BRAND", "Xiaomi"); put("DEVICE", "Mi 13 Pro"); put("MANUFACTURER", "Xiaomi"); put("MODEL", "2210132G");
        }});
        SPOOF_PROPS.put("com.pubg.krmobile", new HashMap<String, String>() {{
            put("BRAND", "Xiaomi"); put("DEVICE", "Mi 13 Pro"); put("MANUFACTURER", "Xiaomi"); put("MODEL", "2210132G");
        }});
        SPOOF_PROPS.put("com.rekoo.pubgm", new HashMap<String, String>() {{
            put("BRAND", "Xiaomi"); put("DEVICE", "Mi 13 Pro"); put("MANUFACTURER", "Xiaomi"); put("MODEL", "2210132G");
        }});
        SPOOF_PROPS.put("com.tencent.tmgp.pubgmhd", new HashMap<String, String>() {{
            put("BRAND", "Xiaomi"); put("DEVICE", "Mi 13 Pro"); put("MANUFACTURER", "Xiaomi"); put("MODEL", "2210132G");
        }});
        SPOOF_PROPS.put("com.vng.pubgmobile", new HashMap<String, String>() {{
            put("BRAND", "Xiaomi"); put("DEVICE", "Mi 13 Pro"); put("MANUFACTURER", "Xiaomi"); put("MODEL", "2210132G");
        }});
        SPOOF_PROPS.put("com.pubg.imobile", new HashMap<String, String>() {{
            put("BRAND", "Xiaomi"); put("DEVICE", "Mi 13 Pro"); put("MANUFACTURER", "Xiaomi"); put("MODEL", "2210132G");
        }});
        SPOOF_PROPS.put("com.tencent.ig", new HashMap<String, String>() {{
            put("BRAND", "Xiaomi"); put("DEVICE", "Mi 13 Pro"); put("MANUFACTURER", "Xiaomi"); put("MODEL", "2210132G");
        }});

        // OnePlus 8 Pro
        SPOOF_PROPS.put("com.netease.lztgglobal", new HashMap<String, String>() {{
            put("BRAND", "OnePlus"); put("DEVICE", "OnePlus 8 PRO"); put("MANUFACTURER", "OnePlus"); put("MODEL", "IN2020");
        }});
        SPOOF_PROPS.put("com.riotgames.league.wildrift", new HashMap<String, String>() {{
            put("BRAND", "OnePlus"); put("DEVICE", "OnePlus 8 PRO"); put("MANUFACTURER", "OnePlus"); put("MODEL", "IN2020");
        }});
        SPOOF_PROPS.put("com.riotgames.league.wildrifttw", new HashMap<String, String>() {{
            put("BRAND", "OnePlus"); put("DEVICE", "OnePlus 8 PRO"); put("MANUFACTURER", "OnePlus"); put("MODEL", "IN2020");
        }});
        SPOOF_PROPS.put("com.riotgames.league.wildriftvn", new HashMap<String, String>() {{
            put("BRAND", "OnePlus"); put("DEVICE", "OnePlus 8 PRO"); put("MANUFACTURER", "OnePlus"); put("MODEL", "IN2020");
        }});

        // OnePlus 9 Pro
        SPOOF_PROPS.put("com.epicgames.fortnite", new HashMap<String, String>() {{
            put("BRAND", "OnePlus"); put("DEVICE", "OnePlus 9 PRO"); put("MANUFACTURER", "OnePlus"); put("MODEL", "LE2101");
        }});
        SPOOF_PROPS.put("com.epicgames.portal", new HashMap<String, String>() {{
            put("BRAND", "OnePlus"); put("DEVICE", "OnePlus 9 PRO"); put("MANUFACTURER", "OnePlus"); put("MODEL", "LE2101");
        }});
        SPOOF_PROPS.put("com.tencent.lolm", new HashMap<String, String>() {{
            put("BRAND", "OnePlus"); put("DEVICE", "OnePlus 9 PRO"); put("MANUFACTURER", "OnePlus"); put("MODEL", "LE2101");
        }});

        // POCO F5
        SPOOF_PROPS.put("com.mobile.legends", new HashMap<String, String>() {{
            put("BRAND", "POCO"); put("DEVICE", "POCO F5"); put("MANUFACTURER", "Xiaomi"); put("MODEL", "23049PCD8G");
        }});

        // Realme 14
        SPOOF_PROPS.put("com.dts.freefireth", new HashMap<String, String>() {{
            put("BRAND", "Realme"); put("DEVICE", "Realme 14"); put("MANUFACTURER", "Realme"); put("MODEL", "RMX5070");
        }});
        SPOOF_PROPS.put("com.dts.freefirethmax", new HashMap<String, String>() {{
            put("BRAND", "Realme"); put("DEVICE", "Realme 14"); put("MANUFACTURER", "Realme"); put("MODEL", "RMX5070");
        }});

        // ROG Phone 6
        SPOOF_PROPS.put("com.ea.gp.fifamobile", new HashMap<String, String>() {{
            put("BRAND", "Asus"); put("DEVICE", "ROG Phone 6"); put("MANUFACTURER", "Asus"); put("MODEL", "ASUS_AI2201");
        }});
        SPOOF_PROPS.put("com.gameloft.android.ANMP.GloftA8HM", new HashMap<String, String>() {{
            put("BRAND", "Asus"); put("DEVICE", "ROG Phone 6"); put("MANUFACTURER", "Asus"); put("MODEL", "ASUS_AI2201");
        }});
        SPOOF_PROPS.put("com.madfingergames.legends", new HashMap<String, String>() {{
            put("BRAND", "Asus"); put("DEVICE", "ROG Phone 6"); put("MANUFACTURER", "Asus"); put("MODEL", "ASUS_AI2201");
        }});
        SPOOF_PROPS.put("com.pearlabyss.blackdesertm", new HashMap<String, String>() {{
            put("BRAND", "Asus"); put("DEVICE", "ROG Phone 6"); put("MANUFACTURER", "Asus"); put("MODEL", "ASUS_AI2201");
        }});
        SPOOF_PROPS.put("com.pearlabyss.blackdesertm.gl", new HashMap<String, String>() {{
            put("BRAND", "Asus"); put("DEVICE", "ROG Phone 6"); put("MANUFACTURER", "Asus"); put("MODEL", "ASUS_AI2201");
        }});

        // Lenovo Tablet
        SPOOF_PROPS.put("com.activision.callofduty.shooter", new HashMap<String, String>() {{
            put("BRAND", "Lenovo"); put("DEVICE", "TB-9707F"); put("MANUFACTURER", "Lenovo"); put("MODEL", "Lenovo TB-9707F");
        }});
        SPOOF_PROPS.put("com.garena.game.codm", new HashMap<String, String>() {{
            put("BRAND", "Lenovo"); put("DEVICE", "TB-9707F"); put("MANUFACTURER", "Lenovo"); put("MODEL", "Lenovo TB-9707F");
        }});
        SPOOF_PROPS.put("com.garena.game.df", new HashMap<String, String>() {{
            put("BRAND", "Lenovo"); put("DEVICE", "TB-9707F"); put("MANUFACTURER", "Lenovo"); put("MODEL", "Lenovo TB-9707F");
        }});
        SPOOF_PROPS.put("com.tencent.tmgp.kr.codm", new HashMap<String, String>() {{
            put("BRAND", "Lenovo"); put("DEVICE", "TB-9707F"); put("MANUFACTURER", "Lenovo"); put("MODEL", "Lenovo TB-9707F");
        }});
        SPOOF_PROPS.put("com.vng.codmvn", new HashMap<String, String>() {{
            put("BRAND", "Lenovo"); put("DEVICE", "TB-9707F"); put("MANUFACTURER", "Lenovo"); put("MODEL", "Lenovo TB-9707F");
        }});

        // Samsung S24 Ultra
        SPOOF_PROPS.put("com.ea.gp.fifamobile", new HashMap<String, String>() {{
            put("BRAND", "samsung"); put("DEVICE", "e3q"); put("MANUFACTURER", "samsung"); put("MODEL", "SM-S928B");
        }}); // Note: Overlaps with ROG Phone 6, last entry wins
    }

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) {
        String packageName = lpparam.packageName;

        if (SPOOF_PROPS.containsKey(packageName)) {
            try {
                spoofBuildProperties(lpparam, packageName);
                hideXposed(lpparam);
                startSpoofingRepeater(packageName); // Start periodic spoofing
                XposedBridge.log("Spoofing " + packageName + " with 5-second repetition and crash handling");
            } catch (Exception e) {
                XposedBridge.log("Error initializing spoofing for " + packageName + ": " + Log.getStackTraceString(e));
            }
        }
    }

    private void spoofBuildProperties(XC_LoadPackage.LoadPackageParam lpparam, String packageName) {
        Map<String, String> props = SPOOF_PROPS.get(packageName);

        // Initial static spoofing
        setBuildProperties(props);

        // Hook Build field getters
        try {
            XposedHelpers.findAndHookMethod("android.os.Build", lpparam.classLoader,
                "getString", String.class, new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) {
                        String key = (String) param.args[0];
                        if (props.containsKey(key)) {
                            param.setResult(props.get(key));
                        }
                    }
                });
        } catch (Exception e) {
            XposedBridge.log("Failed to hook Build.getString for " + packageName + ": " + e.getMessage());
        }

        // Hook SystemProperties
        try {
            XposedHelpers.findAndHookMethod("android.os.SystemProperties", lpparam.classLoader,
                "get", String.class, new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) {
                        String key = (String) param.args[0];
                        if ("ro.product.brand".equals(key)) param.setResult(props.get("BRAND"));
                        if ("ro.product.manufacturer".equals(key)) param.setResult(props.get("MANUFACTURER"));
                        if ("ro.product.device".equals(key)) param.setResult(props.get("DEVICE"));
                        if ("ro.product.model".equals(key)) param.setResult(props.get("MODEL"));
                    }
                });
        } catch (Exception e) {
            XposedBridge.log("Failed to hook SystemProperties.get for " + packageName + ": " + e.getMessage());
        }
    }

    private void hideXposed(XC_LoadPackage.LoadPackageParam lpparam) {
        try {
            XposedHelpers.findAndHookMethod("java.lang.ClassLoader", lpparam.classLoader,
                "loadClass", String.class, new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) {
                        String className = (String) param.args[0];
                        if (className.contains("Xposed") || className.contains("de.robv")) {
                            param.setThrowable(new ClassNotFoundException("Hidden"));
                        }
                    }
                });
        } catch (Exception e) {
            XposedBridge.log("Failed to hook ClassLoader.loadClass: " + e.getMessage());
        }

        try {
            XposedHelpers.findAndHookMethod("java.lang.Thread", lpparam.classLoader,
                "getStackTrace", new XC_MethodHook() {
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) {
                        StackTraceElement[] stack = (StackTraceElement[]) param.getResult();
                        param.setResult(Arrays.stream(stack)
                            .filter(e -> !e.getClassName().contains("Xposed"))
                            .toArray(StackTraceElement[]::new));
                    }
                });
        } catch (Exception e) {
            XposedBridge.log("Failed to hook Thread.getStackTrace: " + e.getMessage());
        }
    }

    private void startSpoofingRepeater(String packageName) {
        Map<String, String> props = SPOOF_PROPS.get(packageName);
        new Thread(() -> {
            while (keepSpoofing) {
                try {
                    setBuildProperties(props);
                    XposedBridge.log("Reapplied spoofing for " + packageName);
                    Thread.sleep(5000); // Repeat every 5 seconds
                } catch (InterruptedException e) {
                    XposedBridge.log("Spoofing thread interrupted for " + packageName);
                    break;
                } catch (Exception e) {
                    XposedBridge.log("Error in spoofing repeater for " + packageName + ": " + e.getMessage());
                    // Continue running despite error to avoid stopping the loop
                }
            }
        }).start();
    }

    private void setBuildProperties(Map<String, String> props) {
        for (Map.Entry<String, String> entry : props.entrySet()) {
            try {
                Field field = Build.class.getDeclaredField(entry.getKey());
                field.setAccessible(true);
                field.set(null, entry.getValue());
                field.setAccessible(false);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                XposedBridge.log("Failed to set Build." + entry.getKey() + " to " + entry.getValue() + ": " + e.getMessage());
            }
        }
    }

    // Cleanup method (optional, not directly callable by Xposed, but good practice)
    public void onPackageUnload() {
        keepSpoofing = false; // Stop the thread when the package unloads
    }
}
