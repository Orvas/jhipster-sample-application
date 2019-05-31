package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.ListNominalWallThickness;
import io.github.jhipster.application.domain.BendHist;
import io.github.jhipster.application.domain.BuckleArrestorHist;
import io.github.jhipster.application.domain.PipeHist;
import io.github.jhipster.application.domain.TeeHist;
import io.github.jhipster.application.domain.ValveHist;
import io.github.jhipster.application.repository.ListNominalWallThicknessRepository;
import io.github.jhipster.application.service.ListNominalWallThicknessService;
import io.github.jhipster.application.service.dto.ListNominalWallThicknessDTO;
import io.github.jhipster.application.service.mapper.ListNominalWallThicknessMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.ListNominalWallThicknessCriteria;
import io.github.jhipster.application.service.ListNominalWallThicknessQueryService;

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
 * Integration tests for the {@Link ListNominalWallThicknessResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class ListNominalWallThicknessResourceIT {

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
    private ListNominalWallThicknessRepository listNominalWallThicknessRepository;

    @Autowired
    private ListNominalWallThicknessMapper listNominalWallThicknessMapper;

    @Autowired
    private ListNominalWallThicknessService listNominalWallThicknessService;

    @Autowired
    private ListNominalWallThicknessQueryService listNominalWallThicknessQueryService;

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

    private MockMvc restListNominalWallThicknessMockMvc;

    private ListNominalWallThickness listNominalWallThickness;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ListNominalWallThicknessResource listNominalWallThicknessResource = new ListNominalWallThicknessResource(listNominalWallThicknessService, listNominalWallThicknessQueryService);
        this.restListNominalWallThicknessMockMvc = MockMvcBuilders.standaloneSetup(listNominalWallThicknessResource)
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
    public static ListNominalWallThickness createEntity(EntityManager em) {
        ListNominalWallThickness listNominalWallThickness = new ListNominalWallThickness()
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME)
            .fullName(DEFAULT_FULL_NAME)
            .isCurrentFlag(DEFAULT_IS_CURRENT_FLAG)
            .description(DEFAULT_DESCRIPTION)
            .dateCreate(DEFAULT_DATE_CREATE)
            .dateEdit(DEFAULT_DATE_EDIT)
            .creator(DEFAULT_CREATOR)
            .editor(DEFAULT_EDITOR);
        return listNominalWallThickness;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ListNominalWallThickness createUpdatedEntity(EntityManager em) {
        ListNominalWallThickness listNominalWallThickness = new ListNominalWallThickness()
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        return listNominalWallThickness;
    }

    @BeforeEach
    public void initTest() {
        listNominalWallThickness = createEntity(em);
    }

    @Test
    @Transactional
    public void createListNominalWallThickness() throws Exception {
        int databaseSizeBeforeCreate = listNominalWallThicknessRepository.findAll().size();

        // Create the ListNominalWallThickness
        ListNominalWallThicknessDTO listNominalWallThicknessDTO = listNominalWallThicknessMapper.toDto(listNominalWallThickness);
        restListNominalWallThicknessMockMvc.perform(post("/api/list-nominal-wall-thicknesses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listNominalWallThicknessDTO)))
            .andExpect(status().isCreated());

        // Validate the ListNominalWallThickness in the database
        List<ListNominalWallThickness> listNominalWallThicknessList = listNominalWallThicknessRepository.findAll();
        assertThat(listNominalWallThicknessList).hasSize(databaseSizeBeforeCreate + 1);
        ListNominalWallThickness testListNominalWallThickness = listNominalWallThicknessList.get(listNominalWallThicknessList.size() - 1);
        assertThat(testListNominalWallThickness.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testListNominalWallThickness.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testListNominalWallThickness.getFullName()).isEqualTo(DEFAULT_FULL_NAME);
        assertThat(testListNominalWallThickness.getIsCurrentFlag()).isEqualTo(DEFAULT_IS_CURRENT_FLAG);
        assertThat(testListNominalWallThickness.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testListNominalWallThickness.getDateCreate()).isEqualTo(DEFAULT_DATE_CREATE);
        assertThat(testListNominalWallThickness.getDateEdit()).isEqualTo(DEFAULT_DATE_EDIT);
        assertThat(testListNominalWallThickness.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testListNominalWallThickness.getEditor()).isEqualTo(DEFAULT_EDITOR);
    }

    @Test
    @Transactional
    public void createListNominalWallThicknessWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = listNominalWallThicknessRepository.findAll().size();

        // Create the ListNominalWallThickness with an existing ID
        listNominalWallThickness.setId(1L);
        ListNominalWallThicknessDTO listNominalWallThicknessDTO = listNominalWallThicknessMapper.toDto(listNominalWallThickness);

        // An entity with an existing ID cannot be created, so this API call must fail
        restListNominalWallThicknessMockMvc.perform(post("/api/list-nominal-wall-thicknesses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listNominalWallThicknessDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListNominalWallThickness in the database
        List<ListNominalWallThickness> listNominalWallThicknessList = listNominalWallThicknessRepository.findAll();
        assertThat(listNominalWallThicknessList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = listNominalWallThicknessRepository.findAll().size();
        // set the field null
        listNominalWallThickness.setCode(null);

        // Create the ListNominalWallThickness, which fails.
        ListNominalWallThicknessDTO listNominalWallThicknessDTO = listNominalWallThicknessMapper.toDto(listNominalWallThickness);

        restListNominalWallThicknessMockMvc.perform(post("/api/list-nominal-wall-thicknesses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listNominalWallThicknessDTO)))
            .andExpect(status().isBadRequest());

        List<ListNominalWallThickness> listNominalWallThicknessList = listNominalWallThicknessRepository.findAll();
        assertThat(listNominalWallThicknessList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listNominalWallThicknessRepository.findAll().size();
        // set the field null
        listNominalWallThickness.setName(null);

        // Create the ListNominalWallThickness, which fails.
        ListNominalWallThicknessDTO listNominalWallThicknessDTO = listNominalWallThicknessMapper.toDto(listNominalWallThickness);

        restListNominalWallThicknessMockMvc.perform(post("/api/list-nominal-wall-thicknesses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listNominalWallThicknessDTO)))
            .andExpect(status().isBadRequest());

        List<ListNominalWallThickness> listNominalWallThicknessList = listNominalWallThicknessRepository.findAll();
        assertThat(listNominalWallThicknessList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFullNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = listNominalWallThicknessRepository.findAll().size();
        // set the field null
        listNominalWallThickness.setFullName(null);

        // Create the ListNominalWallThickness, which fails.
        ListNominalWallThicknessDTO listNominalWallThicknessDTO = listNominalWallThicknessMapper.toDto(listNominalWallThickness);

        restListNominalWallThicknessMockMvc.perform(post("/api/list-nominal-wall-thicknesses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listNominalWallThicknessDTO)))
            .andExpect(status().isBadRequest());

        List<ListNominalWallThickness> listNominalWallThicknessList = listNominalWallThicknessRepository.findAll();
        assertThat(listNominalWallThicknessList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsCurrentFlagIsRequired() throws Exception {
        int databaseSizeBeforeTest = listNominalWallThicknessRepository.findAll().size();
        // set the field null
        listNominalWallThickness.setIsCurrentFlag(null);

        // Create the ListNominalWallThickness, which fails.
        ListNominalWallThicknessDTO listNominalWallThicknessDTO = listNominalWallThicknessMapper.toDto(listNominalWallThickness);

        restListNominalWallThicknessMockMvc.perform(post("/api/list-nominal-wall-thicknesses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listNominalWallThicknessDTO)))
            .andExpect(status().isBadRequest());

        List<ListNominalWallThickness> listNominalWallThicknessList = listNominalWallThicknessRepository.findAll();
        assertThat(listNominalWallThicknessList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllListNominalWallThicknesses() throws Exception {
        // Initialize the database
        listNominalWallThicknessRepository.saveAndFlush(listNominalWallThickness);

        // Get all the listNominalWallThicknessList
        restListNominalWallThicknessMockMvc.perform(get("/api/list-nominal-wall-thicknesses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listNominalWallThickness.getId().intValue())))
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
    public void getListNominalWallThickness() throws Exception {
        // Initialize the database
        listNominalWallThicknessRepository.saveAndFlush(listNominalWallThickness);

        // Get the listNominalWallThickness
        restListNominalWallThicknessMockMvc.perform(get("/api/list-nominal-wall-thicknesses/{id}", listNominalWallThickness.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(listNominalWallThickness.getId().intValue()))
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
    public void getAllListNominalWallThicknessesByCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        listNominalWallThicknessRepository.saveAndFlush(listNominalWallThickness);

        // Get all the listNominalWallThicknessList where code equals to DEFAULT_CODE
        defaultListNominalWallThicknessShouldBeFound("code.equals=" + DEFAULT_CODE);

        // Get all the listNominalWallThicknessList where code equals to UPDATED_CODE
        defaultListNominalWallThicknessShouldNotBeFound("code.equals=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListNominalWallThicknessesByCodeIsInShouldWork() throws Exception {
        // Initialize the database
        listNominalWallThicknessRepository.saveAndFlush(listNominalWallThickness);

        // Get all the listNominalWallThicknessList where code in DEFAULT_CODE or UPDATED_CODE
        defaultListNominalWallThicknessShouldBeFound("code.in=" + DEFAULT_CODE + "," + UPDATED_CODE);

        // Get all the listNominalWallThicknessList where code equals to UPDATED_CODE
        defaultListNominalWallThicknessShouldNotBeFound("code.in=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    public void getAllListNominalWallThicknessesByCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        listNominalWallThicknessRepository.saveAndFlush(listNominalWallThickness);

        // Get all the listNominalWallThicknessList where code is not null
        defaultListNominalWallThicknessShouldBeFound("code.specified=true");

        // Get all the listNominalWallThicknessList where code is null
        defaultListNominalWallThicknessShouldNotBeFound("code.specified=false");
    }

    @Test
    @Transactional
    public void getAllListNominalWallThicknessesByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listNominalWallThicknessRepository.saveAndFlush(listNominalWallThickness);

        // Get all the listNominalWallThicknessList where name equals to DEFAULT_NAME
        defaultListNominalWallThicknessShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the listNominalWallThicknessList where name equals to UPDATED_NAME
        defaultListNominalWallThicknessShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListNominalWallThicknessesByNameIsInShouldWork() throws Exception {
        // Initialize the database
        listNominalWallThicknessRepository.saveAndFlush(listNominalWallThickness);

        // Get all the listNominalWallThicknessList where name in DEFAULT_NAME or UPDATED_NAME
        defaultListNominalWallThicknessShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the listNominalWallThicknessList where name equals to UPDATED_NAME
        defaultListNominalWallThicknessShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllListNominalWallThicknessesByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listNominalWallThicknessRepository.saveAndFlush(listNominalWallThickness);

        // Get all the listNominalWallThicknessList where name is not null
        defaultListNominalWallThicknessShouldBeFound("name.specified=true");

        // Get all the listNominalWallThicknessList where name is null
        defaultListNominalWallThicknessShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    public void getAllListNominalWallThicknessesByFullNameIsEqualToSomething() throws Exception {
        // Initialize the database
        listNominalWallThicknessRepository.saveAndFlush(listNominalWallThickness);

        // Get all the listNominalWallThicknessList where fullName equals to DEFAULT_FULL_NAME
        defaultListNominalWallThicknessShouldBeFound("fullName.equals=" + DEFAULT_FULL_NAME);

        // Get all the listNominalWallThicknessList where fullName equals to UPDATED_FULL_NAME
        defaultListNominalWallThicknessShouldNotBeFound("fullName.equals=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListNominalWallThicknessesByFullNameIsInShouldWork() throws Exception {
        // Initialize the database
        listNominalWallThicknessRepository.saveAndFlush(listNominalWallThickness);

        // Get all the listNominalWallThicknessList where fullName in DEFAULT_FULL_NAME or UPDATED_FULL_NAME
        defaultListNominalWallThicknessShouldBeFound("fullName.in=" + DEFAULT_FULL_NAME + "," + UPDATED_FULL_NAME);

        // Get all the listNominalWallThicknessList where fullName equals to UPDATED_FULL_NAME
        defaultListNominalWallThicknessShouldNotBeFound("fullName.in=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    public void getAllListNominalWallThicknessesByFullNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        listNominalWallThicknessRepository.saveAndFlush(listNominalWallThickness);

        // Get all the listNominalWallThicknessList where fullName is not null
        defaultListNominalWallThicknessShouldBeFound("fullName.specified=true");

        // Get all the listNominalWallThicknessList where fullName is null
        defaultListNominalWallThicknessShouldNotBeFound("fullName.specified=false");
    }

    @Test
    @Transactional
    public void getAllListNominalWallThicknessesByIsCurrentFlagIsEqualToSomething() throws Exception {
        // Initialize the database
        listNominalWallThicknessRepository.saveAndFlush(listNominalWallThickness);

        // Get all the listNominalWallThicknessList where isCurrentFlag equals to DEFAULT_IS_CURRENT_FLAG
        defaultListNominalWallThicknessShouldBeFound("isCurrentFlag.equals=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listNominalWallThicknessList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListNominalWallThicknessShouldNotBeFound("isCurrentFlag.equals=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListNominalWallThicknessesByIsCurrentFlagIsInShouldWork() throws Exception {
        // Initialize the database
        listNominalWallThicknessRepository.saveAndFlush(listNominalWallThickness);

        // Get all the listNominalWallThicknessList where isCurrentFlag in DEFAULT_IS_CURRENT_FLAG or UPDATED_IS_CURRENT_FLAG
        defaultListNominalWallThicknessShouldBeFound("isCurrentFlag.in=" + DEFAULT_IS_CURRENT_FLAG + "," + UPDATED_IS_CURRENT_FLAG);

        // Get all the listNominalWallThicknessList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultListNominalWallThicknessShouldNotBeFound("isCurrentFlag.in=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListNominalWallThicknessesByIsCurrentFlagIsNullOrNotNull() throws Exception {
        // Initialize the database
        listNominalWallThicknessRepository.saveAndFlush(listNominalWallThickness);

        // Get all the listNominalWallThicknessList where isCurrentFlag is not null
        defaultListNominalWallThicknessShouldBeFound("isCurrentFlag.specified=true");

        // Get all the listNominalWallThicknessList where isCurrentFlag is null
        defaultListNominalWallThicknessShouldNotBeFound("isCurrentFlag.specified=false");
    }

    @Test
    @Transactional
    public void getAllListNominalWallThicknessesByIsCurrentFlagIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        listNominalWallThicknessRepository.saveAndFlush(listNominalWallThickness);

        // Get all the listNominalWallThicknessList where isCurrentFlag greater than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListNominalWallThicknessShouldBeFound("isCurrentFlag.greaterOrEqualThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listNominalWallThicknessList where isCurrentFlag greater than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListNominalWallThicknessShouldNotBeFound("isCurrentFlag.greaterOrEqualThan=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllListNominalWallThicknessesByIsCurrentFlagIsLessThanSomething() throws Exception {
        // Initialize the database
        listNominalWallThicknessRepository.saveAndFlush(listNominalWallThickness);

        // Get all the listNominalWallThicknessList where isCurrentFlag less than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultListNominalWallThicknessShouldNotBeFound("isCurrentFlag.lessThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the listNominalWallThicknessList where isCurrentFlag less than or equals to UPDATED_IS_CURRENT_FLAG
        defaultListNominalWallThicknessShouldBeFound("isCurrentFlag.lessThan=" + UPDATED_IS_CURRENT_FLAG);
    }


    @Test
    @Transactional
    public void getAllListNominalWallThicknessesByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        listNominalWallThicknessRepository.saveAndFlush(listNominalWallThickness);

        // Get all the listNominalWallThicknessList where description equals to DEFAULT_DESCRIPTION
        defaultListNominalWallThicknessShouldBeFound("description.equals=" + DEFAULT_DESCRIPTION);

        // Get all the listNominalWallThicknessList where description equals to UPDATED_DESCRIPTION
        defaultListNominalWallThicknessShouldNotBeFound("description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListNominalWallThicknessesByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        listNominalWallThicknessRepository.saveAndFlush(listNominalWallThickness);

        // Get all the listNominalWallThicknessList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
        defaultListNominalWallThicknessShouldBeFound("description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION);

        // Get all the listNominalWallThicknessList where description equals to UPDATED_DESCRIPTION
        defaultListNominalWallThicknessShouldNotBeFound("description.in=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllListNominalWallThicknessesByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        listNominalWallThicknessRepository.saveAndFlush(listNominalWallThickness);

        // Get all the listNominalWallThicknessList where description is not null
        defaultListNominalWallThicknessShouldBeFound("description.specified=true");

        // Get all the listNominalWallThicknessList where description is null
        defaultListNominalWallThicknessShouldNotBeFound("description.specified=false");
    }

    @Test
    @Transactional
    public void getAllListNominalWallThicknessesByDateCreateIsEqualToSomething() throws Exception {
        // Initialize the database
        listNominalWallThicknessRepository.saveAndFlush(listNominalWallThickness);

        // Get all the listNominalWallThicknessList where dateCreate equals to DEFAULT_DATE_CREATE
        defaultListNominalWallThicknessShouldBeFound("dateCreate.equals=" + DEFAULT_DATE_CREATE);

        // Get all the listNominalWallThicknessList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListNominalWallThicknessShouldNotBeFound("dateCreate.equals=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListNominalWallThicknessesByDateCreateIsInShouldWork() throws Exception {
        // Initialize the database
        listNominalWallThicknessRepository.saveAndFlush(listNominalWallThickness);

        // Get all the listNominalWallThicknessList where dateCreate in DEFAULT_DATE_CREATE or UPDATED_DATE_CREATE
        defaultListNominalWallThicknessShouldBeFound("dateCreate.in=" + DEFAULT_DATE_CREATE + "," + UPDATED_DATE_CREATE);

        // Get all the listNominalWallThicknessList where dateCreate equals to UPDATED_DATE_CREATE
        defaultListNominalWallThicknessShouldNotBeFound("dateCreate.in=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllListNominalWallThicknessesByDateCreateIsNullOrNotNull() throws Exception {
        // Initialize the database
        listNominalWallThicknessRepository.saveAndFlush(listNominalWallThickness);

        // Get all the listNominalWallThicknessList where dateCreate is not null
        defaultListNominalWallThicknessShouldBeFound("dateCreate.specified=true");

        // Get all the listNominalWallThicknessList where dateCreate is null
        defaultListNominalWallThicknessShouldNotBeFound("dateCreate.specified=false");
    }

    @Test
    @Transactional
    public void getAllListNominalWallThicknessesByDateEditIsEqualToSomething() throws Exception {
        // Initialize the database
        listNominalWallThicknessRepository.saveAndFlush(listNominalWallThickness);

        // Get all the listNominalWallThicknessList where dateEdit equals to DEFAULT_DATE_EDIT
        defaultListNominalWallThicknessShouldBeFound("dateEdit.equals=" + DEFAULT_DATE_EDIT);

        // Get all the listNominalWallThicknessList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListNominalWallThicknessShouldNotBeFound("dateEdit.equals=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListNominalWallThicknessesByDateEditIsInShouldWork() throws Exception {
        // Initialize the database
        listNominalWallThicknessRepository.saveAndFlush(listNominalWallThickness);

        // Get all the listNominalWallThicknessList where dateEdit in DEFAULT_DATE_EDIT or UPDATED_DATE_EDIT
        defaultListNominalWallThicknessShouldBeFound("dateEdit.in=" + DEFAULT_DATE_EDIT + "," + UPDATED_DATE_EDIT);

        // Get all the listNominalWallThicknessList where dateEdit equals to UPDATED_DATE_EDIT
        defaultListNominalWallThicknessShouldNotBeFound("dateEdit.in=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllListNominalWallThicknessesByDateEditIsNullOrNotNull() throws Exception {
        // Initialize the database
        listNominalWallThicknessRepository.saveAndFlush(listNominalWallThickness);

        // Get all the listNominalWallThicknessList where dateEdit is not null
        defaultListNominalWallThicknessShouldBeFound("dateEdit.specified=true");

        // Get all the listNominalWallThicknessList where dateEdit is null
        defaultListNominalWallThicknessShouldNotBeFound("dateEdit.specified=false");
    }

    @Test
    @Transactional
    public void getAllListNominalWallThicknessesByCreatorIsEqualToSomething() throws Exception {
        // Initialize the database
        listNominalWallThicknessRepository.saveAndFlush(listNominalWallThickness);

        // Get all the listNominalWallThicknessList where creator equals to DEFAULT_CREATOR
        defaultListNominalWallThicknessShouldBeFound("creator.equals=" + DEFAULT_CREATOR);

        // Get all the listNominalWallThicknessList where creator equals to UPDATED_CREATOR
        defaultListNominalWallThicknessShouldNotBeFound("creator.equals=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListNominalWallThicknessesByCreatorIsInShouldWork() throws Exception {
        // Initialize the database
        listNominalWallThicknessRepository.saveAndFlush(listNominalWallThickness);

        // Get all the listNominalWallThicknessList where creator in DEFAULT_CREATOR or UPDATED_CREATOR
        defaultListNominalWallThicknessShouldBeFound("creator.in=" + DEFAULT_CREATOR + "," + UPDATED_CREATOR);

        // Get all the listNominalWallThicknessList where creator equals to UPDATED_CREATOR
        defaultListNominalWallThicknessShouldNotBeFound("creator.in=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllListNominalWallThicknessesByCreatorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listNominalWallThicknessRepository.saveAndFlush(listNominalWallThickness);

        // Get all the listNominalWallThicknessList where creator is not null
        defaultListNominalWallThicknessShouldBeFound("creator.specified=true");

        // Get all the listNominalWallThicknessList where creator is null
        defaultListNominalWallThicknessShouldNotBeFound("creator.specified=false");
    }

    @Test
    @Transactional
    public void getAllListNominalWallThicknessesByEditorIsEqualToSomething() throws Exception {
        // Initialize the database
        listNominalWallThicknessRepository.saveAndFlush(listNominalWallThickness);

        // Get all the listNominalWallThicknessList where editor equals to DEFAULT_EDITOR
        defaultListNominalWallThicknessShouldBeFound("editor.equals=" + DEFAULT_EDITOR);

        // Get all the listNominalWallThicknessList where editor equals to UPDATED_EDITOR
        defaultListNominalWallThicknessShouldNotBeFound("editor.equals=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListNominalWallThicknessesByEditorIsInShouldWork() throws Exception {
        // Initialize the database
        listNominalWallThicknessRepository.saveAndFlush(listNominalWallThickness);

        // Get all the listNominalWallThicknessList where editor in DEFAULT_EDITOR or UPDATED_EDITOR
        defaultListNominalWallThicknessShouldBeFound("editor.in=" + DEFAULT_EDITOR + "," + UPDATED_EDITOR);

        // Get all the listNominalWallThicknessList where editor equals to UPDATED_EDITOR
        defaultListNominalWallThicknessShouldNotBeFound("editor.in=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllListNominalWallThicknessesByEditorIsNullOrNotNull() throws Exception {
        // Initialize the database
        listNominalWallThicknessRepository.saveAndFlush(listNominalWallThickness);

        // Get all the listNominalWallThicknessList where editor is not null
        defaultListNominalWallThicknessShouldBeFound("editor.specified=true");

        // Get all the listNominalWallThicknessList where editor is null
        defaultListNominalWallThicknessShouldNotBeFound("editor.specified=false");
    }

    @Test
    @Transactional
    public void getAllListNominalWallThicknessesByBendHistIsEqualToSomething() throws Exception {
        // Initialize the database
        BendHist bendHist = BendHistResourceIT.createEntity(em);
        em.persist(bendHist);
        em.flush();
        listNominalWallThickness.addBendHist(bendHist);
        listNominalWallThicknessRepository.saveAndFlush(listNominalWallThickness);
        Long bendHistId = bendHist.getId();

        // Get all the listNominalWallThicknessList where bendHist equals to bendHistId
        defaultListNominalWallThicknessShouldBeFound("bendHistId.equals=" + bendHistId);

        // Get all the listNominalWallThicknessList where bendHist equals to bendHistId + 1
        defaultListNominalWallThicknessShouldNotBeFound("bendHistId.equals=" + (bendHistId + 1));
    }


    @Test
    @Transactional
    public void getAllListNominalWallThicknessesByBuckleArrestorHistIsEqualToSomething() throws Exception {
        // Initialize the database
        BuckleArrestorHist buckleArrestorHist = BuckleArrestorHistResourceIT.createEntity(em);
        em.persist(buckleArrestorHist);
        em.flush();
        listNominalWallThickness.addBuckleArrestorHist(buckleArrestorHist);
        listNominalWallThicknessRepository.saveAndFlush(listNominalWallThickness);
        Long buckleArrestorHistId = buckleArrestorHist.getId();

        // Get all the listNominalWallThicknessList where buckleArrestorHist equals to buckleArrestorHistId
        defaultListNominalWallThicknessShouldBeFound("buckleArrestorHistId.equals=" + buckleArrestorHistId);

        // Get all the listNominalWallThicknessList where buckleArrestorHist equals to buckleArrestorHistId + 1
        defaultListNominalWallThicknessShouldNotBeFound("buckleArrestorHistId.equals=" + (buckleArrestorHistId + 1));
    }


    @Test
    @Transactional
    public void getAllListNominalWallThicknessesByPipeHistIsEqualToSomething() throws Exception {
        // Initialize the database
        PipeHist pipeHist = PipeHistResourceIT.createEntity(em);
        em.persist(pipeHist);
        em.flush();
        listNominalWallThickness.addPipeHist(pipeHist);
        listNominalWallThicknessRepository.saveAndFlush(listNominalWallThickness);
        Long pipeHistId = pipeHist.getId();

        // Get all the listNominalWallThicknessList where pipeHist equals to pipeHistId
        defaultListNominalWallThicknessShouldBeFound("pipeHistId.equals=" + pipeHistId);

        // Get all the listNominalWallThicknessList where pipeHist equals to pipeHistId + 1
        defaultListNominalWallThicknessShouldNotBeFound("pipeHistId.equals=" + (pipeHistId + 1));
    }


    @Test
    @Transactional
    public void getAllListNominalWallThicknessesByTeeHistIsEqualToSomething() throws Exception {
        // Initialize the database
        TeeHist teeHist = TeeHistResourceIT.createEntity(em);
        em.persist(teeHist);
        em.flush();
        listNominalWallThickness.addTeeHist(teeHist);
        listNominalWallThicknessRepository.saveAndFlush(listNominalWallThickness);
        Long teeHistId = teeHist.getId();

        // Get all the listNominalWallThicknessList where teeHist equals to teeHistId
        defaultListNominalWallThicknessShouldBeFound("teeHistId.equals=" + teeHistId);

        // Get all the listNominalWallThicknessList where teeHist equals to teeHistId + 1
        defaultListNominalWallThicknessShouldNotBeFound("teeHistId.equals=" + (teeHistId + 1));
    }


    @Test
    @Transactional
    public void getAllListNominalWallThicknessesByValveHistIsEqualToSomething() throws Exception {
        // Initialize the database
        ValveHist valveHist = ValveHistResourceIT.createEntity(em);
        em.persist(valveHist);
        em.flush();
        listNominalWallThickness.addValveHist(valveHist);
        listNominalWallThicknessRepository.saveAndFlush(listNominalWallThickness);
        Long valveHistId = valveHist.getId();

        // Get all the listNominalWallThicknessList where valveHist equals to valveHistId
        defaultListNominalWallThicknessShouldBeFound("valveHistId.equals=" + valveHistId);

        // Get all the listNominalWallThicknessList where valveHist equals to valveHistId + 1
        defaultListNominalWallThicknessShouldNotBeFound("valveHistId.equals=" + (valveHistId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultListNominalWallThicknessShouldBeFound(String filter) throws Exception {
        restListNominalWallThicknessMockMvc.perform(get("/api/list-nominal-wall-thicknesses?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(listNominalWallThickness.getId().intValue())))
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
        restListNominalWallThicknessMockMvc.perform(get("/api/list-nominal-wall-thicknesses/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultListNominalWallThicknessShouldNotBeFound(String filter) throws Exception {
        restListNominalWallThicknessMockMvc.perform(get("/api/list-nominal-wall-thicknesses?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restListNominalWallThicknessMockMvc.perform(get("/api/list-nominal-wall-thicknesses/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingListNominalWallThickness() throws Exception {
        // Get the listNominalWallThickness
        restListNominalWallThicknessMockMvc.perform(get("/api/list-nominal-wall-thicknesses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateListNominalWallThickness() throws Exception {
        // Initialize the database
        listNominalWallThicknessRepository.saveAndFlush(listNominalWallThickness);

        int databaseSizeBeforeUpdate = listNominalWallThicknessRepository.findAll().size();

        // Update the listNominalWallThickness
        ListNominalWallThickness updatedListNominalWallThickness = listNominalWallThicknessRepository.findById(listNominalWallThickness.getId()).get();
        // Disconnect from session so that the updates on updatedListNominalWallThickness are not directly saved in db
        em.detach(updatedListNominalWallThickness);
        updatedListNominalWallThickness
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .fullName(UPDATED_FULL_NAME)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        ListNominalWallThicknessDTO listNominalWallThicknessDTO = listNominalWallThicknessMapper.toDto(updatedListNominalWallThickness);

        restListNominalWallThicknessMockMvc.perform(put("/api/list-nominal-wall-thicknesses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listNominalWallThicknessDTO)))
            .andExpect(status().isOk());

        // Validate the ListNominalWallThickness in the database
        List<ListNominalWallThickness> listNominalWallThicknessList = listNominalWallThicknessRepository.findAll();
        assertThat(listNominalWallThicknessList).hasSize(databaseSizeBeforeUpdate);
        ListNominalWallThickness testListNominalWallThickness = listNominalWallThicknessList.get(listNominalWallThicknessList.size() - 1);
        assertThat(testListNominalWallThickness.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testListNominalWallThickness.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testListNominalWallThickness.getFullName()).isEqualTo(UPDATED_FULL_NAME);
        assertThat(testListNominalWallThickness.getIsCurrentFlag()).isEqualTo(UPDATED_IS_CURRENT_FLAG);
        assertThat(testListNominalWallThickness.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testListNominalWallThickness.getDateCreate()).isEqualTo(UPDATED_DATE_CREATE);
        assertThat(testListNominalWallThickness.getDateEdit()).isEqualTo(UPDATED_DATE_EDIT);
        assertThat(testListNominalWallThickness.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testListNominalWallThickness.getEditor()).isEqualTo(UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void updateNonExistingListNominalWallThickness() throws Exception {
        int databaseSizeBeforeUpdate = listNominalWallThicknessRepository.findAll().size();

        // Create the ListNominalWallThickness
        ListNominalWallThicknessDTO listNominalWallThicknessDTO = listNominalWallThicknessMapper.toDto(listNominalWallThickness);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restListNominalWallThicknessMockMvc.perform(put("/api/list-nominal-wall-thicknesses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(listNominalWallThicknessDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ListNominalWallThickness in the database
        List<ListNominalWallThickness> listNominalWallThicknessList = listNominalWallThicknessRepository.findAll();
        assertThat(listNominalWallThicknessList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteListNominalWallThickness() throws Exception {
        // Initialize the database
        listNominalWallThicknessRepository.saveAndFlush(listNominalWallThickness);

        int databaseSizeBeforeDelete = listNominalWallThicknessRepository.findAll().size();

        // Delete the listNominalWallThickness
        restListNominalWallThicknessMockMvc.perform(delete("/api/list-nominal-wall-thicknesses/{id}", listNominalWallThickness.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<ListNominalWallThickness> listNominalWallThicknessList = listNominalWallThicknessRepository.findAll();
        assertThat(listNominalWallThicknessList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListNominalWallThickness.class);
        ListNominalWallThickness listNominalWallThickness1 = new ListNominalWallThickness();
        listNominalWallThickness1.setId(1L);
        ListNominalWallThickness listNominalWallThickness2 = new ListNominalWallThickness();
        listNominalWallThickness2.setId(listNominalWallThickness1.getId());
        assertThat(listNominalWallThickness1).isEqualTo(listNominalWallThickness2);
        listNominalWallThickness2.setId(2L);
        assertThat(listNominalWallThickness1).isNotEqualTo(listNominalWallThickness2);
        listNominalWallThickness1.setId(null);
        assertThat(listNominalWallThickness1).isNotEqualTo(listNominalWallThickness2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ListNominalWallThicknessDTO.class);
        ListNominalWallThicknessDTO listNominalWallThicknessDTO1 = new ListNominalWallThicknessDTO();
        listNominalWallThicknessDTO1.setId(1L);
        ListNominalWallThicknessDTO listNominalWallThicknessDTO2 = new ListNominalWallThicknessDTO();
        assertThat(listNominalWallThicknessDTO1).isNotEqualTo(listNominalWallThicknessDTO2);
        listNominalWallThicknessDTO2.setId(listNominalWallThicknessDTO1.getId());
        assertThat(listNominalWallThicknessDTO1).isEqualTo(listNominalWallThicknessDTO2);
        listNominalWallThicknessDTO2.setId(2L);
        assertThat(listNominalWallThicknessDTO1).isNotEqualTo(listNominalWallThicknessDTO2);
        listNominalWallThicknessDTO1.setId(null);
        assertThat(listNominalWallThicknessDTO1).isNotEqualTo(listNominalWallThicknessDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(listNominalWallThicknessMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(listNominalWallThicknessMapper.fromId(null)).isNull();
    }
}
