package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.PipelineSegmentService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.PipelineSegmentDTO;
import io.github.jhipster.application.service.dto.PipelineSegmentCriteria;
import io.github.jhipster.application.service.PipelineSegmentQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.PipelineSegment}.
 */
@RestController
@RequestMapping("/api")
public class PipelineSegmentResource {

    private final Logger log = LoggerFactory.getLogger(PipelineSegmentResource.class);

    private static final String ENTITY_NAME = "pipelineSegment";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PipelineSegmentService pipelineSegmentService;

    private final PipelineSegmentQueryService pipelineSegmentQueryService;

    public PipelineSegmentResource(PipelineSegmentService pipelineSegmentService, PipelineSegmentQueryService pipelineSegmentQueryService) {
        this.pipelineSegmentService = pipelineSegmentService;
        this.pipelineSegmentQueryService = pipelineSegmentQueryService;
    }

    /**
     * {@code POST  /pipeline-segments} : Create a new pipelineSegment.
     *
     * @param pipelineSegmentDTO the pipelineSegmentDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new pipelineSegmentDTO, or with status {@code 400 (Bad Request)} if the pipelineSegment has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pipeline-segments")
    public ResponseEntity<PipelineSegmentDTO> createPipelineSegment(@Valid @RequestBody PipelineSegmentDTO pipelineSegmentDTO) throws URISyntaxException {
        log.debug("REST request to save PipelineSegment : {}", pipelineSegmentDTO);
        if (pipelineSegmentDTO.getId() != null) {
            throw new BadRequestAlertException("A new pipelineSegment cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PipelineSegmentDTO result = pipelineSegmentService.save(pipelineSegmentDTO);
        return ResponseEntity.created(new URI("/api/pipeline-segments/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pipeline-segments} : Updates an existing pipelineSegment.
     *
     * @param pipelineSegmentDTO the pipelineSegmentDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pipelineSegmentDTO,
     * or with status {@code 400 (Bad Request)} if the pipelineSegmentDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the pipelineSegmentDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pipeline-segments")
    public ResponseEntity<PipelineSegmentDTO> updatePipelineSegment(@Valid @RequestBody PipelineSegmentDTO pipelineSegmentDTO) throws URISyntaxException {
        log.debug("REST request to update PipelineSegment : {}", pipelineSegmentDTO);
        if (pipelineSegmentDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PipelineSegmentDTO result = pipelineSegmentService.save(pipelineSegmentDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, pipelineSegmentDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /pipeline-segments} : get all the pipelineSegments.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of pipelineSegments in body.
     */
    @GetMapping("/pipeline-segments")
    public ResponseEntity<List<PipelineSegmentDTO>> getAllPipelineSegments(PipelineSegmentCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get PipelineSegments by criteria: {}", criteria);
        Page<PipelineSegmentDTO> page = pipelineSegmentQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /pipeline-segments/count} : count all the pipelineSegments.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/pipeline-segments/count")
    public ResponseEntity<Long> countPipelineSegments(PipelineSegmentCriteria criteria) {
        log.debug("REST request to count PipelineSegments by criteria: {}", criteria);
        return ResponseEntity.ok().body(pipelineSegmentQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /pipeline-segments/:id} : get the "id" pipelineSegment.
     *
     * @param id the id of the pipelineSegmentDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the pipelineSegmentDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pipeline-segments/{id}")
    public ResponseEntity<PipelineSegmentDTO> getPipelineSegment(@PathVariable Long id) {
        log.debug("REST request to get PipelineSegment : {}", id);
        Optional<PipelineSegmentDTO> pipelineSegmentDTO = pipelineSegmentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(pipelineSegmentDTO);
    }

    /**
     * {@code DELETE  /pipeline-segments/:id} : delete the "id" pipelineSegment.
     *
     * @param id the id of the pipelineSegmentDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pipeline-segments/{id}")
    public ResponseEntity<Void> deletePipelineSegment(@PathVariable Long id) {
        log.debug("REST request to delete PipelineSegment : {}", id);
        pipelineSegmentService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
