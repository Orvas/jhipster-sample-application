package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.ListEffAxforceUcase;
import io.github.jhipster.application.repository.ListEffAxforceUcaseRepository;
import io.github.jhipster.application.service.ListEffAxforceUcaseService;
import io.github.jhipster.application.service.dto.ListEffAxforceUcaseDTO;
import io.github.jhipster.application.service.mapper.ListEffAxforceUcaseMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.ListEffAxforceUcaseCriteria;
import io.github.jhipster.application.service.ListEffAxforceUcaseQueryService;

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
 * Integration tests for the {@Link ListEffAxforceUcaseResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class ListEffAxforceUcaseResourceIT {

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
    private ListEffAxforceUcaseRepository listEffAxforceUcaseRepository;

    @Autowired
    private ListEffAxforceUcaseMapper listEffAxforceUcaseMapper;

    @Autowired
    private ListEffAxforceUcaseService listEffAxforceUcaseService;

    @Autowired
    private ListEffAxforceUcaseQueryService listEffAxforceUcaseQueryService;

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

    private MockMvc restListEffAxforceUcaseMockMvc;

    private ListEffAxforceUcase listEffAxforceUcase;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ListEffAxforceUcaseResource listEffAxforceUcaseResource = new ListEffAxforceUcaseResource(listEffAxforceUcaseService, listEffAxforceUcaseQueryService);
        this.restListEffAxforceUcaseMockMvc = MockMvcBuilders.standaloneSetup(listEffAxforceUcaseResource)
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
    public static ListEffAxforceUcase createEntity(EntityManager em) {
        ListEffAxforceUcase listEffAxforceUcase = new ListEffAxforceUcase()
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME)
            .fullName(DEFAULT_FULL_NAME)
            .isCurrentFlag(DEFAULT_IS_CURRENT_FLAG)
            .description(DEFAULT_DESCRIPTION)
            .dateCreate(DEFAULT_DATE_CREATE)
            .dateEdit(DEFAULT_DATE_EDIT)
            .creator(DEFAULT_CREATOR)
            .editor(DEFAULT_EDITOR);
        return listEffAxforceUcase;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ListEffAxforceUcase createUpdatedEntity(EntityManager em) {
        ListEffAxforceUcase listEffAxforceUcase = new ListEffAxforceUcase()
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        return listEffAxforceUcase;
    }

    @BeforeEach
    public void initTest() {
        listEffAxforceUcase = createEntity(em);
    }

    @Test
    @Transactional
    public void createListEffAxforceUcase() throws Exception {
        int databaseSizeBeforeCreate = listEffAxforceUcaseRepository.findAll().size();

        // Create the ListEffAxforceUcase
        ListEffAxforceUcaseDTO listEffAxforceUcaseDTO = listEffAxforceUcaseMapper.toDto(listEffAxforceUcase);
        restListEffAxforceUcaseMockMvc.perform(post("/api/list-eff-axforce-ucases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listEffAxforceUcaseDTO)))
            .andExpect(status().isCreated());

        // Validate the ListEffAxforceUcase in the database
        List<ListEffAxforceUcase> listEffAxforceUcaseList = listEffAxforceUcaseRepository.findAll();
        assertThat(listEffAxforceUcaseList).hasSize(databaseSizeBeforeCreate + 1);
        ListEffAxforceUcase testListEffAxforceUcase = listEffAxforceUcaseList.get(listEffAxforceUcaseList.size() - 1);
        assertThat(testListEffAxforceUcase.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testListEffAxforceUcase.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testListEffAxforceUcase.getFullName()).isEqualTo(DEFAULT_FULL_NAME);
        assertThat(testListEffAxforceUcase.getIsCurrentFlag()).isEqualTo(DEFAULT_IS_CURRENT_FLAG);
        assertThat(testListEffAxforceUcase.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testListEffAxforceUcase.getDateCreate()).isEqualTo(DEFAULT_DATE_CREATE);
        assertThat(testListEffAxforceUcase.getDateEdit()).isEqualTo(DEFAULT_DATE_EDIT);
        assertThat(testListEffAxforceUcase.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testListEffAxforceUcase.getEditor()).isEqualTo(DEFAULT_EDITOR);
    }

    @Test
    @Transactional
    public void createListEffAxforceUcaseWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = listEffAxforceUcaseRepository.findAll().size();

        // Create the ListEffAxforceUcase with an existing ID
        listEffAxforceUcase.setId(1L);
        ListEffAxforceUcaseDTO listEffAxforceUcaseDTO = listEffAxforceUcaseMapper.toDto(listEffAxforceUcase);

        // An entity with an existing ID cannot be created, so this API call must fail
        restListEffAxforceUcaseMockMvc.perform(post("/api/list-eff-axforce-ucases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listEffAxforceUcaseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListEffAxforceUcase in the database
        List<ListEffAxforceUcase> listEffAxforceUcaseList = listEffAxforceUcaseRepository.findAll();
        assertThat(listEffAxforceUcaseList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = listEffAxforceUcaseRepository.findAll().size();
        // set the field null
        listEffAxforceUcase.setCode(null);

        // Create the ListEffAxforceUcase, which fails.
        ListEffAxforceUcaseDTO listEffAxforceUcaseDTO = listEffAxforceUcaseMapper.toDto(listEffAxforceUcase);

        restListEffAxforceUcaseMockMvc.perform(post("/api/list-eff-axforce-ucases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listEffAxforceUcaseDTO)))
            .andExpect(status().isBadRequest());

        List<ListEffAxforceUcase> listEffAxforceUcaseList = listEffAxforceUcaseRepository.findAll();
        assertThat(listEffAxforceUcaseList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listEffAxforceUcaseRepository.findAll().size();
        // set the field null
        listEffAxforceUcase.setName(null);

        // Create the ListEffAxforceUcase, which fails.
        ListEffAxforceUcaseDTO listEffAxforceUcaseDTO = listEffAxforceUcaseMapper.toDto(listEffAxforceUcase);

        restListEffAxforceUcaseMockMvc.perform(post("/api/list-eff-axforce-ucases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listEffAxforceUcaseDTO)))
            .andExpect(status().isBadRequest());

        List<ListEffAxforceUcase> listEffAxforceUcaseList = listEffAxforceUcaseRepository.findAll();
        assertThat(listEffAxforceUcaseList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFullNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listEffAxforceUcaseRepository.findAll().size();
        // set the field null
        listEffAxforceUcase.setFullName(null);

        // Create the ListEffAxforceUcase, which fails.
        ListEffAxforceUcaseDTO listEffAxforceUcaseDTO = listEffAxforceUcaseMapper.toDto(listEffAxforceUcase);

        restListEffAxforceUcaseMockMvc.perform(post("/api/list-eff-axforce-ucases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listEffAxforceUcaseDTO)))
            .andExpect(status().isBadRequest());

        List<ListEffAxforceUcase> listEffAxforceUcaseList = listEffAxforceUcaseRepository.findAll();
        assertThat(listEffAxforceUcaseList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsCurrentFlagIsRequired() throws Exception {
        int databaseSizeBeforeTest = listEffAxforceUcaseRepository.findAll().size();
        // set the field null
        listEffAxforceUcase.setIsCurrentFlag(null);

        // Create the ListEffAxforceUcase, which fails.
        ListEffAxforceUcaseDTO listEffAxforceUcaseDTO = listEffAxforceUcaseMapper.toDto(listEffAxforceUcase);

        restListEffAxforceUcaseMockMvc.perform(post("/api/list-eff-axforce-ucases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listEffAxforceUcaseDTO)))
            .andExpect(status().isBadRequest());

        List<ListEffAxforceUcase> listEffAxforceUcaseList = listEffAxforceUcaseRepository.findAll();
        assertThat(listEffAxforceUcaseList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllListEffAxforceUcases() throws Exception {
        // Initialize the database
        listEffAxforceUcaseRepository.saveAndFlush(listEffAxforceUcase);

        // Get all the listEffAxforceUcaseList
        restListEffAxforceUcaseMockMvc.perform(get("/api/list-eff-axforce-ucases?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listEffAxforceUcase.getId().intValue())))
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
    public void getListEffAxforceUcase() throws Exception {
        // Initialize the database
        listEffAxforceUcaseRepository.saveAndFlush(listEffAxforceUcase);

        // Get the listEffAxforceUcase
        restListEffAxforceUcaseMockMvc.perform(get("/api/list-eff-axforce-ucases/{id}", listEffAxforceUcase.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(listEffAxforceUcase.getId().intValue()))
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
    public void getAllListEffAxforceUcasesByCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        listEffAxforceUcaseRepository.saveAndFlush(listEffAxforceUcase);

        // Get all the listEffAxforceUcaseList where code equals to DEFAULT_CODE
        defaultListEffAxforceUcaseShouldBeFound("code.equals=" + DEFAULT_CODE);

        // Get all the listEffAxforceUcaseList where code equals to UPDATED_CODE
        defaultListEffAxforceUcaseShouldNotBeFound("code.equals=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListEffAxforceUcasesByCodeIsInShouldWork() throws Exception {
        // Initialize the database
        listEffAxforceUcaseRepository.saveAndFlush(listEffAxforceUcase);

        // Get all the listEffAxforceUcaseList where code in DEFAULT_CODE or UPDATED_CODE
        defaultListEffAxforceUcaseShouldBeFound("code.in=" + DEFAULT_CODE + "," + UPDATED_CODE);

        // Get all the listEffAxforceUcaseList where code equals to UPDATED_CODE
        defaultListEffAxforceUcaseShouldNotBeFound("code.in=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListEffAxforceUcasesByCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        listEffAxforceUcaseRepository.saveAndFlush(listEffAxforceUcase);

        // Get all the listEffAxforceUcaseList where code is not null
        defaultListEffAxforceUcaseShouldBeFound("code.specified=true");

        // Get all the listEffAxforceUcaseList where code is null
        defaultListEffAxforceUcaseShouldNotBeFound("code.specified=false");
    }

    @Test
    @Transactional
    public void getAllListEffAxforceUcasesByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listEffAxforceUcaseRepository.saveAndFlush(listEffAxforceUcase);

        // Get all the listEffAxforceUcaseList where name equals to DEFAULT_NAME
        defaultListEffAxforceUcaseShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the listEffAxforceUcaseList where name equals to UPDATED_NAME
        defaultListEffAxforceUcaseShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListEffAxforceUcasesByNameIsInShouldWork() throws Exception {
        // Initialize the database
        listEffAxforceUcaseRepository.saveAndFlush(listEffAxforceUcase);

        // Get all the listEffAxforceUcaseList where name in DEFAULT_NAME or UPDATED_NAME
        defaultListEffAxforceUcaseShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the listEffAxforceUcaseList where name equals to UPDATED_NAME
        defaultListEffAxforceUcaseShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListEffAxforceUcasesByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listEffAxforceUcaseRepository.saveAndFlush(listEffAxforceUcase);

        // Get all the listEffAxforceUcaseList where name is not null
        defaultListEffAxforceUcaseShouldBeFound("name.specified=true");

        // Get all the listEffAxforceUcaseList where name is null
        defaultListEffAxforceUcaseShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    public void getAllListEffAxforceUcasesByFullNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listEffAxforceUcaseRepository.saveAndFlush(listEffAxforceUcase);

        // Get all the listEffAxforceUcaseList where fullName equals to DEFAULT_FULL_NAME
        defaultListEffAxforceUcaseShouldBeFound("fullName.equals=" + DEFAULT_FULL_NAME);

        // Get all the listEffAxforceUcaseList where fullName equals to UPDATED_FULL_NAME
        defaultListEffAxforceUcaseShouldNotBeFound("fullName.equals=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListEffAxforceUcasesByFullNameIsInShouldWork() throws Exception {
        // Initialize the database
        listEffAxforceUcaseRepository.saveAndFlush(listEffAxforceUcase);

        // Get all the listEffAxforceUcaseList where fullName in DEFAULT_FULL_NAME or UPDATED_FULL_NAME
        defaultListEffAxforceUcaseShouldBeFound("fullName.in=" + DEFAULT_FULL_NAME + "," + UPDATED_FULL_NAME);

        // Get all the listEffAxforceUcaseList where fullName equals to UPDATED_FULL_NAME
        defaultListEffAxforceUcaseShouldNotBeFound("fullName.in=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListEffAxforceUcasesByFullNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listEffAxforceUcaseRepository.saveAndFlush(listEffAxforceUcase);

        // Get all the listEffAxforceUcaseList where fullName is not null
        defaultListEffAxforceUcaseShouldBeFound("fullName.specified=true");

        // Get all the listEffAxforceUcaseList where fullName is null
        defaultListEffAxforceUcaseShouldNotBeFound("fullName.specified=false");
    }

    @Test
    @Transactional
    public void getAllListEffAxforceUcasesByIsCurrentFlagIsEqualToSomething() throws Exception {
        // Initialize the database
        listEffAxforceUcaseRepository.saveAndFlush(listEffAxforceUcase);

        // Get all the listEffAxforceUcaseList where isCurrentFlag equals to DEFAULT_IS_CURRENT_FLAG
        defaultListEffAxforceUcaseShouldBeFound("isCurrentFlag.equals=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listEffAxforceUcaseList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListEffAxforceUcaseShouldNotBeFound("isCurrentFlag.equals=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListEffAxforceUcasesByIsCurrentFlagIsInShouldWork() throws Exception {
        // Initialize the database
        listEffAxforceUcaseRepository.saveAndFlush(listEffAxforceUcase);

        // Get all the listEffAxforceUcaseList where isCurrentFlag in DEFAULT_IS_CURRENT_FLAG or UPDATED_IS_CURRENT_FLAG
        defaultListEffAxforceUcaseShouldBeFound("isCurrentFlag.in=" + DEFAULT_IS_CURRENT_FLAG + "," + UPDATED_IS_CURRENT_FLAG);

        // Get all the listEffAxforceUcaseList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListEffAxforceUcaseShouldNotBeFound("isCurrentFlag.in=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListEffAxforceUcasesByIsCurrentFlagIsNullOrNotNull() throws Exception {
        // Initialize the database
        listEffAxforceUcaseRepository.saveAndFlush(listEffAxforceUcase);

        // Get all the listEffAxforceUcaseList where isCurrentFlag is not null
        defaultListEffAxforceUcaseShouldBeFound("isCurrentFlag.specified=true");

        // Get all the listEffAxforceUcaseList where isCurrentFlag is null
        defaultListEffAxforceUcaseShouldNotBeFound("isCurrentFlag.specified=false");
    }

    @Test
    @Transactional
    public void getAllListEffAxforceUcasesByIsCurrentFlagIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        listEffAxforceUcaseRepository.saveAndFlush(listEffAxforceUcase);

        // Get all the listEffAxforceUcaseList where isCurrentFlag greater than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListEffAxforceUcaseShouldBeFound("isCurrentFlag.greaterOrEqualThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listEffAxforceUcaseList where isCurrentFlag greater than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListEffAxforceUcaseShouldNotBeFound("isCurrentFlag.greaterOrEqualThan=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListEffAxforceUcasesByIsCurrentFlagIsLessThanSomething() throws Exception {
        // Initialize the database
        listEffAxforceUcaseRepository.saveAndFlush(listEffAxforceUcase);

        // Get all the listEffAxforceUcaseList where isCurrentFlag less than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListEffAxforceUcaseShouldNotBeFound("isCurrentFlag.lessThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listEffAxforceUcaseList where isCurrentFlag less than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListEffAxforceUcaseShouldBeFound("isCurrentFlag.lessThan=" + UPDATED_IS_CURRENT_FLAG);
    }


    @Test
    @Transactional
    public void getAllListEffAxforceUcasesByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        listEffAxforceUcaseRepository.saveAndFlush(listEffAxforceUcase);

        // Get all the listEffAxforceUcaseList where description equals to DEFAULT_DESCRIPTION
        defaultListEffAxforceUcaseShouldBeFound("description.equals=" + DEFAULT_DESCRIPTION);

        // Get all the listEffAxforceUcaseList where description equals to UPDATED_DESCRIPTION
        defaultListEffAxforceUcaseShouldNotBeFound("description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListEffAxforceUcasesByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        listEffAxforceUcaseRepository.saveAndFlush(listEffAxforceUcase);

        // Get all the listEffAxforceUcaseList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
        defaultListEffAxforceUcaseShouldBeFound("description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION);

        // Get all the listEffAxforceUcaseList where description equals to UPDATED_DESCRIPTION
        defaultListEffAxforceUcaseShouldNotBeFound("description.in=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListEffAxforceUcasesByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        listEffAxforceUcaseRepository.saveAndFlush(listEffAxforceUcase);

        // Get all the listEffAxforceUcaseList where description is not null
        defaultListEffAxforceUcaseShouldBeFound("description.specified=true");

        // Get all the listEffAxforceUcaseList where description is null
        defaultListEffAxforceUcaseShouldNotBeFound("description.specified=false");
    }

    @Test
    @Transactional
    public void getAllListEffAxforceUcasesByDateCreateIsEqualToSomething() throws Exception {
        // Initialize the database
        listEffAxforceUcaseRepository.saveAndFlush(listEffAxforceUcase);

        // Get all the listEffAxforceUcaseList where dateCreate equals to DEFAULT_DATE_CREATE
        defaultListEffAxforceUcaseShouldBeFound("dateCreate.equals=" + DEFAULT_DATE_CREATE);

        // Get all the listEffAxforceUcaseList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListEffAxforceUcaseShouldNotBeFound("dateCreate.equals=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListEffAxforceUcasesByDateCreateIsInShouldWork() throws Exception {
        // Initialize the database
        listEffAxforceUcaseRepository.saveAndFlush(listEffAxforceUcase);

        // Get all the listEffAxforceUcaseList where dateCreate in DEFAULT_DATE_CREATE or UPDATED_DATE_CREATE
        defaultListEffAxforceUcaseShouldBeFound("dateCreate.in=" + DEFAULT_DATE_CREATE + "," + UPDATED_DATE_CREATE);

        // Get all the listEffAxforceUcaseList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListEffAxforceUcaseShouldNotBeFound("dateCreate.in=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListEffAxforceUcasesByDateCreateIsNullOrNotNull() throws Exception {
        // Initialize the database
        listEffAxforceUcaseRepository.saveAndFlush(listEffAxforceUcase);

        // Get all the listEffAxforceUcaseList where dateCreate is not null
        defaultListEffAxforceUcaseShouldBeFound("dateCreate.specified=true");

        // Get all the listEffAxforceUcaseList where dateCreate is null
        defaultListEffAxforceUcaseShouldNotBeFound("dateCreate.specified=false");
    }

    @Test
    @Transactional
    public void getAllListEffAxforceUcasesByDateEditIsEqualToSomething() throws Exception {
        // Initialize the database
        listEffAxforceUcaseRepository.saveAndFlush(listEffAxforceUcase);

        // Get all the listEffAxforceUcaseList where dateEdit equals to DEFAULT_DATE_EDIT
        defaultListEffAxforceUcaseShouldBeFound("dateEdit.equals=" + DEFAULT_DATE_EDIT);

        // Get all the listEffAxforceUcaseList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListEffAxforceUcaseShouldNotBeFound("dateEdit.equals=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListEffAxforceUcasesByDateEditIsInShouldWork() throws Exception {
        // Initialize the database
        listEffAxforceUcaseRepository.saveAndFlush(listEffAxforceUcase);

        // Get all the listEffAxforceUcaseList where dateEdit in DEFAULT_DATE_EDIT or UPDATED_DATE_EDIT
        defaultListEffAxforceUcaseShouldBeFound("dateEdit.in=" + DEFAULT_DATE_EDIT + "," + UPDATED_DATE_EDIT);

        // Get all the listEffAxforceUcaseList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListEffAxforceUcaseShouldNotBeFound("dateEdit.in=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListEffAxforceUcasesByDateEditIsNullOrNotNull() throws Exception {
        // Initialize the database
        listEffAxforceUcaseRepository.saveAndFlush(listEffAxforceUcase);

        // Get all the listEffAxforceUcaseList where dateEdit is not null
        defaultListEffAxforceUcaseShouldBeFound("dateEdit.specified=true");

        // Get all the listEffAxforceUcaseList where dateEdit is null
        defaultListEffAxforceUcaseShouldNotBeFound("dateEdit.specified=false");
    }

    @Test
    @Transactional
    public void getAllListEffAxforceUcasesByCreatorIsEqualToSomething() throws Exception {
        // Initialize the database
        listEffAxforceUcaseRepository.saveAndFlush(listEffAxforceUcase);

        // Get all the listEffAxforceUcaseList where creator equals to DEFAULT_CREATOR
        defaultListEffAxforceUcaseShouldBeFound("creator.equals=" + DEFAULT_CREATOR);

        // Get all the listEffAxforceUcaseList where creator equals to UPDATED_CREATOR
        defaultListEffAxforceUcaseShouldNotBeFound("creator.equals=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListEffAxforceUcasesByCreatorIsInShouldWork() throws Exception {
        // Initialize the database
        listEffAxforceUcaseRepository.saveAndFlush(listEffAxforceUcase);

        // Get all the listEffAxforceUcaseList where creator in DEFAULT_CREATOR or UPDATED_CREATOR
        defaultListEffAxforceUcaseShouldBeFound("creator.in=" + DEFAULT_CREATOR + "," + UPDATED_CREATOR);

        // Get all the listEffAxforceUcaseList where creator equals to UPDATED_CREATOR
        defaultListEffAxforceUcaseShouldNotBeFound("creator.in=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListEffAxforceUcasesByCreatorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listEffAxforceUcaseRepository.saveAndFlush(listEffAxforceUcase);

        // Get all the listEffAxforceUcaseList where creator is not null
        defaultListEffAxforceUcaseShouldBeFound("creator.specified=true");

        // Get all the listEffAxforceUcaseList where creator is null
        defaultListEffAxforceUcaseShouldNotBeFound("creator.specified=false");
    }

    @Test
    @Transactional
    public void getAllListEffAxforceUcasesByEditorIsEqualToSomething() throws Exception {
        // Initialize the database
        listEffAxforceUcaseRepository.saveAndFlush(listEffAxforceUcase);

        // Get all the listEffAxforceUcaseList where editor equals to DEFAULT_EDITOR
        defaultListEffAxforceUcaseShouldBeFound("editor.equals=" + DEFAULT_EDITOR);

        // Get all the listEffAxforceUcaseList where editor equals to UPDATED_EDITOR
        defaultListEffAxforceUcaseShouldNotBeFound("editor.equals=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListEffAxforceUcasesByEditorIsInShouldWork() throws Exception {
        // Initialize the database
        listEffAxforceUcaseRepository.saveAndFlush(listEffAxforceUcase);

        // Get all the listEffAxforceUcaseList where editor in DEFAULT_EDITOR or UPDATED_EDITOR
        defaultListEffAxforceUcaseShouldBeFound("editor.in=" + DEFAULT_EDITOR + "," + UPDATED_EDITOR);

        // Get all the listEffAxforceUcaseList where editor equals to UPDATED_EDITOR
        defaultListEffAxforceUcaseShouldNotBeFound("editor.in=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListEffAxforceUcasesByEditorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listEffAxforceUcaseRepository.saveAndFlush(listEffAxforceUcase);

        // Get all the listEffAxforceUcaseList where editor is not null
        defaultListEffAxforceUcaseShouldBeFound("editor.specified=true");

        // Get all the listEffAxforceUcaseList where editor is null
        defaultListEffAxforceUcaseShouldNotBeFound("editor.specified=false");
    }
    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultListEffAxforceUcaseShouldBeFound(String filter) throws Exception {
        restListEffAxforceUcaseMockMvc.perform(get("/api/list-eff-axforce-ucases?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listEffAxforceUcase.getId().intValue())))
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
        restListEffAxforceUcaseMockMvc.perform(get("/api/list-eff-axforce-ucases/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultListEffAxforceUcaseShouldNotBeFound(String filter) throws Exception {
        restListEffAxforceUcaseMockMvc.perform(get("/api/list-eff-axforce-ucases?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restListEffAxforceUcaseMockMvc.perform(get("/api/list-eff-axforce-ucases/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingListEffAxforceUcase() throws Exception {
        // Get the listEffAxforceUcase
        restListEffAxforceUcaseMockMvc.perform(get("/api/list-eff-axforce-ucases/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateListEffAxforceUcase() throws Exception {
        // Initialize the database
        listEffAxforceUcaseRepository.saveAndFlush(listEffAxforceUcase);

        int databaseSizeBeforeUpdate = listEffAxforceUcaseRepository.findAll().size();

        // Update the listEffAxforceUcase
        ListEffAxforceUcase updatedListEffAxforceUcase = listEffAxforceUcaseRepository.findById(listEffAxforceUcase.getId()).get();
        // Disconnect from session so that the updates on updatedListEffAxforceUcase are not directly saved in db
        em.detach(updatedListEffAxforceUcase);
        updatedListEffAxforceUcase
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        ListEffAxforceUcaseDTO listEffAxforceUcaseDTO = listEffAxforceUcaseMapper.toDto(updatedListEffAxforceUcase);

        restListEffAxforceUcaseMockMvc.perform(put("/api/list-eff-axforce-ucases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listEffAxforceUcaseDTO)))
            .andExpect(status().isOk());

        // Validate the ListEffAxforceUcase in the database
        List<ListEffAxforceUcase> listEffAxforceUcaseList = listEffAxforceUcaseRepository.findAll();
        assertThat(listEffAxforceUcaseList).hasSize(databaseSizeBeforeUpdate);
        ListEffAxforceUcase testListEffAxforceUcase = listEffAxforceUcaseList.get(listEffAxforceUcaseList.size() - 1);
        assertThat(testListEffAxforceUcase.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testListEffAxforceUcase.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testListEffAxforceUcase.getFullName()).isEqualTo(UPDATED_FULL_NAME);
        assertThat(testListEffAxforceUcase.getIsCurrentFlag()).isEqualTo(UPDATED_IS_CURRENT_FLAG);
        assertThat(testListEffAxforceUcase.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testListEffAxforceUcase.getDateCreate()).isEqualTo(UPDATED_DATE_CREATE);
        assertThat(testListEffAxforceUcase.getDateEdit()).isEqualTo(UPDATED_DATE_EDIT);
        assertThat(testListEffAxforceUcase.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testListEffAxforceUcase.getEditor()).isEqualTo(UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void updateNonExistingListEffAxforceUcase() throws Exception {
        int databaseSizeBeforeUpdate = listEffAxforceUcaseRepository.findAll().size();

        // Create the ListEffAxforceUcase
        ListEffAxforceUcaseDTO listEffAxforceUcaseDTO = listEffAxforceUcaseMapper.toDto(listEffAxforceUcase);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restListEffAxforceUcaseMockMvc.perform(put("/api/list-eff-axforce-ucases")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listEffAxforceUcaseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListEffAxforceUcase in the database
        List<ListEffAxforceUcase> listEffAxforceUcaseList = listEffAxforceUcaseRepository.findAll();
        assertThat(listEffAxforceUcaseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteListEffAxforceUcase() throws Exception {
        // Initialize the database
        listEffAxforceUcaseRepository.saveAndFlush(listEffAxforceUcase);

        int databaseSizeBeforeDelete = listEffAxforceUcaseRepository.findAll().size();

        // Delete the listEffAxforceUcase
        restListEffAxforceUcaseMockMvc.perform(delete("/api/list-eff-axforce-ucases/{id}", listEffAxforceUcase.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<ListEffAxforceUcase> listEffAxforceUcaseList = listEffAxforceUcaseRepository.findAll();
        assertThat(listEffAxforceUcaseList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListEffAxforceUcase.class);
        ListEffAxforceUcase listEffAxforceUcase1 = new ListEffAxforceUcase();
        listEffAxforceUcase1.setId(1L);
        ListEffAxforceUcase listEffAxforceUcase2 = new ListEffAxforceUcase();
        listEffAxforceUcase2.setId(listEffAxforceUcase1.getId());
        assertThat(listEffAxforceUcase1).isEqualTo(listEffAxforceUcase2);
        listEffAxforceUcase2.setId(2L);
        assertThat(listEffAxforceUcase1).isNotEqualTo(listEffAxforceUcase2);
        listEffAxforceUcase1.setId(null);
        assertThat(listEffAxforceUcase1).isNotEqualTo(listEffAxforceUcase2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListEffAxforceUcaseDTO.class);
        ListEffAxforceUcaseDTO listEffAxforceUcaseDTO1 = new ListEffAxforceUcaseDTO();
        listEffAxforceUcaseDTO1.setId(1L);
        ListEffAxforceUcaseDTO listEffAxforceUcaseDTO2 = new ListEffAxforceUcaseDTO();
        assertThat(listEffAxforceUcaseDTO1).isNotEqualTo(listEffAxforceUcaseDTO2);
        listEffAxforceUcaseDTO2.setId(listEffAxforceUcaseDTO1.getId());
        assertThat(listEffAxforceUcaseDTO1).isEqualTo(listEffAxforceUcaseDTO2);
        listEffAxforceUcaseDTO2.setId(2L);
        assertThat(listEffAxforceUcaseDTO1).isNotEqualTo(listEffAxforceUcaseDTO2);
        listEffAxforceUcaseDTO1.setId(null);
        assertThat(listEffAxforceUcaseDTO1).isNotEqualTo(listEffAxforceUcaseDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(listEffAxforceUcaseMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(listEffAxforceUcaseMapper.fromId(null)).isNull();
    }
}
