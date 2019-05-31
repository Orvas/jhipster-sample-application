package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.BuckleArrestor;
import io.github.jhipster.application.domain.BaseClass;
import io.github.jhipster.application.domain.BuckleArrestorHist;
import io.github.jhipster.application.repository.BuckleArrestorRepository;
import io.github.jhipster.application.service.BuckleArrestorService;
import io.github.jhipster.application.service.dto.BuckleArrestorDTO;
import io.github.jhipster.application.service.mapper.BuckleArrestorMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.BuckleArrestorCriteria;
import io.github.jhipster.application.service.BuckleArrestorQueryService;

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
 * Integration tests for the {@Link BuckleArrestorResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class BuckleArrestorResourceIT {

    private static final Instant DEFAULT_DATE_CREATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_CREATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_DATE_EDIT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_EDIT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CREATOR = "AAAAAAAAAA";
    private static final String UPDATED_CREATOR = "BBBBBBBBBB";

    private static final String DEFAULT_EDITOR = "AAAAAAAAAA";
    private static final String UPDATED_EDITOR = "BBBBBBBBBB";

    @Autowired
    private BuckleArrestorRepository buckleArrestorRepository;

    @Autowired
    private BuckleArrestorMapper buckleArrestorMapper;

    @Autowired
    private BuckleArrestorService buckleArrestorService;

    @Autowired
    private BuckleArrestorQueryService buckleArrestorQueryService;

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

    private MockMvc restBuckleArrestorMockMvc;

    private BuckleArrestor buckleArrestor;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BuckleArrestorResource buckleArrestorResource = new BuckleArrestorResource(buckleArrestorService, buckleArrestorQueryService);
        this.restBuckleArrestorMockMvc = MockMvcBuilders.standaloneSetup(buckleArrestorResource)
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
    public static BuckleArrestor createEntity(EntityManager em) {
        BuckleArrestor buckleArrestor = new BuckleArrestor()
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
        buckleArrestor.setId(baseClass);
        return buckleArrestor;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BuckleArrestor createUpdatedEntity(EntityManager em) {
        BuckleArrestor buckleArrestor = new BuckleArrestor()
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
        buckleArrestor.setId(baseClass);
        return buckleArrestor;
    }

    @BeforeEach
    public void initTest() {
        buckleArrestor = createEntity(em);
    }

    @Test
    @Transactional
    public void createBuckleArrestor() throws Exception {
        int databaseSizeBeforeCreate = buckleArrestorRepository.findAll().size();

        // Create the BuckleArrestor
        BuckleArrestorDTO buckleArrestorDTO = buckleArrestorMapper.toDto(buckleArrestor);
        restBuckleArrestorMockMvc.perform(post("/api/buckle-arrestors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(buckleArrestorDTO)))
            .andExpect(status().isCreated());

        // Validate the BuckleArrestor in the database
        List<BuckleArrestor> buckleArrestorList = buckleArrestorRepository.findAll();
        assertThat(buckleArrestorList).hasSize(databaseSizeBeforeCreate + 1);
        BuckleArrestor testBuckleArrestor = buckleArrestorList.get(buckleArrestorList.size() - 1);
        assertThat(testBuckleArrestor.getDateCreate()).isEqualTo(DEFAULT_DATE_CREATE);
        assertThat(testBuckleArrestor.getDateEdit()).isEqualTo(DEFAULT_DATE_EDIT);
        assertThat(testBuckleArrestor.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testBuckleArrestor.getEditor()).isEqualTo(DEFAULT_EDITOR);
    }

    @Test
    @Transactional
    public void createBuckleArrestorWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = buckleArrestorRepository.findAll().size();

        // Create the BuckleArrestor with an existing ID
        buckleArrestor.setId(1L);
        BuckleArrestorDTO buckleArrestorDTO = buckleArrestorMapper.toDto(buckleArrestor);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBuckleArrestorMockMvc.perform(post("/api/buckle-arrestors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(buckleArrestorDTO)))
            .andExpect(status().isBadRequest());

        // Validate the BuckleArrestor in the database
        List<BuckleArrestor> buckleArrestorList = buckleArrestorRepository.findAll();
        assertThat(buckleArrestorList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllBuckleArrestors() throws Exception {
        // Initialize the database
        buckleArrestorRepository.saveAndFlush(buckleArrestor);

        // Get all the buckleArrestorList
        restBuckleArrestorMockMvc.perform(get("/api/buckle-arrestors?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(buckleArrestor.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateCreate").value(hasItem(DEFAULT_DATE_CREATE.toString())))
            .andExpect(jsonPath("$.[*].dateEdit").value(hasItem(DEFAULT_DATE_EDIT.toString())))
            .andExpect(jsonPath("$.[*].creator").value(hasItem(DEFAULT_CREATOR.toString())))
            .andExpect(jsonPath("$.[*].editor").value(hasItem(DEFAULT_EDITOR.toString())));
    }
    
    @Test
    @Transactional
    public void getBuckleArrestor() throws Exception {
        // Initialize the database
        buckleArrestorRepository.saveAndFlush(buckleArrestor);

        // Get the buckleArrestor
        restBuckleArrestorMockMvc.perform(get("/api/buckle-arrestors/{id}", buckleArrestor.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(buckleArrestor.getId().intValue()))
            .andExpect(jsonPath("$.dateCreate").value(DEFAULT_DATE_CREATE.toString()))
            .andExpect(jsonPath("$.dateEdit").value(DEFAULT_DATE_EDIT.toString()))
            .andExpect(jsonPath("$.creator").value(DEFAULT_CREATOR.toString()))
            .andExpect(jsonPath("$.editor").value(DEFAULT_EDITOR.toString()));
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorsByDateCreateIsEqualToSomething() throws Exception {
        // Initialize the database
        buckleArrestorRepository.saveAndFlush(buckleArrestor);

        // Get all the buckleArrestorList where dateCreate equals to DEFAULT_DATE_CREATE
        defaultBuckleArrestorShouldBeFound("dateCreate.equals=" + DEFAULT_DATE_CREATE);

        // Get all the buckleArrestorList where dateCreate equals to UPDATED_DATE_CREATE
        defaultBuckleArrestorShouldNotBeFound("dateCreate.equals=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorsByDateCreateIsInShouldWork() throws Exception {
        // Initialize the database
        buckleArrestorRepository.saveAndFlush(buckleArrestor);

        // Get all the buckleArrestorList where dateCreate in DEFAULT_DATE_CREATE or UPDATED_DATE_CREATE
        defaultBuckleArrestorShouldBeFound("dateCreate.in=" + DEFAULT_DATE_CREATE + "," + UPDATED_DATE_CREATE);

        // Get all the buckleArrestorList where dateCreate equals to UPDATED_DATE_CREATE
        defaultBuckleArrestorShouldNotBeFound("dateCreate.in=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorsByDateCreateIsNullOrNotNull() throws Exception {
        // Initialize the database
        buckleArrestorRepository.saveAndFlush(buckleArrestor);

        // Get all the buckleArrestorList where dateCreate is not null
        defaultBuckleArrestorShouldBeFound("dateCreate.specified=true");

        // Get all the buckleArrestorList where dateCreate is null
        defaultBuckleArrestorShouldNotBeFound("dateCreate.specified=false");
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorsByDateEditIsEqualToSomething() throws Exception {
        // Initialize the database
        buckleArrestorRepository.saveAndFlush(buckleArrestor);

        // Get all the buckleArrestorList where dateEdit equals to DEFAULT_DATE_EDIT
        defaultBuckleArrestorShouldBeFound("dateEdit.equals=" + DEFAULT_DATE_EDIT);

        // Get all the buckleArrestorList where dateEdit equals to UPDATED_DATE_EDIT
        defaultBuckleArrestorShouldNotBeFound("dateEdit.equals=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorsByDateEditIsInShouldWork() throws Exception {
        // Initialize the database
        buckleArrestorRepository.saveAndFlush(buckleArrestor);

        // Get all the buckleArrestorList where dateEdit in DEFAULT_DATE_EDIT or UPDATED_DATE_EDIT
        defaultBuckleArrestorShouldBeFound("dateEdit.in=" + DEFAULT_DATE_EDIT + "," + UPDATED_DATE_EDIT);

        // Get all the buckleArrestorList where dateEdit equals to UPDATED_DATE_EDIT
        defaultBuckleArrestorShouldNotBeFound("dateEdit.in=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorsByDateEditIsNullOrNotNull() throws Exception {
        // Initialize the database
        buckleArrestorRepository.saveAndFlush(buckleArrestor);

        // Get all the buckleArrestorList where dateEdit is not null
        defaultBuckleArrestorShouldBeFound("dateEdit.specified=true");

        // Get all the buckleArrestorList where dateEdit is null
        defaultBuckleArrestorShouldNotBeFound("dateEdit.specified=false");
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorsByCreatorIsEqualToSomething() throws Exception {
        // Initialize the database
        buckleArrestorRepository.saveAndFlush(buckleArrestor);

        // Get all the buckleArrestorList where creator equals to DEFAULT_CREATOR
        defaultBuckleArrestorShouldBeFound("creator.equals=" + DEFAULT_CREATOR);

        // Get all the buckleArrestorList where creator equals to UPDATED_CREATOR
        defaultBuckleArrestorShouldNotBeFound("creator.equals=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorsByCreatorIsInShouldWork() throws Exception {
        // Initialize the database
        buckleArrestorRepository.saveAndFlush(buckleArrestor);

        // Get all the buckleArrestorList where creator in DEFAULT_CREATOR or UPDATED_CREATOR
        defaultBuckleArrestorShouldBeFound("creator.in=" + DEFAULT_CREATOR + "," + UPDATED_CREATOR);

        // Get all the buckleArrestorList where creator equals to UPDATED_CREATOR
        defaultBuckleArrestorShouldNotBeFound("creator.in=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorsByCreatorIsNullOrNotNull() throws Exception {
        // Initialize the database
        buckleArrestorRepository.saveAndFlush(buckleArrestor);

        // Get all the buckleArrestorList where creator is not null
        defaultBuckleArrestorShouldBeFound("creator.specified=true");

        // Get all the buckleArrestorList where creator is null
        defaultBuckleArrestorShouldNotBeFound("creator.specified=false");
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorsByEditorIsEqualToSomething() throws Exception {
        // Initialize the database
        buckleArrestorRepository.saveAndFlush(buckleArrestor);

        // Get all the buckleArrestorList where editor equals to DEFAULT_EDITOR
        defaultBuckleArrestorShouldBeFound("editor.equals=" + DEFAULT_EDITOR);

        // Get all the buckleArrestorList where editor equals to UPDATED_EDITOR
        defaultBuckleArrestorShouldNotBeFound("editor.equals=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorsByEditorIsInShouldWork() throws Exception {
        // Initialize the database
        buckleArrestorRepository.saveAndFlush(buckleArrestor);

        // Get all the buckleArrestorList where editor in DEFAULT_EDITOR or UPDATED_EDITOR
        defaultBuckleArrestorShouldBeFound("editor.in=" + DEFAULT_EDITOR + "," + UPDATED_EDITOR);

        // Get all the buckleArrestorList where editor equals to UPDATED_EDITOR
        defaultBuckleArrestorShouldNotBeFound("editor.in=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorsByEditorIsNullOrNotNull() throws Exception {
        // Initialize the database
        buckleArrestorRepository.saveAndFlush(buckleArrestor);

        // Get all the buckleArrestorList where editor is not null
        defaultBuckleArrestorShouldBeFound("editor.specified=true");

        // Get all the buckleArrestorList where editor is null
        defaultBuckleArrestorShouldNotBeFound("editor.specified=false");
    }

    @Test
    @Transactional
    public void getAllBuckleArrestorsByIdIsEqualToSomething() throws Exception {
        // Get already existing entity
        BaseClass id = buckleArrestor.getId();
        buckleArrestorRepository.saveAndFlush(buckleArrestor);
        Long idId = id.getId();

        // Get all the buckleArrestorList where id equals to idId
        defaultBuckleArrestorShouldBeFound("idId.equals=" + idId);

        // Get all the buckleArrestorList where id equals to idId + 1
        defaultBuckleArrestorShouldNotBeFound("idId.equals=" + (idId + 1));
    }


    @Test
    @Transactional
    public void getAllBuckleArrestorsByBuckleArrestorHistIsEqualToSomething() throws Exception {
        // Initialize the database
        BuckleArrestorHist buckleArrestorHist = BuckleArrestorHistResourceIT.createEntity(em);
        em.persist(buckleArrestorHist);
        em.flush();
        buckleArrestor.addBuckleArrestorHist(buckleArrestorHist);
        buckleArrestorRepository.saveAndFlush(buckleArrestor);
        Long buckleArrestorHistId = buckleArrestorHist.getId();

        // Get all the buckleArrestorList where buckleArrestorHist equals to buckleArrestorHistId
        defaultBuckleArrestorShouldBeFound("buckleArrestorHistId.equals=" + buckleArrestorHistId);

        // Get all the buckleArrestorList where buckleArrestorHist equals to buckleArrestorHistId + 1
        defaultBuckleArrestorShouldNotBeFound("buckleArrestorHistId.equals=" + (buckleArrestorHistId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultBuckleArrestorShouldBeFound(String filter) throws Exception {
        restBuckleArrestorMockMvc.perform(get("/api/buckle-arrestors?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(buckleArrestor.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateCreate").value(hasItem(DEFAULT_DATE_CREATE.toString())))
            .andExpect(jsonPath("$.[*].dateEdit").value(hasItem(DEFAULT_DATE_EDIT.toString())))
            .andExpect(jsonPath("$.[*].creator").value(hasItem(DEFAULT_CREATOR)))
            .andExpect(jsonPath("$.[*].editor").value(hasItem(DEFAULT_EDITOR)));

        // Check, that the count call also returns 1
        restBuckleArrestorMockMvc.perform(get("/api/buckle-arrestors/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultBuckleArrestorShouldNotBeFound(String filter) throws Exception {
        restBuckleArrestorMockMvc.perform(get("/api/buckle-arrestors?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restBuckleArrestorMockMvc.perform(get("/api/buckle-arrestors/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingBuckleArrestor() throws Exception {
        // Get the buckleArrestor
        restBuckleArrestorMockMvc.perform(get("/api/buckle-arrestors/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBuckleArrestor() throws Exception {
        // Initialize the database
        buckleArrestorRepository.saveAndFlush(buckleArrestor);

        int databaseSizeBeforeUpdate = buckleArrestorRepository.findAll().size();

        // Update the buckleArrestor
        BuckleArrestor updatedBuckleArrestor = buckleArrestorRepository.findById(buckleArrestor.getId()).get();
        // Disconnect from session so that the updates on updatedBuckleArrestor are not directly saved in db
        em.detach(updatedBuckleArrestor);
        updatedBuckleArrestor
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        BuckleArrestorDTO buckleArrestorDTO = buckleArrestorMapper.toDto(updatedBuckleArrestor);

        restBuckleArrestorMockMvc.perform(put("/api/buckle-arrestors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(buckleArrestorDTO)))
            .andExpect(status().isOk());

        // Validate the BuckleArrestor in the database
        List<BuckleArrestor> buckleArrestorList = buckleArrestorRepository.findAll();
        assertThat(buckleArrestorList).hasSize(databaseSizeBeforeUpdate);
        BuckleArrestor testBuckleArrestor = buckleArrestorList.get(buckleArrestorList.size() - 1);
        assertThat(testBuckleArrestor.getDateCreate()).isEqualTo(UPDATED_DATE_CREATE);
        assertThat(testBuckleArrestor.getDateEdit()).isEqualTo(UPDATED_DATE_EDIT);
        assertThat(testBuckleArrestor.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testBuckleArrestor.getEditor()).isEqualTo(UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void updateNonExistingBuckleArrestor() throws Exception {
        int databaseSizeBeforeUpdate = buckleArrestorRepository.findAll().size();

        // Create the BuckleArrestor
        BuckleArrestorDTO buckleArrestorDTO = buckleArrestorMapper.toDto(buckleArrestor);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBuckleArrestorMockMvc.perform(put("/api/buckle-arrestors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(buckleArrestorDTO)))
            .andExpect(status().isBadRequest());

        // Validate the BuckleArrestor in the database
        List<BuckleArrestor> buckleArrestorList = buckleArrestorRepository.findAll();
        assertThat(buckleArrestorList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBuckleArrestor() throws Exception {
        // Initialize the database
        buckleArrestorRepository.saveAndFlush(buckleArrestor);

        int databaseSizeBeforeDelete = buckleArrestorRepository.findAll().size();

        // Delete the buckleArrestor
        restBuckleArrestorMockMvc.perform(delete("/api/buckle-arrestors/{id}", buckleArrestor.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<BuckleArrestor> buckleArrestorList = buckleArrestorRepository.findAll();
        assertThat(buckleArrestorList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BuckleArrestor.class);
        BuckleArrestor buckleArrestor1 = new BuckleArrestor();
        buckleArrestor1.setId(1L);
        BuckleArrestor buckleArrestor2 = new BuckleArrestor();
        buckleArrestor2.setId(buckleArrestor1.getId());
        assertThat(buckleArrestor1).isEqualTo(buckleArrestor2);
        buckleArrestor2.setId(2L);
        assertThat(buckleArrestor1).isNotEqualTo(buckleArrestor2);
        buckleArrestor1.setId(null);
        assertThat(buckleArrestor1).isNotEqualTo(buckleArrestor2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BuckleArrestorDTO.class);
        BuckleArrestorDTO buckleArrestorDTO1 = new BuckleArrestorDTO();
        buckleArrestorDTO1.setId(1L);
        BuckleArrestorDTO buckleArrestorDTO2 = new BuckleArrestorDTO();
        assertThat(buckleArrestorDTO1).isNotEqualTo(buckleArrestorDTO2);
        buckleArrestorDTO2.setId(buckleArrestorDTO1.getId());
        assertThat(buckleArrestorDTO1).isEqualTo(buckleArrestorDTO2);
        buckleArrestorDTO2.setId(2L);
        assertThat(buckleArrestorDTO1).isNotEqualTo(buckleArrestorDTO2);
        buckleArrestorDTO1.setId(null);
        assertThat(buckleArrestorDTO1).isNotEqualTo(buckleArrestorDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(buckleArrestorMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(buckleArrestorMapper.fromId(null)).isNull();
    }
}
