package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.ListIliPigType;
import io.github.jhipster.application.repository.ListIliPigTypeRepository;
import io.github.jhipster.application.service.dto.ListIliPigTypeDTO;
import io.github.jhipster.application.service.mapper.ListIliPigTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ListIliPigType}.
 */
@Service
@Transactional
public class ListIliPigTypeService {

    private final Logger log = LoggerFactory.getLogger(ListIliPigTypeService.class);

    private final ListIliPigTypeRepository listIliPigTypeRepository;

    private final ListIliPigTypeMapper listIliPigTypeMapper;

    public ListIliPigTypeService(ListIliPigTypeRepository listIliPigTypeRepository, ListIliPigTypeMapper listIliPigTypeMapper) {
        this.listIliPigTypeRepository = listIliPigTypeRepository;
        this.listIliPigTypeMapper = listIliPigTypeMapper;
    }

    /**
     * Save a listIliPigType.
     *
     * @param listIliPigTypeDTO the entity to save.
     * @return the persisted entity.
     */
    public ListIliPigTypeDTO save(ListIliPigTypeDTO listIliPigTypeDTO) {
        log.debug("Request to save ListIliPigType : {}", listIliPigTypeDTO);
        ListIliPigType listIliPigType = listIliPigTypeMapper.toEntity(listIliPigTypeDTO);
        listIliPigType = listIliPigTypeRepository.save(listIliPigType);
        return listIliPigTypeMapper.toDto(listIliPigType);
    }

    /**
     * Get all the listIliPigTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ListIliPigTypeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ListIliPigTypes");
        return listIliPigTypeRepository.findAll(pageable)
            .map(listIliPigTypeMapper::toDto);
    }


    /**
     * Get one listIliPigType by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ListIliPigTypeDTO> findOne(Long id) {
        log.debug("Request to get ListIliPigType : {}", id);
        return listIliPigTypeRepository.findById(id)
            .map(listIliPigTypeMapper::toDto);
    }

    /**
     * Delete the listIliPigType by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ListIliPigType : {}", id);
        listIliPigTypeRepository.deleteById(id);
    }
}
