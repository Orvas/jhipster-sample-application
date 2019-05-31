package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.ListValveTypeService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.ListValveTypeDTO;
import io.github.jhipster.application.service.dto.ListValveTypeCriteria;
import io.github.jhipster.application.service.ListValveTypeQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.ListValveType}.
 */
@RestController
@RequestMapping("/api")
public class ListValveTypeResource {

    private final Logger log = LoggerFactory.getLogger(ListValveTypeResource.class);

    private static final String ENTITY_NAME = "listValveType";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ListValveTypeService listValveTypeService;

    private final ListValveTypeQueryService listValveTypeQueryService;

    public ListValveTypeResource(ListValveTypeService listValveTypeService, ListValveTypeQueryService listValveTypeQueryService) {
        this.listValveTypeService = listValveTypeService;
        this.listValveTypeQueryService = listValveTypeQueryService;
    }

    /**
     * {@code POST  /list-valve-types} : Create a new listValveType.
     *
     * @param listValveTypeDTO the listValveTypeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new listValveTypeDTO, or with status {@code 400 (Bad Request)} if the listValveType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/list-valve-types")
    public ResponseEntity<ListValveTypeDTO> createListValveType(@Valid @RequestBody ListValveTypeDTO listValveTypeDTO) throws URISyntaxException {
        log.debug("REST request to save ListValveType : {}", listValveTypeDTO);
        if (listValveTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new listValveType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ListValveTypeDTO result = listValveTypeService.save(listValveTypeDTO);
        return ResponseEntity.created(new URI("/api/list-valve-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /list-valve-types} : Updates an existing listValveType.
     *
     * @param listValveTypeDTO the listValveTypeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated listValveTypeDTO,
     * or with status {@code 400 (Bad Request)} if the listValveTypeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the listValveTypeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/list-valve-types")
    public ResponseEntity<ListValveTypeDTO> updateListValveType(@Valid @RequestBody ListValveTypeDTO listValveTypeDTO) throws URISyntaxException {
        log.debug("REST request to update ListValveType : {}", listValveTypeDTO);
        if (listValveTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ListValveTypeDTO result = listValveTypeService.save(listValveTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, listValveTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /list-valve-types} : get all the listValveTypes.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of listValveTypes in body.
     */
    @GetMapping("/list-valve-types")
    public ResponseEntity<List<ListValveTypeDTO>> getAllListValveTypes(ListValveTypeCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get ListValveTypes by criteria: {}", criteria);
        Page<ListValveTypeDTO> page = listValveTypeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /list-valve-types/count} : count all the listValveTypes.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/list-valve-types/count")
    public ResponseEntity<Long> countListValveTypes(ListValveTypeCriteria criteria) {
        log.debug("REST request to count ListValveTypes by criteria: {}", criteria);
        return ResponseEntity.ok().body(listValveTypeQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /list-valve-types/:id} : get the "id" listValveType.
     *
     * @param id the id of the listValveTypeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the listValveTypeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/list-valve-types/{id}")
    public ResponseEntity<ListValveTypeDTO> getListValveType(@PathVariable Long id) {
        log.debug("REST request to get ListValveType : {}", id);
        Optional<ListValveTypeDTO> listValveTypeDTO = listValveTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(listValveTypeDTO);
    }

    /**
     * {@code DELETE  /list-valve-types/:id} : delete the "id" listValveType.
     *
     * @param id the id of the listValveTypeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/list-valve-types/{id}")
    public ResponseEntity<Void> deleteListValveType(@PathVariable Long id) {
        log.debug("REST request to delete ListValveType : {}", id);
        listValveTypeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
