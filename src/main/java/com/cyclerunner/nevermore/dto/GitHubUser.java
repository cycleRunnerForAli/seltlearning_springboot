package com.cyclerunner.nevermore.dto;

import lombok.Data;

@Data
public class GitHubUser {
    private String id;
    private String name;
    private String bio;
    private String avatar_url;
}
