package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.ListPipejointSpecification;
import io.github.jhipster.application.domain.PipejointHist;
import io.github.jhipster.application.repository.ListPipejointSpecificationRepository;
import io.github.jhipster.application.service.ListPipejointSpecificationService;
import io.github.jhipster.application.service.dto.ListPipejointSpecificationDTO;
import io.github.jhipster.application.service.mapper.ListPipejointSpecificationMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.ListPipejointSpecificationCriteria;
import io.github.jhipster.application.service.ListPipejointSpecificationQueryService;

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
 * Integration tests for the {@Link ListPipejointSpecificationResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class ListPipejointSpecificationResourceIT {

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
    private ListPipejointSpecificationRepository listPipejointSpecificationRepository;

    @Autowired
    private ListPipejointSpecificationMapper listPipejointSpecificationMapper;

    @Autowired
    private ListPipejointSpecificationService listPipejointSpecificationService;

    @Autowired
    private ListPipejointSpecificationQueryService listPipejointSpecificationQueryService;

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

    private MockMvc restListPipejointSpecificationMockMvc;

    private ListPipejointSpecification listPipejointSpecification;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ListPipejointSpecificationResource listPipejointSpecificationResource = new ListPipejointSpecificationResource(listPipejointSpecificationService, listPipejointSpecificationQueryService);
        this.restListPipejointSpecificationMockMvc = MockMvcBuilders.standaloneSetup(listPipejointSpecificationResource)
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
    public static ListPipejointSpecification createEntity(EntityManager em) {
        ListPipejointSpecification listPipejointSpecification = new ListPipejointSpecification()
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME)
            .fullName(DEFAULT_FULL_NAME)
            .isCurrentFlag(DEFAULT_IS_CURRENT_FLAG)
            .description(DEFAULT_DESCRIPTION)
            .dateCreate(DEFAULT_DATE_CREATE)
            .dateEdit(DEFAULT_DATE_EDIT)
            .creator(DEFAULT_CREATOR)
            .editor(DEFAULT_EDITOR);
        return listPipejointSpecification;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ListPipejointSpecification createUpdatedEntity(EntityManager em) {
        ListPipejointSpecification listPipejointSpecification = new ListPipejointSpecification()
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        return listPipejointSpecification;
    }

    @BeforeEach
    public void initTest() {
        listPipejointSpecification = createEntity(em);
    }

    @Test
    @Transactional
    public void createListPipejointSpecification() throws Exception {
        int databaseSizeBeforeCreate = listPipejointSpecificationRepository.findAll().size();

        // Create the ListPipejointSpecification
        ListPipejointSpecificationDTO listPipejointSpecificationDTO = listPipejointSpecificationMapper.toDto(listPipejointSpecification);
        restListPipejointSpecificationMockMvc.perform(post("/api/list-pipejoint-specifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listPipejointSpecificationDTO)))
            .andExpect(status().isCreated());

        // Validate the ListPipejointSpecification in the database
        List<ListPipejointSpecification> listPipejointSpecificationList = listPipejointSpecificationRepository.findAll();
        assertThat(listPipejointSpecificationList).hasSize(databaseSizeBeforeCreate + 1);
        ListPipejointSpecification testListPipejointSpecification = listPipejointSpecificationList.get(listPipejointSpecificationList.size() - 1);
        assertThat(testListPipejointSpecification.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testListPipejointSpecification.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testListPipejointSpecification.getFullName()).isEqualTo(DEFAULT_FULL_NAME);
        assertThat(testListPipejointSpecification.getIsCurrentFlag()).isEqualTo(DEFAULT_IS_CURRENT_FLAG);
        assertThat(testListPipejointSpecification.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testListPipejointSpecification.getDateCreate()).isEqualTo(DEFAULT_DATE_CREATE);
        assertThat(testListPipejointSpecification.getDateEdit()).isEqualTo(DEFAULT_DATE_EDIT);
        assertThat(testListPipejointSpecification.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testListPipejointSpecification.getEditor()).isEqualTo(DEFAULT_EDITOR);
    }

    @Test
    @Transactional
    public void createListPipejointSpecificationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = listPipejointSpecificationRepository.findAll().size();

        // Create the ListPipejointSpecification with an existing ID
        listPipejointSpecification.setId(1L);
        ListPipejointSpecificationDTO listPipejointSpecificationDTO = listPipejointSpecificationMapper.toDto(listPipejointSpecification);

        // An entity with an existing ID cannot be created, so this API call must fail
        restListPipejointSpecificationMockMvc.perform(post("/api/list-pipejoint-specifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listPipejointSpecificationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListPipejointSpecification in the database
        List<ListPipejointSpecification> listPipejointSpecificationList = listPipejointSpecificationRepository.findAll();
        assertThat(listPipejointSpecificationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = listPipejointSpecificationRepository.findAll().size();
        // set the field null
        listPipejointSpecification.setCode(null);

        // Create the ListPipejointSpecification, which fails.
        ListPipejointSpecificationDTO listPipejointSpecificationDTO = listPipejointSpecificationMapper.toDto(listPipejointSpecification);

        restListPipejointSpecificationMockMvc.perform(post("/api/list-pipejoint-specifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listPipejointSpecificationDTO)))
            .andExpect(status().isBadRequest());

        List<ListPipejointSpecification> listPipejointSpecificationList = listPipejointSpecificationRepository.findAll();
        assertThat(listPipejointSpecificationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listPipejointSpecificationRepository.findAll().size();
        // set the field null
        listPipejointSpecification.setName(null);

        // Create the ListPipejointSpecification, which fails.
        ListPipejointSpecificationDTO listPipejointSpecificationDTO = listPipejointSpecificationMapper.toDto(listPipejointSpecification);

        restListPipejointSpecificationMockMvc.perform(post("/api/list-pipejoint-specifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listPipejointSpecificationDTO)))
            .andExpect(status().isBadRequest());

        List<ListPipejointSpecification> listPipejointSpecificationList = listPipejointSpecificationRepository.findAll();
        assertThat(listPipejointSpecificationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFullNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listPipejointSpecificationRepository.findAll().size();
        // set the field null
        listPipejointSpecification.setFullName(null);

        // Create the ListPipejointSpecification, which fails.
        ListPipejointSpecificationDTO listPipejointSpecificationDTO = listPipejointSpecificationMapper.toDto(listPipejointSpecification);

        restListPipejointSpecificationMockMvc.perform(post("/api/list-pipejoint-specifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listPipejointSpecificationDTO)))
            .andExpect(status().isBadRequest());

        List<ListPipejointSpecification> listPipejointSpecificationList = listPipejointSpecificationRepository.findAll();
        assertThat(listPipejointSpecificationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsCurrentFlagIsRequired() throws Exception {
        int databaseSizeBeforeTest = listPipejointSpecificationRepository.findAll().size();
        // set the field null
        listPipejointSpecification.setIsCurrentFlag(null);

        // Create the ListPipejointSpecification, which fails.
        ListPipejointSpecificationDTO listPipejointSpecificationDTO = listPipejointSpecificationMapper.toDto(listPipejointSpecification);

        restListPipejointSpecificationMockMvc.perform(post("/api/list-pipejoint-specifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listPipejointSpecificationDTO)))
            .andExpect(status().isBadRequest());

        List<ListPipejointSpecification> listPipejointSpecificationList = listPipejointSpecificationRepository.findAll();
        assertThat(listPipejointSpecificationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllListPipejointSpecifications() throws Exception {
        // Initialize the database
        listPipejointSpecificationRepository.saveAndFlush(listPipejointSpecification);

        // Get all the listPipejointSpecificationList
        restListPipejointSpecificationMockMvc.perform(get("/api/list-pipejoint-specifications?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listPipejointSpecification.getId().intValue())))
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
    public void getListPipejointSpecification() throws Exception {
        // Initialize the database
        listPipejointSpecificationRepository.saveAndFlush(listPipejointSpecification);

        // Get the listPipejointSpecification
        restListPipejointSpecificationMockMvc.perform(get("/api/list-pipejoint-specifications/{id}", listPipejointSpecification.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(listPipejointSpecification.getId().intValue()))
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
    public void getAllListPipejointSpecificationsByCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        listPipejointSpecificationRepository.saveAndFlush(listPipejointSpecification);

        // Get all the listPipejointSpecificationList where code equals to DEFAULT_CODE
        defaultListPipejointSpecificationShouldBeFound("code.equals=" + DEFAULT_CODE);

        // Get all the listPipejointSpecificationList where code equals to UPDATED_CODE
        defaultListPipejointSpecificationShouldNotBeFound("code.equals=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListPipejointSpecificationsByCodeIsInShouldWork() throws Exception {
        // Initialize the database
        listPipejointSpecificationRepository.saveAndFlush(listPipejointSpecification);

        // Get all the listPipejointSpecificationList where code in DEFAULT_CODE or UPDATED_CODE
        defaultListPipejointSpecificationShouldBeFound("code.in=" + DEFAULT_CODE + "," + UPDATED_CODE);

        // Get all the listPipejointSpecificationList where code equals to UPDATED_CODE
        defaultListPipejointSpecificationShouldNotBeFound("code.in=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListPipejointSpecificationsByCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        listPipejointSpecificationRepository.saveAndFlush(listPipejointSpecification);

        // Get all the listPipejointSpecificationList where code is not null
        defaultListPipejointSpecificationShouldBeFound("code.specified=true");

        // Get all the listPipejointSpecificationList where code is null
        defaultListPipejointSpecificationShouldNotBeFound("code.specified=false");
    }

    @Test
    @Transactional
    public void getAllListPipejointSpecificationsByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listPipejointSpecificationRepository.saveAndFlush(listPipejointSpecification);

        // Get all the listPipejointSpecificationList where name equals to DEFAULT_NAME
        defaultListPipejointSpecificationShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the listPipejointSpecificationList where name equals to UPDATED_NAME
        defaultListPipejointSpecificationShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListPipejointSpecificationsByNameIsInShouldWork() throws Exception {
        // Initialize the database
        listPipejointSpecificationRepository.saveAndFlush(listPipejointSpecification);

        // Get all the listPipejointSpecificationList where name in DEFAULT_NAME or UPDATED_NAME
        defaultListPipejointSpecificationShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the listPipejointSpecificationList where name equals to UPDATED_NAME
        defaultListPipejointSpecificationShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListPipejointSpecificationsByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listPipejointSpecificationRepository.saveAndFlush(listPipejointSpecification);

        // Get all the listPipejointSpecificationList where name is not null
        defaultListPipejointSpecificationShouldBeFound("name.specified=true");

        // Get all the listPipejointSpecificationList where name is null
        defaultListPipejointSpecificationShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    public void getAllListPipejointSpecificationsByFullNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listPipejointSpecificationRepository.saveAndFlush(listPipejointSpecification);

        // Get all the listPipejointSpecificationList where fullName equals to DEFAULT_FULL_NAME
        defaultListPipejointSpecificationShouldBeFound("fullName.equals=" + DEFAULT_FULL_NAME);

        // Get all the listPipejointSpecificationList where fullName equals to UPDATED_FULL_NAME
        defaultListPipejointSpecificationShouldNotBeFound("fullName.equals=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListPipejointSpecificationsByFullNameIsInShouldWork() throws Exception {
        // Initialize the database
        listPipejointSpecificationRepository.saveAndFlush(listPipejointSpecification);

        // Get all the listPipejointSpecificationList where fullName in DEFAULT_FULL_NAME or UPDATED_FULL_NAME
        defaultListPipejointSpecificationShouldBeFound("fullName.in=" + DEFAULT_FULL_NAME + "," + UPDATED_FULL_NAME);

        // Get all the listPipejointSpecificationList where fullName equals to UPDATED_FULL_NAME
        defaultListPipejointSpecificationShouldNotBeFound("fullName.in=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListPipejointSpecificationsByFullNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listPipejointSpecificationRepository.saveAndFlush(listPipejointSpecification);

        // Get all the listPipejointSpecificationList where fullName is not null
        defaultListPipejointSpecificationShouldBeFound("fullName.specified=true");

        // Get all the listPipejointSpecificationList where fullName is null
        defaultListPipejointSpecificationShouldNotBeFound("fullName.specified=false");
    }

    @Test
    @Transactional
    public void getAllListPipejointSpecificationsByIsCurrentFlagIsEqualToSomething() throws Exception {
        // Initialize the database
        listPipejointSpecificationRepository.saveAndFlush(listPipejointSpecification);

        // Get all the listPipejointSpecificationList where isCurrentFlag equals to DEFAULT_IS_CURRENT_FLAG
        defaultListPipejointSpecificationShouldBeFound("isCurrentFlag.equals=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listPipejointSpecificationList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListPipejointSpecificationShouldNotBeFound("isCurrentFlag.equals=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListPipejointSpecificationsByIsCurrentFlagIsInShouldWork() throws Exception {
        // Initialize the database
        listPipejointSpecificationRepository.saveAndFlush(listPipejointSpecification);

        // Get all the listPipejointSpecificationList where isCurrentFlag in DEFAULT_IS_CURRENT_FLAG or UPDATED_IS_CURRENT_FLAG
        defaultListPipejointSpecificationShouldBeFound("isCurrentFlag.in=" + DEFAULT_IS_CURRENT_FLAG + "," + UPDATED_IS_CURRENT_FLAG);

        // Get all the listPipejointSpecificationList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListPipejointSpecificationShouldNotBeFound("isCurrentFlag.in=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListPipejointSpecificationsByIsCurrentFlagIsNullOrNotNull() throws Exception {
        // Initialize the database
        listPipejointSpecificationRepository.saveAndFlush(listPipejointSpecification);

        // Get all the listPipejointSpecificationList where isCurrentFlag is not null
        defaultListPipejointSpecificationShouldBeFound("isCurrentFlag.specified=true");

        // Get all the listPipejointSpecificationList where isCurrentFlag is null
        defaultListPipejointSpecificationShouldNotBeFound("isCurrentFlag.specified=false");
    }

    @Test
    @Transactional
    public void getAllListPipejointSpecificationsByIsCurrentFlagIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        listPipejointSpecificationRepository.saveAndFlush(listPipejointSpecification);

        // Get all the listPipejointSpecificationList where isCurrentFlag greater than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListPipejointSpecificationShouldBeFound("isCurrentFlag.greaterOrEqualThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listPipejointSpecificationList where isCurrentFlag greater than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListPipejointSpecificationShouldNotBeFound("isCurrentFlag.greaterOrEqualThan=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListPipejointSpecificationsByIsCurrentFlagIsLessThanSomething() throws Exception {
        // Initialize the database
        listPipejointSpecificationRepository.saveAndFlush(listPipejointSpecification);

        // Get all the listPipejointSpecificationList where isCurrentFlag less than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListPipejointSpecificationShouldNotBeFound("isCurrentFlag.lessThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listPipejointSpecificationList where isCurrentFlag less than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListPipejointSpecificationShouldBeFound("isCurrentFlag.lessThan=" + UPDATED_IS_CURRENT_FLAG);
    }


    @Test
    @Transactional
    public void getAllListPipejointSpecificationsByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        listPipejointSpecificationRepository.saveAndFlush(listPipejointSpecification);

        // Get all the listPipejointSpecificationList where description equals to DEFAULT_DESCRIPTION
        defaultListPipejointSpecificationShouldBeFound("description.equals=" + DEFAULT_DESCRIPTION);

        // Get all the listPipejointSpecificationList where description equals to UPDATED_DESCRIPTION
        defaultListPipejointSpecificationShouldNotBeFound("description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListPipejointSpecificationsByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        listPipejointSpecificationRepository.saveAndFlush(listPipejointSpecification);

        // Get all the listPipejointSpecificationList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
        defaultListPipejointSpecificationShouldBeFound("description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION);

        // Get all the listPipejointSpecificationList where description equals to UPDATED_DESCRIPTION
        defaultListPipejointSpecificationShouldNotBeFound("description.in=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListPipejointSpecificationsByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        listPipejointSpecificationRepository.saveAndFlush(listPipejointSpecification);

        // Get all the listPipejointSpecificationList where description is not null
        defaultListPipejointSpecificationShouldBeFound("description.specified=true");

        // Get all the listPipejointSpecificationList where description is null
        defaultListPipejointSpecificationShouldNotBeFound("description.specified=false");
    }

    @Test
    @Transactional
    public void getAllListPipejointSpecificationsByDateCreateIsEqualToSomething() throws Exception {
        // Initialize the database
        listPipejointSpecificationRepository.saveAndFlush(listPipejointSpecification);

        // Get all the listPipejointSpecificationList where dateCreate equals to DEFAULT_DATE_CREATE
        defaultListPipejointSpecificationShouldBeFound("dateCreate.equals=" + DEFAULT_DATE_CREATE);

        // Get all the listPipejointSpecificationList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListPipejointSpecificationShouldNotBeFound("dateCreate.equals=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListPipejointSpecificationsByDateCreateIsInShouldWork() throws Exception {
        // Initialize the database
        listPipejointSpecificationRepository.saveAndFlush(listPipejointSpecification);

        // Get all the listPipejointSpecificationList where dateCreate in DEFAULT_DATE_CREATE or UPDATED_DATE_CREATE
        defaultListPipejointSpecificationShouldBeFound("dateCreate.in=" + DEFAULT_DATE_CREATE + "," + UPDATED_DATE_CREATE);

        // Get all the listPipejointSpecificationList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListPipejointSpecificationShouldNotBeFound("dateCreate.in=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListPipejointSpecificationsByDateCreateIsNullOrNotNull() throws Exception {
        // Initialize the database
        listPipejointSpecificationRepository.saveAndFlush(listPipejointSpecification);

        // Get all the listPipejointSpecificationList where dateCreate is not null
        defaultListPipejointSpecificationShouldBeFound("dateCreate.specified=true");

        // Get all the listPipejointSpecificationList where dateCreate is null
        defaultListPipejointSpecificationShouldNotBeFound("dateCreate.specified=false");
    }

    @Test
    @Transactional
    public void getAllListPipejointSpecificationsByDateEditIsEqualToSomething() throws Exception {
        // Initialize the database
        listPipejointSpecificationRepository.saveAndFlush(listPipejointSpecification);

        // Get all the listPipejointSpecificationList where dateEdit equals to DEFAULT_DATE_EDIT
        defaultListPipejointSpecificationShouldBeFound("dateEdit.equals=" + DEFAULT_DATE_EDIT);

        // Get all the listPipejointSpecificationList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListPipejointSpecificationShouldNotBeFound("dateEdit.equals=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListPipejointSpecificationsByDateEditIsInShouldWork() throws Exception {
        // Initialize the database
        listPipejointSpecificationRepository.saveAndFlush(listPipejointSpecification);

        // Get all the listPipejointSpecificationList where dateEdit in DEFAULT_DATE_EDIT or UPDATED_DATE_EDIT
        defaultListPipejointSpecificationShouldBeFound("dateEdit.in=" + DEFAULT_DATE_EDIT + "," + UPDATED_DATE_EDIT);

        // Get all the listPipejointSpecificationList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListPipejointSpecificationShouldNotBeFound("dateEdit.in=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListPipejointSpecificationsByDateEditIsNullOrNotNull() throws Exception {
        // Initialize the database
        listPipejointSpecificationRepository.saveAndFlush(listPipejointSpecification);

        // Get all the listPipejointSpecificationList where dateEdit is not null
        defaultListPipejointSpecificationShouldBeFound("dateEdit.specified=true");

        // Get all the listPipejointSpecificationList where dateEdit is null
        defaultListPipejointSpecificationShouldNotBeFound("dateEdit.specified=false");
    }

    @Test
    @Transactional
    public void getAllListPipejointSpecificationsByCreatorIsEqualToSomething() throws Exception {
        // Initialize the database
        listPipejointSpecificationRepository.saveAndFlush(listPipejointSpecification);

        // Get all the listPipejointSpecificationList where creator equals to DEFAULT_CREATOR
        defaultListPipejointSpecificationShouldBeFound("creator.equals=" + DEFAULT_CREATOR);

        // Get all the listPipejointSpecificationList where creator equals to UPDATED_CREATOR
        defaultListPipejointSpecificationShouldNotBeFound("creator.equals=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListPipejointSpecificationsByCreatorIsInShouldWork() throws Exception {
        // Initialize the database
        listPipejointSpecificationRepository.saveAndFlush(listPipejointSpecification);

        // Get all the listPipejointSpecificationList where creator in DEFAULT_CREATOR or UPDATED_CREATOR
        defaultListPipejointSpecificationShouldBeFound("creator.in=" + DEFAULT_CREATOR + "," + UPDATED_CREATOR);

        // Get all the listPipejointSpecificationList where creator equals to UPDATED_CREATOR
        defaultListPipejointSpecificationShouldNotBeFound("creator.in=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListPipejointSpecificationsByCreatorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listPipejointSpecificationRepository.saveAndFlush(listPipejointSpecification);

        // Get all the listPipejointSpecificationList where creator is not null
        defaultListPipejointSpecificationShouldBeFound("creator.specified=true");

        // Get all the listPipejointSpecificationList where creator is null
        defaultListPipejointSpecificationShouldNotBeFound("creator.specified=false");
    }

    @Test
    @Transactional
    public void getAllListPipejointSpecificationsByEditorIsEqualToSomething() throws Exception {
        // Initialize the database
        listPipejointSpecificationRepository.saveAndFlush(listPipejointSpecification);

        // Get all the listPipejointSpecificationList where editor equals to DEFAULT_EDITOR
        defaultListPipejointSpecificationShouldBeFound("editor.equals=" + DEFAULT_EDITOR);

        // Get all the listPipejointSpecificationList where editor equals to UPDATED_EDITOR
        defaultListPipejointSpecificationShouldNotBeFound("editor.equals=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListPipejointSpecificationsByEditorIsInShouldWork() throws Exception {
        // Initialize the database
        listPipejointSpecificationRepository.saveAndFlush(listPipejointSpecification);

        // Get all the listPipejointSpecificationList where editor in DEFAULT_EDITOR or UPDATED_EDITOR
        defaultListPipejointSpecificationShouldBeFound("editor.in=" + DEFAULT_EDITOR + "," + UPDATED_EDITOR);

        // Get all the listPipejointSpecificationList where editor equals to UPDATED_EDITOR
        defaultListPipejointSpecificationShouldNotBeFound("editor.in=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListPipejointSpecificationsByEditorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listPipejointSpecificationRepository.saveAndFlush(listPipejointSpecification);

        // Get all the listPipejointSpecificationList where editor is not null
        defaultListPipejointSpecificationShouldBeFound("editor.specified=true");

        // Get all the listPipejointSpecificationList where editor is null
        defaultListPipejointSpecificationShouldNotBeFound("editor.specified=false");
    }

    @Test
    @Transactional
    public void getAllListPipejointSpecificationsByPipejointHistIsEqualToSomething() throws Exception {
        // Initialize the database
        PipejointHist pipejointHist = PipejointHistResourceIT.createEntity(em);
        em.persist(pipejointHist);
        em.flush();
        listPipejointSpecification.addPipejointHist(pipejointHist);
        listPipejointSpecificationRepository.saveAndFlush(listPipejointSpecification);
        Long pipejointHistId = pipejointHist.getId();

        // Get all the listPipejointSpecificationList where pipejointHist equals to pipejointHistId
        defaultListPipejointSpecificationShouldBeFound("pipejointHistId.equals=" + pipejointHistId);

        // Get all the listPipejointSpecificationList where pipejointHist equals to pipejointHistId + 1
        defaultListPipejointSpecificationShouldNotBeFound("pipejointHistId.equals=" + (pipejointHistId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultListPipejointSpecificationShouldBeFound(String filter) throws Exception {
        restListPipejointSpecificationMockMvc.perform(get("/api/list-pipejoint-specifications?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listPipejointSpecification.getId().intValue())))
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
        restListPipejointSpecificationMockMvc.perform(get("/api/list-pipejoint-specifications/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultListPipejointSpecificationShouldNotBeFound(String filter) throws Exception {
        restListPipejointSpecificationMockMvc.perform(get("/api/list-pipejoint-specifications?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restListPipejointSpecificationMockMvc.perform(get("/api/list-pipejoint-specifications/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingListPipejointSpecification() throws Exception {
        // Get the listPipejointSpecification
        restListPipejointSpecificationMockMvc.perform(get("/api/list-pipejoint-specifications/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateListPipejointSpecification() throws Exception {
        // Initialize the database
        listPipejointSpecificationRepository.saveAndFlush(listPipejointSpecification);

        int databaseSizeBeforeUpdate = listPipejointSpecificationRepository.findAll().size();

        // Update the listPipejointSpecification
        ListPipejointSpecification updatedListPipejointSpecification = listPipejointSpecificationRepository.findById(listPipejointSpecification.getId()).get();
        // Disconnect from session so that the updates on updatedListPipejointSpecification are not directly saved in db
        em.detach(updatedListPipejointSpecification);
        updatedListPipejointSpecification
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        ListPipejointSpecificationDTO listPipejointSpecificationDTO = listPipejointSpecificationMapper.toDto(updatedListPipejointSpecification);

        restListPipejointSpecificationMockMvc.perform(put("/api/list-pipejoint-specifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listPipejointSpecificationDTO)))
            .andExpect(status().isOk());

        // Validate the ListPipejointSpecification in the database
        List<ListPipejointSpecification> listPipejointSpecificationList = listPipejointSpecificationRepository.findAll();
        assertThat(listPipejointSpecificationList).hasSize(databaseSizeBeforeUpdate);
        ListPipejointSpecification testListPipejointSpecification = listPipejointSpecificationList.get(listPipejointSpecificationList.size() - 1);
        assertThat(testListPipejointSpecification.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testListPipejointSpecification.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testListPipejointSpecification.getFullName()).isEqualTo(UPDATED_FULL_NAME);
        assertThat(testListPipejointSpecification.getIsCurrentFlag()).isEqualTo(UPDATED_IS_CURRENT_FLAG);
        assertThat(testListPipejointSpecification.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testListPipejointSpecification.getDateCreate()).isEqualTo(UPDATED_DATE_CREATE);
        assertThat(testListPipejointSpecification.getDateEdit()).isEqualTo(UPDATED_DATE_EDIT);
        assertThat(testListPipejointSpecification.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testListPipejointSpecification.getEditor()).isEqualTo(UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void updateNonExistingListPipejointSpecification() throws Exception {
        int databaseSizeBeforeUpdate = listPipejointSpecificationRepository.findAll().size();

        // Create the ListPipejointSpecification
        ListPipejointSpecificationDTO listPipejointSpecificationDTO = listPipejointSpecificationMapper.toDto(listPipejointSpecification);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restListPipejointSpecificationMockMvc.perform(put("/api/list-pipejoint-specifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listPipejointSpecificationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListPipejointSpecification in the database
        List<ListPipejointSpecification> listPipejointSpecificationList = listPipejointSpecificationRepository.findAll();
        assertThat(listPipejointSpecificationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteListPipejointSpecification() throws Exception {
        // Initialize the database
        listPipejointSpecificationRepository.saveAndFlush(listPipejointSpecification);

        int databaseSizeBeforeDelete = listPipejointSpecificationRepository.findAll().size();

        // Delete the listPipejointSpecification
        restListPipejointSpecificationMockMvc.perform(delete("/api/list-pipejoint-specifications/{id}", listPipejointSpecification.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<ListPipejointSpecification> listPipejointSpecificationList = listPipejointSpecificationRepository.findAll();
        assertThat(listPipejointSpecificationList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListPipejointSpecification.class);
        ListPipejointSpecification listPipejointSpecification1 = new ListPipejointSpecification();
        listPipejointSpecification1.setId(1L);
        ListPipejointSpecification listPipejointSpecification2 = new ListPipejointSpecification();
        listPipejointSpecification2.setId(listPipejointSpecification1.getId());
        assertThat(listPipejointSpecification1).isEqualTo(listPipejointSpecification2);
        listPipejointSpecification2.setId(2L);
        assertThat(listPipejointSpecification1).isNotEqualTo(listPipejointSpecification2);
        listPipejointSpecification1.setId(null);
        assertThat(listPipejointSpecification1).isNotEqualTo(listPipejointSpecification2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListPipejointSpecificationDTO.class);
        ListPipejointSpecificationDTO listPipejointSpecificationDTO1 = new ListPipejointSpecificationDTO();
        listPipejointSpecificationDTO1.setId(1L);
        ListPipejointSpecificationDTO listPipejointSpecificationDTO2 = new ListPipejointSpecificationDTO();
        assertThat(listPipejointSpecificationDTO1).isNotEqualTo(listPipejointSpecificationDTO2);
        listPipejointSpecificationDTO2.setId(listPipejointSpecificationDTO1.getId());
        assertThat(listPipejointSpecificationDTO1).isEqualTo(listPipejointSpecificationDTO2);
        listPipejointSpecificationDTO2.setId(2L);
        assertThat(listPipejointSpecificationDTO1).isNotEqualTo(listPipejointSpecificationDTO2);
        listPipejointSpecificationDTO1.setId(null);
        assertThat(listPipejointSpecificationDTO1).isNotEqualTo(listPipejointSpecificationDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(listPipejointSpecificationMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(listPipejointSpecificationMapper.fromId(null)).isNull();
    }
}
