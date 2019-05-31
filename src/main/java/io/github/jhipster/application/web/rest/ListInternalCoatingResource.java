package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.ListInternalCoatingService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.ListInternalCoatingDTO;
import io.github.jhipster.application.service.dto.ListInternalCoatingCriteria;
import io.github.jhipster.application.service.ListInternalCoatingQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.ListInternalCoating}.
 */
@RestController
@RequestMapping("/api")
public class ListInternalCoatingResource {

    private final Logger log = LoggerFactory.getLogger(ListInternalCoatingResource.class);

    private static final String ENTITY_NAME = "listInternalCoating";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ListInternalCoatingService listInternalCoatingService;

    private final ListInternalCoatingQueryService listInternalCoatingQueryService;

    public ListInternalCoatingResource(ListInternalCoatingService listInternalCoatingService, ListInternalCoatingQueryService listInternalCoatingQueryService) {
        this.listInternalCoatingService = listInternalCoatingService;
        this.listInternalCoatingQueryService = listInternalCoatingQueryService;
    }

    /**
     * {@code POST  /list-internal-coatings} : Create a new listInternalCoating.
     *
     * @param listInternalCoatingDTO the listInternalCoatingDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new listInternalCoatingDTO, or with status {@code 400 (Bad Request)} if the listInternalCoating has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/list-internal-coatings")
    public ResponseEntity<ListInternalCoatingDTO> createListInternalCoating(@Valid @RequestBody ListInternalCoatingDTO listInternalCoatingDTO) throws URISyntaxException {
        log.debug("REST request to save ListInternalCoating : {}", listInternalCoatingDTO);
        if (listInternalCoatingDTO.getId() != null) {
            throw new BadRequestAlertException("A new listInternalCoating cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ListInternalCoatingDTO result = listInternalCoatingService.save(listInternalCoatingDTO);
        return ResponseEntity.created(new URI("/api/list-internal-coatings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /list-internal-coatings} : Updates an existing listInternalCoating.
     *
     * @param listInternalCoatingDTO the listInternalCoatingDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated listInternalCoatingDTO,
     * or with status {@code 400 (Bad Request)} if the listInternalCoatingDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the listInternalCoatingDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/list-internal-coatings")
    public ResponseEntity<ListInternalCoatingDTO> updateListInternalCoating(@Valid @RequestBody ListInternalCoatingDTO listInternalCoatingDTO) throws URISyntaxException {
        log.debug("REST request to update ListInternalCoating : {}", listInternalCoatingDTO);
        if (listInternalCoatingDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ListInternalCoatingDTO result = listInternalCoatingService.save(listInternalCoatingDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, listInternalCoatingDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /list-internal-coatings} : get all the listInternalCoatings.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of listInternalCoatings in body.
     */
    @GetMapping("/list-internal-coatings")
    public ResponseEntity<List<ListInternalCoatingDTO>> getAllListInternalCoatings(ListInternalCoatingCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get ListInternalCoatings by criteria: {}", criteria);
        Page<ListInternalCoatingDTO> page = listInternalCoatingQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /list-internal-coatings/count} : count all the listInternalCoatings.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/list-internal-coatings/count")
    public ResponseEntity<Long> countListInternalCoatings(ListInternalCoatingCriteria criteria) {
        log.debug("REST request to count ListInternalCoatings by criteria: {}", criteria);
        return ResponseEntity.ok().body(listInternalCoatingQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /list-internal-coatings/:id} : get the "id" listInternalCoating.
     *
     * @param id the id of the listInternalCoatingDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the listInternalCoatingDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/list-internal-coatings/{id}")
    public ResponseEntity<ListInternalCoatingDTO> getListInternalCoating(@PathVariable Long id) {
        log.debug("REST request to get ListInternalCoating : {}", id);
        Optional<ListInternalCoatingDTO> listInternalCoatingDTO = listInternalCoatingService.findOne(id);
        return ResponseUtil.wrapOrNotFound(listInternalCoatingDTO);
    }

    /**
     * {@code DELETE  /list-internal-coatings/:id} : delete the "id" listInternalCoating.
     *
     * @param id the id of the listInternalCoatingDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/list-internal-coatings/{id}")
    public ResponseEntity<Void> deleteListInternalCoating(@PathVariable Long id) {
        log.debug("REST request to delete ListInternalCoating : {}", id);
        listInternalCoatingService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
