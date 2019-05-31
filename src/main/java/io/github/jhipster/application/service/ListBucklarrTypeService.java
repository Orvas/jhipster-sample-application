package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.ListBucklarrType;
import io.github.jhipster.application.repository.ListBucklarrTypeRepository;
import io.github.jhipster.application.service.dto.ListBucklarrTypeDTO;
import io.github.jhipster.application.service.mapper.ListBucklarrTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ListBucklarrType}.
 */
@Service
@Transactional
public class ListBucklarrTypeService {

    private final Logger log = LoggerFactory.getLogger(ListBucklarrTypeService.class);

    private final ListBucklarrTypeRepository listBucklarrTypeRepository;

    private final ListBucklarrTypeMapper listBucklarrTypeMapper;

    public ListBucklarrTypeService(ListBucklarrTypeRepository listBucklarrTypeRepository, ListBucklarrTypeMapper listBucklarrTypeMapper) {
        this.listBucklarrTypeRepository = listBucklarrTypeRepository;
        this.listBucklarrTypeMapper = listBucklarrTypeMapper;
    }

    /**
     * Save a listBucklarrType.
     *
     * @param listBucklarrTypeDTO the entity to save.
     * @return the persisted entity.
     */
    public ListBucklarrTypeDTO save(ListBucklarrTypeDTO listBucklarrTypeDTO) {
        log.debug("Request to save ListBucklarrType : {}", listBucklarrTypeDTO);
        ListBucklarrType listBucklarrType = listBucklarrTypeMapper.toEntity(listBucklarrTypeDTO);
        listBucklarrType = listBucklarrTypeRepository.save(listBucklarrType);
        return listBucklarrTypeMapper.toDto(listBucklarrType);
    }

    /**
     * Get all the listBucklarrTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ListBucklarrTypeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ListBucklarrTypes");
        return listBucklarrTypeRepository.findAll(pageable)
            .map(listBucklarrTypeMapper::toDto);
    }


    /**
     * Get one listBucklarrType by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ListBucklarrTypeDTO> findOne(Long id) {
        log.debug("Request to get ListBucklarrType : {}", id);
        return listBucklarrTypeRepository.findById(id)
            .map(listBucklarrTypeMapper::toDto);
    }

    /**
     * Delete the listBucklarrType by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ListBucklarrType : {}", id);
        listBucklarrTypeRepository.deleteById(id);
    }
}
