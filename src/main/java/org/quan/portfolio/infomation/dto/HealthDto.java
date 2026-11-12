package org.quan.portfolio.infomation.dto;

public record HealthDto(String status, String framework, String javaVersion,
                        String databaseStatus, String timestamp, String developer) {
}
