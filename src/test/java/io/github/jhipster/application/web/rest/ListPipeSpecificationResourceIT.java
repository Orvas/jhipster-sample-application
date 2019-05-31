package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.ListPipeSpecification;
import io.github.jhipster.application.domain.PipeHist;
import io.github.jhipster.application.repository.ListPipeSpecificationRepository;
import io.github.jhipster.application.service.ListPipeSpecificationService;
import io.github.jhipster.application.service.dto.ListPipeSpecificationDTO;
import io.github.jhipster.application.service.mapper.ListPipeSpecificationMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.ListPipeSpecificationCriteria;
import io.github.jhipster.application.service.ListPipeSpecificationQueryService;

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
 * Integration tests for the {@Link ListPipeSpecificationResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class ListPipeSpecificationResourceIT {

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
    private ListPipeSpecificationRepository listPipeSpecificationRepository;

    @Autowired
    private ListPipeSpecificationMapper listPipeSpecificationMapper;

    @Autowired
    private ListPipeSpecificationService listPipeSpecificationService;

    @Autowired
    private ListPipeSpecificationQueryService listPipeSpecificationQueryService;

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

    private MockMvc restListPipeSpecificationMockMvc;

    private ListPipeSpecification listPipeSpecification;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ListPipeSpecificationResource listPipeSpecificationResource = new ListPipeSpecificationResource(listPipeSpecificationService, listPipeSpecificationQueryService);
        this.restListPipeSpecificationMockMvc = MockMvcBuilders.standaloneSetup(listPipeSpecificationResource)
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
    public static ListPipeSpecification createEntity(EntityManager em) {
        ListPipeSpecification listPipeSpecification = new ListPipeSpecification()
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME)
            .fullName(DEFAULT_FULL_NAME)
            .isCurrentFlag(DEFAULT_IS_CURRENT_FLAG)
            .description(DEFAULT_DESCRIPTION)
            .dateCreate(DEFAULT_DATE_CREATE)
            .dateEdit(DEFAULT_DATE_EDIT)
            .creator(DEFAULT_CREATOR)
            .editor(DEFAULT_EDITOR);
        return listPipeSpecification;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ListPipeSpecification createUpdatedEntity(EntityManager em) {
        ListPipeSpecification listPipeSpecification = new ListPipeSpecification()
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        return listPipeSpecification;
    }

    @BeforeEach
    public void initTest() {
        listPipeSpecification = createEntity(em);
    }

    @Test
    @Transactional
    public void createListPipeSpecification() throws Exception {
        int databaseSizeBeforeCreate = listPipeSpecificationRepository.findAll().size();

        // Create the ListPipeSpecification
        ListPipeSpecificationDTO listPipeSpecificationDTO = listPipeSpecificationMapper.toDto(listPipeSpecification);
        restListPipeSpecificationMockMvc.perform(post("/api/list-pipe-specifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listPipeSpecificationDTO)))
            .andExpect(status().isCreated());

        // Validate the ListPipeSpecification in the database
        List<ListPipeSpecification> listPipeSpecificationList = listPipeSpecificationRepository.findAll();
        assertThat(listPipeSpecificationList).hasSize(databaseSizeBeforeCreate + 1);
        ListPipeSpecification testListPipeSpecification = listPipeSpecificationList.get(listPipeSpecificationList.size() - 1);
        assertThat(testListPipeSpecification.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testListPipeSpecification.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testListPipeSpecification.getFullName()).isEqualTo(DEFAULT_FULL_NAME);
        assertThat(testListPipeSpecification.getIsCurrentFlag()).isEqualTo(DEFAULT_IS_CURRENT_FLAG);
        assertThat(testListPipeSpecification.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testListPipeSpecification.getDateCreate()).isEqualTo(DEFAULT_DATE_CREATE);
        assertThat(testListPipeSpecification.getDateEdit()).isEqualTo(DEFAULT_DATE_EDIT);
        assertThat(testListPipeSpecification.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testListPipeSpecification.getEditor()).isEqualTo(DEFAULT_EDITOR);
    }

    @Test
    @Transactional
    public void createListPipeSpecificationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = listPipeSpecificationRepository.findAll().size();

        // Create the ListPipeSpecification with an existing ID
        listPipeSpecification.setId(1L);
        ListPipeSpecificationDTO listPipeSpecificationDTO = listPipeSpecificationMapper.toDto(listPipeSpecification);

        // An entity with an existing ID cannot be created, so this API call must fail
        restListPipeSpecificationMockMvc.perform(post("/api/list-pipe-specifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listPipeSpecificationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListPipeSpecification in the database
        List<ListPipeSpecification> listPipeSpecificationList = listPipeSpecificationRepository.findAll();
        assertThat(listPipeSpecificationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = listPipeSpecificationRepository.findAll().size();
        // set the field null
        listPipeSpecification.setCode(null);

        // Create the ListPipeSpecification, which fails.
        ListPipeSpecificationDTO listPipeSpecificationDTO = listPipeSpecificationMapper.toDto(listPipeSpecification);

        restListPipeSpecificationMockMvc.perform(post("/api/list-pipe-specifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listPipeSpecificationDTO)))
            .andExpect(status().isBadRequest());

        List<ListPipeSpecification> listPipeSpecificationList = listPipeSpecificationRepository.findAll();
        assertThat(listPipeSpecificationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listPipeSpecificationRepository.findAll().size();
        // set the field null
        listPipeSpecification.setName(null);

        // Create the ListPipeSpecification, which fails.
        ListPipeSpecificationDTO listPipeSpecificationDTO = listPipeSpecificationMapper.toDto(listPipeSpecification);

        restListPipeSpecificationMockMvc.perform(post("/api/list-pipe-specifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listPipeSpecificationDTO)))
            .andExpect(status().isBadRequest());

        List<ListPipeSpecification> listPipeSpecificationList = listPipeSpecificationRepository.findAll();
        assertThat(listPipeSpecificationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFullNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listPipeSpecificationRepository.findAll().size();
        // set the field null
        listPipeSpecification.setFullName(null);

        // Create the ListPipeSpecification, which fails.
        ListPipeSpecificationDTO listPipeSpecificationDTO = listPipeSpecificationMapper.toDto(listPipeSpecification);

        restListPipeSpecificationMockMvc.perform(post("/api/list-pipe-specifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listPipeSpecificationDTO)))
            .andExpect(status().isBadRequest());

        List<ListPipeSpecification> listPipeSpecificationList = listPipeSpecificationRepository.findAll();
        assertThat(listPipeSpecificationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsCurrentFlagIsRequired() throws Exception {
        int databaseSizeBeforeTest = listPipeSpecificationRepository.findAll().size();
        // set the field null
        listPipeSpecification.setIsCurrentFlag(null);

        // Create the ListPipeSpecification, which fails.
        ListPipeSpecificationDTO listPipeSpecificationDTO = listPipeSpecificationMapper.toDto(listPipeSpecification);

        restListPipeSpecificationMockMvc.perform(post("/api/list-pipe-specifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listPipeSpecificationDTO)))
            .andExpect(status().isBadRequest());

        List<ListPipeSpecification> listPipeSpecificationList = listPipeSpecificationRepository.findAll();
        assertThat(listPipeSpecificationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllListPipeSpecifications() throws Exception {
        // Initialize the database
        listPipeSpecificationRepository.saveAndFlush(listPipeSpecification);

        // Get all the listPipeSpecificationList
        restListPipeSpecificationMockMvc.perform(get("/api/list-pipe-specifications?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listPipeSpecification.getId().intValue())))
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
    public void getListPipeSpecification() throws Exception {
        // Initialize the database
        listPipeSpecificationRepository.saveAndFlush(listPipeSpecification);

        // Get the listPipeSpecification
        restListPipeSpecificationMockMvc.perform(get("/api/list-pipe-specifications/{id}", listPipeSpecification.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(listPipeSpecification.getId().intValue()))
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
    public void getAllListPipeSpecificationsByCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        listPipeSpecificationRepository.saveAndFlush(listPipeSpecification);

        // Get all the listPipeSpecificationList where code equals to DEFAULT_CODE
        defaultListPipeSpecificationShouldBeFound("code.equals=" + DEFAULT_CODE);

        // Get all the listPipeSpecificationList where code equals to UPDATED_CODE
        defaultListPipeSpecificationShouldNotBeFound("code.equals=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListPipeSpecificationsByCodeIsInShouldWork() throws Exception {
        // Initialize the database
        listPipeSpecificationRepository.saveAndFlush(listPipeSpecification);

        // Get all the listPipeSpecificationList where code in DEFAULT_CODE or UPDATED_CODE
        defaultListPipeSpecificationShouldBeFound("code.in=" + DEFAULT_CODE + "," + UPDATED_CODE);

        // Get all the listPipeSpecificationList where code equals to UPDATED_CODE
        defaultListPipeSpecificationShouldNotBeFound("code.in=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListPipeSpecificationsByCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        listPipeSpecificationRepository.saveAndFlush(listPipeSpecification);

        // Get all the listPipeSpecificationList where code is not null
        defaultListPipeSpecificationShouldBeFound("code.specified=true");

        // Get all the listPipeSpecificationList where code is null
        defaultListPipeSpecificationShouldNotBeFound("code.specified=false");
    }

    @Test
    @Transactional
    public void getAllListPipeSpecificationsByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listPipeSpecificationRepository.saveAndFlush(listPipeSpecification);

        // Get all the listPipeSpecificationList where name equals to DEFAULT_NAME
        defaultListPipeSpecificationShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the listPipeSpecificationList where name equals to UPDATED_NAME
        defaultListPipeSpecificationShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListPipeSpecificationsByNameIsInShouldWork() throws Exception {
        // Initialize the database
        listPipeSpecificationRepository.saveAndFlush(listPipeSpecification);

        // Get all the listPipeSpecificationList where name in DEFAULT_NAME or UPDATED_NAME
        defaultListPipeSpecificationShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the listPipeSpecificationList where name equals to UPDATED_NAME
        defaultListPipeSpecificationShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListPipeSpecificationsByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listPipeSpecificationRepository.saveAndFlush(listPipeSpecification);

        // Get all the listPipeSpecificationList where name is not null
        defaultListPipeSpecificationShouldBeFound("name.specified=true");

        // Get all the listPipeSpecificationList where name is null
        defaultListPipeSpecificationShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    public void getAllListPipeSpecificationsByFullNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listPipeSpecificationRepository.saveAndFlush(listPipeSpecification);

        // Get all the listPipeSpecificationList where fullName equals to DEFAULT_FULL_NAME
        defaultListPipeSpecificationShouldBeFound("fullName.equals=" + DEFAULT_FULL_NAME);

        // Get all the listPipeSpecificationList where fullName equals to UPDATED_FULL_NAME
        defaultListPipeSpecificationShouldNotBeFound("fullName.equals=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListPipeSpecificationsByFullNameIsInShouldWork() throws Exception {
        // Initialize the database
        listPipeSpecificationRepository.saveAndFlush(listPipeSpecification);

        // Get all the listPipeSpecificationList where fullName in DEFAULT_FULL_NAME or UPDATED_FULL_NAME
        defaultListPipeSpecificationShouldBeFound("fullName.in=" + DEFAULT_FULL_NAME + "," + UPDATED_FULL_NAME);

        // Get all the listPipeSpecificationList where fullName equals to UPDATED_FULL_NAME
        defaultListPipeSpecificationShouldNotBeFound("fullName.in=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListPipeSpecificationsByFullNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listPipeSpecificationRepository.saveAndFlush(listPipeSpecification);

        // Get all the listPipeSpecificationList where fullName is not null
        defaultListPipeSpecificationShouldBeFound("fullName.specified=true");

        // Get all the listPipeSpecificationList where fullName is null
        defaultListPipeSpecificationShouldNotBeFound("fullName.specified=false");
    }

    @Test
    @Transactional
    public void getAllListPipeSpecificationsByIsCurrentFlagIsEqualToSomething() throws Exception {
        // Initialize the database
        listPipeSpecificationRepository.saveAndFlush(listPipeSpecification);

        // Get all the listPipeSpecificationList where isCurrentFlag equals to DEFAULT_IS_CURRENT_FLAG
        defaultListPipeSpecificationShouldBeFound("isCurrentFlag.equals=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listPipeSpecificationList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListPipeSpecificationShouldNotBeFound("isCurrentFlag.equals=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListPipeSpecificationsByIsCurrentFlagIsInShouldWork() throws Exception {
        // Initialize the database
        listPipeSpecificationRepository.saveAndFlush(listPipeSpecification);

        // Get all the listPipeSpecificationList where isCurrentFlag in DEFAULT_IS_CURRENT_FLAG or UPDATED_IS_CURRENT_FLAG
        defaultListPipeSpecificationShouldBeFound("isCurrentFlag.in=" + DEFAULT_IS_CURRENT_FLAG + "," + UPDATED_IS_CURRENT_FLAG);

        // Get all the listPipeSpecificationList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListPipeSpecificationShouldNotBeFound("isCurrentFlag.in=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListPipeSpecificationsByIsCurrentFlagIsNullOrNotNull() throws Exception {
        // Initialize the database
        listPipeSpecificationRepository.saveAndFlush(listPipeSpecification);

        // Get all the listPipeSpecificationList where isCurrentFlag is not null
        defaultListPipeSpecificationShouldBeFound("isCurrentFlag.specified=true");

        // Get all the listPipeSpecificationList where isCurrentFlag is null
        defaultListPipeSpecificationShouldNotBeFound("isCurrentFlag.specified=false");
    }

    @Test
    @Transactional
    public void getAllListPipeSpecificationsByIsCurrentFlagIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        listPipeSpecificationRepository.saveAndFlush(listPipeSpecification);

        // Get all the listPipeSpecificationList where isCurrentFlag greater than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListPipeSpecificationShouldBeFound("isCurrentFlag.greaterOrEqualThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listPipeSpecificationList where isCurrentFlag greater than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListPipeSpecificationShouldNotBeFound("isCurrentFlag.greaterOrEqualThan=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListPipeSpecificationsByIsCurrentFlagIsLessThanSomething() throws Exception {
        // Initialize the database
        listPipeSpecificationRepository.saveAndFlush(listPipeSpecification);

        // Get all the listPipeSpecificationList where isCurrentFlag less than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListPipeSpecificationShouldNotBeFound("isCurrentFlag.lessThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listPipeSpecificationList where isCurrentFlag less than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListPipeSpecificationShouldBeFound("isCurrentFlag.lessThan=" + UPDATED_IS_CURRENT_FLAG);
    }


    @Test
    @Transactional
    public void getAllListPipeSpecificationsByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        listPipeSpecificationRepository.saveAndFlush(listPipeSpecification);

        // Get all the listPipeSpecificationList where description equals to DEFAULT_DESCRIPTION
        defaultListPipeSpecificationShouldBeFound("description.equals=" + DEFAULT_DESCRIPTION);

        // Get all the listPipeSpecificationList where description equals to UPDATED_DESCRIPTION
        defaultListPipeSpecificationShouldNotBeFound("description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListPipeSpecificationsByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        listPipeSpecificationRepository.saveAndFlush(listPipeSpecification);

        // Get all the listPipeSpecificationList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
        defaultListPipeSpecificationShouldBeFound("description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION);

        // Get all the listPipeSpecificationList where description equals to UPDATED_DESCRIPTION
        defaultListPipeSpecificationShouldNotBeFound("description.in=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListPipeSpecificationsByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        listPipeSpecificationRepository.saveAndFlush(listPipeSpecification);

        // Get all the listPipeSpecificationList where description is not null
        defaultListPipeSpecificationShouldBeFound("description.specified=true");

        // Get all the listPipeSpecificationList where description is null
        defaultListPipeSpecificationShouldNotBeFound("description.specified=false");
    }

    @Test
    @Transactional
    public void getAllListPipeSpecificationsByDateCreateIsEqualToSomething() throws Exception {
        // Initialize the database
        listPipeSpecificationRepository.saveAndFlush(listPipeSpecification);

        // Get all the listPipeSpecificationList where dateCreate equals to DEFAULT_DATE_CREATE
        defaultListPipeSpecificationShouldBeFound("dateCreate.equals=" + DEFAULT_DATE_CREATE);

        // Get all the listPipeSpecificationList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListPipeSpecificationShouldNotBeFound("dateCreate.equals=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListPipeSpecificationsByDateCreateIsInShouldWork() throws Exception {
        // Initialize the database
        listPipeSpecificationRepository.saveAndFlush(listPipeSpecification);

        // Get all the listPipeSpecificationList where dateCreate in DEFAULT_DATE_CREATE or UPDATED_DATE_CREATE
        defaultListPipeSpecificationShouldBeFound("dateCreate.in=" + DEFAULT_DATE_CREATE + "," + UPDATED_DATE_CREATE);

        // Get all the listPipeSpecificationList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListPipeSpecificationShouldNotBeFound("dateCreate.in=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListPipeSpecificationsByDateCreateIsNullOrNotNull() throws Exception {
        // Initialize the database
        listPipeSpecificationRepository.saveAndFlush(listPipeSpecification);

        // Get all the listPipeSpecificationList where dateCreate is not null
        defaultListPipeSpecificationShouldBeFound("dateCreate.specified=true");

        // Get all the listPipeSpecificationList where dateCreate is null
        defaultListPipeSpecificationShouldNotBeFound("dateCreate.specified=false");
    }

    @Test
    @Transactional
    public void getAllListPipeSpecificationsByDateEditIsEqualToSomething() throws Exception {
        // Initialize the database
        listPipeSpecificationRepository.saveAndFlush(listPipeSpecification);

        // Get all the listPipeSpecificationList where dateEdit equals to DEFAULT_DATE_EDIT
        defaultListPipeSpecificationShouldBeFound("dateEdit.equals=" + DEFAULT_DATE_EDIT);

        // Get all the listPipeSpecificationList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListPipeSpecificationShouldNotBeFound("dateEdit.equals=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListPipeSpecificationsByDateEditIsInShouldWork() throws Exception {
        // Initialize the database
        listPipeSpecificationRepository.saveAndFlush(listPipeSpecification);

        // Get all the listPipeSpecificationList where dateEdit in DEFAULT_DATE_EDIT or UPDATED_DATE_EDIT
        defaultListPipeSpecificationShouldBeFound("dateEdit.in=" + DEFAULT_DATE_EDIT + "," + UPDATED_DATE_EDIT);

        // Get all the listPipeSpecificationList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListPipeSpecificationShouldNotBeFound("dateEdit.in=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListPipeSpecificationsByDateEditIsNullOrNotNull() throws Exception {
        // Initialize the database
        listPipeSpecificationRepository.saveAndFlush(listPipeSpecification);

        // Get all the listPipeSpecificationList where dateEdit is not null
        defaultListPipeSpecificationShouldBeFound("dateEdit.specified=true");

        // Get all the listPipeSpecificationList where dateEdit is null
        defaultListPipeSpecificationShouldNotBeFound("dateEdit.specified=false");
    }

    @Test
    @Transactional
    public void getAllListPipeSpecificationsByCreatorIsEqualToSomething() throws Exception {
        // Initialize the database
        listPipeSpecificationRepository.saveAndFlush(listPipeSpecification);

        // Get all the listPipeSpecificationList where creator equals to DEFAULT_CREATOR
        defaultListPipeSpecificationShouldBeFound("creator.equals=" + DEFAULT_CREATOR);

        // Get all the listPipeSpecificationList where creator equals to UPDATED_CREATOR
        defaultListPipeSpecificationShouldNotBeFound("creator.equals=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListPipeSpecificationsByCreatorIsInShouldWork() throws Exception {
        // Initialize the database
        listPipeSpecificationRepository.saveAndFlush(listPipeSpecification);

        // Get all the listPipeSpecificationList where creator in DEFAULT_CREATOR or UPDATED_CREATOR
        defaultListPipeSpecificationShouldBeFound("creator.in=" + DEFAULT_CREATOR + "," + UPDATED_CREATOR);

        // Get all the listPipeSpecificationList where creator equals to UPDATED_CREATOR
        defaultListPipeSpecificationShouldNotBeFound("creator.in=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListPipeSpecificationsByCreatorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listPipeSpecificationRepository.saveAndFlush(listPipeSpecification);

        // Get all the listPipeSpecificationList where creator is not null
        defaultListPipeSpecificationShouldBeFound("creator.specified=true");

        // Get all the listPipeSpecificationList where creator is null
        defaultListPipeSpecificationShouldNotBeFound("creator.specified=false");
    }

    @Test
    @Transactional
    public void getAllListPipeSpecificationsByEditorIsEqualToSomething() throws Exception {
        // Initialize the database
        listPipeSpecificationRepository.saveAndFlush(listPipeSpecification);

        // Get all the listPipeSpecificationList where editor equals to DEFAULT_EDITOR
        defaultListPipeSpecificationShouldBeFound("editor.equals=" + DEFAULT_EDITOR);

        // Get all the listPipeSpecificationList where editor equals to UPDATED_EDITOR
        defaultListPipeSpecificationShouldNotBeFound("editor.equals=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListPipeSpecificationsByEditorIsInShouldWork() throws Exception {
        // Initialize the database
        listPipeSpecificationRepository.saveAndFlush(listPipeSpecification);

        // Get all the listPipeSpecificationList where editor in DEFAULT_EDITOR or UPDATED_EDITOR
        defaultListPipeSpecificationShouldBeFound("editor.in=" + DEFAULT_EDITOR + "," + UPDATED_EDITOR);

        // Get all the listPipeSpecificationList where editor equals to UPDATED_EDITOR
        defaultListPipeSpecificationShouldNotBeFound("editor.in=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListPipeSpecificationsByEditorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listPipeSpecificationRepository.saveAndFlush(listPipeSpecification);

        // Get all the listPipeSpecificationList where editor is not null
        defaultListPipeSpecificationShouldBeFound("editor.specified=true");

        // Get all the listPipeSpecificationList where editor is null
        defaultListPipeSpecificationShouldNotBeFound("editor.specified=false");
    }

    @Test
    @Transactional
    public void getAllListPipeSpecificationsByPipeHistIsEqualToSomething() throws Exception {
        // Initialize the database
        PipeHist pipeHist = PipeHistResourceIT.createEntity(em);
        em.persist(pipeHist);
        em.flush();
        listPipeSpecification.addPipeHist(pipeHist);
        listPipeSpecificationRepository.saveAndFlush(listPipeSpecification);
        Long pipeHistId = pipeHist.getId();

        // Get all the listPipeSpecificationList where pipeHist equals to pipeHistId
        defaultListPipeSpecificationShouldBeFound("pipeHistId.equals=" + pipeHistId);

        // Get all the listPipeSpecificationList where pipeHist equals to pipeHistId + 1
        defaultListPipeSpecificationShouldNotBeFound("pipeHistId.equals=" + (pipeHistId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultListPipeSpecificationShouldBeFound(String filter) throws Exception {
        restListPipeSpecificationMockMvc.perform(get("/api/list-pipe-specifications?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listPipeSpecification.getId().intValue())))
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
        restListPipeSpecificationMockMvc.perform(get("/api/list-pipe-specifications/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultListPipeSpecificationShouldNotBeFound(String filter) throws Exception {
        restListPipeSpecificationMockMvc.perform(get("/api/list-pipe-specifications?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restListPipeSpecificationMockMvc.perform(get("/api/list-pipe-specifications/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingListPipeSpecification() throws Exception {
        // Get the listPipeSpecification
        restListPipeSpecificationMockMvc.perform(get("/api/list-pipe-specifications/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateListPipeSpecification() throws Exception {
        // Initialize the database
        listPipeSpecificationRepository.saveAndFlush(listPipeSpecification);

        int databaseSizeBeforeUpdate = listPipeSpecificationRepository.findAll().size();

        // Update the listPipeSpecification
        ListPipeSpecification updatedListPipeSpecification = listPipeSpecificationRepository.findById(listPipeSpecification.getId()).get();
        // Disconnect from session so that the updates on updatedListPipeSpecification are not directly saved in db
        em.detach(updatedListPipeSpecification);
        updatedListPipeSpecification
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        ListPipeSpecificationDTO listPipeSpecificationDTO = listPipeSpecificationMapper.toDto(updatedListPipeSpecification);

        restListPipeSpecificationMockMvc.perform(put("/api/list-pipe-specifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listPipeSpecificationDTO)))
            .andExpect(status().isOk());

        // Validate the ListPipeSpecification in the database
        List<ListPipeSpecification> listPipeSpecificationList = listPipeSpecificationRepository.findAll();
        assertThat(listPipeSpecificationList).hasSize(databaseSizeBeforeUpdate);
        ListPipeSpecification testListPipeSpecification = listPipeSpecificationList.get(listPipeSpecificationList.size() - 1);
        assertThat(testListPipeSpecification.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testListPipeSpecification.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testListPipeSpecification.getFullName()).isEqualTo(UPDATED_FULL_NAME);
        assertThat(testListPipeSpecification.getIsCurrentFlag()).isEqualTo(UPDATED_IS_CURRENT_FLAG);
        assertThat(testListPipeSpecification.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testListPipeSpecification.getDateCreate()).isEqualTo(UPDATED_DATE_CREATE);
        assertThat(testListPipeSpecification.getDateEdit()).isEqualTo(UPDATED_DATE_EDIT);
        assertThat(testListPipeSpecification.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testListPipeSpecification.getEditor()).isEqualTo(UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void updateNonExistingListPipeSpecification() throws Exception {
        int databaseSizeBeforeUpdate = listPipeSpecificationRepository.findAll().size();

        // Create the ListPipeSpecification
        ListPipeSpecificationDTO listPipeSpecificationDTO = listPipeSpecificationMapper.toDto(listPipeSpecification);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restListPipeSpecificationMockMvc.perform(put("/api/list-pipe-specifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listPipeSpecificationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListPipeSpecification in the database
        List<ListPipeSpecification> listPipeSpecificationList = listPipeSpecificationRepository.findAll();
        assertThat(listPipeSpecificationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteListPipeSpecification() throws Exception {
        // Initialize the database
        listPipeSpecificationRepository.saveAndFlush(listPipeSpecification);

        int databaseSizeBeforeDelete = listPipeSpecificationRepository.findAll().size();

        // Delete the listPipeSpecification
        restListPipeSpecificationMockMvc.perform(delete("/api/list-pipe-specifications/{id}", listPipeSpecification.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<ListPipeSpecification> listPipeSpecificationList = listPipeSpecificationRepository.findAll();
        assertThat(listPipeSpecificationList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListPipeSpecification.class);
        ListPipeSpecification listPipeSpecification1 = new ListPipeSpecification();
        listPipeSpecification1.setId(1L);
        ListPipeSpecification listPipeSpecification2 = new ListPipeSpecification();
        listPipeSpecification2.setId(listPipeSpecification1.getId());
        assertThat(listPipeSpecification1).isEqualTo(listPipeSpecification2);
        listPipeSpecification2.setId(2L);
        assertThat(listPipeSpecification1).isNotEqualTo(listPipeSpecification2);
        listPipeSpecification1.setId(null);
        assertThat(listPipeSpecification1).isNotEqualTo(listPipeSpecification2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListPipeSpecificationDTO.class);
        ListPipeSpecificationDTO listPipeSpecificationDTO1 = new ListPipeSpecificationDTO();
        listPipeSpecificationDTO1.setId(1L);
        ListPipeSpecificationDTO listPipeSpecificationDTO2 = new ListPipeSpecificationDTO();
        assertThat(listPipeSpecificationDTO1).isNotEqualTo(listPipeSpecificationDTO2);
        listPipeSpecificationDTO2.setId(listPipeSpecificationDTO1.getId());
        assertThat(listPipeSpecificationDTO1).isEqualTo(listPipeSpecificationDTO2);
        listPipeSpecificationDTO2.setId(2L);
        assertThat(listPipeSpecificationDTO1).isNotEqualTo(listPipeSpecificationDTO2);
        listPipeSpecificationDTO1.setId(null);
        assertThat(listPipeSpecificationDTO1).isNotEqualTo(listPipeSpecificationDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(listPipeSpecificationMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(listPipeSpecificationMapper.fromId(null)).isNull();
    }
}
