package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.ListBendManufacturerService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.ListBendManufacturerDTO;
import io.github.jhipster.application.service.dto.ListBendManufacturerCriteria;
import io.github.jhipster.application.service.ListBendManufacturerQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.ListBendManufacturer}.
 */
@RestController
@RequestMapping("/api")
public class ListBendManufacturerResource {

    private final Logger log = LoggerFactory.getLogger(ListBendManufacturerResource.class);

    private static final String ENTITY_NAME = "listBendManufacturer";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ListBendManufacturerService listBendManufacturerService;

    private final ListBendManufacturerQueryService listBendManufacturerQueryService;

    public ListBendManufacturerResource(ListBendManufacturerService listBendManufacturerService, ListBendManufacturerQueryService listBendManufacturerQueryService) {
        this.listBendManufacturerService = listBendManufacturerService;
        this.listBendManufacturerQueryService = listBendManufacturerQueryService;
    }

    /**
     * {@code POST  /list-bend-manufacturers} : Create a new listBendManufacturer.
     *
     * @param listBendManufacturerDTO the listBendManufacturerDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new listBendManufacturerDTO, or with status {@code 400 (Bad Request)} if the listBendManufacturer has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/list-bend-manufacturers")
    public ResponseEntity<ListBendManufacturerDTO> createListBendManufacturer(@Valid @RequestBody ListBendManufacturerDTO listBendManufacturerDTO) throws URISyntaxException {
        log.debug("REST request to save ListBendManufacturer : {}", listBendManufacturerDTO);
        if (listBendManufacturerDTO.getId() != null) {
            throw new BadRequestAlertException("A new listBendManufacturer cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ListBendManufacturerDTO result = listBendManufacturerService.save(listBendManufacturerDTO);
        return ResponseEntity.created(new URI("/api/list-bend-manufacturers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /list-bend-manufacturers} : Updates an existing listBendManufacturer.
     *
     * @param listBendManufacturerDTO the listBendManufacturerDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated listBendManufacturerDTO,
     * or with status {@code 400 (Bad Request)} if the listBendManufacturerDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the listBendManufacturerDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/list-bend-manufacturers")
    public ResponseEntity<ListBendManufacturerDTO> updateListBendManufacturer(@Valid @RequestBody ListBendManufacturerDTO listBendManufacturerDTO) throws URISyntaxException {
        log.debug("REST request to update ListBendManufacturer : {}", listBendManufacturerDTO);
        if (listBendManufacturerDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ListBendManufacturerDTO result = listBendManufacturerService.save(listBendManufacturerDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, listBendManufacturerDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /list-bend-manufacturers} : get all the listBendManufacturers.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of listBendManufacturers in body.
     */
    @GetMapping("/list-bend-manufacturers")
    public ResponseEntity<List<ListBendManufacturerDTO>> getAllListBendManufacturers(ListBendManufacturerCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get ListBendManufacturers by criteria: {}", criteria);
        Page<ListBendManufacturerDTO> page = listBendManufacturerQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /list-bend-manufacturers/count} : count all the listBendManufacturers.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/list-bend-manufacturers/count")
    public ResponseEntity<Long> countListBendManufacturers(ListBendManufacturerCriteria criteria) {
        log.debug("REST request to count ListBendManufacturers by criteria: {}", criteria);
        return ResponseEntity.ok().body(listBendManufacturerQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /list-bend-manufacturers/:id} : get the "id" listBendManufacturer.
     *
     * @param id the id of the listBendManufacturerDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the listBendManufacturerDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/list-bend-manufacturers/{id}")
    public ResponseEntity<ListBendManufacturerDTO> getListBendManufacturer(@PathVariable Long id) {
        log.debug("REST request to get ListBendManufacturer : {}", id);
        Optional<ListBendManufacturerDTO> listBendManufacturerDTO = listBendManufacturerService.findOne(id);
        return ResponseUtil.wrapOrNotFound(listBendManufacturerDTO);
    }

    /**
     * {@code DELETE  /list-bend-manufacturers/:id} : delete the "id" listBendManufacturer.
     *
     * @param id the id of the listBendManufacturerDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/list-bend-manufacturers/{id}")
    public ResponseEntity<Void> deleteListBendManufacturer(@PathVariable Long id) {
        log.debug("REST request to delete ListBendManufacturer : {}", id);
        listBendManufacturerService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
