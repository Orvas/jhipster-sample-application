package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.ListDfctType;
import io.github.jhipster.application.repository.ListDfctTypeRepository;
import io.github.jhipster.application.service.dto.ListDfctTypeDTO;
import io.github.jhipster.application.service.mapper.ListDfctTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ListDfctType}.
 */
@Service
@Transactional
public class ListDfctTypeService {

    private final Logger log = LoggerFactory.getLogger(ListDfctTypeService.class);

    private final ListDfctTypeRepository listDfctTypeRepository;

    private final ListDfctTypeMapper listDfctTypeMapper;

    public ListDfctTypeService(ListDfctTypeRepository listDfctTypeRepository, ListDfctTypeMapper listDfctTypeMapper) {
        this.listDfctTypeRepository = listDfctTypeRepository;
        this.listDfctTypeMapper = listDfctTypeMapper;
    }

    /**
     * Save a listDfctType.
     *
     * @param listDfctTypeDTO the entity to save.
     * @return the persisted entity.
     */
    public ListDfctTypeDTO save(ListDfctTypeDTO listDfctTypeDTO) {
        log.debug("Request to save ListDfctType : {}", listDfctTypeDTO);
        ListDfctType listDfctType = listDfctTypeMapper.toEntity(listDfctTypeDTO);
        listDfctType = listDfctTypeRepository.save(listDfctType);
        return listDfctTypeMapper.toDto(listDfctType);
    }

    /**
     * Get all the listDfctTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ListDfctTypeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ListDfctTypes");
        return listDfctTypeRepository.findAll(pageable)
            .map(listDfctTypeMapper::toDto);
    }


    /**
     * Get one listDfctType by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ListDfctTypeDTO> findOne(Long id) {
        log.debug("Request to get ListDfctType : {}", id);
        return listDfctTypeRepository.findById(id)
            .map(listDfctTypeMapper::toDto);
    }

    /**
     * Delete the listDfctType by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ListDfctType : {}", id);
        listDfctTypeRepository.deleteById(id);
    }
}
