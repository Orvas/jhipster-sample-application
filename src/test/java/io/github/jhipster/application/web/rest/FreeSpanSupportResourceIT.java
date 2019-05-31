package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.FreeSpanSupport;
import io.github.jhipster.application.domain.BaseClass;
import io.github.jhipster.application.domain.FreeSpanSupportHist;
import io.github.jhipster.application.repository.FreeSpanSupportRepository;
import io.github.jhipster.application.service.FreeSpanSupportService;
import io.github.jhipster.application.service.dto.FreeSpanSupportDTO;
import io.github.jhipster.application.service.mapper.FreeSpanSupportMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.FreeSpanSupportCriteria;
import io.github.jhipster.application.service.FreeSpanSupportQueryService;

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
 * Integration tests for the {@Link FreeSpanSupportResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class FreeSpanSupportResourceIT {

    private static final Instant DEFAULT_DATE_CREATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_CREATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_DATE_EDIT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_EDIT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CREATOR = "AAAAAAAAAA";
    private static final String UPDATED_CREATOR = "BBBBBBBBBB";

    private static final String DEFAULT_EDITOR = "AAAAAAAAAA";
    private static final String UPDATED_EDITOR = "BBBBBBBBBB";

    @Autowired
    private FreeSpanSupportRepository freeSpanSupportRepository;

    @Autowired
    private FreeSpanSupportMapper freeSpanSupportMapper;

    @Autowired
    private FreeSpanSupportService freeSpanSupportService;

    @Autowired
    private FreeSpanSupportQueryService freeSpanSupportQueryService;

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

    private MockMvc restFreeSpanSupportMockMvc;

    private FreeSpanSupport freeSpanSupport;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final FreeSpanSupportResource freeSpanSupportResource = new FreeSpanSupportResource(freeSpanSupportService, freeSpanSupportQueryService);
        this.restFreeSpanSupportMockMvc = MockMvcBuilders.standaloneSetup(freeSpanSupportResource)
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
    public static FreeSpanSupport createEntity(EntityManager em) {
        FreeSpanSupport freeSpanSupport = new FreeSpanSupport()
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
        freeSpanSupport.setId(baseClass);
        return freeSpanSupport;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FreeSpanSupport createUpdatedEntity(EntityManager em) {
        FreeSpanSupport freeSpanSupport = new FreeSpanSupport()
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
        freeSpanSupport.setId(baseClass);
        return freeSpanSupport;
    }

    @BeforeEach
    public void initTest() {
        freeSpanSupport = createEntity(em);
    }

    @Test
    @Transactional
    public void createFreeSpanSupport() throws Exception {
        int databaseSizeBeforeCreate = freeSpanSupportRepository.findAll().size();

        // Create the FreeSpanSupport
        FreeSpanSupportDTO freeSpanSupportDTO = freeSpanSupportMapper.toDto(freeSpanSupport);
        restFreeSpanSupportMockMvc.perform(post("/api/free-span-supports")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(freeSpanSupportDTO)))
            .andExpect(status().isCreated());

        // Validate the FreeSpanSupport in the database
        List<FreeSpanSupport> freeSpanSupportList = freeSpanSupportRepository.findAll();
        assertThat(freeSpanSupportList).hasSize(databaseSizeBeforeCreate + 1);
        FreeSpanSupport testFreeSpanSupport = freeSpanSupportList.get(freeSpanSupportList.size() - 1);
        assertThat(testFreeSpanSupport.getDateCreate()).isEqualTo(DEFAULT_DATE_CREATE);
        assertThat(testFreeSpanSupport.getDateEdit()).isEqualTo(DEFAULT_DATE_EDIT);
        assertThat(testFreeSpanSupport.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testFreeSpanSupport.getEditor()).isEqualTo(DEFAULT_EDITOR);
    }

    @Test
    @Transactional
    public void createFreeSpanSupportWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = freeSpanSupportRepository.findAll().size();

        // Create the FreeSpanSupport with an existing ID
        freeSpanSupport.setId(1L);
        FreeSpanSupportDTO freeSpanSupportDTO = freeSpanSupportMapper.toDto(freeSpanSupport);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFreeSpanSupportMockMvc.perform(post("/api/free-span-supports")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(freeSpanSupportDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FreeSpanSupport in the database
        List<FreeSpanSupport> freeSpanSupportList = freeSpanSupportRepository.findAll();
        assertThat(freeSpanSupportList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllFreeSpanSupports() throws Exception {
        // Initialize the database
        freeSpanSupportRepository.saveAndFlush(freeSpanSupport);

        // Get all the freeSpanSupportList
        restFreeSpanSupportMockMvc.perform(get("/api/free-span-supports?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(freeSpanSupport.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateCreate").value(hasItem(DEFAULT_DATE_CREATE.toString())))
            .andExpect(jsonPath("$.[*].dateEdit").value(hasItem(DEFAULT_DATE_EDIT.toString())))
            .andExpect(jsonPath("$.[*].creator").value(hasItem(DEFAULT_CREATOR.toString())))
            .andExpect(jsonPath("$.[*].editor").value(hasItem(DEFAULT_EDITOR.toString())));
    }
    
    @Test
    @Transactional
    public void getFreeSpanSupport() throws Exception {
        // Initialize the database
        freeSpanSupportRepository.saveAndFlush(freeSpanSupport);

        // Get the freeSpanSupport
        restFreeSpanSupportMockMvc.perform(get("/api/free-span-supports/{id}", freeSpanSupport.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(freeSpanSupport.getId().intValue()))
            .andExpect(jsonPath("$.dateCreate").value(DEFAULT_DATE_CREATE.toString()))
            .andExpect(jsonPath("$.dateEdit").value(DEFAULT_DATE_EDIT.toString()))
            .andExpect(jsonPath("$.creator").value(DEFAULT_CREATOR.toString()))
            .andExpect(jsonPath("$.editor").value(DEFAULT_EDITOR.toString()));
    }

    @Test
    @Transactional
    public void getAllFreeSpanSupportsByDateCreateIsEqualToSomething() throws Exception {
        // Initialize the database
        freeSpanSupportRepository.saveAndFlush(freeSpanSupport);

        // Get all the freeSpanSupportList where dateCreate equals to DEFAULT_DATE_CREATE
        defaultFreeSpanSupportShouldBeFound("dateCreate.equals=" + DEFAULT_DATE_CREATE);

        // Get all the freeSpanSupportList where dateCreate equals to UPDATED_DATE_CREATE
        defaultFreeSpanSupportShouldNotBeFound("dateCreate.equals=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllFreeSpanSupportsByDateCreateIsInShouldWork() throws Exception {
        // Initialize the database
        freeSpanSupportRepository.saveAndFlush(freeSpanSupport);

        // Get all the freeSpanSupportList where dateCreate in DEFAULT_DATE_CREATE or UPDATED_DATE_CREATE
        defaultFreeSpanSupportShouldBeFound("dateCreate.in=" + DEFAULT_DATE_CREATE + "," + UPDATED_DATE_CREATE);

        // Get all the freeSpanSupportList where dateCreate equals to UPDATED_DATE_CREATE
        defaultFreeSpanSupportShouldNotBeFound("dateCreate.in=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllFreeSpanSupportsByDateCreateIsNullOrNotNull() throws Exception {
        // Initialize the database
        freeSpanSupportRepository.saveAndFlush(freeSpanSupport);

        // Get all the freeSpanSupportList where dateCreate is not null
        defaultFreeSpanSupportShouldBeFound("dateCreate.specified=true");

        // Get all the freeSpanSupportList where dateCreate is null
        defaultFreeSpanSupportShouldNotBeFound("dateCreate.specified=false");
    }

    @Test
    @Transactional
    public void getAllFreeSpanSupportsByDateEditIsEqualToSomething() throws Exception {
        // Initialize the database
        freeSpanSupportRepository.saveAndFlush(freeSpanSupport);

        // Get all the freeSpanSupportList where dateEdit equals to DEFAULT_DATE_EDIT
        defaultFreeSpanSupportShouldBeFound("dateEdit.equals=" + DEFAULT_DATE_EDIT);

        // Get all the freeSpanSupportList where dateEdit equals to UPDATED_DATE_EDIT
        defaultFreeSpanSupportShouldNotBeFound("dateEdit.equals=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllFreeSpanSupportsByDateEditIsInShouldWork() throws Exception {
        // Initialize the database
        freeSpanSupportRepository.saveAndFlush(freeSpanSupport);

        // Get all the freeSpanSupportList where dateEdit in DEFAULT_DATE_EDIT or UPDATED_DATE_EDIT
        defaultFreeSpanSupportShouldBeFound("dateEdit.in=" + DEFAULT_DATE_EDIT + "," + UPDATED_DATE_EDIT);

        // Get all the freeSpanSupportList where dateEdit equals to UPDATED_DATE_EDIT
        defaultFreeSpanSupportShouldNotBeFound("dateEdit.in=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllFreeSpanSupportsByDateEditIsNullOrNotNull() throws Exception {
        // Initialize the database
        freeSpanSupportRepository.saveAndFlush(freeSpanSupport);

        // Get all the freeSpanSupportList where dateEdit is not null
        defaultFreeSpanSupportShouldBeFound("dateEdit.specified=true");

        // Get all the freeSpanSupportList where dateEdit is null
        defaultFreeSpanSupportShouldNotBeFound("dateEdit.specified=false");
    }

    @Test
    @Transactional
    public void getAllFreeSpanSupportsByCreatorIsEqualToSomething() throws Exception {
        // Initialize the database
        freeSpanSupportRepository.saveAndFlush(freeSpanSupport);

        // Get all the freeSpanSupportList where creator equals to DEFAULT_CREATOR
        defaultFreeSpanSupportShouldBeFound("creator.equals=" + DEFAULT_CREATOR);

        // Get all the freeSpanSupportList where creator equals to UPDATED_CREATOR
        defaultFreeSpanSupportShouldNotBeFound("creator.equals=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllFreeSpanSupportsByCreatorIsInShouldWork() throws Exception {
        // Initialize the database
        freeSpanSupportRepository.saveAndFlush(freeSpanSupport);

        // Get all the freeSpanSupportList where creator in DEFAULT_CREATOR or UPDATED_CREATOR
        defaultFreeSpanSupportShouldBeFound("creator.in=" + DEFAULT_CREATOR + "," + UPDATED_CREATOR);

        // Get all the freeSpanSupportList where creator equals to UPDATED_CREATOR
        defaultFreeSpanSupportShouldNotBeFound("creator.in=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllFreeSpanSupportsByCreatorIsNullOrNotNull() throws Exception {
        // Initialize the database
        freeSpanSupportRepository.saveAndFlush(freeSpanSupport);

        // Get all the freeSpanSupportList where creator is not null
        defaultFreeSpanSupportShouldBeFound("creator.specified=true");

        // Get all the freeSpanSupportList where creator is null
        defaultFreeSpanSupportShouldNotBeFound("creator.specified=false");
    }

    @Test
    @Transactional
    public void getAllFreeSpanSupportsByEditorIsEqualToSomething() throws Exception {
        // Initialize the database
        freeSpanSupportRepository.saveAndFlush(freeSpanSupport);

        // Get all the freeSpanSupportList where editor equals to DEFAULT_EDITOR
        defaultFreeSpanSupportShouldBeFound("editor.equals=" + DEFAULT_EDITOR);

        // Get all the freeSpanSupportList where editor equals to UPDATED_EDITOR
        defaultFreeSpanSupportShouldNotBeFound("editor.equals=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllFreeSpanSupportsByEditorIsInShouldWork() throws Exception {
        // Initialize the database
        freeSpanSupportRepository.saveAndFlush(freeSpanSupport);

        // Get all the freeSpanSupportList where editor in DEFAULT_EDITOR or UPDATED_EDITOR
        defaultFreeSpanSupportShouldBeFound("editor.in=" + DEFAULT_EDITOR + "," + UPDATED_EDITOR);

        // Get all the freeSpanSupportList where editor equals to UPDATED_EDITOR
        defaultFreeSpanSupportShouldNotBeFound("editor.in=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllFreeSpanSupportsByEditorIsNullOrNotNull() throws Exception {
        // Initialize the database
        freeSpanSupportRepository.saveAndFlush(freeSpanSupport);

        // Get all the freeSpanSupportList where editor is not null
        defaultFreeSpanSupportShouldBeFound("editor.specified=true");

        // Get all the freeSpanSupportList where editor is null
        defaultFreeSpanSupportShouldNotBeFound("editor.specified=false");
    }

    @Test
    @Transactional
    public void getAllFreeSpanSupportsByIdIsEqualToSomething() throws Exception {
        // Get already existing entity
        BaseClass id = freeSpanSupport.getId();
        freeSpanSupportRepository.saveAndFlush(freeSpanSupport);
        Long idId = id.getId();

        // Get all the freeSpanSupportList where id equals to idId
        defaultFreeSpanSupportShouldBeFound("idId.equals=" + idId);

        // Get all the freeSpanSupportList where id equals to idId + 1
        defaultFreeSpanSupportShouldNotBeFound("idId.equals=" + (idId + 1));
    }


    @Test
    @Transactional
    public void getAllFreeSpanSupportsByFreeSpanSupportHistIsEqualToSomething() throws Exception {
        // Initialize the database
        FreeSpanSupportHist freeSpanSupportHist = FreeSpanSupportHistResourceIT.createEntity(em);
        em.persist(freeSpanSupportHist);
        em.flush();
        freeSpanSupport.addFreeSpanSupportHist(freeSpanSupportHist);
        freeSpanSupportRepository.saveAndFlush(freeSpanSupport);
        Long freeSpanSupportHistId = freeSpanSupportHist.getId();

        // Get all the freeSpanSupportList where freeSpanSupportHist equals to freeSpanSupportHistId
        defaultFreeSpanSupportShouldBeFound("freeSpanSupportHistId.equals=" + freeSpanSupportHistId);

        // Get all the freeSpanSupportList where freeSpanSupportHist equals to freeSpanSupportHistId + 1
        defaultFreeSpanSupportShouldNotBeFound("freeSpanSupportHistId.equals=" + (freeSpanSupportHistId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultFreeSpanSupportShouldBeFound(String filter) throws Exception {
        restFreeSpanSupportMockMvc.perform(get("/api/free-span-supports?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(freeSpanSupport.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateCreate").value(hasItem(DEFAULT_DATE_CREATE.toString())))
            .andExpect(jsonPath("$.[*].dateEdit").value(hasItem(DEFAULT_DATE_EDIT.toString())))
            .andExpect(jsonPath("$.[*].creator").value(hasItem(DEFAULT_CREATOR)))
            .andExpect(jsonPath("$.[*].editor").value(hasItem(DEFAULT_EDITOR)));

        // Check, that the count call also returns 1
        restFreeSpanSupportMockMvc.perform(get("/api/free-span-supports/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultFreeSpanSupportShouldNotBeFound(String filter) throws Exception {
        restFreeSpanSupportMockMvc.perform(get("/api/free-span-supports?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restFreeSpanSupportMockMvc.perform(get("/api/free-span-supports/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingFreeSpanSupport() throws Exception {
        // Get the freeSpanSupport
        restFreeSpanSupportMockMvc.perform(get("/api/free-span-supports/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFreeSpanSupport() throws Exception {
        // Initialize the database
        freeSpanSupportRepository.saveAndFlush(freeSpanSupport);

        int databaseSizeBeforeUpdate = freeSpanSupportRepository.findAll().size();

        // Update the freeSpanSupport
        FreeSpanSupport updatedFreeSpanSupport = freeSpanSupportRepository.findById(freeSpanSupport.getId()).get();
        // Disconnect from session so that the updates on updatedFreeSpanSupport are not directly saved in db
        em.detach(updatedFreeSpanSupport);
        updatedFreeSpanSupport
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        FreeSpanSupportDTO freeSpanSupportDTO = freeSpanSupportMapper.toDto(updatedFreeSpanSupport);

        restFreeSpanSupportMockMvc.perform(put("/api/free-span-supports")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(freeSpanSupportDTO)))
            .andExpect(status().isOk());

        // Validate the FreeSpanSupport in the database
        List<FreeSpanSupport> freeSpanSupportList = freeSpanSupportRepository.findAll();
        assertThat(freeSpanSupportList).hasSize(databaseSizeBeforeUpdate);
        FreeSpanSupport testFreeSpanSupport = freeSpanSupportList.get(freeSpanSupportList.size() - 1);
        assertThat(testFreeSpanSupport.getDateCreate()).isEqualTo(UPDATED_DATE_CREATE);
        assertThat(testFreeSpanSupport.getDateEdit()).isEqualTo(UPDATED_DATE_EDIT);
        assertThat(testFreeSpanSupport.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testFreeSpanSupport.getEditor()).isEqualTo(UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void updateNonExistingFreeSpanSupport() throws Exception {
        int databaseSizeBeforeUpdate = freeSpanSupportRepository.findAll().size();

        // Create the FreeSpanSupport
        FreeSpanSupportDTO freeSpanSupportDTO = freeSpanSupportMapper.toDto(freeSpanSupport);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFreeSpanSupportMockMvc.perform(put("/api/free-span-supports")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(freeSpanSupportDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FreeSpanSupport in the database
        List<FreeSpanSupport> freeSpanSupportList = freeSpanSupportRepository.findAll();
        assertThat(freeSpanSupportList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFreeSpanSupport() throws Exception {
        // Initialize the database
        freeSpanSupportRepository.saveAndFlush(freeSpanSupport);

        int databaseSizeBeforeDelete = freeSpanSupportRepository.findAll().size();

        // Delete the freeSpanSupport
        restFreeSpanSupportMockMvc.perform(delete("/api/free-span-supports/{id}", freeSpanSupport.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<FreeSpanSupport> freeSpanSupportList = freeSpanSupportRepository.findAll();
        assertThat(freeSpanSupportList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FreeSpanSupport.class);
        FreeSpanSupport freeSpanSupport1 = new FreeSpanSupport();
        freeSpanSupport1.setId(1L);
        FreeSpanSupport freeSpanSupport2 = new FreeSpanSupport();
        freeSpanSupport2.setId(freeSpanSupport1.getId());
        assertThat(freeSpanSupport1).isEqualTo(freeSpanSupport2);
        freeSpanSupport2.setId(2L);
        assertThat(freeSpanSupport1).isNotEqualTo(freeSpanSupport2);
        freeSpanSupport1.setId(null);
        assertThat(freeSpanSupport1).isNotEqualTo(freeSpanSupport2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FreeSpanSupportDTO.class);
        FreeSpanSupportDTO freeSpanSupportDTO1 = new FreeSpanSupportDTO();
        freeSpanSupportDTO1.setId(1L);
        FreeSpanSupportDTO freeSpanSupportDTO2 = new FreeSpanSupportDTO();
        assertThat(freeSpanSupportDTO1).isNotEqualTo(freeSpanSupportDTO2);
        freeSpanSupportDTO2.setId(freeSpanSupportDTO1.getId());
        assertThat(freeSpanSupportDTO1).isEqualTo(freeSpanSupportDTO2);
        freeSpanSupportDTO2.setId(2L);
        assertThat(freeSpanSupportDTO1).isNotEqualTo(freeSpanSupportDTO2);
        freeSpanSupportDTO1.setId(null);
        assertThat(freeSpanSupportDTO1).isNotEqualTo(freeSpanSupportDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(freeSpanSupportMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(freeSpanSupportMapper.fromId(null)).isNull();
    }
}
