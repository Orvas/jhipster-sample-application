package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.ListDfctType;
import io.github.jhipster.application.domain.ListDfctGroup;
import io.github.jhipster.application.repository.ListDfctTypeRepository;
import io.github.jhipster.application.service.ListDfctTypeService;
import io.github.jhipster.application.service.dto.ListDfctTypeDTO;
import io.github.jhipster.application.service.mapper.ListDfctTypeMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.ListDfctTypeCriteria;
import io.github.jhipster.application.service.ListDfctTypeQueryService;

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
 * Integration tests for the {@Link ListDfctTypeResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class ListDfctTypeResourceIT {

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
    private ListDfctTypeRepository listDfctTypeRepository;

    @Autowired
    private ListDfctTypeMapper listDfctTypeMapper;

    @Autowired
    private ListDfctTypeService listDfctTypeService;

    @Autowired
    private ListDfctTypeQueryService listDfctTypeQueryService;

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

    private MockMvc restListDfctTypeMockMvc;

    private ListDfctType listDfctType;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ListDfctTypeResource listDfctTypeResource = new ListDfctTypeResource(listDfctTypeService, listDfctTypeQueryService);
        this.restListDfctTypeMockMvc = MockMvcBuilders.standaloneSetup(listDfctTypeResource)
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
    public static ListDfctType createEntity(EntityManager em) {
        ListDfctType listDfctType = new ListDfctType()
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME)
            .fullName(DEFAULT_FULL_NAME)
            .isCurrentFlag(DEFAULT_IS_CURRENT_FLAG)
            .description(DEFAULT_DESCRIPTION)
            .dateCreate(DEFAULT_DATE_CREATE)
            .dateEdit(DEFAULT_DATE_EDIT)
            .creator(DEFAULT_CREATOR)
            .editor(DEFAULT_EDITOR);
        // Add required entity
        ListDfctGroup listDfctGroup;
        if (TestUtil.findAll(em, ListDfctGroup.class).isEmpty()) {
            listDfctGroup = ListDfctGroupResourceIT.createEntity(em);
            em.persist(listDfctGroup);
            em.flush();
        } else {
            listDfctGroup = TestUtil.findAll(em, ListDfctGroup.class).get(0);
        }
        listDfctType.setIdGroup(listDfctGroup);
        return listDfctType;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ListDfctType createUpdatedEntity(EntityManager em) {
        ListDfctType listDfctType = new ListDfctType()
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        // Add required entity
        ListDfctGroup listDfctGroup;
        if (TestUtil.findAll(em, ListDfctGroup.class).isEmpty()) {
            listDfctGroup = ListDfctGroupResourceIT.createUpdatedEntity(em);
            em.persist(listDfctGroup);
            em.flush();
        } else {
            listDfctGroup = TestUtil.findAll(em, ListDfctGroup.class).get(0);
        }
        listDfctType.setIdGroup(listDfctGroup);
        return listDfctType;
    }

    @BeforeEach
    public void initTest() {
        listDfctType = createEntity(em);
    }

    @Test
    @Transactional
    public void createListDfctType() throws Exception {
        int databaseSizeBeforeCreate = listDfctTypeRepository.findAll().size();

        // Create the ListDfctType
        ListDfctTypeDTO listDfctTypeDTO = listDfctTypeMapper.toDto(listDfctType);
        restListDfctTypeMockMvc.perform(post("/api/list-dfct-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listDfctTypeDTO)))
            .andExpect(status().isCreated());

        // Validate the ListDfctType in the database
        List<ListDfctType> listDfctTypeList = listDfctTypeRepository.findAll();
        assertThat(listDfctTypeList).hasSize(databaseSizeBeforeCreate + 1);
        ListDfctType testListDfctType = listDfctTypeList.get(listDfctTypeList.size() - 1);
        assertThat(testListDfctType.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testListDfctType.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testListDfctType.getFullName()).isEqualTo(DEFAULT_FULL_NAME);
        assertThat(testListDfctType.getIsCurrentFlag()).isEqualTo(DEFAULT_IS_CURRENT_FLAG);
        assertThat(testListDfctType.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testListDfctType.getDateCreate()).isEqualTo(DEFAULT_DATE_CREATE);
        assertThat(testListDfctType.getDateEdit()).isEqualTo(DEFAULT_DATE_EDIT);
        assertThat(testListDfctType.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testListDfctType.getEditor()).isEqualTo(DEFAULT_EDITOR);
    }

    @Test
    @Transactional
    public void createListDfctTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = listDfctTypeRepository.findAll().size();

        // Create the ListDfctType with an existing ID
        listDfctType.setId(1L);
        ListDfctTypeDTO listDfctTypeDTO = listDfctTypeMapper.toDto(listDfctType);

        // An entity with an existing ID cannot be created, so this API call must fail
        restListDfctTypeMockMvc.perform(post("/api/list-dfct-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listDfctTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListDfctType in the database
        List<ListDfctType> listDfctTypeList = listDfctTypeRepository.findAll();
        assertThat(listDfctTypeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = listDfctTypeRepository.findAll().size();
        // set the field null
        listDfctType.setCode(null);

        // Create the ListDfctType, which fails.
        ListDfctTypeDTO listDfctTypeDTO = listDfctTypeMapper.toDto(listDfctType);

        restListDfctTypeMockMvc.perform(post("/api/list-dfct-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listDfctTypeDTO)))
            .andExpect(status().isBadRequest());

        List<ListDfctType> listDfctTypeList = listDfctTypeRepository.findAll();
        assertThat(listDfctTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listDfctTypeRepository.findAll().size();
        // set the field null
        listDfctType.setName(null);

        // Create the ListDfctType, which fails.
        ListDfctTypeDTO listDfctTypeDTO = listDfctTypeMapper.toDto(listDfctType);

        restListDfctTypeMockMvc.perform(post("/api/list-dfct-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listDfctTypeDTO)))
            .andExpect(status().isBadRequest());

        List<ListDfctType> listDfctTypeList = listDfctTypeRepository.findAll();
        assertThat(listDfctTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFullNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listDfctTypeRepository.findAll().size();
        // set the field null
        listDfctType.setFullName(null);

        // Create the ListDfctType, which fails.
        ListDfctTypeDTO listDfctTypeDTO = listDfctTypeMapper.toDto(listDfctType);

        restListDfctTypeMockMvc.perform(post("/api/list-dfct-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listDfctTypeDTO)))
            .andExpect(status().isBadRequest());

        List<ListDfctType> listDfctTypeList = listDfctTypeRepository.findAll();
        assertThat(listDfctTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsCurrentFlagIsRequired() throws Exception {
        int databaseSizeBeforeTest = listDfctTypeRepository.findAll().size();
        // set the field null
        listDfctType.setIsCurrentFlag(null);

        // Create the ListDfctType, which fails.
        ListDfctTypeDTO listDfctTypeDTO = listDfctTypeMapper.toDto(listDfctType);

        restListDfctTypeMockMvc.perform(post("/api/list-dfct-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listDfctTypeDTO)))
            .andExpect(status().isBadRequest());

        List<ListDfctType> listDfctTypeList = listDfctTypeRepository.findAll();
        assertThat(listDfctTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllListDfctTypes() throws Exception {
        // Initialize the database
        listDfctTypeRepository.saveAndFlush(listDfctType);

        // Get all the listDfctTypeList
        restListDfctTypeMockMvc.perform(get("/api/list-dfct-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listDfctType.getId().intValue())))
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
    public void getListDfctType() throws Exception {
        // Initialize the database
        listDfctTypeRepository.saveAndFlush(listDfctType);

        // Get the listDfctType
        restListDfctTypeMockMvc.perform(get("/api/list-dfct-types/{id}", listDfctType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(listDfctType.getId().intValue()))
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
    public void getAllListDfctTypesByCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        listDfctTypeRepository.saveAndFlush(listDfctType);

        // Get all the listDfctTypeList where code equals to DEFAULT_CODE
        defaultListDfctTypeShouldBeFound("code.equals=" + DEFAULT_CODE);

        // Get all the listDfctTypeList where code equals to UPDATED_CODE
        defaultListDfctTypeShouldNotBeFound("code.equals=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListDfctTypesByCodeIsInShouldWork() throws Exception {
        // Initialize the database
        listDfctTypeRepository.saveAndFlush(listDfctType);

        // Get all the listDfctTypeList where code in DEFAULT_CODE or UPDATED_CODE
        defaultListDfctTypeShouldBeFound("code.in=" + DEFAULT_CODE + "," + UPDATED_CODE);

        // Get all the listDfctTypeList where code equals to UPDATED_CODE
        defaultListDfctTypeShouldNotBeFound("code.in=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListDfctTypesByCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        listDfctTypeRepository.saveAndFlush(listDfctType);

        // Get all the listDfctTypeList where code is not null
        defaultListDfctTypeShouldBeFound("code.specified=true");

        // Get all the listDfctTypeList where code is null
        defaultListDfctTypeShouldNotBeFound("code.specified=false");
    }

    @Test
    @Transactional
    public void getAllListDfctTypesByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listDfctTypeRepository.saveAndFlush(listDfctType);

        // Get all the listDfctTypeList where name equals to DEFAULT_NAME
        defaultListDfctTypeShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the listDfctTypeList where name equals to UPDATED_NAME
        defaultListDfctTypeShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListDfctTypesByNameIsInShouldWork() throws Exception {
        // Initialize the database
        listDfctTypeRepository.saveAndFlush(listDfctType);

        // Get all the listDfctTypeList where name in DEFAULT_NAME or UPDATED_NAME
        defaultListDfctTypeShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the listDfctTypeList where name equals to UPDATED_NAME
        defaultListDfctTypeShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListDfctTypesByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listDfctTypeRepository.saveAndFlush(listDfctType);

        // Get all the listDfctTypeList where name is not null
        defaultListDfctTypeShouldBeFound("name.specified=true");

        // Get all the listDfctTypeList where name is null
        defaultListDfctTypeShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    public void getAllListDfctTypesByFullNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listDfctTypeRepository.saveAndFlush(listDfctType);

        // Get all the listDfctTypeList where fullName equals to DEFAULT_FULL_NAME
        defaultListDfctTypeShouldBeFound("fullName.equals=" + DEFAULT_FULL_NAME);

        // Get all the listDfctTypeList where fullName equals to UPDATED_FULL_NAME
        defaultListDfctTypeShouldNotBeFound("fullName.equals=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListDfctTypesByFullNameIsInShouldWork() throws Exception {
        // Initialize the database
        listDfctTypeRepository.saveAndFlush(listDfctType);

        // Get all the listDfctTypeList where fullName in DEFAULT_FULL_NAME or UPDATED_FULL_NAME
        defaultListDfctTypeShouldBeFound("fullName.in=" + DEFAULT_FULL_NAME + "," + UPDATED_FULL_NAME);

        // Get all the listDfctTypeList where fullName equals to UPDATED_FULL_NAME
        defaultListDfctTypeShouldNotBeFound("fullName.in=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListDfctTypesByFullNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listDfctTypeRepository.saveAndFlush(listDfctType);

        // Get all the listDfctTypeList where fullName is not null
        defaultListDfctTypeShouldBeFound("fullName.specified=true");

        // Get all the listDfctTypeList where fullName is null
        defaultListDfctTypeShouldNotBeFound("fullName.specified=false");
    }

    @Test
    @Transactional
    public void getAllListDfctTypesByIsCurrentFlagIsEqualToSomething() throws Exception {
        // Initialize the database
        listDfctTypeRepository.saveAndFlush(listDfctType);

        // Get all the listDfctTypeList where isCurrentFlag equals to DEFAULT_IS_CURRENT_FLAG
        defaultListDfctTypeShouldBeFound("isCurrentFlag.equals=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listDfctTypeList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListDfctTypeShouldNotBeFound("isCurrentFlag.equals=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListDfctTypesByIsCurrentFlagIsInShouldWork() throws Exception {
        // Initialize the database
        listDfctTypeRepository.saveAndFlush(listDfctType);

        // Get all the listDfctTypeList where isCurrentFlag in DEFAULT_IS_CURRENT_FLAG or UPDATED_IS_CURRENT_FLAG
        defaultListDfctTypeShouldBeFound("isCurrentFlag.in=" + DEFAULT_IS_CURRENT_FLAG + "," + UPDATED_IS_CURRENT_FLAG);

        // Get all the listDfctTypeList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListDfctTypeShouldNotBeFound("isCurrentFlag.in=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListDfctTypesByIsCurrentFlagIsNullOrNotNull() throws Exception {
        // Initialize the database
        listDfctTypeRepository.saveAndFlush(listDfctType);

        // Get all the listDfctTypeList where isCurrentFlag is not null
        defaultListDfctTypeShouldBeFound("isCurrentFlag.specified=true");

        // Get all the listDfctTypeList where isCurrentFlag is null
        defaultListDfctTypeShouldNotBeFound("isCurrentFlag.specified=false");
    }

    @Test
    @Transactional
    public void getAllListDfctTypesByIsCurrentFlagIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        listDfctTypeRepository.saveAndFlush(listDfctType);

        // Get all the listDfctTypeList where isCurrentFlag greater than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListDfctTypeShouldBeFound("isCurrentFlag.greaterOrEqualThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listDfctTypeList where isCurrentFlag greater than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListDfctTypeShouldNotBeFound("isCurrentFlag.greaterOrEqualThan=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListDfctTypesByIsCurrentFlagIsLessThanSomething() throws Exception {
        // Initialize the database
        listDfctTypeRepository.saveAndFlush(listDfctType);

        // Get all the listDfctTypeList where isCurrentFlag less than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListDfctTypeShouldNotBeFound("isCurrentFlag.lessThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listDfctTypeList where isCurrentFlag less than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListDfctTypeShouldBeFound("isCurrentFlag.lessThan=" + UPDATED_IS_CURRENT_FLAG);
    }


    @Test
    @Transactional
    public void getAllListDfctTypesByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        listDfctTypeRepository.saveAndFlush(listDfctType);

        // Get all the listDfctTypeList where description equals to DEFAULT_DESCRIPTION
        defaultListDfctTypeShouldBeFound("description.equals=" + DEFAULT_DESCRIPTION);

        // Get all the listDfctTypeList where description equals to UPDATED_DESCRIPTION
        defaultListDfctTypeShouldNotBeFound("description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListDfctTypesByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        listDfctTypeRepository.saveAndFlush(listDfctType);

        // Get all the listDfctTypeList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
        defaultListDfctTypeShouldBeFound("description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION);

        // Get all the listDfctTypeList where description equals to UPDATED_DESCRIPTION
        defaultListDfctTypeShouldNotBeFound("description.in=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListDfctTypesByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        listDfctTypeRepository.saveAndFlush(listDfctType);

        // Get all the listDfctTypeList where description is not null
        defaultListDfctTypeShouldBeFound("description.specified=true");

        // Get all the listDfctTypeList where description is null
        defaultListDfctTypeShouldNotBeFound("description.specified=false");
    }

    @Test
    @Transactional
    public void getAllListDfctTypesByDateCreateIsEqualToSomething() throws Exception {
        // Initialize the database
        listDfctTypeRepository.saveAndFlush(listDfctType);

        // Get all the listDfctTypeList where dateCreate equals to DEFAULT_DATE_CREATE
        defaultListDfctTypeShouldBeFound("dateCreate.equals=" + DEFAULT_DATE_CREATE);

        // Get all the listDfctTypeList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListDfctTypeShouldNotBeFound("dateCreate.equals=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListDfctTypesByDateCreateIsInShouldWork() throws Exception {
        // Initialize the database
        listDfctTypeRepository.saveAndFlush(listDfctType);

        // Get all the listDfctTypeList where dateCreate in DEFAULT_DATE_CREATE or UPDATED_DATE_CREATE
        defaultListDfctTypeShouldBeFound("dateCreate.in=" + DEFAULT_DATE_CREATE + "," + UPDATED_DATE_CREATE);

        // Get all the listDfctTypeList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListDfctTypeShouldNotBeFound("dateCreate.in=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListDfctTypesByDateCreateIsNullOrNotNull() throws Exception {
        // Initialize the database
        listDfctTypeRepository.saveAndFlush(listDfctType);

        // Get all the listDfctTypeList where dateCreate is not null
        defaultListDfctTypeShouldBeFound("dateCreate.specified=true");

        // Get all the listDfctTypeList where dateCreate is null
        defaultListDfctTypeShouldNotBeFound("dateCreate.specified=false");
    }

    @Test
    @Transactional
    public void getAllListDfctTypesByDateEditIsEqualToSomething() throws Exception {
        // Initialize the database
        listDfctTypeRepository.saveAndFlush(listDfctType);

        // Get all the listDfctTypeList where dateEdit equals to DEFAULT_DATE_EDIT
        defaultListDfctTypeShouldBeFound("dateEdit.equals=" + DEFAULT_DATE_EDIT);

        // Get all the listDfctTypeList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListDfctTypeShouldNotBeFound("dateEdit.equals=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListDfctTypesByDateEditIsInShouldWork() throws Exception {
        // Initialize the database
        listDfctTypeRepository.saveAndFlush(listDfctType);

        // Get all the listDfctTypeList where dateEdit in DEFAULT_DATE_EDIT or UPDATED_DATE_EDIT
        defaultListDfctTypeShouldBeFound("dateEdit.in=" + DEFAULT_DATE_EDIT + "," + UPDATED_DATE_EDIT);

        // Get all the listDfctTypeList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListDfctTypeShouldNotBeFound("dateEdit.in=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListDfctTypesByDateEditIsNullOrNotNull() throws Exception {
        // Initialize the database
        listDfctTypeRepository.saveAndFlush(listDfctType);

        // Get all the listDfctTypeList where dateEdit is not null
        defaultListDfctTypeShouldBeFound("dateEdit.specified=true");

        // Get all the listDfctTypeList where dateEdit is null
        defaultListDfctTypeShouldNotBeFound("dateEdit.specified=false");
    }

    @Test
    @Transactional
    public void getAllListDfctTypesByCreatorIsEqualToSomething() throws Exception {
        // Initialize the database
        listDfctTypeRepository.saveAndFlush(listDfctType);

        // Get all the listDfctTypeList where creator equals to DEFAULT_CREATOR
        defaultListDfctTypeShouldBeFound("creator.equals=" + DEFAULT_CREATOR);

        // Get all the listDfctTypeList where creator equals to UPDATED_CREATOR
        defaultListDfctTypeShouldNotBeFound("creator.equals=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListDfctTypesByCreatorIsInShouldWork() throws Exception {
        // Initialize the database
        listDfctTypeRepository.saveAndFlush(listDfctType);

        // Get all the listDfctTypeList where creator in DEFAULT_CREATOR or UPDATED_CREATOR
        defaultListDfctTypeShouldBeFound("creator.in=" + DEFAULT_CREATOR + "," + UPDATED_CREATOR);

        // Get all the listDfctTypeList where creator equals to UPDATED_CREATOR
        defaultListDfctTypeShouldNotBeFound("creator.in=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListDfctTypesByCreatorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listDfctTypeRepository.saveAndFlush(listDfctType);

        // Get all the listDfctTypeList where creator is not null
        defaultListDfctTypeShouldBeFound("creator.specified=true");

        // Get all the listDfctTypeList where creator is null
        defaultListDfctTypeShouldNotBeFound("creator.specified=false");
    }

    @Test
    @Transactional
    public void getAllListDfctTypesByEditorIsEqualToSomething() throws Exception {
        // Initialize the database
        listDfctTypeRepository.saveAndFlush(listDfctType);

        // Get all the listDfctTypeList where editor equals to DEFAULT_EDITOR
        defaultListDfctTypeShouldBeFound("editor.equals=" + DEFAULT_EDITOR);

        // Get all the listDfctTypeList where editor equals to UPDATED_EDITOR
        defaultListDfctTypeShouldNotBeFound("editor.equals=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListDfctTypesByEditorIsInShouldWork() throws Exception {
        // Initialize the database
        listDfctTypeRepository.saveAndFlush(listDfctType);

        // Get all the listDfctTypeList where editor in DEFAULT_EDITOR or UPDATED_EDITOR
        defaultListDfctTypeShouldBeFound("editor.in=" + DEFAULT_EDITOR + "," + UPDATED_EDITOR);

        // Get all the listDfctTypeList where editor equals to UPDATED_EDITOR
        defaultListDfctTypeShouldNotBeFound("editor.in=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListDfctTypesByEditorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listDfctTypeRepository.saveAndFlush(listDfctType);

        // Get all the listDfctTypeList where editor is not null
        defaultListDfctTypeShouldBeFound("editor.specified=true");

        // Get all the listDfctTypeList where editor is null
        defaultListDfctTypeShouldNotBeFound("editor.specified=false");
    }

    @Test
    @Transactional
    public void getAllListDfctTypesByIdGroupIsEqualToSomething() throws Exception {
        // Get already existing entity
        ListDfctGroup idGroup = listDfctType.getIdGroup();
        listDfctTypeRepository.saveAndFlush(listDfctType);
        Long idGroupId = idGroup.getId();

        // Get all the listDfctTypeList where idGroup equals to idGroupId
        defaultListDfctTypeShouldBeFound("idGroupId.equals=" + idGroupId);

        // Get all the listDfctTypeList where idGroup equals to idGroupId + 1
        defaultListDfctTypeShouldNotBeFound("idGroupId.equals=" + (idGroupId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultListDfctTypeShouldBeFound(String filter) throws Exception {
        restListDfctTypeMockMvc.perform(get("/api/list-dfct-types?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listDfctType.getId().intValue())))
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
        restListDfctTypeMockMvc.perform(get("/api/list-dfct-types/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultListDfctTypeShouldNotBeFound(String filter) throws Exception {
        restListDfctTypeMockMvc.perform(get("/api/list-dfct-types?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restListDfctTypeMockMvc.perform(get("/api/list-dfct-types/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingListDfctType() throws Exception {
        // Get the listDfctType
        restListDfctTypeMockMvc.perform(get("/api/list-dfct-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateListDfctType() throws Exception {
        // Initialize the database
        listDfctTypeRepository.saveAndFlush(listDfctType);

        int databaseSizeBeforeUpdate = listDfctTypeRepository.findAll().size();

        // Update the listDfctType
        ListDfctType updatedListDfctType = listDfctTypeRepository.findById(listDfctType.getId()).get();
        // Disconnect from session so that the updates on updatedListDfctType are not directly saved in db
        em.detach(updatedListDfctType);
        updatedListDfctType
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        ListDfctTypeDTO listDfctTypeDTO = listDfctTypeMapper.toDto(updatedListDfctType);

        restListDfctTypeMockMvc.perform(put("/api/list-dfct-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listDfctTypeDTO)))
            .andExpect(status().isOk());

        // Validate the ListDfctType in the database
        List<ListDfctType> listDfctTypeList = listDfctTypeRepository.findAll();
        assertThat(listDfctTypeList).hasSize(databaseSizeBeforeUpdate);
        ListDfctType testListDfctType = listDfctTypeList.get(listDfctTypeList.size() - 1);
        assertThat(testListDfctType.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testListDfctType.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testListDfctType.getFullName()).isEqualTo(UPDATED_FULL_NAME);
        assertThat(testListDfctType.getIsCurrentFlag()).isEqualTo(UPDATED_IS_CURRENT_FLAG);
        assertThat(testListDfctType.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testListDfctType.getDateCreate()).isEqualTo(UPDATED_DATE_CREATE);
        assertThat(testListDfctType.getDateEdit()).isEqualTo(UPDATED_DATE_EDIT);
        assertThat(testListDfctType.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testListDfctType.getEditor()).isEqualTo(UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void updateNonExistingListDfctType() throws Exception {
        int databaseSizeBeforeUpdate = listDfctTypeRepository.findAll().size();

        // Create the ListDfctType
        ListDfctTypeDTO listDfctTypeDTO = listDfctTypeMapper.toDto(listDfctType);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restListDfctTypeMockMvc.perform(put("/api/list-dfct-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listDfctTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListDfctType in the database
        List<ListDfctType> listDfctTypeList = listDfctTypeRepository.findAll();
        assertThat(listDfctTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteListDfctType() throws Exception {
        // Initialize the database
        listDfctTypeRepository.saveAndFlush(listDfctType);

        int databaseSizeBeforeDelete = listDfctTypeRepository.findAll().size();

        // Delete the listDfctType
        restListDfctTypeMockMvc.perform(delete("/api/list-dfct-types/{id}", listDfctType.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<ListDfctType> listDfctTypeList = listDfctTypeRepository.findAll();
        assertThat(listDfctTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListDfctType.class);
        ListDfctType listDfctType1 = new ListDfctType();
        listDfctType1.setId(1L);
        ListDfctType listDfctType2 = new ListDfctType();
        listDfctType2.setId(listDfctType1.getId());
        assertThat(listDfctType1).isEqualTo(listDfctType2);
        listDfctType2.setId(2L);
        assertThat(listDfctType1).isNotEqualTo(listDfctType2);
        listDfctType1.setId(null);
        assertThat(listDfctType1).isNotEqualTo(listDfctType2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListDfctTypeDTO.class);
        ListDfctTypeDTO listDfctTypeDTO1 = new ListDfctTypeDTO();
        listDfctTypeDTO1.setId(1L);
        ListDfctTypeDTO listDfctTypeDTO2 = new ListDfctTypeDTO();
        assertThat(listDfctTypeDTO1).isNotEqualTo(listDfctTypeDTO2);
        listDfctTypeDTO2.setId(listDfctTypeDTO1.getId());
        assertThat(listDfctTypeDTO1).isEqualTo(listDfctTypeDTO2);
        listDfctTypeDTO2.setId(2L);
        assertThat(listDfctTypeDTO1).isNotEqualTo(listDfctTypeDTO2);
        listDfctTypeDTO1.setId(null);
        assertThat(listDfctTypeDTO1).isNotEqualTo(listDfctTypeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(listDfctTypeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(listDfctTypeMapper.fromId(null)).isNull();
    }
}
