package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.BuckleArrestorHist;
import io.github.jhipster.application.repository.BuckleArrestorHistRepository;
import io.github.jhipster.application.service.dto.BuckleArrestorHistDTO;
import io.github.jhipster.application.service.mapper.BuckleArrestorHistMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link BuckleArrestorHist}.
 */
@Service
@Transactional
public class BuckleArrestorHistService {

    private final Logger log = LoggerFactory.getLogger(BuckleArrestorHistService.class);

    private final BuckleArrestorHistRepository buckleArrestorHistRepository;

    private final BuckleArrestorHistMapper buckleArrestorHistMapper;

    public BuckleArrestorHistService(BuckleArrestorHistRepository buckleArrestorHistRepository, BuckleArrestorHistMapper buckleArrestorHistMapper) {
        this.buckleArrestorHistRepository = buckleArrestorHistRepository;
        this.buckleArrestorHistMapper = buckleArrestorHistMapper;
    }

    /**
     * Save a buckleArrestorHist.
     *
     * @param buckleArrestorHistDTO the entity to save.
     * @return the persisted entity.
     */
    public BuckleArrestorHistDTO save(BuckleArrestorHistDTO buckleArrestorHistDTO) {
        log.debug("Request to save BuckleArrestorHist : {}", buckleArrestorHistDTO);
        BuckleArrestorHist buckleArrestorHist = buckleArrestorHistMapper.toEntity(buckleArrestorHistDTO);
        buckleArrestorHist = buckleArrestorHistRepository.save(buckleArrestorHist);
        return buckleArrestorHistMapper.toDto(buckleArrestorHist);
    }

    /**
     * Get all the buckleArrestorHists.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<BuckleArrestorHistDTO> findAll(Pageable pageable) {
        log.debug("Request to get all BuckleArrestorHists");
        return buckleArrestorHistRepository.findAll(pageable)
            .map(buckleArrestorHistMapper::toDto);
    }


    /**
     * Get one buckleArrestorHist by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BuckleArrestorHistDTO> findOne(Long id) {
        log.debug("Request to get BuckleArrestorHist : {}", id);
        return buckleArrestorHistRepository.findById(id)
            .map(buckleArrestorHistMapper::toDto);
    }

    /**
     * Delete the buckleArrestorHist by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete BuckleArrestorHist : {}", id);
        buckleArrestorHistRepository.deleteById(id);
    }
}
