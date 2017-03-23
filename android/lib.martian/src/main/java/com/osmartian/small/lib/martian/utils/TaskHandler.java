package com.osmartian.small.lib.martian.utils;

import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

/**
 * Author   : walid
 * Data     : 2016-08-29  16:12
 * Describe :
 */

public class TaskHandler<T> extends Handler {

    private WeakReference<T> weakReference;

    public TaskHandler(T object) {
        weakReference = new WeakReference<>(object);
    }

    /**
     * 任务执行成功
     */
    public static final int TASK_OK = 0x10000001;
    /**
     * 任务执行失败
     */
    public static final int TASK_FAILED = 0x10000002;

    /**
     * 消息接受处理
     */
    @Override
    public void handleMessage(Message msg) {
        T object = weakReference.get();
        if (object != null) {
            switch (msg.what) {
                case TASK_OK:
                    onTaskOk(object, msg);
                    break;
                case TASK_FAILED:
                    onTaskFailed(object, msg);
                    break;
            }
        }
    }

    /**
     * 发送带消息的失败消息
     */
    public void sendFailedMessage(Object object) {
        Message msg = Message.obtain();
        msg.what = TASK_FAILED;
        msg.arg1 = 0;
        if (null != object) {
            msg.obj = object;
        }
        this.sendMessage(msg);
    }


    /**
     * 发送带消息的失败消息
     */
    public void sendFailedMessage(Object object, int arg1) {
        Message msg = Message.obtain();
        msg.what = TASK_FAILED;
        msg.arg1 = arg1;
        if (null != object) {
            msg.obj = object;
        }
        this.sendMessage(msg);
    }

    /**
     * 发送带延时消息的失败消息
     */
    public void sendFailedDelayedMessage(Object object, int arg1, long timedelay) {
        Message msg = Message.obtain();
        msg.what = TASK_FAILED;
        msg.arg1 = arg1;
        if (null != object) {
            msg.obj = object;
        }
        this.sendMessageDelayed(msg, timedelay);
    }

    public void sendFailedMessage() {
        this.sendEmptyMessage(TASK_FAILED);
    }

    // 发送带消息的成功消息
    public void sendSucessMessage(Object object) {
        Logger.d("Send Sucess Message");
        Message msg = Message.obtain();
        msg.what = TASK_OK;
        msg.arg1 = 0;
        if (null != object) {
            msg.obj = object;
        }
        this.sendMessage(msg);
    }

    // 发送带消息的成功消息
    public void sendSucessMessage(Object object, int arg1) {
        Logger.d("Send Sucess Message");
        Message msg = Message.obtain();
        msg.what = TASK_OK;
        msg.arg1 = arg1;
        if (null != object) {
            msg.obj = object;
        }
        this.sendMessage(msg);
    }

    public void sendSucessMessage() {
        this.sendEmptyMessage(TASK_OK);
    }

    /**
     * 当消息类型为TASK_OK时，回调该方法 可以通过arg1进行消息标记
     */
    public void onTaskOk(T object, Message msg) {

    }

    /**
     * 当消息类型为TASK_FAILED时，回调该方法 可以通过arg1进行消息标记
     */
    public void onTaskFailed(T object, Message msg) {

    }

}
