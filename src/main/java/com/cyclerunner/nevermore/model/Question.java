package com.cyclerunner.nevermore.model;

import lombok.Data;

@Data
public class Question {
    private Integer id;
    private String title;
    private String desc;
    private Long gmt_create;
    private Long gmt_modify;
    private Integer creator;
    private Integer comment_count;
    private Integer view_count;
    private Integer like_count;
    private String tag;

    public Question() {
    }

    public Question(String title, String desc, long gmt_create, long gmt_modify, int creator, int comment_count, int view_count, int like_count, String tag) {
        this.title = title;
        this.desc = desc;
        this.gmt_create = gmt_create;
        this.gmt_modify = gmt_modify;
        this.creator = creator;
        this.comment_count = comment_count;
        this.view_count = view_count;
        this.like_count = like_count;
        this.tag = tag;
    }
}
