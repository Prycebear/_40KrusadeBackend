package com.example._40krusadebackend.Dto;

public record CrusadeForceDto(
        Integer id,
        String crusadeForceName,
        int battlesPlayed,
        int battlesWon,
        int requisitionPoints,
        int supplyLimit,
        int crusadePoints,
        Integer crusadeId,
        Integer factionId,
        String username) {
}
