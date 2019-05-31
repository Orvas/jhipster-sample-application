package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.PipejointService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.PipejointDTO;
import io.github.jhipster.application.service.dto.PipejointCriteria;
import io.github.jhipster.application.service.PipejointQueryService;

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
import java.util.stream.StreamSupport;

/**
 * REST controller for managing {@link io.github.jhipster.application.domain.Pipejoint}.
 */
@RestController
@RequestMapping("/api")
public class PipejointResource {

    private final Logger log = LoggerFactory.getLogger(PipejointResource.class);

    private static final String ENTITY_NAME = "pipejoint";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PipejointService pipejointService;

    private final PipejointQueryService pipejointQueryService;

    public PipejointResource(PipejointService pipejointService, PipejointQueryService pipejointQueryService) {
        this.pipejointService = pipejointService;
        this.pipejointQueryService = pipejointQueryService;
    }

    /**
     * {@code POST  /pipejoints} : Create a new pipejoint.
     *
     * @param pipejointDTO the pipejointDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new pipejointDTO, or with status {@code 400 (Bad Request)} if the pipejoint has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pipejoints")
    public ResponseEntity<PipejointDTO> createPipejoint(@Valid @RequestBody PipejointDTO pipejointDTO) throws URISyntaxException {
        log.debug("REST request to save Pipejoint : {}", pipejointDTO);
        if (pipejointDTO.getId() != null) {
            throw new BadRequestAlertException("A new pipejoint cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PipejointDTO result = pipejointService.save(pipejointDTO);
        return ResponseEntity.created(new URI("/api/pipejoints/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pipejoints} : Updates an existing pipejoint.
     *
     * @param pipejointDTO the pipejointDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pipejointDTO,
     * or with status {@code 400 (Bad Request)} if the pipejointDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the pipejointDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pipejoints")
    public ResponseEntity<PipejointDTO> updatePipejoint(@Valid @RequestBody PipejointDTO pipejointDTO) throws URISyntaxException {
        log.debug("REST request to update Pipejoint : {}", pipejointDTO);
        if (pipejointDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PipejointDTO result = pipejointService.save(pipejointDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, pipejointDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /pipejoints} : get all the pipejoints.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of pipejoints in body.
     */
    @GetMapping("/pipejoints")
    public ResponseEntity<List<PipejointDTO>> getAllPipejoints(PipejointCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get Pipejoints by criteria: {}", criteria);
        Page<PipejointDTO> page = pipejointQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /pipejoints/count} : count all the pipejoints.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/pipejoints/count")
    public ResponseEntity<Long> countPipejoints(PipejointCriteria criteria) {
        log.debug("REST request to count Pipejoints by criteria: {}", criteria);
        return ResponseEntity.ok().body(pipejointQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /pipejoints/:id} : get the "id" pipejoint.
     *
     * @param id the id of the pipejointDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the pipejointDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pipejoints/{id}")
    public ResponseEntity<PipejointDTO> getPipejoint(@PathVariable Long id) {
        log.debug("REST request to get Pipejoint : {}", id);
        Optional<PipejointDTO> pipejointDTO = pipejointService.findOne(id);
        return ResponseUtil.wrapOrNotFound(pipejointDTO);
    }

    /**
     * {@code DELETE  /pipejoints/:id} : delete the "id" pipejoint.
     *
     * @param id the id of the pipejointDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pipejoints/{id}")
    public ResponseEntity<Void> deletePipejoint(@PathVariable Long id) {
        log.debug("REST request to delete Pipejoint : {}", id);
        pipejointService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
