package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.ListValveType;
import io.github.jhipster.application.domain.ValveHist;
import io.github.jhipster.application.repository.ListValveTypeRepository;
import io.github.jhipster.application.service.ListValveTypeService;
import io.github.jhipster.application.service.dto.ListValveTypeDTO;
import io.github.jhipster.application.service.mapper.ListValveTypeMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.ListValveTypeCriteria;
import io.github.jhipster.application.service.ListValveTypeQueryService;

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
 * Integration tests for the {@Link ListValveTypeResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class ListValveTypeResourceIT {

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
    private ListValveTypeRepository listValveTypeRepository;

    @Autowired
    private ListValveTypeMapper listValveTypeMapper;

    @Autowired
    private ListValveTypeService listValveTypeService;

    @Autowired
    private ListValveTypeQueryService listValveTypeQueryService;

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

    private MockMvc restListValveTypeMockMvc;

    private ListValveType listValveType;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ListValveTypeResource listValveTypeResource = new ListValveTypeResource(listValveTypeService, listValveTypeQueryService);
        this.restListValveTypeMockMvc = MockMvcBuilders.standaloneSetup(listValveTypeResource)
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
    public static ListValveType createEntity(EntityManager em) {
        ListValveType listValveType = new ListValveType()
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME)
            .fullName(DEFAULT_FULL_NAME)
            .isCurrentFlag(DEFAULT_IS_CURRENT_FLAG)
            .description(DEFAULT_DESCRIPTION)
            .dateCreate(DEFAULT_DATE_CREATE)
            .dateEdit(DEFAULT_DATE_EDIT)
            .creator(DEFAULT_CREATOR)
            .editor(DEFAULT_EDITOR);
        return listValveType;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ListValveType createUpdatedEntity(EntityManager em) {
        ListValveType listValveType = new ListValveType()
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        return listValveType;
    }

    @BeforeEach
    public void initTest() {
        listValveType = createEntity(em);
    }

    @Test
    @Transactional
    public void createListValveType() throws Exception {
        int databaseSizeBeforeCreate = listValveTypeRepository.findAll().size();

        // Create the ListValveType
        ListValveTypeDTO listValveTypeDTO = listValveTypeMapper.toDto(listValveType);
        restListValveTypeMockMvc.perform(post("/api/list-valve-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listValveTypeDTO)))
            .andExpect(status().isCreated());

        // Validate the ListValveType in the database
        List<ListValveType> listValveTypeList = listValveTypeRepository.findAll();
        assertThat(listValveTypeList).hasSize(databaseSizeBeforeCreate + 1);
        ListValveType testListValveType = listValveTypeList.get(listValveTypeList.size() - 1);
        assertThat(testListValveType.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testListValveType.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testListValveType.getFullName()).isEqualTo(DEFAULT_FULL_NAME);
        assertThat(testListValveType.getIsCurrentFlag()).isEqualTo(DEFAULT_IS_CURRENT_FLAG);
        assertThat(testListValveType.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testListValveType.getDateCreate()).isEqualTo(DEFAULT_DATE_CREATE);
        assertThat(testListValveType.getDateEdit()).isEqualTo(DEFAULT_DATE_EDIT);
        assertThat(testListValveType.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testListValveType.getEditor()).isEqualTo(DEFAULT_EDITOR);
    }

    @Test
    @Transactional
    public void createListValveTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = listValveTypeRepository.findAll().size();

        // Create the ListValveType with an existing ID
        listValveType.setId(1L);
        ListValveTypeDTO listValveTypeDTO = listValveTypeMapper.toDto(listValveType);

        // An entity with an existing ID cannot be created, so this API call must fail
        restListValveTypeMockMvc.perform(post("/api/list-valve-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listValveTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListValveType in the database
        List<ListValveType> listValveTypeList = listValveTypeRepository.findAll();
        assertThat(listValveTypeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = listValveTypeRepository.findAll().size();
        // set the field null
        listValveType.setCode(null);

        // Create the ListValveType, which fails.
        ListValveTypeDTO listValveTypeDTO = listValveTypeMapper.toDto(listValveType);

        restListValveTypeMockMvc.perform(post("/api/list-valve-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listValveTypeDTO)))
            .andExpect(status().isBadRequest());

        List<ListValveType> listValveTypeList = listValveTypeRepository.findAll();
        assertThat(listValveTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listValveTypeRepository.findAll().size();
        // set the field null
        listValveType.setName(null);

        // Create the ListValveType, which fails.
        ListValveTypeDTO listValveTypeDTO = listValveTypeMapper.toDto(listValveType);

        restListValveTypeMockMvc.perform(post("/api/list-valve-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listValveTypeDTO)))
            .andExpect(status().isBadRequest());

        List<ListValveType> listValveTypeList = listValveTypeRepository.findAll();
        assertThat(listValveTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFullNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listValveTypeRepository.findAll().size();
        // set the field null
        listValveType.setFullName(null);

        // Create the ListValveType, which fails.
        ListValveTypeDTO listValveTypeDTO = listValveTypeMapper.toDto(listValveType);

        restListValveTypeMockMvc.perform(post("/api/list-valve-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listValveTypeDTO)))
            .andExpect(status().isBadRequest());

        List<ListValveType> listValveTypeList = listValveTypeRepository.findAll();
        assertThat(listValveTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsCurrentFlagIsRequired() throws Exception {
        int databaseSizeBeforeTest = listValveTypeRepository.findAll().size();
        // set the field null
        listValveType.setIsCurrentFlag(null);

        // Create the ListValveType, which fails.
        ListValveTypeDTO listValveTypeDTO = listValveTypeMapper.toDto(listValveType);

        restListValveTypeMockMvc.perform(post("/api/list-valve-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listValveTypeDTO)))
            .andExpect(status().isBadRequest());

        List<ListValveType> listValveTypeList = listValveTypeRepository.findAll();
        assertThat(listValveTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllListValveTypes() throws Exception {
        // Initialize the database
        listValveTypeRepository.saveAndFlush(listValveType);

        // Get all the listValveTypeList
        restListValveTypeMockMvc.perform(get("/api/list-valve-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listValveType.getId().intValue())))
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
    public void getListValveType() throws Exception {
        // Initialize the database
        listValveTypeRepository.saveAndFlush(listValveType);

        // Get the listValveType
        restListValveTypeMockMvc.perform(get("/api/list-valve-types/{id}", listValveType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(listValveType.getId().intValue()))
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
    public void getAllListValveTypesByCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        listValveTypeRepository.saveAndFlush(listValveType);

        // Get all the listValveTypeList where code equals to DEFAULT_CODE
        defaultListValveTypeShouldBeFound("code.equals=" + DEFAULT_CODE);

        // Get all the listValveTypeList where code equals to UPDATED_CODE
        defaultListValveTypeShouldNotBeFound("code.equals=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListValveTypesByCodeIsInShouldWork() throws Exception {
        // Initialize the database
        listValveTypeRepository.saveAndFlush(listValveType);

        // Get all the listValveTypeList where code in DEFAULT_CODE or UPDATED_CODE
        defaultListValveTypeShouldBeFound("code.in=" + DEFAULT_CODE + "," + UPDATED_CODE);

        // Get all the listValveTypeList where code equals to UPDATED_CODE
        defaultListValveTypeShouldNotBeFound("code.in=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListValveTypesByCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        listValveTypeRepository.saveAndFlush(listValveType);

        // Get all the listValveTypeList where code is not null
        defaultListValveTypeShouldBeFound("code.specified=true");

        // Get all the listValveTypeList where code is null
        defaultListValveTypeShouldNotBeFound("code.specified=false");
    }

    @Test
    @Transactional
    public void getAllListValveTypesByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listValveTypeRepository.saveAndFlush(listValveType);

        // Get all the listValveTypeList where name equals to DEFAULT_NAME
        defaultListValveTypeShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the listValveTypeList where name equals to UPDATED_NAME
        defaultListValveTypeShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListValveTypesByNameIsInShouldWork() throws Exception {
        // Initialize the database
        listValveTypeRepository.saveAndFlush(listValveType);

        // Get all the listValveTypeList where name in DEFAULT_NAME or UPDATED_NAME
        defaultListValveTypeShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the listValveTypeList where name equals to UPDATED_NAME
        defaultListValveTypeShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListValveTypesByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listValveTypeRepository.saveAndFlush(listValveType);

        // Get all the listValveTypeList where name is not null
        defaultListValveTypeShouldBeFound("name.specified=true");

        // Get all the listValveTypeList where name is null
        defaultListValveTypeShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    public void getAllListValveTypesByFullNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listValveTypeRepository.saveAndFlush(listValveType);

        // Get all the listValveTypeList where fullName equals to DEFAULT_FULL_NAME
        defaultListValveTypeShouldBeFound("fullName.equals=" + DEFAULT_FULL_NAME);

        // Get all the listValveTypeList where fullName equals to UPDATED_FULL_NAME
        defaultListValveTypeShouldNotBeFound("fullName.equals=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListValveTypesByFullNameIsInShouldWork() throws Exception {
        // Initialize the database
        listValveTypeRepository.saveAndFlush(listValveType);

        // Get all the listValveTypeList where fullName in DEFAULT_FULL_NAME or UPDATED_FULL_NAME
        defaultListValveTypeShouldBeFound("fullName.in=" + DEFAULT_FULL_NAME + "," + UPDATED_FULL_NAME);

        // Get all the listValveTypeList where fullName equals to UPDATED_FULL_NAME
        defaultListValveTypeShouldNotBeFound("fullName.in=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListValveTypesByFullNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listValveTypeRepository.saveAndFlush(listValveType);

        // Get all the listValveTypeList where fullName is not null
        defaultListValveTypeShouldBeFound("fullName.specified=true");

        // Get all the listValveTypeList where fullName is null
        defaultListValveTypeShouldNotBeFound("fullName.specified=false");
    }

    @Test
    @Transactional
    public void getAllListValveTypesByIsCurrentFlagIsEqualToSomething() throws Exception {
        // Initialize the database
        listValveTypeRepository.saveAndFlush(listValveType);

        // Get all the listValveTypeList where isCurrentFlag equals to DEFAULT_IS_CURRENT_FLAG
        defaultListValveTypeShouldBeFound("isCurrentFlag.equals=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listValveTypeList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListValveTypeShouldNotBeFound("isCurrentFlag.equals=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListValveTypesByIsCurrentFlagIsInShouldWork() throws Exception {
        // Initialize the database
        listValveTypeRepository.saveAndFlush(listValveType);

        // Get all the listValveTypeList where isCurrentFlag in DEFAULT_IS_CURRENT_FLAG or UPDATED_IS_CURRENT_FLAG
        defaultListValveTypeShouldBeFound("isCurrentFlag.in=" + DEFAULT_IS_CURRENT_FLAG + "," + UPDATED_IS_CURRENT_FLAG);

        // Get all the listValveTypeList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListValveTypeShouldNotBeFound("isCurrentFlag.in=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListValveTypesByIsCurrentFlagIsNullOrNotNull() throws Exception {
        // Initialize the database
        listValveTypeRepository.saveAndFlush(listValveType);

        // Get all the listValveTypeList where isCurrentFlag is not null
        defaultListValveTypeShouldBeFound("isCurrentFlag.specified=true");

        // Get all the listValveTypeList where isCurrentFlag is null
        defaultListValveTypeShouldNotBeFound("isCurrentFlag.specified=false");
    }

    @Test
    @Transactional
    public void getAllListValveTypesByIsCurrentFlagIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        listValveTypeRepository.saveAndFlush(listValveType);

        // Get all the listValveTypeList where isCurrentFlag greater than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListValveTypeShouldBeFound("isCurrentFlag.greaterOrEqualThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listValveTypeList where isCurrentFlag greater than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListValveTypeShouldNotBeFound("isCurrentFlag.greaterOrEqualThan=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListValveTypesByIsCurrentFlagIsLessThanSomething() throws Exception {
        // Initialize the database
        listValveTypeRepository.saveAndFlush(listValveType);

        // Get all the listValveTypeList where isCurrentFlag less than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListValveTypeShouldNotBeFound("isCurrentFlag.lessThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listValveTypeList where isCurrentFlag less than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListValveTypeShouldBeFound("isCurrentFlag.lessThan=" + UPDATED_IS_CURRENT_FLAG);
    }


    @Test
    @Transactional
    public void getAllListValveTypesByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        listValveTypeRepository.saveAndFlush(listValveType);

        // Get all the listValveTypeList where description equals to DEFAULT_DESCRIPTION
        defaultListValveTypeShouldBeFound("description.equals=" + DEFAULT_DESCRIPTION);

        // Get all the listValveTypeList where description equals to UPDATED_DESCRIPTION
        defaultListValveTypeShouldNotBeFound("description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListValveTypesByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        listValveTypeRepository.saveAndFlush(listValveType);

        // Get all the listValveTypeList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
        defaultListValveTypeShouldBeFound("description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION);

        // Get all the listValveTypeList where description equals to UPDATED_DESCRIPTION
        defaultListValveTypeShouldNotBeFound("description.in=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListValveTypesByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        listValveTypeRepository.saveAndFlush(listValveType);

        // Get all the listValveTypeList where description is not null
        defaultListValveTypeShouldBeFound("description.specified=true");

        // Get all the listValveTypeList where description is null
        defaultListValveTypeShouldNotBeFound("description.specified=false");
    }

    @Test
    @Transactional
    public void getAllListValveTypesByDateCreateIsEqualToSomething() throws Exception {
        // Initialize the database
        listValveTypeRepository.saveAndFlush(listValveType);

        // Get all the listValveTypeList where dateCreate equals to DEFAULT_DATE_CREATE
        defaultListValveTypeShouldBeFound("dateCreate.equals=" + DEFAULT_DATE_CREATE);

        // Get all the listValveTypeList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListValveTypeShouldNotBeFound("dateCreate.equals=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListValveTypesByDateCreateIsInShouldWork() throws Exception {
        // Initialize the database
        listValveTypeRepository.saveAndFlush(listValveType);

        // Get all the listValveTypeList where dateCreate in DEFAULT_DATE_CREATE or UPDATED_DATE_CREATE
        defaultListValveTypeShouldBeFound("dateCreate.in=" + DEFAULT_DATE_CREATE + "," + UPDATED_DATE_CREATE);

        // Get all the listValveTypeList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListValveTypeShouldNotBeFound("dateCreate.in=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListValveTypesByDateCreateIsNullOrNotNull() throws Exception {
        // Initialize the database
        listValveTypeRepository.saveAndFlush(listValveType);

        // Get all the listValveTypeList where dateCreate is not null
        defaultListValveTypeShouldBeFound("dateCreate.specified=true");

        // Get all the listValveTypeList where dateCreate is null
        defaultListValveTypeShouldNotBeFound("dateCreate.specified=false");
    }

    @Test
    @Transactional
    public void getAllListValveTypesByDateEditIsEqualToSomething() throws Exception {
        // Initialize the database
        listValveTypeRepository.saveAndFlush(listValveType);

        // Get all the listValveTypeList where dateEdit equals to DEFAULT_DATE_EDIT
        defaultListValveTypeShouldBeFound("dateEdit.equals=" + DEFAULT_DATE_EDIT);

        // Get all the listValveTypeList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListValveTypeShouldNotBeFound("dateEdit.equals=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListValveTypesByDateEditIsInShouldWork() throws Exception {
        // Initialize the database
        listValveTypeRepository.saveAndFlush(listValveType);

        // Get all the listValveTypeList where dateEdit in DEFAULT_DATE_EDIT or UPDATED_DATE_EDIT
        defaultListValveTypeShouldBeFound("dateEdit.in=" + DEFAULT_DATE_EDIT + "," + UPDATED_DATE_EDIT);

        // Get all the listValveTypeList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListValveTypeShouldNotBeFound("dateEdit.in=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListValveTypesByDateEditIsNullOrNotNull() throws Exception {
        // Initialize the database
        listValveTypeRepository.saveAndFlush(listValveType);

        // Get all the listValveTypeList where dateEdit is not null
        defaultListValveTypeShouldBeFound("dateEdit.specified=true");

        // Get all the listValveTypeList where dateEdit is null
        defaultListValveTypeShouldNotBeFound("dateEdit.specified=false");
    }

    @Test
    @Transactional
    public void getAllListValveTypesByCreatorIsEqualToSomething() throws Exception {
        // Initialize the database
        listValveTypeRepository.saveAndFlush(listValveType);

        // Get all the listValveTypeList where creator equals to DEFAULT_CREATOR
        defaultListValveTypeShouldBeFound("creator.equals=" + DEFAULT_CREATOR);

        // Get all the listValveTypeList where creator equals to UPDATED_CREATOR
        defaultListValveTypeShouldNotBeFound("creator.equals=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListValveTypesByCreatorIsInShouldWork() throws Exception {
        // Initialize the database
        listValveTypeRepository.saveAndFlush(listValveType);

        // Get all the listValveTypeList where creator in DEFAULT_CREATOR or UPDATED_CREATOR
        defaultListValveTypeShouldBeFound("creator.in=" + DEFAULT_CREATOR + "," + UPDATED_CREATOR);

        // Get all the listValveTypeList where creator equals to UPDATED_CREATOR
        defaultListValveTypeShouldNotBeFound("creator.in=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListValveTypesByCreatorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listValveTypeRepository.saveAndFlush(listValveType);

        // Get all the listValveTypeList where creator is not null
        defaultListValveTypeShouldBeFound("creator.specified=true");

        // Get all the listValveTypeList where creator is null
        defaultListValveTypeShouldNotBeFound("creator.specified=false");
    }

    @Test
    @Transactional
    public void getAllListValveTypesByEditorIsEqualToSomething() throws Exception {
        // Initialize the database
        listValveTypeRepository.saveAndFlush(listValveType);

        // Get all the listValveTypeList where editor equals to DEFAULT_EDITOR
        defaultListValveTypeShouldBeFound("editor.equals=" + DEFAULT_EDITOR);

        // Get all the listValveTypeList where editor equals to UPDATED_EDITOR
        defaultListValveTypeShouldNotBeFound("editor.equals=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListValveTypesByEditorIsInShouldWork() throws Exception {
        // Initialize the database
        listValveTypeRepository.saveAndFlush(listValveType);

        // Get all the listValveTypeList where editor in DEFAULT_EDITOR or UPDATED_EDITOR
        defaultListValveTypeShouldBeFound("editor.in=" + DEFAULT_EDITOR + "," + UPDATED_EDITOR);

        // Get all the listValveTypeList where editor equals to UPDATED_EDITOR
        defaultListValveTypeShouldNotBeFound("editor.in=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListValveTypesByEditorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listValveTypeRepository.saveAndFlush(listValveType);

        // Get all the listValveTypeList where editor is not null
        defaultListValveTypeShouldBeFound("editor.specified=true");

        // Get all the listValveTypeList where editor is null
        defaultListValveTypeShouldNotBeFound("editor.specified=false");
    }

    @Test
    @Transactional
    public void getAllListValveTypesByValveHistIsEqualToSomething() throws Exception {
        // Initialize the database
        ValveHist valveHist = ValveHistResourceIT.createEntity(em);
        em.persist(valveHist);
        em.flush();
        listValveType.addValveHist(valveHist);
        listValveTypeRepository.saveAndFlush(listValveType);
        Long valveHistId = valveHist.getId();

        // Get all the listValveTypeList where valveHist equals to valveHistId
        defaultListValveTypeShouldBeFound("valveHistId.equals=" + valveHistId);

        // Get all the listValveTypeList where valveHist equals to valveHistId + 1
        defaultListValveTypeShouldNotBeFound("valveHistId.equals=" + (valveHistId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultListValveTypeShouldBeFound(String filter) throws Exception {
        restListValveTypeMockMvc.perform(get("/api/list-valve-types?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listValveType.getId().intValue())))
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
        restListValveTypeMockMvc.perform(get("/api/list-valve-types/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultListValveTypeShouldNotBeFound(String filter) throws Exception {
        restListValveTypeMockMvc.perform(get("/api/list-valve-types?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restListValveTypeMockMvc.perform(get("/api/list-valve-types/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingListValveType() throws Exception {
        // Get the listValveType
        restListValveTypeMockMvc.perform(get("/api/list-valve-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateListValveType() throws Exception {
        // Initialize the database
        listValveTypeRepository.saveAndFlush(listValveType);

        int databaseSizeBeforeUpdate = listValveTypeRepository.findAll().size();

        // Update the listValveType
        ListValveType updatedListValveType = listValveTypeRepository.findById(listValveType.getId()).get();
        // Disconnect from session so that the updates on updatedListValveType are not directly saved in db
        em.detach(updatedListValveType);
        updatedListValveType
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        ListValveTypeDTO listValveTypeDTO = listValveTypeMapper.toDto(updatedListValveType);

        restListValveTypeMockMvc.perform(put("/api/list-valve-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listValveTypeDTO)))
            .andExpect(status().isOk());

        // Validate the ListValveType in the database
        List<ListValveType> listValveTypeList = listValveTypeRepository.findAll();
        assertThat(listValveTypeList).hasSize(databaseSizeBeforeUpdate);
        ListValveType testListValveType = listValveTypeList.get(listValveTypeList.size() - 1);
        assertThat(testListValveType.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testListValveType.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testListValveType.getFullName()).isEqualTo(UPDATED_FULL_NAME);
        assertThat(testListValveType.getIsCurrentFlag()).isEqualTo(UPDATED_IS_CURRENT_FLAG);
        assertThat(testListValveType.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testListValveType.getDateCreate()).isEqualTo(UPDATED_DATE_CREATE);
        assertThat(testListValveType.getDateEdit()).isEqualTo(UPDATED_DATE_EDIT);
        assertThat(testListValveType.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testListValveType.getEditor()).isEqualTo(UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void updateNonExistingListValveType() throws Exception {
        int databaseSizeBeforeUpdate = listValveTypeRepository.findAll().size();

        // Create the ListValveType
        ListValveTypeDTO listValveTypeDTO = listValveTypeMapper.toDto(listValveType);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restListValveTypeMockMvc.perform(put("/api/list-valve-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listValveTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListValveType in the database
        List<ListValveType> listValveTypeList = listValveTypeRepository.findAll();
        assertThat(listValveTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteListValveType() throws Exception {
        // Initialize the database
        listValveTypeRepository.saveAndFlush(listValveType);

        int databaseSizeBeforeDelete = listValveTypeRepository.findAll().size();

        // Delete the listValveType
        restListValveTypeMockMvc.perform(delete("/api/list-valve-types/{id}", listValveType.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<ListValveType> listValveTypeList = listValveTypeRepository.findAll();
        assertThat(listValveTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListValveType.class);
        ListValveType listValveType1 = new ListValveType();
        listValveType1.setId(1L);
        ListValveType listValveType2 = new ListValveType();
        listValveType2.setId(listValveType1.getId());
        assertThat(listValveType1).isEqualTo(listValveType2);
        listValveType2.setId(2L);
        assertThat(listValveType1).isNotEqualTo(listValveType2);
        listValveType1.setId(null);
        assertThat(listValveType1).isNotEqualTo(listValveType2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListValveTypeDTO.class);
        ListValveTypeDTO listValveTypeDTO1 = new ListValveTypeDTO();
        listValveTypeDTO1.setId(1L);
        ListValveTypeDTO listValveTypeDTO2 = new ListValveTypeDTO();
        assertThat(listValveTypeDTO1).isNotEqualTo(listValveTypeDTO2);
        listValveTypeDTO2.setId(listValveTypeDTO1.getId());
        assertThat(listValveTypeDTO1).isEqualTo(listValveTypeDTO2);
        listValveTypeDTO2.setId(2L);
        assertThat(listValveTypeDTO1).isNotEqualTo(listValveTypeDTO2);
        listValveTypeDTO1.setId(null);
        assertThat(listValveTypeDTO1).isNotEqualTo(listValveTypeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(listValveTypeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(listValveTypeMapper.fromId(null)).isNull();
    }
}
