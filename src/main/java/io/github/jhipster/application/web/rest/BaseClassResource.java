package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.BaseClassService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.BaseClassDTO;
import io.github.jhipster.application.service.dto.BaseClassCriteria;
import io.github.jhipster.application.service.BaseClassQueryService;

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
import java.util.stream.StreamSupport;

/**
 * REST controller for managing {@link io.github.jhipster.application.domain.BaseClass}.
 */
@RestController
@RequestMapping("/api")
public class BaseClassResource {

    private final Logger log = LoggerFactory.getLogger(BaseClassResource.class);

    private static final String ENTITY_NAME = "baseClass";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BaseClassService baseClassService;

    private final BaseClassQueryService baseClassQueryService;

    public BaseClassResource(BaseClassService baseClassService, BaseClassQueryService baseClassQueryService) {
        this.baseClassService = baseClassService;
        this.baseClassQueryService = baseClassQueryService;
    }

    /**
     * {@code POST  /base-classes} : Create a new baseClass.
     *
     * @param baseClassDTO the baseClassDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new baseClassDTO, or with status {@code 400 (Bad Request)} if the baseClass has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/base-classes")
    public ResponseEntity<BaseClassDTO> createBaseClass(@Valid @RequestBody BaseClassDTO baseClassDTO) throws URISyntaxException {
        log.debug("REST request to save BaseClass : {}", baseClassDTO);
        if (baseClassDTO.getId() != null) {
            throw new BadRequestAlertException("A new baseClass cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BaseClassDTO result = baseClassService.save(baseClassDTO);
        return ResponseEntity.created(new URI("/api/base-classes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /base-classes} : Updates an existing baseClass.
     *
     * @param baseClassDTO the baseClassDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated baseClassDTO,
     * or with status {@code 400 (Bad Request)} if the baseClassDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the baseClassDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/base-classes")
    public ResponseEntity<BaseClassDTO> updateBaseClass(@Valid @RequestBody BaseClassDTO baseClassDTO) throws URISyntaxException {
        log.debug("REST request to update BaseClass : {}", baseClassDTO);
        if (baseClassDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BaseClassDTO result = baseClassService.save(baseClassDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, baseClassDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /base-classes} : get all the baseClasses.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of baseClasses in body.
     */
    @GetMapping("/base-classes")
    public ResponseEntity<List<BaseClassDTO>> getAllBaseClasses(BaseClassCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get BaseClasses by criteria: {}", criteria);
        Page<BaseClassDTO> page = baseClassQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /base-classes/count} : count all the baseClasses.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/base-classes/count")
    public ResponseEntity<Long> countBaseClasses(BaseClassCriteria criteria) {
        log.debug("REST request to count BaseClasses by criteria: {}", criteria);
        return ResponseEntity.ok().body(baseClassQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /base-classes/:id} : get the "id" baseClass.
     *
     * @param id the id of the baseClassDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the baseClassDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/base-classes/{id}")
    public ResponseEntity<BaseClassDTO> getBaseClass(@PathVariable Long id) {
        log.debug("REST request to get BaseClass : {}", id);
        Optional<BaseClassDTO> baseClassDTO = baseClassService.findOne(id);
        return ResponseUtil.wrapOrNotFound(baseClassDTO);
    }

    /**
     * {@code DELETE  /base-classes/:id} : delete the "id" baseClass.
     *
     * @param id the id of the baseClassDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/base-classes/{id}")
    public ResponseEntity<Void> deleteBaseClass(@PathVariable Long id) {
        log.debug("REST request to delete BaseClass : {}", id);
        baseClassService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
