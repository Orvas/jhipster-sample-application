package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.LaunchReceiverHist;
import io.github.jhipster.application.repository.LaunchReceiverHistRepository;
import io.github.jhipster.application.service.dto.LaunchReceiverHistDTO;
import io.github.jhipster.application.service.mapper.LaunchReceiverHistMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link LaunchReceiverHist}.
 */
@Service
@Transactional
public class LaunchReceiverHistService {

    private final Logger log = LoggerFactory.getLogger(LaunchReceiverHistService.class);

    private final LaunchReceiverHistRepository launchReceiverHistRepository;

    private final LaunchReceiverHistMapper launchReceiverHistMapper;

    public LaunchReceiverHistService(LaunchReceiverHistRepository launchReceiverHistRepository, LaunchReceiverHistMapper launchReceiverHistMapper) {
        this.launchReceiverHistRepository = launchReceiverHistRepository;
        this.launchReceiverHistMapper = launchReceiverHistMapper;
    }

    /**
     * Save a launchReceiverHist.
     *
     * @param launchReceiverHistDTO the entity to save.
     * @return the persisted entity.
     */
    public LaunchReceiverHistDTO save(LaunchReceiverHistDTO launchReceiverHistDTO) {
        log.debug("Request to save LaunchReceiverHist : {}", launchReceiverHistDTO);
        LaunchReceiverHist launchReceiverHist = launchReceiverHistMapper.toEntity(launchReceiverHistDTO);
        launchReceiverHist = launchReceiverHistRepository.save(launchReceiverHist);
        return launchReceiverHistMapper.toDto(launchReceiverHist);
    }

    /**
     * Get all the launchReceiverHists.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<LaunchReceiverHistDTO> findAll(Pageable pageable) {
        log.debug("Request to get all LaunchReceiverHists");
        return launchReceiverHistRepository.findAll(pageable)
            .map(launchReceiverHistMapper::toDto);
    }


    /**
     * Get one launchReceiverHist by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<LaunchReceiverHistDTO> findOne(Long id) {
        log.debug("Request to get LaunchReceiverHist : {}", id);
        return launchReceiverHistRepository.findById(id)
            .map(launchReceiverHistMapper::toDto);
    }

    /**
     * Delete the launchReceiverHist by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete LaunchReceiverHist : {}", id);
        launchReceiverHistRepository.deleteById(id);
    }
}
