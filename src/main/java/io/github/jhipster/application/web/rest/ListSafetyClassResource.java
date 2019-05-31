package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.ListSafetyClassService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.ListSafetyClassDTO;
import io.github.jhipster.application.service.dto.ListSafetyClassCriteria;
import io.github.jhipster.application.service.ListSafetyClassQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.ListSafetyClass}.
 */
@RestController
@RequestMapping("/api")
public class ListSafetyClassResource {

    private final Logger log = LoggerFactory.getLogger(ListSafetyClassResource.class);

    private static final String ENTITY_NAME = "listSafetyClass";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ListSafetyClassService listSafetyClassService;

    private final ListSafetyClassQueryService listSafetyClassQueryService;

    public ListSafetyClassResource(ListSafetyClassService listSafetyClassService, ListSafetyClassQueryService listSafetyClassQueryService) {
        this.listSafetyClassService = listSafetyClassService;
        this.listSafetyClassQueryService = listSafetyClassQueryService;
    }

    /**
     * {@code POST  /list-safety-classes} : Create a new listSafetyClass.
     *
     * @param listSafetyClassDTO the listSafetyClassDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new listSafetyClassDTO, or with status {@code 400 (Bad Request)} if the listSafetyClass has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/list-safety-classes")
    public ResponseEntity<ListSafetyClassDTO> createListSafetyClass(@Valid @RequestBody ListSafetyClassDTO listSafetyClassDTO) throws URISyntaxException {
        log.debug("REST request to save ListSafetyClass : {}", listSafetyClassDTO);
        if (listSafetyClassDTO.getId() != null) {
            throw new BadRequestAlertException("A new listSafetyClass cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ListSafetyClassDTO result = listSafetyClassService.save(listSafetyClassDTO);
        return ResponseEntity.created(new URI("/api/list-safety-classes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /list-safety-classes} : Updates an existing listSafetyClass.
     *
     * @param listSafetyClassDTO the listSafetyClassDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated listSafetyClassDTO,
     * or with status {@code 400 (Bad Request)} if the listSafetyClassDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the listSafetyClassDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/list-safety-classes")
    public ResponseEntity<ListSafetyClassDTO> updateListSafetyClass(@Valid @RequestBody ListSafetyClassDTO listSafetyClassDTO) throws URISyntaxException {
        log.debug("REST request to update ListSafetyClass : {}", listSafetyClassDTO);
        if (listSafetyClassDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ListSafetyClassDTO result = listSafetyClassService.save(listSafetyClassDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, listSafetyClassDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /list-safety-classes} : get all the listSafetyClasses.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of listSafetyClasses in body.
     */
    @GetMapping("/list-safety-classes")
    public ResponseEntity<List<ListSafetyClassDTO>> getAllListSafetyClasses(ListSafetyClassCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get ListSafetyClasses by criteria: {}", criteria);
        Page<ListSafetyClassDTO> page = listSafetyClassQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /list-safety-classes/count} : count all the listSafetyClasses.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/list-safety-classes/count")
    public ResponseEntity<Long> countListSafetyClasses(ListSafetyClassCriteria criteria) {
        log.debug("REST request to count ListSafetyClasses by criteria: {}", criteria);
        return ResponseEntity.ok().body(listSafetyClassQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /list-safety-classes/:id} : get the "id" listSafetyClass.
     *
     * @param id the id of the listSafetyClassDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the listSafetyClassDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/list-safety-classes/{id}")
    public ResponseEntity<ListSafetyClassDTO> getListSafetyClass(@PathVariable Long id) {
        log.debug("REST request to get ListSafetyClass : {}", id);
        Optional<ListSafetyClassDTO> listSafetyClassDTO = listSafetyClassService.findOne(id);
        return ResponseUtil.wrapOrNotFound(listSafetyClassDTO);
    }

    /**
     * {@code DELETE  /list-safety-classes/:id} : delete the "id" listSafetyClass.
     *
     * @param id the id of the listSafetyClassDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/list-safety-classes/{id}")
    public ResponseEntity<Void> deleteListSafetyClass(@PathVariable Long id) {
        log.debug("REST request to delete ListSafetyClass : {}", id);
        listSafetyClassService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
