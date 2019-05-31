package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.BuckleArrestor;
import io.github.jhipster.application.repository.BuckleArrestorRepository;
import io.github.jhipster.application.service.dto.BuckleArrestorDTO;
import io.github.jhipster.application.service.mapper.BuckleArrestorMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link BuckleArrestor}.
 */
@Service
@Transactional
public class BuckleArrestorService {

    private final Logger log = LoggerFactory.getLogger(BuckleArrestorService.class);

    private final BuckleArrestorRepository buckleArrestorRepository;

    private final BuckleArrestorMapper buckleArrestorMapper;

    public BuckleArrestorService(BuckleArrestorRepository buckleArrestorRepository, BuckleArrestorMapper buckleArrestorMapper) {
        this.buckleArrestorRepository = buckleArrestorRepository;
        this.buckleArrestorMapper = buckleArrestorMapper;
    }

    /**
     * Save a buckleArrestor.
     *
     * @param buckleArrestorDTO the entity to save.
     * @return the persisted entity.
     */
    public BuckleArrestorDTO save(BuckleArrestorDTO buckleArrestorDTO) {
        log.debug("Request to save BuckleArrestor : {}", buckleArrestorDTO);
        BuckleArrestor buckleArrestor = buckleArrestorMapper.toEntity(buckleArrestorDTO);
        buckleArrestor = buckleArrestorRepository.save(buckleArrestor);
        return buckleArrestorMapper.toDto(buckleArrestor);
    }

    /**
     * Get all the buckleArrestors.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<BuckleArrestorDTO> findAll(Pageable pageable) {
        log.debug("Request to get all BuckleArrestors");
        return buckleArrestorRepository.findAll(pageable)
            .map(buckleArrestorMapper::toDto);
    }


    /**
     * Get one buckleArrestor by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BuckleArrestorDTO> findOne(Long id) {
        log.debug("Request to get BuckleArrestor : {}", id);
        return buckleArrestorRepository.findById(id)
            .map(buckleArrestorMapper::toDto);
    }

    /**
     * Delete the buckleArrestor by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete BuckleArrestor : {}", id);
        buckleArrestorRepository.deleteById(id);
    }
}
