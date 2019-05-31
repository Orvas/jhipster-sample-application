package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.DisplacementHist;
import io.github.jhipster.application.repository.DisplacementHistRepository;
import io.github.jhipster.application.service.dto.DisplacementHistDTO;
import io.github.jhipster.application.service.mapper.DisplacementHistMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link DisplacementHist}.
 */
@Service
@Transactional
public class DisplacementHistService {

    private final Logger log = LoggerFactory.getLogger(DisplacementHistService.class);

    private final DisplacementHistRepository displacementHistRepository;

    private final DisplacementHistMapper displacementHistMapper;

    public DisplacementHistService(DisplacementHistRepository displacementHistRepository, DisplacementHistMapper displacementHistMapper) {
        this.displacementHistRepository = displacementHistRepository;
        this.displacementHistMapper = displacementHistMapper;
    }

    /**
     * Save a displacementHist.
     *
     * @param displacementHistDTO the entity to save.
     * @return the persisted entity.
     */
    public DisplacementHistDTO save(DisplacementHistDTO displacementHistDTO) {
        log.debug("Request to save DisplacementHist : {}", displacementHistDTO);
        DisplacementHist displacementHist = displacementHistMapper.toEntity(displacementHistDTO);
        displacementHist = displacementHistRepository.save(displacementHist);
        return displacementHistMapper.toDto(displacementHist);
    }

    /**
     * Get all the displacementHists.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<DisplacementHistDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DisplacementHists");
        return displacementHistRepository.findAll(pageable)
            .map(displacementHistMapper::toDto);
    }


    /**
     * Get one displacementHist by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DisplacementHistDTO> findOne(Long id) {
        log.debug("Request to get DisplacementHist : {}", id);
        return displacementHistRepository.findById(id)
            .map(displacementHistMapper::toDto);
    }

    /**
     * Delete the displacementHist by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete DisplacementHist : {}", id);
        displacementHistRepository.deleteById(id);
    }
}
