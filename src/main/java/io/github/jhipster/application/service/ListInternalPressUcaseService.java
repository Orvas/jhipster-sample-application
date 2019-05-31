package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.ListInternalPressUcase;
import io.github.jhipster.application.repository.ListInternalPressUcaseRepository;
import io.github.jhipster.application.service.dto.ListInternalPressUcaseDTO;
import io.github.jhipster.application.service.mapper.ListInternalPressUcaseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ListInternalPressUcase}.
 */
@Service
@Transactional
public class ListInternalPressUcaseService {

    private final Logger log = LoggerFactory.getLogger(ListInternalPressUcaseService.class);

    private final ListInternalPressUcaseRepository listInternalPressUcaseRepository;

    private final ListInternalPressUcaseMapper listInternalPressUcaseMapper;

    public ListInternalPressUcaseService(ListInternalPressUcaseRepository listInternalPressUcaseRepository, ListInternalPressUcaseMapper listInternalPressUcaseMapper) {
        this.listInternalPressUcaseRepository = listInternalPressUcaseRepository;
        this.listInternalPressUcaseMapper = listInternalPressUcaseMapper;
    }

    /**
     * Save a listInternalPressUcase.
     *
     * @param listInternalPressUcaseDTO the entity to save.
     * @return the persisted entity.
     */
    public ListInternalPressUcaseDTO save(ListInternalPressUcaseDTO listInternalPressUcaseDTO) {
        log.debug("Request to save ListInternalPressUcase : {}", listInternalPressUcaseDTO);
        ListInternalPressUcase listInternalPressUcase = listInternalPressUcaseMapper.toEntity(listInternalPressUcaseDTO);
        listInternalPressUcase = listInternalPressUcaseRepository.save(listInternalPressUcase);
        return listInternalPressUcaseMapper.toDto(listInternalPressUcase);
    }

    /**
     * Get all the listInternalPressUcases.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ListInternalPressUcaseDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ListInternalPressUcases");
        return listInternalPressUcaseRepository.findAll(pageable)
            .map(listInternalPressUcaseMapper::toDto);
    }


    /**
     * Get one listInternalPressUcase by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ListInternalPressUcaseDTO> findOne(Long id) {
        log.debug("Request to get ListInternalPressUcase : {}", id);
        return listInternalPressUcaseRepository.findById(id)
            .map(listInternalPressUcaseMapper::toDto);
    }

    /**
     * Delete the listInternalPressUcase by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ListInternalPressUcase : {}", id);
        listInternalPressUcaseRepository.deleteById(id);
    }
}
