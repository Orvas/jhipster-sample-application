package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.ListClayTypeService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.ListClayTypeDTO;
import io.github.jhipster.application.service.dto.ListClayTypeCriteria;
import io.github.jhipster.application.service.ListClayTypeQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.ListClayType}.
 */
@RestController
@RequestMapping("/api")
public class ListClayTypeResource {

    private final Logger log = LoggerFactory.getLogger(ListClayTypeResource.class);

    private static final String ENTITY_NAME = "listClayType";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ListClayTypeService listClayTypeService;

    private final ListClayTypeQueryService listClayTypeQueryService;

    public ListClayTypeResource(ListClayTypeService listClayTypeService, ListClayTypeQueryService listClayTypeQueryService) {
        this.listClayTypeService = listClayTypeService;
        this.listClayTypeQueryService = listClayTypeQueryService;
    }

    /**
     * {@code POST  /list-clay-types} : Create a new listClayType.
     *
     * @param listClayTypeDTO the listClayTypeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new listClayTypeDTO, or with status {@code 400 (Bad Request)} if the listClayType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/list-clay-types")
    public ResponseEntity<ListClayTypeDTO> createListClayType(@Valid @RequestBody ListClayTypeDTO listClayTypeDTO) throws URISyntaxException {
        log.debug("REST request to save ListClayType : {}", listClayTypeDTO);
        if (listClayTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new listClayType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ListClayTypeDTO result = listClayTypeService.save(listClayTypeDTO);
        return ResponseEntity.created(new URI("/api/list-clay-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /list-clay-types} : Updates an existing listClayType.
     *
     * @param listClayTypeDTO the listClayTypeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated listClayTypeDTO,
     * or with status {@code 400 (Bad Request)} if the listClayTypeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the listClayTypeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/list-clay-types")
    public ResponseEntity<ListClayTypeDTO> updateListClayType(@Valid @RequestBody ListClayTypeDTO listClayTypeDTO) throws URISyntaxException {
        log.debug("REST request to update ListClayType : {}", listClayTypeDTO);
        if (listClayTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ListClayTypeDTO result = listClayTypeService.save(listClayTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, listClayTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /list-clay-types} : get all the listClayTypes.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of listClayTypes in body.
     */
    @GetMapping("/list-clay-types")
    public ResponseEntity<List<ListClayTypeDTO>> getAllListClayTypes(ListClayTypeCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get ListClayTypes by criteria: {}", criteria);
        Page<ListClayTypeDTO> page = listClayTypeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /list-clay-types/count} : count all the listClayTypes.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/list-clay-types/count")
    public ResponseEntity<Long> countListClayTypes(ListClayTypeCriteria criteria) {
        log.debug("REST request to count ListClayTypes by criteria: {}", criteria);
        return ResponseEntity.ok().body(listClayTypeQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /list-clay-types/:id} : get the "id" listClayType.
     *
     * @param id the id of the listClayTypeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the listClayTypeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/list-clay-types/{id}")
    public ResponseEntity<ListClayTypeDTO> getListClayType(@PathVariable Long id) {
        log.debug("REST request to get ListClayType : {}", id);
        Optional<ListClayTypeDTO> listClayTypeDTO = listClayTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(listClayTypeDTO);
    }

    /**
     * {@code DELETE  /list-clay-types/:id} : delete the "id" listClayType.
     *
     * @param id the id of the listClayTypeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/list-clay-types/{id}")
    public ResponseEntity<Void> deleteListClayType(@PathVariable Long id) {
        log.debug("REST request to delete ListClayType : {}", id);
        listClayTypeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
