package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.ListMillLocation;
import io.github.jhipster.application.repository.ListMillLocationRepository;
import io.github.jhipster.application.service.dto.ListMillLocationDTO;
import io.github.jhipster.application.service.mapper.ListMillLocationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ListMillLocation}.
 */
@Service
@Transactional
public class ListMillLocationService {

    private final Logger log = LoggerFactory.getLogger(ListMillLocationService.class);

    private final ListMillLocationRepository listMillLocationRepository;

    private final ListMillLocationMapper listMillLocationMapper;

    public ListMillLocationService(ListMillLocationRepository listMillLocationRepository, ListMillLocationMapper listMillLocationMapper) {
        this.listMillLocationRepository = listMillLocationRepository;
        this.listMillLocationMapper = listMillLocationMapper;
    }

    /**
     * Save a listMillLocation.
     *
     * @param listMillLocationDTO the entity to save.
     * @return the persisted entity.
     */
    public ListMillLocationDTO save(ListMillLocationDTO listMillLocationDTO) {
        log.debug("Request to save ListMillLocation : {}", listMillLocationDTO);
        ListMillLocation listMillLocation = listMillLocationMapper.toEntity(listMillLocationDTO);
        listMillLocation = listMillLocationRepository.save(listMillLocation);
        return listMillLocationMapper.toDto(listMillLocation);
    }

    /**
     * Get all the listMillLocations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ListMillLocationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ListMillLocations");
        return listMillLocationRepository.findAll(pageable)
            .map(listMillLocationMapper::toDto);
    }


    /**
     * Get one listMillLocation by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ListMillLocationDTO> findOne(Long id) {
        log.debug("Request to get ListMillLocation : {}", id);
        return listMillLocationRepository.findById(id)
            .map(listMillLocationMapper::toDto);
    }

    /**
     * Delete the listMillLocation by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ListMillLocation : {}", id);
        listMillLocationRepository.deleteById(id);
    }
}
