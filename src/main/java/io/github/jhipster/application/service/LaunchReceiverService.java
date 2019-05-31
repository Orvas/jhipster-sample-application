package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.LaunchReceiver;
import io.github.jhipster.application.repository.LaunchReceiverRepository;
import io.github.jhipster.application.service.dto.LaunchReceiverDTO;
import io.github.jhipster.application.service.mapper.LaunchReceiverMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service Implementation for managing {@link LaunchReceiver}.
 */
@Service
@Transactional
public class LaunchReceiverService {

    private final Logger log = LoggerFactory.getLogger(LaunchReceiverService.class);

    private final LaunchReceiverRepository launchReceiverRepository;

    private final LaunchReceiverMapper launchReceiverMapper;

    public LaunchReceiverService(LaunchReceiverRepository launchReceiverRepository, LaunchReceiverMapper launchReceiverMapper) {
        this.launchReceiverRepository = launchReceiverRepository;
        this.launchReceiverMapper = launchReceiverMapper;
    }

    /**
     * Save a launchReceiver.
     *
     * @param launchReceiverDTO the entity to save.
     * @return the persisted entity.
     */
    public LaunchReceiverDTO save(LaunchReceiverDTO launchReceiverDTO) {
        log.debug("Request to save LaunchReceiver : {}", launchReceiverDTO);
        LaunchReceiver launchReceiver = launchReceiverMapper.toEntity(launchReceiverDTO);
        launchReceiver = launchReceiverRepository.save(launchReceiver);
        return launchReceiverMapper.toDto(launchReceiver);
    }

    /**
     * Get all the launchReceivers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<LaunchReceiverDTO> findAll(Pageable pageable) {
        log.debug("Request to get all LaunchReceivers");
        return launchReceiverRepository.findAll(pageable)
            .map(launchReceiverMapper::toDto);
    }



    /**
    *  Get all the launchReceivers where LaunchReceiverHist is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<LaunchReceiverDTO> findAllWhereLaunchReceiverHistIsNull() {
        log.debug("Request to get all launchReceivers where LaunchReceiverHist is null");
        return StreamSupport
            .stream(launchReceiverRepository.findAll().spliterator(), false)
            .filter(launchReceiver -> launchReceiver.getLaunchReceiverHist() == null)
            .map(launchReceiverMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one launchReceiver by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<LaunchReceiverDTO> findOne(Long id) {
        log.debug("Request to get LaunchReceiver : {}", id);
        return launchReceiverRepository.findById(id)
            .map(launchReceiverMapper::toDto);
    }

    /**
     * Delete the launchReceiver by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete LaunchReceiver : {}", id);
        launchReceiverRepository.deleteById(id);
    }
}
