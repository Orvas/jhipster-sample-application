package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.DisplacementHist;
import io.github.jhipster.application.domain.Displacement;
import io.github.jhipster.application.domain.PipelineSection;
import io.github.jhipster.application.repository.DisplacementHistRepository;
import io.github.jhipster.application.service.DisplacementHistService;
import io.github.jhipster.application.service.dto.DisplacementHistDTO;
import io.github.jhipster.application.service.mapper.DisplacementHistMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.DisplacementHistCriteria;
import io.github.jhipster.application.service.DisplacementHistQueryService;

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
 * Integration tests for the {@Link DisplacementHistResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class DisplacementHistResourceIT {

    private static final LocalDate DEFAULT_DATE_FROM = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_FROM = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATE_TO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_TO = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_ID_WRK = 1L;
    private static final Long UPDATED_ID_WRK = 2L;

    private static final BigDecimal DEFAULT_DELTA_X = new BigDecimal(1);
    private static final BigDecimal UPDATED_DELTA_X = new BigDecimal(2);

    private static final BigDecimal DEFAULT_DELTA_Y = new BigDecimal(1);
    private static final BigDecimal UPDATED_DELTA_Y = new BigDecimal(2);

    private static final BigDecimal DEFAULT_DELTA_Z = new BigDecimal(1);
    private static final BigDecimal UPDATED_DELTA_Z = new BigDecimal(2);

    private static final BigDecimal DEFAULT_DELTA_TOTAL = new BigDecimal(1);
    private static final BigDecimal UPDATED_DELTA_TOTAL = new BigDecimal(2);

    private static final BigDecimal DEFAULT_KP_START = new BigDecimal(1);
    private static final BigDecimal UPDATED_KP_START = new BigDecimal(2);

    private static final BigDecimal DEFAULT_KP_END = new BigDecimal(1);
    private static final BigDecimal UPDATED_KP_END = new BigDecimal(2);

    private static final Integer DEFAULT_IS_CURRENT_FLAG = 1;
    private static final Integer UPDATED_IS_CURRENT_FLAG = 2;

    private static final Long DEFAULT_ID_STATUS = 1L;
    private static final Long UPDATED_ID_STATUS = 2L;

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
    private DisplacementHistRepository displacementHistRepository;

    @Autowired
    private DisplacementHistMapper displacementHistMapper;

    @Autowired
    private DisplacementHistService displacementHistService;

    @Autowired
    private DisplacementHistQueryService displacementHistQueryService;

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

    private MockMvc restDisplacementHistMockMvc;

    private DisplacementHist displacementHist;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DisplacementHistResource displacementHistResource = new DisplacementHistResource(displacementHistService, displacementHistQueryService);
        this.restDisplacementHistMockMvc = MockMvcBuilders.standaloneSetup(displacementHistResource)
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
    public static DisplacementHist createEntity(EntityManager em) {
        DisplacementHist displacementHist = new DisplacementHist()
            .dateFrom(DEFAULT_DATE_FROM)
            .dateTo(DEFAULT_DATE_TO)
            .idWrk(DEFAULT_ID_WRK)
            .deltaX(DEFAULT_DELTA_X)
            .deltaY(DEFAULT_DELTA_Y)
            .deltaZ(DEFAULT_DELTA_Z)
            .deltaTotal(DEFAULT_DELTA_TOTAL)
            .kpStart(DEFAULT_KP_START)
            .kpEnd(DEFAULT_KP_END)
            .isCurrentFlag(DEFAULT_IS_CURRENT_FLAG)
            .idStatus(DEFAULT_ID_STATUS)
            .comment(DEFAULT_COMMENT)
            .dateCreate(DEFAULT_DATE_CREATE)
            .dateEdit(DEFAULT_DATE_EDIT)
            .creator(DEFAULT_CREATOR)
            .editor(DEFAULT_EDITOR);
        // Add required entity
        Displacement displacement;
        if (TestUtil.findAll(em, Displacement.class).isEmpty()) {
            displacement = DisplacementResourceIT.createEntity(em);
            em.persist(displacement);
            em.flush();
        } else {
            displacement = TestUtil.findAll(em, Displacement.class).get(0);
        }
        displacementHist.setId(displacement);
        // Add required entity
        PipelineSection pipelineSection;
        if (TestUtil.findAll(em, PipelineSection.class).isEmpty()) {
            pipelineSection = PipelineSectionResourceIT.createEntity(em);
            em.persist(pipelineSection);
            em.flush();
        } else {
            pipelineSection = TestUtil.findAll(em, PipelineSection.class).get(0);
        }
        displacementHist.setIdPipelineSection(pipelineSection);
        return displacementHist;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DisplacementHist createUpdatedEntity(EntityManager em) {
        DisplacementHist displacementHist = new DisplacementHist()
            .dateFrom(UPDATED_DATE_FROM)
            .dateTo(UPDATED_DATE_TO)
            .idWrk(UPDATED_ID_WRK)
            .deltaX(UPDATED_DELTA_X)
            .deltaY(UPDATED_DELTA_Y)
            .deltaZ(UPDATED_DELTA_Z)
            .deltaTotal(UPDATED_DELTA_TOTAL)
            .kpStart(UPDATED_KP_START)
            .kpEnd(UPDATED_KP_END)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .idStatus(UPDATED_ID_STATUS)
            .comment(UPDATED_COMMENT)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        // Add required entity
        Displacement displacement;
        if (TestUtil.findAll(em, Displacement.class).isEmpty()) {
            displacement = DisplacementResourceIT.createUpdatedEntity(em);
            em.persist(displacement);
            em.flush();
        } else {
            displacement = TestUtil.findAll(em, Displacement.class).get(0);
        }
        displacementHist.setId(displacement);
        // Add required entity
        PipelineSection pipelineSection;
        if (TestUtil.findAll(em, PipelineSection.class).isEmpty()) {
            pipelineSection = PipelineSectionResourceIT.createUpdatedEntity(em);
            em.persist(pipelineSection);
            em.flush();
        } else {
            pipelineSection = TestUtil.findAll(em, PipelineSection.class).get(0);
        }
        displacementHist.setIdPipelineSection(pipelineSection);
        return displacementHist;
    }

    @BeforeEach
    public void initTest() {
        displacementHist = createEntity(em);
    }

    @Test
    @Transactional
    public void createDisplacementHist() throws Exception {
        int databaseSizeBeforeCreate = displacementHistRepository.findAll().size();

        // Create the DisplacementHist
        DisplacementHistDTO displacementHistDTO = displacementHistMapper.toDto(displacementHist);
        restDisplacementHistMockMvc.perform(post("/api/displacement-hists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(displacementHistDTO)))
            .andExpect(status().isCreated());

        // Validate the DisplacementHist in the database
        List<DisplacementHist> displacementHistList = displacementHistRepository.findAll();
        assertThat(displacementHistList).hasSize(databaseSizeBeforeCreate + 1);
        DisplacementHist testDisplacementHist = displacementHistList.get(displacementHistList.size() - 1);
        assertThat(testDisplacementHist.getDateFrom()).isEqualTo(DEFAULT_DATE_FROM);
        assertThat(testDisplacementHist.getDateTo()).isEqualTo(DEFAULT_DATE_TO);
        assertThat(testDisplacementHist.getIdWrk()).isEqualTo(DEFAULT_ID_WRK);
        assertThat(testDisplacementHist.getDeltaX()).isEqualTo(DEFAULT_DELTA_X);
        assertThat(testDisplacementHist.getDeltaY()).isEqualTo(DEFAULT_DELTA_Y);
        assertThat(testDisplacementHist.getDeltaZ()).isEqualTo(DEFAULT_DELTA_Z);
        assertThat(testDisplacementHist.getDeltaTotal()).isEqualTo(DEFAULT_DELTA_TOTAL);
        assertThat(testDisplacementHist.getKpStart()).isEqualTo(DEFAULT_KP_START);
        assertThat(testDisplacementHist.getKpEnd()).isEqualTo(DEFAULT_KP_END);
        assertThat(testDisplacementHist.getIsCurrentFlag()).isEqualTo(DEFAULT_IS_CURRENT_FLAG);
        assertThat(testDisplacementHist.getIdStatus()).isEqualTo(DEFAULT_ID_STATUS);
        assertThat(testDisplacementHist.getComment()).isEqualTo(DEFAULT_COMMENT);
        assertThat(testDisplacementHist.getDateCreate()).isEqualTo(DEFAULT_DATE_CREATE);
        assertThat(testDisplacementHist.getDateEdit()).isEqualTo(DEFAULT_DATE_EDIT);
        assertThat(testDisplacementHist.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testDisplacementHist.getEditor()).isEqualTo(DEFAULT_EDITOR);
    }

    @Test
    @Transactional
    public void createDisplacementHistWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = displacementHistRepository.findAll().size();

        // Create the DisplacementHist with an existing ID
        displacementHist.setId(1L);
        DisplacementHistDTO displacementHistDTO = displacementHistMapper.toDto(displacementHist);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDisplacementHistMockMvc.perform(post("/api/displacement-hists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(displacementHistDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DisplacementHist in the database
        List<DisplacementHist> displacementHistList = displacementHistRepository.findAll();
        assertThat(displacementHistList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkDateFromIsRequired() throws Exception {
        int databaseSizeBeforeTest = displacementHistRepository.findAll().size();
        // set the field null
        displacementHist.setDateFrom(null);

        // Create the DisplacementHist, which fails.
        DisplacementHistDTO displacementHistDTO = displacementHistMapper.toDto(displacementHist);

        restDisplacementHistMockMvc.perform(post("/api/displacement-hists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(displacementHistDTO)))
            .andExpect(status().isBadRequest());

        List<DisplacementHist> displacementHistList = displacementHistRepository.findAll();
        assertThat(displacementHistList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDateToIsRequired() throws Exception {
        int databaseSizeBeforeTest = displacementHistRepository.findAll().size();
        // set the field null
        displacementHist.setDateTo(null);

        // Create the DisplacementHist, which fails.
        DisplacementHistDTO displacementHistDTO = displacementHistMapper.toDto(displacementHist);

        restDisplacementHistMockMvc.perform(post("/api/displacement-hists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(displacementHistDTO)))
            .andExpect(status().isBadRequest());

        List<DisplacementHist> displacementHistList = displacementHistRepository.findAll();
        assertThat(displacementHistList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsCurrentFlagIsRequired() throws Exception {
        int databaseSizeBeforeTest = displacementHistRepository.findAll().size();
        // set the field null
        displacementHist.setIsCurrentFlag(null);

        // Create the DisplacementHist, which fails.
        DisplacementHistDTO displacementHistDTO = displacementHistMapper.toDto(displacementHist);

        restDisplacementHistMockMvc.perform(post("/api/displacement-hists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(displacementHistDTO)))
            .andExpect(status().isBadRequest());

        List<DisplacementHist> displacementHistList = displacementHistRepository.findAll();
        assertThat(displacementHistList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIdStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = displacementHistRepository.findAll().size();
        // set the field null
        displacementHist.setIdStatus(null);

        // Create the DisplacementHist, which fails.
        DisplacementHistDTO displacementHistDTO = displacementHistMapper.toDto(displacementHist);

        restDisplacementHistMockMvc.perform(post("/api/displacement-hists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(displacementHistDTO)))
            .andExpect(status().isBadRequest());

        List<DisplacementHist> displacementHistList = displacementHistRepository.findAll();
        assertThat(displacementHistList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDisplacementHists() throws Exception {
        // Initialize the database
        displacementHistRepository.saveAndFlush(displacementHist);

        // Get all the displacementHistList
        restDisplacementHistMockMvc.perform(get("/api/displacement-hists?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(displacementHist.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateFrom").value(hasItem(DEFAULT_DATE_FROM.toString())))
            .andExpect(jsonPath("$.[*].dateTo").value(hasItem(DEFAULT_DATE_TO.toString())))
            .andExpect(jsonPath("$.[*].idWrk").value(hasItem(DEFAULT_ID_WRK.intValue())))
            .andExpect(jsonPath("$.[*].deltaX").value(hasItem(DEFAULT_DELTA_X.intValue())))
            .andExpect(jsonPath("$.[*].deltaY").value(hasItem(DEFAULT_DELTA_Y.intValue())))
            .andExpect(jsonPath("$.[*].deltaZ").value(hasItem(DEFAULT_DELTA_Z.intValue())))
            .andExpect(jsonPath("$.[*].deltaTotal").value(hasItem(DEFAULT_DELTA_TOTAL.intValue())))
            .andExpect(jsonPath("$.[*].kpStart").value(hasItem(DEFAULT_KP_START.intValue())))
            .andExpect(jsonPath("$.[*].kpEnd").value(hasItem(DEFAULT_KP_END.intValue())))
            .andExpect(jsonPath("$.[*].isCurrentFlag").value(hasItem(DEFAULT_IS_CURRENT_FLAG)))
            .andExpect(jsonPath("$.[*].idStatus").value(hasItem(DEFAULT_ID_STATUS.intValue())))
            .andExpect(jsonPath("$.[*].comment").value(hasItem(DEFAULT_COMMENT.toString())))
            .andExpect(jsonPath("$.[*].dateCreate").value(hasItem(DEFAULT_DATE_CREATE.toString())))
            .andExpect(jsonPath("$.[*].dateEdit").value(hasItem(DEFAULT_DATE_EDIT.toString())))
            .andExpect(jsonPath("$.[*].creator").value(hasItem(DEFAULT_CREATOR.toString())))
            .andExpect(jsonPath("$.[*].editor").value(hasItem(DEFAULT_EDITOR.toString())));
    }
    
    @Test
    @Transactional
    public void getDisplacementHist() throws Exception {
        // Initialize the database
        displacementHistRepository.saveAndFlush(displacementHist);

        // Get the displacementHist
        restDisplacementHistMockMvc.perform(get("/api/displacement-hists/{id}", displacementHist.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(displacementHist.getId().intValue()))
            .andExpect(jsonPath("$.dateFrom").value(DEFAULT_DATE_FROM.toString()))
            .andExpect(jsonPath("$.dateTo").value(DEFAULT_DATE_TO.toString()))
            .andExpect(jsonPath("$.idWrk").value(DEFAULT_ID_WRK.intValue()))
            .andExpect(jsonPath("$.deltaX").value(DEFAULT_DELTA_X.intValue()))
            .andExpect(jsonPath("$.deltaY").value(DEFAULT_DELTA_Y.intValue()))
            .andExpect(jsonPath("$.deltaZ").value(DEFAULT_DELTA_Z.intValue()))
            .andExpect(jsonPath("$.deltaTotal").value(DEFAULT_DELTA_TOTAL.intValue()))
            .andExpect(jsonPath("$.kpStart").value(DEFAULT_KP_START.intValue()))
            .andExpect(jsonPath("$.kpEnd").value(DEFAULT_KP_END.intValue()))
            .andExpect(jsonPath("$.isCurrentFlag").value(DEFAULT_IS_CURRENT_FLAG))
            .andExpect(jsonPath("$.idStatus").value(DEFAULT_ID_STATUS.intValue()))
            .andExpect(jsonPath("$.comment").value(DEFAULT_COMMENT.toString()))
            .andExpect(jsonPath("$.dateCreate").value(DEFAULT_DATE_CREATE.toString()))
            .andExpect(jsonPath("$.dateEdit").value(DEFAULT_DATE_EDIT.toString()))
            .andExpect(jsonPath("$.creator").value(DEFAULT_CREATOR.toString()))
            .andExpect(jsonPath("$.editor").value(DEFAULT_EDITOR.toString()));
    }

    @Test
    @Transactional
    public void getAllDisplacementHistsByDateFromIsEqualToSomething() throws Exception {
        // Initialize the database
        displacementHistRepository.saveAndFlush(displacementHist);

        // Get all the displacementHistList where dateFrom equals to DEFAULT_DATE_FROM
        defaultDisplacementHistShouldBeFound("dateFrom.equals=" + DEFAULT_DATE_FROM);

        // Get all the displacementHistList where dateFrom equals to UPDATED_DATE_FROM
        defaultDisplacementHistShouldNotBeFound("dateFrom.equals=" + UPDATED_DATE_FROM);
    }

    @Test
    @Transactional
    public void getAllDisplacementHistsByDateFromIsInShouldWork() throws Exception {
        // Initialize the database
        displacementHistRepository.saveAndFlush(displacementHist);

        // Get all the displacementHistList where dateFrom in DEFAULT_DATE_FROM or UPDATED_DATE_FROM
        defaultDisplacementHistShouldBeFound("dateFrom.in=" + DEFAULT_DATE_FROM + "," + UPDATED_DATE_FROM);

        // Get all the displacementHistList where dateFrom equals to UPDATED_DATE_FROM
        defaultDisplacementHistShouldNotBeFound("dateFrom.in=" + UPDATED_DATE_FROM);
    }

    @Test
    @Transactional
    public void getAllDisplacementHistsByDateFromIsNullOrNotNull() throws Exception {
        // Initialize the database
        displacementHistRepository.saveAndFlush(displacementHist);

        // Get all the displacementHistList where dateFrom is not null
        defaultDisplacementHistShouldBeFound("dateFrom.specified=true");

        // Get all the displacementHistList where dateFrom is null
        defaultDisplacementHistShouldNotBeFound("dateFrom.specified=false");
    }

    @Test
    @Transactional
    public void getAllDisplacementHistsByDateFromIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        displacementHistRepository.saveAndFlush(displacementHist);

        // Get all the displacementHistList where dateFrom greater than or equals to DEFAULT_DATE_FROM
        defaultDisplacementHistShouldBeFound("dateFrom.greaterOrEqualThan=" + DEFAULT_DATE_FROM);

        // Get all the displacementHistList where dateFrom greater than or equals to UPDATED_DATE_FROM
        defaultDisplacementHistShouldNotBeFound("dateFrom.greaterOrEqualThan=" + UPDATED_DATE_FROM);
    }

    @Test
    @Transactional
    public void getAllDisplacementHistsByDateFromIsLessThanSomething() throws Exception {
        // Initialize the database
        displacementHistRepository.saveAndFlush(displacementHist);

        // Get all the displacementHistList where dateFrom less than or equals to DEFAULT_DATE_FROM
        defaultDisplacementHistShouldNotBeFound("dateFrom.lessThan=" + DEFAULT_DATE_FROM);

        // Get all the displacementHistList where dateFrom less than or equals to UPDATED_DATE_FROM
        defaultDisplacementHistShouldBeFound("dateFrom.lessThan=" + UPDATED_DATE_FROM);
    }


    @Test
    @Transactional
    public void getAllDisplacementHistsByDateToIsEqualToSomething() throws Exception {
        // Initialize the database
        displacementHistRepository.saveAndFlush(displacementHist);

        // Get all the displacementHistList where dateTo equals to DEFAULT_DATE_TO
        defaultDisplacementHistShouldBeFound("dateTo.equals=" + DEFAULT_DATE_TO);

        // Get all the displacementHistList where dateTo equals to UPDATED_DATE_TO
        defaultDisplacementHistShouldNotBeFound("dateTo.equals=" + UPDATED_DATE_TO);
    }

    @Test
    @Transactional
    public void getAllDisplacementHistsByDateToIsInShouldWork() throws Exception {
        // Initialize the database
        displacementHistRepository.saveAndFlush(displacementHist);

        // Get all the displacementHistList where dateTo in DEFAULT_DATE_TO or UPDATED_DATE_TO
        defaultDisplacementHistShouldBeFound("dateTo.in=" + DEFAULT_DATE_TO + "," + UPDATED_DATE_TO);

        // Get all the displacementHistList where dateTo equals to UPDATED_DATE_TO
        defaultDisplacementHistShouldNotBeFound("dateTo.in=" + UPDATED_DATE_TO);
    }

    @Test
    @Transactional
    public void getAllDisplacementHistsByDateToIsNullOrNotNull() throws Exception {
        // Initialize the database
        displacementHistRepository.saveAndFlush(displacementHist);

        // Get all the displacementHistList where dateTo is not null
        defaultDisplacementHistShouldBeFound("dateTo.specified=true");

        // Get all the displacementHistList where dateTo is null
        defaultDisplacementHistShouldNotBeFound("dateTo.specified=false");
    }

    @Test
    @Transactional
    public void getAllDisplacementHistsByDateToIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        displacementHistRepository.saveAndFlush(displacementHist);

        // Get all the displacementHistList where dateTo greater than or equals to DEFAULT_DATE_TO
        defaultDisplacementHistShouldBeFound("dateTo.greaterOrEqualThan=" + DEFAULT_DATE_TO);

        // Get all the displacementHistList where dateTo greater than or equals to UPDATED_DATE_TO
        defaultDisplacementHistShouldNotBeFound("dateTo.greaterOrEqualThan=" + UPDATED_DATE_TO);
    }

    @Test
    @Transactional
    public void getAllDisplacementHistsByDateToIsLessThanSomething() throws Exception {
        // Initialize the database
        displacementHistRepository.saveAndFlush(displacementHist);

        // Get all the displacementHistList where dateTo less than or equals to DEFAULT_DATE_TO
        defaultDisplacementHistShouldNotBeFound("dateTo.lessThan=" + DEFAULT_DATE_TO);

        // Get all the displacementHistList where dateTo less than or equals to UPDATED_DATE_TO
        defaultDisplacementHistShouldBeFound("dateTo.lessThan=" + UPDATED_DATE_TO);
    }


    @Test
    @Transactional
    public void getAllDisplacementHistsByIdWrkIsEqualToSomething() throws Exception {
        // Initialize the database
        displacementHistRepository.saveAndFlush(displacementHist);

        // Get all the displacementHistList where idWrk equals to DEFAULT_ID_WRK
        defaultDisplacementHistShouldBeFound("idWrk.equals=" + DEFAULT_ID_WRK);

        // Get all the displacementHistList where idWrk equals to UPDATED_ID_WRK
        defaultDisplacementHistShouldNotBeFound("idWrk.equals=" + UPDATED_ID_WRK);
    }

    @Test
    @Transactional
    public void getAllDisplacementHistsByIdWrkIsInShouldWork() throws Exception {
        // Initialize the database
        displacementHistRepository.saveAndFlush(displacementHist);

        // Get all the displacementHistList where idWrk in DEFAULT_ID_WRK or UPDATED_ID_WRK
        defaultDisplacementHistShouldBeFound("idWrk.in=" + DEFAULT_ID_WRK + "," + UPDATED_ID_WRK);

        // Get all the displacementHistList where idWrk equals to UPDATED_ID_WRK
        defaultDisplacementHistShouldNotBeFound("idWrk.in=" + UPDATED_ID_WRK);
    }

    @Test
    @Transactional
    public void getAllDisplacementHistsByIdWrkIsNullOrNotNull() throws Exception {
        // Initialize the database
        displacementHistRepository.saveAndFlush(displacementHist);

        // Get all the displacementHistList where idWrk is not null
        defaultDisplacementHistShouldBeFound("idWrk.specified=true");

        // Get all the displacementHistList where idWrk is null
        defaultDisplacementHistShouldNotBeFound("idWrk.specified=false");
    }

    @Test
    @Transactional
    public void getAllDisplacementHistsByIdWrkIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        displacementHistRepository.saveAndFlush(displacementHist);

        // Get all the displacementHistList where idWrk greater than or equals to DEFAULT_ID_WRK
        defaultDisplacementHistShouldBeFound("idWrk.greaterOrEqualThan=" + DEFAULT_ID_WRK);

        // Get all the displacementHistList where idWrk greater than or equals to UPDATED_ID_WRK
        defaultDisplacementHistShouldNotBeFound("idWrk.greaterOrEqualThan=" + UPDATED_ID_WRK);
    }

    @Test
    @Transactional
    public void getAllDisplacementHistsByIdWrkIsLessThanSomething() throws Exception {
        // Initialize the database
        displacementHistRepository.saveAndFlush(displacementHist);

        // Get all the displacementHistList where idWrk less than or equals to DEFAULT_ID_WRK
        defaultDisplacementHistShouldNotBeFound("idWrk.lessThan=" + DEFAULT_ID_WRK);

        // Get all the displacementHistList where idWrk less than or equals to UPDATED_ID_WRK
        defaultDisplacementHistShouldBeFound("idWrk.lessThan=" + UPDATED_ID_WRK);
    }


    @Test
    @Transactional
    public void getAllDisplacementHistsByDeltaXIsEqualToSomething() throws Exception {
        // Initialize the database
        displacementHistRepository.saveAndFlush(displacementHist);

        // Get all the displacementHistList where deltaX equals to DEFAULT_DELTA_X
        defaultDisplacementHistShouldBeFound("deltaX.equals=" + DEFAULT_DELTA_X);

        // Get all the displacementHistList where deltaX equals to UPDATED_DELTA_X
        defaultDisplacementHistShouldNotBeFound("deltaX.equals=" + UPDATED_DELTA_X);
    }

    @Test
    @Transactional
    public void getAllDisplacementHistsByDeltaXIsInShouldWork() throws Exception {
        // Initialize the database
        displacementHistRepository.saveAndFlush(displacementHist);

        // Get all the displacementHistList where deltaX in DEFAULT_DELTA_X or UPDATED_DELTA_X
        defaultDisplacementHistShouldBeFound("deltaX.in=" + DEFAULT_DELTA_X + "," + UPDATED_DELTA_X);

        // Get all the displacementHistList where deltaX equals to UPDATED_DELTA_X
        defaultDisplacementHistShouldNotBeFound("deltaX.in=" + UPDATED_DELTA_X);
    }

    @Test
    @Transactional
    public void getAllDisplacementHistsByDeltaXIsNullOrNotNull() throws Exception {
        // Initialize the database
        displacementHistRepository.saveAndFlush(displacementHist);

        // Get all the displacementHistList where deltaX is not null
        defaultDisplacementHistShouldBeFound("deltaX.specified=true");

        // Get all the displacementHistList where deltaX is null
        defaultDisplacementHistShouldNotBeFound("deltaX.specified=false");
    }

    @Test
    @Transactional
    public void getAllDisplacementHistsByDeltaYIsEqualToSomething() throws Exception {
        // Initialize the database
        displacementHistRepository.saveAndFlush(displacementHist);

        // Get all the displacementHistList where deltaY equals to DEFAULT_DELTA_Y
        defaultDisplacementHistShouldBeFound("deltaY.equals=" + DEFAULT_DELTA_Y);

        // Get all the displacementHistList where deltaY equals to UPDATED_DELTA_Y
        defaultDisplacementHistShouldNotBeFound("deltaY.equals=" + UPDATED_DELTA_Y);
    }

    @Test
    @Transactional
    public void getAllDisplacementHistsByDeltaYIsInShouldWork() throws Exception {
        // Initialize the database
        displacementHistRepository.saveAndFlush(displacementHist);

        // Get all the displacementHistList where deltaY in DEFAULT_DELTA_Y or UPDATED_DELTA_Y
        defaultDisplacementHistShouldBeFound("deltaY.in=" + DEFAULT_DELTA_Y + "," + UPDATED_DELTA_Y);

        // Get all the displacementHistList where deltaY equals to UPDATED_DELTA_Y
        defaultDisplacementHistShouldNotBeFound("deltaY.in=" + UPDATED_DELTA_Y);
    }

    @Test
    @Transactional
    public void getAllDisplacementHistsByDeltaYIsNullOrNotNull() throws Exception {
        // Initialize the database
        displacementHistRepository.saveAndFlush(displacementHist);

        // Get all the displacementHistList where deltaY is not null
        defaultDisplacementHistShouldBeFound("deltaY.specified=true");

        // Get all the displacementHistList where deltaY is null
        defaultDisplacementHistShouldNotBeFound("deltaY.specified=false");
    }

    @Test
    @Transactional
    public void getAllDisplacementHistsByDeltaZIsEqualToSomething() throws Exception {
        // Initialize the database
        displacementHistRepository.saveAndFlush(displacementHist);

        // Get all the displacementHistList where deltaZ equals to DEFAULT_DELTA_Z
        defaultDisplacementHistShouldBeFound("deltaZ.equals=" + DEFAULT_DELTA_Z);

        // Get all the displacementHistList where deltaZ equals to UPDATED_DELTA_Z
        defaultDisplacementHistShouldNotBeFound("deltaZ.equals=" + UPDATED_DELTA_Z);
    }

    @Test
    @Transactional
    public void getAllDisplacementHistsByDeltaZIsInShouldWork() throws Exception {
        // Initialize the database
        displacementHistRepository.saveAndFlush(displacementHist);

        // Get all the displacementHistList where deltaZ in DEFAULT_DELTA_Z or UPDATED_DELTA_Z
        defaultDisplacementHistShouldBeFound("deltaZ.in=" + DEFAULT_DELTA_Z + "," + UPDATED_DELTA_Z);

        // Get all the displacementHistList where deltaZ equals to UPDATED_DELTA_Z
        defaultDisplacementHistShouldNotBeFound("deltaZ.in=" + UPDATED_DELTA_Z);
    }

    @Test
    @Transactional
    public void getAllDisplacementHistsByDeltaZIsNullOrNotNull() throws Exception {
        // Initialize the database
        displacementHistRepository.saveAndFlush(displacementHist);

        // Get all the displacementHistList where deltaZ is not null
        defaultDisplacementHistShouldBeFound("deltaZ.specified=true");

        // Get all the displacementHistList where deltaZ is null
        defaultDisplacementHistShouldNotBeFound("deltaZ.specified=false");
    }

    @Test
    @Transactional
    public void getAllDisplacementHistsByDeltaTotalIsEqualToSomething() throws Exception {
        // Initialize the database
        displacementHistRepository.saveAndFlush(displacementHist);

        // Get all the displacementHistList where deltaTotal equals to DEFAULT_DELTA_TOTAL
        defaultDisplacementHistShouldBeFound("deltaTotal.equals=" + DEFAULT_DELTA_TOTAL);

        // Get all the displacementHistList where deltaTotal equals to UPDATED_DELTA_TOTAL
        defaultDisplacementHistShouldNotBeFound("deltaTotal.equals=" + UPDATED_DELTA_TOTAL);
    }

    @Test
    @Transactional
    public void getAllDisplacementHistsByDeltaTotalIsInShouldWork() throws Exception {
        // Initialize the database
        displacementHistRepository.saveAndFlush(displacementHist);

        // Get all the displacementHistList where deltaTotal in DEFAULT_DELTA_TOTAL or UPDATED_DELTA_TOTAL
        defaultDisplacementHistShouldBeFound("deltaTotal.in=" + DEFAULT_DELTA_TOTAL + "," + UPDATED_DELTA_TOTAL);

        // Get all the displacementHistList where deltaTotal equals to UPDATED_DELTA_TOTAL
        defaultDisplacementHistShouldNotBeFound("deltaTotal.in=" + UPDATED_DELTA_TOTAL);
    }

    @Test
    @Transactional
    public void getAllDisplacementHistsByDeltaTotalIsNullOrNotNull() throws Exception {
        // Initialize the database
        displacementHistRepository.saveAndFlush(displacementHist);

        // Get all the displacementHistList where deltaTotal is not null
        defaultDisplacementHistShouldBeFound("deltaTotal.specified=true");

        // Get all the displacementHistList where deltaTotal is null
        defaultDisplacementHistShouldNotBeFound("deltaTotal.specified=false");
    }

    @Test
    @Transactional
    public void getAllDisplacementHistsByKpStartIsEqualToSomething() throws Exception {
        // Initialize the database
        displacementHistRepository.saveAndFlush(displacementHist);

        // Get all the displacementHistList where kpStart equals to DEFAULT_KP_START
        defaultDisplacementHistShouldBeFound("kpStart.equals=" + DEFAULT_KP_START);

        // Get all the displacementHistList where kpStart equals to UPDATED_KP_START
        defaultDisplacementHistShouldNotBeFound("kpStart.equals=" + UPDATED_KP_START);
    }

    @Test
    @Transactional
    public void getAllDisplacementHistsByKpStartIsInShouldWork() throws Exception {
        // Initialize the database
        displacementHistRepository.saveAndFlush(displacementHist);

        // Get all the displacementHistList where kpStart in DEFAULT_KP_START or UPDATED_KP_START
        defaultDisplacementHistShouldBeFound("kpStart.in=" + DEFAULT_KP_START + "," + UPDATED_KP_START);

        // Get all the displacementHistList where kpStart equals to UPDATED_KP_START
        defaultDisplacementHistShouldNotBeFound("kpStart.in=" + UPDATED_KP_START);
    }

    @Test
    @Transactional
    public void getAllDisplacementHistsByKpStartIsNullOrNotNull() throws Exception {
        // Initialize the database
        displacementHistRepository.saveAndFlush(displacementHist);

        // Get all the displacementHistList where kpStart is not null
        defaultDisplacementHistShouldBeFound("kpStart.specified=true");

        // Get all the displacementHistList where kpStart is null
        defaultDisplacementHistShouldNotBeFound("kpStart.specified=false");
    }

    @Test
    @Transactional
    public void getAllDisplacementHistsByKpEndIsEqualToSomething() throws Exception {
        // Initialize the database
        displacementHistRepository.saveAndFlush(displacementHist);

        // Get all the displacementHistList where kpEnd equals to DEFAULT_KP_END
        defaultDisplacementHistShouldBeFound("kpEnd.equals=" + DEFAULT_KP_END);

        // Get all the displacementHistList where kpEnd equals to UPDATED_KP_END
        defaultDisplacementHistShouldNotBeFound("kpEnd.equals=" + UPDATED_KP_END);
    }

    @Test
    @Transactional
    public void getAllDisplacementHistsByKpEndIsInShouldWork() throws Exception {
        // Initialize the database
        displacementHistRepository.saveAndFlush(displacementHist);

        // Get all the displacementHistList where kpEnd in DEFAULT_KP_END or UPDATED_KP_END
        defaultDisplacementHistShouldBeFound("kpEnd.in=" + DEFAULT_KP_END + "," + UPDATED_KP_END);

        // Get all the displacementHistList where kpEnd equals to UPDATED_KP_END
        defaultDisplacementHistShouldNotBeFound("kpEnd.in=" + UPDATED_KP_END);
    }

    @Test
    @Transactional
    public void getAllDisplacementHistsByKpEndIsNullOrNotNull() throws Exception {
        // Initialize the database
        displacementHistRepository.saveAndFlush(displacementHist);

        // Get all the displacementHistList where kpEnd is not null
        defaultDisplacementHistShouldBeFound("kpEnd.specified=true");

        // Get all the displacementHistList where kpEnd is null
        defaultDisplacementHistShouldNotBeFound("kpEnd.specified=false");
    }

    @Test
    @Transactional
    public void getAllDisplacementHistsByIsCurrentFlagIsEqualToSomething() throws Exception {
        // Initialize the database
        displacementHistRepository.saveAndFlush(displacementHist);

        // Get all the displacementHistList where isCurrentFlag equals to DEFAULT_IS_CURRENT_FLAG
        defaultDisplacementHistShouldBeFound("isCurrentFlag.equals=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the displacementHistList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultDisplacementHistShouldNotBeFound("isCurrentFlag.equals=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllDisplacementHistsByIsCurrentFlagIsInShouldWork() throws Exception {
        // Initialize the database
        displacementHistRepository.saveAndFlush(displacementHist);

        // Get all the displacementHistList where isCurrentFlag in DEFAULT_IS_CURRENT_FLAG or UPDATED_IS_CURRENT_FLAG
        defaultDisplacementHistShouldBeFound("isCurrentFlag.in=" + DEFAULT_IS_CURRENT_FLAG + "," + UPDATED_IS_CURRENT_FLAG);

        // Get all the displacementHistList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultDisplacementHistShouldNotBeFound("isCurrentFlag.in=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllDisplacementHistsByIsCurrentFlagIsNullOrNotNull() throws Exception {
        // Initialize the database
        displacementHistRepository.saveAndFlush(displacementHist);

        // Get all the displacementHistList where isCurrentFlag is not null
        defaultDisplacementHistShouldBeFound("isCurrentFlag.specified=true");

        // Get all the displacementHistList where isCurrentFlag is null
        defaultDisplacementHistShouldNotBeFound("isCurrentFlag.specified=false");
    }

    @Test
    @Transactional
    public void getAllDisplacementHistsByIsCurrentFlagIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        displacementHistRepository.saveAndFlush(displacementHist);

        // Get all the displacementHistList where isCurrentFlag greater than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultDisplacementHistShouldBeFound("isCurrentFlag.greaterOrEqualThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the displacementHistList where isCurrentFlag greater than or equals to UPDATED_IS_CURRENT_FLAG
        defaultDisplacementHistShouldNotBeFound("isCurrentFlag.greaterOrEqualThan=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllDisplacementHistsByIsCurrentFlagIsLessThanSomething() throws Exception {
        // Initialize the database
        displacementHistRepository.saveAndFlush(displacementHist);

        // Get all the displacementHistList where isCurrentFlag less than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultDisplacementHistShouldNotBeFound("isCurrentFlag.lessThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the displacementHistList where isCurrentFlag less than or equals to UPDATED_IS_CURRENT_FLAG
        defaultDisplacementHistShouldBeFound("isCurrentFlag.lessThan=" + UPDATED_IS_CURRENT_FLAG);
    }


    @Test
    @Transactional
    public void getAllDisplacementHistsByIdStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        displacementHistRepository.saveAndFlush(displacementHist);

        // Get all the displacementHistList where idStatus equals to DEFAULT_ID_STATUS
        defaultDisplacementHistShouldBeFound("idStatus.equals=" + DEFAULT_ID_STATUS);

        // Get all the displacementHistList where idStatus equals to UPDATED_ID_STATUS
        defaultDisplacementHistShouldNotBeFound("idStatus.equals=" + UPDATED_ID_STATUS);
    }

    @Test
    @Transactional
    public void getAllDisplacementHistsByIdStatusIsInShouldWork() throws Exception {
        // Initialize the database
        displacementHistRepository.saveAndFlush(displacementHist);

        // Get all the displacementHistList where idStatus in DEFAULT_ID_STATUS or UPDATED_ID_STATUS
        defaultDisplacementHistShouldBeFound("idStatus.in=" + DEFAULT_ID_STATUS + "," + UPDATED_ID_STATUS);

        // Get all the displacementHistList where idStatus equals to UPDATED_ID_STATUS
        defaultDisplacementHistShouldNotBeFound("idStatus.in=" + UPDATED_ID_STATUS);
    }

    @Test
    @Transactional
    public void getAllDisplacementHistsByIdStatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        displacementHistRepository.saveAndFlush(displacementHist);

        // Get all the displacementHistList where idStatus is not null
        defaultDisplacementHistShouldBeFound("idStatus.specified=true");

        // Get all the displacementHistList where idStatus is null
        defaultDisplacementHistShouldNotBeFound("idStatus.specified=false");
    }

    @Test
    @Transactional
    public void getAllDisplacementHistsByIdStatusIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        displacementHistRepository.saveAndFlush(displacementHist);

        // Get all the displacementHistList where idStatus greater than or equals to DEFAULT_ID_STATUS
        defaultDisplacementHistShouldBeFound("idStatus.greaterOrEqualThan=" + DEFAULT_ID_STATUS);

        // Get all the displacementHistList where idStatus greater than or equals to UPDATED_ID_STATUS
        defaultDisplacementHistShouldNotBeFound("idStatus.greaterOrEqualThan=" + UPDATED_ID_STATUS);
    }

    @Test
    @Transactional
    public void getAllDisplacementHistsByIdStatusIsLessThanSomething() throws Exception {
        // Initialize the database
        displacementHistRepository.saveAndFlush(displacementHist);

        // Get all the displacementHistList where idStatus less than or equals to DEFAULT_ID_STATUS
        defaultDisplacementHistShouldNotBeFound("idStatus.lessThan=" + DEFAULT_ID_STATUS);

        // Get all the displacementHistList where idStatus less than or equals to UPDATED_ID_STATUS
        defaultDisplacementHistShouldBeFound("idStatus.lessThan=" + UPDATED_ID_STATUS);
    }


    @Test
    @Transactional
    public void getAllDisplacementHistsByCommentIsEqualToSomething() throws Exception {
        // Initialize the database
        displacementHistRepository.saveAndFlush(displacementHist);

        // Get all the displacementHistList where comment equals to DEFAULT_COMMENT
        defaultDisplacementHistShouldBeFound("comment.equals=" + DEFAULT_COMMENT);

        // Get all the displacementHistList where comment equals to UPDATED_COMMENT
        defaultDisplacementHistShouldNotBeFound("comment.equals=" + UPDATED_COMMENT);
    }

    @Test
    @Transactional
    public void getAllDisplacementHistsByCommentIsInShouldWork() throws Exception {
        // Initialize the database
        displacementHistRepository.saveAndFlush(displacementHist);

        // Get all the displacementHistList where comment in DEFAULT_COMMENT or UPDATED_COMMENT
        defaultDisplacementHistShouldBeFound("comment.in=" + DEFAULT_COMMENT + "," + UPDATED_COMMENT);

        // Get all the displacementHistList where comment equals to UPDATED_COMMENT
        defaultDisplacementHistShouldNotBeFound("comment.in=" + UPDATED_COMMENT);
    }

    @Test
    @Transactional
    public void getAllDisplacementHistsByCommentIsNullOrNotNull() throws Exception {
        // Initialize the database
        displacementHistRepository.saveAndFlush(displacementHist);

        // Get all the displacementHistList where comment is not null
        defaultDisplacementHistShouldBeFound("comment.specified=true");

        // Get all the displacementHistList where comment is null
        defaultDisplacementHistShouldNotBeFound("comment.specified=false");
    }

    @Test
    @Transactional
    public void getAllDisplacementHistsByDateCreateIsEqualToSomething() throws Exception {
        // Initialize the database
        displacementHistRepository.saveAndFlush(displacementHist);

        // Get all the displacementHistList where dateCreate equals to DEFAULT_DATE_CREATE
        defaultDisplacementHistShouldBeFound("dateCreate.equals=" + DEFAULT_DATE_CREATE);

        // Get all the displacementHistList where dateCreate equals to UPDATED_DATE_CREATE
        defaultDisplacementHistShouldNotBeFound("dateCreate.equals=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllDisplacementHistsByDateCreateIsInShouldWork() throws Exception {
        // Initialize the database
        displacementHistRepository.saveAndFlush(displacementHist);

        // Get all the displacementHistList where dateCreate in DEFAULT_DATE_CREATE or UPDATED_DATE_CREATE
        defaultDisplacementHistShouldBeFound("dateCreate.in=" + DEFAULT_DATE_CREATE + "," + UPDATED_DATE_CREATE);

        // Get all the displacementHistList where dateCreate equals to UPDATED_DATE_CREATE
        defaultDisplacementHistShouldNotBeFound("dateCreate.in=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllDisplacementHistsByDateCreateIsNullOrNotNull() throws Exception {
        // Initialize the database
        displacementHistRepository.saveAndFlush(displacementHist);

        // Get all the displacementHistList where dateCreate is not null
        defaultDisplacementHistShouldBeFound("dateCreate.specified=true");

        // Get all the displacementHistList where dateCreate is null
        defaultDisplacementHistShouldNotBeFound("dateCreate.specified=false");
    }

    @Test
    @Transactional
    public void getAllDisplacementHistsByDateEditIsEqualToSomething() throws Exception {
        // Initialize the database
        displacementHistRepository.saveAndFlush(displacementHist);

        // Get all the displacementHistList where dateEdit equals to DEFAULT_DATE_EDIT
        defaultDisplacementHistShouldBeFound("dateEdit.equals=" + DEFAULT_DATE_EDIT);

        // Get all the displacementHistList where dateEdit equals to UPDATED_DATE_EDIT
        defaultDisplacementHistShouldNotBeFound("dateEdit.equals=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllDisplacementHistsByDateEditIsInShouldWork() throws Exception {
        // Initialize the database
        displacementHistRepository.saveAndFlush(displacementHist);

        // Get all the displacementHistList where dateEdit in DEFAULT_DATE_EDIT or UPDATED_DATE_EDIT
        defaultDisplacementHistShouldBeFound("dateEdit.in=" + DEFAULT_DATE_EDIT + "," + UPDATED_DATE_EDIT);

        // Get all the displacementHistList where dateEdit equals to UPDATED_DATE_EDIT
        defaultDisplacementHistShouldNotBeFound("dateEdit.in=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllDisplacementHistsByDateEditIsNullOrNotNull() throws Exception {
        // Initialize the database
        displacementHistRepository.saveAndFlush(displacementHist);

        // Get all the displacementHistList where dateEdit is not null
        defaultDisplacementHistShouldBeFound("dateEdit.specified=true");

        // Get all the displacementHistList where dateEdit is null
        defaultDisplacementHistShouldNotBeFound("dateEdit.specified=false");
    }

    @Test
    @Transactional
    public void getAllDisplacementHistsByCreatorIsEqualToSomething() throws Exception {
        // Initialize the database
        displacementHistRepository.saveAndFlush(displacementHist);

        // Get all the displacementHistList where creator equals to DEFAULT_CREATOR
        defaultDisplacementHistShouldBeFound("creator.equals=" + DEFAULT_CREATOR);

        // Get all the displacementHistList where creator equals to UPDATED_CREATOR
        defaultDisplacementHistShouldNotBeFound("creator.equals=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllDisplacementHistsByCreatorIsInShouldWork() throws Exception {
        // Initialize the database
        displacementHistRepository.saveAndFlush(displacementHist);

        // Get all the displacementHistList where creator in DEFAULT_CREATOR or UPDATED_CREATOR
        defaultDisplacementHistShouldBeFound("creator.in=" + DEFAULT_CREATOR + "," + UPDATED_CREATOR);

        // Get all the displacementHistList where creator equals to UPDATED_CREATOR
        defaultDisplacementHistShouldNotBeFound("creator.in=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllDisplacementHistsByCreatorIsNullOrNotNull() throws Exception {
        // Initialize the database
        displacementHistRepository.saveAndFlush(displacementHist);

        // Get all the displacementHistList where creator is not null
        defaultDisplacementHistShouldBeFound("creator.specified=true");

        // Get all the displacementHistList where creator is null
        defaultDisplacementHistShouldNotBeFound("creator.specified=false");
    }

    @Test
    @Transactional
    public void getAllDisplacementHistsByEditorIsEqualToSomething() throws Exception {
        // Initialize the database
        displacementHistRepository.saveAndFlush(displacementHist);

        // Get all the displacementHistList where editor equals to DEFAULT_EDITOR
        defaultDisplacementHistShouldBeFound("editor.equals=" + DEFAULT_EDITOR);

        // Get all the displacementHistList where editor equals to UPDATED_EDITOR
        defaultDisplacementHistShouldNotBeFound("editor.equals=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllDisplacementHistsByEditorIsInShouldWork() throws Exception {
        // Initialize the database
        displacementHistRepository.saveAndFlush(displacementHist);

        // Get all the displacementHistList where editor in DEFAULT_EDITOR or UPDATED_EDITOR
        defaultDisplacementHistShouldBeFound("editor.in=" + DEFAULT_EDITOR + "," + UPDATED_EDITOR);

        // Get all the displacementHistList where editor equals to UPDATED_EDITOR
        defaultDisplacementHistShouldNotBeFound("editor.in=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllDisplacementHistsByEditorIsNullOrNotNull() throws Exception {
        // Initialize the database
        displacementHistRepository.saveAndFlush(displacementHist);

        // Get all the displacementHistList where editor is not null
        defaultDisplacementHistShouldBeFound("editor.specified=true");

        // Get all the displacementHistList where editor is null
        defaultDisplacementHistShouldNotBeFound("editor.specified=false");
    }

    @Test
    @Transactional
    public void getAllDisplacementHistsByIdIsEqualToSomething() throws Exception {
        // Get already existing entity
        Displacement id = displacementHist.getId();
        displacementHistRepository.saveAndFlush(displacementHist);
        Long idId = id.getId();

        // Get all the displacementHistList where id equals to idId
        defaultDisplacementHistShouldBeFound("idId.equals=" + idId);

        // Get all the displacementHistList where id equals to idId + 1
        defaultDisplacementHistShouldNotBeFound("idId.equals=" + (idId + 1));
    }


    @Test
    @Transactional
    public void getAllDisplacementHistsByIdPipelineSectionIsEqualToSomething() throws Exception {
        // Get already existing entity
        PipelineSection idPipelineSection = displacementHist.getIdPipelineSection();
        displacementHistRepository.saveAndFlush(displacementHist);
        Long idPipelineSectionId = idPipelineSection.getId();

        // Get all the displacementHistList where idPipelineSection equals to idPipelineSectionId
        defaultDisplacementHistShouldBeFound("idPipelineSectionId.equals=" + idPipelineSectionId);

        // Get all the displacementHistList where idPipelineSection equals to idPipelineSectionId + 1
        defaultDisplacementHistShouldNotBeFound("idPipelineSectionId.equals=" + (idPipelineSectionId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultDisplacementHistShouldBeFound(String filter) throws Exception {
        restDisplacementHistMockMvc.perform(get("/api/displacement-hists?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(displacementHist.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateFrom").value(hasItem(DEFAULT_DATE_FROM.toString())))
            .andExpect(jsonPath("$.[*].dateTo").value(hasItem(DEFAULT_DATE_TO.toString())))
            .andExpect(jsonPath("$.[*].idWrk").value(hasItem(DEFAULT_ID_WRK.intValue())))
            .andExpect(jsonPath("$.[*].deltaX").value(hasItem(DEFAULT_DELTA_X.intValue())))
            .andExpect(jsonPath("$.[*].deltaY").value(hasItem(DEFAULT_DELTA_Y.intValue())))
            .andExpect(jsonPath("$.[*].deltaZ").value(hasItem(DEFAULT_DELTA_Z.intValue())))
            .andExpect(jsonPath("$.[*].deltaTotal").value(hasItem(DEFAULT_DELTA_TOTAL.intValue())))
            .andExpect(jsonPath("$.[*].kpStart").value(hasItem(DEFAULT_KP_START.intValue())))
            .andExpect(jsonPath("$.[*].kpEnd").value(hasItem(DEFAULT_KP_END.intValue())))
            .andExpect(jsonPath("$.[*].isCurrentFlag").value(hasItem(DEFAULT_IS_CURRENT_FLAG)))
            .andExpect(jsonPath("$.[*].idStatus").value(hasItem(DEFAULT_ID_STATUS.intValue())))
            .andExpect(jsonPath("$.[*].comment").value(hasItem(DEFAULT_COMMENT)))
            .andExpect(jsonPath("$.[*].dateCreate").value(hasItem(DEFAULT_DATE_CREATE.toString())))
            .andExpect(jsonPath("$.[*].dateEdit").value(hasItem(DEFAULT_DATE_EDIT.toString())))
            .andExpect(jsonPath("$.[*].creator").value(hasItem(DEFAULT_CREATOR)))
            .andExpect(jsonPath("$.[*].editor").value(hasItem(DEFAULT_EDITOR)));

        // Check, that the count call also returns 1
        restDisplacementHistMockMvc.perform(get("/api/displacement-hists/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultDisplacementHistShouldNotBeFound(String filter) throws Exception {
        restDisplacementHistMockMvc.perform(get("/api/displacement-hists?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDisplacementHistMockMvc.perform(get("/api/displacement-hists/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingDisplacementHist() throws Exception {
        // Get the displacementHist
        restDisplacementHistMockMvc.perform(get("/api/displacement-hists/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDisplacementHist() throws Exception {
        // Initialize the database
        displacementHistRepository.saveAndFlush(displacementHist);

        int databaseSizeBeforeUpdate = displacementHistRepository.findAll().size();

        // Update the displacementHist
        DisplacementHist updatedDisplacementHist = displacementHistRepository.findById(displacementHist.getId()).get();
        // Disconnect from session so that the updates on updatedDisplacementHist are not directly saved in db
        em.detach(updatedDisplacementHist);
        updatedDisplacementHist
            .dateFrom(UPDATED_DATE_FROM)
            .dateTo(UPDATED_DATE_TO)
            .idWrk(UPDATED_ID_WRK)
            .deltaX(UPDATED_DELTA_X)
            .deltaY(UPDATED_DELTA_Y)
            .deltaZ(UPDATED_DELTA_Z)
            .deltaTotal(UPDATED_DELTA_TOTAL)
            .kpStart(UPDATED_KP_START)
            .kpEnd(UPDATED_KP_END)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .idStatus(UPDATED_ID_STATUS)
            .comment(UPDATED_COMMENT)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        DisplacementHistDTO displacementHistDTO = displacementHistMapper.toDto(updatedDisplacementHist);

        restDisplacementHistMockMvc.perform(put("/api/displacement-hists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(displacementHistDTO)))
            .andExpect(status().isOk());

        // Validate the DisplacementHist in the database
        List<DisplacementHist> displacementHistList = displacementHistRepository.findAll();
        assertThat(displacementHistList).hasSize(databaseSizeBeforeUpdate);
        DisplacementHist testDisplacementHist = displacementHistList.get(displacementHistList.size() - 1);
        assertThat(testDisplacementHist.getDateFrom()).isEqualTo(UPDATED_DATE_FROM);
        assertThat(testDisplacementHist.getDateTo()).isEqualTo(UPDATED_DATE_TO);
        assertThat(testDisplacementHist.getIdWrk()).isEqualTo(UPDATED_ID_WRK);
        assertThat(testDisplacementHist.getDeltaX()).isEqualTo(UPDATED_DELTA_X);
        assertThat(testDisplacementHist.getDeltaY()).isEqualTo(UPDATED_DELTA_Y);
        assertThat(testDisplacementHist.getDeltaZ()).isEqualTo(UPDATED_DELTA_Z);
        assertThat(testDisplacementHist.getDeltaTotal()).isEqualTo(UPDATED_DELTA_TOTAL);
        assertThat(testDisplacementHist.getKpStart()).isEqualTo(UPDATED_KP_START);
        assertThat(testDisplacementHist.getKpEnd()).isEqualTo(UPDATED_KP_END);
        assertThat(testDisplacementHist.getIsCurrentFlag()).isEqualTo(UPDATED_IS_CURRENT_FLAG);
        assertThat(testDisplacementHist.getIdStatus()).isEqualTo(UPDATED_ID_STATUS);
        assertThat(testDisplacementHist.getComment()).isEqualTo(UPDATED_COMMENT);
        assertThat(testDisplacementHist.getDateCreate()).isEqualTo(UPDATED_DATE_CREATE);
        assertThat(testDisplacementHist.getDateEdit()).isEqualTo(UPDATED_DATE_EDIT);
        assertThat(testDisplacementHist.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testDisplacementHist.getEditor()).isEqualTo(UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void updateNonExistingDisplacementHist() throws Exception {
        int databaseSizeBeforeUpdate = displacementHistRepository.findAll().size();

        // Create the DisplacementHist
        DisplacementHistDTO displacementHistDTO = displacementHistMapper.toDto(displacementHist);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDisplacementHistMockMvc.perform(put("/api/displacement-hists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(displacementHistDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DisplacementHist in the database
        List<DisplacementHist> displacementHistList = displacementHistRepository.findAll();
        assertThat(displacementHistList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDisplacementHist() throws Exception {
        // Initialize the database
        displacementHistRepository.saveAndFlush(displacementHist);

        int databaseSizeBeforeDelete = displacementHistRepository.findAll().size();

        // Delete the displacementHist
        restDisplacementHistMockMvc.perform(delete("/api/displacement-hists/{id}", displacementHist.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<DisplacementHist> displacementHistList = displacementHistRepository.findAll();
        assertThat(displacementHistList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DisplacementHist.class);
        DisplacementHist displacementHist1 = new DisplacementHist();
        displacementHist1.setId(1L);
        DisplacementHist displacementHist2 = new DisplacementHist();
        displacementHist2.setId(displacementHist1.getId());
        assertThat(displacementHist1).isEqualTo(displacementHist2);
        displacementHist2.setId(2L);
        assertThat(displacementHist1).isNotEqualTo(displacementHist2);
        displacementHist1.setId(null);
        assertThat(displacementHist1).isNotEqualTo(displacementHist2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DisplacementHistDTO.class);
        DisplacementHistDTO displacementHistDTO1 = new DisplacementHistDTO();
        displacementHistDTO1.setId(1L);
        DisplacementHistDTO displacementHistDTO2 = new DisplacementHistDTO();
        assertThat(displacementHistDTO1).isNotEqualTo(displacementHistDTO2);
        displacementHistDTO2.setId(displacementHistDTO1.getId());
        assertThat(displacementHistDTO1).isEqualTo(displacementHistDTO2);
        displacementHistDTO2.setId(2L);
        assertThat(displacementHistDTO1).isNotEqualTo(displacementHistDTO2);
        displacementHistDTO1.setId(null);
        assertThat(displacementHistDTO1).isNotEqualTo(displacementHistDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(displacementHistMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(displacementHistMapper.fromId(null)).isNull();
    }
}
