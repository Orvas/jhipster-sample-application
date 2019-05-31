package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.ListClcLvl;
import io.github.jhipster.application.repository.ListClcLvlRepository;
import io.github.jhipster.application.service.dto.ListClcLvlDTO;
import io.github.jhipster.application.service.mapper.ListClcLvlMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ListClcLvl}.
 */
@Service
@Transactional
public class ListClcLvlService {

    private final Logger log = LoggerFactory.getLogger(ListClcLvlService.class);

    private final ListClcLvlRepository listClcLvlRepository;

    private final ListClcLvlMapper listClcLvlMapper;

    public ListClcLvlService(ListClcLvlRepository listClcLvlRepository, ListClcLvlMapper listClcLvlMapper) {
        this.listClcLvlRepository = listClcLvlRepository;
        this.listClcLvlMapper = listClcLvlMapper;
    }

    /**
     * Save a listClcLvl.
     *
     * @param listClcLvlDTO the entity to save.
     * @return the persisted entity.
     */
    public ListClcLvlDTO save(ListClcLvlDTO listClcLvlDTO) {
        log.debug("Request to save ListClcLvl : {}", listClcLvlDTO);
        ListClcLvl listClcLvl = listClcLvlMapper.toEntity(listClcLvlDTO);
        listClcLvl = listClcLvlRepository.save(listClcLvl);
        return listClcLvlMapper.toDto(listClcLvl);
    }

    /**
     * Get all the listClcLvls.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ListClcLvlDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ListClcLvls");
        return listClcLvlRepository.findAll(pageable)
            .map(listClcLvlMapper::toDto);
    }


    /**
     * Get one listClcLvl by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ListClcLvlDTO> findOne(Long id) {
        log.debug("Request to get ListClcLvl : {}", id);
        return listClcLvlRepository.findById(id)
            .map(listClcLvlMapper::toDto);
    }

    /**
     * Delete the listClcLvl by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ListClcLvl : {}", id);
        listClcLvlRepository.deleteById(id);
    }
}
