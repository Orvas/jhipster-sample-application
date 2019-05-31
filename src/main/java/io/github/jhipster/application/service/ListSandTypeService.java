package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.ListSandType;
import io.github.jhipster.application.repository.ListSandTypeRepository;
import io.github.jhipster.application.service.dto.ListSandTypeDTO;
import io.github.jhipster.application.service.mapper.ListSandTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ListSandType}.
 */
@Service
@Transactional
public class ListSandTypeService {

    private final Logger log = LoggerFactory.getLogger(ListSandTypeService.class);

    private final ListSandTypeRepository listSandTypeRepository;

    private final ListSandTypeMapper listSandTypeMapper;

    public ListSandTypeService(ListSandTypeRepository listSandTypeRepository, ListSandTypeMapper listSandTypeMapper) {
        this.listSandTypeRepository = listSandTypeRepository;
        this.listSandTypeMapper = listSandTypeMapper;
    }

    /**
     * Save a listSandType.
     *
     * @param listSandTypeDTO the entity to save.
     * @return the persisted entity.
     */
    public ListSandTypeDTO save(ListSandTypeDTO listSandTypeDTO) {
        log.debug("Request to save ListSandType : {}", listSandTypeDTO);
        ListSandType listSandType = listSandTypeMapper.toEntity(listSandTypeDTO);
        listSandType = listSandTypeRepository.save(listSandType);
        return listSandTypeMapper.toDto(listSandType);
    }

    /**
     * Get all the listSandTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ListSandTypeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ListSandTypes");
        return listSandTypeRepository.findAll(pageable)
            .map(listSandTypeMapper::toDto);
    }


    /**
     * Get one listSandType by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ListSandTypeDTO> findOne(Long id) {
        log.debug("Request to get ListSandType : {}", id);
        return listSandTypeRepository.findById(id)
            .map(listSandTypeMapper::toDto);
    }

    /**
     * Delete the listSandType by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ListSandType : {}", id);
        listSandTypeRepository.deleteById(id);
    }
}
