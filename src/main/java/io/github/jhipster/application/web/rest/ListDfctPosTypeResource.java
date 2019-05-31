package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.ListDfctPosTypeService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.ListDfctPosTypeDTO;
import io.github.jhipster.application.service.dto.ListDfctPosTypeCriteria;
import io.github.jhipster.application.service.ListDfctPosTypeQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.ListDfctPosType}.
 */
@RestController
@RequestMapping("/api")
public class ListDfctPosTypeResource {

    private final Logger log = LoggerFactory.getLogger(ListDfctPosTypeResource.class);

    private static final String ENTITY_NAME = "listDfctPosType";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ListDfctPosTypeService listDfctPosTypeService;

    private final ListDfctPosTypeQueryService listDfctPosTypeQueryService;

    public ListDfctPosTypeResource(ListDfctPosTypeService listDfctPosTypeService, ListDfctPosTypeQueryService listDfctPosTypeQueryService) {
        this.listDfctPosTypeService = listDfctPosTypeService;
        this.listDfctPosTypeQueryService = listDfctPosTypeQueryService;
    }

    /**
     * {@code POST  /list-dfct-pos-types} : Create a new listDfctPosType.
     *
     * @param listDfctPosTypeDTO the listDfctPosTypeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new listDfctPosTypeDTO, or with status {@code 400 (Bad Request)} if the listDfctPosType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/list-dfct-pos-types")
    public ResponseEntity<ListDfctPosTypeDTO> createListDfctPosType(@Valid @RequestBody ListDfctPosTypeDTO listDfctPosTypeDTO) throws URISyntaxException {
        log.debug("REST request to save ListDfctPosType : {}", listDfctPosTypeDTO);
        if (listDfctPosTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new listDfctPosType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ListDfctPosTypeDTO result = listDfctPosTypeService.save(listDfctPosTypeDTO);
        return ResponseEntity.created(new URI("/api/list-dfct-pos-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /list-dfct-pos-types} : Updates an existing listDfctPosType.
     *
     * @param listDfctPosTypeDTO the listDfctPosTypeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated listDfctPosTypeDTO,
     * or with status {@code 400 (Bad Request)} if the listDfctPosTypeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the listDfctPosTypeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/list-dfct-pos-types")
    public ResponseEntity<ListDfctPosTypeDTO> updateListDfctPosType(@Valid @RequestBody ListDfctPosTypeDTO listDfctPosTypeDTO) throws URISyntaxException {
        log.debug("REST request to update ListDfctPosType : {}", listDfctPosTypeDTO);
        if (listDfctPosTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ListDfctPosTypeDTO result = listDfctPosTypeService.save(listDfctPosTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, listDfctPosTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /list-dfct-pos-types} : get all the listDfctPosTypes.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of listDfctPosTypes in body.
     */
    @GetMapping("/list-dfct-pos-types")
    public ResponseEntity<List<ListDfctPosTypeDTO>> getAllListDfctPosTypes(ListDfctPosTypeCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get ListDfctPosTypes by criteria: {}", criteria);
        Page<ListDfctPosTypeDTO> page = listDfctPosTypeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /list-dfct-pos-types/count} : count all the listDfctPosTypes.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/list-dfct-pos-types/count")
    public ResponseEntity<Long> countListDfctPosTypes(ListDfctPosTypeCriteria criteria) {
        log.debug("REST request to count ListDfctPosTypes by criteria: {}", criteria);
        return ResponseEntity.ok().body(listDfctPosTypeQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /list-dfct-pos-types/:id} : get the "id" listDfctPosType.
     *
     * @param id the id of the listDfctPosTypeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the listDfctPosTypeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/list-dfct-pos-types/{id}")
    public ResponseEntity<ListDfctPosTypeDTO> getListDfctPosType(@PathVariable Long id) {
        log.debug("REST request to get ListDfctPosType : {}", id);
        Optional<ListDfctPosTypeDTO> listDfctPosTypeDTO = listDfctPosTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(listDfctPosTypeDTO);
    }

    /**
     * {@code DELETE  /list-dfct-pos-types/:id} : delete the "id" listDfctPosType.
     *
     * @param id the id of the listDfctPosTypeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/list-dfct-pos-types/{id}")
    public ResponseEntity<Void> deleteListDfctPosType(@PathVariable Long id) {
        log.debug("REST request to delete ListDfctPosType : {}", id);
        listDfctPosTypeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
