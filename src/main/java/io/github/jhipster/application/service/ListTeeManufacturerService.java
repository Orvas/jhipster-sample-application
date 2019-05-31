package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.ListTeeManufacturer;
import io.github.jhipster.application.repository.ListTeeManufacturerRepository;
import io.github.jhipster.application.service.dto.ListTeeManufacturerDTO;
import io.github.jhipster.application.service.mapper.ListTeeManufacturerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ListTeeManufacturer}.
 */
@Service
@Transactional
public class ListTeeManufacturerService {

    private final Logger log = LoggerFactory.getLogger(ListTeeManufacturerService.class);

    private final ListTeeManufacturerRepository listTeeManufacturerRepository;

    private final ListTeeManufacturerMapper listTeeManufacturerMapper;

    public ListTeeManufacturerService(ListTeeManufacturerRepository listTeeManufacturerRepository, ListTeeManufacturerMapper listTeeManufacturerMapper) {
        this.listTeeManufacturerRepository = listTeeManufacturerRepository;
        this.listTeeManufacturerMapper = listTeeManufacturerMapper;
    }

    /**
     * Save a listTeeManufacturer.
     *
     * @param listTeeManufacturerDTO the entity to save.
     * @return the persisted entity.
     */
    public ListTeeManufacturerDTO save(ListTeeManufacturerDTO listTeeManufacturerDTO) {
        log.debug("Request to save ListTeeManufacturer : {}", listTeeManufacturerDTO);
        ListTeeManufacturer listTeeManufacturer = listTeeManufacturerMapper.toEntity(listTeeManufacturerDTO);
        listTeeManufacturer = listTeeManufacturerRepository.save(listTeeManufacturer);
        return listTeeManufacturerMapper.toDto(listTeeManufacturer);
    }

    /**
     * Get all the listTeeManufacturers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ListTeeManufacturerDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ListTeeManufacturers");
        return listTeeManufacturerRepository.findAll(pageable)
            .map(listTeeManufacturerMapper::toDto);
    }


    /**
     * Get one listTeeManufacturer by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ListTeeManufacturerDTO> findOne(Long id) {
        log.debug("Request to get ListTeeManufacturer : {}", id);
        return listTeeManufacturerRepository.findById(id)
            .map(listTeeManufacturerMapper::toDto);
    }

    /**
     * Delete the listTeeManufacturer by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ListTeeManufacturer : {}", id);
        listTeeManufacturerRepository.deleteById(id);
    }
}
