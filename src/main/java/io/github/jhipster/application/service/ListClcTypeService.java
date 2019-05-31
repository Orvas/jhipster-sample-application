package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.ListClcType;
import io.github.jhipster.application.repository.ListClcTypeRepository;
import io.github.jhipster.application.service.dto.ListClcTypeDTO;
import io.github.jhipster.application.service.mapper.ListClcTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ListClcType}.
 */
@Service
@Transactional
public class ListClcTypeService {

    private final Logger log = LoggerFactory.getLogger(ListClcTypeService.class);

    private final ListClcTypeRepository listClcTypeRepository;

    private final ListClcTypeMapper listClcTypeMapper;

    public ListClcTypeService(ListClcTypeRepository listClcTypeRepository, ListClcTypeMapper listClcTypeMapper) {
        this.listClcTypeRepository = listClcTypeRepository;
        this.listClcTypeMapper = listClcTypeMapper;
    }

    /**
     * Save a listClcType.
     *
     * @param listClcTypeDTO the entity to save.
     * @return the persisted entity.
     */
    public ListClcTypeDTO save(ListClcTypeDTO listClcTypeDTO) {
        log.debug("Request to save ListClcType : {}", listClcTypeDTO);
        ListClcType listClcType = listClcTypeMapper.toEntity(listClcTypeDTO);
        listClcType = listClcTypeRepository.save(listClcType);
        return listClcTypeMapper.toDto(listClcType);
    }

    /**
     * Get all the listClcTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ListClcTypeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ListClcTypes");
        return listClcTypeRepository.findAll(pageable)
            .map(listClcTypeMapper::toDto);
    }


    /**
     * Get one listClcType by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ListClcTypeDTO> findOne(Long id) {
        log.debug("Request to get ListClcType : {}", id);
        return listClcTypeRepository.findById(id)
            .map(listClcTypeMapper::toDto);
    }

    /**
     * Delete the listClcType by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ListClcType : {}", id);
        listClcTypeRepository.deleteById(id);
    }
}
