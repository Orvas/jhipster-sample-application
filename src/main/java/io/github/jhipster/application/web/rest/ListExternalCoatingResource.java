package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.ListExternalCoatingService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.ListExternalCoatingDTO;
import io.github.jhipster.application.service.dto.ListExternalCoatingCriteria;
import io.github.jhipster.application.service.ListExternalCoatingQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.ListExternalCoating}.
 */
@RestController
@RequestMapping("/api")
public class ListExternalCoatingResource {

    private final Logger log = LoggerFactory.getLogger(ListExternalCoatingResource.class);

    private static final String ENTITY_NAME = "listExternalCoating";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ListExternalCoatingService listExternalCoatingService;

    private final ListExternalCoatingQueryService listExternalCoatingQueryService;

    public ListExternalCoatingResource(ListExternalCoatingService listExternalCoatingService, ListExternalCoatingQueryService listExternalCoatingQueryService) {
        this.listExternalCoatingService = listExternalCoatingService;
        this.listExternalCoatingQueryService = listExternalCoatingQueryService;
    }

    /**
     * {@code POST  /list-external-coatings} : Create a new listExternalCoating.
     *
     * @param listExternalCoatingDTO the listExternalCoatingDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new listExternalCoatingDTO, or with status {@code 400 (Bad Request)} if the listExternalCoating has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/list-external-coatings")
    public ResponseEntity<ListExternalCoatingDTO> createListExternalCoating(@Valid @RequestBody ListExternalCoatingDTO listExternalCoatingDTO) throws URISyntaxException {
        log.debug("REST request to save ListExternalCoating : {}", listExternalCoatingDTO);
        if (listExternalCoatingDTO.getId() != null) {
            throw new BadRequestAlertException("A new listExternalCoating cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ListExternalCoatingDTO result = listExternalCoatingService.save(listExternalCoatingDTO);
        return ResponseEntity.created(new URI("/api/list-external-coatings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /list-external-coatings} : Updates an existing listExternalCoating.
     *
     * @param listExternalCoatingDTO the listExternalCoatingDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated listExternalCoatingDTO,
     * or with status {@code 400 (Bad Request)} if the listExternalCoatingDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the listExternalCoatingDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/list-external-coatings")
    public ResponseEntity<ListExternalCoatingDTO> updateListExternalCoating(@Valid @RequestBody ListExternalCoatingDTO listExternalCoatingDTO) throws URISyntaxException {
        log.debug("REST request to update ListExternalCoating : {}", listExternalCoatingDTO);
        if (listExternalCoatingDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ListExternalCoatingDTO result = listExternalCoatingService.save(listExternalCoatingDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, listExternalCoatingDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /list-external-coatings} : get all the listExternalCoatings.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of listExternalCoatings in body.
     */
    @GetMapping("/list-external-coatings")
    public ResponseEntity<List<ListExternalCoatingDTO>> getAllListExternalCoatings(ListExternalCoatingCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get ListExternalCoatings by criteria: {}", criteria);
        Page<ListExternalCoatingDTO> page = listExternalCoatingQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /list-external-coatings/count} : count all the listExternalCoatings.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/list-external-coatings/count")
    public ResponseEntity<Long> countListExternalCoatings(ListExternalCoatingCriteria criteria) {
        log.debug("REST request to count ListExternalCoatings by criteria: {}", criteria);
        return ResponseEntity.ok().body(listExternalCoatingQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /list-external-coatings/:id} : get the "id" listExternalCoating.
     *
     * @param id the id of the listExternalCoatingDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the listExternalCoatingDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/list-external-coatings/{id}")
    public ResponseEntity<ListExternalCoatingDTO> getListExternalCoating(@PathVariable Long id) {
        log.debug("REST request to get ListExternalCoating : {}", id);
        Optional<ListExternalCoatingDTO> listExternalCoatingDTO = listExternalCoatingService.findOne(id);
        return ResponseUtil.wrapOrNotFound(listExternalCoatingDTO);
    }

    /**
     * {@code DELETE  /list-external-coatings/:id} : delete the "id" listExternalCoating.
     *
     * @param id the id of the listExternalCoatingDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/list-external-coatings/{id}")
    public ResponseEntity<Void> deleteListExternalCoating(@PathVariable Long id) {
        log.debug("REST request to delete ListExternalCoating : {}", id);
        listExternalCoatingService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
