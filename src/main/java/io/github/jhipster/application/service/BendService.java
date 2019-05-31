package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.Bend;
import io.github.jhipster.application.repository.BendRepository;
import io.github.jhipster.application.service.dto.BendDTO;
import io.github.jhipster.application.service.mapper.BendMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Bend}.
 */
@Service
@Transactional
public class BendService {

    private final Logger log = LoggerFactory.getLogger(BendService.class);

    private final BendRepository bendRepository;

    private final BendMapper bendMapper;

    public BendService(BendRepository bendRepository, BendMapper bendMapper) {
        this.bendRepository = bendRepository;
        this.bendMapper = bendMapper;
    }

    /**
     * Save a bend.
     *
     * @param bendDTO the entity to save.
     * @return the persisted entity.
     */
    public BendDTO save(BendDTO bendDTO) {
        log.debug("Request to save Bend : {}", bendDTO);
        Bend bend = bendMapper.toEntity(bendDTO);
        bend = bendRepository.save(bend);
        return bendMapper.toDto(bend);
    }

    /**
     * Get all the bends.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<BendDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Bends");
        return bendRepository.findAll(pageable)
            .map(bendMapper::toDto);
    }


    /**
     * Get one bend by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BendDTO> findOne(Long id) {
        log.debug("Request to get Bend : {}", id);
        return bendRepository.findById(id)
            .map(bendMapper::toDto);
    }

    /**
     * Delete the bend by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Bend : {}", id);
        bendRepository.deleteById(id);
    }
}
