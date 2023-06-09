package com.example.zoomlibtry.cmd;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.zoomlibtry.ui.meeting.interfaces.IListener;
import com.example.zoomlibtry.ui.meeting.interfaces.SimpleVideoSDKDelegate;
import com.example.zoomlibtry.ui.meeting.models.ListenerList;
import com.example.zoomlibtry.ui.meeting.util.ErrorMsgUtil;

import us.zoom.sdk.ZoomVideoSDK;
import us.zoom.sdk.ZoomVideoSDKErrors;
import us.zoom.sdk.ZoomVideoSDKUser;

public class CmdHelper extends SimpleVideoSDKDelegate {
    private static final String TAG = "CmdHelper";

    private static volatile CmdHelper instance = null;
    private ListenerList listeners = new ListenerList();
    private CmdHelper() {}

    public static CmdHelper getInstance() {
        if (instance == null) {
            synchronized (CmdHelper.class) {
                if (instance == null) {
                    instance = new CmdHelper();
                    ZoomVideoSDK.getInstance().addListener(instance);
                }
            }
        }
        return instance;
    }

    public void addListener(@NonNull CmdHandler handler) {
        listeners.add(handler);
    }

    public void removeListener(@NonNull CmdHandler handler) {
        listeners.remove(handler);
    }

    @Override
    public void onCommandReceived(ZoomVideoSDKUser sender, String strCmd) {

        CmdRequest cmdRequest = CmdRequest.getRequest(sender, strCmd);

        if (cmdRequest != null) {
            for (IListener listener : listeners.getAll()) {
                if (listener instanceof CmdHandler) {
                    ((CmdHandler) listener).onCmdReceived(cmdRequest);
                }
            }
        }
    }

    public void sendCommand(CmdRequest request) {
        String cmd = request.generateCmdString();
        if (cmd == null) {
            Log.e(TAG, "Wrong Command Null");
            return;
        }
        int error = ZoomVideoSDK.getInstance().getCmdChannel().sendCommand(request.user, cmd);
        if (error != ZoomVideoSDKErrors.Errors_Success) {
            Log.e(TAG, "send command " + cmd + " error for: " + ErrorMsgUtil.getMsgByErrorCode(error));
            return;
        }
        if (request instanceof CmdReactionRequest) {
            // self should show emoji
            onCommandReceived(ZoomVideoSDK.getInstance().getSession().getMySelf(), cmd);
        }
        if (request instanceof CmdLowerThirdRequest) {
            // self should show lower third
            onCommandReceived(ZoomVideoSDK.getInstance().getSession().getMySelf(), cmd);
        }
    }
}
