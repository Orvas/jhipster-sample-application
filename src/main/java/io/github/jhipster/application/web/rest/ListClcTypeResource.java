package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.ListClcTypeService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.ListClcTypeDTO;
import io.github.jhipster.application.service.dto.ListClcTypeCriteria;
import io.github.jhipster.application.service.ListClcTypeQueryService;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link io.github.jhipster.application.domain.ListClcType}.
 */
@RestController
@RequestMapping("/api")
public class ListClcTypeResource {

    private final Logger log = LoggerFactory.getLogger(ListClcTypeResource.class);

    private static final String ENTITY_NAME = "listClcType";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ListClcTypeService listClcTypeService;

    private final ListClcTypeQueryService listClcTypeQueryService;

    public ListClcTypeResource(ListClcTypeService listClcTypeService, ListClcTypeQueryService listClcTypeQueryService) {
        this.listClcTypeService = listClcTypeService;
        this.listClcTypeQueryService = listClcTypeQueryService;
    }

    /**
     * {@code POST  /list-clc-types} : Create a new listClcType.
     *
     * @param listClcTypeDTO the listClcTypeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new listClcTypeDTO, or with status {@code 400 (Bad Request)} if the listClcType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/list-clc-types")
    public ResponseEntity<ListClcTypeDTO> createListClcType(@Valid @RequestBody ListClcTypeDTO listClcTypeDTO) throws URISyntaxException {
        log.debug("REST request to save ListClcType : {}", listClcTypeDTO);
        if (listClcTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new listClcType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ListClcTypeDTO result = listClcTypeService.save(listClcTypeDTO);
        return ResponseEntity.created(new URI("/api/list-clc-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /list-clc-types} : Updates an existing listClcType.
     *
     * @param listClcTypeDTO the listClcTypeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated listClcTypeDTO,
     * or with status {@code 400 (Bad Request)} if the listClcTypeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the listClcTypeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/list-clc-types")
    public ResponseEntity<ListClcTypeDTO> updateListClcType(@Valid @RequestBody ListClcTypeDTO listClcTypeDTO) throws URISyntaxException {
        log.debug("REST request to update ListClcType : {}", listClcTypeDTO);
        if (listClcTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ListClcTypeDTO result = listClcTypeService.save(listClcTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, listClcTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /list-clc-types} : get all the listClcTypes.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of listClcTypes in body.
     */
    @GetMapping("/list-clc-types")
    public ResponseEntity<List<ListClcTypeDTO>> getAllListClcTypes(ListClcTypeCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get ListClcTypes by criteria: {}", criteria);
        Page<ListClcTypeDTO> page = listClcTypeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /list-clc-types/count} : count all the listClcTypes.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/list-clc-types/count")
    public ResponseEntity<Long> countListClcTypes(ListClcTypeCriteria criteria) {
        log.debug("REST request to count ListClcTypes by criteria: {}", criteria);
        return ResponseEntity.ok().body(listClcTypeQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /list-clc-types/:id} : get the "id" listClcType.
     *
     * @param id the id of the listClcTypeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the listClcTypeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/list-clc-types/{id}")
    public ResponseEntity<ListClcTypeDTO> getListClcType(@PathVariable Long id) {
        log.debug("REST request to get ListClcType : {}", id);
        Optional<ListClcTypeDTO> listClcTypeDTO = listClcTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(listClcTypeDTO);
    }

    /**
     * {@code DELETE  /list-clc-types/:id} : delete the "id" listClcType.
     *
     * @param id the id of the listClcTypeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/list-clc-types/{id}")
    public ResponseEntity<Void> deleteListClcType(@PathVariable Long id) {
        log.debug("REST request to delete ListClcType : {}", id);
        listClcTypeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
