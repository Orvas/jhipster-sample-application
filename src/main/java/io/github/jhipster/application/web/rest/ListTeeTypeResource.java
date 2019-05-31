package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.ListTeeTypeService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.ListTeeTypeDTO;
import io.github.jhipster.application.service.dto.ListTeeTypeCriteria;
import io.github.jhipster.application.service.ListTeeTypeQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.ListTeeType}.
 */
@RestController
@RequestMapping("/api")
public class ListTeeTypeResource {

    private final Logger log = LoggerFactory.getLogger(ListTeeTypeResource.class);

    private static final String ENTITY_NAME = "listTeeType";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ListTeeTypeService listTeeTypeService;

    private final ListTeeTypeQueryService listTeeTypeQueryService;

    public ListTeeTypeResource(ListTeeTypeService listTeeTypeService, ListTeeTypeQueryService listTeeTypeQueryService) {
        this.listTeeTypeService = listTeeTypeService;
        this.listTeeTypeQueryService = listTeeTypeQueryService;
    }

    /**
     * {@code POST  /list-tee-types} : Create a new listTeeType.
     *
     * @param listTeeTypeDTO the listTeeTypeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new listTeeTypeDTO, or with status {@code 400 (Bad Request)} if the listTeeType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/list-tee-types")
    public ResponseEntity<ListTeeTypeDTO> createListTeeType(@Valid @RequestBody ListTeeTypeDTO listTeeTypeDTO) throws URISyntaxException {
        log.debug("REST request to save ListTeeType : {}", listTeeTypeDTO);
        if (listTeeTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new listTeeType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ListTeeTypeDTO result = listTeeTypeService.save(listTeeTypeDTO);
        return ResponseEntity.created(new URI("/api/list-tee-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /list-tee-types} : Updates an existing listTeeType.
     *
     * @param listTeeTypeDTO the listTeeTypeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated listTeeTypeDTO,
     * or with status {@code 400 (Bad Request)} if the listTeeTypeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the listTeeTypeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/list-tee-types")
    public ResponseEntity<ListTeeTypeDTO> updateListTeeType(@Valid @RequestBody ListTeeTypeDTO listTeeTypeDTO) throws URISyntaxException {
        log.debug("REST request to update ListTeeType : {}", listTeeTypeDTO);
        if (listTeeTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ListTeeTypeDTO result = listTeeTypeService.save(listTeeTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, listTeeTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /list-tee-types} : get all the listTeeTypes.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of listTeeTypes in body.
     */
    @GetMapping("/list-tee-types")
    public ResponseEntity<List<ListTeeTypeDTO>> getAllListTeeTypes(ListTeeTypeCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get ListTeeTypes by criteria: {}", criteria);
        Page<ListTeeTypeDTO> page = listTeeTypeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /list-tee-types/count} : count all the listTeeTypes.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/list-tee-types/count")
    public ResponseEntity<Long> countListTeeTypes(ListTeeTypeCriteria criteria) {
        log.debug("REST request to count ListTeeTypes by criteria: {}", criteria);
        return ResponseEntity.ok().body(listTeeTypeQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /list-tee-types/:id} : get the "id" listTeeType.
     *
     * @param id the id of the listTeeTypeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the listTeeTypeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/list-tee-types/{id}")
    public ResponseEntity<ListTeeTypeDTO> getListTeeType(@PathVariable Long id) {
        log.debug("REST request to get ListTeeType : {}", id);
        Optional<ListTeeTypeDTO> listTeeTypeDTO = listTeeTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(listTeeTypeDTO);
    }

    /**
     * {@code DELETE  /list-tee-types/:id} : delete the "id" listTeeType.
     *
     * @param id the id of the listTeeTypeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/list-tee-types/{id}")
    public ResponseEntity<Void> deleteListTeeType(@PathVariable Long id) {
        log.debug("REST request to delete ListTeeType : {}", id);
        listTeeTypeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
