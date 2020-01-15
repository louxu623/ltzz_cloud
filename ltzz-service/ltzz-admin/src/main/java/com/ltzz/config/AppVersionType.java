package com.sqqmall.config;

/**
 * Created by lijian on 2019/6/12.
 */
public enum AppVersionType {
    /**
     * ios
     */
    IOS(0),
    /**
     * android
     */
    ANDROID(1),
    /**
     * ios_appstore
     */
    IOS_APPSTORE(2);

    private int type;

    AppVersionType(int type) {
        this.type = type;
    }

    public int getType() {
        return this.type;
    }

    public static boolean checkExist(int type) {
        if (type == AppVersionType.IOS.getType() || type == AppVersionType.ANDROID.getType()
                || type == AppVersionType.IOS_APPSTORE.getType()) {
            return true;
        }
        return false;
    }

}
