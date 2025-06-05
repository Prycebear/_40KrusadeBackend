package com.example._40krusadebackend.Dto;

import java.util.List;

public record OrderOfBattleDto(int orderOfBattleId, List<CrusadeUnitSummaryDto> units) {
}
