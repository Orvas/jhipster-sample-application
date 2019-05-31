package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.ListDfctPosType;
import io.github.jhipster.application.repository.ListDfctPosTypeRepository;
import io.github.jhipster.application.service.dto.ListDfctPosTypeDTO;
import io.github.jhipster.application.service.mapper.ListDfctPosTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ListDfctPosType}.
 */
@Service
@Transactional
public class ListDfctPosTypeService {

    private final Logger log = LoggerFactory.getLogger(ListDfctPosTypeService.class);

    private final ListDfctPosTypeRepository listDfctPosTypeRepository;

    private final ListDfctPosTypeMapper listDfctPosTypeMapper;

    public ListDfctPosTypeService(ListDfctPosTypeRepository listDfctPosTypeRepository, ListDfctPosTypeMapper listDfctPosTypeMapper) {
        this.listDfctPosTypeRepository = listDfctPosTypeRepository;
        this.listDfctPosTypeMapper = listDfctPosTypeMapper;
    }

    /**
     * Save a listDfctPosType.
     *
     * @param listDfctPosTypeDTO the entity to save.
     * @return the persisted entity.
     */
    public ListDfctPosTypeDTO save(ListDfctPosTypeDTO listDfctPosTypeDTO) {
        log.debug("Request to save ListDfctPosType : {}", listDfctPosTypeDTO);
        ListDfctPosType listDfctPosType = listDfctPosTypeMapper.toEntity(listDfctPosTypeDTO);
        listDfctPosType = listDfctPosTypeRepository.save(listDfctPosType);
        return listDfctPosTypeMapper.toDto(listDfctPosType);
    }

    /**
     * Get all the listDfctPosTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ListDfctPosTypeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ListDfctPosTypes");
        return listDfctPosTypeRepository.findAll(pageable)
            .map(listDfctPosTypeMapper::toDto);
    }


    /**
     * Get one listDfctPosType by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ListDfctPosTypeDTO> findOne(Long id) {
        log.debug("Request to get ListDfctPosType : {}", id);
        return listDfctPosTypeRepository.findById(id)
            .map(listDfctPosTypeMapper::toDto);
    }

    /**
     * Delete the listDfctPosType by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ListDfctPosType : {}", id);
        listDfctPosTypeRepository.deleteById(id);
    }
}
