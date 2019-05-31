package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.ListBucklarrTypeService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.ListBucklarrTypeDTO;
import io.github.jhipster.application.service.dto.ListBucklarrTypeCriteria;
import io.github.jhipster.application.service.ListBucklarrTypeQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.ListBucklarrType}.
 */
@RestController
@RequestMapping("/api")
public class ListBucklarrTypeResource {

    private final Logger log = LoggerFactory.getLogger(ListBucklarrTypeResource.class);

    private static final String ENTITY_NAME = "listBucklarrType";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ListBucklarrTypeService listBucklarrTypeService;

    private final ListBucklarrTypeQueryService listBucklarrTypeQueryService;

    public ListBucklarrTypeResource(ListBucklarrTypeService listBucklarrTypeService, ListBucklarrTypeQueryService listBucklarrTypeQueryService) {
        this.listBucklarrTypeService = listBucklarrTypeService;
        this.listBucklarrTypeQueryService = listBucklarrTypeQueryService;
    }

    /**
     * {@code POST  /list-bucklarr-types} : Create a new listBucklarrType.
     *
     * @param listBucklarrTypeDTO the listBucklarrTypeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new listBucklarrTypeDTO, or with status {@code 400 (Bad Request)} if the listBucklarrType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/list-bucklarr-types")
    public ResponseEntity<ListBucklarrTypeDTO> createListBucklarrType(@Valid @RequestBody ListBucklarrTypeDTO listBucklarrTypeDTO) throws URISyntaxException {
        log.debug("REST request to save ListBucklarrType : {}", listBucklarrTypeDTO);
        if (listBucklarrTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new listBucklarrType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ListBucklarrTypeDTO result = listBucklarrTypeService.save(listBucklarrTypeDTO);
        return ResponseEntity.created(new URI("/api/list-bucklarr-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /list-bucklarr-types} : Updates an existing listBucklarrType.
     *
     * @param listBucklarrTypeDTO the listBucklarrTypeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated listBucklarrTypeDTO,
     * or with status {@code 400 (Bad Request)} if the listBucklarrTypeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the listBucklarrTypeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/list-bucklarr-types")
    public ResponseEntity<ListBucklarrTypeDTO> updateListBucklarrType(@Valid @RequestBody ListBucklarrTypeDTO listBucklarrTypeDTO) throws URISyntaxException {
        log.debug("REST request to update ListBucklarrType : {}", listBucklarrTypeDTO);
        if (listBucklarrTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ListBucklarrTypeDTO result = listBucklarrTypeService.save(listBucklarrTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, listBucklarrTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /list-bucklarr-types} : get all the listBucklarrTypes.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of listBucklarrTypes in body.
     */
    @GetMapping("/list-bucklarr-types")
    public ResponseEntity<List<ListBucklarrTypeDTO>> getAllListBucklarrTypes(ListBucklarrTypeCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get ListBucklarrTypes by criteria: {}", criteria);
        Page<ListBucklarrTypeDTO> page = listBucklarrTypeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /list-bucklarr-types/count} : count all the listBucklarrTypes.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/list-bucklarr-types/count")
    public ResponseEntity<Long> countListBucklarrTypes(ListBucklarrTypeCriteria criteria) {
        log.debug("REST request to count ListBucklarrTypes by criteria: {}", criteria);
        return ResponseEntity.ok().body(listBucklarrTypeQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /list-bucklarr-types/:id} : get the "id" listBucklarrType.
     *
     * @param id the id of the listBucklarrTypeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the listBucklarrTypeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/list-bucklarr-types/{id}")
    public ResponseEntity<ListBucklarrTypeDTO> getListBucklarrType(@PathVariable Long id) {
        log.debug("REST request to get ListBucklarrType : {}", id);
        Optional<ListBucklarrTypeDTO> listBucklarrTypeDTO = listBucklarrTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(listBucklarrTypeDTO);
    }

    /**
     * {@code DELETE  /list-bucklarr-types/:id} : delete the "id" listBucklarrType.
     *
     * @param id the id of the listBucklarrTypeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/list-bucklarr-types/{id}")
    public ResponseEntity<Void> deleteListBucklarrType(@PathVariable Long id) {
        log.debug("REST request to delete ListBucklarrType : {}", id);
        listBucklarrTypeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
