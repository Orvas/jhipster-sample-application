package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.ListSandType;
import io.github.jhipster.application.repository.ListSandTypeRepository;
import io.github.jhipster.application.service.ListSandTypeService;
import io.github.jhipster.application.service.dto.ListSandTypeDTO;
import io.github.jhipster.application.service.mapper.ListSandTypeMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.ListSandTypeCriteria;
import io.github.jhipster.application.service.ListSandTypeQueryService;

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
 * Integration tests for the {@Link ListSandTypeResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class ListSandTypeResourceIT {

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
    private ListSandTypeRepository listSandTypeRepository;

    @Autowired
    private ListSandTypeMapper listSandTypeMapper;

    @Autowired
    private ListSandTypeService listSandTypeService;

    @Autowired
    private ListSandTypeQueryService listSandTypeQueryService;

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

    private MockMvc restListSandTypeMockMvc;

    private ListSandType listSandType;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ListSandTypeResource listSandTypeResource = new ListSandTypeResource(listSandTypeService, listSandTypeQueryService);
        this.restListSandTypeMockMvc = MockMvcBuilders.standaloneSetup(listSandTypeResource)
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
    public static ListSandType createEntity(EntityManager em) {
        ListSandType listSandType = new ListSandType()
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME)
            .fullName(DEFAULT_FULL_NAME)
            .isCurrentFlag(DEFAULT_IS_CURRENT_FLAG)
            .description(DEFAULT_DESCRIPTION)
            .dateCreate(DEFAULT_DATE_CREATE)
            .dateEdit(DEFAULT_DATE_EDIT)
            .creator(DEFAULT_CREATOR)
            .editor(DEFAULT_EDITOR);
        return listSandType;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ListSandType createUpdatedEntity(EntityManager em) {
        ListSandType listSandType = new ListSandType()
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        return listSandType;
    }

    @BeforeEach
    public void initTest() {
        listSandType = createEntity(em);
    }

    @Test
    @Transactional
    public void createListSandType() throws Exception {
        int databaseSizeBeforeCreate = listSandTypeRepository.findAll().size();

        // Create the ListSandType
        ListSandTypeDTO listSandTypeDTO = listSandTypeMapper.toDto(listSandType);
        restListSandTypeMockMvc.perform(post("/api/list-sand-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listSandTypeDTO)))
            .andExpect(status().isCreated());

        // Validate the ListSandType in the database
        List<ListSandType> listSandTypeList = listSandTypeRepository.findAll();
        assertThat(listSandTypeList).hasSize(databaseSizeBeforeCreate + 1);
        ListSandType testListSandType = listSandTypeList.get(listSandTypeList.size() - 1);
        assertThat(testListSandType.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testListSandType.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testListSandType.getFullName()).isEqualTo(DEFAULT_FULL_NAME);
        assertThat(testListSandType.getIsCurrentFlag()).isEqualTo(DEFAULT_IS_CURRENT_FLAG);
        assertThat(testListSandType.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testListSandType.getDateCreate()).isEqualTo(DEFAULT_DATE_CREATE);
        assertThat(testListSandType.getDateEdit()).isEqualTo(DEFAULT_DATE_EDIT);
        assertThat(testListSandType.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testListSandType.getEditor()).isEqualTo(DEFAULT_EDITOR);
    }

    @Test
    @Transactional
    public void createListSandTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = listSandTypeRepository.findAll().size();

        // Create the ListSandType with an existing ID
        listSandType.setId(1L);
        ListSandTypeDTO listSandTypeDTO = listSandTypeMapper.toDto(listSandType);

        // An entity with an existing ID cannot be created, so this API call must fail
        restListSandTypeMockMvc.perform(post("/api/list-sand-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listSandTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListSandType in the database
        List<ListSandType> listSandTypeList = listSandTypeRepository.findAll();
        assertThat(listSandTypeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = listSandTypeRepository.findAll().size();
        // set the field null
        listSandType.setCode(null);

        // Create the ListSandType, which fails.
        ListSandTypeDTO listSandTypeDTO = listSandTypeMapper.toDto(listSandType);

        restListSandTypeMockMvc.perform(post("/api/list-sand-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listSandTypeDTO)))
            .andExpect(status().isBadRequest());

        List<ListSandType> listSandTypeList = listSandTypeRepository.findAll();
        assertThat(listSandTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listSandTypeRepository.findAll().size();
        // set the field null
        listSandType.setName(null);

        // Create the ListSandType, which fails.
        ListSandTypeDTO listSandTypeDTO = listSandTypeMapper.toDto(listSandType);

        restListSandTypeMockMvc.perform(post("/api/list-sand-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listSandTypeDTO)))
            .andExpect(status().isBadRequest());

        List<ListSandType> listSandTypeList = listSandTypeRepository.findAll();
        assertThat(listSandTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFullNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listSandTypeRepository.findAll().size();
        // set the field null
        listSandType.setFullName(null);

        // Create the ListSandType, which fails.
        ListSandTypeDTO listSandTypeDTO = listSandTypeMapper.toDto(listSandType);

        restListSandTypeMockMvc.perform(post("/api/list-sand-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listSandTypeDTO)))
            .andExpect(status().isBadRequest());

        List<ListSandType> listSandTypeList = listSandTypeRepository.findAll();
        assertThat(listSandTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsCurrentFlagIsRequired() throws Exception {
        int databaseSizeBeforeTest = listSandTypeRepository.findAll().size();
        // set the field null
        listSandType.setIsCurrentFlag(null);

        // Create the ListSandType, which fails.
        ListSandTypeDTO listSandTypeDTO = listSandTypeMapper.toDto(listSandType);

        restListSandTypeMockMvc.perform(post("/api/list-sand-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listSandTypeDTO)))
            .andExpect(status().isBadRequest());

        List<ListSandType> listSandTypeList = listSandTypeRepository.findAll();
        assertThat(listSandTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllListSandTypes() throws Exception {
        // Initialize the database
        listSandTypeRepository.saveAndFlush(listSandType);

        // Get all the listSandTypeList
        restListSandTypeMockMvc.perform(get("/api/list-sand-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listSandType.getId().intValue())))
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
    public void getListSandType() throws Exception {
        // Initialize the database
        listSandTypeRepository.saveAndFlush(listSandType);

        // Get the listSandType
        restListSandTypeMockMvc.perform(get("/api/list-sand-types/{id}", listSandType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(listSandType.getId().intValue()))
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
    public void getAllListSandTypesByCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        listSandTypeRepository.saveAndFlush(listSandType);

        // Get all the listSandTypeList where code equals to DEFAULT_CODE
        defaultListSandTypeShouldBeFound("code.equals=" + DEFAULT_CODE);

        // Get all the listSandTypeList where code equals to UPDATED_CODE
        defaultListSandTypeShouldNotBeFound("code.equals=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListSandTypesByCodeIsInShouldWork() throws Exception {
        // Initialize the database
        listSandTypeRepository.saveAndFlush(listSandType);

        // Get all the listSandTypeList where code in DEFAULT_CODE or UPDATED_CODE
        defaultListSandTypeShouldBeFound("code.in=" + DEFAULT_CODE + "," + UPDATED_CODE);

        // Get all the listSandTypeList where code equals to UPDATED_CODE
        defaultListSandTypeShouldNotBeFound("code.in=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListSandTypesByCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        listSandTypeRepository.saveAndFlush(listSandType);

        // Get all the listSandTypeList where code is not null
        defaultListSandTypeShouldBeFound("code.specified=true");

        // Get all the listSandTypeList where code is null
        defaultListSandTypeShouldNotBeFound("code.specified=false");
    }

    @Test
    @Transactional
    public void getAllListSandTypesByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listSandTypeRepository.saveAndFlush(listSandType);

        // Get all the listSandTypeList where name equals to DEFAULT_NAME
        defaultListSandTypeShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the listSandTypeList where name equals to UPDATED_NAME
        defaultListSandTypeShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListSandTypesByNameIsInShouldWork() throws Exception {
        // Initialize the database
        listSandTypeRepository.saveAndFlush(listSandType);

        // Get all the listSandTypeList where name in DEFAULT_NAME or UPDATED_NAME
        defaultListSandTypeShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the listSandTypeList where name equals to UPDATED_NAME
        defaultListSandTypeShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListSandTypesByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listSandTypeRepository.saveAndFlush(listSandType);

        // Get all the listSandTypeList where name is not null
        defaultListSandTypeShouldBeFound("name.specified=true");

        // Get all the listSandTypeList where name is null
        defaultListSandTypeShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    public void getAllListSandTypesByFullNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listSandTypeRepository.saveAndFlush(listSandType);

        // Get all the listSandTypeList where fullName equals to DEFAULT_FULL_NAME
        defaultListSandTypeShouldBeFound("fullName.equals=" + DEFAULT_FULL_NAME);

        // Get all the listSandTypeList where fullName equals to UPDATED_FULL_NAME
        defaultListSandTypeShouldNotBeFound("fullName.equals=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListSandTypesByFullNameIsInShouldWork() throws Exception {
        // Initialize the database
        listSandTypeRepository.saveAndFlush(listSandType);

        // Get all the listSandTypeList where fullName in DEFAULT_FULL_NAME or UPDATED_FULL_NAME
        defaultListSandTypeShouldBeFound("fullName.in=" + DEFAULT_FULL_NAME + "," + UPDATED_FULL_NAME);

        // Get all the listSandTypeList where fullName equals to UPDATED_FULL_NAME
        defaultListSandTypeShouldNotBeFound("fullName.in=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListSandTypesByFullNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listSandTypeRepository.saveAndFlush(listSandType);

        // Get all the listSandTypeList where fullName is not null
        defaultListSandTypeShouldBeFound("fullName.specified=true");

        // Get all the listSandTypeList where fullName is null
        defaultListSandTypeShouldNotBeFound("fullName.specified=false");
    }

    @Test
    @Transactional
    public void getAllListSandTypesByIsCurrentFlagIsEqualToSomething() throws Exception {
        // Initialize the database
        listSandTypeRepository.saveAndFlush(listSandType);

        // Get all the listSandTypeList where isCurrentFlag equals to DEFAULT_IS_CURRENT_FLAG
        defaultListSandTypeShouldBeFound("isCurrentFlag.equals=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listSandTypeList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListSandTypeShouldNotBeFound("isCurrentFlag.equals=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListSandTypesByIsCurrentFlagIsInShouldWork() throws Exception {
        // Initialize the database
        listSandTypeRepository.saveAndFlush(listSandType);

        // Get all the listSandTypeList where isCurrentFlag in DEFAULT_IS_CURRENT_FLAG or UPDATED_IS_CURRENT_FLAG
        defaultListSandTypeShouldBeFound("isCurrentFlag.in=" + DEFAULT_IS_CURRENT_FLAG + "," + UPDATED_IS_CURRENT_FLAG);

        // Get all the listSandTypeList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListSandTypeShouldNotBeFound("isCurrentFlag.in=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListSandTypesByIsCurrentFlagIsNullOrNotNull() throws Exception {
        // Initialize the database
        listSandTypeRepository.saveAndFlush(listSandType);

        // Get all the listSandTypeList where isCurrentFlag is not null
        defaultListSandTypeShouldBeFound("isCurrentFlag.specified=true");

        // Get all the listSandTypeList where isCurrentFlag is null
        defaultListSandTypeShouldNotBeFound("isCurrentFlag.specified=false");
    }

    @Test
    @Transactional
    public void getAllListSandTypesByIsCurrentFlagIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        listSandTypeRepository.saveAndFlush(listSandType);

        // Get all the listSandTypeList where isCurrentFlag greater than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListSandTypeShouldBeFound("isCurrentFlag.greaterOrEqualThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listSandTypeList where isCurrentFlag greater than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListSandTypeShouldNotBeFound("isCurrentFlag.greaterOrEqualThan=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListSandTypesByIsCurrentFlagIsLessThanSomething() throws Exception {
        // Initialize the database
        listSandTypeRepository.saveAndFlush(listSandType);

        // Get all the listSandTypeList where isCurrentFlag less than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListSandTypeShouldNotBeFound("isCurrentFlag.lessThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listSandTypeList where isCurrentFlag less than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListSandTypeShouldBeFound("isCurrentFlag.lessThan=" + UPDATED_IS_CURRENT_FLAG);
    }


    @Test
    @Transactional
    public void getAllListSandTypesByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        listSandTypeRepository.saveAndFlush(listSandType);

        // Get all the listSandTypeList where description equals to DEFAULT_DESCRIPTION
        defaultListSandTypeShouldBeFound("description.equals=" + DEFAULT_DESCRIPTION);

        // Get all the listSandTypeList where description equals to UPDATED_DESCRIPTION
        defaultListSandTypeShouldNotBeFound("description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListSandTypesByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        listSandTypeRepository.saveAndFlush(listSandType);

        // Get all the listSandTypeList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
        defaultListSandTypeShouldBeFound("description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION);

        // Get all the listSandTypeList where description equals to UPDATED_DESCRIPTION
        defaultListSandTypeShouldNotBeFound("description.in=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListSandTypesByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        listSandTypeRepository.saveAndFlush(listSandType);

        // Get all the listSandTypeList where description is not null
        defaultListSandTypeShouldBeFound("description.specified=true");

        // Get all the listSandTypeList where description is null
        defaultListSandTypeShouldNotBeFound("description.specified=false");
    }

    @Test
    @Transactional
    public void getAllListSandTypesByDateCreateIsEqualToSomething() throws Exception {
        // Initialize the database
        listSandTypeRepository.saveAndFlush(listSandType);

        // Get all the listSandTypeList where dateCreate equals to DEFAULT_DATE_CREATE
        defaultListSandTypeShouldBeFound("dateCreate.equals=" + DEFAULT_DATE_CREATE);

        // Get all the listSandTypeList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListSandTypeShouldNotBeFound("dateCreate.equals=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListSandTypesByDateCreateIsInShouldWork() throws Exception {
        // Initialize the database
        listSandTypeRepository.saveAndFlush(listSandType);

        // Get all the listSandTypeList where dateCreate in DEFAULT_DATE_CREATE or UPDATED_DATE_CREATE
        defaultListSandTypeShouldBeFound("dateCreate.in=" + DEFAULT_DATE_CREATE + "," + UPDATED_DATE_CREATE);

        // Get all the listSandTypeList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListSandTypeShouldNotBeFound("dateCreate.in=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListSandTypesByDateCreateIsNullOrNotNull() throws Exception {
        // Initialize the database
        listSandTypeRepository.saveAndFlush(listSandType);

        // Get all the listSandTypeList where dateCreate is not null
        defaultListSandTypeShouldBeFound("dateCreate.specified=true");

        // Get all the listSandTypeList where dateCreate is null
        defaultListSandTypeShouldNotBeFound("dateCreate.specified=false");
    }

    @Test
    @Transactional
    public void getAllListSandTypesByDateEditIsEqualToSomething() throws Exception {
        // Initialize the database
        listSandTypeRepository.saveAndFlush(listSandType);

        // Get all the listSandTypeList where dateEdit equals to DEFAULT_DATE_EDIT
        defaultListSandTypeShouldBeFound("dateEdit.equals=" + DEFAULT_DATE_EDIT);

        // Get all the listSandTypeList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListSandTypeShouldNotBeFound("dateEdit.equals=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListSandTypesByDateEditIsInShouldWork() throws Exception {
        // Initialize the database
        listSandTypeRepository.saveAndFlush(listSandType);

        // Get all the listSandTypeList where dateEdit in DEFAULT_DATE_EDIT or UPDATED_DATE_EDIT
        defaultListSandTypeShouldBeFound("dateEdit.in=" + DEFAULT_DATE_EDIT + "," + UPDATED_DATE_EDIT);

        // Get all the listSandTypeList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListSandTypeShouldNotBeFound("dateEdit.in=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListSandTypesByDateEditIsNullOrNotNull() throws Exception {
        // Initialize the database
        listSandTypeRepository.saveAndFlush(listSandType);

        // Get all the listSandTypeList where dateEdit is not null
        defaultListSandTypeShouldBeFound("dateEdit.specified=true");

        // Get all the listSandTypeList where dateEdit is null
        defaultListSandTypeShouldNotBeFound("dateEdit.specified=false");
    }

    @Test
    @Transactional
    public void getAllListSandTypesByCreatorIsEqualToSomething() throws Exception {
        // Initialize the database
        listSandTypeRepository.saveAndFlush(listSandType);

        // Get all the listSandTypeList where creator equals to DEFAULT_CREATOR
        defaultListSandTypeShouldBeFound("creator.equals=" + DEFAULT_CREATOR);

        // Get all the listSandTypeList where creator equals to UPDATED_CREATOR
        defaultListSandTypeShouldNotBeFound("creator.equals=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListSandTypesByCreatorIsInShouldWork() throws Exception {
        // Initialize the database
        listSandTypeRepository.saveAndFlush(listSandType);

        // Get all the listSandTypeList where creator in DEFAULT_CREATOR or UPDATED_CREATOR
        defaultListSandTypeShouldBeFound("creator.in=" + DEFAULT_CREATOR + "," + UPDATED_CREATOR);

        // Get all the listSandTypeList where creator equals to UPDATED_CREATOR
        defaultListSandTypeShouldNotBeFound("creator.in=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListSandTypesByCreatorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listSandTypeRepository.saveAndFlush(listSandType);

        // Get all the listSandTypeList where creator is not null
        defaultListSandTypeShouldBeFound("creator.specified=true");

        // Get all the listSandTypeList where creator is null
        defaultListSandTypeShouldNotBeFound("creator.specified=false");
    }

    @Test
    @Transactional
    public void getAllListSandTypesByEditorIsEqualToSomething() throws Exception {
        // Initialize the database
        listSandTypeRepository.saveAndFlush(listSandType);

        // Get all the listSandTypeList where editor equals to DEFAULT_EDITOR
        defaultListSandTypeShouldBeFound("editor.equals=" + DEFAULT_EDITOR);

        // Get all the listSandTypeList where editor equals to UPDATED_EDITOR
        defaultListSandTypeShouldNotBeFound("editor.equals=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListSandTypesByEditorIsInShouldWork() throws Exception {
        // Initialize the database
        listSandTypeRepository.saveAndFlush(listSandType);

        // Get all the listSandTypeList where editor in DEFAULT_EDITOR or UPDATED_EDITOR
        defaultListSandTypeShouldBeFound("editor.in=" + DEFAULT_EDITOR + "," + UPDATED_EDITOR);

        // Get all the listSandTypeList where editor equals to UPDATED_EDITOR
        defaultListSandTypeShouldNotBeFound("editor.in=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListSandTypesByEditorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listSandTypeRepository.saveAndFlush(listSandType);

        // Get all the listSandTypeList where editor is not null
        defaultListSandTypeShouldBeFound("editor.specified=true");

        // Get all the listSandTypeList where editor is null
        defaultListSandTypeShouldNotBeFound("editor.specified=false");
    }
    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultListSandTypeShouldBeFound(String filter) throws Exception {
        restListSandTypeMockMvc.perform(get("/api/list-sand-types?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listSandType.getId().intValue())))
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
        restListSandTypeMockMvc.perform(get("/api/list-sand-types/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultListSandTypeShouldNotBeFound(String filter) throws Exception {
        restListSandTypeMockMvc.perform(get("/api/list-sand-types?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restListSandTypeMockMvc.perform(get("/api/list-sand-types/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingListSandType() throws Exception {
        // Get the listSandType
        restListSandTypeMockMvc.perform(get("/api/list-sand-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateListSandType() throws Exception {
        // Initialize the database
        listSandTypeRepository.saveAndFlush(listSandType);

        int databaseSizeBeforeUpdate = listSandTypeRepository.findAll().size();

        // Update the listSandType
        ListSandType updatedListSandType = listSandTypeRepository.findById(listSandType.getId()).get();
        // Disconnect from session so that the updates on updatedListSandType are not directly saved in db
        em.detach(updatedListSandType);
        updatedListSandType
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        ListSandTypeDTO listSandTypeDTO = listSandTypeMapper.toDto(updatedListSandType);

        restListSandTypeMockMvc.perform(put("/api/list-sand-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listSandTypeDTO)))
            .andExpect(status().isOk());

        // Validate the ListSandType in the database
        List<ListSandType> listSandTypeList = listSandTypeRepository.findAll();
        assertThat(listSandTypeList).hasSize(databaseSizeBeforeUpdate);
        ListSandType testListSandType = listSandTypeList.get(listSandTypeList.size() - 1);
        assertThat(testListSandType.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testListSandType.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testListSandType.getFullName()).isEqualTo(UPDATED_FULL_NAME);
        assertThat(testListSandType.getIsCurrentFlag()).isEqualTo(UPDATED_IS_CURRENT_FLAG);
        assertThat(testListSandType.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testListSandType.getDateCreate()).isEqualTo(UPDATED_DATE_CREATE);
        assertThat(testListSandType.getDateEdit()).isEqualTo(UPDATED_DATE_EDIT);
        assertThat(testListSandType.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testListSandType.getEditor()).isEqualTo(UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void updateNonExistingListSandType() throws Exception {
        int databaseSizeBeforeUpdate = listSandTypeRepository.findAll().size();

        // Create the ListSandType
        ListSandTypeDTO listSandTypeDTO = listSandTypeMapper.toDto(listSandType);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restListSandTypeMockMvc.perform(put("/api/list-sand-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listSandTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListSandType in the database
        List<ListSandType> listSandTypeList = listSandTypeRepository.findAll();
        assertThat(listSandTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteListSandType() throws Exception {
        // Initialize the database
        listSandTypeRepository.saveAndFlush(listSandType);

        int databaseSizeBeforeDelete = listSandTypeRepository.findAll().size();

        // Delete the listSandType
        restListSandTypeMockMvc.perform(delete("/api/list-sand-types/{id}", listSandType.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<ListSandType> listSandTypeList = listSandTypeRepository.findAll();
        assertThat(listSandTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListSandType.class);
        ListSandType listSandType1 = new ListSandType();
        listSandType1.setId(1L);
        ListSandType listSandType2 = new ListSandType();
        listSandType2.setId(listSandType1.getId());
        assertThat(listSandType1).isEqualTo(listSandType2);
        listSandType2.setId(2L);
        assertThat(listSandType1).isNotEqualTo(listSandType2);
        listSandType1.setId(null);
        assertThat(listSandType1).isNotEqualTo(listSandType2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListSandTypeDTO.class);
        ListSandTypeDTO listSandTypeDTO1 = new ListSandTypeDTO();
        listSandTypeDTO1.setId(1L);
        ListSandTypeDTO listSandTypeDTO2 = new ListSandTypeDTO();
        assertThat(listSandTypeDTO1).isNotEqualTo(listSandTypeDTO2);
        listSandTypeDTO2.setId(listSandTypeDTO1.getId());
        assertThat(listSandTypeDTO1).isEqualTo(listSandTypeDTO2);
        listSandTypeDTO2.setId(2L);
        assertThat(listSandTypeDTO1).isNotEqualTo(listSandTypeDTO2);
        listSandTypeDTO1.setId(null);
        assertThat(listSandTypeDTO1).isNotEqualTo(listSandTypeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(listSandTypeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(listSandTypeMapper.fromId(null)).isNull();
    }
}
