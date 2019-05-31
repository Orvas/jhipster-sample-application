package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.MetaList;
import io.github.jhipster.application.repository.MetaListRepository;
import io.github.jhipster.application.service.MetaListService;
import io.github.jhipster.application.service.dto.MetaListDTO;
import io.github.jhipster.application.service.mapper.MetaListMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.MetaListCriteria;
import io.github.jhipster.application.service.MetaListQueryService;

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
 * Integration tests for the {@Link MetaListResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class MetaListResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SCHEMA_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SCHEMA_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_TABLE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_TABLE_NAME = "BBBBBBBBBB";

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
    private MetaListRepository metaListRepository;

    @Autowired
    private MetaListMapper metaListMapper;

    @Autowired
    private MetaListService metaListService;

    @Autowired
    private MetaListQueryService metaListQueryService;

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

    private MockMvc restMetaListMockMvc;

    private MetaList metaList;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MetaListResource metaListResource = new MetaListResource(metaListService, metaListQueryService);
        this.restMetaListMockMvc = MockMvcBuilders.standaloneSetup(metaListResource)
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
    public static MetaList createEntity(EntityManager em) {
        MetaList metaList = new MetaList()
            .name(DEFAULT_NAME)
            .schemaName(DEFAULT_SCHEMA_NAME)
            .tableName(DEFAULT_TABLE_NAME)
            .isCurrentFlag(DEFAULT_IS_CURRENT_FLAG)
            .description(DEFAULT_DESCRIPTION)
            .dateCreate(DEFAULT_DATE_CREATE)
            .dateEdit(DEFAULT_DATE_EDIT)
            .creator(DEFAULT_CREATOR)
            .editor(DEFAULT_EDITOR);
        return metaList;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MetaList createUpdatedEntity(EntityManager em) {
        MetaList metaList = new MetaList()
            .name(UPDATED_NAME)
            .schemaName(UPDATED_SCHEMA_NAME)
            .tableName(UPDATED_TABLE_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        return metaList;
    }

    @BeforeEach
    public void initTest() {
        metaList = createEntity(em);
    }

    @Test
    @Transactional
    public void createMetaList() throws Exception {
        int databaseSizeBeforeCreate = metaListRepository.findAll().size();

        // Create the MetaList
        MetaListDTO metaListDTO = metaListMapper.toDto(metaList);
        restMetaListMockMvc.perform(post("/api/meta-lists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(metaListDTO)))
            .andExpect(status().isCreated());

        // Validate the MetaList in the database
        List<MetaList> metaListList = metaListRepository.findAll();
        assertThat(metaListList).hasSize(databaseSizeBeforeCreate + 1);
        MetaList testMetaList = metaListList.get(metaListList.size() - 1);
        assertThat(testMetaList.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testMetaList.getSchemaName()).isEqualTo(DEFAULT_SCHEMA_NAME);
        assertThat(testMetaList.getTableName()).isEqualTo(DEFAULT_TABLE_NAME);
        assertThat(testMetaList.getIsCurrentFlag()).isEqualTo(DEFAULT_IS_CURRENT_FLAG);
        assertThat(testMetaList.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testMetaList.getDateCreate()).isEqualTo(DEFAULT_DATE_CREATE);
        assertThat(testMetaList.getDateEdit()).isEqualTo(DEFAULT_DATE_EDIT);
        assertThat(testMetaList.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testMetaList.getEditor()).isEqualTo(DEFAULT_EDITOR);
    }

    @Test
    @Transactional
    public void createMetaListWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = metaListRepository.findAll().size();

        // Create the MetaList with an existing ID
        metaList.setId(1L);
        MetaListDTO metaListDTO = metaListMapper.toDto(metaList);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMetaListMockMvc.perform(post("/api/meta-lists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(metaListDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MetaList in the database
        List<MetaList> metaListList = metaListRepository.findAll();
        assertThat(metaListList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = metaListRepository.findAll().size();
        // set the field null
        metaList.setName(null);

        // Create the MetaList, which fails.
        MetaListDTO metaListDTO = metaListMapper.toDto(metaList);

        restMetaListMockMvc.perform(post("/api/meta-lists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(metaListDTO)))
            .andExpect(status().isBadRequest());

        List<MetaList> metaListList = metaListRepository.findAll();
        assertThat(metaListList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSchemaNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = metaListRepository.findAll().size();
        // set the field null
        metaList.setSchemaName(null);

        // Create the MetaList, which fails.
        MetaListDTO metaListDTO = metaListMapper.toDto(metaList);

        restMetaListMockMvc.perform(post("/api/meta-lists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(metaListDTO)))
            .andExpect(status().isBadRequest());

        List<MetaList> metaListList = metaListRepository.findAll();
        assertThat(metaListList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTableNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = metaListRepository.findAll().size();
        // set the field null
        metaList.setTableName(null);

        // Create the MetaList, which fails.
        MetaListDTO metaListDTO = metaListMapper.toDto(metaList);

        restMetaListMockMvc.perform(post("/api/meta-lists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(metaListDTO)))
            .andExpect(status().isBadRequest());

        List<MetaList> metaListList = metaListRepository.findAll();
        assertThat(metaListList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsCurrentFlagIsRequired() throws Exception {
        int databaseSizeBeforeTest = metaListRepository.findAll().size();
        // set the field null
        metaList.setIsCurrentFlag(null);

        // Create the MetaList, which fails.
        MetaListDTO metaListDTO = metaListMapper.toDto(metaList);

        restMetaListMockMvc.perform(post("/api/meta-lists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(metaListDTO)))
            .andExpect(status().isBadRequest());

        List<MetaList> metaListList = metaListRepository.findAll();
        assertThat(metaListList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMetaLists() throws Exception {
        // Initialize the database
        metaListRepository.saveAndFlush(metaList);

        // Get all the metaListList
        restMetaListMockMvc.perform(get("/api/meta-lists?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(metaList.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].schemaName").value(hasItem(DEFAULT_SCHEMA_NAME.toString())))
            .andExpect(jsonPath("$.[*].tableName").value(hasItem(DEFAULT_TABLE_NAME.toString())))
            .andExpect(jsonPath("$.[*].isCurrentFlag").value(hasItem(DEFAULT_IS_CURRENT_FLAG)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].dateCreate").value(hasItem(DEFAULT_DATE_CREATE.toString())))
            .andExpect(jsonPath("$.[*].dateEdit").value(hasItem(DEFAULT_DATE_EDIT.toString())))
            .andExpect(jsonPath("$.[*].creator").value(hasItem(DEFAULT_CREATOR.toString())))
            .andExpect(jsonPath("$.[*].editor").value(hasItem(DEFAULT_EDITOR.toString())));
    }
    
    @Test
    @Transactional
    public void getMetaList() throws Exception {
        // Initialize the database
        metaListRepository.saveAndFlush(metaList);

        // Get the metaList
        restMetaListMockMvc.perform(get("/api/meta-lists/{id}", metaList.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(metaList.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.schemaName").value(DEFAULT_SCHEMA_NAME.toString()))
            .andExpect(jsonPath("$.tableName").value(DEFAULT_TABLE_NAME.toString()))
            .andExpect(jsonPath("$.isCurrentFlag").value(DEFAULT_IS_CURRENT_FLAG))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.dateCreate").value(DEFAULT_DATE_CREATE.toString()))
            .andExpect(jsonPath("$.dateEdit").value(DEFAULT_DATE_EDIT.toString()))
            .andExpect(jsonPath("$.creator").value(DEFAULT_CREATOR.toString()))
            .andExpect(jsonPath("$.editor").value(DEFAULT_EDITOR.toString()));
    }

    @Test
    @Transactional
    public void getAllMetaListsByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        metaListRepository.saveAndFlush(metaList);

        // Get all the metaListList where name equals to DEFAULT_NAME
        defaultMetaListShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the metaListList where name equals to UPDATED_NAME
        defaultMetaListShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllMetaListsByNameIsInShouldWork() throws Exception {
        // Initialize the database
        metaListRepository.saveAndFlush(metaList);

        // Get all the metaListList where name in DEFAULT_NAME or UPDATED_NAME
        defaultMetaListShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the metaListList where name equals to UPDATED_NAME
        defaultMetaListShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllMetaListsByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        metaListRepository.saveAndFlush(metaList);

        // Get all the metaListList where name is not null
        defaultMetaListShouldBeFound("name.specified=true");

        // Get all the metaListList where name is null
        defaultMetaListShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    public void getAllMetaListsBySchemaNameIsEqualToSomething() throws Exception {
        // Initialize the database
        metaListRepository.saveAndFlush(metaList);

        // Get all the metaListList where schemaName equals to DEFAULT_SCHEMA_NAME
        defaultMetaListShouldBeFound("schemaName.equals=" + DEFAULT_SCHEMA_NAME);

        // Get all the metaListList where schemaName equals to UPDATED_SCHEMA_NAME
        defaultMetaListShouldNotBeFound("schemaName.equals=" + UPDATED_SCHEMA_NAME);
    }

    @Test
    @Transactional
    public void getAllMetaListsBySchemaNameIsInShouldWork() throws Exception {
        // Initialize the database
        metaListRepository.saveAndFlush(metaList);

        // Get all the metaListList where schemaName in DEFAULT_SCHEMA_NAME or UPDATED_SCHEMA_NAME
        defaultMetaListShouldBeFound("schemaName.in=" + DEFAULT_SCHEMA_NAME + "," + UPDATED_SCHEMA_NAME);

        // Get all the metaListList where schemaName equals to UPDATED_SCHEMA_NAME
        defaultMetaListShouldNotBeFound("schemaName.in=" + UPDATED_SCHEMA_NAME);
    }

    @Test
    @Transactional
    public void getAllMetaListsBySchemaNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        metaListRepository.saveAndFlush(metaList);

        // Get all the metaListList where schemaName is not null
        defaultMetaListShouldBeFound("schemaName.specified=true");

        // Get all the metaListList where schemaName is null
        defaultMetaListShouldNotBeFound("schemaName.specified=false");
    }

    @Test
    @Transactional
    public void getAllMetaListsByTableNameIsEqualToSomething() throws Exception {
        // Initialize the database
        metaListRepository.saveAndFlush(metaList);

        // Get all the metaListList where tableName equals to DEFAULT_TABLE_NAME
        defaultMetaListShouldBeFound("tableName.equals=" + DEFAULT_TABLE_NAME);

        // Get all the metaListList where tableName equals to UPDATED_TABLE_NAME
        defaultMetaListShouldNotBeFound("tableName.equals=" + UPDATED_TABLE_NAME);
    }

    @Test
    @Transactional
    public void getAllMetaListsByTableNameIsInShouldWork() throws Exception {
        // Initialize the database
        metaListRepository.saveAndFlush(metaList);

        // Get all the metaListList where tableName in DEFAULT_TABLE_NAME or UPDATED_TABLE_NAME
        defaultMetaListShouldBeFound("tableName.in=" + DEFAULT_TABLE_NAME + "," + UPDATED_TABLE_NAME);

        // Get all the metaListList where tableName equals to UPDATED_TABLE_NAME
        defaultMetaListShouldNotBeFound("tableName.in=" + UPDATED_TABLE_NAME);
    }

    @Test
    @Transactional
    public void getAllMetaListsByTableNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        metaListRepository.saveAndFlush(metaList);

        // Get all the metaListList where tableName is not null
        defaultMetaListShouldBeFound("tableName.specified=true");

        // Get all the metaListList where tableName is null
        defaultMetaListShouldNotBeFound("tableName.specified=false");
    }

    @Test
    @Transactional
    public void getAllMetaListsByIsCurrentFlagIsEqualToSomething() throws Exception {
        // Initialize the database
        metaListRepository.saveAndFlush(metaList);

        // Get all the metaListList where isCurrentFlag equals to DEFAULT_IS_CURRENT_FLAG
        defaultMetaListShouldBeFound("isCurrentFlag.equals=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the metaListList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultMetaListShouldNotBeFound("isCurrentFlag.equals=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllMetaListsByIsCurrentFlagIsInShouldWork() throws Exception {
        // Initialize the database
        metaListRepository.saveAndFlush(metaList);

        // Get all the metaListList where isCurrentFlag in DEFAULT_IS_CURRENT_FLAG or UPDATED_IS_CURRENT_FLAG
        defaultMetaListShouldBeFound("isCurrentFlag.in=" + DEFAULT_IS_CURRENT_FLAG + "," + UPDATED_IS_CURRENT_FLAG);

        // Get all the metaListList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultMetaListShouldNotBeFound("isCurrentFlag.in=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllMetaListsByIsCurrentFlagIsNullOrNotNull() throws Exception {
        // Initialize the database
        metaListRepository.saveAndFlush(metaList);

        // Get all the metaListList where isCurrentFlag is not null
        defaultMetaListShouldBeFound("isCurrentFlag.specified=true");

        // Get all the metaListList where isCurrentFlag is null
        defaultMetaListShouldNotBeFound("isCurrentFlag.specified=false");
    }

    @Test
    @Transactional
    public void getAllMetaListsByIsCurrentFlagIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        metaListRepository.saveAndFlush(metaList);

        // Get all the metaListList where isCurrentFlag greater than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultMetaListShouldBeFound("isCurrentFlag.greaterOrEqualThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the metaListList where isCurrentFlag greater than or equals to UPDATED_IS_CURRENT_FLAG
        defaultMetaListShouldNotBeFound("isCurrentFlag.greaterOrEqualThan=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllMetaListsByIsCurrentFlagIsLessThanSomething() throws Exception {
        // Initialize the database
        metaListRepository.saveAndFlush(metaList);

        // Get all the metaListList where isCurrentFlag less than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultMetaListShouldNotBeFound("isCurrentFlag.lessThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the metaListList where isCurrentFlag less than or equals to UPDATED_IS_CURRENT_FLAG
        defaultMetaListShouldBeFound("isCurrentFlag.lessThan=" + UPDATED_IS_CURRENT_FLAG);
    }


    @Test
    @Transactional
    public void getAllMetaListsByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        metaListRepository.saveAndFlush(metaList);

        // Get all the metaListList where description equals to DEFAULT_DESCRIPTION
        defaultMetaListShouldBeFound("description.equals=" + DEFAULT_DESCRIPTION);

        // Get all the metaListList where description equals to UPDATED_DESCRIPTION
        defaultMetaListShouldNotBeFound("description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllMetaListsByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        metaListRepository.saveAndFlush(metaList);

        // Get all the metaListList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
        defaultMetaListShouldBeFound("description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION);

        // Get all the metaListList where description equals to UPDATED_DESCRIPTION
        defaultMetaListShouldNotBeFound("description.in=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllMetaListsByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        metaListRepository.saveAndFlush(metaList);

        // Get all the metaListList where description is not null
        defaultMetaListShouldBeFound("description.specified=true");

        // Get all the metaListList where description is null
        defaultMetaListShouldNotBeFound("description.specified=false");
    }

    @Test
    @Transactional
    public void getAllMetaListsByDateCreateIsEqualToSomething() throws Exception {
        // Initialize the database
        metaListRepository.saveAndFlush(metaList);

        // Get all the metaListList where dateCreate equals to DEFAULT_DATE_CREATE
        defaultMetaListShouldBeFound("dateCreate.equals=" + DEFAULT_DATE_CREATE);

        // Get all the metaListList where dateCreate equals to UPDATED_DATE_CREATE
        defaultMetaListShouldNotBeFound("dateCreate.equals=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllMetaListsByDateCreateIsInShouldWork() throws Exception {
        // Initialize the database
        metaListRepository.saveAndFlush(metaList);

        // Get all the metaListList where dateCreate in DEFAULT_DATE_CREATE or UPDATED_DATE_CREATE
        defaultMetaListShouldBeFound("dateCreate.in=" + DEFAULT_DATE_CREATE + "," + UPDATED_DATE_CREATE);

        // Get all the metaListList where dateCreate equals to UPDATED_DATE_CREATE
        defaultMetaListShouldNotBeFound("dateCreate.in=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllMetaListsByDateCreateIsNullOrNotNull() throws Exception {
        // Initialize the database
        metaListRepository.saveAndFlush(metaList);

        // Get all the metaListList where dateCreate is not null
        defaultMetaListShouldBeFound("dateCreate.specified=true");

        // Get all the metaListList where dateCreate is null
        defaultMetaListShouldNotBeFound("dateCreate.specified=false");
    }

    @Test
    @Transactional
    public void getAllMetaListsByDateEditIsEqualToSomething() throws Exception {
        // Initialize the database
        metaListRepository.saveAndFlush(metaList);

        // Get all the metaListList where dateEdit equals to DEFAULT_DATE_EDIT
        defaultMetaListShouldBeFound("dateEdit.equals=" + DEFAULT_DATE_EDIT);

        // Get all the metaListList where dateEdit equals to UPDATED_DATE_EDIT
        defaultMetaListShouldNotBeFound("dateEdit.equals=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllMetaListsByDateEditIsInShouldWork() throws Exception {
        // Initialize the database
        metaListRepository.saveAndFlush(metaList);

        // Get all the metaListList where dateEdit in DEFAULT_DATE_EDIT or UPDATED_DATE_EDIT
        defaultMetaListShouldBeFound("dateEdit.in=" + DEFAULT_DATE_EDIT + "," + UPDATED_DATE_EDIT);

        // Get all the metaListList where dateEdit equals to UPDATED_DATE_EDIT
        defaultMetaListShouldNotBeFound("dateEdit.in=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllMetaListsByDateEditIsNullOrNotNull() throws Exception {
        // Initialize the database
        metaListRepository.saveAndFlush(metaList);

        // Get all the metaListList where dateEdit is not null
        defaultMetaListShouldBeFound("dateEdit.specified=true");

        // Get all the metaListList where dateEdit is null
        defaultMetaListShouldNotBeFound("dateEdit.specified=false");
    }

    @Test
    @Transactional
    public void getAllMetaListsByCreatorIsEqualToSomething() throws Exception {
        // Initialize the database
        metaListRepository.saveAndFlush(metaList);

        // Get all the metaListList where creator equals to DEFAULT_CREATOR
        defaultMetaListShouldBeFound("creator.equals=" + DEFAULT_CREATOR);

        // Get all the metaListList where creator equals to UPDATED_CREATOR
        defaultMetaListShouldNotBeFound("creator.equals=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllMetaListsByCreatorIsInShouldWork() throws Exception {
        // Initialize the database
        metaListRepository.saveAndFlush(metaList);

        // Get all the metaListList where creator in DEFAULT_CREATOR or UPDATED_CREATOR
        defaultMetaListShouldBeFound("creator.in=" + DEFAULT_CREATOR + "," + UPDATED_CREATOR);

        // Get all the metaListList where creator equals to UPDATED_CREATOR
        defaultMetaListShouldNotBeFound("creator.in=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllMetaListsByCreatorIsNullOrNotNull() throws Exception {
        // Initialize the database
        metaListRepository.saveAndFlush(metaList);

        // Get all the metaListList where creator is not null
        defaultMetaListShouldBeFound("creator.specified=true");

        // Get all the metaListList where creator is null
        defaultMetaListShouldNotBeFound("creator.specified=false");
    }

    @Test
    @Transactional
    public void getAllMetaListsByEditorIsEqualToSomething() throws Exception {
        // Initialize the database
        metaListRepository.saveAndFlush(metaList);

        // Get all the metaListList where editor equals to DEFAULT_EDITOR
        defaultMetaListShouldBeFound("editor.equals=" + DEFAULT_EDITOR);

        // Get all the metaListList where editor equals to UPDATED_EDITOR
        defaultMetaListShouldNotBeFound("editor.equals=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllMetaListsByEditorIsInShouldWork() throws Exception {
        // Initialize the database
        metaListRepository.saveAndFlush(metaList);

        // Get all the metaListList where editor in DEFAULT_EDITOR or UPDATED_EDITOR
        defaultMetaListShouldBeFound("editor.in=" + DEFAULT_EDITOR + "," + UPDATED_EDITOR);

        // Get all the metaListList where editor equals to UPDATED_EDITOR
        defaultMetaListShouldNotBeFound("editor.in=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllMetaListsByEditorIsNullOrNotNull() throws Exception {
        // Initialize the database
        metaListRepository.saveAndFlush(metaList);

        // Get all the metaListList where editor is not null
        defaultMetaListShouldBeFound("editor.specified=true");

        // Get all the metaListList where editor is null
        defaultMetaListShouldNotBeFound("editor.specified=false");
    }
    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMetaListShouldBeFound(String filter) throws Exception {
        restMetaListMockMvc.perform(get("/api/meta-lists?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(metaList.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].schemaName").value(hasItem(DEFAULT_SCHEMA_NAME)))
            .andExpect(jsonPath("$.[*].tableName").value(hasItem(DEFAULT_TABLE_NAME)))
            .andExpect(jsonPath("$.[*].isCurrentFlag").value(hasItem(DEFAULT_IS_CURRENT_FLAG)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].dateCreate").value(hasItem(DEFAULT_DATE_CREATE.toString())))
            .andExpect(jsonPath("$.[*].dateEdit").value(hasItem(DEFAULT_DATE_EDIT.toString())))
            .andExpect(jsonPath("$.[*].creator").value(hasItem(DEFAULT_CREATOR)))
            .andExpect(jsonPath("$.[*].editor").value(hasItem(DEFAULT_EDITOR)));

        // Check, that the count call also returns 1
        restMetaListMockMvc.perform(get("/api/meta-lists/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMetaListShouldNotBeFound(String filter) throws Exception {
        restMetaListMockMvc.perform(get("/api/meta-lists?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMetaListMockMvc.perform(get("/api/meta-lists/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMetaList() throws Exception {
        // Get the metaList
        restMetaListMockMvc.perform(get("/api/meta-lists/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMetaList() throws Exception {
        // Initialize the database
        metaListRepository.saveAndFlush(metaList);

        int databaseSizeBeforeUpdate = metaListRepository.findAll().size();

        // Update the metaList
        MetaList updatedMetaList = metaListRepository.findById(metaList.getId()).get();
        // Disconnect from session so that the updates on updatedMetaList are not directly saved in db
        em.detach(updatedMetaList);
        updatedMetaList
            .name(UPDATED_NAME)
            .schemaName(UPDATED_SCHEMA_NAME)
            .tableName(UPDATED_TABLE_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        MetaListDTO metaListDTO = metaListMapper.toDto(updatedMetaList);

        restMetaListMockMvc.perform(put("/api/meta-lists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(metaListDTO)))
            .andExpect(status().isOk());

        // Validate the MetaList in the database
        List<MetaList> metaListList = metaListRepository.findAll();
        assertThat(metaListList).hasSize(databaseSizeBeforeUpdate);
        MetaList testMetaList = metaListList.get(metaListList.size() - 1);
        assertThat(testMetaList.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMetaList.getSchemaName()).isEqualTo(UPDATED_SCHEMA_NAME);
        assertThat(testMetaList.getTableName()).isEqualTo(UPDATED_TABLE_NAME);
        assertThat(testMetaList.getIsCurrentFlag()).isEqualTo(UPDATED_IS_CURRENT_FLAG);
        assertThat(testMetaList.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testMetaList.getDateCreate()).isEqualTo(UPDATED_DATE_CREATE);
        assertThat(testMetaList.getDateEdit()).isEqualTo(UPDATED_DATE_EDIT);
        assertThat(testMetaList.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testMetaList.getEditor()).isEqualTo(UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void updateNonExistingMetaList() throws Exception {
        int databaseSizeBeforeUpdate = metaListRepository.findAll().size();

        // Create the MetaList
        MetaListDTO metaListDTO = metaListMapper.toDto(metaList);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMetaListMockMvc.perform(put("/api/meta-lists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(metaListDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MetaList in the database
        List<MetaList> metaListList = metaListRepository.findAll();
        assertThat(metaListList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMetaList() throws Exception {
        // Initialize the database
        metaListRepository.saveAndFlush(metaList);

        int databaseSizeBeforeDelete = metaListRepository.findAll().size();

        // Delete the metaList
        restMetaListMockMvc.perform(delete("/api/meta-lists/{id}", metaList.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<MetaList> metaListList = metaListRepository.findAll();
        assertThat(metaListList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MetaList.class);
        MetaList metaList1 = new MetaList();
        metaList1.setId(1L);
        MetaList metaList2 = new MetaList();
        metaList2.setId(metaList1.getId());
        assertThat(metaList1).isEqualTo(metaList2);
        metaList2.setId(2L);
        assertThat(metaList1).isNotEqualTo(metaList2);
        metaList1.setId(null);
        assertThat(metaList1).isNotEqualTo(metaList2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MetaListDTO.class);
        MetaListDTO metaListDTO1 = new MetaListDTO();
        metaListDTO1.setId(1L);
        MetaListDTO metaListDTO2 = new MetaListDTO();
        assertThat(metaListDTO1).isNotEqualTo(metaListDTO2);
        metaListDTO2.setId(metaListDTO1.getId());
        assertThat(metaListDTO1).isEqualTo(metaListDTO2);
        metaListDTO2.setId(2L);
        assertThat(metaListDTO1).isNotEqualTo(metaListDTO2);
        metaListDTO1.setId(null);
        assertThat(metaListDTO1).isNotEqualTo(metaListDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(metaListMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(metaListMapper.fromId(null)).isNull();
    }
}
