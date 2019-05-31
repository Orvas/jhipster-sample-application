package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.LaunchReceiverDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link LaunchReceiver} and its DTO {@link LaunchReceiverDTO}.
 */
@Mapper(componentModel = "spring", uses = {BaseClassMapper.class})
public interface LaunchReceiverMapper extends EntityMapper<LaunchReceiverDTO, LaunchReceiver> {

    @Mapping(source = "baseClass.id", target = "baseClassId")
    LaunchReceiverDTO toDto(LaunchReceiver launchReceiver);

    @Mapping(source = "baseClassId", target = "baseClass")
    @Mapping(target = "launchReceiverHist", ignore = true)
    LaunchReceiver toEntity(LaunchReceiverDTO launchReceiverDTO);

    default LaunchReceiver fromId(Long id) {
        if (id == null) {
            return null;
        }
        LaunchReceiver launchReceiver = new LaunchReceiver();
        launchReceiver.setId(id);
        return launchReceiver;
    }
}
