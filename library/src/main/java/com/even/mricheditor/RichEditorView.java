package com.even.mricheditor;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

/**
 * 作者：tiger on 2018/10/29 16:10
 */
public class RichEditorView extends WebView {

    private String mPlaceHolder;
    private RichEditorAction mRichEditorAction;

    public RichEditorView(Context context) {
        super(context);
        init();
    }

    public RichEditorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RichEditorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public RichEditorView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void init() {
        mRichEditorAction = new RichEditorAction(this);

        getSettings().setJavaScriptEnabled(true);
        getSettings().setDomStorageEnabled(true);

        loadUrl("file:///android_asset/richEditor.html");
    }

    public void setPlaceHolder(String mPlaceHolder) {
        this.mPlaceHolder = mPlaceHolder;
    }

    public void execCommand(ActionType type) {
        if (this.mRichEditorAction != null) {
            this.mRichEditorAction.execCommand(type);
        }
    }

    private RichEditorCallback.OnGetHtmlListener onGetHtmlListener;

    public void setOnGetHtmlListener(RichEditorCallback.OnGetHtmlListener onGetHtmlListener) {
        this.onGetHtmlListener = onGetHtmlListener;
    }

    public void getHtml(RichEditorCallback.OnGetHtmlListener onGetHtmlListener) {
        this. onGetHtmlListener = onGetHtmlListener;

        this.evaluateJavascript("getCode()", new ValueCallback<String>() {
            @Override
            public void onReceiveValue(String value) {
                RichEditorView.this.onGetHtmlListener.getHtml(value);
            }
        });
    }

    private class CustomWebChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            if (newProgress == 100) {
                if (!TextUtils.isEmpty(mPlaceHolder)) {
                    mRichEditorAction.insertHtml(mPlaceHolder);
                }
            }
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
        }
    }
}
