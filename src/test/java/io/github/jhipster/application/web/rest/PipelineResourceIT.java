package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.Pipeline;
import io.github.jhipster.application.domain.BaseClass;
import io.github.jhipster.application.domain.LaunchReceiverHist;
import io.github.jhipster.application.domain.PipelineHist;
import io.github.jhipster.application.domain.PipelineSection;
import io.github.jhipster.application.repository.PipelineRepository;
import io.github.jhipster.application.service.PipelineService;
import io.github.jhipster.application.service.dto.PipelineDTO;
import io.github.jhipster.application.service.mapper.PipelineMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.PipelineCriteria;
import io.github.jhipster.application.service.PipelineQueryService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@Link PipelineResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class PipelineResourceIT {

    private static final Instant DEFAULT_DATE_CREATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_CREATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_DATE_EDIT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_EDIT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CREATOR = "AAAAAAAAAA";
    private static final String UPDATED_CREATOR = "BBBBBBBBBB";

    private static final String DEFAULT_EDITOR = "AAAAAAAAAA";
    private static final String UPDATED_EDITOR = "BBBBBBBBBB";

    @Autowired
    private PipelineRepository pipelineRepository;

    @Autowired
    private PipelineMapper pipelineMapper;

    @Autowired
    private PipelineService pipelineService;

    @Autowired
    private PipelineQueryService pipelineQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restPipelineMockMvc;

    private Pipeline pipeline;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PipelineResource pipelineResource = new PipelineResource(pipelineService, pipelineQueryService);
        this.restPipelineMockMvc = MockMvcBuilders.standaloneSetup(pipelineResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Pipeline createEntity(EntityManager em) {
        Pipeline pipeline = new Pipeline()
            .dateCreate(DEFAULT_DATE_CREATE)
            .dateEdit(DEFAULT_DATE_EDIT)
            .creator(DEFAULT_CREATOR)
            .editor(DEFAULT_EDITOR);
        // Add required entity
        BaseClass baseClass;
        if (TestUtil.findAll(em, BaseClass.class).isEmpty()) {
            baseClass = BaseClassResourceIT.createEntity(em);
            em.persist(baseClass);
            em.flush();
        } else {
            baseClass = TestUtil.findAll(em, BaseClass.class).get(0);
        }
        pipeline.setId(baseClass);
        return pipeline;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Pipeline createUpdatedEntity(EntityManager em) {
        Pipeline pipeline = new Pipeline()
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        // Add required entity
        BaseClass baseClass;
        if (TestUtil.findAll(em, BaseClass.class).isEmpty()) {
            baseClass = BaseClassResourceIT.createUpdatedEntity(em);
            em.persist(baseClass);
            em.flush();
        } else {
            baseClass = TestUtil.findAll(em, BaseClass.class).get(0);
        }
        pipeline.setId(baseClass);
        return pipeline;
    }

    @BeforeEach
    public void initTest() {
        pipeline = createEntity(em);
    }

    @Test
    @Transactional
    public void createPipeline() throws Exception {
        int databaseSizeBeforeCreate = pipelineRepository.findAll().size();

        // Create the Pipeline
        PipelineDTO pipelineDTO = pipelineMapper.toDto(pipeline);
        restPipelineMockMvc.perform(post("/api/pipelines")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pipelineDTO)))
            .andExpect(status().isCreated());

        // Validate the Pipeline in the database
        List<Pipeline> pipelineList = pipelineRepository.findAll();
        assertThat(pipelineList).hasSize(databaseSizeBeforeCreate + 1);
        Pipeline testPipeline = pipelineList.get(pipelineList.size() - 1);
        assertThat(testPipeline.getDateCreate()).isEqualTo(DEFAULT_DATE_CREATE);
        assertThat(testPipeline.getDateEdit()).isEqualTo(DEFAULT_DATE_EDIT);
        assertThat(testPipeline.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testPipeline.getEditor()).isEqualTo(DEFAULT_EDITOR);
    }

    @Test
    @Transactional
    public void createPipelineWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = pipelineRepository.findAll().size();

        // Create the Pipeline with an existing ID
        pipeline.setId(1L);
        PipelineDTO pipelineDTO = pipelineMapper.toDto(pipeline);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPipelineMockMvc.perform(post("/api/pipelines")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pipelineDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Pipeline in the database
        List<Pipeline> pipelineList = pipelineRepository.findAll();
        assertThat(pipelineList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllPipelines() throws Exception {
        // Initialize the database
        pipelineRepository.saveAndFlush(pipeline);

        // Get all the pipelineList
        restPipelineMockMvc.perform(get("/api/pipelines?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pipeline.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateCreate").value(hasItem(DEFAULT_DATE_CREATE.toString())))
            .andExpect(jsonPath("$.[*].dateEdit").value(hasItem(DEFAULT_DATE_EDIT.toString())))
            .andExpect(jsonPath("$.[*].creator").value(hasItem(DEFAULT_CREATOR.toString())))
            .andExpect(jsonPath("$.[*].editor").value(hasItem(DEFAULT_EDITOR.toString())));
    }
    
    @Test
    @Transactional
    public void getPipeline() throws Exception {
        // Initialize the database
        pipelineRepository.saveAndFlush(pipeline);

        // Get the pipeline
        restPipelineMockMvc.perform(get("/api/pipelines/{id}", pipeline.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(pipeline.getId().intValue()))
            .andExpect(jsonPath("$.dateCreate").value(DEFAULT_DATE_CREATE.toString()))
            .andExpect(jsonPath("$.dateEdit").value(DEFAULT_DATE_EDIT.toString()))
            .andExpect(jsonPath("$.creator").value(DEFAULT_CREATOR.toString()))
            .andExpect(jsonPath("$.editor").value(DEFAULT_EDITOR.toString()));
    }

    @Test
    @Transactional
    public void getAllPipelinesByDateCreateIsEqualToSomething() throws Exception {
        // Initialize the database
        pipelineRepository.saveAndFlush(pipeline);

        // Get all the pipelineList where dateCreate equals to DEFAULT_DATE_CREATE
        defaultPipelineShouldBeFound("dateCreate.equals=" + DEFAULT_DATE_CREATE);

        // Get all the pipelineList where dateCreate equals to UPDATED_DATE_CREATE
        defaultPipelineShouldNotBeFound("dateCreate.equals=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllPipelinesByDateCreateIsInShouldWork() throws Exception {
        // Initialize the database
        pipelineRepository.saveAndFlush(pipeline);

        // Get all the pipelineList where dateCreate in DEFAULT_DATE_CREATE or UPDATED_DATE_CREATE
        defaultPipelineShouldBeFound("dateCreate.in=" + DEFAULT_DATE_CREATE + "," + UPDATED_DATE_CREATE);

        // Get all the pipelineList where dateCreate equals to UPDATED_DATE_CREATE
        defaultPipelineShouldNotBeFound("dateCreate.in=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllPipelinesByDateCreateIsNullOrNotNull() throws Exception {
        // Initialize the database
        pipelineRepository.saveAndFlush(pipeline);

        // Get all the pipelineList where dateCreate is not null
        defaultPipelineShouldBeFound("dateCreate.specified=true");

        // Get all the pipelineList where dateCreate is null
        defaultPipelineShouldNotBeFound("dateCreate.specified=false");
    }

    @Test
    @Transactional
    public void getAllPipelinesByDateEditIsEqualToSomething() throws Exception {
        // Initialize the database
        pipelineRepository.saveAndFlush(pipeline);

        // Get all the pipelineList where dateEdit equals to DEFAULT_DATE_EDIT
        defaultPipelineShouldBeFound("dateEdit.equals=" + DEFAULT_DATE_EDIT);

        // Get all the pipelineList where dateEdit equals to UPDATED_DATE_EDIT
        defaultPipelineShouldNotBeFound("dateEdit.equals=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllPipelinesByDateEditIsInShouldWork() throws Exception {
        // Initialize the database
        pipelineRepository.saveAndFlush(pipeline);

        // Get all the pipelineList where dateEdit in DEFAULT_DATE_EDIT or UPDATED_DATE_EDIT
        defaultPipelineShouldBeFound("dateEdit.in=" + DEFAULT_DATE_EDIT + "," + UPDATED_DATE_EDIT);

        // Get all the pipelineList where dateEdit equals to UPDATED_DATE_EDIT
        defaultPipelineShouldNotBeFound("dateEdit.in=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllPipelinesByDateEditIsNullOrNotNull() throws Exception {
        // Initialize the database
        pipelineRepository.saveAndFlush(pipeline);

        // Get all the pipelineList where dateEdit is not null
        defaultPipelineShouldBeFound("dateEdit.specified=true");

        // Get all the pipelineList where dateEdit is null
        defaultPipelineShouldNotBeFound("dateEdit.specified=false");
    }

    @Test
    @Transactional
    public void getAllPipelinesByCreatorIsEqualToSomething() throws Exception {
        // Initialize the database
        pipelineRepository.saveAndFlush(pipeline);

        // Get all the pipelineList where creator equals to DEFAULT_CREATOR
        defaultPipelineShouldBeFound("creator.equals=" + DEFAULT_CREATOR);

        // Get all the pipelineList where creator equals to UPDATED_CREATOR
        defaultPipelineShouldNotBeFound("creator.equals=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllPipelinesByCreatorIsInShouldWork() throws Exception {
        // Initialize the database
        pipelineRepository.saveAndFlush(pipeline);

        // Get all the pipelineList where creator in DEFAULT_CREATOR or UPDATED_CREATOR
        defaultPipelineShouldBeFound("creator.in=" + DEFAULT_CREATOR + "," + UPDATED_CREATOR);

        // Get all the pipelineList where creator equals to UPDATED_CREATOR
        defaultPipelineShouldNotBeFound("creator.in=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllPipelinesByCreatorIsNullOrNotNull() throws Exception {
        // Initialize the database
        pipelineRepository.saveAndFlush(pipeline);

        // Get all the pipelineList where creator is not null
        defaultPipelineShouldBeFound("creator.specified=true");

        // Get all the pipelineList where creator is null
        defaultPipelineShouldNotBeFound("creator.specified=false");
    }

    @Test
    @Transactional
    public void getAllPipelinesByEditorIsEqualToSomething() throws Exception {
        // Initialize the database
        pipelineRepository.saveAndFlush(pipeline);

        // Get all the pipelineList where editor equals to DEFAULT_EDITOR
        defaultPipelineShouldBeFound("editor.equals=" + DEFAULT_EDITOR);

        // Get all the pipelineList where editor equals to UPDATED_EDITOR
        defaultPipelineShouldNotBeFound("editor.equals=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllPipelinesByEditorIsInShouldWork() throws Exception {
        // Initialize the database
        pipelineRepository.saveAndFlush(pipeline);

        // Get all the pipelineList where editor in DEFAULT_EDITOR or UPDATED_EDITOR
        defaultPipelineShouldBeFound("editor.in=" + DEFAULT_EDITOR + "," + UPDATED_EDITOR);

        // Get all the pipelineList where editor equals to UPDATED_EDITOR
        defaultPipelineShouldNotBeFound("editor.in=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllPipelinesByEditorIsNullOrNotNull() throws Exception {
        // Initialize the database
        pipelineRepository.saveAndFlush(pipeline);

        // Get all the pipelineList where editor is not null
        defaultPipelineShouldBeFound("editor.specified=true");

        // Get all the pipelineList where editor is null
        defaultPipelineShouldNotBeFound("editor.specified=false");
    }

    @Test
    @Transactional
    public void getAllPipelinesByIdIsEqualToSomething() throws Exception {
        // Get already existing entity
        BaseClass id = pipeline.getId();
        pipelineRepository.saveAndFlush(pipeline);
        Long idId = id.getId();

        // Get all the pipelineList where id equals to idId
        defaultPipelineShouldBeFound("idId.equals=" + idId);

        // Get all the pipelineList where id equals to idId + 1
        defaultPipelineShouldNotBeFound("idId.equals=" + (idId + 1));
    }


    @Test
    @Transactional
    public void getAllPipelinesByLaunchReceiverHistIsEqualToSomething() throws Exception {
        // Initialize the database
        LaunchReceiverHist launchReceiverHist = LaunchReceiverHistResourceIT.createEntity(em);
        em.persist(launchReceiverHist);
        em.flush();
        pipeline.addLaunchReceiverHist(launchReceiverHist);
        pipelineRepository.saveAndFlush(pipeline);
        Long launchReceiverHistId = launchReceiverHist.getId();

        // Get all the pipelineList where launchReceiverHist equals to launchReceiverHistId
        defaultPipelineShouldBeFound("launchReceiverHistId.equals=" + launchReceiverHistId);

        // Get all the pipelineList where launchReceiverHist equals to launchReceiverHistId + 1
        defaultPipelineShouldNotBeFound("launchReceiverHistId.equals=" + (launchReceiverHistId + 1));
    }


    @Test
    @Transactional
    public void getAllPipelinesByPipelineHistIsEqualToSomething() throws Exception {
        // Initialize the database
        PipelineHist pipelineHist = PipelineHistResourceIT.createEntity(em);
        em.persist(pipelineHist);
        em.flush();
        pipeline.addPipelineHist(pipelineHist);
        pipelineRepository.saveAndFlush(pipeline);
        Long pipelineHistId = pipelineHist.getId();

        // Get all the pipelineList where pipelineHist equals to pipelineHistId
        defaultPipelineShouldBeFound("pipelineHistId.equals=" + pipelineHistId);

        // Get all the pipelineList where pipelineHist equals to pipelineHistId + 1
        defaultPipelineShouldNotBeFound("pipelineHistId.equals=" + (pipelineHistId + 1));
    }


    @Test
    @Transactional
    public void getAllPipelinesByPipelineSectionIsEqualToSomething() throws Exception {
        // Initialize the database
        PipelineSection pipelineSection = PipelineSectionResourceIT.createEntity(em);
        em.persist(pipelineSection);
        em.flush();
        pipeline.addPipelineSection(pipelineSection);
        pipelineRepository.saveAndFlush(pipeline);
        Long pipelineSectionId = pipelineSection.getId();

        // Get all the pipelineList where pipelineSection equals to pipelineSectionId
        defaultPipelineShouldBeFound("pipelineSectionId.equals=" + pipelineSectionId);

        // Get all the pipelineList where pipelineSection equals to pipelineSectionId + 1
        defaultPipelineShouldNotBeFound("pipelineSectionId.equals=" + (pipelineSectionId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultPipelineShouldBeFound(String filter) throws Exception {
        restPipelineMockMvc.perform(get("/api/pipelines?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pipeline.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateCreate").value(hasItem(DEFAULT_DATE_CREATE.toString())))
            .andExpect(jsonPath("$.[*].dateEdit").value(hasItem(DEFAULT_DATE_EDIT.toString())))
            .andExpect(jsonPath("$.[*].creator").value(hasItem(DEFAULT_CREATOR)))
            .andExpect(jsonPath("$.[*].editor").value(hasItem(DEFAULT_EDITOR)));

        // Check, that the count call also returns 1
        restPipelineMockMvc.perform(get("/api/pipelines/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultPipelineShouldNotBeFound(String filter) throws Exception {
        restPipelineMockMvc.perform(get("/api/pipelines?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restPipelineMockMvc.perform(get("/api/pipelines/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingPipeline() throws Exception {
        // Get the pipeline
        restPipelineMockMvc.perform(get("/api/pipelines/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePipeline() throws Exception {
        // Initialize the database
        pipelineRepository.saveAndFlush(pipeline);

        int databaseSizeBeforeUpdate = pipelineRepository.findAll().size();

        // Update the pipeline
        Pipeline updatedPipeline = pipelineRepository.findById(pipeline.getId()).get();
        // Disconnect from session so that the updates on updatedPipeline are not directly saved in db
        em.detach(updatedPipeline);
        updatedPipeline
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        PipelineDTO pipelineDTO = pipelineMapper.toDto(updatedPipeline);

        restPipelineMockMvc.perform(put("/api/pipelines")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pipelineDTO)))
            .andExpect(status().isOk());

        // Validate the Pipeline in the database
        List<Pipeline> pipelineList = pipelineRepository.findAll();
        assertThat(pipelineList).hasSize(databaseSizeBeforeUpdate);
        Pipeline testPipeline = pipelineList.get(pipelineList.size() - 1);
        assertThat(testPipeline.getDateCreate()).isEqualTo(UPDATED_DATE_CREATE);
        assertThat(testPipeline.getDateEdit()).isEqualTo(UPDATED_DATE_EDIT);
        assertThat(testPipeline.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testPipeline.getEditor()).isEqualTo(UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void updateNonExistingPipeline() throws Exception {
        int databaseSizeBeforeUpdate = pipelineRepository.findAll().size();

        // Create the Pipeline
        PipelineDTO pipelineDTO = pipelineMapper.toDto(pipeline);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPipelineMockMvc.perform(put("/api/pipelines")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pipelineDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Pipeline in the database
        List<Pipeline> pipelineList = pipelineRepository.findAll();
        assertThat(pipelineList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePipeline() throws Exception {
        // Initialize the database
        pipelineRepository.saveAndFlush(pipeline);

        int databaseSizeBeforeDelete = pipelineRepository.findAll().size();

        // Delete the pipeline
        restPipelineMockMvc.perform(delete("/api/pipelines/{id}", pipeline.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<Pipeline> pipelineList = pipelineRepository.findAll();
        assertThat(pipelineList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Pipeline.class);
        Pipeline pipeline1 = new Pipeline();
        pipeline1.setId(1L);
        Pipeline pipeline2 = new Pipeline();
        pipeline2.setId(pipeline1.getId());
        assertThat(pipeline1).isEqualTo(pipeline2);
        pipeline2.setId(2L);
        assertThat(pipeline1).isNotEqualTo(pipeline2);
        pipeline1.setId(null);
        assertThat(pipeline1).isNotEqualTo(pipeline2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PipelineDTO.class);
        PipelineDTO pipelineDTO1 = new PipelineDTO();
        pipelineDTO1.setId(1L);
        PipelineDTO pipelineDTO2 = new PipelineDTO();
        assertThat(pipelineDTO1).isNotEqualTo(pipelineDTO2);
        pipelineDTO2.setId(pipelineDTO1.getId());
        assertThat(pipelineDTO1).isEqualTo(pipelineDTO2);
        pipelineDTO2.setId(2L);
        assertThat(pipelineDTO1).isNotEqualTo(pipelineDTO2);
        pipelineDTO1.setId(null);
        assertThat(pipelineDTO1).isNotEqualTo(pipelineDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(pipelineMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(pipelineMapper.fromId(null)).isNull();
    }
}
