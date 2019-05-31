package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.ListPipelineLocation;
import io.github.jhipster.application.repository.ListPipelineLocationRepository;
import io.github.jhipster.application.service.dto.ListPipelineLocationDTO;
import io.github.jhipster.application.service.mapper.ListPipelineLocationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ListPipelineLocation}.
 */
@Service
@Transactional
public class ListPipelineLocationService {

    private final Logger log = LoggerFactory.getLogger(ListPipelineLocationService.class);

    private final ListPipelineLocationRepository listPipelineLocationRepository;

    private final ListPipelineLocationMapper listPipelineLocationMapper;

    public ListPipelineLocationService(ListPipelineLocationRepository listPipelineLocationRepository, ListPipelineLocationMapper listPipelineLocationMapper) {
        this.listPipelineLocationRepository = listPipelineLocationRepository;
        this.listPipelineLocationMapper = listPipelineLocationMapper;
    }

    /**
     * Save a listPipelineLocation.
     *
     * @param listPipelineLocationDTO the entity to save.
     * @return the persisted entity.
     */
    public ListPipelineLocationDTO save(ListPipelineLocationDTO listPipelineLocationDTO) {
        log.debug("Request to save ListPipelineLocation : {}", listPipelineLocationDTO);
        ListPipelineLocation listPipelineLocation = listPipelineLocationMapper.toEntity(listPipelineLocationDTO);
        listPipelineLocation = listPipelineLocationRepository.save(listPipelineLocation);
        return listPipelineLocationMapper.toDto(listPipelineLocation);
    }

    /**
     * Get all the listPipelineLocations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ListPipelineLocationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ListPipelineLocations");
        return listPipelineLocationRepository.findAll(pageable)
            .map(listPipelineLocationMapper::toDto);
    }


    /**
     * Get one listPipelineLocation by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ListPipelineLocationDTO> findOne(Long id) {
        log.debug("Request to get ListPipelineLocation : {}", id);
        return listPipelineLocationRepository.findById(id)
            .map(listPipelineLocationMapper::toDto);
    }

    /**
     * Delete the listPipelineLocation by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ListPipelineLocation : {}", id);
        listPipelineLocationRepository.deleteById(id);
    }
}
