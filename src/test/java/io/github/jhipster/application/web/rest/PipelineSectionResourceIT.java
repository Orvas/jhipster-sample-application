package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.PipelineSection;
import io.github.jhipster.application.repository.PipelineSectionRepository;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;

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
 * Integration tests for the {@Link PipelineSectionResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class PipelineSectionResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_PIPELINE_ID = 1L;
    private static final Long UPDATED_PIPELINE_ID = 2L;

    private static final Boolean DEFAULT_IS_ONSHORE = false;
    private static final Boolean UPDATED_IS_ONSHORE = true;

    private static final Long DEFAULT_SAFETY_CLASS_ID = 1L;
    private static final Long UPDATED_SAFETY_CLASS_ID = 2L;

    private static final Double DEFAULT_KP_START = 1D;
    private static final Double UPDATED_KP_START = 2D;

    private static final Double DEFAULT_KP_END = 1D;
    private static final Double UPDATED_KP_END = 2D;

    private static final Instant DEFAULT_DATE_CREATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_CREATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_DATE_EDIT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_EDIT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CREATOR = "AAAAAAAAAA";
    private static final String UPDATED_CREATOR = "BBBBBBBBBB";

    private static final String DEFAULT_EDITOR = "AAAAAAAAAA";
    private static final String UPDATED_EDITOR = "BBBBBBBBBB";

    @Autowired
    private PipelineSectionRepository pipelineSectionRepository;

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

    private MockMvc restPipelineSectionMockMvc;

    private PipelineSection pipelineSection;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PipelineSectionResource pipelineSectionResource = new PipelineSectionResource(pipelineSectionRepository);
        this.restPipelineSectionMockMvc = MockMvcBuilders.standaloneSetup(pipelineSectionResource)
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
    public static PipelineSection createEntity(EntityManager em) {
        PipelineSection pipelineSection = new PipelineSection()
            .name(DEFAULT_NAME)
            .pipelineId(DEFAULT_PIPELINE_ID)
            .isOnshore(DEFAULT_IS_ONSHORE)
            .safetyClassId(DEFAULT_SAFETY_CLASS_ID)
            .kpStart(DEFAULT_KP_START)
            .kpEnd(DEFAULT_KP_END)
            .dateCreate(DEFAULT_DATE_CREATE)
            .dateEdit(DEFAULT_DATE_EDIT)
            .creator(DEFAULT_CREATOR)
            .editor(DEFAULT_EDITOR);
        return pipelineSection;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PipelineSection createUpdatedEntity(EntityManager em) {
        PipelineSection pipelineSection = new PipelineSection()
            .name(UPDATED_NAME)
            .pipelineId(UPDATED_PIPELINE_ID)
            .isOnshore(UPDATED_IS_ONSHORE)
            .safetyClassId(UPDATED_SAFETY_CLASS_ID)
            .kpStart(UPDATED_KP_START)
            .kpEnd(UPDATED_KP_END)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        return pipelineSection;
    }

    @BeforeEach
    public void initTest() {
        pipelineSection = createEntity(em);
    }

    @Test
    @Transactional
    public void createPipelineSection() throws Exception {
        int databaseSizeBeforeCreate = pipelineSectionRepository.findAll().size();

        // Create the PipelineSection
        restPipelineSectionMockMvc.perform(post("/api/pipeline-sections")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pipelineSection)))
            .andExpect(status().isCreated());

        // Validate the PipelineSection in the database
        List<PipelineSection> pipelineSectionList = pipelineSectionRepository.findAll();
        assertThat(pipelineSectionList).hasSize(databaseSizeBeforeCreate + 1);
        PipelineSection testPipelineSection = pipelineSectionList.get(pipelineSectionList.size() - 1);
        assertThat(testPipelineSection.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testPipelineSection.getPipelineId()).isEqualTo(DEFAULT_PIPELINE_ID);
        assertThat(testPipelineSection.isIsOnshore()).isEqualTo(DEFAULT_IS_ONSHORE);
        assertThat(testPipelineSection.getSafetyClassId()).isEqualTo(DEFAULT_SAFETY_CLASS_ID);
        assertThat(testPipelineSection.getKpStart()).isEqualTo(DEFAULT_KP_START);
        assertThat(testPipelineSection.getKpEnd()).isEqualTo(DEFAULT_KP_END);
        assertThat(testPipelineSection.getDateCreate()).isEqualTo(DEFAULT_DATE_CREATE);
        assertThat(testPipelineSection.getDateEdit()).isEqualTo(DEFAULT_DATE_EDIT);
        assertThat(testPipelineSection.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testPipelineSection.getEditor()).isEqualTo(DEFAULT_EDITOR);
    }

    @Test
    @Transactional
    public void createPipelineSectionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = pipelineSectionRepository.findAll().size();

        // Create the PipelineSection with an existing ID
        pipelineSection.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPipelineSectionMockMvc.perform(post("/api/pipeline-sections")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pipelineSection)))
            .andExpect(status().isBadRequest());

        // Validate the PipelineSection in the database
        List<PipelineSection> pipelineSectionList = pipelineSectionRepository.findAll();
        assertThat(pipelineSectionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllPipelineSections() throws Exception {
        // Initialize the database
        pipelineSectionRepository.saveAndFlush(pipelineSection);

        // Get all the pipelineSectionList
        restPipelineSectionMockMvc.perform(get("/api/pipeline-sections?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pipelineSection.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].pipelineId").value(hasItem(DEFAULT_PIPELINE_ID.intValue())))
            .andExpect(jsonPath("$.[*].isOnshore").value(hasItem(DEFAULT_IS_ONSHORE.booleanValue())))
            .andExpect(jsonPath("$.[*].safetyClassId").value(hasItem(DEFAULT_SAFETY_CLASS_ID.intValue())))
            .andExpect(jsonPath("$.[*].kpStart").value(hasItem(DEFAULT_KP_START.doubleValue())))
            .andExpect(jsonPath("$.[*].kpEnd").value(hasItem(DEFAULT_KP_END.doubleValue())))
            .andExpect(jsonPath("$.[*].dateCreate").value(hasItem(DEFAULT_DATE_CREATE.toString())))
            .andExpect(jsonPath("$.[*].dateEdit").value(hasItem(DEFAULT_DATE_EDIT.toString())))
            .andExpect(jsonPath("$.[*].creator").value(hasItem(DEFAULT_CREATOR.toString())))
            .andExpect(jsonPath("$.[*].editor").value(hasItem(DEFAULT_EDITOR.toString())));
    }
    
    @Test
    @Transactional
    public void getPipelineSection() throws Exception {
        // Initialize the database
        pipelineSectionRepository.saveAndFlush(pipelineSection);

        // Get the pipelineSection
        restPipelineSectionMockMvc.perform(get("/api/pipeline-sections/{id}", pipelineSection.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(pipelineSection.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.pipelineId").value(DEFAULT_PIPELINE_ID.intValue()))
            .andExpect(jsonPath("$.isOnshore").value(DEFAULT_IS_ONSHORE.booleanValue()))
            .andExpect(jsonPath("$.safetyClassId").value(DEFAULT_SAFETY_CLASS_ID.intValue()))
            .andExpect(jsonPath("$.kpStart").value(DEFAULT_KP_START.doubleValue()))
            .andExpect(jsonPath("$.kpEnd").value(DEFAULT_KP_END.doubleValue()))
            .andExpect(jsonPath("$.dateCreate").value(DEFAULT_DATE_CREATE.toString()))
            .andExpect(jsonPath("$.dateEdit").value(DEFAULT_DATE_EDIT.toString()))
            .andExpect(jsonPath("$.creator").value(DEFAULT_CREATOR.toString()))
            .andExpect(jsonPath("$.editor").value(DEFAULT_EDITOR.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingPipelineSection() throws Exception {
        // Get the pipelineSection
        restPipelineSectionMockMvc.perform(get("/api/pipeline-sections/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePipelineSection() throws Exception {
        // Initialize the database
        pipelineSectionRepository.saveAndFlush(pipelineSection);

        int databaseSizeBeforeUpdate = pipelineSectionRepository.findAll().size();

        // Update the pipelineSection
        PipelineSection updatedPipelineSection = pipelineSectionRepository.findById(pipelineSection.getId()).get();
        // Disconnect from session so that the updates on updatedPipelineSection are not directly saved in db
        em.detach(updatedPipelineSection);
        updatedPipelineSection
            .name(UPDATED_NAME)
            .pipelineId(UPDATED_PIPELINE_ID)
            .isOnshore(UPDATED_IS_ONSHORE)
            .safetyClassId(UPDATED_SAFETY_CLASS_ID)
            .kpStart(UPDATED_KP_START)
            .kpEnd(UPDATED_KP_END)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);

        restPipelineSectionMockMvc.perform(put("/api/pipeline-sections")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedPipelineSection)))
            .andExpect(status().isOk());

        // Validate the PipelineSection in the database
        List<PipelineSection> pipelineSectionList = pipelineSectionRepository.findAll();
        assertThat(pipelineSectionList).hasSize(databaseSizeBeforeUpdate);
        PipelineSection testPipelineSection = pipelineSectionList.get(pipelineSectionList.size() - 1);
        assertThat(testPipelineSection.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testPipelineSection.getPipelineId()).isEqualTo(UPDATED_PIPELINE_ID);
        assertThat(testPipelineSection.isIsOnshore()).isEqualTo(UPDATED_IS_ONSHORE);
        assertThat(testPipelineSection.getSafetyClassId()).isEqualTo(UPDATED_SAFETY_CLASS_ID);
        assertThat(testPipelineSection.getKpStart()).isEqualTo(UPDATED_KP_START);
        assertThat(testPipelineSection.getKpEnd()).isEqualTo(UPDATED_KP_END);
        assertThat(testPipelineSection.getDateCreate()).isEqualTo(UPDATED_DATE_CREATE);
        assertThat(testPipelineSection.getDateEdit()).isEqualTo(UPDATED_DATE_EDIT);
        assertThat(testPipelineSection.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testPipelineSection.getEditor()).isEqualTo(UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void updateNonExistingPipelineSection() throws Exception {
        int databaseSizeBeforeUpdate = pipelineSectionRepository.findAll().size();

        // Create the PipelineSection

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPipelineSectionMockMvc.perform(put("/api/pipeline-sections")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pipelineSection)))
            .andExpect(status().isBadRequest());

        // Validate the PipelineSection in the database
        List<PipelineSection> pipelineSectionList = pipelineSectionRepository.findAll();
        assertThat(pipelineSectionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePipelineSection() throws Exception {
        // Initialize the database
        pipelineSectionRepository.saveAndFlush(pipelineSection);

        int databaseSizeBeforeDelete = pipelineSectionRepository.findAll().size();

        // Delete the pipelineSection
        restPipelineSectionMockMvc.perform(delete("/api/pipeline-sections/{id}", pipelineSection.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<PipelineSection> pipelineSectionList = pipelineSectionRepository.findAll();
        assertThat(pipelineSectionList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PipelineSection.class);
        PipelineSection pipelineSection1 = new PipelineSection();
        pipelineSection1.setId(1L);
        PipelineSection pipelineSection2 = new PipelineSection();
        pipelineSection2.setId(pipelineSection1.getId());
        assertThat(pipelineSection1).isEqualTo(pipelineSection2);
        pipelineSection2.setId(2L);
        assertThat(pipelineSection1).isNotEqualTo(pipelineSection2);
        pipelineSection1.setId(null);
        assertThat(pipelineSection1).isNotEqualTo(pipelineSection2);
    }
}
