package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.ListClayType;
import io.github.jhipster.application.repository.ListClayTypeRepository;
import io.github.jhipster.application.service.dto.ListClayTypeDTO;
import io.github.jhipster.application.service.mapper.ListClayTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ListClayType}.
 */
@Service
@Transactional
public class ListClayTypeService {

    private final Logger log = LoggerFactory.getLogger(ListClayTypeService.class);

    private final ListClayTypeRepository listClayTypeRepository;

    private final ListClayTypeMapper listClayTypeMapper;

    public ListClayTypeService(ListClayTypeRepository listClayTypeRepository, ListClayTypeMapper listClayTypeMapper) {
        this.listClayTypeRepository = listClayTypeRepository;
        this.listClayTypeMapper = listClayTypeMapper;
    }

    /**
     * Save a listClayType.
     *
     * @param listClayTypeDTO the entity to save.
     * @return the persisted entity.
     */
    public ListClayTypeDTO save(ListClayTypeDTO listClayTypeDTO) {
        log.debug("Request to save ListClayType : {}", listClayTypeDTO);
        ListClayType listClayType = listClayTypeMapper.toEntity(listClayTypeDTO);
        listClayType = listClayTypeRepository.save(listClayType);
        return listClayTypeMapper.toDto(listClayType);
    }

    /**
     * Get all the listClayTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ListClayTypeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ListClayTypes");
        return listClayTypeRepository.findAll(pageable)
            .map(listClayTypeMapper::toDto);
    }


    /**
     * Get one listClayType by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ListClayTypeDTO> findOne(Long id) {
        log.debug("Request to get ListClayType : {}", id);
        return listClayTypeRepository.findById(id)
            .map(listClayTypeMapper::toDto);
    }

    /**
     * Delete the listClayType by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ListClayType : {}", id);
        listClayTypeRepository.deleteById(id);
    }
}
