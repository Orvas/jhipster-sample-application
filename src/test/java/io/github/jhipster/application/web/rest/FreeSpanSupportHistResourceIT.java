package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.FreeSpanSupportHist;
import io.github.jhipster.application.domain.FreeSpanSupport;
import io.github.jhipster.application.domain.PipelineSection;
import io.github.jhipster.application.domain.ListObjectStatus;
import io.github.jhipster.application.repository.FreeSpanSupportHistRepository;
import io.github.jhipster.application.service.FreeSpanSupportHistService;
import io.github.jhipster.application.service.dto.FreeSpanSupportHistDTO;
import io.github.jhipster.application.service.mapper.FreeSpanSupportHistMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.FreeSpanSupportHistCriteria;
import io.github.jhipster.application.service.FreeSpanSupportHistQueryService;

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
 * Integration tests for the {@Link FreeSpanSupportHistResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class FreeSpanSupportHistResourceIT {

    private static final LocalDate DEFAULT_DATE_FROM = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_FROM = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATE_TO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_TO = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_ID_WRK = 1L;
    private static final Long UPDATED_ID_WRK = 2L;

    private static final BigDecimal DEFAULT_HEIGHT = new BigDecimal(1);
    private static final BigDecimal UPDATED_HEIGHT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_KP_INST = new BigDecimal(1);
    private static final BigDecimal UPDATED_KP_INST = new BigDecimal(2);

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
    private FreeSpanSupportHistRepository freeSpanSupportHistRepository;

    @Autowired
    private FreeSpanSupportHistMapper freeSpanSupportHistMapper;

    @Autowired
    private FreeSpanSupportHistService freeSpanSupportHistService;

    @Autowired
    private FreeSpanSupportHistQueryService freeSpanSupportHistQueryService;

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

    private MockMvc restFreeSpanSupportHistMockMvc;

    private FreeSpanSupportHist freeSpanSupportHist;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final FreeSpanSupportHistResource freeSpanSupportHistResource = new FreeSpanSupportHistResource(freeSpanSupportHistService, freeSpanSupportHistQueryService);
        this.restFreeSpanSupportHistMockMvc = MockMvcBuilders.standaloneSetup(freeSpanSupportHistResource)
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
    public static FreeSpanSupportHist createEntity(EntityManager em) {
        FreeSpanSupportHist freeSpanSupportHist = new FreeSpanSupportHist()
            .dateFrom(DEFAULT_DATE_FROM)
            .dateTo(DEFAULT_DATE_TO)
            .idWrk(DEFAULT_ID_WRK)
            .height(DEFAULT_HEIGHT)
            .kpInst(DEFAULT_KP_INST)
            .isCurrentFlag(DEFAULT_IS_CURRENT_FLAG)
            .comment(DEFAULT_COMMENT)
            .dateCreate(DEFAULT_DATE_CREATE)
            .dateEdit(DEFAULT_DATE_EDIT)
            .creator(DEFAULT_CREATOR)
            .editor(DEFAULT_EDITOR);
        // Add required entity
        FreeSpanSupport freeSpanSupport;
        if (TestUtil.findAll(em, FreeSpanSupport.class).isEmpty()) {
            freeSpanSupport = FreeSpanSupportResourceIT.createEntity(em);
            em.persist(freeSpanSupport);
            em.flush();
        } else {
            freeSpanSupport = TestUtil.findAll(em, FreeSpanSupport.class).get(0);
        }
        freeSpanSupportHist.setId(freeSpanSupport);
        // Add required entity
        PipelineSection pipelineSection;
        if (TestUtil.findAll(em, PipelineSection.class).isEmpty()) {
            pipelineSection = PipelineSectionResourceIT.createEntity(em);
            em.persist(pipelineSection);
            em.flush();
        } else {
            pipelineSection = TestUtil.findAll(em, PipelineSection.class).get(0);
        }
        freeSpanSupportHist.setIdPipelineSection(pipelineSection);
        // Add required entity
        ListObjectStatus listObjectStatus;
        if (TestUtil.findAll(em, ListObjectStatus.class).isEmpty()) {
            listObjectStatus = ListObjectStatusResourceIT.createEntity(em);
            em.persist(listObjectStatus);
            em.flush();
        } else {
            listObjectStatus = TestUtil.findAll(em, ListObjectStatus.class).get(0);
        }
        freeSpanSupportHist.setIdStatus(listObjectStatus);
        return freeSpanSupportHist;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FreeSpanSupportHist createUpdatedEntity(EntityManager em) {
        FreeSpanSupportHist freeSpanSupportHist = new FreeSpanSupportHist()
            .dateFrom(UPDATED_DATE_FROM)
            .dateTo(UPDATED_DATE_TO)
            .idWrk(UPDATED_ID_WRK)
            .height(UPDATED_HEIGHT)
            .kpInst(UPDATED_KP_INST)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .comment(UPDATED_COMMENT)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        // Add required entity
        FreeSpanSupport freeSpanSupport;
        if (TestUtil.findAll(em, FreeSpanSupport.class).isEmpty()) {
            freeSpanSupport = FreeSpanSupportResourceIT.createUpdatedEntity(em);
            em.persist(freeSpanSupport);
            em.flush();
        } else {
            freeSpanSupport = TestUtil.findAll(em, FreeSpanSupport.class).get(0);
        }
        freeSpanSupportHist.setId(freeSpanSupport);
        // Add required entity
        PipelineSection pipelineSection;
        if (TestUtil.findAll(em, PipelineSection.class).isEmpty()) {
            pipelineSection = PipelineSectionResourceIT.createUpdatedEntity(em);
            em.persist(pipelineSection);
            em.flush();
        } else {
            pipelineSection = TestUtil.findAll(em, PipelineSection.class).get(0);
        }
        freeSpanSupportHist.setIdPipelineSection(pipelineSection);
        // Add required entity
        ListObjectStatus listObjectStatus;
        if (TestUtil.findAll(em, ListObjectStatus.class).isEmpty()) {
            listObjectStatus = ListObjectStatusResourceIT.createUpdatedEntity(em);
            em.persist(listObjectStatus);
            em.flush();
        } else {
            listObjectStatus = TestUtil.findAll(em, ListObjectStatus.class).get(0);
        }
        freeSpanSupportHist.setIdStatus(listObjectStatus);
        return freeSpanSupportHist;
    }

    @BeforeEach
    public void initTest() {
        freeSpanSupportHist = createEntity(em);
    }

    @Test
    @Transactional
    public void createFreeSpanSupportHist() throws Exception {
        int databaseSizeBeforeCreate = freeSpanSupportHistRepository.findAll().size();

        // Create the FreeSpanSupportHist
        FreeSpanSupportHistDTO freeSpanSupportHistDTO = freeSpanSupportHistMapper.toDto(freeSpanSupportHist);
        restFreeSpanSupportHistMockMvc.perform(post("/api/free-span-support-hists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(freeSpanSupportHistDTO)))
            .andExpect(status().isCreated());

        // Validate the FreeSpanSupportHist in the database
        List<FreeSpanSupportHist> freeSpanSupportHistList = freeSpanSupportHistRepository.findAll();
        assertThat(freeSpanSupportHistList).hasSize(databaseSizeBeforeCreate + 1);
        FreeSpanSupportHist testFreeSpanSupportHist = freeSpanSupportHistList.get(freeSpanSupportHistList.size() - 1);
        assertThat(testFreeSpanSupportHist.getDateFrom()).isEqualTo(DEFAULT_DATE_FROM);
        assertThat(testFreeSpanSupportHist.getDateTo()).isEqualTo(DEFAULT_DATE_TO);
        assertThat(testFreeSpanSupportHist.getIdWrk()).isEqualTo(DEFAULT_ID_WRK);
        assertThat(testFreeSpanSupportHist.getHeight()).isEqualTo(DEFAULT_HEIGHT);
        assertThat(testFreeSpanSupportHist.getKpInst()).isEqualTo(DEFAULT_KP_INST);
        assertThat(testFreeSpanSupportHist.getIsCurrentFlag()).isEqualTo(DEFAULT_IS_CURRENT_FLAG);
        assertThat(testFreeSpanSupportHist.getComment()).isEqualTo(DEFAULT_COMMENT);
        assertThat(testFreeSpanSupportHist.getDateCreate()).isEqualTo(DEFAULT_DATE_CREATE);
        assertThat(testFreeSpanSupportHist.getDateEdit()).isEqualTo(DEFAULT_DATE_EDIT);
        assertThat(testFreeSpanSupportHist.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testFreeSpanSupportHist.getEditor()).isEqualTo(DEFAULT_EDITOR);
    }

    @Test
    @Transactional
    public void createFreeSpanSupportHistWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = freeSpanSupportHistRepository.findAll().size();

        // Create the FreeSpanSupportHist with an existing ID
        freeSpanSupportHist.setId(1L);
        FreeSpanSupportHistDTO freeSpanSupportHistDTO = freeSpanSupportHistMapper.toDto(freeSpanSupportHist);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFreeSpanSupportHistMockMvc.perform(post("/api/free-span-support-hists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(freeSpanSupportHistDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FreeSpanSupportHist in the database
        List<FreeSpanSupportHist> freeSpanSupportHistList = freeSpanSupportHistRepository.findAll();
        assertThat(freeSpanSupportHistList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkDateFromIsRequired() throws Exception {
        int databaseSizeBeforeTest = freeSpanSupportHistRepository.findAll().size();
        // set the field null
        freeSpanSupportHist.setDateFrom(null);

        // Create the FreeSpanSupportHist, which fails.
        FreeSpanSupportHistDTO freeSpanSupportHistDTO = freeSpanSupportHistMapper.toDto(freeSpanSupportHist);

        restFreeSpanSupportHistMockMvc.perform(post("/api/free-span-support-hists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(freeSpanSupportHistDTO)))
            .andExpect(status().isBadRequest());

        List<FreeSpanSupportHist> freeSpanSupportHistList = freeSpanSupportHistRepository.findAll();
        assertThat(freeSpanSupportHistList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDateToIsRequired() throws Exception {
        int databaseSizeBeforeTest = freeSpanSupportHistRepository.findAll().size();
        // set the field null
        freeSpanSupportHist.setDateTo(null);

        // Create the FreeSpanSupportHist, which fails.
        FreeSpanSupportHistDTO freeSpanSupportHistDTO = freeSpanSupportHistMapper.toDto(freeSpanSupportHist);

        restFreeSpanSupportHistMockMvc.perform(post("/api/free-span-support-hists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(freeSpanSupportHistDTO)))
            .andExpect(status().isBadRequest());

        List<FreeSpanSupportHist> freeSpanSupportHistList = freeSpanSupportHistRepository.findAll();
        assertThat(freeSpanSupportHistList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsCurrentFlagIsRequired() throws Exception {
        int databaseSizeBeforeTest = freeSpanSupportHistRepository.findAll().size();
        // set the field null
        freeSpanSupportHist.setIsCurrentFlag(null);

        // Create the FreeSpanSupportHist, which fails.
        FreeSpanSupportHistDTO freeSpanSupportHistDTO = freeSpanSupportHistMapper.toDto(freeSpanSupportHist);

        restFreeSpanSupportHistMockMvc.perform(post("/api/free-span-support-hists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(freeSpanSupportHistDTO)))
            .andExpect(status().isBadRequest());

        List<FreeSpanSupportHist> freeSpanSupportHistList = freeSpanSupportHistRepository.findAll();
        assertThat(freeSpanSupportHistList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllFreeSpanSupportHists() throws Exception {
        // Initialize the database
        freeSpanSupportHistRepository.saveAndFlush(freeSpanSupportHist);

        // Get all the freeSpanSupportHistList
        restFreeSpanSupportHistMockMvc.perform(get("/api/free-span-support-hists?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(freeSpanSupportHist.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateFrom").value(hasItem(DEFAULT_DATE_FROM.toString())))
            .andExpect(jsonPath("$.[*].dateTo").value(hasItem(DEFAULT_DATE_TO.toString())))
            .andExpect(jsonPath("$.[*].idWrk").value(hasItem(DEFAULT_ID_WRK.intValue())))
            .andExpect(jsonPath("$.[*].height").value(hasItem(DEFAULT_HEIGHT.intValue())))
            .andExpect(jsonPath("$.[*].kpInst").value(hasItem(DEFAULT_KP_INST.intValue())))
            .andExpect(jsonPath("$.[*].isCurrentFlag").value(hasItem(DEFAULT_IS_CURRENT_FLAG)))
            .andExpect(jsonPath("$.[*].comment").value(hasItem(DEFAULT_COMMENT.toString())))
            .andExpect(jsonPath("$.[*].dateCreate").value(hasItem(DEFAULT_DATE_CREATE.toString())))
            .andExpect(jsonPath("$.[*].dateEdit").value(hasItem(DEFAULT_DATE_EDIT.toString())))
            .andExpect(jsonPath("$.[*].creator").value(hasItem(DEFAULT_CREATOR.toString())))
            .andExpect(jsonPath("$.[*].editor").value(hasItem(DEFAULT_EDITOR.toString())));
    }
    
    @Test
    @Transactional
    public void getFreeSpanSupportHist() throws Exception {
        // Initialize the database
        freeSpanSupportHistRepository.saveAndFlush(freeSpanSupportHist);

        // Get the freeSpanSupportHist
        restFreeSpanSupportHistMockMvc.perform(get("/api/free-span-support-hists/{id}", freeSpanSupportHist.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(freeSpanSupportHist.getId().intValue()))
            .andExpect(jsonPath("$.dateFrom").value(DEFAULT_DATE_FROM.toString()))
            .andExpect(jsonPath("$.dateTo").value(DEFAULT_DATE_TO.toString()))
            .andExpect(jsonPath("$.idWrk").value(DEFAULT_ID_WRK.intValue()))
            .andExpect(jsonPath("$.height").value(DEFAULT_HEIGHT.intValue()))
            .andExpect(jsonPath("$.kpInst").value(DEFAULT_KP_INST.intValue()))
            .andExpect(jsonPath("$.isCurrentFlag").value(DEFAULT_IS_CURRENT_FLAG))
            .andExpect(jsonPath("$.comment").value(DEFAULT_COMMENT.toString()))
            .andExpect(jsonPath("$.dateCreate").value(DEFAULT_DATE_CREATE.toString()))
            .andExpect(jsonPath("$.dateEdit").value(DEFAULT_DATE_EDIT.toString()))
            .andExpect(jsonPath("$.creator").value(DEFAULT_CREATOR.toString()))
            .andExpect(jsonPath("$.editor").value(DEFAULT_EDITOR.toString()));
    }

    @Test
    @Transactional
    public void getAllFreeSpanSupportHistsByDateFromIsEqualToSomething() throws Exception {
        // Initialize the database
        freeSpanSupportHistRepository.saveAndFlush(freeSpanSupportHist);

        // Get all the freeSpanSupportHistList where dateFrom equals to DEFAULT_DATE_FROM
        defaultFreeSpanSupportHistShouldBeFound("dateFrom.equals=" + DEFAULT_DATE_FROM);

        // Get all the freeSpanSupportHistList where dateFrom equals to UPDATED_DATE_FROM
        defaultFreeSpanSupportHistShouldNotBeFound("dateFrom.equals=" + UPDATED_DATE_FROM);
    }

    @Test
    @Transactional
    public void getAllFreeSpanSupportHistsByDateFromIsInShouldWork() throws Exception {
        // Initialize the database
        freeSpanSupportHistRepository.saveAndFlush(freeSpanSupportHist);

        // Get all the freeSpanSupportHistList where dateFrom in DEFAULT_DATE_FROM or UPDATED_DATE_FROM
        defaultFreeSpanSupportHistShouldBeFound("dateFrom.in=" + DEFAULT_DATE_FROM + "," + UPDATED_DATE_FROM);

        // Get all the freeSpanSupportHistList where dateFrom equals to UPDATED_DATE_FROM
        defaultFreeSpanSupportHistShouldNotBeFound("dateFrom.in=" + UPDATED_DATE_FROM);
    }

    @Test
    @Transactional
    public void getAllFreeSpanSupportHistsByDateFromIsNullOrNotNull() throws Exception {
        // Initialize the database
        freeSpanSupportHistRepository.saveAndFlush(freeSpanSupportHist);

        // Get all the freeSpanSupportHistList where dateFrom is not null
        defaultFreeSpanSupportHistShouldBeFound("dateFrom.specified=true");

        // Get all the freeSpanSupportHistList where dateFrom is null
        defaultFreeSpanSupportHistShouldNotBeFound("dateFrom.specified=false");
    }

    @Test
    @Transactional
    public void getAllFreeSpanSupportHistsByDateFromIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        freeSpanSupportHistRepository.saveAndFlush(freeSpanSupportHist);

        // Get all the freeSpanSupportHistList where dateFrom greater than or equals to DEFAULT_DATE_FROM
        defaultFreeSpanSupportHistShouldBeFound("dateFrom.greaterOrEqualThan=" + DEFAULT_DATE_FROM);

        // Get all the freeSpanSupportHistList where dateFrom greater than or equals to UPDATED_DATE_FROM
        defaultFreeSpanSupportHistShouldNotBeFound("dateFrom.greaterOrEqualThan=" + UPDATED_DATE_FROM);
    }

    @Test
    @Transactional
    public void getAllFreeSpanSupportHistsByDateFromIsLessThanSomething() throws Exception {
        // Initialize the database
        freeSpanSupportHistRepository.saveAndFlush(freeSpanSupportHist);

        // Get all the freeSpanSupportHistList where dateFrom less than or equals to DEFAULT_DATE_FROM
        defaultFreeSpanSupportHistShouldNotBeFound("dateFrom.lessThan=" + DEFAULT_DATE_FROM);

        // Get all the freeSpanSupportHistList where dateFrom less than or equals to UPDATED_DATE_FROM
        defaultFreeSpanSupportHistShouldBeFound("dateFrom.lessThan=" + UPDATED_DATE_FROM);
    }


    @Test
    @Transactional
    public void getAllFreeSpanSupportHistsByDateToIsEqualToSomething() throws Exception {
        // Initialize the database
        freeSpanSupportHistRepository.saveAndFlush(freeSpanSupportHist);

        // Get all the freeSpanSupportHistList where dateTo equals to DEFAULT_DATE_TO
        defaultFreeSpanSupportHistShouldBeFound("dateTo.equals=" + DEFAULT_DATE_TO);

        // Get all the freeSpanSupportHistList where dateTo equals to UPDATED_DATE_TO
        defaultFreeSpanSupportHistShouldNotBeFound("dateTo.equals=" + UPDATED_DATE_TO);
    }

    @Test
    @Transactional
    public void getAllFreeSpanSupportHistsByDateToIsInShouldWork() throws Exception {
        // Initialize the database
        freeSpanSupportHistRepository.saveAndFlush(freeSpanSupportHist);

        // Get all the freeSpanSupportHistList where dateTo in DEFAULT_DATE_TO or UPDATED_DATE_TO
        defaultFreeSpanSupportHistShouldBeFound("dateTo.in=" + DEFAULT_DATE_TO + "," + UPDATED_DATE_TO);

        // Get all the freeSpanSupportHistList where dateTo equals to UPDATED_DATE_TO
        defaultFreeSpanSupportHistShouldNotBeFound("dateTo.in=" + UPDATED_DATE_TO);
    }

    @Test
    @Transactional
    public void getAllFreeSpanSupportHistsByDateToIsNullOrNotNull() throws Exception {
        // Initialize the database
        freeSpanSupportHistRepository.saveAndFlush(freeSpanSupportHist);

        // Get all the freeSpanSupportHistList where dateTo is not null
        defaultFreeSpanSupportHistShouldBeFound("dateTo.specified=true");

        // Get all the freeSpanSupportHistList where dateTo is null
        defaultFreeSpanSupportHistShouldNotBeFound("dateTo.specified=false");
    }

    @Test
    @Transactional
    public void getAllFreeSpanSupportHistsByDateToIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        freeSpanSupportHistRepository.saveAndFlush(freeSpanSupportHist);

        // Get all the freeSpanSupportHistList where dateTo greater than or equals to DEFAULT_DATE_TO
        defaultFreeSpanSupportHistShouldBeFound("dateTo.greaterOrEqualThan=" + DEFAULT_DATE_TO);

        // Get all the freeSpanSupportHistList where dateTo greater than or equals to UPDATED_DATE_TO
        defaultFreeSpanSupportHistShouldNotBeFound("dateTo.greaterOrEqualThan=" + UPDATED_DATE_TO);
    }

    @Test
    @Transactional
    public void getAllFreeSpanSupportHistsByDateToIsLessThanSomething() throws Exception {
        // Initialize the database
        freeSpanSupportHistRepository.saveAndFlush(freeSpanSupportHist);

        // Get all the freeSpanSupportHistList where dateTo less than or equals to DEFAULT_DATE_TO
        defaultFreeSpanSupportHistShouldNotBeFound("dateTo.lessThan=" + DEFAULT_DATE_TO);

        // Get all the freeSpanSupportHistList where dateTo less than or equals to UPDATED_DATE_TO
        defaultFreeSpanSupportHistShouldBeFound("dateTo.lessThan=" + UPDATED_DATE_TO);
    }


    @Test
    @Transactional
    public void getAllFreeSpanSupportHistsByIdWrkIsEqualToSomething() throws Exception {
        // Initialize the database
        freeSpanSupportHistRepository.saveAndFlush(freeSpanSupportHist);

        // Get all the freeSpanSupportHistList where idWrk equals to DEFAULT_ID_WRK
        defaultFreeSpanSupportHistShouldBeFound("idWrk.equals=" + DEFAULT_ID_WRK);

        // Get all the freeSpanSupportHistList where idWrk equals to UPDATED_ID_WRK
        defaultFreeSpanSupportHistShouldNotBeFound("idWrk.equals=" + UPDATED_ID_WRK);
    }

    @Test
    @Transactional
    public void getAllFreeSpanSupportHistsByIdWrkIsInShouldWork() throws Exception {
        // Initialize the database
        freeSpanSupportHistRepository.saveAndFlush(freeSpanSupportHist);

        // Get all the freeSpanSupportHistList where idWrk in DEFAULT_ID_WRK or UPDATED_ID_WRK
        defaultFreeSpanSupportHistShouldBeFound("idWrk.in=" + DEFAULT_ID_WRK + "," + UPDATED_ID_WRK);

        // Get all the freeSpanSupportHistList where idWrk equals to UPDATED_ID_WRK
        defaultFreeSpanSupportHistShouldNotBeFound("idWrk.in=" + UPDATED_ID_WRK);
    }

    @Test
    @Transactional
    public void getAllFreeSpanSupportHistsByIdWrkIsNullOrNotNull() throws Exception {
        // Initialize the database
        freeSpanSupportHistRepository.saveAndFlush(freeSpanSupportHist);

        // Get all the freeSpanSupportHistList where idWrk is not null
        defaultFreeSpanSupportHistShouldBeFound("idWrk.specified=true");

        // Get all the freeSpanSupportHistList where idWrk is null
        defaultFreeSpanSupportHistShouldNotBeFound("idWrk.specified=false");
    }

    @Test
    @Transactional
    public void getAllFreeSpanSupportHistsByIdWrkIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        freeSpanSupportHistRepository.saveAndFlush(freeSpanSupportHist);

        // Get all the freeSpanSupportHistList where idWrk greater than or equals to DEFAULT_ID_WRK
        defaultFreeSpanSupportHistShouldBeFound("idWrk.greaterOrEqualThan=" + DEFAULT_ID_WRK);

        // Get all the freeSpanSupportHistList where idWrk greater than or equals to UPDATED_ID_WRK
        defaultFreeSpanSupportHistShouldNotBeFound("idWrk.greaterOrEqualThan=" + UPDATED_ID_WRK);
    }

    @Test
    @Transactional
    public void getAllFreeSpanSupportHistsByIdWrkIsLessThanSomething() throws Exception {
        // Initialize the database
        freeSpanSupportHistRepository.saveAndFlush(freeSpanSupportHist);

        // Get all the freeSpanSupportHistList where idWrk less than or equals to DEFAULT_ID_WRK
        defaultFreeSpanSupportHistShouldNotBeFound("idWrk.lessThan=" + DEFAULT_ID_WRK);

        // Get all the freeSpanSupportHistList where idWrk less than or equals to UPDATED_ID_WRK
        defaultFreeSpanSupportHistShouldBeFound("idWrk.lessThan=" + UPDATED_ID_WRK);
    }


    @Test
    @Transactional
    public void getAllFreeSpanSupportHistsByHeightIsEqualToSomething() throws Exception {
        // Initialize the database
        freeSpanSupportHistRepository.saveAndFlush(freeSpanSupportHist);

        // Get all the freeSpanSupportHistList where height equals to DEFAULT_HEIGHT
        defaultFreeSpanSupportHistShouldBeFound("height.equals=" + DEFAULT_HEIGHT);

        // Get all the freeSpanSupportHistList where height equals to UPDATED_HEIGHT
        defaultFreeSpanSupportHistShouldNotBeFound("height.equals=" + UPDATED_HEIGHT);
    }

    @Test
    @Transactional
    public void getAllFreeSpanSupportHistsByHeightIsInShouldWork() throws Exception {
        // Initialize the database
        freeSpanSupportHistRepository.saveAndFlush(freeSpanSupportHist);

        // Get all the freeSpanSupportHistList where height in DEFAULT_HEIGHT or UPDATED_HEIGHT
        defaultFreeSpanSupportHistShouldBeFound("height.in=" + DEFAULT_HEIGHT + "," + UPDATED_HEIGHT);

        // Get all the freeSpanSupportHistList where height equals to UPDATED_HEIGHT
        defaultFreeSpanSupportHistShouldNotBeFound("height.in=" + UPDATED_HEIGHT);
    }

    @Test
    @Transactional
    public void getAllFreeSpanSupportHistsByHeightIsNullOrNotNull() throws Exception {
        // Initialize the database
        freeSpanSupportHistRepository.saveAndFlush(freeSpanSupportHist);

        // Get all the freeSpanSupportHistList where height is not null
        defaultFreeSpanSupportHistShouldBeFound("height.specified=true");

        // Get all the freeSpanSupportHistList where height is null
        defaultFreeSpanSupportHistShouldNotBeFound("height.specified=false");
    }

    @Test
    @Transactional
    public void getAllFreeSpanSupportHistsByKpInstIsEqualToSomething() throws Exception {
        // Initialize the database
        freeSpanSupportHistRepository.saveAndFlush(freeSpanSupportHist);

        // Get all the freeSpanSupportHistList where kpInst equals to DEFAULT_KP_INST
        defaultFreeSpanSupportHistShouldBeFound("kpInst.equals=" + DEFAULT_KP_INST);

        // Get all the freeSpanSupportHistList where kpInst equals to UPDATED_KP_INST
        defaultFreeSpanSupportHistShouldNotBeFound("kpInst.equals=" + UPDATED_KP_INST);
    }

    @Test
    @Transactional
    public void getAllFreeSpanSupportHistsByKpInstIsInShouldWork() throws Exception {
        // Initialize the database
        freeSpanSupportHistRepository.saveAndFlush(freeSpanSupportHist);

        // Get all the freeSpanSupportHistList where kpInst in DEFAULT_KP_INST or UPDATED_KP_INST
        defaultFreeSpanSupportHistShouldBeFound("kpInst.in=" + DEFAULT_KP_INST + "," + UPDATED_KP_INST);

        // Get all the freeSpanSupportHistList where kpInst equals to UPDATED_KP_INST
        defaultFreeSpanSupportHistShouldNotBeFound("kpInst.in=" + UPDATED_KP_INST);
    }

    @Test
    @Transactional
    public void getAllFreeSpanSupportHistsByKpInstIsNullOrNotNull() throws Exception {
        // Initialize the database
        freeSpanSupportHistRepository.saveAndFlush(freeSpanSupportHist);

        // Get all the freeSpanSupportHistList where kpInst is not null
        defaultFreeSpanSupportHistShouldBeFound("kpInst.specified=true");

        // Get all the freeSpanSupportHistList where kpInst is null
        defaultFreeSpanSupportHistShouldNotBeFound("kpInst.specified=false");
    }

    @Test
    @Transactional
    public void getAllFreeSpanSupportHistsByIsCurrentFlagIsEqualToSomething() throws Exception {
        // Initialize the database
        freeSpanSupportHistRepository.saveAndFlush(freeSpanSupportHist);

        // Get all the freeSpanSupportHistList where isCurrentFlag equals to DEFAULT_IS_CURRENT_FLAG
        defaultFreeSpanSupportHistShouldBeFound("isCurrentFlag.equals=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the freeSpanSupportHistList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultFreeSpanSupportHistShouldNotBeFound("isCurrentFlag.equals=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllFreeSpanSupportHistsByIsCurrentFlagIsInShouldWork() throws Exception {
        // Initialize the database
        freeSpanSupportHistRepository.saveAndFlush(freeSpanSupportHist);

        // Get all the freeSpanSupportHistList where isCurrentFlag in DEFAULT_IS_CURRENT_FLAG or UPDATED_IS_CURRENT_FLAG
        defaultFreeSpanSupportHistShouldBeFound("isCurrentFlag.in=" + DEFAULT_IS_CURRENT_FLAG + "," + UPDATED_IS_CURRENT_FLAG);

        // Get all the freeSpanSupportHistList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultFreeSpanSupportHistShouldNotBeFound("isCurrentFlag.in=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllFreeSpanSupportHistsByIsCurrentFlagIsNullOrNotNull() throws Exception {
        // Initialize the database
        freeSpanSupportHistRepository.saveAndFlush(freeSpanSupportHist);

        // Get all the freeSpanSupportHistList where isCurrentFlag is not null
        defaultFreeSpanSupportHistShouldBeFound("isCurrentFlag.specified=true");

        // Get all the freeSpanSupportHistList where isCurrentFlag is null
        defaultFreeSpanSupportHistShouldNotBeFound("isCurrentFlag.specified=false");
    }

    @Test
    @Transactional
    public void getAllFreeSpanSupportHistsByIsCurrentFlagIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        freeSpanSupportHistRepository.saveAndFlush(freeSpanSupportHist);

        // Get all the freeSpanSupportHistList where isCurrentFlag greater than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultFreeSpanSupportHistShouldBeFound("isCurrentFlag.greaterOrEqualThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the freeSpanSupportHistList where isCurrentFlag greater than or equals to UPDATED_IS_CURRENT_FLAG
        defaultFreeSpanSupportHistShouldNotBeFound("isCurrentFlag.greaterOrEqualThan=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllFreeSpanSupportHistsByIsCurrentFlagIsLessThanSomething() throws Exception {
        // Initialize the database
        freeSpanSupportHistRepository.saveAndFlush(freeSpanSupportHist);

        // Get all the freeSpanSupportHistList where isCurrentFlag less than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultFreeSpanSupportHistShouldNotBeFound("isCurrentFlag.lessThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the freeSpanSupportHistList where isCurrentFlag less than or equals to UPDATED_IS_CURRENT_FLAG
        defaultFreeSpanSupportHistShouldBeFound("isCurrentFlag.lessThan=" + UPDATED_IS_CURRENT_FLAG);
    }


    @Test
    @Transactional
    public void getAllFreeSpanSupportHistsByCommentIsEqualToSomething() throws Exception {
        // Initialize the database
        freeSpanSupportHistRepository.saveAndFlush(freeSpanSupportHist);

        // Get all the freeSpanSupportHistList where comment equals to DEFAULT_COMMENT
        defaultFreeSpanSupportHistShouldBeFound("comment.equals=" + DEFAULT_COMMENT);

        // Get all the freeSpanSupportHistList where comment equals to UPDATED_COMMENT
        defaultFreeSpanSupportHistShouldNotBeFound("comment.equals=" + UPDATED_COMMENT);
    }

    @Test
    @Transactional
    public void getAllFreeSpanSupportHistsByCommentIsInShouldWork() throws Exception {
        // Initialize the database
        freeSpanSupportHistRepository.saveAndFlush(freeSpanSupportHist);

        // Get all the freeSpanSupportHistList where comment in DEFAULT_COMMENT or UPDATED_COMMENT
        defaultFreeSpanSupportHistShouldBeFound("comment.in=" + DEFAULT_COMMENT + "," + UPDATED_COMMENT);

        // Get all the freeSpanSupportHistList where comment equals to UPDATED_COMMENT
        defaultFreeSpanSupportHistShouldNotBeFound("comment.in=" + UPDATED_COMMENT);
    }

    @Test
    @Transactional
    public void getAllFreeSpanSupportHistsByCommentIsNullOrNotNull() throws Exception {
        // Initialize the database
        freeSpanSupportHistRepository.saveAndFlush(freeSpanSupportHist);

        // Get all the freeSpanSupportHistList where comment is not null
        defaultFreeSpanSupportHistShouldBeFound("comment.specified=true");

        // Get all the freeSpanSupportHistList where comment is null
        defaultFreeSpanSupportHistShouldNotBeFound("comment.specified=false");
    }

    @Test
    @Transactional
    public void getAllFreeSpanSupportHistsByDateCreateIsEqualToSomething() throws Exception {
        // Initialize the database
        freeSpanSupportHistRepository.saveAndFlush(freeSpanSupportHist);

        // Get all the freeSpanSupportHistList where dateCreate equals to DEFAULT_DATE_CREATE
        defaultFreeSpanSupportHistShouldBeFound("dateCreate.equals=" + DEFAULT_DATE_CREATE);

        // Get all the freeSpanSupportHistList where dateCreate equals to UPDATED_DATE_CREATE
        defaultFreeSpanSupportHistShouldNotBeFound("dateCreate.equals=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllFreeSpanSupportHistsByDateCreateIsInShouldWork() throws Exception {
        // Initialize the database
        freeSpanSupportHistRepository.saveAndFlush(freeSpanSupportHist);

        // Get all the freeSpanSupportHistList where dateCreate in DEFAULT_DATE_CREATE or UPDATED_DATE_CREATE
        defaultFreeSpanSupportHistShouldBeFound("dateCreate.in=" + DEFAULT_DATE_CREATE + "," + UPDATED_DATE_CREATE);

        // Get all the freeSpanSupportHistList where dateCreate equals to UPDATED_DATE_CREATE
        defaultFreeSpanSupportHistShouldNotBeFound("dateCreate.in=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllFreeSpanSupportHistsByDateCreateIsNullOrNotNull() throws Exception {
        // Initialize the database
        freeSpanSupportHistRepository.saveAndFlush(freeSpanSupportHist);

        // Get all the freeSpanSupportHistList where dateCreate is not null
        defaultFreeSpanSupportHistShouldBeFound("dateCreate.specified=true");

        // Get all the freeSpanSupportHistList where dateCreate is null
        defaultFreeSpanSupportHistShouldNotBeFound("dateCreate.specified=false");
    }

    @Test
    @Transactional
    public void getAllFreeSpanSupportHistsByDateEditIsEqualToSomething() throws Exception {
        // Initialize the database
        freeSpanSupportHistRepository.saveAndFlush(freeSpanSupportHist);

        // Get all the freeSpanSupportHistList where dateEdit equals to DEFAULT_DATE_EDIT
        defaultFreeSpanSupportHistShouldBeFound("dateEdit.equals=" + DEFAULT_DATE_EDIT);

        // Get all the freeSpanSupportHistList where dateEdit equals to UPDATED_DATE_EDIT
        defaultFreeSpanSupportHistShouldNotBeFound("dateEdit.equals=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllFreeSpanSupportHistsByDateEditIsInShouldWork() throws Exception {
        // Initialize the database
        freeSpanSupportHistRepository.saveAndFlush(freeSpanSupportHist);

        // Get all the freeSpanSupportHistList where dateEdit in DEFAULT_DATE_EDIT or UPDATED_DATE_EDIT
        defaultFreeSpanSupportHistShouldBeFound("dateEdit.in=" + DEFAULT_DATE_EDIT + "," + UPDATED_DATE_EDIT);

        // Get all the freeSpanSupportHistList where dateEdit equals to UPDATED_DATE_EDIT
        defaultFreeSpanSupportHistShouldNotBeFound("dateEdit.in=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllFreeSpanSupportHistsByDateEditIsNullOrNotNull() throws Exception {
        // Initialize the database
        freeSpanSupportHistRepository.saveAndFlush(freeSpanSupportHist);

        // Get all the freeSpanSupportHistList where dateEdit is not null
        defaultFreeSpanSupportHistShouldBeFound("dateEdit.specified=true");

        // Get all the freeSpanSupportHistList where dateEdit is null
        defaultFreeSpanSupportHistShouldNotBeFound("dateEdit.specified=false");
    }

    @Test
    @Transactional
    public void getAllFreeSpanSupportHistsByCreatorIsEqualToSomething() throws Exception {
        // Initialize the database
        freeSpanSupportHistRepository.saveAndFlush(freeSpanSupportHist);

        // Get all the freeSpanSupportHistList where creator equals to DEFAULT_CREATOR
        defaultFreeSpanSupportHistShouldBeFound("creator.equals=" + DEFAULT_CREATOR);

        // Get all the freeSpanSupportHistList where creator equals to UPDATED_CREATOR
        defaultFreeSpanSupportHistShouldNotBeFound("creator.equals=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllFreeSpanSupportHistsByCreatorIsInShouldWork() throws Exception {
        // Initialize the database
        freeSpanSupportHistRepository.saveAndFlush(freeSpanSupportHist);

        // Get all the freeSpanSupportHistList where creator in DEFAULT_CREATOR or UPDATED_CREATOR
        defaultFreeSpanSupportHistShouldBeFound("creator.in=" + DEFAULT_CREATOR + "," + UPDATED_CREATOR);

        // Get all the freeSpanSupportHistList where creator equals to UPDATED_CREATOR
        defaultFreeSpanSupportHistShouldNotBeFound("creator.in=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllFreeSpanSupportHistsByCreatorIsNullOrNotNull() throws Exception {
        // Initialize the database
        freeSpanSupportHistRepository.saveAndFlush(freeSpanSupportHist);

        // Get all the freeSpanSupportHistList where creator is not null
        defaultFreeSpanSupportHistShouldBeFound("creator.specified=true");

        // Get all the freeSpanSupportHistList where creator is null
        defaultFreeSpanSupportHistShouldNotBeFound("creator.specified=false");
    }

    @Test
    @Transactional
    public void getAllFreeSpanSupportHistsByEditorIsEqualToSomething() throws Exception {
        // Initialize the database
        freeSpanSupportHistRepository.saveAndFlush(freeSpanSupportHist);

        // Get all the freeSpanSupportHistList where editor equals to DEFAULT_EDITOR
        defaultFreeSpanSupportHistShouldBeFound("editor.equals=" + DEFAULT_EDITOR);

        // Get all the freeSpanSupportHistList where editor equals to UPDATED_EDITOR
        defaultFreeSpanSupportHistShouldNotBeFound("editor.equals=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllFreeSpanSupportHistsByEditorIsInShouldWork() throws Exception {
        // Initialize the database
        freeSpanSupportHistRepository.saveAndFlush(freeSpanSupportHist);

        // Get all the freeSpanSupportHistList where editor in DEFAULT_EDITOR or UPDATED_EDITOR
        defaultFreeSpanSupportHistShouldBeFound("editor.in=" + DEFAULT_EDITOR + "," + UPDATED_EDITOR);

        // Get all the freeSpanSupportHistList where editor equals to UPDATED_EDITOR
        defaultFreeSpanSupportHistShouldNotBeFound("editor.in=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllFreeSpanSupportHistsByEditorIsNullOrNotNull() throws Exception {
        // Initialize the database
        freeSpanSupportHistRepository.saveAndFlush(freeSpanSupportHist);

        // Get all the freeSpanSupportHistList where editor is not null
        defaultFreeSpanSupportHistShouldBeFound("editor.specified=true");

        // Get all the freeSpanSupportHistList where editor is null
        defaultFreeSpanSupportHistShouldNotBeFound("editor.specified=false");
    }

    @Test
    @Transactional
    public void getAllFreeSpanSupportHistsByIdIsEqualToSomething() throws Exception {
        // Get already existing entity
        FreeSpanSupport id = freeSpanSupportHist.getId();
        freeSpanSupportHistRepository.saveAndFlush(freeSpanSupportHist);
        Long idId = id.getId();

        // Get all the freeSpanSupportHistList where id equals to idId
        defaultFreeSpanSupportHistShouldBeFound("idId.equals=" + idId);

        // Get all the freeSpanSupportHistList where id equals to idId + 1
        defaultFreeSpanSupportHistShouldNotBeFound("idId.equals=" + (idId + 1));
    }


    @Test
    @Transactional
    public void getAllFreeSpanSupportHistsByIdPipelineSectionIsEqualToSomething() throws Exception {
        // Get already existing entity
        PipelineSection idPipelineSection = freeSpanSupportHist.getIdPipelineSection();
        freeSpanSupportHistRepository.saveAndFlush(freeSpanSupportHist);
        Long idPipelineSectionId = idPipelineSection.getId();

        // Get all the freeSpanSupportHistList where idPipelineSection equals to idPipelineSectionId
        defaultFreeSpanSupportHistShouldBeFound("idPipelineSectionId.equals=" + idPipelineSectionId);

        // Get all the freeSpanSupportHistList where idPipelineSection equals to idPipelineSectionId + 1
        defaultFreeSpanSupportHistShouldNotBeFound("idPipelineSectionId.equals=" + (idPipelineSectionId + 1));
    }


    @Test
    @Transactional
    public void getAllFreeSpanSupportHistsByIdStatusIsEqualToSomething() throws Exception {
        // Get already existing entity
        ListObjectStatus idStatus = freeSpanSupportHist.getIdStatus();
        freeSpanSupportHistRepository.saveAndFlush(freeSpanSupportHist);
        Long idStatusId = idStatus.getId();

        // Get all the freeSpanSupportHistList where idStatus equals to idStatusId
        defaultFreeSpanSupportHistShouldBeFound("idStatusId.equals=" + idStatusId);

        // Get all the freeSpanSupportHistList where idStatus equals to idStatusId + 1
        defaultFreeSpanSupportHistShouldNotBeFound("idStatusId.equals=" + (idStatusId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultFreeSpanSupportHistShouldBeFound(String filter) throws Exception {
        restFreeSpanSupportHistMockMvc.perform(get("/api/free-span-support-hists?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(freeSpanSupportHist.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateFrom").value(hasItem(DEFAULT_DATE_FROM.toString())))
            .andExpect(jsonPath("$.[*].dateTo").value(hasItem(DEFAULT_DATE_TO.toString())))
            .andExpect(jsonPath("$.[*].idWrk").value(hasItem(DEFAULT_ID_WRK.intValue())))
            .andExpect(jsonPath("$.[*].height").value(hasItem(DEFAULT_HEIGHT.intValue())))
            .andExpect(jsonPath("$.[*].kpInst").value(hasItem(DEFAULT_KP_INST.intValue())))
            .andExpect(jsonPath("$.[*].isCurrentFlag").value(hasItem(DEFAULT_IS_CURRENT_FLAG)))
            .andExpect(jsonPath("$.[*].comment").value(hasItem(DEFAULT_COMMENT)))
            .andExpect(jsonPath("$.[*].dateCreate").value(hasItem(DEFAULT_DATE_CREATE.toString())))
            .andExpect(jsonPath("$.[*].dateEdit").value(hasItem(DEFAULT_DATE_EDIT.toString())))
            .andExpect(jsonPath("$.[*].creator").value(hasItem(DEFAULT_CREATOR)))
            .andExpect(jsonPath("$.[*].editor").value(hasItem(DEFAULT_EDITOR)));

        // Check, that the count call also returns 1
        restFreeSpanSupportHistMockMvc.perform(get("/api/free-span-support-hists/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultFreeSpanSupportHistShouldNotBeFound(String filter) throws Exception {
        restFreeSpanSupportHistMockMvc.perform(get("/api/free-span-support-hists?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restFreeSpanSupportHistMockMvc.perform(get("/api/free-span-support-hists/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingFreeSpanSupportHist() throws Exception {
        // Get the freeSpanSupportHist
        restFreeSpanSupportHistMockMvc.perform(get("/api/free-span-support-hists/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFreeSpanSupportHist() throws Exception {
        // Initialize the database
        freeSpanSupportHistRepository.saveAndFlush(freeSpanSupportHist);

        int databaseSizeBeforeUpdate = freeSpanSupportHistRepository.findAll().size();

        // Update the freeSpanSupportHist
        FreeSpanSupportHist updatedFreeSpanSupportHist = freeSpanSupportHistRepository.findById(freeSpanSupportHist.getId()).get();
        // Disconnect from session so that the updates on updatedFreeSpanSupportHist are not directly saved in db
        em.detach(updatedFreeSpanSupportHist);
        updatedFreeSpanSupportHist
            .dateFrom(UPDATED_DATE_FROM)
            .dateTo(UPDATED_DATE_TO)
            .idWrk(UPDATED_ID_WRK)
            .height(UPDATED_HEIGHT)
            .kpInst(UPDATED_KP_INST)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .comment(UPDATED_COMMENT)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        FreeSpanSupportHistDTO freeSpanSupportHistDTO = freeSpanSupportHistMapper.toDto(updatedFreeSpanSupportHist);

        restFreeSpanSupportHistMockMvc.perform(put("/api/free-span-support-hists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(freeSpanSupportHistDTO)))
            .andExpect(status().isOk());

        // Validate the FreeSpanSupportHist in the database
        List<FreeSpanSupportHist> freeSpanSupportHistList = freeSpanSupportHistRepository.findAll();
        assertThat(freeSpanSupportHistList).hasSize(databaseSizeBeforeUpdate);
        FreeSpanSupportHist testFreeSpanSupportHist = freeSpanSupportHistList.get(freeSpanSupportHistList.size() - 1);
        assertThat(testFreeSpanSupportHist.getDateFrom()).isEqualTo(UPDATED_DATE_FROM);
        assertThat(testFreeSpanSupportHist.getDateTo()).isEqualTo(UPDATED_DATE_TO);
        assertThat(testFreeSpanSupportHist.getIdWrk()).isEqualTo(UPDATED_ID_WRK);
        assertThat(testFreeSpanSupportHist.getHeight()).isEqualTo(UPDATED_HEIGHT);
        assertThat(testFreeSpanSupportHist.getKpInst()).isEqualTo(UPDATED_KP_INST);
        assertThat(testFreeSpanSupportHist.getIsCurrentFlag()).isEqualTo(UPDATED_IS_CURRENT_FLAG);
        assertThat(testFreeSpanSupportHist.getComment()).isEqualTo(UPDATED_COMMENT);
        assertThat(testFreeSpanSupportHist.getDateCreate()).isEqualTo(UPDATED_DATE_CREATE);
        assertThat(testFreeSpanSupportHist.getDateEdit()).isEqualTo(UPDATED_DATE_EDIT);
        assertThat(testFreeSpanSupportHist.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testFreeSpanSupportHist.getEditor()).isEqualTo(UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void updateNonExistingFreeSpanSupportHist() throws Exception {
        int databaseSizeBeforeUpdate = freeSpanSupportHistRepository.findAll().size();

        // Create the FreeSpanSupportHist
        FreeSpanSupportHistDTO freeSpanSupportHistDTO = freeSpanSupportHistMapper.toDto(freeSpanSupportHist);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFreeSpanSupportHistMockMvc.perform(put("/api/free-span-support-hists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(freeSpanSupportHistDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FreeSpanSupportHist in the database
        List<FreeSpanSupportHist> freeSpanSupportHistList = freeSpanSupportHistRepository.findAll();
        assertThat(freeSpanSupportHistList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFreeSpanSupportHist() throws Exception {
        // Initialize the database
        freeSpanSupportHistRepository.saveAndFlush(freeSpanSupportHist);

        int databaseSizeBeforeDelete = freeSpanSupportHistRepository.findAll().size();

        // Delete the freeSpanSupportHist
        restFreeSpanSupportHistMockMvc.perform(delete("/api/free-span-support-hists/{id}", freeSpanSupportHist.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<FreeSpanSupportHist> freeSpanSupportHistList = freeSpanSupportHistRepository.findAll();
        assertThat(freeSpanSupportHistList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FreeSpanSupportHist.class);
        FreeSpanSupportHist freeSpanSupportHist1 = new FreeSpanSupportHist();
        freeSpanSupportHist1.setId(1L);
        FreeSpanSupportHist freeSpanSupportHist2 = new FreeSpanSupportHist();
        freeSpanSupportHist2.setId(freeSpanSupportHist1.getId());
        assertThat(freeSpanSupportHist1).isEqualTo(freeSpanSupportHist2);
        freeSpanSupportHist2.setId(2L);
        assertThat(freeSpanSupportHist1).isNotEqualTo(freeSpanSupportHist2);
        freeSpanSupportHist1.setId(null);
        assertThat(freeSpanSupportHist1).isNotEqualTo(freeSpanSupportHist2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FreeSpanSupportHistDTO.class);
        FreeSpanSupportHistDTO freeSpanSupportHistDTO1 = new FreeSpanSupportHistDTO();
        freeSpanSupportHistDTO1.setId(1L);
        FreeSpanSupportHistDTO freeSpanSupportHistDTO2 = new FreeSpanSupportHistDTO();
        assertThat(freeSpanSupportHistDTO1).isNotEqualTo(freeSpanSupportHistDTO2);
        freeSpanSupportHistDTO2.setId(freeSpanSupportHistDTO1.getId());
        assertThat(freeSpanSupportHistDTO1).isEqualTo(freeSpanSupportHistDTO2);
        freeSpanSupportHistDTO2.setId(2L);
        assertThat(freeSpanSupportHistDTO1).isNotEqualTo(freeSpanSupportHistDTO2);
        freeSpanSupportHistDTO1.setId(null);
        assertThat(freeSpanSupportHistDTO1).isNotEqualTo(freeSpanSupportHistDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(freeSpanSupportHistMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(freeSpanSupportHistMapper.fromId(null)).isNull();
    }
}
