package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.ListBendSpecification;
import io.github.jhipster.application.repository.ListBendSpecificationRepository;
import io.github.jhipster.application.service.dto.ListBendSpecificationDTO;
import io.github.jhipster.application.service.mapper.ListBendSpecificationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ListBendSpecification}.
 */
@Service
@Transactional
public class ListBendSpecificationService {

    private final Logger log = LoggerFactory.getLogger(ListBendSpecificationService.class);

    private final ListBendSpecificationRepository listBendSpecificationRepository;

    private final ListBendSpecificationMapper listBendSpecificationMapper;

    public ListBendSpecificationService(ListBendSpecificationRepository listBendSpecificationRepository, ListBendSpecificationMapper listBendSpecificationMapper) {
        this.listBendSpecificationRepository = listBendSpecificationRepository;
        this.listBendSpecificationMapper = listBendSpecificationMapper;
    }

    /**
     * Save a listBendSpecification.
     *
     * @param listBendSpecificationDTO the entity to save.
     * @return the persisted entity.
     */
    public ListBendSpecificationDTO save(ListBendSpecificationDTO listBendSpecificationDTO) {
        log.debug("Request to save ListBendSpecification : {}", listBendSpecificationDTO);
        ListBendSpecification listBendSpecification = listBendSpecificationMapper.toEntity(listBendSpecificationDTO);
        listBendSpecification = listBendSpecificationRepository.save(listBendSpecification);
        return listBendSpecificationMapper.toDto(listBendSpecification);
    }

    /**
     * Get all the listBendSpecifications.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ListBendSpecificationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ListBendSpecifications");
        return listBendSpecificationRepository.findAll(pageable)
            .map(listBendSpecificationMapper::toDto);
    }


    /**
     * Get one listBendSpecification by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ListBendSpecificationDTO> findOne(Long id) {
        log.debug("Request to get ListBendSpecification : {}", id);
        return listBendSpecificationRepository.findById(id)
            .map(listBendSpecificationMapper::toDto);
    }

    /**
     * Delete the listBendSpecification by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ListBendSpecification : {}", id);
        listBendSpecificationRepository.deleteById(id);
    }
}
