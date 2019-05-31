package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.Anode;
import io.github.jhipster.application.repository.AnodeRepository;
import io.github.jhipster.application.service.dto.AnodeDTO;
import io.github.jhipster.application.service.mapper.AnodeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Anode}.
 */
@Service
@Transactional
public class AnodeService {

    private final Logger log = LoggerFactory.getLogger(AnodeService.class);

    private final AnodeRepository anodeRepository;

    private final AnodeMapper anodeMapper;

    public AnodeService(AnodeRepository anodeRepository, AnodeMapper anodeMapper) {
        this.anodeRepository = anodeRepository;
        this.anodeMapper = anodeMapper;
    }

    /**
     * Save a anode.
     *
     * @param anodeDTO the entity to save.
     * @return the persisted entity.
     */
    public AnodeDTO save(AnodeDTO anodeDTO) {
        log.debug("Request to save Anode : {}", anodeDTO);
        Anode anode = anodeMapper.toEntity(anodeDTO);
        anode = anodeRepository.save(anode);
        return anodeMapper.toDto(anode);
    }

    /**
     * Get all the anodes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<AnodeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Anodes");
        return anodeRepository.findAll(pageable)
            .map(anodeMapper::toDto);
    }


    /**
     * Get one anode by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<AnodeDTO> findOne(Long id) {
        log.debug("Request to get Anode : {}", id);
        return anodeRepository.findById(id)
            .map(anodeMapper::toDto);
    }

    /**
     * Delete the anode by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Anode : {}", id);
        anodeRepository.deleteById(id);
    }
}
