package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.ListSoilTypeService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.ListSoilTypeDTO;
import io.github.jhipster.application.service.dto.ListSoilTypeCriteria;
import io.github.jhipster.application.service.ListSoilTypeQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.ListSoilType}.
 */
@RestController
@RequestMapping("/api")
public class ListSoilTypeResource {

    private final Logger log = LoggerFactory.getLogger(ListSoilTypeResource.class);

    private static final String ENTITY_NAME = "listSoilType";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ListSoilTypeService listSoilTypeService;

    private final ListSoilTypeQueryService listSoilTypeQueryService;

    public ListSoilTypeResource(ListSoilTypeService listSoilTypeService, ListSoilTypeQueryService listSoilTypeQueryService) {
        this.listSoilTypeService = listSoilTypeService;
        this.listSoilTypeQueryService = listSoilTypeQueryService;
    }

    /**
     * {@code POST  /list-soil-types} : Create a new listSoilType.
     *
     * @param listSoilTypeDTO the listSoilTypeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new listSoilTypeDTO, or with status {@code 400 (Bad Request)} if the listSoilType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/list-soil-types")
    public ResponseEntity<ListSoilTypeDTO> createListSoilType(@Valid @RequestBody ListSoilTypeDTO listSoilTypeDTO) throws URISyntaxException {
        log.debug("REST request to save ListSoilType : {}", listSoilTypeDTO);
        if (listSoilTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new listSoilType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ListSoilTypeDTO result = listSoilTypeService.save(listSoilTypeDTO);
        return ResponseEntity.created(new URI("/api/list-soil-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /list-soil-types} : Updates an existing listSoilType.
     *
     * @param listSoilTypeDTO the listSoilTypeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated listSoilTypeDTO,
     * or with status {@code 400 (Bad Request)} if the listSoilTypeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the listSoilTypeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/list-soil-types")
    public ResponseEntity<ListSoilTypeDTO> updateListSoilType(@Valid @RequestBody ListSoilTypeDTO listSoilTypeDTO) throws URISyntaxException {
        log.debug("REST request to update ListSoilType : {}", listSoilTypeDTO);
        if (listSoilTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ListSoilTypeDTO result = listSoilTypeService.save(listSoilTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, listSoilTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /list-soil-types} : get all the listSoilTypes.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of listSoilTypes in body.
     */
    @GetMapping("/list-soil-types")
    public ResponseEntity<List<ListSoilTypeDTO>> getAllListSoilTypes(ListSoilTypeCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get ListSoilTypes by criteria: {}", criteria);
        Page<ListSoilTypeDTO> page = listSoilTypeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /list-soil-types/count} : count all the listSoilTypes.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/list-soil-types/count")
    public ResponseEntity<Long> countListSoilTypes(ListSoilTypeCriteria criteria) {
        log.debug("REST request to count ListSoilTypes by criteria: {}", criteria);
        return ResponseEntity.ok().body(listSoilTypeQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /list-soil-types/:id} : get the "id" listSoilType.
     *
     * @param id the id of the listSoilTypeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the listSoilTypeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/list-soil-types/{id}")
    public ResponseEntity<ListSoilTypeDTO> getListSoilType(@PathVariable Long id) {
        log.debug("REST request to get ListSoilType : {}", id);
        Optional<ListSoilTypeDTO> listSoilTypeDTO = listSoilTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(listSoilTypeDTO);
    }

    /**
     * {@code DELETE  /list-soil-types/:id} : delete the "id" listSoilType.
     *
     * @param id the id of the listSoilTypeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/list-soil-types/{id}")
    public ResponseEntity<Void> deleteListSoilType(@PathVariable Long id) {
        log.debug("REST request to delete ListSoilType : {}", id);
        listSoilTypeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
