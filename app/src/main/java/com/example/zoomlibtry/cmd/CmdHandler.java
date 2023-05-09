package com.example.zoomlibtry.cmd;


import com.example.zoomlibtry.ui.meeting.interfaces.IListener;

public interface CmdHandler extends IListener {
    void onCmdReceived(CmdRequest request);
}
