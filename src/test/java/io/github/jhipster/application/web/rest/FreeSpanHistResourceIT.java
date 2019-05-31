package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.FreeSpanHist;
import io.github.jhipster.application.domain.FreeSpan;
import io.github.jhipster.application.domain.PipelineSection;
import io.github.jhipster.application.domain.ListObjectStatus;
import io.github.jhipster.application.repository.FreeSpanHistRepository;
import io.github.jhipster.application.service.FreeSpanHistService;
import io.github.jhipster.application.service.dto.FreeSpanHistDTO;
import io.github.jhipster.application.service.mapper.FreeSpanHistMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.FreeSpanHistCriteria;
import io.github.jhipster.application.service.FreeSpanHistQueryService;

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
 * Integration tests for the {@Link FreeSpanHistResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class FreeSpanHistResourceIT {

    private static final LocalDate DEFAULT_DATE_FROM = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_FROM = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATE_TO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_TO = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_ID_WRK = 1L;
    private static final Long UPDATED_ID_WRK = 2L;

    private static final BigDecimal DEFAULT_LENGHT = new BigDecimal(1);
    private static final BigDecimal UPDATED_LENGHT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_LENGHT_ALLOW = new BigDecimal(1);
    private static final BigDecimal UPDATED_LENGHT_ALLOW = new BigDecimal(2);

    private static final BigDecimal DEFAULT_HEIGHT = new BigDecimal(1);
    private static final BigDecimal UPDATED_HEIGHT = new BigDecimal(2);

    private static final Integer DEFAULT_IS_MULTISPAN = 1;
    private static final Integer UPDATED_IS_MULTISPAN = 2;

    private static final BigDecimal DEFAULT_GAP = new BigDecimal(1);
    private static final BigDecimal UPDATED_GAP = new BigDecimal(2);

    private static final BigDecimal DEFAULT_KP_START = new BigDecimal(1);
    private static final BigDecimal UPDATED_KP_START = new BigDecimal(2);

    private static final BigDecimal DEFAULT_KP_END = new BigDecimal(1);
    private static final BigDecimal UPDATED_KP_END = new BigDecimal(2);

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
    private FreeSpanHistRepository freeSpanHistRepository;

    @Autowired
    private FreeSpanHistMapper freeSpanHistMapper;

    @Autowired
    private FreeSpanHistService freeSpanHistService;

    @Autowired
    private FreeSpanHistQueryService freeSpanHistQueryService;

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

    private MockMvc restFreeSpanHistMockMvc;

    private FreeSpanHist freeSpanHist;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final FreeSpanHistResource freeSpanHistResource = new FreeSpanHistResource(freeSpanHistService, freeSpanHistQueryService);
        this.restFreeSpanHistMockMvc = MockMvcBuilders.standaloneSetup(freeSpanHistResource)
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
    public static FreeSpanHist createEntity(EntityManager em) {
        FreeSpanHist freeSpanHist = new FreeSpanHist()
            .dateFrom(DEFAULT_DATE_FROM)
            .dateTo(DEFAULT_DATE_TO)
            .idWrk(DEFAULT_ID_WRK)
            .lenght(DEFAULT_LENGHT)
            .lenghtAllow(DEFAULT_LENGHT_ALLOW)
            .height(DEFAULT_HEIGHT)
            .isMultispan(DEFAULT_IS_MULTISPAN)
            .gap(DEFAULT_GAP)
            .kpStart(DEFAULT_KP_START)
            .kpEnd(DEFAULT_KP_END)
            .isCurrentFlag(DEFAULT_IS_CURRENT_FLAG)
            .comment(DEFAULT_COMMENT)
            .dateCreate(DEFAULT_DATE_CREATE)
            .dateEdit(DEFAULT_DATE_EDIT)
            .creator(DEFAULT_CREATOR)
            .editor(DEFAULT_EDITOR);
        // Add required entity
        PipelineSection pipelineSection;
        if (TestUtil.findAll(em, PipelineSection.class).isEmpty()) {
            pipelineSection = PipelineSectionResourceIT.createEntity(em);
            em.persist(pipelineSection);
            em.flush();
        } else {
            pipelineSection = TestUtil.findAll(em, PipelineSection.class).get(0);
        }
        freeSpanHist.setIdPipelineSection(pipelineSection);
        // Add required entity
        ListObjectStatus listObjectStatus;
        if (TestUtil.findAll(em, ListObjectStatus.class).isEmpty()) {
            listObjectStatus = ListObjectStatusResourceIT.createEntity(em);
            em.persist(listObjectStatus);
            em.flush();
        } else {
            listObjectStatus = TestUtil.findAll(em, ListObjectStatus.class).get(0);
        }
        freeSpanHist.setIdStatus(listObjectStatus);
        return freeSpanHist;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FreeSpanHist createUpdatedEntity(EntityManager em) {
        FreeSpanHist freeSpanHist = new FreeSpanHist()
            .dateFrom(UPDATED_DATE_FROM)
            .dateTo(UPDATED_DATE_TO)
            .idWrk(UPDATED_ID_WRK)
            .lenght(UPDATED_LENGHT)
            .lenghtAllow(UPDATED_LENGHT_ALLOW)
            .height(UPDATED_HEIGHT)
            .isMultispan(UPDATED_IS_MULTISPAN)
            .gap(UPDATED_GAP)
            .kpStart(UPDATED_KP_START)
            .kpEnd(UPDATED_KP_END)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .comment(UPDATED_COMMENT)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        // Add required entity
        PipelineSection pipelineSection;
        if (TestUtil.findAll(em, PipelineSection.class).isEmpty()) {
            pipelineSection = PipelineSectionResourceIT.createUpdatedEntity(em);
            em.persist(pipelineSection);
            em.flush();
        } else {
            pipelineSection = TestUtil.findAll(em, PipelineSection.class).get(0);
        }
        freeSpanHist.setIdPipelineSection(pipelineSection);
        // Add required entity
        ListObjectStatus listObjectStatus;
        if (TestUtil.findAll(em, ListObjectStatus.class).isEmpty()) {
            listObjectStatus = ListObjectStatusResourceIT.createUpdatedEntity(em);
            em.persist(listObjectStatus);
            em.flush();
        } else {
            listObjectStatus = TestUtil.findAll(em, ListObjectStatus.class).get(0);
        }
        freeSpanHist.setIdStatus(listObjectStatus);
        return freeSpanHist;
    }

    @BeforeEach
    public void initTest() {
        freeSpanHist = createEntity(em);
    }

    @Test
    @Transactional
    public void createFreeSpanHist() throws Exception {
        int databaseSizeBeforeCreate = freeSpanHistRepository.findAll().size();

        // Create the FreeSpanHist
        FreeSpanHistDTO freeSpanHistDTO = freeSpanHistMapper.toDto(freeSpanHist);
        restFreeSpanHistMockMvc.perform(post("/api/free-span-hists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(freeSpanHistDTO)))
            .andExpect(status().isCreated());

        // Validate the FreeSpanHist in the database
        List<FreeSpanHist> freeSpanHistList = freeSpanHistRepository.findAll();
        assertThat(freeSpanHistList).hasSize(databaseSizeBeforeCreate + 1);
        FreeSpanHist testFreeSpanHist = freeSpanHistList.get(freeSpanHistList.size() - 1);
        assertThat(testFreeSpanHist.getDateFrom()).isEqualTo(DEFAULT_DATE_FROM);
        assertThat(testFreeSpanHist.getDateTo()).isEqualTo(DEFAULT_DATE_TO);
        assertThat(testFreeSpanHist.getIdWrk()).isEqualTo(DEFAULT_ID_WRK);
        assertThat(testFreeSpanHist.getLenght()).isEqualTo(DEFAULT_LENGHT);
        assertThat(testFreeSpanHist.getLenghtAllow()).isEqualTo(DEFAULT_LENGHT_ALLOW);
        assertThat(testFreeSpanHist.getHeight()).isEqualTo(DEFAULT_HEIGHT);
        assertThat(testFreeSpanHist.getIsMultispan()).isEqualTo(DEFAULT_IS_MULTISPAN);
        assertThat(testFreeSpanHist.getGap()).isEqualTo(DEFAULT_GAP);
        assertThat(testFreeSpanHist.getKpStart()).isEqualTo(DEFAULT_KP_START);
        assertThat(testFreeSpanHist.getKpEnd()).isEqualTo(DEFAULT_KP_END);
        assertThat(testFreeSpanHist.getIsCurrentFlag()).isEqualTo(DEFAULT_IS_CURRENT_FLAG);
        assertThat(testFreeSpanHist.getComment()).isEqualTo(DEFAULT_COMMENT);
        assertThat(testFreeSpanHist.getDateCreate()).isEqualTo(DEFAULT_DATE_CREATE);
        assertThat(testFreeSpanHist.getDateEdit()).isEqualTo(DEFAULT_DATE_EDIT);
        assertThat(testFreeSpanHist.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testFreeSpanHist.getEditor()).isEqualTo(DEFAULT_EDITOR);
    }

    @Test
    @Transactional
    public void createFreeSpanHistWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = freeSpanHistRepository.findAll().size();

        // Create the FreeSpanHist with an existing ID
        freeSpanHist.setId(1L);
        FreeSpanHistDTO freeSpanHistDTO = freeSpanHistMapper.toDto(freeSpanHist);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFreeSpanHistMockMvc.perform(post("/api/free-span-hists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(freeSpanHistDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FreeSpanHist in the database
        List<FreeSpanHist> freeSpanHistList = freeSpanHistRepository.findAll();
        assertThat(freeSpanHistList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkDateFromIsRequired() throws Exception {
        int databaseSizeBeforeTest = freeSpanHistRepository.findAll().size();
        // set the field null
        freeSpanHist.setDateFrom(null);

        // Create the FreeSpanHist, which fails.
        FreeSpanHistDTO freeSpanHistDTO = freeSpanHistMapper.toDto(freeSpanHist);

        restFreeSpanHistMockMvc.perform(post("/api/free-span-hists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(freeSpanHistDTO)))
            .andExpect(status().isBadRequest());

        List<FreeSpanHist> freeSpanHistList = freeSpanHistRepository.findAll();
        assertThat(freeSpanHistList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDateToIsRequired() throws Exception {
        int databaseSizeBeforeTest = freeSpanHistRepository.findAll().size();
        // set the field null
        freeSpanHist.setDateTo(null);

        // Create the FreeSpanHist, which fails.
        FreeSpanHistDTO freeSpanHistDTO = freeSpanHistMapper.toDto(freeSpanHist);

        restFreeSpanHistMockMvc.perform(post("/api/free-span-hists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(freeSpanHistDTO)))
            .andExpect(status().isBadRequest());

        List<FreeSpanHist> freeSpanHistList = freeSpanHistRepository.findAll();
        assertThat(freeSpanHistList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsCurrentFlagIsRequired() throws Exception {
        int databaseSizeBeforeTest = freeSpanHistRepository.findAll().size();
        // set the field null
        freeSpanHist.setIsCurrentFlag(null);

        // Create the FreeSpanHist, which fails.
        FreeSpanHistDTO freeSpanHistDTO = freeSpanHistMapper.toDto(freeSpanHist);

        restFreeSpanHistMockMvc.perform(post("/api/free-span-hists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(freeSpanHistDTO)))
            .andExpect(status().isBadRequest());

        List<FreeSpanHist> freeSpanHistList = freeSpanHistRepository.findAll();
        assertThat(freeSpanHistList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllFreeSpanHists() throws Exception {
        // Initialize the database
        freeSpanHistRepository.saveAndFlush(freeSpanHist);

        // Get all the freeSpanHistList
        restFreeSpanHistMockMvc.perform(get("/api/free-span-hists?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(freeSpanHist.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateFrom").value(hasItem(DEFAULT_DATE_FROM.toString())))
            .andExpect(jsonPath("$.[*].dateTo").value(hasItem(DEFAULT_DATE_TO.toString())))
            .andExpect(jsonPath("$.[*].idWrk").value(hasItem(DEFAULT_ID_WRK.intValue())))
            .andExpect(jsonPath("$.[*].lenght").value(hasItem(DEFAULT_LENGHT.intValue())))
            .andExpect(jsonPath("$.[*].lenghtAllow").value(hasItem(DEFAULT_LENGHT_ALLOW.intValue())))
            .andExpect(jsonPath("$.[*].height").value(hasItem(DEFAULT_HEIGHT.intValue())))
            .andExpect(jsonPath("$.[*].isMultispan").value(hasItem(DEFAULT_IS_MULTISPAN)))
            .andExpect(jsonPath("$.[*].gap").value(hasItem(DEFAULT_GAP.intValue())))
            .andExpect(jsonPath("$.[*].kpStart").value(hasItem(DEFAULT_KP_START.intValue())))
            .andExpect(jsonPath("$.[*].kpEnd").value(hasItem(DEFAULT_KP_END.intValue())))
            .andExpect(jsonPath("$.[*].isCurrentFlag").value(hasItem(DEFAULT_IS_CURRENT_FLAG)))
            .andExpect(jsonPath("$.[*].comment").value(hasItem(DEFAULT_COMMENT.toString())))
            .andExpect(jsonPath("$.[*].dateCreate").value(hasItem(DEFAULT_DATE_CREATE.toString())))
            .andExpect(jsonPath("$.[*].dateEdit").value(hasItem(DEFAULT_DATE_EDIT.toString())))
            .andExpect(jsonPath("$.[*].creator").value(hasItem(DEFAULT_CREATOR.toString())))
            .andExpect(jsonPath("$.[*].editor").value(hasItem(DEFAULT_EDITOR.toString())));
    }
    
    @Test
    @Transactional
    public void getFreeSpanHist() throws Exception {
        // Initialize the database
        freeSpanHistRepository.saveAndFlush(freeSpanHist);

        // Get the freeSpanHist
        restFreeSpanHistMockMvc.perform(get("/api/free-span-hists/{id}", freeSpanHist.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(freeSpanHist.getId().intValue()))
            .andExpect(jsonPath("$.dateFrom").value(DEFAULT_DATE_FROM.toString()))
            .andExpect(jsonPath("$.dateTo").value(DEFAULT_DATE_TO.toString()))
            .andExpect(jsonPath("$.idWrk").value(DEFAULT_ID_WRK.intValue()))
            .andExpect(jsonPath("$.lenght").value(DEFAULT_LENGHT.intValue()))
            .andExpect(jsonPath("$.lenghtAllow").value(DEFAULT_LENGHT_ALLOW.intValue()))
            .andExpect(jsonPath("$.height").value(DEFAULT_HEIGHT.intValue()))
            .andExpect(jsonPath("$.isMultispan").value(DEFAULT_IS_MULTISPAN))
            .andExpect(jsonPath("$.gap").value(DEFAULT_GAP.intValue()))
            .andExpect(jsonPath("$.kpStart").value(DEFAULT_KP_START.intValue()))
            .andExpect(jsonPath("$.kpEnd").value(DEFAULT_KP_END.intValue()))
            .andExpect(jsonPath("$.isCurrentFlag").value(DEFAULT_IS_CURRENT_FLAG))
            .andExpect(jsonPath("$.comment").value(DEFAULT_COMMENT.toString()))
            .andExpect(jsonPath("$.dateCreate").value(DEFAULT_DATE_CREATE.toString()))
            .andExpect(jsonPath("$.dateEdit").value(DEFAULT_DATE_EDIT.toString()))
            .andExpect(jsonPath("$.creator").value(DEFAULT_CREATOR.toString()))
            .andExpect(jsonPath("$.editor").value(DEFAULT_EDITOR.toString()));
    }

    @Test
    @Transactional
    public void getAllFreeSpanHistsByDateFromIsEqualToSomething() throws Exception {
        // Initialize the database
        freeSpanHistRepository.saveAndFlush(freeSpanHist);

        // Get all the freeSpanHistList where dateFrom equals to DEFAULT_DATE_FROM
        defaultFreeSpanHistShouldBeFound("dateFrom.equals=" + DEFAULT_DATE_FROM);

        // Get all the freeSpanHistList where dateFrom equals to UPDATED_DATE_FROM
        defaultFreeSpanHistShouldNotBeFound("dateFrom.equals=" + UPDATED_DATE_FROM);
    }

    @Test
    @Transactional
    public void getAllFreeSpanHistsByDateFromIsInShouldWork() throws Exception {
        // Initialize the database
        freeSpanHistRepository.saveAndFlush(freeSpanHist);

        // Get all the freeSpanHistList where dateFrom in DEFAULT_DATE_FROM or UPDATED_DATE_FROM
        defaultFreeSpanHistShouldBeFound("dateFrom.in=" + DEFAULT_DATE_FROM + "," + UPDATED_DATE_FROM);

        // Get all the freeSpanHistList where dateFrom equals to UPDATED_DATE_FROM
        defaultFreeSpanHistShouldNotBeFound("dateFrom.in=" + UPDATED_DATE_FROM);
    }

    @Test
    @Transactional
    public void getAllFreeSpanHistsByDateFromIsNullOrNotNull() throws Exception {
        // Initialize the database
        freeSpanHistRepository.saveAndFlush(freeSpanHist);

        // Get all the freeSpanHistList where dateFrom is not null
        defaultFreeSpanHistShouldBeFound("dateFrom.specified=true");

        // Get all the freeSpanHistList where dateFrom is null
        defaultFreeSpanHistShouldNotBeFound("dateFrom.specified=false");
    }

    @Test
    @Transactional
    public void getAllFreeSpanHistsByDateFromIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        freeSpanHistRepository.saveAndFlush(freeSpanHist);

        // Get all the freeSpanHistList where dateFrom greater than or equals to DEFAULT_DATE_FROM
        defaultFreeSpanHistShouldBeFound("dateFrom.greaterOrEqualThan=" + DEFAULT_DATE_FROM);

        // Get all the freeSpanHistList where dateFrom greater than or equals to UPDATED_DATE_FROM
        defaultFreeSpanHistShouldNotBeFound("dateFrom.greaterOrEqualThan=" + UPDATED_DATE_FROM);
    }

    @Test
    @Transactional
    public void getAllFreeSpanHistsByDateFromIsLessThanSomething() throws Exception {
        // Initialize the database
        freeSpanHistRepository.saveAndFlush(freeSpanHist);

        // Get all the freeSpanHistList where dateFrom less than or equals to DEFAULT_DATE_FROM
        defaultFreeSpanHistShouldNotBeFound("dateFrom.lessThan=" + DEFAULT_DATE_FROM);

        // Get all the freeSpanHistList where dateFrom less than or equals to UPDATED_DATE_FROM
        defaultFreeSpanHistShouldBeFound("dateFrom.lessThan=" + UPDATED_DATE_FROM);
    }


    @Test
    @Transactional
    public void getAllFreeSpanHistsByDateToIsEqualToSomething() throws Exception {
        // Initialize the database
        freeSpanHistRepository.saveAndFlush(freeSpanHist);

        // Get all the freeSpanHistList where dateTo equals to DEFAULT_DATE_TO
        defaultFreeSpanHistShouldBeFound("dateTo.equals=" + DEFAULT_DATE_TO);

        // Get all the freeSpanHistList where dateTo equals to UPDATED_DATE_TO
        defaultFreeSpanHistShouldNotBeFound("dateTo.equals=" + UPDATED_DATE_TO);
    }

    @Test
    @Transactional
    public void getAllFreeSpanHistsByDateToIsInShouldWork() throws Exception {
        // Initialize the database
        freeSpanHistRepository.saveAndFlush(freeSpanHist);

        // Get all the freeSpanHistList where dateTo in DEFAULT_DATE_TO or UPDATED_DATE_TO
        defaultFreeSpanHistShouldBeFound("dateTo.in=" + DEFAULT_DATE_TO + "," + UPDATED_DATE_TO);

        // Get all the freeSpanHistList where dateTo equals to UPDATED_DATE_TO
        defaultFreeSpanHistShouldNotBeFound("dateTo.in=" + UPDATED_DATE_TO);
    }

    @Test
    @Transactional
    public void getAllFreeSpanHistsByDateToIsNullOrNotNull() throws Exception {
        // Initialize the database
        freeSpanHistRepository.saveAndFlush(freeSpanHist);

        // Get all the freeSpanHistList where dateTo is not null
        defaultFreeSpanHistShouldBeFound("dateTo.specified=true");

        // Get all the freeSpanHistList where dateTo is null
        defaultFreeSpanHistShouldNotBeFound("dateTo.specified=false");
    }

    @Test
    @Transactional
    public void getAllFreeSpanHistsByDateToIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        freeSpanHistRepository.saveAndFlush(freeSpanHist);

        // Get all the freeSpanHistList where dateTo greater than or equals to DEFAULT_DATE_TO
        defaultFreeSpanHistShouldBeFound("dateTo.greaterOrEqualThan=" + DEFAULT_DATE_TO);

        // Get all the freeSpanHistList where dateTo greater than or equals to UPDATED_DATE_TO
        defaultFreeSpanHistShouldNotBeFound("dateTo.greaterOrEqualThan=" + UPDATED_DATE_TO);
    }

    @Test
    @Transactional
    public void getAllFreeSpanHistsByDateToIsLessThanSomething() throws Exception {
        // Initialize the database
        freeSpanHistRepository.saveAndFlush(freeSpanHist);

        // Get all the freeSpanHistList where dateTo less than or equals to DEFAULT_DATE_TO
        defaultFreeSpanHistShouldNotBeFound("dateTo.lessThan=" + DEFAULT_DATE_TO);

        // Get all the freeSpanHistList where dateTo less than or equals to UPDATED_DATE_TO
        defaultFreeSpanHistShouldBeFound("dateTo.lessThan=" + UPDATED_DATE_TO);
    }


    @Test
    @Transactional
    public void getAllFreeSpanHistsByIdWrkIsEqualToSomething() throws Exception {
        // Initialize the database
        freeSpanHistRepository.saveAndFlush(freeSpanHist);

        // Get all the freeSpanHistList where idWrk equals to DEFAULT_ID_WRK
        defaultFreeSpanHistShouldBeFound("idWrk.equals=" + DEFAULT_ID_WRK);

        // Get all the freeSpanHistList where idWrk equals to UPDATED_ID_WRK
        defaultFreeSpanHistShouldNotBeFound("idWrk.equals=" + UPDATED_ID_WRK);
    }

    @Test
    @Transactional
    public void getAllFreeSpanHistsByIdWrkIsInShouldWork() throws Exception {
        // Initialize the database
        freeSpanHistRepository.saveAndFlush(freeSpanHist);

        // Get all the freeSpanHistList where idWrk in DEFAULT_ID_WRK or UPDATED_ID_WRK
        defaultFreeSpanHistShouldBeFound("idWrk.in=" + DEFAULT_ID_WRK + "," + UPDATED_ID_WRK);

        // Get all the freeSpanHistList where idWrk equals to UPDATED_ID_WRK
        defaultFreeSpanHistShouldNotBeFound("idWrk.in=" + UPDATED_ID_WRK);
    }

    @Test
    @Transactional
    public void getAllFreeSpanHistsByIdWrkIsNullOrNotNull() throws Exception {
        // Initialize the database
        freeSpanHistRepository.saveAndFlush(freeSpanHist);

        // Get all the freeSpanHistList where idWrk is not null
        defaultFreeSpanHistShouldBeFound("idWrk.specified=true");

        // Get all the freeSpanHistList where idWrk is null
        defaultFreeSpanHistShouldNotBeFound("idWrk.specified=false");
    }

    @Test
    @Transactional
    public void getAllFreeSpanHistsByIdWrkIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        freeSpanHistRepository.saveAndFlush(freeSpanHist);

        // Get all the freeSpanHistList where idWrk greater than or equals to DEFAULT_ID_WRK
        defaultFreeSpanHistShouldBeFound("idWrk.greaterOrEqualThan=" + DEFAULT_ID_WRK);

        // Get all the freeSpanHistList where idWrk greater than or equals to UPDATED_ID_WRK
        defaultFreeSpanHistShouldNotBeFound("idWrk.greaterOrEqualThan=" + UPDATED_ID_WRK);
    }

    @Test
    @Transactional
    public void getAllFreeSpanHistsByIdWrkIsLessThanSomething() throws Exception {
        // Initialize the database
        freeSpanHistRepository.saveAndFlush(freeSpanHist);

        // Get all the freeSpanHistList where idWrk less than or equals to DEFAULT_ID_WRK
        defaultFreeSpanHistShouldNotBeFound("idWrk.lessThan=" + DEFAULT_ID_WRK);

        // Get all the freeSpanHistList where idWrk less than or equals to UPDATED_ID_WRK
        defaultFreeSpanHistShouldBeFound("idWrk.lessThan=" + UPDATED_ID_WRK);
    }


    @Test
    @Transactional
    public void getAllFreeSpanHistsByLenghtIsEqualToSomething() throws Exception {
        // Initialize the database
        freeSpanHistRepository.saveAndFlush(freeSpanHist);

        // Get all the freeSpanHistList where lenght equals to DEFAULT_LENGHT
        defaultFreeSpanHistShouldBeFound("lenght.equals=" + DEFAULT_LENGHT);

        // Get all the freeSpanHistList where lenght equals to UPDATED_LENGHT
        defaultFreeSpanHistShouldNotBeFound("lenght.equals=" + UPDATED_LENGHT);
    }

    @Test
    @Transactional
    public void getAllFreeSpanHistsByLenghtIsInShouldWork() throws Exception {
        // Initialize the database
        freeSpanHistRepository.saveAndFlush(freeSpanHist);

        // Get all the freeSpanHistList where lenght in DEFAULT_LENGHT or UPDATED_LENGHT
        defaultFreeSpanHistShouldBeFound("lenght.in=" + DEFAULT_LENGHT + "," + UPDATED_LENGHT);

        // Get all the freeSpanHistList where lenght equals to UPDATED_LENGHT
        defaultFreeSpanHistShouldNotBeFound("lenght.in=" + UPDATED_LENGHT);
    }

    @Test
    @Transactional
    public void getAllFreeSpanHistsByLenghtIsNullOrNotNull() throws Exception {
        // Initialize the database
        freeSpanHistRepository.saveAndFlush(freeSpanHist);

        // Get all the freeSpanHistList where lenght is not null
        defaultFreeSpanHistShouldBeFound("lenght.specified=true");

        // Get all the freeSpanHistList where lenght is null
        defaultFreeSpanHistShouldNotBeFound("lenght.specified=false");
    }

    @Test
    @Transactional
    public void getAllFreeSpanHistsByLenghtAllowIsEqualToSomething() throws Exception {
        // Initialize the database
        freeSpanHistRepository.saveAndFlush(freeSpanHist);

        // Get all the freeSpanHistList where lenghtAllow equals to DEFAULT_LENGHT_ALLOW
        defaultFreeSpanHistShouldBeFound("lenghtAllow.equals=" + DEFAULT_LENGHT_ALLOW);

        // Get all the freeSpanHistList where lenghtAllow equals to UPDATED_LENGHT_ALLOW
        defaultFreeSpanHistShouldNotBeFound("lenghtAllow.equals=" + UPDATED_LENGHT_ALLOW);
    }

    @Test
    @Transactional
    public void getAllFreeSpanHistsByLenghtAllowIsInShouldWork() throws Exception {
        // Initialize the database
        freeSpanHistRepository.saveAndFlush(freeSpanHist);

        // Get all the freeSpanHistList where lenghtAllow in DEFAULT_LENGHT_ALLOW or UPDATED_LENGHT_ALLOW
        defaultFreeSpanHistShouldBeFound("lenghtAllow.in=" + DEFAULT_LENGHT_ALLOW + "," + UPDATED_LENGHT_ALLOW);

        // Get all the freeSpanHistList where lenghtAllow equals to UPDATED_LENGHT_ALLOW
        defaultFreeSpanHistShouldNotBeFound("lenghtAllow.in=" + UPDATED_LENGHT_ALLOW);
    }

    @Test
    @Transactional
    public void getAllFreeSpanHistsByLenghtAllowIsNullOrNotNull() throws Exception {
        // Initialize the database
        freeSpanHistRepository.saveAndFlush(freeSpanHist);

        // Get all the freeSpanHistList where lenghtAllow is not null
        defaultFreeSpanHistShouldBeFound("lenghtAllow.specified=true");

        // Get all the freeSpanHistList where lenghtAllow is null
        defaultFreeSpanHistShouldNotBeFound("lenghtAllow.specified=false");
    }

    @Test
    @Transactional
    public void getAllFreeSpanHistsByHeightIsEqualToSomething() throws Exception {
        // Initialize the database
        freeSpanHistRepository.saveAndFlush(freeSpanHist);

        // Get all the freeSpanHistList where height equals to DEFAULT_HEIGHT
        defaultFreeSpanHistShouldBeFound("height.equals=" + DEFAULT_HEIGHT);

        // Get all the freeSpanHistList where height equals to UPDATED_HEIGHT
        defaultFreeSpanHistShouldNotBeFound("height.equals=" + UPDATED_HEIGHT);
    }

    @Test
    @Transactional
    public void getAllFreeSpanHistsByHeightIsInShouldWork() throws Exception {
        // Initialize the database
        freeSpanHistRepository.saveAndFlush(freeSpanHist);

        // Get all the freeSpanHistList where height in DEFAULT_HEIGHT or UPDATED_HEIGHT
        defaultFreeSpanHistShouldBeFound("height.in=" + DEFAULT_HEIGHT + "," + UPDATED_HEIGHT);

        // Get all the freeSpanHistList where height equals to UPDATED_HEIGHT
        defaultFreeSpanHistShouldNotBeFound("height.in=" + UPDATED_HEIGHT);
    }

    @Test
    @Transactional
    public void getAllFreeSpanHistsByHeightIsNullOrNotNull() throws Exception {
        // Initialize the database
        freeSpanHistRepository.saveAndFlush(freeSpanHist);

        // Get all the freeSpanHistList where height is not null
        defaultFreeSpanHistShouldBeFound("height.specified=true");

        // Get all the freeSpanHistList where height is null
        defaultFreeSpanHistShouldNotBeFound("height.specified=false");
    }

    @Test
    @Transactional
    public void getAllFreeSpanHistsByIsMultispanIsEqualToSomething() throws Exception {
        // Initialize the database
        freeSpanHistRepository.saveAndFlush(freeSpanHist);

        // Get all the freeSpanHistList where isMultispan equals to DEFAULT_IS_MULTISPAN
        defaultFreeSpanHistShouldBeFound("isMultispan.equals=" + DEFAULT_IS_MULTISPAN);

        // Get all the freeSpanHistList where isMultispan equals to UPDATED_IS_MULTISPAN
        defaultFreeSpanHistShouldNotBeFound("isMultispan.equals=" + UPDATED_IS_MULTISPAN);
    }

    @Test
    @Transactional
    public void getAllFreeSpanHistsByIsMultispanIsInShouldWork() throws Exception {
        // Initialize the database
        freeSpanHistRepository.saveAndFlush(freeSpanHist);

        // Get all the freeSpanHistList where isMultispan in DEFAULT_IS_MULTISPAN or UPDATED_IS_MULTISPAN
        defaultFreeSpanHistShouldBeFound("isMultispan.in=" + DEFAULT_IS_MULTISPAN + "," + UPDATED_IS_MULTISPAN);

        // Get all the freeSpanHistList where isMultispan equals to UPDATED_IS_MULTISPAN
        defaultFreeSpanHistShouldNotBeFound("isMultispan.in=" + UPDATED_IS_MULTISPAN);
    }

    @Test
    @Transactional
    public void getAllFreeSpanHistsByIsMultispanIsNullOrNotNull() throws Exception {
        // Initialize the database
        freeSpanHistRepository.saveAndFlush(freeSpanHist);

        // Get all the freeSpanHistList where isMultispan is not null
        defaultFreeSpanHistShouldBeFound("isMultispan.specified=true");

        // Get all the freeSpanHistList where isMultispan is null
        defaultFreeSpanHistShouldNotBeFound("isMultispan.specified=false");
    }

    @Test
    @Transactional
    public void getAllFreeSpanHistsByIsMultispanIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        freeSpanHistRepository.saveAndFlush(freeSpanHist);

        // Get all the freeSpanHistList where isMultispan greater than or equals to DEFAULT_IS_MULTISPAN
        defaultFreeSpanHistShouldBeFound("isMultispan.greaterOrEqualThan=" + DEFAULT_IS_MULTISPAN);

        // Get all the freeSpanHistList where isMultispan greater than or equals to UPDATED_IS_MULTISPAN
        defaultFreeSpanHistShouldNotBeFound("isMultispan.greaterOrEqualThan=" + UPDATED_IS_MULTISPAN);
    }

    @Test
    @Transactional
    public void getAllFreeSpanHistsByIsMultispanIsLessThanSomething() throws Exception {
        // Initialize the database
        freeSpanHistRepository.saveAndFlush(freeSpanHist);

        // Get all the freeSpanHistList where isMultispan less than or equals to DEFAULT_IS_MULTISPAN
        defaultFreeSpanHistShouldNotBeFound("isMultispan.lessThan=" + DEFAULT_IS_MULTISPAN);

        // Get all the freeSpanHistList where isMultispan less than or equals to UPDATED_IS_MULTISPAN
        defaultFreeSpanHistShouldBeFound("isMultispan.lessThan=" + UPDATED_IS_MULTISPAN);
    }


    @Test
    @Transactional
    public void getAllFreeSpanHistsByGapIsEqualToSomething() throws Exception {
        // Initialize the database
        freeSpanHistRepository.saveAndFlush(freeSpanHist);

        // Get all the freeSpanHistList where gap equals to DEFAULT_GAP
        defaultFreeSpanHistShouldBeFound("gap.equals=" + DEFAULT_GAP);

        // Get all the freeSpanHistList where gap equals to UPDATED_GAP
        defaultFreeSpanHistShouldNotBeFound("gap.equals=" + UPDATED_GAP);
    }

    @Test
    @Transactional
    public void getAllFreeSpanHistsByGapIsInShouldWork() throws Exception {
        // Initialize the database
        freeSpanHistRepository.saveAndFlush(freeSpanHist);

        // Get all the freeSpanHistList where gap in DEFAULT_GAP or UPDATED_GAP
        defaultFreeSpanHistShouldBeFound("gap.in=" + DEFAULT_GAP + "," + UPDATED_GAP);

        // Get all the freeSpanHistList where gap equals to UPDATED_GAP
        defaultFreeSpanHistShouldNotBeFound("gap.in=" + UPDATED_GAP);
    }

    @Test
    @Transactional
    public void getAllFreeSpanHistsByGapIsNullOrNotNull() throws Exception {
        // Initialize the database
        freeSpanHistRepository.saveAndFlush(freeSpanHist);

        // Get all the freeSpanHistList where gap is not null
        defaultFreeSpanHistShouldBeFound("gap.specified=true");

        // Get all the freeSpanHistList where gap is null
        defaultFreeSpanHistShouldNotBeFound("gap.specified=false");
    }

    @Test
    @Transactional
    public void getAllFreeSpanHistsByKpStartIsEqualToSomething() throws Exception {
        // Initialize the database
        freeSpanHistRepository.saveAndFlush(freeSpanHist);

        // Get all the freeSpanHistList where kpStart equals to DEFAULT_KP_START
        defaultFreeSpanHistShouldBeFound("kpStart.equals=" + DEFAULT_KP_START);

        // Get all the freeSpanHistList where kpStart equals to UPDATED_KP_START
        defaultFreeSpanHistShouldNotBeFound("kpStart.equals=" + UPDATED_KP_START);
    }

    @Test
    @Transactional
    public void getAllFreeSpanHistsByKpStartIsInShouldWork() throws Exception {
        // Initialize the database
        freeSpanHistRepository.saveAndFlush(freeSpanHist);

        // Get all the freeSpanHistList where kpStart in DEFAULT_KP_START or UPDATED_KP_START
        defaultFreeSpanHistShouldBeFound("kpStart.in=" + DEFAULT_KP_START + "," + UPDATED_KP_START);

        // Get all the freeSpanHistList where kpStart equals to UPDATED_KP_START
        defaultFreeSpanHistShouldNotBeFound("kpStart.in=" + UPDATED_KP_START);
    }

    @Test
    @Transactional
    public void getAllFreeSpanHistsByKpStartIsNullOrNotNull() throws Exception {
        // Initialize the database
        freeSpanHistRepository.saveAndFlush(freeSpanHist);

        // Get all the freeSpanHistList where kpStart is not null
        defaultFreeSpanHistShouldBeFound("kpStart.specified=true");

        // Get all the freeSpanHistList where kpStart is null
        defaultFreeSpanHistShouldNotBeFound("kpStart.specified=false");
    }

    @Test
    @Transactional
    public void getAllFreeSpanHistsByKpEndIsEqualToSomething() throws Exception {
        // Initialize the database
        freeSpanHistRepository.saveAndFlush(freeSpanHist);

        // Get all the freeSpanHistList where kpEnd equals to DEFAULT_KP_END
        defaultFreeSpanHistShouldBeFound("kpEnd.equals=" + DEFAULT_KP_END);

        // Get all the freeSpanHistList where kpEnd equals to UPDATED_KP_END
        defaultFreeSpanHistShouldNotBeFound("kpEnd.equals=" + UPDATED_KP_END);
    }

    @Test
    @Transactional
    public void getAllFreeSpanHistsByKpEndIsInShouldWork() throws Exception {
        // Initialize the database
        freeSpanHistRepository.saveAndFlush(freeSpanHist);

        // Get all the freeSpanHistList where kpEnd in DEFAULT_KP_END or UPDATED_KP_END
        defaultFreeSpanHistShouldBeFound("kpEnd.in=" + DEFAULT_KP_END + "," + UPDATED_KP_END);

        // Get all the freeSpanHistList where kpEnd equals to UPDATED_KP_END
        defaultFreeSpanHistShouldNotBeFound("kpEnd.in=" + UPDATED_KP_END);
    }

    @Test
    @Transactional
    public void getAllFreeSpanHistsByKpEndIsNullOrNotNull() throws Exception {
        // Initialize the database
        freeSpanHistRepository.saveAndFlush(freeSpanHist);

        // Get all the freeSpanHistList where kpEnd is not null
        defaultFreeSpanHistShouldBeFound("kpEnd.specified=true");

        // Get all the freeSpanHistList where kpEnd is null
        defaultFreeSpanHistShouldNotBeFound("kpEnd.specified=false");
    }

    @Test
    @Transactional
    public void getAllFreeSpanHistsByIsCurrentFlagIsEqualToSomething() throws Exception {
        // Initialize the database
        freeSpanHistRepository.saveAndFlush(freeSpanHist);

        // Get all the freeSpanHistList where isCurrentFlag equals to DEFAULT_IS_CURRENT_FLAG
        defaultFreeSpanHistShouldBeFound("isCurrentFlag.equals=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the freeSpanHistList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultFreeSpanHistShouldNotBeFound("isCurrentFlag.equals=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllFreeSpanHistsByIsCurrentFlagIsInShouldWork() throws Exception {
        // Initialize the database
        freeSpanHistRepository.saveAndFlush(freeSpanHist);

        // Get all the freeSpanHistList where isCurrentFlag in DEFAULT_IS_CURRENT_FLAG or UPDATED_IS_CURRENT_FLAG
        defaultFreeSpanHistShouldBeFound("isCurrentFlag.in=" + DEFAULT_IS_CURRENT_FLAG + "," + UPDATED_IS_CURRENT_FLAG);

        // Get all the freeSpanHistList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultFreeSpanHistShouldNotBeFound("isCurrentFlag.in=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllFreeSpanHistsByIsCurrentFlagIsNullOrNotNull() throws Exception {
        // Initialize the database
        freeSpanHistRepository.saveAndFlush(freeSpanHist);

        // Get all the freeSpanHistList where isCurrentFlag is not null
        defaultFreeSpanHistShouldBeFound("isCurrentFlag.specified=true");

        // Get all the freeSpanHistList where isCurrentFlag is null
        defaultFreeSpanHistShouldNotBeFound("isCurrentFlag.specified=false");
    }

    @Test
    @Transactional
    public void getAllFreeSpanHistsByIsCurrentFlagIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        freeSpanHistRepository.saveAndFlush(freeSpanHist);

        // Get all the freeSpanHistList where isCurrentFlag greater than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultFreeSpanHistShouldBeFound("isCurrentFlag.greaterOrEqualThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the freeSpanHistList where isCurrentFlag greater than or equals to UPDATED_IS_CURRENT_FLAG
        defaultFreeSpanHistShouldNotBeFound("isCurrentFlag.greaterOrEqualThan=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllFreeSpanHistsByIsCurrentFlagIsLessThanSomething() throws Exception {
        // Initialize the database
        freeSpanHistRepository.saveAndFlush(freeSpanHist);

        // Get all the freeSpanHistList where isCurrentFlag less than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultFreeSpanHistShouldNotBeFound("isCurrentFlag.lessThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the freeSpanHistList where isCurrentFlag less than or equals to UPDATED_IS_CURRENT_FLAG
        defaultFreeSpanHistShouldBeFound("isCurrentFlag.lessThan=" + UPDATED_IS_CURRENT_FLAG);
    }


    @Test
    @Transactional
    public void getAllFreeSpanHistsByCommentIsEqualToSomething() throws Exception {
        // Initialize the database
        freeSpanHistRepository.saveAndFlush(freeSpanHist);

        // Get all the freeSpanHistList where comment equals to DEFAULT_COMMENT
        defaultFreeSpanHistShouldBeFound("comment.equals=" + DEFAULT_COMMENT);

        // Get all the freeSpanHistList where comment equals to UPDATED_COMMENT
        defaultFreeSpanHistShouldNotBeFound("comment.equals=" + UPDATED_COMMENT);
    }

    @Test
    @Transactional
    public void getAllFreeSpanHistsByCommentIsInShouldWork() throws Exception {
        // Initialize the database
        freeSpanHistRepository.saveAndFlush(freeSpanHist);

        // Get all the freeSpanHistList where comment in DEFAULT_COMMENT or UPDATED_COMMENT
        defaultFreeSpanHistShouldBeFound("comment.in=" + DEFAULT_COMMENT + "," + UPDATED_COMMENT);

        // Get all the freeSpanHistList where comment equals to UPDATED_COMMENT
        defaultFreeSpanHistShouldNotBeFound("comment.in=" + UPDATED_COMMENT);
    }

    @Test
    @Transactional
    public void getAllFreeSpanHistsByCommentIsNullOrNotNull() throws Exception {
        // Initialize the database
        freeSpanHistRepository.saveAndFlush(freeSpanHist);

        // Get all the freeSpanHistList where comment is not null
        defaultFreeSpanHistShouldBeFound("comment.specified=true");

        // Get all the freeSpanHistList where comment is null
        defaultFreeSpanHistShouldNotBeFound("comment.specified=false");
    }

    @Test
    @Transactional
    public void getAllFreeSpanHistsByDateCreateIsEqualToSomething() throws Exception {
        // Initialize the database
        freeSpanHistRepository.saveAndFlush(freeSpanHist);

        // Get all the freeSpanHistList where dateCreate equals to DEFAULT_DATE_CREATE
        defaultFreeSpanHistShouldBeFound("dateCreate.equals=" + DEFAULT_DATE_CREATE);

        // Get all the freeSpanHistList where dateCreate equals to UPDATED_DATE_CREATE
        defaultFreeSpanHistShouldNotBeFound("dateCreate.equals=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllFreeSpanHistsByDateCreateIsInShouldWork() throws Exception {
        // Initialize the database
        freeSpanHistRepository.saveAndFlush(freeSpanHist);

        // Get all the freeSpanHistList where dateCreate in DEFAULT_DATE_CREATE or UPDATED_DATE_CREATE
        defaultFreeSpanHistShouldBeFound("dateCreate.in=" + DEFAULT_DATE_CREATE + "," + UPDATED_DATE_CREATE);

        // Get all the freeSpanHistList where dateCreate equals to UPDATED_DATE_CREATE
        defaultFreeSpanHistShouldNotBeFound("dateCreate.in=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllFreeSpanHistsByDateCreateIsNullOrNotNull() throws Exception {
        // Initialize the database
        freeSpanHistRepository.saveAndFlush(freeSpanHist);

        // Get all the freeSpanHistList where dateCreate is not null
        defaultFreeSpanHistShouldBeFound("dateCreate.specified=true");

        // Get all the freeSpanHistList where dateCreate is null
        defaultFreeSpanHistShouldNotBeFound("dateCreate.specified=false");
    }

    @Test
    @Transactional
    public void getAllFreeSpanHistsByDateEditIsEqualToSomething() throws Exception {
        // Initialize the database
        freeSpanHistRepository.saveAndFlush(freeSpanHist);

        // Get all the freeSpanHistList where dateEdit equals to DEFAULT_DATE_EDIT
        defaultFreeSpanHistShouldBeFound("dateEdit.equals=" + DEFAULT_DATE_EDIT);

        // Get all the freeSpanHistList where dateEdit equals to UPDATED_DATE_EDIT
        defaultFreeSpanHistShouldNotBeFound("dateEdit.equals=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllFreeSpanHistsByDateEditIsInShouldWork() throws Exception {
        // Initialize the database
        freeSpanHistRepository.saveAndFlush(freeSpanHist);

        // Get all the freeSpanHistList where dateEdit in DEFAULT_DATE_EDIT or UPDATED_DATE_EDIT
        defaultFreeSpanHistShouldBeFound("dateEdit.in=" + DEFAULT_DATE_EDIT + "," + UPDATED_DATE_EDIT);

        // Get all the freeSpanHistList where dateEdit equals to UPDATED_DATE_EDIT
        defaultFreeSpanHistShouldNotBeFound("dateEdit.in=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllFreeSpanHistsByDateEditIsNullOrNotNull() throws Exception {
        // Initialize the database
        freeSpanHistRepository.saveAndFlush(freeSpanHist);

        // Get all the freeSpanHistList where dateEdit is not null
        defaultFreeSpanHistShouldBeFound("dateEdit.specified=true");

        // Get all the freeSpanHistList where dateEdit is null
        defaultFreeSpanHistShouldNotBeFound("dateEdit.specified=false");
    }

    @Test
    @Transactional
    public void getAllFreeSpanHistsByCreatorIsEqualToSomething() throws Exception {
        // Initialize the database
        freeSpanHistRepository.saveAndFlush(freeSpanHist);

        // Get all the freeSpanHistList where creator equals to DEFAULT_CREATOR
        defaultFreeSpanHistShouldBeFound("creator.equals=" + DEFAULT_CREATOR);

        // Get all the freeSpanHistList where creator equals to UPDATED_CREATOR
        defaultFreeSpanHistShouldNotBeFound("creator.equals=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllFreeSpanHistsByCreatorIsInShouldWork() throws Exception {
        // Initialize the database
        freeSpanHistRepository.saveAndFlush(freeSpanHist);

        // Get all the freeSpanHistList where creator in DEFAULT_CREATOR or UPDATED_CREATOR
        defaultFreeSpanHistShouldBeFound("creator.in=" + DEFAULT_CREATOR + "," + UPDATED_CREATOR);

        // Get all the freeSpanHistList where creator equals to UPDATED_CREATOR
        defaultFreeSpanHistShouldNotBeFound("creator.in=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllFreeSpanHistsByCreatorIsNullOrNotNull() throws Exception {
        // Initialize the database
        freeSpanHistRepository.saveAndFlush(freeSpanHist);

        // Get all the freeSpanHistList where creator is not null
        defaultFreeSpanHistShouldBeFound("creator.specified=true");

        // Get all the freeSpanHistList where creator is null
        defaultFreeSpanHistShouldNotBeFound("creator.specified=false");
    }

    @Test
    @Transactional
    public void getAllFreeSpanHistsByEditorIsEqualToSomething() throws Exception {
        // Initialize the database
        freeSpanHistRepository.saveAndFlush(freeSpanHist);

        // Get all the freeSpanHistList where editor equals to DEFAULT_EDITOR
        defaultFreeSpanHistShouldBeFound("editor.equals=" + DEFAULT_EDITOR);

        // Get all the freeSpanHistList where editor equals to UPDATED_EDITOR
        defaultFreeSpanHistShouldNotBeFound("editor.equals=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllFreeSpanHistsByEditorIsInShouldWork() throws Exception {
        // Initialize the database
        freeSpanHistRepository.saveAndFlush(freeSpanHist);

        // Get all the freeSpanHistList where editor in DEFAULT_EDITOR or UPDATED_EDITOR
        defaultFreeSpanHistShouldBeFound("editor.in=" + DEFAULT_EDITOR + "," + UPDATED_EDITOR);

        // Get all the freeSpanHistList where editor equals to UPDATED_EDITOR
        defaultFreeSpanHistShouldNotBeFound("editor.in=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllFreeSpanHistsByEditorIsNullOrNotNull() throws Exception {
        // Initialize the database
        freeSpanHistRepository.saveAndFlush(freeSpanHist);

        // Get all the freeSpanHistList where editor is not null
        defaultFreeSpanHistShouldBeFound("editor.specified=true");

        // Get all the freeSpanHistList where editor is null
        defaultFreeSpanHistShouldNotBeFound("editor.specified=false");
    }

    @Test
    @Transactional
    public void getAllFreeSpanHistsByFreeSpanIsEqualToSomething() throws Exception {
        // Initialize the database
        FreeSpan freeSpan = FreeSpanResourceIT.createEntity(em);
        em.persist(freeSpan);
        em.flush();
        freeSpanHist.setFreeSpan(freeSpan);
        freeSpanHistRepository.saveAndFlush(freeSpanHist);
        Long freeSpanId = freeSpan.getId();

        // Get all the freeSpanHistList where freeSpan equals to freeSpanId
        defaultFreeSpanHistShouldBeFound("freeSpanId.equals=" + freeSpanId);

        // Get all the freeSpanHistList where freeSpan equals to freeSpanId + 1
        defaultFreeSpanHistShouldNotBeFound("freeSpanId.equals=" + (freeSpanId + 1));
    }


    @Test
    @Transactional
    public void getAllFreeSpanHistsByIdPipelineSectionIsEqualToSomething() throws Exception {
        // Get already existing entity
        PipelineSection idPipelineSection = freeSpanHist.getIdPipelineSection();
        freeSpanHistRepository.saveAndFlush(freeSpanHist);
        Long idPipelineSectionId = idPipelineSection.getId();

        // Get all the freeSpanHistList where idPipelineSection equals to idPipelineSectionId
        defaultFreeSpanHistShouldBeFound("idPipelineSectionId.equals=" + idPipelineSectionId);

        // Get all the freeSpanHistList where idPipelineSection equals to idPipelineSectionId + 1
        defaultFreeSpanHistShouldNotBeFound("idPipelineSectionId.equals=" + (idPipelineSectionId + 1));
    }


    @Test
    @Transactional
    public void getAllFreeSpanHistsByIdStatusIsEqualToSomething() throws Exception {
        // Get already existing entity
        ListObjectStatus idStatus = freeSpanHist.getIdStatus();
        freeSpanHistRepository.saveAndFlush(freeSpanHist);
        Long idStatusId = idStatus.getId();

        // Get all the freeSpanHistList where idStatus equals to idStatusId
        defaultFreeSpanHistShouldBeFound("idStatusId.equals=" + idStatusId);

        // Get all the freeSpanHistList where idStatus equals to idStatusId + 1
        defaultFreeSpanHistShouldNotBeFound("idStatusId.equals=" + (idStatusId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultFreeSpanHistShouldBeFound(String filter) throws Exception {
        restFreeSpanHistMockMvc.perform(get("/api/free-span-hists?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(freeSpanHist.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateFrom").value(hasItem(DEFAULT_DATE_FROM.toString())))
            .andExpect(jsonPath("$.[*].dateTo").value(hasItem(DEFAULT_DATE_TO.toString())))
            .andExpect(jsonPath("$.[*].idWrk").value(hasItem(DEFAULT_ID_WRK.intValue())))
            .andExpect(jsonPath("$.[*].lenght").value(hasItem(DEFAULT_LENGHT.intValue())))
            .andExpect(jsonPath("$.[*].lenghtAllow").value(hasItem(DEFAULT_LENGHT_ALLOW.intValue())))
            .andExpect(jsonPath("$.[*].height").value(hasItem(DEFAULT_HEIGHT.intValue())))
            .andExpect(jsonPath("$.[*].isMultispan").value(hasItem(DEFAULT_IS_MULTISPAN)))
            .andExpect(jsonPath("$.[*].gap").value(hasItem(DEFAULT_GAP.intValue())))
            .andExpect(jsonPath("$.[*].kpStart").value(hasItem(DEFAULT_KP_START.intValue())))
            .andExpect(jsonPath("$.[*].kpEnd").value(hasItem(DEFAULT_KP_END.intValue())))
            .andExpect(jsonPath("$.[*].isCurrentFlag").value(hasItem(DEFAULT_IS_CURRENT_FLAG)))
            .andExpect(jsonPath("$.[*].comment").value(hasItem(DEFAULT_COMMENT)))
            .andExpect(jsonPath("$.[*].dateCreate").value(hasItem(DEFAULT_DATE_CREATE.toString())))
            .andExpect(jsonPath("$.[*].dateEdit").value(hasItem(DEFAULT_DATE_EDIT.toString())))
            .andExpect(jsonPath("$.[*].creator").value(hasItem(DEFAULT_CREATOR)))
            .andExpect(jsonPath("$.[*].editor").value(hasItem(DEFAULT_EDITOR)));

        // Check, that the count call also returns 1
        restFreeSpanHistMockMvc.perform(get("/api/free-span-hists/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultFreeSpanHistShouldNotBeFound(String filter) throws Exception {
        restFreeSpanHistMockMvc.perform(get("/api/free-span-hists?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restFreeSpanHistMockMvc.perform(get("/api/free-span-hists/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingFreeSpanHist() throws Exception {
        // Get the freeSpanHist
        restFreeSpanHistMockMvc.perform(get("/api/free-span-hists/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFreeSpanHist() throws Exception {
        // Initialize the database
        freeSpanHistRepository.saveAndFlush(freeSpanHist);

        int databaseSizeBeforeUpdate = freeSpanHistRepository.findAll().size();

        // Update the freeSpanHist
        FreeSpanHist updatedFreeSpanHist = freeSpanHistRepository.findById(freeSpanHist.getId()).get();
        // Disconnect from session so that the updates on updatedFreeSpanHist are not directly saved in db
        em.detach(updatedFreeSpanHist);
        updatedFreeSpanHist
            .dateFrom(UPDATED_DATE_FROM)
            .dateTo(UPDATED_DATE_TO)
            .idWrk(UPDATED_ID_WRK)
            .lenght(UPDATED_LENGHT)
            .lenghtAllow(UPDATED_LENGHT_ALLOW)
            .height(UPDATED_HEIGHT)
            .isMultispan(UPDATED_IS_MULTISPAN)
            .gap(UPDATED_GAP)
            .kpStart(UPDATED_KP_START)
            .kpEnd(UPDATED_KP_END)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .comment(UPDATED_COMMENT)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        FreeSpanHistDTO freeSpanHistDTO = freeSpanHistMapper.toDto(updatedFreeSpanHist);

        restFreeSpanHistMockMvc.perform(put("/api/free-span-hists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(freeSpanHistDTO)))
            .andExpect(status().isOk());

        // Validate the FreeSpanHist in the database
        List<FreeSpanHist> freeSpanHistList = freeSpanHistRepository.findAll();
        assertThat(freeSpanHistList).hasSize(databaseSizeBeforeUpdate);
        FreeSpanHist testFreeSpanHist = freeSpanHistList.get(freeSpanHistList.size() - 1);
        assertThat(testFreeSpanHist.getDateFrom()).isEqualTo(UPDATED_DATE_FROM);
        assertThat(testFreeSpanHist.getDateTo()).isEqualTo(UPDATED_DATE_TO);
        assertThat(testFreeSpanHist.getIdWrk()).isEqualTo(UPDATED_ID_WRK);
        assertThat(testFreeSpanHist.getLenght()).isEqualTo(UPDATED_LENGHT);
        assertThat(testFreeSpanHist.getLenghtAllow()).isEqualTo(UPDATED_LENGHT_ALLOW);
        assertThat(testFreeSpanHist.getHeight()).isEqualTo(UPDATED_HEIGHT);
        assertThat(testFreeSpanHist.getIsMultispan()).isEqualTo(UPDATED_IS_MULTISPAN);
        assertThat(testFreeSpanHist.getGap()).isEqualTo(UPDATED_GAP);
        assertThat(testFreeSpanHist.getKpStart()).isEqualTo(UPDATED_KP_START);
        assertThat(testFreeSpanHist.getKpEnd()).isEqualTo(UPDATED_KP_END);
        assertThat(testFreeSpanHist.getIsCurrentFlag()).isEqualTo(UPDATED_IS_CURRENT_FLAG);
        assertThat(testFreeSpanHist.getComment()).isEqualTo(UPDATED_COMMENT);
        assertThat(testFreeSpanHist.getDateCreate()).isEqualTo(UPDATED_DATE_CREATE);
        assertThat(testFreeSpanHist.getDateEdit()).isEqualTo(UPDATED_DATE_EDIT);
        assertThat(testFreeSpanHist.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testFreeSpanHist.getEditor()).isEqualTo(UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void updateNonExistingFreeSpanHist() throws Exception {
        int databaseSizeBeforeUpdate = freeSpanHistRepository.findAll().size();

        // Create the FreeSpanHist
        FreeSpanHistDTO freeSpanHistDTO = freeSpanHistMapper.toDto(freeSpanHist);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFreeSpanHistMockMvc.perform(put("/api/free-span-hists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(freeSpanHistDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FreeSpanHist in the database
        List<FreeSpanHist> freeSpanHistList = freeSpanHistRepository.findAll();
        assertThat(freeSpanHistList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFreeSpanHist() throws Exception {
        // Initialize the database
        freeSpanHistRepository.saveAndFlush(freeSpanHist);

        int databaseSizeBeforeDelete = freeSpanHistRepository.findAll().size();

        // Delete the freeSpanHist
        restFreeSpanHistMockMvc.perform(delete("/api/free-span-hists/{id}", freeSpanHist.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<FreeSpanHist> freeSpanHistList = freeSpanHistRepository.findAll();
        assertThat(freeSpanHistList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FreeSpanHist.class);
        FreeSpanHist freeSpanHist1 = new FreeSpanHist();
        freeSpanHist1.setId(1L);
        FreeSpanHist freeSpanHist2 = new FreeSpanHist();
        freeSpanHist2.setId(freeSpanHist1.getId());
        assertThat(freeSpanHist1).isEqualTo(freeSpanHist2);
        freeSpanHist2.setId(2L);
        assertThat(freeSpanHist1).isNotEqualTo(freeSpanHist2);
        freeSpanHist1.setId(null);
        assertThat(freeSpanHist1).isNotEqualTo(freeSpanHist2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FreeSpanHistDTO.class);
        FreeSpanHistDTO freeSpanHistDTO1 = new FreeSpanHistDTO();
        freeSpanHistDTO1.setId(1L);
        FreeSpanHistDTO freeSpanHistDTO2 = new FreeSpanHistDTO();
        assertThat(freeSpanHistDTO1).isNotEqualTo(freeSpanHistDTO2);
        freeSpanHistDTO2.setId(freeSpanHistDTO1.getId());
        assertThat(freeSpanHistDTO1).isEqualTo(freeSpanHistDTO2);
        freeSpanHistDTO2.setId(2L);
        assertThat(freeSpanHistDTO1).isNotEqualTo(freeSpanHistDTO2);
        freeSpanHistDTO1.setId(null);
        assertThat(freeSpanHistDTO1).isNotEqualTo(freeSpanHistDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(freeSpanHistMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(freeSpanHistMapper.fromId(null)).isNull();
    }
}
