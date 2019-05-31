package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.ListBoundaryCondUcase;
import io.github.jhipster.application.repository.ListBoundaryCondUcaseRepository;
import io.github.jhipster.application.service.ListBoundaryCondUcaseService;
import io.github.jhipster.application.service.dto.ListBoundaryCondUcaseDTO;
import io.github.jhipster.application.service.mapper.ListBoundaryCondUcaseMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.ListBoundaryCondUcaseCriteria;
import io.github.jhipster.application.service.ListBoundaryCondUcaseQueryService;

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
 * Integration tests for the {@Link ListBoundaryCondUcaseResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class ListBoundaryCondUcaseResourceIT {

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
    private ListBoundaryCondUcaseRepository listBoundaryCondUcaseRepository;

    @Autowired
    private ListBoundaryCondUcaseMapper listBoundaryCondUcaseMapper;

    @Autowired
    private ListBoundaryCondUcaseService listBoundaryCondUcaseService;

    @Autowired
    private ListBoundaryCondUcaseQueryService listBoundaryCondUcaseQueryService;

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

    private MockMvc restListBoundaryCondUcaseMockMvc;

    private ListBoundaryCondUcase listBoundaryCondUcase;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ListBoundaryCondUcaseResource listBoundaryCondUcaseResource = new ListBoundaryCondUcaseResource(listBoundaryCondUcaseService, listBoundaryCondUcaseQueryService);
        this.restListBoundaryCondUcaseMockMvc = MockMvcBuilders.standaloneSetup(listBoundaryCondUcaseResource)
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
    public static ListBoundaryCondUcase createEntity(EntityManager em) {
        ListBoundaryCondUcase listBoundaryCondUcase = new ListBoundaryCondUcase()
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME)
            .fullName(DEFAULT_FULL_NAME)
            .isCurrentFlag(DEFAULT_IS_CURRENT_FLAG)
            .description(DEFAULT_DESCRIPTION)
            .dateCreate(DEFAULT_DATE_CREATE)
            .dateEdit(DEFAULT_DATE_EDIT)
            .creator(DEFAULT_CREATOR)
            .editor(DEFAULT_EDITOR);
        return listBoundaryCondUcase;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ListBoundaryCondUcase createUpdatedEntity(EntityManager em) {
        ListBoundaryCondUcase listBoundaryCondUcase = new ListBoundaryCondUcase()
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        return listBoundaryCondUcase;
    }

    @BeforeEach
    public void initTest() {
        listBoundaryCondUcase = createEntity(em);
    }

    @Test
    @Transactional
    public void createListBoundaryCondUcase() throws Exception {
        int databaseSizeBeforeCreate = listBoundaryCondUcaseRepository.findAll().size();

        // Create the ListBoundaryCondUcase
        ListBoundaryCondUcaseDTO listBoundaryCondUcaseDTO = listBoundaryCondUcaseMapper.toDto(listBoundaryCondUcase);
        restListBoundaryCondUcaseMockMvc.perform(post("/api/list-boundary-cond-ucases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listBoundaryCondUcaseDTO)))
            .andExpect(status().isCreated());

        // Validate the ListBoundaryCondUcase in the database
        List<ListBoundaryCondUcase> listBoundaryCondUcaseList = listBoundaryCondUcaseRepository.findAll();
        assertThat(listBoundaryCondUcaseList).hasSize(databaseSizeBeforeCreate + 1);
        ListBoundaryCondUcase testListBoundaryCondUcase = listBoundaryCondUcaseList.get(listBoundaryCondUcaseList.size() - 1);
        assertThat(testListBoundaryCondUcase.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testListBoundaryCondUcase.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testListBoundaryCondUcase.getFullName()).isEqualTo(DEFAULT_FULL_NAME);
        assertThat(testListBoundaryCondUcase.getIsCurrentFlag()).isEqualTo(DEFAULT_IS_CURRENT_FLAG);
        assertThat(testListBoundaryCondUcase.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testListBoundaryCondUcase.getDateCreate()).isEqualTo(DEFAULT_DATE_CREATE);
        assertThat(testListBoundaryCondUcase.getDateEdit()).isEqualTo(DEFAULT_DATE_EDIT);
        assertThat(testListBoundaryCondUcase.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testListBoundaryCondUcase.getEditor()).isEqualTo(DEFAULT_EDITOR);
    }

    @Test
    @Transactional
    public void createListBoundaryCondUcaseWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = listBoundaryCondUcaseRepository.findAll().size();

        // Create the ListBoundaryCondUcase with an existing ID
        listBoundaryCondUcase.setId(1L);
        ListBoundaryCondUcaseDTO listBoundaryCondUcaseDTO = listBoundaryCondUcaseMapper.toDto(listBoundaryCondUcase);

        // An entity with an existing ID cannot be created, so this API call must fail
        restListBoundaryCondUcaseMockMvc.perform(post("/api/list-boundary-cond-ucases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listBoundaryCondUcaseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListBoundaryCondUcase in the database
        List<ListBoundaryCondUcase> listBoundaryCondUcaseList = listBoundaryCondUcaseRepository.findAll();
        assertThat(listBoundaryCondUcaseList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = listBoundaryCondUcaseRepository.findAll().size();
        // set the field null
        listBoundaryCondUcase.setCode(null);

        // Create the ListBoundaryCondUcase, which fails.
        ListBoundaryCondUcaseDTO listBoundaryCondUcaseDTO = listBoundaryCondUcaseMapper.toDto(listBoundaryCondUcase);

        restListBoundaryCondUcaseMockMvc.perform(post("/api/list-boundary-cond-ucases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listBoundaryCondUcaseDTO)))
            .andExpect(status().isBadRequest());

        List<ListBoundaryCondUcase> listBoundaryCondUcaseList = listBoundaryCondUcaseRepository.findAll();
        assertThat(listBoundaryCondUcaseList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listBoundaryCondUcaseRepository.findAll().size();
        // set the field null
        listBoundaryCondUcase.setName(null);

        // Create the ListBoundaryCondUcase, which fails.
        ListBoundaryCondUcaseDTO listBoundaryCondUcaseDTO = listBoundaryCondUcaseMapper.toDto(listBoundaryCondUcase);

        restListBoundaryCondUcaseMockMvc.perform(post("/api/list-boundary-cond-ucases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listBoundaryCondUcaseDTO)))
            .andExpect(status().isBadRequest());

        List<ListBoundaryCondUcase> listBoundaryCondUcaseList = listBoundaryCondUcaseRepository.findAll();
        assertThat(listBoundaryCondUcaseList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFullNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listBoundaryCondUcaseRepository.findAll().size();
        // set the field null
        listBoundaryCondUcase.setFullName(null);

        // Create the ListBoundaryCondUcase, which fails.
        ListBoundaryCondUcaseDTO listBoundaryCondUcaseDTO = listBoundaryCondUcaseMapper.toDto(listBoundaryCondUcase);

        restListBoundaryCondUcaseMockMvc.perform(post("/api/list-boundary-cond-ucases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listBoundaryCondUcaseDTO)))
            .andExpect(status().isBadRequest());

        List<ListBoundaryCondUcase> listBoundaryCondUcaseList = listBoundaryCondUcaseRepository.findAll();
        assertThat(listBoundaryCondUcaseList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsCurrentFlagIsRequired() throws Exception {
        int databaseSizeBeforeTest = listBoundaryCondUcaseRepository.findAll().size();
        // set the field null
        listBoundaryCondUcase.setIsCurrentFlag(null);

        // Create the ListBoundaryCondUcase, which fails.
        ListBoundaryCondUcaseDTO listBoundaryCondUcaseDTO = listBoundaryCondUcaseMapper.toDto(listBoundaryCondUcase);

        restListBoundaryCondUcaseMockMvc.perform(post("/api/list-boundary-cond-ucases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listBoundaryCondUcaseDTO)))
            .andExpect(status().isBadRequest());

        List<ListBoundaryCondUcase> listBoundaryCondUcaseList = listBoundaryCondUcaseRepository.findAll();
        assertThat(listBoundaryCondUcaseList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllListBoundaryCondUcases() throws Exception {
        // Initialize the database
        listBoundaryCondUcaseRepository.saveAndFlush(listBoundaryCondUcase);

        // Get all the listBoundaryCondUcaseList
        restListBoundaryCondUcaseMockMvc.perform(get("/api/list-boundary-cond-ucases?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listBoundaryCondUcase.getId().intValue())))
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
    public void getListBoundaryCondUcase() throws Exception {
        // Initialize the database
        listBoundaryCondUcaseRepository.saveAndFlush(listBoundaryCondUcase);

        // Get the listBoundaryCondUcase
        restListBoundaryCondUcaseMockMvc.perform(get("/api/list-boundary-cond-ucases/{id}", listBoundaryCondUcase.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(listBoundaryCondUcase.getId().intValue()))
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
    public void getAllListBoundaryCondUcasesByCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        listBoundaryCondUcaseRepository.saveAndFlush(listBoundaryCondUcase);

        // Get all the listBoundaryCondUcaseList where code equals to DEFAULT_CODE
        defaultListBoundaryCondUcaseShouldBeFound("code.equals=" + DEFAULT_CODE);

        // Get all the listBoundaryCondUcaseList where code equals to UPDATED_CODE
        defaultListBoundaryCondUcaseShouldNotBeFound("code.equals=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListBoundaryCondUcasesByCodeIsInShouldWork() throws Exception {
        // Initialize the database
        listBoundaryCondUcaseRepository.saveAndFlush(listBoundaryCondUcase);

        // Get all the listBoundaryCondUcaseList where code in DEFAULT_CODE or UPDATED_CODE
        defaultListBoundaryCondUcaseShouldBeFound("code.in=" + DEFAULT_CODE + "," + UPDATED_CODE);

        // Get all the listBoundaryCondUcaseList where code equals to UPDATED_CODE
        defaultListBoundaryCondUcaseShouldNotBeFound("code.in=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListBoundaryCondUcasesByCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        listBoundaryCondUcaseRepository.saveAndFlush(listBoundaryCondUcase);

        // Get all the listBoundaryCondUcaseList where code is not null
        defaultListBoundaryCondUcaseShouldBeFound("code.specified=true");

        // Get all the listBoundaryCondUcaseList where code is null
        defaultListBoundaryCondUcaseShouldNotBeFound("code.specified=false");
    }

    @Test
    @Transactional
    public void getAllListBoundaryCondUcasesByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listBoundaryCondUcaseRepository.saveAndFlush(listBoundaryCondUcase);

        // Get all the listBoundaryCondUcaseList where name equals to DEFAULT_NAME
        defaultListBoundaryCondUcaseShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the listBoundaryCondUcaseList where name equals to UPDATED_NAME
        defaultListBoundaryCondUcaseShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListBoundaryCondUcasesByNameIsInShouldWork() throws Exception {
        // Initialize the database
        listBoundaryCondUcaseRepository.saveAndFlush(listBoundaryCondUcase);

        // Get all the listBoundaryCondUcaseList where name in DEFAULT_NAME or UPDATED_NAME
        defaultListBoundaryCondUcaseShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the listBoundaryCondUcaseList where name equals to UPDATED_NAME
        defaultListBoundaryCondUcaseShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListBoundaryCondUcasesByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listBoundaryCondUcaseRepository.saveAndFlush(listBoundaryCondUcase);

        // Get all the listBoundaryCondUcaseList where name is not null
        defaultListBoundaryCondUcaseShouldBeFound("name.specified=true");

        // Get all the listBoundaryCondUcaseList where name is null
        defaultListBoundaryCondUcaseShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    public void getAllListBoundaryCondUcasesByFullNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listBoundaryCondUcaseRepository.saveAndFlush(listBoundaryCondUcase);

        // Get all the listBoundaryCondUcaseList where fullName equals to DEFAULT_FULL_NAME
        defaultListBoundaryCondUcaseShouldBeFound("fullName.equals=" + DEFAULT_FULL_NAME);

        // Get all the listBoundaryCondUcaseList where fullName equals to UPDATED_FULL_NAME
        defaultListBoundaryCondUcaseShouldNotBeFound("fullName.equals=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListBoundaryCondUcasesByFullNameIsInShouldWork() throws Exception {
        // Initialize the database
        listBoundaryCondUcaseRepository.saveAndFlush(listBoundaryCondUcase);

        // Get all the listBoundaryCondUcaseList where fullName in DEFAULT_FULL_NAME or UPDATED_FULL_NAME
        defaultListBoundaryCondUcaseShouldBeFound("fullName.in=" + DEFAULT_FULL_NAME + "," + UPDATED_FULL_NAME);

        // Get all the listBoundaryCondUcaseList where fullName equals to UPDATED_FULL_NAME
        defaultListBoundaryCondUcaseShouldNotBeFound("fullName.in=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListBoundaryCondUcasesByFullNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listBoundaryCondUcaseRepository.saveAndFlush(listBoundaryCondUcase);

        // Get all the listBoundaryCondUcaseList where fullName is not null
        defaultListBoundaryCondUcaseShouldBeFound("fullName.specified=true");

        // Get all the listBoundaryCondUcaseList where fullName is null
        defaultListBoundaryCondUcaseShouldNotBeFound("fullName.specified=false");
    }

    @Test
    @Transactional
    public void getAllListBoundaryCondUcasesByIsCurrentFlagIsEqualToSomething() throws Exception {
        // Initialize the database
        listBoundaryCondUcaseRepository.saveAndFlush(listBoundaryCondUcase);

        // Get all the listBoundaryCondUcaseList where isCurrentFlag equals to DEFAULT_IS_CURRENT_FLAG
        defaultListBoundaryCondUcaseShouldBeFound("isCurrentFlag.equals=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listBoundaryCondUcaseList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListBoundaryCondUcaseShouldNotBeFound("isCurrentFlag.equals=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListBoundaryCondUcasesByIsCurrentFlagIsInShouldWork() throws Exception {
        // Initialize the database
        listBoundaryCondUcaseRepository.saveAndFlush(listBoundaryCondUcase);

        // Get all the listBoundaryCondUcaseList where isCurrentFlag in DEFAULT_IS_CURRENT_FLAG or UPDATED_IS_CURRENT_FLAG
        defaultListBoundaryCondUcaseShouldBeFound("isCurrentFlag.in=" + DEFAULT_IS_CURRENT_FLAG + "," + UPDATED_IS_CURRENT_FLAG);

        // Get all the listBoundaryCondUcaseList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListBoundaryCondUcaseShouldNotBeFound("isCurrentFlag.in=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListBoundaryCondUcasesByIsCurrentFlagIsNullOrNotNull() throws Exception {
        // Initialize the database
        listBoundaryCondUcaseRepository.saveAndFlush(listBoundaryCondUcase);

        // Get all the listBoundaryCondUcaseList where isCurrentFlag is not null
        defaultListBoundaryCondUcaseShouldBeFound("isCurrentFlag.specified=true");

        // Get all the listBoundaryCondUcaseList where isCurrentFlag is null
        defaultListBoundaryCondUcaseShouldNotBeFound("isCurrentFlag.specified=false");
    }

    @Test
    @Transactional
    public void getAllListBoundaryCondUcasesByIsCurrentFlagIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        listBoundaryCondUcaseRepository.saveAndFlush(listBoundaryCondUcase);

        // Get all the listBoundaryCondUcaseList where isCurrentFlag greater than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListBoundaryCondUcaseShouldBeFound("isCurrentFlag.greaterOrEqualThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listBoundaryCondUcaseList where isCurrentFlag greater than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListBoundaryCondUcaseShouldNotBeFound("isCurrentFlag.greaterOrEqualThan=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListBoundaryCondUcasesByIsCurrentFlagIsLessThanSomething() throws Exception {
        // Initialize the database
        listBoundaryCondUcaseRepository.saveAndFlush(listBoundaryCondUcase);

        // Get all the listBoundaryCondUcaseList where isCurrentFlag less than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListBoundaryCondUcaseShouldNotBeFound("isCurrentFlag.lessThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listBoundaryCondUcaseList where isCurrentFlag less than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListBoundaryCondUcaseShouldBeFound("isCurrentFlag.lessThan=" + UPDATED_IS_CURRENT_FLAG);
    }


    @Test
    @Transactional
    public void getAllListBoundaryCondUcasesByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        listBoundaryCondUcaseRepository.saveAndFlush(listBoundaryCondUcase);

        // Get all the listBoundaryCondUcaseList where description equals to DEFAULT_DESCRIPTION
        defaultListBoundaryCondUcaseShouldBeFound("description.equals=" + DEFAULT_DESCRIPTION);

        // Get all the listBoundaryCondUcaseList where description equals to UPDATED_DESCRIPTION
        defaultListBoundaryCondUcaseShouldNotBeFound("description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListBoundaryCondUcasesByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        listBoundaryCondUcaseRepository.saveAndFlush(listBoundaryCondUcase);

        // Get all the listBoundaryCondUcaseList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
        defaultListBoundaryCondUcaseShouldBeFound("description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION);

        // Get all the listBoundaryCondUcaseList where description equals to UPDATED_DESCRIPTION
        defaultListBoundaryCondUcaseShouldNotBeFound("description.in=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListBoundaryCondUcasesByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        listBoundaryCondUcaseRepository.saveAndFlush(listBoundaryCondUcase);

        // Get all the listBoundaryCondUcaseList where description is not null
        defaultListBoundaryCondUcaseShouldBeFound("description.specified=true");

        // Get all the listBoundaryCondUcaseList where description is null
        defaultListBoundaryCondUcaseShouldNotBeFound("description.specified=false");
    }

    @Test
    @Transactional
    public void getAllListBoundaryCondUcasesByDateCreateIsEqualToSomething() throws Exception {
        // Initialize the database
        listBoundaryCondUcaseRepository.saveAndFlush(listBoundaryCondUcase);

        // Get all the listBoundaryCondUcaseList where dateCreate equals to DEFAULT_DATE_CREATE
        defaultListBoundaryCondUcaseShouldBeFound("dateCreate.equals=" + DEFAULT_DATE_CREATE);

        // Get all the listBoundaryCondUcaseList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListBoundaryCondUcaseShouldNotBeFound("dateCreate.equals=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListBoundaryCondUcasesByDateCreateIsInShouldWork() throws Exception {
        // Initialize the database
        listBoundaryCondUcaseRepository.saveAndFlush(listBoundaryCondUcase);

        // Get all the listBoundaryCondUcaseList where dateCreate in DEFAULT_DATE_CREATE or UPDATED_DATE_CREATE
        defaultListBoundaryCondUcaseShouldBeFound("dateCreate.in=" + DEFAULT_DATE_CREATE + "," + UPDATED_DATE_CREATE);

        // Get all the listBoundaryCondUcaseList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListBoundaryCondUcaseShouldNotBeFound("dateCreate.in=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListBoundaryCondUcasesByDateCreateIsNullOrNotNull() throws Exception {
        // Initialize the database
        listBoundaryCondUcaseRepository.saveAndFlush(listBoundaryCondUcase);

        // Get all the listBoundaryCondUcaseList where dateCreate is not null
        defaultListBoundaryCondUcaseShouldBeFound("dateCreate.specified=true");

        // Get all the listBoundaryCondUcaseList where dateCreate is null
        defaultListBoundaryCondUcaseShouldNotBeFound("dateCreate.specified=false");
    }

    @Test
    @Transactional
    public void getAllListBoundaryCondUcasesByDateEditIsEqualToSomething() throws Exception {
        // Initialize the database
        listBoundaryCondUcaseRepository.saveAndFlush(listBoundaryCondUcase);

        // Get all the listBoundaryCondUcaseList where dateEdit equals to DEFAULT_DATE_EDIT
        defaultListBoundaryCondUcaseShouldBeFound("dateEdit.equals=" + DEFAULT_DATE_EDIT);

        // Get all the listBoundaryCondUcaseList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListBoundaryCondUcaseShouldNotBeFound("dateEdit.equals=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListBoundaryCondUcasesByDateEditIsInShouldWork() throws Exception {
        // Initialize the database
        listBoundaryCondUcaseRepository.saveAndFlush(listBoundaryCondUcase);

        // Get all the listBoundaryCondUcaseList where dateEdit in DEFAULT_DATE_EDIT or UPDATED_DATE_EDIT
        defaultListBoundaryCondUcaseShouldBeFound("dateEdit.in=" + DEFAULT_DATE_EDIT + "," + UPDATED_DATE_EDIT);

        // Get all the listBoundaryCondUcaseList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListBoundaryCondUcaseShouldNotBeFound("dateEdit.in=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListBoundaryCondUcasesByDateEditIsNullOrNotNull() throws Exception {
        // Initialize the database
        listBoundaryCondUcaseRepository.saveAndFlush(listBoundaryCondUcase);

        // Get all the listBoundaryCondUcaseList where dateEdit is not null
        defaultListBoundaryCondUcaseShouldBeFound("dateEdit.specified=true");

        // Get all the listBoundaryCondUcaseList where dateEdit is null
        defaultListBoundaryCondUcaseShouldNotBeFound("dateEdit.specified=false");
    }

    @Test
    @Transactional
    public void getAllListBoundaryCondUcasesByCreatorIsEqualToSomething() throws Exception {
        // Initialize the database
        listBoundaryCondUcaseRepository.saveAndFlush(listBoundaryCondUcase);

        // Get all the listBoundaryCondUcaseList where creator equals to DEFAULT_CREATOR
        defaultListBoundaryCondUcaseShouldBeFound("creator.equals=" + DEFAULT_CREATOR);

        // Get all the listBoundaryCondUcaseList where creator equals to UPDATED_CREATOR
        defaultListBoundaryCondUcaseShouldNotBeFound("creator.equals=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListBoundaryCondUcasesByCreatorIsInShouldWork() throws Exception {
        // Initialize the database
        listBoundaryCondUcaseRepository.saveAndFlush(listBoundaryCondUcase);

        // Get all the listBoundaryCondUcaseList where creator in DEFAULT_CREATOR or UPDATED_CREATOR
        defaultListBoundaryCondUcaseShouldBeFound("creator.in=" + DEFAULT_CREATOR + "," + UPDATED_CREATOR);

        // Get all the listBoundaryCondUcaseList where creator equals to UPDATED_CREATOR
        defaultListBoundaryCondUcaseShouldNotBeFound("creator.in=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListBoundaryCondUcasesByCreatorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listBoundaryCondUcaseRepository.saveAndFlush(listBoundaryCondUcase);

        // Get all the listBoundaryCondUcaseList where creator is not null
        defaultListBoundaryCondUcaseShouldBeFound("creator.specified=true");

        // Get all the listBoundaryCondUcaseList where creator is null
        defaultListBoundaryCondUcaseShouldNotBeFound("creator.specified=false");
    }

    @Test
    @Transactional
    public void getAllListBoundaryCondUcasesByEditorIsEqualToSomething() throws Exception {
        // Initialize the database
        listBoundaryCondUcaseRepository.saveAndFlush(listBoundaryCondUcase);

        // Get all the listBoundaryCondUcaseList where editor equals to DEFAULT_EDITOR
        defaultListBoundaryCondUcaseShouldBeFound("editor.equals=" + DEFAULT_EDITOR);

        // Get all the listBoundaryCondUcaseList where editor equals to UPDATED_EDITOR
        defaultListBoundaryCondUcaseShouldNotBeFound("editor.equals=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListBoundaryCondUcasesByEditorIsInShouldWork() throws Exception {
        // Initialize the database
        listBoundaryCondUcaseRepository.saveAndFlush(listBoundaryCondUcase);

        // Get all the listBoundaryCondUcaseList where editor in DEFAULT_EDITOR or UPDATED_EDITOR
        defaultListBoundaryCondUcaseShouldBeFound("editor.in=" + DEFAULT_EDITOR + "," + UPDATED_EDITOR);

        // Get all the listBoundaryCondUcaseList where editor equals to UPDATED_EDITOR
        defaultListBoundaryCondUcaseShouldNotBeFound("editor.in=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListBoundaryCondUcasesByEditorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listBoundaryCondUcaseRepository.saveAndFlush(listBoundaryCondUcase);

        // Get all the listBoundaryCondUcaseList where editor is not null
        defaultListBoundaryCondUcaseShouldBeFound("editor.specified=true");

        // Get all the listBoundaryCondUcaseList where editor is null
        defaultListBoundaryCondUcaseShouldNotBeFound("editor.specified=false");
    }
    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultListBoundaryCondUcaseShouldBeFound(String filter) throws Exception {
        restListBoundaryCondUcaseMockMvc.perform(get("/api/list-boundary-cond-ucases?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listBoundaryCondUcase.getId().intValue())))
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
        restListBoundaryCondUcaseMockMvc.perform(get("/api/list-boundary-cond-ucases/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultListBoundaryCondUcaseShouldNotBeFound(String filter) throws Exception {
        restListBoundaryCondUcaseMockMvc.perform(get("/api/list-boundary-cond-ucases?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restListBoundaryCondUcaseMockMvc.perform(get("/api/list-boundary-cond-ucases/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingListBoundaryCondUcase() throws Exception {
        // Get the listBoundaryCondUcase
        restListBoundaryCondUcaseMockMvc.perform(get("/api/list-boundary-cond-ucases/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateListBoundaryCondUcase() throws Exception {
        // Initialize the database
        listBoundaryCondUcaseRepository.saveAndFlush(listBoundaryCondUcase);

        int databaseSizeBeforeUpdate = listBoundaryCondUcaseRepository.findAll().size();

        // Update the listBoundaryCondUcase
        ListBoundaryCondUcase updatedListBoundaryCondUcase = listBoundaryCondUcaseRepository.findById(listBoundaryCondUcase.getId()).get();
        // Disconnect from session so that the updates on updatedListBoundaryCondUcase are not directly saved in db
        em.detach(updatedListBoundaryCondUcase);
        updatedListBoundaryCondUcase
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        ListBoundaryCondUcaseDTO listBoundaryCondUcaseDTO = listBoundaryCondUcaseMapper.toDto(updatedListBoundaryCondUcase);

        restListBoundaryCondUcaseMockMvc.perform(put("/api/list-boundary-cond-ucases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listBoundaryCondUcaseDTO)))
            .andExpect(status().isOk());

        // Validate the ListBoundaryCondUcase in the database
        List<ListBoundaryCondUcase> listBoundaryCondUcaseList = listBoundaryCondUcaseRepository.findAll();
        assertThat(listBoundaryCondUcaseList).hasSize(databaseSizeBeforeUpdate);
        ListBoundaryCondUcase testListBoundaryCondUcase = listBoundaryCondUcaseList.get(listBoundaryCondUcaseList.size() - 1);
        assertThat(testListBoundaryCondUcase.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testListBoundaryCondUcase.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testListBoundaryCondUcase.getFullName()).isEqualTo(UPDATED_FULL_NAME);
        assertThat(testListBoundaryCondUcase.getIsCurrentFlag()).isEqualTo(UPDATED_IS_CURRENT_FLAG);
        assertThat(testListBoundaryCondUcase.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testListBoundaryCondUcase.getDateCreate()).isEqualTo(UPDATED_DATE_CREATE);
        assertThat(testListBoundaryCondUcase.getDateEdit()).isEqualTo(UPDATED_DATE_EDIT);
        assertThat(testListBoundaryCondUcase.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testListBoundaryCondUcase.getEditor()).isEqualTo(UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void updateNonExistingListBoundaryCondUcase() throws Exception {
        int databaseSizeBeforeUpdate = listBoundaryCondUcaseRepository.findAll().size();

        // Create the ListBoundaryCondUcase
        ListBoundaryCondUcaseDTO listBoundaryCondUcaseDTO = listBoundaryCondUcaseMapper.toDto(listBoundaryCondUcase);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restListBoundaryCondUcaseMockMvc.perform(put("/api/list-boundary-cond-ucases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listBoundaryCondUcaseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListBoundaryCondUcase in the database
        List<ListBoundaryCondUcase> listBoundaryCondUcaseList = listBoundaryCondUcaseRepository.findAll();
        assertThat(listBoundaryCondUcaseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteListBoundaryCondUcase() throws Exception {
        // Initialize the database
        listBoundaryCondUcaseRepository.saveAndFlush(listBoundaryCondUcase);

        int databaseSizeBeforeDelete = listBoundaryCondUcaseRepository.findAll().size();

        // Delete the listBoundaryCondUcase
        restListBoundaryCondUcaseMockMvc.perform(delete("/api/list-boundary-cond-ucases/{id}", listBoundaryCondUcase.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<ListBoundaryCondUcase> listBoundaryCondUcaseList = listBoundaryCondUcaseRepository.findAll();
        assertThat(listBoundaryCondUcaseList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListBoundaryCondUcase.class);
        ListBoundaryCondUcase listBoundaryCondUcase1 = new ListBoundaryCondUcase();
        listBoundaryCondUcase1.setId(1L);
        ListBoundaryCondUcase listBoundaryCondUcase2 = new ListBoundaryCondUcase();
        listBoundaryCondUcase2.setId(listBoundaryCondUcase1.getId());
        assertThat(listBoundaryCondUcase1).isEqualTo(listBoundaryCondUcase2);
        listBoundaryCondUcase2.setId(2L);
        assertThat(listBoundaryCondUcase1).isNotEqualTo(listBoundaryCondUcase2);
        listBoundaryCondUcase1.setId(null);
        assertThat(listBoundaryCondUcase1).isNotEqualTo(listBoundaryCondUcase2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListBoundaryCondUcaseDTO.class);
        ListBoundaryCondUcaseDTO listBoundaryCondUcaseDTO1 = new ListBoundaryCondUcaseDTO();
        listBoundaryCondUcaseDTO1.setId(1L);
        ListBoundaryCondUcaseDTO listBoundaryCondUcaseDTO2 = new ListBoundaryCondUcaseDTO();
        assertThat(listBoundaryCondUcaseDTO1).isNotEqualTo(listBoundaryCondUcaseDTO2);
        listBoundaryCondUcaseDTO2.setId(listBoundaryCondUcaseDTO1.getId());
        assertThat(listBoundaryCondUcaseDTO1).isEqualTo(listBoundaryCondUcaseDTO2);
        listBoundaryCondUcaseDTO2.setId(2L);
        assertThat(listBoundaryCondUcaseDTO1).isNotEqualTo(listBoundaryCondUcaseDTO2);
        listBoundaryCondUcaseDTO1.setId(null);
        assertThat(listBoundaryCondUcaseDTO1).isNotEqualTo(listBoundaryCondUcaseDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(listBoundaryCondUcaseMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(listBoundaryCondUcaseMapper.fromId(null)).isNull();
    }
}
