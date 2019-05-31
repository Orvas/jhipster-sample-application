package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.ListNominalWallThicknessService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.ListNominalWallThicknessDTO;
import io.github.jhipster.application.service.dto.ListNominalWallThicknessCriteria;
import io.github.jhipster.application.service.ListNominalWallThicknessQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.ListNominalWallThickness}.
 */
@RestController
@RequestMapping("/api")
public class ListNominalWallThicknessResource {

    private final Logger log = LoggerFactory.getLogger(ListNominalWallThicknessResource.class);

    private static final String ENTITY_NAME = "listNominalWallThickness";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ListNominalWallThicknessService listNominalWallThicknessService;

    private final ListNominalWallThicknessQueryService listNominalWallThicknessQueryService;

    public ListNominalWallThicknessResource(ListNominalWallThicknessService listNominalWallThicknessService, ListNominalWallThicknessQueryService listNominalWallThicknessQueryService) {
        this.listNominalWallThicknessService = listNominalWallThicknessService;
        this.listNominalWallThicknessQueryService = listNominalWallThicknessQueryService;
    }

    /**
     * {@code POST  /list-nominal-wall-thicknesses} : Create a new listNominalWallThickness.
     *
     * @param listNominalWallThicknessDTO the listNominalWallThicknessDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new listNominalWallThicknessDTO, or with status {@code 400 (Bad Request)} if the listNominalWallThickness has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/list-nominal-wall-thicknesses")
    public ResponseEntity<ListNominalWallThicknessDTO> createListNominalWallThickness(@Valid @RequestBody ListNominalWallThicknessDTO listNominalWallThicknessDTO) throws URISyntaxException {
        log.debug("REST request to save ListNominalWallThickness : {}", listNominalWallThicknessDTO);
        if (listNominalWallThicknessDTO.getId() != null) {
            throw new BadRequestAlertException("A new listNominalWallThickness cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ListNominalWallThicknessDTO result = listNominalWallThicknessService.save(listNominalWallThicknessDTO);
        return ResponseEntity.created(new URI("/api/list-nominal-wall-thicknesses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /list-nominal-wall-thicknesses} : Updates an existing listNominalWallThickness.
     *
     * @param listNominalWallThicknessDTO the listNominalWallThicknessDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated listNominalWallThicknessDTO,
     * or with status {@code 400 (Bad Request)} if the listNominalWallThicknessDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the listNominalWallThicknessDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/list-nominal-wall-thicknesses")
    public ResponseEntity<ListNominalWallThicknessDTO> updateListNominalWallThickness(@Valid @RequestBody ListNominalWallThicknessDTO listNominalWallThicknessDTO) throws URISyntaxException {
        log.debug("REST request to update ListNominalWallThickness : {}", listNominalWallThicknessDTO);
        if (listNominalWallThicknessDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ListNominalWallThicknessDTO result = listNominalWallThicknessService.save(listNominalWallThicknessDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, listNominalWallThicknessDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /list-nominal-wall-thicknesses} : get all the listNominalWallThicknesses.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of listNominalWallThicknesses in body.
     */
    @GetMapping("/list-nominal-wall-thicknesses")
    public ResponseEntity<List<ListNominalWallThicknessDTO>> getAllListNominalWallThicknesses(ListNominalWallThicknessCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get ListNominalWallThicknesses by criteria: {}", criteria);
        Page<ListNominalWallThicknessDTO> page = listNominalWallThicknessQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /list-nominal-wall-thicknesses/count} : count all the listNominalWallThicknesses.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/list-nominal-wall-thicknesses/count")
    public ResponseEntity<Long> countListNominalWallThicknesses(ListNominalWallThicknessCriteria criteria) {
        log.debug("REST request to count ListNominalWallThicknesses by criteria: {}", criteria);
        return ResponseEntity.ok().body(listNominalWallThicknessQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /list-nominal-wall-thicknesses/:id} : get the "id" listNominalWallThickness.
     *
     * @param id the id of the listNominalWallThicknessDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the listNominalWallThicknessDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/list-nominal-wall-thicknesses/{id}")
    public ResponseEntity<ListNominalWallThicknessDTO> getListNominalWallThickness(@PathVariable Long id) {
        log.debug("REST request to get ListNominalWallThickness : {}", id);
        Optional<ListNominalWallThicknessDTO> listNominalWallThicknessDTO = listNominalWallThicknessService.findOne(id);
        return ResponseUtil.wrapOrNotFound(listNominalWallThicknessDTO);
    }

    /**
     * {@code DELETE  /list-nominal-wall-thicknesses/:id} : delete the "id" listNominalWallThickness.
     *
     * @param id the id of the listNominalWallThicknessDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/list-nominal-wall-thicknesses/{id}")
    public ResponseEntity<Void> deleteListNominalWallThickness(@PathVariable Long id) {
        log.debug("REST request to delete ListNominalWallThickness : {}", id);
        listNominalWallThicknessService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
