package com.example._40krusadebackend.Translator;

import com.example._40krusadebackend.Dto.AppUserUsernameDto;
import com.example._40krusadebackend.Dto.CrusadeDto;
import com.example._40krusadebackend.Dto.CrusadeForceDto;
import com.example._40krusadebackend.Dto.CrusadeForceInCrusadeListDto;
import com.example._40krusadebackend.Model.Crusade;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CrusadeTranslator {
    public CrusadeDto crusadeToCrusadeDto(Crusade crusade) {

        List<CrusadeForceInCrusadeListDto> forceDtos = crusade.getForces().stream()
                .map(force -> {
                    CrusadeForceInCrusadeListDto forceDto = new CrusadeForceInCrusadeListDto(force.getCrusadeId(),
                            force.getCrusadeForceName(), force.getUser().getUsername());
                    return forceDto;
                })
                .collect(Collectors.toList());

        CrusadeDto crusadeDto = new CrusadeDto(crusade.getCrusadeId(),
                crusade.getCrusadeName(),
                crusade.getCrusadeDescription(),
                new AppUserUsernameDto(crusade.getOwner().getUsername()), forceDtos);

        return crusadeDto;
    }
}
