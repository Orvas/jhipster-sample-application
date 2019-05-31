package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.Pipe;
import io.github.jhipster.application.repository.PipeRepository;
import io.github.jhipster.application.service.dto.PipeDTO;
import io.github.jhipster.application.service.mapper.PipeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Pipe}.
 */
@Service
@Transactional
public class PipeService {

    private final Logger log = LoggerFactory.getLogger(PipeService.class);

    private final PipeRepository pipeRepository;

    private final PipeMapper pipeMapper;

    public PipeService(PipeRepository pipeRepository, PipeMapper pipeMapper) {
        this.pipeRepository = pipeRepository;
        this.pipeMapper = pipeMapper;
    }

    /**
     * Save a pipe.
     *
     * @param pipeDTO the entity to save.
     * @return the persisted entity.
     */
    public PipeDTO save(PipeDTO pipeDTO) {
        log.debug("Request to save Pipe : {}", pipeDTO);
        Pipe pipe = pipeMapper.toEntity(pipeDTO);
        pipe = pipeRepository.save(pipe);
        return pipeMapper.toDto(pipe);
    }

    /**
     * Get all the pipes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<PipeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Pipes");
        return pipeRepository.findAll(pageable)
            .map(pipeMapper::toDto);
    }


    /**
     * Get one pipe by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PipeDTO> findOne(Long id) {
        log.debug("Request to get Pipe : {}", id);
        return pipeRepository.findById(id)
            .map(pipeMapper::toDto);
    }

    /**
     * Delete the pipe by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Pipe : {}", id);
        pipeRepository.deleteById(id);
    }
}
