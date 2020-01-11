package com.mchaw.tauruspay.common.util;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Vibrator;

import com.mchaw.tauruspay.MyFrameApplication;
import com.mchaw.tauruspay.R;

/**
 * @author : Bruce Lee
 * @date : 2019/11/27 0027 03:26
 * @description :
 */
public class WarningToneUtils {
    private static final WarningToneUtils INSTANCE = new WarningToneUtils(MyFrameApplication.getInstance());

    public static WarningToneUtils getInstance() {
        synchronized (WarningToneUtils.class) {
            return INSTANCE;
        }
    }

    private SoundPool mSound;
    private int cardHit,auditHit;
    private Context mContext;
    private Vibrator vibrator ;
    public WarningToneUtils(Context context) {
        this.mContext = context;
    }

    public void init() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mSound = new SoundPool.Builder().setMaxStreams(10).build();
        } else {
            mSound = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        }
        cardHit = mSound.load(mContext, R.raw.goal, 1);
        auditHit = mSound.load(mContext, R.raw.audit, 1);
        vibrator =  (Vibrator) mContext.getSystemService(Context.VIBRATOR_SERVICE);
    }

    public void playSound() {
        mSound.play(cardHit, 15, 15, 1, 0, 1);
        cardHit = mSound.load(mContext, R.raw.goal, 1);
    }

    public void playAuditSound() {
        mSound.play(auditHit, 15, 15, 1, 0, 1);
        auditHit = mSound.load(mContext, R.raw.audit, 1);
    }

    public void playShake() {
        vibrator.vibrate(1000);
    }
}
