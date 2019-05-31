package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.ListWrkKindService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.ListWrkKindDTO;
import io.github.jhipster.application.service.dto.ListWrkKindCriteria;
import io.github.jhipster.application.service.ListWrkKindQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.ListWrkKind}.
 */
@RestController
@RequestMapping("/api")
public class ListWrkKindResource {

    private final Logger log = LoggerFactory.getLogger(ListWrkKindResource.class);

    private static final String ENTITY_NAME = "listWrkKind";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ListWrkKindService listWrkKindService;

    private final ListWrkKindQueryService listWrkKindQueryService;

    public ListWrkKindResource(ListWrkKindService listWrkKindService, ListWrkKindQueryService listWrkKindQueryService) {
        this.listWrkKindService = listWrkKindService;
        this.listWrkKindQueryService = listWrkKindQueryService;
    }

    /**
     * {@code POST  /list-wrk-kinds} : Create a new listWrkKind.
     *
     * @param listWrkKindDTO the listWrkKindDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new listWrkKindDTO, or with status {@code 400 (Bad Request)} if the listWrkKind has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/list-wrk-kinds")
    public ResponseEntity<ListWrkKindDTO> createListWrkKind(@Valid @RequestBody ListWrkKindDTO listWrkKindDTO) throws URISyntaxException {
        log.debug("REST request to save ListWrkKind : {}", listWrkKindDTO);
        if (listWrkKindDTO.getId() != null) {
            throw new BadRequestAlertException("A new listWrkKind cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ListWrkKindDTO result = listWrkKindService.save(listWrkKindDTO);
        return ResponseEntity.created(new URI("/api/list-wrk-kinds/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /list-wrk-kinds} : Updates an existing listWrkKind.
     *
     * @param listWrkKindDTO the listWrkKindDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated listWrkKindDTO,
     * or with status {@code 400 (Bad Request)} if the listWrkKindDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the listWrkKindDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/list-wrk-kinds")
    public ResponseEntity<ListWrkKindDTO> updateListWrkKind(@Valid @RequestBody ListWrkKindDTO listWrkKindDTO) throws URISyntaxException {
        log.debug("REST request to update ListWrkKind : {}", listWrkKindDTO);
        if (listWrkKindDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ListWrkKindDTO result = listWrkKindService.save(listWrkKindDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, listWrkKindDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /list-wrk-kinds} : get all the listWrkKinds.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of listWrkKinds in body.
     */
    @GetMapping("/list-wrk-kinds")
    public ResponseEntity<List<ListWrkKindDTO>> getAllListWrkKinds(ListWrkKindCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get ListWrkKinds by criteria: {}", criteria);
        Page<ListWrkKindDTO> page = listWrkKindQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /list-wrk-kinds/count} : count all the listWrkKinds.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/list-wrk-kinds/count")
    public ResponseEntity<Long> countListWrkKinds(ListWrkKindCriteria criteria) {
        log.debug("REST request to count ListWrkKinds by criteria: {}", criteria);
        return ResponseEntity.ok().body(listWrkKindQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /list-wrk-kinds/:id} : get the "id" listWrkKind.
     *
     * @param id the id of the listWrkKindDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the listWrkKindDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/list-wrk-kinds/{id}")
    public ResponseEntity<ListWrkKindDTO> getListWrkKind(@PathVariable Long id) {
        log.debug("REST request to get ListWrkKind : {}", id);
        Optional<ListWrkKindDTO> listWrkKindDTO = listWrkKindService.findOne(id);
        return ResponseUtil.wrapOrNotFound(listWrkKindDTO);
    }

    /**
     * {@code DELETE  /list-wrk-kinds/:id} : delete the "id" listWrkKind.
     *
     * @param id the id of the listWrkKindDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/list-wrk-kinds/{id}")
    public ResponseEntity<Void> deleteListWrkKind(@PathVariable Long id) {
        log.debug("REST request to delete ListWrkKind : {}", id);
        listWrkKindService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
