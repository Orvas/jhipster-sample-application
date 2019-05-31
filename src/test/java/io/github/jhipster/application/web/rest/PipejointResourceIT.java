package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.Pipejoint;
import io.github.jhipster.application.domain.BaseClass;
import io.github.jhipster.application.domain.BendHist;
import io.github.jhipster.application.domain.BuckleArrestorHist;
import io.github.jhipster.application.domain.PipeHist;
import io.github.jhipster.application.domain.PipejointHist;
import io.github.jhipster.application.domain.TeeHist;
import io.github.jhipster.application.domain.ValveHist;
import io.github.jhipster.application.repository.PipejointRepository;
import io.github.jhipster.application.service.PipejointService;
import io.github.jhipster.application.service.dto.PipejointDTO;
import io.github.jhipster.application.service.mapper.PipejointMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.PipejointCriteria;
import io.github.jhipster.application.service.PipejointQueryService;

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
 * Integration tests for the {@Link PipejointResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class PipejointResourceIT {

    private static final Instant DEFAULT_DATE_CREATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_CREATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_DATE_EDIT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_EDIT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CREATOR = "AAAAAAAAAA";
    private static final String UPDATED_CREATOR = "BBBBBBBBBB";

    private static final String DEFAULT_EDITOR = "AAAAAAAAAA";
    private static final String UPDATED_EDITOR = "BBBBBBBBBB";

    @Autowired
    private PipejointRepository pipejointRepository;

    @Autowired
    private PipejointMapper pipejointMapper;

    @Autowired
    private PipejointService pipejointService;

    @Autowired
    private PipejointQueryService pipejointQueryService;

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

    private MockMvc restPipejointMockMvc;

    private Pipejoint pipejoint;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PipejointResource pipejointResource = new PipejointResource(pipejointService, pipejointQueryService);
        this.restPipejointMockMvc = MockMvcBuilders.standaloneSetup(pipejointResource)
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
    public static Pipejoint createEntity(EntityManager em) {
        Pipejoint pipejoint = new Pipejoint()
            .dateCreate(DEFAULT_DATE_CREATE)
            .dateEdit(DEFAULT_DATE_EDIT)
            .creator(DEFAULT_CREATOR)
            .editor(DEFAULT_EDITOR);
        return pipejoint;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Pipejoint createUpdatedEntity(EntityManager em) {
        Pipejoint pipejoint = new Pipejoint()
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        return pipejoint;
    }

    @BeforeEach
    public void initTest() {
        pipejoint = createEntity(em);
    }

    @Test
    @Transactional
    public void createPipejoint() throws Exception {
        int databaseSizeBeforeCreate = pipejointRepository.findAll().size();

        // Create the Pipejoint
        PipejointDTO pipejointDTO = pipejointMapper.toDto(pipejoint);
        restPipejointMockMvc.perform(post("/api/pipejoints")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pipejointDTO)))
            .andExpect(status().isCreated());

        // Validate the Pipejoint in the database
        List<Pipejoint> pipejointList = pipejointRepository.findAll();
        assertThat(pipejointList).hasSize(databaseSizeBeforeCreate + 1);
        Pipejoint testPipejoint = pipejointList.get(pipejointList.size() - 1);
        assertThat(testPipejoint.getDateCreate()).isEqualTo(DEFAULT_DATE_CREATE);
        assertThat(testPipejoint.getDateEdit()).isEqualTo(DEFAULT_DATE_EDIT);
        assertThat(testPipejoint.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testPipejoint.getEditor()).isEqualTo(DEFAULT_EDITOR);
    }

    @Test
    @Transactional
    public void createPipejointWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = pipejointRepository.findAll().size();

        // Create the Pipejoint with an existing ID
        pipejoint.setId(1L);
        PipejointDTO pipejointDTO = pipejointMapper.toDto(pipejoint);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPipejointMockMvc.perform(post("/api/pipejoints")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pipejointDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Pipejoint in the database
        List<Pipejoint> pipejointList = pipejointRepository.findAll();
        assertThat(pipejointList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllPipejoints() throws Exception {
        // Initialize the database
        pipejointRepository.saveAndFlush(pipejoint);

        // Get all the pipejointList
        restPipejointMockMvc.perform(get("/api/pipejoints?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pipejoint.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateCreate").value(hasItem(DEFAULT_DATE_CREATE.toString())))
            .andExpect(jsonPath("$.[*].dateEdit").value(hasItem(DEFAULT_DATE_EDIT.toString())))
            .andExpect(jsonPath("$.[*].creator").value(hasItem(DEFAULT_CREATOR.toString())))
            .andExpect(jsonPath("$.[*].editor").value(hasItem(DEFAULT_EDITOR.toString())));
    }
    
    @Test
    @Transactional
    public void getPipejoint() throws Exception {
        // Initialize the database
        pipejointRepository.saveAndFlush(pipejoint);

        // Get the pipejoint
        restPipejointMockMvc.perform(get("/api/pipejoints/{id}", pipejoint.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(pipejoint.getId().intValue()))
            .andExpect(jsonPath("$.dateCreate").value(DEFAULT_DATE_CREATE.toString()))
            .andExpect(jsonPath("$.dateEdit").value(DEFAULT_DATE_EDIT.toString()))
            .andExpect(jsonPath("$.creator").value(DEFAULT_CREATOR.toString()))
            .andExpect(jsonPath("$.editor").value(DEFAULT_EDITOR.toString()));
    }

    @Test
    @Transactional
    public void getAllPipejointsByDateCreateIsEqualToSomething() throws Exception {
        // Initialize the database
        pipejointRepository.saveAndFlush(pipejoint);

        // Get all the pipejointList where dateCreate equals to DEFAULT_DATE_CREATE
        defaultPipejointShouldBeFound("dateCreate.equals=" + DEFAULT_DATE_CREATE);

        // Get all the pipejointList where dateCreate equals to UPDATED_DATE_CREATE
        defaultPipejointShouldNotBeFound("dateCreate.equals=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllPipejointsByDateCreateIsInShouldWork() throws Exception {
        // Initialize the database
        pipejointRepository.saveAndFlush(pipejoint);

        // Get all the pipejointList where dateCreate in DEFAULT_DATE_CREATE or UPDATED_DATE_CREATE
        defaultPipejointShouldBeFound("dateCreate.in=" + DEFAULT_DATE_CREATE + "," + UPDATED_DATE_CREATE);

        // Get all the pipejointList where dateCreate equals to UPDATED_DATE_CREATE
        defaultPipejointShouldNotBeFound("dateCreate.in=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllPipejointsByDateCreateIsNullOrNotNull() throws Exception {
        // Initialize the database
        pipejointRepository.saveAndFlush(pipejoint);

        // Get all the pipejointList where dateCreate is not null
        defaultPipejointShouldBeFound("dateCreate.specified=true");

        // Get all the pipejointList where dateCreate is null
        defaultPipejointShouldNotBeFound("dateCreate.specified=false");
    }

    @Test
    @Transactional
    public void getAllPipejointsByDateEditIsEqualToSomething() throws Exception {
        // Initialize the database
        pipejointRepository.saveAndFlush(pipejoint);

        // Get all the pipejointList where dateEdit equals to DEFAULT_DATE_EDIT
        defaultPipejointShouldBeFound("dateEdit.equals=" + DEFAULT_DATE_EDIT);

        // Get all the pipejointList where dateEdit equals to UPDATED_DATE_EDIT
        defaultPipejointShouldNotBeFound("dateEdit.equals=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllPipejointsByDateEditIsInShouldWork() throws Exception {
        // Initialize the database
        pipejointRepository.saveAndFlush(pipejoint);

        // Get all the pipejointList where dateEdit in DEFAULT_DATE_EDIT or UPDATED_DATE_EDIT
        defaultPipejointShouldBeFound("dateEdit.in=" + DEFAULT_DATE_EDIT + "," + UPDATED_DATE_EDIT);

        // Get all the pipejointList where dateEdit equals to UPDATED_DATE_EDIT
        defaultPipejointShouldNotBeFound("dateEdit.in=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllPipejointsByDateEditIsNullOrNotNull() throws Exception {
        // Initialize the database
        pipejointRepository.saveAndFlush(pipejoint);

        // Get all the pipejointList where dateEdit is not null
        defaultPipejointShouldBeFound("dateEdit.specified=true");

        // Get all the pipejointList where dateEdit is null
        defaultPipejointShouldNotBeFound("dateEdit.specified=false");
    }

    @Test
    @Transactional
    public void getAllPipejointsByCreatorIsEqualToSomething() throws Exception {
        // Initialize the database
        pipejointRepository.saveAndFlush(pipejoint);

        // Get all the pipejointList where creator equals to DEFAULT_CREATOR
        defaultPipejointShouldBeFound("creator.equals=" + DEFAULT_CREATOR);

        // Get all the pipejointList where creator equals to UPDATED_CREATOR
        defaultPipejointShouldNotBeFound("creator.equals=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllPipejointsByCreatorIsInShouldWork() throws Exception {
        // Initialize the database
        pipejointRepository.saveAndFlush(pipejoint);

        // Get all the pipejointList where creator in DEFAULT_CREATOR or UPDATED_CREATOR
        defaultPipejointShouldBeFound("creator.in=" + DEFAULT_CREATOR + "," + UPDATED_CREATOR);

        // Get all the pipejointList where creator equals to UPDATED_CREATOR
        defaultPipejointShouldNotBeFound("creator.in=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllPipejointsByCreatorIsNullOrNotNull() throws Exception {
        // Initialize the database
        pipejointRepository.saveAndFlush(pipejoint);

        // Get all the pipejointList where creator is not null
        defaultPipejointShouldBeFound("creator.specified=true");

        // Get all the pipejointList where creator is null
        defaultPipejointShouldNotBeFound("creator.specified=false");
    }

    @Test
    @Transactional
    public void getAllPipejointsByEditorIsEqualToSomething() throws Exception {
        // Initialize the database
        pipejointRepository.saveAndFlush(pipejoint);

        // Get all the pipejointList where editor equals to DEFAULT_EDITOR
        defaultPipejointShouldBeFound("editor.equals=" + DEFAULT_EDITOR);

        // Get all the pipejointList where editor equals to UPDATED_EDITOR
        defaultPipejointShouldNotBeFound("editor.equals=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllPipejointsByEditorIsInShouldWork() throws Exception {
        // Initialize the database
        pipejointRepository.saveAndFlush(pipejoint);

        // Get all the pipejointList where editor in DEFAULT_EDITOR or UPDATED_EDITOR
        defaultPipejointShouldBeFound("editor.in=" + DEFAULT_EDITOR + "," + UPDATED_EDITOR);

        // Get all the pipejointList where editor equals to UPDATED_EDITOR
        defaultPipejointShouldNotBeFound("editor.in=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllPipejointsByEditorIsNullOrNotNull() throws Exception {
        // Initialize the database
        pipejointRepository.saveAndFlush(pipejoint);

        // Get all the pipejointList where editor is not null
        defaultPipejointShouldBeFound("editor.specified=true");

        // Get all the pipejointList where editor is null
        defaultPipejointShouldNotBeFound("editor.specified=false");
    }

    @Test
    @Transactional
    public void getAllPipejointsByBaseClassIsEqualToSomething() throws Exception {
        // Initialize the database
        BaseClass baseClass = BaseClassResourceIT.createEntity(em);
        em.persist(baseClass);
        em.flush();
        pipejoint.setBaseClass(baseClass);
        pipejointRepository.saveAndFlush(pipejoint);
        Long baseClassId = baseClass.getId();

        // Get all the pipejointList where baseClass equals to baseClassId
        defaultPipejointShouldBeFound("baseClassId.equals=" + baseClassId);

        // Get all the pipejointList where baseClass equals to baseClassId + 1
        defaultPipejointShouldNotBeFound("baseClassId.equals=" + (baseClassId + 1));
    }


    @Test
    @Transactional
    public void getAllPipejointsByBendHistIsEqualToSomething() throws Exception {
        // Initialize the database
        BendHist bendHist = BendHistResourceIT.createEntity(em);
        em.persist(bendHist);
        em.flush();
        pipejoint.addBendHist(bendHist);
        pipejointRepository.saveAndFlush(pipejoint);
        Long bendHistId = bendHist.getId();

        // Get all the pipejointList where bendHist equals to bendHistId
        defaultPipejointShouldBeFound("bendHistId.equals=" + bendHistId);

        // Get all the pipejointList where bendHist equals to bendHistId + 1
        defaultPipejointShouldNotBeFound("bendHistId.equals=" + (bendHistId + 1));
    }


    @Test
    @Transactional
    public void getAllPipejointsByBuckleArrestorHistIsEqualToSomething() throws Exception {
        // Initialize the database
        BuckleArrestorHist buckleArrestorHist = BuckleArrestorHistResourceIT.createEntity(em);
        em.persist(buckleArrestorHist);
        em.flush();
        pipejoint.addBuckleArrestorHist(buckleArrestorHist);
        pipejointRepository.saveAndFlush(pipejoint);
        Long buckleArrestorHistId = buckleArrestorHist.getId();

        // Get all the pipejointList where buckleArrestorHist equals to buckleArrestorHistId
        defaultPipejointShouldBeFound("buckleArrestorHistId.equals=" + buckleArrestorHistId);

        // Get all the pipejointList where buckleArrestorHist equals to buckleArrestorHistId + 1
        defaultPipejointShouldNotBeFound("buckleArrestorHistId.equals=" + (buckleArrestorHistId + 1));
    }


    @Test
    @Transactional
    public void getAllPipejointsByPipeHistIsEqualToSomething() throws Exception {
        // Initialize the database
        PipeHist pipeHist = PipeHistResourceIT.createEntity(em);
        em.persist(pipeHist);
        em.flush();
        pipejoint.addPipeHist(pipeHist);
        pipejointRepository.saveAndFlush(pipejoint);
        Long pipeHistId = pipeHist.getId();

        // Get all the pipejointList where pipeHist equals to pipeHistId
        defaultPipejointShouldBeFound("pipeHistId.equals=" + pipeHistId);

        // Get all the pipejointList where pipeHist equals to pipeHistId + 1
        defaultPipejointShouldNotBeFound("pipeHistId.equals=" + (pipeHistId + 1));
    }


    @Test
    @Transactional
    public void getAllPipejointsByPipejointHistIsEqualToSomething() throws Exception {
        // Initialize the database
        PipejointHist pipejointHist = PipejointHistResourceIT.createEntity(em);
        em.persist(pipejointHist);
        em.flush();
        pipejoint.addPipejointHist(pipejointHist);
        pipejointRepository.saveAndFlush(pipejoint);
        Long pipejointHistId = pipejointHist.getId();

        // Get all the pipejointList where pipejointHist equals to pipejointHistId
        defaultPipejointShouldBeFound("pipejointHistId.equals=" + pipejointHistId);

        // Get all the pipejointList where pipejointHist equals to pipejointHistId + 1
        defaultPipejointShouldNotBeFound("pipejointHistId.equals=" + (pipejointHistId + 1));
    }


    @Test
    @Transactional
    public void getAllPipejointsByTeeHistIsEqualToSomething() throws Exception {
        // Initialize the database
        TeeHist teeHist = TeeHistResourceIT.createEntity(em);
        em.persist(teeHist);
        em.flush();
        pipejoint.addTeeHist(teeHist);
        pipejointRepository.saveAndFlush(pipejoint);
        Long teeHistId = teeHist.getId();

        // Get all the pipejointList where teeHist equals to teeHistId
        defaultPipejointShouldBeFound("teeHistId.equals=" + teeHistId);

        // Get all the pipejointList where teeHist equals to teeHistId + 1
        defaultPipejointShouldNotBeFound("teeHistId.equals=" + (teeHistId + 1));
    }


    @Test
    @Transactional
    public void getAllPipejointsByValveHistIsEqualToSomething() throws Exception {
        // Initialize the database
        ValveHist valveHist = ValveHistResourceIT.createEntity(em);
        em.persist(valveHist);
        em.flush();
        pipejoint.addValveHist(valveHist);
        pipejointRepository.saveAndFlush(pipejoint);
        Long valveHistId = valveHist.getId();

        // Get all the pipejointList where valveHist equals to valveHistId
        defaultPipejointShouldBeFound("valveHistId.equals=" + valveHistId);

        // Get all the pipejointList where valveHist equals to valveHistId + 1
        defaultPipejointShouldNotBeFound("valveHistId.equals=" + (valveHistId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultPipejointShouldBeFound(String filter) throws Exception {
        restPipejointMockMvc.perform(get("/api/pipejoints?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pipejoint.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateCreate").value(hasItem(DEFAULT_DATE_CREATE.toString())))
            .andExpect(jsonPath("$.[*].dateEdit").value(hasItem(DEFAULT_DATE_EDIT.toString())))
            .andExpect(jsonPath("$.[*].creator").value(hasItem(DEFAULT_CREATOR)))
            .andExpect(jsonPath("$.[*].editor").value(hasItem(DEFAULT_EDITOR)));

        // Check, that the count call also returns 1
        restPipejointMockMvc.perform(get("/api/pipejoints/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultPipejointShouldNotBeFound(String filter) throws Exception {
        restPipejointMockMvc.perform(get("/api/pipejoints?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restPipejointMockMvc.perform(get("/api/pipejoints/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingPipejoint() throws Exception {
        // Get the pipejoint
        restPipejointMockMvc.perform(get("/api/pipejoints/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePipejoint() throws Exception {
        // Initialize the database
        pipejointRepository.saveAndFlush(pipejoint);

        int databaseSizeBeforeUpdate = pipejointRepository.findAll().size();

        // Update the pipejoint
        Pipejoint updatedPipejoint = pipejointRepository.findById(pipejoint.getId()).get();
        // Disconnect from session so that the updates on updatedPipejoint are not directly saved in db
        em.detach(updatedPipejoint);
        updatedPipejoint
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        PipejointDTO pipejointDTO = pipejointMapper.toDto(updatedPipejoint);

        restPipejointMockMvc.perform(put("/api/pipejoints")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pipejointDTO)))
            .andExpect(status().isOk());

        // Validate the Pipejoint in the database
        List<Pipejoint> pipejointList = pipejointRepository.findAll();
        assertThat(pipejointList).hasSize(databaseSizeBeforeUpdate);
        Pipejoint testPipejoint = pipejointList.get(pipejointList.size() - 1);
        assertThat(testPipejoint.getDateCreate()).isEqualTo(UPDATED_DATE_CREATE);
        assertThat(testPipejoint.getDateEdit()).isEqualTo(UPDATED_DATE_EDIT);
        assertThat(testPipejoint.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testPipejoint.getEditor()).isEqualTo(UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void updateNonExistingPipejoint() throws Exception {
        int databaseSizeBeforeUpdate = pipejointRepository.findAll().size();

        // Create the Pipejoint
        PipejointDTO pipejointDTO = pipejointMapper.toDto(pipejoint);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPipejointMockMvc.perform(put("/api/pipejoints")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pipejointDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Pipejoint in the database
        List<Pipejoint> pipejointList = pipejointRepository.findAll();
        assertThat(pipejointList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePipejoint() throws Exception {
        // Initialize the database
        pipejointRepository.saveAndFlush(pipejoint);

        int databaseSizeBeforeDelete = pipejointRepository.findAll().size();

        // Delete the pipejoint
        restPipejointMockMvc.perform(delete("/api/pipejoints/{id}", pipejoint.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<Pipejoint> pipejointList = pipejointRepository.findAll();
        assertThat(pipejointList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Pipejoint.class);
        Pipejoint pipejoint1 = new Pipejoint();
        pipejoint1.setId(1L);
        Pipejoint pipejoint2 = new Pipejoint();
        pipejoint2.setId(pipejoint1.getId());
        assertThat(pipejoint1).isEqualTo(pipejoint2);
        pipejoint2.setId(2L);
        assertThat(pipejoint1).isNotEqualTo(pipejoint2);
        pipejoint1.setId(null);
        assertThat(pipejoint1).isNotEqualTo(pipejoint2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PipejointDTO.class);
        PipejointDTO pipejointDTO1 = new PipejointDTO();
        pipejointDTO1.setId(1L);
        PipejointDTO pipejointDTO2 = new PipejointDTO();
        assertThat(pipejointDTO1).isNotEqualTo(pipejointDTO2);
        pipejointDTO2.setId(pipejointDTO1.getId());
        assertThat(pipejointDTO1).isEqualTo(pipejointDTO2);
        pipejointDTO2.setId(2L);
        assertThat(pipejointDTO1).isNotEqualTo(pipejointDTO2);
        pipejointDTO1.setId(null);
        assertThat(pipejointDTO1).isNotEqualTo(pipejointDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(pipejointMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(pipejointMapper.fromId(null)).isNull();
    }
}
