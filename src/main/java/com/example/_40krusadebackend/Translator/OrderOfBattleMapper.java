package com.example._40krusadebackend.Translator;

import com.example._40krusadebackend.Dto.CrusadeUnitDto;
import com.example._40krusadebackend.Dto.CrusadeUnitSummaryDto;
import com.example._40krusadebackend.Dto.OrderOfBattleDto;
import com.example._40krusadebackend.Model.CrusadeUnit;
import com.example._40krusadebackend.Model.OrderOfBattle;

import java.util.List;
import java.util.stream.Collectors;

public class OrderOfBattleMapper {

    public static OrderOfBattleDto toDto(OrderOfBattle orderOfBattle) {
        List<CrusadeUnitSummaryDto> unitDtos = orderOfBattle.getUnits().stream()
                .map(unit -> new CrusadeUnitSummaryDto(
                        unit.getId(),
                        unit.getUnitOfficialName(),
                        unit.getCrusadeUnitName(),
                        unit.getExperience()
                ))
                .toList();

        return new OrderOfBattleDto(orderOfBattle.getOrderOfBattleId(), unitDtos);
    }

    private static CrusadeUnitDto toUnitDto(CrusadeUnit unit) {
        return new CrusadeUnitDto(
                unit.getId(),
                unit.getUnitOfficialName(),
                unit.getExperience(),
                unit.getKills()
        );
    }

}
