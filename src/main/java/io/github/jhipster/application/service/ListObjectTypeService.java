package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.ListObjectType;
import io.github.jhipster.application.repository.ListObjectTypeRepository;
import io.github.jhipster.application.service.dto.ListObjectTypeDTO;
import io.github.jhipster.application.service.mapper.ListObjectTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ListObjectType}.
 */
@Service
@Transactional
public class ListObjectTypeService {

    private final Logger log = LoggerFactory.getLogger(ListObjectTypeService.class);

    private final ListObjectTypeRepository listObjectTypeRepository;

    private final ListObjectTypeMapper listObjectTypeMapper;

    public ListObjectTypeService(ListObjectTypeRepository listObjectTypeRepository, ListObjectTypeMapper listObjectTypeMapper) {
        this.listObjectTypeRepository = listObjectTypeRepository;
        this.listObjectTypeMapper = listObjectTypeMapper;
    }

    /**
     * Save a listObjectType.
     *
     * @param listObjectTypeDTO the entity to save.
     * @return the persisted entity.
     */
    public ListObjectTypeDTO save(ListObjectTypeDTO listObjectTypeDTO) {
        log.debug("Request to save ListObjectType : {}", listObjectTypeDTO);
        ListObjectType listObjectType = listObjectTypeMapper.toEntity(listObjectTypeDTO);
        listObjectType = listObjectTypeRepository.save(listObjectType);
        return listObjectTypeMapper.toDto(listObjectType);
    }

    /**
     * Get all the listObjectTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ListObjectTypeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ListObjectTypes");
        return listObjectTypeRepository.findAll(pageable)
            .map(listObjectTypeMapper::toDto);
    }


    /**
     * Get one listObjectType by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ListObjectTypeDTO> findOne(Long id) {
        log.debug("Request to get ListObjectType : {}", id);
        return listObjectTypeRepository.findById(id)
            .map(listObjectTypeMapper::toDto);
    }

    /**
     * Delete the listObjectType by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ListObjectType : {}", id);
        listObjectTypeRepository.deleteById(id);
    }
}
