package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.PipelineSectionService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.PipelineSectionDTO;
import io.github.jhipster.application.service.dto.PipelineSectionCriteria;
import io.github.jhipster.application.service.PipelineSectionQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.PipelineSection}.
 */
@RestController
@RequestMapping("/api")
public class PipelineSectionResource {

    private final Logger log = LoggerFactory.getLogger(PipelineSectionResource.class);

    private static final String ENTITY_NAME = "pipelineSection";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PipelineSectionService pipelineSectionService;

    private final PipelineSectionQueryService pipelineSectionQueryService;

    public PipelineSectionResource(PipelineSectionService pipelineSectionService, PipelineSectionQueryService pipelineSectionQueryService) {
        this.pipelineSectionService = pipelineSectionService;
        this.pipelineSectionQueryService = pipelineSectionQueryService;
    }

    /**
     * {@code POST  /pipeline-sections} : Create a new pipelineSection.
     *
     * @param pipelineSectionDTO the pipelineSectionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new pipelineSectionDTO, or with status {@code 400 (Bad Request)} if the pipelineSection has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pipeline-sections")
    public ResponseEntity<PipelineSectionDTO> createPipelineSection(@Valid @RequestBody PipelineSectionDTO pipelineSectionDTO) throws URISyntaxException {
        log.debug("REST request to save PipelineSection : {}", pipelineSectionDTO);
        if (pipelineSectionDTO.getId() != null) {
            throw new BadRequestAlertException("A new pipelineSection cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PipelineSectionDTO result = pipelineSectionService.save(pipelineSectionDTO);
        return ResponseEntity.created(new URI("/api/pipeline-sections/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pipeline-sections} : Updates an existing pipelineSection.
     *
     * @param pipelineSectionDTO the pipelineSectionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pipelineSectionDTO,
     * or with status {@code 400 (Bad Request)} if the pipelineSectionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the pipelineSectionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pipeline-sections")
    public ResponseEntity<PipelineSectionDTO> updatePipelineSection(@Valid @RequestBody PipelineSectionDTO pipelineSectionDTO) throws URISyntaxException {
        log.debug("REST request to update PipelineSection : {}", pipelineSectionDTO);
        if (pipelineSectionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PipelineSectionDTO result = pipelineSectionService.save(pipelineSectionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, pipelineSectionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /pipeline-sections} : get all the pipelineSections.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of pipelineSections in body.
     */
    @GetMapping("/pipeline-sections")
    public ResponseEntity<List<PipelineSectionDTO>> getAllPipelineSections(PipelineSectionCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get PipelineSections by criteria: {}", criteria);
        Page<PipelineSectionDTO> page = pipelineSectionQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /pipeline-sections/count} : count all the pipelineSections.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/pipeline-sections/count")
    public ResponseEntity<Long> countPipelineSections(PipelineSectionCriteria criteria) {
        log.debug("REST request to count PipelineSections by criteria: {}", criteria);
        return ResponseEntity.ok().body(pipelineSectionQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /pipeline-sections/:id} : get the "id" pipelineSection.
     *
     * @param id the id of the pipelineSectionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the pipelineSectionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pipeline-sections/{id}")
    public ResponseEntity<PipelineSectionDTO> getPipelineSection(@PathVariable Long id) {
        log.debug("REST request to get PipelineSection : {}", id);
        Optional<PipelineSectionDTO> pipelineSectionDTO = pipelineSectionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(pipelineSectionDTO);
    }

    /**
     * {@code DELETE  /pipeline-sections/:id} : delete the "id" pipelineSection.
     *
     * @param id the id of the pipelineSectionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pipeline-sections/{id}")
    public ResponseEntity<Void> deletePipelineSection(@PathVariable Long id) {
        log.debug("REST request to delete PipelineSection : {}", id);
        pipelineSectionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
