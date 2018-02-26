package controller.request;

import model.Dimension;

public class MonitoringRequest {

    private String eventType;

    private String websiteUrl;

    private String sessionId;

    private Dimension resizeFrom;

    private Dimension resizeTo;

    private Boolean paste;

    private Integer formCompletionTime;

    public Boolean getPaste() {
        return paste;
    }

    public Integer getFormCompletionTime() {
        return formCompletionTime;
    }

    public String getEventType() {
        return eventType;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public String getSessionId() {
        return sessionId;
    }

    public Dimension getResizeFrom() {
        return resizeFrom;
    }

    public Dimension getResizeTo() {
        return resizeTo;
    }

    @Override public String toString() {
        return "MonitoringRequest{" +
            "eventType='" + eventType + '\'' +
            ", websiteUrl='" + websiteUrl + '\'' +
            ", sessionId='" + sessionId + '\'' +
            ", resizeFrom=" + resizeFrom +
            ", resizeTo=" + resizeTo +
            ", paste=" + paste +
            ", formCompletionTime=" + formCompletionTime +
            '}';
    }

}
