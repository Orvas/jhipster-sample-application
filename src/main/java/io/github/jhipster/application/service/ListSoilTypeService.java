package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.ListSoilType;
import io.github.jhipster.application.repository.ListSoilTypeRepository;
import io.github.jhipster.application.service.dto.ListSoilTypeDTO;
import io.github.jhipster.application.service.mapper.ListSoilTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ListSoilType}.
 */
@Service
@Transactional
public class ListSoilTypeService {

    private final Logger log = LoggerFactory.getLogger(ListSoilTypeService.class);

    private final ListSoilTypeRepository listSoilTypeRepository;

    private final ListSoilTypeMapper listSoilTypeMapper;

    public ListSoilTypeService(ListSoilTypeRepository listSoilTypeRepository, ListSoilTypeMapper listSoilTypeMapper) {
        this.listSoilTypeRepository = listSoilTypeRepository;
        this.listSoilTypeMapper = listSoilTypeMapper;
    }

    /**
     * Save a listSoilType.
     *
     * @param listSoilTypeDTO the entity to save.
     * @return the persisted entity.
     */
    public ListSoilTypeDTO save(ListSoilTypeDTO listSoilTypeDTO) {
        log.debug("Request to save ListSoilType : {}", listSoilTypeDTO);
        ListSoilType listSoilType = listSoilTypeMapper.toEntity(listSoilTypeDTO);
        listSoilType = listSoilTypeRepository.save(listSoilType);
        return listSoilTypeMapper.toDto(listSoilType);
    }

    /**
     * Get all the listSoilTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ListSoilTypeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ListSoilTypes");
        return listSoilTypeRepository.findAll(pageable)
            .map(listSoilTypeMapper::toDto);
    }


    /**
     * Get one listSoilType by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ListSoilTypeDTO> findOne(Long id) {
        log.debug("Request to get ListSoilType : {}", id);
        return listSoilTypeRepository.findById(id)
            .map(listSoilTypeMapper::toDto);
    }

    /**
     * Delete the listSoilType by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ListSoilType : {}", id);
        listSoilTypeRepository.deleteById(id);
    }
}
