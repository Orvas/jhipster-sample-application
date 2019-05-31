package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.ListExternalCoating;
import io.github.jhipster.application.repository.ListExternalCoatingRepository;
import io.github.jhipster.application.service.dto.ListExternalCoatingDTO;
import io.github.jhipster.application.service.mapper.ListExternalCoatingMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ListExternalCoating}.
 */
@Service
@Transactional
public class ListExternalCoatingService {

    private final Logger log = LoggerFactory.getLogger(ListExternalCoatingService.class);

    private final ListExternalCoatingRepository listExternalCoatingRepository;

    private final ListExternalCoatingMapper listExternalCoatingMapper;

    public ListExternalCoatingService(ListExternalCoatingRepository listExternalCoatingRepository, ListExternalCoatingMapper listExternalCoatingMapper) {
        this.listExternalCoatingRepository = listExternalCoatingRepository;
        this.listExternalCoatingMapper = listExternalCoatingMapper;
    }

    /**
     * Save a listExternalCoating.
     *
     * @param listExternalCoatingDTO the entity to save.
     * @return the persisted entity.
     */
    public ListExternalCoatingDTO save(ListExternalCoatingDTO listExternalCoatingDTO) {
        log.debug("Request to save ListExternalCoating : {}", listExternalCoatingDTO);
        ListExternalCoating listExternalCoating = listExternalCoatingMapper.toEntity(listExternalCoatingDTO);
        listExternalCoating = listExternalCoatingRepository.save(listExternalCoating);
        return listExternalCoatingMapper.toDto(listExternalCoating);
    }

    /**
     * Get all the listExternalCoatings.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ListExternalCoatingDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ListExternalCoatings");
        return listExternalCoatingRepository.findAll(pageable)
            .map(listExternalCoatingMapper::toDto);
    }


    /**
     * Get one listExternalCoating by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ListExternalCoatingDTO> findOne(Long id) {
        log.debug("Request to get ListExternalCoating : {}", id);
        return listExternalCoatingRepository.findById(id)
            .map(listExternalCoatingMapper::toDto);
    }

    /**
     * Delete the listExternalCoating by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ListExternalCoating : {}", id);
        listExternalCoatingRepository.deleteById(id);
    }
}
