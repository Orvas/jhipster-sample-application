package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.ListIliPigTypeService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.ListIliPigTypeDTO;
import io.github.jhipster.application.service.dto.ListIliPigTypeCriteria;
import io.github.jhipster.application.service.ListIliPigTypeQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.ListIliPigType}.
 */
@RestController
@RequestMapping("/api")
public class ListIliPigTypeResource {

    private final Logger log = LoggerFactory.getLogger(ListIliPigTypeResource.class);

    private static final String ENTITY_NAME = "listIliPigType";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ListIliPigTypeService listIliPigTypeService;

    private final ListIliPigTypeQueryService listIliPigTypeQueryService;

    public ListIliPigTypeResource(ListIliPigTypeService listIliPigTypeService, ListIliPigTypeQueryService listIliPigTypeQueryService) {
        this.listIliPigTypeService = listIliPigTypeService;
        this.listIliPigTypeQueryService = listIliPigTypeQueryService;
    }

    /**
     * {@code POST  /list-ili-pig-types} : Create a new listIliPigType.
     *
     * @param listIliPigTypeDTO the listIliPigTypeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new listIliPigTypeDTO, or with status {@code 400 (Bad Request)} if the listIliPigType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/list-ili-pig-types")
    public ResponseEntity<ListIliPigTypeDTO> createListIliPigType(@Valid @RequestBody ListIliPigTypeDTO listIliPigTypeDTO) throws URISyntaxException {
        log.debug("REST request to save ListIliPigType : {}", listIliPigTypeDTO);
        if (listIliPigTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new listIliPigType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ListIliPigTypeDTO result = listIliPigTypeService.save(listIliPigTypeDTO);
        return ResponseEntity.created(new URI("/api/list-ili-pig-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /list-ili-pig-types} : Updates an existing listIliPigType.
     *
     * @param listIliPigTypeDTO the listIliPigTypeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated listIliPigTypeDTO,
     * or with status {@code 400 (Bad Request)} if the listIliPigTypeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the listIliPigTypeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/list-ili-pig-types")
    public ResponseEntity<ListIliPigTypeDTO> updateListIliPigType(@Valid @RequestBody ListIliPigTypeDTO listIliPigTypeDTO) throws URISyntaxException {
        log.debug("REST request to update ListIliPigType : {}", listIliPigTypeDTO);
        if (listIliPigTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ListIliPigTypeDTO result = listIliPigTypeService.save(listIliPigTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, listIliPigTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /list-ili-pig-types} : get all the listIliPigTypes.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of listIliPigTypes in body.
     */
    @GetMapping("/list-ili-pig-types")
    public ResponseEntity<List<ListIliPigTypeDTO>> getAllListIliPigTypes(ListIliPigTypeCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get ListIliPigTypes by criteria: {}", criteria);
        Page<ListIliPigTypeDTO> page = listIliPigTypeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /list-ili-pig-types/count} : count all the listIliPigTypes.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/list-ili-pig-types/count")
    public ResponseEntity<Long> countListIliPigTypes(ListIliPigTypeCriteria criteria) {
        log.debug("REST request to count ListIliPigTypes by criteria: {}", criteria);
        return ResponseEntity.ok().body(listIliPigTypeQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /list-ili-pig-types/:id} : get the "id" listIliPigType.
     *
     * @param id the id of the listIliPigTypeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the listIliPigTypeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/list-ili-pig-types/{id}")
    public ResponseEntity<ListIliPigTypeDTO> getListIliPigType(@PathVariable Long id) {
        log.debug("REST request to get ListIliPigType : {}", id);
        Optional<ListIliPigTypeDTO> listIliPigTypeDTO = listIliPigTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(listIliPigTypeDTO);
    }

    /**
     * {@code DELETE  /list-ili-pig-types/:id} : delete the "id" listIliPigType.
     *
     * @param id the id of the listIliPigTypeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/list-ili-pig-types/{id}")
    public ResponseEntity<Void> deleteListIliPigType(@PathVariable Long id) {
        log.debug("REST request to delete ListIliPigType : {}", id);
        listIliPigTypeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
