package com.even.mricheditor.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import com.even.mricheditor.ActionType;
import com.even.mricheditor.R;

/**
 * The Interface of Action Button
 * Created by even.wu on 22/8/17.
 */

public class ActionImageView extends AppCompatImageView {
    private ActionType mActionType;
    private Context mContext;

    private boolean enabled = true;
    private boolean activated = true;

    private int enabledColor;
    private int disabledColor;
    private int activatedColor;
    private int deactivatedColor;

    public ActionImageView(Context context) {
        this(context, null);
    }

    public ActionImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ActionImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        this.mContext = context;
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ActionImageView);
        mActionType =
            ActionType.fromInteger(ta.getInteger(R.styleable.ActionImageView_actionType, 0));
        ta.recycle();
    }

    public ActionType getActionType() {
        return mActionType;
    }

    public void setActionType(ActionType mActionType) {
        this.mActionType = mActionType;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public boolean isActivated() {
        return activated;
    }

    public void resetStatus() {
    }

    public int getEnabledColor() {
        return enabledColor;
    }

    public void setEnabledColor(int enabledColor) {
        this.enabledColor = enabledColor;
    }

    public int getDisabledColor() {
        return disabledColor;
    }

    public void setDisabledColor(int disabledColor) {
        this.disabledColor = disabledColor;
    }

    public int getActivatedColor() {
        return activatedColor;
    }

    public void setActivatedColor(int activatedColor) {
        this.activatedColor = activatedColor;
    }

    public int getDeactivatedColor() {
        return deactivatedColor;
    }

    public void setDeactivatedColor(int deactivatedColor) {
        this.deactivatedColor = deactivatedColor;
    }

    public void notifyFontStyleChange(final ActionType type, final String value) {

        post(new Runnable() {
            @Override public void run() {
                switch (type) {
                    case BOLD:
                    case ITALIC:
                    case UNDERLINE:
                    case SUBSCRIPT:
                    case SUPERSCRIPT:
                    case STRIKETHROUGH:
                    case NORMAL:
                    case H1:
                    case H2:
                    case H3:
                    case H4:
                    case H5:
                    case H6:
                    case JUSTIFY_LEFT:
                    case JUSTIFY_CENTER:
                    case JUSTIFY_RIGHT:
                    case JUSTIFY_FULL:
                    case ORDERED:
                    case UNORDERED:
                        setColorFilter(ContextCompat.getColor(mContext,
                            Boolean.valueOf(value) ? getActivatedColor() : getDeactivatedColor()));
                        break;
                    default:
                        break;
                }
            }
        });
    }
}
