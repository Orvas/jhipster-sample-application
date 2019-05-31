package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.ListPipelineLocation;
import io.github.jhipster.application.domain.PipelineHist;
import io.github.jhipster.application.repository.ListPipelineLocationRepository;
import io.github.jhipster.application.service.ListPipelineLocationService;
import io.github.jhipster.application.service.dto.ListPipelineLocationDTO;
import io.github.jhipster.application.service.mapper.ListPipelineLocationMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.ListPipelineLocationCriteria;
import io.github.jhipster.application.service.ListPipelineLocationQueryService;

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
 * Integration tests for the {@Link ListPipelineLocationResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class ListPipelineLocationResourceIT {

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
    private ListPipelineLocationRepository listPipelineLocationRepository;

    @Autowired
    private ListPipelineLocationMapper listPipelineLocationMapper;

    @Autowired
    private ListPipelineLocationService listPipelineLocationService;

    @Autowired
    private ListPipelineLocationQueryService listPipelineLocationQueryService;

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

    private MockMvc restListPipelineLocationMockMvc;

    private ListPipelineLocation listPipelineLocation;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ListPipelineLocationResource listPipelineLocationResource = new ListPipelineLocationResource(listPipelineLocationService, listPipelineLocationQueryService);
        this.restListPipelineLocationMockMvc = MockMvcBuilders.standaloneSetup(listPipelineLocationResource)
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
    public static ListPipelineLocation createEntity(EntityManager em) {
        ListPipelineLocation listPipelineLocation = new ListPipelineLocation()
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME)
            .fullName(DEFAULT_FULL_NAME)
            .isCurrentFlag(DEFAULT_IS_CURRENT_FLAG)
            .description(DEFAULT_DESCRIPTION)
            .dateCreate(DEFAULT_DATE_CREATE)
            .dateEdit(DEFAULT_DATE_EDIT)
            .creator(DEFAULT_CREATOR)
            .editor(DEFAULT_EDITOR);
        return listPipelineLocation;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ListPipelineLocation createUpdatedEntity(EntityManager em) {
        ListPipelineLocation listPipelineLocation = new ListPipelineLocation()
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        return listPipelineLocation;
    }

    @BeforeEach
    public void initTest() {
        listPipelineLocation = createEntity(em);
    }

    @Test
    @Transactional
    public void createListPipelineLocation() throws Exception {
        int databaseSizeBeforeCreate = listPipelineLocationRepository.findAll().size();

        // Create the ListPipelineLocation
        ListPipelineLocationDTO listPipelineLocationDTO = listPipelineLocationMapper.toDto(listPipelineLocation);
        restListPipelineLocationMockMvc.perform(post("/api/list-pipeline-locations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listPipelineLocationDTO)))
            .andExpect(status().isCreated());

        // Validate the ListPipelineLocation in the database
        List<ListPipelineLocation> listPipelineLocationList = listPipelineLocationRepository.findAll();
        assertThat(listPipelineLocationList).hasSize(databaseSizeBeforeCreate + 1);
        ListPipelineLocation testListPipelineLocation = listPipelineLocationList.get(listPipelineLocationList.size() - 1);
        assertThat(testListPipelineLocation.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testListPipelineLocation.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testListPipelineLocation.getFullName()).isEqualTo(DEFAULT_FULL_NAME);
        assertThat(testListPipelineLocation.getIsCurrentFlag()).isEqualTo(DEFAULT_IS_CURRENT_FLAG);
        assertThat(testListPipelineLocation.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testListPipelineLocation.getDateCreate()).isEqualTo(DEFAULT_DATE_CREATE);
        assertThat(testListPipelineLocation.getDateEdit()).isEqualTo(DEFAULT_DATE_EDIT);
        assertThat(testListPipelineLocation.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testListPipelineLocation.getEditor()).isEqualTo(DEFAULT_EDITOR);
    }

    @Test
    @Transactional
    public void createListPipelineLocationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = listPipelineLocationRepository.findAll().size();

        // Create the ListPipelineLocation with an existing ID
        listPipelineLocation.setId(1L);
        ListPipelineLocationDTO listPipelineLocationDTO = listPipelineLocationMapper.toDto(listPipelineLocation);

        // An entity with an existing ID cannot be created, so this API call must fail
        restListPipelineLocationMockMvc.perform(post("/api/list-pipeline-locations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listPipelineLocationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListPipelineLocation in the database
        List<ListPipelineLocation> listPipelineLocationList = listPipelineLocationRepository.findAll();
        assertThat(listPipelineLocationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = listPipelineLocationRepository.findAll().size();
        // set the field null
        listPipelineLocation.setCode(null);

        // Create the ListPipelineLocation, which fails.
        ListPipelineLocationDTO listPipelineLocationDTO = listPipelineLocationMapper.toDto(listPipelineLocation);

        restListPipelineLocationMockMvc.perform(post("/api/list-pipeline-locations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listPipelineLocationDTO)))
            .andExpect(status().isBadRequest());

        List<ListPipelineLocation> listPipelineLocationList = listPipelineLocationRepository.findAll();
        assertThat(listPipelineLocationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listPipelineLocationRepository.findAll().size();
        // set the field null
        listPipelineLocation.setName(null);

        // Create the ListPipelineLocation, which fails.
        ListPipelineLocationDTO listPipelineLocationDTO = listPipelineLocationMapper.toDto(listPipelineLocation);

        restListPipelineLocationMockMvc.perform(post("/api/list-pipeline-locations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listPipelineLocationDTO)))
            .andExpect(status().isBadRequest());

        List<ListPipelineLocation> listPipelineLocationList = listPipelineLocationRepository.findAll();
        assertThat(listPipelineLocationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFullNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listPipelineLocationRepository.findAll().size();
        // set the field null
        listPipelineLocation.setFullName(null);

        // Create the ListPipelineLocation, which fails.
        ListPipelineLocationDTO listPipelineLocationDTO = listPipelineLocationMapper.toDto(listPipelineLocation);

        restListPipelineLocationMockMvc.perform(post("/api/list-pipeline-locations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listPipelineLocationDTO)))
            .andExpect(status().isBadRequest());

        List<ListPipelineLocation> listPipelineLocationList = listPipelineLocationRepository.findAll();
        assertThat(listPipelineLocationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsCurrentFlagIsRequired() throws Exception {
        int databaseSizeBeforeTest = listPipelineLocationRepository.findAll().size();
        // set the field null
        listPipelineLocation.setIsCurrentFlag(null);

        // Create the ListPipelineLocation, which fails.
        ListPipelineLocationDTO listPipelineLocationDTO = listPipelineLocationMapper.toDto(listPipelineLocation);

        restListPipelineLocationMockMvc.perform(post("/api/list-pipeline-locations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listPipelineLocationDTO)))
            .andExpect(status().isBadRequest());

        List<ListPipelineLocation> listPipelineLocationList = listPipelineLocationRepository.findAll();
        assertThat(listPipelineLocationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllListPipelineLocations() throws Exception {
        // Initialize the database
        listPipelineLocationRepository.saveAndFlush(listPipelineLocation);

        // Get all the listPipelineLocationList
        restListPipelineLocationMockMvc.perform(get("/api/list-pipeline-locations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listPipelineLocation.getId().intValue())))
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
    public void getListPipelineLocation() throws Exception {
        // Initialize the database
        listPipelineLocationRepository.saveAndFlush(listPipelineLocation);

        // Get the listPipelineLocation
        restListPipelineLocationMockMvc.perform(get("/api/list-pipeline-locations/{id}", listPipelineLocation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(listPipelineLocation.getId().intValue()))
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
    public void getAllListPipelineLocationsByCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        listPipelineLocationRepository.saveAndFlush(listPipelineLocation);

        // Get all the listPipelineLocationList where code equals to DEFAULT_CODE
        defaultListPipelineLocationShouldBeFound("code.equals=" + DEFAULT_CODE);

        // Get all the listPipelineLocationList where code equals to UPDATED_CODE
        defaultListPipelineLocationShouldNotBeFound("code.equals=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListPipelineLocationsByCodeIsInShouldWork() throws Exception {
        // Initialize the database
        listPipelineLocationRepository.saveAndFlush(listPipelineLocation);

        // Get all the listPipelineLocationList where code in DEFAULT_CODE or UPDATED_CODE
        defaultListPipelineLocationShouldBeFound("code.in=" + DEFAULT_CODE + "," + UPDATED_CODE);

        // Get all the listPipelineLocationList where code equals to UPDATED_CODE
        defaultListPipelineLocationShouldNotBeFound("code.in=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListPipelineLocationsByCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        listPipelineLocationRepository.saveAndFlush(listPipelineLocation);

        // Get all the listPipelineLocationList where code is not null
        defaultListPipelineLocationShouldBeFound("code.specified=true");

        // Get all the listPipelineLocationList where code is null
        defaultListPipelineLocationShouldNotBeFound("code.specified=false");
    }

    @Test
    @Transactional
    public void getAllListPipelineLocationsByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listPipelineLocationRepository.saveAndFlush(listPipelineLocation);

        // Get all the listPipelineLocationList where name equals to DEFAULT_NAME
        defaultListPipelineLocationShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the listPipelineLocationList where name equals to UPDATED_NAME
        defaultListPipelineLocationShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListPipelineLocationsByNameIsInShouldWork() throws Exception {
        // Initialize the database
        listPipelineLocationRepository.saveAndFlush(listPipelineLocation);

        // Get all the listPipelineLocationList where name in DEFAULT_NAME or UPDATED_NAME
        defaultListPipelineLocationShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the listPipelineLocationList where name equals to UPDATED_NAME
        defaultListPipelineLocationShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListPipelineLocationsByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listPipelineLocationRepository.saveAndFlush(listPipelineLocation);

        // Get all the listPipelineLocationList where name is not null
        defaultListPipelineLocationShouldBeFound("name.specified=true");

        // Get all the listPipelineLocationList where name is null
        defaultListPipelineLocationShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    public void getAllListPipelineLocationsByFullNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listPipelineLocationRepository.saveAndFlush(listPipelineLocation);

        // Get all the listPipelineLocationList where fullName equals to DEFAULT_FULL_NAME
        defaultListPipelineLocationShouldBeFound("fullName.equals=" + DEFAULT_FULL_NAME);

        // Get all the listPipelineLocationList where fullName equals to UPDATED_FULL_NAME
        defaultListPipelineLocationShouldNotBeFound("fullName.equals=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListPipelineLocationsByFullNameIsInShouldWork() throws Exception {
        // Initialize the database
        listPipelineLocationRepository.saveAndFlush(listPipelineLocation);

        // Get all the listPipelineLocationList where fullName in DEFAULT_FULL_NAME or UPDATED_FULL_NAME
        defaultListPipelineLocationShouldBeFound("fullName.in=" + DEFAULT_FULL_NAME + "," + UPDATED_FULL_NAME);

        // Get all the listPipelineLocationList where fullName equals to UPDATED_FULL_NAME
        defaultListPipelineLocationShouldNotBeFound("fullName.in=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListPipelineLocationsByFullNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listPipelineLocationRepository.saveAndFlush(listPipelineLocation);

        // Get all the listPipelineLocationList where fullName is not null
        defaultListPipelineLocationShouldBeFound("fullName.specified=true");

        // Get all the listPipelineLocationList where fullName is null
        defaultListPipelineLocationShouldNotBeFound("fullName.specified=false");
    }

    @Test
    @Transactional
    public void getAllListPipelineLocationsByIsCurrentFlagIsEqualToSomething() throws Exception {
        // Initialize the database
        listPipelineLocationRepository.saveAndFlush(listPipelineLocation);

        // Get all the listPipelineLocationList where isCurrentFlag equals to DEFAULT_IS_CURRENT_FLAG
        defaultListPipelineLocationShouldBeFound("isCurrentFlag.equals=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listPipelineLocationList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListPipelineLocationShouldNotBeFound("isCurrentFlag.equals=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListPipelineLocationsByIsCurrentFlagIsInShouldWork() throws Exception {
        // Initialize the database
        listPipelineLocationRepository.saveAndFlush(listPipelineLocation);

        // Get all the listPipelineLocationList where isCurrentFlag in DEFAULT_IS_CURRENT_FLAG or UPDATED_IS_CURRENT_FLAG
        defaultListPipelineLocationShouldBeFound("isCurrentFlag.in=" + DEFAULT_IS_CURRENT_FLAG + "," + UPDATED_IS_CURRENT_FLAG);

        // Get all the listPipelineLocationList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListPipelineLocationShouldNotBeFound("isCurrentFlag.in=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListPipelineLocationsByIsCurrentFlagIsNullOrNotNull() throws Exception {
        // Initialize the database
        listPipelineLocationRepository.saveAndFlush(listPipelineLocation);

        // Get all the listPipelineLocationList where isCurrentFlag is not null
        defaultListPipelineLocationShouldBeFound("isCurrentFlag.specified=true");

        // Get all the listPipelineLocationList where isCurrentFlag is null
        defaultListPipelineLocationShouldNotBeFound("isCurrentFlag.specified=false");
    }

    @Test
    @Transactional
    public void getAllListPipelineLocationsByIsCurrentFlagIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        listPipelineLocationRepository.saveAndFlush(listPipelineLocation);

        // Get all the listPipelineLocationList where isCurrentFlag greater than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListPipelineLocationShouldBeFound("isCurrentFlag.greaterOrEqualThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listPipelineLocationList where isCurrentFlag greater than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListPipelineLocationShouldNotBeFound("isCurrentFlag.greaterOrEqualThan=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListPipelineLocationsByIsCurrentFlagIsLessThanSomething() throws Exception {
        // Initialize the database
        listPipelineLocationRepository.saveAndFlush(listPipelineLocation);

        // Get all the listPipelineLocationList where isCurrentFlag less than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListPipelineLocationShouldNotBeFound("isCurrentFlag.lessThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listPipelineLocationList where isCurrentFlag less than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListPipelineLocationShouldBeFound("isCurrentFlag.lessThan=" + UPDATED_IS_CURRENT_FLAG);
    }


    @Test
    @Transactional
    public void getAllListPipelineLocationsByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        listPipelineLocationRepository.saveAndFlush(listPipelineLocation);

        // Get all the listPipelineLocationList where description equals to DEFAULT_DESCRIPTION
        defaultListPipelineLocationShouldBeFound("description.equals=" + DEFAULT_DESCRIPTION);

        // Get all the listPipelineLocationList where description equals to UPDATED_DESCRIPTION
        defaultListPipelineLocationShouldNotBeFound("description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListPipelineLocationsByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        listPipelineLocationRepository.saveAndFlush(listPipelineLocation);

        // Get all the listPipelineLocationList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
        defaultListPipelineLocationShouldBeFound("description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION);

        // Get all the listPipelineLocationList where description equals to UPDATED_DESCRIPTION
        defaultListPipelineLocationShouldNotBeFound("description.in=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListPipelineLocationsByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        listPipelineLocationRepository.saveAndFlush(listPipelineLocation);

        // Get all the listPipelineLocationList where description is not null
        defaultListPipelineLocationShouldBeFound("description.specified=true");

        // Get all the listPipelineLocationList where description is null
        defaultListPipelineLocationShouldNotBeFound("description.specified=false");
    }

    @Test
    @Transactional
    public void getAllListPipelineLocationsByDateCreateIsEqualToSomething() throws Exception {
        // Initialize the database
        listPipelineLocationRepository.saveAndFlush(listPipelineLocation);

        // Get all the listPipelineLocationList where dateCreate equals to DEFAULT_DATE_CREATE
        defaultListPipelineLocationShouldBeFound("dateCreate.equals=" + DEFAULT_DATE_CREATE);

        // Get all the listPipelineLocationList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListPipelineLocationShouldNotBeFound("dateCreate.equals=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListPipelineLocationsByDateCreateIsInShouldWork() throws Exception {
        // Initialize the database
        listPipelineLocationRepository.saveAndFlush(listPipelineLocation);

        // Get all the listPipelineLocationList where dateCreate in DEFAULT_DATE_CREATE or UPDATED_DATE_CREATE
        defaultListPipelineLocationShouldBeFound("dateCreate.in=" + DEFAULT_DATE_CREATE + "," + UPDATED_DATE_CREATE);

        // Get all the listPipelineLocationList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListPipelineLocationShouldNotBeFound("dateCreate.in=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListPipelineLocationsByDateCreateIsNullOrNotNull() throws Exception {
        // Initialize the database
        listPipelineLocationRepository.saveAndFlush(listPipelineLocation);

        // Get all the listPipelineLocationList where dateCreate is not null
        defaultListPipelineLocationShouldBeFound("dateCreate.specified=true");

        // Get all the listPipelineLocationList where dateCreate is null
        defaultListPipelineLocationShouldNotBeFound("dateCreate.specified=false");
    }

    @Test
    @Transactional
    public void getAllListPipelineLocationsByDateEditIsEqualToSomething() throws Exception {
        // Initialize the database
        listPipelineLocationRepository.saveAndFlush(listPipelineLocation);

        // Get all the listPipelineLocationList where dateEdit equals to DEFAULT_DATE_EDIT
        defaultListPipelineLocationShouldBeFound("dateEdit.equals=" + DEFAULT_DATE_EDIT);

        // Get all the listPipelineLocationList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListPipelineLocationShouldNotBeFound("dateEdit.equals=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListPipelineLocationsByDateEditIsInShouldWork() throws Exception {
        // Initialize the database
        listPipelineLocationRepository.saveAndFlush(listPipelineLocation);

        // Get all the listPipelineLocationList where dateEdit in DEFAULT_DATE_EDIT or UPDATED_DATE_EDIT
        defaultListPipelineLocationShouldBeFound("dateEdit.in=" + DEFAULT_DATE_EDIT + "," + UPDATED_DATE_EDIT);

        // Get all the listPipelineLocationList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListPipelineLocationShouldNotBeFound("dateEdit.in=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListPipelineLocationsByDateEditIsNullOrNotNull() throws Exception {
        // Initialize the database
        listPipelineLocationRepository.saveAndFlush(listPipelineLocation);

        // Get all the listPipelineLocationList where dateEdit is not null
        defaultListPipelineLocationShouldBeFound("dateEdit.specified=true");

        // Get all the listPipelineLocationList where dateEdit is null
        defaultListPipelineLocationShouldNotBeFound("dateEdit.specified=false");
    }

    @Test
    @Transactional
    public void getAllListPipelineLocationsByCreatorIsEqualToSomething() throws Exception {
        // Initialize the database
        listPipelineLocationRepository.saveAndFlush(listPipelineLocation);

        // Get all the listPipelineLocationList where creator equals to DEFAULT_CREATOR
        defaultListPipelineLocationShouldBeFound("creator.equals=" + DEFAULT_CREATOR);

        // Get all the listPipelineLocationList where creator equals to UPDATED_CREATOR
        defaultListPipelineLocationShouldNotBeFound("creator.equals=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListPipelineLocationsByCreatorIsInShouldWork() throws Exception {
        // Initialize the database
        listPipelineLocationRepository.saveAndFlush(listPipelineLocation);

        // Get all the listPipelineLocationList where creator in DEFAULT_CREATOR or UPDATED_CREATOR
        defaultListPipelineLocationShouldBeFound("creator.in=" + DEFAULT_CREATOR + "," + UPDATED_CREATOR);

        // Get all the listPipelineLocationList where creator equals to UPDATED_CREATOR
        defaultListPipelineLocationShouldNotBeFound("creator.in=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListPipelineLocationsByCreatorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listPipelineLocationRepository.saveAndFlush(listPipelineLocation);

        // Get all the listPipelineLocationList where creator is not null
        defaultListPipelineLocationShouldBeFound("creator.specified=true");

        // Get all the listPipelineLocationList where creator is null
        defaultListPipelineLocationShouldNotBeFound("creator.specified=false");
    }

    @Test
    @Transactional
    public void getAllListPipelineLocationsByEditorIsEqualToSomething() throws Exception {
        // Initialize the database
        listPipelineLocationRepository.saveAndFlush(listPipelineLocation);

        // Get all the listPipelineLocationList where editor equals to DEFAULT_EDITOR
        defaultListPipelineLocationShouldBeFound("editor.equals=" + DEFAULT_EDITOR);

        // Get all the listPipelineLocationList where editor equals to UPDATED_EDITOR
        defaultListPipelineLocationShouldNotBeFound("editor.equals=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListPipelineLocationsByEditorIsInShouldWork() throws Exception {
        // Initialize the database
        listPipelineLocationRepository.saveAndFlush(listPipelineLocation);

        // Get all the listPipelineLocationList where editor in DEFAULT_EDITOR or UPDATED_EDITOR
        defaultListPipelineLocationShouldBeFound("editor.in=" + DEFAULT_EDITOR + "," + UPDATED_EDITOR);

        // Get all the listPipelineLocationList where editor equals to UPDATED_EDITOR
        defaultListPipelineLocationShouldNotBeFound("editor.in=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListPipelineLocationsByEditorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listPipelineLocationRepository.saveAndFlush(listPipelineLocation);

        // Get all the listPipelineLocationList where editor is not null
        defaultListPipelineLocationShouldBeFound("editor.specified=true");

        // Get all the listPipelineLocationList where editor is null
        defaultListPipelineLocationShouldNotBeFound("editor.specified=false");
    }

    @Test
    @Transactional
    public void getAllListPipelineLocationsByPipelineHistIsEqualToSomething() throws Exception {
        // Initialize the database
        PipelineHist pipelineHist = PipelineHistResourceIT.createEntity(em);
        em.persist(pipelineHist);
        em.flush();
        listPipelineLocation.addPipelineHist(pipelineHist);
        listPipelineLocationRepository.saveAndFlush(listPipelineLocation);
        Long pipelineHistId = pipelineHist.getId();

        // Get all the listPipelineLocationList where pipelineHist equals to pipelineHistId
        defaultListPipelineLocationShouldBeFound("pipelineHistId.equals=" + pipelineHistId);

        // Get all the listPipelineLocationList where pipelineHist equals to pipelineHistId + 1
        defaultListPipelineLocationShouldNotBeFound("pipelineHistId.equals=" + (pipelineHistId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultListPipelineLocationShouldBeFound(String filter) throws Exception {
        restListPipelineLocationMockMvc.perform(get("/api/list-pipeline-locations?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listPipelineLocation.getId().intValue())))
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
        restListPipelineLocationMockMvc.perform(get("/api/list-pipeline-locations/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultListPipelineLocationShouldNotBeFound(String filter) throws Exception {
        restListPipelineLocationMockMvc.perform(get("/api/list-pipeline-locations?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restListPipelineLocationMockMvc.perform(get("/api/list-pipeline-locations/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingListPipelineLocation() throws Exception {
        // Get the listPipelineLocation
        restListPipelineLocationMockMvc.perform(get("/api/list-pipeline-locations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateListPipelineLocation() throws Exception {
        // Initialize the database
        listPipelineLocationRepository.saveAndFlush(listPipelineLocation);

        int databaseSizeBeforeUpdate = listPipelineLocationRepository.findAll().size();

        // Update the listPipelineLocation
        ListPipelineLocation updatedListPipelineLocation = listPipelineLocationRepository.findById(listPipelineLocation.getId()).get();
        // Disconnect from session so that the updates on updatedListPipelineLocation are not directly saved in db
        em.detach(updatedListPipelineLocation);
        updatedListPipelineLocation
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        ListPipelineLocationDTO listPipelineLocationDTO = listPipelineLocationMapper.toDto(updatedListPipelineLocation);

        restListPipelineLocationMockMvc.perform(put("/api/list-pipeline-locations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listPipelineLocationDTO)))
            .andExpect(status().isOk());

        // Validate the ListPipelineLocation in the database
        List<ListPipelineLocation> listPipelineLocationList = listPipelineLocationRepository.findAll();
        assertThat(listPipelineLocationList).hasSize(databaseSizeBeforeUpdate);
        ListPipelineLocation testListPipelineLocation = listPipelineLocationList.get(listPipelineLocationList.size() - 1);
        assertThat(testListPipelineLocation.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testListPipelineLocation.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testListPipelineLocation.getFullName()).isEqualTo(UPDATED_FULL_NAME);
        assertThat(testListPipelineLocation.getIsCurrentFlag()).isEqualTo(UPDATED_IS_CURRENT_FLAG);
        assertThat(testListPipelineLocation.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testListPipelineLocation.getDateCreate()).isEqualTo(UPDATED_DATE_CREATE);
        assertThat(testListPipelineLocation.getDateEdit()).isEqualTo(UPDATED_DATE_EDIT);
        assertThat(testListPipelineLocation.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testListPipelineLocation.getEditor()).isEqualTo(UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void updateNonExistingListPipelineLocation() throws Exception {
        int databaseSizeBeforeUpdate = listPipelineLocationRepository.findAll().size();

        // Create the ListPipelineLocation
        ListPipelineLocationDTO listPipelineLocationDTO = listPipelineLocationMapper.toDto(listPipelineLocation);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restListPipelineLocationMockMvc.perform(put("/api/list-pipeline-locations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listPipelineLocationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListPipelineLocation in the database
        List<ListPipelineLocation> listPipelineLocationList = listPipelineLocationRepository.findAll();
        assertThat(listPipelineLocationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteListPipelineLocation() throws Exception {
        // Initialize the database
        listPipelineLocationRepository.saveAndFlush(listPipelineLocation);

        int databaseSizeBeforeDelete = listPipelineLocationRepository.findAll().size();

        // Delete the listPipelineLocation
        restListPipelineLocationMockMvc.perform(delete("/api/list-pipeline-locations/{id}", listPipelineLocation.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<ListPipelineLocation> listPipelineLocationList = listPipelineLocationRepository.findAll();
        assertThat(listPipelineLocationList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListPipelineLocation.class);
        ListPipelineLocation listPipelineLocation1 = new ListPipelineLocation();
        listPipelineLocation1.setId(1L);
        ListPipelineLocation listPipelineLocation2 = new ListPipelineLocation();
        listPipelineLocation2.setId(listPipelineLocation1.getId());
        assertThat(listPipelineLocation1).isEqualTo(listPipelineLocation2);
        listPipelineLocation2.setId(2L);
        assertThat(listPipelineLocation1).isNotEqualTo(listPipelineLocation2);
        listPipelineLocation1.setId(null);
        assertThat(listPipelineLocation1).isNotEqualTo(listPipelineLocation2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListPipelineLocationDTO.class);
        ListPipelineLocationDTO listPipelineLocationDTO1 = new ListPipelineLocationDTO();
        listPipelineLocationDTO1.setId(1L);
        ListPipelineLocationDTO listPipelineLocationDTO2 = new ListPipelineLocationDTO();
        assertThat(listPipelineLocationDTO1).isNotEqualTo(listPipelineLocationDTO2);
        listPipelineLocationDTO2.setId(listPipelineLocationDTO1.getId());
        assertThat(listPipelineLocationDTO1).isEqualTo(listPipelineLocationDTO2);
        listPipelineLocationDTO2.setId(2L);
        assertThat(listPipelineLocationDTO1).isNotEqualTo(listPipelineLocationDTO2);
        listPipelineLocationDTO1.setId(null);
        assertThat(listPipelineLocationDTO1).isNotEqualTo(listPipelineLocationDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(listPipelineLocationMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(listPipelineLocationMapper.fromId(null)).isNull();
    }
}
