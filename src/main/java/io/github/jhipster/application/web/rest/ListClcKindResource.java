package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.ListClcKindService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.ListClcKindDTO;
import io.github.jhipster.application.service.dto.ListClcKindCriteria;
import io.github.jhipster.application.service.ListClcKindQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.ListClcKind}.
 */
@RestController
@RequestMapping("/api")
public class ListClcKindResource {

    private final Logger log = LoggerFactory.getLogger(ListClcKindResource.class);

    private static final String ENTITY_NAME = "listClcKind";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ListClcKindService listClcKindService;

    private final ListClcKindQueryService listClcKindQueryService;

    public ListClcKindResource(ListClcKindService listClcKindService, ListClcKindQueryService listClcKindQueryService) {
        this.listClcKindService = listClcKindService;
        this.listClcKindQueryService = listClcKindQueryService;
    }

    /**
     * {@code POST  /list-clc-kinds} : Create a new listClcKind.
     *
     * @param listClcKindDTO the listClcKindDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new listClcKindDTO, or with status {@code 400 (Bad Request)} if the listClcKind has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/list-clc-kinds")
    public ResponseEntity<ListClcKindDTO> createListClcKind(@Valid @RequestBody ListClcKindDTO listClcKindDTO) throws URISyntaxException {
        log.debug("REST request to save ListClcKind : {}", listClcKindDTO);
        if (listClcKindDTO.getId() != null) {
            throw new BadRequestAlertException("A new listClcKind cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ListClcKindDTO result = listClcKindService.save(listClcKindDTO);
        return ResponseEntity.created(new URI("/api/list-clc-kinds/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /list-clc-kinds} : Updates an existing listClcKind.
     *
     * @param listClcKindDTO the listClcKindDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated listClcKindDTO,
     * or with status {@code 400 (Bad Request)} if the listClcKindDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the listClcKindDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/list-clc-kinds")
    public ResponseEntity<ListClcKindDTO> updateListClcKind(@Valid @RequestBody ListClcKindDTO listClcKindDTO) throws URISyntaxException {
        log.debug("REST request to update ListClcKind : {}", listClcKindDTO);
        if (listClcKindDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ListClcKindDTO result = listClcKindService.save(listClcKindDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, listClcKindDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /list-clc-kinds} : get all the listClcKinds.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of listClcKinds in body.
     */
    @GetMapping("/list-clc-kinds")
    public ResponseEntity<List<ListClcKindDTO>> getAllListClcKinds(ListClcKindCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get ListClcKinds by criteria: {}", criteria);
        Page<ListClcKindDTO> page = listClcKindQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /list-clc-kinds/count} : count all the listClcKinds.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/list-clc-kinds/count")
    public ResponseEntity<Long> countListClcKinds(ListClcKindCriteria criteria) {
        log.debug("REST request to count ListClcKinds by criteria: {}", criteria);
        return ResponseEntity.ok().body(listClcKindQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /list-clc-kinds/:id} : get the "id" listClcKind.
     *
     * @param id the id of the listClcKindDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the listClcKindDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/list-clc-kinds/{id}")
    public ResponseEntity<ListClcKindDTO> getListClcKind(@PathVariable Long id) {
        log.debug("REST request to get ListClcKind : {}", id);
        Optional<ListClcKindDTO> listClcKindDTO = listClcKindService.findOne(id);
        return ResponseUtil.wrapOrNotFound(listClcKindDTO);
    }

    /**
     * {@code DELETE  /list-clc-kinds/:id} : delete the "id" listClcKind.
     *
     * @param id the id of the listClcKindDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/list-clc-kinds/{id}")
    public ResponseEntity<Void> deleteListClcKind(@PathVariable Long id) {
        log.debug("REST request to delete ListClcKind : {}", id);
        listClcKindService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
