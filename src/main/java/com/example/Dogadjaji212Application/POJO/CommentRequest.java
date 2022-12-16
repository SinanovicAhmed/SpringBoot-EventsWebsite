package com.example.Dogadjaji212Application.POJO;

public class CommentRequest {
    private String comment;
    private Long user_id;
    private Long event_id;

    public CommentRequest(String comment, Long user_id, Long event_id) {
        this.comment = comment;
        this.user_id = user_id;
        this.event_id = event_id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getEvent_id() {
        return event_id;
    }

    public void setEvent_id(Long event_id) {
        this.event_id = event_id;
    }
}
