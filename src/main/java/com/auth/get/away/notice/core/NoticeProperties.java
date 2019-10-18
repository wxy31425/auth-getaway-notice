package com.auth.get.away.notice.core;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "notice", ignoreUnknownFields = false)
@Component
public class NoticeProperties {

    private final Mail mail = new Mail();

    public Mail getMail() {
        return mail;
    }

    public static class Mail {
        private String from = "";

        private String baseUrl = "";


        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public String getBaseUrl() {
            return baseUrl;
        }

        public void setBaseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
        }
    }
}
