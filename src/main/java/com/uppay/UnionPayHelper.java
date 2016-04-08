package com.uppay;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.unionpay.UPPayAssistEx;
import com.unionpay.uppay.PayActivity;

public class UnionPayHelper {

    public static final String PAY_RESULT = "pay_result";
    public static final String RESULT_SUCESS = "success";
    public static final String RESULT_FAIL = "fail";
    public static final String RESULT_CANCEL = "cancel";

    public static void pay(Activity activity, String tn, String mode) {
        UPPayAssistEx.startPayByJAR(activity, PayActivity.class, null, null, tn, mode);
    }

    public static void pay(Fragment fragment, Context context, String tn) {
        pay(fragment, context, tn, "00");
    }

    public static void pay(Fragment fragment, Context context, String tn, String mode) {
        Bundle bundle = new Bundle();
        repParams(tn, bundle, mode);
        bundle.putString("SpId", null);
        bundle.putString("SysProvide", null);
        bundle.putString("paydata", tn);
        bundle.putInt("reqOriginalId", 2);
        Intent var7;
        (var7 = new Intent()).putExtras(bundle);
        var7.setClass(context, PayActivity.class);
        fragment.startActivityForResult(var7, 10);
    }

    private static void repParams(String var0, Bundle var1, String var2) {
        if (var0 != null && var0.trim().length() > 0) {
            if (var0.trim().charAt(0) == 60) {
                if (var2 != null && var2.trim().equalsIgnoreCase("00")) {
                    var1.putBoolean("UseTestMode", false);
                } else {
                    var1.putBoolean("UseTestMode", true);
                }
            } else {
                var1.putString("ex_mode", var2);
            }
        }
    }
}
