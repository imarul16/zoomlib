package com.example.zoomlibtry.ui.meeting.feedback.model;

import com.example.zoomlibtry.cmd.FeedbackType;



public class FeedbackResultItem {
    public FeedbackResultItem(FeedbackType feedbackType) {
        this.feedbackType = feedbackType;
    }
    public FeedbackType feedbackType;
    public int percent;
    public int responseCount;
    public String title;
    public int iconResId;
}
