package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.ListAnodeBraceleteType;
import io.github.jhipster.application.repository.ListAnodeBraceleteTypeRepository;
import io.github.jhipster.application.service.dto.ListAnodeBraceleteTypeDTO;
import io.github.jhipster.application.service.mapper.ListAnodeBraceleteTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ListAnodeBraceleteType}.
 */
@Service
@Transactional
public class ListAnodeBraceleteTypeService {

    private final Logger log = LoggerFactory.getLogger(ListAnodeBraceleteTypeService.class);

    private final ListAnodeBraceleteTypeRepository listAnodeBraceleteTypeRepository;

    private final ListAnodeBraceleteTypeMapper listAnodeBraceleteTypeMapper;

    public ListAnodeBraceleteTypeService(ListAnodeBraceleteTypeRepository listAnodeBraceleteTypeRepository, ListAnodeBraceleteTypeMapper listAnodeBraceleteTypeMapper) {
        this.listAnodeBraceleteTypeRepository = listAnodeBraceleteTypeRepository;
        this.listAnodeBraceleteTypeMapper = listAnodeBraceleteTypeMapper;
    }

    /**
     * Save a listAnodeBraceleteType.
     *
     * @param listAnodeBraceleteTypeDTO the entity to save.
     * @return the persisted entity.
     */
    public ListAnodeBraceleteTypeDTO save(ListAnodeBraceleteTypeDTO listAnodeBraceleteTypeDTO) {
        log.debug("Request to save ListAnodeBraceleteType : {}", listAnodeBraceleteTypeDTO);
        ListAnodeBraceleteType listAnodeBraceleteType = listAnodeBraceleteTypeMapper.toEntity(listAnodeBraceleteTypeDTO);
        listAnodeBraceleteType = listAnodeBraceleteTypeRepository.save(listAnodeBraceleteType);
        return listAnodeBraceleteTypeMapper.toDto(listAnodeBraceleteType);
    }

    /**
     * Get all the listAnodeBraceleteTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ListAnodeBraceleteTypeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ListAnodeBraceleteTypes");
        return listAnodeBraceleteTypeRepository.findAll(pageable)
            .map(listAnodeBraceleteTypeMapper::toDto);
    }


    /**
     * Get one listAnodeBraceleteType by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ListAnodeBraceleteTypeDTO> findOne(Long id) {
        log.debug("Request to get ListAnodeBraceleteType : {}", id);
        return listAnodeBraceleteTypeRepository.findById(id)
            .map(listAnodeBraceleteTypeMapper::toDto);
    }

    /**
     * Delete the listAnodeBraceleteType by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ListAnodeBraceleteType : {}", id);
        listAnodeBraceleteTypeRepository.deleteById(id);
    }
}
