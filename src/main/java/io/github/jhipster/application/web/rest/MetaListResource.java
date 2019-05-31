package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.MetaListService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.MetaListDTO;
import io.github.jhipster.application.service.dto.MetaListCriteria;
import io.github.jhipster.application.service.MetaListQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.MetaList}.
 */
@RestController
@RequestMapping("/api")
public class MetaListResource {

    private final Logger log = LoggerFactory.getLogger(MetaListResource.class);

    private static final String ENTITY_NAME = "metaList";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MetaListService metaListService;

    private final MetaListQueryService metaListQueryService;

    public MetaListResource(MetaListService metaListService, MetaListQueryService metaListQueryService) {
        this.metaListService = metaListService;
        this.metaListQueryService = metaListQueryService;
    }

    /**
     * {@code POST  /meta-lists} : Create a new metaList.
     *
     * @param metaListDTO the metaListDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new metaListDTO, or with status {@code 400 (Bad Request)} if the metaList has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/meta-lists")
    public ResponseEntity<MetaListDTO> createMetaList(@Valid @RequestBody MetaListDTO metaListDTO) throws URISyntaxException {
        log.debug("REST request to save MetaList : {}", metaListDTO);
        if (metaListDTO.getId() != null) {
            throw new BadRequestAlertException("A new metaList cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MetaListDTO result = metaListService.save(metaListDTO);
        return ResponseEntity.created(new URI("/api/meta-lists/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /meta-lists} : Updates an existing metaList.
     *
     * @param metaListDTO the metaListDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated metaListDTO,
     * or with status {@code 400 (Bad Request)} if the metaListDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the metaListDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/meta-lists")
    public ResponseEntity<MetaListDTO> updateMetaList(@Valid @RequestBody MetaListDTO metaListDTO) throws URISyntaxException {
        log.debug("REST request to update MetaList : {}", metaListDTO);
        if (metaListDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MetaListDTO result = metaListService.save(metaListDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, metaListDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /meta-lists} : get all the metaLists.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of metaLists in body.
     */
    @GetMapping("/meta-lists")
    public ResponseEntity<List<MetaListDTO>> getAllMetaLists(MetaListCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get MetaLists by criteria: {}", criteria);
        Page<MetaListDTO> page = metaListQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /meta-lists/count} : count all the metaLists.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/meta-lists/count")
    public ResponseEntity<Long> countMetaLists(MetaListCriteria criteria) {
        log.debug("REST request to count MetaLists by criteria: {}", criteria);
        return ResponseEntity.ok().body(metaListQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /meta-lists/:id} : get the "id" metaList.
     *
     * @param id the id of the metaListDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the metaListDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/meta-lists/{id}")
    public ResponseEntity<MetaListDTO> getMetaList(@PathVariable Long id) {
        log.debug("REST request to get MetaList : {}", id);
        Optional<MetaListDTO> metaListDTO = metaListService.findOne(id);
        return ResponseUtil.wrapOrNotFound(metaListDTO);
    }

    /**
     * {@code DELETE  /meta-lists/:id} : delete the "id" metaList.
     *
     * @param id the id of the metaListDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/meta-lists/{id}")
    public ResponseEntity<Void> deleteMetaList(@PathVariable Long id) {
        log.debug("REST request to delete MetaList : {}", id);
        metaListService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
