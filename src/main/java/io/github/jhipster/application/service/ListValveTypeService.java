package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.ListValveType;
import io.github.jhipster.application.repository.ListValveTypeRepository;
import io.github.jhipster.application.service.dto.ListValveTypeDTO;
import io.github.jhipster.application.service.mapper.ListValveTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ListValveType}.
 */
@Service
@Transactional
public class ListValveTypeService {

    private final Logger log = LoggerFactory.getLogger(ListValveTypeService.class);

    private final ListValveTypeRepository listValveTypeRepository;

    private final ListValveTypeMapper listValveTypeMapper;

    public ListValveTypeService(ListValveTypeRepository listValveTypeRepository, ListValveTypeMapper listValveTypeMapper) {
        this.listValveTypeRepository = listValveTypeRepository;
        this.listValveTypeMapper = listValveTypeMapper;
    }

    /**
     * Save a listValveType.
     *
     * @param listValveTypeDTO the entity to save.
     * @return the persisted entity.
     */
    public ListValveTypeDTO save(ListValveTypeDTO listValveTypeDTO) {
        log.debug("Request to save ListValveType : {}", listValveTypeDTO);
        ListValveType listValveType = listValveTypeMapper.toEntity(listValveTypeDTO);
        listValveType = listValveTypeRepository.save(listValveType);
        return listValveTypeMapper.toDto(listValveType);
    }

    /**
     * Get all the listValveTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ListValveTypeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ListValveTypes");
        return listValveTypeRepository.findAll(pageable)
            .map(listValveTypeMapper::toDto);
    }


    /**
     * Get one listValveType by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ListValveTypeDTO> findOne(Long id) {
        log.debug("Request to get ListValveType : {}", id);
        return listValveTypeRepository.findById(id)
            .map(listValveTypeMapper::toDto);
    }

    /**
     * Delete the listValveType by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ListValveType : {}", id);
        listValveTypeRepository.deleteById(id);
    }
}
