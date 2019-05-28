package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.domain.PipelineSection;
import io.github.jhipster.application.repository.PipelineSectionRepository;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    private final PipelineSectionRepository pipelineSectionRepository;

    public PipelineSectionResource(PipelineSectionRepository pipelineSectionRepository) {
        this.pipelineSectionRepository = pipelineSectionRepository;
    }

    /**
     * {@code POST  /pipeline-sections} : Create a new pipelineSection.
     *
     * @param pipelineSection the pipelineSection to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new pipelineSection, or with status {@code 400 (Bad Request)} if the pipelineSection has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pipeline-sections")
    public ResponseEntity<PipelineSection> createPipelineSection(@RequestBody PipelineSection pipelineSection) throws URISyntaxException {
        log.debug("REST request to save PipelineSection : {}", pipelineSection);
        if (pipelineSection.getId() != null) {
            throw new BadRequestAlertException("A new pipelineSection cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PipelineSection result = pipelineSectionRepository.save(pipelineSection);
        return ResponseEntity.created(new URI("/api/pipeline-sections/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pipeline-sections} : Updates an existing pipelineSection.
     *
     * @param pipelineSection the pipelineSection to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated pipelineSection,
     * or with status {@code 400 (Bad Request)} if the pipelineSection is not valid,
     * or with status {@code 500 (Internal Server Error)} if the pipelineSection couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pipeline-sections")
    public ResponseEntity<PipelineSection> updatePipelineSection(@RequestBody PipelineSection pipelineSection) throws URISyntaxException {
        log.debug("REST request to update PipelineSection : {}", pipelineSection);
        if (pipelineSection.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PipelineSection result = pipelineSectionRepository.save(pipelineSection);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, pipelineSection.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /pipeline-sections} : get all the pipelineSections.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of pipelineSections in body.
     */
    @GetMapping("/pipeline-sections")
    public List<PipelineSection> getAllPipelineSections() {
        log.debug("REST request to get all PipelineSections");
        return pipelineSectionRepository.findAll();
    }

    /**
     * {@code GET  /pipeline-sections/:id} : get the "id" pipelineSection.
     *
     * @param id the id of the pipelineSection to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the pipelineSection, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pipeline-sections/{id}")
    public ResponseEntity<PipelineSection> getPipelineSection(@PathVariable Long id) {
        log.debug("REST request to get PipelineSection : {}", id);
        Optional<PipelineSection> pipelineSection = pipelineSectionRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(pipelineSection);
    }

    /**
     * {@code DELETE  /pipeline-sections/:id} : delete the "id" pipelineSection.
     *
     * @param id the id of the pipelineSection to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pipeline-sections/{id}")
    public ResponseEntity<Void> deletePipelineSection(@PathVariable Long id) {
        log.debug("REST request to delete PipelineSection : {}", id);
        pipelineSectionRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
