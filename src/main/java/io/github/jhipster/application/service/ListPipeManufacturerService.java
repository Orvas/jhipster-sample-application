package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.ListPipeManufacturer;
import io.github.jhipster.application.repository.ListPipeManufacturerRepository;
import io.github.jhipster.application.service.dto.ListPipeManufacturerDTO;
import io.github.jhipster.application.service.mapper.ListPipeManufacturerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ListPipeManufacturer}.
 */
@Service
@Transactional
public class ListPipeManufacturerService {

    private final Logger log = LoggerFactory.getLogger(ListPipeManufacturerService.class);

    private final ListPipeManufacturerRepository listPipeManufacturerRepository;

    private final ListPipeManufacturerMapper listPipeManufacturerMapper;

    public ListPipeManufacturerService(ListPipeManufacturerRepository listPipeManufacturerRepository, ListPipeManufacturerMapper listPipeManufacturerMapper) {
        this.listPipeManufacturerRepository = listPipeManufacturerRepository;
        this.listPipeManufacturerMapper = listPipeManufacturerMapper;
    }

    /**
     * Save a listPipeManufacturer.
     *
     * @param listPipeManufacturerDTO the entity to save.
     * @return the persisted entity.
     */
    public ListPipeManufacturerDTO save(ListPipeManufacturerDTO listPipeManufacturerDTO) {
        log.debug("Request to save ListPipeManufacturer : {}", listPipeManufacturerDTO);
        ListPipeManufacturer listPipeManufacturer = listPipeManufacturerMapper.toEntity(listPipeManufacturerDTO);
        listPipeManufacturer = listPipeManufacturerRepository.save(listPipeManufacturer);
        return listPipeManufacturerMapper.toDto(listPipeManufacturer);
    }

    /**
     * Get all the listPipeManufacturers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ListPipeManufacturerDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ListPipeManufacturers");
        return listPipeManufacturerRepository.findAll(pageable)
            .map(listPipeManufacturerMapper::toDto);
    }


    /**
     * Get one listPipeManufacturer by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ListPipeManufacturerDTO> findOne(Long id) {
        log.debug("Request to get ListPipeManufacturer : {}", id);
        return listPipeManufacturerRepository.findById(id)
            .map(listPipeManufacturerMapper::toDto);
    }

    /**
     * Delete the listPipeManufacturer by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ListPipeManufacturer : {}", id);
        listPipeManufacturerRepository.deleteById(id);
    }
}
