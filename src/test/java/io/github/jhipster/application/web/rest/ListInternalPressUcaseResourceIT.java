package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.ListInternalPressUcase;
import io.github.jhipster.application.repository.ListInternalPressUcaseRepository;
import io.github.jhipster.application.service.ListInternalPressUcaseService;
import io.github.jhipster.application.service.dto.ListInternalPressUcaseDTO;
import io.github.jhipster.application.service.mapper.ListInternalPressUcaseMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.ListInternalPressUcaseCriteria;
import io.github.jhipster.application.service.ListInternalPressUcaseQueryService;

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
 * Integration tests for the {@Link ListInternalPressUcaseResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class ListInternalPressUcaseResourceIT {

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
    private ListInternalPressUcaseRepository listInternalPressUcaseRepository;

    @Autowired
    private ListInternalPressUcaseMapper listInternalPressUcaseMapper;

    @Autowired
    private ListInternalPressUcaseService listInternalPressUcaseService;

    @Autowired
    private ListInternalPressUcaseQueryService listInternalPressUcaseQueryService;

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

    private MockMvc restListInternalPressUcaseMockMvc;

    private ListInternalPressUcase listInternalPressUcase;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ListInternalPressUcaseResource listInternalPressUcaseResource = new ListInternalPressUcaseResource(listInternalPressUcaseService, listInternalPressUcaseQueryService);
        this.restListInternalPressUcaseMockMvc = MockMvcBuilders.standaloneSetup(listInternalPressUcaseResource)
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
    public static ListInternalPressUcase createEntity(EntityManager em) {
        ListInternalPressUcase listInternalPressUcase = new ListInternalPressUcase()
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME)
            .fullName(DEFAULT_FULL_NAME)
            .isCurrentFlag(DEFAULT_IS_CURRENT_FLAG)
            .description(DEFAULT_DESCRIPTION)
            .dateCreate(DEFAULT_DATE_CREATE)
            .dateEdit(DEFAULT_DATE_EDIT)
            .creator(DEFAULT_CREATOR)
            .editor(DEFAULT_EDITOR);
        return listInternalPressUcase;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ListInternalPressUcase createUpdatedEntity(EntityManager em) {
        ListInternalPressUcase listInternalPressUcase = new ListInternalPressUcase()
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        return listInternalPressUcase;
    }

    @BeforeEach
    public void initTest() {
        listInternalPressUcase = createEntity(em);
    }

    @Test
    @Transactional
    public void createListInternalPressUcase() throws Exception {
        int databaseSizeBeforeCreate = listInternalPressUcaseRepository.findAll().size();

        // Create the ListInternalPressUcase
        ListInternalPressUcaseDTO listInternalPressUcaseDTO = listInternalPressUcaseMapper.toDto(listInternalPressUcase);
        restListInternalPressUcaseMockMvc.perform(post("/api/list-internal-press-ucases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listInternalPressUcaseDTO)))
            .andExpect(status().isCreated());

        // Validate the ListInternalPressUcase in the database
        List<ListInternalPressUcase> listInternalPressUcaseList = listInternalPressUcaseRepository.findAll();
        assertThat(listInternalPressUcaseList).hasSize(databaseSizeBeforeCreate + 1);
        ListInternalPressUcase testListInternalPressUcase = listInternalPressUcaseList.get(listInternalPressUcaseList.size() - 1);
        assertThat(testListInternalPressUcase.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testListInternalPressUcase.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testListInternalPressUcase.getFullName()).isEqualTo(DEFAULT_FULL_NAME);
        assertThat(testListInternalPressUcase.getIsCurrentFlag()).isEqualTo(DEFAULT_IS_CURRENT_FLAG);
        assertThat(testListInternalPressUcase.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testListInternalPressUcase.getDateCreate()).isEqualTo(DEFAULT_DATE_CREATE);
        assertThat(testListInternalPressUcase.getDateEdit()).isEqualTo(DEFAULT_DATE_EDIT);
        assertThat(testListInternalPressUcase.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testListInternalPressUcase.getEditor()).isEqualTo(DEFAULT_EDITOR);
    }

    @Test
    @Transactional
    public void createListInternalPressUcaseWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = listInternalPressUcaseRepository.findAll().size();

        // Create the ListInternalPressUcase with an existing ID
        listInternalPressUcase.setId(1L);
        ListInternalPressUcaseDTO listInternalPressUcaseDTO = listInternalPressUcaseMapper.toDto(listInternalPressUcase);

        // An entity with an existing ID cannot be created, so this API call must fail
        restListInternalPressUcaseMockMvc.perform(post("/api/list-internal-press-ucases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listInternalPressUcaseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListInternalPressUcase in the database
        List<ListInternalPressUcase> listInternalPressUcaseList = listInternalPressUcaseRepository.findAll();
        assertThat(listInternalPressUcaseList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = listInternalPressUcaseRepository.findAll().size();
        // set the field null
        listInternalPressUcase.setCode(null);

        // Create the ListInternalPressUcase, which fails.
        ListInternalPressUcaseDTO listInternalPressUcaseDTO = listInternalPressUcaseMapper.toDto(listInternalPressUcase);

        restListInternalPressUcaseMockMvc.perform(post("/api/list-internal-press-ucases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listInternalPressUcaseDTO)))
            .andExpect(status().isBadRequest());

        List<ListInternalPressUcase> listInternalPressUcaseList = listInternalPressUcaseRepository.findAll();
        assertThat(listInternalPressUcaseList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listInternalPressUcaseRepository.findAll().size();
        // set the field null
        listInternalPressUcase.setName(null);

        // Create the ListInternalPressUcase, which fails.
        ListInternalPressUcaseDTO listInternalPressUcaseDTO = listInternalPressUcaseMapper.toDto(listInternalPressUcase);

        restListInternalPressUcaseMockMvc.perform(post("/api/list-internal-press-ucases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listInternalPressUcaseDTO)))
            .andExpect(status().isBadRequest());

        List<ListInternalPressUcase> listInternalPressUcaseList = listInternalPressUcaseRepository.findAll();
        assertThat(listInternalPressUcaseList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFullNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listInternalPressUcaseRepository.findAll().size();
        // set the field null
        listInternalPressUcase.setFullName(null);

        // Create the ListInternalPressUcase, which fails.
        ListInternalPressUcaseDTO listInternalPressUcaseDTO = listInternalPressUcaseMapper.toDto(listInternalPressUcase);

        restListInternalPressUcaseMockMvc.perform(post("/api/list-internal-press-ucases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listInternalPressUcaseDTO)))
            .andExpect(status().isBadRequest());

        List<ListInternalPressUcase> listInternalPressUcaseList = listInternalPressUcaseRepository.findAll();
        assertThat(listInternalPressUcaseList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsCurrentFlagIsRequired() throws Exception {
        int databaseSizeBeforeTest = listInternalPressUcaseRepository.findAll().size();
        // set the field null
        listInternalPressUcase.setIsCurrentFlag(null);

        // Create the ListInternalPressUcase, which fails.
        ListInternalPressUcaseDTO listInternalPressUcaseDTO = listInternalPressUcaseMapper.toDto(listInternalPressUcase);

        restListInternalPressUcaseMockMvc.perform(post("/api/list-internal-press-ucases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listInternalPressUcaseDTO)))
            .andExpect(status().isBadRequest());

        List<ListInternalPressUcase> listInternalPressUcaseList = listInternalPressUcaseRepository.findAll();
        assertThat(listInternalPressUcaseList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllListInternalPressUcases() throws Exception {
        // Initialize the database
        listInternalPressUcaseRepository.saveAndFlush(listInternalPressUcase);

        // Get all the listInternalPressUcaseList
        restListInternalPressUcaseMockMvc.perform(get("/api/list-internal-press-ucases?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listInternalPressUcase.getId().intValue())))
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
    public void getListInternalPressUcase() throws Exception {
        // Initialize the database
        listInternalPressUcaseRepository.saveAndFlush(listInternalPressUcase);

        // Get the listInternalPressUcase
        restListInternalPressUcaseMockMvc.perform(get("/api/list-internal-press-ucases/{id}", listInternalPressUcase.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(listInternalPressUcase.getId().intValue()))
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
    public void getAllListInternalPressUcasesByCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        listInternalPressUcaseRepository.saveAndFlush(listInternalPressUcase);

        // Get all the listInternalPressUcaseList where code equals to DEFAULT_CODE
        defaultListInternalPressUcaseShouldBeFound("code.equals=" + DEFAULT_CODE);

        // Get all the listInternalPressUcaseList where code equals to UPDATED_CODE
        defaultListInternalPressUcaseShouldNotBeFound("code.equals=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListInternalPressUcasesByCodeIsInShouldWork() throws Exception {
        // Initialize the database
        listInternalPressUcaseRepository.saveAndFlush(listInternalPressUcase);

        // Get all the listInternalPressUcaseList where code in DEFAULT_CODE or UPDATED_CODE
        defaultListInternalPressUcaseShouldBeFound("code.in=" + DEFAULT_CODE + "," + UPDATED_CODE);

        // Get all the listInternalPressUcaseList where code equals to UPDATED_CODE
        defaultListInternalPressUcaseShouldNotBeFound("code.in=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListInternalPressUcasesByCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        listInternalPressUcaseRepository.saveAndFlush(listInternalPressUcase);

        // Get all the listInternalPressUcaseList where code is not null
        defaultListInternalPressUcaseShouldBeFound("code.specified=true");

        // Get all the listInternalPressUcaseList where code is null
        defaultListInternalPressUcaseShouldNotBeFound("code.specified=false");
    }

    @Test
    @Transactional
    public void getAllListInternalPressUcasesByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listInternalPressUcaseRepository.saveAndFlush(listInternalPressUcase);

        // Get all the listInternalPressUcaseList where name equals to DEFAULT_NAME
        defaultListInternalPressUcaseShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the listInternalPressUcaseList where name equals to UPDATED_NAME
        defaultListInternalPressUcaseShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListInternalPressUcasesByNameIsInShouldWork() throws Exception {
        // Initialize the database
        listInternalPressUcaseRepository.saveAndFlush(listInternalPressUcase);

        // Get all the listInternalPressUcaseList where name in DEFAULT_NAME or UPDATED_NAME
        defaultListInternalPressUcaseShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the listInternalPressUcaseList where name equals to UPDATED_NAME
        defaultListInternalPressUcaseShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListInternalPressUcasesByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listInternalPressUcaseRepository.saveAndFlush(listInternalPressUcase);

        // Get all the listInternalPressUcaseList where name is not null
        defaultListInternalPressUcaseShouldBeFound("name.specified=true");

        // Get all the listInternalPressUcaseList where name is null
        defaultListInternalPressUcaseShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    public void getAllListInternalPressUcasesByFullNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listInternalPressUcaseRepository.saveAndFlush(listInternalPressUcase);

        // Get all the listInternalPressUcaseList where fullName equals to DEFAULT_FULL_NAME
        defaultListInternalPressUcaseShouldBeFound("fullName.equals=" + DEFAULT_FULL_NAME);

        // Get all the listInternalPressUcaseList where fullName equals to UPDATED_FULL_NAME
        defaultListInternalPressUcaseShouldNotBeFound("fullName.equals=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListInternalPressUcasesByFullNameIsInShouldWork() throws Exception {
        // Initialize the database
        listInternalPressUcaseRepository.saveAndFlush(listInternalPressUcase);

        // Get all the listInternalPressUcaseList where fullName in DEFAULT_FULL_NAME or UPDATED_FULL_NAME
        defaultListInternalPressUcaseShouldBeFound("fullName.in=" + DEFAULT_FULL_NAME + "," + UPDATED_FULL_NAME);

        // Get all the listInternalPressUcaseList where fullName equals to UPDATED_FULL_NAME
        defaultListInternalPressUcaseShouldNotBeFound("fullName.in=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListInternalPressUcasesByFullNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listInternalPressUcaseRepository.saveAndFlush(listInternalPressUcase);

        // Get all the listInternalPressUcaseList where fullName is not null
        defaultListInternalPressUcaseShouldBeFound("fullName.specified=true");

        // Get all the listInternalPressUcaseList where fullName is null
        defaultListInternalPressUcaseShouldNotBeFound("fullName.specified=false");
    }

    @Test
    @Transactional
    public void getAllListInternalPressUcasesByIsCurrentFlagIsEqualToSomething() throws Exception {
        // Initialize the database
        listInternalPressUcaseRepository.saveAndFlush(listInternalPressUcase);

        // Get all the listInternalPressUcaseList where isCurrentFlag equals to DEFAULT_IS_CURRENT_FLAG
        defaultListInternalPressUcaseShouldBeFound("isCurrentFlag.equals=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listInternalPressUcaseList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListInternalPressUcaseShouldNotBeFound("isCurrentFlag.equals=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListInternalPressUcasesByIsCurrentFlagIsInShouldWork() throws Exception {
        // Initialize the database
        listInternalPressUcaseRepository.saveAndFlush(listInternalPressUcase);

        // Get all the listInternalPressUcaseList where isCurrentFlag in DEFAULT_IS_CURRENT_FLAG or UPDATED_IS_CURRENT_FLAG
        defaultListInternalPressUcaseShouldBeFound("isCurrentFlag.in=" + DEFAULT_IS_CURRENT_FLAG + "," + UPDATED_IS_CURRENT_FLAG);

        // Get all the listInternalPressUcaseList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListInternalPressUcaseShouldNotBeFound("isCurrentFlag.in=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListInternalPressUcasesByIsCurrentFlagIsNullOrNotNull() throws Exception {
        // Initialize the database
        listInternalPressUcaseRepository.saveAndFlush(listInternalPressUcase);

        // Get all the listInternalPressUcaseList where isCurrentFlag is not null
        defaultListInternalPressUcaseShouldBeFound("isCurrentFlag.specified=true");

        // Get all the listInternalPressUcaseList where isCurrentFlag is null
        defaultListInternalPressUcaseShouldNotBeFound("isCurrentFlag.specified=false");
    }

    @Test
    @Transactional
    public void getAllListInternalPressUcasesByIsCurrentFlagIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        listInternalPressUcaseRepository.saveAndFlush(listInternalPressUcase);

        // Get all the listInternalPressUcaseList where isCurrentFlag greater than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListInternalPressUcaseShouldBeFound("isCurrentFlag.greaterOrEqualThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listInternalPressUcaseList where isCurrentFlag greater than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListInternalPressUcaseShouldNotBeFound("isCurrentFlag.greaterOrEqualThan=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListInternalPressUcasesByIsCurrentFlagIsLessThanSomething() throws Exception {
        // Initialize the database
        listInternalPressUcaseRepository.saveAndFlush(listInternalPressUcase);

        // Get all the listInternalPressUcaseList where isCurrentFlag less than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListInternalPressUcaseShouldNotBeFound("isCurrentFlag.lessThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listInternalPressUcaseList where isCurrentFlag less than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListInternalPressUcaseShouldBeFound("isCurrentFlag.lessThan=" + UPDATED_IS_CURRENT_FLAG);
    }


    @Test
    @Transactional
    public void getAllListInternalPressUcasesByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        listInternalPressUcaseRepository.saveAndFlush(listInternalPressUcase);

        // Get all the listInternalPressUcaseList where description equals to DEFAULT_DESCRIPTION
        defaultListInternalPressUcaseShouldBeFound("description.equals=" + DEFAULT_DESCRIPTION);

        // Get all the listInternalPressUcaseList where description equals to UPDATED_DESCRIPTION
        defaultListInternalPressUcaseShouldNotBeFound("description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListInternalPressUcasesByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        listInternalPressUcaseRepository.saveAndFlush(listInternalPressUcase);

        // Get all the listInternalPressUcaseList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
        defaultListInternalPressUcaseShouldBeFound("description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION);

        // Get all the listInternalPressUcaseList where description equals to UPDATED_DESCRIPTION
        defaultListInternalPressUcaseShouldNotBeFound("description.in=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListInternalPressUcasesByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        listInternalPressUcaseRepository.saveAndFlush(listInternalPressUcase);

        // Get all the listInternalPressUcaseList where description is not null
        defaultListInternalPressUcaseShouldBeFound("description.specified=true");

        // Get all the listInternalPressUcaseList where description is null
        defaultListInternalPressUcaseShouldNotBeFound("description.specified=false");
    }

    @Test
    @Transactional
    public void getAllListInternalPressUcasesByDateCreateIsEqualToSomething() throws Exception {
        // Initialize the database
        listInternalPressUcaseRepository.saveAndFlush(listInternalPressUcase);

        // Get all the listInternalPressUcaseList where dateCreate equals to DEFAULT_DATE_CREATE
        defaultListInternalPressUcaseShouldBeFound("dateCreate.equals=" + DEFAULT_DATE_CREATE);

        // Get all the listInternalPressUcaseList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListInternalPressUcaseShouldNotBeFound("dateCreate.equals=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListInternalPressUcasesByDateCreateIsInShouldWork() throws Exception {
        // Initialize the database
        listInternalPressUcaseRepository.saveAndFlush(listInternalPressUcase);

        // Get all the listInternalPressUcaseList where dateCreate in DEFAULT_DATE_CREATE or UPDATED_DATE_CREATE
        defaultListInternalPressUcaseShouldBeFound("dateCreate.in=" + DEFAULT_DATE_CREATE + "," + UPDATED_DATE_CREATE);

        // Get all the listInternalPressUcaseList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListInternalPressUcaseShouldNotBeFound("dateCreate.in=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListInternalPressUcasesByDateCreateIsNullOrNotNull() throws Exception {
        // Initialize the database
        listInternalPressUcaseRepository.saveAndFlush(listInternalPressUcase);

        // Get all the listInternalPressUcaseList where dateCreate is not null
        defaultListInternalPressUcaseShouldBeFound("dateCreate.specified=true");

        // Get all the listInternalPressUcaseList where dateCreate is null
        defaultListInternalPressUcaseShouldNotBeFound("dateCreate.specified=false");
    }

    @Test
    @Transactional
    public void getAllListInternalPressUcasesByDateEditIsEqualToSomething() throws Exception {
        // Initialize the database
        listInternalPressUcaseRepository.saveAndFlush(listInternalPressUcase);

        // Get all the listInternalPressUcaseList where dateEdit equals to DEFAULT_DATE_EDIT
        defaultListInternalPressUcaseShouldBeFound("dateEdit.equals=" + DEFAULT_DATE_EDIT);

        // Get all the listInternalPressUcaseList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListInternalPressUcaseShouldNotBeFound("dateEdit.equals=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListInternalPressUcasesByDateEditIsInShouldWork() throws Exception {
        // Initialize the database
        listInternalPressUcaseRepository.saveAndFlush(listInternalPressUcase);

        // Get all the listInternalPressUcaseList where dateEdit in DEFAULT_DATE_EDIT or UPDATED_DATE_EDIT
        defaultListInternalPressUcaseShouldBeFound("dateEdit.in=" + DEFAULT_DATE_EDIT + "," + UPDATED_DATE_EDIT);

        // Get all the listInternalPressUcaseList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListInternalPressUcaseShouldNotBeFound("dateEdit.in=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListInternalPressUcasesByDateEditIsNullOrNotNull() throws Exception {
        // Initialize the database
        listInternalPressUcaseRepository.saveAndFlush(listInternalPressUcase);

        // Get all the listInternalPressUcaseList where dateEdit is not null
        defaultListInternalPressUcaseShouldBeFound("dateEdit.specified=true");

        // Get all the listInternalPressUcaseList where dateEdit is null
        defaultListInternalPressUcaseShouldNotBeFound("dateEdit.specified=false");
    }

    @Test
    @Transactional
    public void getAllListInternalPressUcasesByCreatorIsEqualToSomething() throws Exception {
        // Initialize the database
        listInternalPressUcaseRepository.saveAndFlush(listInternalPressUcase);

        // Get all the listInternalPressUcaseList where creator equals to DEFAULT_CREATOR
        defaultListInternalPressUcaseShouldBeFound("creator.equals=" + DEFAULT_CREATOR);

        // Get all the listInternalPressUcaseList where creator equals to UPDATED_CREATOR
        defaultListInternalPressUcaseShouldNotBeFound("creator.equals=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListInternalPressUcasesByCreatorIsInShouldWork() throws Exception {
        // Initialize the database
        listInternalPressUcaseRepository.saveAndFlush(listInternalPressUcase);

        // Get all the listInternalPressUcaseList where creator in DEFAULT_CREATOR or UPDATED_CREATOR
        defaultListInternalPressUcaseShouldBeFound("creator.in=" + DEFAULT_CREATOR + "," + UPDATED_CREATOR);

        // Get all the listInternalPressUcaseList where creator equals to UPDATED_CREATOR
        defaultListInternalPressUcaseShouldNotBeFound("creator.in=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListInternalPressUcasesByCreatorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listInternalPressUcaseRepository.saveAndFlush(listInternalPressUcase);

        // Get all the listInternalPressUcaseList where creator is not null
        defaultListInternalPressUcaseShouldBeFound("creator.specified=true");

        // Get all the listInternalPressUcaseList where creator is null
        defaultListInternalPressUcaseShouldNotBeFound("creator.specified=false");
    }

    @Test
    @Transactional
    public void getAllListInternalPressUcasesByEditorIsEqualToSomething() throws Exception {
        // Initialize the database
        listInternalPressUcaseRepository.saveAndFlush(listInternalPressUcase);

        // Get all the listInternalPressUcaseList where editor equals to DEFAULT_EDITOR
        defaultListInternalPressUcaseShouldBeFound("editor.equals=" + DEFAULT_EDITOR);

        // Get all the listInternalPressUcaseList where editor equals to UPDATED_EDITOR
        defaultListInternalPressUcaseShouldNotBeFound("editor.equals=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListInternalPressUcasesByEditorIsInShouldWork() throws Exception {
        // Initialize the database
        listInternalPressUcaseRepository.saveAndFlush(listInternalPressUcase);

        // Get all the listInternalPressUcaseList where editor in DEFAULT_EDITOR or UPDATED_EDITOR
        defaultListInternalPressUcaseShouldBeFound("editor.in=" + DEFAULT_EDITOR + "," + UPDATED_EDITOR);

        // Get all the listInternalPressUcaseList where editor equals to UPDATED_EDITOR
        defaultListInternalPressUcaseShouldNotBeFound("editor.in=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListInternalPressUcasesByEditorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listInternalPressUcaseRepository.saveAndFlush(listInternalPressUcase);

        // Get all the listInternalPressUcaseList where editor is not null
        defaultListInternalPressUcaseShouldBeFound("editor.specified=true");

        // Get all the listInternalPressUcaseList where editor is null
        defaultListInternalPressUcaseShouldNotBeFound("editor.specified=false");
    }
    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultListInternalPressUcaseShouldBeFound(String filter) throws Exception {
        restListInternalPressUcaseMockMvc.perform(get("/api/list-internal-press-ucases?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listInternalPressUcase.getId().intValue())))
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
        restListInternalPressUcaseMockMvc.perform(get("/api/list-internal-press-ucases/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultListInternalPressUcaseShouldNotBeFound(String filter) throws Exception {
        restListInternalPressUcaseMockMvc.perform(get("/api/list-internal-press-ucases?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restListInternalPressUcaseMockMvc.perform(get("/api/list-internal-press-ucases/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingListInternalPressUcase() throws Exception {
        // Get the listInternalPressUcase
        restListInternalPressUcaseMockMvc.perform(get("/api/list-internal-press-ucases/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateListInternalPressUcase() throws Exception {
        // Initialize the database
        listInternalPressUcaseRepository.saveAndFlush(listInternalPressUcase);

        int databaseSizeBeforeUpdate = listInternalPressUcaseRepository.findAll().size();

        // Update the listInternalPressUcase
        ListInternalPressUcase updatedListInternalPressUcase = listInternalPressUcaseRepository.findById(listInternalPressUcase.getId()).get();
        // Disconnect from session so that the updates on updatedListInternalPressUcase are not directly saved in db
        em.detach(updatedListInternalPressUcase);
        updatedListInternalPressUcase
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        ListInternalPressUcaseDTO listInternalPressUcaseDTO = listInternalPressUcaseMapper.toDto(updatedListInternalPressUcase);

        restListInternalPressUcaseMockMvc.perform(put("/api/list-internal-press-ucases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listInternalPressUcaseDTO)))
            .andExpect(status().isOk());

        // Validate the ListInternalPressUcase in the database
        List<ListInternalPressUcase> listInternalPressUcaseList = listInternalPressUcaseRepository.findAll();
        assertThat(listInternalPressUcaseList).hasSize(databaseSizeBeforeUpdate);
        ListInternalPressUcase testListInternalPressUcase = listInternalPressUcaseList.get(listInternalPressUcaseList.size() - 1);
        assertThat(testListInternalPressUcase.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testListInternalPressUcase.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testListInternalPressUcase.getFullName()).isEqualTo(UPDATED_FULL_NAME);
        assertThat(testListInternalPressUcase.getIsCurrentFlag()).isEqualTo(UPDATED_IS_CURRENT_FLAG);
        assertThat(testListInternalPressUcase.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testListInternalPressUcase.getDateCreate()).isEqualTo(UPDATED_DATE_CREATE);
        assertThat(testListInternalPressUcase.getDateEdit()).isEqualTo(UPDATED_DATE_EDIT);
        assertThat(testListInternalPressUcase.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testListInternalPressUcase.getEditor()).isEqualTo(UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void updateNonExistingListInternalPressUcase() throws Exception {
        int databaseSizeBeforeUpdate = listInternalPressUcaseRepository.findAll().size();

        // Create the ListInternalPressUcase
        ListInternalPressUcaseDTO listInternalPressUcaseDTO = listInternalPressUcaseMapper.toDto(listInternalPressUcase);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restListInternalPressUcaseMockMvc.perform(put("/api/list-internal-press-ucases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listInternalPressUcaseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListInternalPressUcase in the database
        List<ListInternalPressUcase> listInternalPressUcaseList = listInternalPressUcaseRepository.findAll();
        assertThat(listInternalPressUcaseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteListInternalPressUcase() throws Exception {
        // Initialize the database
        listInternalPressUcaseRepository.saveAndFlush(listInternalPressUcase);

        int databaseSizeBeforeDelete = listInternalPressUcaseRepository.findAll().size();

        // Delete the listInternalPressUcase
        restListInternalPressUcaseMockMvc.perform(delete("/api/list-internal-press-ucases/{id}", listInternalPressUcase.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<ListInternalPressUcase> listInternalPressUcaseList = listInternalPressUcaseRepository.findAll();
        assertThat(listInternalPressUcaseList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListInternalPressUcase.class);
        ListInternalPressUcase listInternalPressUcase1 = new ListInternalPressUcase();
        listInternalPressUcase1.setId(1L);
        ListInternalPressUcase listInternalPressUcase2 = new ListInternalPressUcase();
        listInternalPressUcase2.setId(listInternalPressUcase1.getId());
        assertThat(listInternalPressUcase1).isEqualTo(listInternalPressUcase2);
        listInternalPressUcase2.setId(2L);
        assertThat(listInternalPressUcase1).isNotEqualTo(listInternalPressUcase2);
        listInternalPressUcase1.setId(null);
        assertThat(listInternalPressUcase1).isNotEqualTo(listInternalPressUcase2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListInternalPressUcaseDTO.class);
        ListInternalPressUcaseDTO listInternalPressUcaseDTO1 = new ListInternalPressUcaseDTO();
        listInternalPressUcaseDTO1.setId(1L);
        ListInternalPressUcaseDTO listInternalPressUcaseDTO2 = new ListInternalPressUcaseDTO();
        assertThat(listInternalPressUcaseDTO1).isNotEqualTo(listInternalPressUcaseDTO2);
        listInternalPressUcaseDTO2.setId(listInternalPressUcaseDTO1.getId());
        assertThat(listInternalPressUcaseDTO1).isEqualTo(listInternalPressUcaseDTO2);
        listInternalPressUcaseDTO2.setId(2L);
        assertThat(listInternalPressUcaseDTO1).isNotEqualTo(listInternalPressUcaseDTO2);
        listInternalPressUcaseDTO1.setId(null);
        assertThat(listInternalPressUcaseDTO1).isNotEqualTo(listInternalPressUcaseDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(listInternalPressUcaseMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(listInternalPressUcaseMapper.fromId(null)).isNull();
    }
}
