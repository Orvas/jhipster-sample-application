package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.BendHist;
import io.github.jhipster.application.repository.BendHistRepository;
import io.github.jhipster.application.service.dto.BendHistDTO;
import io.github.jhipster.application.service.mapper.BendHistMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link BendHist}.
 */
@Service
@Transactional
public class BendHistService {

    private final Logger log = LoggerFactory.getLogger(BendHistService.class);

    private final BendHistRepository bendHistRepository;

    private final BendHistMapper bendHistMapper;

    public BendHistService(BendHistRepository bendHistRepository, BendHistMapper bendHistMapper) {
        this.bendHistRepository = bendHistRepository;
        this.bendHistMapper = bendHistMapper;
    }

    /**
     * Save a bendHist.
     *
     * @param bendHistDTO the entity to save.
     * @return the persisted entity.
     */
    public BendHistDTO save(BendHistDTO bendHistDTO) {
        log.debug("Request to save BendHist : {}", bendHistDTO);
        BendHist bendHist = bendHistMapper.toEntity(bendHistDTO);
        bendHist = bendHistRepository.save(bendHist);
        return bendHistMapper.toDto(bendHist);
    }

    /**
     * Get all the bendHists.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<BendHistDTO> findAll(Pageable pageable) {
        log.debug("Request to get all BendHists");
        return bendHistRepository.findAll(pageable)
            .map(bendHistMapper::toDto);
    }


    /**
     * Get one bendHist by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BendHistDTO> findOne(Long id) {
        log.debug("Request to get BendHist : {}", id);
        return bendHistRepository.findById(id)
            .map(bendHistMapper::toDto);
    }

    /**
     * Delete the bendHist by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete BendHist : {}", id);
        bendHistRepository.deleteById(id);
    }
}
