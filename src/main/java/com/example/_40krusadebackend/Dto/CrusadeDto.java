package com.example._40krusadebackend.Dto;

import com.example._40krusadebackend.Model.CrusadeForce;

import java.util.List;

public record CrusadeDto(Integer crusadeId,
                                String crusadeName,
                                String crusadeDescription,
                                AppUserUsernameDto owner,
                                List<CrusadeForceInCrusadeListDto> forces) {
}
