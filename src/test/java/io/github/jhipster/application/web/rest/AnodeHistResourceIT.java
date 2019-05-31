package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;
import io.github.jhipster.application.domain.AnodeHist;
import io.github.jhipster.application.domain.Anode;
import io.github.jhipster.application.domain.PipelineSection;
import io.github.jhipster.application.domain.ListAnodeBraceleteType;
import io.github.jhipster.application.domain.ListMaterial;
import io.github.jhipster.application.domain.ListWrkStatus;
import io.github.jhipster.application.repository.AnodeHistRepository;
import io.github.jhipster.application.service.AnodeHistService;
import io.github.jhipster.application.service.dto.AnodeHistDTO;
import io.github.jhipster.application.service.mapper.AnodeHistMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.AnodeHistCriteria;
import io.github.jhipster.application.service.AnodeHistQueryService;

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
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@Link AnodeHistResource} REST controller.
 */
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class AnodeHistResourceIT {

    private static final LocalDate DEFAULT_DATE_FROM = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_FROM = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATE_TO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_TO = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_ID_WRK = 1L;
    private static final Long UPDATED_ID_WRK = 2L;

    private static final Integer DEFAULT_DESIGN_LIFE = 1;
    private static final Integer UPDATED_DESIGN_LIFE = 2;

    private static final BigDecimal DEFAULT_DMCD = new BigDecimal(1);
    private static final BigDecimal UPDATED_DMCD = new BigDecimal(2);

    private static final BigDecimal DEFAULT_L_1 = new BigDecimal(1);
    private static final BigDecimal UPDATED_L_1 = new BigDecimal(2);

    private static final BigDecimal DEFAULT_L_2 = new BigDecimal(1);
    private static final BigDecimal UPDATED_L_2 = new BigDecimal(2);

    private static final Integer DEFAULT_LENGTH = 1;
    private static final Integer UPDATED_LENGTH = 2;

    private static final BigDecimal DEFAULT_ELECTR_CAPAC = new BigDecimal(1);
    private static final BigDecimal UPDATED_ELECTR_CAPAC = new BigDecimal(2);

    private static final BigDecimal DEFAULT_DESIGN_WEIGHT = new BigDecimal(1);
    private static final BigDecimal UPDATED_DESIGN_WEIGHT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_WEIGHT = new BigDecimal(1);
    private static final BigDecimal UPDATED_WEIGHT = new BigDecimal(2);

    private static final Integer DEFAULT_IS_BURIAL = 1;
    private static final Integer UPDATED_IS_BURIAL = 2;

    private static final String DEFAULT_CHEMICAL_COMPOSITION = "AAAAAAAAAA";
    private static final String UPDATED_CHEMICAL_COMPOSITION = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_DENSITY = new BigDecimal(1);
    private static final BigDecimal UPDATED_DENSITY = new BigDecimal(2);

    private static final Integer DEFAULT_SPACING = 1;
    private static final Integer UPDATED_SPACING = 2;

    private static final Integer DEFAULT_COAT_CUTBACK_LENGTH = 1;
    private static final Integer UPDATED_COAT_CUTBACK_LENGTH = 2;

    private static final BigDecimal DEFAULT_COAT_DMG_AREA = new BigDecimal(1);
    private static final BigDecimal UPDATED_COAT_DMG_AREA = new BigDecimal(2);

    private static final BigDecimal DEFAULT_H_2_S_SOIL = new BigDecimal(1);
    private static final BigDecimal UPDATED_H_2_S_SOIL = new BigDecimal(2);

    private static final BigDecimal DEFAULT_REMAIN = new BigDecimal(1);
    private static final BigDecimal UPDATED_REMAIN = new BigDecimal(2);

    private static final BigDecimal DEFAULT_INT_FLD_TEMP = new BigDecimal(1);
    private static final BigDecimal UPDATED_INT_FLD_TEMP = new BigDecimal(2);

    private static final BigDecimal DEFAULT_MIN_PRC = new BigDecimal(1);
    private static final BigDecimal UPDATED_MIN_PRC = new BigDecimal(2);

    private static final BigDecimal DEFAULT_THICKNESS = new BigDecimal(1);
    private static final BigDecimal UPDATED_THICKNESS = new BigDecimal(2);

    private static final String DEFAULT_COORD = "AAAAAAAAAA";
    private static final String UPDATED_COORD = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_KP_START = new BigDecimal(1);
    private static final BigDecimal UPDATED_KP_START = new BigDecimal(2);

    private static final BigDecimal DEFAULT_COAT_THICKNESS = new BigDecimal(1);
    private static final BigDecimal UPDATED_COAT_THICKNESS = new BigDecimal(2);

    private static final BigDecimal DEFAULT_KP_END = new BigDecimal(1);
    private static final BigDecimal UPDATED_KP_END = new BigDecimal(2);

    private static final Integer DEFAULT_IS_CURRENT_FLAG = 1;
    private static final Integer UPDATED_IS_CURRENT_FLAG = 2;

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_COMMENT = "AAAAAAAAAA";
    private static final String UPDATED_COMMENT = "BBBBBBBBBB";

    private static final Instant DEFAULT_DATE_CREATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_CREATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_DATE_EDIT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_EDIT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CREATOR = "AAAAAAAAAA";
    private static final String UPDATED_CREATOR = "BBBBBBBBBB";

    private static final String DEFAULT_EDITOR = "AAAAAAAAAA";
    private static final String UPDATED_EDITOR = "BBBBBBBBBB";

    @Autowired
    private AnodeHistRepository anodeHistRepository;

    @Autowired
    private AnodeHistMapper anodeHistMapper;

    @Autowired
    private AnodeHistService anodeHistService;

    @Autowired
    private AnodeHistQueryService anodeHistQueryService;

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

    private MockMvc restAnodeHistMockMvc;

    private AnodeHist anodeHist;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AnodeHistResource anodeHistResource = new AnodeHistResource(anodeHistService, anodeHistQueryService);
        this.restAnodeHistMockMvc = MockMvcBuilders.standaloneSetup(anodeHistResource)
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
    public static AnodeHist createEntity(EntityManager em) {
        AnodeHist anodeHist = new AnodeHist()
            .dateFrom(DEFAULT_DATE_FROM)
            .dateTo(DEFAULT_DATE_TO)
            .idWrk(DEFAULT_ID_WRK)
            .designLife(DEFAULT_DESIGN_LIFE)
            .dmcd(DEFAULT_DMCD)
            .l1(DEFAULT_L_1)
            .l2(DEFAULT_L_2)
            .length(DEFAULT_LENGTH)
            .electrCapac(DEFAULT_ELECTR_CAPAC)
            .designWeight(DEFAULT_DESIGN_WEIGHT)
            .weight(DEFAULT_WEIGHT)
            .isBurial(DEFAULT_IS_BURIAL)
            .chemicalComposition(DEFAULT_CHEMICAL_COMPOSITION)
            .density(DEFAULT_DENSITY)
            .spacing(DEFAULT_SPACING)
            .coatCutbackLength(DEFAULT_COAT_CUTBACK_LENGTH)
            .coatDmgArea(DEFAULT_COAT_DMG_AREA)
            .h2sSoil(DEFAULT_H_2_S_SOIL)
            .remain(DEFAULT_REMAIN)
            .intFldTemp(DEFAULT_INT_FLD_TEMP)
            .minPrc(DEFAULT_MIN_PRC)
            .thickness(DEFAULT_THICKNESS)
            .coord(DEFAULT_COORD)
            .kpStart(DEFAULT_KP_START)
            .coatThickness(DEFAULT_COAT_THICKNESS)
            .kpEnd(DEFAULT_KP_END)
            .isCurrentFlag(DEFAULT_IS_CURRENT_FLAG)
            .description(DEFAULT_DESCRIPTION)
            .comment(DEFAULT_COMMENT)
            .dateCreate(DEFAULT_DATE_CREATE)
            .dateEdit(DEFAULT_DATE_EDIT)
            .creator(DEFAULT_CREATOR)
            .editor(DEFAULT_EDITOR);
        // Add required entity
        Anode anode;
        if (TestUtil.findAll(em, Anode.class).isEmpty()) {
            anode = AnodeResourceIT.createEntity(em);
            em.persist(anode);
            em.flush();
        } else {
            anode = TestUtil.findAll(em, Anode.class).get(0);
        }
        anodeHist.setId(anode);
        // Add required entity
        PipelineSection pipelineSection;
        if (TestUtil.findAll(em, PipelineSection.class).isEmpty()) {
            pipelineSection = PipelineSectionResourceIT.createEntity(em);
            em.persist(pipelineSection);
            em.flush();
        } else {
            pipelineSection = TestUtil.findAll(em, PipelineSection.class).get(0);
        }
        anodeHist.setIdPipelineSection(pipelineSection);
        // Add required entity
        ListWrkStatus listWrkStatus;
        if (TestUtil.findAll(em, ListWrkStatus.class).isEmpty()) {
            listWrkStatus = ListWrkStatusResourceIT.createEntity(em);
            em.persist(listWrkStatus);
            em.flush();
        } else {
            listWrkStatus = TestUtil.findAll(em, ListWrkStatus.class).get(0);
        }
        anodeHist.setIdStatus(listWrkStatus);
        return anodeHist;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AnodeHist createUpdatedEntity(EntityManager em) {
        AnodeHist anodeHist = new AnodeHist()
            .dateFrom(UPDATED_DATE_FROM)
            .dateTo(UPDATED_DATE_TO)
            .idWrk(UPDATED_ID_WRK)
            .designLife(UPDATED_DESIGN_LIFE)
            .dmcd(UPDATED_DMCD)
            .l1(UPDATED_L_1)
            .l2(UPDATED_L_2)
            .length(UPDATED_LENGTH)
            .electrCapac(UPDATED_ELECTR_CAPAC)
            .designWeight(UPDATED_DESIGN_WEIGHT)
            .weight(UPDATED_WEIGHT)
            .isBurial(UPDATED_IS_BURIAL)
            .chemicalComposition(UPDATED_CHEMICAL_COMPOSITION)
            .density(UPDATED_DENSITY)
            .spacing(UPDATED_SPACING)
            .coatCutbackLength(UPDATED_COAT_CUTBACK_LENGTH)
            .coatDmgArea(UPDATED_COAT_DMG_AREA)
            .h2sSoil(UPDATED_H_2_S_SOIL)
            .remain(UPDATED_REMAIN)
            .intFldTemp(UPDATED_INT_FLD_TEMP)
            .minPrc(UPDATED_MIN_PRC)
            .thickness(UPDATED_THICKNESS)
            .coord(UPDATED_COORD)
            .kpStart(UPDATED_KP_START)
            .coatThickness(UPDATED_COAT_THICKNESS)
            .kpEnd(UPDATED_KP_END)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .comment(UPDATED_COMMENT)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        // Add required entity
        Anode anode;
        if (TestUtil.findAll(em, Anode.class).isEmpty()) {
            anode = AnodeResourceIT.createUpdatedEntity(em);
            em.persist(anode);
            em.flush();
        } else {
            anode = TestUtil.findAll(em, Anode.class).get(0);
        }
        anodeHist.setId(anode);
        // Add required entity
        PipelineSection pipelineSection;
        if (TestUtil.findAll(em, PipelineSection.class).isEmpty()) {
            pipelineSection = PipelineSectionResourceIT.createUpdatedEntity(em);
            em.persist(pipelineSection);
            em.flush();
        } else {
            pipelineSection = TestUtil.findAll(em, PipelineSection.class).get(0);
        }
        anodeHist.setIdPipelineSection(pipelineSection);
        // Add required entity
        ListWrkStatus listWrkStatus;
        if (TestUtil.findAll(em, ListWrkStatus.class).isEmpty()) {
            listWrkStatus = ListWrkStatusResourceIT.createUpdatedEntity(em);
            em.persist(listWrkStatus);
            em.flush();
        } else {
            listWrkStatus = TestUtil.findAll(em, ListWrkStatus.class).get(0);
        }
        anodeHist.setIdStatus(listWrkStatus);
        return anodeHist;
    }

    @BeforeEach
    public void initTest() {
        anodeHist = createEntity(em);
    }

    @Test
    @Transactional
    public void createAnodeHist() throws Exception {
        int databaseSizeBeforeCreate = anodeHistRepository.findAll().size();

        // Create the AnodeHist
        AnodeHistDTO anodeHistDTO = anodeHistMapper.toDto(anodeHist);
        restAnodeHistMockMvc.perform(post("/api/anode-hists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(anodeHistDTO)))
            .andExpect(status().isCreated());

        // Validate the AnodeHist in the database
        List<AnodeHist> anodeHistList = anodeHistRepository.findAll();
        assertThat(anodeHistList).hasSize(databaseSizeBeforeCreate + 1);
        AnodeHist testAnodeHist = anodeHistList.get(anodeHistList.size() - 1);
        assertThat(testAnodeHist.getDateFrom()).isEqualTo(DEFAULT_DATE_FROM);
        assertThat(testAnodeHist.getDateTo()).isEqualTo(DEFAULT_DATE_TO);
        assertThat(testAnodeHist.getIdWrk()).isEqualTo(DEFAULT_ID_WRK);
        assertThat(testAnodeHist.getDesignLife()).isEqualTo(DEFAULT_DESIGN_LIFE);
        assertThat(testAnodeHist.getDmcd()).isEqualTo(DEFAULT_DMCD);
        assertThat(testAnodeHist.getl1()).isEqualTo(DEFAULT_L_1);
        assertThat(testAnodeHist.getl2()).isEqualTo(DEFAULT_L_2);
        assertThat(testAnodeHist.getLength()).isEqualTo(DEFAULT_LENGTH);
        assertThat(testAnodeHist.getElectrCapac()).isEqualTo(DEFAULT_ELECTR_CAPAC);
        assertThat(testAnodeHist.getDesignWeight()).isEqualTo(DEFAULT_DESIGN_WEIGHT);
        assertThat(testAnodeHist.getWeight()).isEqualTo(DEFAULT_WEIGHT);
        assertThat(testAnodeHist.getIsBurial()).isEqualTo(DEFAULT_IS_BURIAL);
        assertThat(testAnodeHist.getChemicalComposition()).isEqualTo(DEFAULT_CHEMICAL_COMPOSITION);
        assertThat(testAnodeHist.getDensity()).isEqualTo(DEFAULT_DENSITY);
        assertThat(testAnodeHist.getSpacing()).isEqualTo(DEFAULT_SPACING);
        assertThat(testAnodeHist.getCoatCutbackLength()).isEqualTo(DEFAULT_COAT_CUTBACK_LENGTH);
        assertThat(testAnodeHist.getCoatDmgArea()).isEqualTo(DEFAULT_COAT_DMG_AREA);
        assertThat(testAnodeHist.geth2sSoil()).isEqualTo(DEFAULT_H_2_S_SOIL);
        assertThat(testAnodeHist.getRemain()).isEqualTo(DEFAULT_REMAIN);
        assertThat(testAnodeHist.getIntFldTemp()).isEqualTo(DEFAULT_INT_FLD_TEMP);
        assertThat(testAnodeHist.getMinPrc()).isEqualTo(DEFAULT_MIN_PRC);
        assertThat(testAnodeHist.getThickness()).isEqualTo(DEFAULT_THICKNESS);
        assertThat(testAnodeHist.getCoord()).isEqualTo(DEFAULT_COORD);
        assertThat(testAnodeHist.getKpStart()).isEqualTo(DEFAULT_KP_START);
        assertThat(testAnodeHist.getCoatThickness()).isEqualTo(DEFAULT_COAT_THICKNESS);
        assertThat(testAnodeHist.getKpEnd()).isEqualTo(DEFAULT_KP_END);
        assertThat(testAnodeHist.getIsCurrentFlag()).isEqualTo(DEFAULT_IS_CURRENT_FLAG);
        assertThat(testAnodeHist.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testAnodeHist.getComment()).isEqualTo(DEFAULT_COMMENT);
        assertThat(testAnodeHist.getDateCreate()).isEqualTo(DEFAULT_DATE_CREATE);
        assertThat(testAnodeHist.getDateEdit()).isEqualTo(DEFAULT_DATE_EDIT);
        assertThat(testAnodeHist.getCreator()).isEqualTo(DEFAULT_CREATOR);
        assertThat(testAnodeHist.getEditor()).isEqualTo(DEFAULT_EDITOR);
    }

    @Test
    @Transactional
    public void createAnodeHistWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = anodeHistRepository.findAll().size();

        // Create the AnodeHist with an existing ID
        anodeHist.setId(1L);
        AnodeHistDTO anodeHistDTO = anodeHistMapper.toDto(anodeHist);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAnodeHistMockMvc.perform(post("/api/anode-hists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(anodeHistDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AnodeHist in the database
        List<AnodeHist> anodeHistList = anodeHistRepository.findAll();
        assertThat(anodeHistList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkDateFromIsRequired() throws Exception {
        int databaseSizeBeforeTest = anodeHistRepository.findAll().size();
        // set the field null
        anodeHist.setDateFrom(null);

        // Create the AnodeHist, which fails.
        AnodeHistDTO anodeHistDTO = anodeHistMapper.toDto(anodeHist);

        restAnodeHistMockMvc.perform(post("/api/anode-hists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(anodeHistDTO)))
            .andExpect(status().isBadRequest());

        List<AnodeHist> anodeHistList = anodeHistRepository.findAll();
        assertThat(anodeHistList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDateToIsRequired() throws Exception {
        int databaseSizeBeforeTest = anodeHistRepository.findAll().size();
        // set the field null
        anodeHist.setDateTo(null);

        // Create the AnodeHist, which fails.
        AnodeHistDTO anodeHistDTO = anodeHistMapper.toDto(anodeHist);

        restAnodeHistMockMvc.perform(post("/api/anode-hists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(anodeHistDTO)))
            .andExpect(status().isBadRequest());

        List<AnodeHist> anodeHistList = anodeHistRepository.findAll();
        assertThat(anodeHistList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsCurrentFlagIsRequired() throws Exception {
        int databaseSizeBeforeTest = anodeHistRepository.findAll().size();
        // set the field null
        anodeHist.setIsCurrentFlag(null);

        // Create the AnodeHist, which fails.
        AnodeHistDTO anodeHistDTO = anodeHistMapper.toDto(anodeHist);

        restAnodeHistMockMvc.perform(post("/api/anode-hists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(anodeHistDTO)))
            .andExpect(status().isBadRequest());

        List<AnodeHist> anodeHistList = anodeHistRepository.findAll();
        assertThat(anodeHistList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAnodeHists() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList
        restAnodeHistMockMvc.perform(get("/api/anode-hists?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(anodeHist.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateFrom").value(hasItem(DEFAULT_DATE_FROM.toString())))
            .andExpect(jsonPath("$.[*].dateTo").value(hasItem(DEFAULT_DATE_TO.toString())))
            .andExpect(jsonPath("$.[*].idWrk").value(hasItem(DEFAULT_ID_WRK.intValue())))
            .andExpect(jsonPath("$.[*].designLife").value(hasItem(DEFAULT_DESIGN_LIFE)))
            .andExpect(jsonPath("$.[*].dmcd").value(hasItem(DEFAULT_DMCD.intValue())))
            .andExpect(jsonPath("$.[*].l1").value(hasItem(DEFAULT_L_1.intValue())))
            .andExpect(jsonPath("$.[*].l2").value(hasItem(DEFAULT_L_2.intValue())))
            .andExpect(jsonPath("$.[*].length").value(hasItem(DEFAULT_LENGTH)))
            .andExpect(jsonPath("$.[*].electrCapac").value(hasItem(DEFAULT_ELECTR_CAPAC.intValue())))
            .andExpect(jsonPath("$.[*].designWeight").value(hasItem(DEFAULT_DESIGN_WEIGHT.intValue())))
            .andExpect(jsonPath("$.[*].weight").value(hasItem(DEFAULT_WEIGHT.intValue())))
            .andExpect(jsonPath("$.[*].isBurial").value(hasItem(DEFAULT_IS_BURIAL)))
            .andExpect(jsonPath("$.[*].chemicalComposition").value(hasItem(DEFAULT_CHEMICAL_COMPOSITION.toString())))
            .andExpect(jsonPath("$.[*].density").value(hasItem(DEFAULT_DENSITY.intValue())))
            .andExpect(jsonPath("$.[*].spacing").value(hasItem(DEFAULT_SPACING)))
            .andExpect(jsonPath("$.[*].coatCutbackLength").value(hasItem(DEFAULT_COAT_CUTBACK_LENGTH)))
            .andExpect(jsonPath("$.[*].coatDmgArea").value(hasItem(DEFAULT_COAT_DMG_AREA.intValue())))
            .andExpect(jsonPath("$.[*].h2sSoil").value(hasItem(DEFAULT_H_2_S_SOIL.intValue())))
            .andExpect(jsonPath("$.[*].remain").value(hasItem(DEFAULT_REMAIN.intValue())))
            .andExpect(jsonPath("$.[*].intFldTemp").value(hasItem(DEFAULT_INT_FLD_TEMP.intValue())))
            .andExpect(jsonPath("$.[*].minPrc").value(hasItem(DEFAULT_MIN_PRC.intValue())))
            .andExpect(jsonPath("$.[*].thickness").value(hasItem(DEFAULT_THICKNESS.intValue())))
            .andExpect(jsonPath("$.[*].coord").value(hasItem(DEFAULT_COORD.toString())))
            .andExpect(jsonPath("$.[*].kpStart").value(hasItem(DEFAULT_KP_START.intValue())))
            .andExpect(jsonPath("$.[*].coatThickness").value(hasItem(DEFAULT_COAT_THICKNESS.intValue())))
            .andExpect(jsonPath("$.[*].kpEnd").value(hasItem(DEFAULT_KP_END.intValue())))
            .andExpect(jsonPath("$.[*].isCurrentFlag").value(hasItem(DEFAULT_IS_CURRENT_FLAG)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].comment").value(hasItem(DEFAULT_COMMENT.toString())))
            .andExpect(jsonPath("$.[*].dateCreate").value(hasItem(DEFAULT_DATE_CREATE.toString())))
            .andExpect(jsonPath("$.[*].dateEdit").value(hasItem(DEFAULT_DATE_EDIT.toString())))
            .andExpect(jsonPath("$.[*].creator").value(hasItem(DEFAULT_CREATOR.toString())))
            .andExpect(jsonPath("$.[*].editor").value(hasItem(DEFAULT_EDITOR.toString())));
    }
    
    @Test
    @Transactional
    public void getAnodeHist() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get the anodeHist
        restAnodeHistMockMvc.perform(get("/api/anode-hists/{id}", anodeHist.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(anodeHist.getId().intValue()))
            .andExpect(jsonPath("$.dateFrom").value(DEFAULT_DATE_FROM.toString()))
            .andExpect(jsonPath("$.dateTo").value(DEFAULT_DATE_TO.toString()))
            .andExpect(jsonPath("$.idWrk").value(DEFAULT_ID_WRK.intValue()))
            .andExpect(jsonPath("$.designLife").value(DEFAULT_DESIGN_LIFE))
            .andExpect(jsonPath("$.dmcd").value(DEFAULT_DMCD.intValue()))
            .andExpect(jsonPath("$.l1").value(DEFAULT_L_1.intValue()))
            .andExpect(jsonPath("$.l2").value(DEFAULT_L_2.intValue()))
            .andExpect(jsonPath("$.length").value(DEFAULT_LENGTH))
            .andExpect(jsonPath("$.electrCapac").value(DEFAULT_ELECTR_CAPAC.intValue()))
            .andExpect(jsonPath("$.designWeight").value(DEFAULT_DESIGN_WEIGHT.intValue()))
            .andExpect(jsonPath("$.weight").value(DEFAULT_WEIGHT.intValue()))
            .andExpect(jsonPath("$.isBurial").value(DEFAULT_IS_BURIAL))
            .andExpect(jsonPath("$.chemicalComposition").value(DEFAULT_CHEMICAL_COMPOSITION.toString()))
            .andExpect(jsonPath("$.density").value(DEFAULT_DENSITY.intValue()))
            .andExpect(jsonPath("$.spacing").value(DEFAULT_SPACING))
            .andExpect(jsonPath("$.coatCutbackLength").value(DEFAULT_COAT_CUTBACK_LENGTH))
            .andExpect(jsonPath("$.coatDmgArea").value(DEFAULT_COAT_DMG_AREA.intValue()))
            .andExpect(jsonPath("$.h2sSoil").value(DEFAULT_H_2_S_SOIL.intValue()))
            .andExpect(jsonPath("$.remain").value(DEFAULT_REMAIN.intValue()))
            .andExpect(jsonPath("$.intFldTemp").value(DEFAULT_INT_FLD_TEMP.intValue()))
            .andExpect(jsonPath("$.minPrc").value(DEFAULT_MIN_PRC.intValue()))
            .andExpect(jsonPath("$.thickness").value(DEFAULT_THICKNESS.intValue()))
            .andExpect(jsonPath("$.coord").value(DEFAULT_COORD.toString()))
            .andExpect(jsonPath("$.kpStart").value(DEFAULT_KP_START.intValue()))
            .andExpect(jsonPath("$.coatThickness").value(DEFAULT_COAT_THICKNESS.intValue()))
            .andExpect(jsonPath("$.kpEnd").value(DEFAULT_KP_END.intValue()))
            .andExpect(jsonPath("$.isCurrentFlag").value(DEFAULT_IS_CURRENT_FLAG))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.comment").value(DEFAULT_COMMENT.toString()))
            .andExpect(jsonPath("$.dateCreate").value(DEFAULT_DATE_CREATE.toString()))
            .andExpect(jsonPath("$.dateEdit").value(DEFAULT_DATE_EDIT.toString()))
            .andExpect(jsonPath("$.creator").value(DEFAULT_CREATOR.toString()))
            .andExpect(jsonPath("$.editor").value(DEFAULT_EDITOR.toString()));
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByDateFromIsEqualToSomething() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where dateFrom equals to DEFAULT_DATE_FROM
        defaultAnodeHistShouldBeFound("dateFrom.equals=" + DEFAULT_DATE_FROM);

        // Get all the anodeHistList where dateFrom equals to UPDATED_DATE_FROM
        defaultAnodeHistShouldNotBeFound("dateFrom.equals=" + UPDATED_DATE_FROM);
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByDateFromIsInShouldWork() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where dateFrom in DEFAULT_DATE_FROM or UPDATED_DATE_FROM
        defaultAnodeHistShouldBeFound("dateFrom.in=" + DEFAULT_DATE_FROM + "," + UPDATED_DATE_FROM);

        // Get all the anodeHistList where dateFrom equals to UPDATED_DATE_FROM
        defaultAnodeHistShouldNotBeFound("dateFrom.in=" + UPDATED_DATE_FROM);
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByDateFromIsNullOrNotNull() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where dateFrom is not null
        defaultAnodeHistShouldBeFound("dateFrom.specified=true");

        // Get all the anodeHistList where dateFrom is null
        defaultAnodeHistShouldNotBeFound("dateFrom.specified=false");
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByDateFromIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where dateFrom greater than or equals to DEFAULT_DATE_FROM
        defaultAnodeHistShouldBeFound("dateFrom.greaterOrEqualThan=" + DEFAULT_DATE_FROM);

        // Get all the anodeHistList where dateFrom greater than or equals to UPDATED_DATE_FROM
        defaultAnodeHistShouldNotBeFound("dateFrom.greaterOrEqualThan=" + UPDATED_DATE_FROM);
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByDateFromIsLessThanSomething() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where dateFrom less than or equals to DEFAULT_DATE_FROM
        defaultAnodeHistShouldNotBeFound("dateFrom.lessThan=" + DEFAULT_DATE_FROM);

        // Get all the anodeHistList where dateFrom less than or equals to UPDATED_DATE_FROM
        defaultAnodeHistShouldBeFound("dateFrom.lessThan=" + UPDATED_DATE_FROM);
    }


    @Test
    @Transactional
    public void getAllAnodeHistsByDateToIsEqualToSomething() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where dateTo equals to DEFAULT_DATE_TO
        defaultAnodeHistShouldBeFound("dateTo.equals=" + DEFAULT_DATE_TO);

        // Get all the anodeHistList where dateTo equals to UPDATED_DATE_TO
        defaultAnodeHistShouldNotBeFound("dateTo.equals=" + UPDATED_DATE_TO);
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByDateToIsInShouldWork() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where dateTo in DEFAULT_DATE_TO or UPDATED_DATE_TO
        defaultAnodeHistShouldBeFound("dateTo.in=" + DEFAULT_DATE_TO + "," + UPDATED_DATE_TO);

        // Get all the anodeHistList where dateTo equals to UPDATED_DATE_TO
        defaultAnodeHistShouldNotBeFound("dateTo.in=" + UPDATED_DATE_TO);
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByDateToIsNullOrNotNull() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where dateTo is not null
        defaultAnodeHistShouldBeFound("dateTo.specified=true");

        // Get all the anodeHistList where dateTo is null
        defaultAnodeHistShouldNotBeFound("dateTo.specified=false");
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByDateToIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where dateTo greater than or equals to DEFAULT_DATE_TO
        defaultAnodeHistShouldBeFound("dateTo.greaterOrEqualThan=" + DEFAULT_DATE_TO);

        // Get all the anodeHistList where dateTo greater than or equals to UPDATED_DATE_TO
        defaultAnodeHistShouldNotBeFound("dateTo.greaterOrEqualThan=" + UPDATED_DATE_TO);
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByDateToIsLessThanSomething() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where dateTo less than or equals to DEFAULT_DATE_TO
        defaultAnodeHistShouldNotBeFound("dateTo.lessThan=" + DEFAULT_DATE_TO);

        // Get all the anodeHistList where dateTo less than or equals to UPDATED_DATE_TO
        defaultAnodeHistShouldBeFound("dateTo.lessThan=" + UPDATED_DATE_TO);
    }


    @Test
    @Transactional
    public void getAllAnodeHistsByIdWrkIsEqualToSomething() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where idWrk equals to DEFAULT_ID_WRK
        defaultAnodeHistShouldBeFound("idWrk.equals=" + DEFAULT_ID_WRK);

        // Get all the anodeHistList where idWrk equals to UPDATED_ID_WRK
        defaultAnodeHistShouldNotBeFound("idWrk.equals=" + UPDATED_ID_WRK);
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByIdWrkIsInShouldWork() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where idWrk in DEFAULT_ID_WRK or UPDATED_ID_WRK
        defaultAnodeHistShouldBeFound("idWrk.in=" + DEFAULT_ID_WRK + "," + UPDATED_ID_WRK);

        // Get all the anodeHistList where idWrk equals to UPDATED_ID_WRK
        defaultAnodeHistShouldNotBeFound("idWrk.in=" + UPDATED_ID_WRK);
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByIdWrkIsNullOrNotNull() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where idWrk is not null
        defaultAnodeHistShouldBeFound("idWrk.specified=true");

        // Get all the anodeHistList where idWrk is null
        defaultAnodeHistShouldNotBeFound("idWrk.specified=false");
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByIdWrkIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where idWrk greater than or equals to DEFAULT_ID_WRK
        defaultAnodeHistShouldBeFound("idWrk.greaterOrEqualThan=" + DEFAULT_ID_WRK);

        // Get all the anodeHistList where idWrk greater than or equals to UPDATED_ID_WRK
        defaultAnodeHistShouldNotBeFound("idWrk.greaterOrEqualThan=" + UPDATED_ID_WRK);
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByIdWrkIsLessThanSomething() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where idWrk less than or equals to DEFAULT_ID_WRK
        defaultAnodeHistShouldNotBeFound("idWrk.lessThan=" + DEFAULT_ID_WRK);

        // Get all the anodeHistList where idWrk less than or equals to UPDATED_ID_WRK
        defaultAnodeHistShouldBeFound("idWrk.lessThan=" + UPDATED_ID_WRK);
    }


    @Test
    @Transactional
    public void getAllAnodeHistsByDesignLifeIsEqualToSomething() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where designLife equals to DEFAULT_DESIGN_LIFE
        defaultAnodeHistShouldBeFound("designLife.equals=" + DEFAULT_DESIGN_LIFE);

        // Get all the anodeHistList where designLife equals to UPDATED_DESIGN_LIFE
        defaultAnodeHistShouldNotBeFound("designLife.equals=" + UPDATED_DESIGN_LIFE);
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByDesignLifeIsInShouldWork() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where designLife in DEFAULT_DESIGN_LIFE or UPDATED_DESIGN_LIFE
        defaultAnodeHistShouldBeFound("designLife.in=" + DEFAULT_DESIGN_LIFE + "," + UPDATED_DESIGN_LIFE);

        // Get all the anodeHistList where designLife equals to UPDATED_DESIGN_LIFE
        defaultAnodeHistShouldNotBeFound("designLife.in=" + UPDATED_DESIGN_LIFE);
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByDesignLifeIsNullOrNotNull() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where designLife is not null
        defaultAnodeHistShouldBeFound("designLife.specified=true");

        // Get all the anodeHistList where designLife is null
        defaultAnodeHistShouldNotBeFound("designLife.specified=false");
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByDesignLifeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where designLife greater than or equals to DEFAULT_DESIGN_LIFE
        defaultAnodeHistShouldBeFound("designLife.greaterOrEqualThan=" + DEFAULT_DESIGN_LIFE);

        // Get all the anodeHistList where designLife greater than or equals to UPDATED_DESIGN_LIFE
        defaultAnodeHistShouldNotBeFound("designLife.greaterOrEqualThan=" + UPDATED_DESIGN_LIFE);
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByDesignLifeIsLessThanSomething() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where designLife less than or equals to DEFAULT_DESIGN_LIFE
        defaultAnodeHistShouldNotBeFound("designLife.lessThan=" + DEFAULT_DESIGN_LIFE);

        // Get all the anodeHistList where designLife less than or equals to UPDATED_DESIGN_LIFE
        defaultAnodeHistShouldBeFound("designLife.lessThan=" + UPDATED_DESIGN_LIFE);
    }


    @Test
    @Transactional
    public void getAllAnodeHistsByDmcdIsEqualToSomething() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where dmcd equals to DEFAULT_DMCD
        defaultAnodeHistShouldBeFound("dmcd.equals=" + DEFAULT_DMCD);

        // Get all the anodeHistList where dmcd equals to UPDATED_DMCD
        defaultAnodeHistShouldNotBeFound("dmcd.equals=" + UPDATED_DMCD);
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByDmcdIsInShouldWork() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where dmcd in DEFAULT_DMCD or UPDATED_DMCD
        defaultAnodeHistShouldBeFound("dmcd.in=" + DEFAULT_DMCD + "," + UPDATED_DMCD);

        // Get all the anodeHistList where dmcd equals to UPDATED_DMCD
        defaultAnodeHistShouldNotBeFound("dmcd.in=" + UPDATED_DMCD);
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByDmcdIsNullOrNotNull() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where dmcd is not null
        defaultAnodeHistShouldBeFound("dmcd.specified=true");

        // Get all the anodeHistList where dmcd is null
        defaultAnodeHistShouldNotBeFound("dmcd.specified=false");
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByl1IsEqualToSomething() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where l1 equals to DEFAULT_L_1
        defaultAnodeHistShouldBeFound("l1.equals=" + DEFAULT_L_1);

        // Get all the anodeHistList where l1 equals to UPDATED_L_1
        defaultAnodeHistShouldNotBeFound("l1.equals=" + UPDATED_L_1);
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByl1IsInShouldWork() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where l1 in DEFAULT_L_1 or UPDATED_L_1
        defaultAnodeHistShouldBeFound("l1.in=" + DEFAULT_L_1 + "," + UPDATED_L_1);

        // Get all the anodeHistList where l1 equals to UPDATED_L_1
        defaultAnodeHistShouldNotBeFound("l1.in=" + UPDATED_L_1);
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByl1IsNullOrNotNull() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where l1 is not null
        defaultAnodeHistShouldBeFound("l1.specified=true");

        // Get all the anodeHistList where l1 is null
        defaultAnodeHistShouldNotBeFound("l1.specified=false");
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByl2IsEqualToSomething() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where l2 equals to DEFAULT_L_2
        defaultAnodeHistShouldBeFound("l2.equals=" + DEFAULT_L_2);

        // Get all the anodeHistList where l2 equals to UPDATED_L_2
        defaultAnodeHistShouldNotBeFound("l2.equals=" + UPDATED_L_2);
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByl2IsInShouldWork() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where l2 in DEFAULT_L_2 or UPDATED_L_2
        defaultAnodeHistShouldBeFound("l2.in=" + DEFAULT_L_2 + "," + UPDATED_L_2);

        // Get all the anodeHistList where l2 equals to UPDATED_L_2
        defaultAnodeHistShouldNotBeFound("l2.in=" + UPDATED_L_2);
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByl2IsNullOrNotNull() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where l2 is not null
        defaultAnodeHistShouldBeFound("l2.specified=true");

        // Get all the anodeHistList where l2 is null
        defaultAnodeHistShouldNotBeFound("l2.specified=false");
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByLengthIsEqualToSomething() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where length equals to DEFAULT_LENGTH
        defaultAnodeHistShouldBeFound("length.equals=" + DEFAULT_LENGTH);

        // Get all the anodeHistList where length equals to UPDATED_LENGTH
        defaultAnodeHistShouldNotBeFound("length.equals=" + UPDATED_LENGTH);
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByLengthIsInShouldWork() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where length in DEFAULT_LENGTH or UPDATED_LENGTH
        defaultAnodeHistShouldBeFound("length.in=" + DEFAULT_LENGTH + "," + UPDATED_LENGTH);

        // Get all the anodeHistList where length equals to UPDATED_LENGTH
        defaultAnodeHistShouldNotBeFound("length.in=" + UPDATED_LENGTH);
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByLengthIsNullOrNotNull() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where length is not null
        defaultAnodeHistShouldBeFound("length.specified=true");

        // Get all the anodeHistList where length is null
        defaultAnodeHistShouldNotBeFound("length.specified=false");
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByLengthIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where length greater than or equals to DEFAULT_LENGTH
        defaultAnodeHistShouldBeFound("length.greaterOrEqualThan=" + DEFAULT_LENGTH);

        // Get all the anodeHistList where length greater than or equals to UPDATED_LENGTH
        defaultAnodeHistShouldNotBeFound("length.greaterOrEqualThan=" + UPDATED_LENGTH);
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByLengthIsLessThanSomething() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where length less than or equals to DEFAULT_LENGTH
        defaultAnodeHistShouldNotBeFound("length.lessThan=" + DEFAULT_LENGTH);

        // Get all the anodeHistList where length less than or equals to UPDATED_LENGTH
        defaultAnodeHistShouldBeFound("length.lessThan=" + UPDATED_LENGTH);
    }


    @Test
    @Transactional
    public void getAllAnodeHistsByElectrCapacIsEqualToSomething() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where electrCapac equals to DEFAULT_ELECTR_CAPAC
        defaultAnodeHistShouldBeFound("electrCapac.equals=" + DEFAULT_ELECTR_CAPAC);

        // Get all the anodeHistList where electrCapac equals to UPDATED_ELECTR_CAPAC
        defaultAnodeHistShouldNotBeFound("electrCapac.equals=" + UPDATED_ELECTR_CAPAC);
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByElectrCapacIsInShouldWork() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where electrCapac in DEFAULT_ELECTR_CAPAC or UPDATED_ELECTR_CAPAC
        defaultAnodeHistShouldBeFound("electrCapac.in=" + DEFAULT_ELECTR_CAPAC + "," + UPDATED_ELECTR_CAPAC);

        // Get all the anodeHistList where electrCapac equals to UPDATED_ELECTR_CAPAC
        defaultAnodeHistShouldNotBeFound("electrCapac.in=" + UPDATED_ELECTR_CAPAC);
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByElectrCapacIsNullOrNotNull() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where electrCapac is not null
        defaultAnodeHistShouldBeFound("electrCapac.specified=true");

        // Get all the anodeHistList where electrCapac is null
        defaultAnodeHistShouldNotBeFound("electrCapac.specified=false");
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByDesignWeightIsEqualToSomething() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where designWeight equals to DEFAULT_DESIGN_WEIGHT
        defaultAnodeHistShouldBeFound("designWeight.equals=" + DEFAULT_DESIGN_WEIGHT);

        // Get all the anodeHistList where designWeight equals to UPDATED_DESIGN_WEIGHT
        defaultAnodeHistShouldNotBeFound("designWeight.equals=" + UPDATED_DESIGN_WEIGHT);
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByDesignWeightIsInShouldWork() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where designWeight in DEFAULT_DESIGN_WEIGHT or UPDATED_DESIGN_WEIGHT
        defaultAnodeHistShouldBeFound("designWeight.in=" + DEFAULT_DESIGN_WEIGHT + "," + UPDATED_DESIGN_WEIGHT);

        // Get all the anodeHistList where designWeight equals to UPDATED_DESIGN_WEIGHT
        defaultAnodeHistShouldNotBeFound("designWeight.in=" + UPDATED_DESIGN_WEIGHT);
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByDesignWeightIsNullOrNotNull() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where designWeight is not null
        defaultAnodeHistShouldBeFound("designWeight.specified=true");

        // Get all the anodeHistList where designWeight is null
        defaultAnodeHistShouldNotBeFound("designWeight.specified=false");
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByWeightIsEqualToSomething() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where weight equals to DEFAULT_WEIGHT
        defaultAnodeHistShouldBeFound("weight.equals=" + DEFAULT_WEIGHT);

        // Get all the anodeHistList where weight equals to UPDATED_WEIGHT
        defaultAnodeHistShouldNotBeFound("weight.equals=" + UPDATED_WEIGHT);
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByWeightIsInShouldWork() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where weight in DEFAULT_WEIGHT or UPDATED_WEIGHT
        defaultAnodeHistShouldBeFound("weight.in=" + DEFAULT_WEIGHT + "," + UPDATED_WEIGHT);

        // Get all the anodeHistList where weight equals to UPDATED_WEIGHT
        defaultAnodeHistShouldNotBeFound("weight.in=" + UPDATED_WEIGHT);
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByWeightIsNullOrNotNull() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where weight is not null
        defaultAnodeHistShouldBeFound("weight.specified=true");

        // Get all the anodeHistList where weight is null
        defaultAnodeHistShouldNotBeFound("weight.specified=false");
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByIsBurialIsEqualToSomething() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where isBurial equals to DEFAULT_IS_BURIAL
        defaultAnodeHistShouldBeFound("isBurial.equals=" + DEFAULT_IS_BURIAL);

        // Get all the anodeHistList where isBurial equals to UPDATED_IS_BURIAL
        defaultAnodeHistShouldNotBeFound("isBurial.equals=" + UPDATED_IS_BURIAL);
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByIsBurialIsInShouldWork() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where isBurial in DEFAULT_IS_BURIAL or UPDATED_IS_BURIAL
        defaultAnodeHistShouldBeFound("isBurial.in=" + DEFAULT_IS_BURIAL + "," + UPDATED_IS_BURIAL);

        // Get all the anodeHistList where isBurial equals to UPDATED_IS_BURIAL
        defaultAnodeHistShouldNotBeFound("isBurial.in=" + UPDATED_IS_BURIAL);
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByIsBurialIsNullOrNotNull() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where isBurial is not null
        defaultAnodeHistShouldBeFound("isBurial.specified=true");

        // Get all the anodeHistList where isBurial is null
        defaultAnodeHistShouldNotBeFound("isBurial.specified=false");
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByIsBurialIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where isBurial greater than or equals to DEFAULT_IS_BURIAL
        defaultAnodeHistShouldBeFound("isBurial.greaterOrEqualThan=" + DEFAULT_IS_BURIAL);

        // Get all the anodeHistList where isBurial greater than or equals to UPDATED_IS_BURIAL
        defaultAnodeHistShouldNotBeFound("isBurial.greaterOrEqualThan=" + UPDATED_IS_BURIAL);
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByIsBurialIsLessThanSomething() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where isBurial less than or equals to DEFAULT_IS_BURIAL
        defaultAnodeHistShouldNotBeFound("isBurial.lessThan=" + DEFAULT_IS_BURIAL);

        // Get all the anodeHistList where isBurial less than or equals to UPDATED_IS_BURIAL
        defaultAnodeHistShouldBeFound("isBurial.lessThan=" + UPDATED_IS_BURIAL);
    }


    @Test
    @Transactional
    public void getAllAnodeHistsByChemicalCompositionIsEqualToSomething() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where chemicalComposition equals to DEFAULT_CHEMICAL_COMPOSITION
        defaultAnodeHistShouldBeFound("chemicalComposition.equals=" + DEFAULT_CHEMICAL_COMPOSITION);

        // Get all the anodeHistList where chemicalComposition equals to UPDATED_CHEMICAL_COMPOSITION
        defaultAnodeHistShouldNotBeFound("chemicalComposition.equals=" + UPDATED_CHEMICAL_COMPOSITION);
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByChemicalCompositionIsInShouldWork() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where chemicalComposition in DEFAULT_CHEMICAL_COMPOSITION or UPDATED_CHEMICAL_COMPOSITION
        defaultAnodeHistShouldBeFound("chemicalComposition.in=" + DEFAULT_CHEMICAL_COMPOSITION + "," + UPDATED_CHEMICAL_COMPOSITION);

        // Get all the anodeHistList where chemicalComposition equals to UPDATED_CHEMICAL_COMPOSITION
        defaultAnodeHistShouldNotBeFound("chemicalComposition.in=" + UPDATED_CHEMICAL_COMPOSITION);
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByChemicalCompositionIsNullOrNotNull() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where chemicalComposition is not null
        defaultAnodeHistShouldBeFound("chemicalComposition.specified=true");

        // Get all the anodeHistList where chemicalComposition is null
        defaultAnodeHistShouldNotBeFound("chemicalComposition.specified=false");
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByDensityIsEqualToSomething() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where density equals to DEFAULT_DENSITY
        defaultAnodeHistShouldBeFound("density.equals=" + DEFAULT_DENSITY);

        // Get all the anodeHistList where density equals to UPDATED_DENSITY
        defaultAnodeHistShouldNotBeFound("density.equals=" + UPDATED_DENSITY);
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByDensityIsInShouldWork() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where density in DEFAULT_DENSITY or UPDATED_DENSITY
        defaultAnodeHistShouldBeFound("density.in=" + DEFAULT_DENSITY + "," + UPDATED_DENSITY);

        // Get all the anodeHistList where density equals to UPDATED_DENSITY
        defaultAnodeHistShouldNotBeFound("density.in=" + UPDATED_DENSITY);
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByDensityIsNullOrNotNull() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where density is not null
        defaultAnodeHistShouldBeFound("density.specified=true");

        // Get all the anodeHistList where density is null
        defaultAnodeHistShouldNotBeFound("density.specified=false");
    }

    @Test
    @Transactional
    public void getAllAnodeHistsBySpacingIsEqualToSomething() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where spacing equals to DEFAULT_SPACING
        defaultAnodeHistShouldBeFound("spacing.equals=" + DEFAULT_SPACING);

        // Get all the anodeHistList where spacing equals to UPDATED_SPACING
        defaultAnodeHistShouldNotBeFound("spacing.equals=" + UPDATED_SPACING);
    }

    @Test
    @Transactional
    public void getAllAnodeHistsBySpacingIsInShouldWork() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where spacing in DEFAULT_SPACING or UPDATED_SPACING
        defaultAnodeHistShouldBeFound("spacing.in=" + DEFAULT_SPACING + "," + UPDATED_SPACING);

        // Get all the anodeHistList where spacing equals to UPDATED_SPACING
        defaultAnodeHistShouldNotBeFound("spacing.in=" + UPDATED_SPACING);
    }

    @Test
    @Transactional
    public void getAllAnodeHistsBySpacingIsNullOrNotNull() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where spacing is not null
        defaultAnodeHistShouldBeFound("spacing.specified=true");

        // Get all the anodeHistList where spacing is null
        defaultAnodeHistShouldNotBeFound("spacing.specified=false");
    }

    @Test
    @Transactional
    public void getAllAnodeHistsBySpacingIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where spacing greater than or equals to DEFAULT_SPACING
        defaultAnodeHistShouldBeFound("spacing.greaterOrEqualThan=" + DEFAULT_SPACING);

        // Get all the anodeHistList where spacing greater than or equals to UPDATED_SPACING
        defaultAnodeHistShouldNotBeFound("spacing.greaterOrEqualThan=" + UPDATED_SPACING);
    }

    @Test
    @Transactional
    public void getAllAnodeHistsBySpacingIsLessThanSomething() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where spacing less than or equals to DEFAULT_SPACING
        defaultAnodeHistShouldNotBeFound("spacing.lessThan=" + DEFAULT_SPACING);

        // Get all the anodeHistList where spacing less than or equals to UPDATED_SPACING
        defaultAnodeHistShouldBeFound("spacing.lessThan=" + UPDATED_SPACING);
    }


    @Test
    @Transactional
    public void getAllAnodeHistsByCoatCutbackLengthIsEqualToSomething() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where coatCutbackLength equals to DEFAULT_COAT_CUTBACK_LENGTH
        defaultAnodeHistShouldBeFound("coatCutbackLength.equals=" + DEFAULT_COAT_CUTBACK_LENGTH);

        // Get all the anodeHistList where coatCutbackLength equals to UPDATED_COAT_CUTBACK_LENGTH
        defaultAnodeHistShouldNotBeFound("coatCutbackLength.equals=" + UPDATED_COAT_CUTBACK_LENGTH);
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByCoatCutbackLengthIsInShouldWork() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where coatCutbackLength in DEFAULT_COAT_CUTBACK_LENGTH or UPDATED_COAT_CUTBACK_LENGTH
        defaultAnodeHistShouldBeFound("coatCutbackLength.in=" + DEFAULT_COAT_CUTBACK_LENGTH + "," + UPDATED_COAT_CUTBACK_LENGTH);

        // Get all the anodeHistList where coatCutbackLength equals to UPDATED_COAT_CUTBACK_LENGTH
        defaultAnodeHistShouldNotBeFound("coatCutbackLength.in=" + UPDATED_COAT_CUTBACK_LENGTH);
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByCoatCutbackLengthIsNullOrNotNull() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where coatCutbackLength is not null
        defaultAnodeHistShouldBeFound("coatCutbackLength.specified=true");

        // Get all the anodeHistList where coatCutbackLength is null
        defaultAnodeHistShouldNotBeFound("coatCutbackLength.specified=false");
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByCoatCutbackLengthIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where coatCutbackLength greater than or equals to DEFAULT_COAT_CUTBACK_LENGTH
        defaultAnodeHistShouldBeFound("coatCutbackLength.greaterOrEqualThan=" + DEFAULT_COAT_CUTBACK_LENGTH);

        // Get all the anodeHistList where coatCutbackLength greater than or equals to UPDATED_COAT_CUTBACK_LENGTH
        defaultAnodeHistShouldNotBeFound("coatCutbackLength.greaterOrEqualThan=" + UPDATED_COAT_CUTBACK_LENGTH);
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByCoatCutbackLengthIsLessThanSomething() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where coatCutbackLength less than or equals to DEFAULT_COAT_CUTBACK_LENGTH
        defaultAnodeHistShouldNotBeFound("coatCutbackLength.lessThan=" + DEFAULT_COAT_CUTBACK_LENGTH);

        // Get all the anodeHistList where coatCutbackLength less than or equals to UPDATED_COAT_CUTBACK_LENGTH
        defaultAnodeHistShouldBeFound("coatCutbackLength.lessThan=" + UPDATED_COAT_CUTBACK_LENGTH);
    }


    @Test
    @Transactional
    public void getAllAnodeHistsByCoatDmgAreaIsEqualToSomething() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where coatDmgArea equals to DEFAULT_COAT_DMG_AREA
        defaultAnodeHistShouldBeFound("coatDmgArea.equals=" + DEFAULT_COAT_DMG_AREA);

        // Get all the anodeHistList where coatDmgArea equals to UPDATED_COAT_DMG_AREA
        defaultAnodeHistShouldNotBeFound("coatDmgArea.equals=" + UPDATED_COAT_DMG_AREA);
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByCoatDmgAreaIsInShouldWork() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where coatDmgArea in DEFAULT_COAT_DMG_AREA or UPDATED_COAT_DMG_AREA
        defaultAnodeHistShouldBeFound("coatDmgArea.in=" + DEFAULT_COAT_DMG_AREA + "," + UPDATED_COAT_DMG_AREA);

        // Get all the anodeHistList where coatDmgArea equals to UPDATED_COAT_DMG_AREA
        defaultAnodeHistShouldNotBeFound("coatDmgArea.in=" + UPDATED_COAT_DMG_AREA);
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByCoatDmgAreaIsNullOrNotNull() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where coatDmgArea is not null
        defaultAnodeHistShouldBeFound("coatDmgArea.specified=true");

        // Get all the anodeHistList where coatDmgArea is null
        defaultAnodeHistShouldNotBeFound("coatDmgArea.specified=false");
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByh2sSoilIsEqualToSomething() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where h2sSoil equals to DEFAULT_H_2_S_SOIL
        defaultAnodeHistShouldBeFound("h2sSoil.equals=" + DEFAULT_H_2_S_SOIL);

        // Get all the anodeHistList where h2sSoil equals to UPDATED_H_2_S_SOIL
        defaultAnodeHistShouldNotBeFound("h2sSoil.equals=" + UPDATED_H_2_S_SOIL);
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByh2sSoilIsInShouldWork() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where h2sSoil in DEFAULT_H_2_S_SOIL or UPDATED_H_2_S_SOIL
        defaultAnodeHistShouldBeFound("h2sSoil.in=" + DEFAULT_H_2_S_SOIL + "," + UPDATED_H_2_S_SOIL);

        // Get all the anodeHistList where h2sSoil equals to UPDATED_H_2_S_SOIL
        defaultAnodeHistShouldNotBeFound("h2sSoil.in=" + UPDATED_H_2_S_SOIL);
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByh2sSoilIsNullOrNotNull() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where h2sSoil is not null
        defaultAnodeHistShouldBeFound("h2sSoil.specified=true");

        // Get all the anodeHistList where h2sSoil is null
        defaultAnodeHistShouldNotBeFound("h2sSoil.specified=false");
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByRemainIsEqualToSomething() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where remain equals to DEFAULT_REMAIN
        defaultAnodeHistShouldBeFound("remain.equals=" + DEFAULT_REMAIN);

        // Get all the anodeHistList where remain equals to UPDATED_REMAIN
        defaultAnodeHistShouldNotBeFound("remain.equals=" + UPDATED_REMAIN);
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByRemainIsInShouldWork() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where remain in DEFAULT_REMAIN or UPDATED_REMAIN
        defaultAnodeHistShouldBeFound("remain.in=" + DEFAULT_REMAIN + "," + UPDATED_REMAIN);

        // Get all the anodeHistList where remain equals to UPDATED_REMAIN
        defaultAnodeHistShouldNotBeFound("remain.in=" + UPDATED_REMAIN);
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByRemainIsNullOrNotNull() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where remain is not null
        defaultAnodeHistShouldBeFound("remain.specified=true");

        // Get all the anodeHistList where remain is null
        defaultAnodeHistShouldNotBeFound("remain.specified=false");
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByIntFldTempIsEqualToSomething() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where intFldTemp equals to DEFAULT_INT_FLD_TEMP
        defaultAnodeHistShouldBeFound("intFldTemp.equals=" + DEFAULT_INT_FLD_TEMP);

        // Get all the anodeHistList where intFldTemp equals to UPDATED_INT_FLD_TEMP
        defaultAnodeHistShouldNotBeFound("intFldTemp.equals=" + UPDATED_INT_FLD_TEMP);
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByIntFldTempIsInShouldWork() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where intFldTemp in DEFAULT_INT_FLD_TEMP or UPDATED_INT_FLD_TEMP
        defaultAnodeHistShouldBeFound("intFldTemp.in=" + DEFAULT_INT_FLD_TEMP + "," + UPDATED_INT_FLD_TEMP);

        // Get all the anodeHistList where intFldTemp equals to UPDATED_INT_FLD_TEMP
        defaultAnodeHistShouldNotBeFound("intFldTemp.in=" + UPDATED_INT_FLD_TEMP);
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByIntFldTempIsNullOrNotNull() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where intFldTemp is not null
        defaultAnodeHistShouldBeFound("intFldTemp.specified=true");

        // Get all the anodeHistList where intFldTemp is null
        defaultAnodeHistShouldNotBeFound("intFldTemp.specified=false");
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByMinPrcIsEqualToSomething() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where minPrc equals to DEFAULT_MIN_PRC
        defaultAnodeHistShouldBeFound("minPrc.equals=" + DEFAULT_MIN_PRC);

        // Get all the anodeHistList where minPrc equals to UPDATED_MIN_PRC
        defaultAnodeHistShouldNotBeFound("minPrc.equals=" + UPDATED_MIN_PRC);
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByMinPrcIsInShouldWork() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where minPrc in DEFAULT_MIN_PRC or UPDATED_MIN_PRC
        defaultAnodeHistShouldBeFound("minPrc.in=" + DEFAULT_MIN_PRC + "," + UPDATED_MIN_PRC);

        // Get all the anodeHistList where minPrc equals to UPDATED_MIN_PRC
        defaultAnodeHistShouldNotBeFound("minPrc.in=" + UPDATED_MIN_PRC);
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByMinPrcIsNullOrNotNull() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where minPrc is not null
        defaultAnodeHistShouldBeFound("minPrc.specified=true");

        // Get all the anodeHistList where minPrc is null
        defaultAnodeHistShouldNotBeFound("minPrc.specified=false");
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByThicknessIsEqualToSomething() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where thickness equals to DEFAULT_THICKNESS
        defaultAnodeHistShouldBeFound("thickness.equals=" + DEFAULT_THICKNESS);

        // Get all the anodeHistList where thickness equals to UPDATED_THICKNESS
        defaultAnodeHistShouldNotBeFound("thickness.equals=" + UPDATED_THICKNESS);
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByThicknessIsInShouldWork() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where thickness in DEFAULT_THICKNESS or UPDATED_THICKNESS
        defaultAnodeHistShouldBeFound("thickness.in=" + DEFAULT_THICKNESS + "," + UPDATED_THICKNESS);

        // Get all the anodeHistList where thickness equals to UPDATED_THICKNESS
        defaultAnodeHistShouldNotBeFound("thickness.in=" + UPDATED_THICKNESS);
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByThicknessIsNullOrNotNull() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where thickness is not null
        defaultAnodeHistShouldBeFound("thickness.specified=true");

        // Get all the anodeHistList where thickness is null
        defaultAnodeHistShouldNotBeFound("thickness.specified=false");
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByCoordIsEqualToSomething() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where coord equals to DEFAULT_COORD
        defaultAnodeHistShouldBeFound("coord.equals=" + DEFAULT_COORD);

        // Get all the anodeHistList where coord equals to UPDATED_COORD
        defaultAnodeHistShouldNotBeFound("coord.equals=" + UPDATED_COORD);
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByCoordIsInShouldWork() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where coord in DEFAULT_COORD or UPDATED_COORD
        defaultAnodeHistShouldBeFound("coord.in=" + DEFAULT_COORD + "," + UPDATED_COORD);

        // Get all the anodeHistList where coord equals to UPDATED_COORD
        defaultAnodeHistShouldNotBeFound("coord.in=" + UPDATED_COORD);
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByCoordIsNullOrNotNull() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where coord is not null
        defaultAnodeHistShouldBeFound("coord.specified=true");

        // Get all the anodeHistList where coord is null
        defaultAnodeHistShouldNotBeFound("coord.specified=false");
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByKpStartIsEqualToSomething() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where kpStart equals to DEFAULT_KP_START
        defaultAnodeHistShouldBeFound("kpStart.equals=" + DEFAULT_KP_START);

        // Get all the anodeHistList where kpStart equals to UPDATED_KP_START
        defaultAnodeHistShouldNotBeFound("kpStart.equals=" + UPDATED_KP_START);
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByKpStartIsInShouldWork() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where kpStart in DEFAULT_KP_START or UPDATED_KP_START
        defaultAnodeHistShouldBeFound("kpStart.in=" + DEFAULT_KP_START + "," + UPDATED_KP_START);

        // Get all the anodeHistList where kpStart equals to UPDATED_KP_START
        defaultAnodeHistShouldNotBeFound("kpStart.in=" + UPDATED_KP_START);
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByKpStartIsNullOrNotNull() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where kpStart is not null
        defaultAnodeHistShouldBeFound("kpStart.specified=true");

        // Get all the anodeHistList where kpStart is null
        defaultAnodeHistShouldNotBeFound("kpStart.specified=false");
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByCoatThicknessIsEqualToSomething() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where coatThickness equals to DEFAULT_COAT_THICKNESS
        defaultAnodeHistShouldBeFound("coatThickness.equals=" + DEFAULT_COAT_THICKNESS);

        // Get all the anodeHistList where coatThickness equals to UPDATED_COAT_THICKNESS
        defaultAnodeHistShouldNotBeFound("coatThickness.equals=" + UPDATED_COAT_THICKNESS);
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByCoatThicknessIsInShouldWork() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where coatThickness in DEFAULT_COAT_THICKNESS or UPDATED_COAT_THICKNESS
        defaultAnodeHistShouldBeFound("coatThickness.in=" + DEFAULT_COAT_THICKNESS + "," + UPDATED_COAT_THICKNESS);

        // Get all the anodeHistList where coatThickness equals to UPDATED_COAT_THICKNESS
        defaultAnodeHistShouldNotBeFound("coatThickness.in=" + UPDATED_COAT_THICKNESS);
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByCoatThicknessIsNullOrNotNull() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where coatThickness is not null
        defaultAnodeHistShouldBeFound("coatThickness.specified=true");

        // Get all the anodeHistList where coatThickness is null
        defaultAnodeHistShouldNotBeFound("coatThickness.specified=false");
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByKpEndIsEqualToSomething() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where kpEnd equals to DEFAULT_KP_END
        defaultAnodeHistShouldBeFound("kpEnd.equals=" + DEFAULT_KP_END);

        // Get all the anodeHistList where kpEnd equals to UPDATED_KP_END
        defaultAnodeHistShouldNotBeFound("kpEnd.equals=" + UPDATED_KP_END);
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByKpEndIsInShouldWork() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where kpEnd in DEFAULT_KP_END or UPDATED_KP_END
        defaultAnodeHistShouldBeFound("kpEnd.in=" + DEFAULT_KP_END + "," + UPDATED_KP_END);

        // Get all the anodeHistList where kpEnd equals to UPDATED_KP_END
        defaultAnodeHistShouldNotBeFound("kpEnd.in=" + UPDATED_KP_END);
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByKpEndIsNullOrNotNull() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where kpEnd is not null
        defaultAnodeHistShouldBeFound("kpEnd.specified=true");

        // Get all the anodeHistList where kpEnd is null
        defaultAnodeHistShouldNotBeFound("kpEnd.specified=false");
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByIsCurrentFlagIsEqualToSomething() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where isCurrentFlag equals to DEFAULT_IS_CURRENT_FLAG
        defaultAnodeHistShouldBeFound("isCurrentFlag.equals=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the anodeHistList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultAnodeHistShouldNotBeFound("isCurrentFlag.equals=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByIsCurrentFlagIsInShouldWork() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where isCurrentFlag in DEFAULT_IS_CURRENT_FLAG or UPDATED_IS_CURRENT_FLAG
        defaultAnodeHistShouldBeFound("isCurrentFlag.in=" + DEFAULT_IS_CURRENT_FLAG + "," + UPDATED_IS_CURRENT_FLAG);

        // Get all the anodeHistList where isCurrentFlag equals to UPDATED_IS_CURRENT_FLAG
        defaultAnodeHistShouldNotBeFound("isCurrentFlag.in=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByIsCurrentFlagIsNullOrNotNull() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where isCurrentFlag is not null
        defaultAnodeHistShouldBeFound("isCurrentFlag.specified=true");

        // Get all the anodeHistList where isCurrentFlag is null
        defaultAnodeHistShouldNotBeFound("isCurrentFlag.specified=false");
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByIsCurrentFlagIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where isCurrentFlag greater than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultAnodeHistShouldBeFound("isCurrentFlag.greaterOrEqualThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the anodeHistList where isCurrentFlag greater than or equals to UPDATED_IS_CURRENT_FLAG
        defaultAnodeHistShouldNotBeFound("isCurrentFlag.greaterOrEqualThan=" + UPDATED_IS_CURRENT_FLAG);
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByIsCurrentFlagIsLessThanSomething() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where isCurrentFlag less than or equals to DEFAULT_IS_CURRENT_FLAG
        defaultAnodeHistShouldNotBeFound("isCurrentFlag.lessThan=" + DEFAULT_IS_CURRENT_FLAG);

        // Get all the anodeHistList where isCurrentFlag less than or equals to UPDATED_IS_CURRENT_FLAG
        defaultAnodeHistShouldBeFound("isCurrentFlag.lessThan=" + UPDATED_IS_CURRENT_FLAG);
    }


    @Test
    @Transactional
    public void getAllAnodeHistsByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where description equals to DEFAULT_DESCRIPTION
        defaultAnodeHistShouldBeFound("description.equals=" + DEFAULT_DESCRIPTION);

        // Get all the anodeHistList where description equals to UPDATED_DESCRIPTION
        defaultAnodeHistShouldNotBeFound("description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
        defaultAnodeHistShouldBeFound("description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION);

        // Get all the anodeHistList where description equals to UPDATED_DESCRIPTION
        defaultAnodeHistShouldNotBeFound("description.in=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where description is not null
        defaultAnodeHistShouldBeFound("description.specified=true");

        // Get all the anodeHistList where description is null
        defaultAnodeHistShouldNotBeFound("description.specified=false");
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByCommentIsEqualToSomething() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where comment equals to DEFAULT_COMMENT
        defaultAnodeHistShouldBeFound("comment.equals=" + DEFAULT_COMMENT);

        // Get all the anodeHistList where comment equals to UPDATED_COMMENT
        defaultAnodeHistShouldNotBeFound("comment.equals=" + UPDATED_COMMENT);
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByCommentIsInShouldWork() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where comment in DEFAULT_COMMENT or UPDATED_COMMENT
        defaultAnodeHistShouldBeFound("comment.in=" + DEFAULT_COMMENT + "," + UPDATED_COMMENT);

        // Get all the anodeHistList where comment equals to UPDATED_COMMENT
        defaultAnodeHistShouldNotBeFound("comment.in=" + UPDATED_COMMENT);
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByCommentIsNullOrNotNull() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where comment is not null
        defaultAnodeHistShouldBeFound("comment.specified=true");

        // Get all the anodeHistList where comment is null
        defaultAnodeHistShouldNotBeFound("comment.specified=false");
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByDateCreateIsEqualToSomething() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where dateCreate equals to DEFAULT_DATE_CREATE
        defaultAnodeHistShouldBeFound("dateCreate.equals=" + DEFAULT_DATE_CREATE);

        // Get all the anodeHistList where dateCreate equals to UPDATED_DATE_CREATE
        defaultAnodeHistShouldNotBeFound("dateCreate.equals=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByDateCreateIsInShouldWork() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where dateCreate in DEFAULT_DATE_CREATE or UPDATED_DATE_CREATE
        defaultAnodeHistShouldBeFound("dateCreate.in=" + DEFAULT_DATE_CREATE + "," + UPDATED_DATE_CREATE);

        // Get all the anodeHistList where dateCreate equals to UPDATED_DATE_CREATE
        defaultAnodeHistShouldNotBeFound("dateCreate.in=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByDateCreateIsNullOrNotNull() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where dateCreate is not null
        defaultAnodeHistShouldBeFound("dateCreate.specified=true");

        // Get all the anodeHistList where dateCreate is null
        defaultAnodeHistShouldNotBeFound("dateCreate.specified=false");
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByDateEditIsEqualToSomething() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where dateEdit equals to DEFAULT_DATE_EDIT
        defaultAnodeHistShouldBeFound("dateEdit.equals=" + DEFAULT_DATE_EDIT);

        // Get all the anodeHistList where dateEdit equals to UPDATED_DATE_EDIT
        defaultAnodeHistShouldNotBeFound("dateEdit.equals=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByDateEditIsInShouldWork() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where dateEdit in DEFAULT_DATE_EDIT or UPDATED_DATE_EDIT
        defaultAnodeHistShouldBeFound("dateEdit.in=" + DEFAULT_DATE_EDIT + "," + UPDATED_DATE_EDIT);

        // Get all the anodeHistList where dateEdit equals to UPDATED_DATE_EDIT
        defaultAnodeHistShouldNotBeFound("dateEdit.in=" + UPDATED_DATE_EDIT);
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByDateEditIsNullOrNotNull() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where dateEdit is not null
        defaultAnodeHistShouldBeFound("dateEdit.specified=true");

        // Get all the anodeHistList where dateEdit is null
        defaultAnodeHistShouldNotBeFound("dateEdit.specified=false");
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByCreatorIsEqualToSomething() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where creator equals to DEFAULT_CREATOR
        defaultAnodeHistShouldBeFound("creator.equals=" + DEFAULT_CREATOR);

        // Get all the anodeHistList where creator equals to UPDATED_CREATOR
        defaultAnodeHistShouldNotBeFound("creator.equals=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByCreatorIsInShouldWork() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where creator in DEFAULT_CREATOR or UPDATED_CREATOR
        defaultAnodeHistShouldBeFound("creator.in=" + DEFAULT_CREATOR + "," + UPDATED_CREATOR);

        // Get all the anodeHistList where creator equals to UPDATED_CREATOR
        defaultAnodeHistShouldNotBeFound("creator.in=" + UPDATED_CREATOR);
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByCreatorIsNullOrNotNull() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where creator is not null
        defaultAnodeHistShouldBeFound("creator.specified=true");

        // Get all the anodeHistList where creator is null
        defaultAnodeHistShouldNotBeFound("creator.specified=false");
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByEditorIsEqualToSomething() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where editor equals to DEFAULT_EDITOR
        defaultAnodeHistShouldBeFound("editor.equals=" + DEFAULT_EDITOR);

        // Get all the anodeHistList where editor equals to UPDATED_EDITOR
        defaultAnodeHistShouldNotBeFound("editor.equals=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByEditorIsInShouldWork() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where editor in DEFAULT_EDITOR or UPDATED_EDITOR
        defaultAnodeHistShouldBeFound("editor.in=" + DEFAULT_EDITOR + "," + UPDATED_EDITOR);

        // Get all the anodeHistList where editor equals to UPDATED_EDITOR
        defaultAnodeHistShouldNotBeFound("editor.in=" + UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByEditorIsNullOrNotNull() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        // Get all the anodeHistList where editor is not null
        defaultAnodeHistShouldBeFound("editor.specified=true");

        // Get all the anodeHistList where editor is null
        defaultAnodeHistShouldNotBeFound("editor.specified=false");
    }

    @Test
    @Transactional
    public void getAllAnodeHistsByIdIsEqualToSomething() throws Exception {
        // Get already existing entity
        Anode id = anodeHist.getId();
        anodeHistRepository.saveAndFlush(anodeHist);
        Long idId = id.getId();

        // Get all the anodeHistList where id equals to idId
        defaultAnodeHistShouldBeFound("idId.equals=" + idId);

        // Get all the anodeHistList where id equals to idId + 1
        defaultAnodeHistShouldNotBeFound("idId.equals=" + (idId + 1));
    }


    @Test
    @Transactional
    public void getAllAnodeHistsByIdPipelineSectionIsEqualToSomething() throws Exception {
        // Get already existing entity
        PipelineSection idPipelineSection = anodeHist.getIdPipelineSection();
        anodeHistRepository.saveAndFlush(anodeHist);
        Long idPipelineSectionId = idPipelineSection.getId();

        // Get all the anodeHistList where idPipelineSection equals to idPipelineSectionId
        defaultAnodeHistShouldBeFound("idPipelineSectionId.equals=" + idPipelineSectionId);

        // Get all the anodeHistList where idPipelineSection equals to idPipelineSectionId + 1
        defaultAnodeHistShouldNotBeFound("idPipelineSectionId.equals=" + (idPipelineSectionId + 1));
    }


    @Test
    @Transactional
    public void getAllAnodeHistsByIdBraceleteTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        ListAnodeBraceleteType idBraceleteType = ListAnodeBraceleteTypeResourceIT.createEntity(em);
        em.persist(idBraceleteType);
        em.flush();
        anodeHist.setIdBraceleteType(idBraceleteType);
        anodeHistRepository.saveAndFlush(anodeHist);
        Long idBraceleteTypeId = idBraceleteType.getId();

        // Get all the anodeHistList where idBraceleteType equals to idBraceleteTypeId
        defaultAnodeHistShouldBeFound("idBraceleteTypeId.equals=" + idBraceleteTypeId);

        // Get all the anodeHistList where idBraceleteType equals to idBraceleteTypeId + 1
        defaultAnodeHistShouldNotBeFound("idBraceleteTypeId.equals=" + (idBraceleteTypeId + 1));
    }


    @Test
    @Transactional
    public void getAllAnodeHistsByIdMaterialIsEqualToSomething() throws Exception {
        // Initialize the database
        ListMaterial idMaterial = ListMaterialResourceIT.createEntity(em);
        em.persist(idMaterial);
        em.flush();
        anodeHist.setIdMaterial(idMaterial);
        anodeHistRepository.saveAndFlush(anodeHist);
        Long idMaterialId = idMaterial.getId();

        // Get all the anodeHistList where idMaterial equals to idMaterialId
        defaultAnodeHistShouldBeFound("idMaterialId.equals=" + idMaterialId);

        // Get all the anodeHistList where idMaterial equals to idMaterialId + 1
        defaultAnodeHistShouldNotBeFound("idMaterialId.equals=" + (idMaterialId + 1));
    }


    @Test
    @Transactional
    public void getAllAnodeHistsByIdStatusIsEqualToSomething() throws Exception {
        // Get already existing entity
        ListWrkStatus idStatus = anodeHist.getIdStatus();
        anodeHistRepository.saveAndFlush(anodeHist);
        Long idStatusId = idStatus.getId();

        // Get all the anodeHistList where idStatus equals to idStatusId
        defaultAnodeHistShouldBeFound("idStatusId.equals=" + idStatusId);

        // Get all the anodeHistList where idStatus equals to idStatusId + 1
        defaultAnodeHistShouldNotBeFound("idStatusId.equals=" + (idStatusId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultAnodeHistShouldBeFound(String filter) throws Exception {
        restAnodeHistMockMvc.perform(get("/api/anode-hists?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(anodeHist.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateFrom").value(hasItem(DEFAULT_DATE_FROM.toString())))
            .andExpect(jsonPath("$.[*].dateTo").value(hasItem(DEFAULT_DATE_TO.toString())))
            .andExpect(jsonPath("$.[*].idWrk").value(hasItem(DEFAULT_ID_WRK.intValue())))
            .andExpect(jsonPath("$.[*].designLife").value(hasItem(DEFAULT_DESIGN_LIFE)))
            .andExpect(jsonPath("$.[*].dmcd").value(hasItem(DEFAULT_DMCD.intValue())))
            .andExpect(jsonPath("$.[*].l1").value(hasItem(DEFAULT_L_1.intValue())))
            .andExpect(jsonPath("$.[*].l2").value(hasItem(DEFAULT_L_2.intValue())))
            .andExpect(jsonPath("$.[*].length").value(hasItem(DEFAULT_LENGTH)))
            .andExpect(jsonPath("$.[*].electrCapac").value(hasItem(DEFAULT_ELECTR_CAPAC.intValue())))
            .andExpect(jsonPath("$.[*].designWeight").value(hasItem(DEFAULT_DESIGN_WEIGHT.intValue())))
            .andExpect(jsonPath("$.[*].weight").value(hasItem(DEFAULT_WEIGHT.intValue())))
            .andExpect(jsonPath("$.[*].isBurial").value(hasItem(DEFAULT_IS_BURIAL)))
            .andExpect(jsonPath("$.[*].chemicalComposition").value(hasItem(DEFAULT_CHEMICAL_COMPOSITION)))
            .andExpect(jsonPath("$.[*].density").value(hasItem(DEFAULT_DENSITY.intValue())))
            .andExpect(jsonPath("$.[*].spacing").value(hasItem(DEFAULT_SPACING)))
            .andExpect(jsonPath("$.[*].coatCutbackLength").value(hasItem(DEFAULT_COAT_CUTBACK_LENGTH)))
            .andExpect(jsonPath("$.[*].coatDmgArea").value(hasItem(DEFAULT_COAT_DMG_AREA.intValue())))
            .andExpect(jsonPath("$.[*].h2sSoil").value(hasItem(DEFAULT_H_2_S_SOIL.intValue())))
            .andExpect(jsonPath("$.[*].remain").value(hasItem(DEFAULT_REMAIN.intValue())))
            .andExpect(jsonPath("$.[*].intFldTemp").value(hasItem(DEFAULT_INT_FLD_TEMP.intValue())))
            .andExpect(jsonPath("$.[*].minPrc").value(hasItem(DEFAULT_MIN_PRC.intValue())))
            .andExpect(jsonPath("$.[*].thickness").value(hasItem(DEFAULT_THICKNESS.intValue())))
            .andExpect(jsonPath("$.[*].coord").value(hasItem(DEFAULT_COORD)))
            .andExpect(jsonPath("$.[*].kpStart").value(hasItem(DEFAULT_KP_START.intValue())))
            .andExpect(jsonPath("$.[*].coatThickness").value(hasItem(DEFAULT_COAT_THICKNESS.intValue())))
            .andExpect(jsonPath("$.[*].kpEnd").value(hasItem(DEFAULT_KP_END.intValue())))
            .andExpect(jsonPath("$.[*].isCurrentFlag").value(hasItem(DEFAULT_IS_CURRENT_FLAG)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].comment").value(hasItem(DEFAULT_COMMENT)))
            .andExpect(jsonPath("$.[*].dateCreate").value(hasItem(DEFAULT_DATE_CREATE.toString())))
            .andExpect(jsonPath("$.[*].dateEdit").value(hasItem(DEFAULT_DATE_EDIT.toString())))
            .andExpect(jsonPath("$.[*].creator").value(hasItem(DEFAULT_CREATOR)))
            .andExpect(jsonPath("$.[*].editor").value(hasItem(DEFAULT_EDITOR)));

        // Check, that the count call also returns 1
        restAnodeHistMockMvc.perform(get("/api/anode-hists/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultAnodeHistShouldNotBeFound(String filter) throws Exception {
        restAnodeHistMockMvc.perform(get("/api/anode-hists?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restAnodeHistMockMvc.perform(get("/api/anode-hists/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingAnodeHist() throws Exception {
        // Get the anodeHist
        restAnodeHistMockMvc.perform(get("/api/anode-hists/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAnodeHist() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        int databaseSizeBeforeUpdate = anodeHistRepository.findAll().size();

        // Update the anodeHist
        AnodeHist updatedAnodeHist = anodeHistRepository.findById(anodeHist.getId()).get();
        // Disconnect from session so that the updates on updatedAnodeHist are not directly saved in db
        em.detach(updatedAnodeHist);
        updatedAnodeHist
            .dateFrom(UPDATED_DATE_FROM)
            .dateTo(UPDATED_DATE_TO)
            .idWrk(UPDATED_ID_WRK)
            .designLife(UPDATED_DESIGN_LIFE)
            .dmcd(UPDATED_DMCD)
            .l1(UPDATED_L_1)
            .l2(UPDATED_L_2)
            .length(UPDATED_LENGTH)
            .electrCapac(UPDATED_ELECTR_CAPAC)
            .designWeight(UPDATED_DESIGN_WEIGHT)
            .weight(UPDATED_WEIGHT)
            .isBurial(UPDATED_IS_BURIAL)
            .chemicalComposition(UPDATED_CHEMICAL_COMPOSITION)
            .density(UPDATED_DENSITY)
            .spacing(UPDATED_SPACING)
            .coatCutbackLength(UPDATED_COAT_CUTBACK_LENGTH)
            .coatDmgArea(UPDATED_COAT_DMG_AREA)
            .h2sSoil(UPDATED_H_2_S_SOIL)
            .remain(UPDATED_REMAIN)
            .intFldTemp(UPDATED_INT_FLD_TEMP)
            .minPrc(UPDATED_MIN_PRC)
            .thickness(UPDATED_THICKNESS)
            .coord(UPDATED_COORD)
            .kpStart(UPDATED_KP_START)
            .coatThickness(UPDATED_COAT_THICKNESS)
            .kpEnd(UPDATED_KP_END)
            .isCurrentFlag(UPDATED_IS_CURRENT_FLAG)
            .description(UPDATED_DESCRIPTION)
            .comment(UPDATED_COMMENT)
            .dateCreate(UPDATED_DATE_CREATE)
            .dateEdit(UPDATED_DATE_EDIT)
            .creator(UPDATED_CREATOR)
            .editor(UPDATED_EDITOR);
        AnodeHistDTO anodeHistDTO = anodeHistMapper.toDto(updatedAnodeHist);

        restAnodeHistMockMvc.perform(put("/api/anode-hists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(anodeHistDTO)))
            .andExpect(status().isOk());

        // Validate the AnodeHist in the database
        List<AnodeHist> anodeHistList = anodeHistRepository.findAll();
        assertThat(anodeHistList).hasSize(databaseSizeBeforeUpdate);
        AnodeHist testAnodeHist = anodeHistList.get(anodeHistList.size() - 1);
        assertThat(testAnodeHist.getDateFrom()).isEqualTo(UPDATED_DATE_FROM);
        assertThat(testAnodeHist.getDateTo()).isEqualTo(UPDATED_DATE_TO);
        assertThat(testAnodeHist.getIdWrk()).isEqualTo(UPDATED_ID_WRK);
        assertThat(testAnodeHist.getDesignLife()).isEqualTo(UPDATED_DESIGN_LIFE);
        assertThat(testAnodeHist.getDmcd()).isEqualTo(UPDATED_DMCD);
        assertThat(testAnodeHist.getl1()).isEqualTo(UPDATED_L_1);
        assertThat(testAnodeHist.getl2()).isEqualTo(UPDATED_L_2);
        assertThat(testAnodeHist.getLength()).isEqualTo(UPDATED_LENGTH);
        assertThat(testAnodeHist.getElectrCapac()).isEqualTo(UPDATED_ELECTR_CAPAC);
        assertThat(testAnodeHist.getDesignWeight()).isEqualTo(UPDATED_DESIGN_WEIGHT);
        assertThat(testAnodeHist.getWeight()).isEqualTo(UPDATED_WEIGHT);
        assertThat(testAnodeHist.getIsBurial()).isEqualTo(UPDATED_IS_BURIAL);
        assertThat(testAnodeHist.getChemicalComposition()).isEqualTo(UPDATED_CHEMICAL_COMPOSITION);
        assertThat(testAnodeHist.getDensity()).isEqualTo(UPDATED_DENSITY);
        assertThat(testAnodeHist.getSpacing()).isEqualTo(UPDATED_SPACING);
        assertThat(testAnodeHist.getCoatCutbackLength()).isEqualTo(UPDATED_COAT_CUTBACK_LENGTH);
        assertThat(testAnodeHist.getCoatDmgArea()).isEqualTo(UPDATED_COAT_DMG_AREA);
        assertThat(testAnodeHist.geth2sSoil()).isEqualTo(UPDATED_H_2_S_SOIL);
        assertThat(testAnodeHist.getRemain()).isEqualTo(UPDATED_REMAIN);
        assertThat(testAnodeHist.getIntFldTemp()).isEqualTo(UPDATED_INT_FLD_TEMP);
        assertThat(testAnodeHist.getMinPrc()).isEqualTo(UPDATED_MIN_PRC);
        assertThat(testAnodeHist.getThickness()).isEqualTo(UPDATED_THICKNESS);
        assertThat(testAnodeHist.getCoord()).isEqualTo(UPDATED_COORD);
        assertThat(testAnodeHist.getKpStart()).isEqualTo(UPDATED_KP_START);
        assertThat(testAnodeHist.getCoatThickness()).isEqualTo(UPDATED_COAT_THICKNESS);
        assertThat(testAnodeHist.getKpEnd()).isEqualTo(UPDATED_KP_END);
        assertThat(testAnodeHist.getIsCurrentFlag()).isEqualTo(UPDATED_IS_CURRENT_FLAG);
        assertThat(testAnodeHist.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testAnodeHist.getComment()).isEqualTo(UPDATED_COMMENT);
        assertThat(testAnodeHist.getDateCreate()).isEqualTo(UPDATED_DATE_CREATE);
        assertThat(testAnodeHist.getDateEdit()).isEqualTo(UPDATED_DATE_EDIT);
        assertThat(testAnodeHist.getCreator()).isEqualTo(UPDATED_CREATOR);
        assertThat(testAnodeHist.getEditor()).isEqualTo(UPDATED_EDITOR);
    }

    @Test
    @Transactional
    public void updateNonExistingAnodeHist() throws Exception {
        int databaseSizeBeforeUpdate = anodeHistRepository.findAll().size();

        // Create the AnodeHist
        AnodeHistDTO anodeHistDTO = anodeHistMapper.toDto(anodeHist);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAnodeHistMockMvc.perform(put("/api/anode-hists")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(anodeHistDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AnodeHist in the database
        List<AnodeHist> anodeHistList = anodeHistRepository.findAll();
        assertThat(anodeHistList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAnodeHist() throws Exception {
        // Initialize the database
        anodeHistRepository.saveAndFlush(anodeHist);

        int databaseSizeBeforeDelete = anodeHistRepository.findAll().size();

        // Delete the anodeHist
        restAnodeHistMockMvc.perform(delete("/api/anode-hists/{id}", anodeHist.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<AnodeHist> anodeHistList = anodeHistRepository.findAll();
        assertThat(anodeHistList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AnodeHist.class);
        AnodeHist anodeHist1 = new AnodeHist();
        anodeHist1.setId(1L);
        AnodeHist anodeHist2 = new AnodeHist();
        anodeHist2.setId(anodeHist1.getId());
        assertThat(anodeHist1).isEqualTo(anodeHist2);
        anodeHist2.setId(2L);
        assertThat(anodeHist1).isNotEqualTo(anodeHist2);
        anodeHist1.setId(null);
        assertThat(anodeHist1).isNotEqualTo(anodeHist2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AnodeHistDTO.class);
        AnodeHistDTO anodeHistDTO1 = new AnodeHistDTO();
        anodeHistDTO1.setId(1L);
        AnodeHistDTO anodeHistDTO2 = new AnodeHistDTO();
        assertThat(anodeHistDTO1).isNotEqualTo(anodeHistDTO2);
        anodeHistDTO2.setId(anodeHistDTO1.getId());
        assertThat(anodeHistDTO1).isEqualTo(anodeHistDTO2);
        anodeHistDTO2.setId(2L);
        assertThat(anodeHistDTO1).isNotEqualTo(anodeHistDTO2);
        anodeHistDTO1.setId(null);
        assertThat(anodeHistDTO1).isNotEqualTo(anodeHistDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(anodeHistMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(anodeHistMapper.fromId(null)).isNull();
    }
}
