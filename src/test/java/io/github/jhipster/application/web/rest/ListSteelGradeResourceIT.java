package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.ListSteelGrade;
import io.github.jhipster.application.domain.BendHist;
import io.github.jhipster.application.domain.BuckleArrestorHist;
import io.github.jhipster.application.domain.PipeHist;
import io.github.jhipster.application.domain.TeeHist;
import io.github.jhipster.application.domain.ValveHist;
import io.github.jhipster.application.repository.ListSteelGradeRepository;
import io.github.jhipster.application.service.ListSteelGradeService;
import io.github.jhipster.application.service.dto.ListSteelGradeDTO;
import io.github.jhipster.application.service.mapper.ListSteelGradeMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.ListSteelGradeCriteria;
import io.github.jhipster.application.service.ListSteelGradeQueryService;

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
 * Integration tests for the {@Link ListSteelGradeResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class ListSteelGradeResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_FULL_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FULL_NAME = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_STEEL_DENSITY = new BigDecimal(1);
    private static final BigDecimal UPDATED_STEEL_DENSITY = new BigDecimal(2);

    private static final BigDecimal DEFAULT_THERM_EXP_COEF = new BigDecimal(1);
    private static final BigDecimal UPDATED_THERM_EXP_COEF = new BigDecimal(2);

    private static final Integer DEFAULT_IS_CURRENT_FLAG = 1;
    private static final Integer UPDATED_IS_CURRENT_FLAG = 2;

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Instant DEFAULT_DATE_CREATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_CREATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_DATE_EDIT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_EDIT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CREATOR = "AAAAAAAAAA";
    private static final String UPDATED_CREATOR = "BBBBBBBBBB";

    private static final String DEFAULT_EDITOR = "AAAAAAAAAA";
    private static final String UPDATED_EDITOR = "BBBBBBBBBB";

    @Autowired
    private ListSteelGradeRepository listSteelGradeRepository;

    @Autowired
    private ListSteelGradeMapper listSteelGradeMapper;

    @Autowired
    private ListSteelGradeService listSteelGradeService;

    @Autowired
    private ListSteelGradeQueryService listSteelGradeQueryService;

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

    private MockMvc restListSteelGradeMockMvc;

    private ListSteelGrade listSteelGrade;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ListSteelGradeResource listSteelGradeResource = new ListSteelGradeResource(listSteelGradeService, listSteelGradeQueryService);
        this.restListSteelGradeMockMvc = MockMvcBuilders.standaloneSetup(listSteelGradeResource)
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
    public static ListSteelGrade createEntity(EntityManager em) {
        ListSteelGrade listSteelGrade = new ListSteelGrade()
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME)
            .fullName(DEFAULT_FULL_NAME)
            .steelDensity(DEFAULT_STEEL_DENSITY)
            .thermExpCoef(DEFAULT_THERM_EXP_COEF)
            .isCurrentFlag(DEFAULT_IS_CURRENT_FLAG)
            .description(DEFAULT_DESCRIPTION)
            .dateCreate(DEFAULT_DATE_CREATE)
            .dateEdit(DEFAULT_DATE_EDIT)
            .creator(DEFAULT_CREATOR)
            .editor(DEFAULT_EDITOR);
        return listSteelGrade;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ListSteelGrade createUpdatedEntity(EntityManager em) {
        ListSteelGrade listSteelGrade = new ListSteelGrade()
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .steelDensity(UPDATED_STEEL_DENSITY)
            .thermExpCoef(UPDATED_THERM_EXP_COEF)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        return listSteelGrade;
    }

    @BeforeEach
    public void initTest() {
        listSteelGrade = createEntity(em);
    }

    @Test
    @Transactional
    public void createListSteelGrade() throws Exception {
        int databaseSizeBeforeCreate = listSteelGradeRepository.findAll().size();

        // Create the ListSteelGrade
        ListSteelGradeDTO listSteelGradeDTO = listSteelGradeMapper.toDto(listSteelGrade);
        restListSteelGradeMockMvc.perform(post("/api/list-steel-grades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listSteelGradeDTO)))
            .andExpect(status().isCreated());

        // Validate the ListSteelGrade in the database
        List<ListSteelGrade> listSteelGradeList = listSteelGradeRepository.findAll();
        assertThat(listSteelGradeList).hasSize(databaseSizeBeforeCreate + 1);
        ListSteelGrade testListSteelGrade = listSteelGradeList.get(listSteelGradeList.size() - 1);
        assertThat(testListSteelGrade.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testListSteelGrade.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testListSteelGrade.getFullName()).isEqualTo(DEFAULT_FULL_NAME);
        assertThat(testListSteelGrade.getSteelDensity()).isEqualTo(DEFAULT_STEEL_DENSITY);
        assertThat(testListSteelGrade.getThermExpCoef()).isEqualTo(DEFAULT_THERM_EXP_COEF);
        assertThat(testListSteelGrade.getIsCurrentFlag()).isEqualTo(DEFAULT_IS_CURRENT_FLAG);
        assertThat(testListSteelGrade.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testListSteelGrade.getDateCreate()).isEqualTo(DEFAULT_DATE_CREATE);
        assertThat(testListSteelGrade.getDateEdit()).isEqualTo(DEFAULT_DATE_EDIT);
        assertThat(testListSteelGrade.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testListSteelGrade.getEditor()).isEqualTo(DEFAULT_EDITOR);
    }

    @Test
    @Transactional
    public void createListSteelGradeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = listSteelGradeRepository.findAll().size();

        // Create the ListSteelGrade with an existing ID
        listSteelGrade.setId(1L);
        ListSteelGradeDTO listSteelGradeDTO = listSteelGradeMapper.toDto(listSteelGrade);

        // An entity with an existing ID cannot be created, so this API call must fail
        restListSteelGradeMockMvc.perform(post("/api/list-steel-grades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listSteelGradeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListSteelGrade in the database
        List<ListSteelGrade> listSteelGradeList = listSteelGradeRepository.findAll();
        assertThat(listSteelGradeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = listSteelGradeRepository.findAll().size();
        // set the field null
        listSteelGrade.setCode(null);

        // Create the ListSteelGrade, which fails.
        ListSteelGradeDTO listSteelGradeDTO = listSteelGradeMapper.toDto(listSteelGrade);

        restListSteelGradeMockMvc.perform(post("/api/list-steel-grades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listSteelGradeDTO)))
            .andExpect(status().isBadRequest());

        List<ListSteelGrade> listSteelGradeList = listSteelGradeRepository.findAll();
        assertThat(listSteelGradeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listSteelGradeRepository.findAll().size();
        // set the field null
        listSteelGrade.setName(null);

        // Create the ListSteelGrade, which fails.
        ListSteelGradeDTO listSteelGradeDTO = listSteelGradeMapper.toDto(listSteelGrade);

        restListSteelGradeMockMvc.perform(post("/api/list-steel-grades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listSteelGradeDTO)))
            .andExpect(status().isBadRequest());

        List<ListSteelGrade> listSteelGradeList = listSteelGradeRepository.findAll();
        assertThat(listSteelGradeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFullNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listSteelGradeRepository.findAll().size();
        // set the field null
        listSteelGrade.setFullName(null);

        // Create the ListSteelGrade, which fails.
        ListSteelGradeDTO listSteelGradeDTO = listSteelGradeMapper.toDto(listSteelGrade);

        restListSteelGradeMockMvc.perform(post("/api/list-steel-grades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listSteelGradeDTO)))
            .andExpect(status().isBadRequest());

        List<ListSteelGrade> listSteelGradeList = listSteelGradeRepository.findAll();
        assertThat(listSteelGradeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsCurrentFlagIsRequired() throws Exception {
        int databaseSizeBeforeTest = listSteelGradeRepository.findAll().size();
        // set the field null
        listSteelGrade.setIsCurrentFlag(null);

        // Create the ListSteelGrade, which fails.
        ListSteelGradeDTO listSteelGradeDTO = listSteelGradeMapper.toDto(listSteelGrade);

        restListSteelGradeMockMvc.perform(post("/api/list-steel-grades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listSteelGradeDTO)))
            .andExpect(status().isBadRequest());

        List<ListSteelGrade> listSteelGradeList = listSteelGradeRepository.findAll();
        assertThat(listSteelGradeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllListSteelGrades() throws Exception {
        // Initialize the database
        listSteelGradeRepository.saveAndFlush(listSteelGrade);

        // Get all the listSteelGradeList
        restListSteelGradeMockMvc.perform(get("/api/list-steel-grades?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listSteelGrade.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].fullName").value(hasItem(DEFAULT_FULL_NAME.toString())))
            .andExpect(jsonPath("$.[*].steelDensity").value(hasItem(DEFAULT_STEEL_DENSITY.intValue())))
            .andExpect(jsonPath("$.[*].thermExpCoef").value(hasItem(DEFAULT_THERM_EXP_COEF.intValue())))
            .andExpect(jsonPath("$.[*].isCurrentFlag").value(hasItem(DEFAULT_IS_CURRENT_FLAG)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].dateCreate").value(hasItem(DEFAULT_DATE_CREATE.toString())))
            .andExpect(jsonPath("$.[*].dateEdit").value(hasItem(DEFAULT_DATE_EDIT.toString())))
            .andExpect(jsonPath("$.[*].creator").value(hasItem(DEFAULT_CREATOR.toString())))
            .andExpect(jsonPath("$.[*].editor").value(hasItem(DEFAULT_EDITOR.toString())));
    }
    
    @Test
    @Transactional
    public void getListSteelGrade() throws Exception {
        // Initialize the database
        listSteelGradeRepository.saveAndFlush(listSteelGrade);

        // Get the listSteelGrade
        restListSteelGradeMockMvc.perform(get("/api/list-steel-grades/{id}", listSteelGrade.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(listSteelGrade.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.fullName").value(DEFAULT_FULL_NAME.toString()))
            .andExpect(jsonPath("$.steelDensity").value(DEFAULT_STEEL_DENSITY.intValue()))
            .andExpect(jsonPath("$.thermExpCoef").value(DEFAULT_THERM_EXP_COEF.intValue()))
            .andExpect(jsonPath("$.isCurrentFlag").value(DEFAULT_IS_CURRENT_FLAG))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.dateCreate").value(DEFAULT_DATE_CREATE.toString()))
            .andExpect(jsonPath("$.dateEdit").value(DEFAULT_DATE_EDIT.toString()))
            .andExpect(jsonPath("$.creator").value(DEFAULT_CREATOR.toString()))
            .andExpect(jsonPath("$.editor").value(DEFAULT_EDITOR.toString()));
    }

    @Test
    @Transactional
    public void getAllListSteelGradesByCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        listSteelGradeRepository.saveAndFlush(listSteelGrade);

        // Get all the listSteelGradeList where code equals to DEFAULT_CODE
        defaultListSteelGradeShouldBeFound("code.equals=" + DEFAULT_CODE);

        // Get all the listSteelGradeList where code equals to UPDATED_CODE
        defaultListSteelGradeShouldNotBeFound("code.equals=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListSteelGradesByCodeIsInShouldWork() throws Exception {
        // Initialize the database
        listSteelGradeRepository.saveAndFlush(listSteelGrade);

        // Get all the listSteelGradeList where code in DEFAULT_CODE or UPDATED_CODE
        defaultListSteelGradeShouldBeFound("code.in=" + DEFAULT_CODE + "," + UPDATED_CODE);

        // Get all the listSteelGradeList where code equals to UPDATED_CODE
        defaultListSteelGradeShouldNotBeFound("code.in=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListSteelGradesByCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        listSteelGradeRepository.saveAndFlush(listSteelGrade);

        // Get all the listSteelGradeList where code is not null
        defaultListSteelGradeShouldBeFound("code.specified=true");

        // Get all the listSteelGradeList where code is null
        defaultListSteelGradeShouldNotBeFound("code.specified=false");
    }

    @Test
    @Transactional
    public void getAllListSteelGradesByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listSteelGradeRepository.saveAndFlush(listSteelGrade);

        // Get all the listSteelGradeList where name equals to DEFAULT_NAME
        defaultListSteelGradeShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the listSteelGradeList where name equals to UPDATED_NAME
        defaultListSteelGradeShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListSteelGradesByNameIsInShouldWork() throws Exception {
        // Initialize the database
        listSteelGradeRepository.saveAndFlush(listSteelGrade);

        // Get all the listSteelGradeList where name in DEFAULT_NAME or UPDATED_NAME
        defaultListSteelGradeShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the listSteelGradeList where name equals to UPDATED_NAME
        defaultListSteelGradeShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListSteelGradesByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listSteelGradeRepository.saveAndFlush(listSteelGrade);

        // Get all the listSteelGradeList where name is not null
        defaultListSteelGradeShouldBeFound("name.specified=true");

        // Get all the listSteelGradeList where name is null
        defaultListSteelGradeShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    public void getAllListSteelGradesByFullNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listSteelGradeRepository.saveAndFlush(listSteelGrade);

        // Get all the listSteelGradeList where fullName equals to DEFAULT_FULL_NAME
        defaultListSteelGradeShouldBeFound("fullName.equals=" + DEFAULT_FULL_NAME);

        // Get all the listSteelGradeList where fullName equals to UPDATED_FULL_NAME
        defaultListSteelGradeShouldNotBeFound("fullName.equals=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListSteelGradesByFullNameIsInShouldWork() throws Exception {
        // Initialize the database
        listSteelGradeRepository.saveAndFlush(listSteelGrade);

        // Get all the listSteelGradeList where fullName in DEFAULT_FULL_NAME or UPDATED_FULL_NAME
        defaultListSteelGradeShouldBeFound("fullName.in=" + DEFAULT_FULL_NAME + "," + UPDATED_FULL_NAME);

        // Get all the listSteelGradeList where fullName equals to UPDATED_FULL_NAME
        defaultListSteelGradeShouldNotBeFound("fullName.in=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListSteelGradesByFullNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listSteelGradeRepository.saveAndFlush(listSteelGrade);

        // Get all the listSteelGradeList where fullName is not null
        defaultListSteelGradeShouldBeFound("fullName.specified=true");

        // Get all the listSteelGradeList where fullName is null
        defaultListSteelGradeShouldNotBeFound("fullName.specified=false");
    }

    @Test
    @Transactional
    public void getAllListSteelGradesBySteelDensityIsEqualToSomething() throws Exception {
        // Initialize the database
        listSteelGradeRepository.saveAndFlush(listSteelGrade);

        // Get all the listSteelGradeList where steelDensity equals to DEFAULT_STEEL_DENSITY
        defaultListSteelGradeShouldBeFound("steelDensity.equals=" + DEFAULT_STEEL_DENSITY);

        // Get all the listSteelGradeList where steelDensity equals to UPDATED_STEEL_DENSITY
        defaultListSteelGradeShouldNotBeFound("steelDensity.equals=" + UPDATED_STEEL_DENSITY);
    }

    @Test
    @Transactional
    public void getAllListSteelGradesBySteelDensityIsInShouldWork() throws Exception {
        // Initialize the database
        listSteelGradeRepository.saveAndFlush(listSteelGrade);

        // Get all the listSteelGradeList where steelDensity in DEFAULT_STEEL_DENSITY or UPDATED_STEEL_DENSITY
        defaultListSteelGradeShouldBeFound("steelDensity.in=" + DEFAULT_STEEL_DENSITY + "," + UPDATED_STEEL_DENSITY);

        // Get all the listSteelGradeList where steelDensity equals to UPDATED_STEEL_DENSITY
        defaultListSteelGradeShouldNotBeFound("steelDensity.in=" + UPDATED_STEEL_DENSITY);
    }

    @Test
    @Transactional
    public void getAllListSteelGradesBySteelDensityIsNullOrNotNull() throws Exception {
        // Initialize the database
        listSteelGradeRepository.saveAndFlush(listSteelGrade);

        // Get all the listSteelGradeList where steelDensity is not null
        defaultListSteelGradeShouldBeFound("steelDensity.specified=true");

        // Get all the listSteelGradeList where steelDensity is null
        defaultListSteelGradeShouldNotBeFound("steelDensity.specified=false");
    }

    @Test
    @Transactional
    public void getAllListSteelGradesByThermExpCoefIsEqualToSomething() throws Exception {
        // Initialize the database
        listSteelGradeRepository.saveAndFlush(listSteelGrade);

        // Get all the listSteelGradeList where thermExpCoef equals to DEFAULT_THERM_EXP_COEF
        defaultListSteelGradeShouldBeFound("thermExpCoef.equals=" + DEFAULT_THERM_EXP_COEF);

        // Get all the listSteelGradeList where thermExpCoef equals to UPDATED_THERM_EXP_COEF
        defaultListSteelGradeShouldNotBeFound("thermExpCoef.equals=" + UPDATED_THERM_EXP_COEF);
    }

    @Test
    @Transactional
    public void getAllListSteelGradesByThermExpCoefIsInShouldWork() throws Exception {
        // Initialize the database
        listSteelGradeRepository.saveAndFlush(listSteelGrade);

        // Get all the listSteelGradeList where thermExpCoef in DEFAULT_THERM_EXP_COEF or UPDATED_THERM_EXP_COEF
        defaultListSteelGradeShouldBeFound("thermExpCoef.in=" + DEFAULT_THERM_EXP_COEF + "," + UPDATED_THERM_EXP_COEF);

        // Get all the listSteelGradeList where thermExpCoef equals to UPDATED_THERM_EXP_COEF
        defaultListSteelGradeShouldNotBeFound("thermExpCoef.in=" + UPDATED_THERM_EXP_COEF);
    }

    @Test
    @Transactional
    public void getAllListSteelGradesByThermExpCoefIsNullOrNotNull() throws Exception {
        // Initialize the database
        listSteelGradeRepository.saveAndFlush(listSteelGrade);

        // Get all the listSteelGradeList where thermExpCoef is not null
        defaultListSteelGradeShouldBeFound("thermExpCoef.specified=true");

        // Get all the listSteelGradeList where thermExpCoef is null
        defaultListSteelGradeShouldNotBeFound("thermExpCoef.specified=false");
    }

    @Test
    @Transactional
    public void getAllListSteelGradesByIsCurrentFlagIsEqualToSomething() throws Exception {
        // Initialize the database
        listSteelGradeRepository.saveAndFlush(listSteelGrade);

        // Get all the listSteelGradeList where isCurrentFlag equals to DEFAULT_IS_CURRENT_FLAG
        defaultListSteelGradeShouldBeFound("isCurrentFlag.equals=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listSteelGradeList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListSteelGradeShouldNotBeFound("isCurrentFlag.equals=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListSteelGradesByIsCurrentFlagIsInShouldWork() throws Exception {
        // Initialize the database
        listSteelGradeRepository.saveAndFlush(listSteelGrade);

        // Get all the listSteelGradeList where isCurrentFlag in DEFAULT_IS_CURRENT_FLAG or UPDATED_IS_CURRENT_FLAG
        defaultListSteelGradeShouldBeFound("isCurrentFlag.in=" + DEFAULT_IS_CURRENT_FLAG + "," + UPDATED_IS_CURRENT_FLAG);

        // Get all the listSteelGradeList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListSteelGradeShouldNotBeFound("isCurrentFlag.in=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListSteelGradesByIsCurrentFlagIsNullOrNotNull() throws Exception {
        // Initialize the database
        listSteelGradeRepository.saveAndFlush(listSteelGrade);

        // Get all the listSteelGradeList where isCurrentFlag is not null
        defaultListSteelGradeShouldBeFound("isCurrentFlag.specified=true");

        // Get all the listSteelGradeList where isCurrentFlag is null
        defaultListSteelGradeShouldNotBeFound("isCurrentFlag.specified=false");
    }

    @Test
    @Transactional
    public void getAllListSteelGradesByIsCurrentFlagIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        listSteelGradeRepository.saveAndFlush(listSteelGrade);

        // Get all the listSteelGradeList where isCurrentFlag greater than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListSteelGradeShouldBeFound("isCurrentFlag.greaterOrEqualThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listSteelGradeList where isCurrentFlag greater than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListSteelGradeShouldNotBeFound("isCurrentFlag.greaterOrEqualThan=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListSteelGradesByIsCurrentFlagIsLessThanSomething() throws Exception {
        // Initialize the database
        listSteelGradeRepository.saveAndFlush(listSteelGrade);

        // Get all the listSteelGradeList where isCurrentFlag less than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListSteelGradeShouldNotBeFound("isCurrentFlag.lessThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listSteelGradeList where isCurrentFlag less than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListSteelGradeShouldBeFound("isCurrentFlag.lessThan=" + UPDATED_IS_CURRENT_FLAG);
    }


    @Test
    @Transactional
    public void getAllListSteelGradesByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        listSteelGradeRepository.saveAndFlush(listSteelGrade);

        // Get all the listSteelGradeList where description equals to DEFAULT_DESCRIPTION
        defaultListSteelGradeShouldBeFound("description.equals=" + DEFAULT_DESCRIPTION);

        // Get all the listSteelGradeList where description equals to UPDATED_DESCRIPTION
        defaultListSteelGradeShouldNotBeFound("description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListSteelGradesByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        listSteelGradeRepository.saveAndFlush(listSteelGrade);

        // Get all the listSteelGradeList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
        defaultListSteelGradeShouldBeFound("description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION);

        // Get all the listSteelGradeList where description equals to UPDATED_DESCRIPTION
        defaultListSteelGradeShouldNotBeFound("description.in=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListSteelGradesByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        listSteelGradeRepository.saveAndFlush(listSteelGrade);

        // Get all the listSteelGradeList where description is not null
        defaultListSteelGradeShouldBeFound("description.specified=true");

        // Get all the listSteelGradeList where description is null
        defaultListSteelGradeShouldNotBeFound("description.specified=false");
    }

    @Test
    @Transactional
    public void getAllListSteelGradesByDateCreateIsEqualToSomething() throws Exception {
        // Initialize the database
        listSteelGradeRepository.saveAndFlush(listSteelGrade);

        // Get all the listSteelGradeList where dateCreate equals to DEFAULT_DATE_CREATE
        defaultListSteelGradeShouldBeFound("dateCreate.equals=" + DEFAULT_DATE_CREATE);

        // Get all the listSteelGradeList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListSteelGradeShouldNotBeFound("dateCreate.equals=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListSteelGradesByDateCreateIsInShouldWork() throws Exception {
        // Initialize the database
        listSteelGradeRepository.saveAndFlush(listSteelGrade);

        // Get all the listSteelGradeList where dateCreate in DEFAULT_DATE_CREATE or UPDATED_DATE_CREATE
        defaultListSteelGradeShouldBeFound("dateCreate.in=" + DEFAULT_DATE_CREATE + "," + UPDATED_DATE_CREATE);

        // Get all the listSteelGradeList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListSteelGradeShouldNotBeFound("dateCreate.in=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListSteelGradesByDateCreateIsNullOrNotNull() throws Exception {
        // Initialize the database
        listSteelGradeRepository.saveAndFlush(listSteelGrade);

        // Get all the listSteelGradeList where dateCreate is not null
        defaultListSteelGradeShouldBeFound("dateCreate.specified=true");

        // Get all the listSteelGradeList where dateCreate is null
        defaultListSteelGradeShouldNotBeFound("dateCreate.specified=false");
    }

    @Test
    @Transactional
    public void getAllListSteelGradesByDateEditIsEqualToSomething() throws Exception {
        // Initialize the database
        listSteelGradeRepository.saveAndFlush(listSteelGrade);

        // Get all the listSteelGradeList where dateEdit equals to DEFAULT_DATE_EDIT
        defaultListSteelGradeShouldBeFound("dateEdit.equals=" + DEFAULT_DATE_EDIT);

        // Get all the listSteelGradeList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListSteelGradeShouldNotBeFound("dateEdit.equals=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListSteelGradesByDateEditIsInShouldWork() throws Exception {
        // Initialize the database
        listSteelGradeRepository.saveAndFlush(listSteelGrade);

        // Get all the listSteelGradeList where dateEdit in DEFAULT_DATE_EDIT or UPDATED_DATE_EDIT
        defaultListSteelGradeShouldBeFound("dateEdit.in=" + DEFAULT_DATE_EDIT + "," + UPDATED_DATE_EDIT);

        // Get all the listSteelGradeList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListSteelGradeShouldNotBeFound("dateEdit.in=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListSteelGradesByDateEditIsNullOrNotNull() throws Exception {
        // Initialize the database
        listSteelGradeRepository.saveAndFlush(listSteelGrade);

        // Get all the listSteelGradeList where dateEdit is not null
        defaultListSteelGradeShouldBeFound("dateEdit.specified=true");

        // Get all the listSteelGradeList where dateEdit is null
        defaultListSteelGradeShouldNotBeFound("dateEdit.specified=false");
    }

    @Test
    @Transactional
    public void getAllListSteelGradesByCreatorIsEqualToSomething() throws Exception {
        // Initialize the database
        listSteelGradeRepository.saveAndFlush(listSteelGrade);

        // Get all the listSteelGradeList where creator equals to DEFAULT_CREATOR
        defaultListSteelGradeShouldBeFound("creator.equals=" + DEFAULT_CREATOR);

        // Get all the listSteelGradeList where creator equals to UPDATED_CREATOR
        defaultListSteelGradeShouldNotBeFound("creator.equals=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListSteelGradesByCreatorIsInShouldWork() throws Exception {
        // Initialize the database
        listSteelGradeRepository.saveAndFlush(listSteelGrade);

        // Get all the listSteelGradeList where creator in DEFAULT_CREATOR or UPDATED_CREATOR
        defaultListSteelGradeShouldBeFound("creator.in=" + DEFAULT_CREATOR + "," + UPDATED_CREATOR);

        // Get all the listSteelGradeList where creator equals to UPDATED_CREATOR
        defaultListSteelGradeShouldNotBeFound("creator.in=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListSteelGradesByCreatorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listSteelGradeRepository.saveAndFlush(listSteelGrade);

        // Get all the listSteelGradeList where creator is not null
        defaultListSteelGradeShouldBeFound("creator.specified=true");

        // Get all the listSteelGradeList where creator is null
        defaultListSteelGradeShouldNotBeFound("creator.specified=false");
    }

    @Test
    @Transactional
    public void getAllListSteelGradesByEditorIsEqualToSomething() throws Exception {
        // Initialize the database
        listSteelGradeRepository.saveAndFlush(listSteelGrade);

        // Get all the listSteelGradeList where editor equals to DEFAULT_EDITOR
        defaultListSteelGradeShouldBeFound("editor.equals=" + DEFAULT_EDITOR);

        // Get all the listSteelGradeList where editor equals to UPDATED_EDITOR
        defaultListSteelGradeShouldNotBeFound("editor.equals=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListSteelGradesByEditorIsInShouldWork() throws Exception {
        // Initialize the database
        listSteelGradeRepository.saveAndFlush(listSteelGrade);

        // Get all the listSteelGradeList where editor in DEFAULT_EDITOR or UPDATED_EDITOR
        defaultListSteelGradeShouldBeFound("editor.in=" + DEFAULT_EDITOR + "," + UPDATED_EDITOR);

        // Get all the listSteelGradeList where editor equals to UPDATED_EDITOR
        defaultListSteelGradeShouldNotBeFound("editor.in=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListSteelGradesByEditorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listSteelGradeRepository.saveAndFlush(listSteelGrade);

        // Get all the listSteelGradeList where editor is not null
        defaultListSteelGradeShouldBeFound("editor.specified=true");

        // Get all the listSteelGradeList where editor is null
        defaultListSteelGradeShouldNotBeFound("editor.specified=false");
    }

    @Test
    @Transactional
    public void getAllListSteelGradesByBendHistIsEqualToSomething() throws Exception {
        // Initialize the database
        BendHist bendHist = BendHistResourceIT.createEntity(em);
        em.persist(bendHist);
        em.flush();
        listSteelGrade.addBendHist(bendHist);
        listSteelGradeRepository.saveAndFlush(listSteelGrade);
        Long bendHistId = bendHist.getId();

        // Get all the listSteelGradeList where bendHist equals to bendHistId
        defaultListSteelGradeShouldBeFound("bendHistId.equals=" + bendHistId);

        // Get all the listSteelGradeList where bendHist equals to bendHistId + 1
        defaultListSteelGradeShouldNotBeFound("bendHistId.equals=" + (bendHistId + 1));
    }


    @Test
    @Transactional
    public void getAllListSteelGradesByBuckleArrestorHistIsEqualToSomething() throws Exception {
        // Initialize the database
        BuckleArrestorHist buckleArrestorHist = BuckleArrestorHistResourceIT.createEntity(em);
        em.persist(buckleArrestorHist);
        em.flush();
        listSteelGrade.addBuckleArrestorHist(buckleArrestorHist);
        listSteelGradeRepository.saveAndFlush(listSteelGrade);
        Long buckleArrestorHistId = buckleArrestorHist.getId();

        // Get all the listSteelGradeList where buckleArrestorHist equals to buckleArrestorHistId
        defaultListSteelGradeShouldBeFound("buckleArrestorHistId.equals=" + buckleArrestorHistId);

        // Get all the listSteelGradeList where buckleArrestorHist equals to buckleArrestorHistId + 1
        defaultListSteelGradeShouldNotBeFound("buckleArrestorHistId.equals=" + (buckleArrestorHistId + 1));
    }


    @Test
    @Transactional
    public void getAllListSteelGradesByPipeHistIsEqualToSomething() throws Exception {
        // Initialize the database
        PipeHist pipeHist = PipeHistResourceIT.createEntity(em);
        em.persist(pipeHist);
        em.flush();
        listSteelGrade.addPipeHist(pipeHist);
        listSteelGradeRepository.saveAndFlush(listSteelGrade);
        Long pipeHistId = pipeHist.getId();

        // Get all the listSteelGradeList where pipeHist equals to pipeHistId
        defaultListSteelGradeShouldBeFound("pipeHistId.equals=" + pipeHistId);

        // Get all the listSteelGradeList where pipeHist equals to pipeHistId + 1
        defaultListSteelGradeShouldNotBeFound("pipeHistId.equals=" + (pipeHistId + 1));
    }


    @Test
    @Transactional
    public void getAllListSteelGradesByTeeHistIsEqualToSomething() throws Exception {
        // Initialize the database
        TeeHist teeHist = TeeHistResourceIT.createEntity(em);
        em.persist(teeHist);
        em.flush();
        listSteelGrade.addTeeHist(teeHist);
        listSteelGradeRepository.saveAndFlush(listSteelGrade);
        Long teeHistId = teeHist.getId();

        // Get all the listSteelGradeList where teeHist equals to teeHistId
        defaultListSteelGradeShouldBeFound("teeHistId.equals=" + teeHistId);

        // Get all the listSteelGradeList where teeHist equals to teeHistId + 1
        defaultListSteelGradeShouldNotBeFound("teeHistId.equals=" + (teeHistId + 1));
    }


    @Test
    @Transactional
    public void getAllListSteelGradesByValveHistIsEqualToSomething() throws Exception {
        // Initialize the database
        ValveHist valveHist = ValveHistResourceIT.createEntity(em);
        em.persist(valveHist);
        em.flush();
        listSteelGrade.addValveHist(valveHist);
        listSteelGradeRepository.saveAndFlush(listSteelGrade);
        Long valveHistId = valveHist.getId();

        // Get all the listSteelGradeList where valveHist equals to valveHistId
        defaultListSteelGradeShouldBeFound("valveHistId.equals=" + valveHistId);

        // Get all the listSteelGradeList where valveHist equals to valveHistId + 1
        defaultListSteelGradeShouldNotBeFound("valveHistId.equals=" + (valveHistId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultListSteelGradeShouldBeFound(String filter) throws Exception {
        restListSteelGradeMockMvc.perform(get("/api/list-steel-grades?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listSteelGrade.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].fullName").value(hasItem(DEFAULT_FULL_NAME)))
            .andExpect(jsonPath("$.[*].steelDensity").value(hasItem(DEFAULT_STEEL_DENSITY.intValue())))
            .andExpect(jsonPath("$.[*].thermExpCoef").value(hasItem(DEFAULT_THERM_EXP_COEF.intValue())))
            .andExpect(jsonPath("$.[*].isCurrentFlag").value(hasItem(DEFAULT_IS_CURRENT_FLAG)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].dateCreate").value(hasItem(DEFAULT_DATE_CREATE.toString())))
            .andExpect(jsonPath("$.[*].dateEdit").value(hasItem(DEFAULT_DATE_EDIT.toString())))
            .andExpect(jsonPath("$.[*].creator").value(hasItem(DEFAULT_CREATOR)))
            .andExpect(jsonPath("$.[*].editor").value(hasItem(DEFAULT_EDITOR)));

        // Check, that the count call also returns 1
        restListSteelGradeMockMvc.perform(get("/api/list-steel-grades/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultListSteelGradeShouldNotBeFound(String filter) throws Exception {
        restListSteelGradeMockMvc.perform(get("/api/list-steel-grades?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restListSteelGradeMockMvc.perform(get("/api/list-steel-grades/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingListSteelGrade() throws Exception {
        // Get the listSteelGrade
        restListSteelGradeMockMvc.perform(get("/api/list-steel-grades/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateListSteelGrade() throws Exception {
        // Initialize the database
        listSteelGradeRepository.saveAndFlush(listSteelGrade);

        int databaseSizeBeforeUpdate = listSteelGradeRepository.findAll().size();

        // Update the listSteelGrade
        ListSteelGrade updatedListSteelGrade = listSteelGradeRepository.findById(listSteelGrade.getId()).get();
        // Disconnect from session so that the updates on updatedListSteelGrade are not directly saved in db
        em.detach(updatedListSteelGrade);
        updatedListSteelGrade
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .steelDensity(UPDATED_STEEL_DENSITY)
            .thermExpCoef(UPDATED_THERM_EXP_COEF)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        ListSteelGradeDTO listSteelGradeDTO = listSteelGradeMapper.toDto(updatedListSteelGrade);

        restListSteelGradeMockMvc.perform(put("/api/list-steel-grades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listSteelGradeDTO)))
            .andExpect(status().isOk());

        // Validate the ListSteelGrade in the database
        List<ListSteelGrade> listSteelGradeList = listSteelGradeRepository.findAll();
        assertThat(listSteelGradeList).hasSize(databaseSizeBeforeUpdate);
        ListSteelGrade testListSteelGrade = listSteelGradeList.get(listSteelGradeList.size() - 1);
        assertThat(testListSteelGrade.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testListSteelGrade.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testListSteelGrade.getFullName()).isEqualTo(UPDATED_FULL_NAME);
        assertThat(testListSteelGrade.getSteelDensity()).isEqualTo(UPDATED_STEEL_DENSITY);
        assertThat(testListSteelGrade.getThermExpCoef()).isEqualTo(UPDATED_THERM_EXP_COEF);
        assertThat(testListSteelGrade.getIsCurrentFlag()).isEqualTo(UPDATED_IS_CURRENT_FLAG);
        assertThat(testListSteelGrade.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testListSteelGrade.getDateCreate()).isEqualTo(UPDATED_DATE_CREATE);
        assertThat(testListSteelGrade.getDateEdit()).isEqualTo(UPDATED_DATE_EDIT);
        assertThat(testListSteelGrade.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testListSteelGrade.getEditor()).isEqualTo(UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void updateNonExistingListSteelGrade() throws Exception {
        int databaseSizeBeforeUpdate = listSteelGradeRepository.findAll().size();

        // Create the ListSteelGrade
        ListSteelGradeDTO listSteelGradeDTO = listSteelGradeMapper.toDto(listSteelGrade);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restListSteelGradeMockMvc.perform(put("/api/list-steel-grades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listSteelGradeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListSteelGrade in the database
        List<ListSteelGrade> listSteelGradeList = listSteelGradeRepository.findAll();
        assertThat(listSteelGradeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteListSteelGrade() throws Exception {
        // Initialize the database
        listSteelGradeRepository.saveAndFlush(listSteelGrade);

        int databaseSizeBeforeDelete = listSteelGradeRepository.findAll().size();

        // Delete the listSteelGrade
        restListSteelGradeMockMvc.perform(delete("/api/list-steel-grades/{id}", listSteelGrade.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<ListSteelGrade> listSteelGradeList = listSteelGradeRepository.findAll();
        assertThat(listSteelGradeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListSteelGrade.class);
        ListSteelGrade listSteelGrade1 = new ListSteelGrade();
        listSteelGrade1.setId(1L);
        ListSteelGrade listSteelGrade2 = new ListSteelGrade();
        listSteelGrade2.setId(listSteelGrade1.getId());
        assertThat(listSteelGrade1).isEqualTo(listSteelGrade2);
        listSteelGrade2.setId(2L);
        assertThat(listSteelGrade1).isNotEqualTo(listSteelGrade2);
        listSteelGrade1.setId(null);
        assertThat(listSteelGrade1).isNotEqualTo(listSteelGrade2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListSteelGradeDTO.class);
        ListSteelGradeDTO listSteelGradeDTO1 = new ListSteelGradeDTO();
        listSteelGradeDTO1.setId(1L);
        ListSteelGradeDTO listSteelGradeDTO2 = new ListSteelGradeDTO();
        assertThat(listSteelGradeDTO1).isNotEqualTo(listSteelGradeDTO2);
        listSteelGradeDTO2.setId(listSteelGradeDTO1.getId());
        assertThat(listSteelGradeDTO1).isEqualTo(listSteelGradeDTO2);
        listSteelGradeDTO2.setId(2L);
        assertThat(listSteelGradeDTO1).isNotEqualTo(listSteelGradeDTO2);
        listSteelGradeDTO1.setId(null);
        assertThat(listSteelGradeDTO1).isNotEqualTo(listSteelGradeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(listSteelGradeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(listSteelGradeMapper.fromId(null)).isNull();
    }
}
