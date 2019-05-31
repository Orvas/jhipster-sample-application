package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.ListBucklarrSpecification;
import io.github.jhipster.application.domain.BuckleArrestorHist;
import io.github.jhipster.application.repository.ListBucklarrSpecificationRepository;
import io.github.jhipster.application.service.ListBucklarrSpecificationService;
import io.github.jhipster.application.service.dto.ListBucklarrSpecificationDTO;
import io.github.jhipster.application.service.mapper.ListBucklarrSpecificationMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.ListBucklarrSpecificationCriteria;
import io.github.jhipster.application.service.ListBucklarrSpecificationQueryService;

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
 * Integration tests for the {@Link ListBucklarrSpecificationResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class ListBucklarrSpecificationResourceIT {

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
    private ListBucklarrSpecificationRepository listBucklarrSpecificationRepository;

    @Autowired
    private ListBucklarrSpecificationMapper listBucklarrSpecificationMapper;

    @Autowired
    private ListBucklarrSpecificationService listBucklarrSpecificationService;

    @Autowired
    private ListBucklarrSpecificationQueryService listBucklarrSpecificationQueryService;

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

    private MockMvc restListBucklarrSpecificationMockMvc;

    private ListBucklarrSpecification listBucklarrSpecification;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ListBucklarrSpecificationResource listBucklarrSpecificationResource = new ListBucklarrSpecificationResource(listBucklarrSpecificationService, listBucklarrSpecificationQueryService);
        this.restListBucklarrSpecificationMockMvc = MockMvcBuilders.standaloneSetup(listBucklarrSpecificationResource)
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
    public static ListBucklarrSpecification createEntity(EntityManager em) {
        ListBucklarrSpecification listBucklarrSpecification = new ListBucklarrSpecification()
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME)
            .fullName(DEFAULT_FULL_NAME)
            .isCurrentFlag(DEFAULT_IS_CURRENT_FLAG)
            .description(DEFAULT_DESCRIPTION)
            .dateCreate(DEFAULT_DATE_CREATE)
            .dateEdit(DEFAULT_DATE_EDIT)
            .creator(DEFAULT_CREATOR)
            .editor(DEFAULT_EDITOR);
        return listBucklarrSpecification;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ListBucklarrSpecification createUpdatedEntity(EntityManager em) {
        ListBucklarrSpecification listBucklarrSpecification = new ListBucklarrSpecification()
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        return listBucklarrSpecification;
    }

    @BeforeEach
    public void initTest() {
        listBucklarrSpecification = createEntity(em);
    }

    @Test
    @Transactional
    public void createListBucklarrSpecification() throws Exception {
        int databaseSizeBeforeCreate = listBucklarrSpecificationRepository.findAll().size();

        // Create the ListBucklarrSpecification
        ListBucklarrSpecificationDTO listBucklarrSpecificationDTO = listBucklarrSpecificationMapper.toDto(listBucklarrSpecification);
        restListBucklarrSpecificationMockMvc.perform(post("/api/list-bucklarr-specifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listBucklarrSpecificationDTO)))
            .andExpect(status().isCreated());

        // Validate the ListBucklarrSpecification in the database
        List<ListBucklarrSpecification> listBucklarrSpecificationList = listBucklarrSpecificationRepository.findAll();
        assertThat(listBucklarrSpecificationList).hasSize(databaseSizeBeforeCreate + 1);
        ListBucklarrSpecification testListBucklarrSpecification = listBucklarrSpecificationList.get(listBucklarrSpecificationList.size() - 1);
        assertThat(testListBucklarrSpecification.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testListBucklarrSpecification.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testListBucklarrSpecification.getFullName()).isEqualTo(DEFAULT_FULL_NAME);
        assertThat(testListBucklarrSpecification.getIsCurrentFlag()).isEqualTo(DEFAULT_IS_CURRENT_FLAG);
        assertThat(testListBucklarrSpecification.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testListBucklarrSpecification.getDateCreate()).isEqualTo(DEFAULT_DATE_CREATE);
        assertThat(testListBucklarrSpecification.getDateEdit()).isEqualTo(DEFAULT_DATE_EDIT);
        assertThat(testListBucklarrSpecification.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testListBucklarrSpecification.getEditor()).isEqualTo(DEFAULT_EDITOR);
    }

    @Test
    @Transactional
    public void createListBucklarrSpecificationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = listBucklarrSpecificationRepository.findAll().size();

        // Create the ListBucklarrSpecification with an existing ID
        listBucklarrSpecification.setId(1L);
        ListBucklarrSpecificationDTO listBucklarrSpecificationDTO = listBucklarrSpecificationMapper.toDto(listBucklarrSpecification);

        // An entity with an existing ID cannot be created, so this API call must fail
        restListBucklarrSpecificationMockMvc.perform(post("/api/list-bucklarr-specifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listBucklarrSpecificationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListBucklarrSpecification in the database
        List<ListBucklarrSpecification> listBucklarrSpecificationList = listBucklarrSpecificationRepository.findAll();
        assertThat(listBucklarrSpecificationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = listBucklarrSpecificationRepository.findAll().size();
        // set the field null
        listBucklarrSpecification.setCode(null);

        // Create the ListBucklarrSpecification, which fails.
        ListBucklarrSpecificationDTO listBucklarrSpecificationDTO = listBucklarrSpecificationMapper.toDto(listBucklarrSpecification);

        restListBucklarrSpecificationMockMvc.perform(post("/api/list-bucklarr-specifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listBucklarrSpecificationDTO)))
            .andExpect(status().isBadRequest());

        List<ListBucklarrSpecification> listBucklarrSpecificationList = listBucklarrSpecificationRepository.findAll();
        assertThat(listBucklarrSpecificationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listBucklarrSpecificationRepository.findAll().size();
        // set the field null
        listBucklarrSpecification.setName(null);

        // Create the ListBucklarrSpecification, which fails.
        ListBucklarrSpecificationDTO listBucklarrSpecificationDTO = listBucklarrSpecificationMapper.toDto(listBucklarrSpecification);

        restListBucklarrSpecificationMockMvc.perform(post("/api/list-bucklarr-specifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listBucklarrSpecificationDTO)))
            .andExpect(status().isBadRequest());

        List<ListBucklarrSpecification> listBucklarrSpecificationList = listBucklarrSpecificationRepository.findAll();
        assertThat(listBucklarrSpecificationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFullNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listBucklarrSpecificationRepository.findAll().size();
        // set the field null
        listBucklarrSpecification.setFullName(null);

        // Create the ListBucklarrSpecification, which fails.
        ListBucklarrSpecificationDTO listBucklarrSpecificationDTO = listBucklarrSpecificationMapper.toDto(listBucklarrSpecification);

        restListBucklarrSpecificationMockMvc.perform(post("/api/list-bucklarr-specifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listBucklarrSpecificationDTO)))
            .andExpect(status().isBadRequest());

        List<ListBucklarrSpecification> listBucklarrSpecificationList = listBucklarrSpecificationRepository.findAll();
        assertThat(listBucklarrSpecificationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsCurrentFlagIsRequired() throws Exception {
        int databaseSizeBeforeTest = listBucklarrSpecificationRepository.findAll().size();
        // set the field null
        listBucklarrSpecification.setIsCurrentFlag(null);

        // Create the ListBucklarrSpecification, which fails.
        ListBucklarrSpecificationDTO listBucklarrSpecificationDTO = listBucklarrSpecificationMapper.toDto(listBucklarrSpecification);

        restListBucklarrSpecificationMockMvc.perform(post("/api/list-bucklarr-specifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listBucklarrSpecificationDTO)))
            .andExpect(status().isBadRequest());

        List<ListBucklarrSpecification> listBucklarrSpecificationList = listBucklarrSpecificationRepository.findAll();
        assertThat(listBucklarrSpecificationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllListBucklarrSpecifications() throws Exception {
        // Initialize the database
        listBucklarrSpecificationRepository.saveAndFlush(listBucklarrSpecification);

        // Get all the listBucklarrSpecificationList
        restListBucklarrSpecificationMockMvc.perform(get("/api/list-bucklarr-specifications?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listBucklarrSpecification.getId().intValue())))
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
    public void getListBucklarrSpecification() throws Exception {
        // Initialize the database
        listBucklarrSpecificationRepository.saveAndFlush(listBucklarrSpecification);

        // Get the listBucklarrSpecification
        restListBucklarrSpecificationMockMvc.perform(get("/api/list-bucklarr-specifications/{id}", listBucklarrSpecification.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(listBucklarrSpecification.getId().intValue()))
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
    public void getAllListBucklarrSpecificationsByCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        listBucklarrSpecificationRepository.saveAndFlush(listBucklarrSpecification);

        // Get all the listBucklarrSpecificationList where code equals to DEFAULT_CODE
        defaultListBucklarrSpecificationShouldBeFound("code.equals=" + DEFAULT_CODE);

        // Get all the listBucklarrSpecificationList where code equals to UPDATED_CODE
        defaultListBucklarrSpecificationShouldNotBeFound("code.equals=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListBucklarrSpecificationsByCodeIsInShouldWork() throws Exception {
        // Initialize the database
        listBucklarrSpecificationRepository.saveAndFlush(listBucklarrSpecification);

        // Get all the listBucklarrSpecificationList where code in DEFAULT_CODE or UPDATED_CODE
        defaultListBucklarrSpecificationShouldBeFound("code.in=" + DEFAULT_CODE + "," + UPDATED_CODE);

        // Get all the listBucklarrSpecificationList where code equals to UPDATED_CODE
        defaultListBucklarrSpecificationShouldNotBeFound("code.in=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListBucklarrSpecificationsByCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        listBucklarrSpecificationRepository.saveAndFlush(listBucklarrSpecification);

        // Get all the listBucklarrSpecificationList where code is not null
        defaultListBucklarrSpecificationShouldBeFound("code.specified=true");

        // Get all the listBucklarrSpecificationList where code is null
        defaultListBucklarrSpecificationShouldNotBeFound("code.specified=false");
    }

    @Test
    @Transactional
    public void getAllListBucklarrSpecificationsByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listBucklarrSpecificationRepository.saveAndFlush(listBucklarrSpecification);

        // Get all the listBucklarrSpecificationList where name equals to DEFAULT_NAME
        defaultListBucklarrSpecificationShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the listBucklarrSpecificationList where name equals to UPDATED_NAME
        defaultListBucklarrSpecificationShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListBucklarrSpecificationsByNameIsInShouldWork() throws Exception {
        // Initialize the database
        listBucklarrSpecificationRepository.saveAndFlush(listBucklarrSpecification);

        // Get all the listBucklarrSpecificationList where name in DEFAULT_NAME or UPDATED_NAME
        defaultListBucklarrSpecificationShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the listBucklarrSpecificationList where name equals to UPDATED_NAME
        defaultListBucklarrSpecificationShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListBucklarrSpecificationsByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listBucklarrSpecificationRepository.saveAndFlush(listBucklarrSpecification);

        // Get all the listBucklarrSpecificationList where name is not null
        defaultListBucklarrSpecificationShouldBeFound("name.specified=true");

        // Get all the listBucklarrSpecificationList where name is null
        defaultListBucklarrSpecificationShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    public void getAllListBucklarrSpecificationsByFullNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listBucklarrSpecificationRepository.saveAndFlush(listBucklarrSpecification);

        // Get all the listBucklarrSpecificationList where fullName equals to DEFAULT_FULL_NAME
        defaultListBucklarrSpecificationShouldBeFound("fullName.equals=" + DEFAULT_FULL_NAME);

        // Get all the listBucklarrSpecificationList where fullName equals to UPDATED_FULL_NAME
        defaultListBucklarrSpecificationShouldNotBeFound("fullName.equals=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListBucklarrSpecificationsByFullNameIsInShouldWork() throws Exception {
        // Initialize the database
        listBucklarrSpecificationRepository.saveAndFlush(listBucklarrSpecification);

        // Get all the listBucklarrSpecificationList where fullName in DEFAULT_FULL_NAME or UPDATED_FULL_NAME
        defaultListBucklarrSpecificationShouldBeFound("fullName.in=" + DEFAULT_FULL_NAME + "," + UPDATED_FULL_NAME);

        // Get all the listBucklarrSpecificationList where fullName equals to UPDATED_FULL_NAME
        defaultListBucklarrSpecificationShouldNotBeFound("fullName.in=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListBucklarrSpecificationsByFullNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listBucklarrSpecificationRepository.saveAndFlush(listBucklarrSpecification);

        // Get all the listBucklarrSpecificationList where fullName is not null
        defaultListBucklarrSpecificationShouldBeFound("fullName.specified=true");

        // Get all the listBucklarrSpecificationList where fullName is null
        defaultListBucklarrSpecificationShouldNotBeFound("fullName.specified=false");
    }

    @Test
    @Transactional
    public void getAllListBucklarrSpecificationsByIsCurrentFlagIsEqualToSomething() throws Exception {
        // Initialize the database
        listBucklarrSpecificationRepository.saveAndFlush(listBucklarrSpecification);

        // Get all the listBucklarrSpecificationList where isCurrentFlag equals to DEFAULT_IS_CURRENT_FLAG
        defaultListBucklarrSpecificationShouldBeFound("isCurrentFlag.equals=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listBucklarrSpecificationList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListBucklarrSpecificationShouldNotBeFound("isCurrentFlag.equals=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListBucklarrSpecificationsByIsCurrentFlagIsInShouldWork() throws Exception {
        // Initialize the database
        listBucklarrSpecificationRepository.saveAndFlush(listBucklarrSpecification);

        // Get all the listBucklarrSpecificationList where isCurrentFlag in DEFAULT_IS_CURRENT_FLAG or UPDATED_IS_CURRENT_FLAG
        defaultListBucklarrSpecificationShouldBeFound("isCurrentFlag.in=" + DEFAULT_IS_CURRENT_FLAG + "," + UPDATED_IS_CURRENT_FLAG);

        // Get all the listBucklarrSpecificationList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListBucklarrSpecificationShouldNotBeFound("isCurrentFlag.in=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListBucklarrSpecificationsByIsCurrentFlagIsNullOrNotNull() throws Exception {
        // Initialize the database
        listBucklarrSpecificationRepository.saveAndFlush(listBucklarrSpecification);

        // Get all the listBucklarrSpecificationList where isCurrentFlag is not null
        defaultListBucklarrSpecificationShouldBeFound("isCurrentFlag.specified=true");

        // Get all the listBucklarrSpecificationList where isCurrentFlag is null
        defaultListBucklarrSpecificationShouldNotBeFound("isCurrentFlag.specified=false");
    }

    @Test
    @Transactional
    public void getAllListBucklarrSpecificationsByIsCurrentFlagIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        listBucklarrSpecificationRepository.saveAndFlush(listBucklarrSpecification);

        // Get all the listBucklarrSpecificationList where isCurrentFlag greater than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListBucklarrSpecificationShouldBeFound("isCurrentFlag.greaterOrEqualThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listBucklarrSpecificationList where isCurrentFlag greater than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListBucklarrSpecificationShouldNotBeFound("isCurrentFlag.greaterOrEqualThan=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListBucklarrSpecificationsByIsCurrentFlagIsLessThanSomething() throws Exception {
        // Initialize the database
        listBucklarrSpecificationRepository.saveAndFlush(listBucklarrSpecification);

        // Get all the listBucklarrSpecificationList where isCurrentFlag less than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListBucklarrSpecificationShouldNotBeFound("isCurrentFlag.lessThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listBucklarrSpecificationList where isCurrentFlag less than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListBucklarrSpecificationShouldBeFound("isCurrentFlag.lessThan=" + UPDATED_IS_CURRENT_FLAG);
    }


    @Test
    @Transactional
    public void getAllListBucklarrSpecificationsByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        listBucklarrSpecificationRepository.saveAndFlush(listBucklarrSpecification);

        // Get all the listBucklarrSpecificationList where description equals to DEFAULT_DESCRIPTION
        defaultListBucklarrSpecificationShouldBeFound("description.equals=" + DEFAULT_DESCRIPTION);

        // Get all the listBucklarrSpecificationList where description equals to UPDATED_DESCRIPTION
        defaultListBucklarrSpecificationShouldNotBeFound("description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListBucklarrSpecificationsByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        listBucklarrSpecificationRepository.saveAndFlush(listBucklarrSpecification);

        // Get all the listBucklarrSpecificationList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
        defaultListBucklarrSpecificationShouldBeFound("description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION);

        // Get all the listBucklarrSpecificationList where description equals to UPDATED_DESCRIPTION
        defaultListBucklarrSpecificationShouldNotBeFound("description.in=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListBucklarrSpecificationsByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        listBucklarrSpecificationRepository.saveAndFlush(listBucklarrSpecification);

        // Get all the listBucklarrSpecificationList where description is not null
        defaultListBucklarrSpecificationShouldBeFound("description.specified=true");

        // Get all the listBucklarrSpecificationList where description is null
        defaultListBucklarrSpecificationShouldNotBeFound("description.specified=false");
    }

    @Test
    @Transactional
    public void getAllListBucklarrSpecificationsByDateCreateIsEqualToSomething() throws Exception {
        // Initialize the database
        listBucklarrSpecificationRepository.saveAndFlush(listBucklarrSpecification);

        // Get all the listBucklarrSpecificationList where dateCreate equals to DEFAULT_DATE_CREATE
        defaultListBucklarrSpecificationShouldBeFound("dateCreate.equals=" + DEFAULT_DATE_CREATE);

        // Get all the listBucklarrSpecificationList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListBucklarrSpecificationShouldNotBeFound("dateCreate.equals=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListBucklarrSpecificationsByDateCreateIsInShouldWork() throws Exception {
        // Initialize the database
        listBucklarrSpecificationRepository.saveAndFlush(listBucklarrSpecification);

        // Get all the listBucklarrSpecificationList where dateCreate in DEFAULT_DATE_CREATE or UPDATED_DATE_CREATE
        defaultListBucklarrSpecificationShouldBeFound("dateCreate.in=" + DEFAULT_DATE_CREATE + "," + UPDATED_DATE_CREATE);

        // Get all the listBucklarrSpecificationList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListBucklarrSpecificationShouldNotBeFound("dateCreate.in=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListBucklarrSpecificationsByDateCreateIsNullOrNotNull() throws Exception {
        // Initialize the database
        listBucklarrSpecificationRepository.saveAndFlush(listBucklarrSpecification);

        // Get all the listBucklarrSpecificationList where dateCreate is not null
        defaultListBucklarrSpecificationShouldBeFound("dateCreate.specified=true");

        // Get all the listBucklarrSpecificationList where dateCreate is null
        defaultListBucklarrSpecificationShouldNotBeFound("dateCreate.specified=false");
    }

    @Test
    @Transactional
    public void getAllListBucklarrSpecificationsByDateEditIsEqualToSomething() throws Exception {
        // Initialize the database
        listBucklarrSpecificationRepository.saveAndFlush(listBucklarrSpecification);

        // Get all the listBucklarrSpecificationList where dateEdit equals to DEFAULT_DATE_EDIT
        defaultListBucklarrSpecificationShouldBeFound("dateEdit.equals=" + DEFAULT_DATE_EDIT);

        // Get all the listBucklarrSpecificationList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListBucklarrSpecificationShouldNotBeFound("dateEdit.equals=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListBucklarrSpecificationsByDateEditIsInShouldWork() throws Exception {
        // Initialize the database
        listBucklarrSpecificationRepository.saveAndFlush(listBucklarrSpecification);

        // Get all the listBucklarrSpecificationList where dateEdit in DEFAULT_DATE_EDIT or UPDATED_DATE_EDIT
        defaultListBucklarrSpecificationShouldBeFound("dateEdit.in=" + DEFAULT_DATE_EDIT + "," + UPDATED_DATE_EDIT);

        // Get all the listBucklarrSpecificationList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListBucklarrSpecificationShouldNotBeFound("dateEdit.in=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListBucklarrSpecificationsByDateEditIsNullOrNotNull() throws Exception {
        // Initialize the database
        listBucklarrSpecificationRepository.saveAndFlush(listBucklarrSpecification);

        // Get all the listBucklarrSpecificationList where dateEdit is not null
        defaultListBucklarrSpecificationShouldBeFound("dateEdit.specified=true");

        // Get all the listBucklarrSpecificationList where dateEdit is null
        defaultListBucklarrSpecificationShouldNotBeFound("dateEdit.specified=false");
    }

    @Test
    @Transactional
    public void getAllListBucklarrSpecificationsByCreatorIsEqualToSomething() throws Exception {
        // Initialize the database
        listBucklarrSpecificationRepository.saveAndFlush(listBucklarrSpecification);

        // Get all the listBucklarrSpecificationList where creator equals to DEFAULT_CREATOR
        defaultListBucklarrSpecificationShouldBeFound("creator.equals=" + DEFAULT_CREATOR);

        // Get all the listBucklarrSpecificationList where creator equals to UPDATED_CREATOR
        defaultListBucklarrSpecificationShouldNotBeFound("creator.equals=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListBucklarrSpecificationsByCreatorIsInShouldWork() throws Exception {
        // Initialize the database
        listBucklarrSpecificationRepository.saveAndFlush(listBucklarrSpecification);

        // Get all the listBucklarrSpecificationList where creator in DEFAULT_CREATOR or UPDATED_CREATOR
        defaultListBucklarrSpecificationShouldBeFound("creator.in=" + DEFAULT_CREATOR + "," + UPDATED_CREATOR);

        // Get all the listBucklarrSpecificationList where creator equals to UPDATED_CREATOR
        defaultListBucklarrSpecificationShouldNotBeFound("creator.in=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListBucklarrSpecificationsByCreatorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listBucklarrSpecificationRepository.saveAndFlush(listBucklarrSpecification);

        // Get all the listBucklarrSpecificationList where creator is not null
        defaultListBucklarrSpecificationShouldBeFound("creator.specified=true");

        // Get all the listBucklarrSpecificationList where creator is null
        defaultListBucklarrSpecificationShouldNotBeFound("creator.specified=false");
    }

    @Test
    @Transactional
    public void getAllListBucklarrSpecificationsByEditorIsEqualToSomething() throws Exception {
        // Initialize the database
        listBucklarrSpecificationRepository.saveAndFlush(listBucklarrSpecification);

        // Get all the listBucklarrSpecificationList where editor equals to DEFAULT_EDITOR
        defaultListBucklarrSpecificationShouldBeFound("editor.equals=" + DEFAULT_EDITOR);

        // Get all the listBucklarrSpecificationList where editor equals to UPDATED_EDITOR
        defaultListBucklarrSpecificationShouldNotBeFound("editor.equals=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListBucklarrSpecificationsByEditorIsInShouldWork() throws Exception {
        // Initialize the database
        listBucklarrSpecificationRepository.saveAndFlush(listBucklarrSpecification);

        // Get all the listBucklarrSpecificationList where editor in DEFAULT_EDITOR or UPDATED_EDITOR
        defaultListBucklarrSpecificationShouldBeFound("editor.in=" + DEFAULT_EDITOR + "," + UPDATED_EDITOR);

        // Get all the listBucklarrSpecificationList where editor equals to UPDATED_EDITOR
        defaultListBucklarrSpecificationShouldNotBeFound("editor.in=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListBucklarrSpecificationsByEditorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listBucklarrSpecificationRepository.saveAndFlush(listBucklarrSpecification);

        // Get all the listBucklarrSpecificationList where editor is not null
        defaultListBucklarrSpecificationShouldBeFound("editor.specified=true");

        // Get all the listBucklarrSpecificationList where editor is null
        defaultListBucklarrSpecificationShouldNotBeFound("editor.specified=false");
    }

    @Test
    @Transactional
    public void getAllListBucklarrSpecificationsByBuckleArrestorHistIsEqualToSomething() throws Exception {
        // Initialize the database
        BuckleArrestorHist buckleArrestorHist = BuckleArrestorHistResourceIT.createEntity(em);
        em.persist(buckleArrestorHist);
        em.flush();
        listBucklarrSpecification.addBuckleArrestorHist(buckleArrestorHist);
        listBucklarrSpecificationRepository.saveAndFlush(listBucklarrSpecification);
        Long buckleArrestorHistId = buckleArrestorHist.getId();

        // Get all the listBucklarrSpecificationList where buckleArrestorHist equals to buckleArrestorHistId
        defaultListBucklarrSpecificationShouldBeFound("buckleArrestorHistId.equals=" + buckleArrestorHistId);

        // Get all the listBucklarrSpecificationList where buckleArrestorHist equals to buckleArrestorHistId + 1
        defaultListBucklarrSpecificationShouldNotBeFound("buckleArrestorHistId.equals=" + (buckleArrestorHistId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultListBucklarrSpecificationShouldBeFound(String filter) throws Exception {
        restListBucklarrSpecificationMockMvc.perform(get("/api/list-bucklarr-specifications?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listBucklarrSpecification.getId().intValue())))
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
        restListBucklarrSpecificationMockMvc.perform(get("/api/list-bucklarr-specifications/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultListBucklarrSpecificationShouldNotBeFound(String filter) throws Exception {
        restListBucklarrSpecificationMockMvc.perform(get("/api/list-bucklarr-specifications?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restListBucklarrSpecificationMockMvc.perform(get("/api/list-bucklarr-specifications/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingListBucklarrSpecification() throws Exception {
        // Get the listBucklarrSpecification
        restListBucklarrSpecificationMockMvc.perform(get("/api/list-bucklarr-specifications/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateListBucklarrSpecification() throws Exception {
        // Initialize the database
        listBucklarrSpecificationRepository.saveAndFlush(listBucklarrSpecification);

        int databaseSizeBeforeUpdate = listBucklarrSpecificationRepository.findAll().size();

        // Update the listBucklarrSpecification
        ListBucklarrSpecification updatedListBucklarrSpecification = listBucklarrSpecificationRepository.findById(listBucklarrSpecification.getId()).get();
        // Disconnect from session so that the updates on updatedListBucklarrSpecification are not directly saved in db
        em.detach(updatedListBucklarrSpecification);
        updatedListBucklarrSpecification
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        ListBucklarrSpecificationDTO listBucklarrSpecificationDTO = listBucklarrSpecificationMapper.toDto(updatedListBucklarrSpecification);

        restListBucklarrSpecificationMockMvc.perform(put("/api/list-bucklarr-specifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listBucklarrSpecificationDTO)))
            .andExpect(status().isOk());

        // Validate the ListBucklarrSpecification in the database
        List<ListBucklarrSpecification> listBucklarrSpecificationList = listBucklarrSpecificationRepository.findAll();
        assertThat(listBucklarrSpecificationList).hasSize(databaseSizeBeforeUpdate);
        ListBucklarrSpecification testListBucklarrSpecification = listBucklarrSpecificationList.get(listBucklarrSpecificationList.size() - 1);
        assertThat(testListBucklarrSpecification.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testListBucklarrSpecification.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testListBucklarrSpecification.getFullName()).isEqualTo(UPDATED_FULL_NAME);
        assertThat(testListBucklarrSpecification.getIsCurrentFlag()).isEqualTo(UPDATED_IS_CURRENT_FLAG);
        assertThat(testListBucklarrSpecification.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testListBucklarrSpecification.getDateCreate()).isEqualTo(UPDATED_DATE_CREATE);
        assertThat(testListBucklarrSpecification.getDateEdit()).isEqualTo(UPDATED_DATE_EDIT);
        assertThat(testListBucklarrSpecification.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testListBucklarrSpecification.getEditor()).isEqualTo(UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void updateNonExistingListBucklarrSpecification() throws Exception {
        int databaseSizeBeforeUpdate = listBucklarrSpecificationRepository.findAll().size();

        // Create the ListBucklarrSpecification
        ListBucklarrSpecificationDTO listBucklarrSpecificationDTO = listBucklarrSpecificationMapper.toDto(listBucklarrSpecification);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restListBucklarrSpecificationMockMvc.perform(put("/api/list-bucklarr-specifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listBucklarrSpecificationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListBucklarrSpecification in the database
        List<ListBucklarrSpecification> listBucklarrSpecificationList = listBucklarrSpecificationRepository.findAll();
        assertThat(listBucklarrSpecificationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteListBucklarrSpecification() throws Exception {
        // Initialize the database
        listBucklarrSpecificationRepository.saveAndFlush(listBucklarrSpecification);

        int databaseSizeBeforeDelete = listBucklarrSpecificationRepository.findAll().size();

        // Delete the listBucklarrSpecification
        restListBucklarrSpecificationMockMvc.perform(delete("/api/list-bucklarr-specifications/{id}", listBucklarrSpecification.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<ListBucklarrSpecification> listBucklarrSpecificationList = listBucklarrSpecificationRepository.findAll();
        assertThat(listBucklarrSpecificationList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListBucklarrSpecification.class);
        ListBucklarrSpecification listBucklarrSpecification1 = new ListBucklarrSpecification();
        listBucklarrSpecification1.setId(1L);
        ListBucklarrSpecification listBucklarrSpecification2 = new ListBucklarrSpecification();
        listBucklarrSpecification2.setId(listBucklarrSpecification1.getId());
        assertThat(listBucklarrSpecification1).isEqualTo(listBucklarrSpecification2);
        listBucklarrSpecification2.setId(2L);
        assertThat(listBucklarrSpecification1).isNotEqualTo(listBucklarrSpecification2);
        listBucklarrSpecification1.setId(null);
        assertThat(listBucklarrSpecification1).isNotEqualTo(listBucklarrSpecification2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListBucklarrSpecificationDTO.class);
        ListBucklarrSpecificationDTO listBucklarrSpecificationDTO1 = new ListBucklarrSpecificationDTO();
        listBucklarrSpecificationDTO1.setId(1L);
        ListBucklarrSpecificationDTO listBucklarrSpecificationDTO2 = new ListBucklarrSpecificationDTO();
        assertThat(listBucklarrSpecificationDTO1).isNotEqualTo(listBucklarrSpecificationDTO2);
        listBucklarrSpecificationDTO2.setId(listBucklarrSpecificationDTO1.getId());
        assertThat(listBucklarrSpecificationDTO1).isEqualTo(listBucklarrSpecificationDTO2);
        listBucklarrSpecificationDTO2.setId(2L);
        assertThat(listBucklarrSpecificationDTO1).isNotEqualTo(listBucklarrSpecificationDTO2);
        listBucklarrSpecificationDTO1.setId(null);
        assertThat(listBucklarrSpecificationDTO1).isNotEqualTo(listBucklarrSpecificationDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(listBucklarrSpecificationMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(listBucklarrSpecificationMapper.fromId(null)).isNull();
    }
}
