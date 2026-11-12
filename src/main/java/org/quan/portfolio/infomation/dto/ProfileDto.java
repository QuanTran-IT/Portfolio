package org.quan.portfolio.infomation.dto;

import java.util.List;

public record ProfileDto(String name, String title, String university, String major,
                         double gpa, String email, String github, String status,
                         List<String> primaryFocus) {
}
