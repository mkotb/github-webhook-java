package com.mkotb.github.model;

import lombok.Getter;

import java.util.Date;

@Getter
public class Comment {
    private String url;
    private String htmlUrl;
    private long id;
    private String nodeId;
    private String body;
    private String path;
    private int position;
    private int line;
    private String commitId;
    private GitHubUser user;
    private Date createdAt;
    private Date updatedAt;
}
