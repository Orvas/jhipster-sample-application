package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.ListAnodeBraceleteType;
import io.github.jhipster.application.domain.AnodeHist;
import io.github.jhipster.application.repository.ListAnodeBraceleteTypeRepository;
import io.github.jhipster.application.service.ListAnodeBraceleteTypeService;
import io.github.jhipster.application.service.dto.ListAnodeBraceleteTypeDTO;
import io.github.jhipster.application.service.mapper.ListAnodeBraceleteTypeMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.ListAnodeBraceleteTypeCriteria;
import io.github.jhipster.application.service.ListAnodeBraceleteTypeQueryService;

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
 * Integration tests for the {@Link ListAnodeBraceleteTypeResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class ListAnodeBraceleteTypeResourceIT {

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
    private ListAnodeBraceleteTypeRepository listAnodeBraceleteTypeRepository;

    @Autowired
    private ListAnodeBraceleteTypeMapper listAnodeBraceleteTypeMapper;

    @Autowired
    private ListAnodeBraceleteTypeService listAnodeBraceleteTypeService;

    @Autowired
    private ListAnodeBraceleteTypeQueryService listAnodeBraceleteTypeQueryService;

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

    private MockMvc restListAnodeBraceleteTypeMockMvc;

    private ListAnodeBraceleteType listAnodeBraceleteType;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ListAnodeBraceleteTypeResource listAnodeBraceleteTypeResource = new ListAnodeBraceleteTypeResource(listAnodeBraceleteTypeService, listAnodeBraceleteTypeQueryService);
        this.restListAnodeBraceleteTypeMockMvc = MockMvcBuilders.standaloneSetup(listAnodeBraceleteTypeResource)
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
    public static ListAnodeBraceleteType createEntity(EntityManager em) {
        ListAnodeBraceleteType listAnodeBraceleteType = new ListAnodeBraceleteType()
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME)
            .fullName(DEFAULT_FULL_NAME)
            .isCurrentFlag(DEFAULT_IS_CURRENT_FLAG)
            .description(DEFAULT_DESCRIPTION)
            .dateCreate(DEFAULT_DATE_CREATE)
            .dateEdit(DEFAULT_DATE_EDIT)
            .creator(DEFAULT_CREATOR)
            .editor(DEFAULT_EDITOR);
        return listAnodeBraceleteType;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ListAnodeBraceleteType createUpdatedEntity(EntityManager em) {
        ListAnodeBraceleteType listAnodeBraceleteType = new ListAnodeBraceleteType()
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        return listAnodeBraceleteType;
    }

    @BeforeEach
    public void initTest() {
        listAnodeBraceleteType = createEntity(em);
    }

    @Test
    @Transactional
    public void createListAnodeBraceleteType() throws Exception {
        int databaseSizeBeforeCreate = listAnodeBraceleteTypeRepository.findAll().size();

        // Create the ListAnodeBraceleteType
        ListAnodeBraceleteTypeDTO listAnodeBraceleteTypeDTO = listAnodeBraceleteTypeMapper.toDto(listAnodeBraceleteType);
        restListAnodeBraceleteTypeMockMvc.perform(post("/api/list-anode-bracelete-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listAnodeBraceleteTypeDTO)))
            .andExpect(status().isCreated());

        // Validate the ListAnodeBraceleteType in the database
        List<ListAnodeBraceleteType> listAnodeBraceleteTypeList = listAnodeBraceleteTypeRepository.findAll();
        assertThat(listAnodeBraceleteTypeList).hasSize(databaseSizeBeforeCreate + 1);
        ListAnodeBraceleteType testListAnodeBraceleteType = listAnodeBraceleteTypeList.get(listAnodeBraceleteTypeList.size() - 1);
        assertThat(testListAnodeBraceleteType.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testListAnodeBraceleteType.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testListAnodeBraceleteType.getFullName()).isEqualTo(DEFAULT_FULL_NAME);
        assertThat(testListAnodeBraceleteType.getIsCurrentFlag()).isEqualTo(DEFAULT_IS_CURRENT_FLAG);
        assertThat(testListAnodeBraceleteType.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testListAnodeBraceleteType.getDateCreate()).isEqualTo(DEFAULT_DATE_CREATE);
        assertThat(testListAnodeBraceleteType.getDateEdit()).isEqualTo(DEFAULT_DATE_EDIT);
        assertThat(testListAnodeBraceleteType.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testListAnodeBraceleteType.getEditor()).isEqualTo(DEFAULT_EDITOR);
    }

    @Test
    @Transactional
    public void createListAnodeBraceleteTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = listAnodeBraceleteTypeRepository.findAll().size();

        // Create the ListAnodeBraceleteType with an existing ID
        listAnodeBraceleteType.setId(1L);
        ListAnodeBraceleteTypeDTO listAnodeBraceleteTypeDTO = listAnodeBraceleteTypeMapper.toDto(listAnodeBraceleteType);

        // An entity with an existing ID cannot be created, so this API call must fail
        restListAnodeBraceleteTypeMockMvc.perform(post("/api/list-anode-bracelete-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listAnodeBraceleteTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListAnodeBraceleteType in the database
        List<ListAnodeBraceleteType> listAnodeBraceleteTypeList = listAnodeBraceleteTypeRepository.findAll();
        assertThat(listAnodeBraceleteTypeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = listAnodeBraceleteTypeRepository.findAll().size();
        // set the field null
        listAnodeBraceleteType.setCode(null);

        // Create the ListAnodeBraceleteType, which fails.
        ListAnodeBraceleteTypeDTO listAnodeBraceleteTypeDTO = listAnodeBraceleteTypeMapper.toDto(listAnodeBraceleteType);

        restListAnodeBraceleteTypeMockMvc.perform(post("/api/list-anode-bracelete-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listAnodeBraceleteTypeDTO)))
            .andExpect(status().isBadRequest());

        List<ListAnodeBraceleteType> listAnodeBraceleteTypeList = listAnodeBraceleteTypeRepository.findAll();
        assertThat(listAnodeBraceleteTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listAnodeBraceleteTypeRepository.findAll().size();
        // set the field null
        listAnodeBraceleteType.setName(null);

        // Create the ListAnodeBraceleteType, which fails.
        ListAnodeBraceleteTypeDTO listAnodeBraceleteTypeDTO = listAnodeBraceleteTypeMapper.toDto(listAnodeBraceleteType);

        restListAnodeBraceleteTypeMockMvc.perform(post("/api/list-anode-bracelete-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listAnodeBraceleteTypeDTO)))
            .andExpect(status().isBadRequest());

        List<ListAnodeBraceleteType> listAnodeBraceleteTypeList = listAnodeBraceleteTypeRepository.findAll();
        assertThat(listAnodeBraceleteTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFullNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listAnodeBraceleteTypeRepository.findAll().size();
        // set the field null
        listAnodeBraceleteType.setFullName(null);

        // Create the ListAnodeBraceleteType, which fails.
        ListAnodeBraceleteTypeDTO listAnodeBraceleteTypeDTO = listAnodeBraceleteTypeMapper.toDto(listAnodeBraceleteType);

        restListAnodeBraceleteTypeMockMvc.perform(post("/api/list-anode-bracelete-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listAnodeBraceleteTypeDTO)))
            .andExpect(status().isBadRequest());

        List<ListAnodeBraceleteType> listAnodeBraceleteTypeList = listAnodeBraceleteTypeRepository.findAll();
        assertThat(listAnodeBraceleteTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsCurrentFlagIsRequired() throws Exception {
        int databaseSizeBeforeTest = listAnodeBraceleteTypeRepository.findAll().size();
        // set the field null
        listAnodeBraceleteType.setIsCurrentFlag(null);

        // Create the ListAnodeBraceleteType, which fails.
        ListAnodeBraceleteTypeDTO listAnodeBraceleteTypeDTO = listAnodeBraceleteTypeMapper.toDto(listAnodeBraceleteType);

        restListAnodeBraceleteTypeMockMvc.perform(post("/api/list-anode-bracelete-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listAnodeBraceleteTypeDTO)))
            .andExpect(status().isBadRequest());

        List<ListAnodeBraceleteType> listAnodeBraceleteTypeList = listAnodeBraceleteTypeRepository.findAll();
        assertThat(listAnodeBraceleteTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllListAnodeBraceleteTypes() throws Exception {
        // Initialize the database
        listAnodeBraceleteTypeRepository.saveAndFlush(listAnodeBraceleteType);

        // Get all the listAnodeBraceleteTypeList
        restListAnodeBraceleteTypeMockMvc.perform(get("/api/list-anode-bracelete-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listAnodeBraceleteType.getId().intValue())))
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
    public void getListAnodeBraceleteType() throws Exception {
        // Initialize the database
        listAnodeBraceleteTypeRepository.saveAndFlush(listAnodeBraceleteType);

        // Get the listAnodeBraceleteType
        restListAnodeBraceleteTypeMockMvc.perform(get("/api/list-anode-bracelete-types/{id}", listAnodeBraceleteType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(listAnodeBraceleteType.getId().intValue()))
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
    public void getAllListAnodeBraceleteTypesByCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        listAnodeBraceleteTypeRepository.saveAndFlush(listAnodeBraceleteType);

        // Get all the listAnodeBraceleteTypeList where code equals to DEFAULT_CODE
        defaultListAnodeBraceleteTypeShouldBeFound("code.equals=" + DEFAULT_CODE);

        // Get all the listAnodeBraceleteTypeList where code equals to UPDATED_CODE
        defaultListAnodeBraceleteTypeShouldNotBeFound("code.equals=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListAnodeBraceleteTypesByCodeIsInShouldWork() throws Exception {
        // Initialize the database
        listAnodeBraceleteTypeRepository.saveAndFlush(listAnodeBraceleteType);

        // Get all the listAnodeBraceleteTypeList where code in DEFAULT_CODE or UPDATED_CODE
        defaultListAnodeBraceleteTypeShouldBeFound("code.in=" + DEFAULT_CODE + "," + UPDATED_CODE);

        // Get all the listAnodeBraceleteTypeList where code equals to UPDATED_CODE
        defaultListAnodeBraceleteTypeShouldNotBeFound("code.in=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListAnodeBraceleteTypesByCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        listAnodeBraceleteTypeRepository.saveAndFlush(listAnodeBraceleteType);

        // Get all the listAnodeBraceleteTypeList where code is not null
        defaultListAnodeBraceleteTypeShouldBeFound("code.specified=true");

        // Get all the listAnodeBraceleteTypeList where code is null
        defaultListAnodeBraceleteTypeShouldNotBeFound("code.specified=false");
    }

    @Test
    @Transactional
    public void getAllListAnodeBraceleteTypesByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listAnodeBraceleteTypeRepository.saveAndFlush(listAnodeBraceleteType);

        // Get all the listAnodeBraceleteTypeList where name equals to DEFAULT_NAME
        defaultListAnodeBraceleteTypeShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the listAnodeBraceleteTypeList where name equals to UPDATED_NAME
        defaultListAnodeBraceleteTypeShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListAnodeBraceleteTypesByNameIsInShouldWork() throws Exception {
        // Initialize the database
        listAnodeBraceleteTypeRepository.saveAndFlush(listAnodeBraceleteType);

        // Get all the listAnodeBraceleteTypeList where name in DEFAULT_NAME or UPDATED_NAME
        defaultListAnodeBraceleteTypeShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the listAnodeBraceleteTypeList where name equals to UPDATED_NAME
        defaultListAnodeBraceleteTypeShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListAnodeBraceleteTypesByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listAnodeBraceleteTypeRepository.saveAndFlush(listAnodeBraceleteType);

        // Get all the listAnodeBraceleteTypeList where name is not null
        defaultListAnodeBraceleteTypeShouldBeFound("name.specified=true");

        // Get all the listAnodeBraceleteTypeList where name is null
        defaultListAnodeBraceleteTypeShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    public void getAllListAnodeBraceleteTypesByFullNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listAnodeBraceleteTypeRepository.saveAndFlush(listAnodeBraceleteType);

        // Get all the listAnodeBraceleteTypeList where fullName equals to DEFAULT_FULL_NAME
        defaultListAnodeBraceleteTypeShouldBeFound("fullName.equals=" + DEFAULT_FULL_NAME);

        // Get all the listAnodeBraceleteTypeList where fullName equals to UPDATED_FULL_NAME
        defaultListAnodeBraceleteTypeShouldNotBeFound("fullName.equals=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListAnodeBraceleteTypesByFullNameIsInShouldWork() throws Exception {
        // Initialize the database
        listAnodeBraceleteTypeRepository.saveAndFlush(listAnodeBraceleteType);

        // Get all the listAnodeBraceleteTypeList where fullName in DEFAULT_FULL_NAME or UPDATED_FULL_NAME
        defaultListAnodeBraceleteTypeShouldBeFound("fullName.in=" + DEFAULT_FULL_NAME + "," + UPDATED_FULL_NAME);

        // Get all the listAnodeBraceleteTypeList where fullName equals to UPDATED_FULL_NAME
        defaultListAnodeBraceleteTypeShouldNotBeFound("fullName.in=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListAnodeBraceleteTypesByFullNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listAnodeBraceleteTypeRepository.saveAndFlush(listAnodeBraceleteType);

        // Get all the listAnodeBraceleteTypeList where fullName is not null
        defaultListAnodeBraceleteTypeShouldBeFound("fullName.specified=true");

        // Get all the listAnodeBraceleteTypeList where fullName is null
        defaultListAnodeBraceleteTypeShouldNotBeFound("fullName.specified=false");
    }

    @Test
    @Transactional
    public void getAllListAnodeBraceleteTypesByIsCurrentFlagIsEqualToSomething() throws Exception {
        // Initialize the database
        listAnodeBraceleteTypeRepository.saveAndFlush(listAnodeBraceleteType);

        // Get all the listAnodeBraceleteTypeList where isCurrentFlag equals to DEFAULT_IS_CURRENT_FLAG
        defaultListAnodeBraceleteTypeShouldBeFound("isCurrentFlag.equals=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listAnodeBraceleteTypeList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListAnodeBraceleteTypeShouldNotBeFound("isCurrentFlag.equals=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListAnodeBraceleteTypesByIsCurrentFlagIsInShouldWork() throws Exception {
        // Initialize the database
        listAnodeBraceleteTypeRepository.saveAndFlush(listAnodeBraceleteType);

        // Get all the listAnodeBraceleteTypeList where isCurrentFlag in DEFAULT_IS_CURRENT_FLAG or UPDATED_IS_CURRENT_FLAG
        defaultListAnodeBraceleteTypeShouldBeFound("isCurrentFlag.in=" + DEFAULT_IS_CURRENT_FLAG + "," + UPDATED_IS_CURRENT_FLAG);

        // Get all the listAnodeBraceleteTypeList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListAnodeBraceleteTypeShouldNotBeFound("isCurrentFlag.in=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListAnodeBraceleteTypesByIsCurrentFlagIsNullOrNotNull() throws Exception {
        // Initialize the database
        listAnodeBraceleteTypeRepository.saveAndFlush(listAnodeBraceleteType);

        // Get all the listAnodeBraceleteTypeList where isCurrentFlag is not null
        defaultListAnodeBraceleteTypeShouldBeFound("isCurrentFlag.specified=true");

        // Get all the listAnodeBraceleteTypeList where isCurrentFlag is null
        defaultListAnodeBraceleteTypeShouldNotBeFound("isCurrentFlag.specified=false");
    }

    @Test
    @Transactional
    public void getAllListAnodeBraceleteTypesByIsCurrentFlagIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        listAnodeBraceleteTypeRepository.saveAndFlush(listAnodeBraceleteType);

        // Get all the listAnodeBraceleteTypeList where isCurrentFlag greater than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListAnodeBraceleteTypeShouldBeFound("isCurrentFlag.greaterOrEqualThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listAnodeBraceleteTypeList where isCurrentFlag greater than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListAnodeBraceleteTypeShouldNotBeFound("isCurrentFlag.greaterOrEqualThan=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListAnodeBraceleteTypesByIsCurrentFlagIsLessThanSomething() throws Exception {
        // Initialize the database
        listAnodeBraceleteTypeRepository.saveAndFlush(listAnodeBraceleteType);

        // Get all the listAnodeBraceleteTypeList where isCurrentFlag less than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListAnodeBraceleteTypeShouldNotBeFound("isCurrentFlag.lessThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listAnodeBraceleteTypeList where isCurrentFlag less than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListAnodeBraceleteTypeShouldBeFound("isCurrentFlag.lessThan=" + UPDATED_IS_CURRENT_FLAG);
    }


    @Test
    @Transactional
    public void getAllListAnodeBraceleteTypesByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        listAnodeBraceleteTypeRepository.saveAndFlush(listAnodeBraceleteType);

        // Get all the listAnodeBraceleteTypeList where description equals to DEFAULT_DESCRIPTION
        defaultListAnodeBraceleteTypeShouldBeFound("description.equals=" + DEFAULT_DESCRIPTION);

        // Get all the listAnodeBraceleteTypeList where description equals to UPDATED_DESCRIPTION
        defaultListAnodeBraceleteTypeShouldNotBeFound("description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListAnodeBraceleteTypesByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        listAnodeBraceleteTypeRepository.saveAndFlush(listAnodeBraceleteType);

        // Get all the listAnodeBraceleteTypeList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
        defaultListAnodeBraceleteTypeShouldBeFound("description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION);

        // Get all the listAnodeBraceleteTypeList where description equals to UPDATED_DESCRIPTION
        defaultListAnodeBraceleteTypeShouldNotBeFound("description.in=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListAnodeBraceleteTypesByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        listAnodeBraceleteTypeRepository.saveAndFlush(listAnodeBraceleteType);

        // Get all the listAnodeBraceleteTypeList where description is not null
        defaultListAnodeBraceleteTypeShouldBeFound("description.specified=true");

        // Get all the listAnodeBraceleteTypeList where description is null
        defaultListAnodeBraceleteTypeShouldNotBeFound("description.specified=false");
    }

    @Test
    @Transactional
    public void getAllListAnodeBraceleteTypesByDateCreateIsEqualToSomething() throws Exception {
        // Initialize the database
        listAnodeBraceleteTypeRepository.saveAndFlush(listAnodeBraceleteType);

        // Get all the listAnodeBraceleteTypeList where dateCreate equals to DEFAULT_DATE_CREATE
        defaultListAnodeBraceleteTypeShouldBeFound("dateCreate.equals=" + DEFAULT_DATE_CREATE);

        // Get all the listAnodeBraceleteTypeList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListAnodeBraceleteTypeShouldNotBeFound("dateCreate.equals=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListAnodeBraceleteTypesByDateCreateIsInShouldWork() throws Exception {
        // Initialize the database
        listAnodeBraceleteTypeRepository.saveAndFlush(listAnodeBraceleteType);

        // Get all the listAnodeBraceleteTypeList where dateCreate in DEFAULT_DATE_CREATE or UPDATED_DATE_CREATE
        defaultListAnodeBraceleteTypeShouldBeFound("dateCreate.in=" + DEFAULT_DATE_CREATE + "," + UPDATED_DATE_CREATE);

        // Get all the listAnodeBraceleteTypeList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListAnodeBraceleteTypeShouldNotBeFound("dateCreate.in=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListAnodeBraceleteTypesByDateCreateIsNullOrNotNull() throws Exception {
        // Initialize the database
        listAnodeBraceleteTypeRepository.saveAndFlush(listAnodeBraceleteType);

        // Get all the listAnodeBraceleteTypeList where dateCreate is not null
        defaultListAnodeBraceleteTypeShouldBeFound("dateCreate.specified=true");

        // Get all the listAnodeBraceleteTypeList where dateCreate is null
        defaultListAnodeBraceleteTypeShouldNotBeFound("dateCreate.specified=false");
    }

    @Test
    @Transactional
    public void getAllListAnodeBraceleteTypesByDateEditIsEqualToSomething() throws Exception {
        // Initialize the database
        listAnodeBraceleteTypeRepository.saveAndFlush(listAnodeBraceleteType);

        // Get all the listAnodeBraceleteTypeList where dateEdit equals to DEFAULT_DATE_EDIT
        defaultListAnodeBraceleteTypeShouldBeFound("dateEdit.equals=" + DEFAULT_DATE_EDIT);

        // Get all the listAnodeBraceleteTypeList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListAnodeBraceleteTypeShouldNotBeFound("dateEdit.equals=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListAnodeBraceleteTypesByDateEditIsInShouldWork() throws Exception {
        // Initialize the database
        listAnodeBraceleteTypeRepository.saveAndFlush(listAnodeBraceleteType);

        // Get all the listAnodeBraceleteTypeList where dateEdit in DEFAULT_DATE_EDIT or UPDATED_DATE_EDIT
        defaultListAnodeBraceleteTypeShouldBeFound("dateEdit.in=" + DEFAULT_DATE_EDIT + "," + UPDATED_DATE_EDIT);

        // Get all the listAnodeBraceleteTypeList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListAnodeBraceleteTypeShouldNotBeFound("dateEdit.in=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListAnodeBraceleteTypesByDateEditIsNullOrNotNull() throws Exception {
        // Initialize the database
        listAnodeBraceleteTypeRepository.saveAndFlush(listAnodeBraceleteType);

        // Get all the listAnodeBraceleteTypeList where dateEdit is not null
        defaultListAnodeBraceleteTypeShouldBeFound("dateEdit.specified=true");

        // Get all the listAnodeBraceleteTypeList where dateEdit is null
        defaultListAnodeBraceleteTypeShouldNotBeFound("dateEdit.specified=false");
    }

    @Test
    @Transactional
    public void getAllListAnodeBraceleteTypesByCreatorIsEqualToSomething() throws Exception {
        // Initialize the database
        listAnodeBraceleteTypeRepository.saveAndFlush(listAnodeBraceleteType);

        // Get all the listAnodeBraceleteTypeList where creator equals to DEFAULT_CREATOR
        defaultListAnodeBraceleteTypeShouldBeFound("creator.equals=" + DEFAULT_CREATOR);

        // Get all the listAnodeBraceleteTypeList where creator equals to UPDATED_CREATOR
        defaultListAnodeBraceleteTypeShouldNotBeFound("creator.equals=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListAnodeBraceleteTypesByCreatorIsInShouldWork() throws Exception {
        // Initialize the database
        listAnodeBraceleteTypeRepository.saveAndFlush(listAnodeBraceleteType);

        // Get all the listAnodeBraceleteTypeList where creator in DEFAULT_CREATOR or UPDATED_CREATOR
        defaultListAnodeBraceleteTypeShouldBeFound("creator.in=" + DEFAULT_CREATOR + "," + UPDATED_CREATOR);

        // Get all the listAnodeBraceleteTypeList where creator equals to UPDATED_CREATOR
        defaultListAnodeBraceleteTypeShouldNotBeFound("creator.in=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListAnodeBraceleteTypesByCreatorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listAnodeBraceleteTypeRepository.saveAndFlush(listAnodeBraceleteType);

        // Get all the listAnodeBraceleteTypeList where creator is not null
        defaultListAnodeBraceleteTypeShouldBeFound("creator.specified=true");

        // Get all the listAnodeBraceleteTypeList where creator is null
        defaultListAnodeBraceleteTypeShouldNotBeFound("creator.specified=false");
    }

    @Test
    @Transactional
    public void getAllListAnodeBraceleteTypesByEditorIsEqualToSomething() throws Exception {
        // Initialize the database
        listAnodeBraceleteTypeRepository.saveAndFlush(listAnodeBraceleteType);

        // Get all the listAnodeBraceleteTypeList where editor equals to DEFAULT_EDITOR
        defaultListAnodeBraceleteTypeShouldBeFound("editor.equals=" + DEFAULT_EDITOR);

        // Get all the listAnodeBraceleteTypeList where editor equals to UPDATED_EDITOR
        defaultListAnodeBraceleteTypeShouldNotBeFound("editor.equals=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListAnodeBraceleteTypesByEditorIsInShouldWork() throws Exception {
        // Initialize the database
        listAnodeBraceleteTypeRepository.saveAndFlush(listAnodeBraceleteType);

        // Get all the listAnodeBraceleteTypeList where editor in DEFAULT_EDITOR or UPDATED_EDITOR
        defaultListAnodeBraceleteTypeShouldBeFound("editor.in=" + DEFAULT_EDITOR + "," + UPDATED_EDITOR);

        // Get all the listAnodeBraceleteTypeList where editor equals to UPDATED_EDITOR
        defaultListAnodeBraceleteTypeShouldNotBeFound("editor.in=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListAnodeBraceleteTypesByEditorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listAnodeBraceleteTypeRepository.saveAndFlush(listAnodeBraceleteType);

        // Get all the listAnodeBraceleteTypeList where editor is not null
        defaultListAnodeBraceleteTypeShouldBeFound("editor.specified=true");

        // Get all the listAnodeBraceleteTypeList where editor is null
        defaultListAnodeBraceleteTypeShouldNotBeFound("editor.specified=false");
    }

    @Test
    @Transactional
    public void getAllListAnodeBraceleteTypesByAnodeHistIsEqualToSomething() throws Exception {
        // Initialize the database
        AnodeHist anodeHist = AnodeHistResourceIT.createEntity(em);
        em.persist(anodeHist);
        em.flush();
        listAnodeBraceleteType.addAnodeHist(anodeHist);
        listAnodeBraceleteTypeRepository.saveAndFlush(listAnodeBraceleteType);
        Long anodeHistId = anodeHist.getId();

        // Get all the listAnodeBraceleteTypeList where anodeHist equals to anodeHistId
        defaultListAnodeBraceleteTypeShouldBeFound("anodeHistId.equals=" + anodeHistId);

        // Get all the listAnodeBraceleteTypeList where anodeHist equals to anodeHistId + 1
        defaultListAnodeBraceleteTypeShouldNotBeFound("anodeHistId.equals=" + (anodeHistId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultListAnodeBraceleteTypeShouldBeFound(String filter) throws Exception {
        restListAnodeBraceleteTypeMockMvc.perform(get("/api/list-anode-bracelete-types?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listAnodeBraceleteType.getId().intValue())))
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
        restListAnodeBraceleteTypeMockMvc.perform(get("/api/list-anode-bracelete-types/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultListAnodeBraceleteTypeShouldNotBeFound(String filter) throws Exception {
        restListAnodeBraceleteTypeMockMvc.perform(get("/api/list-anode-bracelete-types?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restListAnodeBraceleteTypeMockMvc.perform(get("/api/list-anode-bracelete-types/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingListAnodeBraceleteType() throws Exception {
        // Get the listAnodeBraceleteType
        restListAnodeBraceleteTypeMockMvc.perform(get("/api/list-anode-bracelete-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateListAnodeBraceleteType() throws Exception {
        // Initialize the database
        listAnodeBraceleteTypeRepository.saveAndFlush(listAnodeBraceleteType);

        int databaseSizeBeforeUpdate = listAnodeBraceleteTypeRepository.findAll().size();

        // Update the listAnodeBraceleteType
        ListAnodeBraceleteType updatedListAnodeBraceleteType = listAnodeBraceleteTypeRepository.findById(listAnodeBraceleteType.getId()).get();
        // Disconnect from session so that the updates on updatedListAnodeBraceleteType are not directly saved in db
        em.detach(updatedListAnodeBraceleteType);
        updatedListAnodeBraceleteType
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        ListAnodeBraceleteTypeDTO listAnodeBraceleteTypeDTO = listAnodeBraceleteTypeMapper.toDto(updatedListAnodeBraceleteType);

        restListAnodeBraceleteTypeMockMvc.perform(put("/api/list-anode-bracelete-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listAnodeBraceleteTypeDTO)))
            .andExpect(status().isOk());

        // Validate the ListAnodeBraceleteType in the database
        List<ListAnodeBraceleteType> listAnodeBraceleteTypeList = listAnodeBraceleteTypeRepository.findAll();
        assertThat(listAnodeBraceleteTypeList).hasSize(databaseSizeBeforeUpdate);
        ListAnodeBraceleteType testListAnodeBraceleteType = listAnodeBraceleteTypeList.get(listAnodeBraceleteTypeList.size() - 1);
        assertThat(testListAnodeBraceleteType.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testListAnodeBraceleteType.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testListAnodeBraceleteType.getFullName()).isEqualTo(UPDATED_FULL_NAME);
        assertThat(testListAnodeBraceleteType.getIsCurrentFlag()).isEqualTo(UPDATED_IS_CURRENT_FLAG);
        assertThat(testListAnodeBraceleteType.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testListAnodeBraceleteType.getDateCreate()).isEqualTo(UPDATED_DATE_CREATE);
        assertThat(testListAnodeBraceleteType.getDateEdit()).isEqualTo(UPDATED_DATE_EDIT);
        assertThat(testListAnodeBraceleteType.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testListAnodeBraceleteType.getEditor()).isEqualTo(UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void updateNonExistingListAnodeBraceleteType() throws Exception {
        int databaseSizeBeforeUpdate = listAnodeBraceleteTypeRepository.findAll().size();

        // Create the ListAnodeBraceleteType
        ListAnodeBraceleteTypeDTO listAnodeBraceleteTypeDTO = listAnodeBraceleteTypeMapper.toDto(listAnodeBraceleteType);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restListAnodeBraceleteTypeMockMvc.perform(put("/api/list-anode-bracelete-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listAnodeBraceleteTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListAnodeBraceleteType in the database
        List<ListAnodeBraceleteType> listAnodeBraceleteTypeList = listAnodeBraceleteTypeRepository.findAll();
        assertThat(listAnodeBraceleteTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteListAnodeBraceleteType() throws Exception {
        // Initialize the database
        listAnodeBraceleteTypeRepository.saveAndFlush(listAnodeBraceleteType);

        int databaseSizeBeforeDelete = listAnodeBraceleteTypeRepository.findAll().size();

        // Delete the listAnodeBraceleteType
        restListAnodeBraceleteTypeMockMvc.perform(delete("/api/list-anode-bracelete-types/{id}", listAnodeBraceleteType.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<ListAnodeBraceleteType> listAnodeBraceleteTypeList = listAnodeBraceleteTypeRepository.findAll();
        assertThat(listAnodeBraceleteTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListAnodeBraceleteType.class);
        ListAnodeBraceleteType listAnodeBraceleteType1 = new ListAnodeBraceleteType();
        listAnodeBraceleteType1.setId(1L);
        ListAnodeBraceleteType listAnodeBraceleteType2 = new ListAnodeBraceleteType();
        listAnodeBraceleteType2.setId(listAnodeBraceleteType1.getId());
        assertThat(listAnodeBraceleteType1).isEqualTo(listAnodeBraceleteType2);
        listAnodeBraceleteType2.setId(2L);
        assertThat(listAnodeBraceleteType1).isNotEqualTo(listAnodeBraceleteType2);
        listAnodeBraceleteType1.setId(null);
        assertThat(listAnodeBraceleteType1).isNotEqualTo(listAnodeBraceleteType2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListAnodeBraceleteTypeDTO.class);
        ListAnodeBraceleteTypeDTO listAnodeBraceleteTypeDTO1 = new ListAnodeBraceleteTypeDTO();
        listAnodeBraceleteTypeDTO1.setId(1L);
        ListAnodeBraceleteTypeDTO listAnodeBraceleteTypeDTO2 = new ListAnodeBraceleteTypeDTO();
        assertThat(listAnodeBraceleteTypeDTO1).isNotEqualTo(listAnodeBraceleteTypeDTO2);
        listAnodeBraceleteTypeDTO2.setId(listAnodeBraceleteTypeDTO1.getId());
        assertThat(listAnodeBraceleteTypeDTO1).isEqualTo(listAnodeBraceleteTypeDTO2);
        listAnodeBraceleteTypeDTO2.setId(2L);
        assertThat(listAnodeBraceleteTypeDTO1).isNotEqualTo(listAnodeBraceleteTypeDTO2);
        listAnodeBraceleteTypeDTO1.setId(null);
        assertThat(listAnodeBraceleteTypeDTO1).isNotEqualTo(listAnodeBraceleteTypeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(listAnodeBraceleteTypeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(listAnodeBraceleteTypeMapper.fromId(null)).isNull();
    }
}
