package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.ListDfctGroupService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.ListDfctGroupDTO;
import io.github.jhipster.application.service.dto.ListDfctGroupCriteria;
import io.github.jhipster.application.service.ListDfctGroupQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.ListDfctGroup}.
 */
@RestController
@RequestMapping("/api")
public class ListDfctGroupResource {

    private final Logger log = LoggerFactory.getLogger(ListDfctGroupResource.class);

    private static final String ENTITY_NAME = "listDfctGroup";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ListDfctGroupService listDfctGroupService;

    private final ListDfctGroupQueryService listDfctGroupQueryService;

    public ListDfctGroupResource(ListDfctGroupService listDfctGroupService, ListDfctGroupQueryService listDfctGroupQueryService) {
        this.listDfctGroupService = listDfctGroupService;
        this.listDfctGroupQueryService = listDfctGroupQueryService;
    }

    /**
     * {@code POST  /list-dfct-groups} : Create a new listDfctGroup.
     *
     * @param listDfctGroupDTO the listDfctGroupDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new listDfctGroupDTO, or with status {@code 400 (Bad Request)} if the listDfctGroup has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/list-dfct-groups")
    public ResponseEntity<ListDfctGroupDTO> createListDfctGroup(@Valid @RequestBody ListDfctGroupDTO listDfctGroupDTO) throws URISyntaxException {
        log.debug("REST request to save ListDfctGroup : {}", listDfctGroupDTO);
        if (listDfctGroupDTO.getId() != null) {
            throw new BadRequestAlertException("A new listDfctGroup cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ListDfctGroupDTO result = listDfctGroupService.save(listDfctGroupDTO);
        return ResponseEntity.created(new URI("/api/list-dfct-groups/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /list-dfct-groups} : Updates an existing listDfctGroup.
     *
     * @param listDfctGroupDTO the listDfctGroupDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated listDfctGroupDTO,
     * or with status {@code 400 (Bad Request)} if the listDfctGroupDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the listDfctGroupDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/list-dfct-groups")
    public ResponseEntity<ListDfctGroupDTO> updateListDfctGroup(@Valid @RequestBody ListDfctGroupDTO listDfctGroupDTO) throws URISyntaxException {
        log.debug("REST request to update ListDfctGroup : {}", listDfctGroupDTO);
        if (listDfctGroupDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ListDfctGroupDTO result = listDfctGroupService.save(listDfctGroupDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, listDfctGroupDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /list-dfct-groups} : get all the listDfctGroups.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of listDfctGroups in body.
     */
    @GetMapping("/list-dfct-groups")
    public ResponseEntity<List<ListDfctGroupDTO>> getAllListDfctGroups(ListDfctGroupCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get ListDfctGroups by criteria: {}", criteria);
        Page<ListDfctGroupDTO> page = listDfctGroupQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /list-dfct-groups/count} : count all the listDfctGroups.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/list-dfct-groups/count")
    public ResponseEntity<Long> countListDfctGroups(ListDfctGroupCriteria criteria) {
        log.debug("REST request to count ListDfctGroups by criteria: {}", criteria);
        return ResponseEntity.ok().body(listDfctGroupQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /list-dfct-groups/:id} : get the "id" listDfctGroup.
     *
     * @param id the id of the listDfctGroupDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the listDfctGroupDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/list-dfct-groups/{id}")
    public ResponseEntity<ListDfctGroupDTO> getListDfctGroup(@PathVariable Long id) {
        log.debug("REST request to get ListDfctGroup : {}", id);
        Optional<ListDfctGroupDTO> listDfctGroupDTO = listDfctGroupService.findOne(id);
        return ResponseUtil.wrapOrNotFound(listDfctGroupDTO);
    }

    /**
     * {@code DELETE  /list-dfct-groups/:id} : delete the "id" listDfctGroup.
     *
     * @param id the id of the listDfctGroupDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/list-dfct-groups/{id}")
    public ResponseEntity<Void> deleteListDfctGroup(@PathVariable Long id) {
        log.debug("REST request to delete ListDfctGroup : {}", id);
        listDfctGroupService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
