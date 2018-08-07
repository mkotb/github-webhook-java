package com.mkotb.github.webhooks.event;

import com.mkotb.github.model.Comment;
import com.mkotb.github.model.GitHubUser;
import com.mkotb.github.model.Repository;
import lombok.Getter;

@Getter
public class CommitCommentEvent extends WebhookEvent<CommitCommentEvent.Action> {
    private Action action;
    private Comment comment;
    private Repository repository;
    private GitHubUser sender;

    public enum Action {
        CREATED
    }
}
