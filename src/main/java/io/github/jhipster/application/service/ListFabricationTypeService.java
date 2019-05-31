package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.ListFabricationType;
import io.github.jhipster.application.repository.ListFabricationTypeRepository;
import io.github.jhipster.application.service.dto.ListFabricationTypeDTO;
import io.github.jhipster.application.service.mapper.ListFabricationTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ListFabricationType}.
 */
@Service
@Transactional
public class ListFabricationTypeService {

    private final Logger log = LoggerFactory.getLogger(ListFabricationTypeService.class);

    private final ListFabricationTypeRepository listFabricationTypeRepository;

    private final ListFabricationTypeMapper listFabricationTypeMapper;

    public ListFabricationTypeService(ListFabricationTypeRepository listFabricationTypeRepository, ListFabricationTypeMapper listFabricationTypeMapper) {
        this.listFabricationTypeRepository = listFabricationTypeRepository;
        this.listFabricationTypeMapper = listFabricationTypeMapper;
    }

    /**
     * Save a listFabricationType.
     *
     * @param listFabricationTypeDTO the entity to save.
     * @return the persisted entity.
     */
    public ListFabricationTypeDTO save(ListFabricationTypeDTO listFabricationTypeDTO) {
        log.debug("Request to save ListFabricationType : {}", listFabricationTypeDTO);
        ListFabricationType listFabricationType = listFabricationTypeMapper.toEntity(listFabricationTypeDTO);
        listFabricationType = listFabricationTypeRepository.save(listFabricationType);
        return listFabricationTypeMapper.toDto(listFabricationType);
    }

    /**
     * Get all the listFabricationTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ListFabricationTypeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ListFabricationTypes");
        return listFabricationTypeRepository.findAll(pageable)
            .map(listFabricationTypeMapper::toDto);
    }


    /**
     * Get one listFabricationType by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ListFabricationTypeDTO> findOne(Long id) {
        log.debug("Request to get ListFabricationType : {}", id);
        return listFabricationTypeRepository.findById(id)
            .map(listFabricationTypeMapper::toDto);
    }

    /**
     * Delete the listFabricationType by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ListFabricationType : {}", id);
        listFabricationTypeRepository.deleteById(id);
    }
}
