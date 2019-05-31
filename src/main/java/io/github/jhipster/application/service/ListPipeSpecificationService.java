package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.ListPipeSpecification;
import io.github.jhipster.application.repository.ListPipeSpecificationRepository;
import io.github.jhipster.application.service.dto.ListPipeSpecificationDTO;
import io.github.jhipster.application.service.mapper.ListPipeSpecificationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ListPipeSpecification}.
 */
@Service
@Transactional
public class ListPipeSpecificationService {

    private final Logger log = LoggerFactory.getLogger(ListPipeSpecificationService.class);

    private final ListPipeSpecificationRepository listPipeSpecificationRepository;

    private final ListPipeSpecificationMapper listPipeSpecificationMapper;

    public ListPipeSpecificationService(ListPipeSpecificationRepository listPipeSpecificationRepository, ListPipeSpecificationMapper listPipeSpecificationMapper) {
        this.listPipeSpecificationRepository = listPipeSpecificationRepository;
        this.listPipeSpecificationMapper = listPipeSpecificationMapper;
    }

    /**
     * Save a listPipeSpecification.
     *
     * @param listPipeSpecificationDTO the entity to save.
     * @return the persisted entity.
     */
    public ListPipeSpecificationDTO save(ListPipeSpecificationDTO listPipeSpecificationDTO) {
        log.debug("Request to save ListPipeSpecification : {}", listPipeSpecificationDTO);
        ListPipeSpecification listPipeSpecification = listPipeSpecificationMapper.toEntity(listPipeSpecificationDTO);
        listPipeSpecification = listPipeSpecificationRepository.save(listPipeSpecification);
        return listPipeSpecificationMapper.toDto(listPipeSpecification);
    }

    /**
     * Get all the listPipeSpecifications.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ListPipeSpecificationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ListPipeSpecifications");
        return listPipeSpecificationRepository.findAll(pageable)
            .map(listPipeSpecificationMapper::toDto);
    }


    /**
     * Get one listPipeSpecification by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ListPipeSpecificationDTO> findOne(Long id) {
        log.debug("Request to get ListPipeSpecification : {}", id);
        return listPipeSpecificationRepository.findById(id)
            .map(listPipeSpecificationMapper::toDto);
    }

    /**
     * Delete the listPipeSpecification by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ListPipeSpecification : {}", id);
        listPipeSpecificationRepository.deleteById(id);
    }
}
