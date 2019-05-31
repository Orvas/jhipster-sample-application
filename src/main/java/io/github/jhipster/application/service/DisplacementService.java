package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.Displacement;
import io.github.jhipster.application.repository.DisplacementRepository;
import io.github.jhipster.application.service.dto.DisplacementDTO;
import io.github.jhipster.application.service.mapper.DisplacementMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Displacement}.
 */
@Service
@Transactional
public class DisplacementService {

    private final Logger log = LoggerFactory.getLogger(DisplacementService.class);

    private final DisplacementRepository displacementRepository;

    private final DisplacementMapper displacementMapper;

    public DisplacementService(DisplacementRepository displacementRepository, DisplacementMapper displacementMapper) {
        this.displacementRepository = displacementRepository;
        this.displacementMapper = displacementMapper;
    }

    /**
     * Save a displacement.
     *
     * @param displacementDTO the entity to save.
     * @return the persisted entity.
     */
    public DisplacementDTO save(DisplacementDTO displacementDTO) {
        log.debug("Request to save Displacement : {}", displacementDTO);
        Displacement displacement = displacementMapper.toEntity(displacementDTO);
        displacement = displacementRepository.save(displacement);
        return displacementMapper.toDto(displacement);
    }

    /**
     * Get all the displacements.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<DisplacementDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Displacements");
        return displacementRepository.findAll(pageable)
            .map(displacementMapper::toDto);
    }


    /**
     * Get one displacement by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DisplacementDTO> findOne(Long id) {
        log.debug("Request to get Displacement : {}", id);
        return displacementRepository.findById(id)
            .map(displacementMapper::toDto);
    }

    /**
     * Delete the displacement by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Displacement : {}", id);
        displacementRepository.deleteById(id);
    }
}
