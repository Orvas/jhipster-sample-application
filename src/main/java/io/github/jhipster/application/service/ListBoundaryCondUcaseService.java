package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.ListBoundaryCondUcase;
import io.github.jhipster.application.repository.ListBoundaryCondUcaseRepository;
import io.github.jhipster.application.service.dto.ListBoundaryCondUcaseDTO;
import io.github.jhipster.application.service.mapper.ListBoundaryCondUcaseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ListBoundaryCondUcase}.
 */
@Service
@Transactional
public class ListBoundaryCondUcaseService {

    private final Logger log = LoggerFactory.getLogger(ListBoundaryCondUcaseService.class);

    private final ListBoundaryCondUcaseRepository listBoundaryCondUcaseRepository;

    private final ListBoundaryCondUcaseMapper listBoundaryCondUcaseMapper;

    public ListBoundaryCondUcaseService(ListBoundaryCondUcaseRepository listBoundaryCondUcaseRepository, ListBoundaryCondUcaseMapper listBoundaryCondUcaseMapper) {
        this.listBoundaryCondUcaseRepository = listBoundaryCondUcaseRepository;
        this.listBoundaryCondUcaseMapper = listBoundaryCondUcaseMapper;
    }

    /**
     * Save a listBoundaryCondUcase.
     *
     * @param listBoundaryCondUcaseDTO the entity to save.
     * @return the persisted entity.
     */
    public ListBoundaryCondUcaseDTO save(ListBoundaryCondUcaseDTO listBoundaryCondUcaseDTO) {
        log.debug("Request to save ListBoundaryCondUcase : {}", listBoundaryCondUcaseDTO);
        ListBoundaryCondUcase listBoundaryCondUcase = listBoundaryCondUcaseMapper.toEntity(listBoundaryCondUcaseDTO);
        listBoundaryCondUcase = listBoundaryCondUcaseRepository.save(listBoundaryCondUcase);
        return listBoundaryCondUcaseMapper.toDto(listBoundaryCondUcase);
    }

    /**
     * Get all the listBoundaryCondUcases.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ListBoundaryCondUcaseDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ListBoundaryCondUcases");
        return listBoundaryCondUcaseRepository.findAll(pageable)
            .map(listBoundaryCondUcaseMapper::toDto);
    }


    /**
     * Get one listBoundaryCondUcase by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ListBoundaryCondUcaseDTO> findOne(Long id) {
        log.debug("Request to get ListBoundaryCondUcase : {}", id);
        return listBoundaryCondUcaseRepository.findById(id)
            .map(listBoundaryCondUcaseMapper::toDto);
    }

    /**
     * Delete the listBoundaryCondUcase by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ListBoundaryCondUcase : {}", id);
        listBoundaryCondUcaseRepository.deleteById(id);
    }
}
