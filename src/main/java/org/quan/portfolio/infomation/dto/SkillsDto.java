package org.quan.portfolio.infomation.dto;

import java.util.List;

public record SkillsDto(List<String> backend, List<String> databases,
                        List<String> fundamentals, List<String> tooling,
                        List<String> allDatabaseSynced) {
}
