package com.mkotb.github.webhooks.event;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum WebhookEventType {
    ;

    private final Class<? extends WebhookEvent> eventClass;
}
