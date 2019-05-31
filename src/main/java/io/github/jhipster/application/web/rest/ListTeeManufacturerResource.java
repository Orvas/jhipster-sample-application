package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.ListTeeManufacturerService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.ListTeeManufacturerDTO;
import io.github.jhipster.application.service.dto.ListTeeManufacturerCriteria;
import io.github.jhipster.application.service.ListTeeManufacturerQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.ListTeeManufacturer}.
 */
@RestController
@RequestMapping("/api")
public class ListTeeManufacturerResource {

    private final Logger log = LoggerFactory.getLogger(ListTeeManufacturerResource.class);

    private static final String ENTITY_NAME = "listTeeManufacturer";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ListTeeManufacturerService listTeeManufacturerService;

    private final ListTeeManufacturerQueryService listTeeManufacturerQueryService;

    public ListTeeManufacturerResource(ListTeeManufacturerService listTeeManufacturerService, ListTeeManufacturerQueryService listTeeManufacturerQueryService) {
        this.listTeeManufacturerService = listTeeManufacturerService;
        this.listTeeManufacturerQueryService = listTeeManufacturerQueryService;
    }

    /**
     * {@code POST  /list-tee-manufacturers} : Create a new listTeeManufacturer.
     *
     * @param listTeeManufacturerDTO the listTeeManufacturerDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new listTeeManufacturerDTO, or with status {@code 400 (Bad Request)} if the listTeeManufacturer has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/list-tee-manufacturers")
    public ResponseEntity<ListTeeManufacturerDTO> createListTeeManufacturer(@Valid @RequestBody ListTeeManufacturerDTO listTeeManufacturerDTO) throws URISyntaxException {
        log.debug("REST request to save ListTeeManufacturer : {}", listTeeManufacturerDTO);
        if (listTeeManufacturerDTO.getId() != null) {
            throw new BadRequestAlertException("A new listTeeManufacturer cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ListTeeManufacturerDTO result = listTeeManufacturerService.save(listTeeManufacturerDTO);
        return ResponseEntity.created(new URI("/api/list-tee-manufacturers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /list-tee-manufacturers} : Updates an existing listTeeManufacturer.
     *
     * @param listTeeManufacturerDTO the listTeeManufacturerDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated listTeeManufacturerDTO,
     * or with status {@code 400 (Bad Request)} if the listTeeManufacturerDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the listTeeManufacturerDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/list-tee-manufacturers")
    public ResponseEntity<ListTeeManufacturerDTO> updateListTeeManufacturer(@Valid @RequestBody ListTeeManufacturerDTO listTeeManufacturerDTO) throws URISyntaxException {
        log.debug("REST request to update ListTeeManufacturer : {}", listTeeManufacturerDTO);
        if (listTeeManufacturerDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ListTeeManufacturerDTO result = listTeeManufacturerService.save(listTeeManufacturerDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, listTeeManufacturerDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /list-tee-manufacturers} : get all the listTeeManufacturers.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of listTeeManufacturers in body.
     */
    @GetMapping("/list-tee-manufacturers")
    public ResponseEntity<List<ListTeeManufacturerDTO>> getAllListTeeManufacturers(ListTeeManufacturerCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get ListTeeManufacturers by criteria: {}", criteria);
        Page<ListTeeManufacturerDTO> page = listTeeManufacturerQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /list-tee-manufacturers/count} : count all the listTeeManufacturers.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/list-tee-manufacturers/count")
    public ResponseEntity<Long> countListTeeManufacturers(ListTeeManufacturerCriteria criteria) {
        log.debug("REST request to count ListTeeManufacturers by criteria: {}", criteria);
        return ResponseEntity.ok().body(listTeeManufacturerQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /list-tee-manufacturers/:id} : get the "id" listTeeManufacturer.
     *
     * @param id the id of the listTeeManufacturerDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the listTeeManufacturerDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/list-tee-manufacturers/{id}")
    public ResponseEntity<ListTeeManufacturerDTO> getListTeeManufacturer(@PathVariable Long id) {
        log.debug("REST request to get ListTeeManufacturer : {}", id);
        Optional<ListTeeManufacturerDTO> listTeeManufacturerDTO = listTeeManufacturerService.findOne(id);
        return ResponseUtil.wrapOrNotFound(listTeeManufacturerDTO);
    }

    /**
     * {@code DELETE  /list-tee-manufacturers/:id} : delete the "id" listTeeManufacturer.
     *
     * @param id the id of the listTeeManufacturerDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/list-tee-manufacturers/{id}")
    public ResponseEntity<Void> deleteListTeeManufacturer(@PathVariable Long id) {
        log.debug("REST request to delete ListTeeManufacturer : {}", id);
        listTeeManufacturerService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
