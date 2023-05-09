package com.example.zoomlibtry.ui.meeting.feedback.util;

import com.example.zoomlibtry.cmd.CmdFeedbackPushRequest;
import com.example.zoomlibtry.cmd.CmdFeedbackSubmitRequest;
import com.example.zoomlibtry.cmd.CmdHelper;
import com.example.zoomlibtry.cmd.FeedbackType;

public class FeedbackUtils {
    public static void submitFeedback(FeedbackType type) {
        CmdFeedbackSubmitRequest request = new CmdFeedbackSubmitRequest();
        request.feedbackType = type;
        CmdHelper.getInstance().sendCommand(request);
    }

    public static void pushFeedback() {
        CmdFeedbackPushRequest request = new CmdFeedbackPushRequest();
        CmdHelper.getInstance().sendCommand(request);
    }
}
