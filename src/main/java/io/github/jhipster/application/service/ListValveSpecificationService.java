package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.ListValveSpecification;
import io.github.jhipster.application.repository.ListValveSpecificationRepository;
import io.github.jhipster.application.service.dto.ListValveSpecificationDTO;
import io.github.jhipster.application.service.mapper.ListValveSpecificationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ListValveSpecification}.
 */
@Service
@Transactional
public class ListValveSpecificationService {

    private final Logger log = LoggerFactory.getLogger(ListValveSpecificationService.class);

    private final ListValveSpecificationRepository listValveSpecificationRepository;

    private final ListValveSpecificationMapper listValveSpecificationMapper;

    public ListValveSpecificationService(ListValveSpecificationRepository listValveSpecificationRepository, ListValveSpecificationMapper listValveSpecificationMapper) {
        this.listValveSpecificationRepository = listValveSpecificationRepository;
        this.listValveSpecificationMapper = listValveSpecificationMapper;
    }

    /**
     * Save a listValveSpecification.
     *
     * @param listValveSpecificationDTO the entity to save.
     * @return the persisted entity.
     */
    public ListValveSpecificationDTO save(ListValveSpecificationDTO listValveSpecificationDTO) {
        log.debug("Request to save ListValveSpecification : {}", listValveSpecificationDTO);
        ListValveSpecification listValveSpecification = listValveSpecificationMapper.toEntity(listValveSpecificationDTO);
        listValveSpecification = listValveSpecificationRepository.save(listValveSpecification);
        return listValveSpecificationMapper.toDto(listValveSpecification);
    }

    /**
     * Get all the listValveSpecifications.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ListValveSpecificationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ListValveSpecifications");
        return listValveSpecificationRepository.findAll(pageable)
            .map(listValveSpecificationMapper::toDto);
    }


    /**
     * Get one listValveSpecification by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ListValveSpecificationDTO> findOne(Long id) {
        log.debug("Request to get ListValveSpecification : {}", id);
        return listValveSpecificationRepository.findById(id)
            .map(listValveSpecificationMapper::toDto);
    }

    /**
     * Delete the listValveSpecification by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ListValveSpecification : {}", id);
        listValveSpecificationRepository.deleteById(id);
    }
}
