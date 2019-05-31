package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.PipelineSection;
import io.github.jhipster.application.domain.BaseClass;
import io.github.jhipster.application.domain.Pipeline;
import io.github.jhipster.application.domain.ListSafetyClass;
import io.github.jhipster.application.domain.AnodeHist;
import io.github.jhipster.application.domain.BendHist;
import io.github.jhipster.application.domain.BuckleArrestorHist;
import io.github.jhipster.application.domain.CpsHist;
import io.github.jhipster.application.domain.CpsRange;
import io.github.jhipster.application.domain.DisplacementHist;
import io.github.jhipster.application.domain.FreeSpanHist;
import io.github.jhipster.application.domain.FreeSpanSupportHist;
import io.github.jhipster.application.domain.PipeHist;
import io.github.jhipster.application.domain.TeeHist;
import io.github.jhipster.application.domain.ValveHist;
import io.github.jhipster.application.repository.PipelineSectionRepository;
import io.github.jhipster.application.service.PipelineSectionService;
import io.github.jhipster.application.service.dto.PipelineSectionDTO;
import io.github.jhipster.application.service.mapper.PipelineSectionMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.PipelineSectionCriteria;
import io.github.jhipster.application.service.PipelineSectionQueryService;

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
import java.math.BigDecimal;
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

    private static final Integer DEFAULT_IS_ONSHORE = 1;
    private static final Integer UPDATED_IS_ONSHORE = 2;

    private static final BigDecimal DEFAULT_KP_START = new BigDecimal(1);
    private static final BigDecimal UPDATED_KP_START = new BigDecimal(2);

    private static final BigDecimal DEFAULT_KP_END = new BigDecimal(1);
    private static final BigDecimal UPDATED_KP_END = new BigDecimal(2);

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
    private PipelineSectionMapper pipelineSectionMapper;

    @Autowired
    private PipelineSectionService pipelineSectionService;

    @Autowired
    private PipelineSectionQueryService pipelineSectionQueryService;

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
        final PipelineSectionResource pipelineSectionResource = new PipelineSectionResource(pipelineSectionService, pipelineSectionQueryService);
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
            .isOnshore(DEFAULT_IS_ONSHORE)
            .kpStart(DEFAULT_KP_START)
            .kpEnd(DEFAULT_KP_END)
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
        pipelineSection.setId(baseClass);
        // Add required entity
        Pipeline pipeline;
        if (TestUtil.findAll(em, Pipeline.class).isEmpty()) {
            pipeline = PipelineResourceIT.createEntity(em);
            em.persist(pipeline);
            em.flush();
        } else {
            pipeline = TestUtil.findAll(em, Pipeline.class).get(0);
        }
        pipelineSection.setIdPipeline(pipeline);
        // Add required entity
        ListSafetyClass listSafetyClass;
        if (TestUtil.findAll(em, ListSafetyClass.class).isEmpty()) {
            listSafetyClass = ListSafetyClassResourceIT.createEntity(em);
            em.persist(listSafetyClass);
            em.flush();
        } else {
            listSafetyClass = TestUtil.findAll(em, ListSafetyClass.class).get(0);
        }
        pipelineSection.setIdSafetyClass(listSafetyClass);
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
            .isOnshore(UPDATED_IS_ONSHORE)
            .kpStart(UPDATED_KP_START)
            .kpEnd(UPDATED_KP_END)
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
        pipelineSection.setId(baseClass);
        // Add required entity
        Pipeline pipeline;
        if (TestUtil.findAll(em, Pipeline.class).isEmpty()) {
            pipeline = PipelineResourceIT.createUpdatedEntity(em);
            em.persist(pipeline);
            em.flush();
        } else {
            pipeline = TestUtil.findAll(em, Pipeline.class).get(0);
        }
        pipelineSection.setIdPipeline(pipeline);
        // Add required entity
        ListSafetyClass listSafetyClass;
        if (TestUtil.findAll(em, ListSafetyClass.class).isEmpty()) {
            listSafetyClass = ListSafetyClassResourceIT.createUpdatedEntity(em);
            em.persist(listSafetyClass);
            em.flush();
        } else {
            listSafetyClass = TestUtil.findAll(em, ListSafetyClass.class).get(0);
        }
        pipelineSection.setIdSafetyClass(listSafetyClass);
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
        PipelineSectionDTO pipelineSectionDTO = pipelineSectionMapper.toDto(pipelineSection);
        restPipelineSectionMockMvc.perform(post("/api/pipeline-sections")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pipelineSectionDTO)))
            .andExpect(status().isCreated());

        // Validate the PipelineSection in the database
        List<PipelineSection> pipelineSectionList = pipelineSectionRepository.findAll();
        assertThat(pipelineSectionList).hasSize(databaseSizeBeforeCreate + 1);
        PipelineSection testPipelineSection = pipelineSectionList.get(pipelineSectionList.size() - 1);
        assertThat(testPipelineSection.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testPipelineSection.getIsOnshore()).isEqualTo(DEFAULT_IS_ONSHORE);
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
        PipelineSectionDTO pipelineSectionDTO = pipelineSectionMapper.toDto(pipelineSection);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPipelineSectionMockMvc.perform(post("/api/pipeline-sections")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pipelineSectionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PipelineSection in the database
        List<PipelineSection> pipelineSectionList = pipelineSectionRepository.findAll();
        assertThat(pipelineSectionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = pipelineSectionRepository.findAll().size();
        // set the field null
        pipelineSection.setName(null);

        // Create the PipelineSection, which fails.
        PipelineSectionDTO pipelineSectionDTO = pipelineSectionMapper.toDto(pipelineSection);

        restPipelineSectionMockMvc.perform(post("/api/pipeline-sections")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pipelineSectionDTO)))
            .andExpect(status().isBadRequest());

        List<PipelineSection> pipelineSectionList = pipelineSectionRepository.findAll();
        assertThat(pipelineSectionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsOnshoreIsRequired() throws Exception {
        int databaseSizeBeforeTest = pipelineSectionRepository.findAll().size();
        // set the field null
        pipelineSection.setIsOnshore(null);

        // Create the PipelineSection, which fails.
        PipelineSectionDTO pipelineSectionDTO = pipelineSectionMapper.toDto(pipelineSection);

        restPipelineSectionMockMvc.perform(post("/api/pipeline-sections")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pipelineSectionDTO)))
            .andExpect(status().isBadRequest());

        List<PipelineSection> pipelineSectionList = pipelineSectionRepository.findAll();
        assertThat(pipelineSectionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkKpStartIsRequired() throws Exception {
        int databaseSizeBeforeTest = pipelineSectionRepository.findAll().size();
        // set the field null
        pipelineSection.setKpStart(null);

        // Create the PipelineSection, which fails.
        PipelineSectionDTO pipelineSectionDTO = pipelineSectionMapper.toDto(pipelineSection);

        restPipelineSectionMockMvc.perform(post("/api/pipeline-sections")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pipelineSectionDTO)))
            .andExpect(status().isBadRequest());

        List<PipelineSection> pipelineSectionList = pipelineSectionRepository.findAll();
        assertThat(pipelineSectionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkKpEndIsRequired() throws Exception {
        int databaseSizeBeforeTest = pipelineSectionRepository.findAll().size();
        // set the field null
        pipelineSection.setKpEnd(null);

        // Create the PipelineSection, which fails.
        PipelineSectionDTO pipelineSectionDTO = pipelineSectionMapper.toDto(pipelineSection);

        restPipelineSectionMockMvc.perform(post("/api/pipeline-sections")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pipelineSectionDTO)))
            .andExpect(status().isBadRequest());

        List<PipelineSection> pipelineSectionList = pipelineSectionRepository.findAll();
        assertThat(pipelineSectionList).hasSize(databaseSizeBeforeTest);
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
            .andExpect(jsonPath("$.[*].isOnshore").value(hasItem(DEFAULT_IS_ONSHORE)))
            .andExpect(jsonPath("$.[*].kpStart").value(hasItem(DEFAULT_KP_START.intValue())))
            .andExpect(jsonPath("$.[*].kpEnd").value(hasItem(DEFAULT_KP_END.intValue())))
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
            .andExpect(jsonPath("$.isOnshore").value(DEFAULT_IS_ONSHORE))
            .andExpect(jsonPath("$.kpStart").value(DEFAULT_KP_START.intValue()))
            .andExpect(jsonPath("$.kpEnd").value(DEFAULT_KP_END.intValue()))
            .andExpect(jsonPath("$.dateCreate").value(DEFAULT_DATE_CREATE.toString()))
            .andExpect(jsonPath("$.dateEdit").value(DEFAULT_DATE_EDIT.toString()))
            .andExpect(jsonPath("$.creator").value(DEFAULT_CREATOR.toString()))
            .andExpect(jsonPath("$.editor").value(DEFAULT_EDITOR.toString()));
    }

    @Test
    @Transactional
    public void getAllPipelineSectionsByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        pipelineSectionRepository.saveAndFlush(pipelineSection);

        // Get all the pipelineSectionList where name equals to DEFAULT_NAME
        defaultPipelineSectionShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the pipelineSectionList where name equals to UPDATED_NAME
        defaultPipelineSectionShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllPipelineSectionsByNameIsInShouldWork() throws Exception {
        // Initialize the database
        pipelineSectionRepository.saveAndFlush(pipelineSection);

        // Get all the pipelineSectionList where name in DEFAULT_NAME or UPDATED_NAME
        defaultPipelineSectionShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the pipelineSectionList where name equals to UPDATED_NAME
        defaultPipelineSectionShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllPipelineSectionsByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        pipelineSectionRepository.saveAndFlush(pipelineSection);

        // Get all the pipelineSectionList where name is not null
        defaultPipelineSectionShouldBeFound("name.specified=true");

        // Get all the pipelineSectionList where name is null
        defaultPipelineSectionShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    public void getAllPipelineSectionsByIsOnshoreIsEqualToSomething() throws Exception {
        // Initialize the database
        pipelineSectionRepository.saveAndFlush(pipelineSection);

        // Get all the pipelineSectionList where isOnshore equals to DEFAULT_IS_ONSHORE
        defaultPipelineSectionShouldBeFound("isOnshore.equals=" + DEFAULT_IS_ONSHORE);

        // Get all the pipelineSectionList where isOnshore equals to UPDATED_IS_ONSHORE
        defaultPipelineSectionShouldNotBeFound("isOnshore.equals=" + UPDATED_IS_ONSHORE);
    }

    @Test
    @Transactional
    public void getAllPipelineSectionsByIsOnshoreIsInShouldWork() throws Exception {
        // Initialize the database
        pipelineSectionRepository.saveAndFlush(pipelineSection);

        // Get all the pipelineSectionList where isOnshore in DEFAULT_IS_ONSHORE or UPDATED_IS_ONSHORE
        defaultPipelineSectionShouldBeFound("isOnshore.in=" + DEFAULT_IS_ONSHORE + "," + UPDATED_IS_ONSHORE);

        // Get all the pipelineSectionList where isOnshore equals to UPDATED_IS_ONSHORE
        defaultPipelineSectionShouldNotBeFound("isOnshore.in=" + UPDATED_IS_ONSHORE);
    }

    @Test
    @Transactional
    public void getAllPipelineSectionsByIsOnshoreIsNullOrNotNull() throws Exception {
        // Initialize the database
        pipelineSectionRepository.saveAndFlush(pipelineSection);

        // Get all the pipelineSectionList where isOnshore is not null
        defaultPipelineSectionShouldBeFound("isOnshore.specified=true");

        // Get all the pipelineSectionList where isOnshore is null
        defaultPipelineSectionShouldNotBeFound("isOnshore.specified=false");
    }

    @Test
    @Transactional
    public void getAllPipelineSectionsByIsOnshoreIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        pipelineSectionRepository.saveAndFlush(pipelineSection);

        // Get all the pipelineSectionList where isOnshore greater than or equals to DEFAULT_IS_ONSHORE
        defaultPipelineSectionShouldBeFound("isOnshore.greaterOrEqualThan=" + DEFAULT_IS_ONSHORE);

        // Get all the pipelineSectionList where isOnshore greater than or equals to UPDATED_IS_ONSHORE
        defaultPipelineSectionShouldNotBeFound("isOnshore.greaterOrEqualThan=" + UPDATED_IS_ONSHORE);
    }

    @Test
    @Transactional
    public void getAllPipelineSectionsByIsOnshoreIsLessThanSomething() throws Exception {
        // Initialize the database
        pipelineSectionRepository.saveAndFlush(pipelineSection);

        // Get all the pipelineSectionList where isOnshore less than or equals to DEFAULT_IS_ONSHORE
        defaultPipelineSectionShouldNotBeFound("isOnshore.lessThan=" + DEFAULT_IS_ONSHORE);

        // Get all the pipelineSectionList where isOnshore less than or equals to UPDATED_IS_ONSHORE
        defaultPipelineSectionShouldBeFound("isOnshore.lessThan=" + UPDATED_IS_ONSHORE);
    }


    @Test
    @Transactional
    public void getAllPipelineSectionsByKpStartIsEqualToSomething() throws Exception {
        // Initialize the database
        pipelineSectionRepository.saveAndFlush(pipelineSection);

        // Get all the pipelineSectionList where kpStart equals to DEFAULT_KP_START
        defaultPipelineSectionShouldBeFound("kpStart.equals=" + DEFAULT_KP_START);

        // Get all the pipelineSectionList where kpStart equals to UPDATED_KP_START
        defaultPipelineSectionShouldNotBeFound("kpStart.equals=" + UPDATED_KP_START);
    }

    @Test
    @Transactional
    public void getAllPipelineSectionsByKpStartIsInShouldWork() throws Exception {
        // Initialize the database
        pipelineSectionRepository.saveAndFlush(pipelineSection);

        // Get all the pipelineSectionList where kpStart in DEFAULT_KP_START or UPDATED_KP_START
        defaultPipelineSectionShouldBeFound("kpStart.in=" + DEFAULT_KP_START + "," + UPDATED_KP_START);

        // Get all the pipelineSectionList where kpStart equals to UPDATED_KP_START
        defaultPipelineSectionShouldNotBeFound("kpStart.in=" + UPDATED_KP_START);
    }

    @Test
    @Transactional
    public void getAllPipelineSectionsByKpStartIsNullOrNotNull() throws Exception {
        // Initialize the database
        pipelineSectionRepository.saveAndFlush(pipelineSection);

        // Get all the pipelineSectionList where kpStart is not null
        defaultPipelineSectionShouldBeFound("kpStart.specified=true");

        // Get all the pipelineSectionList where kpStart is null
        defaultPipelineSectionShouldNotBeFound("kpStart.specified=false");
    }

    @Test
    @Transactional
    public void getAllPipelineSectionsByKpEndIsEqualToSomething() throws Exception {
        // Initialize the database
        pipelineSectionRepository.saveAndFlush(pipelineSection);

        // Get all the pipelineSectionList where kpEnd equals to DEFAULT_KP_END
        defaultPipelineSectionShouldBeFound("kpEnd.equals=" + DEFAULT_KP_END);

        // Get all the pipelineSectionList where kpEnd equals to UPDATED_KP_END
        defaultPipelineSectionShouldNotBeFound("kpEnd.equals=" + UPDATED_KP_END);
    }

    @Test
    @Transactional
    public void getAllPipelineSectionsByKpEndIsInShouldWork() throws Exception {
        // Initialize the database
        pipelineSectionRepository.saveAndFlush(pipelineSection);

        // Get all the pipelineSectionList where kpEnd in DEFAULT_KP_END or UPDATED_KP_END
        defaultPipelineSectionShouldBeFound("kpEnd.in=" + DEFAULT_KP_END + "," + UPDATED_KP_END);

        // Get all the pipelineSectionList where kpEnd equals to UPDATED_KP_END
        defaultPipelineSectionShouldNotBeFound("kpEnd.in=" + UPDATED_KP_END);
    }

    @Test
    @Transactional
    public void getAllPipelineSectionsByKpEndIsNullOrNotNull() throws Exception {
        // Initialize the database
        pipelineSectionRepository.saveAndFlush(pipelineSection);

        // Get all the pipelineSectionList where kpEnd is not null
        defaultPipelineSectionShouldBeFound("kpEnd.specified=true");

        // Get all the pipelineSectionList where kpEnd is null
        defaultPipelineSectionShouldNotBeFound("kpEnd.specified=false");
    }

    @Test
    @Transactional
    public void getAllPipelineSectionsByDateCreateIsEqualToSomething() throws Exception {
        // Initialize the database
        pipelineSectionRepository.saveAndFlush(pipelineSection);

        // Get all the pipelineSectionList where dateCreate equals to DEFAULT_DATE_CREATE
        defaultPipelineSectionShouldBeFound("dateCreate.equals=" + DEFAULT_DATE_CREATE);

        // Get all the pipelineSectionList where dateCreate equals to UPDATED_DATE_CREATE
        defaultPipelineSectionShouldNotBeFound("dateCreate.equals=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllPipelineSectionsByDateCreateIsInShouldWork() throws Exception {
        // Initialize the database
        pipelineSectionRepository.saveAndFlush(pipelineSection);

        // Get all the pipelineSectionList where dateCreate in DEFAULT_DATE_CREATE or UPDATED_DATE_CREATE
        defaultPipelineSectionShouldBeFound("dateCreate.in=" + DEFAULT_DATE_CREATE + "," + UPDATED_DATE_CREATE);

        // Get all the pipelineSectionList where dateCreate equals to UPDATED_DATE_CREATE
        defaultPipelineSectionShouldNotBeFound("dateCreate.in=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllPipelineSectionsByDateCreateIsNullOrNotNull() throws Exception {
        // Initialize the database
        pipelineSectionRepository.saveAndFlush(pipelineSection);

        // Get all the pipelineSectionList where dateCreate is not null
        defaultPipelineSectionShouldBeFound("dateCreate.specified=true");

        // Get all the pipelineSectionList where dateCreate is null
        defaultPipelineSectionShouldNotBeFound("dateCreate.specified=false");
    }

    @Test
    @Transactional
    public void getAllPipelineSectionsByDateEditIsEqualToSomething() throws Exception {
        // Initialize the database
        pipelineSectionRepository.saveAndFlush(pipelineSection);

        // Get all the pipelineSectionList where dateEdit equals to DEFAULT_DATE_EDIT
        defaultPipelineSectionShouldBeFound("dateEdit.equals=" + DEFAULT_DATE_EDIT);

        // Get all the pipelineSectionList where dateEdit equals to UPDATED_DATE_EDIT
        defaultPipelineSectionShouldNotBeFound("dateEdit.equals=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllPipelineSectionsByDateEditIsInShouldWork() throws Exception {
        // Initialize the database
        pipelineSectionRepository.saveAndFlush(pipelineSection);

        // Get all the pipelineSectionList where dateEdit in DEFAULT_DATE_EDIT or UPDATED_DATE_EDIT
        defaultPipelineSectionShouldBeFound("dateEdit.in=" + DEFAULT_DATE_EDIT + "," + UPDATED_DATE_EDIT);

        // Get all the pipelineSectionList where dateEdit equals to UPDATED_DATE_EDIT
        defaultPipelineSectionShouldNotBeFound("dateEdit.in=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllPipelineSectionsByDateEditIsNullOrNotNull() throws Exception {
        // Initialize the database
        pipelineSectionRepository.saveAndFlush(pipelineSection);

        // Get all the pipelineSectionList where dateEdit is not null
        defaultPipelineSectionShouldBeFound("dateEdit.specified=true");

        // Get all the pipelineSectionList where dateEdit is null
        defaultPipelineSectionShouldNotBeFound("dateEdit.specified=false");
    }

    @Test
    @Transactional
    public void getAllPipelineSectionsByCreatorIsEqualToSomething() throws Exception {
        // Initialize the database
        pipelineSectionRepository.saveAndFlush(pipelineSection);

        // Get all the pipelineSectionList where creator equals to DEFAULT_CREATOR
        defaultPipelineSectionShouldBeFound("creator.equals=" + DEFAULT_CREATOR);

        // Get all the pipelineSectionList where creator equals to UPDATED_CREATOR
        defaultPipelineSectionShouldNotBeFound("creator.equals=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllPipelineSectionsByCreatorIsInShouldWork() throws Exception {
        // Initialize the database
        pipelineSectionRepository.saveAndFlush(pipelineSection);

        // Get all the pipelineSectionList where creator in DEFAULT_CREATOR or UPDATED_CREATOR
        defaultPipelineSectionShouldBeFound("creator.in=" + DEFAULT_CREATOR + "," + UPDATED_CREATOR);

        // Get all the pipelineSectionList where creator equals to UPDATED_CREATOR
        defaultPipelineSectionShouldNotBeFound("creator.in=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllPipelineSectionsByCreatorIsNullOrNotNull() throws Exception {
        // Initialize the database
        pipelineSectionRepository.saveAndFlush(pipelineSection);

        // Get all the pipelineSectionList where creator is not null
        defaultPipelineSectionShouldBeFound("creator.specified=true");

        // Get all the pipelineSectionList where creator is null
        defaultPipelineSectionShouldNotBeFound("creator.specified=false");
    }

    @Test
    @Transactional
    public void getAllPipelineSectionsByEditorIsEqualToSomething() throws Exception {
        // Initialize the database
        pipelineSectionRepository.saveAndFlush(pipelineSection);

        // Get all the pipelineSectionList where editor equals to DEFAULT_EDITOR
        defaultPipelineSectionShouldBeFound("editor.equals=" + DEFAULT_EDITOR);

        // Get all the pipelineSectionList where editor equals to UPDATED_EDITOR
        defaultPipelineSectionShouldNotBeFound("editor.equals=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllPipelineSectionsByEditorIsInShouldWork() throws Exception {
        // Initialize the database
        pipelineSectionRepository.saveAndFlush(pipelineSection);

        // Get all the pipelineSectionList where editor in DEFAULT_EDITOR or UPDATED_EDITOR
        defaultPipelineSectionShouldBeFound("editor.in=" + DEFAULT_EDITOR + "," + UPDATED_EDITOR);

        // Get all the pipelineSectionList where editor equals to UPDATED_EDITOR
        defaultPipelineSectionShouldNotBeFound("editor.in=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllPipelineSectionsByEditorIsNullOrNotNull() throws Exception {
        // Initialize the database
        pipelineSectionRepository.saveAndFlush(pipelineSection);

        // Get all the pipelineSectionList where editor is not null
        defaultPipelineSectionShouldBeFound("editor.specified=true");

        // Get all the pipelineSectionList where editor is null
        defaultPipelineSectionShouldNotBeFound("editor.specified=false");
    }

    @Test
    @Transactional
    public void getAllPipelineSectionsByIdIsEqualToSomething() throws Exception {
        // Get already existing entity
        BaseClass id = pipelineSection.getId();
        pipelineSectionRepository.saveAndFlush(pipelineSection);
        Long idId = id.getId();

        // Get all the pipelineSectionList where id equals to idId
        defaultPipelineSectionShouldBeFound("idId.equals=" + idId);

        // Get all the pipelineSectionList where id equals to idId + 1
        defaultPipelineSectionShouldNotBeFound("idId.equals=" + (idId + 1));
    }


    @Test
    @Transactional
    public void getAllPipelineSectionsByIdPipelineIsEqualToSomething() throws Exception {
        // Get already existing entity
        Pipeline idPipeline = pipelineSection.getIdPipeline();
        pipelineSectionRepository.saveAndFlush(pipelineSection);
        Long idPipelineId = idPipeline.getId();

        // Get all the pipelineSectionList where idPipeline equals to idPipelineId
        defaultPipelineSectionShouldBeFound("idPipelineId.equals=" + idPipelineId);

        // Get all the pipelineSectionList where idPipeline equals to idPipelineId + 1
        defaultPipelineSectionShouldNotBeFound("idPipelineId.equals=" + (idPipelineId + 1));
    }


    @Test
    @Transactional
    public void getAllPipelineSectionsByIdSafetyClassIsEqualToSomething() throws Exception {
        // Get already existing entity
        ListSafetyClass idSafetyClass = pipelineSection.getIdSafetyClass();
        pipelineSectionRepository.saveAndFlush(pipelineSection);
        Long idSafetyClassId = idSafetyClass.getId();

        // Get all the pipelineSectionList where idSafetyClass equals to idSafetyClassId
        defaultPipelineSectionShouldBeFound("idSafetyClassId.equals=" + idSafetyClassId);

        // Get all the pipelineSectionList where idSafetyClass equals to idSafetyClassId + 1
        defaultPipelineSectionShouldNotBeFound("idSafetyClassId.equals=" + (idSafetyClassId + 1));
    }


    @Test
    @Transactional
    public void getAllPipelineSectionsByAnodeHistIsEqualToSomething() throws Exception {
        // Initialize the database
        AnodeHist anodeHist = AnodeHistResourceIT.createEntity(em);
        em.persist(anodeHist);
        em.flush();
        pipelineSection.addAnodeHist(anodeHist);
        pipelineSectionRepository.saveAndFlush(pipelineSection);
        Long anodeHistId = anodeHist.getId();

        // Get all the pipelineSectionList where anodeHist equals to anodeHistId
        defaultPipelineSectionShouldBeFound("anodeHistId.equals=" + anodeHistId);

        // Get all the pipelineSectionList where anodeHist equals to anodeHistId + 1
        defaultPipelineSectionShouldNotBeFound("anodeHistId.equals=" + (anodeHistId + 1));
    }


    @Test
    @Transactional
    public void getAllPipelineSectionsByBendHistIsEqualToSomething() throws Exception {
        // Initialize the database
        BendHist bendHist = BendHistResourceIT.createEntity(em);
        em.persist(bendHist);
        em.flush();
        pipelineSection.addBendHist(bendHist);
        pipelineSectionRepository.saveAndFlush(pipelineSection);
        Long bendHistId = bendHist.getId();

        // Get all the pipelineSectionList where bendHist equals to bendHistId
        defaultPipelineSectionShouldBeFound("bendHistId.equals=" + bendHistId);

        // Get all the pipelineSectionList where bendHist equals to bendHistId + 1
        defaultPipelineSectionShouldNotBeFound("bendHistId.equals=" + (bendHistId + 1));
    }


    @Test
    @Transactional
    public void getAllPipelineSectionsByBuckleArrestorHistIsEqualToSomething() throws Exception {
        // Initialize the database
        BuckleArrestorHist buckleArrestorHist = BuckleArrestorHistResourceIT.createEntity(em);
        em.persist(buckleArrestorHist);
        em.flush();
        pipelineSection.addBuckleArrestorHist(buckleArrestorHist);
        pipelineSectionRepository.saveAndFlush(pipelineSection);
        Long buckleArrestorHistId = buckleArrestorHist.getId();

        // Get all the pipelineSectionList where buckleArrestorHist equals to buckleArrestorHistId
        defaultPipelineSectionShouldBeFound("buckleArrestorHistId.equals=" + buckleArrestorHistId);

        // Get all the pipelineSectionList where buckleArrestorHist equals to buckleArrestorHistId + 1
        defaultPipelineSectionShouldNotBeFound("buckleArrestorHistId.equals=" + (buckleArrestorHistId + 1));
    }


    @Test
    @Transactional
    public void getAllPipelineSectionsByCpsHistIsEqualToSomething() throws Exception {
        // Initialize the database
        CpsHist cpsHist = CpsHistResourceIT.createEntity(em);
        em.persist(cpsHist);
        em.flush();
        pipelineSection.addCpsHist(cpsHist);
        pipelineSectionRepository.saveAndFlush(pipelineSection);
        Long cpsHistId = cpsHist.getId();

        // Get all the pipelineSectionList where cpsHist equals to cpsHistId
        defaultPipelineSectionShouldBeFound("cpsHistId.equals=" + cpsHistId);

        // Get all the pipelineSectionList where cpsHist equals to cpsHistId + 1
        defaultPipelineSectionShouldNotBeFound("cpsHistId.equals=" + (cpsHistId + 1));
    }


    @Test
    @Transactional
    public void getAllPipelineSectionsByCpsRangeIsEqualToSomething() throws Exception {
        // Initialize the database
        CpsRange cpsRange = CpsRangeResourceIT.createEntity(em);
        em.persist(cpsRange);
        em.flush();
        pipelineSection.addCpsRange(cpsRange);
        pipelineSectionRepository.saveAndFlush(pipelineSection);
        Long cpsRangeId = cpsRange.getId();

        // Get all the pipelineSectionList where cpsRange equals to cpsRangeId
        defaultPipelineSectionShouldBeFound("cpsRangeId.equals=" + cpsRangeId);

        // Get all the pipelineSectionList where cpsRange equals to cpsRangeId + 1
        defaultPipelineSectionShouldNotBeFound("cpsRangeId.equals=" + (cpsRangeId + 1));
    }


    @Test
    @Transactional
    public void getAllPipelineSectionsByDisplacementHistIsEqualToSomething() throws Exception {
        // Initialize the database
        DisplacementHist displacementHist = DisplacementHistResourceIT.createEntity(em);
        em.persist(displacementHist);
        em.flush();
        pipelineSection.addDisplacementHist(displacementHist);
        pipelineSectionRepository.saveAndFlush(pipelineSection);
        Long displacementHistId = displacementHist.getId();

        // Get all the pipelineSectionList where displacementHist equals to displacementHistId
        defaultPipelineSectionShouldBeFound("displacementHistId.equals=" + displacementHistId);

        // Get all the pipelineSectionList where displacementHist equals to displacementHistId + 1
        defaultPipelineSectionShouldNotBeFound("displacementHistId.equals=" + (displacementHistId + 1));
    }


    @Test
    @Transactional
    public void getAllPipelineSectionsByFreeSpanHistIsEqualToSomething() throws Exception {
        // Initialize the database
        FreeSpanHist freeSpanHist = FreeSpanHistResourceIT.createEntity(em);
        em.persist(freeSpanHist);
        em.flush();
        pipelineSection.addFreeSpanHist(freeSpanHist);
        pipelineSectionRepository.saveAndFlush(pipelineSection);
        Long freeSpanHistId = freeSpanHist.getId();

        // Get all the pipelineSectionList where freeSpanHist equals to freeSpanHistId
        defaultPipelineSectionShouldBeFound("freeSpanHistId.equals=" + freeSpanHistId);

        // Get all the pipelineSectionList where freeSpanHist equals to freeSpanHistId + 1
        defaultPipelineSectionShouldNotBeFound("freeSpanHistId.equals=" + (freeSpanHistId + 1));
    }


    @Test
    @Transactional
    public void getAllPipelineSectionsByFreeSpanSupportHistIsEqualToSomething() throws Exception {
        // Initialize the database
        FreeSpanSupportHist freeSpanSupportHist = FreeSpanSupportHistResourceIT.createEntity(em);
        em.persist(freeSpanSupportHist);
        em.flush();
        pipelineSection.addFreeSpanSupportHist(freeSpanSupportHist);
        pipelineSectionRepository.saveAndFlush(pipelineSection);
        Long freeSpanSupportHistId = freeSpanSupportHist.getId();

        // Get all the pipelineSectionList where freeSpanSupportHist equals to freeSpanSupportHistId
        defaultPipelineSectionShouldBeFound("freeSpanSupportHistId.equals=" + freeSpanSupportHistId);

        // Get all the pipelineSectionList where freeSpanSupportHist equals to freeSpanSupportHistId + 1
        defaultPipelineSectionShouldNotBeFound("freeSpanSupportHistId.equals=" + (freeSpanSupportHistId + 1));
    }


    @Test
    @Transactional
    public void getAllPipelineSectionsByPipeHistIsEqualToSomething() throws Exception {
        // Initialize the database
        PipeHist pipeHist = PipeHistResourceIT.createEntity(em);
        em.persist(pipeHist);
        em.flush();
        pipelineSection.addPipeHist(pipeHist);
        pipelineSectionRepository.saveAndFlush(pipelineSection);
        Long pipeHistId = pipeHist.getId();

        // Get all the pipelineSectionList where pipeHist equals to pipeHistId
        defaultPipelineSectionShouldBeFound("pipeHistId.equals=" + pipeHistId);

        // Get all the pipelineSectionList where pipeHist equals to pipeHistId + 1
        defaultPipelineSectionShouldNotBeFound("pipeHistId.equals=" + (pipeHistId + 1));
    }


    @Test
    @Transactional
    public void getAllPipelineSectionsByTeeHistIsEqualToSomething() throws Exception {
        // Initialize the database
        TeeHist teeHist = TeeHistResourceIT.createEntity(em);
        em.persist(teeHist);
        em.flush();
        pipelineSection.addTeeHist(teeHist);
        pipelineSectionRepository.saveAndFlush(pipelineSection);
        Long teeHistId = teeHist.getId();

        // Get all the pipelineSectionList where teeHist equals to teeHistId
        defaultPipelineSectionShouldBeFound("teeHistId.equals=" + teeHistId);

        // Get all the pipelineSectionList where teeHist equals to teeHistId + 1
        defaultPipelineSectionShouldNotBeFound("teeHistId.equals=" + (teeHistId + 1));
    }


    @Test
    @Transactional
    public void getAllPipelineSectionsByValveHistIsEqualToSomething() throws Exception {
        // Initialize the database
        ValveHist valveHist = ValveHistResourceIT.createEntity(em);
        em.persist(valveHist);
        em.flush();
        pipelineSection.addValveHist(valveHist);
        pipelineSectionRepository.saveAndFlush(pipelineSection);
        Long valveHistId = valveHist.getId();

        // Get all the pipelineSectionList where valveHist equals to valveHistId
        defaultPipelineSectionShouldBeFound("valveHistId.equals=" + valveHistId);

        // Get all the pipelineSectionList where valveHist equals to valveHistId + 1
        defaultPipelineSectionShouldNotBeFound("valveHistId.equals=" + (valveHistId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultPipelineSectionShouldBeFound(String filter) throws Exception {
        restPipelineSectionMockMvc.perform(get("/api/pipeline-sections?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pipelineSection.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].isOnshore").value(hasItem(DEFAULT_IS_ONSHORE)))
            .andExpect(jsonPath("$.[*].kpStart").value(hasItem(DEFAULT_KP_START.intValue())))
            .andExpect(jsonPath("$.[*].kpEnd").value(hasItem(DEFAULT_KP_END.intValue())))
            .andExpect(jsonPath("$.[*].dateCreate").value(hasItem(DEFAULT_DATE_CREATE.toString())))
            .andExpect(jsonPath("$.[*].dateEdit").value(hasItem(DEFAULT_DATE_EDIT.toString())))
            .andExpect(jsonPath("$.[*].creator").value(hasItem(DEFAULT_CREATOR)))
            .andExpect(jsonPath("$.[*].editor").value(hasItem(DEFAULT_EDITOR)));

        // Check, that the count call also returns 1
        restPipelineSectionMockMvc.perform(get("/api/pipeline-sections/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultPipelineSectionShouldNotBeFound(String filter) throws Exception {
        restPipelineSectionMockMvc.perform(get("/api/pipeline-sections?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restPipelineSectionMockMvc.perform(get("/api/pipeline-sections/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
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
            .isOnshore(UPDATED_IS_ONSHORE)
            .kpStart(UPDATED_KP_START)
            .kpEnd(UPDATED_KP_END)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        PipelineSectionDTO pipelineSectionDTO = pipelineSectionMapper.toDto(updatedPipelineSection);

        restPipelineSectionMockMvc.perform(put("/api/pipeline-sections")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pipelineSectionDTO)))
            .andExpect(status().isOk());

        // Validate the PipelineSection in the database
        List<PipelineSection> pipelineSectionList = pipelineSectionRepository.findAll();
        assertThat(pipelineSectionList).hasSize(databaseSizeBeforeUpdate);
        PipelineSection testPipelineSection = pipelineSectionList.get(pipelineSectionList.size() - 1);
        assertThat(testPipelineSection.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testPipelineSection.getIsOnshore()).isEqualTo(UPDATED_IS_ONSHORE);
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
        PipelineSectionDTO pipelineSectionDTO = pipelineSectionMapper.toDto(pipelineSection);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPipelineSectionMockMvc.perform(put("/api/pipeline-sections")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pipelineSectionDTO)))
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

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PipelineSectionDTO.class);
        PipelineSectionDTO pipelineSectionDTO1 = new PipelineSectionDTO();
        pipelineSectionDTO1.setId(1L);
        PipelineSectionDTO pipelineSectionDTO2 = new PipelineSectionDTO();
        assertThat(pipelineSectionDTO1).isNotEqualTo(pipelineSectionDTO2);
        pipelineSectionDTO2.setId(pipelineSectionDTO1.getId());
        assertThat(pipelineSectionDTO1).isEqualTo(pipelineSectionDTO2);
        pipelineSectionDTO2.setId(2L);
        assertThat(pipelineSectionDTO1).isNotEqualTo(pipelineSectionDTO2);
        pipelineSectionDTO1.setId(null);
        assertThat(pipelineSectionDTO1).isNotEqualTo(pipelineSectionDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(pipelineSectionMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(pipelineSectionMapper.fromId(null)).isNull();
    }
}
