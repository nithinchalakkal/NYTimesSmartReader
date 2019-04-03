package com.smartreader.utils;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ProgressBar;

/**
 * Created by Nithin Chalakkal on 03-04-2019.
 */

public class SmartReaderProgressbar extends Dialog {

    Context mContext;
    Dialog dialog1;

    public Dialog showDialog() {
        dialog1 = new SmartReaderProgressbar(mContext);
        ProgressBar progressBar = new ProgressBar(mContext);
        dialog1.addContentView(progressBar, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        if (progressBar.getIndeterminateDrawable() != null) {
            progressBar.getIndeterminateDrawable().setColorFilter(mContext.getResources().getColor(com.smartreader.R.color.wallet_holo_blue_light), android.graphics.PorterDuff.Mode.SRC_IN);
        }
        try {
            dialog1.show();
        } catch (Exception e) {
            Log.e("Error" , "" + e.getMessage());
        }
        return dialog1;
    }

    public void dismissDialog()
    {
        if(dialog1!=null && dialog1.isShowing())
        {
            dialog1.dismiss();
        }
    }

    public SmartReaderProgressbar(@NonNull Context context) {
        super(context, com.smartreader.R.style.NewDialog);
        this.mContext = context;
    }
}
