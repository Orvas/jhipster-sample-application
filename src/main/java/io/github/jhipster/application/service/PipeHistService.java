package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.PipeHist;
import io.github.jhipster.application.repository.PipeHistRepository;
import io.github.jhipster.application.service.dto.PipeHistDTO;
import io.github.jhipster.application.service.mapper.PipeHistMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link PipeHist}.
 */
@Service
@Transactional
public class PipeHistService {

    private final Logger log = LoggerFactory.getLogger(PipeHistService.class);

    private final PipeHistRepository pipeHistRepository;

    private final PipeHistMapper pipeHistMapper;

    public PipeHistService(PipeHistRepository pipeHistRepository, PipeHistMapper pipeHistMapper) {
        this.pipeHistRepository = pipeHistRepository;
        this.pipeHistMapper = pipeHistMapper;
    }

    /**
     * Save a pipeHist.
     *
     * @param pipeHistDTO the entity to save.
     * @return the persisted entity.
     */
    public PipeHistDTO save(PipeHistDTO pipeHistDTO) {
        log.debug("Request to save PipeHist : {}", pipeHistDTO);
        PipeHist pipeHist = pipeHistMapper.toEntity(pipeHistDTO);
        pipeHist = pipeHistRepository.save(pipeHist);
        return pipeHistMapper.toDto(pipeHist);
    }

    /**
     * Get all the pipeHists.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<PipeHistDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PipeHists");
        return pipeHistRepository.findAll(pageable)
            .map(pipeHistMapper::toDto);
    }


    /**
     * Get one pipeHist by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PipeHistDTO> findOne(Long id) {
        log.debug("Request to get PipeHist : {}", id);
        return pipeHistRepository.findById(id)
            .map(pipeHistMapper::toDto);
    }

    /**
     * Delete the pipeHist by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete PipeHist : {}", id);
        pipeHistRepository.deleteById(id);
    }
}
