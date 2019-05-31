package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.ListWrkcmmsStatusService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.ListWrkcmmsStatusDTO;
import io.github.jhipster.application.service.dto.ListWrkcmmsStatusCriteria;
import io.github.jhipster.application.service.ListWrkcmmsStatusQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.ListWrkcmmsStatus}.
 */
@RestController
@RequestMapping("/api")
public class ListWrkcmmsStatusResource {

    private final Logger log = LoggerFactory.getLogger(ListWrkcmmsStatusResource.class);

    private static final String ENTITY_NAME = "listWrkcmmsStatus";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ListWrkcmmsStatusService listWrkcmmsStatusService;

    private final ListWrkcmmsStatusQueryService listWrkcmmsStatusQueryService;

    public ListWrkcmmsStatusResource(ListWrkcmmsStatusService listWrkcmmsStatusService, ListWrkcmmsStatusQueryService listWrkcmmsStatusQueryService) {
        this.listWrkcmmsStatusService = listWrkcmmsStatusService;
        this.listWrkcmmsStatusQueryService = listWrkcmmsStatusQueryService;
    }

    /**
     * {@code POST  /list-wrkcmms-statuses} : Create a new listWrkcmmsStatus.
     *
     * @param listWrkcmmsStatusDTO the listWrkcmmsStatusDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new listWrkcmmsStatusDTO, or with status {@code 400 (Bad Request)} if the listWrkcmmsStatus has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/list-wrkcmms-statuses")
    public ResponseEntity<ListWrkcmmsStatusDTO> createListWrkcmmsStatus(@Valid @RequestBody ListWrkcmmsStatusDTO listWrkcmmsStatusDTO) throws URISyntaxException {
        log.debug("REST request to save ListWrkcmmsStatus : {}", listWrkcmmsStatusDTO);
        if (listWrkcmmsStatusDTO.getId() != null) {
            throw new BadRequestAlertException("A new listWrkcmmsStatus cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ListWrkcmmsStatusDTO result = listWrkcmmsStatusService.save(listWrkcmmsStatusDTO);
        return ResponseEntity.created(new URI("/api/list-wrkcmms-statuses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /list-wrkcmms-statuses} : Updates an existing listWrkcmmsStatus.
     *
     * @param listWrkcmmsStatusDTO the listWrkcmmsStatusDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated listWrkcmmsStatusDTO,
     * or with status {@code 400 (Bad Request)} if the listWrkcmmsStatusDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the listWrkcmmsStatusDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/list-wrkcmms-statuses")
    public ResponseEntity<ListWrkcmmsStatusDTO> updateListWrkcmmsStatus(@Valid @RequestBody ListWrkcmmsStatusDTO listWrkcmmsStatusDTO) throws URISyntaxException {
        log.debug("REST request to update ListWrkcmmsStatus : {}", listWrkcmmsStatusDTO);
        if (listWrkcmmsStatusDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ListWrkcmmsStatusDTO result = listWrkcmmsStatusService.save(listWrkcmmsStatusDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, listWrkcmmsStatusDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /list-wrkcmms-statuses} : get all the listWrkcmmsStatuses.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of listWrkcmmsStatuses in body.
     */
    @GetMapping("/list-wrkcmms-statuses")
    public ResponseEntity<List<ListWrkcmmsStatusDTO>> getAllListWrkcmmsStatuses(ListWrkcmmsStatusCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get ListWrkcmmsStatuses by criteria: {}", criteria);
        Page<ListWrkcmmsStatusDTO> page = listWrkcmmsStatusQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /list-wrkcmms-statuses/count} : count all the listWrkcmmsStatuses.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/list-wrkcmms-statuses/count")
    public ResponseEntity<Long> countListWrkcmmsStatuses(ListWrkcmmsStatusCriteria criteria) {
        log.debug("REST request to count ListWrkcmmsStatuses by criteria: {}", criteria);
        return ResponseEntity.ok().body(listWrkcmmsStatusQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /list-wrkcmms-statuses/:id} : get the "id" listWrkcmmsStatus.
     *
     * @param id the id of the listWrkcmmsStatusDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the listWrkcmmsStatusDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/list-wrkcmms-statuses/{id}")
    public ResponseEntity<ListWrkcmmsStatusDTO> getListWrkcmmsStatus(@PathVariable Long id) {
        log.debug("REST request to get ListWrkcmmsStatus : {}", id);
        Optional<ListWrkcmmsStatusDTO> listWrkcmmsStatusDTO = listWrkcmmsStatusService.findOne(id);
        return ResponseUtil.wrapOrNotFound(listWrkcmmsStatusDTO);
    }

    /**
     * {@code DELETE  /list-wrkcmms-statuses/:id} : delete the "id" listWrkcmmsStatus.
     *
     * @param id the id of the listWrkcmmsStatusDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/list-wrkcmms-statuses/{id}")
    public ResponseEntity<Void> deleteListWrkcmmsStatus(@PathVariable Long id) {
        log.debug("REST request to delete ListWrkcmmsStatus : {}", id);
        listWrkcmmsStatusService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
