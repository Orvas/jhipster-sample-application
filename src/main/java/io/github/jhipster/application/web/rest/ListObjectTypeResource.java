package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.ListObjectTypeService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.ListObjectTypeDTO;
import io.github.jhipster.application.service.dto.ListObjectTypeCriteria;
import io.github.jhipster.application.service.ListObjectTypeQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.ListObjectType}.
 */
@RestController
@RequestMapping("/api")
public class ListObjectTypeResource {

    private final Logger log = LoggerFactory.getLogger(ListObjectTypeResource.class);

    private static final String ENTITY_NAME = "listObjectType";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ListObjectTypeService listObjectTypeService;

    private final ListObjectTypeQueryService listObjectTypeQueryService;

    public ListObjectTypeResource(ListObjectTypeService listObjectTypeService, ListObjectTypeQueryService listObjectTypeQueryService) {
        this.listObjectTypeService = listObjectTypeService;
        this.listObjectTypeQueryService = listObjectTypeQueryService;
    }

    /**
     * {@code POST  /list-object-types} : Create a new listObjectType.
     *
     * @param listObjectTypeDTO the listObjectTypeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new listObjectTypeDTO, or with status {@code 400 (Bad Request)} if the listObjectType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/list-object-types")
    public ResponseEntity<ListObjectTypeDTO> createListObjectType(@Valid @RequestBody ListObjectTypeDTO listObjectTypeDTO) throws URISyntaxException {
        log.debug("REST request to save ListObjectType : {}", listObjectTypeDTO);
        if (listObjectTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new listObjectType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ListObjectTypeDTO result = listObjectTypeService.save(listObjectTypeDTO);
        return ResponseEntity.created(new URI("/api/list-object-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /list-object-types} : Updates an existing listObjectType.
     *
     * @param listObjectTypeDTO the listObjectTypeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated listObjectTypeDTO,
     * or with status {@code 400 (Bad Request)} if the listObjectTypeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the listObjectTypeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/list-object-types")
    public ResponseEntity<ListObjectTypeDTO> updateListObjectType(@Valid @RequestBody ListObjectTypeDTO listObjectTypeDTO) throws URISyntaxException {
        log.debug("REST request to update ListObjectType : {}", listObjectTypeDTO);
        if (listObjectTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ListObjectTypeDTO result = listObjectTypeService.save(listObjectTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, listObjectTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /list-object-types} : get all the listObjectTypes.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of listObjectTypes in body.
     */
    @GetMapping("/list-object-types")
    public ResponseEntity<List<ListObjectTypeDTO>> getAllListObjectTypes(ListObjectTypeCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get ListObjectTypes by criteria: {}", criteria);
        Page<ListObjectTypeDTO> page = listObjectTypeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /list-object-types/count} : count all the listObjectTypes.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/list-object-types/count")
    public ResponseEntity<Long> countListObjectTypes(ListObjectTypeCriteria criteria) {
        log.debug("REST request to count ListObjectTypes by criteria: {}", criteria);
        return ResponseEntity.ok().body(listObjectTypeQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /list-object-types/:id} : get the "id" listObjectType.
     *
     * @param id the id of the listObjectTypeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the listObjectTypeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/list-object-types/{id}")
    public ResponseEntity<ListObjectTypeDTO> getListObjectType(@PathVariable Long id) {
        log.debug("REST request to get ListObjectType : {}", id);
        Optional<ListObjectTypeDTO> listObjectTypeDTO = listObjectTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(listObjectTypeDTO);
    }

    /**
     * {@code DELETE  /list-object-types/:id} : delete the "id" listObjectType.
     *
     * @param id the id of the listObjectTypeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/list-object-types/{id}")
    public ResponseEntity<Void> deleteListObjectType(@PathVariable Long id) {
        log.debug("REST request to delete ListObjectType : {}", id);
        listObjectTypeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
