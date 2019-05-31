package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.CpsRange;
import io.github.jhipster.application.domain.Cps;
import io.github.jhipster.application.domain.PipelineSection;
import io.github.jhipster.application.repository.CpsRangeRepository;
import io.github.jhipster.application.service.CpsRangeService;
import io.github.jhipster.application.service.dto.CpsRangeDTO;
import io.github.jhipster.application.service.mapper.CpsRangeMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.CpsRangeCriteria;
import io.github.jhipster.application.service.CpsRangeQueryService;

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
 * Integration tests for the {@Link CpsRangeResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class CpsRangeResourceIT {

    private static final BigDecimal DEFAULT_KP_START = new BigDecimal(1);
    private static final BigDecimal UPDATED_KP_START = new BigDecimal(2);

    private static final BigDecimal DEFAULT_KP_END = new BigDecimal(1);
    private static final BigDecimal UPDATED_KP_END = new BigDecimal(2);

    private static final LocalDate DEFAULT_DATE_FROM = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_FROM = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATE_TO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_TO = LocalDate.now(ZoneId.systemDefault());

    private static final Instant DEFAULT_DATE_CREATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_CREATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_DATE_EDIT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_EDIT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CREATOR = "AAAAAAAAAA";
    private static final String UPDATED_CREATOR = "BBBBBBBBBB";

    private static final String DEFAULT_EDITOR = "AAAAAAAAAA";
    private static final String UPDATED_EDITOR = "BBBBBBBBBB";

    @Autowired
    private CpsRangeRepository cpsRangeRepository;

    @Autowired
    private CpsRangeMapper cpsRangeMapper;

    @Autowired
    private CpsRangeService cpsRangeService;

    @Autowired
    private CpsRangeQueryService cpsRangeQueryService;

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

    private MockMvc restCpsRangeMockMvc;

    private CpsRange cpsRange;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CpsRangeResource cpsRangeResource = new CpsRangeResource(cpsRangeService, cpsRangeQueryService);
        this.restCpsRangeMockMvc = MockMvcBuilders.standaloneSetup(cpsRangeResource)
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
    public static CpsRange createEntity(EntityManager em) {
        CpsRange cpsRange = new CpsRange()
            .kpStart(DEFAULT_KP_START)
            .kpEnd(DEFAULT_KP_END)
            .dateFrom(DEFAULT_DATE_FROM)
            .dateTo(DEFAULT_DATE_TO)
            .dateCreate(DEFAULT_DATE_CREATE)
            .dateEdit(DEFAULT_DATE_EDIT)
            .creator(DEFAULT_CREATOR)
            .editor(DEFAULT_EDITOR);
        // Add required entity
        Cps cps;
        if (TestUtil.findAll(em, Cps.class).isEmpty()) {
            cps = CpsResourceIT.createEntity(em);
            em.persist(cps);
            em.flush();
        } else {
            cps = TestUtil.findAll(em, Cps.class).get(0);
        }
        cpsRange.setIdCps(cps);
        // Add required entity
        PipelineSection pipelineSection;
        if (TestUtil.findAll(em, PipelineSection.class).isEmpty()) {
            pipelineSection = PipelineSectionResourceIT.createEntity(em);
            em.persist(pipelineSection);
            em.flush();
        } else {
            pipelineSection = TestUtil.findAll(em, PipelineSection.class).get(0);
        }
        cpsRange.setIdPipelineSection(pipelineSection);
        return cpsRange;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CpsRange createUpdatedEntity(EntityManager em) {
        CpsRange cpsRange = new CpsRange()
            .kpStart(UPDATED_KP_START)
            .kpEnd(UPDATED_KP_END)
            .dateFrom(UPDATED_DATE_FROM)
            .dateTo(UPDATED_DATE_TO)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        // Add required entity
        Cps cps;
        if (TestUtil.findAll(em, Cps.class).isEmpty()) {
            cps = CpsResourceIT.createUpdatedEntity(em);
            em.persist(cps);
            em.flush();
        } else {
            cps = TestUtil.findAll(em, Cps.class).get(0);
        }
        cpsRange.setIdCps(cps);
        // Add required entity
        PipelineSection pipelineSection;
        if (TestUtil.findAll(em, PipelineSection.class).isEmpty()) {
            pipelineSection = PipelineSectionResourceIT.createUpdatedEntity(em);
            em.persist(pipelineSection);
            em.flush();
        } else {
            pipelineSection = TestUtil.findAll(em, PipelineSection.class).get(0);
        }
        cpsRange.setIdPipelineSection(pipelineSection);
        return cpsRange;
    }

    @BeforeEach
    public void initTest() {
        cpsRange = createEntity(em);
    }

    @Test
    @Transactional
    public void createCpsRange() throws Exception {
        int databaseSizeBeforeCreate = cpsRangeRepository.findAll().size();

        // Create the CpsRange
        CpsRangeDTO cpsRangeDTO = cpsRangeMapper.toDto(cpsRange);
        restCpsRangeMockMvc.perform(post("/api/cps-ranges")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cpsRangeDTO)))
            .andExpect(status().isCreated());

        // Validate the CpsRange in the database
        List<CpsRange> cpsRangeList = cpsRangeRepository.findAll();
        assertThat(cpsRangeList).hasSize(databaseSizeBeforeCreate + 1);
        CpsRange testCpsRange = cpsRangeList.get(cpsRangeList.size() - 1);
        assertThat(testCpsRange.getKpStart()).isEqualTo(DEFAULT_KP_START);
        assertThat(testCpsRange.getKpEnd()).isEqualTo(DEFAULT_KP_END);
        assertThat(testCpsRange.getDateFrom()).isEqualTo(DEFAULT_DATE_FROM);
        assertThat(testCpsRange.getDateTo()).isEqualTo(DEFAULT_DATE_TO);
        assertThat(testCpsRange.getDateCreate()).isEqualTo(DEFAULT_DATE_CREATE);
        assertThat(testCpsRange.getDateEdit()).isEqualTo(DEFAULT_DATE_EDIT);
        assertThat(testCpsRange.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testCpsRange.getEditor()).isEqualTo(DEFAULT_EDITOR);
    }

    @Test
    @Transactional
    public void createCpsRangeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = cpsRangeRepository.findAll().size();

        // Create the CpsRange with an existing ID
        cpsRange.setId(1L);
        CpsRangeDTO cpsRangeDTO = cpsRangeMapper.toDto(cpsRange);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCpsRangeMockMvc.perform(post("/api/cps-ranges")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cpsRangeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CpsRange in the database
        List<CpsRange> cpsRangeList = cpsRangeRepository.findAll();
        assertThat(cpsRangeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkKpStartIsRequired() throws Exception {
        int databaseSizeBeforeTest = cpsRangeRepository.findAll().size();
        // set the field null
        cpsRange.setKpStart(null);

        // Create the CpsRange, which fails.
        CpsRangeDTO cpsRangeDTO = cpsRangeMapper.toDto(cpsRange);

        restCpsRangeMockMvc.perform(post("/api/cps-ranges")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cpsRangeDTO)))
            .andExpect(status().isBadRequest());

        List<CpsRange> cpsRangeList = cpsRangeRepository.findAll();
        assertThat(cpsRangeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkKpEndIsRequired() throws Exception {
        int databaseSizeBeforeTest = cpsRangeRepository.findAll().size();
        // set the field null
        cpsRange.setKpEnd(null);

        // Create the CpsRange, which fails.
        CpsRangeDTO cpsRangeDTO = cpsRangeMapper.toDto(cpsRange);

        restCpsRangeMockMvc.perform(post("/api/cps-ranges")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cpsRangeDTO)))
            .andExpect(status().isBadRequest());

        List<CpsRange> cpsRangeList = cpsRangeRepository.findAll();
        assertThat(cpsRangeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDateFromIsRequired() throws Exception {
        int databaseSizeBeforeTest = cpsRangeRepository.findAll().size();
        // set the field null
        cpsRange.setDateFrom(null);

        // Create the CpsRange, which fails.
        CpsRangeDTO cpsRangeDTO = cpsRangeMapper.toDto(cpsRange);

        restCpsRangeMockMvc.perform(post("/api/cps-ranges")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cpsRangeDTO)))
            .andExpect(status().isBadRequest());

        List<CpsRange> cpsRangeList = cpsRangeRepository.findAll();
        assertThat(cpsRangeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDateToIsRequired() throws Exception {
        int databaseSizeBeforeTest = cpsRangeRepository.findAll().size();
        // set the field null
        cpsRange.setDateTo(null);

        // Create the CpsRange, which fails.
        CpsRangeDTO cpsRangeDTO = cpsRangeMapper.toDto(cpsRange);

        restCpsRangeMockMvc.perform(post("/api/cps-ranges")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cpsRangeDTO)))
            .andExpect(status().isBadRequest());

        List<CpsRange> cpsRangeList = cpsRangeRepository.findAll();
        assertThat(cpsRangeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCpsRanges() throws Exception {
        // Initialize the database
        cpsRangeRepository.saveAndFlush(cpsRange);

        // Get all the cpsRangeList
        restCpsRangeMockMvc.perform(get("/api/cps-ranges?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cpsRange.getId().intValue())))
            .andExpect(jsonPath("$.[*].kpStart").value(hasItem(DEFAULT_KP_START.intValue())))
            .andExpect(jsonPath("$.[*].kpEnd").value(hasItem(DEFAULT_KP_END.intValue())))
            .andExpect(jsonPath("$.[*].dateFrom").value(hasItem(DEFAULT_DATE_FROM.toString())))
            .andExpect(jsonPath("$.[*].dateTo").value(hasItem(DEFAULT_DATE_TO.toString())))
            .andExpect(jsonPath("$.[*].dateCreate").value(hasItem(DEFAULT_DATE_CREATE.toString())))
            .andExpect(jsonPath("$.[*].dateEdit").value(hasItem(DEFAULT_DATE_EDIT.toString())))
            .andExpect(jsonPath("$.[*].creator").value(hasItem(DEFAULT_CREATOR.toString())))
            .andExpect(jsonPath("$.[*].editor").value(hasItem(DEFAULT_EDITOR.toString())));
    }
    
    @Test
    @Transactional
    public void getCpsRange() throws Exception {
        // Initialize the database
        cpsRangeRepository.saveAndFlush(cpsRange);

        // Get the cpsRange
        restCpsRangeMockMvc.perform(get("/api/cps-ranges/{id}", cpsRange.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(cpsRange.getId().intValue()))
            .andExpect(jsonPath("$.kpStart").value(DEFAULT_KP_START.intValue()))
            .andExpect(jsonPath("$.kpEnd").value(DEFAULT_KP_END.intValue()))
            .andExpect(jsonPath("$.dateFrom").value(DEFAULT_DATE_FROM.toString()))
            .andExpect(jsonPath("$.dateTo").value(DEFAULT_DATE_TO.toString()))
            .andExpect(jsonPath("$.dateCreate").value(DEFAULT_DATE_CREATE.toString()))
            .andExpect(jsonPath("$.dateEdit").value(DEFAULT_DATE_EDIT.toString()))
            .andExpect(jsonPath("$.creator").value(DEFAULT_CREATOR.toString()))
            .andExpect(jsonPath("$.editor").value(DEFAULT_EDITOR.toString()));
    }

    @Test
    @Transactional
    public void getAllCpsRangesByKpStartIsEqualToSomething() throws Exception {
        // Initialize the database
        cpsRangeRepository.saveAndFlush(cpsRange);

        // Get all the cpsRangeList where kpStart equals to DEFAULT_KP_START
        defaultCpsRangeShouldBeFound("kpStart.equals=" + DEFAULT_KP_START);

        // Get all the cpsRangeList where kpStart equals to UPDATED_KP_START
        defaultCpsRangeShouldNotBeFound("kpStart.equals=" + UPDATED_KP_START);
    }

    @Test
    @Transactional
    public void getAllCpsRangesByKpStartIsInShouldWork() throws Exception {
        // Initialize the database
        cpsRangeRepository.saveAndFlush(cpsRange);

        // Get all the cpsRangeList where kpStart in DEFAULT_KP_START or UPDATED_KP_START
        defaultCpsRangeShouldBeFound("kpStart.in=" + DEFAULT_KP_START + "," + UPDATED_KP_START);

        // Get all the cpsRangeList where kpStart equals to UPDATED_KP_START
        defaultCpsRangeShouldNotBeFound("kpStart.in=" + UPDATED_KP_START);
    }

    @Test
    @Transactional
    public void getAllCpsRangesByKpStartIsNullOrNotNull() throws Exception {
        // Initialize the database
        cpsRangeRepository.saveAndFlush(cpsRange);

        // Get all the cpsRangeList where kpStart is not null
        defaultCpsRangeShouldBeFound("kpStart.specified=true");

        // Get all the cpsRangeList where kpStart is null
        defaultCpsRangeShouldNotBeFound("kpStart.specified=false");
    }

    @Test
    @Transactional
    public void getAllCpsRangesByKpEndIsEqualToSomething() throws Exception {
        // Initialize the database
        cpsRangeRepository.saveAndFlush(cpsRange);

        // Get all the cpsRangeList where kpEnd equals to DEFAULT_KP_END
        defaultCpsRangeShouldBeFound("kpEnd.equals=" + DEFAULT_KP_END);

        // Get all the cpsRangeList where kpEnd equals to UPDATED_KP_END
        defaultCpsRangeShouldNotBeFound("kpEnd.equals=" + UPDATED_KP_END);
    }

    @Test
    @Transactional
    public void getAllCpsRangesByKpEndIsInShouldWork() throws Exception {
        // Initialize the database
        cpsRangeRepository.saveAndFlush(cpsRange);

        // Get all the cpsRangeList where kpEnd in DEFAULT_KP_END or UPDATED_KP_END
        defaultCpsRangeShouldBeFound("kpEnd.in=" + DEFAULT_KP_END + "," + UPDATED_KP_END);

        // Get all the cpsRangeList where kpEnd equals to UPDATED_KP_END
        defaultCpsRangeShouldNotBeFound("kpEnd.in=" + UPDATED_KP_END);
    }

    @Test
    @Transactional
    public void getAllCpsRangesByKpEndIsNullOrNotNull() throws Exception {
        // Initialize the database
        cpsRangeRepository.saveAndFlush(cpsRange);

        // Get all the cpsRangeList where kpEnd is not null
        defaultCpsRangeShouldBeFound("kpEnd.specified=true");

        // Get all the cpsRangeList where kpEnd is null
        defaultCpsRangeShouldNotBeFound("kpEnd.specified=false");
    }

    @Test
    @Transactional
    public void getAllCpsRangesByDateFromIsEqualToSomething() throws Exception {
        // Initialize the database
        cpsRangeRepository.saveAndFlush(cpsRange);

        // Get all the cpsRangeList where dateFrom equals to DEFAULT_DATE_FROM
        defaultCpsRangeShouldBeFound("dateFrom.equals=" + DEFAULT_DATE_FROM);

        // Get all the cpsRangeList where dateFrom equals to UPDATED_DATE_FROM
        defaultCpsRangeShouldNotBeFound("dateFrom.equals=" + UPDATED_DATE_FROM);
    }

    @Test
    @Transactional
    public void getAllCpsRangesByDateFromIsInShouldWork() throws Exception {
        // Initialize the database
        cpsRangeRepository.saveAndFlush(cpsRange);

        // Get all the cpsRangeList where dateFrom in DEFAULT_DATE_FROM or UPDATED_DATE_FROM
        defaultCpsRangeShouldBeFound("dateFrom.in=" + DEFAULT_DATE_FROM + "," + UPDATED_DATE_FROM);

        // Get all the cpsRangeList where dateFrom equals to UPDATED_DATE_FROM
        defaultCpsRangeShouldNotBeFound("dateFrom.in=" + UPDATED_DATE_FROM);
    }

    @Test
    @Transactional
    public void getAllCpsRangesByDateFromIsNullOrNotNull() throws Exception {
        // Initialize the database
        cpsRangeRepository.saveAndFlush(cpsRange);

        // Get all the cpsRangeList where dateFrom is not null
        defaultCpsRangeShouldBeFound("dateFrom.specified=true");

        // Get all the cpsRangeList where dateFrom is null
        defaultCpsRangeShouldNotBeFound("dateFrom.specified=false");
    }

    @Test
    @Transactional
    public void getAllCpsRangesByDateFromIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cpsRangeRepository.saveAndFlush(cpsRange);

        // Get all the cpsRangeList where dateFrom greater than or equals to DEFAULT_DATE_FROM
        defaultCpsRangeShouldBeFound("dateFrom.greaterOrEqualThan=" + DEFAULT_DATE_FROM);

        // Get all the cpsRangeList where dateFrom greater than or equals to UPDATED_DATE_FROM
        defaultCpsRangeShouldNotBeFound("dateFrom.greaterOrEqualThan=" + UPDATED_DATE_FROM);
    }

    @Test
    @Transactional
    public void getAllCpsRangesByDateFromIsLessThanSomething() throws Exception {
        // Initialize the database
        cpsRangeRepository.saveAndFlush(cpsRange);

        // Get all the cpsRangeList where dateFrom less than or equals to DEFAULT_DATE_FROM
        defaultCpsRangeShouldNotBeFound("dateFrom.lessThan=" + DEFAULT_DATE_FROM);

        // Get all the cpsRangeList where dateFrom less than or equals to UPDATED_DATE_FROM
        defaultCpsRangeShouldBeFound("dateFrom.lessThan=" + UPDATED_DATE_FROM);
    }


    @Test
    @Transactional
    public void getAllCpsRangesByDateToIsEqualToSomething() throws Exception {
        // Initialize the database
        cpsRangeRepository.saveAndFlush(cpsRange);

        // Get all the cpsRangeList where dateTo equals to DEFAULT_DATE_TO
        defaultCpsRangeShouldBeFound("dateTo.equals=" + DEFAULT_DATE_TO);

        // Get all the cpsRangeList where dateTo equals to UPDATED_DATE_TO
        defaultCpsRangeShouldNotBeFound("dateTo.equals=" + UPDATED_DATE_TO);
    }

    @Test
    @Transactional
    public void getAllCpsRangesByDateToIsInShouldWork() throws Exception {
        // Initialize the database
        cpsRangeRepository.saveAndFlush(cpsRange);

        // Get all the cpsRangeList where dateTo in DEFAULT_DATE_TO or UPDATED_DATE_TO
        defaultCpsRangeShouldBeFound("dateTo.in=" + DEFAULT_DATE_TO + "," + UPDATED_DATE_TO);

        // Get all the cpsRangeList where dateTo equals to UPDATED_DATE_TO
        defaultCpsRangeShouldNotBeFound("dateTo.in=" + UPDATED_DATE_TO);
    }

    @Test
    @Transactional
    public void getAllCpsRangesByDateToIsNullOrNotNull() throws Exception {
        // Initialize the database
        cpsRangeRepository.saveAndFlush(cpsRange);

        // Get all the cpsRangeList where dateTo is not null
        defaultCpsRangeShouldBeFound("dateTo.specified=true");

        // Get all the cpsRangeList where dateTo is null
        defaultCpsRangeShouldNotBeFound("dateTo.specified=false");
    }

    @Test
    @Transactional
    public void getAllCpsRangesByDateToIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cpsRangeRepository.saveAndFlush(cpsRange);

        // Get all the cpsRangeList where dateTo greater than or equals to DEFAULT_DATE_TO
        defaultCpsRangeShouldBeFound("dateTo.greaterOrEqualThan=" + DEFAULT_DATE_TO);

        // Get all the cpsRangeList where dateTo greater than or equals to UPDATED_DATE_TO
        defaultCpsRangeShouldNotBeFound("dateTo.greaterOrEqualThan=" + UPDATED_DATE_TO);
    }

    @Test
    @Transactional
    public void getAllCpsRangesByDateToIsLessThanSomething() throws Exception {
        // Initialize the database
        cpsRangeRepository.saveAndFlush(cpsRange);

        // Get all the cpsRangeList where dateTo less than or equals to DEFAULT_DATE_TO
        defaultCpsRangeShouldNotBeFound("dateTo.lessThan=" + DEFAULT_DATE_TO);

        // Get all the cpsRangeList where dateTo less than or equals to UPDATED_DATE_TO
        defaultCpsRangeShouldBeFound("dateTo.lessThan=" + UPDATED_DATE_TO);
    }


    @Test
    @Transactional
    public void getAllCpsRangesByDateCreateIsEqualToSomething() throws Exception {
        // Initialize the database
        cpsRangeRepository.saveAndFlush(cpsRange);

        // Get all the cpsRangeList where dateCreate equals to DEFAULT_DATE_CREATE
        defaultCpsRangeShouldBeFound("dateCreate.equals=" + DEFAULT_DATE_CREATE);

        // Get all the cpsRangeList where dateCreate equals to UPDATED_DATE_CREATE
        defaultCpsRangeShouldNotBeFound("dateCreate.equals=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllCpsRangesByDateCreateIsInShouldWork() throws Exception {
        // Initialize the database
        cpsRangeRepository.saveAndFlush(cpsRange);

        // Get all the cpsRangeList where dateCreate in DEFAULT_DATE_CREATE or UPDATED_DATE_CREATE
        defaultCpsRangeShouldBeFound("dateCreate.in=" + DEFAULT_DATE_CREATE + "," + UPDATED_DATE_CREATE);

        // Get all the cpsRangeList where dateCreate equals to UPDATED_DATE_CREATE
        defaultCpsRangeShouldNotBeFound("dateCreate.in=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllCpsRangesByDateCreateIsNullOrNotNull() throws Exception {
        // Initialize the database
        cpsRangeRepository.saveAndFlush(cpsRange);

        // Get all the cpsRangeList where dateCreate is not null
        defaultCpsRangeShouldBeFound("dateCreate.specified=true");

        // Get all the cpsRangeList where dateCreate is null
        defaultCpsRangeShouldNotBeFound("dateCreate.specified=false");
    }

    @Test
    @Transactional
    public void getAllCpsRangesByDateEditIsEqualToSomething() throws Exception {
        // Initialize the database
        cpsRangeRepository.saveAndFlush(cpsRange);

        // Get all the cpsRangeList where dateEdit equals to DEFAULT_DATE_EDIT
        defaultCpsRangeShouldBeFound("dateEdit.equals=" + DEFAULT_DATE_EDIT);

        // Get all the cpsRangeList where dateEdit equals to UPDATED_DATE_EDIT
        defaultCpsRangeShouldNotBeFound("dateEdit.equals=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllCpsRangesByDateEditIsInShouldWork() throws Exception {
        // Initialize the database
        cpsRangeRepository.saveAndFlush(cpsRange);

        // Get all the cpsRangeList where dateEdit in DEFAULT_DATE_EDIT or UPDATED_DATE_EDIT
        defaultCpsRangeShouldBeFound("dateEdit.in=" + DEFAULT_DATE_EDIT + "," + UPDATED_DATE_EDIT);

        // Get all the cpsRangeList where dateEdit equals to UPDATED_DATE_EDIT
        defaultCpsRangeShouldNotBeFound("dateEdit.in=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllCpsRangesByDateEditIsNullOrNotNull() throws Exception {
        // Initialize the database
        cpsRangeRepository.saveAndFlush(cpsRange);

        // Get all the cpsRangeList where dateEdit is not null
        defaultCpsRangeShouldBeFound("dateEdit.specified=true");

        // Get all the cpsRangeList where dateEdit is null
        defaultCpsRangeShouldNotBeFound("dateEdit.specified=false");
    }

    @Test
    @Transactional
    public void getAllCpsRangesByCreatorIsEqualToSomething() throws Exception {
        // Initialize the database
        cpsRangeRepository.saveAndFlush(cpsRange);

        // Get all the cpsRangeList where creator equals to DEFAULT_CREATOR
        defaultCpsRangeShouldBeFound("creator.equals=" + DEFAULT_CREATOR);

        // Get all the cpsRangeList where creator equals to UPDATED_CREATOR
        defaultCpsRangeShouldNotBeFound("creator.equals=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllCpsRangesByCreatorIsInShouldWork() throws Exception {
        // Initialize the database
        cpsRangeRepository.saveAndFlush(cpsRange);

        // Get all the cpsRangeList where creator in DEFAULT_CREATOR or UPDATED_CREATOR
        defaultCpsRangeShouldBeFound("creator.in=" + DEFAULT_CREATOR + "," + UPDATED_CREATOR);

        // Get all the cpsRangeList where creator equals to UPDATED_CREATOR
        defaultCpsRangeShouldNotBeFound("creator.in=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllCpsRangesByCreatorIsNullOrNotNull() throws Exception {
        // Initialize the database
        cpsRangeRepository.saveAndFlush(cpsRange);

        // Get all the cpsRangeList where creator is not null
        defaultCpsRangeShouldBeFound("creator.specified=true");

        // Get all the cpsRangeList where creator is null
        defaultCpsRangeShouldNotBeFound("creator.specified=false");
    }

    @Test
    @Transactional
    public void getAllCpsRangesByEditorIsEqualToSomething() throws Exception {
        // Initialize the database
        cpsRangeRepository.saveAndFlush(cpsRange);

        // Get all the cpsRangeList where editor equals to DEFAULT_EDITOR
        defaultCpsRangeShouldBeFound("editor.equals=" + DEFAULT_EDITOR);

        // Get all the cpsRangeList where editor equals to UPDATED_EDITOR
        defaultCpsRangeShouldNotBeFound("editor.equals=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllCpsRangesByEditorIsInShouldWork() throws Exception {
        // Initialize the database
        cpsRangeRepository.saveAndFlush(cpsRange);

        // Get all the cpsRangeList where editor in DEFAULT_EDITOR or UPDATED_EDITOR
        defaultCpsRangeShouldBeFound("editor.in=" + DEFAULT_EDITOR + "," + UPDATED_EDITOR);

        // Get all the cpsRangeList where editor equals to UPDATED_EDITOR
        defaultCpsRangeShouldNotBeFound("editor.in=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllCpsRangesByEditorIsNullOrNotNull() throws Exception {
        // Initialize the database
        cpsRangeRepository.saveAndFlush(cpsRange);

        // Get all the cpsRangeList where editor is not null
        defaultCpsRangeShouldBeFound("editor.specified=true");

        // Get all the cpsRangeList where editor is null
        defaultCpsRangeShouldNotBeFound("editor.specified=false");
    }

    @Test
    @Transactional
    public void getAllCpsRangesByIdCpsIsEqualToSomething() throws Exception {
        // Get already existing entity
        Cps idCps = cpsRange.getIdCps();
        cpsRangeRepository.saveAndFlush(cpsRange);
        Long idCpsId = idCps.getId();

        // Get all the cpsRangeList where idCps equals to idCpsId
        defaultCpsRangeShouldBeFound("idCpsId.equals=" + idCpsId);

        // Get all the cpsRangeList where idCps equals to idCpsId + 1
        defaultCpsRangeShouldNotBeFound("idCpsId.equals=" + (idCpsId + 1));
    }


    @Test
    @Transactional
    public void getAllCpsRangesByIdPipelineSectionIsEqualToSomething() throws Exception {
        // Get already existing entity
        PipelineSection idPipelineSection = cpsRange.getIdPipelineSection();
        cpsRangeRepository.saveAndFlush(cpsRange);
        Long idPipelineSectionId = idPipelineSection.getId();

        // Get all the cpsRangeList where idPipelineSection equals to idPipelineSectionId
        defaultCpsRangeShouldBeFound("idPipelineSectionId.equals=" + idPipelineSectionId);

        // Get all the cpsRangeList where idPipelineSection equals to idPipelineSectionId + 1
        defaultCpsRangeShouldNotBeFound("idPipelineSectionId.equals=" + (idPipelineSectionId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultCpsRangeShouldBeFound(String filter) throws Exception {
        restCpsRangeMockMvc.perform(get("/api/cps-ranges?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cpsRange.getId().intValue())))
            .andExpect(jsonPath("$.[*].kpStart").value(hasItem(DEFAULT_KP_START.intValue())))
            .andExpect(jsonPath("$.[*].kpEnd").value(hasItem(DEFAULT_KP_END.intValue())))
            .andExpect(jsonPath("$.[*].dateFrom").value(hasItem(DEFAULT_DATE_FROM.toString())))
            .andExpect(jsonPath("$.[*].dateTo").value(hasItem(DEFAULT_DATE_TO.toString())))
            .andExpect(jsonPath("$.[*].dateCreate").value(hasItem(DEFAULT_DATE_CREATE.toString())))
            .andExpect(jsonPath("$.[*].dateEdit").value(hasItem(DEFAULT_DATE_EDIT.toString())))
            .andExpect(jsonPath("$.[*].creator").value(hasItem(DEFAULT_CREATOR)))
            .andExpect(jsonPath("$.[*].editor").value(hasItem(DEFAULT_EDITOR)));

        // Check, that the count call also returns 1
        restCpsRangeMockMvc.perform(get("/api/cps-ranges/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultCpsRangeShouldNotBeFound(String filter) throws Exception {
        restCpsRangeMockMvc.perform(get("/api/cps-ranges?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restCpsRangeMockMvc.perform(get("/api/cps-ranges/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingCpsRange() throws Exception {
        // Get the cpsRange
        restCpsRangeMockMvc.perform(get("/api/cps-ranges/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCpsRange() throws Exception {
        // Initialize the database
        cpsRangeRepository.saveAndFlush(cpsRange);

        int databaseSizeBeforeUpdate = cpsRangeRepository.findAll().size();

        // Update the cpsRange
        CpsRange updatedCpsRange = cpsRangeRepository.findById(cpsRange.getId()).get();
        // Disconnect from session so that the updates on updatedCpsRange are not directly saved in db
        em.detach(updatedCpsRange);
        updatedCpsRange
            .kpStart(UPDATED_KP_START)
            .kpEnd(UPDATED_KP_END)
            .dateFrom(UPDATED_DATE_FROM)
            .dateTo(UPDATED_DATE_TO)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        CpsRangeDTO cpsRangeDTO = cpsRangeMapper.toDto(updatedCpsRange);

        restCpsRangeMockMvc.perform(put("/api/cps-ranges")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cpsRangeDTO)))
            .andExpect(status().isOk());

        // Validate the CpsRange in the database
        List<CpsRange> cpsRangeList = cpsRangeRepository.findAll();
        assertThat(cpsRangeList).hasSize(databaseSizeBeforeUpdate);
        CpsRange testCpsRange = cpsRangeList.get(cpsRangeList.size() - 1);
        assertThat(testCpsRange.getKpStart()).isEqualTo(UPDATED_KP_START);
        assertThat(testCpsRange.getKpEnd()).isEqualTo(UPDATED_KP_END);
        assertThat(testCpsRange.getDateFrom()).isEqualTo(UPDATED_DATE_FROM);
        assertThat(testCpsRange.getDateTo()).isEqualTo(UPDATED_DATE_TO);
        assertThat(testCpsRange.getDateCreate()).isEqualTo(UPDATED_DATE_CREATE);
        assertThat(testCpsRange.getDateEdit()).isEqualTo(UPDATED_DATE_EDIT);
        assertThat(testCpsRange.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testCpsRange.getEditor()).isEqualTo(UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void updateNonExistingCpsRange() throws Exception {
        int databaseSizeBeforeUpdate = cpsRangeRepository.findAll().size();

        // Create the CpsRange
        CpsRangeDTO cpsRangeDTO = cpsRangeMapper.toDto(cpsRange);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCpsRangeMockMvc.perform(put("/api/cps-ranges")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cpsRangeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CpsRange in the database
        List<CpsRange> cpsRangeList = cpsRangeRepository.findAll();
        assertThat(cpsRangeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCpsRange() throws Exception {
        // Initialize the database
        cpsRangeRepository.saveAndFlush(cpsRange);

        int databaseSizeBeforeDelete = cpsRangeRepository.findAll().size();

        // Delete the cpsRange
        restCpsRangeMockMvc.perform(delete("/api/cps-ranges/{id}", cpsRange.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<CpsRange> cpsRangeList = cpsRangeRepository.findAll();
        assertThat(cpsRangeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CpsRange.class);
        CpsRange cpsRange1 = new CpsRange();
        cpsRange1.setId(1L);
        CpsRange cpsRange2 = new CpsRange();
        cpsRange2.setId(cpsRange1.getId());
        assertThat(cpsRange1).isEqualTo(cpsRange2);
        cpsRange2.setId(2L);
        assertThat(cpsRange1).isNotEqualTo(cpsRange2);
        cpsRange1.setId(null);
        assertThat(cpsRange1).isNotEqualTo(cpsRange2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CpsRangeDTO.class);
        CpsRangeDTO cpsRangeDTO1 = new CpsRangeDTO();
        cpsRangeDTO1.setId(1L);
        CpsRangeDTO cpsRangeDTO2 = new CpsRangeDTO();
        assertThat(cpsRangeDTO1).isNotEqualTo(cpsRangeDTO2);
        cpsRangeDTO2.setId(cpsRangeDTO1.getId());
        assertThat(cpsRangeDTO1).isEqualTo(cpsRangeDTO2);
        cpsRangeDTO2.setId(2L);
        assertThat(cpsRangeDTO1).isNotEqualTo(cpsRangeDTO2);
        cpsRangeDTO1.setId(null);
        assertThat(cpsRangeDTO1).isNotEqualTo(cpsRangeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(cpsRangeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(cpsRangeMapper.fromId(null)).isNull();
    }
}
