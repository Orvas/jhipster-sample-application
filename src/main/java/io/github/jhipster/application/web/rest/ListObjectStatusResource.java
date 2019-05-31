package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.ListObjectStatusService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.ListObjectStatusDTO;
import io.github.jhipster.application.service.dto.ListObjectStatusCriteria;
import io.github.jhipster.application.service.ListObjectStatusQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.ListObjectStatus}.
 */
@RestController
@RequestMapping("/api")
public class ListObjectStatusResource {

    private final Logger log = LoggerFactory.getLogger(ListObjectStatusResource.class);

    private static final String ENTITY_NAME = "listObjectStatus";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ListObjectStatusService listObjectStatusService;

    private final ListObjectStatusQueryService listObjectStatusQueryService;

    public ListObjectStatusResource(ListObjectStatusService listObjectStatusService, ListObjectStatusQueryService listObjectStatusQueryService) {
        this.listObjectStatusService = listObjectStatusService;
        this.listObjectStatusQueryService = listObjectStatusQueryService;
    }

    /**
     * {@code POST  /list-object-statuses} : Create a new listObjectStatus.
     *
     * @param listObjectStatusDTO the listObjectStatusDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new listObjectStatusDTO, or with status {@code 400 (Bad Request)} if the listObjectStatus has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/list-object-statuses")
    public ResponseEntity<ListObjectStatusDTO> createListObjectStatus(@Valid @RequestBody ListObjectStatusDTO listObjectStatusDTO) throws URISyntaxException {
        log.debug("REST request to save ListObjectStatus : {}", listObjectStatusDTO);
        if (listObjectStatusDTO.getId() != null) {
            throw new BadRequestAlertException("A new listObjectStatus cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ListObjectStatusDTO result = listObjectStatusService.save(listObjectStatusDTO);
        return ResponseEntity.created(new URI("/api/list-object-statuses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /list-object-statuses} : Updates an existing listObjectStatus.
     *
     * @param listObjectStatusDTO the listObjectStatusDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated listObjectStatusDTO,
     * or with status {@code 400 (Bad Request)} if the listObjectStatusDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the listObjectStatusDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/list-object-statuses")
    public ResponseEntity<ListObjectStatusDTO> updateListObjectStatus(@Valid @RequestBody ListObjectStatusDTO listObjectStatusDTO) throws URISyntaxException {
        log.debug("REST request to update ListObjectStatus : {}", listObjectStatusDTO);
        if (listObjectStatusDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ListObjectStatusDTO result = listObjectStatusService.save(listObjectStatusDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, listObjectStatusDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /list-object-statuses} : get all the listObjectStatuses.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of listObjectStatuses in body.
     */
    @GetMapping("/list-object-statuses")
    public ResponseEntity<List<ListObjectStatusDTO>> getAllListObjectStatuses(ListObjectStatusCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get ListObjectStatuses by criteria: {}", criteria);
        Page<ListObjectStatusDTO> page = listObjectStatusQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /list-object-statuses/count} : count all the listObjectStatuses.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/list-object-statuses/count")
    public ResponseEntity<Long> countListObjectStatuses(ListObjectStatusCriteria criteria) {
        log.debug("REST request to count ListObjectStatuses by criteria: {}", criteria);
        return ResponseEntity.ok().body(listObjectStatusQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /list-object-statuses/:id} : get the "id" listObjectStatus.
     *
     * @param id the id of the listObjectStatusDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the listObjectStatusDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/list-object-statuses/{id}")
    public ResponseEntity<ListObjectStatusDTO> getListObjectStatus(@PathVariable Long id) {
        log.debug("REST request to get ListObjectStatus : {}", id);
        Optional<ListObjectStatusDTO> listObjectStatusDTO = listObjectStatusService.findOne(id);
        return ResponseUtil.wrapOrNotFound(listObjectStatusDTO);
    }

    /**
     * {@code DELETE  /list-object-statuses/:id} : delete the "id" listObjectStatus.
     *
     * @param id the id of the listObjectStatusDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/list-object-statuses/{id}")
    public ResponseEntity<Void> deleteListObjectStatus(@PathVariable Long id) {
        log.debug("REST request to delete ListObjectStatus : {}", id);
        listObjectStatusService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
