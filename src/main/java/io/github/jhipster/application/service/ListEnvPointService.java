package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.ListEnvPoint;
import io.github.jhipster.application.repository.ListEnvPointRepository;
import io.github.jhipster.application.service.dto.ListEnvPointDTO;
import io.github.jhipster.application.service.mapper.ListEnvPointMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ListEnvPoint}.
 */
@Service
@Transactional
public class ListEnvPointService {

    private final Logger log = LoggerFactory.getLogger(ListEnvPointService.class);

    private final ListEnvPointRepository listEnvPointRepository;

    private final ListEnvPointMapper listEnvPointMapper;

    public ListEnvPointService(ListEnvPointRepository listEnvPointRepository, ListEnvPointMapper listEnvPointMapper) {
        this.listEnvPointRepository = listEnvPointRepository;
        this.listEnvPointMapper = listEnvPointMapper;
    }

    /**
     * Save a listEnvPoint.
     *
     * @param listEnvPointDTO the entity to save.
     * @return the persisted entity.
     */
    public ListEnvPointDTO save(ListEnvPointDTO listEnvPointDTO) {
        log.debug("Request to save ListEnvPoint : {}", listEnvPointDTO);
        ListEnvPoint listEnvPoint = listEnvPointMapper.toEntity(listEnvPointDTO);
        listEnvPoint = listEnvPointRepository.save(listEnvPoint);
        return listEnvPointMapper.toDto(listEnvPoint);
    }

    /**
     * Get all the listEnvPoints.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ListEnvPointDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ListEnvPoints");
        return listEnvPointRepository.findAll(pageable)
            .map(listEnvPointMapper::toDto);
    }


    /**
     * Get one listEnvPoint by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ListEnvPointDTO> findOne(Long id) {
        log.debug("Request to get ListEnvPoint : {}", id);
        return listEnvPointRepository.findById(id)
            .map(listEnvPointMapper::toDto);
    }

    /**
     * Delete the listEnvPoint by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ListEnvPoint : {}", id);
        listEnvPointRepository.deleteById(id);
    }
}
