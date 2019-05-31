package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.ListTeeType;
import io.github.jhipster.application.repository.ListTeeTypeRepository;
import io.github.jhipster.application.service.dto.ListTeeTypeDTO;
import io.github.jhipster.application.service.mapper.ListTeeTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ListTeeType}.
 */
@Service
@Transactional
public class ListTeeTypeService {

    private final Logger log = LoggerFactory.getLogger(ListTeeTypeService.class);

    private final ListTeeTypeRepository listTeeTypeRepository;

    private final ListTeeTypeMapper listTeeTypeMapper;

    public ListTeeTypeService(ListTeeTypeRepository listTeeTypeRepository, ListTeeTypeMapper listTeeTypeMapper) {
        this.listTeeTypeRepository = listTeeTypeRepository;
        this.listTeeTypeMapper = listTeeTypeMapper;
    }

    /**
     * Save a listTeeType.
     *
     * @param listTeeTypeDTO the entity to save.
     * @return the persisted entity.
     */
    public ListTeeTypeDTO save(ListTeeTypeDTO listTeeTypeDTO) {
        log.debug("Request to save ListTeeType : {}", listTeeTypeDTO);
        ListTeeType listTeeType = listTeeTypeMapper.toEntity(listTeeTypeDTO);
        listTeeType = listTeeTypeRepository.save(listTeeType);
        return listTeeTypeMapper.toDto(listTeeType);
    }

    /**
     * Get all the listTeeTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ListTeeTypeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ListTeeTypes");
        return listTeeTypeRepository.findAll(pageable)
            .map(listTeeTypeMapper::toDto);
    }


    /**
     * Get one listTeeType by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ListTeeTypeDTO> findOne(Long id) {
        log.debug("Request to get ListTeeType : {}", id);
        return listTeeTypeRepository.findById(id)
            .map(listTeeTypeMapper::toDto);
    }

    /**
     * Delete the listTeeType by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ListTeeType : {}", id);
        listTeeTypeRepository.deleteById(id);
    }
}
