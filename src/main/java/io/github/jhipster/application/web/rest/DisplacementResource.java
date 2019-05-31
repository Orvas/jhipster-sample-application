package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.DisplacementService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.DisplacementDTO;
import io.github.jhipster.application.service.dto.DisplacementCriteria;
import io.github.jhipster.application.service.DisplacementQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.Displacement}.
 */
@RestController
@RequestMapping("/api")
public class DisplacementResource {

    private final Logger log = LoggerFactory.getLogger(DisplacementResource.class);

    private static final String ENTITY_NAME = "displacement";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DisplacementService displacementService;

    private final DisplacementQueryService displacementQueryService;

    public DisplacementResource(DisplacementService displacementService, DisplacementQueryService displacementQueryService) {
        this.displacementService = displacementService;
        this.displacementQueryService = displacementQueryService;
    }

    /**
     * {@code POST  /displacements} : Create a new displacement.
     *
     * @param displacementDTO the displacementDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new displacementDTO, or with status {@code 400 (Bad Request)} if the displacement has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/displacements")
    public ResponseEntity<DisplacementDTO> createDisplacement(@Valid @RequestBody DisplacementDTO displacementDTO) throws URISyntaxException {
        log.debug("REST request to save Displacement : {}", displacementDTO);
        if (displacementDTO.getId() != null) {
            throw new BadRequestAlertException("A new displacement cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DisplacementDTO result = displacementService.save(displacementDTO);
        return ResponseEntity.created(new URI("/api/displacements/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /displacements} : Updates an existing displacement.
     *
     * @param displacementDTO the displacementDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated displacementDTO,
     * or with status {@code 400 (Bad Request)} if the displacementDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the displacementDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/displacements")
    public ResponseEntity<DisplacementDTO> updateDisplacement(@Valid @RequestBody DisplacementDTO displacementDTO) throws URISyntaxException {
        log.debug("REST request to update Displacement : {}", displacementDTO);
        if (displacementDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DisplacementDTO result = displacementService.save(displacementDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, displacementDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /displacements} : get all the displacements.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of displacements in body.
     */
    @GetMapping("/displacements")
    public ResponseEntity<List<DisplacementDTO>> getAllDisplacements(DisplacementCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get Displacements by criteria: {}", criteria);
        Page<DisplacementDTO> page = displacementQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /displacements/count} : count all the displacements.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/displacements/count")
    public ResponseEntity<Long> countDisplacements(DisplacementCriteria criteria) {
        log.debug("REST request to count Displacements by criteria: {}", criteria);
        return ResponseEntity.ok().body(displacementQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /displacements/:id} : get the "id" displacement.
     *
     * @param id the id of the displacementDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the displacementDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/displacements/{id}")
    public ResponseEntity<DisplacementDTO> getDisplacement(@PathVariable Long id) {
        log.debug("REST request to get Displacement : {}", id);
        Optional<DisplacementDTO> displacementDTO = displacementService.findOne(id);
        return ResponseUtil.wrapOrNotFound(displacementDTO);
    }

    /**
     * {@code DELETE  /displacements/:id} : delete the "id" displacement.
     *
     * @param id the id of the displacementDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/displacements/{id}")
    public ResponseEntity<Void> deleteDisplacement(@PathVariable Long id) {
        log.debug("REST request to delete Displacement : {}", id);
        displacementService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
