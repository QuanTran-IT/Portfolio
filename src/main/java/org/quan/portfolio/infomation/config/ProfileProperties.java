package org.quan.portfolio.infomation.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

// Đọc các dòng "portfolio.profile.*" trong application.properties
// Tên field camelCase tự khớp với tên kebab-case (fullName <-> full-name)
@ConfigurationProperties(prefix = "portfolio.profile")
public record ProfileProperties(String fullName,
                                String title,
                                String about,
                                String email,
                                String githubUrl) {
}