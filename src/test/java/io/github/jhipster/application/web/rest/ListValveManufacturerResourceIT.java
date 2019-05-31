package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.ListValveManufacturer;
import io.github.jhipster.application.domain.ValveHist;
import io.github.jhipster.application.repository.ListValveManufacturerRepository;
import io.github.jhipster.application.service.ListValveManufacturerService;
import io.github.jhipster.application.service.dto.ListValveManufacturerDTO;
import io.github.jhipster.application.service.mapper.ListValveManufacturerMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.ListValveManufacturerCriteria;
import io.github.jhipster.application.service.ListValveManufacturerQueryService;

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
 * Integration tests for the {@Link ListValveManufacturerResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class ListValveManufacturerResourceIT {

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
    private ListValveManufacturerRepository listValveManufacturerRepository;

    @Autowired
    private ListValveManufacturerMapper listValveManufacturerMapper;

    @Autowired
    private ListValveManufacturerService listValveManufacturerService;

    @Autowired
    private ListValveManufacturerQueryService listValveManufacturerQueryService;

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

    private MockMvc restListValveManufacturerMockMvc;

    private ListValveManufacturer listValveManufacturer;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ListValveManufacturerResource listValveManufacturerResource = new ListValveManufacturerResource(listValveManufacturerService, listValveManufacturerQueryService);
        this.restListValveManufacturerMockMvc = MockMvcBuilders.standaloneSetup(listValveManufacturerResource)
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
    public static ListValveManufacturer createEntity(EntityManager em) {
        ListValveManufacturer listValveManufacturer = new ListValveManufacturer()
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME)
            .fullName(DEFAULT_FULL_NAME)
            .isCurrentFlag(DEFAULT_IS_CURRENT_FLAG)
            .description(DEFAULT_DESCRIPTION)
            .dateCreate(DEFAULT_DATE_CREATE)
            .dateEdit(DEFAULT_DATE_EDIT)
            .creator(DEFAULT_CREATOR)
            .editor(DEFAULT_EDITOR);
        return listValveManufacturer;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ListValveManufacturer createUpdatedEntity(EntityManager em) {
        ListValveManufacturer listValveManufacturer = new ListValveManufacturer()
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        return listValveManufacturer;
    }

    @BeforeEach
    public void initTest() {
        listValveManufacturer = createEntity(em);
    }

    @Test
    @Transactional
    public void createListValveManufacturer() throws Exception {
        int databaseSizeBeforeCreate = listValveManufacturerRepository.findAll().size();

        // Create the ListValveManufacturer
        ListValveManufacturerDTO listValveManufacturerDTO = listValveManufacturerMapper.toDto(listValveManufacturer);
        restListValveManufacturerMockMvc.perform(post("/api/list-valve-manufacturers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listValveManufacturerDTO)))
            .andExpect(status().isCreated());

        // Validate the ListValveManufacturer in the database
        List<ListValveManufacturer> listValveManufacturerList = listValveManufacturerRepository.findAll();
        assertThat(listValveManufacturerList).hasSize(databaseSizeBeforeCreate + 1);
        ListValveManufacturer testListValveManufacturer = listValveManufacturerList.get(listValveManufacturerList.size() - 1);
        assertThat(testListValveManufacturer.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testListValveManufacturer.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testListValveManufacturer.getFullName()).isEqualTo(DEFAULT_FULL_NAME);
        assertThat(testListValveManufacturer.getIsCurrentFlag()).isEqualTo(DEFAULT_IS_CURRENT_FLAG);
        assertThat(testListValveManufacturer.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testListValveManufacturer.getDateCreate()).isEqualTo(DEFAULT_DATE_CREATE);
        assertThat(testListValveManufacturer.getDateEdit()).isEqualTo(DEFAULT_DATE_EDIT);
        assertThat(testListValveManufacturer.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testListValveManufacturer.getEditor()).isEqualTo(DEFAULT_EDITOR);
    }

    @Test
    @Transactional
    public void createListValveManufacturerWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = listValveManufacturerRepository.findAll().size();

        // Create the ListValveManufacturer with an existing ID
        listValveManufacturer.setId(1L);
        ListValveManufacturerDTO listValveManufacturerDTO = listValveManufacturerMapper.toDto(listValveManufacturer);

        // An entity with an existing ID cannot be created, so this API call must fail
        restListValveManufacturerMockMvc.perform(post("/api/list-valve-manufacturers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listValveManufacturerDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListValveManufacturer in the database
        List<ListValveManufacturer> listValveManufacturerList = listValveManufacturerRepository.findAll();
        assertThat(listValveManufacturerList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = listValveManufacturerRepository.findAll().size();
        // set the field null
        listValveManufacturer.setCode(null);

        // Create the ListValveManufacturer, which fails.
        ListValveManufacturerDTO listValveManufacturerDTO = listValveManufacturerMapper.toDto(listValveManufacturer);

        restListValveManufacturerMockMvc.perform(post("/api/list-valve-manufacturers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listValveManufacturerDTO)))
            .andExpect(status().isBadRequest());

        List<ListValveManufacturer> listValveManufacturerList = listValveManufacturerRepository.findAll();
        assertThat(listValveManufacturerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listValveManufacturerRepository.findAll().size();
        // set the field null
        listValveManufacturer.setName(null);

        // Create the ListValveManufacturer, which fails.
        ListValveManufacturerDTO listValveManufacturerDTO = listValveManufacturerMapper.toDto(listValveManufacturer);

        restListValveManufacturerMockMvc.perform(post("/api/list-valve-manufacturers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listValveManufacturerDTO)))
            .andExpect(status().isBadRequest());

        List<ListValveManufacturer> listValveManufacturerList = listValveManufacturerRepository.findAll();
        assertThat(listValveManufacturerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFullNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listValveManufacturerRepository.findAll().size();
        // set the field null
        listValveManufacturer.setFullName(null);

        // Create the ListValveManufacturer, which fails.
        ListValveManufacturerDTO listValveManufacturerDTO = listValveManufacturerMapper.toDto(listValveManufacturer);

        restListValveManufacturerMockMvc.perform(post("/api/list-valve-manufacturers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listValveManufacturerDTO)))
            .andExpect(status().isBadRequest());

        List<ListValveManufacturer> listValveManufacturerList = listValveManufacturerRepository.findAll();
        assertThat(listValveManufacturerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsCurrentFlagIsRequired() throws Exception {
        int databaseSizeBeforeTest = listValveManufacturerRepository.findAll().size();
        // set the field null
        listValveManufacturer.setIsCurrentFlag(null);

        // Create the ListValveManufacturer, which fails.
        ListValveManufacturerDTO listValveManufacturerDTO = listValveManufacturerMapper.toDto(listValveManufacturer);

        restListValveManufacturerMockMvc.perform(post("/api/list-valve-manufacturers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listValveManufacturerDTO)))
            .andExpect(status().isBadRequest());

        List<ListValveManufacturer> listValveManufacturerList = listValveManufacturerRepository.findAll();
        assertThat(listValveManufacturerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllListValveManufacturers() throws Exception {
        // Initialize the database
        listValveManufacturerRepository.saveAndFlush(listValveManufacturer);

        // Get all the listValveManufacturerList
        restListValveManufacturerMockMvc.perform(get("/api/list-valve-manufacturers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listValveManufacturer.getId().intValue())))
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
    public void getListValveManufacturer() throws Exception {
        // Initialize the database
        listValveManufacturerRepository.saveAndFlush(listValveManufacturer);

        // Get the listValveManufacturer
        restListValveManufacturerMockMvc.perform(get("/api/list-valve-manufacturers/{id}", listValveManufacturer.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(listValveManufacturer.getId().intValue()))
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
    public void getAllListValveManufacturersByCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        listValveManufacturerRepository.saveAndFlush(listValveManufacturer);

        // Get all the listValveManufacturerList where code equals to DEFAULT_CODE
        defaultListValveManufacturerShouldBeFound("code.equals=" + DEFAULT_CODE);

        // Get all the listValveManufacturerList where code equals to UPDATED_CODE
        defaultListValveManufacturerShouldNotBeFound("code.equals=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListValveManufacturersByCodeIsInShouldWork() throws Exception {
        // Initialize the database
        listValveManufacturerRepository.saveAndFlush(listValveManufacturer);

        // Get all the listValveManufacturerList where code in DEFAULT_CODE or UPDATED_CODE
        defaultListValveManufacturerShouldBeFound("code.in=" + DEFAULT_CODE + "," + UPDATED_CODE);

        // Get all the listValveManufacturerList where code equals to UPDATED_CODE
        defaultListValveManufacturerShouldNotBeFound("code.in=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListValveManufacturersByCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        listValveManufacturerRepository.saveAndFlush(listValveManufacturer);

        // Get all the listValveManufacturerList where code is not null
        defaultListValveManufacturerShouldBeFound("code.specified=true");

        // Get all the listValveManufacturerList where code is null
        defaultListValveManufacturerShouldNotBeFound("code.specified=false");
    }

    @Test
    @Transactional
    public void getAllListValveManufacturersByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listValveManufacturerRepository.saveAndFlush(listValveManufacturer);

        // Get all the listValveManufacturerList where name equals to DEFAULT_NAME
        defaultListValveManufacturerShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the listValveManufacturerList where name equals to UPDATED_NAME
        defaultListValveManufacturerShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListValveManufacturersByNameIsInShouldWork() throws Exception {
        // Initialize the database
        listValveManufacturerRepository.saveAndFlush(listValveManufacturer);

        // Get all the listValveManufacturerList where name in DEFAULT_NAME or UPDATED_NAME
        defaultListValveManufacturerShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the listValveManufacturerList where name equals to UPDATED_NAME
        defaultListValveManufacturerShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListValveManufacturersByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listValveManufacturerRepository.saveAndFlush(listValveManufacturer);

        // Get all the listValveManufacturerList where name is not null
        defaultListValveManufacturerShouldBeFound("name.specified=true");

        // Get all the listValveManufacturerList where name is null
        defaultListValveManufacturerShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    public void getAllListValveManufacturersByFullNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listValveManufacturerRepository.saveAndFlush(listValveManufacturer);

        // Get all the listValveManufacturerList where fullName equals to DEFAULT_FULL_NAME
        defaultListValveManufacturerShouldBeFound("fullName.equals=" + DEFAULT_FULL_NAME);

        // Get all the listValveManufacturerList where fullName equals to UPDATED_FULL_NAME
        defaultListValveManufacturerShouldNotBeFound("fullName.equals=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListValveManufacturersByFullNameIsInShouldWork() throws Exception {
        // Initialize the database
        listValveManufacturerRepository.saveAndFlush(listValveManufacturer);

        // Get all the listValveManufacturerList where fullName in DEFAULT_FULL_NAME or UPDATED_FULL_NAME
        defaultListValveManufacturerShouldBeFound("fullName.in=" + DEFAULT_FULL_NAME + "," + UPDATED_FULL_NAME);

        // Get all the listValveManufacturerList where fullName equals to UPDATED_FULL_NAME
        defaultListValveManufacturerShouldNotBeFound("fullName.in=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListValveManufacturersByFullNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listValveManufacturerRepository.saveAndFlush(listValveManufacturer);

        // Get all the listValveManufacturerList where fullName is not null
        defaultListValveManufacturerShouldBeFound("fullName.specified=true");

        // Get all the listValveManufacturerList where fullName is null
        defaultListValveManufacturerShouldNotBeFound("fullName.specified=false");
    }

    @Test
    @Transactional
    public void getAllListValveManufacturersByIsCurrentFlagIsEqualToSomething() throws Exception {
        // Initialize the database
        listValveManufacturerRepository.saveAndFlush(listValveManufacturer);

        // Get all the listValveManufacturerList where isCurrentFlag equals to DEFAULT_IS_CURRENT_FLAG
        defaultListValveManufacturerShouldBeFound("isCurrentFlag.equals=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listValveManufacturerList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListValveManufacturerShouldNotBeFound("isCurrentFlag.equals=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListValveManufacturersByIsCurrentFlagIsInShouldWork() throws Exception {
        // Initialize the database
        listValveManufacturerRepository.saveAndFlush(listValveManufacturer);

        // Get all the listValveManufacturerList where isCurrentFlag in DEFAULT_IS_CURRENT_FLAG or UPDATED_IS_CURRENT_FLAG
        defaultListValveManufacturerShouldBeFound("isCurrentFlag.in=" + DEFAULT_IS_CURRENT_FLAG + "," + UPDATED_IS_CURRENT_FLAG);

        // Get all the listValveManufacturerList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListValveManufacturerShouldNotBeFound("isCurrentFlag.in=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListValveManufacturersByIsCurrentFlagIsNullOrNotNull() throws Exception {
        // Initialize the database
        listValveManufacturerRepository.saveAndFlush(listValveManufacturer);

        // Get all the listValveManufacturerList where isCurrentFlag is not null
        defaultListValveManufacturerShouldBeFound("isCurrentFlag.specified=true");

        // Get all the listValveManufacturerList where isCurrentFlag is null
        defaultListValveManufacturerShouldNotBeFound("isCurrentFlag.specified=false");
    }

    @Test
    @Transactional
    public void getAllListValveManufacturersByIsCurrentFlagIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        listValveManufacturerRepository.saveAndFlush(listValveManufacturer);

        // Get all the listValveManufacturerList where isCurrentFlag greater than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListValveManufacturerShouldBeFound("isCurrentFlag.greaterOrEqualThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listValveManufacturerList where isCurrentFlag greater than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListValveManufacturerShouldNotBeFound("isCurrentFlag.greaterOrEqualThan=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListValveManufacturersByIsCurrentFlagIsLessThanSomething() throws Exception {
        // Initialize the database
        listValveManufacturerRepository.saveAndFlush(listValveManufacturer);

        // Get all the listValveManufacturerList where isCurrentFlag less than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListValveManufacturerShouldNotBeFound("isCurrentFlag.lessThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listValveManufacturerList where isCurrentFlag less than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListValveManufacturerShouldBeFound("isCurrentFlag.lessThan=" + UPDATED_IS_CURRENT_FLAG);
    }


    @Test
    @Transactional
    public void getAllListValveManufacturersByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        listValveManufacturerRepository.saveAndFlush(listValveManufacturer);

        // Get all the listValveManufacturerList where description equals to DEFAULT_DESCRIPTION
        defaultListValveManufacturerShouldBeFound("description.equals=" + DEFAULT_DESCRIPTION);

        // Get all the listValveManufacturerList where description equals to UPDATED_DESCRIPTION
        defaultListValveManufacturerShouldNotBeFound("description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListValveManufacturersByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        listValveManufacturerRepository.saveAndFlush(listValveManufacturer);

        // Get all the listValveManufacturerList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
        defaultListValveManufacturerShouldBeFound("description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION);

        // Get all the listValveManufacturerList where description equals to UPDATED_DESCRIPTION
        defaultListValveManufacturerShouldNotBeFound("description.in=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListValveManufacturersByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        listValveManufacturerRepository.saveAndFlush(listValveManufacturer);

        // Get all the listValveManufacturerList where description is not null
        defaultListValveManufacturerShouldBeFound("description.specified=true");

        // Get all the listValveManufacturerList where description is null
        defaultListValveManufacturerShouldNotBeFound("description.specified=false");
    }

    @Test
    @Transactional
    public void getAllListValveManufacturersByDateCreateIsEqualToSomething() throws Exception {
        // Initialize the database
        listValveManufacturerRepository.saveAndFlush(listValveManufacturer);

        // Get all the listValveManufacturerList where dateCreate equals to DEFAULT_DATE_CREATE
        defaultListValveManufacturerShouldBeFound("dateCreate.equals=" + DEFAULT_DATE_CREATE);

        // Get all the listValveManufacturerList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListValveManufacturerShouldNotBeFound("dateCreate.equals=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListValveManufacturersByDateCreateIsInShouldWork() throws Exception {
        // Initialize the database
        listValveManufacturerRepository.saveAndFlush(listValveManufacturer);

        // Get all the listValveManufacturerList where dateCreate in DEFAULT_DATE_CREATE or UPDATED_DATE_CREATE
        defaultListValveManufacturerShouldBeFound("dateCreate.in=" + DEFAULT_DATE_CREATE + "," + UPDATED_DATE_CREATE);

        // Get all the listValveManufacturerList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListValveManufacturerShouldNotBeFound("dateCreate.in=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListValveManufacturersByDateCreateIsNullOrNotNull() throws Exception {
        // Initialize the database
        listValveManufacturerRepository.saveAndFlush(listValveManufacturer);

        // Get all the listValveManufacturerList where dateCreate is not null
        defaultListValveManufacturerShouldBeFound("dateCreate.specified=true");

        // Get all the listValveManufacturerList where dateCreate is null
        defaultListValveManufacturerShouldNotBeFound("dateCreate.specified=false");
    }

    @Test
    @Transactional
    public void getAllListValveManufacturersByDateEditIsEqualToSomething() throws Exception {
        // Initialize the database
        listValveManufacturerRepository.saveAndFlush(listValveManufacturer);

        // Get all the listValveManufacturerList where dateEdit equals to DEFAULT_DATE_EDIT
        defaultListValveManufacturerShouldBeFound("dateEdit.equals=" + DEFAULT_DATE_EDIT);

        // Get all the listValveManufacturerList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListValveManufacturerShouldNotBeFound("dateEdit.equals=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListValveManufacturersByDateEditIsInShouldWork() throws Exception {
        // Initialize the database
        listValveManufacturerRepository.saveAndFlush(listValveManufacturer);

        // Get all the listValveManufacturerList where dateEdit in DEFAULT_DATE_EDIT or UPDATED_DATE_EDIT
        defaultListValveManufacturerShouldBeFound("dateEdit.in=" + DEFAULT_DATE_EDIT + "," + UPDATED_DATE_EDIT);

        // Get all the listValveManufacturerList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListValveManufacturerShouldNotBeFound("dateEdit.in=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListValveManufacturersByDateEditIsNullOrNotNull() throws Exception {
        // Initialize the database
        listValveManufacturerRepository.saveAndFlush(listValveManufacturer);

        // Get all the listValveManufacturerList where dateEdit is not null
        defaultListValveManufacturerShouldBeFound("dateEdit.specified=true");

        // Get all the listValveManufacturerList where dateEdit is null
        defaultListValveManufacturerShouldNotBeFound("dateEdit.specified=false");
    }

    @Test
    @Transactional
    public void getAllListValveManufacturersByCreatorIsEqualToSomething() throws Exception {
        // Initialize the database
        listValveManufacturerRepository.saveAndFlush(listValveManufacturer);

        // Get all the listValveManufacturerList where creator equals to DEFAULT_CREATOR
        defaultListValveManufacturerShouldBeFound("creator.equals=" + DEFAULT_CREATOR);

        // Get all the listValveManufacturerList where creator equals to UPDATED_CREATOR
        defaultListValveManufacturerShouldNotBeFound("creator.equals=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListValveManufacturersByCreatorIsInShouldWork() throws Exception {
        // Initialize the database
        listValveManufacturerRepository.saveAndFlush(listValveManufacturer);

        // Get all the listValveManufacturerList where creator in DEFAULT_CREATOR or UPDATED_CREATOR
        defaultListValveManufacturerShouldBeFound("creator.in=" + DEFAULT_CREATOR + "," + UPDATED_CREATOR);

        // Get all the listValveManufacturerList where creator equals to UPDATED_CREATOR
        defaultListValveManufacturerShouldNotBeFound("creator.in=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListValveManufacturersByCreatorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listValveManufacturerRepository.saveAndFlush(listValveManufacturer);

        // Get all the listValveManufacturerList where creator is not null
        defaultListValveManufacturerShouldBeFound("creator.specified=true");

        // Get all the listValveManufacturerList where creator is null
        defaultListValveManufacturerShouldNotBeFound("creator.specified=false");
    }

    @Test
    @Transactional
    public void getAllListValveManufacturersByEditorIsEqualToSomething() throws Exception {
        // Initialize the database
        listValveManufacturerRepository.saveAndFlush(listValveManufacturer);

        // Get all the listValveManufacturerList where editor equals to DEFAULT_EDITOR
        defaultListValveManufacturerShouldBeFound("editor.equals=" + DEFAULT_EDITOR);

        // Get all the listValveManufacturerList where editor equals to UPDATED_EDITOR
        defaultListValveManufacturerShouldNotBeFound("editor.equals=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListValveManufacturersByEditorIsInShouldWork() throws Exception {
        // Initialize the database
        listValveManufacturerRepository.saveAndFlush(listValveManufacturer);

        // Get all the listValveManufacturerList where editor in DEFAULT_EDITOR or UPDATED_EDITOR
        defaultListValveManufacturerShouldBeFound("editor.in=" + DEFAULT_EDITOR + "," + UPDATED_EDITOR);

        // Get all the listValveManufacturerList where editor equals to UPDATED_EDITOR
        defaultListValveManufacturerShouldNotBeFound("editor.in=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListValveManufacturersByEditorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listValveManufacturerRepository.saveAndFlush(listValveManufacturer);

        // Get all the listValveManufacturerList where editor is not null
        defaultListValveManufacturerShouldBeFound("editor.specified=true");

        // Get all the listValveManufacturerList where editor is null
        defaultListValveManufacturerShouldNotBeFound("editor.specified=false");
    }

    @Test
    @Transactional
    public void getAllListValveManufacturersByValveHistIsEqualToSomething() throws Exception {
        // Initialize the database
        ValveHist valveHist = ValveHistResourceIT.createEntity(em);
        em.persist(valveHist);
        em.flush();
        listValveManufacturer.addValveHist(valveHist);
        listValveManufacturerRepository.saveAndFlush(listValveManufacturer);
        Long valveHistId = valveHist.getId();

        // Get all the listValveManufacturerList where valveHist equals to valveHistId
        defaultListValveManufacturerShouldBeFound("valveHistId.equals=" + valveHistId);

        // Get all the listValveManufacturerList where valveHist equals to valveHistId + 1
        defaultListValveManufacturerShouldNotBeFound("valveHistId.equals=" + (valveHistId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultListValveManufacturerShouldBeFound(String filter) throws Exception {
        restListValveManufacturerMockMvc.perform(get("/api/list-valve-manufacturers?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listValveManufacturer.getId().intValue())))
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
        restListValveManufacturerMockMvc.perform(get("/api/list-valve-manufacturers/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultListValveManufacturerShouldNotBeFound(String filter) throws Exception {
        restListValveManufacturerMockMvc.perform(get("/api/list-valve-manufacturers?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restListValveManufacturerMockMvc.perform(get("/api/list-valve-manufacturers/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingListValveManufacturer() throws Exception {
        // Get the listValveManufacturer
        restListValveManufacturerMockMvc.perform(get("/api/list-valve-manufacturers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateListValveManufacturer() throws Exception {
        // Initialize the database
        listValveManufacturerRepository.saveAndFlush(listValveManufacturer);

        int databaseSizeBeforeUpdate = listValveManufacturerRepository.findAll().size();

        // Update the listValveManufacturer
        ListValveManufacturer updatedListValveManufacturer = listValveManufacturerRepository.findById(listValveManufacturer.getId()).get();
        // Disconnect from session so that the updates on updatedListValveManufacturer are not directly saved in db
        em.detach(updatedListValveManufacturer);
        updatedListValveManufacturer
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        ListValveManufacturerDTO listValveManufacturerDTO = listValveManufacturerMapper.toDto(updatedListValveManufacturer);

        restListValveManufacturerMockMvc.perform(put("/api/list-valve-manufacturers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listValveManufacturerDTO)))
            .andExpect(status().isOk());

        // Validate the ListValveManufacturer in the database
        List<ListValveManufacturer> listValveManufacturerList = listValveManufacturerRepository.findAll();
        assertThat(listValveManufacturerList).hasSize(databaseSizeBeforeUpdate);
        ListValveManufacturer testListValveManufacturer = listValveManufacturerList.get(listValveManufacturerList.size() - 1);
        assertThat(testListValveManufacturer.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testListValveManufacturer.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testListValveManufacturer.getFullName()).isEqualTo(UPDATED_FULL_NAME);
        assertThat(testListValveManufacturer.getIsCurrentFlag()).isEqualTo(UPDATED_IS_CURRENT_FLAG);
        assertThat(testListValveManufacturer.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testListValveManufacturer.getDateCreate()).isEqualTo(UPDATED_DATE_CREATE);
        assertThat(testListValveManufacturer.getDateEdit()).isEqualTo(UPDATED_DATE_EDIT);
        assertThat(testListValveManufacturer.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testListValveManufacturer.getEditor()).isEqualTo(UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void updateNonExistingListValveManufacturer() throws Exception {
        int databaseSizeBeforeUpdate = listValveManufacturerRepository.findAll().size();

        // Create the ListValveManufacturer
        ListValveManufacturerDTO listValveManufacturerDTO = listValveManufacturerMapper.toDto(listValveManufacturer);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restListValveManufacturerMockMvc.perform(put("/api/list-valve-manufacturers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listValveManufacturerDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListValveManufacturer in the database
        List<ListValveManufacturer> listValveManufacturerList = listValveManufacturerRepository.findAll();
        assertThat(listValveManufacturerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteListValveManufacturer() throws Exception {
        // Initialize the database
        listValveManufacturerRepository.saveAndFlush(listValveManufacturer);

        int databaseSizeBeforeDelete = listValveManufacturerRepository.findAll().size();

        // Delete the listValveManufacturer
        restListValveManufacturerMockMvc.perform(delete("/api/list-valve-manufacturers/{id}", listValveManufacturer.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<ListValveManufacturer> listValveManufacturerList = listValveManufacturerRepository.findAll();
        assertThat(listValveManufacturerList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListValveManufacturer.class);
        ListValveManufacturer listValveManufacturer1 = new ListValveManufacturer();
        listValveManufacturer1.setId(1L);
        ListValveManufacturer listValveManufacturer2 = new ListValveManufacturer();
        listValveManufacturer2.setId(listValveManufacturer1.getId());
        assertThat(listValveManufacturer1).isEqualTo(listValveManufacturer2);
        listValveManufacturer2.setId(2L);
        assertThat(listValveManufacturer1).isNotEqualTo(listValveManufacturer2);
        listValveManufacturer1.setId(null);
        assertThat(listValveManufacturer1).isNotEqualTo(listValveManufacturer2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListValveManufacturerDTO.class);
        ListValveManufacturerDTO listValveManufacturerDTO1 = new ListValveManufacturerDTO();
        listValveManufacturerDTO1.setId(1L);
        ListValveManufacturerDTO listValveManufacturerDTO2 = new ListValveManufacturerDTO();
        assertThat(listValveManufacturerDTO1).isNotEqualTo(listValveManufacturerDTO2);
        listValveManufacturerDTO2.setId(listValveManufacturerDTO1.getId());
        assertThat(listValveManufacturerDTO1).isEqualTo(listValveManufacturerDTO2);
        listValveManufacturerDTO2.setId(2L);
        assertThat(listValveManufacturerDTO1).isNotEqualTo(listValveManufacturerDTO2);
        listValveManufacturerDTO1.setId(null);
        assertThat(listValveManufacturerDTO1).isNotEqualTo(listValveManufacturerDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(listValveManufacturerMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(listValveManufacturerMapper.fromId(null)).isNull();
    }
}
