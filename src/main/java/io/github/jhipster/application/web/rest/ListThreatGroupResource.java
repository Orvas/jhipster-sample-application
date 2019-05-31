package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.ListThreatGroupService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.ListThreatGroupDTO;
import io.github.jhipster.application.service.dto.ListThreatGroupCriteria;
import io.github.jhipster.application.service.ListThreatGroupQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.ListThreatGroup}.
 */
@RestController
@RequestMapping("/api")
public class ListThreatGroupResource {

    private final Logger log = LoggerFactory.getLogger(ListThreatGroupResource.class);

    private static final String ENTITY_NAME = "listThreatGroup";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ListThreatGroupService listThreatGroupService;

    private final ListThreatGroupQueryService listThreatGroupQueryService;

    public ListThreatGroupResource(ListThreatGroupService listThreatGroupService, ListThreatGroupQueryService listThreatGroupQueryService) {
        this.listThreatGroupService = listThreatGroupService;
        this.listThreatGroupQueryService = listThreatGroupQueryService;
    }

    /**
     * {@code POST  /list-threat-groups} : Create a new listThreatGroup.
     *
     * @param listThreatGroupDTO the listThreatGroupDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new listThreatGroupDTO, or with status {@code 400 (Bad Request)} if the listThreatGroup has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/list-threat-groups")
    public ResponseEntity<ListThreatGroupDTO> createListThreatGroup(@Valid @RequestBody ListThreatGroupDTO listThreatGroupDTO) throws URISyntaxException {
        log.debug("REST request to save ListThreatGroup : {}", listThreatGroupDTO);
        if (listThreatGroupDTO.getId() != null) {
            throw new BadRequestAlertException("A new listThreatGroup cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ListThreatGroupDTO result = listThreatGroupService.save(listThreatGroupDTO);
        return ResponseEntity.created(new URI("/api/list-threat-groups/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /list-threat-groups} : Updates an existing listThreatGroup.
     *
     * @param listThreatGroupDTO the listThreatGroupDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated listThreatGroupDTO,
     * or with status {@code 400 (Bad Request)} if the listThreatGroupDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the listThreatGroupDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/list-threat-groups")
    public ResponseEntity<ListThreatGroupDTO> updateListThreatGroup(@Valid @RequestBody ListThreatGroupDTO listThreatGroupDTO) throws URISyntaxException {
        log.debug("REST request to update ListThreatGroup : {}", listThreatGroupDTO);
        if (listThreatGroupDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ListThreatGroupDTO result = listThreatGroupService.save(listThreatGroupDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, listThreatGroupDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /list-threat-groups} : get all the listThreatGroups.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of listThreatGroups in body.
     */
    @GetMapping("/list-threat-groups")
    public ResponseEntity<List<ListThreatGroupDTO>> getAllListThreatGroups(ListThreatGroupCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get ListThreatGroups by criteria: {}", criteria);
        Page<ListThreatGroupDTO> page = listThreatGroupQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /list-threat-groups/count} : count all the listThreatGroups.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/list-threat-groups/count")
    public ResponseEntity<Long> countListThreatGroups(ListThreatGroupCriteria criteria) {
        log.debug("REST request to count ListThreatGroups by criteria: {}", criteria);
        return ResponseEntity.ok().body(listThreatGroupQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /list-threat-groups/:id} : get the "id" listThreatGroup.
     *
     * @param id the id of the listThreatGroupDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the listThreatGroupDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/list-threat-groups/{id}")
    public ResponseEntity<ListThreatGroupDTO> getListThreatGroup(@PathVariable Long id) {
        log.debug("REST request to get ListThreatGroup : {}", id);
        Optional<ListThreatGroupDTO> listThreatGroupDTO = listThreatGroupService.findOne(id);
        return ResponseUtil.wrapOrNotFound(listThreatGroupDTO);
    }

    /**
     * {@code DELETE  /list-threat-groups/:id} : delete the "id" listThreatGroup.
     *
     * @param id the id of the listThreatGroupDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/list-threat-groups/{id}")
    public ResponseEntity<Void> deleteListThreatGroup(@PathVariable Long id) {
        log.debug("REST request to delete ListThreatGroup : {}", id);
        listThreatGroupService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
