package com.example._40krusadebackend.Translator;

import com.example._40krusadebackend.Dto.CrusadeForceDto;
import com.example._40krusadebackend.Model.CrusadeForce;

public class CrusadeForceTranslator {

    public static CrusadeForceDto toDto(CrusadeForce force) {
        return new CrusadeForceDto(
                force.getCrusadeId(),
                force.getCrusadeForceName(),
                force.getBattlesPlayed(),
                force.getBattlesWon(),
                force.getRequisitionPoints(),
                force.getSupplyLimit(),
                force.getCrusadePoints(),
                force.getCrusade() != null ? force.getCrusade().getCrusadeId() : null,
                force.getCrusadeFaction() != null ? force.getCrusadeFaction().getFactionId() : null,
                force.getUser() != null ? force.getUser().getUsername() : null
        );
    }
}
