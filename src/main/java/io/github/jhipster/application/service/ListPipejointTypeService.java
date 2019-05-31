package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.ListPipejointType;
import io.github.jhipster.application.repository.ListPipejointTypeRepository;
import io.github.jhipster.application.service.dto.ListPipejointTypeDTO;
import io.github.jhipster.application.service.mapper.ListPipejointTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ListPipejointType}.
 */
@Service
@Transactional
public class ListPipejointTypeService {

    private final Logger log = LoggerFactory.getLogger(ListPipejointTypeService.class);

    private final ListPipejointTypeRepository listPipejointTypeRepository;

    private final ListPipejointTypeMapper listPipejointTypeMapper;

    public ListPipejointTypeService(ListPipejointTypeRepository listPipejointTypeRepository, ListPipejointTypeMapper listPipejointTypeMapper) {
        this.listPipejointTypeRepository = listPipejointTypeRepository;
        this.listPipejointTypeMapper = listPipejointTypeMapper;
    }

    /**
     * Save a listPipejointType.
     *
     * @param listPipejointTypeDTO the entity to save.
     * @return the persisted entity.
     */
    public ListPipejointTypeDTO save(ListPipejointTypeDTO listPipejointTypeDTO) {
        log.debug("Request to save ListPipejointType : {}", listPipejointTypeDTO);
        ListPipejointType listPipejointType = listPipejointTypeMapper.toEntity(listPipejointTypeDTO);
        listPipejointType = listPipejointTypeRepository.save(listPipejointType);
        return listPipejointTypeMapper.toDto(listPipejointType);
    }

    /**
     * Get all the listPipejointTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ListPipejointTypeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ListPipejointTypes");
        return listPipejointTypeRepository.findAll(pageable)
            .map(listPipejointTypeMapper::toDto);
    }


    /**
     * Get one listPipejointType by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ListPipejointTypeDTO> findOne(Long id) {
        log.debug("Request to get ListPipejointType : {}", id);
        return listPipejointTypeRepository.findById(id)
            .map(listPipejointTypeMapper::toDto);
    }

    /**
     * Delete the listPipejointType by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ListPipejointType : {}", id);
        listPipejointTypeRepository.deleteById(id);
    }
}
