package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.ListMillLocation;
import io.github.jhipster.application.domain.BendHist;
import io.github.jhipster.application.domain.BuckleArrestorHist;
import io.github.jhipster.application.domain.PipeHist;
import io.github.jhipster.application.domain.TeeHist;
import io.github.jhipster.application.domain.ValveHist;
import io.github.jhipster.application.repository.ListMillLocationRepository;
import io.github.jhipster.application.service.ListMillLocationService;
import io.github.jhipster.application.service.dto.ListMillLocationDTO;
import io.github.jhipster.application.service.mapper.ListMillLocationMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.ListMillLocationCriteria;
import io.github.jhipster.application.service.ListMillLocationQueryService;

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
 * Integration tests for the {@Link ListMillLocationResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class ListMillLocationResourceIT {

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
    private ListMillLocationRepository listMillLocationRepository;

    @Autowired
    private ListMillLocationMapper listMillLocationMapper;

    @Autowired
    private ListMillLocationService listMillLocationService;

    @Autowired
    private ListMillLocationQueryService listMillLocationQueryService;

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

    private MockMvc restListMillLocationMockMvc;

    private ListMillLocation listMillLocation;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ListMillLocationResource listMillLocationResource = new ListMillLocationResource(listMillLocationService, listMillLocationQueryService);
        this.restListMillLocationMockMvc = MockMvcBuilders.standaloneSetup(listMillLocationResource)
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
    public static ListMillLocation createEntity(EntityManager em) {
        ListMillLocation listMillLocation = new ListMillLocation()
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME)
            .fullName(DEFAULT_FULL_NAME)
            .isCurrentFlag(DEFAULT_IS_CURRENT_FLAG)
            .description(DEFAULT_DESCRIPTION)
            .dateCreate(DEFAULT_DATE_CREATE)
            .dateEdit(DEFAULT_DATE_EDIT)
            .creator(DEFAULT_CREATOR)
            .editor(DEFAULT_EDITOR);
        return listMillLocation;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ListMillLocation createUpdatedEntity(EntityManager em) {
        ListMillLocation listMillLocation = new ListMillLocation()
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        return listMillLocation;
    }

    @BeforeEach
    public void initTest() {
        listMillLocation = createEntity(em);
    }

    @Test
    @Transactional
    public void createListMillLocation() throws Exception {
        int databaseSizeBeforeCreate = listMillLocationRepository.findAll().size();

        // Create the ListMillLocation
        ListMillLocationDTO listMillLocationDTO = listMillLocationMapper.toDto(listMillLocation);
        restListMillLocationMockMvc.perform(post("/api/list-mill-locations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listMillLocationDTO)))
            .andExpect(status().isCreated());

        // Validate the ListMillLocation in the database
        List<ListMillLocation> listMillLocationList = listMillLocationRepository.findAll();
        assertThat(listMillLocationList).hasSize(databaseSizeBeforeCreate + 1);
        ListMillLocation testListMillLocation = listMillLocationList.get(listMillLocationList.size() - 1);
        assertThat(testListMillLocation.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testListMillLocation.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testListMillLocation.getFullName()).isEqualTo(DEFAULT_FULL_NAME);
        assertThat(testListMillLocation.getIsCurrentFlag()).isEqualTo(DEFAULT_IS_CURRENT_FLAG);
        assertThat(testListMillLocation.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testListMillLocation.getDateCreate()).isEqualTo(DEFAULT_DATE_CREATE);
        assertThat(testListMillLocation.getDateEdit()).isEqualTo(DEFAULT_DATE_EDIT);
        assertThat(testListMillLocation.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testListMillLocation.getEditor()).isEqualTo(DEFAULT_EDITOR);
    }

    @Test
    @Transactional
    public void createListMillLocationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = listMillLocationRepository.findAll().size();

        // Create the ListMillLocation with an existing ID
        listMillLocation.setId(1L);
        ListMillLocationDTO listMillLocationDTO = listMillLocationMapper.toDto(listMillLocation);

        // An entity with an existing ID cannot be created, so this API call must fail
        restListMillLocationMockMvc.perform(post("/api/list-mill-locations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listMillLocationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListMillLocation in the database
        List<ListMillLocation> listMillLocationList = listMillLocationRepository.findAll();
        assertThat(listMillLocationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = listMillLocationRepository.findAll().size();
        // set the field null
        listMillLocation.setCode(null);

        // Create the ListMillLocation, which fails.
        ListMillLocationDTO listMillLocationDTO = listMillLocationMapper.toDto(listMillLocation);

        restListMillLocationMockMvc.perform(post("/api/list-mill-locations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listMillLocationDTO)))
            .andExpect(status().isBadRequest());

        List<ListMillLocation> listMillLocationList = listMillLocationRepository.findAll();
        assertThat(listMillLocationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listMillLocationRepository.findAll().size();
        // set the field null
        listMillLocation.setName(null);

        // Create the ListMillLocation, which fails.
        ListMillLocationDTO listMillLocationDTO = listMillLocationMapper.toDto(listMillLocation);

        restListMillLocationMockMvc.perform(post("/api/list-mill-locations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listMillLocationDTO)))
            .andExpect(status().isBadRequest());

        List<ListMillLocation> listMillLocationList = listMillLocationRepository.findAll();
        assertThat(listMillLocationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFullNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listMillLocationRepository.findAll().size();
        // set the field null
        listMillLocation.setFullName(null);

        // Create the ListMillLocation, which fails.
        ListMillLocationDTO listMillLocationDTO = listMillLocationMapper.toDto(listMillLocation);

        restListMillLocationMockMvc.perform(post("/api/list-mill-locations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listMillLocationDTO)))
            .andExpect(status().isBadRequest());

        List<ListMillLocation> listMillLocationList = listMillLocationRepository.findAll();
        assertThat(listMillLocationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsCurrentFlagIsRequired() throws Exception {
        int databaseSizeBeforeTest = listMillLocationRepository.findAll().size();
        // set the field null
        listMillLocation.setIsCurrentFlag(null);

        // Create the ListMillLocation, which fails.
        ListMillLocationDTO listMillLocationDTO = listMillLocationMapper.toDto(listMillLocation);

        restListMillLocationMockMvc.perform(post("/api/list-mill-locations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listMillLocationDTO)))
            .andExpect(status().isBadRequest());

        List<ListMillLocation> listMillLocationList = listMillLocationRepository.findAll();
        assertThat(listMillLocationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllListMillLocations() throws Exception {
        // Initialize the database
        listMillLocationRepository.saveAndFlush(listMillLocation);

        // Get all the listMillLocationList
        restListMillLocationMockMvc.perform(get("/api/list-mill-locations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listMillLocation.getId().intValue())))
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
    public void getListMillLocation() throws Exception {
        // Initialize the database
        listMillLocationRepository.saveAndFlush(listMillLocation);

        // Get the listMillLocation
        restListMillLocationMockMvc.perform(get("/api/list-mill-locations/{id}", listMillLocation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(listMillLocation.getId().intValue()))
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
    public void getAllListMillLocationsByCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        listMillLocationRepository.saveAndFlush(listMillLocation);

        // Get all the listMillLocationList where code equals to DEFAULT_CODE
        defaultListMillLocationShouldBeFound("code.equals=" + DEFAULT_CODE);

        // Get all the listMillLocationList where code equals to UPDATED_CODE
        defaultListMillLocationShouldNotBeFound("code.equals=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListMillLocationsByCodeIsInShouldWork() throws Exception {
        // Initialize the database
        listMillLocationRepository.saveAndFlush(listMillLocation);

        // Get all the listMillLocationList where code in DEFAULT_CODE or UPDATED_CODE
        defaultListMillLocationShouldBeFound("code.in=" + DEFAULT_CODE + "," + UPDATED_CODE);

        // Get all the listMillLocationList where code equals to UPDATED_CODE
        defaultListMillLocationShouldNotBeFound("code.in=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListMillLocationsByCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        listMillLocationRepository.saveAndFlush(listMillLocation);

        // Get all the listMillLocationList where code is not null
        defaultListMillLocationShouldBeFound("code.specified=true");

        // Get all the listMillLocationList where code is null
        defaultListMillLocationShouldNotBeFound("code.specified=false");
    }

    @Test
    @Transactional
    public void getAllListMillLocationsByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listMillLocationRepository.saveAndFlush(listMillLocation);

        // Get all the listMillLocationList where name equals to DEFAULT_NAME
        defaultListMillLocationShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the listMillLocationList where name equals to UPDATED_NAME
        defaultListMillLocationShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListMillLocationsByNameIsInShouldWork() throws Exception {
        // Initialize the database
        listMillLocationRepository.saveAndFlush(listMillLocation);

        // Get all the listMillLocationList where name in DEFAULT_NAME or UPDATED_NAME
        defaultListMillLocationShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the listMillLocationList where name equals to UPDATED_NAME
        defaultListMillLocationShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListMillLocationsByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listMillLocationRepository.saveAndFlush(listMillLocation);

        // Get all the listMillLocationList where name is not null
        defaultListMillLocationShouldBeFound("name.specified=true");

        // Get all the listMillLocationList where name is null
        defaultListMillLocationShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    public void getAllListMillLocationsByFullNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listMillLocationRepository.saveAndFlush(listMillLocation);

        // Get all the listMillLocationList where fullName equals to DEFAULT_FULL_NAME
        defaultListMillLocationShouldBeFound("fullName.equals=" + DEFAULT_FULL_NAME);

        // Get all the listMillLocationList where fullName equals to UPDATED_FULL_NAME
        defaultListMillLocationShouldNotBeFound("fullName.equals=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListMillLocationsByFullNameIsInShouldWork() throws Exception {
        // Initialize the database
        listMillLocationRepository.saveAndFlush(listMillLocation);

        // Get all the listMillLocationList where fullName in DEFAULT_FULL_NAME or UPDATED_FULL_NAME
        defaultListMillLocationShouldBeFound("fullName.in=" + DEFAULT_FULL_NAME + "," + UPDATED_FULL_NAME);

        // Get all the listMillLocationList where fullName equals to UPDATED_FULL_NAME
        defaultListMillLocationShouldNotBeFound("fullName.in=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListMillLocationsByFullNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listMillLocationRepository.saveAndFlush(listMillLocation);

        // Get all the listMillLocationList where fullName is not null
        defaultListMillLocationShouldBeFound("fullName.specified=true");

        // Get all the listMillLocationList where fullName is null
        defaultListMillLocationShouldNotBeFound("fullName.specified=false");
    }

    @Test
    @Transactional
    public void getAllListMillLocationsByIsCurrentFlagIsEqualToSomething() throws Exception {
        // Initialize the database
        listMillLocationRepository.saveAndFlush(listMillLocation);

        // Get all the listMillLocationList where isCurrentFlag equals to DEFAULT_IS_CURRENT_FLAG
        defaultListMillLocationShouldBeFound("isCurrentFlag.equals=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listMillLocationList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListMillLocationShouldNotBeFound("isCurrentFlag.equals=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListMillLocationsByIsCurrentFlagIsInShouldWork() throws Exception {
        // Initialize the database
        listMillLocationRepository.saveAndFlush(listMillLocation);

        // Get all the listMillLocationList where isCurrentFlag in DEFAULT_IS_CURRENT_FLAG or UPDATED_IS_CURRENT_FLAG
        defaultListMillLocationShouldBeFound("isCurrentFlag.in=" + DEFAULT_IS_CURRENT_FLAG + "," + UPDATED_IS_CURRENT_FLAG);

        // Get all the listMillLocationList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListMillLocationShouldNotBeFound("isCurrentFlag.in=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListMillLocationsByIsCurrentFlagIsNullOrNotNull() throws Exception {
        // Initialize the database
        listMillLocationRepository.saveAndFlush(listMillLocation);

        // Get all the listMillLocationList where isCurrentFlag is not null
        defaultListMillLocationShouldBeFound("isCurrentFlag.specified=true");

        // Get all the listMillLocationList where isCurrentFlag is null
        defaultListMillLocationShouldNotBeFound("isCurrentFlag.specified=false");
    }

    @Test
    @Transactional
    public void getAllListMillLocationsByIsCurrentFlagIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        listMillLocationRepository.saveAndFlush(listMillLocation);

        // Get all the listMillLocationList where isCurrentFlag greater than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListMillLocationShouldBeFound("isCurrentFlag.greaterOrEqualThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listMillLocationList where isCurrentFlag greater than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListMillLocationShouldNotBeFound("isCurrentFlag.greaterOrEqualThan=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListMillLocationsByIsCurrentFlagIsLessThanSomething() throws Exception {
        // Initialize the database
        listMillLocationRepository.saveAndFlush(listMillLocation);

        // Get all the listMillLocationList where isCurrentFlag less than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListMillLocationShouldNotBeFound("isCurrentFlag.lessThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listMillLocationList where isCurrentFlag less than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListMillLocationShouldBeFound("isCurrentFlag.lessThan=" + UPDATED_IS_CURRENT_FLAG);
    }


    @Test
    @Transactional
    public void getAllListMillLocationsByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        listMillLocationRepository.saveAndFlush(listMillLocation);

        // Get all the listMillLocationList where description equals to DEFAULT_DESCRIPTION
        defaultListMillLocationShouldBeFound("description.equals=" + DEFAULT_DESCRIPTION);

        // Get all the listMillLocationList where description equals to UPDATED_DESCRIPTION
        defaultListMillLocationShouldNotBeFound("description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListMillLocationsByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        listMillLocationRepository.saveAndFlush(listMillLocation);

        // Get all the listMillLocationList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
        defaultListMillLocationShouldBeFound("description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION);

        // Get all the listMillLocationList where description equals to UPDATED_DESCRIPTION
        defaultListMillLocationShouldNotBeFound("description.in=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListMillLocationsByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        listMillLocationRepository.saveAndFlush(listMillLocation);

        // Get all the listMillLocationList where description is not null
        defaultListMillLocationShouldBeFound("description.specified=true");

        // Get all the listMillLocationList where description is null
        defaultListMillLocationShouldNotBeFound("description.specified=false");
    }

    @Test
    @Transactional
    public void getAllListMillLocationsByDateCreateIsEqualToSomething() throws Exception {
        // Initialize the database
        listMillLocationRepository.saveAndFlush(listMillLocation);

        // Get all the listMillLocationList where dateCreate equals to DEFAULT_DATE_CREATE
        defaultListMillLocationShouldBeFound("dateCreate.equals=" + DEFAULT_DATE_CREATE);

        // Get all the listMillLocationList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListMillLocationShouldNotBeFound("dateCreate.equals=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListMillLocationsByDateCreateIsInShouldWork() throws Exception {
        // Initialize the database
        listMillLocationRepository.saveAndFlush(listMillLocation);

        // Get all the listMillLocationList where dateCreate in DEFAULT_DATE_CREATE or UPDATED_DATE_CREATE
        defaultListMillLocationShouldBeFound("dateCreate.in=" + DEFAULT_DATE_CREATE + "," + UPDATED_DATE_CREATE);

        // Get all the listMillLocationList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListMillLocationShouldNotBeFound("dateCreate.in=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListMillLocationsByDateCreateIsNullOrNotNull() throws Exception {
        // Initialize the database
        listMillLocationRepository.saveAndFlush(listMillLocation);

        // Get all the listMillLocationList where dateCreate is not null
        defaultListMillLocationShouldBeFound("dateCreate.specified=true");

        // Get all the listMillLocationList where dateCreate is null
        defaultListMillLocationShouldNotBeFound("dateCreate.specified=false");
    }

    @Test
    @Transactional
    public void getAllListMillLocationsByDateEditIsEqualToSomething() throws Exception {
        // Initialize the database
        listMillLocationRepository.saveAndFlush(listMillLocation);

        // Get all the listMillLocationList where dateEdit equals to DEFAULT_DATE_EDIT
        defaultListMillLocationShouldBeFound("dateEdit.equals=" + DEFAULT_DATE_EDIT);

        // Get all the listMillLocationList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListMillLocationShouldNotBeFound("dateEdit.equals=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListMillLocationsByDateEditIsInShouldWork() throws Exception {
        // Initialize the database
        listMillLocationRepository.saveAndFlush(listMillLocation);

        // Get all the listMillLocationList where dateEdit in DEFAULT_DATE_EDIT or UPDATED_DATE_EDIT
        defaultListMillLocationShouldBeFound("dateEdit.in=" + DEFAULT_DATE_EDIT + "," + UPDATED_DATE_EDIT);

        // Get all the listMillLocationList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListMillLocationShouldNotBeFound("dateEdit.in=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListMillLocationsByDateEditIsNullOrNotNull() throws Exception {
        // Initialize the database
        listMillLocationRepository.saveAndFlush(listMillLocation);

        // Get all the listMillLocationList where dateEdit is not null
        defaultListMillLocationShouldBeFound("dateEdit.specified=true");

        // Get all the listMillLocationList where dateEdit is null
        defaultListMillLocationShouldNotBeFound("dateEdit.specified=false");
    }

    @Test
    @Transactional
    public void getAllListMillLocationsByCreatorIsEqualToSomething() throws Exception {
        // Initialize the database
        listMillLocationRepository.saveAndFlush(listMillLocation);

        // Get all the listMillLocationList where creator equals to DEFAULT_CREATOR
        defaultListMillLocationShouldBeFound("creator.equals=" + DEFAULT_CREATOR);

        // Get all the listMillLocationList where creator equals to UPDATED_CREATOR
        defaultListMillLocationShouldNotBeFound("creator.equals=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListMillLocationsByCreatorIsInShouldWork() throws Exception {
        // Initialize the database
        listMillLocationRepository.saveAndFlush(listMillLocation);

        // Get all the listMillLocationList where creator in DEFAULT_CREATOR or UPDATED_CREATOR
        defaultListMillLocationShouldBeFound("creator.in=" + DEFAULT_CREATOR + "," + UPDATED_CREATOR);

        // Get all the listMillLocationList where creator equals to UPDATED_CREATOR
        defaultListMillLocationShouldNotBeFound("creator.in=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListMillLocationsByCreatorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listMillLocationRepository.saveAndFlush(listMillLocation);

        // Get all the listMillLocationList where creator is not null
        defaultListMillLocationShouldBeFound("creator.specified=true");

        // Get all the listMillLocationList where creator is null
        defaultListMillLocationShouldNotBeFound("creator.specified=false");
    }

    @Test
    @Transactional
    public void getAllListMillLocationsByEditorIsEqualToSomething() throws Exception {
        // Initialize the database
        listMillLocationRepository.saveAndFlush(listMillLocation);

        // Get all the listMillLocationList where editor equals to DEFAULT_EDITOR
        defaultListMillLocationShouldBeFound("editor.equals=" + DEFAULT_EDITOR);

        // Get all the listMillLocationList where editor equals to UPDATED_EDITOR
        defaultListMillLocationShouldNotBeFound("editor.equals=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListMillLocationsByEditorIsInShouldWork() throws Exception {
        // Initialize the database
        listMillLocationRepository.saveAndFlush(listMillLocation);

        // Get all the listMillLocationList where editor in DEFAULT_EDITOR or UPDATED_EDITOR
        defaultListMillLocationShouldBeFound("editor.in=" + DEFAULT_EDITOR + "," + UPDATED_EDITOR);

        // Get all the listMillLocationList where editor equals to UPDATED_EDITOR
        defaultListMillLocationShouldNotBeFound("editor.in=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListMillLocationsByEditorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listMillLocationRepository.saveAndFlush(listMillLocation);

        // Get all the listMillLocationList where editor is not null
        defaultListMillLocationShouldBeFound("editor.specified=true");

        // Get all the listMillLocationList where editor is null
        defaultListMillLocationShouldNotBeFound("editor.specified=false");
    }

    @Test
    @Transactional
    public void getAllListMillLocationsByBendHistIsEqualToSomething() throws Exception {
        // Initialize the database
        BendHist bendHist = BendHistResourceIT.createEntity(em);
        em.persist(bendHist);
        em.flush();
        listMillLocation.addBendHist(bendHist);
        listMillLocationRepository.saveAndFlush(listMillLocation);
        Long bendHistId = bendHist.getId();

        // Get all the listMillLocationList where bendHist equals to bendHistId
        defaultListMillLocationShouldBeFound("bendHistId.equals=" + bendHistId);

        // Get all the listMillLocationList where bendHist equals to bendHistId + 1
        defaultListMillLocationShouldNotBeFound("bendHistId.equals=" + (bendHistId + 1));
    }


    @Test
    @Transactional
    public void getAllListMillLocationsByBuckleArrestorHistIsEqualToSomething() throws Exception {
        // Initialize the database
        BuckleArrestorHist buckleArrestorHist = BuckleArrestorHistResourceIT.createEntity(em);
        em.persist(buckleArrestorHist);
        em.flush();
        listMillLocation.addBuckleArrestorHist(buckleArrestorHist);
        listMillLocationRepository.saveAndFlush(listMillLocation);
        Long buckleArrestorHistId = buckleArrestorHist.getId();

        // Get all the listMillLocationList where buckleArrestorHist equals to buckleArrestorHistId
        defaultListMillLocationShouldBeFound("buckleArrestorHistId.equals=" + buckleArrestorHistId);

        // Get all the listMillLocationList where buckleArrestorHist equals to buckleArrestorHistId + 1
        defaultListMillLocationShouldNotBeFound("buckleArrestorHistId.equals=" + (buckleArrestorHistId + 1));
    }


    @Test
    @Transactional
    public void getAllListMillLocationsByPipeHistIsEqualToSomething() throws Exception {
        // Initialize the database
        PipeHist pipeHist = PipeHistResourceIT.createEntity(em);
        em.persist(pipeHist);
        em.flush();
        listMillLocation.addPipeHist(pipeHist);
        listMillLocationRepository.saveAndFlush(listMillLocation);
        Long pipeHistId = pipeHist.getId();

        // Get all the listMillLocationList where pipeHist equals to pipeHistId
        defaultListMillLocationShouldBeFound("pipeHistId.equals=" + pipeHistId);

        // Get all the listMillLocationList where pipeHist equals to pipeHistId + 1
        defaultListMillLocationShouldNotBeFound("pipeHistId.equals=" + (pipeHistId + 1));
    }


    @Test
    @Transactional
    public void getAllListMillLocationsByTeeHistIsEqualToSomething() throws Exception {
        // Initialize the database
        TeeHist teeHist = TeeHistResourceIT.createEntity(em);
        em.persist(teeHist);
        em.flush();
        listMillLocation.addTeeHist(teeHist);
        listMillLocationRepository.saveAndFlush(listMillLocation);
        Long teeHistId = teeHist.getId();

        // Get all the listMillLocationList where teeHist equals to teeHistId
        defaultListMillLocationShouldBeFound("teeHistId.equals=" + teeHistId);

        // Get all the listMillLocationList where teeHist equals to teeHistId + 1
        defaultListMillLocationShouldNotBeFound("teeHistId.equals=" + (teeHistId + 1));
    }


    @Test
    @Transactional
    public void getAllListMillLocationsByValveHistIsEqualToSomething() throws Exception {
        // Initialize the database
        ValveHist valveHist = ValveHistResourceIT.createEntity(em);
        em.persist(valveHist);
        em.flush();
        listMillLocation.addValveHist(valveHist);
        listMillLocationRepository.saveAndFlush(listMillLocation);
        Long valveHistId = valveHist.getId();

        // Get all the listMillLocationList where valveHist equals to valveHistId
        defaultListMillLocationShouldBeFound("valveHistId.equals=" + valveHistId);

        // Get all the listMillLocationList where valveHist equals to valveHistId + 1
        defaultListMillLocationShouldNotBeFound("valveHistId.equals=" + (valveHistId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultListMillLocationShouldBeFound(String filter) throws Exception {
        restListMillLocationMockMvc.perform(get("/api/list-mill-locations?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listMillLocation.getId().intValue())))
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
        restListMillLocationMockMvc.perform(get("/api/list-mill-locations/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultListMillLocationShouldNotBeFound(String filter) throws Exception {
        restListMillLocationMockMvc.perform(get("/api/list-mill-locations?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restListMillLocationMockMvc.perform(get("/api/list-mill-locations/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingListMillLocation() throws Exception {
        // Get the listMillLocation
        restListMillLocationMockMvc.perform(get("/api/list-mill-locations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateListMillLocation() throws Exception {
        // Initialize the database
        listMillLocationRepository.saveAndFlush(listMillLocation);

        int databaseSizeBeforeUpdate = listMillLocationRepository.findAll().size();

        // Update the listMillLocation
        ListMillLocation updatedListMillLocation = listMillLocationRepository.findById(listMillLocation.getId()).get();
        // Disconnect from session so that the updates on updatedListMillLocation are not directly saved in db
        em.detach(updatedListMillLocation);
        updatedListMillLocation
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        ListMillLocationDTO listMillLocationDTO = listMillLocationMapper.toDto(updatedListMillLocation);

        restListMillLocationMockMvc.perform(put("/api/list-mill-locations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listMillLocationDTO)))
            .andExpect(status().isOk());

        // Validate the ListMillLocation in the database
        List<ListMillLocation> listMillLocationList = listMillLocationRepository.findAll();
        assertThat(listMillLocationList).hasSize(databaseSizeBeforeUpdate);
        ListMillLocation testListMillLocation = listMillLocationList.get(listMillLocationList.size() - 1);
        assertThat(testListMillLocation.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testListMillLocation.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testListMillLocation.getFullName()).isEqualTo(UPDATED_FULL_NAME);
        assertThat(testListMillLocation.getIsCurrentFlag()).isEqualTo(UPDATED_IS_CURRENT_FLAG);
        assertThat(testListMillLocation.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testListMillLocation.getDateCreate()).isEqualTo(UPDATED_DATE_CREATE);
        assertThat(testListMillLocation.getDateEdit()).isEqualTo(UPDATED_DATE_EDIT);
        assertThat(testListMillLocation.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testListMillLocation.getEditor()).isEqualTo(UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void updateNonExistingListMillLocation() throws Exception {
        int databaseSizeBeforeUpdate = listMillLocationRepository.findAll().size();

        // Create the ListMillLocation
        ListMillLocationDTO listMillLocationDTO = listMillLocationMapper.toDto(listMillLocation);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restListMillLocationMockMvc.perform(put("/api/list-mill-locations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listMillLocationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListMillLocation in the database
        List<ListMillLocation> listMillLocationList = listMillLocationRepository.findAll();
        assertThat(listMillLocationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteListMillLocation() throws Exception {
        // Initialize the database
        listMillLocationRepository.saveAndFlush(listMillLocation);

        int databaseSizeBeforeDelete = listMillLocationRepository.findAll().size();

        // Delete the listMillLocation
        restListMillLocationMockMvc.perform(delete("/api/list-mill-locations/{id}", listMillLocation.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<ListMillLocation> listMillLocationList = listMillLocationRepository.findAll();
        assertThat(listMillLocationList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListMillLocation.class);
        ListMillLocation listMillLocation1 = new ListMillLocation();
        listMillLocation1.setId(1L);
        ListMillLocation listMillLocation2 = new ListMillLocation();
        listMillLocation2.setId(listMillLocation1.getId());
        assertThat(listMillLocation1).isEqualTo(listMillLocation2);
        listMillLocation2.setId(2L);
        assertThat(listMillLocation1).isNotEqualTo(listMillLocation2);
        listMillLocation1.setId(null);
        assertThat(listMillLocation1).isNotEqualTo(listMillLocation2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListMillLocationDTO.class);
        ListMillLocationDTO listMillLocationDTO1 = new ListMillLocationDTO();
        listMillLocationDTO1.setId(1L);
        ListMillLocationDTO listMillLocationDTO2 = new ListMillLocationDTO();
        assertThat(listMillLocationDTO1).isNotEqualTo(listMillLocationDTO2);
        listMillLocationDTO2.setId(listMillLocationDTO1.getId());
        assertThat(listMillLocationDTO1).isEqualTo(listMillLocationDTO2);
        listMillLocationDTO2.setId(2L);
        assertThat(listMillLocationDTO1).isNotEqualTo(listMillLocationDTO2);
        listMillLocationDTO1.setId(null);
        assertThat(listMillLocationDTO1).isNotEqualTo(listMillLocationDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(listMillLocationMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(listMillLocationMapper.fromId(null)).isNull();
    }
}
