package com.example._40krusadebackend.Dto.Auth;

import com.example._40krusadebackend.Model.CrusadeForce;

import java.util.List;

public record CrusadeDto(Integer crusadeId,
                                String crusadeName,
                                String crusadeDescription,
                                String owner,
                                List<CrusadeForce> forces) {
}
