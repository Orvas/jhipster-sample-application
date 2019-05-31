package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.ListMinpressUcase;
import io.github.jhipster.application.repository.ListMinpressUcaseRepository;
import io.github.jhipster.application.service.ListMinpressUcaseService;
import io.github.jhipster.application.service.dto.ListMinpressUcaseDTO;
import io.github.jhipster.application.service.mapper.ListMinpressUcaseMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.ListMinpressUcaseCriteria;
import io.github.jhipster.application.service.ListMinpressUcaseQueryService;

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
 * Integration tests for the {@Link ListMinpressUcaseResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class ListMinpressUcaseResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_FULL_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FULL_NAME = "BBBBBBBBBB";

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
    private ListMinpressUcaseRepository listMinpressUcaseRepository;

    @Autowired
    private ListMinpressUcaseMapper listMinpressUcaseMapper;

    @Autowired
    private ListMinpressUcaseService listMinpressUcaseService;

    @Autowired
    private ListMinpressUcaseQueryService listMinpressUcaseQueryService;

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

    private MockMvc restListMinpressUcaseMockMvc;

    private ListMinpressUcase listMinpressUcase;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ListMinpressUcaseResource listMinpressUcaseResource = new ListMinpressUcaseResource(listMinpressUcaseService, listMinpressUcaseQueryService);
        this.restListMinpressUcaseMockMvc = MockMvcBuilders.standaloneSetup(listMinpressUcaseResource)
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
    public static ListMinpressUcase createEntity(EntityManager em) {
        ListMinpressUcase listMinpressUcase = new ListMinpressUcase()
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME)
            .fullName(DEFAULT_FULL_NAME)
            .isCurrentFlag(DEFAULT_IS_CURRENT_FLAG)
            .description(DEFAULT_DESCRIPTION)
            .dateCreate(DEFAULT_DATE_CREATE)
            .dateEdit(DEFAULT_DATE_EDIT)
            .creator(DEFAULT_CREATOR)
            .editor(DEFAULT_EDITOR);
        return listMinpressUcase;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ListMinpressUcase createUpdatedEntity(EntityManager em) {
        ListMinpressUcase listMinpressUcase = new ListMinpressUcase()
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        return listMinpressUcase;
    }

    @BeforeEach
    public void initTest() {
        listMinpressUcase = createEntity(em);
    }

    @Test
    @Transactional
    public void createListMinpressUcase() throws Exception {
        int databaseSizeBeforeCreate = listMinpressUcaseRepository.findAll().size();

        // Create the ListMinpressUcase
        ListMinpressUcaseDTO listMinpressUcaseDTO = listMinpressUcaseMapper.toDto(listMinpressUcase);
        restListMinpressUcaseMockMvc.perform(post("/api/list-minpress-ucases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listMinpressUcaseDTO)))
            .andExpect(status().isCreated());

        // Validate the ListMinpressUcase in the database
        List<ListMinpressUcase> listMinpressUcaseList = listMinpressUcaseRepository.findAll();
        assertThat(listMinpressUcaseList).hasSize(databaseSizeBeforeCreate + 1);
        ListMinpressUcase testListMinpressUcase = listMinpressUcaseList.get(listMinpressUcaseList.size() - 1);
        assertThat(testListMinpressUcase.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testListMinpressUcase.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testListMinpressUcase.getFullName()).isEqualTo(DEFAULT_FULL_NAME);
        assertThat(testListMinpressUcase.getIsCurrentFlag()).isEqualTo(DEFAULT_IS_CURRENT_FLAG);
        assertThat(testListMinpressUcase.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testListMinpressUcase.getDateCreate()).isEqualTo(DEFAULT_DATE_CREATE);
        assertThat(testListMinpressUcase.getDateEdit()).isEqualTo(DEFAULT_DATE_EDIT);
        assertThat(testListMinpressUcase.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testListMinpressUcase.getEditor()).isEqualTo(DEFAULT_EDITOR);
    }

    @Test
    @Transactional
    public void createListMinpressUcaseWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = listMinpressUcaseRepository.findAll().size();

        // Create the ListMinpressUcase with an existing ID
        listMinpressUcase.setId(1L);
        ListMinpressUcaseDTO listMinpressUcaseDTO = listMinpressUcaseMapper.toDto(listMinpressUcase);

        // An entity with an existing ID cannot be created, so this API call must fail
        restListMinpressUcaseMockMvc.perform(post("/api/list-minpress-ucases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listMinpressUcaseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListMinpressUcase in the database
        List<ListMinpressUcase> listMinpressUcaseList = listMinpressUcaseRepository.findAll();
        assertThat(listMinpressUcaseList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = listMinpressUcaseRepository.findAll().size();
        // set the field null
        listMinpressUcase.setCode(null);

        // Create the ListMinpressUcase, which fails.
        ListMinpressUcaseDTO listMinpressUcaseDTO = listMinpressUcaseMapper.toDto(listMinpressUcase);

        restListMinpressUcaseMockMvc.perform(post("/api/list-minpress-ucases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listMinpressUcaseDTO)))
            .andExpect(status().isBadRequest());

        List<ListMinpressUcase> listMinpressUcaseList = listMinpressUcaseRepository.findAll();
        assertThat(listMinpressUcaseList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listMinpressUcaseRepository.findAll().size();
        // set the field null
        listMinpressUcase.setName(null);

        // Create the ListMinpressUcase, which fails.
        ListMinpressUcaseDTO listMinpressUcaseDTO = listMinpressUcaseMapper.toDto(listMinpressUcase);

        restListMinpressUcaseMockMvc.perform(post("/api/list-minpress-ucases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listMinpressUcaseDTO)))
            .andExpect(status().isBadRequest());

        List<ListMinpressUcase> listMinpressUcaseList = listMinpressUcaseRepository.findAll();
        assertThat(listMinpressUcaseList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFullNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listMinpressUcaseRepository.findAll().size();
        // set the field null
        listMinpressUcase.setFullName(null);

        // Create the ListMinpressUcase, which fails.
        ListMinpressUcaseDTO listMinpressUcaseDTO = listMinpressUcaseMapper.toDto(listMinpressUcase);

        restListMinpressUcaseMockMvc.perform(post("/api/list-minpress-ucases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listMinpressUcaseDTO)))
            .andExpect(status().isBadRequest());

        List<ListMinpressUcase> listMinpressUcaseList = listMinpressUcaseRepository.findAll();
        assertThat(listMinpressUcaseList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsCurrentFlagIsRequired() throws Exception {
        int databaseSizeBeforeTest = listMinpressUcaseRepository.findAll().size();
        // set the field null
        listMinpressUcase.setIsCurrentFlag(null);

        // Create the ListMinpressUcase, which fails.
        ListMinpressUcaseDTO listMinpressUcaseDTO = listMinpressUcaseMapper.toDto(listMinpressUcase);

        restListMinpressUcaseMockMvc.perform(post("/api/list-minpress-ucases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listMinpressUcaseDTO)))
            .andExpect(status().isBadRequest());

        List<ListMinpressUcase> listMinpressUcaseList = listMinpressUcaseRepository.findAll();
        assertThat(listMinpressUcaseList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllListMinpressUcases() throws Exception {
        // Initialize the database
        listMinpressUcaseRepository.saveAndFlush(listMinpressUcase);

        // Get all the listMinpressUcaseList
        restListMinpressUcaseMockMvc.perform(get("/api/list-minpress-ucases?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listMinpressUcase.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].fullName").value(hasItem(DEFAULT_FULL_NAME.toString())))
            .andExpect(jsonPath("$.[*].isCurrentFlag").value(hasItem(DEFAULT_IS_CURRENT_FLAG)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].dateCreate").value(hasItem(DEFAULT_DATE_CREATE.toString())))
            .andExpect(jsonPath("$.[*].dateEdit").value(hasItem(DEFAULT_DATE_EDIT.toString())))
            .andExpect(jsonPath("$.[*].creator").value(hasItem(DEFAULT_CREATOR.toString())))
            .andExpect(jsonPath("$.[*].editor").value(hasItem(DEFAULT_EDITOR.toString())));
    }
    
    @Test
    @Transactional
    public void getListMinpressUcase() throws Exception {
        // Initialize the database
        listMinpressUcaseRepository.saveAndFlush(listMinpressUcase);

        // Get the listMinpressUcase
        restListMinpressUcaseMockMvc.perform(get("/api/list-minpress-ucases/{id}", listMinpressUcase.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(listMinpressUcase.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.fullName").value(DEFAULT_FULL_NAME.toString()))
            .andExpect(jsonPath("$.isCurrentFlag").value(DEFAULT_IS_CURRENT_FLAG))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.dateCreate").value(DEFAULT_DATE_CREATE.toString()))
            .andExpect(jsonPath("$.dateEdit").value(DEFAULT_DATE_EDIT.toString()))
            .andExpect(jsonPath("$.creator").value(DEFAULT_CREATOR.toString()))
            .andExpect(jsonPath("$.editor").value(DEFAULT_EDITOR.toString()));
    }

    @Test
    @Transactional
    public void getAllListMinpressUcasesByCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        listMinpressUcaseRepository.saveAndFlush(listMinpressUcase);

        // Get all the listMinpressUcaseList where code equals to DEFAULT_CODE
        defaultListMinpressUcaseShouldBeFound("code.equals=" + DEFAULT_CODE);

        // Get all the listMinpressUcaseList where code equals to UPDATED_CODE
        defaultListMinpressUcaseShouldNotBeFound("code.equals=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListMinpressUcasesByCodeIsInShouldWork() throws Exception {
        // Initialize the database
        listMinpressUcaseRepository.saveAndFlush(listMinpressUcase);

        // Get all the listMinpressUcaseList where code in DEFAULT_CODE or UPDATED_CODE
        defaultListMinpressUcaseShouldBeFound("code.in=" + DEFAULT_CODE + "," + UPDATED_CODE);

        // Get all the listMinpressUcaseList where code equals to UPDATED_CODE
        defaultListMinpressUcaseShouldNotBeFound("code.in=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListMinpressUcasesByCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        listMinpressUcaseRepository.saveAndFlush(listMinpressUcase);

        // Get all the listMinpressUcaseList where code is not null
        defaultListMinpressUcaseShouldBeFound("code.specified=true");

        // Get all the listMinpressUcaseList where code is null
        defaultListMinpressUcaseShouldNotBeFound("code.specified=false");
    }

    @Test
    @Transactional
    public void getAllListMinpressUcasesByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listMinpressUcaseRepository.saveAndFlush(listMinpressUcase);

        // Get all the listMinpressUcaseList where name equals to DEFAULT_NAME
        defaultListMinpressUcaseShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the listMinpressUcaseList where name equals to UPDATED_NAME
        defaultListMinpressUcaseShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListMinpressUcasesByNameIsInShouldWork() throws Exception {
        // Initialize the database
        listMinpressUcaseRepository.saveAndFlush(listMinpressUcase);

        // Get all the listMinpressUcaseList where name in DEFAULT_NAME or UPDATED_NAME
        defaultListMinpressUcaseShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the listMinpressUcaseList where name equals to UPDATED_NAME
        defaultListMinpressUcaseShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListMinpressUcasesByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listMinpressUcaseRepository.saveAndFlush(listMinpressUcase);

        // Get all the listMinpressUcaseList where name is not null
        defaultListMinpressUcaseShouldBeFound("name.specified=true");

        // Get all the listMinpressUcaseList where name is null
        defaultListMinpressUcaseShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    public void getAllListMinpressUcasesByFullNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listMinpressUcaseRepository.saveAndFlush(listMinpressUcase);

        // Get all the listMinpressUcaseList where fullName equals to DEFAULT_FULL_NAME
        defaultListMinpressUcaseShouldBeFound("fullName.equals=" + DEFAULT_FULL_NAME);

        // Get all the listMinpressUcaseList where fullName equals to UPDATED_FULL_NAME
        defaultListMinpressUcaseShouldNotBeFound("fullName.equals=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListMinpressUcasesByFullNameIsInShouldWork() throws Exception {
        // Initialize the database
        listMinpressUcaseRepository.saveAndFlush(listMinpressUcase);

        // Get all the listMinpressUcaseList where fullName in DEFAULT_FULL_NAME or UPDATED_FULL_NAME
        defaultListMinpressUcaseShouldBeFound("fullName.in=" + DEFAULT_FULL_NAME + "," + UPDATED_FULL_NAME);

        // Get all the listMinpressUcaseList where fullName equals to UPDATED_FULL_NAME
        defaultListMinpressUcaseShouldNotBeFound("fullName.in=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListMinpressUcasesByFullNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listMinpressUcaseRepository.saveAndFlush(listMinpressUcase);

        // Get all the listMinpressUcaseList where fullName is not null
        defaultListMinpressUcaseShouldBeFound("fullName.specified=true");

        // Get all the listMinpressUcaseList where fullName is null
        defaultListMinpressUcaseShouldNotBeFound("fullName.specified=false");
    }

    @Test
    @Transactional
    public void getAllListMinpressUcasesByIsCurrentFlagIsEqualToSomething() throws Exception {
        // Initialize the database
        listMinpressUcaseRepository.saveAndFlush(listMinpressUcase);

        // Get all the listMinpressUcaseList where isCurrentFlag equals to DEFAULT_IS_CURRENT_FLAG
        defaultListMinpressUcaseShouldBeFound("isCurrentFlag.equals=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listMinpressUcaseList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListMinpressUcaseShouldNotBeFound("isCurrentFlag.equals=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListMinpressUcasesByIsCurrentFlagIsInShouldWork() throws Exception {
        // Initialize the database
        listMinpressUcaseRepository.saveAndFlush(listMinpressUcase);

        // Get all the listMinpressUcaseList where isCurrentFlag in DEFAULT_IS_CURRENT_FLAG or UPDATED_IS_CURRENT_FLAG
        defaultListMinpressUcaseShouldBeFound("isCurrentFlag.in=" + DEFAULT_IS_CURRENT_FLAG + "," + UPDATED_IS_CURRENT_FLAG);

        // Get all the listMinpressUcaseList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListMinpressUcaseShouldNotBeFound("isCurrentFlag.in=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListMinpressUcasesByIsCurrentFlagIsNullOrNotNull() throws Exception {
        // Initialize the database
        listMinpressUcaseRepository.saveAndFlush(listMinpressUcase);

        // Get all the listMinpressUcaseList where isCurrentFlag is not null
        defaultListMinpressUcaseShouldBeFound("isCurrentFlag.specified=true");

        // Get all the listMinpressUcaseList where isCurrentFlag is null
        defaultListMinpressUcaseShouldNotBeFound("isCurrentFlag.specified=false");
    }

    @Test
    @Transactional
    public void getAllListMinpressUcasesByIsCurrentFlagIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        listMinpressUcaseRepository.saveAndFlush(listMinpressUcase);

        // Get all the listMinpressUcaseList where isCurrentFlag greater than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListMinpressUcaseShouldBeFound("isCurrentFlag.greaterOrEqualThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listMinpressUcaseList where isCurrentFlag greater than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListMinpressUcaseShouldNotBeFound("isCurrentFlag.greaterOrEqualThan=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListMinpressUcasesByIsCurrentFlagIsLessThanSomething() throws Exception {
        // Initialize the database
        listMinpressUcaseRepository.saveAndFlush(listMinpressUcase);

        // Get all the listMinpressUcaseList where isCurrentFlag less than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListMinpressUcaseShouldNotBeFound("isCurrentFlag.lessThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listMinpressUcaseList where isCurrentFlag less than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListMinpressUcaseShouldBeFound("isCurrentFlag.lessThan=" + UPDATED_IS_CURRENT_FLAG);
    }


    @Test
    @Transactional
    public void getAllListMinpressUcasesByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        listMinpressUcaseRepository.saveAndFlush(listMinpressUcase);

        // Get all the listMinpressUcaseList where description equals to DEFAULT_DESCRIPTION
        defaultListMinpressUcaseShouldBeFound("description.equals=" + DEFAULT_DESCRIPTION);

        // Get all the listMinpressUcaseList where description equals to UPDATED_DESCRIPTION
        defaultListMinpressUcaseShouldNotBeFound("description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListMinpressUcasesByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        listMinpressUcaseRepository.saveAndFlush(listMinpressUcase);

        // Get all the listMinpressUcaseList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
        defaultListMinpressUcaseShouldBeFound("description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION);

        // Get all the listMinpressUcaseList where description equals to UPDATED_DESCRIPTION
        defaultListMinpressUcaseShouldNotBeFound("description.in=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListMinpressUcasesByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        listMinpressUcaseRepository.saveAndFlush(listMinpressUcase);

        // Get all the listMinpressUcaseList where description is not null
        defaultListMinpressUcaseShouldBeFound("description.specified=true");

        // Get all the listMinpressUcaseList where description is null
        defaultListMinpressUcaseShouldNotBeFound("description.specified=false");
    }

    @Test
    @Transactional
    public void getAllListMinpressUcasesByDateCreateIsEqualToSomething() throws Exception {
        // Initialize the database
        listMinpressUcaseRepository.saveAndFlush(listMinpressUcase);

        // Get all the listMinpressUcaseList where dateCreate equals to DEFAULT_DATE_CREATE
        defaultListMinpressUcaseShouldBeFound("dateCreate.equals=" + DEFAULT_DATE_CREATE);

        // Get all the listMinpressUcaseList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListMinpressUcaseShouldNotBeFound("dateCreate.equals=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListMinpressUcasesByDateCreateIsInShouldWork() throws Exception {
        // Initialize the database
        listMinpressUcaseRepository.saveAndFlush(listMinpressUcase);

        // Get all the listMinpressUcaseList where dateCreate in DEFAULT_DATE_CREATE or UPDATED_DATE_CREATE
        defaultListMinpressUcaseShouldBeFound("dateCreate.in=" + DEFAULT_DATE_CREATE + "," + UPDATED_DATE_CREATE);

        // Get all the listMinpressUcaseList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListMinpressUcaseShouldNotBeFound("dateCreate.in=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListMinpressUcasesByDateCreateIsNullOrNotNull() throws Exception {
        // Initialize the database
        listMinpressUcaseRepository.saveAndFlush(listMinpressUcase);

        // Get all the listMinpressUcaseList where dateCreate is not null
        defaultListMinpressUcaseShouldBeFound("dateCreate.specified=true");

        // Get all the listMinpressUcaseList where dateCreate is null
        defaultListMinpressUcaseShouldNotBeFound("dateCreate.specified=false");
    }

    @Test
    @Transactional
    public void getAllListMinpressUcasesByDateEditIsEqualToSomething() throws Exception {
        // Initialize the database
        listMinpressUcaseRepository.saveAndFlush(listMinpressUcase);

        // Get all the listMinpressUcaseList where dateEdit equals to DEFAULT_DATE_EDIT
        defaultListMinpressUcaseShouldBeFound("dateEdit.equals=" + DEFAULT_DATE_EDIT);

        // Get all the listMinpressUcaseList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListMinpressUcaseShouldNotBeFound("dateEdit.equals=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListMinpressUcasesByDateEditIsInShouldWork() throws Exception {
        // Initialize the database
        listMinpressUcaseRepository.saveAndFlush(listMinpressUcase);

        // Get all the listMinpressUcaseList where dateEdit in DEFAULT_DATE_EDIT or UPDATED_DATE_EDIT
        defaultListMinpressUcaseShouldBeFound("dateEdit.in=" + DEFAULT_DATE_EDIT + "," + UPDATED_DATE_EDIT);

        // Get all the listMinpressUcaseList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListMinpressUcaseShouldNotBeFound("dateEdit.in=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListMinpressUcasesByDateEditIsNullOrNotNull() throws Exception {
        // Initialize the database
        listMinpressUcaseRepository.saveAndFlush(listMinpressUcase);

        // Get all the listMinpressUcaseList where dateEdit is not null
        defaultListMinpressUcaseShouldBeFound("dateEdit.specified=true");

        // Get all the listMinpressUcaseList where dateEdit is null
        defaultListMinpressUcaseShouldNotBeFound("dateEdit.specified=false");
    }

    @Test
    @Transactional
    public void getAllListMinpressUcasesByCreatorIsEqualToSomething() throws Exception {
        // Initialize the database
        listMinpressUcaseRepository.saveAndFlush(listMinpressUcase);

        // Get all the listMinpressUcaseList where creator equals to DEFAULT_CREATOR
        defaultListMinpressUcaseShouldBeFound("creator.equals=" + DEFAULT_CREATOR);

        // Get all the listMinpressUcaseList where creator equals to UPDATED_CREATOR
        defaultListMinpressUcaseShouldNotBeFound("creator.equals=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListMinpressUcasesByCreatorIsInShouldWork() throws Exception {
        // Initialize the database
        listMinpressUcaseRepository.saveAndFlush(listMinpressUcase);

        // Get all the listMinpressUcaseList where creator in DEFAULT_CREATOR or UPDATED_CREATOR
        defaultListMinpressUcaseShouldBeFound("creator.in=" + DEFAULT_CREATOR + "," + UPDATED_CREATOR);

        // Get all the listMinpressUcaseList where creator equals to UPDATED_CREATOR
        defaultListMinpressUcaseShouldNotBeFound("creator.in=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListMinpressUcasesByCreatorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listMinpressUcaseRepository.saveAndFlush(listMinpressUcase);

        // Get all the listMinpressUcaseList where creator is not null
        defaultListMinpressUcaseShouldBeFound("creator.specified=true");

        // Get all the listMinpressUcaseList where creator is null
        defaultListMinpressUcaseShouldNotBeFound("creator.specified=false");
    }

    @Test
    @Transactional
    public void getAllListMinpressUcasesByEditorIsEqualToSomething() throws Exception {
        // Initialize the database
        listMinpressUcaseRepository.saveAndFlush(listMinpressUcase);

        // Get all the listMinpressUcaseList where editor equals to DEFAULT_EDITOR
        defaultListMinpressUcaseShouldBeFound("editor.equals=" + DEFAULT_EDITOR);

        // Get all the listMinpressUcaseList where editor equals to UPDATED_EDITOR
        defaultListMinpressUcaseShouldNotBeFound("editor.equals=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListMinpressUcasesByEditorIsInShouldWork() throws Exception {
        // Initialize the database
        listMinpressUcaseRepository.saveAndFlush(listMinpressUcase);

        // Get all the listMinpressUcaseList where editor in DEFAULT_EDITOR or UPDATED_EDITOR
        defaultListMinpressUcaseShouldBeFound("editor.in=" + DEFAULT_EDITOR + "," + UPDATED_EDITOR);

        // Get all the listMinpressUcaseList where editor equals to UPDATED_EDITOR
        defaultListMinpressUcaseShouldNotBeFound("editor.in=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListMinpressUcasesByEditorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listMinpressUcaseRepository.saveAndFlush(listMinpressUcase);

        // Get all the listMinpressUcaseList where editor is not null
        defaultListMinpressUcaseShouldBeFound("editor.specified=true");

        // Get all the listMinpressUcaseList where editor is null
        defaultListMinpressUcaseShouldNotBeFound("editor.specified=false");
    }
    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultListMinpressUcaseShouldBeFound(String filter) throws Exception {
        restListMinpressUcaseMockMvc.perform(get("/api/list-minpress-ucases?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listMinpressUcase.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].fullName").value(hasItem(DEFAULT_FULL_NAME)))
            .andExpect(jsonPath("$.[*].isCurrentFlag").value(hasItem(DEFAULT_IS_CURRENT_FLAG)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].dateCreate").value(hasItem(DEFAULT_DATE_CREATE.toString())))
            .andExpect(jsonPath("$.[*].dateEdit").value(hasItem(DEFAULT_DATE_EDIT.toString())))
            .andExpect(jsonPath("$.[*].creator").value(hasItem(DEFAULT_CREATOR)))
            .andExpect(jsonPath("$.[*].editor").value(hasItem(DEFAULT_EDITOR)));

        // Check, that the count call also returns 1
        restListMinpressUcaseMockMvc.perform(get("/api/list-minpress-ucases/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultListMinpressUcaseShouldNotBeFound(String filter) throws Exception {
        restListMinpressUcaseMockMvc.perform(get("/api/list-minpress-ucases?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restListMinpressUcaseMockMvc.perform(get("/api/list-minpress-ucases/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingListMinpressUcase() throws Exception {
        // Get the listMinpressUcase
        restListMinpressUcaseMockMvc.perform(get("/api/list-minpress-ucases/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateListMinpressUcase() throws Exception {
        // Initialize the database
        listMinpressUcaseRepository.saveAndFlush(listMinpressUcase);

        int databaseSizeBeforeUpdate = listMinpressUcaseRepository.findAll().size();

        // Update the listMinpressUcase
        ListMinpressUcase updatedListMinpressUcase = listMinpressUcaseRepository.findById(listMinpressUcase.getId()).get();
        // Disconnect from session so that the updates on updatedListMinpressUcase are not directly saved in db
        em.detach(updatedListMinpressUcase);
        updatedListMinpressUcase
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        ListMinpressUcaseDTO listMinpressUcaseDTO = listMinpressUcaseMapper.toDto(updatedListMinpressUcase);

        restListMinpressUcaseMockMvc.perform(put("/api/list-minpress-ucases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listMinpressUcaseDTO)))
            .andExpect(status().isOk());

        // Validate the ListMinpressUcase in the database
        List<ListMinpressUcase> listMinpressUcaseList = listMinpressUcaseRepository.findAll();
        assertThat(listMinpressUcaseList).hasSize(databaseSizeBeforeUpdate);
        ListMinpressUcase testListMinpressUcase = listMinpressUcaseList.get(listMinpressUcaseList.size() - 1);
        assertThat(testListMinpressUcase.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testListMinpressUcase.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testListMinpressUcase.getFullName()).isEqualTo(UPDATED_FULL_NAME);
        assertThat(testListMinpressUcase.getIsCurrentFlag()).isEqualTo(UPDATED_IS_CURRENT_FLAG);
        assertThat(testListMinpressUcase.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testListMinpressUcase.getDateCreate()).isEqualTo(UPDATED_DATE_CREATE);
        assertThat(testListMinpressUcase.getDateEdit()).isEqualTo(UPDATED_DATE_EDIT);
        assertThat(testListMinpressUcase.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testListMinpressUcase.getEditor()).isEqualTo(UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void updateNonExistingListMinpressUcase() throws Exception {
        int databaseSizeBeforeUpdate = listMinpressUcaseRepository.findAll().size();

        // Create the ListMinpressUcase
        ListMinpressUcaseDTO listMinpressUcaseDTO = listMinpressUcaseMapper.toDto(listMinpressUcase);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restListMinpressUcaseMockMvc.perform(put("/api/list-minpress-ucases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listMinpressUcaseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListMinpressUcase in the database
        List<ListMinpressUcase> listMinpressUcaseList = listMinpressUcaseRepository.findAll();
        assertThat(listMinpressUcaseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteListMinpressUcase() throws Exception {
        // Initialize the database
        listMinpressUcaseRepository.saveAndFlush(listMinpressUcase);

        int databaseSizeBeforeDelete = listMinpressUcaseRepository.findAll().size();

        // Delete the listMinpressUcase
        restListMinpressUcaseMockMvc.perform(delete("/api/list-minpress-ucases/{id}", listMinpressUcase.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<ListMinpressUcase> listMinpressUcaseList = listMinpressUcaseRepository.findAll();
        assertThat(listMinpressUcaseList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListMinpressUcase.class);
        ListMinpressUcase listMinpressUcase1 = new ListMinpressUcase();
        listMinpressUcase1.setId(1L);
        ListMinpressUcase listMinpressUcase2 = new ListMinpressUcase();
        listMinpressUcase2.setId(listMinpressUcase1.getId());
        assertThat(listMinpressUcase1).isEqualTo(listMinpressUcase2);
        listMinpressUcase2.setId(2L);
        assertThat(listMinpressUcase1).isNotEqualTo(listMinpressUcase2);
        listMinpressUcase1.setId(null);
        assertThat(listMinpressUcase1).isNotEqualTo(listMinpressUcase2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListMinpressUcaseDTO.class);
        ListMinpressUcaseDTO listMinpressUcaseDTO1 = new ListMinpressUcaseDTO();
        listMinpressUcaseDTO1.setId(1L);
        ListMinpressUcaseDTO listMinpressUcaseDTO2 = new ListMinpressUcaseDTO();
        assertThat(listMinpressUcaseDTO1).isNotEqualTo(listMinpressUcaseDTO2);
        listMinpressUcaseDTO2.setId(listMinpressUcaseDTO1.getId());
        assertThat(listMinpressUcaseDTO1).isEqualTo(listMinpressUcaseDTO2);
        listMinpressUcaseDTO2.setId(2L);
        assertThat(listMinpressUcaseDTO1).isNotEqualTo(listMinpressUcaseDTO2);
        listMinpressUcaseDTO1.setId(null);
        assertThat(listMinpressUcaseDTO1).isNotEqualTo(listMinpressUcaseDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(listMinpressUcaseMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(listMinpressUcaseMapper.fromId(null)).isNull();
    }
}
