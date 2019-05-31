package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.ListLongSeamWeldType;
import io.github.jhipster.application.repository.ListLongSeamWeldTypeRepository;
import io.github.jhipster.application.service.dto.ListLongSeamWeldTypeDTO;
import io.github.jhipster.application.service.mapper.ListLongSeamWeldTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ListLongSeamWeldType}.
 */
@Service
@Transactional
public class ListLongSeamWeldTypeService {

    private final Logger log = LoggerFactory.getLogger(ListLongSeamWeldTypeService.class);

    private final ListLongSeamWeldTypeRepository listLongSeamWeldTypeRepository;

    private final ListLongSeamWeldTypeMapper listLongSeamWeldTypeMapper;

    public ListLongSeamWeldTypeService(ListLongSeamWeldTypeRepository listLongSeamWeldTypeRepository, ListLongSeamWeldTypeMapper listLongSeamWeldTypeMapper) {
        this.listLongSeamWeldTypeRepository = listLongSeamWeldTypeRepository;
        this.listLongSeamWeldTypeMapper = listLongSeamWeldTypeMapper;
    }

    /**
     * Save a listLongSeamWeldType.
     *
     * @param listLongSeamWeldTypeDTO the entity to save.
     * @return the persisted entity.
     */
    public ListLongSeamWeldTypeDTO save(ListLongSeamWeldTypeDTO listLongSeamWeldTypeDTO) {
        log.debug("Request to save ListLongSeamWeldType : {}", listLongSeamWeldTypeDTO);
        ListLongSeamWeldType listLongSeamWeldType = listLongSeamWeldTypeMapper.toEntity(listLongSeamWeldTypeDTO);
        listLongSeamWeldType = listLongSeamWeldTypeRepository.save(listLongSeamWeldType);
        return listLongSeamWeldTypeMapper.toDto(listLongSeamWeldType);
    }

    /**
     * Get all the listLongSeamWeldTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ListLongSeamWeldTypeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ListLongSeamWeldTypes");
        return listLongSeamWeldTypeRepository.findAll(pageable)
            .map(listLongSeamWeldTypeMapper::toDto);
    }


    /**
     * Get one listLongSeamWeldType by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ListLongSeamWeldTypeDTO> findOne(Long id) {
        log.debug("Request to get ListLongSeamWeldType : {}", id);
        return listLongSeamWeldTypeRepository.findById(id)
            .map(listLongSeamWeldTypeMapper::toDto);
    }

    /**
     * Delete the listLongSeamWeldType by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ListLongSeamWeldType : {}", id);
        listLongSeamWeldTypeRepository.deleteById(id);
    }
}
