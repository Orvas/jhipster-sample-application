package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.ListBucklarrManufacturerService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.ListBucklarrManufacturerDTO;
import io.github.jhipster.application.service.dto.ListBucklarrManufacturerCriteria;
import io.github.jhipster.application.service.ListBucklarrManufacturerQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.ListBucklarrManufacturer}.
 */
@RestController
@RequestMapping("/api")
public class ListBucklarrManufacturerResource {

    private final Logger log = LoggerFactory.getLogger(ListBucklarrManufacturerResource.class);

    private static final String ENTITY_NAME = "listBucklarrManufacturer";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ListBucklarrManufacturerService listBucklarrManufacturerService;

    private final ListBucklarrManufacturerQueryService listBucklarrManufacturerQueryService;

    public ListBucklarrManufacturerResource(ListBucklarrManufacturerService listBucklarrManufacturerService, ListBucklarrManufacturerQueryService listBucklarrManufacturerQueryService) {
        this.listBucklarrManufacturerService = listBucklarrManufacturerService;
        this.listBucklarrManufacturerQueryService = listBucklarrManufacturerQueryService;
    }

    /**
     * {@code POST  /list-bucklarr-manufacturers} : Create a new listBucklarrManufacturer.
     *
     * @param listBucklarrManufacturerDTO the listBucklarrManufacturerDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new listBucklarrManufacturerDTO, or with status {@code 400 (Bad Request)} if the listBucklarrManufacturer has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/list-bucklarr-manufacturers")
    public ResponseEntity<ListBucklarrManufacturerDTO> createListBucklarrManufacturer(@Valid @RequestBody ListBucklarrManufacturerDTO listBucklarrManufacturerDTO) throws URISyntaxException {
        log.debug("REST request to save ListBucklarrManufacturer : {}", listBucklarrManufacturerDTO);
        if (listBucklarrManufacturerDTO.getId() != null) {
            throw new BadRequestAlertException("A new listBucklarrManufacturer cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ListBucklarrManufacturerDTO result = listBucklarrManufacturerService.save(listBucklarrManufacturerDTO);
        return ResponseEntity.created(new URI("/api/list-bucklarr-manufacturers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /list-bucklarr-manufacturers} : Updates an existing listBucklarrManufacturer.
     *
     * @param listBucklarrManufacturerDTO the listBucklarrManufacturerDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated listBucklarrManufacturerDTO,
     * or with status {@code 400 (Bad Request)} if the listBucklarrManufacturerDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the listBucklarrManufacturerDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/list-bucklarr-manufacturers")
    public ResponseEntity<ListBucklarrManufacturerDTO> updateListBucklarrManufacturer(@Valid @RequestBody ListBucklarrManufacturerDTO listBucklarrManufacturerDTO) throws URISyntaxException {
        log.debug("REST request to update ListBucklarrManufacturer : {}", listBucklarrManufacturerDTO);
        if (listBucklarrManufacturerDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ListBucklarrManufacturerDTO result = listBucklarrManufacturerService.save(listBucklarrManufacturerDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, listBucklarrManufacturerDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /list-bucklarr-manufacturers} : get all the listBucklarrManufacturers.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of listBucklarrManufacturers in body.
     */
    @GetMapping("/list-bucklarr-manufacturers")
    public ResponseEntity<List<ListBucklarrManufacturerDTO>> getAllListBucklarrManufacturers(ListBucklarrManufacturerCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get ListBucklarrManufacturers by criteria: {}", criteria);
        Page<ListBucklarrManufacturerDTO> page = listBucklarrManufacturerQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /list-bucklarr-manufacturers/count} : count all the listBucklarrManufacturers.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/list-bucklarr-manufacturers/count")
    public ResponseEntity<Long> countListBucklarrManufacturers(ListBucklarrManufacturerCriteria criteria) {
        log.debug("REST request to count ListBucklarrManufacturers by criteria: {}", criteria);
        return ResponseEntity.ok().body(listBucklarrManufacturerQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /list-bucklarr-manufacturers/:id} : get the "id" listBucklarrManufacturer.
     *
     * @param id the id of the listBucklarrManufacturerDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the listBucklarrManufacturerDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/list-bucklarr-manufacturers/{id}")
    public ResponseEntity<ListBucklarrManufacturerDTO> getListBucklarrManufacturer(@PathVariable Long id) {
        log.debug("REST request to get ListBucklarrManufacturer : {}", id);
        Optional<ListBucklarrManufacturerDTO> listBucklarrManufacturerDTO = listBucklarrManufacturerService.findOne(id);
        return ResponseUtil.wrapOrNotFound(listBucklarrManufacturerDTO);
    }

    /**
     * {@code DELETE  /list-bucklarr-manufacturers/:id} : delete the "id" listBucklarrManufacturer.
     *
     * @param id the id of the listBucklarrManufacturerDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/list-bucklarr-manufacturers/{id}")
    public ResponseEntity<Void> deleteListBucklarrManufacturer(@PathVariable Long id) {
        log.debug("REST request to delete ListBucklarrManufacturer : {}", id);
        listBucklarrManufacturerService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
