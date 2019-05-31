package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.ListClcType;
import io.github.jhipster.application.repository.ListClcTypeRepository;
import io.github.jhipster.application.service.ListClcTypeService;
import io.github.jhipster.application.service.dto.ListClcTypeDTO;
import io.github.jhipster.application.service.mapper.ListClcTypeMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.ListClcTypeCriteria;
import io.github.jhipster.application.service.ListClcTypeQueryService;

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
 * Integration tests for the {@Link ListClcTypeResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class ListClcTypeResourceIT {

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
    private ListClcTypeRepository listClcTypeRepository;

    @Autowired
    private ListClcTypeMapper listClcTypeMapper;

    @Autowired
    private ListClcTypeService listClcTypeService;

    @Autowired
    private ListClcTypeQueryService listClcTypeQueryService;

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

    private MockMvc restListClcTypeMockMvc;

    private ListClcType listClcType;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ListClcTypeResource listClcTypeResource = new ListClcTypeResource(listClcTypeService, listClcTypeQueryService);
        this.restListClcTypeMockMvc = MockMvcBuilders.standaloneSetup(listClcTypeResource)
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
    public static ListClcType createEntity(EntityManager em) {
        ListClcType listClcType = new ListClcType()
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME)
            .fullName(DEFAULT_FULL_NAME)
            .isCurrentFlag(DEFAULT_IS_CURRENT_FLAG)
            .description(DEFAULT_DESCRIPTION)
            .dateCreate(DEFAULT_DATE_CREATE)
            .dateEdit(DEFAULT_DATE_EDIT)
            .creator(DEFAULT_CREATOR)
            .editor(DEFAULT_EDITOR);
        return listClcType;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ListClcType createUpdatedEntity(EntityManager em) {
        ListClcType listClcType = new ListClcType()
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        return listClcType;
    }

    @BeforeEach
    public void initTest() {
        listClcType = createEntity(em);
    }

    @Test
    @Transactional
    public void createListClcType() throws Exception {
        int databaseSizeBeforeCreate = listClcTypeRepository.findAll().size();

        // Create the ListClcType
        ListClcTypeDTO listClcTypeDTO = listClcTypeMapper.toDto(listClcType);
        restListClcTypeMockMvc.perform(post("/api/list-clc-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listClcTypeDTO)))
            .andExpect(status().isCreated());

        // Validate the ListClcType in the database
        List<ListClcType> listClcTypeList = listClcTypeRepository.findAll();
        assertThat(listClcTypeList).hasSize(databaseSizeBeforeCreate + 1);
        ListClcType testListClcType = listClcTypeList.get(listClcTypeList.size() - 1);
        assertThat(testListClcType.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testListClcType.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testListClcType.getFullName()).isEqualTo(DEFAULT_FULL_NAME);
        assertThat(testListClcType.getIsCurrentFlag()).isEqualTo(DEFAULT_IS_CURRENT_FLAG);
        assertThat(testListClcType.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testListClcType.getDateCreate()).isEqualTo(DEFAULT_DATE_CREATE);
        assertThat(testListClcType.getDateEdit()).isEqualTo(DEFAULT_DATE_EDIT);
        assertThat(testListClcType.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testListClcType.getEditor()).isEqualTo(DEFAULT_EDITOR);
    }

    @Test
    @Transactional
    public void createListClcTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = listClcTypeRepository.findAll().size();

        // Create the ListClcType with an existing ID
        listClcType.setId(1L);
        ListClcTypeDTO listClcTypeDTO = listClcTypeMapper.toDto(listClcType);

        // An entity with an existing ID cannot be created, so this API call must fail
        restListClcTypeMockMvc.perform(post("/api/list-clc-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listClcTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListClcType in the database
        List<ListClcType> listClcTypeList = listClcTypeRepository.findAll();
        assertThat(listClcTypeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = listClcTypeRepository.findAll().size();
        // set the field null
        listClcType.setCode(null);

        // Create the ListClcType, which fails.
        ListClcTypeDTO listClcTypeDTO = listClcTypeMapper.toDto(listClcType);

        restListClcTypeMockMvc.perform(post("/api/list-clc-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listClcTypeDTO)))
            .andExpect(status().isBadRequest());

        List<ListClcType> listClcTypeList = listClcTypeRepository.findAll();
        assertThat(listClcTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listClcTypeRepository.findAll().size();
        // set the field null
        listClcType.setName(null);

        // Create the ListClcType, which fails.
        ListClcTypeDTO listClcTypeDTO = listClcTypeMapper.toDto(listClcType);

        restListClcTypeMockMvc.perform(post("/api/list-clc-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listClcTypeDTO)))
            .andExpect(status().isBadRequest());

        List<ListClcType> listClcTypeList = listClcTypeRepository.findAll();
        assertThat(listClcTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFullNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listClcTypeRepository.findAll().size();
        // set the field null
        listClcType.setFullName(null);

        // Create the ListClcType, which fails.
        ListClcTypeDTO listClcTypeDTO = listClcTypeMapper.toDto(listClcType);

        restListClcTypeMockMvc.perform(post("/api/list-clc-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listClcTypeDTO)))
            .andExpect(status().isBadRequest());

        List<ListClcType> listClcTypeList = listClcTypeRepository.findAll();
        assertThat(listClcTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsCurrentFlagIsRequired() throws Exception {
        int databaseSizeBeforeTest = listClcTypeRepository.findAll().size();
        // set the field null
        listClcType.setIsCurrentFlag(null);

        // Create the ListClcType, which fails.
        ListClcTypeDTO listClcTypeDTO = listClcTypeMapper.toDto(listClcType);

        restListClcTypeMockMvc.perform(post("/api/list-clc-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listClcTypeDTO)))
            .andExpect(status().isBadRequest());

        List<ListClcType> listClcTypeList = listClcTypeRepository.findAll();
        assertThat(listClcTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllListClcTypes() throws Exception {
        // Initialize the database
        listClcTypeRepository.saveAndFlush(listClcType);

        // Get all the listClcTypeList
        restListClcTypeMockMvc.perform(get("/api/list-clc-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listClcType.getId().intValue())))
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
    public void getListClcType() throws Exception {
        // Initialize the database
        listClcTypeRepository.saveAndFlush(listClcType);

        // Get the listClcType
        restListClcTypeMockMvc.perform(get("/api/list-clc-types/{id}", listClcType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(listClcType.getId().intValue()))
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
    public void getAllListClcTypesByCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        listClcTypeRepository.saveAndFlush(listClcType);

        // Get all the listClcTypeList where code equals to DEFAULT_CODE
        defaultListClcTypeShouldBeFound("code.equals=" + DEFAULT_CODE);

        // Get all the listClcTypeList where code equals to UPDATED_CODE
        defaultListClcTypeShouldNotBeFound("code.equals=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListClcTypesByCodeIsInShouldWork() throws Exception {
        // Initialize the database
        listClcTypeRepository.saveAndFlush(listClcType);

        // Get all the listClcTypeList where code in DEFAULT_CODE or UPDATED_CODE
        defaultListClcTypeShouldBeFound("code.in=" + DEFAULT_CODE + "," + UPDATED_CODE);

        // Get all the listClcTypeList where code equals to UPDATED_CODE
        defaultListClcTypeShouldNotBeFound("code.in=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListClcTypesByCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        listClcTypeRepository.saveAndFlush(listClcType);

        // Get all the listClcTypeList where code is not null
        defaultListClcTypeShouldBeFound("code.specified=true");

        // Get all the listClcTypeList where code is null
        defaultListClcTypeShouldNotBeFound("code.specified=false");
    }

    @Test
    @Transactional
    public void getAllListClcTypesByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listClcTypeRepository.saveAndFlush(listClcType);

        // Get all the listClcTypeList where name equals to DEFAULT_NAME
        defaultListClcTypeShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the listClcTypeList where name equals to UPDATED_NAME
        defaultListClcTypeShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListClcTypesByNameIsInShouldWork() throws Exception {
        // Initialize the database
        listClcTypeRepository.saveAndFlush(listClcType);

        // Get all the listClcTypeList where name in DEFAULT_NAME or UPDATED_NAME
        defaultListClcTypeShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the listClcTypeList where name equals to UPDATED_NAME
        defaultListClcTypeShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListClcTypesByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listClcTypeRepository.saveAndFlush(listClcType);

        // Get all the listClcTypeList where name is not null
        defaultListClcTypeShouldBeFound("name.specified=true");

        // Get all the listClcTypeList where name is null
        defaultListClcTypeShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    public void getAllListClcTypesByFullNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listClcTypeRepository.saveAndFlush(listClcType);

        // Get all the listClcTypeList where fullName equals to DEFAULT_FULL_NAME
        defaultListClcTypeShouldBeFound("fullName.equals=" + DEFAULT_FULL_NAME);

        // Get all the listClcTypeList where fullName equals to UPDATED_FULL_NAME
        defaultListClcTypeShouldNotBeFound("fullName.equals=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListClcTypesByFullNameIsInShouldWork() throws Exception {
        // Initialize the database
        listClcTypeRepository.saveAndFlush(listClcType);

        // Get all the listClcTypeList where fullName in DEFAULT_FULL_NAME or UPDATED_FULL_NAME
        defaultListClcTypeShouldBeFound("fullName.in=" + DEFAULT_FULL_NAME + "," + UPDATED_FULL_NAME);

        // Get all the listClcTypeList where fullName equals to UPDATED_FULL_NAME
        defaultListClcTypeShouldNotBeFound("fullName.in=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListClcTypesByFullNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listClcTypeRepository.saveAndFlush(listClcType);

        // Get all the listClcTypeList where fullName is not null
        defaultListClcTypeShouldBeFound("fullName.specified=true");

        // Get all the listClcTypeList where fullName is null
        defaultListClcTypeShouldNotBeFound("fullName.specified=false");
    }

    @Test
    @Transactional
    public void getAllListClcTypesByIsCurrentFlagIsEqualToSomething() throws Exception {
        // Initialize the database
        listClcTypeRepository.saveAndFlush(listClcType);

        // Get all the listClcTypeList where isCurrentFlag equals to DEFAULT_IS_CURRENT_FLAG
        defaultListClcTypeShouldBeFound("isCurrentFlag.equals=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listClcTypeList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListClcTypeShouldNotBeFound("isCurrentFlag.equals=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListClcTypesByIsCurrentFlagIsInShouldWork() throws Exception {
        // Initialize the database
        listClcTypeRepository.saveAndFlush(listClcType);

        // Get all the listClcTypeList where isCurrentFlag in DEFAULT_IS_CURRENT_FLAG or UPDATED_IS_CURRENT_FLAG
        defaultListClcTypeShouldBeFound("isCurrentFlag.in=" + DEFAULT_IS_CURRENT_FLAG + "," + UPDATED_IS_CURRENT_FLAG);

        // Get all the listClcTypeList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListClcTypeShouldNotBeFound("isCurrentFlag.in=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListClcTypesByIsCurrentFlagIsNullOrNotNull() throws Exception {
        // Initialize the database
        listClcTypeRepository.saveAndFlush(listClcType);

        // Get all the listClcTypeList where isCurrentFlag is not null
        defaultListClcTypeShouldBeFound("isCurrentFlag.specified=true");

        // Get all the listClcTypeList where isCurrentFlag is null
        defaultListClcTypeShouldNotBeFound("isCurrentFlag.specified=false");
    }

    @Test
    @Transactional
    public void getAllListClcTypesByIsCurrentFlagIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        listClcTypeRepository.saveAndFlush(listClcType);

        // Get all the listClcTypeList where isCurrentFlag greater than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListClcTypeShouldBeFound("isCurrentFlag.greaterOrEqualThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listClcTypeList where isCurrentFlag greater than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListClcTypeShouldNotBeFound("isCurrentFlag.greaterOrEqualThan=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListClcTypesByIsCurrentFlagIsLessThanSomething() throws Exception {
        // Initialize the database
        listClcTypeRepository.saveAndFlush(listClcType);

        // Get all the listClcTypeList where isCurrentFlag less than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListClcTypeShouldNotBeFound("isCurrentFlag.lessThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listClcTypeList where isCurrentFlag less than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListClcTypeShouldBeFound("isCurrentFlag.lessThan=" + UPDATED_IS_CURRENT_FLAG);
    }


    @Test
    @Transactional
    public void getAllListClcTypesByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        listClcTypeRepository.saveAndFlush(listClcType);

        // Get all the listClcTypeList where description equals to DEFAULT_DESCRIPTION
        defaultListClcTypeShouldBeFound("description.equals=" + DEFAULT_DESCRIPTION);

        // Get all the listClcTypeList where description equals to UPDATED_DESCRIPTION
        defaultListClcTypeShouldNotBeFound("description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListClcTypesByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        listClcTypeRepository.saveAndFlush(listClcType);

        // Get all the listClcTypeList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
        defaultListClcTypeShouldBeFound("description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION);

        // Get all the listClcTypeList where description equals to UPDATED_DESCRIPTION
        defaultListClcTypeShouldNotBeFound("description.in=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListClcTypesByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        listClcTypeRepository.saveAndFlush(listClcType);

        // Get all the listClcTypeList where description is not null
        defaultListClcTypeShouldBeFound("description.specified=true");

        // Get all the listClcTypeList where description is null
        defaultListClcTypeShouldNotBeFound("description.specified=false");
    }

    @Test
    @Transactional
    public void getAllListClcTypesByDateCreateIsEqualToSomething() throws Exception {
        // Initialize the database
        listClcTypeRepository.saveAndFlush(listClcType);

        // Get all the listClcTypeList where dateCreate equals to DEFAULT_DATE_CREATE
        defaultListClcTypeShouldBeFound("dateCreate.equals=" + DEFAULT_DATE_CREATE);

        // Get all the listClcTypeList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListClcTypeShouldNotBeFound("dateCreate.equals=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListClcTypesByDateCreateIsInShouldWork() throws Exception {
        // Initialize the database
        listClcTypeRepository.saveAndFlush(listClcType);

        // Get all the listClcTypeList where dateCreate in DEFAULT_DATE_CREATE or UPDATED_DATE_CREATE
        defaultListClcTypeShouldBeFound("dateCreate.in=" + DEFAULT_DATE_CREATE + "," + UPDATED_DATE_CREATE);

        // Get all the listClcTypeList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListClcTypeShouldNotBeFound("dateCreate.in=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListClcTypesByDateCreateIsNullOrNotNull() throws Exception {
        // Initialize the database
        listClcTypeRepository.saveAndFlush(listClcType);

        // Get all the listClcTypeList where dateCreate is not null
        defaultListClcTypeShouldBeFound("dateCreate.specified=true");

        // Get all the listClcTypeList where dateCreate is null
        defaultListClcTypeShouldNotBeFound("dateCreate.specified=false");
    }

    @Test
    @Transactional
    public void getAllListClcTypesByDateEditIsEqualToSomething() throws Exception {
        // Initialize the database
        listClcTypeRepository.saveAndFlush(listClcType);

        // Get all the listClcTypeList where dateEdit equals to DEFAULT_DATE_EDIT
        defaultListClcTypeShouldBeFound("dateEdit.equals=" + DEFAULT_DATE_EDIT);

        // Get all the listClcTypeList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListClcTypeShouldNotBeFound("dateEdit.equals=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListClcTypesByDateEditIsInShouldWork() throws Exception {
        // Initialize the database
        listClcTypeRepository.saveAndFlush(listClcType);

        // Get all the listClcTypeList where dateEdit in DEFAULT_DATE_EDIT or UPDATED_DATE_EDIT
        defaultListClcTypeShouldBeFound("dateEdit.in=" + DEFAULT_DATE_EDIT + "," + UPDATED_DATE_EDIT);

        // Get all the listClcTypeList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListClcTypeShouldNotBeFound("dateEdit.in=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListClcTypesByDateEditIsNullOrNotNull() throws Exception {
        // Initialize the database
        listClcTypeRepository.saveAndFlush(listClcType);

        // Get all the listClcTypeList where dateEdit is not null
        defaultListClcTypeShouldBeFound("dateEdit.specified=true");

        // Get all the listClcTypeList where dateEdit is null
        defaultListClcTypeShouldNotBeFound("dateEdit.specified=false");
    }

    @Test
    @Transactional
    public void getAllListClcTypesByCreatorIsEqualToSomething() throws Exception {
        // Initialize the database
        listClcTypeRepository.saveAndFlush(listClcType);

        // Get all the listClcTypeList where creator equals to DEFAULT_CREATOR
        defaultListClcTypeShouldBeFound("creator.equals=" + DEFAULT_CREATOR);

        // Get all the listClcTypeList where creator equals to UPDATED_CREATOR
        defaultListClcTypeShouldNotBeFound("creator.equals=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListClcTypesByCreatorIsInShouldWork() throws Exception {
        // Initialize the database
        listClcTypeRepository.saveAndFlush(listClcType);

        // Get all the listClcTypeList where creator in DEFAULT_CREATOR or UPDATED_CREATOR
        defaultListClcTypeShouldBeFound("creator.in=" + DEFAULT_CREATOR + "," + UPDATED_CREATOR);

        // Get all the listClcTypeList where creator equals to UPDATED_CREATOR
        defaultListClcTypeShouldNotBeFound("creator.in=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListClcTypesByCreatorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listClcTypeRepository.saveAndFlush(listClcType);

        // Get all the listClcTypeList where creator is not null
        defaultListClcTypeShouldBeFound("creator.specified=true");

        // Get all the listClcTypeList where creator is null
        defaultListClcTypeShouldNotBeFound("creator.specified=false");
    }

    @Test
    @Transactional
    public void getAllListClcTypesByEditorIsEqualToSomething() throws Exception {
        // Initialize the database
        listClcTypeRepository.saveAndFlush(listClcType);

        // Get all the listClcTypeList where editor equals to DEFAULT_EDITOR
        defaultListClcTypeShouldBeFound("editor.equals=" + DEFAULT_EDITOR);

        // Get all the listClcTypeList where editor equals to UPDATED_EDITOR
        defaultListClcTypeShouldNotBeFound("editor.equals=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListClcTypesByEditorIsInShouldWork() throws Exception {
        // Initialize the database
        listClcTypeRepository.saveAndFlush(listClcType);

        // Get all the listClcTypeList where editor in DEFAULT_EDITOR or UPDATED_EDITOR
        defaultListClcTypeShouldBeFound("editor.in=" + DEFAULT_EDITOR + "," + UPDATED_EDITOR);

        // Get all the listClcTypeList where editor equals to UPDATED_EDITOR
        defaultListClcTypeShouldNotBeFound("editor.in=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListClcTypesByEditorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listClcTypeRepository.saveAndFlush(listClcType);

        // Get all the listClcTypeList where editor is not null
        defaultListClcTypeShouldBeFound("editor.specified=true");

        // Get all the listClcTypeList where editor is null
        defaultListClcTypeShouldNotBeFound("editor.specified=false");
    }
    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultListClcTypeShouldBeFound(String filter) throws Exception {
        restListClcTypeMockMvc.perform(get("/api/list-clc-types?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listClcType.getId().intValue())))
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
        restListClcTypeMockMvc.perform(get("/api/list-clc-types/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultListClcTypeShouldNotBeFound(String filter) throws Exception {
        restListClcTypeMockMvc.perform(get("/api/list-clc-types?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restListClcTypeMockMvc.perform(get("/api/list-clc-types/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingListClcType() throws Exception {
        // Get the listClcType
        restListClcTypeMockMvc.perform(get("/api/list-clc-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateListClcType() throws Exception {
        // Initialize the database
        listClcTypeRepository.saveAndFlush(listClcType);

        int databaseSizeBeforeUpdate = listClcTypeRepository.findAll().size();

        // Update the listClcType
        ListClcType updatedListClcType = listClcTypeRepository.findById(listClcType.getId()).get();
        // Disconnect from session so that the updates on updatedListClcType are not directly saved in db
        em.detach(updatedListClcType);
        updatedListClcType
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        ListClcTypeDTO listClcTypeDTO = listClcTypeMapper.toDto(updatedListClcType);

        restListClcTypeMockMvc.perform(put("/api/list-clc-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listClcTypeDTO)))
            .andExpect(status().isOk());

        // Validate the ListClcType in the database
        List<ListClcType> listClcTypeList = listClcTypeRepository.findAll();
        assertThat(listClcTypeList).hasSize(databaseSizeBeforeUpdate);
        ListClcType testListClcType = listClcTypeList.get(listClcTypeList.size() - 1);
        assertThat(testListClcType.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testListClcType.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testListClcType.getFullName()).isEqualTo(UPDATED_FULL_NAME);
        assertThat(testListClcType.getIsCurrentFlag()).isEqualTo(UPDATED_IS_CURRENT_FLAG);
        assertThat(testListClcType.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testListClcType.getDateCreate()).isEqualTo(UPDATED_DATE_CREATE);
        assertThat(testListClcType.getDateEdit()).isEqualTo(UPDATED_DATE_EDIT);
        assertThat(testListClcType.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testListClcType.getEditor()).isEqualTo(UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void updateNonExistingListClcType() throws Exception {
        int databaseSizeBeforeUpdate = listClcTypeRepository.findAll().size();

        // Create the ListClcType
        ListClcTypeDTO listClcTypeDTO = listClcTypeMapper.toDto(listClcType);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restListClcTypeMockMvc.perform(put("/api/list-clc-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listClcTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListClcType in the database
        List<ListClcType> listClcTypeList = listClcTypeRepository.findAll();
        assertThat(listClcTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteListClcType() throws Exception {
        // Initialize the database
        listClcTypeRepository.saveAndFlush(listClcType);

        int databaseSizeBeforeDelete = listClcTypeRepository.findAll().size();

        // Delete the listClcType
        restListClcTypeMockMvc.perform(delete("/api/list-clc-types/{id}", listClcType.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<ListClcType> listClcTypeList = listClcTypeRepository.findAll();
        assertThat(listClcTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListClcType.class);
        ListClcType listClcType1 = new ListClcType();
        listClcType1.setId(1L);
        ListClcType listClcType2 = new ListClcType();
        listClcType2.setId(listClcType1.getId());
        assertThat(listClcType1).isEqualTo(listClcType2);
        listClcType2.setId(2L);
        assertThat(listClcType1).isNotEqualTo(listClcType2);
        listClcType1.setId(null);
        assertThat(listClcType1).isNotEqualTo(listClcType2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListClcTypeDTO.class);
        ListClcTypeDTO listClcTypeDTO1 = new ListClcTypeDTO();
        listClcTypeDTO1.setId(1L);
        ListClcTypeDTO listClcTypeDTO2 = new ListClcTypeDTO();
        assertThat(listClcTypeDTO1).isNotEqualTo(listClcTypeDTO2);
        listClcTypeDTO2.setId(listClcTypeDTO1.getId());
        assertThat(listClcTypeDTO1).isEqualTo(listClcTypeDTO2);
        listClcTypeDTO2.setId(2L);
        assertThat(listClcTypeDTO1).isNotEqualTo(listClcTypeDTO2);
        listClcTypeDTO1.setId(null);
        assertThat(listClcTypeDTO1).isNotEqualTo(listClcTypeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(listClcTypeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(listClcTypeMapper.fromId(null)).isNull();
    }
}
