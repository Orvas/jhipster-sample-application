package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.FreeSpan;
import io.github.jhipster.application.domain.BaseClass;
import io.github.jhipster.application.domain.FreeSpanHist;
import io.github.jhipster.application.repository.FreeSpanRepository;
import io.github.jhipster.application.service.FreeSpanService;
import io.github.jhipster.application.service.dto.FreeSpanDTO;
import io.github.jhipster.application.service.mapper.FreeSpanMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.FreeSpanCriteria;
import io.github.jhipster.application.service.FreeSpanQueryService;

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
 * Integration tests for the {@Link FreeSpanResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class FreeSpanResourceIT {

    private static final Instant DEFAULT_DATE_CREATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_CREATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_DATE_EDIT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_EDIT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CREATOR = "AAAAAAAAAA";
    private static final String UPDATED_CREATOR = "BBBBBBBBBB";

    private static final String DEFAULT_EDITOR = "AAAAAAAAAA";
    private static final String UPDATED_EDITOR = "BBBBBBBBBB";

    @Autowired
    private FreeSpanRepository freeSpanRepository;

    @Autowired
    private FreeSpanMapper freeSpanMapper;

    @Autowired
    private FreeSpanService freeSpanService;

    @Autowired
    private FreeSpanQueryService freeSpanQueryService;

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

    private MockMvc restFreeSpanMockMvc;

    private FreeSpan freeSpan;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final FreeSpanResource freeSpanResource = new FreeSpanResource(freeSpanService, freeSpanQueryService);
        this.restFreeSpanMockMvc = MockMvcBuilders.standaloneSetup(freeSpanResource)
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
    public static FreeSpan createEntity(EntityManager em) {
        FreeSpan freeSpan = new FreeSpan()
            .dateCreate(DEFAULT_DATE_CREATE)
            .dateEdit(DEFAULT_DATE_EDIT)
            .creator(DEFAULT_CREATOR)
            .editor(DEFAULT_EDITOR);
        return freeSpan;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FreeSpan createUpdatedEntity(EntityManager em) {
        FreeSpan freeSpan = new FreeSpan()
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        return freeSpan;
    }

    @BeforeEach
    public void initTest() {
        freeSpan = createEntity(em);
    }

    @Test
    @Transactional
    public void createFreeSpan() throws Exception {
        int databaseSizeBeforeCreate = freeSpanRepository.findAll().size();

        // Create the FreeSpan
        FreeSpanDTO freeSpanDTO = freeSpanMapper.toDto(freeSpan);
        restFreeSpanMockMvc.perform(post("/api/free-spans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(freeSpanDTO)))
            .andExpect(status().isCreated());

        // Validate the FreeSpan in the database
        List<FreeSpan> freeSpanList = freeSpanRepository.findAll();
        assertThat(freeSpanList).hasSize(databaseSizeBeforeCreate + 1);
        FreeSpan testFreeSpan = freeSpanList.get(freeSpanList.size() - 1);
        assertThat(testFreeSpan.getDateCreate()).isEqualTo(DEFAULT_DATE_CREATE);
        assertThat(testFreeSpan.getDateEdit()).isEqualTo(DEFAULT_DATE_EDIT);
        assertThat(testFreeSpan.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testFreeSpan.getEditor()).isEqualTo(DEFAULT_EDITOR);
    }

    @Test
    @Transactional
    public void createFreeSpanWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = freeSpanRepository.findAll().size();

        // Create the FreeSpan with an existing ID
        freeSpan.setId(1L);
        FreeSpanDTO freeSpanDTO = freeSpanMapper.toDto(freeSpan);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFreeSpanMockMvc.perform(post("/api/free-spans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(freeSpanDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FreeSpan in the database
        List<FreeSpan> freeSpanList = freeSpanRepository.findAll();
        assertThat(freeSpanList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllFreeSpans() throws Exception {
        // Initialize the database
        freeSpanRepository.saveAndFlush(freeSpan);

        // Get all the freeSpanList
        restFreeSpanMockMvc.perform(get("/api/free-spans?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(freeSpan.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateCreate").value(hasItem(DEFAULT_DATE_CREATE.toString())))
            .andExpect(jsonPath("$.[*].dateEdit").value(hasItem(DEFAULT_DATE_EDIT.toString())))
            .andExpect(jsonPath("$.[*].creator").value(hasItem(DEFAULT_CREATOR.toString())))
            .andExpect(jsonPath("$.[*].editor").value(hasItem(DEFAULT_EDITOR.toString())));
    }
    
    @Test
    @Transactional
    public void getFreeSpan() throws Exception {
        // Initialize the database
        freeSpanRepository.saveAndFlush(freeSpan);

        // Get the freeSpan
        restFreeSpanMockMvc.perform(get("/api/free-spans/{id}", freeSpan.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(freeSpan.getId().intValue()))
            .andExpect(jsonPath("$.dateCreate").value(DEFAULT_DATE_CREATE.toString()))
            .andExpect(jsonPath("$.dateEdit").value(DEFAULT_DATE_EDIT.toString()))
            .andExpect(jsonPath("$.creator").value(DEFAULT_CREATOR.toString()))
            .andExpect(jsonPath("$.editor").value(DEFAULT_EDITOR.toString()));
    }

    @Test
    @Transactional
    public void getAllFreeSpansByDateCreateIsEqualToSomething() throws Exception {
        // Initialize the database
        freeSpanRepository.saveAndFlush(freeSpan);

        // Get all the freeSpanList where dateCreate equals to DEFAULT_DATE_CREATE
        defaultFreeSpanShouldBeFound("dateCreate.equals=" + DEFAULT_DATE_CREATE);

        // Get all the freeSpanList where dateCreate equals to UPDATED_DATE_CREATE
        defaultFreeSpanShouldNotBeFound("dateCreate.equals=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllFreeSpansByDateCreateIsInShouldWork() throws Exception {
        // Initialize the database
        freeSpanRepository.saveAndFlush(freeSpan);

        // Get all the freeSpanList where dateCreate in DEFAULT_DATE_CREATE or UPDATED_DATE_CREATE
        defaultFreeSpanShouldBeFound("dateCreate.in=" + DEFAULT_DATE_CREATE + "," + UPDATED_DATE_CREATE);

        // Get all the freeSpanList where dateCreate equals to UPDATED_DATE_CREATE
        defaultFreeSpanShouldNotBeFound("dateCreate.in=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllFreeSpansByDateCreateIsNullOrNotNull() throws Exception {
        // Initialize the database
        freeSpanRepository.saveAndFlush(freeSpan);

        // Get all the freeSpanList where dateCreate is not null
        defaultFreeSpanShouldBeFound("dateCreate.specified=true");

        // Get all the freeSpanList where dateCreate is null
        defaultFreeSpanShouldNotBeFound("dateCreate.specified=false");
    }

    @Test
    @Transactional
    public void getAllFreeSpansByDateEditIsEqualToSomething() throws Exception {
        // Initialize the database
        freeSpanRepository.saveAndFlush(freeSpan);

        // Get all the freeSpanList where dateEdit equals to DEFAULT_DATE_EDIT
        defaultFreeSpanShouldBeFound("dateEdit.equals=" + DEFAULT_DATE_EDIT);

        // Get all the freeSpanList where dateEdit equals to UPDATED_DATE_EDIT
        defaultFreeSpanShouldNotBeFound("dateEdit.equals=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllFreeSpansByDateEditIsInShouldWork() throws Exception {
        // Initialize the database
        freeSpanRepository.saveAndFlush(freeSpan);

        // Get all the freeSpanList where dateEdit in DEFAULT_DATE_EDIT or UPDATED_DATE_EDIT
        defaultFreeSpanShouldBeFound("dateEdit.in=" + DEFAULT_DATE_EDIT + "," + UPDATED_DATE_EDIT);

        // Get all the freeSpanList where dateEdit equals to UPDATED_DATE_EDIT
        defaultFreeSpanShouldNotBeFound("dateEdit.in=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllFreeSpansByDateEditIsNullOrNotNull() throws Exception {
        // Initialize the database
        freeSpanRepository.saveAndFlush(freeSpan);

        // Get all the freeSpanList where dateEdit is not null
        defaultFreeSpanShouldBeFound("dateEdit.specified=true");

        // Get all the freeSpanList where dateEdit is null
        defaultFreeSpanShouldNotBeFound("dateEdit.specified=false");
    }

    @Test
    @Transactional
    public void getAllFreeSpansByCreatorIsEqualToSomething() throws Exception {
        // Initialize the database
        freeSpanRepository.saveAndFlush(freeSpan);

        // Get all the freeSpanList where creator equals to DEFAULT_CREATOR
        defaultFreeSpanShouldBeFound("creator.equals=" + DEFAULT_CREATOR);

        // Get all the freeSpanList where creator equals to UPDATED_CREATOR
        defaultFreeSpanShouldNotBeFound("creator.equals=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllFreeSpansByCreatorIsInShouldWork() throws Exception {
        // Initialize the database
        freeSpanRepository.saveAndFlush(freeSpan);

        // Get all the freeSpanList where creator in DEFAULT_CREATOR or UPDATED_CREATOR
        defaultFreeSpanShouldBeFound("creator.in=" + DEFAULT_CREATOR + "," + UPDATED_CREATOR);

        // Get all the freeSpanList where creator equals to UPDATED_CREATOR
        defaultFreeSpanShouldNotBeFound("creator.in=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllFreeSpansByCreatorIsNullOrNotNull() throws Exception {
        // Initialize the database
        freeSpanRepository.saveAndFlush(freeSpan);

        // Get all the freeSpanList where creator is not null
        defaultFreeSpanShouldBeFound("creator.specified=true");

        // Get all the freeSpanList where creator is null
        defaultFreeSpanShouldNotBeFound("creator.specified=false");
    }

    @Test
    @Transactional
    public void getAllFreeSpansByEditorIsEqualToSomething() throws Exception {
        // Initialize the database
        freeSpanRepository.saveAndFlush(freeSpan);

        // Get all the freeSpanList where editor equals to DEFAULT_EDITOR
        defaultFreeSpanShouldBeFound("editor.equals=" + DEFAULT_EDITOR);

        // Get all the freeSpanList where editor equals to UPDATED_EDITOR
        defaultFreeSpanShouldNotBeFound("editor.equals=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllFreeSpansByEditorIsInShouldWork() throws Exception {
        // Initialize the database
        freeSpanRepository.saveAndFlush(freeSpan);

        // Get all the freeSpanList where editor in DEFAULT_EDITOR or UPDATED_EDITOR
        defaultFreeSpanShouldBeFound("editor.in=" + DEFAULT_EDITOR + "," + UPDATED_EDITOR);

        // Get all the freeSpanList where editor equals to UPDATED_EDITOR
        defaultFreeSpanShouldNotBeFound("editor.in=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllFreeSpansByEditorIsNullOrNotNull() throws Exception {
        // Initialize the database
        freeSpanRepository.saveAndFlush(freeSpan);

        // Get all the freeSpanList where editor is not null
        defaultFreeSpanShouldBeFound("editor.specified=true");

        // Get all the freeSpanList where editor is null
        defaultFreeSpanShouldNotBeFound("editor.specified=false");
    }

    @Test
    @Transactional
    public void getAllFreeSpansByBaseClassIsEqualToSomething() throws Exception {
        // Initialize the database
        BaseClass baseClass = BaseClassResourceIT.createEntity(em);
        em.persist(baseClass);
        em.flush();
        freeSpan.setBaseClass(baseClass);
        freeSpanRepository.saveAndFlush(freeSpan);
        Long baseClassId = baseClass.getId();

        // Get all the freeSpanList where baseClass equals to baseClassId
        defaultFreeSpanShouldBeFound("baseClassId.equals=" + baseClassId);

        // Get all the freeSpanList where baseClass equals to baseClassId + 1
        defaultFreeSpanShouldNotBeFound("baseClassId.equals=" + (baseClassId + 1));
    }


    @Test
    @Transactional
    public void getAllFreeSpansByFreeSpanHistIsEqualToSomething() throws Exception {
        // Initialize the database
        FreeSpanHist freeSpanHist = FreeSpanHistResourceIT.createEntity(em);
        em.persist(freeSpanHist);
        em.flush();
        freeSpan.setFreeSpanHist(freeSpanHist);
        freeSpanHist.setFreeSpan(freeSpan);
        freeSpanRepository.saveAndFlush(freeSpan);
        Long freeSpanHistId = freeSpanHist.getId();

        // Get all the freeSpanList where freeSpanHist equals to freeSpanHistId
        defaultFreeSpanShouldBeFound("freeSpanHistId.equals=" + freeSpanHistId);

        // Get all the freeSpanList where freeSpanHist equals to freeSpanHistId + 1
        defaultFreeSpanShouldNotBeFound("freeSpanHistId.equals=" + (freeSpanHistId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultFreeSpanShouldBeFound(String filter) throws Exception {
        restFreeSpanMockMvc.perform(get("/api/free-spans?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(freeSpan.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateCreate").value(hasItem(DEFAULT_DATE_CREATE.toString())))
            .andExpect(jsonPath("$.[*].dateEdit").value(hasItem(DEFAULT_DATE_EDIT.toString())))
            .andExpect(jsonPath("$.[*].creator").value(hasItem(DEFAULT_CREATOR)))
            .andExpect(jsonPath("$.[*].editor").value(hasItem(DEFAULT_EDITOR)));

        // Check, that the count call also returns 1
        restFreeSpanMockMvc.perform(get("/api/free-spans/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultFreeSpanShouldNotBeFound(String filter) throws Exception {
        restFreeSpanMockMvc.perform(get("/api/free-spans?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restFreeSpanMockMvc.perform(get("/api/free-spans/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingFreeSpan() throws Exception {
        // Get the freeSpan
        restFreeSpanMockMvc.perform(get("/api/free-spans/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFreeSpan() throws Exception {
        // Initialize the database
        freeSpanRepository.saveAndFlush(freeSpan);

        int databaseSizeBeforeUpdate = freeSpanRepository.findAll().size();

        // Update the freeSpan
        FreeSpan updatedFreeSpan = freeSpanRepository.findById(freeSpan.getId()).get();
        // Disconnect from session so that the updates on updatedFreeSpan are not directly saved in db
        em.detach(updatedFreeSpan);
        updatedFreeSpan
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        FreeSpanDTO freeSpanDTO = freeSpanMapper.toDto(updatedFreeSpan);

        restFreeSpanMockMvc.perform(put("/api/free-spans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(freeSpanDTO)))
            .andExpect(status().isOk());

        // Validate the FreeSpan in the database
        List<FreeSpan> freeSpanList = freeSpanRepository.findAll();
        assertThat(freeSpanList).hasSize(databaseSizeBeforeUpdate);
        FreeSpan testFreeSpan = freeSpanList.get(freeSpanList.size() - 1);
        assertThat(testFreeSpan.getDateCreate()).isEqualTo(UPDATED_DATE_CREATE);
        assertThat(testFreeSpan.getDateEdit()).isEqualTo(UPDATED_DATE_EDIT);
        assertThat(testFreeSpan.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testFreeSpan.getEditor()).isEqualTo(UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void updateNonExistingFreeSpan() throws Exception {
        int databaseSizeBeforeUpdate = freeSpanRepository.findAll().size();

        // Create the FreeSpan
        FreeSpanDTO freeSpanDTO = freeSpanMapper.toDto(freeSpan);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFreeSpanMockMvc.perform(put("/api/free-spans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(freeSpanDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FreeSpan in the database
        List<FreeSpan> freeSpanList = freeSpanRepository.findAll();
        assertThat(freeSpanList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFreeSpan() throws Exception {
        // Initialize the database
        freeSpanRepository.saveAndFlush(freeSpan);

        int databaseSizeBeforeDelete = freeSpanRepository.findAll().size();

        // Delete the freeSpan
        restFreeSpanMockMvc.perform(delete("/api/free-spans/{id}", freeSpan.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<FreeSpan> freeSpanList = freeSpanRepository.findAll();
        assertThat(freeSpanList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FreeSpan.class);
        FreeSpan freeSpan1 = new FreeSpan();
        freeSpan1.setId(1L);
        FreeSpan freeSpan2 = new FreeSpan();
        freeSpan2.setId(freeSpan1.getId());
        assertThat(freeSpan1).isEqualTo(freeSpan2);
        freeSpan2.setId(2L);
        assertThat(freeSpan1).isNotEqualTo(freeSpan2);
        freeSpan1.setId(null);
        assertThat(freeSpan1).isNotEqualTo(freeSpan2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FreeSpanDTO.class);
        FreeSpanDTO freeSpanDTO1 = new FreeSpanDTO();
        freeSpanDTO1.setId(1L);
        FreeSpanDTO freeSpanDTO2 = new FreeSpanDTO();
        assertThat(freeSpanDTO1).isNotEqualTo(freeSpanDTO2);
        freeSpanDTO2.setId(freeSpanDTO1.getId());
        assertThat(freeSpanDTO1).isEqualTo(freeSpanDTO2);
        freeSpanDTO2.setId(2L);
        assertThat(freeSpanDTO1).isNotEqualTo(freeSpanDTO2);
        freeSpanDTO1.setId(null);
        assertThat(freeSpanDTO1).isNotEqualTo(freeSpanDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(freeSpanMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(freeSpanMapper.fromId(null)).isNull();
    }
}
