package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.ListEffAxforceUcaseService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.ListEffAxforceUcaseDTO;
import io.github.jhipster.application.service.dto.ListEffAxforceUcaseCriteria;
import io.github.jhipster.application.service.ListEffAxforceUcaseQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.ListEffAxforceUcase}.
 */
@RestController
@RequestMapping("/api")
public class ListEffAxforceUcaseResource {

    private final Logger log = LoggerFactory.getLogger(ListEffAxforceUcaseResource.class);

    private static final String ENTITY_NAME = "listEffAxforceUcase";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ListEffAxforceUcaseService listEffAxforceUcaseService;

    private final ListEffAxforceUcaseQueryService listEffAxforceUcaseQueryService;

    public ListEffAxforceUcaseResource(ListEffAxforceUcaseService listEffAxforceUcaseService, ListEffAxforceUcaseQueryService listEffAxforceUcaseQueryService) {
        this.listEffAxforceUcaseService = listEffAxforceUcaseService;
        this.listEffAxforceUcaseQueryService = listEffAxforceUcaseQueryService;
    }

    /**
     * {@code POST  /list-eff-axforce-ucases} : Create a new listEffAxforceUcase.
     *
     * @param listEffAxforceUcaseDTO the listEffAxforceUcaseDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new listEffAxforceUcaseDTO, or with status {@code 400 (Bad Request)} if the listEffAxforceUcase has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/list-eff-axforce-ucases")
    public ResponseEntity<ListEffAxforceUcaseDTO> createListEffAxforceUcase(@Valid @RequestBody ListEffAxforceUcaseDTO listEffAxforceUcaseDTO) throws URISyntaxException {
        log.debug("REST request to save ListEffAxforceUcase : {}", listEffAxforceUcaseDTO);
        if (listEffAxforceUcaseDTO.getId() != null) {
            throw new BadRequestAlertException("A new listEffAxforceUcase cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ListEffAxforceUcaseDTO result = listEffAxforceUcaseService.save(listEffAxforceUcaseDTO);
        return ResponseEntity.created(new URI("/api/list-eff-axforce-ucases/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /list-eff-axforce-ucases} : Updates an existing listEffAxforceUcase.
     *
     * @param listEffAxforceUcaseDTO the listEffAxforceUcaseDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated listEffAxforceUcaseDTO,
     * or with status {@code 400 (Bad Request)} if the listEffAxforceUcaseDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the listEffAxforceUcaseDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/list-eff-axforce-ucases")
    public ResponseEntity<ListEffAxforceUcaseDTO> updateListEffAxforceUcase(@Valid @RequestBody ListEffAxforceUcaseDTO listEffAxforceUcaseDTO) throws URISyntaxException {
        log.debug("REST request to update ListEffAxforceUcase : {}", listEffAxforceUcaseDTO);
        if (listEffAxforceUcaseDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ListEffAxforceUcaseDTO result = listEffAxforceUcaseService.save(listEffAxforceUcaseDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, listEffAxforceUcaseDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /list-eff-axforce-ucases} : get all the listEffAxforceUcases.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of listEffAxforceUcases in body.
     */
    @GetMapping("/list-eff-axforce-ucases")
    public ResponseEntity<List<ListEffAxforceUcaseDTO>> getAllListEffAxforceUcases(ListEffAxforceUcaseCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get ListEffAxforceUcases by criteria: {}", criteria);
        Page<ListEffAxforceUcaseDTO> page = listEffAxforceUcaseQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /list-eff-axforce-ucases/count} : count all the listEffAxforceUcases.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/list-eff-axforce-ucases/count")
    public ResponseEntity<Long> countListEffAxforceUcases(ListEffAxforceUcaseCriteria criteria) {
        log.debug("REST request to count ListEffAxforceUcases by criteria: {}", criteria);
        return ResponseEntity.ok().body(listEffAxforceUcaseQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /list-eff-axforce-ucases/:id} : get the "id" listEffAxforceUcase.
     *
     * @param id the id of the listEffAxforceUcaseDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the listEffAxforceUcaseDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/list-eff-axforce-ucases/{id}")
    public ResponseEntity<ListEffAxforceUcaseDTO> getListEffAxforceUcase(@PathVariable Long id) {
        log.debug("REST request to get ListEffAxforceUcase : {}", id);
        Optional<ListEffAxforceUcaseDTO> listEffAxforceUcaseDTO = listEffAxforceUcaseService.findOne(id);
        return ResponseUtil.wrapOrNotFound(listEffAxforceUcaseDTO);
    }

    /**
     * {@code DELETE  /list-eff-axforce-ucases/:id} : delete the "id" listEffAxforceUcase.
     *
     * @param id the id of the listEffAxforceUcaseDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/list-eff-axforce-ucases/{id}")
    public ResponseEntity<Void> deleteListEffAxforceUcase(@PathVariable Long id) {
        log.debug("REST request to delete ListEffAxforceUcase : {}", id);
        listEffAxforceUcaseService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
