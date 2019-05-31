package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.ListTeeType;
import io.github.jhipster.application.domain.TeeHist;
import io.github.jhipster.application.repository.ListTeeTypeRepository;
import io.github.jhipster.application.service.ListTeeTypeService;
import io.github.jhipster.application.service.dto.ListTeeTypeDTO;
import io.github.jhipster.application.service.mapper.ListTeeTypeMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.ListTeeTypeCriteria;
import io.github.jhipster.application.service.ListTeeTypeQueryService;

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
 * Integration tests for the {@Link ListTeeTypeResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class ListTeeTypeResourceIT {

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
    private ListTeeTypeRepository listTeeTypeRepository;

    @Autowired
    private ListTeeTypeMapper listTeeTypeMapper;

    @Autowired
    private ListTeeTypeService listTeeTypeService;

    @Autowired
    private ListTeeTypeQueryService listTeeTypeQueryService;

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

    private MockMvc restListTeeTypeMockMvc;

    private ListTeeType listTeeType;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ListTeeTypeResource listTeeTypeResource = new ListTeeTypeResource(listTeeTypeService, listTeeTypeQueryService);
        this.restListTeeTypeMockMvc = MockMvcBuilders.standaloneSetup(listTeeTypeResource)
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
    public static ListTeeType createEntity(EntityManager em) {
        ListTeeType listTeeType = new ListTeeType()
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME)
            .fullName(DEFAULT_FULL_NAME)
            .isCurrentFlag(DEFAULT_IS_CURRENT_FLAG)
            .description(DEFAULT_DESCRIPTION)
            .dateCreate(DEFAULT_DATE_CREATE)
            .dateEdit(DEFAULT_DATE_EDIT)
            .creator(DEFAULT_CREATOR)
            .editor(DEFAULT_EDITOR);
        return listTeeType;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ListTeeType createUpdatedEntity(EntityManager em) {
        ListTeeType listTeeType = new ListTeeType()
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        return listTeeType;
    }

    @BeforeEach
    public void initTest() {
        listTeeType = createEntity(em);
    }

    @Test
    @Transactional
    public void createListTeeType() throws Exception {
        int databaseSizeBeforeCreate = listTeeTypeRepository.findAll().size();

        // Create the ListTeeType
        ListTeeTypeDTO listTeeTypeDTO = listTeeTypeMapper.toDto(listTeeType);
        restListTeeTypeMockMvc.perform(post("/api/list-tee-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listTeeTypeDTO)))
            .andExpect(status().isCreated());

        // Validate the ListTeeType in the database
        List<ListTeeType> listTeeTypeList = listTeeTypeRepository.findAll();
        assertThat(listTeeTypeList).hasSize(databaseSizeBeforeCreate + 1);
        ListTeeType testListTeeType = listTeeTypeList.get(listTeeTypeList.size() - 1);
        assertThat(testListTeeType.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testListTeeType.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testListTeeType.getFullName()).isEqualTo(DEFAULT_FULL_NAME);
        assertThat(testListTeeType.getIsCurrentFlag()).isEqualTo(DEFAULT_IS_CURRENT_FLAG);
        assertThat(testListTeeType.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testListTeeType.getDateCreate()).isEqualTo(DEFAULT_DATE_CREATE);
        assertThat(testListTeeType.getDateEdit()).isEqualTo(DEFAULT_DATE_EDIT);
        assertThat(testListTeeType.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testListTeeType.getEditor()).isEqualTo(DEFAULT_EDITOR);
    }

    @Test
    @Transactional
    public void createListTeeTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = listTeeTypeRepository.findAll().size();

        // Create the ListTeeType with an existing ID
        listTeeType.setId(1L);
        ListTeeTypeDTO listTeeTypeDTO = listTeeTypeMapper.toDto(listTeeType);

        // An entity with an existing ID cannot be created, so this API call must fail
        restListTeeTypeMockMvc.perform(post("/api/list-tee-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listTeeTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListTeeType in the database
        List<ListTeeType> listTeeTypeList = listTeeTypeRepository.findAll();
        assertThat(listTeeTypeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = listTeeTypeRepository.findAll().size();
        // set the field null
        listTeeType.setCode(null);

        // Create the ListTeeType, which fails.
        ListTeeTypeDTO listTeeTypeDTO = listTeeTypeMapper.toDto(listTeeType);

        restListTeeTypeMockMvc.perform(post("/api/list-tee-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listTeeTypeDTO)))
            .andExpect(status().isBadRequest());

        List<ListTeeType> listTeeTypeList = listTeeTypeRepository.findAll();
        assertThat(listTeeTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listTeeTypeRepository.findAll().size();
        // set the field null
        listTeeType.setName(null);

        // Create the ListTeeType, which fails.
        ListTeeTypeDTO listTeeTypeDTO = listTeeTypeMapper.toDto(listTeeType);

        restListTeeTypeMockMvc.perform(post("/api/list-tee-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listTeeTypeDTO)))
            .andExpect(status().isBadRequest());

        List<ListTeeType> listTeeTypeList = listTeeTypeRepository.findAll();
        assertThat(listTeeTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFullNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listTeeTypeRepository.findAll().size();
        // set the field null
        listTeeType.setFullName(null);

        // Create the ListTeeType, which fails.
        ListTeeTypeDTO listTeeTypeDTO = listTeeTypeMapper.toDto(listTeeType);

        restListTeeTypeMockMvc.perform(post("/api/list-tee-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listTeeTypeDTO)))
            .andExpect(status().isBadRequest());

        List<ListTeeType> listTeeTypeList = listTeeTypeRepository.findAll();
        assertThat(listTeeTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsCurrentFlagIsRequired() throws Exception {
        int databaseSizeBeforeTest = listTeeTypeRepository.findAll().size();
        // set the field null
        listTeeType.setIsCurrentFlag(null);

        // Create the ListTeeType, which fails.
        ListTeeTypeDTO listTeeTypeDTO = listTeeTypeMapper.toDto(listTeeType);

        restListTeeTypeMockMvc.perform(post("/api/list-tee-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listTeeTypeDTO)))
            .andExpect(status().isBadRequest());

        List<ListTeeType> listTeeTypeList = listTeeTypeRepository.findAll();
        assertThat(listTeeTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllListTeeTypes() throws Exception {
        // Initialize the database
        listTeeTypeRepository.saveAndFlush(listTeeType);

        // Get all the listTeeTypeList
        restListTeeTypeMockMvc.perform(get("/api/list-tee-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listTeeType.getId().intValue())))
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
    public void getListTeeType() throws Exception {
        // Initialize the database
        listTeeTypeRepository.saveAndFlush(listTeeType);

        // Get the listTeeType
        restListTeeTypeMockMvc.perform(get("/api/list-tee-types/{id}", listTeeType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(listTeeType.getId().intValue()))
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
    public void getAllListTeeTypesByCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        listTeeTypeRepository.saveAndFlush(listTeeType);

        // Get all the listTeeTypeList where code equals to DEFAULT_CODE
        defaultListTeeTypeShouldBeFound("code.equals=" + DEFAULT_CODE);

        // Get all the listTeeTypeList where code equals to UPDATED_CODE
        defaultListTeeTypeShouldNotBeFound("code.equals=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListTeeTypesByCodeIsInShouldWork() throws Exception {
        // Initialize the database
        listTeeTypeRepository.saveAndFlush(listTeeType);

        // Get all the listTeeTypeList where code in DEFAULT_CODE or UPDATED_CODE
        defaultListTeeTypeShouldBeFound("code.in=" + DEFAULT_CODE + "," + UPDATED_CODE);

        // Get all the listTeeTypeList where code equals to UPDATED_CODE
        defaultListTeeTypeShouldNotBeFound("code.in=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListTeeTypesByCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        listTeeTypeRepository.saveAndFlush(listTeeType);

        // Get all the listTeeTypeList where code is not null
        defaultListTeeTypeShouldBeFound("code.specified=true");

        // Get all the listTeeTypeList where code is null
        defaultListTeeTypeShouldNotBeFound("code.specified=false");
    }

    @Test
    @Transactional
    public void getAllListTeeTypesByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listTeeTypeRepository.saveAndFlush(listTeeType);

        // Get all the listTeeTypeList where name equals to DEFAULT_NAME
        defaultListTeeTypeShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the listTeeTypeList where name equals to UPDATED_NAME
        defaultListTeeTypeShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListTeeTypesByNameIsInShouldWork() throws Exception {
        // Initialize the database
        listTeeTypeRepository.saveAndFlush(listTeeType);

        // Get all the listTeeTypeList where name in DEFAULT_NAME or UPDATED_NAME
        defaultListTeeTypeShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the listTeeTypeList where name equals to UPDATED_NAME
        defaultListTeeTypeShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListTeeTypesByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listTeeTypeRepository.saveAndFlush(listTeeType);

        // Get all the listTeeTypeList where name is not null
        defaultListTeeTypeShouldBeFound("name.specified=true");

        // Get all the listTeeTypeList where name is null
        defaultListTeeTypeShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    public void getAllListTeeTypesByFullNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listTeeTypeRepository.saveAndFlush(listTeeType);

        // Get all the listTeeTypeList where fullName equals to DEFAULT_FULL_NAME
        defaultListTeeTypeShouldBeFound("fullName.equals=" + DEFAULT_FULL_NAME);

        // Get all the listTeeTypeList where fullName equals to UPDATED_FULL_NAME
        defaultListTeeTypeShouldNotBeFound("fullName.equals=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListTeeTypesByFullNameIsInShouldWork() throws Exception {
        // Initialize the database
        listTeeTypeRepository.saveAndFlush(listTeeType);

        // Get all the listTeeTypeList where fullName in DEFAULT_FULL_NAME or UPDATED_FULL_NAME
        defaultListTeeTypeShouldBeFound("fullName.in=" + DEFAULT_FULL_NAME + "," + UPDATED_FULL_NAME);

        // Get all the listTeeTypeList where fullName equals to UPDATED_FULL_NAME
        defaultListTeeTypeShouldNotBeFound("fullName.in=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListTeeTypesByFullNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listTeeTypeRepository.saveAndFlush(listTeeType);

        // Get all the listTeeTypeList where fullName is not null
        defaultListTeeTypeShouldBeFound("fullName.specified=true");

        // Get all the listTeeTypeList where fullName is null
        defaultListTeeTypeShouldNotBeFound("fullName.specified=false");
    }

    @Test
    @Transactional
    public void getAllListTeeTypesByIsCurrentFlagIsEqualToSomething() throws Exception {
        // Initialize the database
        listTeeTypeRepository.saveAndFlush(listTeeType);

        // Get all the listTeeTypeList where isCurrentFlag equals to DEFAULT_IS_CURRENT_FLAG
        defaultListTeeTypeShouldBeFound("isCurrentFlag.equals=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listTeeTypeList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListTeeTypeShouldNotBeFound("isCurrentFlag.equals=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListTeeTypesByIsCurrentFlagIsInShouldWork() throws Exception {
        // Initialize the database
        listTeeTypeRepository.saveAndFlush(listTeeType);

        // Get all the listTeeTypeList where isCurrentFlag in DEFAULT_IS_CURRENT_FLAG or UPDATED_IS_CURRENT_FLAG
        defaultListTeeTypeShouldBeFound("isCurrentFlag.in=" + DEFAULT_IS_CURRENT_FLAG + "," + UPDATED_IS_CURRENT_FLAG);

        // Get all the listTeeTypeList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListTeeTypeShouldNotBeFound("isCurrentFlag.in=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListTeeTypesByIsCurrentFlagIsNullOrNotNull() throws Exception {
        // Initialize the database
        listTeeTypeRepository.saveAndFlush(listTeeType);

        // Get all the listTeeTypeList where isCurrentFlag is not null
        defaultListTeeTypeShouldBeFound("isCurrentFlag.specified=true");

        // Get all the listTeeTypeList where isCurrentFlag is null
        defaultListTeeTypeShouldNotBeFound("isCurrentFlag.specified=false");
    }

    @Test
    @Transactional
    public void getAllListTeeTypesByIsCurrentFlagIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        listTeeTypeRepository.saveAndFlush(listTeeType);

        // Get all the listTeeTypeList where isCurrentFlag greater than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListTeeTypeShouldBeFound("isCurrentFlag.greaterOrEqualThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listTeeTypeList where isCurrentFlag greater than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListTeeTypeShouldNotBeFound("isCurrentFlag.greaterOrEqualThan=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListTeeTypesByIsCurrentFlagIsLessThanSomething() throws Exception {
        // Initialize the database
        listTeeTypeRepository.saveAndFlush(listTeeType);

        // Get all the listTeeTypeList where isCurrentFlag less than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListTeeTypeShouldNotBeFound("isCurrentFlag.lessThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listTeeTypeList where isCurrentFlag less than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListTeeTypeShouldBeFound("isCurrentFlag.lessThan=" + UPDATED_IS_CURRENT_FLAG);
    }


    @Test
    @Transactional
    public void getAllListTeeTypesByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        listTeeTypeRepository.saveAndFlush(listTeeType);

        // Get all the listTeeTypeList where description equals to DEFAULT_DESCRIPTION
        defaultListTeeTypeShouldBeFound("description.equals=" + DEFAULT_DESCRIPTION);

        // Get all the listTeeTypeList where description equals to UPDATED_DESCRIPTION
        defaultListTeeTypeShouldNotBeFound("description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListTeeTypesByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        listTeeTypeRepository.saveAndFlush(listTeeType);

        // Get all the listTeeTypeList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
        defaultListTeeTypeShouldBeFound("description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION);

        // Get all the listTeeTypeList where description equals to UPDATED_DESCRIPTION
        defaultListTeeTypeShouldNotBeFound("description.in=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListTeeTypesByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        listTeeTypeRepository.saveAndFlush(listTeeType);

        // Get all the listTeeTypeList where description is not null
        defaultListTeeTypeShouldBeFound("description.specified=true");

        // Get all the listTeeTypeList where description is null
        defaultListTeeTypeShouldNotBeFound("description.specified=false");
    }

    @Test
    @Transactional
    public void getAllListTeeTypesByDateCreateIsEqualToSomething() throws Exception {
        // Initialize the database
        listTeeTypeRepository.saveAndFlush(listTeeType);

        // Get all the listTeeTypeList where dateCreate equals to DEFAULT_DATE_CREATE
        defaultListTeeTypeShouldBeFound("dateCreate.equals=" + DEFAULT_DATE_CREATE);

        // Get all the listTeeTypeList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListTeeTypeShouldNotBeFound("dateCreate.equals=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListTeeTypesByDateCreateIsInShouldWork() throws Exception {
        // Initialize the database
        listTeeTypeRepository.saveAndFlush(listTeeType);

        // Get all the listTeeTypeList where dateCreate in DEFAULT_DATE_CREATE or UPDATED_DATE_CREATE
        defaultListTeeTypeShouldBeFound("dateCreate.in=" + DEFAULT_DATE_CREATE + "," + UPDATED_DATE_CREATE);

        // Get all the listTeeTypeList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListTeeTypeShouldNotBeFound("dateCreate.in=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListTeeTypesByDateCreateIsNullOrNotNull() throws Exception {
        // Initialize the database
        listTeeTypeRepository.saveAndFlush(listTeeType);

        // Get all the listTeeTypeList where dateCreate is not null
        defaultListTeeTypeShouldBeFound("dateCreate.specified=true");

        // Get all the listTeeTypeList where dateCreate is null
        defaultListTeeTypeShouldNotBeFound("dateCreate.specified=false");
    }

    @Test
    @Transactional
    public void getAllListTeeTypesByDateEditIsEqualToSomething() throws Exception {
        // Initialize the database
        listTeeTypeRepository.saveAndFlush(listTeeType);

        // Get all the listTeeTypeList where dateEdit equals to DEFAULT_DATE_EDIT
        defaultListTeeTypeShouldBeFound("dateEdit.equals=" + DEFAULT_DATE_EDIT);

        // Get all the listTeeTypeList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListTeeTypeShouldNotBeFound("dateEdit.equals=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListTeeTypesByDateEditIsInShouldWork() throws Exception {
        // Initialize the database
        listTeeTypeRepository.saveAndFlush(listTeeType);

        // Get all the listTeeTypeList where dateEdit in DEFAULT_DATE_EDIT or UPDATED_DATE_EDIT
        defaultListTeeTypeShouldBeFound("dateEdit.in=" + DEFAULT_DATE_EDIT + "," + UPDATED_DATE_EDIT);

        // Get all the listTeeTypeList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListTeeTypeShouldNotBeFound("dateEdit.in=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListTeeTypesByDateEditIsNullOrNotNull() throws Exception {
        // Initialize the database
        listTeeTypeRepository.saveAndFlush(listTeeType);

        // Get all the listTeeTypeList where dateEdit is not null
        defaultListTeeTypeShouldBeFound("dateEdit.specified=true");

        // Get all the listTeeTypeList where dateEdit is null
        defaultListTeeTypeShouldNotBeFound("dateEdit.specified=false");
    }

    @Test
    @Transactional
    public void getAllListTeeTypesByCreatorIsEqualToSomething() throws Exception {
        // Initialize the database
        listTeeTypeRepository.saveAndFlush(listTeeType);

        // Get all the listTeeTypeList where creator equals to DEFAULT_CREATOR
        defaultListTeeTypeShouldBeFound("creator.equals=" + DEFAULT_CREATOR);

        // Get all the listTeeTypeList where creator equals to UPDATED_CREATOR
        defaultListTeeTypeShouldNotBeFound("creator.equals=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListTeeTypesByCreatorIsInShouldWork() throws Exception {
        // Initialize the database
        listTeeTypeRepository.saveAndFlush(listTeeType);

        // Get all the listTeeTypeList where creator in DEFAULT_CREATOR or UPDATED_CREATOR
        defaultListTeeTypeShouldBeFound("creator.in=" + DEFAULT_CREATOR + "," + UPDATED_CREATOR);

        // Get all the listTeeTypeList where creator equals to UPDATED_CREATOR
        defaultListTeeTypeShouldNotBeFound("creator.in=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListTeeTypesByCreatorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listTeeTypeRepository.saveAndFlush(listTeeType);

        // Get all the listTeeTypeList where creator is not null
        defaultListTeeTypeShouldBeFound("creator.specified=true");

        // Get all the listTeeTypeList where creator is null
        defaultListTeeTypeShouldNotBeFound("creator.specified=false");
    }

    @Test
    @Transactional
    public void getAllListTeeTypesByEditorIsEqualToSomething() throws Exception {
        // Initialize the database
        listTeeTypeRepository.saveAndFlush(listTeeType);

        // Get all the listTeeTypeList where editor equals to DEFAULT_EDITOR
        defaultListTeeTypeShouldBeFound("editor.equals=" + DEFAULT_EDITOR);

        // Get all the listTeeTypeList where editor equals to UPDATED_EDITOR
        defaultListTeeTypeShouldNotBeFound("editor.equals=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListTeeTypesByEditorIsInShouldWork() throws Exception {
        // Initialize the database
        listTeeTypeRepository.saveAndFlush(listTeeType);

        // Get all the listTeeTypeList where editor in DEFAULT_EDITOR or UPDATED_EDITOR
        defaultListTeeTypeShouldBeFound("editor.in=" + DEFAULT_EDITOR + "," + UPDATED_EDITOR);

        // Get all the listTeeTypeList where editor equals to UPDATED_EDITOR
        defaultListTeeTypeShouldNotBeFound("editor.in=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListTeeTypesByEditorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listTeeTypeRepository.saveAndFlush(listTeeType);

        // Get all the listTeeTypeList where editor is not null
        defaultListTeeTypeShouldBeFound("editor.specified=true");

        // Get all the listTeeTypeList where editor is null
        defaultListTeeTypeShouldNotBeFound("editor.specified=false");
    }

    @Test
    @Transactional
    public void getAllListTeeTypesByTeeHistIsEqualToSomething() throws Exception {
        // Initialize the database
        TeeHist teeHist = TeeHistResourceIT.createEntity(em);
        em.persist(teeHist);
        em.flush();
        listTeeType.addTeeHist(teeHist);
        listTeeTypeRepository.saveAndFlush(listTeeType);
        Long teeHistId = teeHist.getId();

        // Get all the listTeeTypeList where teeHist equals to teeHistId
        defaultListTeeTypeShouldBeFound("teeHistId.equals=" + teeHistId);

        // Get all the listTeeTypeList where teeHist equals to teeHistId + 1
        defaultListTeeTypeShouldNotBeFound("teeHistId.equals=" + (teeHistId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultListTeeTypeShouldBeFound(String filter) throws Exception {
        restListTeeTypeMockMvc.perform(get("/api/list-tee-types?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listTeeType.getId().intValue())))
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
        restListTeeTypeMockMvc.perform(get("/api/list-tee-types/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultListTeeTypeShouldNotBeFound(String filter) throws Exception {
        restListTeeTypeMockMvc.perform(get("/api/list-tee-types?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restListTeeTypeMockMvc.perform(get("/api/list-tee-types/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingListTeeType() throws Exception {
        // Get the listTeeType
        restListTeeTypeMockMvc.perform(get("/api/list-tee-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateListTeeType() throws Exception {
        // Initialize the database
        listTeeTypeRepository.saveAndFlush(listTeeType);

        int databaseSizeBeforeUpdate = listTeeTypeRepository.findAll().size();

        // Update the listTeeType
        ListTeeType updatedListTeeType = listTeeTypeRepository.findById(listTeeType.getId()).get();
        // Disconnect from session so that the updates on updatedListTeeType are not directly saved in db
        em.detach(updatedListTeeType);
        updatedListTeeType
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        ListTeeTypeDTO listTeeTypeDTO = listTeeTypeMapper.toDto(updatedListTeeType);

        restListTeeTypeMockMvc.perform(put("/api/list-tee-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listTeeTypeDTO)))
            .andExpect(status().isOk());

        // Validate the ListTeeType in the database
        List<ListTeeType> listTeeTypeList = listTeeTypeRepository.findAll();
        assertThat(listTeeTypeList).hasSize(databaseSizeBeforeUpdate);
        ListTeeType testListTeeType = listTeeTypeList.get(listTeeTypeList.size() - 1);
        assertThat(testListTeeType.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testListTeeType.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testListTeeType.getFullName()).isEqualTo(UPDATED_FULL_NAME);
        assertThat(testListTeeType.getIsCurrentFlag()).isEqualTo(UPDATED_IS_CURRENT_FLAG);
        assertThat(testListTeeType.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testListTeeType.getDateCreate()).isEqualTo(UPDATED_DATE_CREATE);
        assertThat(testListTeeType.getDateEdit()).isEqualTo(UPDATED_DATE_EDIT);
        assertThat(testListTeeType.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testListTeeType.getEditor()).isEqualTo(UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void updateNonExistingListTeeType() throws Exception {
        int databaseSizeBeforeUpdate = listTeeTypeRepository.findAll().size();

        // Create the ListTeeType
        ListTeeTypeDTO listTeeTypeDTO = listTeeTypeMapper.toDto(listTeeType);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restListTeeTypeMockMvc.perform(put("/api/list-tee-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listTeeTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListTeeType in the database
        List<ListTeeType> listTeeTypeList = listTeeTypeRepository.findAll();
        assertThat(listTeeTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteListTeeType() throws Exception {
        // Initialize the database
        listTeeTypeRepository.saveAndFlush(listTeeType);

        int databaseSizeBeforeDelete = listTeeTypeRepository.findAll().size();

        // Delete the listTeeType
        restListTeeTypeMockMvc.perform(delete("/api/list-tee-types/{id}", listTeeType.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<ListTeeType> listTeeTypeList = listTeeTypeRepository.findAll();
        assertThat(listTeeTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListTeeType.class);
        ListTeeType listTeeType1 = new ListTeeType();
        listTeeType1.setId(1L);
        ListTeeType listTeeType2 = new ListTeeType();
        listTeeType2.setId(listTeeType1.getId());
        assertThat(listTeeType1).isEqualTo(listTeeType2);
        listTeeType2.setId(2L);
        assertThat(listTeeType1).isNotEqualTo(listTeeType2);
        listTeeType1.setId(null);
        assertThat(listTeeType1).isNotEqualTo(listTeeType2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListTeeTypeDTO.class);
        ListTeeTypeDTO listTeeTypeDTO1 = new ListTeeTypeDTO();
        listTeeTypeDTO1.setId(1L);
        ListTeeTypeDTO listTeeTypeDTO2 = new ListTeeTypeDTO();
        assertThat(listTeeTypeDTO1).isNotEqualTo(listTeeTypeDTO2);
        listTeeTypeDTO2.setId(listTeeTypeDTO1.getId());
        assertThat(listTeeTypeDTO1).isEqualTo(listTeeTypeDTO2);
        listTeeTypeDTO2.setId(2L);
        assertThat(listTeeTypeDTO1).isNotEqualTo(listTeeTypeDTO2);
        listTeeTypeDTO1.setId(null);
        assertThat(listTeeTypeDTO1).isNotEqualTo(listTeeTypeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(listTeeTypeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(listTeeTypeMapper.fromId(null)).isNull();
    }
}
