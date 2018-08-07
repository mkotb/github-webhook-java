package com.mkotb.github.webhooks;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.http.HttpServerRequest;
import lombok.Builder;
import lombok.Getter;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Getter
public class GitHubWebhook {
    private final Vertx vertx = Vertx.vertx();
    private HttpServer server;

    @Builder
    private GitHubWebhook(HttpServerOptions options, String secretToken) {
        this.server = vertx.createHttpServer(options);

        server.requestHandler((request) -> {
            if (!"payload".equals(request.path().substring(1))) {
                request.response().setStatusCode(404).end("404 not found");
                return;
            }

            request.bodyHandler((buffer) -> {
                String body = buffer.toString();

                if (secretToken != null) {
                    String gitHubHash = request.getHeader("HTTP_X_HUB_SIGNATURE");

                    if (gitHubHash == null) {
                        invalidSignature(request);
                        return;
                    }

                    try {
                        SecretKeySpec key = new SecretKeySpec(secretToken.getBytes(), "HmacSHA1");
                        Mac mac = Mac.getInstance("HmacSHA1");

                        mac.init(key);
                        byte[] hash = mac.doFinal(body.getBytes());

                        // todo convert to hex and compare hash with github
                    } catch (NoSuchAlgorithmException | InvalidKeyException ex) {
                        request.response().setStatusCode(500).end("Internal server error");
                        ex.printStackTrace();
                    }
                }

                // todo look at the webhook event type, deserialize the event with its given class, and call appropriate listeners
            });
        });
    }

    private void invalidSignature(HttpServerRequest request) {
        request.response().setStatusCode(400).end("Invalid Signature");
    }
}
