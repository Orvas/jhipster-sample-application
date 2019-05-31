package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.ListBendType;
import io.github.jhipster.application.repository.ListBendTypeRepository;
import io.github.jhipster.application.service.dto.ListBendTypeDTO;
import io.github.jhipster.application.service.mapper.ListBendTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ListBendType}.
 */
@Service
@Transactional
public class ListBendTypeService {

    private final Logger log = LoggerFactory.getLogger(ListBendTypeService.class);

    private final ListBendTypeRepository listBendTypeRepository;

    private final ListBendTypeMapper listBendTypeMapper;

    public ListBendTypeService(ListBendTypeRepository listBendTypeRepository, ListBendTypeMapper listBendTypeMapper) {
        this.listBendTypeRepository = listBendTypeRepository;
        this.listBendTypeMapper = listBendTypeMapper;
    }

    /**
     * Save a listBendType.
     *
     * @param listBendTypeDTO the entity to save.
     * @return the persisted entity.
     */
    public ListBendTypeDTO save(ListBendTypeDTO listBendTypeDTO) {
        log.debug("Request to save ListBendType : {}", listBendTypeDTO);
        ListBendType listBendType = listBendTypeMapper.toEntity(listBendTypeDTO);
        listBendType = listBendTypeRepository.save(listBendType);
        return listBendTypeMapper.toDto(listBendType);
    }

    /**
     * Get all the listBendTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ListBendTypeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ListBendTypes");
        return listBendTypeRepository.findAll(pageable)
            .map(listBendTypeMapper::toDto);
    }


    /**
     * Get one listBendType by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ListBendTypeDTO> findOne(Long id) {
        log.debug("Request to get ListBendType : {}", id);
        return listBendTypeRepository.findById(id)
            .map(listBendTypeMapper::toDto);
    }

    /**
     * Delete the listBendType by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ListBendType : {}", id);
        listBendTypeRepository.deleteById(id);
    }
}
