package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.ListDfctTypeService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.ListDfctTypeDTO;
import io.github.jhipster.application.service.dto.ListDfctTypeCriteria;
import io.github.jhipster.application.service.ListDfctTypeQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.ListDfctType}.
 */
@RestController
@RequestMapping("/api")
public class ListDfctTypeResource {

    private final Logger log = LoggerFactory.getLogger(ListDfctTypeResource.class);

    private static final String ENTITY_NAME = "listDfctType";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ListDfctTypeService listDfctTypeService;

    private final ListDfctTypeQueryService listDfctTypeQueryService;

    public ListDfctTypeResource(ListDfctTypeService listDfctTypeService, ListDfctTypeQueryService listDfctTypeQueryService) {
        this.listDfctTypeService = listDfctTypeService;
        this.listDfctTypeQueryService = listDfctTypeQueryService;
    }

    /**
     * {@code POST  /list-dfct-types} : Create a new listDfctType.
     *
     * @param listDfctTypeDTO the listDfctTypeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new listDfctTypeDTO, or with status {@code 400 (Bad Request)} if the listDfctType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/list-dfct-types")
    public ResponseEntity<ListDfctTypeDTO> createListDfctType(@Valid @RequestBody ListDfctTypeDTO listDfctTypeDTO) throws URISyntaxException {
        log.debug("REST request to save ListDfctType : {}", listDfctTypeDTO);
        if (listDfctTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new listDfctType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ListDfctTypeDTO result = listDfctTypeService.save(listDfctTypeDTO);
        return ResponseEntity.created(new URI("/api/list-dfct-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /list-dfct-types} : Updates an existing listDfctType.
     *
     * @param listDfctTypeDTO the listDfctTypeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated listDfctTypeDTO,
     * or with status {@code 400 (Bad Request)} if the listDfctTypeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the listDfctTypeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/list-dfct-types")
    public ResponseEntity<ListDfctTypeDTO> updateListDfctType(@Valid @RequestBody ListDfctTypeDTO listDfctTypeDTO) throws URISyntaxException {
        log.debug("REST request to update ListDfctType : {}", listDfctTypeDTO);
        if (listDfctTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ListDfctTypeDTO result = listDfctTypeService.save(listDfctTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, listDfctTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /list-dfct-types} : get all the listDfctTypes.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of listDfctTypes in body.
     */
    @GetMapping("/list-dfct-types")
    public ResponseEntity<List<ListDfctTypeDTO>> getAllListDfctTypes(ListDfctTypeCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get ListDfctTypes by criteria: {}", criteria);
        Page<ListDfctTypeDTO> page = listDfctTypeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /list-dfct-types/count} : count all the listDfctTypes.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/list-dfct-types/count")
    public ResponseEntity<Long> countListDfctTypes(ListDfctTypeCriteria criteria) {
        log.debug("REST request to count ListDfctTypes by criteria: {}", criteria);
        return ResponseEntity.ok().body(listDfctTypeQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /list-dfct-types/:id} : get the "id" listDfctType.
     *
     * @param id the id of the listDfctTypeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the listDfctTypeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/list-dfct-types/{id}")
    public ResponseEntity<ListDfctTypeDTO> getListDfctType(@PathVariable Long id) {
        log.debug("REST request to get ListDfctType : {}", id);
        Optional<ListDfctTypeDTO> listDfctTypeDTO = listDfctTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(listDfctTypeDTO);
    }

    /**
     * {@code DELETE  /list-dfct-types/:id} : delete the "id" listDfctType.
     *
     * @param id the id of the listDfctTypeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/list-dfct-types/{id}")
    public ResponseEntity<Void> deleteListDfctType(@PathVariable Long id) {
        log.debug("REST request to delete ListDfctType : {}", id);
        listDfctTypeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
