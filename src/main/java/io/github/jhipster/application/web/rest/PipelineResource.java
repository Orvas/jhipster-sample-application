package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.PipelineService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.PipelineDTO;
import io.github.jhipster.application.service.dto.PipelineCriteria;
import io.github.jhipster.application.service.PipelineQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.Pipeline}.
 */
@RestController
@RequestMapping("/api")
public class PipelineResource {

    private final Logger log = LoggerFactory.getLogger(PipelineResource.class);

    private static final String ENTITY_NAME = "pipeline";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PipelineService pipelineService;

    private final PipelineQueryService pipelineQueryService;

    public PipelineResource(PipelineService pipelineService, PipelineQueryService pipelineQueryService) {
        this.pipelineService = pipelineService;
        this.pipelineQueryService = pipelineQueryService;
    }

    /**
     * {@code POST  /pipelines} : Create a new pipeline.
     *
     * @param pipelineDTO the pipelineDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new pipelineDTO, or with status {@code 400 (Bad Request)} if the pipeline has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pipelines")
    public ResponseEntity<PipelineDTO> createPipeline(@Valid @RequestBody PipelineDTO pipelineDTO) throws URISyntaxException {
        log.debug("REST request to save Pipeline : {}", pipelineDTO);
        if (pipelineDTO.getId() != null) {
            throw new BadRequestAlertException("A new pipeline cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PipelineDTO result = pipelineService.save(pipelineDTO);
        return ResponseEntity.created(new URI("/api/pipelines/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pipelines} : Updates an existing pipeline.
     *
     * @param pipelineDTO the pipelineDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pipelineDTO,
     * or with status {@code 400 (Bad Request)} if the pipelineDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the pipelineDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pipelines")
    public ResponseEntity<PipelineDTO> updatePipeline(@Valid @RequestBody PipelineDTO pipelineDTO) throws URISyntaxException {
        log.debug("REST request to update Pipeline : {}", pipelineDTO);
        if (pipelineDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PipelineDTO result = pipelineService.save(pipelineDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, pipelineDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /pipelines} : get all the pipelines.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of pipelines in body.
     */
    @GetMapping("/pipelines")
    public ResponseEntity<List<PipelineDTO>> getAllPipelines(PipelineCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get Pipelines by criteria: {}", criteria);
        Page<PipelineDTO> page = pipelineQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /pipelines/count} : count all the pipelines.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/pipelines/count")
    public ResponseEntity<Long> countPipelines(PipelineCriteria criteria) {
        log.debug("REST request to count Pipelines by criteria: {}", criteria);
        return ResponseEntity.ok().body(pipelineQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /pipelines/:id} : get the "id" pipeline.
     *
     * @param id the id of the pipelineDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the pipelineDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pipelines/{id}")
    public ResponseEntity<PipelineDTO> getPipeline(@PathVariable Long id) {
        log.debug("REST request to get Pipeline : {}", id);
        Optional<PipelineDTO> pipelineDTO = pipelineService.findOne(id);
        return ResponseUtil.wrapOrNotFound(pipelineDTO);
    }

    /**
     * {@code DELETE  /pipelines/:id} : delete the "id" pipeline.
     *
     * @param id the id of the pipelineDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pipelines/{id}")
    public ResponseEntity<Void> deletePipeline(@PathVariable Long id) {
        log.debug("REST request to delete Pipeline : {}", id);
        pipelineService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
