package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.ListObjectType;
import io.github.jhipster.application.domain.BaseClass;
import io.github.jhipster.application.repository.ListObjectTypeRepository;
import io.github.jhipster.application.service.ListObjectTypeService;
import io.github.jhipster.application.service.dto.ListObjectTypeDTO;
import io.github.jhipster.application.service.mapper.ListObjectTypeMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.ListObjectTypeCriteria;
import io.github.jhipster.application.service.ListObjectTypeQueryService;

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
 * Integration tests for the {@Link ListObjectTypeResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class ListObjectTypeResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_FULL_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FULL_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_TABLE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_TABLE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_TABLE_NAME_HIST = "AAAAAAAAAA";
    private static final String UPDATED_TABLE_NAME_HIST = "BBBBBBBBBB";

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
    private ListObjectTypeRepository listObjectTypeRepository;

    @Autowired
    private ListObjectTypeMapper listObjectTypeMapper;

    @Autowired
    private ListObjectTypeService listObjectTypeService;

    @Autowired
    private ListObjectTypeQueryService listObjectTypeQueryService;

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

    private MockMvc restListObjectTypeMockMvc;

    private ListObjectType listObjectType;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ListObjectTypeResource listObjectTypeResource = new ListObjectTypeResource(listObjectTypeService, listObjectTypeQueryService);
        this.restListObjectTypeMockMvc = MockMvcBuilders.standaloneSetup(listObjectTypeResource)
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
    public static ListObjectType createEntity(EntityManager em) {
        ListObjectType listObjectType = new ListObjectType()
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME)
            .fullName(DEFAULT_FULL_NAME)
            .tableName(DEFAULT_TABLE_NAME)
            .tableNameHist(DEFAULT_TABLE_NAME_HIST)
            .isCurrentFlag(DEFAULT_IS_CURRENT_FLAG)
            .description(DEFAULT_DESCRIPTION)
            .dateCreate(DEFAULT_DATE_CREATE)
            .dateEdit(DEFAULT_DATE_EDIT)
            .creator(DEFAULT_CREATOR)
            .editor(DEFAULT_EDITOR);
        return listObjectType;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ListObjectType createUpdatedEntity(EntityManager em) {
        ListObjectType listObjectType = new ListObjectType()
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .tableName(UPDATED_TABLE_NAME)
            .tableNameHist(UPDATED_TABLE_NAME_HIST)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        return listObjectType;
    }

    @BeforeEach
    public void initTest() {
        listObjectType = createEntity(em);
    }

    @Test
    @Transactional
    public void createListObjectType() throws Exception {
        int databaseSizeBeforeCreate = listObjectTypeRepository.findAll().size();

        // Create the ListObjectType
        ListObjectTypeDTO listObjectTypeDTO = listObjectTypeMapper.toDto(listObjectType);
        restListObjectTypeMockMvc.perform(post("/api/list-object-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listObjectTypeDTO)))
            .andExpect(status().isCreated());

        // Validate the ListObjectType in the database
        List<ListObjectType> listObjectTypeList = listObjectTypeRepository.findAll();
        assertThat(listObjectTypeList).hasSize(databaseSizeBeforeCreate + 1);
        ListObjectType testListObjectType = listObjectTypeList.get(listObjectTypeList.size() - 1);
        assertThat(testListObjectType.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testListObjectType.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testListObjectType.getFullName()).isEqualTo(DEFAULT_FULL_NAME);
        assertThat(testListObjectType.getTableName()).isEqualTo(DEFAULT_TABLE_NAME);
        assertThat(testListObjectType.getTableNameHist()).isEqualTo(DEFAULT_TABLE_NAME_HIST);
        assertThat(testListObjectType.getIsCurrentFlag()).isEqualTo(DEFAULT_IS_CURRENT_FLAG);
        assertThat(testListObjectType.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testListObjectType.getDateCreate()).isEqualTo(DEFAULT_DATE_CREATE);
        assertThat(testListObjectType.getDateEdit()).isEqualTo(DEFAULT_DATE_EDIT);
        assertThat(testListObjectType.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testListObjectType.getEditor()).isEqualTo(DEFAULT_EDITOR);
    }

    @Test
    @Transactional
    public void createListObjectTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = listObjectTypeRepository.findAll().size();

        // Create the ListObjectType with an existing ID
        listObjectType.setId(1L);
        ListObjectTypeDTO listObjectTypeDTO = listObjectTypeMapper.toDto(listObjectType);

        // An entity with an existing ID cannot be created, so this API call must fail
        restListObjectTypeMockMvc.perform(post("/api/list-object-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listObjectTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListObjectType in the database
        List<ListObjectType> listObjectTypeList = listObjectTypeRepository.findAll();
        assertThat(listObjectTypeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = listObjectTypeRepository.findAll().size();
        // set the field null
        listObjectType.setCode(null);

        // Create the ListObjectType, which fails.
        ListObjectTypeDTO listObjectTypeDTO = listObjectTypeMapper.toDto(listObjectType);

        restListObjectTypeMockMvc.perform(post("/api/list-object-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listObjectTypeDTO)))
            .andExpect(status().isBadRequest());

        List<ListObjectType> listObjectTypeList = listObjectTypeRepository.findAll();
        assertThat(listObjectTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listObjectTypeRepository.findAll().size();
        // set the field null
        listObjectType.setName(null);

        // Create the ListObjectType, which fails.
        ListObjectTypeDTO listObjectTypeDTO = listObjectTypeMapper.toDto(listObjectType);

        restListObjectTypeMockMvc.perform(post("/api/list-object-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listObjectTypeDTO)))
            .andExpect(status().isBadRequest());

        List<ListObjectType> listObjectTypeList = listObjectTypeRepository.findAll();
        assertThat(listObjectTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFullNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listObjectTypeRepository.findAll().size();
        // set the field null
        listObjectType.setFullName(null);

        // Create the ListObjectType, which fails.
        ListObjectTypeDTO listObjectTypeDTO = listObjectTypeMapper.toDto(listObjectType);

        restListObjectTypeMockMvc.perform(post("/api/list-object-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listObjectTypeDTO)))
            .andExpect(status().isBadRequest());

        List<ListObjectType> listObjectTypeList = listObjectTypeRepository.findAll();
        assertThat(listObjectTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTableNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listObjectTypeRepository.findAll().size();
        // set the field null
        listObjectType.setTableName(null);

        // Create the ListObjectType, which fails.
        ListObjectTypeDTO listObjectTypeDTO = listObjectTypeMapper.toDto(listObjectType);

        restListObjectTypeMockMvc.perform(post("/api/list-object-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listObjectTypeDTO)))
            .andExpect(status().isBadRequest());

        List<ListObjectType> listObjectTypeList = listObjectTypeRepository.findAll();
        assertThat(listObjectTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsCurrentFlagIsRequired() throws Exception {
        int databaseSizeBeforeTest = listObjectTypeRepository.findAll().size();
        // set the field null
        listObjectType.setIsCurrentFlag(null);

        // Create the ListObjectType, which fails.
        ListObjectTypeDTO listObjectTypeDTO = listObjectTypeMapper.toDto(listObjectType);

        restListObjectTypeMockMvc.perform(post("/api/list-object-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listObjectTypeDTO)))
            .andExpect(status().isBadRequest());

        List<ListObjectType> listObjectTypeList = listObjectTypeRepository.findAll();
        assertThat(listObjectTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllListObjectTypes() throws Exception {
        // Initialize the database
        listObjectTypeRepository.saveAndFlush(listObjectType);

        // Get all the listObjectTypeList
        restListObjectTypeMockMvc.perform(get("/api/list-object-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listObjectType.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].fullName").value(hasItem(DEFAULT_FULL_NAME.toString())))
            .andExpect(jsonPath("$.[*].tableName").value(hasItem(DEFAULT_TABLE_NAME.toString())))
            .andExpect(jsonPath("$.[*].tableNameHist").value(hasItem(DEFAULT_TABLE_NAME_HIST.toString())))
            .andExpect(jsonPath("$.[*].isCurrentFlag").value(hasItem(DEFAULT_IS_CURRENT_FLAG)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].dateCreate").value(hasItem(DEFAULT_DATE_CREATE.toString())))
            .andExpect(jsonPath("$.[*].dateEdit").value(hasItem(DEFAULT_DATE_EDIT.toString())))
            .andExpect(jsonPath("$.[*].creator").value(hasItem(DEFAULT_CREATOR.toString())))
            .andExpect(jsonPath("$.[*].editor").value(hasItem(DEFAULT_EDITOR.toString())));
    }
    
    @Test
    @Transactional
    public void getListObjectType() throws Exception {
        // Initialize the database
        listObjectTypeRepository.saveAndFlush(listObjectType);

        // Get the listObjectType
        restListObjectTypeMockMvc.perform(get("/api/list-object-types/{id}", listObjectType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(listObjectType.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.fullName").value(DEFAULT_FULL_NAME.toString()))
            .andExpect(jsonPath("$.tableName").value(DEFAULT_TABLE_NAME.toString()))
            .andExpect(jsonPath("$.tableNameHist").value(DEFAULT_TABLE_NAME_HIST.toString()))
            .andExpect(jsonPath("$.isCurrentFlag").value(DEFAULT_IS_CURRENT_FLAG))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.dateCreate").value(DEFAULT_DATE_CREATE.toString()))
            .andExpect(jsonPath("$.dateEdit").value(DEFAULT_DATE_EDIT.toString()))
            .andExpect(jsonPath("$.creator").value(DEFAULT_CREATOR.toString()))
            .andExpect(jsonPath("$.editor").value(DEFAULT_EDITOR.toString()));
    }

    @Test
    @Transactional
    public void getAllListObjectTypesByCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        listObjectTypeRepository.saveAndFlush(listObjectType);

        // Get all the listObjectTypeList where code equals to DEFAULT_CODE
        defaultListObjectTypeShouldBeFound("code.equals=" + DEFAULT_CODE);

        // Get all the listObjectTypeList where code equals to UPDATED_CODE
        defaultListObjectTypeShouldNotBeFound("code.equals=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListObjectTypesByCodeIsInShouldWork() throws Exception {
        // Initialize the database
        listObjectTypeRepository.saveAndFlush(listObjectType);

        // Get all the listObjectTypeList where code in DEFAULT_CODE or UPDATED_CODE
        defaultListObjectTypeShouldBeFound("code.in=" + DEFAULT_CODE + "," + UPDATED_CODE);

        // Get all the listObjectTypeList where code equals to UPDATED_CODE
        defaultListObjectTypeShouldNotBeFound("code.in=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListObjectTypesByCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        listObjectTypeRepository.saveAndFlush(listObjectType);

        // Get all the listObjectTypeList where code is not null
        defaultListObjectTypeShouldBeFound("code.specified=true");

        // Get all the listObjectTypeList where code is null
        defaultListObjectTypeShouldNotBeFound("code.specified=false");
    }

    @Test
    @Transactional
    public void getAllListObjectTypesByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listObjectTypeRepository.saveAndFlush(listObjectType);

        // Get all the listObjectTypeList where name equals to DEFAULT_NAME
        defaultListObjectTypeShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the listObjectTypeList where name equals to UPDATED_NAME
        defaultListObjectTypeShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListObjectTypesByNameIsInShouldWork() throws Exception {
        // Initialize the database
        listObjectTypeRepository.saveAndFlush(listObjectType);

        // Get all the listObjectTypeList where name in DEFAULT_NAME or UPDATED_NAME
        defaultListObjectTypeShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the listObjectTypeList where name equals to UPDATED_NAME
        defaultListObjectTypeShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListObjectTypesByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listObjectTypeRepository.saveAndFlush(listObjectType);

        // Get all the listObjectTypeList where name is not null
        defaultListObjectTypeShouldBeFound("name.specified=true");

        // Get all the listObjectTypeList where name is null
        defaultListObjectTypeShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    public void getAllListObjectTypesByFullNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listObjectTypeRepository.saveAndFlush(listObjectType);

        // Get all the listObjectTypeList where fullName equals to DEFAULT_FULL_NAME
        defaultListObjectTypeShouldBeFound("fullName.equals=" + DEFAULT_FULL_NAME);

        // Get all the listObjectTypeList where fullName equals to UPDATED_FULL_NAME
        defaultListObjectTypeShouldNotBeFound("fullName.equals=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListObjectTypesByFullNameIsInShouldWork() throws Exception {
        // Initialize the database
        listObjectTypeRepository.saveAndFlush(listObjectType);

        // Get all the listObjectTypeList where fullName in DEFAULT_FULL_NAME or UPDATED_FULL_NAME
        defaultListObjectTypeShouldBeFound("fullName.in=" + DEFAULT_FULL_NAME + "," + UPDATED_FULL_NAME);

        // Get all the listObjectTypeList where fullName equals to UPDATED_FULL_NAME
        defaultListObjectTypeShouldNotBeFound("fullName.in=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListObjectTypesByFullNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listObjectTypeRepository.saveAndFlush(listObjectType);

        // Get all the listObjectTypeList where fullName is not null
        defaultListObjectTypeShouldBeFound("fullName.specified=true");

        // Get all the listObjectTypeList where fullName is null
        defaultListObjectTypeShouldNotBeFound("fullName.specified=false");
    }

    @Test
    @Transactional
    public void getAllListObjectTypesByTableNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listObjectTypeRepository.saveAndFlush(listObjectType);

        // Get all the listObjectTypeList where tableName equals to DEFAULT_TABLE_NAME
        defaultListObjectTypeShouldBeFound("tableName.equals=" + DEFAULT_TABLE_NAME);

        // Get all the listObjectTypeList where tableName equals to UPDATED_TABLE_NAME
        defaultListObjectTypeShouldNotBeFound("tableName.equals=" + UPDATED_TABLE_NAME);
    }

    @Test
    @Transactional
    public void getAllListObjectTypesByTableNameIsInShouldWork() throws Exception {
        // Initialize the database
        listObjectTypeRepository.saveAndFlush(listObjectType);

        // Get all the listObjectTypeList where tableName in DEFAULT_TABLE_NAME or UPDATED_TABLE_NAME
        defaultListObjectTypeShouldBeFound("tableName.in=" + DEFAULT_TABLE_NAME + "," + UPDATED_TABLE_NAME);

        // Get all the listObjectTypeList where tableName equals to UPDATED_TABLE_NAME
        defaultListObjectTypeShouldNotBeFound("tableName.in=" + UPDATED_TABLE_NAME);
    }

    @Test
    @Transactional
    public void getAllListObjectTypesByTableNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listObjectTypeRepository.saveAndFlush(listObjectType);

        // Get all the listObjectTypeList where tableName is not null
        defaultListObjectTypeShouldBeFound("tableName.specified=true");

        // Get all the listObjectTypeList where tableName is null
        defaultListObjectTypeShouldNotBeFound("tableName.specified=false");
    }

    @Test
    @Transactional
    public void getAllListObjectTypesByTableNameHistIsEqualToSomething() throws Exception {
        // Initialize the database
        listObjectTypeRepository.saveAndFlush(listObjectType);

        // Get all the listObjectTypeList where tableNameHist equals to DEFAULT_TABLE_NAME_HIST
        defaultListObjectTypeShouldBeFound("tableNameHist.equals=" + DEFAULT_TABLE_NAME_HIST);

        // Get all the listObjectTypeList where tableNameHist equals to UPDATED_TABLE_NAME_HIST
        defaultListObjectTypeShouldNotBeFound("tableNameHist.equals=" + UPDATED_TABLE_NAME_HIST);
    }

    @Test
    @Transactional
    public void getAllListObjectTypesByTableNameHistIsInShouldWork() throws Exception {
        // Initialize the database
        listObjectTypeRepository.saveAndFlush(listObjectType);

        // Get all the listObjectTypeList where tableNameHist in DEFAULT_TABLE_NAME_HIST or UPDATED_TABLE_NAME_HIST
        defaultListObjectTypeShouldBeFound("tableNameHist.in=" + DEFAULT_TABLE_NAME_HIST + "," + UPDATED_TABLE_NAME_HIST);

        // Get all the listObjectTypeList where tableNameHist equals to UPDATED_TABLE_NAME_HIST
        defaultListObjectTypeShouldNotBeFound("tableNameHist.in=" + UPDATED_TABLE_NAME_HIST);
    }

    @Test
    @Transactional
    public void getAllListObjectTypesByTableNameHistIsNullOrNotNull() throws Exception {
        // Initialize the database
        listObjectTypeRepository.saveAndFlush(listObjectType);

        // Get all the listObjectTypeList where tableNameHist is not null
        defaultListObjectTypeShouldBeFound("tableNameHist.specified=true");

        // Get all the listObjectTypeList where tableNameHist is null
        defaultListObjectTypeShouldNotBeFound("tableNameHist.specified=false");
    }

    @Test
    @Transactional
    public void getAllListObjectTypesByIsCurrentFlagIsEqualToSomething() throws Exception {
        // Initialize the database
        listObjectTypeRepository.saveAndFlush(listObjectType);

        // Get all the listObjectTypeList where isCurrentFlag equals to DEFAULT_IS_CURRENT_FLAG
        defaultListObjectTypeShouldBeFound("isCurrentFlag.equals=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listObjectTypeList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListObjectTypeShouldNotBeFound("isCurrentFlag.equals=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListObjectTypesByIsCurrentFlagIsInShouldWork() throws Exception {
        // Initialize the database
        listObjectTypeRepository.saveAndFlush(listObjectType);

        // Get all the listObjectTypeList where isCurrentFlag in DEFAULT_IS_CURRENT_FLAG or UPDATED_IS_CURRENT_FLAG
        defaultListObjectTypeShouldBeFound("isCurrentFlag.in=" + DEFAULT_IS_CURRENT_FLAG + "," + UPDATED_IS_CURRENT_FLAG);

        // Get all the listObjectTypeList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListObjectTypeShouldNotBeFound("isCurrentFlag.in=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListObjectTypesByIsCurrentFlagIsNullOrNotNull() throws Exception {
        // Initialize the database
        listObjectTypeRepository.saveAndFlush(listObjectType);

        // Get all the listObjectTypeList where isCurrentFlag is not null
        defaultListObjectTypeShouldBeFound("isCurrentFlag.specified=true");

        // Get all the listObjectTypeList where isCurrentFlag is null
        defaultListObjectTypeShouldNotBeFound("isCurrentFlag.specified=false");
    }

    @Test
    @Transactional
    public void getAllListObjectTypesByIsCurrentFlagIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        listObjectTypeRepository.saveAndFlush(listObjectType);

        // Get all the listObjectTypeList where isCurrentFlag greater than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListObjectTypeShouldBeFound("isCurrentFlag.greaterOrEqualThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listObjectTypeList where isCurrentFlag greater than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListObjectTypeShouldNotBeFound("isCurrentFlag.greaterOrEqualThan=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListObjectTypesByIsCurrentFlagIsLessThanSomething() throws Exception {
        // Initialize the database
        listObjectTypeRepository.saveAndFlush(listObjectType);

        // Get all the listObjectTypeList where isCurrentFlag less than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListObjectTypeShouldNotBeFound("isCurrentFlag.lessThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listObjectTypeList where isCurrentFlag less than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListObjectTypeShouldBeFound("isCurrentFlag.lessThan=" + UPDATED_IS_CURRENT_FLAG);
    }


    @Test
    @Transactional
    public void getAllListObjectTypesByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        listObjectTypeRepository.saveAndFlush(listObjectType);

        // Get all the listObjectTypeList where description equals to DEFAULT_DESCRIPTION
        defaultListObjectTypeShouldBeFound("description.equals=" + DEFAULT_DESCRIPTION);

        // Get all the listObjectTypeList where description equals to UPDATED_DESCRIPTION
        defaultListObjectTypeShouldNotBeFound("description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListObjectTypesByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        listObjectTypeRepository.saveAndFlush(listObjectType);

        // Get all the listObjectTypeList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
        defaultListObjectTypeShouldBeFound("description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION);

        // Get all the listObjectTypeList where description equals to UPDATED_DESCRIPTION
        defaultListObjectTypeShouldNotBeFound("description.in=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListObjectTypesByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        listObjectTypeRepository.saveAndFlush(listObjectType);

        // Get all the listObjectTypeList where description is not null
        defaultListObjectTypeShouldBeFound("description.specified=true");

        // Get all the listObjectTypeList where description is null
        defaultListObjectTypeShouldNotBeFound("description.specified=false");
    }

    @Test
    @Transactional
    public void getAllListObjectTypesByDateCreateIsEqualToSomething() throws Exception {
        // Initialize the database
        listObjectTypeRepository.saveAndFlush(listObjectType);

        // Get all the listObjectTypeList where dateCreate equals to DEFAULT_DATE_CREATE
        defaultListObjectTypeShouldBeFound("dateCreate.equals=" + DEFAULT_DATE_CREATE);

        // Get all the listObjectTypeList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListObjectTypeShouldNotBeFound("dateCreate.equals=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListObjectTypesByDateCreateIsInShouldWork() throws Exception {
        // Initialize the database
        listObjectTypeRepository.saveAndFlush(listObjectType);

        // Get all the listObjectTypeList where dateCreate in DEFAULT_DATE_CREATE or UPDATED_DATE_CREATE
        defaultListObjectTypeShouldBeFound("dateCreate.in=" + DEFAULT_DATE_CREATE + "," + UPDATED_DATE_CREATE);

        // Get all the listObjectTypeList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListObjectTypeShouldNotBeFound("dateCreate.in=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListObjectTypesByDateCreateIsNullOrNotNull() throws Exception {
        // Initialize the database
        listObjectTypeRepository.saveAndFlush(listObjectType);

        // Get all the listObjectTypeList where dateCreate is not null
        defaultListObjectTypeShouldBeFound("dateCreate.specified=true");

        // Get all the listObjectTypeList where dateCreate is null
        defaultListObjectTypeShouldNotBeFound("dateCreate.specified=false");
    }

    @Test
    @Transactional
    public void getAllListObjectTypesByDateEditIsEqualToSomething() throws Exception {
        // Initialize the database
        listObjectTypeRepository.saveAndFlush(listObjectType);

        // Get all the listObjectTypeList where dateEdit equals to DEFAULT_DATE_EDIT
        defaultListObjectTypeShouldBeFound("dateEdit.equals=" + DEFAULT_DATE_EDIT);

        // Get all the listObjectTypeList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListObjectTypeShouldNotBeFound("dateEdit.equals=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListObjectTypesByDateEditIsInShouldWork() throws Exception {
        // Initialize the database
        listObjectTypeRepository.saveAndFlush(listObjectType);

        // Get all the listObjectTypeList where dateEdit in DEFAULT_DATE_EDIT or UPDATED_DATE_EDIT
        defaultListObjectTypeShouldBeFound("dateEdit.in=" + DEFAULT_DATE_EDIT + "," + UPDATED_DATE_EDIT);

        // Get all the listObjectTypeList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListObjectTypeShouldNotBeFound("dateEdit.in=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListObjectTypesByDateEditIsNullOrNotNull() throws Exception {
        // Initialize the database
        listObjectTypeRepository.saveAndFlush(listObjectType);

        // Get all the listObjectTypeList where dateEdit is not null
        defaultListObjectTypeShouldBeFound("dateEdit.specified=true");

        // Get all the listObjectTypeList where dateEdit is null
        defaultListObjectTypeShouldNotBeFound("dateEdit.specified=false");
    }

    @Test
    @Transactional
    public void getAllListObjectTypesByCreatorIsEqualToSomething() throws Exception {
        // Initialize the database
        listObjectTypeRepository.saveAndFlush(listObjectType);

        // Get all the listObjectTypeList where creator equals to DEFAULT_CREATOR
        defaultListObjectTypeShouldBeFound("creator.equals=" + DEFAULT_CREATOR);

        // Get all the listObjectTypeList where creator equals to UPDATED_CREATOR
        defaultListObjectTypeShouldNotBeFound("creator.equals=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListObjectTypesByCreatorIsInShouldWork() throws Exception {
        // Initialize the database
        listObjectTypeRepository.saveAndFlush(listObjectType);

        // Get all the listObjectTypeList where creator in DEFAULT_CREATOR or UPDATED_CREATOR
        defaultListObjectTypeShouldBeFound("creator.in=" + DEFAULT_CREATOR + "," + UPDATED_CREATOR);

        // Get all the listObjectTypeList where creator equals to UPDATED_CREATOR
        defaultListObjectTypeShouldNotBeFound("creator.in=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListObjectTypesByCreatorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listObjectTypeRepository.saveAndFlush(listObjectType);

        // Get all the listObjectTypeList where creator is not null
        defaultListObjectTypeShouldBeFound("creator.specified=true");

        // Get all the listObjectTypeList where creator is null
        defaultListObjectTypeShouldNotBeFound("creator.specified=false");
    }

    @Test
    @Transactional
    public void getAllListObjectTypesByEditorIsEqualToSomething() throws Exception {
        // Initialize the database
        listObjectTypeRepository.saveAndFlush(listObjectType);

        // Get all the listObjectTypeList where editor equals to DEFAULT_EDITOR
        defaultListObjectTypeShouldBeFound("editor.equals=" + DEFAULT_EDITOR);

        // Get all the listObjectTypeList where editor equals to UPDATED_EDITOR
        defaultListObjectTypeShouldNotBeFound("editor.equals=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListObjectTypesByEditorIsInShouldWork() throws Exception {
        // Initialize the database
        listObjectTypeRepository.saveAndFlush(listObjectType);

        // Get all the listObjectTypeList where editor in DEFAULT_EDITOR or UPDATED_EDITOR
        defaultListObjectTypeShouldBeFound("editor.in=" + DEFAULT_EDITOR + "," + UPDATED_EDITOR);

        // Get all the listObjectTypeList where editor equals to UPDATED_EDITOR
        defaultListObjectTypeShouldNotBeFound("editor.in=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListObjectTypesByEditorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listObjectTypeRepository.saveAndFlush(listObjectType);

        // Get all the listObjectTypeList where editor is not null
        defaultListObjectTypeShouldBeFound("editor.specified=true");

        // Get all the listObjectTypeList where editor is null
        defaultListObjectTypeShouldNotBeFound("editor.specified=false");
    }

    @Test
    @Transactional
    public void getAllListObjectTypesByBaseClassIsEqualToSomething() throws Exception {
        // Initialize the database
        BaseClass baseClass = BaseClassResourceIT.createEntity(em);
        em.persist(baseClass);
        em.flush();
        listObjectType.addBaseClass(baseClass);
        listObjectTypeRepository.saveAndFlush(listObjectType);
        Long baseClassId = baseClass.getId();

        // Get all the listObjectTypeList where baseClass equals to baseClassId
        defaultListObjectTypeShouldBeFound("baseClassId.equals=" + baseClassId);

        // Get all the listObjectTypeList where baseClass equals to baseClassId + 1
        defaultListObjectTypeShouldNotBeFound("baseClassId.equals=" + (baseClassId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultListObjectTypeShouldBeFound(String filter) throws Exception {
        restListObjectTypeMockMvc.perform(get("/api/list-object-types?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listObjectType.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].fullName").value(hasItem(DEFAULT_FULL_NAME)))
            .andExpect(jsonPath("$.[*].tableName").value(hasItem(DEFAULT_TABLE_NAME)))
            .andExpect(jsonPath("$.[*].tableNameHist").value(hasItem(DEFAULT_TABLE_NAME_HIST)))
            .andExpect(jsonPath("$.[*].isCurrentFlag").value(hasItem(DEFAULT_IS_CURRENT_FLAG)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].dateCreate").value(hasItem(DEFAULT_DATE_CREATE.toString())))
            .andExpect(jsonPath("$.[*].dateEdit").value(hasItem(DEFAULT_DATE_EDIT.toString())))
            .andExpect(jsonPath("$.[*].creator").value(hasItem(DEFAULT_CREATOR)))
            .andExpect(jsonPath("$.[*].editor").value(hasItem(DEFAULT_EDITOR)));

        // Check, that the count call also returns 1
        restListObjectTypeMockMvc.perform(get("/api/list-object-types/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultListObjectTypeShouldNotBeFound(String filter) throws Exception {
        restListObjectTypeMockMvc.perform(get("/api/list-object-types?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restListObjectTypeMockMvc.perform(get("/api/list-object-types/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingListObjectType() throws Exception {
        // Get the listObjectType
        restListObjectTypeMockMvc.perform(get("/api/list-object-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateListObjectType() throws Exception {
        // Initialize the database
        listObjectTypeRepository.saveAndFlush(listObjectType);

        int databaseSizeBeforeUpdate = listObjectTypeRepository.findAll().size();

        // Update the listObjectType
        ListObjectType updatedListObjectType = listObjectTypeRepository.findById(listObjectType.getId()).get();
        // Disconnect from session so that the updates on updatedListObjectType are not directly saved in db
        em.detach(updatedListObjectType);
        updatedListObjectType
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .tableName(UPDATED_TABLE_NAME)
            .tableNameHist(UPDATED_TABLE_NAME_HIST)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        ListObjectTypeDTO listObjectTypeDTO = listObjectTypeMapper.toDto(updatedListObjectType);

        restListObjectTypeMockMvc.perform(put("/api/list-object-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listObjectTypeDTO)))
            .andExpect(status().isOk());

        // Validate the ListObjectType in the database
        List<ListObjectType> listObjectTypeList = listObjectTypeRepository.findAll();
        assertThat(listObjectTypeList).hasSize(databaseSizeBeforeUpdate);
        ListObjectType testListObjectType = listObjectTypeList.get(listObjectTypeList.size() - 1);
        assertThat(testListObjectType.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testListObjectType.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testListObjectType.getFullName()).isEqualTo(UPDATED_FULL_NAME);
        assertThat(testListObjectType.getTableName()).isEqualTo(UPDATED_TABLE_NAME);
        assertThat(testListObjectType.getTableNameHist()).isEqualTo(UPDATED_TABLE_NAME_HIST);
        assertThat(testListObjectType.getIsCurrentFlag()).isEqualTo(UPDATED_IS_CURRENT_FLAG);
        assertThat(testListObjectType.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testListObjectType.getDateCreate()).isEqualTo(UPDATED_DATE_CREATE);
        assertThat(testListObjectType.getDateEdit()).isEqualTo(UPDATED_DATE_EDIT);
        assertThat(testListObjectType.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testListObjectType.getEditor()).isEqualTo(UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void updateNonExistingListObjectType() throws Exception {
        int databaseSizeBeforeUpdate = listObjectTypeRepository.findAll().size();

        // Create the ListObjectType
        ListObjectTypeDTO listObjectTypeDTO = listObjectTypeMapper.toDto(listObjectType);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restListObjectTypeMockMvc.perform(put("/api/list-object-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listObjectTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListObjectType in the database
        List<ListObjectType> listObjectTypeList = listObjectTypeRepository.findAll();
        assertThat(listObjectTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteListObjectType() throws Exception {
        // Initialize the database
        listObjectTypeRepository.saveAndFlush(listObjectType);

        int databaseSizeBeforeDelete = listObjectTypeRepository.findAll().size();

        // Delete the listObjectType
        restListObjectTypeMockMvc.perform(delete("/api/list-object-types/{id}", listObjectType.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<ListObjectType> listObjectTypeList = listObjectTypeRepository.findAll();
        assertThat(listObjectTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListObjectType.class);
        ListObjectType listObjectType1 = new ListObjectType();
        listObjectType1.setId(1L);
        ListObjectType listObjectType2 = new ListObjectType();
        listObjectType2.setId(listObjectType1.getId());
        assertThat(listObjectType1).isEqualTo(listObjectType2);
        listObjectType2.setId(2L);
        assertThat(listObjectType1).isNotEqualTo(listObjectType2);
        listObjectType1.setId(null);
        assertThat(listObjectType1).isNotEqualTo(listObjectType2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListObjectTypeDTO.class);
        ListObjectTypeDTO listObjectTypeDTO1 = new ListObjectTypeDTO();
        listObjectTypeDTO1.setId(1L);
        ListObjectTypeDTO listObjectTypeDTO2 = new ListObjectTypeDTO();
        assertThat(listObjectTypeDTO1).isNotEqualTo(listObjectTypeDTO2);
        listObjectTypeDTO2.setId(listObjectTypeDTO1.getId());
        assertThat(listObjectTypeDTO1).isEqualTo(listObjectTypeDTO2);
        listObjectTypeDTO2.setId(2L);
        assertThat(listObjectTypeDTO1).isNotEqualTo(listObjectTypeDTO2);
        listObjectTypeDTO1.setId(null);
        assertThat(listObjectTypeDTO1).isNotEqualTo(listObjectTypeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(listObjectTypeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(listObjectTypeMapper.fromId(null)).isNull();
    }
}
