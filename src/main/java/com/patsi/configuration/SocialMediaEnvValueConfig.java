package com.patsi.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "com.patsi.social-media")
public record SocialMediaEnvValueConfig(String profilePicturePath, String bannerPicturePath) {
}
