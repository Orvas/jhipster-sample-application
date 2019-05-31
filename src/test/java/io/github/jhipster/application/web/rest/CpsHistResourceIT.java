package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.CpsHist;
import io.github.jhipster.application.domain.Cps;
import io.github.jhipster.application.domain.PipelineSection;
import io.github.jhipster.application.domain.ListObjectStatus;
import io.github.jhipster.application.repository.CpsHistRepository;
import io.github.jhipster.application.service.CpsHistService;
import io.github.jhipster.application.service.dto.CpsHistDTO;
import io.github.jhipster.application.service.mapper.CpsHistMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.CpsHistCriteria;
import io.github.jhipster.application.service.CpsHistQueryService;

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
 * Integration tests for the {@Link CpsHistResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class CpsHistResourceIT {

    private static final LocalDate DEFAULT_DATE_FROM = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_FROM = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATE_TO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_TO = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_ID_WRK = 1L;
    private static final Long UPDATED_ID_WRK = 2L;

    private static final BigDecimal DEFAULT_CURRENT = new BigDecimal(1);
    private static final BigDecimal UPDATED_CURRENT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_POTENTIAL = new BigDecimal(1);
    private static final BigDecimal UPDATED_POTENTIAL = new BigDecimal(2);

    private static final BigDecimal DEFAULT_DOWNTIME = new BigDecimal(1);
    private static final BigDecimal UPDATED_DOWNTIME = new BigDecimal(2);

    private static final String DEFAULT_COORD = "AAAAAAAAAA";
    private static final String UPDATED_COORD = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_KP_INST = new BigDecimal(1);
    private static final BigDecimal UPDATED_KP_INST = new BigDecimal(2);

    private static final Integer DEFAULT_IS_CURRENT_FLAG = 1;
    private static final Integer UPDATED_IS_CURRENT_FLAG = 2;

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

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
    private CpsHistRepository cpsHistRepository;

    @Autowired
    private CpsHistMapper cpsHistMapper;

    @Autowired
    private CpsHistService cpsHistService;

    @Autowired
    private CpsHistQueryService cpsHistQueryService;

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

    private MockMvc restCpsHistMockMvc;

    private CpsHist cpsHist;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CpsHistResource cpsHistResource = new CpsHistResource(cpsHistService, cpsHistQueryService);
        this.restCpsHistMockMvc = MockMvcBuilders.standaloneSetup(cpsHistResource)
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
    public static CpsHist createEntity(EntityManager em) {
        CpsHist cpsHist = new CpsHist()
            .dateFrom(DEFAULT_DATE_FROM)
            .dateTo(DEFAULT_DATE_TO)
            .idWrk(DEFAULT_ID_WRK)
            .current(DEFAULT_CURRENT)
            .potential(DEFAULT_POTENTIAL)
            .downtime(DEFAULT_DOWNTIME)
            .coord(DEFAULT_COORD)
            .kpInst(DEFAULT_KP_INST)
            .isCurrentFlag(DEFAULT_IS_CURRENT_FLAG)
            .description(DEFAULT_DESCRIPTION)
            .comment(DEFAULT_COMMENT)
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
        cpsHist.setId(cps);
        // Add required entity
        PipelineSection pipelineSection;
        if (TestUtil.findAll(em, PipelineSection.class).isEmpty()) {
            pipelineSection = PipelineSectionResourceIT.createEntity(em);
            em.persist(pipelineSection);
            em.flush();
        } else {
            pipelineSection = TestUtil.findAll(em, PipelineSection.class).get(0);
        }
        cpsHist.setIdPipelineSection(pipelineSection);
        // Add required entity
        ListObjectStatus listObjectStatus;
        if (TestUtil.findAll(em, ListObjectStatus.class).isEmpty()) {
            listObjectStatus = ListObjectStatusResourceIT.createEntity(em);
            em.persist(listObjectStatus);
            em.flush();
        } else {
            listObjectStatus = TestUtil.findAll(em, ListObjectStatus.class).get(0);
        }
        cpsHist.setIdStatus(listObjectStatus);
        return cpsHist;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CpsHist createUpdatedEntity(EntityManager em) {
        CpsHist cpsHist = new CpsHist()
            .dateFrom(UPDATED_DATE_FROM)
            .dateTo(UPDATED_DATE_TO)
            .idWrk(UPDATED_ID_WRK)
            .current(UPDATED_CURRENT)
            .potential(UPDATED_POTENTIAL)
            .downtime(UPDATED_DOWNTIME)
            .coord(UPDATED_COORD)
            .kpInst(UPDATED_KP_INST)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .comment(UPDATED_COMMENT)
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
        cpsHist.setId(cps);
        // Add required entity
        PipelineSection pipelineSection;
        if (TestUtil.findAll(em, PipelineSection.class).isEmpty()) {
            pipelineSection = PipelineSectionResourceIT.createUpdatedEntity(em);
            em.persist(pipelineSection);
            em.flush();
        } else {
            pipelineSection = TestUtil.findAll(em, PipelineSection.class).get(0);
        }
        cpsHist.setIdPipelineSection(pipelineSection);
        // Add required entity
        ListObjectStatus listObjectStatus;
        if (TestUtil.findAll(em, ListObjectStatus.class).isEmpty()) {
            listObjectStatus = ListObjectStatusResourceIT.createUpdatedEntity(em);
            em.persist(listObjectStatus);
            em.flush();
        } else {
            listObjectStatus = TestUtil.findAll(em, ListObjectStatus.class).get(0);
        }
        cpsHist.setIdStatus(listObjectStatus);
        return cpsHist;
    }

    @BeforeEach
    public void initTest() {
        cpsHist = createEntity(em);
    }

    @Test
    @Transactional
    public void createCpsHist() throws Exception {
        int databaseSizeBeforeCreate = cpsHistRepository.findAll().size();

        // Create the CpsHist
        CpsHistDTO cpsHistDTO = cpsHistMapper.toDto(cpsHist);
        restCpsHistMockMvc.perform(post("/api/cps-hists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cpsHistDTO)))
            .andExpect(status().isCreated());

        // Validate the CpsHist in the database
        List<CpsHist> cpsHistList = cpsHistRepository.findAll();
        assertThat(cpsHistList).hasSize(databaseSizeBeforeCreate + 1);
        CpsHist testCpsHist = cpsHistList.get(cpsHistList.size() - 1);
        assertThat(testCpsHist.getDateFrom()).isEqualTo(DEFAULT_DATE_FROM);
        assertThat(testCpsHist.getDateTo()).isEqualTo(DEFAULT_DATE_TO);
        assertThat(testCpsHist.getIdWrk()).isEqualTo(DEFAULT_ID_WRK);
        assertThat(testCpsHist.getCurrent()).isEqualTo(DEFAULT_CURRENT);
        assertThat(testCpsHist.getPotential()).isEqualTo(DEFAULT_POTENTIAL);
        assertThat(testCpsHist.getDowntime()).isEqualTo(DEFAULT_DOWNTIME);
        assertThat(testCpsHist.getCoord()).isEqualTo(DEFAULT_COORD);
        assertThat(testCpsHist.getKpInst()).isEqualTo(DEFAULT_KP_INST);
        assertThat(testCpsHist.getIsCurrentFlag()).isEqualTo(DEFAULT_IS_CURRENT_FLAG);
        assertThat(testCpsHist.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testCpsHist.getComment()).isEqualTo(DEFAULT_COMMENT);
        assertThat(testCpsHist.getDateCreate()).isEqualTo(DEFAULT_DATE_CREATE);
        assertThat(testCpsHist.getDateEdit()).isEqualTo(DEFAULT_DATE_EDIT);
        assertThat(testCpsHist.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testCpsHist.getEditor()).isEqualTo(DEFAULT_EDITOR);
    }

    @Test
    @Transactional
    public void createCpsHistWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = cpsHistRepository.findAll().size();

        // Create the CpsHist with an existing ID
        cpsHist.setId(1L);
        CpsHistDTO cpsHistDTO = cpsHistMapper.toDto(cpsHist);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCpsHistMockMvc.perform(post("/api/cps-hists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cpsHistDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CpsHist in the database
        List<CpsHist> cpsHistList = cpsHistRepository.findAll();
        assertThat(cpsHistList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkDateFromIsRequired() throws Exception {
        int databaseSizeBeforeTest = cpsHistRepository.findAll().size();
        // set the field null
        cpsHist.setDateFrom(null);

        // Create the CpsHist, which fails.
        CpsHistDTO cpsHistDTO = cpsHistMapper.toDto(cpsHist);

        restCpsHistMockMvc.perform(post("/api/cps-hists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cpsHistDTO)))
            .andExpect(status().isBadRequest());

        List<CpsHist> cpsHistList = cpsHistRepository.findAll();
        assertThat(cpsHistList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDateToIsRequired() throws Exception {
        int databaseSizeBeforeTest = cpsHistRepository.findAll().size();
        // set the field null
        cpsHist.setDateTo(null);

        // Create the CpsHist, which fails.
        CpsHistDTO cpsHistDTO = cpsHistMapper.toDto(cpsHist);

        restCpsHistMockMvc.perform(post("/api/cps-hists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cpsHistDTO)))
            .andExpect(status().isBadRequest());

        List<CpsHist> cpsHistList = cpsHistRepository.findAll();
        assertThat(cpsHistList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsCurrentFlagIsRequired() throws Exception {
        int databaseSizeBeforeTest = cpsHistRepository.findAll().size();
        // set the field null
        cpsHist.setIsCurrentFlag(null);

        // Create the CpsHist, which fails.
        CpsHistDTO cpsHistDTO = cpsHistMapper.toDto(cpsHist);

        restCpsHistMockMvc.perform(post("/api/cps-hists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cpsHistDTO)))
            .andExpect(status().isBadRequest());

        List<CpsHist> cpsHistList = cpsHistRepository.findAll();
        assertThat(cpsHistList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCpsHists() throws Exception {
        // Initialize the database
        cpsHistRepository.saveAndFlush(cpsHist);

        // Get all the cpsHistList
        restCpsHistMockMvc.perform(get("/api/cps-hists?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cpsHist.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateFrom").value(hasItem(DEFAULT_DATE_FROM.toString())))
            .andExpect(jsonPath("$.[*].dateTo").value(hasItem(DEFAULT_DATE_TO.toString())))
            .andExpect(jsonPath("$.[*].idWrk").value(hasItem(DEFAULT_ID_WRK.intValue())))
            .andExpect(jsonPath("$.[*].current").value(hasItem(DEFAULT_CURRENT.intValue())))
            .andExpect(jsonPath("$.[*].potential").value(hasItem(DEFAULT_POTENTIAL.intValue())))
            .andExpect(jsonPath("$.[*].downtime").value(hasItem(DEFAULT_DOWNTIME.intValue())))
            .andExpect(jsonPath("$.[*].coord").value(hasItem(DEFAULT_COORD.toString())))
            .andExpect(jsonPath("$.[*].kpInst").value(hasItem(DEFAULT_KP_INST.intValue())))
            .andExpect(jsonPath("$.[*].isCurrentFlag").value(hasItem(DEFAULT_IS_CURRENT_FLAG)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].comment").value(hasItem(DEFAULT_COMMENT.toString())))
            .andExpect(jsonPath("$.[*].dateCreate").value(hasItem(DEFAULT_DATE_CREATE.toString())))
            .andExpect(jsonPath("$.[*].dateEdit").value(hasItem(DEFAULT_DATE_EDIT.toString())))
            .andExpect(jsonPath("$.[*].creator").value(hasItem(DEFAULT_CREATOR.toString())))
            .andExpect(jsonPath("$.[*].editor").value(hasItem(DEFAULT_EDITOR.toString())));
    }
    
    @Test
    @Transactional
    public void getCpsHist() throws Exception {
        // Initialize the database
        cpsHistRepository.saveAndFlush(cpsHist);

        // Get the cpsHist
        restCpsHistMockMvc.perform(get("/api/cps-hists/{id}", cpsHist.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(cpsHist.getId().intValue()))
            .andExpect(jsonPath("$.dateFrom").value(DEFAULT_DATE_FROM.toString()))
            .andExpect(jsonPath("$.dateTo").value(DEFAULT_DATE_TO.toString()))
            .andExpect(jsonPath("$.idWrk").value(DEFAULT_ID_WRK.intValue()))
            .andExpect(jsonPath("$.current").value(DEFAULT_CURRENT.intValue()))
            .andExpect(jsonPath("$.potential").value(DEFAULT_POTENTIAL.intValue()))
            .andExpect(jsonPath("$.downtime").value(DEFAULT_DOWNTIME.intValue()))
            .andExpect(jsonPath("$.coord").value(DEFAULT_COORD.toString()))
            .andExpect(jsonPath("$.kpInst").value(DEFAULT_KP_INST.intValue()))
            .andExpect(jsonPath("$.isCurrentFlag").value(DEFAULT_IS_CURRENT_FLAG))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.comment").value(DEFAULT_COMMENT.toString()))
            .andExpect(jsonPath("$.dateCreate").value(DEFAULT_DATE_CREATE.toString()))
            .andExpect(jsonPath("$.dateEdit").value(DEFAULT_DATE_EDIT.toString()))
            .andExpect(jsonPath("$.creator").value(DEFAULT_CREATOR.toString()))
            .andExpect(jsonPath("$.editor").value(DEFAULT_EDITOR.toString()));
    }

    @Test
    @Transactional
    public void getAllCpsHistsByDateFromIsEqualToSomething() throws Exception {
        // Initialize the database
        cpsHistRepository.saveAndFlush(cpsHist);

        // Get all the cpsHistList where dateFrom equals to DEFAULT_DATE_FROM
        defaultCpsHistShouldBeFound("dateFrom.equals=" + DEFAULT_DATE_FROM);

        // Get all the cpsHistList where dateFrom equals to UPDATED_DATE_FROM
        defaultCpsHistShouldNotBeFound("dateFrom.equals=" + UPDATED_DATE_FROM);
    }

    @Test
    @Transactional
    public void getAllCpsHistsByDateFromIsInShouldWork() throws Exception {
        // Initialize the database
        cpsHistRepository.saveAndFlush(cpsHist);

        // Get all the cpsHistList where dateFrom in DEFAULT_DATE_FROM or UPDATED_DATE_FROM
        defaultCpsHistShouldBeFound("dateFrom.in=" + DEFAULT_DATE_FROM + "," + UPDATED_DATE_FROM);

        // Get all the cpsHistList where dateFrom equals to UPDATED_DATE_FROM
        defaultCpsHistShouldNotBeFound("dateFrom.in=" + UPDATED_DATE_FROM);
    }

    @Test
    @Transactional
    public void getAllCpsHistsByDateFromIsNullOrNotNull() throws Exception {
        // Initialize the database
        cpsHistRepository.saveAndFlush(cpsHist);

        // Get all the cpsHistList where dateFrom is not null
        defaultCpsHistShouldBeFound("dateFrom.specified=true");

        // Get all the cpsHistList where dateFrom is null
        defaultCpsHistShouldNotBeFound("dateFrom.specified=false");
    }

    @Test
    @Transactional
    public void getAllCpsHistsByDateFromIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cpsHistRepository.saveAndFlush(cpsHist);

        // Get all the cpsHistList where dateFrom greater than or equals to DEFAULT_DATE_FROM
        defaultCpsHistShouldBeFound("dateFrom.greaterOrEqualThan=" + DEFAULT_DATE_FROM);

        // Get all the cpsHistList where dateFrom greater than or equals to UPDATED_DATE_FROM
        defaultCpsHistShouldNotBeFound("dateFrom.greaterOrEqualThan=" + UPDATED_DATE_FROM);
    }

    @Test
    @Transactional
    public void getAllCpsHistsByDateFromIsLessThanSomething() throws Exception {
        // Initialize the database
        cpsHistRepository.saveAndFlush(cpsHist);

        // Get all the cpsHistList where dateFrom less than or equals to DEFAULT_DATE_FROM
        defaultCpsHistShouldNotBeFound("dateFrom.lessThan=" + DEFAULT_DATE_FROM);

        // Get all the cpsHistList where dateFrom less than or equals to UPDATED_DATE_FROM
        defaultCpsHistShouldBeFound("dateFrom.lessThan=" + UPDATED_DATE_FROM);
    }


    @Test
    @Transactional
    public void getAllCpsHistsByDateToIsEqualToSomething() throws Exception {
        // Initialize the database
        cpsHistRepository.saveAndFlush(cpsHist);

        // Get all the cpsHistList where dateTo equals to DEFAULT_DATE_TO
        defaultCpsHistShouldBeFound("dateTo.equals=" + DEFAULT_DATE_TO);

        // Get all the cpsHistList where dateTo equals to UPDATED_DATE_TO
        defaultCpsHistShouldNotBeFound("dateTo.equals=" + UPDATED_DATE_TO);
    }

    @Test
    @Transactional
    public void getAllCpsHistsByDateToIsInShouldWork() throws Exception {
        // Initialize the database
        cpsHistRepository.saveAndFlush(cpsHist);

        // Get all the cpsHistList where dateTo in DEFAULT_DATE_TO or UPDATED_DATE_TO
        defaultCpsHistShouldBeFound("dateTo.in=" + DEFAULT_DATE_TO + "," + UPDATED_DATE_TO);

        // Get all the cpsHistList where dateTo equals to UPDATED_DATE_TO
        defaultCpsHistShouldNotBeFound("dateTo.in=" + UPDATED_DATE_TO);
    }

    @Test
    @Transactional
    public void getAllCpsHistsByDateToIsNullOrNotNull() throws Exception {
        // Initialize the database
        cpsHistRepository.saveAndFlush(cpsHist);

        // Get all the cpsHistList where dateTo is not null
        defaultCpsHistShouldBeFound("dateTo.specified=true");

        // Get all the cpsHistList where dateTo is null
        defaultCpsHistShouldNotBeFound("dateTo.specified=false");
    }

    @Test
    @Transactional
    public void getAllCpsHistsByDateToIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cpsHistRepository.saveAndFlush(cpsHist);

        // Get all the cpsHistList where dateTo greater than or equals to DEFAULT_DATE_TO
        defaultCpsHistShouldBeFound("dateTo.greaterOrEqualThan=" + DEFAULT_DATE_TO);

        // Get all the cpsHistList where dateTo greater than or equals to UPDATED_DATE_TO
        defaultCpsHistShouldNotBeFound("dateTo.greaterOrEqualThan=" + UPDATED_DATE_TO);
    }

    @Test
    @Transactional
    public void getAllCpsHistsByDateToIsLessThanSomething() throws Exception {
        // Initialize the database
        cpsHistRepository.saveAndFlush(cpsHist);

        // Get all the cpsHistList where dateTo less than or equals to DEFAULT_DATE_TO
        defaultCpsHistShouldNotBeFound("dateTo.lessThan=" + DEFAULT_DATE_TO);

        // Get all the cpsHistList where dateTo less than or equals to UPDATED_DATE_TO
        defaultCpsHistShouldBeFound("dateTo.lessThan=" + UPDATED_DATE_TO);
    }


    @Test
    @Transactional
    public void getAllCpsHistsByIdWrkIsEqualToSomething() throws Exception {
        // Initialize the database
        cpsHistRepository.saveAndFlush(cpsHist);

        // Get all the cpsHistList where idWrk equals to DEFAULT_ID_WRK
        defaultCpsHistShouldBeFound("idWrk.equals=" + DEFAULT_ID_WRK);

        // Get all the cpsHistList where idWrk equals to UPDATED_ID_WRK
        defaultCpsHistShouldNotBeFound("idWrk.equals=" + UPDATED_ID_WRK);
    }

    @Test
    @Transactional
    public void getAllCpsHistsByIdWrkIsInShouldWork() throws Exception {
        // Initialize the database
        cpsHistRepository.saveAndFlush(cpsHist);

        // Get all the cpsHistList where idWrk in DEFAULT_ID_WRK or UPDATED_ID_WRK
        defaultCpsHistShouldBeFound("idWrk.in=" + DEFAULT_ID_WRK + "," + UPDATED_ID_WRK);

        // Get all the cpsHistList where idWrk equals to UPDATED_ID_WRK
        defaultCpsHistShouldNotBeFound("idWrk.in=" + UPDATED_ID_WRK);
    }

    @Test
    @Transactional
    public void getAllCpsHistsByIdWrkIsNullOrNotNull() throws Exception {
        // Initialize the database
        cpsHistRepository.saveAndFlush(cpsHist);

        // Get all the cpsHistList where idWrk is not null
        defaultCpsHistShouldBeFound("idWrk.specified=true");

        // Get all the cpsHistList where idWrk is null
        defaultCpsHistShouldNotBeFound("idWrk.specified=false");
    }

    @Test
    @Transactional
    public void getAllCpsHistsByIdWrkIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cpsHistRepository.saveAndFlush(cpsHist);

        // Get all the cpsHistList where idWrk greater than or equals to DEFAULT_ID_WRK
        defaultCpsHistShouldBeFound("idWrk.greaterOrEqualThan=" + DEFAULT_ID_WRK);

        // Get all the cpsHistList where idWrk greater than or equals to UPDATED_ID_WRK
        defaultCpsHistShouldNotBeFound("idWrk.greaterOrEqualThan=" + UPDATED_ID_WRK);
    }

    @Test
    @Transactional
    public void getAllCpsHistsByIdWrkIsLessThanSomething() throws Exception {
        // Initialize the database
        cpsHistRepository.saveAndFlush(cpsHist);

        // Get all the cpsHistList where idWrk less than or equals to DEFAULT_ID_WRK
        defaultCpsHistShouldNotBeFound("idWrk.lessThan=" + DEFAULT_ID_WRK);

        // Get all the cpsHistList where idWrk less than or equals to UPDATED_ID_WRK
        defaultCpsHistShouldBeFound("idWrk.lessThan=" + UPDATED_ID_WRK);
    }


    @Test
    @Transactional
    public void getAllCpsHistsByCurrentIsEqualToSomething() throws Exception {
        // Initialize the database
        cpsHistRepository.saveAndFlush(cpsHist);

        // Get all the cpsHistList where current equals to DEFAULT_CURRENT
        defaultCpsHistShouldBeFound("current.equals=" + DEFAULT_CURRENT);

        // Get all the cpsHistList where current equals to UPDATED_CURRENT
        defaultCpsHistShouldNotBeFound("current.equals=" + UPDATED_CURRENT);
    }

    @Test
    @Transactional
    public void getAllCpsHistsByCurrentIsInShouldWork() throws Exception {
        // Initialize the database
        cpsHistRepository.saveAndFlush(cpsHist);

        // Get all the cpsHistList where current in DEFAULT_CURRENT or UPDATED_CURRENT
        defaultCpsHistShouldBeFound("current.in=" + DEFAULT_CURRENT + "," + UPDATED_CURRENT);

        // Get all the cpsHistList where current equals to UPDATED_CURRENT
        defaultCpsHistShouldNotBeFound("current.in=" + UPDATED_CURRENT);
    }

    @Test
    @Transactional
    public void getAllCpsHistsByCurrentIsNullOrNotNull() throws Exception {
        // Initialize the database
        cpsHistRepository.saveAndFlush(cpsHist);

        // Get all the cpsHistList where current is not null
        defaultCpsHistShouldBeFound("current.specified=true");

        // Get all the cpsHistList where current is null
        defaultCpsHistShouldNotBeFound("current.specified=false");
    }

    @Test
    @Transactional
    public void getAllCpsHistsByPotentialIsEqualToSomething() throws Exception {
        // Initialize the database
        cpsHistRepository.saveAndFlush(cpsHist);

        // Get all the cpsHistList where potential equals to DEFAULT_POTENTIAL
        defaultCpsHistShouldBeFound("potential.equals=" + DEFAULT_POTENTIAL);

        // Get all the cpsHistList where potential equals to UPDATED_POTENTIAL
        defaultCpsHistShouldNotBeFound("potential.equals=" + UPDATED_POTENTIAL);
    }

    @Test
    @Transactional
    public void getAllCpsHistsByPotentialIsInShouldWork() throws Exception {
        // Initialize the database
        cpsHistRepository.saveAndFlush(cpsHist);

        // Get all the cpsHistList where potential in DEFAULT_POTENTIAL or UPDATED_POTENTIAL
        defaultCpsHistShouldBeFound("potential.in=" + DEFAULT_POTENTIAL + "," + UPDATED_POTENTIAL);

        // Get all the cpsHistList where potential equals to UPDATED_POTENTIAL
        defaultCpsHistShouldNotBeFound("potential.in=" + UPDATED_POTENTIAL);
    }

    @Test
    @Transactional
    public void getAllCpsHistsByPotentialIsNullOrNotNull() throws Exception {
        // Initialize the database
        cpsHistRepository.saveAndFlush(cpsHist);

        // Get all the cpsHistList where potential is not null
        defaultCpsHistShouldBeFound("potential.specified=true");

        // Get all the cpsHistList where potential is null
        defaultCpsHistShouldNotBeFound("potential.specified=false");
    }

    @Test
    @Transactional
    public void getAllCpsHistsByDowntimeIsEqualToSomething() throws Exception {
        // Initialize the database
        cpsHistRepository.saveAndFlush(cpsHist);

        // Get all the cpsHistList where downtime equals to DEFAULT_DOWNTIME
        defaultCpsHistShouldBeFound("downtime.equals=" + DEFAULT_DOWNTIME);

        // Get all the cpsHistList where downtime equals to UPDATED_DOWNTIME
        defaultCpsHistShouldNotBeFound("downtime.equals=" + UPDATED_DOWNTIME);
    }

    @Test
    @Transactional
    public void getAllCpsHistsByDowntimeIsInShouldWork() throws Exception {
        // Initialize the database
        cpsHistRepository.saveAndFlush(cpsHist);

        // Get all the cpsHistList where downtime in DEFAULT_DOWNTIME or UPDATED_DOWNTIME
        defaultCpsHistShouldBeFound("downtime.in=" + DEFAULT_DOWNTIME + "," + UPDATED_DOWNTIME);

        // Get all the cpsHistList where downtime equals to UPDATED_DOWNTIME
        defaultCpsHistShouldNotBeFound("downtime.in=" + UPDATED_DOWNTIME);
    }

    @Test
    @Transactional
    public void getAllCpsHistsByDowntimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        cpsHistRepository.saveAndFlush(cpsHist);

        // Get all the cpsHistList where downtime is not null
        defaultCpsHistShouldBeFound("downtime.specified=true");

        // Get all the cpsHistList where downtime is null
        defaultCpsHistShouldNotBeFound("downtime.specified=false");
    }

    @Test
    @Transactional
    public void getAllCpsHistsByCoordIsEqualToSomething() throws Exception {
        // Initialize the database
        cpsHistRepository.saveAndFlush(cpsHist);

        // Get all the cpsHistList where coord equals to DEFAULT_COORD
        defaultCpsHistShouldBeFound("coord.equals=" + DEFAULT_COORD);

        // Get all the cpsHistList where coord equals to UPDATED_COORD
        defaultCpsHistShouldNotBeFound("coord.equals=" + UPDATED_COORD);
    }

    @Test
    @Transactional
    public void getAllCpsHistsByCoordIsInShouldWork() throws Exception {
        // Initialize the database
        cpsHistRepository.saveAndFlush(cpsHist);

        // Get all the cpsHistList where coord in DEFAULT_COORD or UPDATED_COORD
        defaultCpsHistShouldBeFound("coord.in=" + DEFAULT_COORD + "," + UPDATED_COORD);

        // Get all the cpsHistList where coord equals to UPDATED_COORD
        defaultCpsHistShouldNotBeFound("coord.in=" + UPDATED_COORD);
    }

    @Test
    @Transactional
    public void getAllCpsHistsByCoordIsNullOrNotNull() throws Exception {
        // Initialize the database
        cpsHistRepository.saveAndFlush(cpsHist);

        // Get all the cpsHistList where coord is not null
        defaultCpsHistShouldBeFound("coord.specified=true");

        // Get all the cpsHistList where coord is null
        defaultCpsHistShouldNotBeFound("coord.specified=false");
    }

    @Test
    @Transactional
    public void getAllCpsHistsByKpInstIsEqualToSomething() throws Exception {
        // Initialize the database
        cpsHistRepository.saveAndFlush(cpsHist);

        // Get all the cpsHistList where kpInst equals to DEFAULT_KP_INST
        defaultCpsHistShouldBeFound("kpInst.equals=" + DEFAULT_KP_INST);

        // Get all the cpsHistList where kpInst equals to UPDATED_KP_INST
        defaultCpsHistShouldNotBeFound("kpInst.equals=" + UPDATED_KP_INST);
    }

    @Test
    @Transactional
    public void getAllCpsHistsByKpInstIsInShouldWork() throws Exception {
        // Initialize the database
        cpsHistRepository.saveAndFlush(cpsHist);

        // Get all the cpsHistList where kpInst in DEFAULT_KP_INST or UPDATED_KP_INST
        defaultCpsHistShouldBeFound("kpInst.in=" + DEFAULT_KP_INST + "," + UPDATED_KP_INST);

        // Get all the cpsHistList where kpInst equals to UPDATED_KP_INST
        defaultCpsHistShouldNotBeFound("kpInst.in=" + UPDATED_KP_INST);
    }

    @Test
    @Transactional
    public void getAllCpsHistsByKpInstIsNullOrNotNull() throws Exception {
        // Initialize the database
        cpsHistRepository.saveAndFlush(cpsHist);

        // Get all the cpsHistList where kpInst is not null
        defaultCpsHistShouldBeFound("kpInst.specified=true");

        // Get all the cpsHistList where kpInst is null
        defaultCpsHistShouldNotBeFound("kpInst.specified=false");
    }

    @Test
    @Transactional
    public void getAllCpsHistsByIsCurrentFlagIsEqualToSomething() throws Exception {
        // Initialize the database
        cpsHistRepository.saveAndFlush(cpsHist);

        // Get all the cpsHistList where isCurrentFlag equals to DEFAULT_IS_CURRENT_FLAG
        defaultCpsHistShouldBeFound("isCurrentFlag.equals=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the cpsHistList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultCpsHistShouldNotBeFound("isCurrentFlag.equals=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllCpsHistsByIsCurrentFlagIsInShouldWork() throws Exception {
        // Initialize the database
        cpsHistRepository.saveAndFlush(cpsHist);

        // Get all the cpsHistList where isCurrentFlag in DEFAULT_IS_CURRENT_FLAG or UPDATED_IS_CURRENT_FLAG
        defaultCpsHistShouldBeFound("isCurrentFlag.in=" + DEFAULT_IS_CURRENT_FLAG + "," + UPDATED_IS_CURRENT_FLAG);

        // Get all the cpsHistList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultCpsHistShouldNotBeFound("isCurrentFlag.in=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllCpsHistsByIsCurrentFlagIsNullOrNotNull() throws Exception {
        // Initialize the database
        cpsHistRepository.saveAndFlush(cpsHist);

        // Get all the cpsHistList where isCurrentFlag is not null
        defaultCpsHistShouldBeFound("isCurrentFlag.specified=true");

        // Get all the cpsHistList where isCurrentFlag is null
        defaultCpsHistShouldNotBeFound("isCurrentFlag.specified=false");
    }

    @Test
    @Transactional
    public void getAllCpsHistsByIsCurrentFlagIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        cpsHistRepository.saveAndFlush(cpsHist);

        // Get all the cpsHistList where isCurrentFlag greater than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultCpsHistShouldBeFound("isCurrentFlag.greaterOrEqualThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the cpsHistList where isCurrentFlag greater than or equals to UPDATED_IS_CURRENT_FLAG
        defaultCpsHistShouldNotBeFound("isCurrentFlag.greaterOrEqualThan=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllCpsHistsByIsCurrentFlagIsLessThanSomething() throws Exception {
        // Initialize the database
        cpsHistRepository.saveAndFlush(cpsHist);

        // Get all the cpsHistList where isCurrentFlag less than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultCpsHistShouldNotBeFound("isCurrentFlag.lessThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the cpsHistList where isCurrentFlag less than or equals to UPDATED_IS_CURRENT_FLAG
        defaultCpsHistShouldBeFound("isCurrentFlag.lessThan=" + UPDATED_IS_CURRENT_FLAG);
    }


    @Test
    @Transactional
    public void getAllCpsHistsByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        cpsHistRepository.saveAndFlush(cpsHist);

        // Get all the cpsHistList where description equals to DEFAULT_DESCRIPTION
        defaultCpsHistShouldBeFound("description.equals=" + DEFAULT_DESCRIPTION);

        // Get all the cpsHistList where description equals to UPDATED_DESCRIPTION
        defaultCpsHistShouldNotBeFound("description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllCpsHistsByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        cpsHistRepository.saveAndFlush(cpsHist);

        // Get all the cpsHistList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
        defaultCpsHistShouldBeFound("description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION);

        // Get all the cpsHistList where description equals to UPDATED_DESCRIPTION
        defaultCpsHistShouldNotBeFound("description.in=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllCpsHistsByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        cpsHistRepository.saveAndFlush(cpsHist);

        // Get all the cpsHistList where description is not null
        defaultCpsHistShouldBeFound("description.specified=true");

        // Get all the cpsHistList where description is null
        defaultCpsHistShouldNotBeFound("description.specified=false");
    }

    @Test
    @Transactional
    public void getAllCpsHistsByCommentIsEqualToSomething() throws Exception {
        // Initialize the database
        cpsHistRepository.saveAndFlush(cpsHist);

        // Get all the cpsHistList where comment equals to DEFAULT_COMMENT
        defaultCpsHistShouldBeFound("comment.equals=" + DEFAULT_COMMENT);

        // Get all the cpsHistList where comment equals to UPDATED_COMMENT
        defaultCpsHistShouldNotBeFound("comment.equals=" + UPDATED_COMMENT);
    }

    @Test
    @Transactional
    public void getAllCpsHistsByCommentIsInShouldWork() throws Exception {
        // Initialize the database
        cpsHistRepository.saveAndFlush(cpsHist);

        // Get all the cpsHistList where comment in DEFAULT_COMMENT or UPDATED_COMMENT
        defaultCpsHistShouldBeFound("comment.in=" + DEFAULT_COMMENT + "," + UPDATED_COMMENT);

        // Get all the cpsHistList where comment equals to UPDATED_COMMENT
        defaultCpsHistShouldNotBeFound("comment.in=" + UPDATED_COMMENT);
    }

    @Test
    @Transactional
    public void getAllCpsHistsByCommentIsNullOrNotNull() throws Exception {
        // Initialize the database
        cpsHistRepository.saveAndFlush(cpsHist);

        // Get all the cpsHistList where comment is not null
        defaultCpsHistShouldBeFound("comment.specified=true");

        // Get all the cpsHistList where comment is null
        defaultCpsHistShouldNotBeFound("comment.specified=false");
    }

    @Test
    @Transactional
    public void getAllCpsHistsByDateCreateIsEqualToSomething() throws Exception {
        // Initialize the database
        cpsHistRepository.saveAndFlush(cpsHist);

        // Get all the cpsHistList where dateCreate equals to DEFAULT_DATE_CREATE
        defaultCpsHistShouldBeFound("dateCreate.equals=" + DEFAULT_DATE_CREATE);

        // Get all the cpsHistList where dateCreate equals to UPDATED_DATE_CREATE
        defaultCpsHistShouldNotBeFound("dateCreate.equals=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllCpsHistsByDateCreateIsInShouldWork() throws Exception {
        // Initialize the database
        cpsHistRepository.saveAndFlush(cpsHist);

        // Get all the cpsHistList where dateCreate in DEFAULT_DATE_CREATE or UPDATED_DATE_CREATE
        defaultCpsHistShouldBeFound("dateCreate.in=" + DEFAULT_DATE_CREATE + "," + UPDATED_DATE_CREATE);

        // Get all the cpsHistList where dateCreate equals to UPDATED_DATE_CREATE
        defaultCpsHistShouldNotBeFound("dateCreate.in=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllCpsHistsByDateCreateIsNullOrNotNull() throws Exception {
        // Initialize the database
        cpsHistRepository.saveAndFlush(cpsHist);

        // Get all the cpsHistList where dateCreate is not null
        defaultCpsHistShouldBeFound("dateCreate.specified=true");

        // Get all the cpsHistList where dateCreate is null
        defaultCpsHistShouldNotBeFound("dateCreate.specified=false");
    }

    @Test
    @Transactional
    public void getAllCpsHistsByDateEditIsEqualToSomething() throws Exception {
        // Initialize the database
        cpsHistRepository.saveAndFlush(cpsHist);

        // Get all the cpsHistList where dateEdit equals to DEFAULT_DATE_EDIT
        defaultCpsHistShouldBeFound("dateEdit.equals=" + DEFAULT_DATE_EDIT);

        // Get all the cpsHistList where dateEdit equals to UPDATED_DATE_EDIT
        defaultCpsHistShouldNotBeFound("dateEdit.equals=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllCpsHistsByDateEditIsInShouldWork() throws Exception {
        // Initialize the database
        cpsHistRepository.saveAndFlush(cpsHist);

        // Get all the cpsHistList where dateEdit in DEFAULT_DATE_EDIT or UPDATED_DATE_EDIT
        defaultCpsHistShouldBeFound("dateEdit.in=" + DEFAULT_DATE_EDIT + "," + UPDATED_DATE_EDIT);

        // Get all the cpsHistList where dateEdit equals to UPDATED_DATE_EDIT
        defaultCpsHistShouldNotBeFound("dateEdit.in=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllCpsHistsByDateEditIsNullOrNotNull() throws Exception {
        // Initialize the database
        cpsHistRepository.saveAndFlush(cpsHist);

        // Get all the cpsHistList where dateEdit is not null
        defaultCpsHistShouldBeFound("dateEdit.specified=true");

        // Get all the cpsHistList where dateEdit is null
        defaultCpsHistShouldNotBeFound("dateEdit.specified=false");
    }

    @Test
    @Transactional
    public void getAllCpsHistsByCreatorIsEqualToSomething() throws Exception {
        // Initialize the database
        cpsHistRepository.saveAndFlush(cpsHist);

        // Get all the cpsHistList where creator equals to DEFAULT_CREATOR
        defaultCpsHistShouldBeFound("creator.equals=" + DEFAULT_CREATOR);

        // Get all the cpsHistList where creator equals to UPDATED_CREATOR
        defaultCpsHistShouldNotBeFound("creator.equals=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllCpsHistsByCreatorIsInShouldWork() throws Exception {
        // Initialize the database
        cpsHistRepository.saveAndFlush(cpsHist);

        // Get all the cpsHistList where creator in DEFAULT_CREATOR or UPDATED_CREATOR
        defaultCpsHistShouldBeFound("creator.in=" + DEFAULT_CREATOR + "," + UPDATED_CREATOR);

        // Get all the cpsHistList where creator equals to UPDATED_CREATOR
        defaultCpsHistShouldNotBeFound("creator.in=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllCpsHistsByCreatorIsNullOrNotNull() throws Exception {
        // Initialize the database
        cpsHistRepository.saveAndFlush(cpsHist);

        // Get all the cpsHistList where creator is not null
        defaultCpsHistShouldBeFound("creator.specified=true");

        // Get all the cpsHistList where creator is null
        defaultCpsHistShouldNotBeFound("creator.specified=false");
    }

    @Test
    @Transactional
    public void getAllCpsHistsByEditorIsEqualToSomething() throws Exception {
        // Initialize the database
        cpsHistRepository.saveAndFlush(cpsHist);

        // Get all the cpsHistList where editor equals to DEFAULT_EDITOR
        defaultCpsHistShouldBeFound("editor.equals=" + DEFAULT_EDITOR);

        // Get all the cpsHistList where editor equals to UPDATED_EDITOR
        defaultCpsHistShouldNotBeFound("editor.equals=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllCpsHistsByEditorIsInShouldWork() throws Exception {
        // Initialize the database
        cpsHistRepository.saveAndFlush(cpsHist);

        // Get all the cpsHistList where editor in DEFAULT_EDITOR or UPDATED_EDITOR
        defaultCpsHistShouldBeFound("editor.in=" + DEFAULT_EDITOR + "," + UPDATED_EDITOR);

        // Get all the cpsHistList where editor equals to UPDATED_EDITOR
        defaultCpsHistShouldNotBeFound("editor.in=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllCpsHistsByEditorIsNullOrNotNull() throws Exception {
        // Initialize the database
        cpsHistRepository.saveAndFlush(cpsHist);

        // Get all the cpsHistList where editor is not null
        defaultCpsHistShouldBeFound("editor.specified=true");

        // Get all the cpsHistList where editor is null
        defaultCpsHistShouldNotBeFound("editor.specified=false");
    }

    @Test
    @Transactional
    public void getAllCpsHistsByIdIsEqualToSomething() throws Exception {
        // Get already existing entity
        Cps id = cpsHist.getId();
        cpsHistRepository.saveAndFlush(cpsHist);
        Long idId = id.getId();

        // Get all the cpsHistList where id equals to idId
        defaultCpsHistShouldBeFound("idId.equals=" + idId);

        // Get all the cpsHistList where id equals to idId + 1
        defaultCpsHistShouldNotBeFound("idId.equals=" + (idId + 1));
    }


    @Test
    @Transactional
    public void getAllCpsHistsByIdPipelineSectionIsEqualToSomething() throws Exception {
        // Get already existing entity
        PipelineSection idPipelineSection = cpsHist.getIdPipelineSection();
        cpsHistRepository.saveAndFlush(cpsHist);
        Long idPipelineSectionId = idPipelineSection.getId();

        // Get all the cpsHistList where idPipelineSection equals to idPipelineSectionId
        defaultCpsHistShouldBeFound("idPipelineSectionId.equals=" + idPipelineSectionId);

        // Get all the cpsHistList where idPipelineSection equals to idPipelineSectionId + 1
        defaultCpsHistShouldNotBeFound("idPipelineSectionId.equals=" + (idPipelineSectionId + 1));
    }


    @Test
    @Transactional
    public void getAllCpsHistsByIdStatusIsEqualToSomething() throws Exception {
        // Get already existing entity
        ListObjectStatus idStatus = cpsHist.getIdStatus();
        cpsHistRepository.saveAndFlush(cpsHist);
        Long idStatusId = idStatus.getId();

        // Get all the cpsHistList where idStatus equals to idStatusId
        defaultCpsHistShouldBeFound("idStatusId.equals=" + idStatusId);

        // Get all the cpsHistList where idStatus equals to idStatusId + 1
        defaultCpsHistShouldNotBeFound("idStatusId.equals=" + (idStatusId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultCpsHistShouldBeFound(String filter) throws Exception {
        restCpsHistMockMvc.perform(get("/api/cps-hists?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cpsHist.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateFrom").value(hasItem(DEFAULT_DATE_FROM.toString())))
            .andExpect(jsonPath("$.[*].dateTo").value(hasItem(DEFAULT_DATE_TO.toString())))
            .andExpect(jsonPath("$.[*].idWrk").value(hasItem(DEFAULT_ID_WRK.intValue())))
            .andExpect(jsonPath("$.[*].current").value(hasItem(DEFAULT_CURRENT.intValue())))
            .andExpect(jsonPath("$.[*].potential").value(hasItem(DEFAULT_POTENTIAL.intValue())))
            .andExpect(jsonPath("$.[*].downtime").value(hasItem(DEFAULT_DOWNTIME.intValue())))
            .andExpect(jsonPath("$.[*].coord").value(hasItem(DEFAULT_COORD)))
            .andExpect(jsonPath("$.[*].kpInst").value(hasItem(DEFAULT_KP_INST.intValue())))
            .andExpect(jsonPath("$.[*].isCurrentFlag").value(hasItem(DEFAULT_IS_CURRENT_FLAG)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].comment").value(hasItem(DEFAULT_COMMENT)))
            .andExpect(jsonPath("$.[*].dateCreate").value(hasItem(DEFAULT_DATE_CREATE.toString())))
            .andExpect(jsonPath("$.[*].dateEdit").value(hasItem(DEFAULT_DATE_EDIT.toString())))
            .andExpect(jsonPath("$.[*].creator").value(hasItem(DEFAULT_CREATOR)))
            .andExpect(jsonPath("$.[*].editor").value(hasItem(DEFAULT_EDITOR)));

        // Check, that the count call also returns 1
        restCpsHistMockMvc.perform(get("/api/cps-hists/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultCpsHistShouldNotBeFound(String filter) throws Exception {
        restCpsHistMockMvc.perform(get("/api/cps-hists?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restCpsHistMockMvc.perform(get("/api/cps-hists/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingCpsHist() throws Exception {
        // Get the cpsHist
        restCpsHistMockMvc.perform(get("/api/cps-hists/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCpsHist() throws Exception {
        // Initialize the database
        cpsHistRepository.saveAndFlush(cpsHist);

        int databaseSizeBeforeUpdate = cpsHistRepository.findAll().size();

        // Update the cpsHist
        CpsHist updatedCpsHist = cpsHistRepository.findById(cpsHist.getId()).get();
        // Disconnect from session so that the updates on updatedCpsHist are not directly saved in db
        em.detach(updatedCpsHist);
        updatedCpsHist
            .dateFrom(UPDATED_DATE_FROM)
            .dateTo(UPDATED_DATE_TO)
            .idWrk(UPDATED_ID_WRK)
            .current(UPDATED_CURRENT)
            .potential(UPDATED_POTENTIAL)
            .downtime(UPDATED_DOWNTIME)
            .coord(UPDATED_COORD)
            .kpInst(UPDATED_KP_INST)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .comment(UPDATED_COMMENT)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        CpsHistDTO cpsHistDTO = cpsHistMapper.toDto(updatedCpsHist);

        restCpsHistMockMvc.perform(put("/api/cps-hists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cpsHistDTO)))
            .andExpect(status().isOk());

        // Validate the CpsHist in the database
        List<CpsHist> cpsHistList = cpsHistRepository.findAll();
        assertThat(cpsHistList).hasSize(databaseSizeBeforeUpdate);
        CpsHist testCpsHist = cpsHistList.get(cpsHistList.size() - 1);
        assertThat(testCpsHist.getDateFrom()).isEqualTo(UPDATED_DATE_FROM);
        assertThat(testCpsHist.getDateTo()).isEqualTo(UPDATED_DATE_TO);
        assertThat(testCpsHist.getIdWrk()).isEqualTo(UPDATED_ID_WRK);
        assertThat(testCpsHist.getCurrent()).isEqualTo(UPDATED_CURRENT);
        assertThat(testCpsHist.getPotential()).isEqualTo(UPDATED_POTENTIAL);
        assertThat(testCpsHist.getDowntime()).isEqualTo(UPDATED_DOWNTIME);
        assertThat(testCpsHist.getCoord()).isEqualTo(UPDATED_COORD);
        assertThat(testCpsHist.getKpInst()).isEqualTo(UPDATED_KP_INST);
        assertThat(testCpsHist.getIsCurrentFlag()).isEqualTo(UPDATED_IS_CURRENT_FLAG);
        assertThat(testCpsHist.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testCpsHist.getComment()).isEqualTo(UPDATED_COMMENT);
        assertThat(testCpsHist.getDateCreate()).isEqualTo(UPDATED_DATE_CREATE);
        assertThat(testCpsHist.getDateEdit()).isEqualTo(UPDATED_DATE_EDIT);
        assertThat(testCpsHist.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testCpsHist.getEditor()).isEqualTo(UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void updateNonExistingCpsHist() throws Exception {
        int databaseSizeBeforeUpdate = cpsHistRepository.findAll().size();

        // Create the CpsHist
        CpsHistDTO cpsHistDTO = cpsHistMapper.toDto(cpsHist);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCpsHistMockMvc.perform(put("/api/cps-hists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(cpsHistDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CpsHist in the database
        List<CpsHist> cpsHistList = cpsHistRepository.findAll();
        assertThat(cpsHistList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCpsHist() throws Exception {
        // Initialize the database
        cpsHistRepository.saveAndFlush(cpsHist);

        int databaseSizeBeforeDelete = cpsHistRepository.findAll().size();

        // Delete the cpsHist
        restCpsHistMockMvc.perform(delete("/api/cps-hists/{id}", cpsHist.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<CpsHist> cpsHistList = cpsHistRepository.findAll();
        assertThat(cpsHistList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CpsHist.class);
        CpsHist cpsHist1 = new CpsHist();
        cpsHist1.setId(1L);
        CpsHist cpsHist2 = new CpsHist();
        cpsHist2.setId(cpsHist1.getId());
        assertThat(cpsHist1).isEqualTo(cpsHist2);
        cpsHist2.setId(2L);
        assertThat(cpsHist1).isNotEqualTo(cpsHist2);
        cpsHist1.setId(null);
        assertThat(cpsHist1).isNotEqualTo(cpsHist2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CpsHistDTO.class);
        CpsHistDTO cpsHistDTO1 = new CpsHistDTO();
        cpsHistDTO1.setId(1L);
        CpsHistDTO cpsHistDTO2 = new CpsHistDTO();
        assertThat(cpsHistDTO1).isNotEqualTo(cpsHistDTO2);
        cpsHistDTO2.setId(cpsHistDTO1.getId());
        assertThat(cpsHistDTO1).isEqualTo(cpsHistDTO2);
        cpsHistDTO2.setId(2L);
        assertThat(cpsHistDTO1).isNotEqualTo(cpsHistDTO2);
        cpsHistDTO1.setId(null);
        assertThat(cpsHistDTO1).isNotEqualTo(cpsHistDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(cpsHistMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(cpsHistMapper.fromId(null)).isNull();
    }
}
