package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.PipelineHist;
import io.github.jhipster.application.domain.Pipeline;
import io.github.jhipster.application.domain.ListPipelineLocation;
import io.github.jhipster.application.domain.ListObjectStatus;
import io.github.jhipster.application.repository.PipelineHistRepository;
import io.github.jhipster.application.service.PipelineHistService;
import io.github.jhipster.application.service.dto.PipelineHistDTO;
import io.github.jhipster.application.service.mapper.PipelineHistMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.PipelineHistCriteria;
import io.github.jhipster.application.service.PipelineHistQueryService;

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
import java.time.LocalDate;
import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@Link PipelineHistResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class PipelineHistResourceIT {

    private static final LocalDate DEFAULT_DATE_FROM = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_FROM = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATE_TO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_TO = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_DESIGN_LIFE = 1;
    private static final Integer UPDATED_DESIGN_LIFE = 2;

    private static final Integer DEFAULT_IS_CURRENT_FLAG = 1;
    private static final Integer UPDATED_IS_CURRENT_FLAG = 2;

    private static final String DEFAULT_COMMENT = "AAAAAAAAAA";
    private static final String UPDATED_COMMENT = "BBBBBBBBBB";

    private static final Instant DEFAULT_DATE_CREATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_CREATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_DATE_EDIT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_EDIT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CREATOR = "AAAAAAAAAA";
    private static final String UPDATED_CREATOR = "BBBBBBBBBB";

    private static final String DEFAULT_EDITOR = "AAAAAAAAAA";
    private static final String UPDATED_EDITOR = "BBBBBBBBBB";

    @Autowired
    private PipelineHistRepository pipelineHistRepository;

    @Autowired
    private PipelineHistMapper pipelineHistMapper;

    @Autowired
    private PipelineHistService pipelineHistService;

    @Autowired
    private PipelineHistQueryService pipelineHistQueryService;

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

    private MockMvc restPipelineHistMockMvc;

    private PipelineHist pipelineHist;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PipelineHistResource pipelineHistResource = new PipelineHistResource(pipelineHistService, pipelineHistQueryService);
        this.restPipelineHistMockMvc = MockMvcBuilders.standaloneSetup(pipelineHistResource)
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
    public static PipelineHist createEntity(EntityManager em) {
        PipelineHist pipelineHist = new PipelineHist()
            .dateFrom(DEFAULT_DATE_FROM)
            .dateTo(DEFAULT_DATE_TO)
            .name(DEFAULT_NAME)
            .designLife(DEFAULT_DESIGN_LIFE)
            .isCurrentFlag(DEFAULT_IS_CURRENT_FLAG)
            .comment(DEFAULT_COMMENT)
            .dateCreate(DEFAULT_DATE_CREATE)
            .dateEdit(DEFAULT_DATE_EDIT)
            .creator(DEFAULT_CREATOR)
            .editor(DEFAULT_EDITOR);
        // Add required entity
        Pipeline pipeline;
        if (TestUtil.findAll(em, Pipeline.class).isEmpty()) {
            pipeline = PipelineResourceIT.createEntity(em);
            em.persist(pipeline);
            em.flush();
        } else {
            pipeline = TestUtil.findAll(em, Pipeline.class).get(0);
        }
        pipelineHist.setId(pipeline);
        // Add required entity
        ListObjectStatus listObjectStatus;
        if (TestUtil.findAll(em, ListObjectStatus.class).isEmpty()) {
            listObjectStatus = ListObjectStatusResourceIT.createEntity(em);
            em.persist(listObjectStatus);
            em.flush();
        } else {
            listObjectStatus = TestUtil.findAll(em, ListObjectStatus.class).get(0);
        }
        pipelineHist.setIdStatus(listObjectStatus);
        return pipelineHist;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PipelineHist createUpdatedEntity(EntityManager em) {
        PipelineHist pipelineHist = new PipelineHist()
            .dateFrom(UPDATED_DATE_FROM)
            .dateTo(UPDATED_DATE_TO)
            .name(UPDATED_NAME)
            .designLife(UPDATED_DESIGN_LIFE)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .comment(UPDATED_COMMENT)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        // Add required entity
        Pipeline pipeline;
        if (TestUtil.findAll(em, Pipeline.class).isEmpty()) {
            pipeline = PipelineResourceIT.createUpdatedEntity(em);
            em.persist(pipeline);
            em.flush();
        } else {
            pipeline = TestUtil.findAll(em, Pipeline.class).get(0);
        }
        pipelineHist.setId(pipeline);
        // Add required entity
        ListObjectStatus listObjectStatus;
        if (TestUtil.findAll(em, ListObjectStatus.class).isEmpty()) {
            listObjectStatus = ListObjectStatusResourceIT.createUpdatedEntity(em);
            em.persist(listObjectStatus);
            em.flush();
        } else {
            listObjectStatus = TestUtil.findAll(em, ListObjectStatus.class).get(0);
        }
        pipelineHist.setIdStatus(listObjectStatus);
        return pipelineHist;
    }

    @BeforeEach
    public void initTest() {
        pipelineHist = createEntity(em);
    }

    @Test
    @Transactional
    public void createPipelineHist() throws Exception {
        int databaseSizeBeforeCreate = pipelineHistRepository.findAll().size();

        // Create the PipelineHist
        PipelineHistDTO pipelineHistDTO = pipelineHistMapper.toDto(pipelineHist);
        restPipelineHistMockMvc.perform(post("/api/pipeline-hists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pipelineHistDTO)))
            .andExpect(status().isCreated());

        // Validate the PipelineHist in the database
        List<PipelineHist> pipelineHistList = pipelineHistRepository.findAll();
        assertThat(pipelineHistList).hasSize(databaseSizeBeforeCreate + 1);
        PipelineHist testPipelineHist = pipelineHistList.get(pipelineHistList.size() - 1);
        assertThat(testPipelineHist.getDateFrom()).isEqualTo(DEFAULT_DATE_FROM);
        assertThat(testPipelineHist.getDateTo()).isEqualTo(DEFAULT_DATE_TO);
        assertThat(testPipelineHist.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testPipelineHist.getDesignLife()).isEqualTo(DEFAULT_DESIGN_LIFE);
        assertThat(testPipelineHist.getIsCurrentFlag()).isEqualTo(DEFAULT_IS_CURRENT_FLAG);
        assertThat(testPipelineHist.getComment()).isEqualTo(DEFAULT_COMMENT);
        assertThat(testPipelineHist.getDateCreate()).isEqualTo(DEFAULT_DATE_CREATE);
        assertThat(testPipelineHist.getDateEdit()).isEqualTo(DEFAULT_DATE_EDIT);
        assertThat(testPipelineHist.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testPipelineHist.getEditor()).isEqualTo(DEFAULT_EDITOR);
    }

    @Test
    @Transactional
    public void createPipelineHistWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = pipelineHistRepository.findAll().size();

        // Create the PipelineHist with an existing ID
        pipelineHist.setId(1L);
        PipelineHistDTO pipelineHistDTO = pipelineHistMapper.toDto(pipelineHist);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPipelineHistMockMvc.perform(post("/api/pipeline-hists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pipelineHistDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PipelineHist in the database
        List<PipelineHist> pipelineHistList = pipelineHistRepository.findAll();
        assertThat(pipelineHistList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkDateFromIsRequired() throws Exception {
        int databaseSizeBeforeTest = pipelineHistRepository.findAll().size();
        // set the field null
        pipelineHist.setDateFrom(null);

        // Create the PipelineHist, which fails.
        PipelineHistDTO pipelineHistDTO = pipelineHistMapper.toDto(pipelineHist);

        restPipelineHistMockMvc.perform(post("/api/pipeline-hists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pipelineHistDTO)))
            .andExpect(status().isBadRequest());

        List<PipelineHist> pipelineHistList = pipelineHistRepository.findAll();
        assertThat(pipelineHistList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDateToIsRequired() throws Exception {
        int databaseSizeBeforeTest = pipelineHistRepository.findAll().size();
        // set the field null
        pipelineHist.setDateTo(null);

        // Create the PipelineHist, which fails.
        PipelineHistDTO pipelineHistDTO = pipelineHistMapper.toDto(pipelineHist);

        restPipelineHistMockMvc.perform(post("/api/pipeline-hists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pipelineHistDTO)))
            .andExpect(status().isBadRequest());

        List<PipelineHist> pipelineHistList = pipelineHistRepository.findAll();
        assertThat(pipelineHistList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = pipelineHistRepository.findAll().size();
        // set the field null
        pipelineHist.setName(null);

        // Create the PipelineHist, which fails.
        PipelineHistDTO pipelineHistDTO = pipelineHistMapper.toDto(pipelineHist);

        restPipelineHistMockMvc.perform(post("/api/pipeline-hists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pipelineHistDTO)))
            .andExpect(status().isBadRequest());

        List<PipelineHist> pipelineHistList = pipelineHistRepository.findAll();
        assertThat(pipelineHistList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsCurrentFlagIsRequired() throws Exception {
        int databaseSizeBeforeTest = pipelineHistRepository.findAll().size();
        // set the field null
        pipelineHist.setIsCurrentFlag(null);

        // Create the PipelineHist, which fails.
        PipelineHistDTO pipelineHistDTO = pipelineHistMapper.toDto(pipelineHist);

        restPipelineHistMockMvc.perform(post("/api/pipeline-hists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pipelineHistDTO)))
            .andExpect(status().isBadRequest());

        List<PipelineHist> pipelineHistList = pipelineHistRepository.findAll();
        assertThat(pipelineHistList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPipelineHists() throws Exception {
        // Initialize the database
        pipelineHistRepository.saveAndFlush(pipelineHist);

        // Get all the pipelineHistList
        restPipelineHistMockMvc.perform(get("/api/pipeline-hists?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pipelineHist.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateFrom").value(hasItem(DEFAULT_DATE_FROM.toString())))
            .andExpect(jsonPath("$.[*].dateTo").value(hasItem(DEFAULT_DATE_TO.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].designLife").value(hasItem(DEFAULT_DESIGN_LIFE)))
            .andExpect(jsonPath("$.[*].isCurrentFlag").value(hasItem(DEFAULT_IS_CURRENT_FLAG)))
            .andExpect(jsonPath("$.[*].comment").value(hasItem(DEFAULT_COMMENT.toString())))
            .andExpect(jsonPath("$.[*].dateCreate").value(hasItem(DEFAULT_DATE_CREATE.toString())))
            .andExpect(jsonPath("$.[*].dateEdit").value(hasItem(DEFAULT_DATE_EDIT.toString())))
            .andExpect(jsonPath("$.[*].creator").value(hasItem(DEFAULT_CREATOR.toString())))
            .andExpect(jsonPath("$.[*].editor").value(hasItem(DEFAULT_EDITOR.toString())));
    }
    
    @Test
    @Transactional
    public void getPipelineHist() throws Exception {
        // Initialize the database
        pipelineHistRepository.saveAndFlush(pipelineHist);

        // Get the pipelineHist
        restPipelineHistMockMvc.perform(get("/api/pipeline-hists/{id}", pipelineHist.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(pipelineHist.getId().intValue()))
            .andExpect(jsonPath("$.dateFrom").value(DEFAULT_DATE_FROM.toString()))
            .andExpect(jsonPath("$.dateTo").value(DEFAULT_DATE_TO.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.designLife").value(DEFAULT_DESIGN_LIFE))
            .andExpect(jsonPath("$.isCurrentFlag").value(DEFAULT_IS_CURRENT_FLAG))
            .andExpect(jsonPath("$.comment").value(DEFAULT_COMMENT.toString()))
            .andExpect(jsonPath("$.dateCreate").value(DEFAULT_DATE_CREATE.toString()))
            .andExpect(jsonPath("$.dateEdit").value(DEFAULT_DATE_EDIT.toString()))
            .andExpect(jsonPath("$.creator").value(DEFAULT_CREATOR.toString()))
            .andExpect(jsonPath("$.editor").value(DEFAULT_EDITOR.toString()));
    }

    @Test
    @Transactional
    public void getAllPipelineHistsByDateFromIsEqualToSomething() throws Exception {
        // Initialize the database
        pipelineHistRepository.saveAndFlush(pipelineHist);

        // Get all the pipelineHistList where dateFrom equals to DEFAULT_DATE_FROM
        defaultPipelineHistShouldBeFound("dateFrom.equals=" + DEFAULT_DATE_FROM);

        // Get all the pipelineHistList where dateFrom equals to UPDATED_DATE_FROM
        defaultPipelineHistShouldNotBeFound("dateFrom.equals=" + UPDATED_DATE_FROM);
    }

    @Test
    @Transactional
    public void getAllPipelineHistsByDateFromIsInShouldWork() throws Exception {
        // Initialize the database
        pipelineHistRepository.saveAndFlush(pipelineHist);

        // Get all the pipelineHistList where dateFrom in DEFAULT_DATE_FROM or UPDATED_DATE_FROM
        defaultPipelineHistShouldBeFound("dateFrom.in=" + DEFAULT_DATE_FROM + "," + UPDATED_DATE_FROM);

        // Get all the pipelineHistList where dateFrom equals to UPDATED_DATE_FROM
        defaultPipelineHistShouldNotBeFound("dateFrom.in=" + UPDATED_DATE_FROM);
    }

    @Test
    @Transactional
    public void getAllPipelineHistsByDateFromIsNullOrNotNull() throws Exception {
        // Initialize the database
        pipelineHistRepository.saveAndFlush(pipelineHist);

        // Get all the pipelineHistList where dateFrom is not null
        defaultPipelineHistShouldBeFound("dateFrom.specified=true");

        // Get all the pipelineHistList where dateFrom is null
        defaultPipelineHistShouldNotBeFound("dateFrom.specified=false");
    }

    @Test
    @Transactional
    public void getAllPipelineHistsByDateFromIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        pipelineHistRepository.saveAndFlush(pipelineHist);

        // Get all the pipelineHistList where dateFrom greater than or equals to DEFAULT_DATE_FROM
        defaultPipelineHistShouldBeFound("dateFrom.greaterOrEqualThan=" + DEFAULT_DATE_FROM);

        // Get all the pipelineHistList where dateFrom greater than or equals to UPDATED_DATE_FROM
        defaultPipelineHistShouldNotBeFound("dateFrom.greaterOrEqualThan=" + UPDATED_DATE_FROM);
    }

    @Test
    @Transactional
    public void getAllPipelineHistsByDateFromIsLessThanSomething() throws Exception {
        // Initialize the database
        pipelineHistRepository.saveAndFlush(pipelineHist);

        // Get all the pipelineHistList where dateFrom less than or equals to DEFAULT_DATE_FROM
        defaultPipelineHistShouldNotBeFound("dateFrom.lessThan=" + DEFAULT_DATE_FROM);

        // Get all the pipelineHistList where dateFrom less than or equals to UPDATED_DATE_FROM
        defaultPipelineHistShouldBeFound("dateFrom.lessThan=" + UPDATED_DATE_FROM);
    }


    @Test
    @Transactional
    public void getAllPipelineHistsByDateToIsEqualToSomething() throws Exception {
        // Initialize the database
        pipelineHistRepository.saveAndFlush(pipelineHist);

        // Get all the pipelineHistList where dateTo equals to DEFAULT_DATE_TO
        defaultPipelineHistShouldBeFound("dateTo.equals=" + DEFAULT_DATE_TO);

        // Get all the pipelineHistList where dateTo equals to UPDATED_DATE_TO
        defaultPipelineHistShouldNotBeFound("dateTo.equals=" + UPDATED_DATE_TO);
    }

    @Test
    @Transactional
    public void getAllPipelineHistsByDateToIsInShouldWork() throws Exception {
        // Initialize the database
        pipelineHistRepository.saveAndFlush(pipelineHist);

        // Get all the pipelineHistList where dateTo in DEFAULT_DATE_TO or UPDATED_DATE_TO
        defaultPipelineHistShouldBeFound("dateTo.in=" + DEFAULT_DATE_TO + "," + UPDATED_DATE_TO);

        // Get all the pipelineHistList where dateTo equals to UPDATED_DATE_TO
        defaultPipelineHistShouldNotBeFound("dateTo.in=" + UPDATED_DATE_TO);
    }

    @Test
    @Transactional
    public void getAllPipelineHistsByDateToIsNullOrNotNull() throws Exception {
        // Initialize the database
        pipelineHistRepository.saveAndFlush(pipelineHist);

        // Get all the pipelineHistList where dateTo is not null
        defaultPipelineHistShouldBeFound("dateTo.specified=true");

        // Get all the pipelineHistList where dateTo is null
        defaultPipelineHistShouldNotBeFound("dateTo.specified=false");
    }

    @Test
    @Transactional
    public void getAllPipelineHistsByDateToIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        pipelineHistRepository.saveAndFlush(pipelineHist);

        // Get all the pipelineHistList where dateTo greater than or equals to DEFAULT_DATE_TO
        defaultPipelineHistShouldBeFound("dateTo.greaterOrEqualThan=" + DEFAULT_DATE_TO);

        // Get all the pipelineHistList where dateTo greater than or equals to UPDATED_DATE_TO
        defaultPipelineHistShouldNotBeFound("dateTo.greaterOrEqualThan=" + UPDATED_DATE_TO);
    }

    @Test
    @Transactional
    public void getAllPipelineHistsByDateToIsLessThanSomething() throws Exception {
        // Initialize the database
        pipelineHistRepository.saveAndFlush(pipelineHist);

        // Get all the pipelineHistList where dateTo less than or equals to DEFAULT_DATE_TO
        defaultPipelineHistShouldNotBeFound("dateTo.lessThan=" + DEFAULT_DATE_TO);

        // Get all the pipelineHistList where dateTo less than or equals to UPDATED_DATE_TO
        defaultPipelineHistShouldBeFound("dateTo.lessThan=" + UPDATED_DATE_TO);
    }


    @Test
    @Transactional
    public void getAllPipelineHistsByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        pipelineHistRepository.saveAndFlush(pipelineHist);

        // Get all the pipelineHistList where name equals to DEFAULT_NAME
        defaultPipelineHistShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the pipelineHistList where name equals to UPDATED_NAME
        defaultPipelineHistShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllPipelineHistsByNameIsInShouldWork() throws Exception {
        // Initialize the database
        pipelineHistRepository.saveAndFlush(pipelineHist);

        // Get all the pipelineHistList where name in DEFAULT_NAME or UPDATED_NAME
        defaultPipelineHistShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the pipelineHistList where name equals to UPDATED_NAME
        defaultPipelineHistShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllPipelineHistsByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        pipelineHistRepository.saveAndFlush(pipelineHist);

        // Get all the pipelineHistList where name is not null
        defaultPipelineHistShouldBeFound("name.specified=true");

        // Get all the pipelineHistList where name is null
        defaultPipelineHistShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    public void getAllPipelineHistsByDesignLifeIsEqualToSomething() throws Exception {
        // Initialize the database
        pipelineHistRepository.saveAndFlush(pipelineHist);

        // Get all the pipelineHistList where designLife equals to DEFAULT_DESIGN_LIFE
        defaultPipelineHistShouldBeFound("designLife.equals=" + DEFAULT_DESIGN_LIFE);

        // Get all the pipelineHistList where designLife equals to UPDATED_DESIGN_LIFE
        defaultPipelineHistShouldNotBeFound("designLife.equals=" + UPDATED_DESIGN_LIFE);
    }

    @Test
    @Transactional
    public void getAllPipelineHistsByDesignLifeIsInShouldWork() throws Exception {
        // Initialize the database
        pipelineHistRepository.saveAndFlush(pipelineHist);

        // Get all the pipelineHistList where designLife in DEFAULT_DESIGN_LIFE or UPDATED_DESIGN_LIFE
        defaultPipelineHistShouldBeFound("designLife.in=" + DEFAULT_DESIGN_LIFE + "," + UPDATED_DESIGN_LIFE);

        // Get all the pipelineHistList where designLife equals to UPDATED_DESIGN_LIFE
        defaultPipelineHistShouldNotBeFound("designLife.in=" + UPDATED_DESIGN_LIFE);
    }

    @Test
    @Transactional
    public void getAllPipelineHistsByDesignLifeIsNullOrNotNull() throws Exception {
        // Initialize the database
        pipelineHistRepository.saveAndFlush(pipelineHist);

        // Get all the pipelineHistList where designLife is not null
        defaultPipelineHistShouldBeFound("designLife.specified=true");

        // Get all the pipelineHistList where designLife is null
        defaultPipelineHistShouldNotBeFound("designLife.specified=false");
    }

    @Test
    @Transactional
    public void getAllPipelineHistsByDesignLifeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        pipelineHistRepository.saveAndFlush(pipelineHist);

        // Get all the pipelineHistList where designLife greater than or equals to DEFAULT_DESIGN_LIFE
        defaultPipelineHistShouldBeFound("designLife.greaterOrEqualThan=" + DEFAULT_DESIGN_LIFE);

        // Get all the pipelineHistList where designLife greater than or equals to UPDATED_DESIGN_LIFE
        defaultPipelineHistShouldNotBeFound("designLife.greaterOrEqualThan=" + UPDATED_DESIGN_LIFE);
    }

    @Test
    @Transactional
    public void getAllPipelineHistsByDesignLifeIsLessThanSomething() throws Exception {
        // Initialize the database
        pipelineHistRepository.saveAndFlush(pipelineHist);

        // Get all the pipelineHistList where designLife less than or equals to DEFAULT_DESIGN_LIFE
        defaultPipelineHistShouldNotBeFound("designLife.lessThan=" + DEFAULT_DESIGN_LIFE);

        // Get all the pipelineHistList where designLife less than or equals to UPDATED_DESIGN_LIFE
        defaultPipelineHistShouldBeFound("designLife.lessThan=" + UPDATED_DESIGN_LIFE);
    }


    @Test
    @Transactional
    public void getAllPipelineHistsByIsCurrentFlagIsEqualToSomething() throws Exception {
        // Initialize the database
        pipelineHistRepository.saveAndFlush(pipelineHist);

        // Get all the pipelineHistList where isCurrentFlag equals to DEFAULT_IS_CURRENT_FLAG
        defaultPipelineHistShouldBeFound("isCurrentFlag.equals=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the pipelineHistList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultPipelineHistShouldNotBeFound("isCurrentFlag.equals=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllPipelineHistsByIsCurrentFlagIsInShouldWork() throws Exception {
        // Initialize the database
        pipelineHistRepository.saveAndFlush(pipelineHist);

        // Get all the pipelineHistList where isCurrentFlag in DEFAULT_IS_CURRENT_FLAG or UPDATED_IS_CURRENT_FLAG
        defaultPipelineHistShouldBeFound("isCurrentFlag.in=" + DEFAULT_IS_CURRENT_FLAG + "," + UPDATED_IS_CURRENT_FLAG);

        // Get all the pipelineHistList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultPipelineHistShouldNotBeFound("isCurrentFlag.in=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllPipelineHistsByIsCurrentFlagIsNullOrNotNull() throws Exception {
        // Initialize the database
        pipelineHistRepository.saveAndFlush(pipelineHist);

        // Get all the pipelineHistList where isCurrentFlag is not null
        defaultPipelineHistShouldBeFound("isCurrentFlag.specified=true");

        // Get all the pipelineHistList where isCurrentFlag is null
        defaultPipelineHistShouldNotBeFound("isCurrentFlag.specified=false");
    }

    @Test
    @Transactional
    public void getAllPipelineHistsByIsCurrentFlagIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        pipelineHistRepository.saveAndFlush(pipelineHist);

        // Get all the pipelineHistList where isCurrentFlag greater than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultPipelineHistShouldBeFound("isCurrentFlag.greaterOrEqualThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the pipelineHistList where isCurrentFlag greater than or equals to UPDATED_IS_CURRENT_FLAG
        defaultPipelineHistShouldNotBeFound("isCurrentFlag.greaterOrEqualThan=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllPipelineHistsByIsCurrentFlagIsLessThanSomething() throws Exception {
        // Initialize the database
        pipelineHistRepository.saveAndFlush(pipelineHist);

        // Get all the pipelineHistList where isCurrentFlag less than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultPipelineHistShouldNotBeFound("isCurrentFlag.lessThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the pipelineHistList where isCurrentFlag less than or equals to UPDATED_IS_CURRENT_FLAG
        defaultPipelineHistShouldBeFound("isCurrentFlag.lessThan=" + UPDATED_IS_CURRENT_FLAG);
    }


    @Test
    @Transactional
    public void getAllPipelineHistsByCommentIsEqualToSomething() throws Exception {
        // Initialize the database
        pipelineHistRepository.saveAndFlush(pipelineHist);

        // Get all the pipelineHistList where comment equals to DEFAULT_COMMENT
        defaultPipelineHistShouldBeFound("comment.equals=" + DEFAULT_COMMENT);

        // Get all the pipelineHistList where comment equals to UPDATED_COMMENT
        defaultPipelineHistShouldNotBeFound("comment.equals=" + UPDATED_COMMENT);
    }

    @Test
    @Transactional
    public void getAllPipelineHistsByCommentIsInShouldWork() throws Exception {
        // Initialize the database
        pipelineHistRepository.saveAndFlush(pipelineHist);

        // Get all the pipelineHistList where comment in DEFAULT_COMMENT or UPDATED_COMMENT
        defaultPipelineHistShouldBeFound("comment.in=" + DEFAULT_COMMENT + "," + UPDATED_COMMENT);

        // Get all the pipelineHistList where comment equals to UPDATED_COMMENT
        defaultPipelineHistShouldNotBeFound("comment.in=" + UPDATED_COMMENT);
    }

    @Test
    @Transactional
    public void getAllPipelineHistsByCommentIsNullOrNotNull() throws Exception {
        // Initialize the database
        pipelineHistRepository.saveAndFlush(pipelineHist);

        // Get all the pipelineHistList where comment is not null
        defaultPipelineHistShouldBeFound("comment.specified=true");

        // Get all the pipelineHistList where comment is null
        defaultPipelineHistShouldNotBeFound("comment.specified=false");
    }

    @Test
    @Transactional
    public void getAllPipelineHistsByDateCreateIsEqualToSomething() throws Exception {
        // Initialize the database
        pipelineHistRepository.saveAndFlush(pipelineHist);

        // Get all the pipelineHistList where dateCreate equals to DEFAULT_DATE_CREATE
        defaultPipelineHistShouldBeFound("dateCreate.equals=" + DEFAULT_DATE_CREATE);

        // Get all the pipelineHistList where dateCreate equals to UPDATED_DATE_CREATE
        defaultPipelineHistShouldNotBeFound("dateCreate.equals=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllPipelineHistsByDateCreateIsInShouldWork() throws Exception {
        // Initialize the database
        pipelineHistRepository.saveAndFlush(pipelineHist);

        // Get all the pipelineHistList where dateCreate in DEFAULT_DATE_CREATE or UPDATED_DATE_CREATE
        defaultPipelineHistShouldBeFound("dateCreate.in=" + DEFAULT_DATE_CREATE + "," + UPDATED_DATE_CREATE);

        // Get all the pipelineHistList where dateCreate equals to UPDATED_DATE_CREATE
        defaultPipelineHistShouldNotBeFound("dateCreate.in=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllPipelineHistsByDateCreateIsNullOrNotNull() throws Exception {
        // Initialize the database
        pipelineHistRepository.saveAndFlush(pipelineHist);

        // Get all the pipelineHistList where dateCreate is not null
        defaultPipelineHistShouldBeFound("dateCreate.specified=true");

        // Get all the pipelineHistList where dateCreate is null
        defaultPipelineHistShouldNotBeFound("dateCreate.specified=false");
    }

    @Test
    @Transactional
    public void getAllPipelineHistsByDateEditIsEqualToSomething() throws Exception {
        // Initialize the database
        pipelineHistRepository.saveAndFlush(pipelineHist);

        // Get all the pipelineHistList where dateEdit equals to DEFAULT_DATE_EDIT
        defaultPipelineHistShouldBeFound("dateEdit.equals=" + DEFAULT_DATE_EDIT);

        // Get all the pipelineHistList where dateEdit equals to UPDATED_DATE_EDIT
        defaultPipelineHistShouldNotBeFound("dateEdit.equals=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllPipelineHistsByDateEditIsInShouldWork() throws Exception {
        // Initialize the database
        pipelineHistRepository.saveAndFlush(pipelineHist);

        // Get all the pipelineHistList where dateEdit in DEFAULT_DATE_EDIT or UPDATED_DATE_EDIT
        defaultPipelineHistShouldBeFound("dateEdit.in=" + DEFAULT_DATE_EDIT + "," + UPDATED_DATE_EDIT);

        // Get all the pipelineHistList where dateEdit equals to UPDATED_DATE_EDIT
        defaultPipelineHistShouldNotBeFound("dateEdit.in=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllPipelineHistsByDateEditIsNullOrNotNull() throws Exception {
        // Initialize the database
        pipelineHistRepository.saveAndFlush(pipelineHist);

        // Get all the pipelineHistList where dateEdit is not null
        defaultPipelineHistShouldBeFound("dateEdit.specified=true");

        // Get all the pipelineHistList where dateEdit is null
        defaultPipelineHistShouldNotBeFound("dateEdit.specified=false");
    }

    @Test
    @Transactional
    public void getAllPipelineHistsByCreatorIsEqualToSomething() throws Exception {
        // Initialize the database
        pipelineHistRepository.saveAndFlush(pipelineHist);

        // Get all the pipelineHistList where creator equals to DEFAULT_CREATOR
        defaultPipelineHistShouldBeFound("creator.equals=" + DEFAULT_CREATOR);

        // Get all the pipelineHistList where creator equals to UPDATED_CREATOR
        defaultPipelineHistShouldNotBeFound("creator.equals=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllPipelineHistsByCreatorIsInShouldWork() throws Exception {
        // Initialize the database
        pipelineHistRepository.saveAndFlush(pipelineHist);

        // Get all the pipelineHistList where creator in DEFAULT_CREATOR or UPDATED_CREATOR
        defaultPipelineHistShouldBeFound("creator.in=" + DEFAULT_CREATOR + "," + UPDATED_CREATOR);

        // Get all the pipelineHistList where creator equals to UPDATED_CREATOR
        defaultPipelineHistShouldNotBeFound("creator.in=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllPipelineHistsByCreatorIsNullOrNotNull() throws Exception {
        // Initialize the database
        pipelineHistRepository.saveAndFlush(pipelineHist);

        // Get all the pipelineHistList where creator is not null
        defaultPipelineHistShouldBeFound("creator.specified=true");

        // Get all the pipelineHistList where creator is null
        defaultPipelineHistShouldNotBeFound("creator.specified=false");
    }

    @Test
    @Transactional
    public void getAllPipelineHistsByEditorIsEqualToSomething() throws Exception {
        // Initialize the database
        pipelineHistRepository.saveAndFlush(pipelineHist);

        // Get all the pipelineHistList where editor equals to DEFAULT_EDITOR
        defaultPipelineHistShouldBeFound("editor.equals=" + DEFAULT_EDITOR);

        // Get all the pipelineHistList where editor equals to UPDATED_EDITOR
        defaultPipelineHistShouldNotBeFound("editor.equals=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllPipelineHistsByEditorIsInShouldWork() throws Exception {
        // Initialize the database
        pipelineHistRepository.saveAndFlush(pipelineHist);

        // Get all the pipelineHistList where editor in DEFAULT_EDITOR or UPDATED_EDITOR
        defaultPipelineHistShouldBeFound("editor.in=" + DEFAULT_EDITOR + "," + UPDATED_EDITOR);

        // Get all the pipelineHistList where editor equals to UPDATED_EDITOR
        defaultPipelineHistShouldNotBeFound("editor.in=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllPipelineHistsByEditorIsNullOrNotNull() throws Exception {
        // Initialize the database
        pipelineHistRepository.saveAndFlush(pipelineHist);

        // Get all the pipelineHistList where editor is not null
        defaultPipelineHistShouldBeFound("editor.specified=true");

        // Get all the pipelineHistList where editor is null
        defaultPipelineHistShouldNotBeFound("editor.specified=false");
    }

    @Test
    @Transactional
    public void getAllPipelineHistsByIdIsEqualToSomething() throws Exception {
        // Get already existing entity
        Pipeline id = pipelineHist.getId();
        pipelineHistRepository.saveAndFlush(pipelineHist);
        Long idId = id.getId();

        // Get all the pipelineHistList where id equals to idId
        defaultPipelineHistShouldBeFound("idId.equals=" + idId);

        // Get all the pipelineHistList where id equals to idId + 1
        defaultPipelineHistShouldNotBeFound("idId.equals=" + (idId + 1));
    }


    @Test
    @Transactional
    public void getAllPipelineHistsByIdLocationIsEqualToSomething() throws Exception {
        // Initialize the database
        ListPipelineLocation idLocation = ListPipelineLocationResourceIT.createEntity(em);
        em.persist(idLocation);
        em.flush();
        pipelineHist.setIdLocation(idLocation);
        pipelineHistRepository.saveAndFlush(pipelineHist);
        Long idLocationId = idLocation.getId();

        // Get all the pipelineHistList where idLocation equals to idLocationId
        defaultPipelineHistShouldBeFound("idLocationId.equals=" + idLocationId);

        // Get all the pipelineHistList where idLocation equals to idLocationId + 1
        defaultPipelineHistShouldNotBeFound("idLocationId.equals=" + (idLocationId + 1));
    }


    @Test
    @Transactional
    public void getAllPipelineHistsByIdStatusIsEqualToSomething() throws Exception {
        // Get already existing entity
        ListObjectStatus idStatus = pipelineHist.getIdStatus();
        pipelineHistRepository.saveAndFlush(pipelineHist);
        Long idStatusId = idStatus.getId();

        // Get all the pipelineHistList where idStatus equals to idStatusId
        defaultPipelineHistShouldBeFound("idStatusId.equals=" + idStatusId);

        // Get all the pipelineHistList where idStatus equals to idStatusId + 1
        defaultPipelineHistShouldNotBeFound("idStatusId.equals=" + (idStatusId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultPipelineHistShouldBeFound(String filter) throws Exception {
        restPipelineHistMockMvc.perform(get("/api/pipeline-hists?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pipelineHist.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateFrom").value(hasItem(DEFAULT_DATE_FROM.toString())))
            .andExpect(jsonPath("$.[*].dateTo").value(hasItem(DEFAULT_DATE_TO.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].designLife").value(hasItem(DEFAULT_DESIGN_LIFE)))
            .andExpect(jsonPath("$.[*].isCurrentFlag").value(hasItem(DEFAULT_IS_CURRENT_FLAG)))
            .andExpect(jsonPath("$.[*].comment").value(hasItem(DEFAULT_COMMENT)))
            .andExpect(jsonPath("$.[*].dateCreate").value(hasItem(DEFAULT_DATE_CREATE.toString())))
            .andExpect(jsonPath("$.[*].dateEdit").value(hasItem(DEFAULT_DATE_EDIT.toString())))
            .andExpect(jsonPath("$.[*].creator").value(hasItem(DEFAULT_CREATOR)))
            .andExpect(jsonPath("$.[*].editor").value(hasItem(DEFAULT_EDITOR)));

        // Check, that the count call also returns 1
        restPipelineHistMockMvc.perform(get("/api/pipeline-hists/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultPipelineHistShouldNotBeFound(String filter) throws Exception {
        restPipelineHistMockMvc.perform(get("/api/pipeline-hists?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restPipelineHistMockMvc.perform(get("/api/pipeline-hists/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingPipelineHist() throws Exception {
        // Get the pipelineHist
        restPipelineHistMockMvc.perform(get("/api/pipeline-hists/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePipelineHist() throws Exception {
        // Initialize the database
        pipelineHistRepository.saveAndFlush(pipelineHist);

        int databaseSizeBeforeUpdate = pipelineHistRepository.findAll().size();

        // Update the pipelineHist
        PipelineHist updatedPipelineHist = pipelineHistRepository.findById(pipelineHist.getId()).get();
        // Disconnect from session so that the updates on updatedPipelineHist are not directly saved in db
        em.detach(updatedPipelineHist);
        updatedPipelineHist
            .dateFrom(UPDATED_DATE_FROM)
            .dateTo(UPDATED_DATE_TO)
            .name(UPDATED_NAME)
            .designLife(UPDATED_DESIGN_LIFE)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .comment(UPDATED_COMMENT)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        PipelineHistDTO pipelineHistDTO = pipelineHistMapper.toDto(updatedPipelineHist);

        restPipelineHistMockMvc.perform(put("/api/pipeline-hists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pipelineHistDTO)))
            .andExpect(status().isOk());

        // Validate the PipelineHist in the database
        List<PipelineHist> pipelineHistList = pipelineHistRepository.findAll();
        assertThat(pipelineHistList).hasSize(databaseSizeBeforeUpdate);
        PipelineHist testPipelineHist = pipelineHistList.get(pipelineHistList.size() - 1);
        assertThat(testPipelineHist.getDateFrom()).isEqualTo(UPDATED_DATE_FROM);
        assertThat(testPipelineHist.getDateTo()).isEqualTo(UPDATED_DATE_TO);
        assertThat(testPipelineHist.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testPipelineHist.getDesignLife()).isEqualTo(UPDATED_DESIGN_LIFE);
        assertThat(testPipelineHist.getIsCurrentFlag()).isEqualTo(UPDATED_IS_CURRENT_FLAG);
        assertThat(testPipelineHist.getComment()).isEqualTo(UPDATED_COMMENT);
        assertThat(testPipelineHist.getDateCreate()).isEqualTo(UPDATED_DATE_CREATE);
        assertThat(testPipelineHist.getDateEdit()).isEqualTo(UPDATED_DATE_EDIT);
        assertThat(testPipelineHist.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testPipelineHist.getEditor()).isEqualTo(UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void updateNonExistingPipelineHist() throws Exception {
        int databaseSizeBeforeUpdate = pipelineHistRepository.findAll().size();

        // Create the PipelineHist
        PipelineHistDTO pipelineHistDTO = pipelineHistMapper.toDto(pipelineHist);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPipelineHistMockMvc.perform(put("/api/pipeline-hists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pipelineHistDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PipelineHist in the database
        List<PipelineHist> pipelineHistList = pipelineHistRepository.findAll();
        assertThat(pipelineHistList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePipelineHist() throws Exception {
        // Initialize the database
        pipelineHistRepository.saveAndFlush(pipelineHist);

        int databaseSizeBeforeDelete = pipelineHistRepository.findAll().size();

        // Delete the pipelineHist
        restPipelineHistMockMvc.perform(delete("/api/pipeline-hists/{id}", pipelineHist.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<PipelineHist> pipelineHistList = pipelineHistRepository.findAll();
        assertThat(pipelineHistList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PipelineHist.class);
        PipelineHist pipelineHist1 = new PipelineHist();
        pipelineHist1.setId(1L);
        PipelineHist pipelineHist2 = new PipelineHist();
        pipelineHist2.setId(pipelineHist1.getId());
        assertThat(pipelineHist1).isEqualTo(pipelineHist2);
        pipelineHist2.setId(2L);
        assertThat(pipelineHist1).isNotEqualTo(pipelineHist2);
        pipelineHist1.setId(null);
        assertThat(pipelineHist1).isNotEqualTo(pipelineHist2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PipelineHistDTO.class);
        PipelineHistDTO pipelineHistDTO1 = new PipelineHistDTO();
        pipelineHistDTO1.setId(1L);
        PipelineHistDTO pipelineHistDTO2 = new PipelineHistDTO();
        assertThat(pipelineHistDTO1).isNotEqualTo(pipelineHistDTO2);
        pipelineHistDTO2.setId(pipelineHistDTO1.getId());
        assertThat(pipelineHistDTO1).isEqualTo(pipelineHistDTO2);
        pipelineHistDTO2.setId(2L);
        assertThat(pipelineHistDTO1).isNotEqualTo(pipelineHistDTO2);
        pipelineHistDTO1.setId(null);
        assertThat(pipelineHistDTO1).isNotEqualTo(pipelineHistDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(pipelineHistMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(pipelineHistMapper.fromId(null)).isNull();
    }
}
