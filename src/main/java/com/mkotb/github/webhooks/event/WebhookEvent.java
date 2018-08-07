package com.mkotb.github.webhooks.event;

public abstract class WebhookEvent<EventAction extends Enum<EventAction>> {
    public abstract EventAction getAction();
}
