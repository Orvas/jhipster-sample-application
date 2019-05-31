package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.ListSafetyClass;
import io.github.jhipster.application.domain.PipelineSection;
import io.github.jhipster.application.repository.ListSafetyClassRepository;
import io.github.jhipster.application.service.ListSafetyClassService;
import io.github.jhipster.application.service.dto.ListSafetyClassDTO;
import io.github.jhipster.application.service.mapper.ListSafetyClassMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.ListSafetyClassCriteria;
import io.github.jhipster.application.service.ListSafetyClassQueryService;

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
 * Integration tests for the {@Link ListSafetyClassResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class ListSafetyClassResourceIT {

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
    private ListSafetyClassRepository listSafetyClassRepository;

    @Autowired
    private ListSafetyClassMapper listSafetyClassMapper;

    @Autowired
    private ListSafetyClassService listSafetyClassService;

    @Autowired
    private ListSafetyClassQueryService listSafetyClassQueryService;

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

    private MockMvc restListSafetyClassMockMvc;

    private ListSafetyClass listSafetyClass;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ListSafetyClassResource listSafetyClassResource = new ListSafetyClassResource(listSafetyClassService, listSafetyClassQueryService);
        this.restListSafetyClassMockMvc = MockMvcBuilders.standaloneSetup(listSafetyClassResource)
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
    public static ListSafetyClass createEntity(EntityManager em) {
        ListSafetyClass listSafetyClass = new ListSafetyClass()
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME)
            .fullName(DEFAULT_FULL_NAME)
            .isCurrentFlag(DEFAULT_IS_CURRENT_FLAG)
            .description(DEFAULT_DESCRIPTION)
            .dateCreate(DEFAULT_DATE_CREATE)
            .dateEdit(DEFAULT_DATE_EDIT)
            .creator(DEFAULT_CREATOR)
            .editor(DEFAULT_EDITOR);
        return listSafetyClass;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ListSafetyClass createUpdatedEntity(EntityManager em) {
        ListSafetyClass listSafetyClass = new ListSafetyClass()
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        return listSafetyClass;
    }

    @BeforeEach
    public void initTest() {
        listSafetyClass = createEntity(em);
    }

    @Test
    @Transactional
    public void createListSafetyClass() throws Exception {
        int databaseSizeBeforeCreate = listSafetyClassRepository.findAll().size();

        // Create the ListSafetyClass
        ListSafetyClassDTO listSafetyClassDTO = listSafetyClassMapper.toDto(listSafetyClass);
        restListSafetyClassMockMvc.perform(post("/api/list-safety-classes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listSafetyClassDTO)))
            .andExpect(status().isCreated());

        // Validate the ListSafetyClass in the database
        List<ListSafetyClass> listSafetyClassList = listSafetyClassRepository.findAll();
        assertThat(listSafetyClassList).hasSize(databaseSizeBeforeCreate + 1);
        ListSafetyClass testListSafetyClass = listSafetyClassList.get(listSafetyClassList.size() - 1);
        assertThat(testListSafetyClass.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testListSafetyClass.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testListSafetyClass.getFullName()).isEqualTo(DEFAULT_FULL_NAME);
        assertThat(testListSafetyClass.getIsCurrentFlag()).isEqualTo(DEFAULT_IS_CURRENT_FLAG);
        assertThat(testListSafetyClass.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testListSafetyClass.getDateCreate()).isEqualTo(DEFAULT_DATE_CREATE);
        assertThat(testListSafetyClass.getDateEdit()).isEqualTo(DEFAULT_DATE_EDIT);
        assertThat(testListSafetyClass.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testListSafetyClass.getEditor()).isEqualTo(DEFAULT_EDITOR);
    }

    @Test
    @Transactional
    public void createListSafetyClassWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = listSafetyClassRepository.findAll().size();

        // Create the ListSafetyClass with an existing ID
        listSafetyClass.setId(1L);
        ListSafetyClassDTO listSafetyClassDTO = listSafetyClassMapper.toDto(listSafetyClass);

        // An entity with an existing ID cannot be created, so this API call must fail
        restListSafetyClassMockMvc.perform(post("/api/list-safety-classes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listSafetyClassDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListSafetyClass in the database
        List<ListSafetyClass> listSafetyClassList = listSafetyClassRepository.findAll();
        assertThat(listSafetyClassList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = listSafetyClassRepository.findAll().size();
        // set the field null
        listSafetyClass.setCode(null);

        // Create the ListSafetyClass, which fails.
        ListSafetyClassDTO listSafetyClassDTO = listSafetyClassMapper.toDto(listSafetyClass);

        restListSafetyClassMockMvc.perform(post("/api/list-safety-classes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listSafetyClassDTO)))
            .andExpect(status().isBadRequest());

        List<ListSafetyClass> listSafetyClassList = listSafetyClassRepository.findAll();
        assertThat(listSafetyClassList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listSafetyClassRepository.findAll().size();
        // set the field null
        listSafetyClass.setName(null);

        // Create the ListSafetyClass, which fails.
        ListSafetyClassDTO listSafetyClassDTO = listSafetyClassMapper.toDto(listSafetyClass);

        restListSafetyClassMockMvc.perform(post("/api/list-safety-classes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listSafetyClassDTO)))
            .andExpect(status().isBadRequest());

        List<ListSafetyClass> listSafetyClassList = listSafetyClassRepository.findAll();
        assertThat(listSafetyClassList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFullNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listSafetyClassRepository.findAll().size();
        // set the field null
        listSafetyClass.setFullName(null);

        // Create the ListSafetyClass, which fails.
        ListSafetyClassDTO listSafetyClassDTO = listSafetyClassMapper.toDto(listSafetyClass);

        restListSafetyClassMockMvc.perform(post("/api/list-safety-classes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listSafetyClassDTO)))
            .andExpect(status().isBadRequest());

        List<ListSafetyClass> listSafetyClassList = listSafetyClassRepository.findAll();
        assertThat(listSafetyClassList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsCurrentFlagIsRequired() throws Exception {
        int databaseSizeBeforeTest = listSafetyClassRepository.findAll().size();
        // set the field null
        listSafetyClass.setIsCurrentFlag(null);

        // Create the ListSafetyClass, which fails.
        ListSafetyClassDTO listSafetyClassDTO = listSafetyClassMapper.toDto(listSafetyClass);

        restListSafetyClassMockMvc.perform(post("/api/list-safety-classes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listSafetyClassDTO)))
            .andExpect(status().isBadRequest());

        List<ListSafetyClass> listSafetyClassList = listSafetyClassRepository.findAll();
        assertThat(listSafetyClassList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllListSafetyClasses() throws Exception {
        // Initialize the database
        listSafetyClassRepository.saveAndFlush(listSafetyClass);

        // Get all the listSafetyClassList
        restListSafetyClassMockMvc.perform(get("/api/list-safety-classes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listSafetyClass.getId().intValue())))
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
    public void getListSafetyClass() throws Exception {
        // Initialize the database
        listSafetyClassRepository.saveAndFlush(listSafetyClass);

        // Get the listSafetyClass
        restListSafetyClassMockMvc.perform(get("/api/list-safety-classes/{id}", listSafetyClass.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(listSafetyClass.getId().intValue()))
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
    public void getAllListSafetyClassesByCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        listSafetyClassRepository.saveAndFlush(listSafetyClass);

        // Get all the listSafetyClassList where code equals to DEFAULT_CODE
        defaultListSafetyClassShouldBeFound("code.equals=" + DEFAULT_CODE);

        // Get all the listSafetyClassList where code equals to UPDATED_CODE
        defaultListSafetyClassShouldNotBeFound("code.equals=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListSafetyClassesByCodeIsInShouldWork() throws Exception {
        // Initialize the database
        listSafetyClassRepository.saveAndFlush(listSafetyClass);

        // Get all the listSafetyClassList where code in DEFAULT_CODE or UPDATED_CODE
        defaultListSafetyClassShouldBeFound("code.in=" + DEFAULT_CODE + "," + UPDATED_CODE);

        // Get all the listSafetyClassList where code equals to UPDATED_CODE
        defaultListSafetyClassShouldNotBeFound("code.in=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListSafetyClassesByCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        listSafetyClassRepository.saveAndFlush(listSafetyClass);

        // Get all the listSafetyClassList where code is not null
        defaultListSafetyClassShouldBeFound("code.specified=true");

        // Get all the listSafetyClassList where code is null
        defaultListSafetyClassShouldNotBeFound("code.specified=false");
    }

    @Test
    @Transactional
    public void getAllListSafetyClassesByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listSafetyClassRepository.saveAndFlush(listSafetyClass);

        // Get all the listSafetyClassList where name equals to DEFAULT_NAME
        defaultListSafetyClassShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the listSafetyClassList where name equals to UPDATED_NAME
        defaultListSafetyClassShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListSafetyClassesByNameIsInShouldWork() throws Exception {
        // Initialize the database
        listSafetyClassRepository.saveAndFlush(listSafetyClass);

        // Get all the listSafetyClassList where name in DEFAULT_NAME or UPDATED_NAME
        defaultListSafetyClassShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the listSafetyClassList where name equals to UPDATED_NAME
        defaultListSafetyClassShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListSafetyClassesByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listSafetyClassRepository.saveAndFlush(listSafetyClass);

        // Get all the listSafetyClassList where name is not null
        defaultListSafetyClassShouldBeFound("name.specified=true");

        // Get all the listSafetyClassList where name is null
        defaultListSafetyClassShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    public void getAllListSafetyClassesByFullNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listSafetyClassRepository.saveAndFlush(listSafetyClass);

        // Get all the listSafetyClassList where fullName equals to DEFAULT_FULL_NAME
        defaultListSafetyClassShouldBeFound("fullName.equals=" + DEFAULT_FULL_NAME);

        // Get all the listSafetyClassList where fullName equals to UPDATED_FULL_NAME
        defaultListSafetyClassShouldNotBeFound("fullName.equals=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListSafetyClassesByFullNameIsInShouldWork() throws Exception {
        // Initialize the database
        listSafetyClassRepository.saveAndFlush(listSafetyClass);

        // Get all the listSafetyClassList where fullName in DEFAULT_FULL_NAME or UPDATED_FULL_NAME
        defaultListSafetyClassShouldBeFound("fullName.in=" + DEFAULT_FULL_NAME + "," + UPDATED_FULL_NAME);

        // Get all the listSafetyClassList where fullName equals to UPDATED_FULL_NAME
        defaultListSafetyClassShouldNotBeFound("fullName.in=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListSafetyClassesByFullNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listSafetyClassRepository.saveAndFlush(listSafetyClass);

        // Get all the listSafetyClassList where fullName is not null
        defaultListSafetyClassShouldBeFound("fullName.specified=true");

        // Get all the listSafetyClassList where fullName is null
        defaultListSafetyClassShouldNotBeFound("fullName.specified=false");
    }

    @Test
    @Transactional
    public void getAllListSafetyClassesByIsCurrentFlagIsEqualToSomething() throws Exception {
        // Initialize the database
        listSafetyClassRepository.saveAndFlush(listSafetyClass);

        // Get all the listSafetyClassList where isCurrentFlag equals to DEFAULT_IS_CURRENT_FLAG
        defaultListSafetyClassShouldBeFound("isCurrentFlag.equals=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listSafetyClassList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListSafetyClassShouldNotBeFound("isCurrentFlag.equals=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListSafetyClassesByIsCurrentFlagIsInShouldWork() throws Exception {
        // Initialize the database
        listSafetyClassRepository.saveAndFlush(listSafetyClass);

        // Get all the listSafetyClassList where isCurrentFlag in DEFAULT_IS_CURRENT_FLAG or UPDATED_IS_CURRENT_FLAG
        defaultListSafetyClassShouldBeFound("isCurrentFlag.in=" + DEFAULT_IS_CURRENT_FLAG + "," + UPDATED_IS_CURRENT_FLAG);

        // Get all the listSafetyClassList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListSafetyClassShouldNotBeFound("isCurrentFlag.in=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListSafetyClassesByIsCurrentFlagIsNullOrNotNull() throws Exception {
        // Initialize the database
        listSafetyClassRepository.saveAndFlush(listSafetyClass);

        // Get all the listSafetyClassList where isCurrentFlag is not null
        defaultListSafetyClassShouldBeFound("isCurrentFlag.specified=true");

        // Get all the listSafetyClassList where isCurrentFlag is null
        defaultListSafetyClassShouldNotBeFound("isCurrentFlag.specified=false");
    }

    @Test
    @Transactional
    public void getAllListSafetyClassesByIsCurrentFlagIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        listSafetyClassRepository.saveAndFlush(listSafetyClass);

        // Get all the listSafetyClassList where isCurrentFlag greater than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListSafetyClassShouldBeFound("isCurrentFlag.greaterOrEqualThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listSafetyClassList where isCurrentFlag greater than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListSafetyClassShouldNotBeFound("isCurrentFlag.greaterOrEqualThan=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListSafetyClassesByIsCurrentFlagIsLessThanSomething() throws Exception {
        // Initialize the database
        listSafetyClassRepository.saveAndFlush(listSafetyClass);

        // Get all the listSafetyClassList where isCurrentFlag less than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListSafetyClassShouldNotBeFound("isCurrentFlag.lessThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listSafetyClassList where isCurrentFlag less than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListSafetyClassShouldBeFound("isCurrentFlag.lessThan=" + UPDATED_IS_CURRENT_FLAG);
    }


    @Test
    @Transactional
    public void getAllListSafetyClassesByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        listSafetyClassRepository.saveAndFlush(listSafetyClass);

        // Get all the listSafetyClassList where description equals to DEFAULT_DESCRIPTION
        defaultListSafetyClassShouldBeFound("description.equals=" + DEFAULT_DESCRIPTION);

        // Get all the listSafetyClassList where description equals to UPDATED_DESCRIPTION
        defaultListSafetyClassShouldNotBeFound("description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListSafetyClassesByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        listSafetyClassRepository.saveAndFlush(listSafetyClass);

        // Get all the listSafetyClassList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
        defaultListSafetyClassShouldBeFound("description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION);

        // Get all the listSafetyClassList where description equals to UPDATED_DESCRIPTION
        defaultListSafetyClassShouldNotBeFound("description.in=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListSafetyClassesByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        listSafetyClassRepository.saveAndFlush(listSafetyClass);

        // Get all the listSafetyClassList where description is not null
        defaultListSafetyClassShouldBeFound("description.specified=true");

        // Get all the listSafetyClassList where description is null
        defaultListSafetyClassShouldNotBeFound("description.specified=false");
    }

    @Test
    @Transactional
    public void getAllListSafetyClassesByDateCreateIsEqualToSomething() throws Exception {
        // Initialize the database
        listSafetyClassRepository.saveAndFlush(listSafetyClass);

        // Get all the listSafetyClassList where dateCreate equals to DEFAULT_DATE_CREATE
        defaultListSafetyClassShouldBeFound("dateCreate.equals=" + DEFAULT_DATE_CREATE);

        // Get all the listSafetyClassList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListSafetyClassShouldNotBeFound("dateCreate.equals=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListSafetyClassesByDateCreateIsInShouldWork() throws Exception {
        // Initialize the database
        listSafetyClassRepository.saveAndFlush(listSafetyClass);

        // Get all the listSafetyClassList where dateCreate in DEFAULT_DATE_CREATE or UPDATED_DATE_CREATE
        defaultListSafetyClassShouldBeFound("dateCreate.in=" + DEFAULT_DATE_CREATE + "," + UPDATED_DATE_CREATE);

        // Get all the listSafetyClassList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListSafetyClassShouldNotBeFound("dateCreate.in=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListSafetyClassesByDateCreateIsNullOrNotNull() throws Exception {
        // Initialize the database
        listSafetyClassRepository.saveAndFlush(listSafetyClass);

        // Get all the listSafetyClassList where dateCreate is not null
        defaultListSafetyClassShouldBeFound("dateCreate.specified=true");

        // Get all the listSafetyClassList where dateCreate is null
        defaultListSafetyClassShouldNotBeFound("dateCreate.specified=false");
    }

    @Test
    @Transactional
    public void getAllListSafetyClassesByDateEditIsEqualToSomething() throws Exception {
        // Initialize the database
        listSafetyClassRepository.saveAndFlush(listSafetyClass);

        // Get all the listSafetyClassList where dateEdit equals to DEFAULT_DATE_EDIT
        defaultListSafetyClassShouldBeFound("dateEdit.equals=" + DEFAULT_DATE_EDIT);

        // Get all the listSafetyClassList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListSafetyClassShouldNotBeFound("dateEdit.equals=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListSafetyClassesByDateEditIsInShouldWork() throws Exception {
        // Initialize the database
        listSafetyClassRepository.saveAndFlush(listSafetyClass);

        // Get all the listSafetyClassList where dateEdit in DEFAULT_DATE_EDIT or UPDATED_DATE_EDIT
        defaultListSafetyClassShouldBeFound("dateEdit.in=" + DEFAULT_DATE_EDIT + "," + UPDATED_DATE_EDIT);

        // Get all the listSafetyClassList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListSafetyClassShouldNotBeFound("dateEdit.in=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListSafetyClassesByDateEditIsNullOrNotNull() throws Exception {
        // Initialize the database
        listSafetyClassRepository.saveAndFlush(listSafetyClass);

        // Get all the listSafetyClassList where dateEdit is not null
        defaultListSafetyClassShouldBeFound("dateEdit.specified=true");

        // Get all the listSafetyClassList where dateEdit is null
        defaultListSafetyClassShouldNotBeFound("dateEdit.specified=false");
    }

    @Test
    @Transactional
    public void getAllListSafetyClassesByCreatorIsEqualToSomething() throws Exception {
        // Initialize the database
        listSafetyClassRepository.saveAndFlush(listSafetyClass);

        // Get all the listSafetyClassList where creator equals to DEFAULT_CREATOR
        defaultListSafetyClassShouldBeFound("creator.equals=" + DEFAULT_CREATOR);

        // Get all the listSafetyClassList where creator equals to UPDATED_CREATOR
        defaultListSafetyClassShouldNotBeFound("creator.equals=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListSafetyClassesByCreatorIsInShouldWork() throws Exception {
        // Initialize the database
        listSafetyClassRepository.saveAndFlush(listSafetyClass);

        // Get all the listSafetyClassList where creator in DEFAULT_CREATOR or UPDATED_CREATOR
        defaultListSafetyClassShouldBeFound("creator.in=" + DEFAULT_CREATOR + "," + UPDATED_CREATOR);

        // Get all the listSafetyClassList where creator equals to UPDATED_CREATOR
        defaultListSafetyClassShouldNotBeFound("creator.in=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListSafetyClassesByCreatorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listSafetyClassRepository.saveAndFlush(listSafetyClass);

        // Get all the listSafetyClassList where creator is not null
        defaultListSafetyClassShouldBeFound("creator.specified=true");

        // Get all the listSafetyClassList where creator is null
        defaultListSafetyClassShouldNotBeFound("creator.specified=false");
    }

    @Test
    @Transactional
    public void getAllListSafetyClassesByEditorIsEqualToSomething() throws Exception {
        // Initialize the database
        listSafetyClassRepository.saveAndFlush(listSafetyClass);

        // Get all the listSafetyClassList where editor equals to DEFAULT_EDITOR
        defaultListSafetyClassShouldBeFound("editor.equals=" + DEFAULT_EDITOR);

        // Get all the listSafetyClassList where editor equals to UPDATED_EDITOR
        defaultListSafetyClassShouldNotBeFound("editor.equals=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListSafetyClassesByEditorIsInShouldWork() throws Exception {
        // Initialize the database
        listSafetyClassRepository.saveAndFlush(listSafetyClass);

        // Get all the listSafetyClassList where editor in DEFAULT_EDITOR or UPDATED_EDITOR
        defaultListSafetyClassShouldBeFound("editor.in=" + DEFAULT_EDITOR + "," + UPDATED_EDITOR);

        // Get all the listSafetyClassList where editor equals to UPDATED_EDITOR
        defaultListSafetyClassShouldNotBeFound("editor.in=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListSafetyClassesByEditorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listSafetyClassRepository.saveAndFlush(listSafetyClass);

        // Get all the listSafetyClassList where editor is not null
        defaultListSafetyClassShouldBeFound("editor.specified=true");

        // Get all the listSafetyClassList where editor is null
        defaultListSafetyClassShouldNotBeFound("editor.specified=false");
    }

    @Test
    @Transactional
    public void getAllListSafetyClassesByPipelineSectionIsEqualToSomething() throws Exception {
        // Initialize the database
        PipelineSection pipelineSection = PipelineSectionResourceIT.createEntity(em);
        em.persist(pipelineSection);
        em.flush();
        listSafetyClass.addPipelineSection(pipelineSection);
        listSafetyClassRepository.saveAndFlush(listSafetyClass);
        Long pipelineSectionId = pipelineSection.getId();

        // Get all the listSafetyClassList where pipelineSection equals to pipelineSectionId
        defaultListSafetyClassShouldBeFound("pipelineSectionId.equals=" + pipelineSectionId);

        // Get all the listSafetyClassList where pipelineSection equals to pipelineSectionId + 1
        defaultListSafetyClassShouldNotBeFound("pipelineSectionId.equals=" + (pipelineSectionId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultListSafetyClassShouldBeFound(String filter) throws Exception {
        restListSafetyClassMockMvc.perform(get("/api/list-safety-classes?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listSafetyClass.getId().intValue())))
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
        restListSafetyClassMockMvc.perform(get("/api/list-safety-classes/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultListSafetyClassShouldNotBeFound(String filter) throws Exception {
        restListSafetyClassMockMvc.perform(get("/api/list-safety-classes?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restListSafetyClassMockMvc.perform(get("/api/list-safety-classes/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingListSafetyClass() throws Exception {
        // Get the listSafetyClass
        restListSafetyClassMockMvc.perform(get("/api/list-safety-classes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateListSafetyClass() throws Exception {
        // Initialize the database
        listSafetyClassRepository.saveAndFlush(listSafetyClass);

        int databaseSizeBeforeUpdate = listSafetyClassRepository.findAll().size();

        // Update the listSafetyClass
        ListSafetyClass updatedListSafetyClass = listSafetyClassRepository.findById(listSafetyClass.getId()).get();
        // Disconnect from session so that the updates on updatedListSafetyClass are not directly saved in db
        em.detach(updatedListSafetyClass);
        updatedListSafetyClass
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        ListSafetyClassDTO listSafetyClassDTO = listSafetyClassMapper.toDto(updatedListSafetyClass);

        restListSafetyClassMockMvc.perform(put("/api/list-safety-classes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listSafetyClassDTO)))
            .andExpect(status().isOk());

        // Validate the ListSafetyClass in the database
        List<ListSafetyClass> listSafetyClassList = listSafetyClassRepository.findAll();
        assertThat(listSafetyClassList).hasSize(databaseSizeBeforeUpdate);
        ListSafetyClass testListSafetyClass = listSafetyClassList.get(listSafetyClassList.size() - 1);
        assertThat(testListSafetyClass.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testListSafetyClass.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testListSafetyClass.getFullName()).isEqualTo(UPDATED_FULL_NAME);
        assertThat(testListSafetyClass.getIsCurrentFlag()).isEqualTo(UPDATED_IS_CURRENT_FLAG);
        assertThat(testListSafetyClass.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testListSafetyClass.getDateCreate()).isEqualTo(UPDATED_DATE_CREATE);
        assertThat(testListSafetyClass.getDateEdit()).isEqualTo(UPDATED_DATE_EDIT);
        assertThat(testListSafetyClass.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testListSafetyClass.getEditor()).isEqualTo(UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void updateNonExistingListSafetyClass() throws Exception {
        int databaseSizeBeforeUpdate = listSafetyClassRepository.findAll().size();

        // Create the ListSafetyClass
        ListSafetyClassDTO listSafetyClassDTO = listSafetyClassMapper.toDto(listSafetyClass);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restListSafetyClassMockMvc.perform(put("/api/list-safety-classes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listSafetyClassDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListSafetyClass in the database
        List<ListSafetyClass> listSafetyClassList = listSafetyClassRepository.findAll();
        assertThat(listSafetyClassList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteListSafetyClass() throws Exception {
        // Initialize the database
        listSafetyClassRepository.saveAndFlush(listSafetyClass);

        int databaseSizeBeforeDelete = listSafetyClassRepository.findAll().size();

        // Delete the listSafetyClass
        restListSafetyClassMockMvc.perform(delete("/api/list-safety-classes/{id}", listSafetyClass.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<ListSafetyClass> listSafetyClassList = listSafetyClassRepository.findAll();
        assertThat(listSafetyClassList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListSafetyClass.class);
        ListSafetyClass listSafetyClass1 = new ListSafetyClass();
        listSafetyClass1.setId(1L);
        ListSafetyClass listSafetyClass2 = new ListSafetyClass();
        listSafetyClass2.setId(listSafetyClass1.getId());
        assertThat(listSafetyClass1).isEqualTo(listSafetyClass2);
        listSafetyClass2.setId(2L);
        assertThat(listSafetyClass1).isNotEqualTo(listSafetyClass2);
        listSafetyClass1.setId(null);
        assertThat(listSafetyClass1).isNotEqualTo(listSafetyClass2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListSafetyClassDTO.class);
        ListSafetyClassDTO listSafetyClassDTO1 = new ListSafetyClassDTO();
        listSafetyClassDTO1.setId(1L);
        ListSafetyClassDTO listSafetyClassDTO2 = new ListSafetyClassDTO();
        assertThat(listSafetyClassDTO1).isNotEqualTo(listSafetyClassDTO2);
        listSafetyClassDTO2.setId(listSafetyClassDTO1.getId());
        assertThat(listSafetyClassDTO1).isEqualTo(listSafetyClassDTO2);
        listSafetyClassDTO2.setId(2L);
        assertThat(listSafetyClassDTO1).isNotEqualTo(listSafetyClassDTO2);
        listSafetyClassDTO1.setId(null);
        assertThat(listSafetyClassDTO1).isNotEqualTo(listSafetyClassDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(listSafetyClassMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(listSafetyClassMapper.fromId(null)).isNull();
    }
}
