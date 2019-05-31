package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.ListEnvDirection;
import io.github.jhipster.application.repository.ListEnvDirectionRepository;
import io.github.jhipster.application.service.dto.ListEnvDirectionDTO;
import io.github.jhipster.application.service.mapper.ListEnvDirectionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ListEnvDirection}.
 */
@Service
@Transactional
public class ListEnvDirectionService {

    private final Logger log = LoggerFactory.getLogger(ListEnvDirectionService.class);

    private final ListEnvDirectionRepository listEnvDirectionRepository;

    private final ListEnvDirectionMapper listEnvDirectionMapper;

    public ListEnvDirectionService(ListEnvDirectionRepository listEnvDirectionRepository, ListEnvDirectionMapper listEnvDirectionMapper) {
        this.listEnvDirectionRepository = listEnvDirectionRepository;
        this.listEnvDirectionMapper = listEnvDirectionMapper;
    }

    /**
     * Save a listEnvDirection.
     *
     * @param listEnvDirectionDTO the entity to save.
     * @return the persisted entity.
     */
    public ListEnvDirectionDTO save(ListEnvDirectionDTO listEnvDirectionDTO) {
        log.debug("Request to save ListEnvDirection : {}", listEnvDirectionDTO);
        ListEnvDirection listEnvDirection = listEnvDirectionMapper.toEntity(listEnvDirectionDTO);
        listEnvDirection = listEnvDirectionRepository.save(listEnvDirection);
        return listEnvDirectionMapper.toDto(listEnvDirection);
    }

    /**
     * Get all the listEnvDirections.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ListEnvDirectionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ListEnvDirections");
        return listEnvDirectionRepository.findAll(pageable)
            .map(listEnvDirectionMapper::toDto);
    }


    /**
     * Get one listEnvDirection by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ListEnvDirectionDTO> findOne(Long id) {
        log.debug("Request to get ListEnvDirection : {}", id);
        return listEnvDirectionRepository.findById(id)
            .map(listEnvDirectionMapper::toDto);
    }

    /**
     * Delete the listEnvDirection by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ListEnvDirection : {}", id);
        listEnvDirectionRepository.deleteById(id);
    }
}
